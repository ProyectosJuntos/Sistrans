SELECT  tabla_maximos.nombre, tabla_todos.ciudad, tabla_todos.horario, tabla_maximos.num_bebedores
FROM        (SELECT DISTINCT nombre, MAX(num_bebedores) AS num_bebedores
             FROM   (SELECT COUNT(frecuentan.id_bebedor) AS num_bebedores, frecuentan.horario, bares.nombre, bares.ciudad
                     FROM   parranderos.bebidas, parranderos.gustan, parranderos.frecuentan,  parranderos.bebedores, parranderos.bares, parranderos.tipo_bebida
                     WHERE  tipo_bebida.nombre = '&tipo_bebida' AND 
                            tipo_bebida.id = bebidas.tipo AND
                            bebidas.id = gustan.id_bebida AND 
                            gustan.id_bebedor = frecuentan.id_bebedor AND 
                            frecuentan.id_bebedor  = bebedores.id AND 
                            bares.id = frecuentan.id_bar AND 
                            bebedores.ciudad = bares.ciudad
                     GROUP BY   horario, bares.nombre, bares.ciudad 
                     ) 
             GROUP BY   nombre
             ) tabla_maximos, (SELECT   COUNT(frecuentan.id_bebedor) AS num_bebedores, frecuentan.horario, bares.nombre, bares.ciudad
                               FROM     parranderos.bebidas, parranderos.gustan, parranderos.frecuentan,  parranderos.bebedores, parranderos.bares, parranderos.tipo_bebida
                               WHERE    tipo_bebida.nombre = 'gaseosa' AND 
                                        tipo_bebida.id= bebidas.tipo AND
                                        bebidas.id = gustan.id_bebida AND 
                                        gustan.id_bebedor = frecuentan.id_bebedor AND 
                                        frecuentan.id_bebedor  = bebedores.id AND 
                                        bares.id = frecuentan.id_bar AND 
                                        bebedores.ciudad = bares.ciudad
                               GROUP BY     horario, bares.nombre, bares.ciudad 
                               ) tabla_todos
WHERE   tabla_maximos.nombre = tabla_todos.nombre AND
        tabla_maximos.num_bebedores = tabla_todos.num_bebedores 
ORDER BY        nombre ASC;