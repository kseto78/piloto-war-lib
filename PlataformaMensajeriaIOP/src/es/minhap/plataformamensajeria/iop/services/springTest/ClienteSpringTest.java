/**
 * 
 */
package es.minhap.plataformamensajeria.iop.services.springTest;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit38.AbstractJUnit38SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import es.map.sim.jms.sender.SIMMessageSender;
import es.map.sim.negocio.modelo.MensajeJMS;
import es.minhap.common.entity.SearchResult;
import es.minhap.common.properties.PropertiesServices;
import es.minhap.common.spring.ApplicationContextProvider;
import es.minhap.misim.bus.dao.ViewMisimDAO;
import es.minhap.misim.bus.model.ViewMisim;
import es.minhap.misim.bus.query.ViewMisimQuery;
import es.minhap.plataformamensajeria.iop.beans.ConsultaEstadoXMLBean;
import es.minhap.plataformamensajeria.iop.beans.ConsultaHistoricoXMLBean;
import es.minhap.plataformamensajeria.iop.beans.EnvioGISSXMLBean;
import es.minhap.plataformamensajeria.iop.beans.NotificacionesPushXMLBean;
import es.minhap.plataformamensajeria.iop.beans.PeticionClaveAuthRequest;
import es.minhap.plataformamensajeria.iop.beans.PeticionClaveAuthResponse;
import es.minhap.plataformamensajeria.iop.beans.RecepcionEstadoSMSXMLBean;
import es.minhap.plataformamensajeria.iop.beans.RecibirSMSRequest;
import es.minhap.plataformamensajeria.iop.beans.RegistroUsuarioXMLBean;
import es.minhap.plataformamensajeria.iop.beans.ServiciosDisponiblesXMLBean;
import es.minhap.plataformamensajeria.iop.beans.UsuariosXMLBean;
import es.minhap.plataformamensajeria.iop.beans.getAvisosUsuario.PeticionGetAvisosUsuario;
import es.minhap.plataformamensajeria.iop.beans.lotes.PeticionXMLBean;
import es.minhap.plataformamensajeria.iop.manager.TblPlanificacionesManager;
import es.minhap.plataformamensajeria.iop.services.ayuda.IGestionAyudaService;
import es.minhap.plataformamensajeria.iop.services.envioLotes.IEnvioLotesMensajesService;
import es.minhap.plataformamensajeria.iop.services.envioPremium.IEnvioPremiumGISSService;
import es.minhap.plataformamensajeria.iop.services.exceptions.PlataformaBusinessException;
import es.minhap.plataformamensajeria.iop.services.gestionNotificacionesPush.IGestionNotificacionesPushService;
import es.minhap.plataformamensajeria.iop.services.gestionServiciosPush.IGestionServiciosPushService;
import es.minhap.plataformamensajeria.iop.services.operaciones.IOperacionesMensajesService;
import es.minhap.plataformamensajeria.iop.services.procesarSAMLResponse.IGestionSAMLRequestService;
import es.minhap.plataformamensajeria.iop.services.procesarSAMLResponse.IGestionSAMLResponseService;
import es.minhap.plataformamensajeria.iop.services.procesarSAMLResponse.IGetAuthService;
import es.minhap.plataformamensajeria.iop.services.recepcion.IRecepcionMensajesService;
import es.minhap.plataformamensajeria.iop.services.recepcionEstadoSMS.IRecepcionEstadoSMSService;
import es.minhap.plataformamensajeria.iop.services.seguimiento.ISeguimientoMensajesService;
import es.minhap.plataformamensajeria.iop.services.tests.ClienteTest;
import es.minhap.plataformamensajeria.iop.services.usuariosplataformas.IRegistroUsuarioPushService;
import es.minhap.plataformamensajeria.iop.services.usuariosplataformas.IRegistroUsuarioService;
import es.minhap.plataformamensajeria.iop.services.usuariosplataformas.RegistroUsuarioPushResponse;
import es.minhap.plataformamensajeria.iop.services.usuariosplataformas.RegistroUsuarioResponse;

