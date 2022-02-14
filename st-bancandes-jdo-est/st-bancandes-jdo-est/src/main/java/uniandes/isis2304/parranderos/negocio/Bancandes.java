package uniandes.isis2304.parranderos.negocio;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import com.google.gson.JsonObject;

import org.apache.log4j.Logger;

import uniandes.isis2304.parranderos.persistencia.PersistenciaBancandes;

public class Bancandes {

	private static Logger log = Logger.getLogger(Bancandes.class.getName());

	private PersistenciaBancandes pp;

	public Bancandes() {
		pp = PersistenciaBancandes.getInstance();
	}

	public Bancandes(JsonObject tableConfig) {
		pp = PersistenciaBancandes.getInstance(tableConfig);
	}

	public void cerrarUnidadPersistencia() {
		pp.cerrarUnidadPersistencia();
	}

	public String[] obtenerEmpleado(String usuarioSeleccionado2, String login, String contrasenia) throws Exception {
		String[] resul = pp.obtenerEmpleado(usuarioSeleccionado2, login, contrasenia);
		return resul;
	}

	public String[] obtenerUsuario(String login, String contrasenia) {
		String[] resul = pp.obtenerUsuario(login, contrasenia);
		return resul;
	}

	public Usuario adicionarUsuario(String tipoUsuario, String login, String clave, String tipoId, BigDecimal numId,
			String nombre, String nacionalidad, String dirFisica, String dirElectronica, String telefono, String ciudad,
			String departamento, Integer codigoPostal) {
		log.info("Adicionando Usuario: " + nombre);
		Usuario usuario = pp.adicionarUsuario(tipoUsuario, login, clave, tipoId, numId, nombre, nacionalidad, dirFisica,
				dirElectronica, telefono, ciudad, departamento, codigoPostal);
		log.info("Adicionando usuario: " + usuario);
		return usuario;
	}


	public String OperacionSobreCuenta(String tipoUsuarioSeleccionado, String tipoOperacion, Long valor,
	Timestamp fecha, BigDecimal idPuestoAtencion, String tipoIdUsuario, String idUsuario,
	BigDecimal cuenta_origen, BigDecimal cuenta_destino, String tipoIdEmpleado, String idEmpleado)
	{
		log.info("Adicionando operacion: ");
		String operacion = pp.adicionarOperacionSobreCuenta(tipoUsuarioSeleccionado, tipoOperacion, valor, fecha, idPuestoAtencion, tipoIdUsuario, idUsuario, cuenta_origen, cuenta_destino, tipoIdEmpleado, idEmpleado);

		log.info("Adicionando operacion");
		return operacion;
	}

	public String OperacionSobrePrestamo(String tipoUsuarioSeleccionado, String tipoOperacion, Long valor,
			Timestamp fecha, BigDecimal idPuestoAtencion, String tipoIdUsuario, String idUsuario,
			BigDecimal cuenta_origen, BigDecimal cuenta_destino, String tipoIdEmpleado, String idEmpleado) {
				log.info("Adicionando operacion: ");
				String operacion = pp.adicionarOperacionSobrePrestamo(tipoUsuarioSeleccionado, tipoOperacion, valor, fecha, idPuestoAtencion, tipoIdUsuario, idUsuario, cuenta_origen, cuenta_destino, tipoIdEmpleado, idEmpleado);
				log.info("Adicionando operacion");
				return operacion;
	}

	public Oficina adicionarOficina(String nombre, String direccion, Integer numPAtencion, String tipoIdGerente,
			BigDecimal idGerente, LinkedList<Object[]> puestosAtencion) {
		log.info("Adicionando Oficina: " + nombre);
		Oficina oficina = pp.adicionarOficina(nombre, direccion, numPAtencion, tipoIdGerente, idGerente,
				puestosAtencion);
		log.info("Adicionando Oficina: " + oficina);
		return oficina;
	}

	public PuestoAtencion adicionarPuestoAtencion(String tipoPuesto, String localizacion, Oficina oficina,
			BigDecimal cajero, String tipoIdCajero, LinkedList<Object[]> operaciones) {
		log.info("Adicionando Puesto: " + localizacion + " " + oficina);
		PuestoAtencion puestoAtencion = pp.adicionarPuestoAtencion(tipoPuesto, localizacion, oficina, cajero,
				tipoIdCajero, operaciones);
		log.info("Adicionando puesto: " + localizacion + " " + oficina);
		return puestoAtencion;
	}

