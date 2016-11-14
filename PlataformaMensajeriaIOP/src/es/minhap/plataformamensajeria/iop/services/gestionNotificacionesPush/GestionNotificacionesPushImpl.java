package es.minhap.plataformamensajeria.iop.services.gestionNotificacionesPush;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import es.minhap.common.properties.PropertiesServices;
import es.minhap.plataformamensajeria.iop.beans.NotificacionesPushXMLBean;
import es.minhap.plataformamensajeria.iop.beans.getAvisosUsuario.Aviso;
import es.minhap.plataformamensajeria.iop.beans.getAvisosUsuario.Avisos;
import es.minhap.plataformamensajeria.iop.beans.getAvisosUsuario.PeticionGetAvisosUsuario;
import es.minhap.plataformamensajeria.iop.beans.getAvisosUsuario.ResponseStatusType;
import es.minhap.plataformamensajeria.iop.beans.getAvisosUsuario.RespuestaGetAvisosUsuario;
import es.minhap.plataformamensajeria.iop.beans.respuestasServiciosMoviles.RespuestaNotificacionesPush;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorEstados;
import es.minhap.plataformamensajeria.iop.manager.TblAplicacionesManager;
import es.minhap.plataformamensajeria.iop.manager.TblMensajesManager;
import es.minhap.plataformamensajeria.iop.manager.TblUsuariosPushManager;
import es.minhap.plataformamensajeria.iop.services.exceptions.PlataformaBusinessException;
import es.minhap.sim.model.TblMensajes;

/**
 * 
 * @author everis
 *
 */
@Service("gestionNotificacionesPushImpl")
public class GestionNotificacionesPushImpl implements IGestionNotificacionesPushService {
	private final static Logger LOG = Logger.getLogger(GestionNotificacionesPushImpl.class);

	@Resource
	private TblAplicacionesManager aplicacionesManager;

	@Resource
	private TblMensajesManager mensajesManager;
		
	@Resource
	private TblUsuariosPushManager usuariosPushManager;

	@Autowired
	private QueryExecutorEstados queryExecutorEstados;

	@Resource(name = "reloadableResourceBundleMessageSource")
	private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;

	String estadoEnviado;
	String estadoRecibido;
	String estadoLeido;
	String codeLeido;
	String codeRecibido;
		
	@Override
	public String notificacionCambioEstado(NotificacionesPushXMLBean notificacionesPushXMLBean) {
		LOG.debug("[GestionNotificacionesPush] Notificacion cambio de estado de un mensaje");
		PropertiesServices ps = new PropertiesServices(getReloadableResourceBundleMessageSource());
		estadoEnviado = ps.getMessage("constantes.ESTADO_ENVIADO", null);
		estadoRecibido = ps.getMessage("constantes.ESTADO_RECIBIDO", null);
		estadoLeido = ps.getMessage("constantes.ESTADO_LEIDO", null);
		codeLeido = ps.getMessage("plataformaErrores.gestionNotificacionesPush.CODE_LEIDO", null);
		codeRecibido = ps.getMessage("plataformaErrores.gestionNotificacionesPush.CODE_RECIBIDO", null);
		String notificacion = ps.getMessage("constantes.TIPO_MENSAJE_PUSH", null);
		
		String xmlResultado = "";
		RespuestaNotificacionesPush respuesta = new RespuestaNotificacionesPush(); 

		try {
			if (datosNoValidos(notificacionesPushXMLBean)){
				return respuesta.peticionIncorrectaXML(ps);
			}
			
			Boolean existeUsuario = getAplicacionesManager().existeAplicacion(notificacionesPushXMLBean.getUsuario(), notificacionesPushXMLBean.getPassword());
			
			if(existeUsuario) {
		    	List<String> usersId = getUsuariosPushManager().getIdUsersFromDispositivo(notificacionesPushXMLBean.getIdUsuario(), null);
		    	if (null == usersId || usersId.isEmpty()){
		    		return respuesta.noExisteUsuarioPush(ps);
		    	}
		    	if (notificacionesPushXMLBean.getNotificacionId()==null || notificacionesPushXMLBean.getNotificacionId().isEmpty()){
		    		//actualizamos todos los mensajes del usuario.
		    		xmlResultado = actualizaTodosMensajes(notificacionesPushXMLBean, respuesta, usersId, ps);	
		    	}else{
		    		Long mensajeId = Long.parseLong(notificacionesPushXMLBean.getNotificacionId());
		    		xmlResultado = actualizaUnMensaje(notificacionesPushXMLBean, ps, notificacion, respuesta, mensajeId, usersId);
		    	}
				
				LOG.trace(xmlResultado);
			} else{
				xmlResultado = respuesta.loginIncorrectotoXML(ps);
			}
			LOG.trace(xmlResultado);
			LOG.debug("[GestionNotificacionesPush] XML de respuesta generado");
		} catch (PlataformaBusinessException e) {
			LOG.error("[GestionNotificacionesPush] Gestionando Notificaciones Push",e);
		} 
		return xmlResultado;
	}
	
