SELECT  r.table_name AS NombreTabla, r.column_name AS NombreColsPK, c.data_type AS TipoDeDato
FROM        SYS.all_cons_columns r INNER JOIN SYS.all_tab_columns c ON c.table_name = r.table_name AND
                                                                       c.column_name = r.column_name
WHERE   r.owner = 'PARRANDEROS' AND 
        r.constraint_name LIKE 'PK%' AND 
        c.owner = 'PARRANDEROS'
ORDER BY    NombreTabla ASC, NombreColsPK ASC;