	public Cuenta adicionarCuenta(Long valorActual, Timestamp fechaCrecion, Timestamp fechaUltimoMov,
			BigDecimal oficina, BigDecimal usuario, String tipoIdUsuario, LinkedList<Object[]> operaciones,
			String estado, String tipoCuenta) {
		log.info("Adicionando Cuenta: " + usuario + " " + fechaCrecion);
		Cuenta cuenta = pp.adicionarCuenta(fechaCrecion, fechaUltimoMov, oficina, usuario, tipoIdUsuario, tipoCuenta);
		log.info("Adicionando cuenta: " + cuenta);
		return cuenta;
	}

	public long cerrarCuenta(BigDecimal idCuenta, BigDecimal idOficina) {
		log.info("Cerrando cuenta: " + idCuenta + " de la oficina: " + idOficina);
		long cambios = pp.cerrarCuenta(idCuenta, idOficina);
		return cambios;
	}

	public Prestamo registrarPrestamo(BigDecimal idOficina, BigDecimal usuario, String tipoIdUsuario,
			Long cuotaMinima) {
		log.info("Registrano prestamo: " + usuario + idOficina);
		Prestamo p = pp.registrarPrestamo(idOficina, usuario, tipoIdUsuario, cuotaMinima);
		log.info("Registrado");
		return p;
	}

	public List<Usuario> darUsuarios() {
		log.info("Consultando Tipos de bebida");
		List<Usuario> usuario = pp.darUsuarios();
		log.info("Consultando Usuarios: " + usuario.size() + "existentes");
		return usuario;
	}

	public List<VOUsuario> darVOUsuarios() {
		log.info("Generando los VO de Usuarios");
		List<VOUsuario> voUsuarios = new LinkedList<VOUsuario>();
		for (Usuario tb : pp.darUsuarios()) {
			voUsuarios.add(tb);
		}
		log.info("Generando los VO de usuarios: " + voUsuarios.size() + "existentes");
		return voUsuarios;
	}

	public String asociarCuentaParaPago(String tipoUsuario, String tipoIdUsuario, BigDecimal idUsuario,
			String tipoIdEmpleado, BigDecimal idEmpleado, BigDecimal IdCuentaPaga, BigDecimal idCuentaRecibe,
			String frecuencia, Long valor) {

		String resultado = "";
		Usuario clienteJuridico = pp.obtenerUsuarioPorId(tipoIdUsuario, idUsuario);
		if (clienteJuridico != null && clienteJuridico.getTipoUsuario().equals("Juridica")) {
			Usuario empleado = pp.obtenerUsuarioPorId(tipoIdEmpleado, idEmpleado);
			if (empleado != null) {
				Cuenta cuentaPagos = pp.obtenerCuentaPorId(IdCuentaPaga);
				if (cuentaPagos != null && cuentaPagos.getTipoIdUsuario().equals(tipoIdUsuario)
						&& cuentaPagos.getIdUsuario().equals(idUsuario) && cuentaPagos.estado.equals("Activa")) {
					Cuenta cuentaRecibe = pp.obtenerCuentaPorId(idCuentaRecibe);
					if (cuentaRecibe != null && cuentaRecibe.getTipoIdUsuario().equals(tipoIdEmpleado)
							&& cuentaRecibe.getIdUsuario().equals(idEmpleado) && cuentaRecibe.estado.equals("Activa")) {
						resultado += "Todas las condiciones son validas. Asociando el pago...\n";
						resultado += pp.asociarCuentaParaPago(cuentaPagos, cuentaRecibe, clienteJuridico, valor,
								frecuencia);
					} else {
						resultado += "El empleado no tiene la cuenta registrada o esta inactiva.\n";
					}
				} else {
					resultado += "El usuario juridico no tiene la cuenta registrada o esta inactiva.\n";
				}
			} else {
				resultado += "No se encontró al usuario empleado.\n";
			}
		} else {
			resultado += "El usuario actual no es una persona juridica. \n";
		}

		return resultado;
	}

	public List<VOPrestamo> darVOPrestamos(String tipoUsuarioSeleccionado, String tipoIdUsuario, BigDecimal idUsuario) {
		log.info("Generando los VO de los prestamos\n");
		List<VOPrestamo> voPrestamos = new LinkedList<VOPrestamo>();

		List<Prestamo> a = pp.darPrestamos(tipoUsuarioSeleccionado, tipoIdUsuario, idUsuario);
		for (Prestamo beb : a) {
			voPrestamos.add(beb);
		}
		log.info("Generando los VO de los Prestamos: " + voPrestamos.size() + " existentes");
		return voPrestamos;
	}


