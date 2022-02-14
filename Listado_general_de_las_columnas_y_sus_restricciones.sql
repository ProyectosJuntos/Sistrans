SELECT  c.table_name AS NombreTabla, c.column_name AS NombreColumna, c.data_type AS TipoDeDato,  NVL(r.constraint_name, 'NO TIENE') AS NombreRestriccion, c.nullable AS PermiteNulos
FROM        SYS.all_tab_columns c LEFT OUTER JOIN (SELECT  c.column_name, r.constraint_name, r.table_name, c.column_id
                                                   FROM    SYS.all_cons_columns r INNER  JOIN   SYS.all_tab_columns c ON r.table_name = c.table_name AND 
                                                                                                                         c.column_name = r.column_name
                                                   WHERE   r.owner = 'PARRANDEROS' AND 
                                                           c.owner = 'PARRANDEROS') r ON c.column_id = r.column_id AND 
                                                           r.table_name = c.table_name
WHERE  c.OWNER = 'PARRANDEROS'
ORDER BY    NombreTabla ASC, NombreColumna ASC, NombreRestriccion ASC;