	@Override
	public String getAvisosUsuarioPush(PeticionGetAvisosUsuario peticion) {
		RespuestaGetAvisosUsuario respuesta = new RespuestaGetAvisosUsuario();
		PropertiesServices ps = new PropertiesServices(getReloadableResourceBundleMessageSource());
		String statusTextKO = ps.getMessage("plataformaErrores.gestionNotificacionesPush.STATUSTEXT_KO", null);
		String codErrorPeticion = ps.getMessage(
				"plataformaErrores.gestionNotificacionesPush.COD_ERROR_PETICION_GET_AVISOS", null);
		String detailsErrorPeticion = ps.getMessage(
				"plataformaErrores.gestionNotificacionesPush.DETAILS_ERROR_PETICION_GET_AVISOS", null);
		String statusTextOK = ps.getMessage("plataformaErrores.gestionNotificacionesPush.STATUSTEXT_OK", null);
		String statusCodeOK = ps.getMessage("plataformaErrores.gestionNotificacionesPush.STATUSCODE_OK", null);
		String detailsOK = ps.getMessage("plataformaErrores.gestionNotificacionesPush.DETAILS_OK", null);
		String codErrorDispositivo = ps.getMessage("plataformaErrores.gestionNotificacionesPush.COD_ERROR_DISPOSITIVO",
				null);
		String detailsErrorDispositivo = ps.getMessage(
				"plataformaErrores.gestionNotificacionesPush.DETAILS_ERROR_DISPOSITIVO", null);
		String codErrorServicio = ps.getMessage("plataformaErrores.gestionNotificacionesPush.COD_SERVICIO_INCORRECTO",
				null);
		String detailsErrorServicio = ps.getMessage(
				"plataformaErrores.gestionNotificacionesPush.ERROR_SERVICIO_INCORRECTO", null);
		String codErrorGeneral = ps.getMessage("plataformaErrores.gestionNotificacionesPush.COD_ERROR_GENERAL", null);
		String detailsErrorGeneral = ps.getMessage("plataformaErrores.gestionNotificacionesPush.DETAILS_ERROR_GENERAL",
				null);

		String res = "";
		try {
			String errorValidacion = comprobarDatos(peticion, ps);
			if (null != errorValidacion) {
				respuesta = generarRespuesta(statusTextKO, codErrorPeticion, detailsErrorPeticion + errorValidacion,
						null);
			} else {
				// comprobamos aplicacion
				Boolean existeUsuario = getAplicacionesManager().existeAplicacion(peticion.getUsuario(),
						peticion.getPassword());

				if (existeUsuario) {
					respuesta = getListaAvisos(peticion, ps, statusTextKO, statusTextOK, statusCodeOK, detailsOK,
							codErrorDispositivo, detailsErrorDispositivo);

				} else {// no es un usuario correcto
					respuesta = generarRespuesta(statusTextKO, codErrorServicio, detailsErrorServicio, null);
				}
			}
			res = respuesta.toXMLSMS(respuesta);
		} catch (Exception e) {
			LOG.error("[GestionNotificacionesPushImpl.getAvisosUsuarioPush] Obteniendo avisos Usuario", e);
			respuesta = generarRespuesta(statusTextKO, codErrorGeneral, detailsErrorGeneral, null);
			try {
				return respuesta.toXMLSMS(respuesta);
			} catch (PlataformaBusinessException e1) {
				LOG.error("[GestionNotificacionesPushImpl.getAvisosUsuarioPush] Obteniendo avisos Usuario", e1);
			}
		} 
		return res;
	}

