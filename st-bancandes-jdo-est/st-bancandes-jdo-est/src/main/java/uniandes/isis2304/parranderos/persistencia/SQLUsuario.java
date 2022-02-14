package uniandes.isis2304.parranderos.persistencia;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Usuario;

class SQLUsuario {
	private final static String SQL = PersistenciaBancandes.SQL;

	private PersistenciaBancandes pp;

	public SQLUsuario(PersistenciaBancandes pp) {
		this.pp = pp;
	}

	public long adicionarUsuario(PersistenceManager pm, String tipoUsuario, String login, String clave, String tipoId,
			BigDecimal numId, String nombre, String nacionalidad, String dirFisica, String dirElectronica,
			String telefono, String ciudad, String departamento, Integer codigoPostal) {
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaUsuario()
				+ "(tipoUsuario, login, clave, tipoId, id, nombre, nacionalidad, dirFisica, dirElectronica, telefono, ciudad, departamento, codigoPostal) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		q.setParameters(tipoUsuario, login, clave, tipoId, numId, nombre, nacionalidad, dirFisica, dirElectronica,
				telefono, ciudad, departamento, codigoPostal);
		return (long) q.executeUnique();
	}

	public List<Usuario> darUsuarios(PersistenceManager pm) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaUsuario());
		q.setResultClass(Usuario.class);
		return (List<Usuario>) q.executeList();
	}

	public String[] obtenerDatos(PersistenceManager pm, String login, String contrasenia) {
		// System.out.println(login);
		// System.out.println(contrasenia);
		String[] resul = new String[2];

		Query q = pm.newQuery(SQL, "SELECT tipoId FROM " + pp.darTablaUsuario() + " WHERE login = '" + login
				+ "' AND clave = '" + contrasenia + "'");
		// q.setResultClass(String.class);

		// System.out.println(q.executeUnique());
		resul[0] = (String) q.executeUnique();

		// System.out.println(resul[0]);

		q = pm.newQuery(SQL, "SELECT id FROM " + pp.darTablaUsuario() + " WHERE login = ? AND clave = ?");
		q.setParameters(login, contrasenia);
		q.setResultClass(String.class);

		resul[1] = (String) q.executeUnique();

		// System.out.println(resul.toString());

		return resul;
	}

	public Usuario darUsuario(PersistenceManager pm, String tipoId, BigDecimal id) {
		String sql = "SELECT * FROM " + pp.darTablaUsuario() + " WHERE tipoId = '" + tipoId + "' AND id = "
				+ id;
		Query q = pm.newQuery(SQL, sql);
		q.setResultClass(Usuario.class);

		// List a = q.executeList();
		// System.out.println(q.executeUnique().getClass());																										));
		Usuario resul = (Usuario) q.executeUnique();
		// System.out.println(resul.toString());
		return resul;
	}

	public List<Usuario> darPatencionclientes(PersistenceManager pm, BigDecimal idPatencion1, BigDecimal idPatencion2) {
        Query q = pm.newQuery(SQL, "SELECT u.* FROM b_usuario u JOIN b_operacion o ON o.tipoidusuario = u.tipoid AND o.idusuario = u.id WHERE o.idpuestoatencion = " + idPatencion1 + " OR o.idpuestoatencion = " + idPatencion2 + "ORDER BY u.id");
		q.setResultClass(Usuario.class);
        return (List<Usuario>) q.executeList();
    }
}
