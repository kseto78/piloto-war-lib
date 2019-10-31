package es.minhap.plataformamensajeria.iop.services.procesarSAMLResponse;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import es.minhap.common.properties.PropertiesServices;
import es.minhap.plataformamensajeria.iop.beans.PeticionClaveAuthRequest;
import es.minhap.plataformamensajeria.iop.beans.ResponseSAMLStatusType;
import es.minhap.plataformamensajeria.iop.beans.RespuestaSAMLResponse;
import es.minhap.plataformamensajeria.iop.manager.TblAplicacionesManager;
import es.minhap.plataformamensajeria.iop.manager.TblUsuariosPushManager;
import es.minhap.plataformamensajeria.iop.services.exceptions.PlataformaBusinessException;

/**
 * 
 * @author everis
 * 
 */
@Service("gestionSAMLRequestImpl")
public class GestionSAMLRequestServiceImpl implements IGestionSAMLRequestService {

	protected static final String R_CONST_1 = "plataformaErrores.gestionSAMLRequestService.STATUSTEXT_KO";

	private static final Logger LOG = LoggerFactory.getLogger(GestionSAMLRequestServiceImpl.class);

	@Resource
	private TblAplicacionesManager aplicacionesManager;

	@Resource
	private TblUsuariosPushManager usuariosPushManager;

	@Resource(name = "reloadableResourceBundleMessageSource")
	private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;

