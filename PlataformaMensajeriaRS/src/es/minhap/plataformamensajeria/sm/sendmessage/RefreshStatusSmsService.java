package es.minhap.plataformamensajeria.sm.sendmessage;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

import javax.xml.namespace.QName;

import org.apache.log4j.PropertyConfigurator;

import es.minhap.plataformamensajeria.sm.jdbc.JDBCAccess;
import es.minhap.plataformamensajeria.sm.modelo.Historico;
import es.minhap.plataformamensajeria.sm.modelo.ParametrosProveedor;
import es.minhap.plataformamensajeria.sm.modelo.Proveedor;
import es.minhap.plataformamensajeria.sm.modelo.SMSData;
import es.minhap.plataformamensajeria.sm.modelo.Servicio;

public class RefreshStatusSmsService {
	private static final String VERSION_REFRESH_STATUS_SMS = "1.0";
	private Integer maxSecondsRetryBBDD = 60;
	private Integer delayService = 120;
	public Connection conn = null;
	private Boolean stopService;
	private String connectionString;
	private String bbddUser;
	private String bbddPassword;
	private String urlSMS;
	private static RefreshStatusSmsService refreshStatusSmsService = new RefreshStatusSmsService();
	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(RefreshStatusSmsService.class);
	private static JDBCAccess jdbc = new JDBCAccess();
	private static String ruta = "conf/refreshStatus/";
	private Integer idNodo;
	private Integer numTotalNodos;
	private String ejecutarTodosProveedores;
	private Integer difTiempo;
	private Integer maxMensajesBloque;

	private String MISIM_URL_ENVIARMENSAJES_WS = "";
	private String MISIM_SERVICE_NAME = "";
	private String MISIM_USUARIO = "";
	private String MISIM_PASSWORD = "";
	private String MISIM_WSDL = "";
	
	private static final Integer TIME_SLEEP_H_PROCESS = 2000;

