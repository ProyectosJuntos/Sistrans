SELECT  c.table_name AS NombreTabla, c.data_type AS TipoDeDato, COUNT(c.data_type) AS NumColsTipoDato, ROUND ((AVG(c.avg_col_len)), 2) AS PromedioLongitudCol
FROM        SYS.all_tab_columns c
WHERE   c.OWNER = 'PARRANDEROS'
GROUP BY    c.table_name, c.data_type
ORDER BY    NombreTabla ASC, TipoDeDato ASC, NumColsTipoDato ASC;