	// public TipoBebida adicionarTipoBebida (String nombre)
	// {
	// log.info ("Adicionando Tipo de bebida: " + nombre);
	// TipoBebida tipoBebida = pp.adicionarTipoBebida (nombre);
	// log.info ("Adicionando Tipo de bebida: " + tipoBebida);
	// return tipoBebida;
	// }

	// public long eliminarTipoBebidaPorNombre (String nombre)
	// {
	// log.info ("Eliminando Tipo de bebida por nombre: " + nombre);
	// long resp = pp.eliminarTipoBebidaPorNombre (nombre);
	// log.info ("Eliminando Tipo de bebida por nombre: " + resp + " tuplas
	// eliminadas");
	// return resp;
	// }

	// public long eliminarTipoBebidaPorId (long idTipoBebida)
	// {
	// log.info ("Eliminando Tipo de bebida por id: " + idTipoBebida);
	// long resp = pp.eliminarTipoBebidaPorId (idTipoBebida);
	// log.info ("Eliminando Tipo de bebida por id: " + resp + " tuplas
	// eliminadas");
	// return resp;
	// }

	// public List<TipoBebida> darTiposBebida ()
	// {
	// log.info ("Consultando Tipos de bebida");
	// List<TipoBebida> tiposBebida = pp.darTiposBebida ();
	// log.info ("Consultando Tipos de bebida: " + tiposBebida.size() + "
	// existentes");
	// return tiposBebida;
	// }

	// public List<VOTipoBebida> darVOTiposBebida ()
	// {
	// log.info ("Generando los VO de Tipos de bebida");
	// List<VOTipoBebida> voTipos = new LinkedList<VOTipoBebida> ();
	// for (TipoBebida tb : pp.darTiposBebida ())
	// {
	// voTipos.add (tb);
	// }
	// log.info ("Generando los VO de Tipos de bebida: " + voTipos.size() + "
	// existentes");
	// return voTipos;
	// }

	// public TipoBebida darTipoBebidaPorNombre (String nombre)
	// {
	// log.info ("Buscando Tipo de bebida por nombre: " + nombre);
	// List<TipoBebida> tb = pp.darTipoBebidaPorNombre (nombre);
	// return !tb.isEmpty () ? tb.get (0) : null;
	// }

	// public Bebida adicionarBebida (String nombre, long idTipoBebida, int
	// gradoAlcohol)
	// {
	// log.info ("Adicionando bebida " + nombre);
	// Bebida bebida = pp.adicionarBebida (nombre, idTipoBebida, gradoAlcohol);
	// log.info ("Adicionando bebida: " + bebida);
	// return bebida;
	// }

	// public long eliminarBebidaPorNombre (String nombre)
	// {
	// log.info ("Eliminando bebida por nombre: " + nombre);
	// long resp = pp.eliminarBebidaPorNombre (nombre);
	// log.info ("Eliminando bebida por nombre: " + resp + " tuplas eliminadas");
	// return resp;
	// }

	// public long eliminarBebidaPorId (long idBebida)
	// {
	// log.info ("Eliminando bebida por id: " + idBebida);
	// long resp = pp.eliminarBebidaPorId (idBebida);
	// log.info ("Eliminando bebida por id: " + resp + " tuplas eliminadas");
	// return resp;
	// }

	// public List<Bebida> darBebidas ()
	// {
	// log.info ("Consultando Bebidas");
	// List<Bebida> bebidas = pp.darBebidas ();
	// log.info ("Consultando Bebidas: " + bebidas.size() + " bebidas existentes");
	// return bebidas;
	// }

	// public List<VOBebida> darVOBebidas ()
	// {
	// log.info ("Generando los VO de las bebidas");
	// List<VOBebida> voBebidas = new LinkedList<VOBebida> ();
	// for (Bebida beb : pp.darBebidas ())
	// {
	// voBebidas.add (beb);
	// }
	// log.info ("Generando los VO de las bebidas: " + voBebidas.size() + "
	// existentes");
	// return voBebidas;
	// }

	// public long eliminarBebidasNoServidas ()
	// {
	// log.info ("Borrando bebidas no servidas");
	// long resp = pp.eliminarBebidasNoServidas ();
	// log.info ("Borrando bebidas no servidas: " + resp + " bebidas eliminadas");
	// return resp;
	// }

	// public Bebedor adicionarBebedor (String nombre, String presupuesto, String
	// ciudad)
	// {
	// log.info ("Adicionando bebedor: " + nombre);
	// Bebedor bebedor = pp.adicionarBebedor (nombre, presupuesto, ciudad);
	// log.info ("Adicionando bebedor: " + bebedor);
	// return bebedor;
	// }