	private RespuestaGetAvisosUsuario getListaAvisos(PeticionGetAvisosUsuario peticion, PropertiesServices ps,
			String statusTextKO, String statusTextOK, String statusCodeOK, String detailsOK,
			String codErrorDispositivo, String detailsErrorDispositivo) {
		RespuestaGetAvisosUsuario respuesta;
		// comprobamos si existe el usuario con sus datos y docUsuari
		boolean exits = getUsuariosPushManager().comprobarExisteDispositivo(peticion.getIdServicio(), peticion.getIdDispositivo(),
				peticion.getIdPlataforma(), peticion.getIdUsuario());
		if (exits) {
			// /aqui es donde se recuperan los mensajes
			List<Aviso> listaAvisos = getMensajesManager().getAvisosUsuario(peticion.getIdDispositivo(),
					peticion.getIdPlataforma(), peticion.getIdServicio(), peticion.getIdUsuario(),
					peticion.getNumPagina(), peticion.getTamPagina(), ps);
			respuesta = generarRespuesta(statusTextOK, statusCodeOK, detailsOK, listaAvisos);
		} else
			respuesta = generarRespuesta(statusTextKO, codErrorDispositivo, detailsErrorDispositivo, null);
		return respuesta;
	}

	private String actualizaUnMensaje(NotificacionesPushXMLBean notificacionesPushXMLBean, PropertiesServices ps,
			String notificacion, RespuestaNotificacionesPush respuesta, Long mensajeId, List<String> usersId)
			throws PlataformaBusinessException {
		String xmlResultado;
		String statusMessage;
		TblMensajes mensaje = getMensajesManager().getMensaje(mensajeId);
		
		if (getMensajesManager().getMultidestinatarioByMensaje(mensajeId))
			statusMessage = getQueryExecutorEstados().getEstadoByMensajeIdUsuarioPush(mensajeId, usersId);
		else{
			statusMessage = mensaje.getEstadoactual();
		}
		LOG.debug("[GestionNotificacionesPush] Generando XML de respuesta");
		boolean pushNotification = notificacion.equalsIgnoreCase(getMensajesManager().getMensaje(mensajeId).getTipomensaje()) ? true : false;
		
		if (pushNotification) {
			if (getMensajesManager().isMessageUser(usersId, mensajeId)) {
				xmlResultado = actualizaMensaje(notificacionesPushXMLBean, ps, codeLeido, codeRecibido, respuesta, usersId, statusMessage);
			} else {
				xmlResultado = respuesta.incorrectUserXML(ps);
			}
		} else {
			xmlResultado = respuesta.notPushXML(ps);
		}
		return xmlResultado;
	}

	private String actualizaMensaje(NotificacionesPushXMLBean notificacionesPushXMLBean, PropertiesServices ps,
			String codeLeido, String codeRecibido,
			RespuestaNotificacionesPush respuesta, List<String> usersId, String statusMessage)
			throws PlataformaBusinessException {
		String xmlResultado;
		if ((estadoEnviado.equals(statusMessage) && (codeRecibido.equals(notificacionesPushXMLBean.getStatus())))
				|| (estadoRecibido.equals(statusMessage) && (codeLeido.equals(notificacionesPushXMLBean.getStatus())))) {
		String estadoInicial = getEstadoInicial(notificacionesPushXMLBean, ps);
		String estadoFinal = getEstadoFinal(notificacionesPushXMLBean, ps);
		int resultado = getMensajesManager().updateMessagesUsers(usersId, estadoInicial, estadoFinal, notificacionesPushXMLBean.getUsuario());
		xmlResultado = respuesta.toXML(resultado,ps);
		
		}else {
			xmlResultado = respuesta.incorrectStatusXML(ps);
		}
		return xmlResultado;
	}

