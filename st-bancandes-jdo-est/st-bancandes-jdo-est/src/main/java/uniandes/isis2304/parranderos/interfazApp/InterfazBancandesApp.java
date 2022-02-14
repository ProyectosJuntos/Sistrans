/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 *
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Bancandes Uniandes
 * @version 1.0
 * @author Juliana Galeano (´▽`ʃ♡ƪ) & William Mendez
 * Julio de 2018
 *
 * Revisado por: Claudia Jiménez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.parranderos.interfazApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import org.apache.log4j.Logger;
import org.w3c.dom.events.EventTarget;

import uniandes.isis2304.parranderos.negocio.Bancandes;
import uniandes.isis2304.parranderos.negocio.Operacion;
import uniandes.isis2304.parranderos.negocio.Usuario;
import uniandes.isis2304.parranderos.negocio.VOOperacion;
import uniandes.isis2304.parranderos.negocio.VOPrestamo;
import uniandes.isis2304.parranderos.negocio.VOUsuario;

/**
 * Clase principal de la interfaz
 *
 * @author Germán Bravo
 */
@SuppressWarnings("serial")

public class InterfazBancandesApp extends JFrame implements ActionListener, ItemListener {
	/*
	 * **************************************************************** Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(InterfazBancandesApp.class.getName());

	/**
	 * Ruta al archivo de configuración de la interfaz
	 */

	private static final String CONFIG_INTERFAZ_ADMIN = "st-bancandes-jdo-est\\st-bancandes-jdo-est\\src\\main\\resources\\config\\interfaceConfigApp.json";
	private static final String CONFIG_INTERFAZ_GER_GENERAL = "st-bancandes-jdo-est\\st-bancandes-jdo-est\\src\\main\\resources\\config\\interfaceConfigAppGeneral.json";
	private static final String CONFIG_INTERFAZ_GER_OFICINA = "st-bancandes-jdo-est\\st-bancandes-jdo-est\\src\\main\\resources\\config\\interfaceConfigAppOficina.json";
	private static final String CONFIG_INTERFAZ_CAJERO = "st-bancandes-jdo-est\\st-bancandes-jdo-est\\src\\main\\resources\\config\\interfaceConfigAppCajero.json";
	private static final String CONFIG_INTERFAZ_CLIENTE = "st-bancandes-jdo-est\\st-bancandes-jdo-est\\src\\main\\resources\\config\\interfaceConfigAppCliente.json";

	/**
	 * Ruta al archivo de configuración de los nombres de tablas de la base de datos
	 */
	private static final String CONFIG_TABLAS = "st-bancandes-jdo-est\\st-bancandes-jdo-est\\src\\main\\resources\\config\\TablasBD_B.json";
	/*
	 * **************************************************************** Atributos
	 *****************************************************************/
	/**
	 * Objeto JSON con los nombres de las tablas de la base de datos que se quieren
	 * utilizar
	 */
	private JsonObject tableConfig;

	/**
	 * Asociación a la clase principal del negocio.
	 */
	private Bancandes bancandes;

	/*
	 * **************************************************************** Atributos de
	 * interfaz
	 *****************************************************************/
	/**
	 * Objeto JSON con la configuración de interfaz de la app.
	 */
	private JsonObject guiConfig;

	/**
	 * Panel de despliegue de interacción para los requerimientos
	 */
	private PanelDatos panelDatos;

	/**
	 * Menú de la aplicación
	 */
	private JMenuBar menuBar;

	private String tipoUsuarioSeleccionado;
	private String idUsuarioActual;
	private String tipoIdUsuarioActual;

	private JFrame registro;
	private JPanel panelRegistro = new JPanel();
	private JTextField codigo;
	private JPasswordField contrasenia;
	private JButton entrar;
	private JLabel label1, label2;
	private JComboBox<String> menuUsuarios;

	/*
	 * **************************************************************** Métodos
	 *****************************************************************/
	/**
	 * Construye la ventana principal de la aplicación. <br>
	 * <b>post:</b> Todos los componentes de la interfaz fueron inicializados.
	 */
	public InterfazBancandesApp() {

		guiConfig = openConfig("Interfaz", CONFIG_INTERFAZ_ADMIN);

		if (guiConfig != null) {
			crearMenu(guiConfig.getAsJsonArray("menuBar"));
		}

		tableConfig = openConfig("Tablas BD", CONFIG_TABLAS);
		bancandes = new Bancandes(tableConfig);

		// Configura la apariencia del frame que contiene la interfaz gráfica
		String path = guiConfig.get("bannerPath").getAsString();
		panelDatos = new PanelDatos();

		setLayout(new BorderLayout());
		add(new JLabel(new ImageIcon(path)), BorderLayout.NORTH);
		add(panelDatos, BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(false);

		registro = new JFrame();

		String[] usuarios = new String[] { "Seleccione su tipo de usuario", "Cliente", "Cajero", "Gerente Oficina",
				"Gerente general", "Administrador" };
		menuUsuarios = new JComboBox<String>(usuarios);
		menuUsuarios.setMaximumSize(new Dimension(210, 30));
		menuUsuarios.setAlignmentX(Component.CENTER_ALIGNMENT);
		menuUsuarios.addItemListener(this);
		label1 = new JLabel("Login");
		label1.setAlignmentX(Component.CENTER_ALIGNMENT);
		codigo = new JTextField();
		codigo.setMaximumSize(new Dimension(210, 30));
		codigo.setAlignmentX(Component.CENTER_ALIGNMENT);
		label2 = new JLabel("Contrasenia");
		label2.setAlignmentX(Component.CENTER_ALIGNMENT);
		contrasenia = new JPasswordField();
		contrasenia.setAlignmentX(Component.CENTER_ALIGNMENT);
		contrasenia.setMaximumSize(new Dimension(210, 30));
		entrar = new JButton("Entrar");
		entrar.addActionListener(this);
		entrar.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelRegistro.setLayout(new BoxLayout(panelRegistro, BoxLayout.Y_AXIS));
		panelRegistro.add(Box.createRigidArea(new Dimension(0, 30)));
		panelRegistro.add(menuUsuarios);
		panelRegistro.add(Box.createRigidArea(new Dimension(0, 20)));
		panelRegistro.add(label1);
		panelRegistro.add(Box.createRigidArea(new Dimension(0, 8)));
		panelRegistro.add(codigo);
		panelRegistro.add(Box.createRigidArea(new Dimension(0, 20)));
		panelRegistro.add(label2);
		panelRegistro.add(Box.createRigidArea(new Dimension(0, 8)));
		panelRegistro.add(contrasenia);
		panelRegistro.add(Box.createRigidArea(new Dimension(0, 8)));
		panelRegistro.add(entrar);
		panelRegistro.add(Box.createRigidArea(new Dimension(0, 25)));
		registro.add(panelRegistro);

		registro.setTitle("Registro");
		registro.setSize(275, 355);
		registro.setLocationRelativeTo(null);
		registro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		registro.setResizable(false);
		registro.setVisible(true);
	}

	/*
	 * **************************************************************** Métodos de
	 * configuración de la interfaz
	 *****************************************************************/
	/**
	 * Lee datos de configuración para la aplicació, a partir de un archivo JSON o
	 * con valores por defecto si hay errores.
	 *
	 * @param tipo       - El tipo de configuración deseada
	 * @param archConfig - Archivo Json que contiene la configuración
	 * @return Un objeto JSON con la configuración del tipo especificado NULL si hay
	 *         un error en el archivo.
	 */
	private JsonObject openConfig(String tipo, String archConfig) {
		JsonObject config = null;
		try {
			Gson gson = new Gson();
			FileReader file = new FileReader(archConfig);
			JsonReader reader = new JsonReader(file);
			config = gson.fromJson(reader, JsonObject.class);
			log.info("Se encontró un archivo de configuración válido: " + tipo);
		} catch (Exception e) {
			// e.printStackTrace ();
			log.info("NO se encontró un archivo de configuración válido");
			JOptionPane.showMessageDialog(null,
					"No se encontró un archivo de configuración de interfaz válido: " + tipo, "Bancandes App",
					JOptionPane.ERROR_MESSAGE);
		}
		return config;
	}

	/**
	 * Método para configurar el frame principal de la aplicación
	 */
	private void configurarFrame() {

		if (guiConfig != null) {
			crearMenu(guiConfig.getAsJsonArray("menuBar"));
		}

		tableConfig = openConfig("Tablas BD", CONFIG_TABLAS);
		bancandes = new Bancandes(tableConfig);

		int alto = 0;
		int ancho = 0;
		String titulo = "";

		if (guiConfig == null) {
			log.info("Se aplica configuración por defecto");
			titulo = "Bancandes APP Default";
			alto = 300;
			ancho = 500;
		} else {
			log.info("Se aplica configuración indicada en el archivo de configuración");
			titulo = guiConfig.get("title").getAsString();
			alto = guiConfig.get("frameH").getAsInt();
			ancho = guiConfig.get("frameW").getAsInt();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(50, 50);
		setResizable(true);
		setBackground(Color.WHITE);

		setTitle(titulo);
		setSize(ancho, alto);
	}

	/**
	 * Método para crear el menú de la aplicación con base em el objeto JSON leído
	 * Genera una barra de menú y los menús con sus respectivas opciones
	 *
	 * @param jsonMenu - Arreglo Json con los menùs deseados
	 */
	private void crearMenu(JsonArray jsonMenu) {
		// Creación de la barra de menús
		menuBar = new JMenuBar();
		for (JsonElement men : jsonMenu) {
			// Creación de cada uno de los menús
			JsonObject jom = men.getAsJsonObject();

			String menuTitle = jom.get("menuTitle").getAsString();
			JsonArray opciones = jom.getAsJsonArray("options");

			JMenu menu = new JMenu(menuTitle);

			for (JsonElement op : opciones) {
				// Creación de cada una de las opciones del menú
				JsonObject jo = op.getAsJsonObject();
				String lb = jo.get("label").getAsString();
				String event = jo.get("event").getAsString();

				JMenuItem mItem = new JMenuItem(lb);
				mItem.addActionListener(this);
				mItem.setActionCommand(event);

				menu.add(mItem);
			}
			menuBar.add(menu);
		}
		setJMenuBar(menuBar);
	}

	private static boolean verificarCredenciales(String tipoSeleccionado, String login, String contraseña) {

		return true;
	}

	/**
	 * Adiciona un tipo de bebida con la información dada por el usuario Se crea una
	 * nueva tupla de tipoBebida en la base de datos, si un tipo de bebida con ese
	 * nombre no existía
	 */
	public void adicionarUsuario() {
		try {
			String tipoUsuario = JOptionPane.showInputDialog(this, "Seleccione el tipo de persona", "Usuario",
					JOptionPane.PLAIN_MESSAGE, null, new Object[] { "Natural", "Juridica" }, "Natural").toString();
			String login = JOptionPane.showInputDialog(this, "Login?", "Ingrese su login",
					JOptionPane.QUESTION_MESSAGE);
			String clave = JOptionPane.showInputDialog(this, "Clave?", "Ingreser su clave",
					JOptionPane.QUESTION_MESSAGE);
			String tipoId = JOptionPane.showInputDialog(this, "Tipo de identificación?",
					"Ingrese su tipo de identificación (Ej: Cedula)", JOptionPane.QUESTION_MESSAGE);
			BigDecimal numId = BigDecimal.valueOf(Long.valueOf(JOptionPane.showInputDialog(this,
					"Numero de identificación?", "Ingresar numero de identificacion", JOptionPane.QUESTION_MESSAGE)));

			String nombre = JOptionPane.showInputDialog(this, "Nombre?", "Ingrese su nombre",
					JOptionPane.QUESTION_MESSAGE);
			String nacionalidad = JOptionPane.showInputDialog(this, "Nacionalidad?", "Ingresar nacionalidad",
					JOptionPane.QUESTION_MESSAGE);
			String dirFisica = JOptionPane.showInputDialog(this, "Direccion fisica?", "Ingresar direccion fisica",
					JOptionPane.QUESTION_MESSAGE);
			String dirElectronica = JOptionPane.showInputDialog(this, "Direccion electronica?",
					"Ingresar direccion electronica", JOptionPane.QUESTION_MESSAGE);
			String telefono = JOptionPane.showInputDialog(this, "Telefono?", "Ingresar telefono",
					JOptionPane.QUESTION_MESSAGE);
			String ciudad = JOptionPane.showInputDialog(this, "Ciudad?", "Ingresar ciudad",
					JOptionPane.QUESTION_MESSAGE);
			String departamento = JOptionPane.showInputDialog(this, "Departamento?", "Ingresar departamento",
					JOptionPane.QUESTION_MESSAGE);
			Integer codigoPostal = Integer.valueOf(JOptionPane.showInputDialog(this, "Codigo postal?",
					"Ingresar codigo postal", JOptionPane.QUESTION_MESSAGE));

			if (nombre != null) {
				VOUsuario tb = bancandes.adicionarUsuario(tipoUsuario, login, clave, tipoId, numId, nombre,
						nacionalidad, dirFisica, dirElectronica, telefono, ciudad, departamento, codigoPostal);
				if (tb == null) {
					throw new Exception("No se pudo crear un usuario con nombre: " + nombre);
				}
				String resultado = "En adicionarUsuario\n\n";
				resultado += "Usuario adicionado exitosamente: " + tb;
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			} else {
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} catch (Exception e) {
			// e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Consulta en la base de datos los tipos de bebida existentes y los muestra en
	 * el panel de datos de la aplicación
	 */
	public void listarUsuario() {
		try {
			List<VOUsuario> lista = bancandes.darVOUsuarios();

			String resultado = "En listarUsuario";
			resultado += "\n" + listarUsuarios(lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operación terminada";
		} catch (Exception e) {
			// e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Borra de la base de datos el tipo de bebida con el identificador dado po el
	 * usuario Cuando dicho tipo de bebida no existe, se indica que se borraron 0
	 * registros de la base de datos
	 */
	// public void eliminarTipoBebidaPorId( )
	// {
	// try
	// {
	// String idTipoStr = JOptionPane.showInputDialog (this, "Id del tipo de
	// bedida?", "Borrar tipo de bebida por Id", JOptionPane.QUESTION_MESSAGE);
	// if (idTipoStr != null)
	// {
	// long idTipo = Long.valueOf (idTipoStr);
	// long tbEliminados = bancandes.eliminarTipoBebidaPorId (idTipo);

	// String resultado = "En eliminar TipoBebida\n\n";
	// resultado += tbEliminados + " Tipos de bebida eliminados\n";
	// resultado += "\n Operación terminada";
	// panelDatos.actualizarInterfaz(resultado);
	// }
	// else
	// {
	// panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
	// }
	// }
	// catch (Exception e)
	// {
	// // e.printStackTrace();
	// String resultado = generarMensajeError(e);
	// panelDatos.actualizarInterfaz(resultado);
	// }
	// }

	/**
	 * Busca el tipo de bebida con el nombre indicado por el usuario y lo muestra en
	 * el panel de datos
	 */
	// public void buscarTipoBebidaPorNombre( )
	// {
	// try
	// {
	// String nombreTb = JOptionPane.showInputDialog (this, "Nombre del tipo de
	// bedida?", "Buscar tipo de bebida por nombre", JOptionPane.QUESTION_MESSAGE);
	// if (nombreTb != null)
	// {
	// VOTipoBebida tipoBebida = bancandes.darTipoBebidaPorNombre (nombreTb);
	// String resultado = "En buscar Tipo Bebida por nombre\n\n";
	// if (tipoBebida != null)
	// {
	// resultado += "El tipo de bebida es: " + tipoBebida;
	// }
	// else
	// {
	// resultado += "Un tipo de bebida con nombre: " + nombreTb + " NO EXISTE\n";
	// }
	// resultado += "\n Operación terminada";
	// panelDatos.actualizarInterfaz(resultado);
	// }
	// else
	// {
	// panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
	// }
	// }
	// catch (Exception e)
	// {
	// // e.printStackTrace();
	// String resultado = generarMensajeError(e);
	// panelDatos.actualizarInterfaz(resultado);
	// }
	// }

	/*
	 * **************************************************************** Métodos
	 * administrativos
	 *****************************************************************/
	/**
	 * Muestra el log de Bancandes
	 */
	public void mostrarLogBancandes() {
		mostrarArchivo("bancandes.log");
	}

	/**
	 * Muestra el log de datanucleus
	 */
	public void mostrarLogDatanuecleus() {
		mostrarArchivo("datanucleus.log");
	}

	/**
	 * Limpia el contenido del log de bancandes Muestra en el panel de datos la
	 * traza de la ejecución
	 */
	public void limpiarLogBancandes() {
		// Ejecución de la operación y recolección de los resultados
		boolean resp = limpiarArchivo("bancandes.log");

		// Generación de la cadena de caracteres con la traza de la ejecución de la demo
		String resultado = "\n\n************ Limpiando el log de bancandes ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}

	/**
	 * Limpia el contenido del log de datanucleus Muestra en el panel de datos la
	 * traza de la ejecución
	 */
	public void limpiarLogDatanucleus() {
		// Ejecución de la operación y recolección de los resultados
		boolean resp = limpiarArchivo("datanucleus.log");

		// Generación de la cadena de caracteres con la traza de la ejecución de la demo
		String resultado = "\n\n************ Limpiando el log de datanucleus ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}

	/**
	 * Limpia todas las tuplas de todas las tablas de la base de datos de bancandes
	 * Muestra en el panel de datos el número de tuplas eliminadas de cada tabla
	 */
	public void limpiarBD() {
		try {
			// Ejecución de la demo y recolección de los resultados
			long eliminados[] = bancandes.limpiarBancandes();

			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "\n\n************ Limpiando la base de datos ************ \n";
			resultado += eliminados[0] + " Gustan eliminados\n";
			resultado += eliminados[1] + " Sirven eliminados\n";
			resultado += eliminados[2] + " Visitan eliminados\n";
			resultado += eliminados[3] + " Bebidas eliminadas\n";
			resultado += eliminados[4] + " Tipos de bebida eliminados\n";
			resultado += eliminados[5] + " Bebedores eliminados\n";
			resultado += eliminados[6] + " Bares eliminados\n";
			resultado += "\nLimpieza terminada";

			panelDatos.actualizarInterfaz(resultado);
		} catch (Exception e) {
			// e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Muestra la presentación general del proyecto
	 */
	public void mostrarPresentacionGeneral() {
		mostrarArchivo("data/00-ST-BancandesJDO.pdf");
	}

	/**
	 * Muestra el modelo conceptual de Bancandes
	 */
	public void mostrarModeloConceptual() {
		mostrarArchivo("data/Modelo Conceptual Bancandes.pdf");
	}

	/**
	 * Muestra el esquema de la base de datos de Bancandes
	 */
	public void mostrarEsquemaBD() {
		mostrarArchivo("data/Esquema BD Bancandes.pdf");
	}

	/**
	 * Muestra el script de creación de la base de datos
	 */
	public void mostrarScriptBD() {
		mostrarArchivo("data/EsquemaBancandes.sql");
	}

	/**
	 * Muestra la arquitectura de referencia para Bancandes
	 */
	public void mostrarArqRef() {
		mostrarArchivo("data/ArquitecturaReferencia.pdf");
	}

	/**
	 * Muestra la documentación Javadoc del proyectp
	 */
	public void mostrarJavadoc() {
		mostrarArchivo("doc/index.html");
	}

	/**
	 * Muestra la información acerca del desarrollo de esta apicación
	 */
	public void acercaDe() {
		String resultado = "\n\n ************************************\n\n";
		resultado += " * Universidad	de	los	Andes	(Bogotá	- Colombia)\n";
		resultado += " * Departamento	de	Ingeniería	de	Sistemas	y	Computación\n";
		resultado += " * Licenciado	bajo	el	esquema	Academic Free License versión 2.1\n";
		resultado += " * \n";
		resultado += " * Curso: isis2304 - Sistemas Transaccionales\n";
		resultado += " * Proyecto: Bancandes Uniandes\n";
		resultado += " * @version 1.0\n";
		resultado += " * @author Juliana Galeano (´▽`ʃ♡ƪ) & William Mendez\n";
		resultado += " * Noviembre de 2018\n";
		resultado += " * \n";
		resultado += "\n ************************************\n\n";

		panelDatos.actualizarInterfaz(resultado);
	}

	/*
	 * **************************************************************** Métodos
	 * privados para la presentación de resultados y otras operaciones
	 *****************************************************************/

	private String[] obtenerEmpleado(String usuarioSeleccionado2, String login, String contrasenia) throws Exception {
		String[] resul = { null, null };
		resul = bancandes.obtenerEmpleado(usuarioSeleccionado2, login, contrasenia);
		return resul;
	}

	private String[] obtenerUsuario(String login, String contrasenia) {
		String[] resul = bancandes.obtenerUsuario(login, contrasenia);
		return resul;
	}

	/**
	 * Genera una cadena de caracteres con la lista de los tipos de bebida recibida:
	 * una línea por cada tipo de bebida
	 *
	 * @param lista - La lista con los tipos de bebida
	 * @return La cadena con una líea para cada tipo de bebida recibido
	 */
	private String listarUsuarios(List<VOUsuario> lista) {
		String resp = "Los usuarios existentes son:\n";
		int i = 1;
		for (VOUsuario tb : lista) {
			resp += i++ + ". " + tb.toString() + "\n";
		}
		return resp;
	}

	public String consultarUsuario() {
		try {
			String idUsuario = JOptionPane.showInputDialog(this, "Id del usuario de bedida?", "Buscar usuario por id",
					JOptionPane.QUESTION_MESSAGE);
			if (idUsuario != null) {
				// VOUsuario usuario = bancandes.buscarUsuarioPorId();
				String resultado = "En buscar Tipo Bebida por nombre\n\n";
				// if (usuario != null) {
				// 	resultado += "El tipo de bebida es: " + usuario;
				// } else {
				// 	resultado += "Un tipo de bebida con nombre: " + idUsuario + " NO EXISTE\n";
				// }
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			} else {
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} catch (Exception e) {
			// e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
		return "End";
	}

	public void OperacionSobreCuenta() {
		try {
			String tipoUsuarioSeleccionado = this.tipoUsuarioSeleccionado;
			BigDecimal cuenta_origen = null;
			BigDecimal cuenta_destino = null;
			String tipoIdUsuario = null;
			String idUsuario = null;
			String tipoIdEmpleado = null;
			String idEmpleado = null;

			String tipoOperacion = JOptionPane.showInputDialog(this, "Seleccione el tipo de operacion", "Operacion",
					JOptionPane.PLAIN_MESSAGE, null, new Object[] { "Retiro", "Consignacion", "Transferencia" },
					"Retiro").toString();
			Long valor = Long.parseLong(JOptionPane.showInputDialog(this, "Valor?", "Ingrese el valor de la operacion",
					JOptionPane.QUESTION_MESSAGE));
			BigDecimal idPuestoAtencion = null;
			Timestamp fecha = new Timestamp(System.currentTimeMillis());
			if (this.tipoUsuarioSeleccionado.equals("Cliente")) {
				tipoIdUsuario = this.tipoIdUsuarioActual;
				idUsuario = this.idUsuarioActual;
			}
			if (this.tipoUsuarioSeleccionado.equals("Cajero")) {
				idPuestoAtencion = BigDecimal.valueOf(Long.valueOf(JOptionPane.showInputDialog(this,
						"Puesto de atencion?", "Ingresar numero de su puesto de atencion",
						JOptionPane.QUESTION_MESSAGE)));
				tipoIdUsuario = JOptionPane.showInputDialog(this, "Tipoid?", "Ingreser el tipo de id del cliente",
						JOptionPane.QUESTION_MESSAGE);
				idUsuario = JOptionPane.showInputDialog(this, "Id?", "Ingreser la id del cliente",
						JOptionPane.QUESTION_MESSAGE);
				tipoIdEmpleado = this.tipoIdUsuarioActual;
				idEmpleado = this.idUsuarioActual;

			}
			if (tipoOperacion.equals("Retiro") || tipoOperacion.equals("Consignacion")) {
				cuenta_origen = BigDecimal.valueOf(Long.valueOf(JOptionPane.showInputDialog(this,
						"Numero de cuenta?", "Ingresar numero de la cuenta", JOptionPane.QUESTION_MESSAGE)));
				cuenta_destino = null;
			}
			if (tipoOperacion.equals("Transferencia")) {
				cuenta_origen = BigDecimal.valueOf(Long.valueOf(JOptionPane.showInputDialog(this,
						"Numero de cuenta?", "Ingresar numero de la cuenta de origen", JOptionPane.QUESTION_MESSAGE)));
				cuenta_destino = BigDecimal.valueOf(Long.valueOf(JOptionPane.showInputDialog(this,
						"Numero de cuenta?", "Ingresar numero de la cuenta de destino", JOptionPane.QUESTION_MESSAGE)));
			}

			if (cuenta_origen != null) {
				String tb = bancandes.OperacionSobreCuenta(tipoUsuarioSeleccionado, tipoOperacion, valor, fecha,
						idPuestoAtencion, tipoIdUsuario, idUsuario, cuenta_origen, cuenta_destino, tipoIdEmpleado,
						idEmpleado);
				if (tb.equals(null)) {
					throw new Exception("No se pudo realizar la operacion");
				}
				String resultado = "En OperacionSobreCuenta\n\n";
				resultado += "Operacion exitosa";
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			} else {
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} catch (Exception e) {
			// e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	public void OperacionSobrePrestamo() {
		try {
			String tipoUsuarioSeleccionado = this.tipoUsuarioSeleccionado;
			BigDecimal cuenta_origen = null;
			BigDecimal cuenta_destino = null;
			String tipoIdUsuario = null;
			String idUsuario = null;
			String tipoIdEmpleado = null;
			String idEmpleado = null;

			String tipoOperacion = JOptionPane.showInputDialog(this, "Seleccione el tipo de operacion", "Operacion",
					JOptionPane.PLAIN_MESSAGE, null, new Object[] { "Retiro", "Consignacion" }, "Retiro").toString();
			Long valor = Long.parseLong(JOptionPane.showInputDialog(this, "Valor?", "Ingrese el valor de la operacion",
					JOptionPane.QUESTION_MESSAGE));
			BigDecimal idPuestoAtencion = null;
			Timestamp fecha = new Timestamp(System.currentTimeMillis());
			if (this.tipoUsuarioSeleccionado.equals("Cliente")) {
				tipoIdUsuario = this.tipoIdUsuarioActual;
				idUsuario = this.idUsuarioActual;
			}
			if (this.tipoUsuarioSeleccionado.equals("Cajero")) {
				idPuestoAtencion = BigDecimal.valueOf(Long.valueOf(JOptionPane.showInputDialog(this,
						"Puesto de atencion?", "Ingresar numero de su puesto de atencion",
						JOptionPane.QUESTION_MESSAGE)));
				tipoIdUsuario = JOptionPane.showInputDialog(this, "Tipoid?", "Ingreser el tipo de id del cliente",
						JOptionPane.QUESTION_MESSAGE);
				idUsuario = JOptionPane.showInputDialog(this, "Id?", "Ingreser la id del cliente",
						JOptionPane.QUESTION_MESSAGE);
				tipoIdEmpleado = this.tipoIdUsuarioActual;
				idEmpleado = this.idUsuarioActual;

			}
			if (tipoOperacion.equals("Retiro") || tipoOperacion.equals("Consignacion")) {
				cuenta_origen = BigDecimal.valueOf(Long.valueOf(JOptionPane.showInputDialog(this,
						"Numero del prestamo?", "Ingresar numero del prestamo", JOptionPane.QUESTION_MESSAGE)));
				cuenta_destino = null;
			}

			if (cuenta_origen != null) {
				String tb = bancandes.OperacionSobrePrestamo(tipoUsuarioSeleccionado, tipoOperacion, valor, fecha,
						idPuestoAtencion, tipoIdUsuario, idUsuario, cuenta_origen, cuenta_destino, tipoIdEmpleado,
						idEmpleado);
				if (tb.equals(null)) {
					throw new Exception("No se pudo realizar la operacion");
				}
				String resultado = "En OperacionSobreCuenta\n\n";
				resultado += "Operacion exitosa";
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			} else {
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} catch (Exception e) {
			// e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	public String listarUsuarios() {
		return "Not implemented";
	}

	public String desactivarUsuarioPorId() {
		return "Not implemented";
	}

	public String buscarUsuarioMasActivo() {
		return "Not implemented";
	}

	public String adicionarEmpleado() {
		return "Not implemented";
	}

	public String asociarCuentaParaPagoEmpleado() {
		String resultado = "Intentando asociar la cuenta para pago...\n\n";
		try {
			String tipoIdEmpleado = JOptionPane.showInputDialog(this, "Tipo de documento del usuario?",
					"Buscar usuario por id", JOptionPane.QUESTION_MESSAGE);
			Long idEmpleado = Long.valueOf(JOptionPane.showInputDialog(this, "Numero de documento del usuario?",
					"Buscar usuario por id", JOptionPane.QUESTION_MESSAGE));
			Long idCuentaPago = Long
					.valueOf(JOptionPane.showInputDialog(this, "Numero de su cuenta que se usará para pagar?",
							"Buscar usuario por id", JOptionPane.QUESTION_MESSAGE));
			Long idCuentaRecibir = Long
					.valueOf(JOptionPane.showInputDialog(this, "Numero de cuenta que va a recibir los pagos?",
							"Buscar usuario por id", JOptionPane.QUESTION_MESSAGE));
			Long valor = Long.valueOf(JOptionPane.showInputDialog(this, "Cuanto se le pagará al empleado?",
					"Buscar usuario por id", JOptionPane.QUESTION_MESSAGE));
			String frecuencia = JOptionPane.showInputDialog(this, "Seleccione el tipo de persona", "Usuario",
					JOptionPane.PLAIN_MESSAGE, null, new Object[] { "Quincenal", "Mensual" }, "Quincenal").toString();

			if (idEmpleado != null && tipoIdEmpleado != null && idCuentaPago != null && idCuentaRecibir != null) {
				resultado += bancandes.asociarCuentaParaPago(tipoUsuarioSeleccionado, tipoIdUsuarioActual,
						BigDecimal.valueOf(Long.valueOf(idUsuarioActual)), tipoIdEmpleado,
						BigDecimal.valueOf(idEmpleado), BigDecimal.valueOf(idCuentaPago),
						BigDecimal.valueOf(idCuentaRecibir), frecuencia, valor);
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			} else {
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} catch (Exception e) {
			// e.printStackTrace();
			resultado += generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
		return resultado;
	}

	public String adicionarCuenta() {
		return "Not implemented";
	}

	public String listarCuentas() {
		return "Not implemented";
	}

	public String cerrarCuentaPorId() {
		return "Not implemented";
	}

	public String pagarNomina() {
		return "Not implemented";
	}

	public String adicionarPrestamo() {
		return "Not implemented";
	}

	public void consultarOperacionesV2() {
		{

			String fechainferior = JOptionPane.showInputDialog(this,
					"Ingrese la fecha desde la que quiere consutlar en formato dd/mm/aa", "Fecha inferior",
					JOptionPane.QUESTION_MESSAGE);
			String fechasuperior = JOptionPane.showInputDialog(this,
					"Ingrese la fecha hasta la que quiere consutlar en formato dd/mm/aa", "Fecha superior",
					JOptionPane.QUESTION_MESSAGE);
			String criterio = JOptionPane
					.showInputDialog(this, "Seleccione el criterio de busqueda", "Criterio de busqueda",
							JOptionPane.PLAIN_MESSAGE, null,
							new Object[] { "Tipo de transaccion", "Valor entre", "Rango de id", "Id puesto de atencion",
									"Usuario", "Cuenta origen", "Cuenta destino", "Cajero" },
							"tipo de transaccion")
					.toString();
			if (criterio.equals("Tipo de transaccion")) {
				String tipoOperacion = JOptionPane.showInputDialog(this, "Seleccione el tipo de operacion", "Operacion",
						JOptionPane.PLAIN_MESSAGE, null, new Object[] { "Retiro", "Consignacion", "Transferencia" },
						"Retiro").toString();
				try {
					long TInicio, TFin, tiempo;
					TInicio = System.currentTimeMillis();
					List<VOOperacion> lista = bancandes.darVOOperaciontipotransaccion(fechainferior, fechasuperior,
							tipoOperacion);
					String resultado = "En listarOperacionesV2";
					resultado += "\n" + consultarOperaciones(lista);
					panelDatos.actualizarInterfaz(resultado);
					TFin = System.currentTimeMillis();
					tiempo = TFin - TInicio;
					System.out.println("Tiempo de ejecución en milisegundos: " + tiempo);
					resultado += "\n Operación terminada";
				} catch (Exception e) {
					// e.printStackTrace();
					String resultado = generarMensajeError(e);
					panelDatos.actualizarInterfaz(resultado);
				}
			} else if (criterio.equals("Valor entre")) {
				Long valorInferior = Long.parseLong(
						JOptionPane.showInputDialog(this, "Ingrese el valor inferior del rango", "Valor inferior",
								JOptionPane.QUESTION_MESSAGE));
				Long valorSuperior = Long.parseLong(
						JOptionPane.showInputDialog(this, "Ingrese el valor superior del rango", "Valor superior",
								JOptionPane.QUESTION_MESSAGE));
				try {
					long TInicio, TFin, tiempo;
					TInicio = System.currentTimeMillis();
					List<VOOperacion> lista = bancandes.darVOOperacionvalor(fechainferior, fechasuperior, valorInferior,
							valorSuperior);
					String resultado = "En listarOperacionesV2";
					resultado += "\n" + consultarOperaciones(lista);
					panelDatos.actualizarInterfaz(resultado);
					TFin = System.currentTimeMillis();
					tiempo = TFin - TInicio;
					System.out.println("Tiempo de ejecución en milisegundos: " + tiempo);
					resultado += "\n Operación terminada";
				} catch (Exception e) {
					// e.printStackTrace();
					String resultado = generarMensajeError(e);
					panelDatos.actualizarInterfaz(resultado);
				}
			} else if (criterio.equals("Rango de id")) {
				Long idInferior = Long.parseLong(
						JOptionPane.showInputDialog(this, "Ingrese el valor inferior del rango", "Id inferior",
								JOptionPane.QUESTION_MESSAGE));
				Long idSuperior = Long.parseLong(
						JOptionPane.showInputDialog(this, "Ingrese el valor superior del rango", "Id superior",
								JOptionPane.QUESTION_MESSAGE));
				try {
					long TInicio, TFin, tiempo;
					TInicio = System.currentTimeMillis();
					List<VOOperacion> lista = bancandes.darVOOperacionrangoid(fechainferior, fechasuperior,
							idInferior, idSuperior);
					String resultado = "En listarOperacionesV2";
					resultado += "\n" + consultarOperaciones(lista);
					panelDatos.actualizarInterfaz(resultado);
					TFin = System.currentTimeMillis();
					tiempo = TFin - TInicio;
					System.out.println("Tiempo de ejecución en milisegundos: " + tiempo);
					resultado += "\n Operación terminada";
				} catch (Exception e) {
					// e.printStackTrace();
					String resultado = generarMensajeError(e);
					panelDatos.actualizarInterfaz(resultado);
				}
			} else if (criterio.equals("Id puesto de atencion")) {
				BigDecimal idPuestoAtencion = BigDecimal.valueOf(Long.parseLong(
						JOptionPane.showInputDialog(this, "Ingrese el id del puesto de atencion", "Puesto de atencion",
								JOptionPane.QUESTION_MESSAGE)));
				try {
					long TInicio, TFin, tiempo;
					TInicio = System.currentTimeMillis();
					List<VOOperacion> lista = bancandes.darVOOperacionpuestoatencion(fechainferior, fechasuperior, idPuestoAtencion);
					String resultado = "En listarOperacionesV2";
					resultado += "\n" + consultarOperaciones(lista);
					panelDatos.actualizarInterfaz(resultado);
					TFin = System.currentTimeMillis();
					tiempo = TFin - TInicio;
					System.out.println("Tiempo de ejecución en milisegundos: " + tiempo);
					resultado += "\n Operación terminada";
				} catch (Exception e) {
					// e.printStackTrace();
					String resultado = generarMensajeError(e);
					panelDatos.actualizarInterfaz(resultado);
				}
			} else if (criterio.equals("Cuenta origen")) {
				BigDecimal cuenta_origen = BigDecimal.valueOf(Long.parseLong(
						JOptionPane.showInputDialog(this, "Ingrese el id de la cuenta de origen", "Cuenta de origen",
								JOptionPane.QUESTION_MESSAGE)));
				try {
					long TInicio, TFin, tiempo;
					TInicio = System.currentTimeMillis();
					List<VOOperacion> lista = bancandes.darVOOperacioncuentaorigen(fechainferior, fechasuperior, cuenta_origen);
					String resultado = "En listarOperacionesV2";
					resultado += "\n" + consultarOperaciones(lista);
					panelDatos.actualizarInterfaz(resultado);
					TFin = System.currentTimeMillis();
					tiempo = TFin - TInicio;
					System.out.println("Tiempo de ejecución en milisegundos: " + tiempo);
					resultado += "\n Operación terminada";
				} catch (Exception e) {
					// e.printStackTrace();
					String resultado = generarMensajeError(e);
					panelDatos.actualizarInterfaz(resultado);
				}
			} else if (criterio.equals("Cuenta destino")) {
				BigDecimal cuenta_destino = BigDecimal.valueOf(Long.parseLong(
						JOptionPane.showInputDialog(this, "Ingrese el id de la cuenta de destino", "Cuenta de destino",
								JOptionPane.QUESTION_MESSAGE)));
				try {
					long TInicio, TFin, tiempo;
					TInicio = System.currentTimeMillis();
					List<VOOperacion> lista = bancandes.darVOOperacioncuentadestino(fechainferior, fechasuperior, cuenta_destino);
					String resultado = "En listarOperacionesV2";
					resultado += "\n" + consultarOperaciones(lista);
					panelDatos.actualizarInterfaz(resultado);
					TFin = System.currentTimeMillis();
					tiempo = TFin - TInicio;
					System.out.println("Tiempo de ejecución en milisegundos: " + tiempo);
					resultado += "\n Operación terminada";
				} catch (Exception e) {
					// e.printStackTrace();
					String resultado = generarMensajeError(e);
					panelDatos.actualizarInterfaz(resultado);
				}
			} else if (criterio.equals("Usuario")) {
				String tipoIdUsuario = JOptionPane
					.showInputDialog(this, "Seleccione el tipo de id del usuario", "Tipo de identificacion",
							JOptionPane.PLAIN_MESSAGE, null,
							new Object[] { "CC", "CI", "TP", "CE"},"CC")
					.toString();
				BigDecimal idUsuario = BigDecimal.valueOf(Long.parseLong(
						JOptionPane.showInputDialog(this, "Ingrese el id del usuario", "Identificacion del usuario",
								JOptionPane.QUESTION_MESSAGE)));
				try {
					long TInicio, TFin, tiempo;
					TInicio = System.currentTimeMillis();
					List<VOOperacion> lista = bancandes.darVOOperacionusuario(fechainferior, fechasuperior, tipoIdUsuario, idUsuario);
					String resultado = "En listarOperacionesV2";
					resultado += "\n" + consultarOperaciones(lista);
					panelDatos.actualizarInterfaz(resultado);
					TFin = System.currentTimeMillis();
					tiempo = TFin - TInicio;
					System.out.println("Tiempo de ejecución en milisegundos: " + tiempo);
					resultado += "\n Operación terminada";
				} catch (Exception e) {
					// e.printStackTrace();
					String resultado = generarMensajeError(e);
					panelDatos.actualizarInterfaz(resultado);
				}
			} else if (criterio.equals("Cajero")) {
				String tipoIdEmpleado = JOptionPane
					.showInputDialog(this, "Seleccione el tipo de id del cajero", "Tipo de identificacion",
							JOptionPane.PLAIN_MESSAGE, null,
							new Object[] { "CC", "CI", "TP", "CE"},"CC")
					.toString();
				BigDecimal idEmpleado  = BigDecimal.valueOf(Long.parseLong(
						JOptionPane.showInputDialog(this, "Ingrese el id del cajero", "Identificacion del cajero",
								JOptionPane.QUESTION_MESSAGE)));
				try {
					long TInicio, TFin, tiempo;
					TInicio = System.currentTimeMillis();
					List<VOOperacion> lista = bancandes.darVOOperacionempleado(fechainferior, fechasuperior, tipoIdEmpleado, idEmpleado);
					String resultado = "En listarOperacionesV2";
					resultado += "\n" + consultarOperaciones(lista);
					panelDatos.actualizarInterfaz(resultado);
					TFin = System.currentTimeMillis();
					tiempo = TFin - TInicio;
					System.out.println("Tiempo de ejecución en milisegundos: " + tiempo);
					resultado += "\n Operación terminada";
				} catch (Exception e) {
					// e.printStackTrace();
					String resultado = generarMensajeError(e);
					panelDatos.actualizarInterfaz(resultado);
				}
			}
		}
	}
	public void consutarOperacionesV3() {
		{

			String fechainferior = JOptionPane.showInputDialog(this,
					"Ingrese la fecha desde la que quiere consutlar en formato dd/mm/aa", "Fecha inferior",
					JOptionPane.QUESTION_MESSAGE);
			String fechasuperior = JOptionPane.showInputDialog(this,
					"Ingrese la fecha hasta la que quiere consutlar en formato dd/mm/aa", "Fecha superior",
					JOptionPane.QUESTION_MESSAGE);
			String criterio = JOptionPane
					.showInputDialog(this, "Seleccione el criterio de busqueda que no se cumplira", "Criterio de busqueda",
							JOptionPane.PLAIN_MESSAGE, null,
							new Object[] { "Tipo de transaccion", "Valor entre", "Rango de id", "Id puesto de atencion",
									"Usuario", "Cuenta origen", "Cuenta destino", "Cajero" },
							"tipo de transaccion")
					.toString();
			if (criterio.equals("Tipo de transaccion")) {
				String tipoOperacion = JOptionPane.showInputDialog(this, "Seleccione el tipo de operacion", "Operacion",
						JOptionPane.PLAIN_MESSAGE, null, new Object[] { "Retiro", "Consignacion", "Transferencia" },
						"Retiro").toString();
				try {
					long TInicio, TFin, tiempo;
					TInicio = System.currentTimeMillis();
					List<VOOperacion> lista = bancandes.darVOOperacionNotipotransaccion(fechainferior, fechasuperior,
							tipoOperacion);
					String resultado = "En listarOperacionesV2";
					resultado += "\n" + consultarOperaciones(lista);
					panelDatos.actualizarInterfaz(resultado);
					TFin = System.currentTimeMillis();
					tiempo = TFin - TInicio;
					System.out.println("Tiempo de ejecución en milisegundos: " + tiempo);
					resultado += "\n Operación terminada";
				} catch (Exception e) {
					// e.printStackTrace();
					String resultado = generarMensajeError(e);
					panelDatos.actualizarInterfaz(resultado);
				}
			} else if (criterio.equals("Valor entre")) {
				Long valorInferior = Long.parseLong(
						JOptionPane.showInputDialog(this, "Ingrese el valor inferior del rango", "Valor inferior",
								JOptionPane.QUESTION_MESSAGE));
				Long valorSuperior = Long.parseLong(
						JOptionPane.showInputDialog(this, "Ingrese el valor superior del rango", "Valor superior",
								JOptionPane.QUESTION_MESSAGE));
				try {
					long TInicio, TFin, tiempo;
					TInicio = System.currentTimeMillis();
					List<VOOperacion> lista = bancandes.darVOOperacionNovalor(fechainferior, fechasuperior, valorInferior,
							valorSuperior);
					String resultado = "En listarOperacionesV2";
					resultado += "\n" + consultarOperaciones(lista);
					panelDatos.actualizarInterfaz(resultado);
					TFin = System.currentTimeMillis();
					tiempo = TFin - TInicio;
					System.out.println("Tiempo de ejecución en milisegundos: " + tiempo);
					resultado += "\n Operación terminada";
				} catch (Exception e) {
					// e.printStackTrace();
					String resultado = generarMensajeError(e);
					panelDatos.actualizarInterfaz(resultado);
				}
			} else if (criterio.equals("Rango de id")) {
				Long idInferior = Long.parseLong(
						JOptionPane.showInputDialog(this, "Ingrese el valor inferior del rango", "Id inferior",
								JOptionPane.QUESTION_MESSAGE));
				Long idSuperior = Long.parseLong(
						JOptionPane.showInputDialog(this, "Ingrese el valor superior del rango", "Id superior",
								JOptionPane.QUESTION_MESSAGE));
				try {
					long TInicio, TFin, tiempo;
					TInicio = System.currentTimeMillis();
					List<VOOperacion> lista = bancandes.darVOOperacionNorangoid(fechainferior, fechasuperior,
							idInferior, idSuperior);
					String resultado = "En listarOperacionesV2";
					resultado += "\n" + consultarOperaciones(lista);
					panelDatos.actualizarInterfaz(resultado);
					TFin = System.currentTimeMillis();
					tiempo = TFin - TInicio;
					System.out.println("Tiempo de ejecución en milisegundos: " + tiempo);
					resultado += "\n Operación terminada";
				} catch (Exception e) {
					// e.printStackTrace();
					String resultado = generarMensajeError(e);
					panelDatos.actualizarInterfaz(resultado);
				}
			} else if (criterio.equals("Id puesto de atencion")) {
				BigDecimal idPuestoAtencion = BigDecimal.valueOf(Long.parseLong(
						JOptionPane.showInputDialog(this, "Ingrese el id del puesto de atencion", "Puesto de atencion",
								JOptionPane.QUESTION_MESSAGE)));
				try {
					long TInicio, TFin, tiempo;
					TInicio = System.currentTimeMillis();
					List<VOOperacion> lista = bancandes.darVOOperacionNopuestoatencion(fechainferior, fechasuperior, idPuestoAtencion);
					String resultado = "En listarOperacionesV2";
					resultado += "\n" + consultarOperaciones(lista);
					panelDatos.actualizarInterfaz(resultado);
					TFin = System.currentTimeMillis();
					tiempo = TFin - TInicio;
					System.out.println("Tiempo de ejecución en milisegundos: " + tiempo);
					resultado += "\n Operación terminada";
				} catch (Exception e) {
					// e.printStackTrace();
					String resultado = generarMensajeError(e);
					panelDatos.actualizarInterfaz(resultado);
				}
			} else if (criterio.equals("Cuenta origen")) {
				BigDecimal cuenta_origen = BigDecimal.valueOf(Long.parseLong(
						JOptionPane.showInputDialog(this, "Ingrese el id de la cuenta de origen", "Cuenta de origen",
								JOptionPane.QUESTION_MESSAGE)));
				try {
					long TInicio, TFin, tiempo;
					TInicio = System.currentTimeMillis();
					List<VOOperacion> lista = bancandes.darVOOperacionNocuentaorigen(fechainferior, fechasuperior, cuenta_origen);
					String resultado = "En listarOperacionesV2";
					resultado += "\n" + consultarOperaciones(lista);
					panelDatos.actualizarInterfaz(resultado);
					TFin = System.currentTimeMillis();
					tiempo = TFin - TInicio;
					System.out.println("Tiempo de ejecución en milisegundos: " + tiempo);
					resultado += "\n Operación terminada";
				} catch (Exception e) {
					// e.printStackTrace();
					String resultado = generarMensajeError(e);
					panelDatos.actualizarInterfaz(resultado);
				}
			} else if (criterio.equals("Cuenta destino")) {
				BigDecimal cuenta_destino = BigDecimal.valueOf(Long.parseLong(
						JOptionPane.showInputDialog(this, "Ingrese el id de la cuenta de destino", "Cuenta de destino",
								JOptionPane.QUESTION_MESSAGE)));
				try {
					long TInicio, TFin, tiempo;
					TInicio = System.currentTimeMillis();
					List<VOOperacion> lista = bancandes.darVOOperacionNocuentadestino(fechainferior, fechasuperior, cuenta_destino);
					String resultado = "En listarOperacionesV2";
					resultado += "\n" + consultarOperaciones(lista);
					panelDatos.actualizarInterfaz(resultado);
					TFin = System.currentTimeMillis();
					tiempo = TFin - TInicio;
					System.out.println("Tiempo de ejecución en milisegundos: " + tiempo);
					resultado += "\n Operación terminada";
				} catch (Exception e) {
					// e.printStackTrace();
					String resultado = generarMensajeError(e);
					panelDatos.actualizarInterfaz(resultado);
				}
			} else if (criterio.equals("Usuario")) {
				String tipoIdUsuario = JOptionPane
					.showInputDialog(this, "Seleccione el tipo de id del usuario", "Tipo de identificacion",
							JOptionPane.PLAIN_MESSAGE, null,
							new Object[] { "CC", "CI", "TP", "CE"},"CC")
					.toString();
				BigDecimal idUsuario = BigDecimal.valueOf(Long.parseLong(
						JOptionPane.showInputDialog(this, "Ingrese el id del usuario", "Identificacion del usuario",
								JOptionPane.QUESTION_MESSAGE)));
				try {
					long TInicio, TFin, tiempo;
					TInicio = System.currentTimeMillis();
					List<VOOperacion> lista = bancandes.darVOOperacionNousuario(fechainferior, fechasuperior, tipoIdUsuario, idUsuario);
					String resultado = "En listarOperacionesV2";
					resultado += "\n" + consultarOperaciones(lista);
					panelDatos.actualizarInterfaz(resultado);
					TFin = System.currentTimeMillis();
					tiempo = TFin - TInicio;
					System.out.println("Tiempo de ejecución en milisegundos: " + tiempo);
					resultado += "\n Operación terminada";
				} catch (Exception e) {
					// e.printStackTrace();
					String resultado = generarMensajeError(e);
					panelDatos.actualizarInterfaz(resultado);
				}
			} else if (criterio.equals("Cajero")) {
				String tipoIdEmpleado = JOptionPane
					.showInputDialog(this, "Seleccione el tipo de id del cajero", "Tipo de identificacion",
							JOptionPane.PLAIN_MESSAGE, null,
							new Object[] { "CC", "CI", "TP", "CE"},"CC")
					.toString();
				BigDecimal idEmpleado  = BigDecimal.valueOf(Long.parseLong(
						JOptionPane.showInputDialog(this, "Ingrese el id del cajero", "Identificacion del cajero",
								JOptionPane.QUESTION_MESSAGE)));
				try {
					long TInicio, TFin, tiempo;
					TInicio = System.currentTimeMillis();
					List<VOOperacion> lista = bancandes.darVOOperacionNoempleado(fechainferior, fechasuperior, tipoIdEmpleado, idEmpleado);
					String resultado = "En listarOperacionesV2";
					resultado += "\n" + consultarOperaciones(lista);
					panelDatos.actualizarInterfaz(resultado);
					TFin = System.currentTimeMillis();
					tiempo = TFin - TInicio;
					System.out.println("Tiempo de ejecución en milisegundos: " + tiempo);
					resultado += "\n Operación terminada";
				} catch (Exception e) {
					// e.printStackTrace();
					String resultado = generarMensajeError(e);
					panelDatos.actualizarInterfaz(resultado);
				}
			}
		}
	}

	private String consultarOperaciones(List<VOOperacion> lista) {
		long TInicio, TFin;
		TInicio = System.currentTimeMillis();
		String resp = "Las Operaciones existentes son:\n";
		int i = 1;
		for (VOOperacion tb : lista) {
			resp += i++ + ". " + tb.toString() + "\n";
		}
		TFin = System.currentTimeMillis();
		String tiempo = String.valueOf(TFin - TInicio);
		resp += "Tiempo de ejecución en milisegundos: " + tiempo;
		return resp;
	}

	public void consutarConsignaciones() {
		{
			try {
				Long valorX = Long.parseLong(JOptionPane.showInputDialog(this, "Ingrese el valor de inicio", "Valor",
								JOptionPane.QUESTION_MESSAGE));
				String tipoProducto = JOptionPane.showInputDialog(this, "Seleccione el tipo de pruducto", "Tipo de producto",
										JOptionPane.PLAIN_MESSAGE, null,
										new Object[] { "Prestamo","Cuenta","Cuenta juridica"},"Prestamo")
								.toString();
				long TInicio, TFin, tiempo;
				TInicio = System.currentTimeMillis();
				List<VOOperacion> lista = bancandes.darVOConsignaciones(valorX,tipoProducto);
				String resultado = "En consultarConsignaciones";
				resultado += "\n" + consultarOperaciones(lista);
				panelDatos.actualizarInterfaz(resultado);
				TFin = System.currentTimeMillis();
				tiempo = TFin - TInicio;
				System.out.println("Tiempo de ejecución en milisegundos: " + tiempo);
				resultado += "\n Operación terminada";
			} catch (Exception e) {
				// e.printStackTrace();
				String resultado = generarMensajeError(e);
				panelDatos.actualizarInterfaz(resultado);
			}
		}

	}

	public void consutarPatencion() {
		{
			try {
				BigDecimal idPatencion1 = BigDecimal.valueOf(Long.parseLong(JOptionPane.showInputDialog(this, "Ingrese el id del puesto de atencion", "Id puesto de atencion 1",
								JOptionPane.QUESTION_MESSAGE)));
				BigDecimal idPatencion2 = BigDecimal.valueOf(Long.parseLong(JOptionPane.showInputDialog(this, "Ingrese el id del puesto de atencion", "Id puesto de atencion 2",
								JOptionPane.QUESTION_MESSAGE)));
				long TInicio, TFin, tiempo;
				TInicio = System.currentTimeMillis();
				List<VOUsuario> listaClientes = bancandes.darVOPatencionclientes(idPatencion1, idPatencion2);
				List<VOOperacion> listaOperaciones = bancandes.darVOPatencionoperaciones(idPatencion1, idPatencion2);
				TFin = System.currentTimeMillis();
				String resultado = "En consultarConsignaciones";
				tiempo = TFin - TInicio;
				System.out.println("Tiempo de ejecución en milisegundos: " + tiempo);
				resultado += "\n" + consultarUsuarios(listaClientes);
				resultado += "\n" + consultarOperaciones(listaOperaciones);
				panelDatos.actualizarInterfaz(resultado);
				resultado += "\n Operación terminada";
			} catch (Exception e) {
				// e.printStackTrace();
				String resultado = generarMensajeError(e);
				panelDatos.actualizarInterfaz(resultado);
			}
		}

	}

	private String consultarUsuarios(List<VOUsuario> lista) {
		long TInicio, TFin;
		TInicio = System.currentTimeMillis();
		String resp = "Los usuarios de estos puestos de atencion son:\n";
		int i = 1;
		for (VOUsuario tb : lista) {
			resp += i++ + ". " + tb.toString() + "\n";
		}
		TFin = System.currentTimeMillis();
		String tiempo = String.valueOf(TFin - TInicio);
		resp += "Tiempo de ejecución en milisegundos: " + tiempo;
		return resp;
	}

	public void listarPrestamos() {
		{
			try {
				List<VOPrestamo> lista = bancandes.darVOPrestamos(tipoUsuarioSeleccionado, tipoIdUsuarioActual,
						BigDecimal.valueOf(Long.valueOf(idUsuarioActual)));

				String resultado = "En listarPrestamos";
				resultado += "\n" + listarPrestamos(lista);
				panelDatos.actualizarInterfaz(resultado);
				resultado += "\n Operación terminada";
			} catch (Exception e) {
				// e.printStackTrace();
				String resultado = generarMensajeError(e);
				panelDatos.actualizarInterfaz(resultado);
			}
		}

	}

	private String listarPrestamos(List<VOPrestamo> lista) {
		String resp = "Los Prestamos existentes son:\n";
		int i = 1;
		for (VOPrestamo tb : lista) {
			resp += i++ + ". " + tb.toString() + "\n";
		}
		return resp;
	}

	public String cerrarPrestamoPorId() {
	}

	public String OperacionSobrePrestamoV2() {
	}

	public String adicionarOficina() {
	}

	public String adicionarPuestoAtencion() {
	}

	public String listarOperaciones() {
	}

	public String dar10Operaciones() {
	}

	/**
	 * Genera una cadena de caracteres con la descripción de la excepcion e,
	 * haciendo énfasis en las excepcionsde JDO
	 *
	 * @param e - La excepción recibida
	 * @return La descripción de la excepción, cuando es
	 *         javax.jdo.JDODataStoreException, "" de lo contrario
	 */
	private String darDetalleException(Exception e) {
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException")) {
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions()[0].getMessage();
		}
		return resp;
	}

	/**
	 * Genera una cadena para indicar al usuario que hubo un error en la aplicación
	 *
	 * @param e - La excepción generada
	 * @return La cadena con la información de la excepción y detalles adicionales
	 */
	private String generarMensajeError(Exception e) {
		String resultado = "************ Error en la ejecución\n";
		resultado += e.getLocalizedMessage() + ", " + darDetalleException(e);
		resultado += "\n\nRevise datanucleus.log y bancandes.log para más detalles";
		return resultado;
	}

	/**
	 * Limpia el contenido de un archivo dado su nombre
	 *
	 * @param nombreArchivo - El nombre del archivo que se quiere borrar
	 * @return true si se pudo limpiar
	 */
	private boolean limpiarArchivo(String nombreArchivo) {
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(new File(nombreArchivo)));
			bw.write("");
			bw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Abre el archivo dado como parámetro con la aplicación por defecto del sistema
	 *
	 * @param nombreArchivo - El nombre del archivo que se quiere mostrar
	 */
	private void mostrarArchivo(String nombreArchivo) {
		try {
			Desktop.getDesktop().open(new File(nombreArchivo));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "error", JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}
	}

	/*
	 * **************************************************************** Métodos de
	 * la Interacción
	 *****************************************************************/
	/**
	 * Método para la ejecución de los eventos que enlazan el menú con los métodos
	 * de negocio Invoca al método correspondiente según el evento recibido
	 *
	 * @param pEvento - El evento del usuario
	 */
	@Override
	public void actionPerformed(ActionEvent pEvento) {
		String evento = pEvento.getActionCommand();

		if (evento.equals("Entrar")) {
			boolean entrar = InterfazBancandesApp.verificarCredenciales(tipoUsuarioSeleccionado, codigo.getText(),
					String.valueOf(contrasenia.getPassword()));
			if (entrar) {
				if (tipoUsuarioSeleccionado == "Cliente") {
					String[] datos = obtenerUsuario(codigo.getText(), String.valueOf(contrasenia.getPassword()));
					// System.out.println(datos[0]);
					if (datos.length == 2 && datos[0] != null) {
						idUsuarioActual = datos[1];
						tipoIdUsuarioActual = datos[0];

						registro.setVisible(false);
						// Carga la configuración de la interfaz desde un archivo JSON
						guiConfig = openConfig("Interfaz", CONFIG_INTERFAZ_CLIENTE);
						configurarFrame();

						this.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(registro, "Credenciales no validas", "error",
								JOptionPane.WARNING_MESSAGE);
					}
				} else if (tipoUsuarioSeleccionado != "Seleccione su tipo de usuario") {
					try {
						String[] datos = obtenerEmpleado(tipoUsuarioSeleccionado, codigo.getText(),
								String.valueOf(contrasenia.getPassword()));
						if (datos.length == 2 && datos[0] != null) {
							idUsuarioActual = datos[1];
							tipoIdUsuarioActual = datos[0];

							registro.setVisible(false);
							// "Cajero", "Gerente Oficina", "Gerente General", "Administrador"
							if (tipoUsuarioSeleccionado.equals("Cajero")) {
								guiConfig = openConfig("Interfaz", CONFIG_INTERFAZ_CAJERO);
								configurarFrame();
							} else if (tipoUsuarioSeleccionado.equals("Gerente general")) {
								guiConfig = openConfig("Interfaz", CONFIG_INTERFAZ_GER_GENERAL);
								configurarFrame();
							} else if (tipoUsuarioSeleccionado.equals("Gerente Oficina")) {
								guiConfig = openConfig("Interfaz", CONFIG_INTERFAZ_GER_OFICINA);
								configurarFrame();
							} else {
								guiConfig = openConfig("Interfaz", CONFIG_INTERFAZ_ADMIN);
								configurarFrame();
							}
							this.setVisible(true);
						} else {
							JOptionPane.showMessageDialog(registro, "Credenciales no validas", "error",
									JOptionPane.WARNING_MESSAGE);
						}

					} catch (Exception e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(registro, "Tipo de empleado no valido", "error",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		} else {
			try {
				Method req = InterfazBancandesApp.class.getMethod(evento);
				req.invoke(this);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == menuUsuarios) {
			String seleccionado = (String) menuUsuarios.getSelectedItem();
			tipoUsuarioSeleccionado = seleccionado;
		}
	}

	/*
	 * **************************************************************** Programa
	 * principal
	 *****************************************************************/
	/**
	 * Este método ejecuta la aplicación, creando una nueva interfaz
	 *
	 * @param args Arreglo de argumentos que se recibe por línea de comandos
	 */
	public static void main(String[] args) {
		try {

			// Unifica la interfaz para Mac y para Windows.
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			InterfazBancandesApp interfaz = new InterfazBancandesApp();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