	@Override
	public String comprobarDatosUsuario(PeticionClaveAuthRequest peticion) {
		PropertiesServices ps = new PropertiesServices(getReloadableResourceBundleMessageSource());
		String statusTextKO = ps.getMessage(R_CONST_1, null);
		String codErrorPeticion = ps.getMessage("plataformaErrores.gestionSAMLRequestService.COD_ERROR_PETICION", null);
		String detailsErrorPeticion = ps.getMessage(
				"plataformaErrores.gestionSAMLRequestService.DETAILS_ERROR_PETICION", null);
		String codErrorServicio = ps.getMessage("plataformaErrores.gestionSAMLRequestService.COD_ERROR_SERVICIO", null);
		String detailsErrorServicio = ps.getMessage(
				"plataformaErrores.gestionSAMLRequestService.DETAILS_ERROR_SERVICIO", null);
		String codErrorGeneral = ps.getMessage("plataformaErrores.gestionSAMLRequestService.COD_ERROR_GENERAL", null);
		String detailsErrorGeneral = ps.getMessage("plataformaErrores.gestionSAMLRequestService.DETAILS_ERROR_GENERAL",
				null);
		String codeKO = ps.getMessage("plataformaErrores.appMovil.COD_ERROR_TOKEN", null);
		String detailsKO = ps.getMessage("plataformaErrores.generales.DETAILS_ERROR_TOKEN", null);
		String stringTimeSession = ps.getMessage("constantes.tiempoSessionPush", null);
		Integer timeSession = null;
		RespuestaSAMLResponse respuesta = new RespuestaSAMLResponse();
		Boolean existeDispositivo = null;
		String res = "";
		try {

			try {
				timeSession = Integer.parseInt(stringTimeSession);
			} catch (NumberFormatException e) {
				// TODO logger.warn(e.getMessage(), e);
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

			if (datosNoValidos(peticion.getUsuario(), peticion.getPassword(), peticion.getIdServicio(),
					peticion.getIdDispositivo(), peticion.getIdPlataforma())) {
				respuesta = generarRespuesta(statusTextKO, codErrorPeticion, detailsErrorPeticion);
			} else {
				// comprobamos aplicacion
				Boolean existeUsuario = getAplicacionesManager().existeAplicacion(peticion.getUsuario(),
						peticion.getPassword());

				if (existeUsuario) {
					// comprobamos si existe el usuario con sus datos
					existeDispositivo = getUsuariosPushManager().comprobarExisteDispositivo(peticion.getIdServicio(),
							peticion.getIdDispositivo(), peticion.getIdPlataforma(), null);
					respuesta = respuestaUsuario(existeDispositivo, ps);

				} else {// no es un usuario correcto
					respuesta = generarRespuesta(statusTextKO, codErrorServicio, detailsErrorServicio);
				}
			}
			res = respuesta.toXMLSMS(respuesta);
		} catch (Exception e) {
			LOG.error("[GestionSAMLRequestServiceImpl.comprobarDatosUsuario] Error Comprobando los datos del usuario",
					e);
			respuesta = generarRespuesta(statusTextKO, codErrorGeneral, detailsErrorGeneral);
			try {
				return respuesta.toXMLSMS(respuesta);
			} catch (PlataformaBusinessException e1) {
				LOG.error("[GestionSAMLRequestServiceImpl.comprobarDatosUsuario] Obteniendo String con la respuesta",
						e1);
			}
		}

		return res;
	}

	private boolean comprobarTokeSessionCorrecto(String uidDispositivo, String tokenSession, Integer timeSession) {

		return usuariosPushManager.comprobarTokenSession(uidDispositivo, tokenSession, timeSession);
	}

	private RespuestaSAMLResponse respuestaUsuario(Boolean existeDispositivo, PropertiesServices ps) {
		RespuestaSAMLResponse respuesta;
		String statusTextOK = ps.getMessage("plataformaErrores.gestionSAMLRequestService.STATUSTEXT_OK", null);
		String statusOK = ps.getMessage("plataformaErrores.gestionSAMLRequestService.STATUS_OK", null);
		String detailsOK = ps.getMessage("plataformaErrores.gestionSAMLRequestService.DETAILS_OK", null);
		String statusTextKO = ps.getMessage(R_CONST_1, null);
		String statusKO = ps.getMessage(
				"plataformaErrores.gestionSAMLRequestService.COD_ERROR_DISPOSITIVO_NO_ENCONTRADO", null);
		String detailsKO = ps.getMessage(
				"plataformaErrores.gestionSAMLRequestService.DETAILS_DISPOSITIVO_NO_ENCONTRADO", null);
		if (null != existeDispositivo) {
			respuesta = generarRespuesta(statusTextOK, statusOK, detailsOK);
		} else {
			respuesta = generarRespuesta(statusTextKO, statusKO, detailsKO);
		}
		return respuesta;
	}

	private boolean datosNoValidos(String usuario, String password, String servicio, String dispositivo,
			String plataforma) {
		boolean res = false;

		return checkUsuarioPassword(usuario, password) || null == servicio || servicio.isEmpty()
				|| checkDispositivoPlataforma(dispositivo, plataforma) || res;
	}

	private boolean checkUsuarioPassword(String usuario, String password) {
		return checkUsuario(usuario) || null == password || password.isEmpty();
	}

	private boolean checkUsuario(String usuario) {
		return null == usuario || usuario.isEmpty();
	}

	private boolean checkDispositivoPlataforma(String dispositivo, String palataforma) {
		return checkDispositivo(dispositivo) || null == palataforma || palataforma.isEmpty();
	}

	private boolean checkDispositivo(String dispositivo) {
		return null == dispositivo || dispositivo.isEmpty();
	}

	private RespuestaSAMLResponse generarRespuesta(String statustext, String codigo, String details) {
		RespuestaSAMLResponse res = new RespuestaSAMLResponse();
		ResponseSAMLStatusType status = new ResponseSAMLStatusType();

		status.setDetails(details);
		status.setStatusCode(codigo);
		status.setStatusText(statustext);
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
	 * @param aplicacionesManager
	 *            the aplicacionesManager to set
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
	 * @param usuariosPushManager
	 *            the usuariosPushManager to set
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
	 * @param reloadableResourceBundleMessageSource
	 *            the reloadableResourceBundleMessageSource to set
	 */
	public void setReloadableResourceBundleMessageSource(
			ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource) {
		this.reloadableResourceBundleMessageSource = reloadableResourceBundleMessageSource;
	}

}
