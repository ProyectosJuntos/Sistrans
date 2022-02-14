SELECT  'SELECT  NombreColumna, NombreTabla, NumRestricciones 
         FROM        (SELECT    (LENGTH(c.column_name)) AS Lennombre , (LENGTH(c.column_name) - LENGTH(REPLACE((TRANSLATE(c.column_name, ''AEIOU'','' '')), '' '', ''''))) AS Numvocales, c. column_name AS NombreColumna, c.table_name NombreTabla, NVL(r.NumRestricciones,0) AS NumRestricciones 
                      FROM      SYS.all_tab_columns c LEFT OUTER JOIN (SELECT   table_name, column_name, COUNT(constraint_name) AS NumRestricciones
                                                                       FROM     SYS.all_cons_columns
                                                                       WHERE    OWNER=''PARRANDEROS'' 
                                                                       GROUP BY     table_name, column_name
                                                                       ) r ON c.table_name = r.table_name AND
                                                                              c.column_name = r.column_name
                      WHERE     c.OWNER = ''PARRANDEROS'' AND 
                                (LENGTH(c.column_name)) <8 AND 
                                (LENGTH(c.column_name) - LENGTH(REPLACE((TRANSLATE(c.column_name, ''AEIOU'','' '')), '' '', ''''))) >= 2 AND
                                c.table_name = ''' || table_name|| ''');'
FROM        SYS.all_tables
WHERE   owner = 'PARRANDEROS';
