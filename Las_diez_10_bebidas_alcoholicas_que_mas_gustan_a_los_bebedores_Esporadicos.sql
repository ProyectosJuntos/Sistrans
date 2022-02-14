SELECT  * 
FROM        (SELECT   DISTINCT bbd.id, bbd.nombre, tbbd.nombre AS tipo, contadorBebida AS numApariciones
             FROM     PARRANDEROS.bebidas bbd, PARRANDEROS.tipo_bebida tbbd, (SELECT     DISTINCT gus.id_bebida, gus.id_bebedor, COUNT(gus.id_bebida) AS contadorBebida 
                                                                              FROM       PARRANDEROS.gustan gus, (SELECT   *
                                                                                                                  FROM     (SELECT   avg(visitas) AS visitas, id_bebedor AS id_espo
                                                                                                                            FROM     (SELECT     (EXTRACT (YEAR FROM fecha_ultima_visita)) AS anios, count (fecha_ultima_visita) AS visitas, id_bebedor
                                                                                                                                      FROM       parranderos.frecuentan
                                                                                                                                      GROUP BY   (EXTRACT (YEAR FROM fecha_ultima_visita)), id_bebedor
                                                                                                                                      )
                                                                                                                            GROUP BY id_bebedor
                                                                                                                            )
                                                                                                                  WHERE    visitas < 15
                                                                                                                  )
                                                                              WHERE      gus.id_bebedor = id_espo
                                                                              GROUP      BY id_bebedor, id_bebida
                                                                              )
             WHERE    tbbd.id = bbd.tipo AND 
                      bbd.id = id_bebida
             ORDER BY contadorBebida DESC
             )
WHERE      ROWNUM <= 10;
