package uniandes.isis2304.parranderos.persistencia;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.apache.log4j.Logger;

import uniandes.isis2304.parranderos.negocio.Cuenta;
import uniandes.isis2304.parranderos.negocio.Oficina;
import uniandes.isis2304.parranderos.negocio.Operacion;
import uniandes.isis2304.parranderos.negocio.Prestamo;
import uniandes.isis2304.parranderos.negocio.PuestoAtencion;
import uniandes.isis2304.parranderos.negocio.Usuario;

public class PersistenciaBancandes {
	private static Logger log = Logger.getLogger(PersistenciaBancandes.class.getName());

	public final static String SQL = "javax.jdo.query.SQL";

	private static PersistenciaBancandes instance;

	private PersistenceManagerFactory pmf;

	private List<String> tablas;

	private SQLUsuario sqlUsuario;
	private SQLOficina sqlOficina;
	private SQLCuenta sqlCuenta;
	private SQLProducto sqlProducto;
	private SQLPuestoAtencion sqlPuestoAtencion;
	private SQLOperacion sqlOperacion;
	private SQLPrestamo sqlPrestamo;
	private SQLEmpleado sqlEmpleado;
	private SQLCuentaJuridica sqlCuentaJuridica;
	private SQLUtil sqlUtil;

	private PersistenciaBancandes() {
		pmf = JDOHelper.getPersistenceManagerFactory("Bancandes");
		crearClasesSQL();

		// Define los nombres por defecto de las tablas de la base de datos
		tablas = new LinkedList<String>();
		tablas.add("Bancandes_sequence");
		tablas.add("B_ACCION");
		tablas.add("B_AFC");
		tablas.add("B_CDT");
		tablas.add("B_CUENTA");
		tablas.add("B_EMPLEADO");
		tablas.add("B_OFICINA");
		tablas.add("B_OPERACION");
		tablas.add("B_PRESTAMO");
		tablas.add("B_PRODUCTO");
		tablas.add("B_PUESTOATENCION");
		tablas.add("B_USUARIO");
		tablas.add("B_CUENTAJURIDICA");
	}

	private PersistenciaBancandes(JsonObject tableConfig) {
		crearClasesSQL();
		tablas = leerNombresTablas(tableConfig);

		String unidadPersistencia = tableConfig.get("unidadPersistencia").getAsString();
		log.trace("Accediendo unidad de persistencia: " + unidadPersistencia);
		pmf = JDOHelper.getPersistenceManagerFactory(unidadPersistencia);
	}

	public static PersistenciaBancandes getInstance() {
		if (instance == null) {
			instance = new PersistenciaBancandes();
		}
		return instance;
	}

	public static PersistenciaBancandes getInstance(JsonObject tableConfig) {
		if (instance == null) {
			instance = new PersistenciaBancandes(tableConfig);
		}
		return instance;
	}

	public void cerrarUnidadPersistencia() {
		pmf.close();
		instance = null;
	}

	private List<String> leerNombresTablas(JsonObject tableConfig) {
		JsonArray nombres = tableConfig.getAsJsonArray("tablas");

		List<String> resp = new LinkedList<String>();
		for (JsonElement nom : nombres) {
			resp.add(nom.getAsString());
		}

		return resp;
	}

	private void crearClasesSQL() {
		sqlEmpleado = new SQLEmpleado(this);
		sqlOficina = new SQLOficina(this);
		sqlOperacion = new SQLOperacion(this);
		sqlProducto = new SQLProducto(this);
		sqlCuenta = new SQLCuenta(this, sqlProducto);
		sqlPrestamo = new SQLPrestamo(this);
		sqlPuestoAtencion = new SQLPuestoAtencion(this);
		sqlUsuario = new SQLUsuario(this);
		sqlCuentaJuridica = new SQLCuentaJuridica(this);
		// sqlVisitan = new SQLVisitan(this);
		sqlUtil = new SQLUtil(this);
	}

	public String darSeqBancandes() {
		return tablas.get(0);
	}

	public String darTablaAccion() {
		return tablas.get(1);
	}

	public String darTablaAFC() {
		return tablas.get(2);
	}

	public String darTablaCDT() {
		return tablas.get(3);
	}

	public String darTablaCuenta() {
		return tablas.get(4);
	}

	public String darTablaEmpleado() {
		return tablas.get(5);
	}

	public String darTablaOficina() {
		return tablas.get(6);
	}

	public String darTablaOperacion() {
		return tablas.get(7);
	}

	public String darTablaPrestamo() {
		return tablas.get(8);
	}

	public String darTablaProducto() {
		return tablas.get(9);
	}

