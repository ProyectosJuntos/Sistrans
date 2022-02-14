package uniandes.isis2304.parranderos.persistencia;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Cuenta;
import uniandes.isis2304.parranderos.negocio.Producto;

public class SQLCuenta {
    private final static String SQL = PersistenciaBancandes.SQL;

    private PersistenciaBancandes pp;
    private SQLProducto sqlProducto;


    public SQLCuenta(PersistenciaBancandes pp, SQLProducto sqlProducto
    ) {
        this.pp = pp;
        this.sqlProducto = sqlProducto;
    }

    public long adicionarCuenta(PersistenceManager pm, BigDecimal id, Long valorActual, Timestamp fechaCrecion,
            Timestamp fechaUltimoMov, BigDecimal oficina, BigDecimal usuario, String tipoIdUsuario,
            LinkedList<Object[]> operaciones, String estado, String tipoCuenta) {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaCuenta()
                + "(id, valorActual, fechaCrecion, fechaUltimoMov, oficina, usuario, tipoIdUsuario, operaciones, estado, tipoCuenta) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(id, valorActual, fechaCrecion, fechaUltimoMov, oficina, usuario, tipoIdUsuario, operaciones,
                estado, tipoCuenta);
        return (long) q.executeUnique();
    }

    public long cambiarEstado(PersistenceManager pm, BigDecimal id, String estado, BigDecimal idOficina) {
        Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaCuenta() + " SET estado = ? WHERE id = ? AND idOficina = ?");
        q.setParameters(estado, id, idOficina);
        return (long) q.executeUnique();
    }

    public long cambiarSaldo(PersistenceManager pm, BigDecimal id, Long saldoNuevo) {
        Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaCuenta() + " SET valorActual = ? WHERE id = ?");
        q.setParameters(saldoNuevo, id);
        return (long) q.executeUnique();
    }

    public long operacionCuenta(PersistenceManager pm, BigDecimal id, Long cambioSaldo) {
        Query q = pm.newQuery(SQL,
                "UPDATE " + pp.darTablaCuenta() + " c JOIN " + pp.darTablaProducto() + " p ON c.id = p.id SET p.valorActual = p.valorActual + ( ? ) WHERE c.id = ? AND estado = p.?");
        q.setParameters(cambioSaldo, id, "ACTIVA");
        return (long) q.executeUnique();
    };

    public List<Cuenta> darCuentasCliente(PersistenceManager pm, BigDecimal idCliente) {
        String sql = "SELECT * FROM " + pp.darTablaCuenta();
        sql += " WHERE usuario = ? ";

        Query q = pm.newQuery(SQL, sql);
        q.setParameters(idCliente);

        return (List<Cuenta>) q.executeList();
    };

    public List<Cuenta> darCuentasGerente(PersistenceManager pm, BigDecimal idOficina) {
        String sql = "SELECT * FROM " + pp.darTablaCuenta();
        sql += " WHERE idOficina = ? ";

        Query q = pm.newQuery(SQL, sql);
        q.setParameters(idOficina);

        return (List<Cuenta>) q.executeList();
    };

    public List<Cuenta> darCuentasSegunClienteGerente(PersistenceManager pm, BigDecimal idCliente, BigDecimal idOficina) {
        String sql = "SELECT * FROM " + pp.darTablaCuenta();
        sql += " WHERE idOficina = ?  AND idCliente = ?";

        Query q = pm.newQuery(SQL, sql);
        q.setParameters(idOficina, idCliente);

        return (List<Cuenta>) q.executeList();
    };

    public List<Cuenta> darCuentasAdmin(PersistenceManager pm) {
        String sql = "SELECT * FROM " + pp.darTablaCuenta();

        Query q = pm.newQuery(SQL, sql);

        return (List<Cuenta>) q.executeList();
    };

    public List<Cuenta> darCuentasSegunClienteAdmin(PersistenceManager pm, BigDecimal idCliente) {
        String sql = "SELECT * FROM " + pp.darTablaCuenta();
        sql += " WHERE idCliente = ?";

        Query q = pm.newQuery(SQL, sql);
        q.setParameters(idCliente);

        return (List<Cuenta>) q.executeList();
    }

    public Cuenta darCuenta(PersistenceManager pm, BigDecimal idCuenta) {

        Producto p = sqlProducto.darProducto(pm, idCuenta);

        String sql = "SELECT tipoCuenta FROM " + pp.darTablaCuenta();
        sql += " WHERE id = " + idCuenta;

        Query q = pm.newQuery(SQL, sql);
        q.setResultClass(String.class);
        String tipo = (String) q.executeUnique();

        Cuenta resul = new Cuenta(p, tipo);

        System.out.println(resul.toString());
		return resul;
    };
}
