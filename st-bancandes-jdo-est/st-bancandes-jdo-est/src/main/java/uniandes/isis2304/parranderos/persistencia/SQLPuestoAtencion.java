package uniandes.isis2304.parranderos.persistencia;

import java.math.BigDecimal;
import java.util.LinkedList;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Oficina;

public class SQLPuestoAtencion {
    private final static String SQL = PersistenciaBancandes.SQL;

    private PersistenciaBancandes pp;

    public SQLPuestoAtencion (PersistenciaBancandes pp)
	{
		this.pp = pp;
	}

    public long adicionarPuntoAtencion(PersistenceManager pm, BigDecimal id, String tipoPuesto, String localizacion, Oficina oficina, BigDecimal cajero,
    String tipoIdCajero, LinkedList<Object[]> operaciones) {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPuestoAtencion()
                + "(id, tipoPuesto, localizacion, oficina, cajero, tipoIdCajero, operaciones) values (?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(id, tipoPuesto, localizacion, oficina, cajero, tipoIdCajero, operaciones);
        return (long) q.executeUnique();
    }

}
