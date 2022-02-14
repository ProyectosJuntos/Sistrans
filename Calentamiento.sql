SELECT  ciudad, count(id), presupuesto
FROM        parranderos.bebedores
WHERE   (presupuesto= 'Alto' OR 
         presupuesto = 'Medio'
         )
GROUP BY    ciudad, presupuesto
ORDER BY    ciudad;