	// public long eliminarBebedorPorNombre (String nombre)
	// {
	// log.info ("Eliminando bebedor por nombre: " + nombre);
	// long resp = pp.eliminarBebedorPorNombre (nombre);
	// log.info ("Eliminando bebedor por nombre: " + resp + " tuplas eliminadas");
	// return resp;
	// }

	// public long eliminarBebedorPorId (long idBebedor)
	// {
	// log.info ("Eliminando bebedor por id: " + idBebedor);
	// long resp = pp.eliminarBebedorPorId (idBebedor);
	// log.info ("Eliminando bebedor por Id: " + resp + " tuplas eliminadas");
	// return resp;
	// }

	// public Bebedor darBebedorPorId (long idBebedor)
	// {
	// log.info ("Dar información de un bebedor por id: " + idBebedor);
	// Bebedor bebedor = pp.darBebedorPorId (idBebedor);
	// log.info ("Buscando bebedor por Id: " + bebedor != null ? bebedor : "NO
	// EXISTE");
	// return bebedor;
	// }

	// public List<Bebedor> darBebedoresPorNombre (String nombre)
	// {
	// log.info ("Dar información de bebedores por nombre: " + nombre);
	// List<Bebedor> bebedores = pp.darBebedoresPorNombre (nombre);
	// log.info ("Dar información de Bebedores por nombre: " + bebedores.size() + "
	// bebedores con ese nombre existentes");
	// return bebedores;
	// }

	// public List<VOBebedor> darVOBebedoresPorNombre (String nombre)
	// {
	// log.info ("Generando VO de bebedores por nombre: " + nombre);
	// List<VOBebedor> voBebedores = new LinkedList<VOBebedor> ();
	// for (Bebedor bdor : pp.darBebedoresPorNombre (nombre))
	// {
	// voBebedores.add (bdor);
	// }
	// log.info ("Generando los VO de Bebedores: " + voBebedores.size() + "
	// bebedores existentes");
	// return voBebedores;
	// }

	// public Bebedor darBebedorCompleto (long idBebedor)
	// {
	// log.info ("Dar información COMPLETA de un bebedor por id: " + idBebedor);
	// Bebedor bebedor = pp.darBebedorCompleto (idBebedor);
	// log.info ("Buscando bebedor por Id: " + bebedor.toStringCompleto() != null ?
	// bebedor : "NO EXISTE");
	// return bebedor;
	// }

	// public List<Bebedor> darBebedores ()
	// {
	// log.info ("Listando Bebedores");
	// List<Bebedor> bebedores = pp.darBebedores ();
	// log.info ("Listando Bebedores: " + bebedores.size() + " bebedores
	// existentes");
	// return bebedores;
	// }

	// public List<VOBebedor> darVOBebedores ()
	// {
	// log.info ("Generando los VO de Bebedores");
	// List<VOBebedor> voBebedores = new LinkedList<VOBebedor> ();
	// for (Bebedor bdor : pp.darBebedores ())
	// {
	// voBebedores.add (bdor);
	// }
	// log.info ("Generando los VO de Bebedores: " + voBebedores.size() + "
	// bebedores existentes");
	// return voBebedores;
	// }

	// public List<Object []> darBebedoresYNumVisitasRealizadas ()
	// {
	// log.info ("Listando Bebedores y cuántas visitas ha realizado");
	// List<Object []> tuplas = pp.darBebedoresYNumVisitasRealizadas ();
	// log.info ("Listando Bebedores y cuántas visitas ha realizado: Listo!");
	// return tuplas;
	// }

	// public long darCantidadBebedoresCiudadVisitanBares (String ciudad)
	// {
	// log.info ("Calculando cuántos Bebedores de una ciudad visitan bares");
	// long resp = pp.darCantidadBebedoresCiudadVisitanBares (ciudad);
	// log.info ("Calculando cuántos Bebedores de una ciudad visitan bares de " +
	// ciudad +": " + resp);
	// return resp;
	// }

	// public long cambiarCiudadBebedor (long idBebedor, String ciudad)
	// {
	// log.info ("Cambiando ciudad de bebedor: " + idBebedor);
	// long cambios = pp.cambiarCiudadBebedor (idBebedor, ciudad);
	// return cambios;
	// }

	// public long [] eliminarBebedorYVisitas_v1 (long idBebedor)
	// {
	// log.info ("Eliminando bebedor con sus visitas v1: " + idBebedor);
	// long [] resp = pp.eliminarBebedorYVisitas_v1 (idBebedor);
	// log.info ("Eliminando bebedor con sus visitas v1: " + resp [0] + " bebedor y
	// " + resp [1] + " visitas");
	// return resp;
	// }

