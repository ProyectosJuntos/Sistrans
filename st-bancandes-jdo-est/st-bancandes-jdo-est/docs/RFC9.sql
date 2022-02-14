SELECT  o.*
FROM b_usuario u JOIN b_producto p ON (u.tipoid = p.tipoIdUsuario AND u.id = p.idUsuario) JOIN b_prestamo c ON p.id = c.id JOIN b_operacion o ON (o.tipoidUsuario = u.tipoid AND o.idusuario = u.id)
WHERE valor > 9998582 AND o.tipooperacion = 'Consignacion';

SELECT  o.*
FROM b_usuario u JOIN b_producto p ON (u.tipoid = p.tipoIdUsuario AND u.id = p.idUsuario) JOIN b_prestamo c ON p.id = c.id JOIN b_operacion o ON (o.tipoidUsuario = u.tipoid AND o.idusuario = u.id)
WHERE valor > 9800000 AND o.tipooperacion = 'Consignacion';

SELECT  o.*
FROM b_usuario u JOIN b_producto p ON (u.tipoid = p.tipoIdUsuario AND u.id = p.idUsuario) JOIN b_cuenta c ON p.id = c.id JOIN b_operacion o ON (o.tipoidUsuario = u.tipoid AND o.idusuario = u.id)
WHERE valor > 9800000 AND o.tipooperacion = 'Consignacion';

SELECT  o.*
FROM b_usuario u JOIN b_producto p ON (u.tipoid = p.tipoIdUsuario AND u.id = p.idUsuario) JOIN b_cuentajuridica c ON p.id = c.id JOIN b_operacion o ON (o.tipoidUsuario = u.tipoid AND o.idusuario = u.id)
WHERE valor > 9800000 AND o.tipooperacion = 'Consignacion';