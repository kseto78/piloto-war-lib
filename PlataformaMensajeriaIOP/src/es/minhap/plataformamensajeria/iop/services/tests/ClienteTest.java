/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package es.minhap.plataformamensajeria.iop.services.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.Test;

import es.minhap.plataformamensajeria.iop.beans.ConsultaEstadoBean;
import es.minhap.plataformamensajeria.iop.beans.ConsultaEstadoXMLBean;
import es.minhap.plataformamensajeria.iop.beans.ConsultaHistoricoBean;
import es.minhap.plataformamensajeria.iop.beans.ConsultaHistoricoXMLBean;
import es.minhap.plataformamensajeria.iop.beans.DestinatarioXMLBean;
import es.minhap.plataformamensajeria.iop.beans.EnvioAEATXMLBean;
import es.minhap.plataformamensajeria.iop.beans.EnvioEmailXMLBean;
import es.minhap.plataformamensajeria.iop.beans.EnvioGISSXMLBean;
import es.minhap.plataformamensajeria.iop.beans.EnvioPushXMLBean;
import es.minhap.plataformamensajeria.iop.beans.EnvioSMSXMLBean;
import es.minhap.plataformamensajeria.iop.beans.MensajeSMSXMLBean;
import es.minhap.plataformamensajeria.iop.beans.MensajesXMLBean;
import es.minhap.plataformamensajeria.iop.beans.OperacionesLotesMensajesXMLBean;
import es.minhap.plataformamensajeria.iop.beans.OperacionesMensajesXMLBean;
import es.minhap.plataformamensajeria.iop.beans.PeticionClaveAuthResponse;
import es.minhap.plataformamensajeria.iop.beans.RecepcionEstadoSMSXMLBean;
import es.minhap.plataformamensajeria.iop.beans.RecibirSMSRequest;
import es.minhap.plataformamensajeria.iop.beans.UsuariosXMLBean;
import es.minhap.plataformamensajeria.iop.beans.lotes.MensajePeticionLotesPushXMLBean;
import es.minhap.plataformamensajeria.iop.beans.lotes.PeticionXMLBean;
import es.minhap.plataformamensajeria.iop.respone.RespuestaConsultaEstado;
import es.minhap.plataformamensajeria.iop.respone.RespuestaConsultaHistorial;
import es.minhap.plataformamensajeria.iop.respone.RespuestaEnvioXMLBean;
import es.minhap.plataformamensajeria.iop.respone.RespuestaOperacion;
import es.minhap.plataformamensajeria.iop.services.envio.IEnvioMensajesService;
import es.minhap.plataformamensajeria.iop.services.envioLotes.IEnvioLotesMensajesService;
import es.minhap.plataformamensajeria.iop.services.envioPremium.IEnvioPremiumGISSService;
import es.minhap.plataformamensajeria.iop.services.envioPremium.IEnvioPremiumService;
import es.minhap.plataformamensajeria.iop.services.exceptions.PlataformaBusinessException;
import es.minhap.plataformamensajeria.iop.services.operaciones.IOperacionesMensajesService;
import es.minhap.plataformamensajeria.iop.services.procesarSAMLResponse.IGestionSAMLResponseService;
import es.minhap.plataformamensajeria.iop.services.recepcion.IRecepcionMensajesService;
import es.minhap.plataformamensajeria.iop.services.recepcionEstadoSMS.IRecepcionEstadoSMSService;
import es.minhap.plataformamensajeria.iop.services.recepcionEstadoSMS.RespuestaEstadoSMSXMLBean;
import es.minhap.plataformamensajeria.iop.services.seguimiento.ISeguimientoMensajesService;
import es.minhap.plataformamensajeria.iop.services.usuariosplataformas.IRegistroUsuarioPushService;
import es.minhap.plataformamensajeria.iop.services.usuariosplataformas.RegistroUsuarioPushResponse;
import es.minhap.plataformamensajeria.iop.util.FactoryServiceSim;

/**
 * En este test crearemos un cliente del Web Service utilizando Apache CXF. El
 * cliente se crea dinámicamente a través de Spring. En el archivo de
 * configuración de Spring applicationContext.xml de este test se declara el
 * cliente, el cual cumplirá con la interfaz del Web Service.
 */

public class ClienteTest {

	protected static final String R_CONST_1 = "2909";
	protected static final String R_CONST_2 = "testReenviarLote";
	protected static final String R_CONST_3 = "Ejecutado correctamente. Se ha anulado el lote: ";
	protected static final String R_CONST_4 = " || ";
	protected static final String R_CONST_5 = "612485";
	protected static final String R_CONST_6 = "Ejecutado correctamente. Se ha reenviado el mensaje: ";
	protected static final String R_CONST_7 = "pruebasSIMdes";
	protected static final String R_CONST_8 = "Ejecutado correctamente. Se ha anulado el mensaje: ";
	protected static final String R_CONST_9 = "imprimido el primero";
	protected static final String R_CONST_10 = "Error reenviando lote ";
	protected static final String R_CONST_11 = "Error reenviando mensaje: ";
	protected static final String R_CONST_12 = "609691700";
	protected static final String R_CONST_13 = "anularLote.xml";
	protected static final String R_CONST_14 = "Ejecutado correctamente. Se ha reenviado el lote: ";
	protected static final String R_CONST_15 = "2";
	protected static final String R_CONST_16 = "[TEST] Error consultado estado";
	protected static final String R_CONST_17 = "?";
	protected static final String R_CONST_18 = " | ";
	protected static final String R_CONST_19 = "Error anulando lote ";
	protected static final String R_CONST_20 = "lalala";
	protected static final String R_CONST_21 = "ESPAÑOL";
	protected static final String R_CONST_22 = "Errro anulando mensaje: ";
	protected static final String R_CONST_23 = "Total resultados: ";
	protected static final String R_CONST_24 = "26/01/2012";
	protected static final String R_CONST_25 = "ximoim@gmail.com";
	private IEnvioMensajesService instance = FactoryServiceSim.getInstance()
			.getInstanceMensajes();
	private ISeguimientoMensajesService instanceSeguimiento = FactoryServiceSim
			.getInstance().getInstanceSeguimiento();
	private IOperacionesMensajesService instanceOperaciones = FactoryServiceSim
			.getInstance().getInstanceOperaciones();
	private IRegistroUsuarioPushService instanceUsuarios = FactoryServiceSim
			.getInstance().getInstanceUsuario();
	private IRecepcionMensajesService instanceRecepcionMensajes = FactoryServiceSim
			.getInstance().getInstanceRecepcionMensajes();
	
