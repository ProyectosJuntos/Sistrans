SELECT * FROM B_operacion WHERE fecha >= '28/04/01' AND fecha <= '15/07/03' AND tipooperacion = 'Retiro';
SELECT * FROM B_operacion WHERE fecha >= '28/04/01' AND fecha <= '15/07/01' AND tipooperacion = 'Retiro';

SELECT * FROM B_operacion WHERE fecha >= '28/04/01' AND fecha <= '15/07/01' AND tipooperacion = 'Transferencia';
SELECT * FROM B_operacion WHERE fecha >= '28/04/01' AND fecha <= '15/07/01' AND tipooperacion = 'Retiro';

SELECT * FROM B_operacion WHERE fecha >= '28/04/01' AND fecha <= '15/07/02' AND valor >= 10000 AND valor <= 400000 ;
SELECT * FROM B_operacion WHERE fecha >= '28/04/01' AND fecha <= '15/07/02' AND valor >= 10000 AND valor <= 20000 ;

SELECT * FROM B_operacion WHERE fecha >= '28/04/01' AND fecha <= '15/07/02' AND id >= 100 AND id <= 400;
SELECT * FROM B_operacion WHERE fecha >= '28/04/01' AND fecha <= '15/07/02' AND id >= 100 AND id <= 40000;

SELECT * FROM B_operacion WHERE fecha >= '28/04/01' AND fecha <= '15/07/03' AND idpuestoatencion = 434;
SELECT * FROM B_operacion WHERE fecha >= '28/04/01' AND fecha <= '15/07/03' AND idpuestoatencion = 486;

SELECT * FROM B_operacion WHERE fecha >= '28/04/99' AND fecha <= '15/07/21' AND cuenta_origen = 512;
SELECT * FROM B_operacion WHERE fecha >= '28/04/99' AND fecha <= '15/07/21' AND cuenta_origen = 67;

SELECT * FROM B_operacion WHERE fecha >= '28/04/99' AND fecha <= '15/07/21' AND cuenta_destino = 23;
SELECT * FROM B_operacion WHERE fecha >= '28/04/99' AND fecha <= '15/07/21' AND cuenta_destino = 53;

SELECT * FROM B_operacion WHERE fecha >= '28/04/99' AND fecha <= '15/07/21' AND tipoidusuario = 'CI' AND idUsuario = 3;
SELECT * FROM B_operacion WHERE fecha >= '28/04/99' AND fecha <= '15/07/21' AND tipoidusuario = 'CC' AND idUsuario = 75;

SELECT * FROM B_operacion WHERE fecha >= '28/04/99' AND fecha <= '15/07/21' AND tipoidempleado = 'TP' AND idempleado = 8;
SELECT * FROM B_operacion WHERE fecha >= '28/04/99' AND fecha <= '15/07/21' AND tipoidempleado = 'CE' AND idempleado = 691;