/**
 * @author everis
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "transactionManager")
@ContextConfiguration(locations = { ModelTestUtil.SPRING_CONTEXT_LOCATION, ModelTestUtil.SPRING_JMS_CONTEXT_LOCATION })
public class ClienteSpringTest extends AbstractJUnit38SpringContextTests {

	private static Logger logger = LoggerFactory.getLogger(ClienteSpringTest.class);

	@Resource(name = "envioLotesMensajesImpl")
	IEnvioLotesMensajesService envioLotesMensajes;

	@Resource(name = "recepcionMensajesImpl")
	IRecepcionMensajesService recepcionMensajes;

	@Resource(name = "operacionesMensajesImpl")
	IOperacionesMensajesService operacionesMensajes;

	@Resource(name = "envioPremiumGISSServiceImpl")
	IEnvioPremiumGISSService envioPremiumGISSService;

	@Resource(name = "TblPlanificacionesManagerImpl")
	TblPlanificacionesManager planificacionesManager;

	@Resource(name = "recepcionEstadoSMSImpl")
	IRecepcionEstadoSMSService recepcionEstado;

	@Resource(name = "seguimientoMensajesImpl")
	ISeguimientoMensajesService seguimientoMensaje;

	@Resource(name = "registroUsuarioPushImpl")
	IRegistroUsuarioPushService registroUsuarioPush;

	@Resource(name = "registroUsuarioServiceImpl")
	IRegistroUsuarioService registroUsuario;

	@Resource(name = "gestionSAMLRequestImpl")
	IGestionSAMLRequestService samlRequest;

	@Resource(name = "gestionSAMLResponseImpl")
	IGestionSAMLResponseService samlResponse;

	@Resource(name = "getAuthServiceImpl")
	IGetAuthService getAuthService;

	@Resource(name = "gestionAyudaImpl")
	IGestionAyudaService gestionAyuda;
	
	@Resource(name = "gestionNotificacionesPushImpl")
	IGestionNotificacionesPushService gestionNotificacionesPush;
	
	@Resource(name = "gestionServiciosPushImpl")
	IGestionServiciosPushService gestionServiciosPush;
	
	@Resource(name = "messageSender")
	private SIMMessageSender sender;
	
	@Resource
	private ViewMisimDAO viewMisimDao;

	private PropertiesServices ps;

	private ApplicationContextProvider context;

	@Test
	public final void testViewMisimDAO() throws InterruptedException {
		logger.debug("Starting test testViewMisimDAO");
		ViewMisimQuery query = new ViewMisimQuery();
		SearchResult<ViewMisim> resultado = viewMisimDao.search(query);
		assertNotNull(resultado);
	}
	
	/**
	 * Test de encolar mensajes premium, el listener enviarMensajeMessageReceiver se 
	 * debera invocar inmediatamente.
	 * @throws InterruptedException
	 */
	@Test
	public final void JMSComponentEncolarPremium() throws InterruptedException {
		logger.debug("Starting test JMSComponentEncolarPremium");
		for (int i = 0; i < 10; i++) {
			MensajeJMS mensaje = new MensajeJMS();
			mensaje.setIdMensaje("839557");
			mensaje.setDestinatarioMensajeId("92124");
			mensaje.setIdExterno(new Timestamp(System.currentTimeMillis()).toString());
			mensaje.setCodSia("TestCodSia1");
			mensaje.setIdCanal("1");
			long maxRetries = 0L;
			String serviceName = "283";
			boolean premium = false;
			sender.send(mensaje, maxRetries, serviceName, premium);
		}
	
	}
	