	private IRecepcionEstadoSMSService instanceRecepcionEstado = FactoryServiceSim
			.getInstance().getInstanceRecepcionEstado();
	
	private IEnvioLotesMensajesService instanceLotes = FactoryServiceSim.getInstance()
			.getInstanceLotes();
	private IEnvioPremiumService instancePremium = FactoryServiceSim.getInstance()
			.getInstancePremium();
	private IEnvioPremiumGISSService instanceGISS = FactoryServiceSim.getInstance()
			.getInstanceGISS();
	private IGestionSAMLResponseService instanceSAML = FactoryServiceSim.getInstance()
			.getInstanceSAML();

	private static  Integer LOTE_PRUEBAS = 28546;// 84//86
	private static  Integer MENSAJE_PRUEBAS = 29082;
	private static  String USUARIO = "portalNOTIFICA@";
	private static  String PASS = "portalNOTIFICA@";
	private static  String NOMBRE_LOTE_SMS = "LOTE_SMS_TEST_20160129";
	private static  String NOMBRE_LOTE_MAIL = "LOTE_MAIL_TEST_20160129";
	private static  String NOMBRE_LOTE_PUSH = "LOTE_PUSHL_TEST_20160129";
	private static  String SERVICIO_MAIL_ID = "483";
	private static  String SERVICIO_SMS_ID = "522";
	private static  String SERVICIO_PUSH_ID = "1042";
	private static  Integer SERVICIO_ID = 483;
	private static  String SMSTEXT = "Texto del SMS";
	
	private static String USUARIO_NUEVO_NOMBRE = "pepe martinez";
	private static String USUARIO_NUEVO_SERVIDIOID = "1042";	
	private static String USUARIO_NUEVO_PLATAFORMAID = "402";	
	private static String USUARIO_NUEVO_TOKEN = "pepetoken modificado";	
	private static String USUARIO_NUEVO_DISPOSITIVO = "ANDROID";
	
	static Logger LOGGER = Logger.getLogger(ClienteTest.class);
	
	

