package es.minhap.plataformamensajeria.iop.services.gestionNotificacionesPush;

import java.util.List;

import org.apache.log4j.Logger;

import es.minhap.plataformamensajeria.iop.beans.NotificacionesPushXMLBean;
import es.minhap.plataformamensajeria.iop.beans.getAvisosUsuario.Aviso;
import es.minhap.plataformamensajeria.iop.beans.getAvisosUsuario.Avisos;
import es.minhap.plataformamensajeria.iop.beans.getAvisosUsuario.PeticionGetAvisosUsuario;
import es.minhap.plataformamensajeria.iop.beans.getAvisosUsuario.ResponseStatusType;
import es.minhap.plataformamensajeria.iop.beans.getAvisosUsuario.RespuestaGetAvisosUsuario;
import es.minhap.plataformamensajeria.iop.beans.respuestasServiciosMoviles.RespuestaNotificacionesPush;
import es.minhap.plataformamensajeria.iop.jdbc.AplicacionDAO;
import es.minhap.plataformamensajeria.iop.jdbc.MensajeDAO;
import es.minhap.plataformamensajeria.iop.jdbc.UsuariosPushDAO;
import es.minhap.plataformamensajeria.iop.services.exceptions.PlataformaBusinessException;
import es.minhap.plataformamensajeria.iop.util.PlataformaErrores;


public class GestionNotificacionesPushImpl implements IGestionNotificacionesPushService {
	static Logger logger = Logger.getLogger(GestionNotificacionesPushImpl.class);

	private static final Integer USUARIO_CORRECTO = 1;
	
	private static final String STATUS_ENVIADO = "ENVIADO";
	private static final String STATUS_RECIBIDO = "RECIBIDO";
	private static final String STATUS_LEIDO = "LEIDO";
	private static final String CODE_RECIBIDO = "1";
	private static final String CODE_LEIDO = "2";
	private static final String ERROR_DATOS_OBLIGATORIOS = ": Servicio, Plataforma o Dispositivo";
	private static final String ERROR_NUMPAG = "Numero de pagina incorrecto";
	private static final String ERROR_TAMPAG = "Tamano de pagina incorrecto";
	private static final String ERROR_NO_NUMERICO = "El numero de pagina o el tamano de pagina no son numericos";
	
	
	private static final String tamanoPaginaDefecto = "10";
	private static final String numeroPaginaDefecto = "0";
		
