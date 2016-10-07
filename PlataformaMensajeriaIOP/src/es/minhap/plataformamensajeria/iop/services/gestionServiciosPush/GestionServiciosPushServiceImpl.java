package es.minhap.plataformamensajeria.iop.services.gestionServiciosPush;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import es.minhap.plataformamensajeria.iop.beans.ConsultaEstadoBean;
import es.minhap.plataformamensajeria.iop.beans.RegistroUsuarioXMLBean;
import es.minhap.plataformamensajeria.iop.beans.ServiciosDisponiblesXMLBean;
import es.minhap.plataformamensajeria.iop.beans.respuestaSeguimiento.SeguimientoMensaje;
import es.minhap.plataformamensajeria.iop.beans.respuestaSeguimiento.Respuesta;
import es.minhap.plataformamensajeria.iop.beans.respuestasServiciosMoviles.RespuestaServiciosDisponibles;
import es.minhap.plataformamensajeria.iop.beans.respuestasServiciosMoviles.RespuestaServiciosRegistrarUsuario;
import es.minhap.plataformamensajeria.iop.beans.respuestasServiciosMoviles.ServicioMovil;
import es.minhap.plataformamensajeria.iop.jdbc.AplicacionDAO;
import es.minhap.plataformamensajeria.iop.jdbc.RegistroUsuariosServiciosMovilesDAO;
import es.minhap.plataformamensajeria.iop.jdbc.SeguimientoMensajesDAO;
import es.minhap.plataformamensajeria.iop.jdbc.ServiciosDisponiblesDAO;
import es.minhap.plataformamensajeria.iop.services.exceptions.PlataformaBusinessException;


public class GestionServiciosPushServiceImpl implements IGestionServiciosPushService {
	static Logger logger = Logger.getLogger(GestionServiciosPushServiceImpl.class);

	private static final Integer USUARIO_CORRECTO = 1;
	
	private static final String ACCION_ALTA_SERVICIO = "A";
	
	private static final String ACCION_BAJA_SERVICIO = "B";
	
	private static final String ACCION_CONSULTA_SERVICIO = "C";
	
	private static final String USUARIO_SERVICIO_NO_EXISTE = "-1";

