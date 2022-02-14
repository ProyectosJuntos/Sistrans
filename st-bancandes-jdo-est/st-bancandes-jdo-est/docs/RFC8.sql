SELECT * FROM B_operacion WHERE fecha >= '28/04/01' AND fecha <= '29/04/01' AND tipooperacion <> 'Retiro';
SELECT * FROM B_operacion WHERE fecha >= '28/04/01' AND fecha <= '15/07/01' AND tipooperacion <> 'Retiro';

SELECT * FROM B_operacion WHERE fecha >= '28/04/01' AND fecha <= '15/07/01' AND tipooperacion <> 'Retiro';
SELECT * FROM B_operacion WHERE fecha >= '28/04/01' AND fecha <= '15/07/02' AND tipooperacion <> 'Consignacion';

SELECT * FROM B_operacion WHERE fecha >= '28/04/01' AND fecha <= '15/07/10' AND (valor < -9000000 OR valor > 9000000);
SELECT * FROM B_operacion WHERE fecha >= '28/04/01' AND fecha <= '15/07/10' AND (valor < -9900000 OR valor > 9900000);

SELECT * FROM B_operacion WHERE fecha >= '28/04/01' AND fecha <= '15/07/01' AND (id < 5 OR id > 184980);
SELECT * FROM B_operacion WHERE fecha >= '28/04/01' AND fecha <= '15/07/01' AND (id < 50000 OR id > 184000);

SELECT * FROM B_operacion WHERE fecha >=  '28/04/05'  AND fecha <=  '15/07/05'   AND idpuestoatencion <> 5139;
SELECT * FROM B_operacion WHERE fecha >=  '28/04/05'  AND fecha <=  '15/07/05'   AND idpuestoatencion <> 1;

SELECT * FROM B_operacion WHERE fecha >=  '28/04/01'  AND fecha <=  '15/07/01'   AND cuenta_origen <> 203848;
SELECT * FROM B_operacion WHERE fecha >=  '28/04/01'  AND fecha <=  '15/07/01'   AND cuenta_origen <> 1;

SELECT * FROM B_operacion WHERE fecha >=  '28/04/05'  AND fecha <=  '15/07/05'   AND cuenta_destino <> 205123;
SELECT * FROM B_operacion WHERE fecha >=  '28/04/05'  AND fecha <=  '15/07/05'   AND cuenta_destino <> 28;

SELECT * FROM B_operacion WHERE fecha >=  '28/04/05'  AND fecha <=  '15/07/05' AND NOT (tipoidusuario =  'CI' AND idusuario = 19221);
SELECT * FROM B_operacion WHERE fecha >=  '28/04/05'  AND fecha <=  '15/07/05' AND NOT (tipoidusuario =  'CC' AND idusuario = 28);

SELECT * FROM B_operacion WHERE fecha >=  '28/04/05'  AND fecha <=  '15/07/05' AND NOT (tipoidempleado =  'CC' AND idempleado = 16443);
SELECT * FROM B_operacion WHERE fecha >=  '28/04/05'  AND fecha <=  '15/07/05' AND NOT (tipoidempleado =  'CC' AND idempleado = 15);

