package uniandes.isis2304.parranderos.persistencia;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Prestamo;

public class SQLPrestamo {
    private final static String SQL = PersistenciaBancandes.SQL;

    private PersistenciaBancandes pp;

    public SQLPrestamo(PersistenciaBancandes pp) {
        this.pp = pp;
    }

    public long adicionarPrestamo(PersistenceManager pm, BigDecimal id, Long valorActual, Timestamp fechaCrecion,
            BigDecimal oficina, BigDecimal usuario, String tipoIdUsuario, Long cuotaMinima) {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPrestamo()
                + "(id, valorActual, fechaCrecion, oficina, usuario, tipoIdUsuario, estado, cuotaMinima) values (?, ?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(id, valorActual, fechaCrecion, oficina, usuario, tipoIdUsuario, "ACTIVA", cuotaMinima);
        return (long) q.executeUnique();
    }

    public long operarPrestamo(PersistenceManager pm, BigDecimal id, Long valorPagado) {
        Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaPrestamo()
                + "SET valorActual = valorActual - ? WHERE id = ? AND estado = ? AND cuotaMinima <= ?");
        q.setParameters(valorPagado, id, "ACTIVA", valorPagado);
        return (long) q.executeUnique();
    }

    public long cerrarPrestamo(PersistenceManager pm, BigDecimal id) {
        Query q = pm.newQuery(SQL, "SELECT valorActual FROM " + pp.darTablaPrestamo() + " WHERE id = ?");
        q.setParameters(id);
        long resul = 0;
        Long valor = (long) q.executeUnique();
        if (valor != Long.valueOf(0)) {
            resul = -1;
        } else {
            Query q2 = pm.newQuery(SQL, "UPDATE " + pp.darTablaPrestamo() + " SET estado = INACTIVA WHERE id = ?");
            q2.setParameters(id);
            resul = (long) q2.executeUnique();
        }
        return resul;
    }

    public List<Prestamo> darPrestamosCliente(PersistenceManager pm, String tipoIdUsuario, BigDecimal idUsuario) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPrestamo() + " JOIN " + pp.darTablaProducto() + " ON  b_producto.id = b_prestamo.id WHERE b_producto.idUsuario = " + idUsuario + " AND b_producto.tipoIdUsuario = '" + tipoIdUsuario + "'" );
		q.setResultClass(Prestamo.class);
		return (List<Prestamo>) q.executeList();
    }

    public List<Prestamo> darPrestamosOficina(PersistenceManager pm, String tipoIdUsuario, BigDecimal idUsuario) {
        Query a = pm.newQuery(SQL, "SELECT idOficina FROM B_EMPLEADO WHERE tipoId = '" + tipoIdUsuario + "' AND id = " + idUsuario);
        a.setResultClass(String.class);
        String ahh = (String) a.executeUnique();
        BigDecimal aa = BigDecimal.valueOf(Long.valueOf(ahh));

        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPrestamo() + " JOIN " + pp.darTablaProducto() + " ON  b_producto.id = b_prestamo.id WHERE b_producto.idOficina = " + aa);
		q.setResultClass(Prestamo.class);
		return (List<Prestamo>) q.executeList();
    }

    public List<Prestamo> darPrestamos(PersistenceManager pm) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPrestamo() + " JOIN " + pp.darTablaProducto() + " ON  b_producto.id = b_prestamo.id");
		q.setResultClass(Prestamo.class);
		return (List<Prestamo>) q.executeList();
    }
}
