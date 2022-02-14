package uniandes.isis2304.parranderos.persistencia;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Empleado;

public class SQLEmpleado {
    private final static String SQL = PersistenciaBancandes.SQL;

    private PersistenciaBancandes pp;

    public SQLEmpleado(PersistenciaBancandes pp) {
        this.pp = pp;
    }

    public long adicionarEmpleado(PersistenceManager pm, String tipoEmpleado, String login, String clave, String tipoId,
            BigDecimal numId, String nombre, String nacionalidad, String dirFisica, String dirElectronica,
            String telefono, String ciudad, String departamento, Integer codigoPostal, LinkedList<Object[]> operaciones,
            LinkedList<Object[]> productos, LinkedList<Object[]> acciones) {
        // TODO
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaEmpleado()
                + "(tipoEmpleado, login, clave, tipoId, id, nombre, nacionalidad, dirFisica, dirElectronica, telefono, ciudad, departamento, codigoPostal, operaciones, productos, acciones) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(tipoEmpleado, login, clave, tipoId, numId, nombre, nacionalidad, dirFisica, dirElectronica,
                telefono, ciudad, departamento, codigoPostal, operaciones, productos, acciones);
        return (long) q.executeUnique();
    }

    public List<Empleado> darEmpleados(PersistenceManager pm) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaEmpleado());
        q.setResultClass(Empleado.class);
        return (List<Empleado>) q.executeList();
    }

    public String[] obtenerDatos(PersistenceManager pm, String tipoUsuarioSeleccionado, String login,
            String contrasenia) throws Exception {
        String[] resul = new String[2];

        Query q = pm.newQuery(SQL,
                "SELECT u.tipoId FROM " + pp.darTablaUsuario() + " u JOIN " + pp.darTablaEmpleado()
                        + " e ON u.id = e.id WHERE u.login = '" + login + "' AND u.clave = '" + contrasenia
                        + "' AND e.tipoEmpleado = '" + tipoUsuarioSeleccionado + "'");

        resul[0] = (String) q.executeUnique();

        // System.out.println(resul[0]);

        q = pm.newQuery(SQL,
                "SELECT u.id FROM " + pp.darTablaUsuario() + " u JOIN " + pp.darTablaEmpleado()
                        + " e ON u.id = e.id WHERE u.login = '" + login + "' AND u.clave = '" + contrasenia
                        + "' AND e.tipoEmpleado = '" + tipoUsuarioSeleccionado + "'");
        q.setResultClass(String.class);

        resul[1] = (String) q.executeUnique();

        // System.out.println(resul[1]);

        return resul;
    }

}
