SELECT  *
FROM        (SELECT     bebidas.id, bebidas.nombre, (tbebidas.veces + tsirven.veces+tgustan.veces) AS apariciones, bebidas.grado_alcohol
             FROM       (SELECT    count(id) AS veces, id
                         FROM      parranderos.bebidas
                         GROUP BY bebidas. id
                         ) tbebidas,
                        (SELECT    count(id_bebida) AS veces ,id_bebida AS id
                         FROM      parranderos.sirven
                         GROUP BY id_bebida
                         ) tsirven, 
                        (SELECT    count(id_bebida) AS veces, id_bebida AS id
                         FROM      parranderos.gustan
                         GROUP BY id_bebida
                         ) tgustan, parranderos.bebidas
             WHERE      bebidas.id = tbebidas.id AND 
                        tbebidas.id = tsirven.id AND  
                        tsirven.id = tgustan.id 
             GROUP BY   bebidas.id, bebidas.Nombre, bebidas.grado_alcohol, (tbebidas.veces + tsirven.veces + tgustan.veces)
             ORDER BY   apariciones DESC,  grado_alcohol)
WHERE   ROWNUM <=10;
