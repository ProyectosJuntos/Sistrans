SELECT u.*
FROM b_usuario u JOIN b_operacion o ON o.tipoidusuario = u.tipoid AND o.idusuario = u.id
WHERE o.idpuestoatencion = 74 OR o.idpuestoatencion = 28
ORDER BY u.id;
SELECT o.*
FROM b_usuario u JOIN b_operacion o ON o.tipoidusuario = u.tipoid AND o.idusuario = u.id
WHERE o.idpuestoatencion = 74 OR o.idpuestoatencion = 28
ORDER BY u.id;

SELECT u.*
FROM b_usuario u JOIN b_operacion o ON o.tipoidusuario = u.tipoid AND o.idusuario = u.id
WHERE o.idpuestoatencion = 28 OR o.idpuestoatencion = 28
ORDER BY u.id;
SELECT o.*
FROM b_usuario u JOIN b_operacion o ON o.tipoidusuario = u.tipoid AND o.idusuario = u.id
WHERE o.idpuestoatencion = 28 OR o.idpuestoatencion = 28
ORDER BY u.id;
