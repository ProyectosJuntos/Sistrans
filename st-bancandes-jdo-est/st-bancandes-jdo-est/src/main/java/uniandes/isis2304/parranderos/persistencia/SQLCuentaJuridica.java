package uniandes.isis2304.parranderos.persistencia;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLCuentaJuridica {
    private final static String SQL = PersistenciaBancandes.SQL;

    private PersistenciaBancandes pp;

    public SQLCuentaJuridica(PersistenciaBancandes pp) {
        this.pp = pp;
    }

    public Long adicionarCuentaJuridica(PersistenceManager pm, BigDecimal idCuenta, BigDecimal idRecibe,
            BigDecimal idPaga, Long valor, String frecuencia) {
        Query q = pm.newQuery(SQL,
                "INSERT INTO " + pp.darTablaCuentaJuridica() + "(id, valorAPagar, frecuencia, cuentaPagar)"
                        + " values (" + idCuenta + ", " + valor + ", '" + frecuencia + "', " + idPaga + ")");

        Long a = (Long) q.executeUnique();
        q = pm.newQuery(SQL, "INSERT INTO B_CUENTAJURIDICA_CUENTA (idCuentaJuridica, idCuenta)"
                + " values (" + idCuenta + ", " + idRecibe + ")");
        Long b = (Long) q.executeUnique();

        if (a != 0 && b != 0) {
            return Long.valueOf(1);
        } else {
            return Long.valueOf(0);
        }
    }

}