	public static byte[] getBytesFromFile(File file) throws IOException {
		long length = file.length();

		byte[] bytes = new byte[(int) length];
		int offset = 0;
		int numRead = 0;

		InputStream is = new FileInputStream(file);
		try {
			while (offset < bytes.length
					&& (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
				offset += numRead;
			}
		} finally {
			is.close();
		}

		if (offset < bytes.length) {
			throw new IOException("" + file.getName());
		}
		return bytes;
	}

	@Test
	public void testReenviarLote() throws PlataformaBusinessException {
		System.out.println(R_CONST_2);
		String resultado = instanceOperaciones.reenviarLote(LOTE_PRUEBAS,
				USUARIO, PASS);
		RespuestaOperacion respuestaOperacion = new RespuestaOperacion();
		respuestaOperacion.parseResponse(resultado);
		if (respuestaOperacion.getErrores().isEmpty()) {
			System.out
					.println(R_CONST_14
							+ LOTE_PRUEBAS);
		} else {
			System.out.println(R_CONST_10 + LOTE_PRUEBAS + R_CONST_18
					+ respuestaOperacion.getErrores().get(0));
		}

	}

	@Test
	public void testAnularLote() throws PlataformaBusinessException {
		System.out.println(R_CONST_2);
		String resultado = instanceOperaciones.anularLote(LOTE_PRUEBAS,
				USUARIO, PASS);
		RespuestaOperacion respuestaOperacion = new RespuestaOperacion();
		respuestaOperacion.parseResponse(resultado);
		if (respuestaOperacion.getErrores().isEmpty()) {
			System.out
					.println(R_CONST_3
							+ LOTE_PRUEBAS);
		} else {
			System.out.println(R_CONST_19 + LOTE_PRUEBAS + R_CONST_18
					+ respuestaOperacion.getErrores().get(0));
		}
	}

	@Test
	public void testReenviarMensaje() throws PlataformaBusinessException {
		System.out.println("testReenviarMensaje");
		String resultado = instanceOperaciones.reenviarMensaje(MENSAJE_PRUEBAS,
				USUARIO, PASS);
		RespuestaOperacion respuestaOperacion = new RespuestaOperacion();
		respuestaOperacion.parseResponse(resultado);
		if (respuestaOperacion.getErrores().isEmpty()) {
			System.out
					.println(R_CONST_6
							+ MENSAJE_PRUEBAS);
		} else {
			System.out.println(R_CONST_11 + MENSAJE_PRUEBAS
					+ R_CONST_18 + respuestaOperacion.getErrores().get(0));
		}

	}

	@Test
	public void testAnularMensaje() throws PlataformaBusinessException {
		System.out.println("testAnularMensaje");
		String resultado = instanceOperaciones.anularMensaje(MENSAJE_PRUEBAS,
				USUARIO, PASS);
		RespuestaOperacion respuestaOperacion = new RespuestaOperacion();
		respuestaOperacion.parseResponse(resultado);
		if (respuestaOperacion.getErrores().isEmpty()) {
			System.out
					.println(R_CONST_8
							+ MENSAJE_PRUEBAS);
		} else {
			System.out.println(R_CONST_22 + MENSAJE_PRUEBAS
					+ R_CONST_18 + respuestaOperacion.getErrores().get(0));
		}

	}

	@Test
	public void testConsultarEstado() throws PlataformaBusinessException {
		System.out.println("testConsultarEstado");

		String resultado = instanceSeguimiento.consultarEstado(SERVICIO_ID, 0, 0, 0, 0,
				null, 0, R_CONST_24, R_CONST_24, USUARIO, PASS);
		System.out.println(resultado);
		RespuestaConsultaEstado estado = new RespuestaConsultaEstado();
		try {
			estado.parseResponse(resultado);
		} catch (PlataformaBusinessException e) {
			LOGGER.error(e.getMessage(), e);
			throw new PlataformaBusinessException(
					R_CONST_16);
		}
		ArrayList<ConsultaEstadoBean> resultados = estado.getResultados();
		int i = 0;
		for (ConsultaEstadoBean consultaEstadoBean : resultados) {
			System.out.println(consultaEstadoBean.getAplicacion() + R_CONST_4
					+ consultaEstadoBean.getFecha() + R_CONST_4
					+ consultaEstadoBean.getCanal() + R_CONST_4
					+ consultaEstadoBean.getEstado() + R_CONST_4
					+ consultaEstadoBean.getIdMensaje());
			i++;
		}
		System.out.println(R_CONST_23 + i);

	}

	@Test
	public void testConsultarHistorico() throws PlataformaBusinessException {
		System.out.println("testConsultarHistorico");

		String resultado = instanceSeguimiento.consultarHistorial(MENSAJE_PRUEBAS, null,
				USUARIO, PASS);
		// System.out.println(resultado);
		RespuestaConsultaHistorial respuesta = new RespuestaConsultaHistorial();
		respuesta.parseResponse(resultado);
		ArrayList<ConsultaHistoricoBean> listado = respuesta.getResultados();
		int i = 0;
		for (ConsultaHistoricoBean consultaHistoricoBean : listado) {
			System.out.println(consultaHistoricoBean.getIdMensaje() + R_CONST_4
					+ consultaHistoricoBean.getIdExterno() + R_CONST_4
					+ consultaHistoricoBean.getFecha());
			i++;
		}
		System.out.println(R_CONST_23 + i);

	}

	@Test
	public void testEnviarEmail() throws PlataformaBusinessException {
		System.out.println("testEnviarEmail");

		EnvioEmailXMLBean envio = new EnvioEmailXMLBean();
		envio.setNombreLote(NOMBRE_LOTE_MAIL);
		envio.setServicio(SERVICIO_MAIL_ID);
		envio.setUsuario(USUARIO);
		envio.setPassword(PASS);
		MensajesXMLBean mensaje = new MensajesXMLBean();
		mensaje.setAsunto("Asunto Mensaje - MSG_JUAN_3");
		mensaje.setCuerpo("Envío de prueba a traves del servicio web ");
		mensaje.setIdExterno("MSG_JUAN_3");
		mensaje.setModo(R_CONST_15);

		DestinatarioXMLBean destinatario = new DestinatarioXMLBean();
		destinatario.setEmailDestinatario(R_CONST_25);
		destinatario.setTipoDestinatario(DestinatarioXMLBean.TIPO_TO);
//		mensaje.addDestinatario(destinatario);
		destinatario = new DestinatarioXMLBean();
		destinatario.setEmailDestinatario(R_CONST_25);
		destinatario.setTipoDestinatario(DestinatarioXMLBean.TIPO_CC);
//		mensaje.addDestinatario(destinatario);
		/*
		 * AdjuntosXMLBean adjunto1 = new AdjuntosXMLBean();
		 * adjunto1.setNombre("FP_BBDD060-2011_INERCYA.doc"); File fichero = new
		 * File("C:\\ficheroPdfEjemplo.b64"); try {
		 * adjunto1.setContenido(getBytesFromFile(fichero)); } catch
		 * (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } mensaje.addAdjunto(adjunto1);
		 */
		envio.addMensaje(mensaje);
		String result = null;

		result = instance.enviarEmail(envio, null);

		RespuestaEnvioXMLBean respuesta = new RespuestaEnvioXMLBean();
		respuesta.parseResponse(result);
		System.out.println(result);
	}
	
	
	
	/**
	 * @throws PlataformaBusinessException
	 * 
	 */
	@Test
	public void testEnviarSms() throws PlataformaBusinessException {
		EnvioSMSXMLBean envio = new EnvioSMSXMLBean();
		envio.setNombreLote("Pruebas SMS");
		envio.setServicio("1362"); // sms
		envio.setUsuario(R_CONST_7);
		envio.setPassword(R_CONST_7);
		MensajeSMSXMLBean sms = new MensajeSMSXMLBean();
		sms.setIdExterno("IDEXTERNO");
		sms.setCuerpo("Mensaje Jokin pruebas");
//		sms.addDestinatario("609691700");
		envio.addMensaje(sms);
//		sms = new MensajeSMSXMLBean();
//		sms.setIdExterno("ID_SMS_IOP_2");
//		sms.setCuerpo("Mensaje de texto prueba LIBRERIA IOP2");
//		sms.addDestinatario("+34609691700");
//		envio.addMensaje(sms);

		String result = instance.enviarSMS(envio);
		RespuestaEnvioXMLBean respuesta = new RespuestaEnvioXMLBean();
		respuesta.parseResponse(result);
		System.out.println(result);

	}

	@Test
	public void testEnviarNotificacionPush() throws PlataformaBusinessException {
	
		
		EnvioPushXMLBean envio = new EnvioPushXMLBean();
		envio.setNombreLote("Prueba");
		envio.setServicio("822"); // sms
		envio.setUsuario(R_CONST_7);
		envio.setPass(R_CONST_7);

		MensajePeticionLotesPushXMLBean notificacion = new MensajePeticionLotesPushXMLBean();
//		notificacion.setIdExterno("ID_SMS_IOP_2");
		
		notificacion.setCuerpo(R_CONST_20);
		
		notificacion.setIcono(R_CONST_17);
		notificacion.setSonido(R_CONST_17);
//		notificacion.setIdentificadorUsuario("Usuario P1");
//		notificacion.setIdExterno("idexterno_x");
		notificacion.setTitulo(R_CONST_20);
//		DestinatariosPeticionLotesPushXMLBean d = new DestinatariosPeticionLotesPushXMLBean();
//		d.setIdentificadorUsuario("70894428X");
//		d.setIdExterno("lalala");
//		notificacion.getDestinatariosPush().add(d);
		
		
		envio.addMensaje(notificacion);
		

		String result = instance.enviarNotificacion(envio);
		RespuestaEnvioXMLBean respuesta = new RespuestaEnvioXMLBean();
		respuesta.parseResponse(result);
		System.out.println(result);

	}
	
	@Test
	public void testRegistroUsuario() {
		
		RegistroUsuarioPushResponse respuesta;		
		respuesta = instanceUsuarios.registroUsuario(USUARIO_NUEVO_NOMBRE, USUARIO_NUEVO_SERVIDIOID, USUARIO, PASS, USUARIO_NUEVO_PLATAFORMAID, USUARIO_NUEVO_TOKEN, USUARIO_NUEVO_DISPOSITIVO, null,null);


		
		System.out.println(respuesta.toString());
		
	}
	
	@Test
	public void testConsultarEstadoXML() throws PlataformaBusinessException {
		System.out.println("testConsultarEstadoXML");
		
		ConsultaEstadoXMLBean consultaEstado = new ConsultaEstadoXMLBean();
		
		consultaEstado.setUsuario(R_CONST_7);
		consultaEstado.setPassword(R_CONST_7);
		
//		ConsultaEstadoXMLBean.Mensaje mensaje = new ConsultaEstadoXMLBean.Mensaje();
//		mensaje.setIdExterno("1");
//		mensaje.setIdMensaje("612485");
//		consultaEstado.setMensaje(mensaje);
		
		ConsultaEstadoXMLBean.Filtro filtro = new ConsultaEstadoXMLBean.Filtro();
//		filtro.setFechaDesde("24/02/2016");
//		filtro.setFechaHasta("27/02/2016");
//		filtro.setIdAplicacion("382");
//		filtro.setIdEstado("2");
//		filtro.setIdLote("29709");
//		filtro.setIdServicio(462+"");
//		filtro.setIdCanal("2");
//		filtro.setCodSia("codSIA");
//		filtro.setCodOrganismo("YO");
//		filtro.setCodOrganismoPagador("org Pagador");
//		filtro.setDocUsuario("doc usuario");
		ConsultaEstadoXMLBean.Filtro.Mensaje mensajeFiltro = new ConsultaEstadoXMLBean.Filtro.Mensaje();
	//	mensajeFiltro.setIdExterno("MSG_JUAN_3");
		mensajeFiltro.setIdMensaje(R_CONST_5);
		filtro.setMensaje(mensajeFiltro);
		
		consultaEstado.setFiltro(filtro);
		
		
		String s = consultaEstado.toXML();
		
		System.out.println(s);
		System.out.println(R_CONST_9);
	
		ConsultaEstadoXMLBean consultaEstado2 = new ConsultaEstadoXMLBean();
		consultaEstado2.loadObjectFromXML(s);
		
		String result = null;

		result = instanceSeguimiento.consultarEstado(consultaEstado2);

		RespuestaEnvioXMLBean respuesta = new RespuestaEnvioXMLBean();
		respuesta.parseResponse(result);
		System.out.println(result);
		
		RespuestaConsultaEstado estado = new RespuestaConsultaEstado();
		try {
			estado.parseResponse(result);
		} catch (PlataformaBusinessException e) {
			LOGGER.error(e.getMessage(), e);
			throw new PlataformaBusinessException(
					R_CONST_16);
		}
		ArrayList<ConsultaEstadoBean> resultados = estado.getResultados();
		int i = 0;
		for (ConsultaEstadoBean consultaEstadoBean : resultados) {
			System.out.println(consultaEstadoBean.getAplicacion() + R_CONST_4
					+ consultaEstadoBean.getFecha() + R_CONST_4
					+ consultaEstadoBean.getCanal() + R_CONST_4
					+ consultaEstadoBean.getEstado() + R_CONST_4
					+ consultaEstadoBean.getIdMensaje());
			i++;
		}
		System.out.println(R_CONST_23 + i);
	}

	@Test
	public void testConsultarHistoricoXML() throws PlataformaBusinessException {
		System.out.println("testConsultarHistorialXML");

ConsultaHistoricoXMLBean consultaEstado = new ConsultaHistoricoXMLBean();
//String resultado = instanceSeguimiento.consultarHistorial(MENSAJE_PRUEBAS, null,
//		USUARIO, PASSWORD);
		consultaEstado.setUsuario(R_CONST_7);
		consultaEstado.setPassword(R_CONST_7);
		
		ConsultaHistoricoXMLBean.Mensaje mensaje = new ConsultaHistoricoXMLBean.Mensaje();
		mensaje.setIdExterno(null);
		mensaje.setIdMensaje(R_CONST_5);
		consultaEstado.setMensaje(mensaje);
		
//		ConsultaHistoricoXMLBean.Filtro filtro = new ConsultaHistoricoXMLBean.Filtro();
//		filtro.setFechaDesde("26/01/2012");
//		filtro.setFechaHasta("28/02/2016");
//		filtro.setIdAplicacion("442");
//		filtro.setIdEstado("2");
//		filtro.setIdLote("29189");
//		filtro.setIdServicio(SERVICIO_ID+"");
//		filtro.setCodSia("codSIA");
//		filtro.setCodOrganismo("YO");
//		filtro.setCodOrganismoPagador("org Pagador");
//		filtro.setDocUsuario("doc usuario");
//		ConsultaEstadoXMLBean.Filtro.Mensaje mensajeFiltro = new ConsultaEstadoXMLBean.Filtro.Mensaje();
//		mensajeFiltro.setIdExterno("MSG_JUAN_3");
//		mensajeFiltro.setIdMensaje("612485");
//		filtro.setMensaje(mensajeFiltro);
		
//		consultaEstado.setFiltro(filtro);
		
		String s = consultaEstado.toXML();
		
		System.out.println(s);
		System.out.println(R_CONST_9);
		
		ConsultaHistoricoXMLBean consultaHistorico2 = new ConsultaHistoricoXMLBean();
		consultaHistorico2.loadObjectFromXML(s);
		
		String resultado = null;

		resultado = instanceSeguimiento.consultarHistorial(consultaHistorico2);

		
		RespuestaConsultaHistorial respuesta = new RespuestaConsultaHistorial();
		respuesta.parseResponse(resultado);
		ArrayList<ConsultaHistoricoBean> listado = respuesta.getResultados();
		int i = 0;
		for (ConsultaHistoricoBean consultaHistoricoBean : listado) {
			System.out.println(consultaHistoricoBean.getIdMensaje() + R_CONST_4
					+ consultaHistoricoBean.getIdExterno() + R_CONST_4
					+ consultaHistoricoBean.getFecha());
			i++;
		}
		System.out.println(R_CONST_23 + i);
		
		
		respuesta.parseResponse(resultado);
		System.out.println(resultado);
		
	}
	
	@Test
	public void testReenviarMensajeXML() throws PlataformaBusinessException {
		System.out.println("testReenviarMensajeXML");
		
		OperacionesMensajesXMLBean operacionesMensajes = new OperacionesMensajesXMLBean();
//		operacionesMensajes.setLote(LOTE_PRUEBAS+"");
		operacionesMensajes.setUsuario(R_CONST_7);
		operacionesMensajes.setPassword(R_CONST_7);
		
		OperacionesMensajesXMLBean.Mensaje mensaje = new OperacionesMensajesXMLBean.Mensaje();
//		mensaje.setIdExterno("MSG_JUAN_3");
		mensaje.setIdMensaje("521978");
		operacionesMensajes.setMensaje(mensaje);
			
		String s = operacionesMensajes.toXML();
		
		System.out.println(s);
		System.out.println(R_CONST_9);
		
		OperacionesMensajesXMLBean operacionesMensajes2 = new OperacionesMensajesXMLBean();
		operacionesMensajes2.loadObjectFromXML(s);
		
		String resultado = null;

		resultado = instanceOperaciones.reenviarMensaje(operacionesMensajes2);
		RespuestaOperacion respuestaOperacion = new RespuestaOperacion();
		respuestaOperacion.parseResponse(resultado);
		if (respuestaOperacion.getErrores().isEmpty()) {
			System.out
					.println(R_CONST_6
							+ MENSAJE_PRUEBAS);
		} else {
			System.out.println(R_CONST_11 + MENSAJE_PRUEBAS
					+ R_CONST_18 + respuestaOperacion.getErrores().get(0));
		}

	}

	@Test
	public void testAnularMensajeXML() throws PlataformaBusinessException {
		System.out.println("testAnularrMensajeXML");
		
		OperacionesMensajesXMLBean operacionesMensajes = new OperacionesMensajesXMLBean();
//		operacionesMensajes.setLote(LOTE_PRUEBAS+"");
		operacionesMensajes.setUsuario(R_CONST_7);
		operacionesMensajes.setPassword(R_CONST_7);
		
		OperacionesMensajesXMLBean.Mensaje mensaje = new OperacionesMensajesXMLBean.Mensaje();
//		mensaje.setIdExterno("IDEXTERNO2");
		mensaje.setIdMensaje("572288");
		operacionesMensajes.setMensaje(mensaje);
			
		String s = operacionesMensajes.toXML();
		
				
		System.out.println(s);
		System.out.println(R_CONST_9);
		
		OperacionesMensajesXMLBean operacionesMensajes2 = new OperacionesMensajesXMLBean();
		operacionesMensajes2.loadObjectFromXML(s);
		
		String resultado = null;
		
		resultado = instanceOperaciones.anularMensaje(operacionesMensajes2);
		RespuestaOperacion respuestaOperacion = new RespuestaOperacion();
		respuestaOperacion.parseResponse(resultado);
		if (respuestaOperacion.getErrores().isEmpty()) {
			System.out
					.println(R_CONST_8
							+ MENSAJE_PRUEBAS);
		} else {
			System.out.println(R_CONST_22 + MENSAJE_PRUEBAS
					+ R_CONST_18 + respuestaOperacion.getErrores().get(0));
		}

	}
	
	
	@Test
	public void testReenviarLoteXML() throws PlataformaBusinessException {
		System.out.println("testReenviarLoteXML");
		String kk = "";
		OperacionesLotesMensajesXMLBean operacionesLotesMensajes = new OperacionesLotesMensajesXMLBean();
		operacionesLotesMensajes.setLote("34385");
		operacionesLotesMensajes.setUsuario(R_CONST_7);
		operacionesLotesMensajes.setPassword(R_CONST_7);
		
//		OperacionesLotesMensajesXMLBean.Mensaje mensaje = new OperacionesLotesMensajesXMLBean.Mensaje();
//		mensaje.setIdExterno("MSG_JUAN_3");
//		mensaje.setIdMensaje("29082");
//		operacionesLotesMensajes.setMensaje(mensaje);
		
		String s = operacionesLotesMensajes.toXML();
		
		System.out.println(s);
		System.out.println(R_CONST_9);
		
//		OperacionesLotesMensajesXMLBean operacionesLotesMensajes2 = new OperacionesLotesMensajesXMLBean();
//		operacionesLotesMensajes2.loadObjectFromXML(kk);
//		
		String resultado = null;
		
		resultado = instanceOperaciones.reenviarLote(operacionesLotesMensajes);
		RespuestaOperacion respuestaOperacion = new RespuestaOperacion();
		respuestaOperacion.parseResponse(resultado);
		if (respuestaOperacion.getErrores().isEmpty()) {
			System.out
					.println(R_CONST_14
							+ LOTE_PRUEBAS);
		} else {
			System.out.println(R_CONST_10 + LOTE_PRUEBAS + R_CONST_18
					+ respuestaOperacion.getErrores().get(0));
		}
		
	}
	
	@Test
	public void testAnularLoteXML() throws PlataformaBusinessException {
		System.out.println("testAnularLoteXML");
		
		OperacionesLotesMensajesXMLBean operacionesLotesMensajes = new OperacionesLotesMensajesXMLBean();
		operacionesLotesMensajes.setLote("34335");
		operacionesLotesMensajes.setUsuario(R_CONST_7);
		operacionesLotesMensajes.setPassword(R_CONST_7);
		
//		OperacionesLotesMensajesXMLBean.Mensaje mensaje = new OperacionesLotesMensajesXMLBean.Mensaje();
//		mensaje.setIdExterno("MSG_JUAN_3");
//		mensaje.setIdMensaje("30034");
//		operacionesLotesMensajes.setMensaje(mensaje);
		
		String s = operacionesLotesMensajes.toXML();
		
		System.out.println(s);
		System.out.println(R_CONST_9);
		
		OperacionesLotesMensajesXMLBean operacionesLotesMensajes2 = new OperacionesLotesMensajesXMLBean();
		operacionesLotesMensajes2.loadObjectFromXML(s);
		
		String resultado = null;
		
		resultado = instanceOperaciones.anularLote(operacionesLotesMensajes2);
		RespuestaOperacion respuestaOperacion = new RespuestaOperacion();
		respuestaOperacion.parseResponse(resultado);
		if (respuestaOperacion.getErrores().isEmpty()) {
			System.out
					.println(R_CONST_3
							+ LOTE_PRUEBAS);
		} else {
			System.out.println(R_CONST_19 + LOTE_PRUEBAS + R_CONST_18
					+ respuestaOperacion.getErrores().get(0));
		}
		
	}
	
	@Test
	public void testRecepcionMensajesXML() throws PlataformaBusinessException {
		System.out.println("testRecepcionMensajesXML");
		
		RecibirSMSRequest recepcionMensajes = new RecibirSMSRequest();
		recepcionMensajes.setRecipient("638444086");
		recepcionMensajes.setMessageId("118EFE5D");		
//		recepcionMensajes.setSMSText("SIM Prueba enviada desde soporte MenTeS");
		recepcionMensajes.setSMSText("");
		recepcionMensajes.setUser("movistar");
		recepcionMensajes.setPassword("bW92aXN0YXI=");
		recepcionMensajes.setSender(R_CONST_12);
		
		String s = recepcionMensajes.toXML();
		
		System.out.println(s);
		System.out.println(R_CONST_9);
		
		RecibirSMSRequest recepcionMensajes2 = new RecibirSMSRequest();
		recepcionMensajes2.loadObjectFromXML(s);
		
//		RecibirSMSResponse resultado = null;
//		
//		resultado = instanceRecepcionMensajes.recibirSMS(recepcionMensajes2);
		String resultadoString = instanceRecepcionMensajes.recibirSMSXML(recepcionMensajes2);
		System.out.println(resultadoString);	
	}
	
	@Test
	public void testRecepcionEstadoXML() throws PlataformaBusinessException {
		System.out.println("testRecepcionEstadoXML");
		
		RecepcionEstadoSMSXMLBean recepcionEstado = new RecepcionEstadoSMSXMLBean();
		recepcionEstado.setRecipient(R_CONST_7);
		recepcionEstado.setMensajeId(R_CONST_1);
		recepcionEstado.setMessajeStatus(R_CONST_1);
		recepcionEstado.setUser(R_CONST_7);
		recepcionEstado.setPassword(R_CONST_7);
		recepcionEstado.setSender("Remitente");
		recepcionEstado.setStatusText("Texto OK estado");
		
		String s = recepcionEstado.toXML();
		
		System.out.println(s);
		System.out.println(R_CONST_9);
		
		RecepcionEstadoSMSXMLBean recepcionEstado2 = new RecepcionEstadoSMSXMLBean();
		recepcionEstado2.loadObjectFromXML(s);
		
		RespuestaEstadoSMSXMLBean resultado = null;
		
		String res = instanceRecepcionEstado.recibirEstadoSMSXML(recepcionEstado2);
		System.out.println(res);		
	}
	
	@Test
	public void testRegistroUsuariosXML() throws PlataformaBusinessException {
		System.out.println("testRegistroUsuariosXML");

		UsuariosXMLBean usuarioXML = new UsuariosXMLBean();
//		usuarioXML.setNombreUsuario("Usuario P1");
		usuarioXML.setServicioId("1042");
		usuarioXML.setUsuario(USUARIO);
		usuarioXML.setPassword(USUARIO);
		usuarioXML.setPlataformaId("1");	//1 ANDROID - 2 APPLE
//		usuarioXML.setTokenUsuario("TOKEN APPLE");
		usuarioXML.setTokenUsuario("TOKEN ANDROID MODIFICADO");
		usuarioXML.setDispositivoId("1042_42");
		
		
		String s = usuarioXML.toXML();
		
		System.out.println(s);
		System.out.println(R_CONST_9);
		
		UsuariosXMLBean usuarioXML2 = new UsuariosXMLBean();
		usuarioXML2.loadObjectFromXML(s);
		
		RegistroUsuarioPushResponse resultado = null;
		
		resultado = instanceUsuarios.registroUsuario(usuarioXML2);
		String sa = resultado.toXML();
		System.out.println(sa);
		System.out.println(resultado.toString());		
	}
	
	public void testEnviarPeticionLotesSMS() throws PlataformaBusinessException {
		
		InputStream is = ClienteTest.class.getResourceAsStream("ejemplo1.xml");
//		InputStream is = ClienteTest.class.getResourceAsStream("peticionEnvioSms.xml");
//		InputStream is = ClienteTest.class.getResourceAsStream("peticionLotesVariosDestinatariosSMS.xml");
//		InputStream is = ClienteTest.class.getResourceAsStream("peticionSMSVariosDestinatarios.xml"); 
		
		
		PeticionXMLBean peticion = new PeticionXMLBean();
		peticion.loadObjectFromXML(es.minhap.plataformamensajeria.iop.util.Utils.getStringFromInputStream(is));
		String result = instanceLotes.enviarLotesSMS(peticion);
//		RespuestaEnvioXMLBean respuesta = new RespuestaEnvioXMLBean();
//		respuesta.parseResponse(result);
		System.out.println(result);

	}
	
public void testEnviarPeticionLotesPush() throws PlataformaBusinessException {
		
		InputStream is = ClienteTest.class.getResourceAsStream("ejemplo2Push.xml");
//		InputStream is = ClienteTest.class.getResourceAsStream("peticionLotes.xml");
//		InputStream is = ClienteTest.class.getResourceAsStream("peticionLotesVariosDestinatariosPUSH.xml");
		 
		PeticionXMLBean peticion = new PeticionXMLBean();
		peticion.loadObjectFromXML(es.minhap.plataformamensajeria.iop.util.Utils.getStringFromInputStream(is));
		String result = instanceLotes.enviarLotesNotificacion(peticion);
//		RespuestaEnvioXMLBean respuesta = new RespuestaEnvioXMLBean();
//		respuesta.parseResponse(result);
		System.out.println(result);

	}

public void testReenviarLote2() throws PlataformaBusinessException {
	
//	InputStream is = ClienteTest.class.getResourceAsStream("peticionLotes.xml");
	InputStream is = ClienteTest.class.getResourceAsStream("reenvioLote.xml");
	 
	 OperacionesLotesMensajesXMLBean peticion = new OperacionesLotesMensajesXMLBean();
	peticion.loadObjectFromXML(es.minhap.plataformamensajeria.iop.util.Utils.getStringFromInputStream(is));
	String result = instanceOperaciones.reenviarLote(peticion);
//	RespuestaEnvioXMLBean respuesta = new RespuestaEnvioXMLBean();
//	respuesta.parseResponse(result);
	System.out.println(result);

}

public void testAnularLote2() throws PlataformaBusinessException {
	
//	InputStream is = ClienteTest.class.getResourceAsStream("peticionLotes.xml");
	InputStream is = ClienteTest.class.getResourceAsStream(R_CONST_13);
	 
	 OperacionesLotesMensajesXMLBean peticion = new OperacionesLotesMensajesXMLBean();
	peticion.loadObjectFromXML(es.minhap.plataformamensajeria.iop.util.Utils.getStringFromInputStream(is));
	String result = instanceOperaciones.anularLote(peticion);
//	RespuestaEnvioXMLBean respuesta = new RespuestaEnvioXMLBean();
//	respuesta.parseResponse(result);
	System.out.println(result);

}

public void testReenviarMensaje2() throws PlataformaBusinessException {
	
//	InputStream is = ClienteTest.class.getResourceAsStream("peticionLotes.xml");
	InputStream is = ClienteTest.class.getResourceAsStream("reenviarMensaje.xml");
	 
	 OperacionesMensajesXMLBean peticion = new OperacionesMensajesXMLBean();
	peticion.loadObjectFromXML(es.minhap.plataformamensajeria.iop.util.Utils.getStringFromInputStream(is));
	String result = instanceOperaciones.reenviarMensaje(peticion);
//	RespuestaEnvioXMLBean respuesta = new RespuestaEnvioXMLBean();
//	respuesta.parseResponse(result);
	System.out.println(result);

}

public void testAnularMensaje2() throws PlataformaBusinessException {
	
//	InputStream is = ClienteTest.class.getResourceAsStream("peticionLotes.xml");
	InputStream is = ClienteTest.class.getResourceAsStream(R_CONST_13);
	 
	 OperacionesMensajesXMLBean peticion = new OperacionesMensajesXMLBean();
	peticion.loadObjectFromXML(es.minhap.plataformamensajeria.iop.util.Utils.getStringFromInputStream(is));
	String result = instanceOperaciones.anularMensaje(peticion);
//	RespuestaEnvioXMLBean respuesta = new RespuestaEnvioXMLBean();
//	respuesta.parseResponse(result);
	System.out.println(result);
}

public void testEnviarPeticionLotesEmail() throws PlataformaBusinessException {
	
	InputStream is = ClienteTest.class.getResourceAsStream("envio_mails_masivos.xml");
//	InputStream is = ClienteTest.class.getResourceAsStream("peticionLotesVariosDestinatariosEmail.xml");
//	InputStream is = ClienteTest.class.getResourceAsStream("peticionLotesEmail.xml");
	 
	PeticionXMLBean peticion = new PeticionXMLBean();
	peticion.loadObjectFromXML(es.minhap.plataformamensajeria.iop.util.Utils.getStringFromInputStream(is));
	String result = instanceLotes.enviarLotesEmail(peticion, null);
//	RespuestaEnvioXMLBean respuesta = new RespuestaEnvioXMLBean();
//	respuesta.parseResponse(result);
	System.out.println(result);

}
	
public void testEnviarAEAT() throws PlataformaBusinessException {
	
	//InputStream is = ClienteTest.class.getResourceAsStream("peticionEnvioSms.xml");
	 
	EnvioAEATXMLBean envio = new EnvioAEATXMLBean();

	envio.setCodOrganismoPagadorSMS("AEAT");
	envio.setCuerpo("Mensaje de AEAT");
	envio.setDeliveryReportURL("http://hola.com");
	envio.setDestinatario(R_CONST_12);
	envio.setIdExterno(new Date().toString());
	String result = instancePremium.cambiarEstadoSMSPremium(763403, "La danza de los nadie, OK");
//	String result = instancePremium.enviarSMSPremium(envio,"pruebasSIMdes","pruebasSIMdes",1602,"pruebasSIMdes","pruebasSIMdes");
//	String result = instance.enviarSMS(envio);
//	RespuestaEnvioXMLBean respuesta = new RespuestaEnvioXMLBean();
//	respuesta.parseResponse(result);
//	System.out.println(result);

}

public void testEnviarGISS() throws PlataformaBusinessException {
	
	//InputStream is = ClienteTest.class.getResourceAsStream("peticionEnvioSms.xml");
	 
	EnvioGISSXMLBean envio = new EnvioGISSXMLBean();

	envio.setCodOrganismoPagadorSMS("11111112");
	envio.setCuerpo("Mensaje de GISS");
//	envio.setDeliveryReportURL("");
	envio.setDestinatario("+34609691700");
	envio.setIdPeticion(new Date().toString());
	String result = instanceGISS.cambiarEstadoSMSPremium(786655, "OK | SwitchSMS_MT_42590_103");
//	String result = instanceGISS.enviarSMSGISS(envio,"pruebasSIMdes","pruebasSIMdes",1602,"pruebasSIMdes","pruebasSIMdes");
//	String result = instance.enviarSMS(envio);
//	RespuestaEnvioXMLBean respuesta = new RespuestaEnvioXMLBean();
//	respuesta.parseResponse(result);
//	System.out.println(result);

}

public void testProcesarSAML() throws PlataformaBusinessException {
	
	PeticionClaveAuthResponse peticion = new PeticionClaveAuthResponse();
	
	peticion.setDispositivoId("822_322");
	peticion.setIdPlataforma(R_CONST_15);
	peticion.setIdServicio("842");
	peticion.setPassword(R_CONST_7);
	peticion.setUsuario(R_CONST_7);
	
	
	String result = instanceSAML.insertarDatosUsuario(peticion, "JUAN ESPAÑOL ESPAÑOL",
			"70894428X", R_CONST_21, R_CONST_21);
	System.out.println(result);
}

	
	public void testEnviarSmsFromXmlFile() throws PlataformaBusinessException {
		
		InputStream is = ClienteTest.class.getResourceAsStream("peticionEnvioSms.xml");
		 
		EnvioSMSXMLBean envio = new EnvioSMSXMLBean();
		envio.loadObjectFromXML(es.minhap.plataformamensajeria.iop.util.Utils.getStringFromInputStream(is));
		String result = instance.enviarSMS(envio);
		RespuestaEnvioXMLBean respuesta = new RespuestaEnvioXMLBean();
		respuesta.parseResponse(result);
		System.out.println(result);

	}
	
	public void testEnviarNotificacionPushFromXmlFile() throws PlataformaBusinessException {
		
		InputStream is = ClienteTest.class.getResourceAsStream("peticionEnvioNotificacionPush.xml");
		
		EnvioPushXMLBean envio = new EnvioPushXMLBean();
		envio.loadObjectFromXML(es.minhap.plataformamensajeria.iop.util.Utils.getStringFromInputStream(is));

		String result = instance.enviarNotificacion(envio);
		RespuestaEnvioXMLBean respuesta = new RespuestaEnvioXMLBean();
		respuesta.parseResponse(result);
		System.out.println(result);

	}

	public void executeTest() throws PlataformaBusinessException {

//		 testEnviarEmail();
//		 testEnviarSms();
//		testConsultarHistorico();
//		testConsultarHistoricoXML();
//		 testAnularMensaje();
//		testAnularMensajeXML();
//		 testReenviarLote();	
//		 testReenviarLoteXML();
//		testAnularLote();
//		testAnularLoteXML();
//		testReenviarMensaje();
//		testReenviarMensajeXML();
//		testConsultarEstado();
//		testConsultarEstadoXML();
//		testEnviarNotificacionPush();
//		 testEnviarSmsFromXmlFile();
//		 testEnviarNotificacionPushFromXmlFile();
//		testRecepcionMensajesXML();
//		testRecepcionEstadoXML();
//		testRecepcionEstadoXML();
//		 testRegistroUsuariosXML();
		 testEnviarPeticionLotesSMS();
//		 testEnviarPeticionLotesPush();
//		 testEnviarPeticionLotesEmail();
//		testReenviarLote2();
//		testAnularLote2();
//		testReenviarMensaje2();
//		testAnularMensaje2();
//		 testEnviarAEAT();
//		 testEnviarGISS();
//		 testProcesarSAML();
	}

	public static void main(String[] args) throws PlataformaBusinessException {

		try {
			ClienteTest ct = new ClienteTest();
			ct.executeTest();
		} catch (Exception ex) {
			LOGGER.error("[ClienteTest] - ",ex);		
		}
	}

}
