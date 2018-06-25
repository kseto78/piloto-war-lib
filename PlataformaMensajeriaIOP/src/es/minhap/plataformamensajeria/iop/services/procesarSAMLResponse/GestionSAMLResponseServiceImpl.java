package es.minhap.plataformamensajeria.iop.services.procesarSAMLResponse;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import es.minhap.common.properties.PropertiesServices;
import es.minhap.plataformamensajeria.iop.beans.PeticionClaveAuthResponse;
import es.minhap.plataformamensajeria.iop.beans.ResponseSAMLStatusType;
import es.minhap.plataformamensajeria.iop.beans.RespuestaSAMLResponse;
import es.minhap.plataformamensajeria.iop.manager.TblAplicacionesManager;
import es.minhap.plataformamensajeria.iop.manager.TblUsuariosPushManager;
import es.minhap.plataformamensajeria.iop.services.exceptions.PlataformaBusinessException;
import es.minhap.sim.model.TblUsuariosPush;

/**
 * 
 * @author everis
 *
 */
@Service("gestionSAMLResponseImpl")
public class GestionSAMLResponseServiceImpl implements IGestionSAMLResponseService {
	
	private static final Logger LOG = LoggerFactory.getLogger(GestionSAMLResponseServiceImpl.class);
	
	@Resource
	private TblAplicacionesManager aplicacionesManager;
	
	@Resource
	private TblUsuariosPushManager usuariosPushManager;
	
	@Resource(name = "reloadableResourceBundleMessageSource")
	private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;

	private static String textoOK = "plataformaErrores.gestionSAMLRequestService.STATUSTEXT_OK";
	private static String statusOK = "plataformaErrores.gestionSAMLRequestService.STATUS_OK";
	private static String detailsOK = "plataformaErrores.gestionSAMLRequestService.DETAILS_OK";
	private static String textoKO = "plataformaErrores.gestionSAMLRequestService.STATUSTEXT_KO";
	
	@Override
	public String insertarDatosUsuario(PeticionClaveAuthResponse peticion, String nombre, String nif, String apellido1, String apellido2) {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		String statusTextKO = ps.getMessage(textoKO, null);
		String codSAMLNoValido = ps.getMessage("plataformaErrores.gestionSAMLRequestService.COD_SAML_NO_VALIDADO", null);
		String detailsCodSAMLNoValido = ps.getMessage("plataformaErrores.gestionSAMLRequestService.DETAILS_SAML_NO_VALIDADO", null);
		String codErrorServicio = ps.getMessage("plataformaErrores.gestionSAMLRequestService.COD_ERROR_SERVICIO", null);
		String detailsCodErrorServicio = ps.getMessage("plataformaErrores.gestionSAMLRequestService.DETAILS_ERROR_SERVICIO", null);
		String codErrorGeneral = ps.getMessage("plataformaErrores.gestionSAMLRequestService.COD_ERROR_GENERAL", null);
		String detailsErrorGeneral = ps.getMessage("plataformaErrores.gestionSAMLRequestService.DETAILS_ERROR_GENERAL", null);
		String codeKO = ps.getMessage("plataformaErrores.appMovil.COD_ERROR_TOKEN", null);
		String detailsKO = ps.getMessage("plataformaErrores.generales.DETAILS_ERROR_TOKEN", null);
		String stringTimeSession = ps.getMessage("constantes.tiempoSessionPush", null);
		Integer timeSession = null;
		RespuestaSAMLResponse respuesta = new RespuestaSAMLResponse();
		String res = "";	
		try {
			
			try {
				timeSession = Integer.parseInt(stringTimeSession);
			} catch (NumberFormatException e) {
				timeSession = null;
			}
			
			if (!comprobarTokeSessionCorrecto(peticion.getUidDispositivo(), peticion.getTokenSession(), timeSession)) {
				ResponseSAMLStatusType  status = new ResponseSAMLStatusType();
				status.setStatusCode(codeKO);
				status.setStatusText(statusTextKO);
				status.setDetails(detailsKO);
				respuesta.setStatus(status);
				return respuesta.toXMLSMS(respuesta);
			}
			
			if (datosNoValidos(peticion.getIdServicio(), peticion.getIdPlataforma(), peticion.getDispositivoId(), nombre, nif)) {
				respuesta = generarRespuesta(null, null, null, null, statusTextKO, codSAMLNoValido,
						detailsCodSAMLNoValido);
			} else {
				// comprobamos aplicacion
				Boolean existeUsuario = aplicacionesManager.existeAplicacion(peticion.getUsuario(), peticion.getPassword());
				
				if (existeUsuario) {
					respuesta = actualizarUsuario(peticion, nombre, nif, apellido1, apellido2, ps);
				}else {// no es un usuario correcto
					respuesta = generarRespuesta(null, null, null,null,statusTextKO, codErrorServicio, detailsCodErrorServicio);
				}
			}
			res = respuesta.toXMLSMS(respuesta);
		} catch (Exception e) {
			LOG.error("[GestionSAMLResponseServiceImpl.insertarDatosUsuario] Error Insertando los datos del usuario", e);
			respuesta = generarRespuesta(null, null, null, null, statusTextKO, codErrorGeneral, detailsErrorGeneral);
			try {
				return respuesta.toXMLSMS(respuesta);
			} catch (PlataformaBusinessException e1) {
				LOG.error("[GestionSAMLResponseServiceImpl.insertarDatosUsuario] Obteniendo String con la respuesta", e1);
			}
		} 

		return res;
	}