//	@Test
	public final void testEnvioMensaje() {

		if (!ApplicationContextProvider.getInstance().isLoaded()) {
			ApplicationContextProvider.getInstance().loadApplicationContext(applicationContext);
		}
		context = ApplicationContextProvider.getInstance();
		ps = new PropertiesServices(context);

		InputStream is = ClienteTest.class.getResourceAsStream("ejemplo1.xml");

		PeticionXMLBean peticion = new PeticionXMLBean();
		try {
			peticion.loadObjectFromXML(es.minhap.plataformamensajeria.iop.util.Utils.getStringFromInputStream(is));
		} catch (PlataformaBusinessException e) {
			logger.error("Se ha producido un error en testEnvioMensaje:", e);
		}
		String result = envioLotesMensajes.enviarLotesSMS(peticion);
		logger.info("resultado -> " + result);
	}

	// @Test
	public final void testEnvioMensajePush() {

		if (!ApplicationContextProvider.getInstance().isLoaded()) {
			ApplicationContextProvider.getInstance().loadApplicationContext(applicationContext);
		}
		context = ApplicationContextProvider.getInstance();
		ps = new PropertiesServices(context);
		InputStream is = ClienteTest.class.getResourceAsStream("ejemplo1Push.xml");

		PeticionXMLBean peticion = new PeticionXMLBean();
		try {
			peticion.loadObjectFromXML(es.minhap.plataformamensajeria.iop.util.Utils.getStringFromInputStream(is));
		} catch (PlataformaBusinessException e) {
			logger.error("Se ha producido un error en testEnvioMensaje:", e);
		}
		String result = envioLotesMensajes.enviarLotesNotificacion(peticion);
		logger.info("resultado -> " + result);
	}

	// @Test
	public final void testEnvioMensajeEmail() {

		if (!ApplicationContextProvider.getInstance().isLoaded()) {
			ApplicationContextProvider.getInstance().loadApplicationContext(applicationContext);
		}
		context = ApplicationContextProvider.getInstance();
		ps = new PropertiesServices(context);

		InputStream is = ClienteTest.class.getResourceAsStream("ejemplo2Email.xml");

		PeticionXMLBean peticion = new PeticionXMLBean();
		try {
			peticion.loadObjectFromXML(es.minhap.plataformamensajeria.iop.util.Utils.getStringFromInputStream(is));
		} catch (PlataformaBusinessException e) {
			logger.error("Se ha producido un error en testEnvioMensaje:", e);
		}
		String result = envioLotesMensajes.enviarLotesEmail(peticion);
		logger.info("resultado -> " + result);
	}

	// @Test
	public final void testrecepcionSMS() {

		if (!ApplicationContextProvider.getInstance().isLoaded()) {
			ApplicationContextProvider.getInstance().loadApplicationContext(applicationContext);
		}
		context = ApplicationContextProvider.getInstance();
		ps = new PropertiesServices(context);

		InputStream is = ClienteTest.class.getResourceAsStream("ejemplo1RecepcionSMS.xml");

		RecibirSMSRequest peticion = new RecibirSMSRequest();
		try {
			peticion.loadObjectFromXML(es.minhap.plataformamensajeria.iop.util.Utils.getStringFromInputStream(is));
		} catch (PlataformaBusinessException e) {
			logger.error("Se ha producido un error en testEnvioMensaje:", e);
		}
		String result = recepcionMensajes.recibirSMSXML(peticion);
		logger.info("resultado -> " + result);
	}

	// @Test
	public final void testReenvioMensaje() {

		if (!ApplicationContextProvider.getInstance().isLoaded()) {
			ApplicationContextProvider.getInstance().loadApplicationContext(applicationContext);
		}
		context = ApplicationContextProvider.getInstance();
		ps = new PropertiesServices(context);

		String result = operacionesMensajes.reenviarMensaje(787640, "pruebasSIMdes", "pruebasSIMdes");
		logger.info("resultado -> " + result);
		result = operacionesMensajes.anularMensaje(787640, "pruebasSIMdes", "pruebasSIMdes");
		logger.info("resultado -> " + result);
	}

	// @Test
	public final void testAnularMensaje() {

		if (!ApplicationContextProvider.getInstance().isLoaded()) {
			ApplicationContextProvider.getInstance().loadApplicationContext(applicationContext);
		}
		context = ApplicationContextProvider.getInstance();
		ps = new PropertiesServices(context);

		String result = operacionesMensajes.anularMensaje(787645, "pruebasSIMdes", "pruebasSIMdes");
		logger.info("resultado -> " + result);
	}