	private String getEstadoFinal(NotificacionesPushXMLBean notificacionesPushXMLBean, PropertiesServices ps) {
		String res;
		int valor = Integer.parseInt(notificacionesPushXMLBean.getStatus());
		switch (valor) {
		case 1:
			res = ps.getMessage("constantes.ESTADO_RECIBIDO", null);
			break;
		case 2:
			res = ps.getMessage("constantes.ESTADO_LEIDO", null);
			break;
		default:
			res = ps.getMessage("constantes.ESTADO_ENVIADO", null);
			break;
		}
		return res;
	}
	private String getEstadoInicial(NotificacionesPushXMLBean notificacionesPushXMLBean, PropertiesServices ps) {
		String res;
		int valor = Integer.parseInt(notificacionesPushXMLBean.getStatus());
		switch (valor) {
		case 1:
			res = ps.getMessage("constantes.ESTADO_ENVIADO", null);
			break;
		case 2:
			res = ps.getMessage("constantes.ESTADO_RECIBIDO", null);
			break;
		default:
			res = null;
			break;
		}
		return res;
	}

	private String actualizaTodosMensajes(NotificacionesPushXMLBean notificacionesPushXMLBean,
			RespuestaNotificacionesPush respuesta, List<String> usersId, PropertiesServices ps)
			throws PlataformaBusinessException {
		String xmlResultado;
		int resultado = getMensajesManager().updateMessagesUsers(usersId, estadoEnviado, estadoRecibido,
				notificacionesPushXMLBean.getUsuario());

		if (resultado > 0) {
			resultado = getMensajesManager().updateMessagesUsers(usersId, estadoRecibido, estadoLeido,
					notificacionesPushXMLBean.getUsuario());
			xmlResultado = respuesta.actualizarTodosMensajes(resultado,ps);
		} else
			xmlResultado = respuesta.actualizarTodosMensajes(resultado,ps);
		return xmlResultado;
	}

	private boolean datosNoValidos(NotificacionesPushXMLBean np) {
		boolean res = false;
		if (null != np.getNotificacionId() && null == np.getStatus()) {
				return true;
		}
		return res;
	}

	

	private String comprobarDatos(PeticionGetAvisosUsuario peticion, PropertiesServices ps) {
		String res = null;
		String errorDatosObligatorios = ps.getMessage(
				"plataformaErrores.gestionNotificacionesPush.ERROR_DATOS_OBLIGATORIOS", null);
		String tamanoPaginaDefecto = ps.getMessage("plataformaErrores.gestionNotificacionesPush.tamanoPaginaDefecto",
				null);
		String errorTamPagina = ps.getMessage("plataformaErrores.gestionNotificacionesPush.ERROR_TAMPAG", null);
		String numPaginaDefecto = ps
				.getMessage("plataformaErrores.gestionNotificacionesPush.numeroPaginaDefecto", null);
		String errorNumPagina = ps.getMessage("plataformaErrores.gestionNotificacionesPush.ERROR_NUMPAG", null);
		String errorNoNumerico = ps.getMessage("plataformaErrores.gestionNotificacionesPush.ERROR_NO_NUMERICO", null);

		if (checkUsuarioPassword(peticion.getUsuario(), peticion.getPassword()) || null == peticion.getIdServicio()
				|| peticion.getIdServicio().length() <= 0
				|| checkDispositivoPlataforma(peticion.getIdDispositivo(), peticion.getIdPlataforma()))
			return errorDatosObligatorios;
		try {
			if (null != peticion.getNumPagina()) {
				int num = Integer.parseInt(peticion.getNumPagina());
				res = setTamanos(peticion, tamanoPaginaDefecto, errorTamPagina, numPaginaDefecto, errorNumPagina, num);
			} else {
				peticion.setNumPagina(numPaginaDefecto);
				peticion.setTamPagina(tamanoPaginaDefecto);
			}

		} catch (NumberFormatException e) {
			return errorNoNumerico;
		}
		return res;
	}