	// public long [] eliminarBebedorYVisitas_v2 (long idBebedor)
	// {
	// log.info ("Eliminando bebedor con sus visitas v2: " + idBebedor);
	// long [] resp = pp.eliminarBebedorYVisitas_v2 (idBebedor);
	// log.info ("Eliminando bebedor con sus visitas v2: " + resp [0] + " bebedor y
	// " + resp [1] + " visitas");
	// return resp;
	// }

	// public Bar adicionarBar (String nombre, String presupuesto, String ciudad,
	// int sedes)
	// {
	// log.info ("Adicionando bar: " + nombre);
	// Bar bar = pp.adicionarBar (nombre, presupuesto, ciudad, sedes);
	// log.info ("Adicionando bar: " + bar);
	// return bar;
	// }

	// public long eliminarBarPorNombre (String nombre)
	// {
	// log.info ("Eliminando bar por nombre: " + nombre);
	// long resp = pp.eliminarBarPorNombre (nombre);
	// log.info ("Eliminando bar: " + resp + " tuplas eliminadas");
	// return resp;
	// }

	// public long eliminarBarPorId (long idBar)
	// {
	// log.info ("Eliminando bar por id: " + idBar);
	// long resp = pp.eliminarBarPorId (idBar);
	// log.info ("Eliminando bar: " + resp);
	// return resp;
	// }

	// public List<Bar> darBares ()
	// {
	// log.info ("Listando Bares");
	// List<Bar> bares = pp.darBares ();
	// log.info ("Listando Bares: " + bares.size() + " bares existentes");
	// return bares;
	// }

	// public List<VOBar> darVOBares ()
	// {
	// log.info ("Generando los VO de Bares");
	// List<VOBar> voBares = new LinkedList<VOBar> ();
	// for (Bar bar: pp.darBares ())
	// {
	// voBares.add (bar);
	// }
	// log.info ("Generando los VO de Bares: " + voBares.size () + " bares
	// existentes");
	// return voBares;
	// }

	// public long aumentarSedesBaresCiudad (String ciudad)
	// {
	// log.info ("Aumentando sedes de bares de una ciudad: " + ciudad);
	// long resp = pp.aumentarSedesBaresCiudad (ciudad);
	// log.info ("Aumentando sedes de bares de una ciudad: " + resp + " tuplas
	// actualizadas");
	// return resp;
	// }

	// public List<long []> darBaresYCantidadBebidasSirven ()
	// {
	// log.info ("Listando Bares y cuántos bebidas sirven");
	// List<long []> tuplas = pp.darBaresYCantidadBebidasSirven ();
	// log.info ("Listando Bares y cuántos bebidas sirven: Listo!");
	// return tuplas;
	// }

	// public Gustan adicionarGustan (long idBebedor, long idBebida)
	// {
	// log.info ("Adicionando gustan [" + idBebedor + ", " + idBebida + "]");
	// Gustan resp = pp.adicionarGustan (idBebedor, idBebida);
	// log.info ("Adicionando gustan: " + resp + " tuplas insertadas");
	// return resp;
	// }

	// public long eliminarGustan (long idBebedor, long idBebida)
	// {
	// log.info ("Eliminando gustan");
	// long resp = pp.eliminarGustan (idBebedor, idBebida);
	// log.info ("Eliminando gustan: " + resp + " tuplas eliminadas");
	// return resp;
	// }

	// public List<Gustan> darGustan ()
	// {
	// log.info ("Listando Gustan");
	// List<Gustan> gustan = pp.darGustan ();
	// log.info ("Listando Gustan: " + gustan.size() + " preferencias de gusto
	// existentes");
	// return gustan;
	// }

	// public List<VOGustan> darVOGustan ()
	// {
	// log.info ("Generando los VO de Gustan");
	// List<VOGustan> voGustan = new LinkedList<VOGustan> ();
	// for (VOGustan bar: pp.darGustan ())
	// {
	// voGustan.add (bar);
	// }
	// log.info ("Generando los VO de Gustan: " + voGustan.size () + " Gustan
	// existentes");
	// return voGustan;
	// }

	// public Sirven adicionarSirven (long idBar, long idBebida, String horario)
	// {
	// log.info ("Adicionando sirven [" + idBar + ", " + idBebida + "]");
	// Sirven resp = pp.adicionarSirven (idBar, idBebida, horario);
	// log.info ("Adicionando sirven: " + resp + " tuplas insertadas");
	// return resp;
	// }

	// public long eliminarSirven (long idBar, long idBebida)
	// {
	// log.info ("Eliminando sirven");
	// long resp = pp.eliminarSirven (idBar, idBebida);
	// log.info ("Eliminando sirven: " + resp + "tuplas eliminadas");
	// return resp;
	// }

