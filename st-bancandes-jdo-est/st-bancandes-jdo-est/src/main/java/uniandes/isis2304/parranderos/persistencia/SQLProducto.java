package uniandes.isis2304.parranderos.persistencia;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Producto;

public class SQLProducto {
    private final static String SQL = PersistenciaBancandes.SQL;

    private PersistenciaBancandes pp;

    public SQLProducto(PersistenciaBancandes pp) {
        this.pp = pp;
    }

    public Producto darProducto(PersistenceManager pm, BigDecimal idProducto) {
        String sql = "SELECT * FROM " + pp.darTablaProducto() + " WHERE id = " + idProducto;
        Query q = pm.newQuery(SQL, sql);
        q.setResultClass(Producto.class);

        Producto resul = (Producto) q.executeUnique();
        return resul;
    }

    public Long adicionarProducto(PersistenceManager pm, BigDecimal idCuenta, String estado, BigDecimal valorActual, Timestamp fecha,
            Timestamp fecha2, BigDecimal idOficina, String tipoId, BigDecimal id) {

        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaProducto()
                + " (id, estado, valorActual, fechaCreacion, fechaUltimoMov, idOficina, tipoIdUsuario, idUsuario)" +
                " values (" + idCuenta + ", '"+ estado + "', "+ valorActual +", TO_TIMESTAMP('"+ fecha +"', 'YYYY-MM-DD HH24:MI:SS.FF'), TO_TIMESTAMP('"+ fecha2 +"', 'YYYY-MM-DD HH24:MI:SS.FF'), "+ idOficina +", '"+ tipoId +"', "+ id +")");
        return (long) q.executeUnique();
    }

}
