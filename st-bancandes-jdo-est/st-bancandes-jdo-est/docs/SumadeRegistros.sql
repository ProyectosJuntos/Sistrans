WITH usuarios as (SELECT COUNT(*) cu FROM B_USUARIO), patencion as (SELECT COUNT(*) cpa FROM B_PUESTOATENCION),  productos as (SELECT COUNT(*) cp FROM B_PRODUCTO),
prestamos as (SELECT COUNT(*) cpr FROM B_PRESTAMO), operaciones as (SELECT COUNT(*) co FROM B_OPERACION), oficinas as (SELECT COUNT(*) cof FROM B_OFICINA),
empleados as (SELECT COUNT(*) ce FROM B_EMPLEADO), cuentajuridicacuentas as (SELECT COUNT(*) cjc FROM B_CUENTAJURIDICA_CUENTA), cuentasjuridicas as (SELECT COUNT(*) cj FROM B_CUENTAJURIDICA),
cuentas as (SELECT COUNT(*) cc FROM B_CUENTA), cdts as (SELECT COUNT(*) ccd FROM B_CDT), afcs as (SELECT COUNT(*) caf FROM B_AFC), acciones as (SELECT COUNT(*) ca FROM B_ACCION)
SELECT cu+cpa+cp+cpr+co+cof+ce+cjc+cj+cc+ccd+caf+ca
FROM usuarios, patencion, productos, prestamos, operaciones, oficinas, empleados, cuentajuridicacuentas, cuentasjuridicas, cuentas, cdts, afcs, acciones