	// public List<Sirven> darSirven ()
	// {
	// log.info ("Listando Sirven");
	// List<Sirven> sirven = pp.darSirven ();
	// log.info ("Listando Sirven: " + sirven.size() + " sirven existentes");
	// return sirven;
	// }

	// public List<VOSirven> darVOSirven ()
	// {
	// log.info ("Generando los VO de Sirven");
	// List<VOSirven> voGustan = new LinkedList<VOSirven> ();
	// for (VOSirven sirven: pp.darSirven ())
	// {
	// voGustan.add (sirven);
	// }
	// log.info ("Generando los VO de Sirven: " + voGustan.size () + " Sirven
	// existentes");
	// return voGustan;
	// }

	// public Visitan adicionarVisitan (long idBebedor, long idBar, Timestamp fecha,
	// String horario)
	// {
	// log.info ("Adicionando visitan [" + idBebedor + ", " + idBar + "]");
	// Visitan resp = pp.adicionarVisitan (idBebedor, idBar, fecha, horario);
	// log.info ("Adicionando visitan: " + resp + " tuplas insertadas");
	// return resp;
	// }

	// public long eliminarVisitan (long idBebedor, long idBar)
	// {
	// log.info ("Eliminando visitan");
	// long resp = pp.eliminarVisitan (idBebedor, idBar);
	// log.info ("Eliminando visitan: " + resp + " tuplas eliminadas");
	// return resp;
	// }

	// public List<Visitan> darVisitan ()
	// {
	// log.info ("Listando Visitan");
	// List<Visitan> visitan = pp.darVisitan ();
	// log.info ("Listando Visitan: Listo!");
	// return visitan;
	// }

	// public List<VOVisitan> darVOVisitan ()
	// {
	// log.info ("Generando los VO de Visitan");
	// List<VOVisitan> voGustan = new LinkedList<VOVisitan> ();
	// for (VOVisitan vis: pp.darVisitan ())
	// {
	// voGustan.add (vis);
	// }
	// log.info ("Generando los VO de Visitan: " + voGustan.size () + " Visitan
	// existentes");
	// return voGustan;
	// }

	public long[] limpiarBancandes() {
		log.info("Limpiando la BD de Bancandes");
		long[] borrrados = pp.limpiarBancandes();
		log.info("Limpiando la BD de Bancandes: Listo!");
		return borrrados;
	}


    public List<VOOperacion> darVOOperaciontipotransaccion(String fechainferior, String fechasuperior, String tipoOperacion) {
        log.info("Generando los VO de las operaciones\n");
		List<VOOperacion> voOperaciones = new LinkedList<VOOperacion>();

		List<Operacion> a = pp.darOperacionestipotransaccion(fechainferior, fechasuperior, tipoOperacion);
		for (Operacion beb : a) {
			voOperaciones.add(beb);
		}
		log.info("Generando los VO de las operaciones: " + voOperaciones.size() + " existentes");
		return voOperaciones;
	}

    public List<VOOperacion> darVOOperacionvalor(String fechainferior, String fechasuperior, Long valorInferior, Long valorSuperior) {
        log.info("Generando los VO de las operaciones\n");
		List<VOOperacion> voOperaciones = new LinkedList<VOOperacion>();

		List<Operacion> a = pp.darOperacionesvalor(fechainferior, fechasuperior, valorInferior, valorSuperior);
		for (Operacion beb : a) {
			voOperaciones.add(beb);
		}
		log.info("Generando los VO de las operaciones: " + voOperaciones.size() + " existentes");
		return voOperaciones;
	}

	public List<VOOperacion> darVOOperacionrangoid(String fechainferior, String fechasuperior, Long idInferior, Long idSuperior) {
        log.info("Generando los VO de las operaciones\n");
		List<VOOperacion> voOperaciones = new LinkedList<VOOperacion>();

		List<Operacion> a = pp.darOperacionesrangoid(fechainferior, fechasuperior, idInferior, idSuperior);
		for (Operacion beb : a) {
			voOperaciones.add(beb);
		}
		log.info("Generando los VO de las operaciones: " + voOperaciones.size() + " existentes");
		return voOperaciones;
	}

    public List<VOOperacion> darVOOperacionpuestoatencion(String fechainferior, String fechasuperior, BigDecimal idPuestoAtencion) {
        log.info("Generando los VO de las operaciones\n");
		List<VOOperacion> voOperaciones = new LinkedList<VOOperacion>();

		List<Operacion> a = pp.darOperacionespuestoatencion(fechainferior, fechasuperior, idPuestoAtencion);
		for (Operacion beb : a) {
			voOperaciones.add(beb);
		}
		log.info("Generando los VO de las operaciones: " + voOperaciones.size() + " existentes");
		return voOperaciones;
	}