	@Override
	public String notificacionCambioEstado(NotificacionesPushXMLBean notificacionesPushXMLBean) {
		logger.debug("[GestionNotificacionesPush] Notificacion cambio de estado de un mensaje");
		String xmlResultado = "";
		RespuestaNotificacionesPush respuesta = new RespuestaNotificacionesPush(); 
		MensajeDAO dao = new MensajeDAO();
		try {
			if (DatosNoValidos(notificacionesPushXMLBean)){
				return xmlResultado = respuesta.peticionIncorrectaXML();
			}
			
			AplicacionDAO aplicacionDao = new AplicacionDAO();
			aplicacionDao.beginTransaction();
			Integer existeUsuario = aplicacionDao.loginUsuario(notificacionesPushXMLBean.getUsuario(), notificacionesPushXMLBean.getPassword());
			aplicacionDao.endTransaction(true);
			aplicacionDao.closeAll();
			if(USUARIO_CORRECTO == existeUsuario) {
		    	dao.beginTransaction();
		    	List<String> usersId = dao.getIdUsersFromUserName(notificacionesPushXMLBean.getIdUsuario());
		    	dao.endTransaction(true);
		    	dao.closeAll();
		    	if (null == usersId || usersId.size() <= 0){
		    		return xmlResultado = respuesta.noExisteUsuarioPush();
		    	}
		    	if (notificacionesPushXMLBean.getNotificacionId()==null || notificacionesPushXMLBean.getNotificacionId().isEmpty()){
		    		//actualizamos todos los mensajes del usuario.
		    		dao.beginTransaction();
		    		int resultado = dao.updateMessagesUsers(usersId, STATUS_ENVIADO,STATUS_RECIBIDO);
		    		dao.endTransaction(true);
		    		dao.closeAll();
		    		if (resultado > 0){
		    			dao.beginTransaction();
		    			resultado = dao.updateMessagesUsers(usersId, STATUS_RECIBIDO,STATUS_LEIDO);
		    			dao.endTransaction(true);
			    		dao.closeAll();
		    			xmlResultado = respuesta.actualizarTodosMensajes(resultado);
		    		}else
		    			xmlResultado = respuesta.actualizarTodosMensajes(resultado);
		    		
		    	}else{
		    		dao.beginTransaction();
		    		String statusMessage = dao.getStatusMessage(notificacionesPushXMLBean.getNotificacionId(), usersId);
					logger.debug("[GestionNotificacionesPush] Generando XML de respuesta");
								
					boolean pushNotification = dao.checkIsPushNotification(notificacionesPushXMLBean.getNotificacionId());
					if (pushNotification) {
						if (dao.isMessageUser(usersId, notificacionesPushXMLBean.getNotificacionId())) {
							if ((STATUS_ENVIADO.equals(statusMessage) && (CODE_RECIBIDO.equals(notificacionesPushXMLBean.getStatus())))
									|| (STATUS_RECIBIDO.equals(statusMessage) && (CODE_LEIDO.equals(notificacionesPushXMLBean.getStatus())))) {
							
							int resultado = dao.updateMessageStatus(notificacionesPushXMLBean.getNotificacionId(), notificacionesPushXMLBean.getStatus(), usersId);
							xmlResultado = respuesta.toXML(resultado);
							
							}else {
								xmlResultado = respuesta.incorrectStatusXML();
							}
						} else {
							xmlResultado = respuesta.incorrectUserXML();
						}
					} else {
						xmlResultado = respuesta.notPushXML();
					}
					dao.endTransaction(true);
		    	}
				
				logger.trace(xmlResultado);
			} else{
				xmlResultado = respuesta.loginIncorrectotoXML();
			}
			logger.trace(xmlResultado);
			logger.debug("[GestionNotificacionesPush] XML de respuesta generado");
		} catch (PlataformaBusinessException e) {
			dao.endTransaction(false);
			logger.error("[GestionNotificacionesPush] Gestionando Notificaciones Push");
		} finally{
			dao.closeAll();
		}
		return xmlResultado;
	}

	private boolean DatosNoValidos(NotificacionesPushXMLBean np) {
		boolean res = false;
		if (null != np.getNotificacionId()){
			if (null == np.getStatus())
				return true;
		}
		return res;
	}