//	 @Test
	public final void testReenvioLote() {

		if (!ApplicationContextProvider.getInstance().isLoaded()) {
			ApplicationContextProvider.getInstance().loadApplicationContext(applicationContext);
		}
		context = ApplicationContextProvider.getInstance();
		ps = new PropertiesServices(context);

		String result = operacionesMensajes.reenviarLote(37971, "pruebasSIMdes", "pruebasSIMdes");
		logger.info("resultado -> " + result);
		// result = operacionesMensajes.anularLote(37710, "pruebasSIMdes",
		// "pruebasSIMdes");
		logger.info("resultado -> " + result);
	}

	// @Test
	public final void testServiciosPlanificacion() {

		if (!ApplicationContextProvider.getInstance().isLoaded()) {
			ApplicationContextProvider.getInstance().loadApplicationContext(applicationContext);
		}
		context = ApplicationContextProvider.getInstance();
		ps = new PropertiesServices(context);

		List<Long> res = planificacionesManager.getServiciosPlanificacion();

		logger.info("resultado -> " + res.toString());
	}

	// @Test
	public final void testRecepcionEstadoSMS() {

		if (!ApplicationContextProvider.getInstance().isLoaded()) {
			ApplicationContextProvider.getInstance().loadApplicationContext(applicationContext);
		}
		context = ApplicationContextProvider.getInstance();
		ps = new PropertiesServices(context);

		RecepcionEstadoSMSXMLBean x = new RecepcionEstadoSMSXMLBean();
		x.setMensajeId("SwitchSMS_MT_37328_1161349");
		x.setMessajeStatus("-20");
		x.setPassword("pruebasSIMdes");
		x.setRecipient("696644884");
		x.setSender("609691700");
		x.setStatusText("Esto es lo qeu es statustext");
		x.setUser("pruebasSIMdes");
		String res = recepcionEstado.recibirEstadoSMSXML(x);

		logger.info("resultado -> " + res.toString());
	}

	// @Test
	public final void testSeguimientoMensajes() {

		if (!ApplicationContextProvider.getInstance().isLoaded()) {
			ApplicationContextProvider.getInstance().loadApplicationContext(applicationContext);
		}
		context = ApplicationContextProvider.getInstance();
		ps = new PropertiesServices(context);

		InputStream is = ClienteTest.class.getResourceAsStream("ejemploConsultaEstado.xml");

		ConsultaEstadoXMLBean peticion = new ConsultaEstadoXMLBean();
		try {
			peticion.loadObjectFromXML(es.minhap.plataformamensajeria.iop.util.Utils.getStringFromInputStream(is));
		} catch (PlataformaBusinessException e) {
			logger.error("Se ha producido un error en testEnvioMensaje:", e);
		}
		String result = seguimientoMensaje.consultarEstado(peticion);
		logger.info("resultado -> " + result);
	}

	// @Test
	public final void testConsultaHistorial() {

		if (!ApplicationContextProvider.getInstance().isLoaded()) {
			ApplicationContextProvider.getInstance().loadApplicationContext(applicationContext);
		}
		context = ApplicationContextProvider.getInstance();
		ps = new PropertiesServices(context);

		InputStream is = ClienteTest.class.getResourceAsStream("ejemploConsultaHistorial.xml");

		ConsultaHistoricoXMLBean peticion = new ConsultaHistoricoXMLBean();
		try {
			peticion.loadObjectFromXML(es.minhap.plataformamensajeria.iop.util.Utils.getStringFromInputStream(is));
		} catch (PlataformaBusinessException e) {
			logger.error("Se ha producido un error en testEnvioMensaje:", e);
		}
		String result = seguimientoMensaje.consultarHistorial(peticion);
		logger.info("resultado -> " + result);
	}

	// @Test
	public final void testConsultarEstadoAEAT() {

		if (!ApplicationContextProvider.getInstance().isLoaded()) {
			ApplicationContextProvider.getInstance().loadApplicationContext(applicationContext);
		}
		context = ApplicationContextProvider.getInstance();
		ps = new PropertiesServices(context);

		String res = seguimientoMensaje.consultarEstadoAEAT(822, 793133, null, "pruebasSIMdes", "pruebasSIMdes", null,
				null, null);

		logger.info("resultado -> " + res.toString());
	}

	// @Test
	public final void registroUsuarioPush() {
		if (!ApplicationContextProvider.getInstance().isLoaded()) {
			ApplicationContextProvider.getInstance().loadApplicationContext(applicationContext);
		}
		context = ApplicationContextProvider.getInstance();
		ps = new PropertiesServices(context);

		InputStream is = ClienteTest.class.getResourceAsStream("ejemploRegistroUsuarioPush.xml");

		UsuariosXMLBean peticion = new UsuariosXMLBean();
		try {
			peticion.loadObjectFromXML(es.minhap.plataformamensajeria.iop.util.Utils.getStringFromInputStream(is));
		} catch (PlataformaBusinessException e) {
			logger.error("Se ha producido un error en testEnvioMensaje:", e);
		}
		RegistroUsuarioPushResponse res = registroUsuarioPush.registroUsuario(peticion);

		logger.info("resultado -> " + res.toString());

		// Esto es para comprobar el metodo eliminarUsuario:
		boolean eliminado = registroUsuarioPush.eliminarUsuario("2345sdlkjasdfkjasdflkjasdfkqwer");
		logger.info("resultado eliminar -> " + eliminado);
	}

	// @Test
	public final void testRegistroUsuario() {

		if (!ApplicationContextProvider.getInstance().isLoaded()) {
			ApplicationContextProvider.getInstance().loadApplicationContext(applicationContext);
		}
		context = ApplicationContextProvider.getInstance();
		ps = new PropertiesServices(context);

		RegistroUsuarioResponse res = registroUsuario.registroUsuario("822_500", "822", "pruebasSIMdes",
				"pruebasSIMdes", "1", "riauriau", "822_500");

		logger.info("resultado -> " + res.toString());
	}

	// @Test
	public final void gestionSAMLRequest() {
		if (!ApplicationContextProvider.getInstance().isLoaded()) {
			ApplicationContextProvider.getInstance().loadApplicationContext(applicationContext);
		}
		context = ApplicationContextProvider.getInstance();
		ps = new PropertiesServices(context);

		InputStream is = ClienteTest.class.getResourceAsStream("ejemploGenerarSAMLRequest.xml");

		PeticionClaveAuthRequest peticion = new PeticionClaveAuthRequest();
		try {
			peticion.loadObjectFromXML(es.minhap.plataformamensajeria.iop.util.Utils.getStringFromInputStream(is));
		} catch (PlataformaBusinessException e) {
			logger.error("Se ha producido un error en testEnvioMensaje:", e);
		}
		String res = samlRequest.comprobarDatosUsuario(peticion);

		logger.info("resultado -> " + res);

	}

	// @Test
	public final void gestionSAMLResponse() {
		if (!ApplicationContextProvider.getInstance().isLoaded()) {
			ApplicationContextProvider.getInstance().loadApplicationContext(applicationContext);
		}
		context = ApplicationContextProvider.getInstance();
		ps = new PropertiesServices(context);

		InputStream is = ClienteTest.class.getResourceAsStream("ejemploGenerarSAMLResponse.xml");
		//ojo para probarlo hay que cambiar el namespace
		PeticionClaveAuthResponse peticion = new PeticionClaveAuthResponse();
		try {
			peticion.loadObjectFromXML(es.minhap.plataformamensajeria.iop.util.Utils.getStringFromInputStream(is));
		} catch (PlataformaBusinessException e) {
			logger.error("Se ha producido un error en testEnvioMensaje:", e);
		}
		String res = samlResponse.insertarDatosUsuario(peticion, "Jokin", "77522552V", "Chachi", "Piruli");

		logger.info("resultado -> " + res);

	}

	//@Test
	public final void getAuthService() {
		if (!ApplicationContextProvider.getInstance().isLoaded()) {
			ApplicationContextProvider.getInstance().loadApplicationContext(applicationContext);
		}
		context = ApplicationContextProvider.getInstance();
		ps = new PropertiesServices(context);

		InputStream is = ClienteTest.class.getResourceAsStream("ejemploGenerarSAMLRequest.xml");

		PeticionClaveAuthRequest peticion = new PeticionClaveAuthRequest();
		try {
			peticion.loadObjectFromXML(es.minhap.plataformamensajeria.iop.util.Utils.getStringFromInputStream(is));
		} catch (PlataformaBusinessException e) {
			logger.error("Se ha producido un error en testEnvioMensaje:", e);
		}
		String res = getAuthService.getDatosUsuario(peticion);

		logger.info("resultado -> " + res);

	}

	//@Test
	public final void testGestionAyuda() {

		if (!ApplicationContextProvider.getInstance().isLoaded()) {
			ApplicationContextProvider.getInstance().loadApplicationContext(applicationContext);
		}
		context = ApplicationContextProvider.getInstance();
		ps = new PropertiesServices(context);

		String res = gestionAyuda.gestionAyuda("pruebasSIMdes", "pruebasSIMdes");

		logger.info("resultado -> " + res.toString());
	}

	// @Test
	public final void testEnvioSMSGISS() {

		if (!ApplicationContextProvider.getInstance().isLoaded()) {
			ApplicationContextProvider.getInstance().loadApplicationContext(applicationContext);
		}
		context = ApplicationContextProvider.getInstance();
		ps = new PropertiesServices(context);

		InputStream is = ClienteTest.class.getResourceAsStream("ejemploSMSGISS.xml");

		EnvioGISSXMLBean peticion = new EnvioGISSXMLBean();
		try {
			peticion.loadObjectFromXML(es.minhap.plataformamensajeria.iop.util.Utils.getStringFromInputStream(is));
			String username = ps.getMessage("giss.usuario.sms", null, null, null);
			String password = ps.getMessage("giss.contrasena.sms", null, null, null);
			Integer servicio = new Integer(ps.getMessage("giss.servicio.sms.premium", null, null, null));
			String usuarioMISIM = ps.getMessage("misim.aplicacion.giss.usuario.sms", null, null, null);
			String passwordMISIM = ps.getMessage("misim.aplicacion.giss.contrasena.sms", null, null, null);
			String result = envioPremiumGISSService.enviarSMSGISS(peticion, username, password, servicio, usuarioMISIM,
					passwordMISIM);
			logger.info("resultado -> " + result);
		} catch (PlataformaBusinessException e) {
			logger.error("Se ha producido un error en testEnvioMensaje:", e);

		}
	}
	
	
	//@Test
	public final void testgestionNotificacionesPush() {

		if (!ApplicationContextProvider.getInstance().isLoaded()) {
			ApplicationContextProvider.getInstance().loadApplicationContext(applicationContext);
		}
		context = ApplicationContextProvider.getInstance();
		ps = new PropertiesServices(context);

		InputStream is = ClienteTest.class.getResourceAsStream("ejemploGestionNotificacionPush.xml");

		NotificacionesPushXMLBean peticion = new NotificacionesPushXMLBean();
		try {
			peticion.loadObjectFromXML(es.minhap.plataformamensajeria.iop.util.Utils.getStringFromInputStream(is));
		} catch (PlataformaBusinessException e) {
			logger.error("Se ha producido un error en testEnvioMensaje:", e);
		}
		String res = gestionNotificacionesPush.notificacionCambioEstado(peticion);

		logger.info("resultado -> " + res);
	}
	