	private boolean comprobarTokeSessionCorrecto(String uidDispositivo, String tokenSession, Integer timeSession) {

		return usuariosPushManager.comprobarTokenSession(uidDispositivo, tokenSession, timeSession);
	}
	
	private RespuestaSAMLResponse actualizarUsuario(PeticionClaveAuthResponse peticion, String nombre, String nif,
			String apellido1, String apellido2, PropertiesServices ps) {
		RespuestaSAMLResponse respuesta;
		TblUsuariosPush usuario;
		usuario = usuariosPushManager.getDatosUsuario(peticion.getIdServicio(), peticion.getIdPlataforma(),
				peticion.getDispositivoId(), nif, true);
		if (null != usuario) {
			
			// si nombre, apellido1 y apellido2 no están se rellenan
			respuesta = rellenarDatos(nombre, nif, apellido1, apellido2, usuario, ps);
		} else {
			
			// si no existe busco el iddispositivo e inserto una linea con los datos
			respuesta = buscarDispositivo(peticion, nombre, nif, apellido1, apellido2, ps);
		}
		return respuesta;
	}

	private RespuestaSAMLResponse buscarDispositivo(PeticionClaveAuthResponse peticion, String nombre, String nif,
			String apellido1, String apellido2, PropertiesServices ps) {
		String statusText = ps.getMessage(textoKO, null);
		String cod = ps.getMessage("plataformaErrores.gestionSAMLRequestService.COD_ERROR_DISPOSITIVO_NO_ENCONTRADO", null);
		String detailsCod = ps.getMessage("plataformaErrores.gestionSAMLRequestService.DETAILS_DISPOSITIVO_NO_ENCONTRADO", null);
		RespuestaSAMLResponse respuesta;
		TblUsuariosPush usuario;
		usuario = usuariosPushManager.getDispositivoAplicacion(peticion.getIdServicio(), peticion.getDispositivoId(), peticion.getIdPlataforma());
		if (null != usuario) {
			usuario = completarUsuario(usuario, nombre, nif, apellido1, apellido2);

			respuesta = insertarUsuario(nombre, nif, apellido1, apellido2, usuario, ps);
		} else {
			// error de dispositivo no encontrado
			respuesta = generarRespuesta(null, null, null, null, statusText, cod, detailsCod);
		}
		return respuesta;
	}

	private RespuestaSAMLResponse insertarUsuario(String nombre, String nif, String apellido1, String apellido2,
			TblUsuariosPush usuario, PropertiesServices ps) {
		String statusTextOK = ps.getMessage(textoOK, null);
		String codOK = ps.getMessage(statusOK, null);
		String detailsCodOK = ps.getMessage(detailsOK, null);
		String statusTextKO = ps.getMessage(textoKO, null);
		String codKO = ps.getMessage("plataformaErrores.gestionSAMLRequestService.COD_ERROR_DATOS_NO_ACTUALIZADOS", null);
		String detailsCodKO = ps.getMessage("plataformaErrores.gestionSAMLRequestService.DETAILS_DISPOSITIVO_NO_ACTUALIZADO", null);
		RespuestaSAMLResponse respuesta;
		if (usuariosPushManager.insertUsuario(usuario))
			respuesta = generarRespuesta(nif, nombre, apellido1, apellido2, statusTextOK, codOK, detailsCodOK);
		else
			respuesta = generarRespuesta(null, null, null, null, statusTextKO, codKO,
					detailsCodKO);
		return respuesta;
	}