	@Override
	public String getAvisosUsuarioPush(PeticionGetAvisosUsuario peticion) {
		UsuariosPushDAO dao = new UsuariosPushDAO();
		AplicacionDAO aplicacionDao = new AplicacionDAO();
		RespuestaGetAvisosUsuario respuesta = new RespuestaGetAvisosUsuario();
		
		String res = "";
		try {
			String errorValidacion = datosNoValidos(peticion);
			if (null != errorValidacion) {
				respuesta = generarRespuesta(PlataformaErrores.STATUSTEXT_KO, PlataformaErrores.COD_ERROR_PETICION_GET_AVISOS, PlataformaErrores.DETAILS_ERROR_PETICION_GET_AVISOS + errorValidacion, null);
			} else {
				// comprobamos aplicacion
				aplicacionDao.beginTransaction();
				Integer existeUsuario = aplicacionDao.loginUsuario(peticion.getUsuario(), peticion.getPassword());
				aplicacionDao.endTransaction(true);
				if (USUARIO_CORRECTO == existeUsuario) {
					dao.beginTransaction();
					// comprobamos si existe el usuario con sus datos y docUsuari
					boolean exits = dao.existeUsuario(peticion.getIdServicio(),peticion.getIdDispositivo(),peticion.getIdPlataforma(),peticion.getIdUsuario());
					if (exits){
						///aki es donde se recuperan los mensajes
						List<Aviso> listaAvisos = dao.getAvisosUsuario(peticion.getIdDispositivo(),
								peticion.getIdPlataforma(), peticion.getIdServicio(), 
								peticion.getIdUsuario(), peticion.getNumPagina(),peticion.getTamPagina());
						respuesta = generarRespuesta(PlataformaErrores.STATUSTEXT_OK, PlataformaErrores.STATUS_OK,
								PlataformaErrores.DETAILS_OK,listaAvisos);
					}else
						respuesta = generarRespuesta(PlataformaErrores.STATUSTEXT_KO, PlataformaErrores.COD_ERROR_DISPOSITIVO,
								PlataformaErrores.DETAILS_ERROR_DISPOSITIVO,null);
					

					dao.endTransaction(true);
				} else {// no es un usuario correcto
					respuesta = generarRespuesta(PlataformaErrores.STATUSTEXT_KO, PlataformaErrores.COD_SERVICIO_INCORRECTO, PlataformaErrores.ERROR_SERVICIO_INCORRECTO, null);
				}
			}
			res = respuesta.toXMLSMS(respuesta);
		} catch (Exception e) {
			dao.endTransaction(false);
			aplicacionDao.endTransaction(false);
			// e.printStackTrace();
			respuesta = generarRespuesta(PlataformaErrores.STATUSTEXT_KO, PlataformaErrores.COD_ERROR_GENERAL, PlataformaErrores.DETAILS_ERROR_GENERAL, null);
		} finally {
			dao.closeAll();
			aplicacionDao.closeAll();
		}

		return res;
	}
	
	private String datosNoValidos(PeticionGetAvisosUsuario peticion) {
		String res = null;

		if (null == peticion.getUsuario() || peticion.getUsuario().length() <= 0 || null == peticion.getPassword() || peticion.getPassword().length() <= 0 || null == peticion.getIdServicio() || peticion.getIdServicio().length() <= 0 || null == peticion.getIdDispositivo()
				|| peticion.getIdDispositivo().length() <= 0 || null == peticion.getIdPlataforma() || peticion.getIdPlataforma().length() <= 0)
			return ERROR_DATOS_OBLIGATORIOS;
		try{
			if (null !=peticion.getNumPagina()){
				int num = Integer.parseInt(peticion.getNumPagina());
				if (num > 0){
					if (null != peticion.getTamPagina()){
						int tam = Integer.parseInt(peticion.getTamPagina());
						if(tam == 0)
							peticion.setTamPagina(tamanoPaginaDefecto);
						else if (tam < 0)
							return ERROR_TAMPAG;					
					}else
						peticion.setTamPagina(tamanoPaginaDefecto);
				}else if (num < 0)
					return ERROR_NUMPAG;
				else{
					peticion.setNumPagina(numeroPaginaDefecto);
					peticion.setTamPagina(tamanoPaginaDefecto);
				}
			}else{
				peticion.setNumPagina(numeroPaginaDefecto);
				peticion.setTamPagina(tamanoPaginaDefecto);
			}
			
		}catch(NumberFormatException e){
			return ERROR_NO_NUMERICO;
		}
		return res;
	}

	private RespuestaGetAvisosUsuario generarRespuesta(String statustext, String codigo, String details, List<Aviso> ListaAvisos) {
		RespuestaGetAvisosUsuario res = new RespuestaGetAvisosUsuario();
		ResponseStatusType status = new ResponseStatusType();
		

		status.setDetails(details);
		status.setStatusCode(codigo);
		status.setStatusText(statustext);
		
		if (null != ListaAvisos ){
			Avisos avisos = new Avisos();
			for (Aviso aviso : ListaAvisos) {
				avisos.getAviso().add(aviso);
			}
			res.setAvisos(avisos);
		}
		
		res.setStatus(status);
		

		return res;
	}
	
}