	public static void main(String[] args) {

		// Se le pasa como parámetro la ruta relativa
		if (null != args && args.length > 0) {
			ruta = args[0];
		}

		try {
			
			PropertyConfigurator.configure(ruta + "log4j.properties");
			

			if (log.isInfoEnabled())
				log.info("Start Servicio RefreshStatusSMS (Actualizacion estado SMS) Version Actual: " + VERSION_REFRESH_STATUS_SMS);

			// Inicializamos las variables
			refreshStatusSmsService.initializeVariables();

			// Procesamos la cola para actualizar los SMS
			refreshStatusSmsService.refrescarEstadoSMS();

			if (log.isInfoEnabled())
				log.info("Stop RefreshStatusSmsService-Main");

		} catch (Exception e) {
			if (log.isInfoEnabled())
				log.info("Error and Stoping RefreshStatusSmsService-Main : " + e.getMessage());
		} finally{
			refreshStatusSmsService.closeAll();
		}
	}
	public void closeAll(){
		
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn = null;
		}
	}

	/**
	 * INICIALIZA LAS VARIABLES DE CONEXION DE LA BBDD Y INTENTA CONECTARSE
	 */
	public void initializeVariables() {
		try {
			stopService = false;

			Properties configFile = new Properties();
			File f = new File(ruta + "configuration.properties");
			log.info("Ruta para propeties: "+f.getAbsolutePath());
			
			FileInputStream in = new FileInputStream(f);

			configFile.load(in);
			in.close();
			
			connectionString = configFile.getProperty("DatabaseConectionString");
			bbddUser = configFile.getProperty("DatabaseUser");
			bbddPassword = configFile.getProperty("DatabasePassword");
			urlSMS = configFile.getProperty("UrlSMS");
			ejecutarTodosProveedores = configFile.getProperty("EjecutarTodosProveedores");

			MISIM_URL_ENVIARMENSAJES_WS = configFile.getProperty("MISIM_URL_ENVIARMENSAJES_WS");
			MISIM_SERVICE_NAME = configFile.getProperty("MISIM_SERVICE_NAME");
			MISIM_USUARIO = configFile.getProperty("MISIM_USUARIO");
			MISIM_PASSWORD = configFile.getProperty("MISIM_PASSWORD");
			MISIM_WSDL = configFile.getProperty("MISIM_WSDL");
			
			File fu = new File(ruta+MISIM_WSDL);
			URL urlmisim = new URL("file:///"+fu.getAbsolutePath());
			log.info("Ruta para WSDL: "+fu.getAbsolutePath());

			es.redsara.misim.misim_bus_webapp.enviarmensaje.EnviarMensajeService.wsdlURL = urlmisim;
			es.redsara.misim.misim_bus_webapp.enviarmensaje.EnviarMensajeService.serviceName = new QName(MISIM_URL_ENVIARMENSAJES_WS, MISIM_SERVICE_NAME);

			es.redsara.misim.misim_bus_webapp.consultarestado.ConsultarEstadoService.wsdlURL = urlmisim;
			es.redsara.misim.misim_bus_webapp.consultarestado.ConsultarEstadoService.serviceName = new QName(MISIM_URL_ENVIARMENSAJES_WS, MISIM_SERVICE_NAME);

			es.redsara.misim.misim_bus_webapp.recpecionsms.RecibirSmsService.wsdlURL = urlmisim;
			es.redsara.misim.misim_bus_webapp.recpecionsms.RecibirSmsService.serviceName = new QName(MISIM_URL_ENVIARMENSAJES_WS, MISIM_SERVICE_NAME);

			try {
				delayService = Integer.parseInt(configFile.getProperty("TimeDelayService"));
			} catch (Exception e) {
				if (log.isInfoEnabled()) {
					log.info("No se puede parsear la variable 'TimeDelayService': " + e.getMessage() + " (initializeVariables)");
				}
			}
			try {
				maxSecondsRetryBBDD = Integer.parseInt(configFile.getProperty("DatabaseMaxRetry"));
			} catch (Exception e) {
				if (log.isInfoEnabled()) {
					log.info("No se puede parsear la variable 'DatabaseMaxRetry': " + e.getMessage() + " (initializeVariables)");
				}
			}
			try {
				idNodo = Integer.parseInt(configFile.getProperty("IdNodo"));
			} catch (Exception e) {
				if (log.isInfoEnabled()) {
					log.info("No se puede parsear la variable 'IdNodo': " + e.getMessage() + " (initializeVariables)");
				}
			}
			try {
				numTotalNodos = Integer.parseInt(configFile.getProperty("NumTotalNodos"));
			} catch (Exception e) {
				if (log.isInfoEnabled()) {
					log.info("No se puede parsear la variable 'NumTotalNodos': " + e.getMessage() + " (initializeVariables)");
				}
			}
			try {
				difTiempo = Integer.parseInt(configFile.getProperty("DifTiempo"));
			} catch (Exception e) {
				if (log.isInfoEnabled()) {
					log.info("No se puede parsear la variable 'DifTiempo': " + e.getMessage() + " (initializeVariables)");
				}
			}
			try {
				maxMensajesBloque = Integer.parseInt(configFile.getProperty("MaxMensajesBloque"));
			} catch (Exception e) {
				if (log.isInfoEnabled()) {
					log.info("No se puede parsear la variable 'MaxMensajesBloque': " + e.getMessage() + " (initializeVariables)");
				}
			}
			// Abrir Conexion
			openConnection();

		} catch (Exception e) {
			if (log.isInfoEnabled()) {
				log.info("No se puede leer el fichero de configuracion: " + e.getMessage() + " (InitializeVariables)");
			}
		}
	}

	/**
	 * PROCESA LA COLA PARA ACTUALIZAR EL ESTADO DE LOS SMS
	 */
	private void refrescarEstadoSMS() throws InterruptedException {

		if (log.isDebugEnabled()) {
			log.debug("------------------");
			log.debug("RefrescarEstadoSMS");
			log.debug("------------------");
		}

		if (log.isInfoEnabled()) {
			log.info("--------------------------");
			log.info("IdNodo: " + this.idNodo);
			log.info("Numero total de nodos: " + this.numTotalNodos);
			log.info("--------------------------");
		}

		int numNodosRecuperados = 0;

		while (!stopService) {
			try {

				log.info("--------------------------");
				log.info("Inicio del refresco de estado");
				reOpenConnection();
				// EVALUAMOS SI HAY QUE DETENER EL SERVICIO
				evaluateStatusServer();
				// SI EL SERVICIO NO SE HA DETENIDO
				if (!this.stopService) {
					// ASIGNAMOS A LOS MENSAJES CORRESPONDIENTES EL VALOR DEL NODO
					numNodosRecuperados = asignarNodoSMS();
					// SE REALIZA EL TRACKING
					refreshStatusSMS();
					// SE PRODUCE UNA ESPERA PARA VOLVER A REALIZAR LAS OPERACIONES SI NO RECUPERO NINGUN REGISTRO PARA HACER TRACKING
					if (numNodosRecuperados == 0) {
						Thread.sleep(delayService * 1000);
					}
					// SE PONE EL CAMPO NODO A NULL DE AQUELLOS MENSAJES ASIGNADOS PREVIAMENTE AL NODO
					// desasignarNodoSMS();

				}
				log.info("Fin del refresco de estado");
				log.info("--------------------------");

			} catch (Exception e) {
				e.printStackTrace();
				if (log.isInfoEnabled())
					log.info("Se ha producido un error en el metodo refrescarEstadoSMS: " + e.getMessage());
				// stopService = true;
			}
		}

	}

	// METODO QUE ABRE LA CONEXION CON LA BBDD
	private void openConnection() throws InterruptedException {
		boolean existeConexion = false;
		while (!existeConexion) {
			try {
				if (null == conn)
					conn = DriverManager.getConnection(connectionString, bbddUser, bbddPassword);
				log.debug("Se abre conexion.");
				existeConexion = true;
			} catch (Exception ex) {
				if (log.isInfoEnabled())
					log.info("No se ha podido abrir la conexion: " + ex.getMessage());
				Thread.sleep(maxSecondsRetryBBDD * 1000);
			}
		}
	}

	// METODO QUE REABRE LA CONEXION CON LA BBDD
	private void reOpenConnection() throws InterruptedException {
		boolean conexionReabierta = false;
		while (!conexionReabierta) {
			if (conn != null) {
				try {
					conn.close();
					log.debug("Se cierra conexion.");
				} catch (Exception ex) {
					if (log.isInfoEnabled())
						log.info("No se ha podido cerrar la conexion: " + ex.getMessage());
				} finally {
					conn = null;
				}
			}
			openConnection();
			conexionReabierta = true;
		}
	}

	// METODO PARA DESASIGNAR DEL NODO LOS MENSAJES SMS PENDIENTES
	// private void desasignarNodoSMS() throws Exception{
	// int numRows = 0;
	// try
	// {
	// numRows = jdbc.deleteNodoFromSMS(this.conn, this.idNodo);
	// if (log.isInfoEnabled())
	// log.info("Numero SMS desasignados: " + numRows);
	// }
	// catch (Exception ex)
	// {
	// if (log.isDebugEnabled())
	// log.debug("Error desasignarNodoSMS: " + ex.getMessage());
	// reOpenConnection();
	// }
	//
	// }

	// METODO PARA ASIGNAR AL NODO EN EL QUE SE EJECUTA EL SERVICE
	// UN NUMERO EQUITATIVO DE MENSAJES SMS QUE ESTEN PENDIENTES
	private int asignarNodoSMS() throws Exception {
		int numRows = 0;
		try {
			numRows = jdbc.assignNodoFromSMS(this.conn, this.idNodo, this.numTotalNodos, this.difTiempo, this.maxMensajesBloque);
			if (log.isInfoEnabled())
				log.info("Numero SMS asignados: " + numRows);
		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log.debug("Error asignarNodoSMS: " + ex.getMessage());
			reOpenConnection();
		}

		return numRows;

	}

	// METODO PARA ACTUALIZAR LOS MENSAJES SMS QUE ESTEN PENDIENTES
	private void refreshStatusSMS() throws Exception {
		ResultSet listaSMS = null;
		try {
			listaSMS = jdbc.RefreshStatusSMS(this.conn, this.idNodo, this.difTiempo);
			Map<Integer,Servicio> mapServicios = null;
			int numRows = 0;
			ParametrosProveedor ps;
			Proveedor p;
			boolean sendOK;
			
			String uim;
			int mensajeId;
			Integer proveedorID = 0;
			

			while (listaSMS.next()) {
				mensajeId = listaSMS.getInt("MensajeID");
				ArrayList<SMSData> ListaSMSData = new ArrayList<SMSData>();
				uim = listaSMS.getString("UIM");
				// TODO: Hay que hacer lo mismo que en el sendmail
				ListaSMSData = getSMSData(mensajeId);
				// smsData = getSMSData(mensajeId);
				for (SMSData smsData : ListaSMSData) {
					int numServidores = smsData.Servers.size();
					if (numServidores < 1) {
						continue;
					}

					registerSMSDetailsDebug(smsData);

					if (log.isInfoEnabled())
						log.info("ID mensaje tracking: " + mensajeId);
					mapServicios = this.jdbc.findServicioByMessageId(mensajeId,
							conn);
					if ("S".equals(ejecutarTodosProveedores)) {

						int indice = 0;

						sendOK = false;

						if (log.isInfoEnabled())
							log.info("Numero de proveedores: " + numServidores);

						while ((indice++ < numServidores) && !sendOK) {
							proveedorID = smsData.Servers.get(indice)
									.getProveedorId();
							registerSMSParametersDebug(smsData, indice);
							if (mapServicios.containsKey(proveedorID)) {
								Servicio s = mapServicios.get(proveedorID);
								ps = smsData.Servers.get(indice);
								sendOK = this.consultarEstado(ps, mensajeId,
										smsData, s, uim,ps);
							}
						}

					} else {
						// busca el primer proveedor que esté en la tabla
						// servidor/proveedor del servicio
						if (mapServicios.size() > 0) {
							for (ParametrosProveedor pp : smsData.Servers) {
								if (mapServicios.containsKey(pp
										.getProveedorId())) {
									proveedorID = pp.getProveedorId();
									break;
								}
							}
						} else {// si no tomamos el primero recuperado
							proveedorID = smsData.Servers.get(0)
									.getProveedorId();
						}
						Servicio s = mapServicios.get(proveedorID);
						registerSMSParametersDebug(smsData, 0);
						ps = smsData.Servers.get(0);
						sendOK = this.consultarEstado(ps, mensajeId, smsData,
								s, uim,ps);

					}
				}
			}
		} catch (SQLException ex) {
			if (log.isDebugEnabled())
				log.debug("Error refreshStatusSMS: " + ex.getMessage());
			reOpenConnection();
		} finally {
			if(listaSMS!=null) {
				listaSMS.close();
			}
		}

	}

	private boolean consultarEstado(ParametrosProveedor ps, int mensajeId, SMSData smsData, Servicio s, String uim, ParametrosProveedor pp) {

		try {

			boolean resultado = false;

			Proveedor p = this.jdbc.findProveedor(ps.getProveedorId(), conn);

			// llamamos al modulo MISIM y le pasamos el mensaje a enviar
			es.redsara.misim.misim_bus_webapp.consultarestado.respuesta.Respuesta r1;
			String usuario = s.getProveedorUsuarioSMS();
			String password = s.getProveedorPassSMS();
			String producto = "ESTADO_SMS";
			String proveedor = p.getNombre();
//TODO: ver el sms_id cual metemos si es multidestinatario!!!
			String SMS_ID = "" + mensajeId;
			String SMS_HEADER = s.getHeaderSMS();
			String SMS_UIM = uim;

			r1 = es.redsara.misim.misim_bus_webapp.consultarestado.ConsultarEstadoService.consultarEstado(MISIM_USUARIO, MISIM_PASSWORD, producto, proveedor, SMS_ID, SMS_ID, usuario, password, SMS_UIM, SMS_HEADER);
			log.info("Recibida respuesta: " + r1);

			// SI EL SMS HA SIDO ENVIADO CORRECTAMENTE
			if (!"OK".equals(r1.getStatus().getStatusCode())) {
				resultado = false;
			}

			String estado = "";
			try {
				estado = r1.getStatus().getStatusText(); // ejemplo respuesta error: 'ERROR|-10|UIM not found' ejemplo respuesta ok:				
				StringTokenizer st = new StringTokenizer(estado, "|");
				String exito = st.nextToken();
				if ("OK".equals(exito)) {
					BigDecimal cod = new BigDecimal(st.nextToken());
					Historico hist = new Historico(new Date(), new BigDecimal(mensajeId), cod);
					if (smsData.esMultidestinatario){
						sleepHistoryProcess();
						this.jdbc.setMessageStatusMult(mensajeId, 1, pp.getProveedorId(), "", smsData, conn, true);
						sleepHistoryProcess();
					}else{
						sleepHistoryProcess();
						this.jdbc.SetMessageStatus(mensajeId, 1, pp.getProveedorId(), "", conn, true);
						sleepHistoryProcess();
					}
					return true;
				}
			} catch (Exception ex) {
				log.error("Error refreshStatusSMS: Recibido estado SMS no reconocido '" + estado + "'");				
			}			
			
			//  En caso de error metemos el codigo de subestado -20
			BigDecimal cod = new BigDecimal(-20);  // cod = -20 = ERROR GENERAL EN LA PLATAFORMA = ESTADO 3 =  INCIDENCIA			
			Historico hist = new Historico(new Date(), new BigDecimal(mensajeId), cod);
			
			if (smsData.esMultidestinatario){
				sleepHistoryProcess();
				this.jdbc.setMessageStatusMult(mensajeId, 6, pp.getProveedorId(), "ERROR GENERAL EN LA PLATAFORMA", smsData, conn, true);
				sleepHistoryProcess();
			}else{
				sleepHistoryProcess();
				this.jdbc.SetMessageStatus(mensajeId, 6, pp.getProveedorId(), "ERROR GENERAL EN LA PLATAFORMA", conn, true);
				sleepHistoryProcess();
			}
			
		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log.debug("Error refreshStatusSMS: " + ex.getMessage());
			try {
				reOpenConnection();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return false;

	}

	private void evaluateStatusServer() throws Exception {

		try {
			this.stopService = jdbc.evaluateStatusServer(this.conn, this.idNodo);
		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log.debug("Error evaluateStatusServer: " + ex.getMessage());
			reOpenConnection();
		}
	}

	private ArrayList<SMSData> getSMSData(int mailId) throws InterruptedException {
	
		while (true) {
			try {
				return jdbc.GetSMSData(mailId, this.conn);
			} catch (SQLException ex) {
				if (log.isDebugEnabled())
					log.debug("Error getting SMS data: " + ex.getMessage());
				reOpenConnection();
			}
		}
		
	}

	private void registerSMSDetailsDebug(SMSData smsData) {
		if (log.isDebugEnabled()) {
			log.debug("-----------------------------------");
			log.debug("DATOS DE SMS (refreshStatusSMS)");
			log.debug("-----------------------------------");
			log.debug("Telefono: " + smsData.Telefono + " (refreshStatusSMS)");
		}
	}

	private void registerSMSParametersDebug(SMSData smsData, int indice) {
		ParametrosProveedor ps = smsData.Servers.get(indice);
		String url = "";
		String id = "";
		String telefono = "";
		String texto = "";
		String uim = "";
		if (ps.getUrl() != null) {
			url = ps.getUrl();
		}
		if (ps.getId() != null) {
			id = ps.getId();
		}
		if (ps.getTelefono() != null) {
			telefono = ps.getTelefono();
		}
		if (ps.getTexto() != null) {
			texto = ps.getTexto();
		}
		if (ps.getUIM() != null) {
			uim = ps.getUIM();
		}

		if (log.isDebugEnabled()) {
			log.debug("----------------------------");
			log.debug("DATOS DEL PROVEEDOR (postSMS)");
			log.debug("----------------------------");
			log.debug("Url: " + url + " (postSMS)");
			if (id != "")
				log.debug("Id:" + id + " (postSMS)");
			if (telefono != "")
				log.debug("Telefono: " + telefono + " (postSMS)");
			if (texto != "")
				log.debug("Texto: " + texto + " (postSMS)");
			if (uim != "")
				log.debug("UIM: " + uim + " (postSMS)");
			log.debug("-----------------------------------");
		}

	}
	
	private void sleepHistoryProcess() {
		try {
		    TimeUnit.MILLISECONDS.sleep(TIME_SLEEP_H_PROCESS);
		} catch (InterruptedException e) {
			log.error("Se ha producido un error al pausar la ejecucion del trigger de historicos" , e);
		}
	}

}