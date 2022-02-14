SELECT  num_bares, ciudad
FROM        (SELECT count(bares.id) AS num_bares, ciudad
             FROM   parranderos.tipo_bebida, parranderos.bebidas, parranderos.sirven, parranderos.bares
             WHERE  tipo_bebida.nombre = 'cerveza' AND 
                    tipo_bebida.id = bebidas.tipo AND
                    bebidas.grado_alcohol > 4 AND 
                    bebidas.grado_alcohol < 6 AND
                    bebidas.id = sirven.id_bebida AND 
                    sirven.id_bar = bares.id
             GROUP BY   ciudad
             )
WHERE   num_bares = (SELECT max(num_bares)
                     FROM (SELECT  count(bares.id) AS num_bares, ciudad
                           FROM parranderos.tipo_bebida, parranderos.bebidas, parranderos.sirven, parranderos.bares
                           WHERE tipo_bebida.nombre = 'cerveza' AND 
                                 tipo_bebida.id = bebidas.tipo AND 
                                 bebidas.grado_alcohol > 4 AND 
                                 bebidas.grado_alcohol < 6 AND 
                                 bebidas.id = sirven.id_bebida AND 
                                 sirven.id_bar = bares.id
                           GROUP BY ciudad
                           )
                     );