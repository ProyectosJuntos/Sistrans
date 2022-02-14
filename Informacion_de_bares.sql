SELECT  bares.ciudad, bares.nombre, count(tabla.id_bebedor) AS num_bebedores
FROM        (SELECT COUNT(gustan.id_bebida) AS cantidad , id_bebedor
             FROM   parranderos.bebedores, parranderos.gustan, parranderos.bebidas
             WHERE  bebidas.grado_alcohol >10 AND 
                    bebedores.presupuesto = 'Alto' AND 
                    bebidas.id = gustan.id_bebida AND 
                    gustan.id_bebedor = bebedores.id
             GROUP BY       id_bebedor
              HAVING    COUNT(gustan.id_bebida) >= 2 AND 
                        COUNT(gustan.id_bebida) <= 5
             ) tabla, parranderos.bares, parranderos.frecuentan
WHERE   tabla.id_bebedor = frecuentan.id_bebedor AND 
        frecuentan.id_bar = bares.id
GROUP BY    bares.nombre, bares.ciudad
ORDER BY    ciudad, nombre, num_bebedores;