	private String setTamanos(PeticionGetAvisosUsuario peticion, String tamanoPaginaDefecto, String errorTamPagina,
			String numPaginaDefecto, String errorNumPagina, int num) {
		if (num > 0) {
			if (null != peticion.getTamPagina()) {
				int tam = Integer.parseInt(peticion.getTamPagina());
				if (tam == 0)
					peticion.setTamPagina(tamanoPaginaDefecto);
				else if (tam < 0)
					return errorTamPagina;
			} else
				peticion.setTamPagina(tamanoPaginaDefecto);
		} else if (num < 0)
			return errorNumPagina;
		else {
			peticion.setNumPagina(numPaginaDefecto);
			peticion.setTamPagina(tamanoPaginaDefecto);
		}
		return null;
	}

	private boolean checkUsuarioPassword(String usuario, String password) {
		return (checkUsuario(usuario) || null == password || password.length() <= 0) ? true : false;
	}

	private boolean checkUsuario(String usuario) {
		return (null == usuario || usuario.length() <= 0) ? true : false;
	}

	private boolean checkDispositivoPlataforma(String dispositivo, String palataforma) {
		return (checkDispositivo(dispositivo) || null == palataforma || palataforma.length() <= 0) ? true : false;
	}

	private boolean checkDispositivo(String dispositivo) {
		return (null == dispositivo || dispositivo.length() <= 0) ? true : false;
	}

	private RespuestaGetAvisosUsuario generarRespuesta(String statustext, String codigo, String details,
			List<Aviso> listaAvisos) {
		RespuestaGetAvisosUsuario res = new RespuestaGetAvisosUsuario();
		ResponseStatusType status = new ResponseStatusType();

		status.setDetails(details);
		status.setStatusCode(codigo);
		status.setStatusText(statustext);

		if (null != listaAvisos) {
			Avisos avisos = new Avisos();
			for (Aviso aviso : listaAvisos) {
				avisos.getAviso().add(aviso);
			}
			res.setAvisos(avisos);
		}

		res.setStatus(status);

		return res;
	}

	/**
	 * @return the aplicacionesManager
	 */
	public TblAplicacionesManager getAplicacionesManager() {
		return aplicacionesManager;
	}

	/**
	 * @param aplicacionesManager the aplicacionesManager to set
	 */
	public void setAplicacionesManager(TblAplicacionesManager aplicacionesManager) {
		this.aplicacionesManager = aplicacionesManager;
	}

	/**
	 * @return the mensajesManager
	 */
	public TblMensajesManager getMensajesManager() {
		return mensajesManager;
	}

	/**
	 * @param mensajesManager the mensajesManager to set
	 */
	public void setMensajesManager(TblMensajesManager mensajesManager) {
		this.mensajesManager = mensajesManager;
	}

	/**
	 * @return the usuariosPushManager
	 */
	public TblUsuariosPushManager getUsuariosPushManager() {
		return usuariosPushManager;
	}

	/**
	 * @param usuariosPushManager the usuariosPushManager to set
	 */
	public void setUsuariosPushManager(TblUsuariosPushManager usuariosPushManager) {
		this.usuariosPushManager = usuariosPushManager;
	}

	/**
	 * @return the queryExecutorEstados
	 */
	public QueryExecutorEstados getQueryExecutorEstados() {
		return queryExecutorEstados;
	}

	/**
	 * @param queryExecutorEstados the queryExecutorEstados to set
	 */
	public void setQueryExecutorEstados(QueryExecutorEstados queryExecutorEstados) {
		this.queryExecutorEstados = queryExecutorEstados;
	}

	/**
	 * @return the reloadableResourceBundleMessageSource
	 */
	public ReloadableResourceBundleMessageSource getReloadableResourceBundleMessageSource() {
		return reloadableResourceBundleMessageSource;
	}

	/**
	 * @param reloadableResourceBundleMessageSource the reloadableResourceBundleMessageSource to set
	 */
	public void setReloadableResourceBundleMessageSource(ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource) {
		this.reloadableResourceBundleMessageSource = reloadableResourceBundleMessageSource;
	}

}
