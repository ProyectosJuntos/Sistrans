package uniandes.isis2304.parranderos.persistencia;

import java.math.BigDecimal;
import java.util.LinkedList;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLOficina {
    private final static String SQL = PersistenciaBancandes.SQL;

    private PersistenciaBancandes pp;

    public SQLOficina(PersistenciaBancandes pp) {
        this.pp = pp;
    }

    public long adicionarOficina(PersistenceManager pm, BigDecimal id, String nombre, String direccion,
            Integer numPAtencion, String tipoIdGerente, BigDecimal idGerente, LinkedList<Object[]> puestosAtencion) {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaOficina()
                + "(id, nombre, direccion, numPAtencion, tipoIdGerente, idGerente, puestosAtencion) values (?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(id, nombre, direccion, numPAtencion, tipoIdGerente, idGerente, puestosAtencion);
        return (long) q.executeUnique();
    }
}
