INSERT INTO B_USUARIO VALUES  ('CC', 10, 'Natural', 'juan1', '12345678',  'Juan Cliente', 'Colombiano', 'Calle 6d Sur', 'juan@uniandes.edu.co', '3165674893', 'Bogota','Bogota', '1234');
INSERT INTO B_USUARIO VALUES  ('CC', 11, 'Natural', 'juan2', '12345678',  'Juan Cliente', 'Colombiano', 'Calle 6d Sur', 'juan@uniandes.edu.co', '3165674893', 'Bogota','Bogota', '1234');
INSERT INTO B_USUARIO VALUES  ('CC', 12, 'Natural', 'juan3', '12345678',  'Juan Cliente', 'Colombiano', 'Calle 6d Sur', 'juan@uniandes.edu.co', '3165674893', 'Bogota','Bogota', '1234');
INSERT INTO B_USUARIO VALUES  ('CC', 13, 'Natural', 'juan4', '12345678',  'Juan Cliente', 'Colombiano', 'Calle 6d Sur', 'juan@uniandes.edu.co', '3165674893', 'Bogota','Bogota', '1234');
INSERT INTO B_USUARIO VALUES  ('CC', 14, 'Natural', '123', '123',  'Juan Cliente', 'Colombiano', 'Calle 6d Sur', 'juan@uniandes.edu.co', '3165674893', 'Bogota','Bogota', '1234');
INSERT INTO B_USUARIO VALUES  ('CC', 16, 'Natural', '1234', '1234',  'Juan Cliente', 'Colombiano', 'Calle 6d Sur', 'juan@uniandes.edu.co', '3165674893', 'Bogota','Bogota', '1234');
INSERT INTO B_USUARIO VALUES  ('CC', 1, 'Natural', 'ja.galeanoc1', '12345678', 'Juliana Galeano', 'Colombiana', 'Calle 6a Sur', 'ja.galeanoc1@uniandes.edu.co', '3164880766', 'Bogota','Bogota', '1234');
INSERT INTO B_USUARIO VALUES ('CC', 2, 'Natural', 'c.gomez', '12345678', 'Camila Gomez', 'Colombiana', 'Calle 6b Sur', 'c.gomez@uniandes.edu.co', '3169824563', 'Santa Marta','a', '1234');
INSERT INTO B_USUARIO VALUES ('CC', 28, 'Natural', 'w.mendez', '12345678', 'William Mendez', 'Colombiano', 'Calle 6c Sur', 'w.mendez@uniandes.edu.co', '31364758903', 'Bogota','Bogota', '1234');
INSERT INTO B_USUARIO VALUES  ('CC', 15, 'Natural', 'a.galeanoc1', '12345678',  'Andrea Galeano', 'Colombiana', 'Calle 6a Sur', 'a.galeanoc1@uniandes.edu.co', '3164880766', 'Bogota','Bogota', '1234');
INSERT INTO B_EMPLEADO (TIPOID, ID, TIPOEMPLEADO) VALUES ('CC',2,'Cajero');
INSERT INTO B_EMPLEADO (TIPOID, ID,TIPOEMPLEADO) VALUES ('CC',28,'Gerente oficina');
INSERT INTO B_EMPLEADO (TIPOID, ID,TIPOEMPLEADO) VALUES ('CC',1,'Administrador');
INSERT INTO B_EMPLEADO (TIPOID, ID,TIPOEMPLEADO) VALUES ('CC',16,'Administrador');
INSERT INTO B_EMPLEADO (TIPOID, ID,TIPOEMPLEADO) VALUES ('CC', 15,'Gerente general');
INSERT INTO B_OFICINA VALUES (4,'Oficina1','aaaa', 1, 'CC', 28);
INSERT INTO B_PUESTOATENCION VALUES (5,'ATM', 'bbb','CC', 2, 4);
UPDATE B_EMPLEADO SET idpuestoatencion= 5 WHERE id=2;
UPDATE B_EMPLEADO SET IDOFICINA= 4 WHERE id=28;
INSERT INTO B_PRODUCTO (ID, ESTADO, VALORACTUAL, FECHACREACION, IDOFICINA, TIPOIDUSUARIO, IDUSUARIO) VALUES (100, 'Activa', 0, TIMESTAMP '2021-03-24 11:59:59', 4, 'CC', 10);
INSERT INTO B_CUENTA VALUES (100, 'Ahorros');
INSERT INTO B_PRODUCTO (ID, ESTADO, VALORACTUAL, FECHACREACION, IDOFICINA, TIPOIDUSUARIO, IDUSUARIO) VALUES (101, 'Activa', 100, TIMESTAMP '2021-03-22 11:59:59', 4, 'CC', 11);
INSERT INTO B_CUENTA VALUES (101, 'Ahorros');
SELECT * FROM B_OPERACION;
SELECT * FROM B_OPERACION WHERE id = &id;