	public List<VOOperacion> darVOOperacioncuentaorigen(String fechainferior, String fechasuperior, BigDecimal cuenta_origen) {
        log.info("Generando los VO de las operaciones\n");
		List<VOOperacion> voOperaciones = new LinkedList<VOOperacion>();

		List<Operacion> a = pp.darOperacionescuentaorigen(fechainferior, fechasuperior, cuenta_origen);
		for (Operacion beb : a) {
			voOperaciones.add(beb);
		}
		log.info("Generando los VO de las operaciones: " + voOperaciones.size() + " existentes");
		return voOperaciones;
	}

	public List<VOOperacion> darVOOperacioncuentadestino(String fechainferior, String fechasuperior, BigDecimal cuenta_destino) {
        log.info("Generando los VO de las operaciones\n");
		List<VOOperacion> voOperaciones = new LinkedList<VOOperacion>();

		List<Operacion> a = pp.darOperacionescuentadestino(fechainferior, fechasuperior, cuenta_destino);
		for (Operacion beb : a) {
			voOperaciones.add(beb);
		}
		log.info("Generando los VO de las operaciones: " + voOperaciones.size() + " existentes");
		return voOperaciones;
	}

	public List<VOOperacion> darVOOperacionusuario(String fechainferior, String fechasuperior, String tipoIdUsuario, BigDecimal idUsuario) {
        log.info("Generando los VO de las operaciones\n");
		List<VOOperacion> voOperaciones = new LinkedList<VOOperacion>();

		List<Operacion> a = pp.darOperacionesusuario(fechainferior, fechasuperior, tipoIdUsuario, idUsuario);
		for (Operacion beb : a) {
			voOperaciones.add(beb);
		}
		log.info("Generando los VO de las operaciones: " + voOperaciones.size() + " existentes");
		return voOperaciones;
	}

	public List<VOOperacion> darVOOperacionempleado(String fechainferior, String fechasuperior, String tipoIdEmpleado, BigDecimal idEmpleado) {
        log.info("Generando los VO de las operaciones\n");
		List<VOOperacion> voOperaciones = new LinkedList<VOOperacion>();

		List<Operacion> a = pp.darOperacionesempleado(fechainferior, fechasuperior, tipoIdEmpleado, idEmpleado);
		for (Operacion beb : a) {
			voOperaciones.add(beb);
		}
		log.info("Generando los VO de las operaciones: " + voOperaciones.size() + " existentes");
		return voOperaciones;
	}

	public List<VOOperacion> darVOOperacionNotipotransaccion(String fechainferior, String fechasuperior, String tipoOperacion) {
        log.info("Generando los VO de las operaciones\n");
		List<VOOperacion> voOperaciones = new LinkedList<VOOperacion>();

		List<Operacion> a = pp.darOperacionesNotipotransaccion(fechainferior, fechasuperior, tipoOperacion);
		for (Operacion beb : a) {
			voOperaciones.add(beb);
		}
		log.info("Generando los VO de las operaciones: " + voOperaciones.size() + " existentes");
		return voOperaciones;
	}

    public List<VOOperacion> darVOOperacionNovalor(String fechainferior, String fechasuperior, Long valorInferior, Long valorSuperior) {
        log.info("Generando los VO de las operaciones\n");
		List<VOOperacion> voOperaciones = new LinkedList<VOOperacion>();

		List<Operacion> a = pp.darOperacionesNovalor(fechainferior, fechasuperior, valorInferior, valorSuperior);
		for (Operacion beb : a) {
			voOperaciones.add(beb);
		}
		log.info("Generando los VO de las operaciones: " + voOperaciones.size() + " existentes");
		return voOperaciones;
	}

	public List<VOOperacion> darVOOperacionNorangoid(String fechainferior, String fechasuperior, Long idInferior, Long idSuperior) {
        log.info("Generando los VO de las operaciones\n");
		List<VOOperacion> voOperaciones = new LinkedList<VOOperacion>();

		List<Operacion> a = pp.darOperacionesNorangoid(fechainferior, fechasuperior, idInferior, idSuperior);
		for (Operacion beb : a) {
			voOperaciones.add(beb);
		}
		log.info("Generando los VO de las operaciones: " + voOperaciones.size() + " existentes");
		return voOperaciones;
	}