	@Override
	public String registroUsuarioEnServicio(RegistroUsuarioXMLBean registroUsuarioXMLBean) {
		logger.debug("[GestionServiciosPushServiceImpl] Registro usuario en servicio");
		String xmlResultado = "";
		
		RespuestaServiciosRegistrarUsuario respuesta = new RespuestaServiciosRegistrarUsuario(); 
		AplicacionDAO aplicacionDao = new AplicacionDAO();
		RegistroUsuariosServiciosMovilesDAO dao = new RegistroUsuariosServiciosMovilesDAO();
		try {
			aplicacionDao.beginTransaction();
			Integer existeUsuario = aplicacionDao.loginUsuario(registroUsuarioXMLBean.getUsuario(), registroUsuarioXMLBean.getPassword());
			if(USUARIO_CORRECTO == existeUsuario) {
				dao.beginTransaction();
				List<String> users = dao.getIdUsersFromUserName(registroUsuarioXMLBean.getIdUsuario(), registroUsuarioXMLBean.getIdDispositivo());
				if (ACCION_ALTA_SERVICIO.equals(registroUsuarioXMLBean.getAccion())) {
					xmlResultado = insertUserSubscription(registroUsuarioXMLBean, respuesta, dao, users);
				} else if (ACCION_BAJA_SERVICIO.equals(registroUsuarioXMLBean.getAccion())) {
					xmlResultado = deleteUserSubscription(registroUsuarioXMLBean, respuesta, dao, users);
				} else if (ACCION_CONSULTA_SERVICIO.equals(registroUsuarioXMLBean.getAccion())) {
					xmlResultado = checkStatusUserSubscription(registroUsuarioXMLBean, respuesta, dao, users);
				} else {
					xmlResultado = respuesta.invalidActionXML();
				}
				dao.endTransaction(true);
				logger.debug("[GestionServiciosPushServiceImpl] Generando XML de la respuesta");
				logger.trace(xmlResultado);
			} else{
				xmlResultado = respuesta.loginIncorrectotoXML();
			}
			logger.trace(xmlResultado);
			logger.debug("[GestionServiciosPushServiceImpl] XML de respuesta generado");
		} catch (PlataformaBusinessException e) {
			dao.endTransaction(false);
			logger.error("[GestionServiciosPushServiceImpl] Se ha producido un error", e);
		} finally{
			dao.closeAll();
			aplicacionDao.endTransaction(true);
			aplicacionDao.closeAll();
		}
		return xmlResultado;
		
	}
	private String checkStatusUserSubscription(RegistroUsuarioXMLBean registroUsuarioXMLBean, RespuestaServiciosRegistrarUsuario respuesta,
			RegistroUsuariosServiciosMovilesDAO dao, List<String> users) throws PlataformaBusinessException {
		String xmlResultado;
		boolean checkMobileServiceId = dao.checkMobileServie(registroUsuarioXMLBean.getIdServicioMovil());
		if (checkMobileServiceId) {
			String estadoSuscripcion = USUARIO_SERVICIO_NO_EXISTE;
			if (!users.isEmpty())
				estadoSuscripcion = dao.checkSuscriptionStatus(users.get(0), registroUsuarioXMLBean.getIdServicioMovil());
			xmlResultado = respuesta.checkUserService(estadoSuscripcion);
		} else {
			xmlResultado = respuesta.invalidMobielServiceXML();
		}
		return xmlResultado;
	}
	private String deleteUserSubscription(RegistroUsuarioXMLBean registroUsuarioXMLBean, RespuestaServiciosRegistrarUsuario respuesta, RegistroUsuariosServiciosMovilesDAO dao,
			List<String> users) throws PlataformaBusinessException {
		String xmlResultado;
		boolean checkMobileServiceId = dao.checkMobileServie(registroUsuarioXMLBean.getIdServicioMovil());
		if (checkMobileServiceId) {
			boolean removeFromService = false;
			for (String user : users) {
				removeFromService = dao.updateSuscricionServicio(user, registroUsuarioXMLBean.getIdServicioMovil(), registroUsuarioXMLBean.getUsuario(), false);
			}
			xmlResultado = respuesta.toXML(removeFromService);
		} else {
			xmlResultado = respuesta.invalidMobielServiceXML();
		}
		return xmlResultado;
	}
	private String insertUserSubscription(RegistroUsuarioXMLBean registroUsuarioXMLBean, RespuestaServiciosRegistrarUsuario respuesta, RegistroUsuariosServiciosMovilesDAO dao,
			List<String> users) throws PlataformaBusinessException {
		String xmlResultado;
		boolean exists = dao.comprobarUsuarioServicio(users, registroUsuarioXMLBean.getIdServicioMovil()); 
		if (exists == false) {
			boolean checkMobileServiceId = dao.checkMobileServie(registroUsuarioXMLBean.getIdServicioMovil());
			if (checkMobileServiceId) {
				boolean insertIntoService = false;
				for (String user : users) {
					insertIntoService = dao.registraUsuarioServicio(user, registroUsuarioXMLBean.getIdServicioMovil(), registroUsuarioXMLBean.getUsuario());
				}
				xmlResultado = respuesta.toXML(insertIntoService);
			} else {
				xmlResultado = respuesta.invalidMobielServiceXML();
			}
		} else {
			boolean updateSubscription = false;
			for (String user : users) {
				updateSubscription = dao.updateSuscricionServicio(user, registroUsuarioXMLBean.getIdServicioMovil(), registroUsuarioXMLBean.getUsuario(), true);
			}
			xmlResultado = respuesta.userExistsXML(updateSubscription);
		}
		return xmlResultado;
	}
	@Override
	public String consultaServiciosDisponibles(ServiciosDisponiblesXMLBean servDispoXMLBean) {
		logger.debug("[GestionServiciosPushServiceImpl] Consultando los servicios disponibles");
		String xmlResultado = "";
		RespuestaServiciosDisponibles respuesta = new RespuestaServiciosDisponibles(); 
		AplicacionDAO aplicacionDao = new AplicacionDAO();
		ServiciosDisponiblesDAO dao = new ServiciosDisponiblesDAO();
		try {
			aplicacionDao.beginTransaction();
			Integer existeUsuario = aplicacionDao.loginUsuario(servDispoXMLBean.getUsuario(), servDispoXMLBean.getPassword());
	    	dao.beginTransaction();
			if(USUARIO_CORRECTO == existeUsuario) {
				List<ServicioMovil> serviciosMoviles = dao.consultarServiciosDisponibles(servDispoXMLBean.getIdUsuario());
				logger.debug("[GestionServiciosPushServiceImpl] Generando xml de respuesta");
				xmlResultado = respuesta.toXML(serviciosMoviles);
				logger.trace(xmlResultado);
			} else{
				xmlResultado = respuesta.loginIncorrectotoXML();
			}
			logger.trace(xmlResultado);
			logger.debug("[GestionServiciosPushServiceImpl] XML de respuesta generado");
		} catch (PlataformaBusinessException e) {
			e.printStackTrace();
		} finally{
			aplicacionDao.endTransaction(true);
			aplicacionDao.closeAll();
			dao.endTransaction(true);
			dao.closeAll();
		}
		return xmlResultado;
	}

	
	