	private RespuestaSAMLResponse rellenarDatos(String nombre, String nif, String apellido1, String apellido2, TblUsuariosPush usuario, PropertiesServices ps) {
		RespuestaSAMLResponse respuesta;
		String statusTextOK = ps.getMessage(textoOK, null);
		String codOK = ps.getMessage(statusOK, null);
		String detailsCodOK = ps.getMessage(detailsOK, null);
		if (null == usuario.getNombre()) {
			usuario.setNombre(nombre);
			usuario.setApellido1(apellido1);
			usuario.setApellido2(apellido2);
			usuario.setFechamodificacion(new Date());
			respuesta = actualizaUsuario(nombre, nif, apellido1, apellido2, usuario, ps);
		} else {
			respuesta = generarRespuesta(nif, nombre, apellido1, apellido2, statusTextOK, codOK,
					detailsCodOK);
		}
		return respuesta;
	}

	private RespuestaSAMLResponse actualizaUsuario(String nombre, String nif, String apellido1, String apellido2,
			TblUsuariosPush usuario, PropertiesServices ps) {
		String statusTextOK = ps.getMessage(textoOK, null);
		String codOK = ps.getMessage(statusOK, null);
		String detailsCodOK = ps.getMessage(detailsOK, null);
		String statusTextKO = ps.getMessage(textoKO, null);
		String codKO = ps.getMessage("plataformaErrores.gestionSAMLRequestService.COD_ERROR_DATOS_NO_ACTUALIZADOS", null);
		String detailsCodKO = ps.getMessage("plataformaErrores.gestionSAMLRequestService.DETAILS_DISPOSITIVO_NO_ACTUALIZADO", null);
		RespuestaSAMLResponse respuesta;
		if (usuariosPushManager.updateUsuario(usuario))
			respuesta = generarRespuesta(nif, nombre, apellido1, apellido2, statusTextOK, codOK,
					detailsCodOK);
		else
			// error no se pudo actualizar el usuario
			respuesta = generarRespuesta(null, null, null, null, statusTextKO, codKO,
					detailsCodKO);
		return respuesta;
	}

	private boolean datosNoValidos(String servicio, String plataforma, String dispositivoId, String nombre, String nif) {
		boolean res = false;

		if (null == servicio || servicio.length() <= 0 
				|| checkDispositivoPlataforma(dispositivoId, plataforma) 
				|| checkNombreNIF(nombre, nif))
			return true;

		return res;
	}
	
	private boolean checkNombreNIF(String nombre, String nif) {
		return (checkNombre(nombre) || null == nif || nif.length() <= 0)?true : false;
	}
	private boolean checkNombre(String nombre) {
		return (null == nombre || nombre.length() <= 0)?true : false;
	}
	
	private boolean checkDispositivoPlataforma(String dispositivo, String palataforma) {
		return (checkDispositivo(dispositivo) || null == palataforma || palataforma.length() <= 0)?true : false;
	}
	private boolean checkDispositivo(String dispositivo) {
		return (null == dispositivo || dispositivo.length() <= 0)?true : false;
	}

	private RespuestaSAMLResponse generarRespuesta(String nif, String nombre, String apellido1, String apellido2, String statustext, String codigo, String details) {
		RespuestaSAMLResponse res = new RespuestaSAMLResponse();
		ResponseSAMLStatusType status = new ResponseSAMLStatusType();

		if (null != apellido1 && apellido1.length() > 0)
			res.setApellido1(apellido1);
		if (null != apellido2 && apellido2.length() > 0)
			res.setApellido2(apellido2);
		if (null != nif && nif.length() > 0)
			res.setNif(nif);
		if (null != nombre && nombre.length() > 0)
			res.setNombre(nombre);

		status.setDetails(details);
		status.setStatusCode(codigo);
		status.setStatusText(statustext);
		res.setStatus(status);

		return res;
	}

	private TblUsuariosPush completarUsuario(TblUsuariosPush usuario, String nombre, String nif, String apellido1, String apellido2) {
		usuario.setUsuarioid(null);
		usuario.setApellido1(apellido1);
		usuario.setApellido2(apellido2);
		usuario.setNombreusuario(nif);
		usuario.setNombre(nombre);
		usuario.setFechacreacion(new Date());
		return usuario;
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
	 * @return the reloadableResourceBundleMessageSource
	 */
	public ReloadableResourceBundleMessageSource getReloadableResourceBundleMessageSource() {
		return reloadableResourceBundleMessageSource;
	}

	/**
	 * @param reloadableResourceBundleMessageSource the reloadableResourceBundleMessageSource to set
	 */
	public void setReloadableResourceBundleMessageSource(
			ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource) {
		this.reloadableResourceBundleMessageSource = reloadableResourceBundleMessageSource;
	}

}
