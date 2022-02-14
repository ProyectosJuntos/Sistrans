package uniandes.isis2304.parranderos.persistencia;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Operacion;
import uniandes.isis2304.parranderos.negocio.Usuario;

public class SQLOperacion {
    private final static String SQL = PersistenciaBancandes.SQL;

    private PersistenciaBancandes pp;

    public SQLOperacion(PersistenciaBancandes pp) {
        this.pp = pp;
    }

    public long adicionarOperacion(PersistenceManager pm,BigDecimal id, String tipoUsuarioSeleccionado, String tipoOperacion, Long valor,
            Timestamp fecha, BigDecimal idPuestoAtencion, String tipoIdUsuario, String idUsuario,
            BigDecimal cuenta_origen, BigDecimal cuenta_destino, String tipoIdEmpleado, String idEmpleado){
               Long r = Long.valueOf(1);
        if (tipoUsuarioSeleccionado.equals("Cliente")){
            if (tipoOperacion.equals("Retiro")|| tipoOperacion.equals("Consignacion")){
                Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaOperacion()
            + "(id, tipoOperacion, valor, fecha, tipoIdUsuario, idusuario,cuenta_origen)" + "values (" + id + ", '"+ tipoOperacion + "', "+ valor +", TO_TIMESTAMP('"+ fecha +"', 'YYYY-MM-DD HH24:MI:SS.FF'), '"+ tipoIdUsuario +"', "+ BigDecimal.valueOf(Long.valueOf(idUsuario)) +", "+ cuenta_origen +")");
                return (long) q.executeUnique();
            }
            else if (tipoOperacion.equals("Transferencia")){
                Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaOperacion()
            + "(id, tipoOperacion, valor, fecha, tipoIdUsuario, idusuario,cuenta_origen, cuenta_destino)" + "values (" + id + ", '"+ tipoOperacion + "', "+ valor +", TO_TIMESTAMP('"+ fecha +"', 'YYYY-MM-DD HH24:MI:SS.FF'), '"+ tipoIdUsuario +"', "+ BigDecimal.valueOf(Long.valueOf(idUsuario)) +", "+ cuenta_origen +","+ cuenta_destino +")");
                return (long) q.executeUnique();
            }
        }
        else if (tipoUsuarioSeleccionado.equals("Cajero")){
            if (tipoOperacion.equals("Retiro")|| tipoOperacion.equals("Consignacion")){
                Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaOperacion()
            + "(id, tipoOperacion, valor, fecha, tipoIdUsuario, idusuario,cuenta_origen, idpuestoatencion, tipoidempleado, idempleado )" + "values (" + id + ", '"+ tipoOperacion + "', "+ valor +", TO_TIMESTAMP('"+ fecha +"', 'YYYY-MM-DD HH24:MI:SS.FF'), '"+ tipoIdUsuario +"', "+ BigDecimal.valueOf(Long.valueOf(idUsuario)) +", "+ cuenta_origen +", "+ idPuestoAtencion + ", '"+ tipoIdEmpleado +"',"+ BigDecimal.valueOf(Long.valueOf(idEmpleado)) + " )");
                return (long) q.executeUnique();
            }
            else if (tipoOperacion.equals("Transferencia")){
                Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaOperacion()
            + "(id, tipoOperacion, valor, fecha, tipoIdUsuario, idusuario,cuenta_origen, idpuestoatencion, tipoidempleado, idempleado, cuenta_destino )" + "values (" + id + ", '"+ tipoOperacion + "', "+ valor +", TO_TIMESTAMP('"+ fecha +"', 'YYYY-MM-DD HH24:MI:SS.FF'), '"+ tipoIdUsuario +"', "+ BigDecimal.valueOf(Long.valueOf(idUsuario)) +", "+ cuenta_origen +", "+ idPuestoAtencion + ", '"+ tipoIdEmpleado +"',"+ BigDecimal.valueOf(Long.valueOf(idEmpleado)) + ", "+cuenta_destino+" )");
                return (long) q.executeUnique();
            }
        }
    return r;
    }

    public List<Operacion> darOperacionesSegunClienteGerente(PersistenceManager pm, BigDecimal idCliente, BigDecimal idOficina) {
        String sql = "SELECT * FROM " + pp.darTablaOperacion();
        sql += " WHERE idOficina = ?  AND idCliente = ?";

        Query q = pm.newQuery(SQL, sql);
        q.setParameters(idOficina, idCliente);

        return (List<Operacion>) q.executeList();
    };

    public List<Operacion> darOperacionesSegunCliente(PersistenceManager pm, BigDecimal idCliente) {
        String sql = "SELECT * FROM " + pp.darTablaOperacion();
        sql += " WHERE idCliente = ?";

        Query q = pm.newQuery(SQL, sql);
        q.setParameters(idCliente);

        return (List<Operacion>) q.executeList();
    };

    public List<Operacion> dar10OperacionesOficina(BigDecimal idOficina) {
        String sql = "SELECT * FROM " + pp.darTablaOperacion();
        sql += " WHERE idOficina = ?";

    }

	public List<Operacion> darOperacionestipotransaccion(PersistenceManager pm, String fechainferior, String fechasuperior, String tipoOperacion) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOperacion()+ " WHERE fecha >= " + "'" + fechainferior +" 12:00:00,000000000 AM'" + " AND fecha <= "+ "'" + fechasuperior +" 12:00:00,000000000 AM' AND tipooperacion= "+"'"+ tipoOperacion +"'");
		q.setResultClass(Operacion.class);
		return (List<Operacion>) q.executeList();
    }

	public List<Operacion> darOperacionesvalor(PersistenceManager pm, String fechainferior, String fechasuperior, Long valorInferior, Long valorSuperior) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOperacion()+ " WHERE fecha >= " + "'" + fechainferior +" 12:00:00,000000000 AM'" + " AND fecha <= "+ "'" + fechasuperior + " 12:00:00,000000000 AM' AND valor >= "+ valorInferior+ " AND valor <= "+ valorSuperior);
		q.setResultClass(Operacion.class);
		return (List<Operacion>) q.executeList();
    }

	public List<Operacion> darOperacionesrangoid(PersistenceManager pm, String fechainferior, String fechasuperior, Long idInferior, Long idSuperior) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOperacion()+ " WHERE fecha >= " + "'" + fechainferior +" 12:00:00,000000000 AM'" + " AND fecha <= "+ "'" + fechasuperior + " 12:00:00,000000000 AM' AND id >= "+ idInferior+ " AND id <= "+ idSuperior);
		q.setResultClass(Operacion.class);
		return (List<Operacion>) q.executeList();
    }

    public List<Operacion> darOperacionespuestoatencion(PersistenceManager pm, String fechainferior, String fechasuperior, BigDecimal idPuestoAtencion) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOperacion()+ " WHERE fecha >= " + "'" + fechainferior +" 12:00:00,000000000 AM'" + " AND fecha <= "+ "'" + fechasuperior + " 12:00:00,000000000 AM' AND idpuestoatencion = "+ idPuestoAtencion );
		q.setResultClass(Operacion.class);
        return (List<Operacion>) q.executeList();
    }

    public List<Operacion> darOperacionescuentaorigen(PersistenceManager pm, String fechainferior, String fechasuperior, BigDecimal cuenta_origen) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOperacion()+ " WHERE fecha >= " + "'" + fechainferior +" 12:00:00,000000000 AM'" + " AND fecha <= "+ "'" + fechasuperior + " 12:00:00,000000000 AM' AND cuenta_origen = "+ cuenta_origen );
		q.setResultClass(Operacion.class);
        return (List<Operacion>) q.executeList();
    }

    public List<Operacion> darOperacionescuentadestino(PersistenceManager pm, String fechainferior, String fechasuperior, BigDecimal cuenta_destino) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOperacion()+ " WHERE fecha >= " + "'" + fechainferior +" 12:00:00,000000000 AM'" + " AND fecha <= "+ "'" + fechasuperior + " 12:00:00,000000000 AM' AND cuenta_destino = "+ cuenta_destino);
		q.setResultClass(Operacion.class);
        return (List<Operacion>) q.executeList();
    }

    public List<Operacion> darOperacionesusuario(PersistenceManager pm, String fechainferior, String fechasuperior, String tipoIdUsuario, BigDecimal idUsuario) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOperacion()+ " WHERE fecha >= " + "'" + fechainferior +" 12:00:00,000000000 AM'" + " AND fecha <= "+ "'" + fechasuperior + " 12:00:00,000000000 AM' AND tipoidusuario = " + "'" + tipoIdUsuario + "'" + " AND idusuario = " + idUsuario);
		q.setResultClass(Operacion.class);
        return (List<Operacion>) q.executeList();
    }

    public List<Operacion> darOperacionesempleado(PersistenceManager pm, String fechainferior, String fechasuperior, String tipoIdEmpleado, BigDecimal idEmpleado) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOperacion()+ " WHERE fecha >= " + "'" + fechainferior +" 12:00:00,000000000 AM'" + " AND fecha <= "+ "'" + fechasuperior + " 12:00:00,000000000 AM' AND tipoidempleado = " + "'" + tipoIdEmpleado + "'" + " AND idempleado = " + idEmpleado);
		q.setResultClass(Operacion.class);
        return (List<Operacion>) q.executeList();
    }

    public List<Operacion> darOperacionesNotipotransaccion(PersistenceManager pm, String fechainferior, String fechasuperior, String tipoOperacion) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOperacion()+ " WHERE fecha >= " + "'" + fechainferior +" 12:00:00,000000000 AM'" + " AND fecha <= "+ "'" + fechasuperior +" 12:00:00,000000000 AM' AND tipooperacion <> "+"'"+ tipoOperacion +"'");
		q.setResultClass(Operacion.class);
		return (List<Operacion>) q.executeList();
    }

	public List<Operacion> darOperacionesNovalor(PersistenceManager pm, String fechainferior, String fechasuperior, Long valorInferior, Long valorSuperior) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOperacion()+ " WHERE fecha >= " + "'" + fechainferior +" 12:00:00,000000000 AM'" + " AND fecha <= "+ "'" + fechasuperior + " 12:00:00,000000000 AM' AND ( valor < "+ valorInferior+ " OR valor > "+ valorSuperior + " )");
		q.setResultClass(Operacion.class);
		return (List<Operacion>) q.executeList();
    }

	public List<Operacion> darOperacionesNorangoid(PersistenceManager pm, String fechainferior, String fechasuperior, Long idInferior, Long idSuperior) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOperacion()+ " WHERE fecha >= " + "'" + fechainferior +" 12:00:00,000000000 AM'" + " AND fecha <= "+ "'" + fechasuperior + " 12:00:00,000000000 AM' AND ( id < "+ idInferior+ " OR id > "+ idSuperior +" )");
		q.setResultClass(Operacion.class);
		return (List<Operacion>) q.executeList();
    }

    public List<Operacion> darOperacionesNopuestoatencion(PersistenceManager pm, String fechainferior, String fechasuperior, BigDecimal idPuestoAtencion) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOperacion()+ " WHERE fecha >= " + "'" + fechainferior +" 12:00:00,000000000 AM'" + " AND fecha <= "+ "'" + fechasuperior + " 12:00:00,000000000 AM' AND idpuestoatencion <> "+ idPuestoAtencion );
		q.setResultClass(Operacion.class);
        return (List<Operacion>) q.executeList();
    }

    public List<Operacion> darOperacionesNocuentaorigen(PersistenceManager pm, String fechainferior, String fechasuperior, BigDecimal cuenta_origen) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOperacion()+ " WHERE fecha >= " + "'" + fechainferior +" 12:00:00,000000000 AM'" + " AND fecha <= "+ "'" + fechasuperior + " 12:00:00,000000000 AM' AND cuenta_origen <> "+ cuenta_origen );
		q.setResultClass(Operacion.class);
        return (List<Operacion>) q.executeList();
    }

    public List<Operacion> darOperacionesNocuentadestino(PersistenceManager pm, String fechainferior, String fechasuperior, BigDecimal cuenta_destino) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOperacion()+ " WHERE fecha >= " + "'" + fechainferior +" 12:00:00,000000000 AM'" + " AND fecha <= "+ "'" + fechasuperior + " 12:00:00,000000000 AM' AND cuenta_destino <> "+ cuenta_destino);
		q.setResultClass(Operacion.class);
        return (List<Operacion>) q.executeList();
    }

    public List<Operacion> darOperacionesNousuario(PersistenceManager pm, String fechainferior, String fechasuperior, String tipoIdUsuario, BigDecimal idUsuario) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOperacion()+ " WHERE fecha >= " + "'" + fechainferior +" 12:00:00,000000000 AM'" + " AND fecha <= "+ "'" + fechasuperior + " 12:00:00,000000000 AM' AND NOT (tipoidusuario = " + "'" + tipoIdUsuario + "'" + " AND idusuario = " + idUsuario +")");
		q.setResultClass(Operacion.class);
        return (List<Operacion>) q.executeList();
    }

    public List<Operacion> darOperacionesNoempleado(PersistenceManager pm, String fechainferior, String fechasuperior, String tipoIdEmpleado, BigDecimal idEmpleado) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOperacion()+ " WHERE fecha >= " + "'" + fechainferior +" 12:00:00,000000000 AM'" + " AND fecha <= "+ "'" + fechasuperior + " 12:00:00,000000000 AM' AND NOT (tipoidempleado = " + "'" + tipoIdEmpleado + "'" + " AND idempleado = " + idEmpleado +")");
		q.setResultClass(Operacion.class);
        return (List<Operacion>) q.executeList();
    }

    public List<Operacion> darConsignaciones(PersistenceManager pm, Long valorX, String tipoProducto) {
        String tipo = null;
        if (tipoProducto.equals("Prestamo")){
            tipo = pp.darTablaPrestamo();
        } else if (tipoProducto.equals("Cuenta")){
            tipo = pp.darTablaCuenta();
        } else if (tipoProducto.equals("Cuenta juridica")){
            tipo = pp.darTablaCuentaJuridica();
        }
        Query q = pm.newQuery(SQL, "SELECT  o.* FROM b_usuario u JOIN b_producto p ON (u.tipoid = p.tipoIdUsuario AND u.id = p.idUsuario) JOIN " + tipo + " c ON p.id = c.id JOIN b_operacion o ON (o.tipoidUsuario = u.tipoid AND o.idusuario = u.id) WHERE valor > " + valorX + " AND o.tipooperacion = 'Consignacion'");
		q.setResultClass(Operacion.class);
        return (List<Operacion>) q.executeList();
    }

    public List<Operacion> darPatencionoperaciones(PersistenceManager pm, BigDecimal idPatencion1, BigDecimal idPatencion2) {
        Query q = pm.newQuery(SQL, "SELECT o.* FROM b_usuario u JOIN b_operacion o ON o.tipoidusuario = u.tipoid AND o.idusuario = u.id WHERE o.idpuestoatencion = " + idPatencion1 + " OR o.idpuestoatencion = " + idPatencion2 + "ORDER BY u.id");
		q.setResultClass(Operacion.class);
        return (List<Operacion>) q.executeList();
    }

}