    public List<VOOperacion> darVOOperacionNopuestoatencion(String fechainferior, String fechasuperior, BigDecimal idPuestoAtencion) {
        log.info("Generando los VO de las operaciones\n");
		List<VOOperacion> voOperaciones = new LinkedList<VOOperacion>();

		List<Operacion> a = pp.darOperacionesNopuestoatencion(fechainferior, fechasuperior, idPuestoAtencion);
		for (Operacion beb : a) {
			voOperaciones.add(beb);
		}
		log.info("Generando los VO de las operaciones: " + voOperaciones.size() + " existentes");
		return voOperaciones;
	}

	public List<VOOperacion> darVOOperacionNocuentaorigen(String fechainferior, String fechasuperior, BigDecimal cuenta_origen) {
        log.info("Generando los VO de las operaciones\n");
		List<VOOperacion> voOperaciones = new LinkedList<VOOperacion>();

		List<Operacion> a = pp.darOperacionesNocuentaorigen(fechainferior, fechasuperior, cuenta_origen);
		for (Operacion beb : a) {
			voOperaciones.add(beb);
		}
		log.info("Generando los VO de las operaciones: " + voOperaciones.size() + " existentes");
		return voOperaciones;
	}

	public List<VOOperacion> darVOOperacionNocuentadestino(String fechainferior, String fechasuperior, BigDecimal cuenta_destino) {
        log.info("Generando los VO de las operaciones\n");
		List<VOOperacion> voOperaciones = new LinkedList<VOOperacion>();

		List<Operacion> a = pp.darOperacionesNocuentadestino(fechainferior, fechasuperior, cuenta_destino);
		for (Operacion beb : a) {
			voOperaciones.add(beb);
		}
		log.info("Generando los VO de las operaciones: " + voOperaciones.size() + " existentes");
		return voOperaciones;
	}

	public List<VOOperacion> darVOOperacionNousuario(String fechainferior, String fechasuperior, String tipoIdUsuario, BigDecimal idUsuario) {
        log.info("Generando los VO de las operaciones\n");
		List<VOOperacion> voOperaciones = new LinkedList<VOOperacion>();

		List<Operacion> a = pp.darOperacionesNousuario(fechainferior, fechasuperior, tipoIdUsuario, idUsuario);
		for (Operacion beb : a) {
			voOperaciones.add(beb);
		}
		log.info("Generando los VO de las operaciones: " + voOperaciones.size() + " existentes");
		return voOperaciones;
	}

	public List<VOOperacion> darVOOperacionNoempleado(String fechainferior, String fechasuperior, String tipoIdEmpleado, BigDecimal idEmpleado) {
        log.info("Generando los VO de las operaciones\n");
		List<VOOperacion> voOperaciones = new LinkedList<VOOperacion>();

		List<Operacion> a = pp.darOperacionesNoempleado(fechainferior, fechasuperior, tipoIdEmpleado, idEmpleado);
		for (Operacion beb : a) {
			voOperaciones.add(beb);
		}
		log.info("Generando los VO de las operaciones: " + voOperaciones.size() + " existentes");
		return voOperaciones;
	}

	public List<VOOperacion> darVOConsignaciones(Long valorX, String tipoProducto) {
        log.info("Generando los VO de las consignaciones\n");
		List<VOOperacion> voOperaciones = new LinkedList<VOOperacion>();

		List<Operacion> a = pp.darConsignaciones(valorX, tipoProducto);
		for (Operacion beb : a) {
			voOperaciones.add(beb);
		}
		log.info("Generando los VO de las consignaciones: " + voOperaciones.size() + " existentes");
		return voOperaciones;
	}

	public List<VOUsuario> darVOPatencionclientes(BigDecimal idPatencion1, BigDecimal idPatencion2) {
        log.info("Generando los VO de los clientes\n");
		List<VOUsuario> voUsuarios = new LinkedList<VOUsuario>();

		List<Usuario> a = pp.darPatencionclientes(idPatencion1, idPatencion2);
		for (Usuario beb : a) {
			voUsuarios.add(beb);
		}
		log.info("Generando los VO de las clientes: " + voUsuarios.size() + " existentes");
		return voUsuarios;
	}

    public List<VOOperacion> darVOPatencionoperaciones(BigDecimal idPatencion1, BigDecimal idPatencion2) {
        log.info("Generando los VO de las operaciones\n");
		List<VOOperacion> voOperaciones = new LinkedList<VOOperacion>();

		List<Operacion> a = pp.darPatencionoperaciones(idPatencion1, idPatencion2);
		for (Operacion beb : a) {
			voOperaciones.add(beb);
		}
		log.info("Generando los VO de las operaciones: " + voOperaciones.size() + " existentes");
		return voOperaciones;
	}


}
