package es.minhap.plataformamensajeria.iop.services.gestionServiciosPush;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import es.minhap.common.entity.OrderType;
import es.minhap.common.entity.TextComparator;
import es.minhap.common.properties.PropertiesServices;
import es.minhap.plataformamensajeria.iop.beans.PeticionConfirmarAltaUsuario;
import es.minhap.plataformamensajeria.iop.beans.PeticionSolicitudRegistroMovil;
import es.minhap.plataformamensajeria.iop.beans.RegistroUsuarioXMLBean;
import es.minhap.plataformamensajeria.iop.beans.ResponseStatusTypeConfirmarAltaUsuario;
import es.minhap.plataformamensajeria.iop.beans.ResponseStatusTypeRegistroMovil;
import es.minhap.plataformamensajeria.iop.beans.RespuestaConfirmarAltaUsuario;
import es.minhap.plataformamensajeria.iop.beans.RespuestaRegistroMovil;
import es.minhap.plataformamensajeria.iop.beans.respuestasServiciosMoviles.RespuestaServiciosRegistrarUsuario;
import es.minhap.plataformamensajeria.iop.manager.TblAltaNumMovilManager;
import es.minhap.plataformamensajeria.iop.manager.TblAplicacionesManager;
import es.minhap.plataformamensajeria.iop.manager.TblUsuariosPushManager;
import es.minhap.plataformamensajeria.iop.services.exceptions.PlataformaBusinessException;
import es.minhap.plataformamensajeria.iop.util.Utils;
import es.minhap.sim.model.TblAltaNumMovil;
import es.minhap.sim.model.TblUsuariosPush;
import es.minhap.sim.query.TblAltaNumMovilQuery;
import es.minhap.sim.query.TblUsuariosPushQuery;

/**
 * 
 * @author everis
 * 
 */
@Service("registroMovilServiceImpl")
public class RegistroMovilServiceImpl implements IRegistroMovilService {
	private static final Logger LOG = LoggerFactory.getLogger(RegistroMovilServiceImpl.class);

	@Resource
	private TblUsuariosPushManager usuariosPushManager;
	
	@Resource
	private TblAplicacionesManager aplicacionesManager;
	
	@Resource(name="TblAltaNumMovilManagerImpl")
	private TblAltaNumMovilManager tblAltaNumMovilManager;
	
	@Resource(name="gestionServiciosPushImpl")
	private IGestionServiciosPushService gestionServiciosPushServiceImpl;

	@Resource(name = "reloadableResourceBundleMessageSource")
	private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;
	
	private static final String TIMECODIGOACTIVO = "60";
	private static final String REINTENTOS = "3";
	private static final String ACCIONALTA = "A";
	private static final String OK = "OK";

	@Override
	public String solicitudRegistroMovil(PeticionSolicitudRegistroMovil peticionBean) {
		LOG.debug("[RegistroMovilServiceImpl] solicitudRegistroMovil");
		String xmlResultado = "";
		RespuestaRegistroMovil respuesta;
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		String statusTextKO = ps.getMessage("plataformaErrores.generales.STATUSTEXT_KO", null);
		String codeKO = ps.getMessage("plataformaErrores.appMovil.COD_ERROR_TOKEN", null);
		String detailsKO = ps.getMessage("plataformaErrores.generales.DETAILS_ERROR_TOKEN", null);
		String stringTimeSession = ps.getMessage("constantes.tiempoSessionPush", null);
		String loginCodeKO = ps.getMessage("plataformaErrores.gestionServicioPush.STATUSLOGINCODE_KO", null);
		String longinDetailsKO = ps.getMessage("plataformaErrores.gestionServicioPush.STATUSLOGIN_KO", null);
		Integer timeSession = null;
		try {
			timeSession = Integer.parseInt(stringTimeSession);
		} catch (NumberFormatException e) {
			timeSession = null;
		}
		try {
			if (!comprobarTokeSessionCorrecto(peticionBean.getUidDispositivo(), peticionBean.getTokenSession(),
					timeSession)) {
				respuesta = generarRespuesta(null, statusTextKO, codeKO, detailsKO);
				return respuesta.toXML(respuesta);
			}
			
			//validamos el telefono y el servicio
			respuesta = validaciones(peticionBean.getIdServicioMovil(), peticionBean.getNumMovil(), ps);
			
			if(respuesta != null){
				return respuesta.toXML(respuesta);
			}
			
			Boolean existeUsuario = aplicacionesManager.existeAplicacion(peticionBean.getUsuario(), peticionBean.getPassword());
			if(existeUsuario) {
				respuesta = registrarMovil(peticionBean, ps);
			} else {// no es un usuario correcto
				respuesta = generarRespuesta(null, statusTextKO, loginCodeKO, longinDetailsKO);
			}
			
			xmlResultado = respuesta.toXML(respuesta);
		} catch (PlataformaBusinessException e) {
			LOG.error("[RegistroMovilServiceImpl] solicitudRegistroMovil", e);
		}
		return xmlResultado;

	}
	
