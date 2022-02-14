SELECT  t.table_name AS NombreTabla,  COUNT(c.table_name) AS NumColumnas,  n.NumColsNull , t.last_analyzed AS Ultimo_Analisis
FROM        SYS.ALL_TABLES t INNER JOIN SYS.all_tab_columns c ON t.table_name = c.table_name INNER JOIN (SELECT table_name, COUNT(column_name) AS NumColsNull 
                                                                                                         FROM   SYS.all_tab_columns
                                                                                                         WHERE  OWNER = 'PARRANDEROS' AND
                                                                                                                nullable = 'Y' 
                                                                                                         GROUP BY   table_name
                                                                                                         ORDER BY   table_name
                                                                                                         ) n ON n.table_name = c.table_name 
WHERE   t.OWNER = 'PARRANDEROS' AND
        c.owner = 'PARRANDEROS'
GROUP BY        t.table_name, t.last_analyzed, NumColsNull
ORDER BY        NombreTabla;