//	@Test
	public final void testgetAvisosUsuaro() {

		if (!ApplicationContextProvider.getInstance().isLoaded()) {
			ApplicationContextProvider.getInstance().loadApplicationContext(applicationContext);
		}
		context = ApplicationContextProvider.getInstance();
		ps = new PropertiesServices(context);

		InputStream is = ClienteTest.class.getResourceAsStream("ejemploGetAvisosUsuariosPush.xml");

		PeticionGetAvisosUsuario peticion = new PeticionGetAvisosUsuario();
		try {
			peticion.loadObjectFromXML(es.minhap.plataformamensajeria.iop.util.Utils.getStringFromInputStream(is));
		} catch (PlataformaBusinessException e) {
			logger.error("Se ha producido un error en testEnvioMensaje:", e);
		}
		String res = gestionNotificacionesPush.getAvisosUsuarioPush(peticion);

		logger.info("resultado -> " + res);
	}
	
	//@Test
	public final void testRegistroUsuarioEnServicio() {

		if (!ApplicationContextProvider.getInstance().isLoaded()) {
			ApplicationContextProvider.getInstance().loadApplicationContext(applicationContext);
		}
		context = ApplicationContextProvider.getInstance();
		ps = new PropertiesServices(context);

		InputStream is = ClienteTest.class.getResourceAsStream("ejemploRegistroUsuarioServicio.xml");

		RegistroUsuarioXMLBean peticion = new RegistroUsuarioXMLBean();
		try {
			peticion.loadObjectFromXML(es.minhap.plataformamensajeria.iop.util.Utils.getStringFromInputStream(is));
		} catch (PlataformaBusinessException e) {
			logger.error("Se ha producido un error en testEnvioMensaje:", e);
		}
		String res = gestionServiciosPush.registroUsuarioEnServicio(peticion);

		logger.info("resultado -> " + res);
	}

//	@Test
	public final void testConsultaServiciosDisponibles() {

		if (!ApplicationContextProvider.getInstance().isLoaded()) {
			ApplicationContextProvider.getInstance().loadApplicationContext(applicationContext);
		}
		context = ApplicationContextProvider.getInstance();
		ps = new PropertiesServices(context);

		InputStream is = ClienteTest.class.getResourceAsStream("ejemploConsultaServiciosDisponibles.xml");

		ServiciosDisponiblesXMLBean peticion = new ServiciosDisponiblesXMLBean();
		try {
			peticion.loadObjectFromXML(es.minhap.plataformamensajeria.iop.util.Utils.getStringFromInputStream(is));
		} catch (PlataformaBusinessException e) {
			logger.error("Se ha producido un error en testEnvioMensaje:", e);
		}
		String res = gestionServiciosPush.consultaServiciosDisponibles(peticion);

		logger.info("resultado -> " + res);
	}
	
}