	@Override
	public String confirmarAltaUsuario(PeticionConfirmarAltaUsuario peticionBean) {
		LOG.debug("[RegistroMovilServiceImpl] confirmarAltaUsuario");
		String xmlResultado = "";
		RespuestaConfirmarAltaUsuario respuesta;
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		String statusTextKO = ps.getMessage("plataformaErrores.generales.STATUSTEXT_KO", null);
		String codeKO = ps.getMessage("plataformaErrores.appMovil.COD_ERROR_TOKEN", null);
		String detailsKO = ps.getMessage("plataformaErrores.generales.DETAILS_ERROR_TOKEN", null);
		String stringTimeSession = ps.getMessage("constantes.tiempoSessionPush", null);
		String loginCodeKO = ps.getMessage("plataformaErrores.gestionServicioPush.STATUSLOGINCODE_KO", null);
		String longinDetailsKO = ps.getMessage("plataformaErrores.gestionServicioPush.STATUSLOGIN_KO", null);
		Integer timeSession = null;
		try {
			timeSession = Integer.parseInt(stringTimeSession);
		} catch (NumberFormatException e) {
			timeSession = null;
		}
		try {
			if (!comprobarTokeSessionCorrecto(peticionBean.getUidDispositivo(), peticionBean.getTokenSession(),
					timeSession)) {
				respuesta = generarRespuestaConfirmarAlta(statusTextKO, codeKO, detailsKO);
				return respuesta.toXML(respuesta);
			}
			
			Boolean existeUsuario = aplicacionesManager.existeAplicacion(peticionBean.getUsuario(), peticionBean.getPassword());
			if(existeUsuario) {
				respuesta = confirmarAlta(peticionBean, ps);
			} else {// no es un usuario correcto
				respuesta = generarRespuestaConfirmarAlta(statusTextKO, loginCodeKO, longinDetailsKO);
			}
			
			xmlResultado = respuesta.toXML(respuesta);
		} catch (PlataformaBusinessException e) {
			LOG.error("[RegistroMovilServiceImpl] confirmarAltaUsuario", e);
		}
		return xmlResultado;
	}
	
	
	@Override
	public void eliminarRegistrosCaducados(){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR_OF_DAY, -1);
		
		TblAltaNumMovilQuery query = new TblAltaNumMovilQuery();
		query.setFechaCreacionMax(calendar.getTime());
		query.addOrder("idAltaNumMovil", OrderType.DESC);
		
		List<Long> lista = tblAltaNumMovilManager.getListaIdTblAltaNumMovilByQuery(query);
		