	@Override
	public String registroUsuarioEnServicio(Integer servicioId, Integer canalId, Integer aplicacionId,
				Integer loteId, Integer idMensaje, String idExterno, Integer estadoId,
				String fechaDesde, String fechaHasta, String usuario, String password) {
		logger.debug("[RegistroUsuarioEnServicioImpl] Consultando estado de ServicioID "+ servicioId+ ", MensajeID "+idMensaje);
		String xmlResultado = "";
		SeguimientoMensajesDAO dao = new SeguimientoMensajesDAO();
		ArrayList<SeguimientoMensaje> listaResultados = new ArrayList<SeguimientoMensaje>();
		logger.debug("[RegistroUsuarioEnServicioImpl] Iniciando transaccion");
		dao.beginTransaction();
		Date fDesde = null;
		Date fHasta = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try{
			if(fechaDesde!=null&&!fechaDesde.isEmpty()){
				fDesde = sdf.parse(fechaDesde);
			}
		} catch (ParseException e) {
			fDesde=null;
		}
		try{
			if(fechaHasta!=null&&!fechaHasta.isEmpty()){
				fHasta = sdf.parse(fechaHasta);
			}
		} catch (ParseException e) {
			fHasta=null;
		}				
		logger.debug("[RegistroUsuarioEnServicioImpl] Antes de consultarEstado");
			listaResultados = dao.consultarEstado(servicioId,canalId,aplicacionId,loteId,
					idMensaje,idExterno,estadoId,fDesde,fHasta,usuario,password);
			logger.debug("[RegistroUsuarioEnServicioImpl] Despues de registroUsuarioEnServicio");
			Respuesta respuesta = new  Respuesta();
			try {
				logger.debug("[RegistroUsuarioEnServicioImpl] Generando XML de respuesta");
				xmlResultado = respuesta.toXMLEstado(listaResultados);
				logger.trace(xmlResultado);
				logger.debug("[RegistroUsuarioEnServicioImpl] XML de respuesta generado");
			} catch (PlataformaBusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		dao.endTransaction(false);
		dao.closeAll();
		return xmlResultado;
	}
	@Override
	public String consultaServiciosDisponibles(Integer servicioId, Integer canalId, Integer aplicacionId,
				Integer loteId, Integer idMensaje, String idExterno, Integer estadoId,
				String fechaDesde, String fechaHasta, String usuario, String password) {
		logger.debug("[RegistroUsuarioEnServicioImpl] Consultando estado de ServicioID "+ servicioId+ ", MensajeID "+idMensaje);
		String xmlResultado = "";
		SeguimientoMensajesDAO dao = new SeguimientoMensajesDAO();
		ArrayList<SeguimientoMensaje> listaResultados = new ArrayList<SeguimientoMensaje>();
		logger.debug("[RegistroUsuarioEnServicioImpl] Iniciando transaccion");
		dao.beginTransaction();
		Date fDesde = null;
		Date fHasta = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try{
			if(fechaDesde!=null&&!fechaDesde.isEmpty()){
				fDesde = sdf.parse(fechaDesde);
			}
		} catch (ParseException e) {
			fDesde=null;
		}
		try{
			if(fechaHasta!=null&&!fechaHasta.isEmpty()){
				fHasta = sdf.parse(fechaHasta);
			}
		} catch (ParseException e) {
			fHasta=null;
		}				
		logger.debug("[GestionServiciosPushServiceImpl] Antes de consultaServiciosDisponibles");
			listaResultados = dao.consultarEstado(servicioId,canalId,aplicacionId,loteId,
					idMensaje,idExterno,estadoId,fDesde,fHasta,usuario,password);
			logger.debug("[RegistroUsuarioEnServicioImpl] Despues de consultarEstado");
			Respuesta respuesta = new  Respuesta();
			try {
				logger.debug("[GestionServiciosPushServiceImpl] Generando XML de respuesta");
				xmlResultado = respuesta.toXMLEstado(listaResultados);
				logger.trace(xmlResultado);
				logger.debug("[GestionServiciosPushServiceImpl] XML de respuesta generado");
			} catch (PlataformaBusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		dao.endTransaction(false);
		dao.closeAll();
		return xmlResultado;
	}
	
	
	@Override
	public String consultaServiciosDisponibles(ConsultaEstadoBean consultaEstado) {
	
		String xmlResultado = "";
		SeguimientoMensajesDAO dao = new SeguimientoMensajesDAO();
		ArrayList<SeguimientoMensaje> listaResultados = new ArrayList<SeguimientoMensaje>();
		logger.debug("[GestionServiciosPushServiceImpl] Iniciando transaccion");
		dao.beginTransaction();
		Date fDesde = null;
		Date fHasta = null;
		int idServicio=0,idCanal=0,idAplicacion=0,idLote=0,idMensaje=0,idEstado=0;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		try{
			if(consultaEstado.getIdServicio()!=null&&!consultaEstado.getIdServicio().isEmpty()){
				idServicio = Integer.parseInt(consultaEstado.getIdServicio());
			}
		} catch (NumberFormatException e) {
			idServicio=0;
		}
		try{
			if(consultaEstado.getCanal()!=null&&!consultaEstado.getCanal().isEmpty()){
				idCanal = Integer.parseInt(consultaEstado.getCanal());
			}
		} catch (NumberFormatException e) {
			idCanal=0;
		}
		try{
			if(consultaEstado.getIdAplicacion()!=null&&!consultaEstado.getIdAplicacion().isEmpty()){
				idAplicacion = Integer.parseInt(consultaEstado.getIdAplicacion());
			}
		} catch (NumberFormatException e) {
			idAplicacion=0;
		}
		try{
			if(consultaEstado.getLote()!=null&&!consultaEstado.getLote().isEmpty()){
				idLote = Integer.parseInt(consultaEstado.getLote());
			}
		} catch (NumberFormatException e) {
			idLote=0;
		}
		try{
			if(consultaEstado.getIdMensaje()!=null&&!consultaEstado.getIdMensaje().isEmpty()){
				idMensaje = Integer.parseInt(consultaEstado.getIdMensaje());
			}
		} catch (NumberFormatException e) {
			idMensaje=0;
		}
		try{
			if(consultaEstado.getEstado()!=null&&!consultaEstado.getEstado().isEmpty()){
				idEstado = Integer.parseInt(consultaEstado.getEstado());
			}
		} catch (NumberFormatException e) {
			idEstado=0;
		}
		try{
			if(consultaEstado.getFechaDesde()!=null&&!consultaEstado.getFechaDesde().isEmpty()){
				fDesde = sdf.parse(consultaEstado.getFechaDesde());
			}
		} catch (ParseException e) {
			fDesde=null;
		}
		try{
			if(consultaEstado.getFechaHasta()!=null&&!consultaEstado.getFechaHasta().isEmpty()){
				fHasta = sdf.parse(consultaEstado.getFechaHasta());
			}
		} catch (ParseException e) {
			fHasta=null;
		}				
		logger.debug("[GestionServiciosPushServiceImpl] Antes de consultaServiciosDisponibles");
			listaResultados = dao.consultarEstado(idServicio,idCanal,
					idCanal,
					idLote,
					idMensaje,consultaEstado.getIdExterno(),idEstado,fDesde,fHasta,
					consultaEstado.getUsuario(),consultaEstado.getPassword());
			logger.debug("[GestionServiciosPushServiceImpl] Despues de consultarEstado");
			Respuesta respuesta = new  Respuesta();
			try {
				logger.debug("[GestionServiciosPushServiceImpl] Generando XML de respuesta");
				xmlResultado = respuesta.toXMLEstado(listaResultados);
				logger.trace(xmlResultado);
				logger.debug("[GestionServiciosPushServiceImpl] XML de respuesta generado");
			} catch (PlataformaBusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		dao.endTransaction(false);
		dao.closeAll();
		return xmlResultado;
	}
	
	
	
}