	public String darTablaPuestoAtencion() {
		return tablas.get(10);
	}

	public String darTablaUsuario() {
		return tablas.get(11);
	}

	public String darTablaCuentaJuridica() {
		return tablas.get(12);
	}

	private long nextval() {
		long resp = sqlUtil.nextval(pmf.getPersistenceManager());
		log.trace("Generando secuencia: " + resp);
		return resp;
	}

	private String darDetalleException(Exception e) {
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException")) {
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions()[0].getMessage();
		}
		return resp;
	}

	public String[] obtenerEmpleado(String tipoUsuarioSeleccionado, String login, String contrasenia) throws Exception {
		PersistenceManager pm = pmf.getPersistenceManager();
		String[] resul = sqlEmpleado.obtenerDatos(pm, tipoUsuarioSeleccionado, login, contrasenia);
		return resul;
	}

	public String[] obtenerUsuario(String login, String contrasenia) {
		PersistenceManager pm = pmf.getPersistenceManager();
		String[] resul = sqlUsuario.obtenerDatos(pm, login, contrasenia);
		return resul;
	}

	public Usuario adicionarUsuario(String tipoUsuario, String login, String clave, String tipoId, BigDecimal numId,
			String nombre, String nacionalidad, String dirFisica, String dirElectronica, String telefono, String ciudad,
			String departamento, Integer codigoPostal) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long tuplasInsertadas = sqlUsuario.adicionarUsuario(pm, tipoUsuario, login, clave, tipoId, numId, nombre,
					nacionalidad, dirFisica, dirElectronica, telefono, ciudad, departamento, codigoPostal);
			tx.commit();

			log.trace("Inserción de usuario: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");

			return new Usuario(tipoUsuario, login, clave, tipoId, numId, nombre, nacionalidad, dirFisica,
					dirElectronica, telefono, ciudad, departamento, codigoPostal, new LinkedList<>(),
					new LinkedList<>(), new LinkedList<>());
		} catch (Exception e) {
			// e.printStackTrace();
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

	public Oficina adicionarOficina(String nombre, String direccion, Integer numPAtencion, String tipoIdGerente,
			BigDecimal idGerente, LinkedList<Object[]> puestosAtencion) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			BigDecimal idOficina = BigDecimal.valueOf(nextval());
			long tuplasInsertadas = sqlOficina.adicionarOficina(pm, idOficina, nombre, direccion, numPAtencion,
					tipoIdGerente, idGerente, puestosAtencion);
			tx.commit();

			log.trace("Inserción de Oficina: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");

			return new Oficina(idOficina, nombre, direccion, numPAtencion, idGerente, tipoIdGerente, puestosAtencion);
		} catch (Exception e) {
			// e.printStackTrace();
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

	public PuestoAtencion adicionarPuestoAtencion(String tipoPuesto, String localizacion, Oficina oficina,
			BigDecimal cajero, String tipoIdCajero, LinkedList<Object[]> operaciones) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			BigDecimal idPuestoAtencion = BigDecimal.valueOf(nextval());
			long tuplasInsertadas = sqlPuestoAtencion.adicionarPuntoAtencion(pm, idPuestoAtencion, tipoPuesto,
					localizacion, oficina, cajero, tipoIdCajero, operaciones);
			tx.commit();

			log.trace("Inserción de Puesto de Atencion: " + idPuestoAtencion + ": " + tuplasInsertadas
					+ " tuplas insertadas");

			return new PuestoAtencion(idPuestoAtencion, tipoPuesto, localizacion, oficina, cajero, tipoIdCajero,
					operaciones);
		} catch (Exception e) {
			// e.printStackTrace();
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

	public Cuenta adicionarCuenta(Timestamp fechaCrecion, Timestamp fechaUltimoMov, BigDecimal oficina,
			BigDecimal usuario, String tipoIdUsuario, String tipoCuenta) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			BigDecimal idCuenta = BigDecimal.valueOf(nextval());
			long tuplasInsertadas = sqlCuenta.adicionarCuenta(pm, idCuenta, Long.valueOf(0), fechaCrecion,
					fechaUltimoMov, oficina, usuario, tipoIdUsuario, new LinkedList<>(), "ACTIVA", tipoCuenta);
			tx.commit();

			log.trace("Inserción de Cuenta: " + idCuenta + ": " + tuplasInsertadas + " tuplas insertadas");

			return new Cuenta(idCuenta, Long.valueOf(0), fechaCrecion, fechaUltimoMov, oficina, usuario, tipoIdUsuario,
					new LinkedList<>(), "ACTIVA", tipoCuenta);
		} catch (Exception e) {
			// e.printStackTrace();
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

	public long cerrarCuenta(BigDecimal idCuenta, BigDecimal idOficina) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long resp = sqlCuenta.cambiarSaldo(pm, idCuenta, Long.valueOf(0));
			resp = sqlCuenta.cambiarEstado(pm, idCuenta, "CERRADA", idOficina);
			tx.commit();
			return resp;
		} catch (Exception e) {
			// e.printStackTrace();
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}


	public Prestamo registrarPrestamo(BigDecimal idOficina, BigDecimal usuario, String tipoIdUsuario,
			Long cuotaMinima) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			BigDecimal idCuenta = BigDecimal.valueOf(nextval());
			long tuplasInsertadas = sqlPrestamo.adicionarPrestamo(pm, idCuenta, Long.valueOf(0),
					new Timestamp(System.currentTimeMillis()), idOficina, usuario, tipoIdUsuario, cuotaMinima);
			tx.commit();

			log.trace("Inserción de Cuenta: " + idCuenta + ": " + tuplasInsertadas + " tuplas insertadas");

			return new Prestamo(idCuenta, Long.valueOf(0), new Timestamp(System.currentTimeMillis()),
					new Timestamp(System.currentTimeMillis()), idOficina, usuario, tipoIdUsuario, new LinkedList<>(),
					"ACTIVA", cuotaMinima);
		} catch (Exception e) {
			// e.printStackTrace();
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

	public Long[] operacionPrestamo(BigDecimal idPrestamo, Long pago, BigDecimal idPuestoAtencion, BigDecimal cajero,
			String tipoIdCajero, BigDecimal usuario, String tipoIdUsuario) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			LinkedList<Long> resp = new LinkedList<>();
			BigDecimal idOperacion = BigDecimal.valueOf(nextval());
			resp.add(sqlPrestamo.operarPrestamo(pm, idPrestamo, pago));
			log.info("Saldo cambiado");
			Timestamp fecha = new Timestamp(System.currentTimeMillis());
			resp.add(sqlOperacion.adicionarOperacion(pm, idOperacion, "PAGOPRESTAMO", pago, fecha, idPuestoAtencion,
					cajero, tipoIdCajero, usuario, tipoIdUsuario, idPrestamo));
			log.info("operacion agregada");
			tx.commit();
			return (Long[]) resp.toArray();
		} catch (Exception e) {
			// e.printStackTrace();
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return new Long[] { Long.valueOf(-1) };
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

	public long[] limpiarBancandes() {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long[] resp = sqlUtil.limpiarBancandes(pm);
			tx.commit();
			log.info("Borrada la base de datos");
			return resp;
		} catch (Exception e) {
			// e.printStackTrace();
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return new long[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}

	}

	public List<Usuario> darUsuarios() {
		return sqlUsuario.darUsuarios(pmf.getPersistenceManager());
	}

	public Usuario obtenerUsuarioPorId(String tipoId, BigDecimal id) {
		return sqlUsuario.darUsuario(pmf.getPersistenceManager(), tipoId, id);
	}

	public Cuenta obtenerCuentaPorId(BigDecimal idCuenta) {
		return sqlCuenta.darCuenta(pmf.getPersistenceManager(), idCuenta);
	}

	public String asociarCuentaParaPago(Cuenta cuentaPagos, Cuenta cuentaRecibe, Usuario clienteJuridico, Long valor,
			String frecuencia) {
		String a = "";
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Timestamp fecha = Timestamp.from(Instant.now());
			LinkedList<Long> resp = new LinkedList<>();
			BigDecimal idCuenta = BigDecimal.valueOf(nextval());

			resp.add(sqlProducto.adicionarProducto(pm, idCuenta, "Activa", BigDecimal.valueOf(0),
			fecha, fecha, null, clienteJuridico.getTipoId(), clienteJuridico.getId()));
			resp.add(sqlCuentaJuridica.adicionarCuentaJuridica(pm, idCuenta, cuentaRecibe.getId(), cuentaPagos.getId(),
			valor, frecuencia));
			if (resp.get(0) != 0 || resp.get(1) != 0) {
				log.info("Conexion agregada");
				tx.commit();
			} else {
				tx.rollback();
				a = "No se pudo agregar la relación";
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			a = e.getMessage();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return a;
	}

	public String adicionarOperacionSobreCuenta(String tipoUsuarioSeleccionado, String tipoOperacion, Long valor,
			Timestamp fecha, BigDecimal idPuestoAtencion, String tipoIdUsuario, String idUsuario,
			BigDecimal cuenta_origen, BigDecimal cuenta_destino, String tipoIdEmpleado, String idEmpleado) {
				
				PersistenceManager pm = pmf.getPersistenceManager();
				Transaction tx = pm.currentTransaction();
				String a = "";
				try {
					tx.begin();
					BigDecimal idOperacion = BigDecimal.valueOf(nextval());

					Long resp = sqlOperacion.adicionarOperacion(pmf.getPersistenceManager(),idOperacion, tipoUsuarioSeleccionado ,tipoOperacion, valor, fecha, idPuestoAtencion, tipoIdUsuario, idUsuario, cuenta_origen, cuenta_destino, tipoIdEmpleado, idEmpleado);
					if (resp == 0 ){ 
						log.info("Conexion agregada");
						tx.commit();
					} else {
						tx.rollback();
						a = "No se pudo agregar la relación";
					}

				} catch (Exception e) {
					e.printStackTrace();
					log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
					a = e.getMessage();
				} finally {
					if (tx.isActive()) {
						tx.rollback();
					}
					pm.close();
				}


	}

	public List<Prestamo> darPrestamos(String tipoUsuarioSeleccionado, String tipoIdUsuario, BigDecimal idUsuario) {
		PersistenceManager pm = pmf.getPersistenceManager();
		if (tipoUsuarioSeleccionado.equals("Cliente")) {
			return sqlPrestamo.darPrestamosCliente(pm, tipoIdUsuario, idUsuario);
		} else if (tipoUsuarioSeleccionado.equals("Gerente Oficina")) {
			return sqlPrestamo.darPrestamosOficina(pm, tipoIdUsuario, idUsuario);
		} else if (tipoUsuarioSeleccionado.equals("Gerente General") || tipoUsuarioSeleccionado.equals("Administrador")) {
			return sqlPrestamo.darPrestamos(pm);
		}
		return null;

	}

	public String adicionarOperacionSobrePrestamo(String tipoUsuarioSeleccionado, String tipoOperacion, Long valor,
			Timestamp fecha, BigDecimal idPuestoAtencion, String tipoIdUsuario, String idUsuario,
			BigDecimal cuenta_origen, BigDecimal cuenta_destino, String tipoIdEmpleado, String idEmpleado) {
				PersistenceManager pm = pmf.getPersistenceManager();
				Transaction tx = pm.currentTransaction();
				String a = "";
				try {
					tx.begin();
					BigDecimal idOperacion = BigDecimal.valueOf(nextval());
					Long resp = sqlOperacion.adicionarOperacion(pmf.getPersistenceManager(),idOperacion, tipoUsuarioSeleccionado ,tipoOperacion, valor, fecha, idPuestoAtencion, tipoIdUsuario, idUsuario, cuenta_origen, cuenta_destino, tipoIdEmpleado, idEmpleado);
					if (resp == 0 ){ 
						log.info("Conexion agregada");
						tx.commit();
					} else {
						tx.rollback();
						a = "No se pudo agregar la relación";
					}

				} catch (Exception e) {
					e.printStackTrace();
					log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
					a = e.getMessage();
				} finally {
					if (tx.isActive()) {
						tx.rollback();
					}
					pm.close();
				}
				return a;
	}

    public List<Operacion> darOperacionestipotransaccion(String fechainferior, String fechasuperior, String tipoOperacion) {
        PersistenceManager pm = pmf.getPersistenceManager();
		return sqlOperacion.darOperacionestipotransaccion(pm, fechainferior, fechasuperior, tipoOperacion);
    }

	public List<Operacion> darOperacionesvalor(String fechainferior, String fechasuperior, Long valorInferior, Long valorSuperior) {
        PersistenceManager pm = pmf.getPersistenceManager();
		return sqlOperacion.darOperacionesvalor(pm, fechainferior, fechasuperior, valorInferior, valorSuperior);
    }

	public List<Operacion> darOperacionesrangoid(String fechainferior, String fechasuperior, Long idInferior, Long idSuperior) {
        PersistenceManager pm = pmf.getPersistenceManager();
		return sqlOperacion.darOperacionesrangoid(pm, fechainferior, fechasuperior, idInferior, idSuperior);
    }

    public List<Operacion> darOperacionespuestoatencion(String fechainferior, String fechasuperior, BigDecimal idPuestoAtencion) {
        PersistenceManager pm = pmf.getPersistenceManager();
		return sqlOperacion.darOperacionespuestoatencion(pm, fechainferior, fechasuperior, idPuestoAtencion);
    }

	public List<Operacion> darOperacionescuentaorigen(String fechainferior, String fechasuperior, BigDecimal cuenta_origen) {
        PersistenceManager pm = pmf.getPersistenceManager();
		return sqlOperacion.darOperacionescuentaorigen(pm, fechainferior, fechasuperior, cuenta_origen);
    }

	public List<Operacion> darOperacionescuentadestino(String fechainferior, String fechasuperior, BigDecimal cuenta_destino) {
        PersistenceManager pm = pmf.getPersistenceManager();
		return sqlOperacion.darOperacionescuentadestino(pm, fechainferior, fechasuperior, cuenta_destino);
    }

	public List<Operacion> darOperacionesusuario(String fechainferior, String fechasuperior, String tipoIdUsuario, BigDecimal idUsuario) {
        PersistenceManager pm = pmf.getPersistenceManager();
		return sqlOperacion.darOperacionesusuario(pm, fechainferior, fechasuperior, tipoIdUsuario, idUsuario);
    }

	public List<Operacion> darOperacionesempleado(String fechainferior, String fechasuperior, String tipoIdEmpleado, BigDecimal idEmpleado) {
        PersistenceManager pm = pmf.getPersistenceManager();
		return sqlOperacion.darOperacionesempleado(pm, fechainferior, fechasuperior, tipoIdEmpleado, idEmpleado);
    }

	public List<Operacion> darOperacionesNotipotransaccion(String fechainferior, String fechasuperior, String tipoOperacion) {
        PersistenceManager pm = pmf.getPersistenceManager();
		return sqlOperacion.darOperacionesNotipotransaccion(pm, fechainferior, fechasuperior, tipoOperacion);
    }

	public List<Operacion> darOperacionesNovalor(String fechainferior, String fechasuperior, Long valorInferior, Long valorSuperior) {
        PersistenceManager pm = pmf.getPersistenceManager();
		return sqlOperacion.darOperacionesNovalor(pm, fechainferior, fechasuperior, valorInferior, valorSuperior);
    }

	public List<Operacion> darOperacionesNorangoid(String fechainferior, String fechasuperior, Long idInferior, Long idSuperior) {
        PersistenceManager pm = pmf.getPersistenceManager();
		return sqlOperacion.darOperacionesNorangoid(pm, fechainferior, fechasuperior, idInferior, idSuperior);
    }

    public List<Operacion> darOperacionesNopuestoatencion(String fechainferior, String fechasuperior, BigDecimal idPuestoAtencion) {
        PersistenceManager pm = pmf.getPersistenceManager();
		return sqlOperacion.darOperacionesNopuestoatencion(pm, fechainferior, fechasuperior, idPuestoAtencion);
    }

	public List<Operacion> darOperacionesNocuentaorigen(String fechainferior, String fechasuperior, BigDecimal cuenta_origen) {
        PersistenceManager pm = pmf.getPersistenceManager();
		return sqlOperacion.darOperacionesNocuentaorigen(pm, fechainferior, fechasuperior, cuenta_origen);
    }

	public List<Operacion> darOperacionesNocuentadestino(String fechainferior, String fechasuperior, BigDecimal cuenta_destino) {
        PersistenceManager pm = pmf.getPersistenceManager();
		return sqlOperacion.darOperacionesNocuentadestino(pm, fechainferior, fechasuperior, cuenta_destino);
    }

	public List<Operacion> darOperacionesNousuario(String fechainferior, String fechasuperior, String tipoIdUsuario, BigDecimal idUsuario) {
        PersistenceManager pm = pmf.getPersistenceManager();
		return sqlOperacion.darOperacionesNousuario(pm, fechainferior, fechasuperior, tipoIdUsuario, idUsuario);
    }

	public List<Operacion> darOperacionesNoempleado(String fechainferior, String fechasuperior, String tipoIdEmpleado, BigDecimal idEmpleado) {
        PersistenceManager pm = pmf.getPersistenceManager();
		return sqlOperacion.darOperacionesNoempleado(pm, fechainferior, fechasuperior, tipoIdEmpleado, idEmpleado);
    }

	public List<Operacion> darConsignaciones(Long valorX, String tipoProducto) {
        PersistenceManager pm = pmf.getPersistenceManager();
		return sqlOperacion.darConsignaciones(pm, valorX, tipoProducto);
    }

	public List<Usuario> darPatencionclientes(BigDecimal idPatencion1, BigDecimal idPatencion2) {
        PersistenceManager pm = pmf.getPersistenceManager();
		return sqlUsuario.darPatencionclientes(pm, idPatencion1, idPatencion2);
    }

	public List<Operacion> darPatencionoperaciones(BigDecimal idPatencion1, BigDecimal idPatencion2) {
        PersistenceManager pm = pmf.getPersistenceManager();
		return sqlOperacion.darPatencionoperaciones(pm, idPatencion1, idPatencion2);
    }
}