		for (Long id : lista) {
			tblAltaNumMovilManager.eliminar(id);
		}
	}

	private RespuestaConfirmarAltaUsuario confirmarAlta(PeticionConfirmarAltaUsuario peticionBean, PropertiesServices ps) throws PlataformaBusinessException{
		String stringTimeSession = ps.getMessage("constantes.tiempoRegistroMovilActivo", null, TIMECODIGOACTIVO);
		String statusTextOK = ps.getMessage("plataformaErrores.generales.STATUSTEXT_OK", null);
		String statusTextKO = ps.getMessage("plataformaErrores.generales.STATUSTEXT_KO", null);
		String codeTimeKO = ps.getMessage("plataformaErrores.confirmarAltaUsuario.COD_ERROR_CODIGO_CADUCADO", null);
		String detailsTimeKO = ps.getMessage("plataformaErrores.confirmarAltaUsuario.DETAILS_ERROR_CODIGO_CADUCADO", null);
		String codeInsertKO = ps.getMessage("plataformaErrores.confirmarAltaUsuario.COD_ERROR_INSERTAR_USUARIO", null);
		String detailsInsertKO = ps.getMessage("plataformaErrores.confirmarAltaUsuario.DETAILS_ERROR_INSERTAR_USUARIO", null);
		String codeOK = ps.getMessage("plataformaErrores.confirmarAltaUsuario.TAG_STATUSCODE_OK", null);
		String detailsOK = ps.getMessage("plataformaErrores.confirmarAltaUsuario.TAG_MENSAJE_OK", null);
		
		String uidDispositivo = peticionBean.getUidDispositivo();
		String codConfirmacion = peticionBean.getCodConfirmacion();
		Long idServicioMovil = Long.parseLong(peticionBean.getIdServicioMovil());
		Integer timeSession;
		try{
			timeSession = Integer.parseInt(stringTimeSession);
		}catch(NumberFormatException e){
			timeSession = Integer.parseInt(TIMECODIGOACTIVO);
		}
				
		// Buscamos el registro en BBDD
		TblAltaNumMovil tblAltaNumMovil = tblAltaNumMovilManager.getTblAltaNumMovilByQuery(createQueryBusqueda(
				idServicioMovil, uidDispositivo, null, codConfirmacion));

		if (null != tblAltaNumMovil) {

			// Comprobamos si ha caducado. Si caducado se elimina de BBDD e informamos
			if (caducado(tblAltaNumMovil.getFechaCreacion(), timeSession)) {
				tblAltaNumMovilManager.eliminar(tblAltaNumMovil.getIdAltaNumMovil());
				return generarRespuestaConfirmarAlta(statusTextKO, codeTimeKO, detailsTimeKO);
			} else {

				// Se crea entrada en TBL_USUARIOSPUSH con el nombreUsuario con el telefono
				if (!insertarUsuarioPush(tblAltaNumMovil.getNumMovil(), tblAltaNumMovil.getUidDispositivo(), peticionBean.getIdDispositivo(), ps)) {

					// Error en la insercion se indica el error
					return generarRespuestaConfirmarAlta(statusTextKO, codeInsertKO, detailsInsertKO);
				}

				// Es correcto se registra el usuario en el servicio y se devuelve OK
				String registro = gestionServiciosPushServiceImpl
						.registroUsuarioEnServicio(crearRegistroUsuarioXMLBean(peticionBean, tblAltaNumMovil));
				RespuestaServiciosRegistrarUsuario respRegistro = new RespuestaServiciosRegistrarUsuario();
				respRegistro.loadObjectFromXML(registro);

				// Si contiene OK genero respuesta de OK
				if (registro.contains(OK)) {
					return generarRespuestaConfirmarAlta(statusTextOK, codeOK, detailsOK);
				} else {
					return generarRespuestaConfirmarAlta(statusTextKO, respRegistro.getStatus().getStatusCode(),
							respRegistro.getStatus().getDetails());
				}

			}
		} else {// no existe (o lo ha eliminado el job o el código es incorrecto
			return tratarErroresCodConfirmacion(uidDispositivo, idServicioMovil, ps);
		}

	}

	private RespuestaConfirmarAltaUsuario tratarErroresCodConfirmacion(String uidDispositivo, Long idServicioMovil, PropertiesServices ps) {
		String codeCodigoIncorrectoKO = ps.getMessage("plataformaErrores.confirmarAltaUsuario.COD_ERROR_CODIGO_INCORRECTO", null);
		String detailsCodigoIncorrectoKO = ps.getMessage("plataformaErrores.confirmarAltaUsuario.DETAILS_ERROR_CODIGO_INCORRECTO", null);
		String codeReintentosKO = ps.getMessage("plataformaErrores.confirmarAltaUsuario.COD_ERROR_REINTENTOS", null);
		String detailsReintentosKO = ps.getMessage("plataformaErrores.confirmarAltaUsuario.DETAILS_ERROR_REINTENTOS", null);
		String codeUsuarioKO = ps.getMessage("plataformaErrores.confirmarAltaUsuario.COD_ERROR_USUARIO", null);
		String detailsUsuarioKO = ps.getMessage("plataformaErrores.confirmarAltaUsuario.DETAILS_ERROR_USUARIO", null);
		String statusTextKO = ps.getMessage("plataformaErrores.generales.STATUSTEXT_KO", null);
		
		//buscamos el uid y el servicio para aumentar los reintentos 
		TblAltaNumMovil tblAltaNumMovil = tblAltaNumMovilManager.getTblAltaNumMovilByQuery(createQueryBusqueda(idServicioMovil, uidDispositivo, null, null));
		
		if (null != tblAltaNumMovil){//codConfirmacion incorrecto
			if (tblAltaNumMovil.getNumReintentos()>= 0){
				tblAltaNumMovil.setNumReintentos(tblAltaNumMovil.getNumReintentos()-1);
				tblAltaNumMovilManager.actualizar(tblAltaNumMovil);
				return generarRespuestaConfirmarAlta(statusTextKO, codeCodigoIncorrectoKO, detailsCodigoIncorrectoKO);
			}else{
				tblAltaNumMovilManager.eliminar(tblAltaNumMovil.getIdAltaNumMovil());
				return generarRespuestaConfirmarAlta(statusTextKO, codeReintentosKO, detailsReintentosKO);
			}
		}else{//No existe entrada en tabla
			return generarRespuestaConfirmarAlta(statusTextKO, codeUsuarioKO, detailsUsuarioKO);
		}
	}

	

	private Boolean insertarUsuarioPush(String numMovil, String uidDispositivo, String idDispositivo, PropertiesServices ps) {
		Long servicioId = Long.parseLong(ps.getMessage("servicio.notificacionesPUSH", null));
		Boolean res = false;
		
		//obtenemos el usuario de BBDD
		TblUsuariosPush usuario = usuariosPushManager.existeUimDispositivo(uidDispositivo, servicioId);
		
		if (null != usuario){//existe usuario actualiza el nombreUsuario o se crea uno nuevo con el telefono como Nombreusuario
			TblUsuariosPushQuery query = new TblUsuariosPushQuery();
			query.setUiddispositivo(uidDispositivo);
			query.setUiddispositivoComparator(TextComparator.EQUALS);
			query.setServicioid(servicioId);
			query.setNombreusuario(numMovil);
			query.setNombreusuarioComparator(TextComparator.EQUALS);
			
			TblUsuariosPush up = usuariosPushManager.getUsuarioPushByQuery(query);
			if (null == up){
				usuario.setNombreusuario(numMovil);
				usuario.setFechamodificacion(new Date());
				usuario.setUsuarioid(null);
				return usuariosPushManager.insertUsuario(usuario);
			}else{//actualizo
				up.setNombreusuario(numMovil);
				up.setFechamodificacion(new Date());
				up.setDispositivoid(idDispositivo);
				up.setTokenusuario(usuario.getTokenusuario());
				up.setTokensession(usuario.getTokensession());
				return usuariosPushManager.updateUsuario(up);
			}
		}		
		return res;
	}

	private RegistroUsuarioXMLBean crearRegistroUsuarioXMLBean(PeticionConfirmarAltaUsuario peticionBean, TblAltaNumMovil tblAltaNumMovil) {
		RegistroUsuarioXMLBean res = new RegistroUsuarioXMLBean();
		res.setAccion(ACCIONALTA);
		res.setIdDispositivo(peticionBean.getIdDispositivo());
		res.setIdServicioMovil(tblAltaNumMovil.getIdServicioMovil().toString());
		res.setIdUsuario(tblAltaNumMovil.getNumMovil());
		res.setPassword(peticionBean.getPassword());
		res.setTokenSession(peticionBean.getTokenSession());
		res.setUidDispositivo(peticionBean.getUidDispositivo());
		res.setUsuario(peticionBean.getUsuario());
		
		return res;
	}

	private RespuestaRegistroMovil registrarMovil(PeticionSolicitudRegistroMovil peticionBean, PropertiesServices ps) {
		String stringTimeSession = ps.getMessage("constantes.tiempoRegistroMovilActivo", null, TIMECODIGOACTIVO);
		String statusTextOK = ps.getMessage("plataformaErrores.registroMovil.TAG_OK", null);
		String statusCodeOK = ps.getMessage("plataformaErrores.registroMovil.TAG_STATUSCODE_OK", null);
		String statusDetailsOK = ps.getMessage("plataformaErrores.registroMovil.TAG_MENSAJE_OK", null);
		String statusTextKO = ps.getMessage("plataformaErrores.registroMovil.TAG_KO", null);
		String statusCodeKO = ps.getMessage("plataformaErrores.registroMovil.TAG_ERROR_PETICION", null);
		String statusDetailsKO = ps.getMessage("plataformaErrores.registroMovil.TAG_MENSAJE_KO_GENERAL", null);
		Integer numReintentos = Integer.parseInt(ps.getMessage("constantes.numReintentosRegistroMovil", null, REINTENTOS));
		Integer maxRegistrosPermitidosPorUid = Integer.parseInt(ps.getMessage("constantes.maxRegistrosPremitidosPorUidyServicio", null, REINTENTOS));
		String statusDetailsMaxRegistrosKO = ps.getMessage("plataformaErrores.registroMovil.TAG_MENSAJE_KO_MAX_REGISTROS", null);
		
		Integer timeSession = null;
		RespuestaRegistroMovil res;
		
		Long idServicioMovil = Long.parseLong(peticionBean.getIdServicioMovil());
		String uidDispositivo = peticionBean.getUidDispositivo();
		String numMovil = peticionBean.getNumMovil();
		
		try{
			timeSession = Integer.parseInt(stringTimeSession);
		}catch(NumberFormatException e){
			timeSession = Integer.parseInt(TIMECODIGOACTIVO);
		}
		try{
		//comprobamos los reintentos con distintos números de telefono que ha intentado ese uidDispositivo-servicioMovil	
		List<Long> listaYaRegistrados = tblAltaNumMovilManager.getListaIdTblAltaNumMovilByQuery(createQueryBusqueda(idServicioMovil, uidDispositivo, null, null));	
		if (null != listaYaRegistrados && listaYaRegistrados.size()>= maxRegistrosPermitidosPorUid){
			return generarRespuesta(null, statusTextKO, statusCodeKO, statusDetailsMaxRegistrosKO);
		}
		
		//Buscamos si esta ya el registro en BBDD
		TblAltaNumMovil tblAltaNumMovil = tblAltaNumMovilManager.getTblAltaNumMovilByQuery(createQueryBusqueda(idServicioMovil, uidDispositivo, numMovil, null));
		
		if (null != tblAltaNumMovil){
			//comprobamos si ha caducado. Si ha caducado se hace update con nuevo codigo y se envía mensaje sino se devuelve ok y no se envia mensaje
			if (caducado(tblAltaNumMovil.getFechaCreacion(), timeSession)){
				tblAltaNumMovil.setCodConfirmacion(Utils.crearSmsToken(4));
				tblAltaNumMovil.setFechaCreacion(new Date());
				tblAltaNumMovil.setNumReintentos(numReintentos);
				tblAltaNumMovilManager.actualizar(tblAltaNumMovil);
				res = generarRespuesta(tblAltaNumMovil.getCodConfirmacion(), statusTextOK, statusCodeOK, statusDetailsOK);
			}else{
				res = generarRespuesta(null, statusTextOK, statusCodeOK, statusDetailsOK);
			}
		}else{//no existe hacemos insert
			TblAltaNumMovil alta = new TblAltaNumMovil();
			alta.setCodConfirmacion(Utils.crearSmsToken(4));
			alta.setFechaCreacion(new Date());
			alta.setIdServicioMovil(idServicioMovil);
			alta.setNumMovil(numMovil);
			alta.setNumReintentos(numReintentos);
			alta.setUidDispositivo(uidDispositivo);
			tblAltaNumMovilManager.insertar(alta);
			res = generarRespuesta(alta.getCodConfirmacion(), statusTextOK, statusCodeOK, statusDetailsOK);
		}
		}catch(Exception e){
			LOG.error("[RegistroMovilService] registrarMovil" , e);
			return generarRespuesta(null, statusTextKO, statusCodeKO, statusDetailsKO);
		}
		return res;
	}
	
	private RespuestaRegistroMovil validaciones(String idServicioMovil, String numMovil, PropertiesServices ps) {
		RespuestaRegistroMovil res = null;
		String telefonoExcepcion = ps.getMessage("validarTelefono.TelefonoExcepcion", null, "");
		
		//comprueba si el servicio movil es un numero no se comprueba el tipo porque se supone lo controla app movil
		try{
			Long.parseLong(idServicioMovil);
		}catch(Exception e){
			LOG.error("[RegistroMovilService] validaciones Servicio incorrecto" , e);
			String statusCode =ps.getMessage("plataformaErrores.registroMovil.COD_SERVICIO_INCORRECTO", null);
			String statusText = ps.getMessage("plataformaErrores.generales.STATUSTEXT_KO", null);
			String statusDetails = ps.getMessage("plataformaErrores.registroMovil.DETAILS_SERVICIO_INCORRECTO", null);
			return generarRespuesta(null, statusText, statusCode, statusDetails);
		}
		
		//comprueba si el telefono es un numero correcto 0-OK 1-KO
		int valido = Utils.validarTelefono(numMovil, telefonoExcepcion);
		
		if (valido != 0){
			String statusCode =ps.getMessage("plataformaErrores.registroMovil.COD_TELEFONO_INCORRECTO", null);
			String statusText = ps.getMessage("plataformaErrores.generales.STATUSTEXT_KO", null);
			String statusDetails = ps.getMessage("plataformaErrores.registroMovil.DETAILS_TELEFONO_INCORRECTO", null);
			return generarRespuesta(null, statusText, statusCode, statusDetails);
		}
		
		return res;
	}

	

	private boolean caducado( Date fechaCreacion, Integer timeSession) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaCreacion);
		calendar.add(Calendar.MINUTE, timeSession);
		
		return calendar.after(Calendar.getInstance())? false : true;
	}

	private TblAltaNumMovilQuery createQueryBusqueda(Long idServicioMovil, String uidDispositivo, String numMovil, String codConfirmacion) {
		TblAltaNumMovilQuery res = new TblAltaNumMovilQuery();
		res.setIdServicioMovil(idServicioMovil);
		if (null != numMovil){
			res.setNumMovil(numMovil);
			res.setNumMovilComparator(TextComparator.EQUALS);
		}
		res.setUidDispositivo(uidDispositivo);
		res.setUidDispositivoComparator(TextComparator.EQUALS);
		if(null !=codConfirmacion){
			res.setCodConfirmacion(codConfirmacion);
			res.setCodConfirmacionComparator(TextComparator.EQUALS);
		}
		res.addOrder("fechaCreacion", OrderType.DESC);
		return res;
	}

	
	
	private boolean comprobarTokeSessionCorrecto(String uidDispositivo, String tokenSession, Integer timeSession) {
		return usuariosPushManager.comprobarTokenSession(uidDispositivo, tokenSession, timeSession);
	}
	
	private RespuestaRegistroMovil generarRespuesta(String codConfirmacion, String statustext, String codigo, String details) {
		RespuestaRegistroMovil res = new RespuestaRegistroMovil();
		ResponseStatusTypeRegistroMovil status = new ResponseStatusTypeRegistroMovil();

		if (null != codConfirmacion && codConfirmacion.length() > 0){
			res.setCodConfirmacion(codConfirmacion);
		}

		res.setStatus(status);
		
		status.setDetails(details);
		status.setStatusCode(codigo);
		status.setStatusText(statustext);
		res.setStatus(status);

		return res;
	}
	
	private RespuestaConfirmarAltaUsuario generarRespuestaConfirmarAlta(String statustext, String codigo, String details) {
		RespuestaConfirmarAltaUsuario res = new RespuestaConfirmarAltaUsuario();
		ResponseStatusTypeConfirmarAltaUsuario status = new ResponseStatusTypeConfirmarAltaUsuario();

		res.setStatus(status);
		
		status.setDetails(details);
		status.setStatusCode(codigo);
		status.setStatusText(statustext);
		res.setStatus(status);

		return res;
	}

}
