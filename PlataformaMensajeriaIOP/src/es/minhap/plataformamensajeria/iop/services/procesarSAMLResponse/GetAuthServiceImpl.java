package es.minhap.plataformamensajeria.iop.services.procesarSAMLResponse;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import es.minhap.common.properties.PropertiesServices;
import es.minhap.plataformamensajeria.iop.beans.PeticionClaveAuthRequest;
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
@Service("getAuthServiceImpl")
public class GetAuthServiceImpl implements IGetAuthService {
	
	private static final Logger LOG = Logger.getLogger(GetAuthServiceImpl.class);

	@Resource
	private TblAplicacionesManager aplicacionesManager;

	@Resource
	private TblUsuariosPushManager usuariosPushManager;

	@Resource(name = "reloadableResourceBundleMessageSource")
	private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;
		
	@Override
	public String getDatosUsuario(PeticionClaveAuthRequest peticion) {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		String statusTextKO = ps.getMessage("plataformaErrores.gestionSAMLRequestService.STATUSTEXT_KO", null);
		String codErrorPeticion = ps.getMessage("plataformaErrores.gestionSAMLRequestService.COD_ERROR_PETICION", null);
		String detailsErrorPeticion = ps.getMessage(
				"plataformaErrores.gestionSAMLRequestService.DETAILS_ERROR_PETICION", null);
		String codErrorServicio = ps.getMessage("plataformaErrores.gestionSAMLRequestService.COD_ERROR_SERVICIO", null);
		String detailsErrorServicio = ps.getMessage(
				"plataformaErrores.gestionSAMLRequestService.DETAILS_ERROR_SERVICIO", null);
		String codErrorGeneral = ps.getMessage("plataformaErrores.gestionSAMLRequestService.COD_ERROR_GENERAL", null);
		String detailsErrorGeneral = ps.getMessage("plataformaErrores.gestionSAMLRequestService.DETAILS_ERROR_GENERAL",
				null);
		RespuestaSAMLResponse respuesta = new RespuestaSAMLResponse();
		TblUsuariosPush usuario;
		String res = "";	
		try {
			if (datosNoValidos(peticion.getIdServicio(), peticion.getIdPlataforma(), peticion.getIdDispositivo())) {
				respuesta = generarRespuesta(null, null, null, null, statusTextKO, codErrorPeticion, detailsErrorPeticion);
			} else {
				// comprobamos aplicacion
				Boolean existeUsuario = aplicacionesManager.existeAplicacion(peticion.getUsuario(),
						peticion.getPassword());

				if (existeUsuario) {
					usuario = usuariosPushManager.getDatosUsuario(peticion.getIdServicio(), peticion.getIdPlataforma(), peticion.getIdDispositivo(), peticion.getIdDispositivo(), false);
					respuesta = analizarListaUsusarios(usuario, ps);
					
				}else {// no es un usuario correcto
					respuesta = generarRespuesta(null, null, null,null,statusTextKO, codErrorServicio, detailsErrorServicio);
				}
			}
			res = respuesta.toXMLSMS(respuesta);
		} catch (Exception e) {
			LOG.error("[GetAuthServiceImpl.getDatosUsuario] Obteniendo datos usuario",e);
			respuesta = generarRespuesta(null, null, null, null, statusTextKO, codErrorGeneral, detailsErrorGeneral);
			try {
				return respuesta.toXMLSMS(respuesta);
			} catch (PlataformaBusinessException e1) {
				LOG.error(
						"[GetAuthServiceImpl.getDatosUsuario] Obteniendo String con la respuesta", e1);
			}
		} 

		return res;
	}
	
	private RespuestaSAMLResponse analizarListaUsusarios(TblUsuariosPush usuario, PropertiesServices ps) {
		RespuestaSAMLResponse res;
		if (null != usuario){
			res = respuestaCorrecta(usuario, ps);
		}else{
			res = respuestaIncorrecta(ps);
		}
		return res;
	}

	private RespuestaSAMLResponse respuestaIncorrecta(PropertiesServices ps) {
		RespuestaSAMLResponse res;
		String statusTextKO = ps.getMessage("plataformaErrores.gestionSAMLRequestService.STATUSTEXT_KO", null);
		String codError = ps.getMessage("plataformaErrores.gestionSAMLRequestService.COD_ERROR_USUARIO_DISPOSITIVO", null);
		String detailsError = ps.getMessage(
				"plataformaErrores.gestionSAMLRequestService.DETAILS_ERROR_USUARIO_DISPOSITIVO", null);
		
		//error que no se encuentra
		res = generarRespuesta(null, null,null, null,statusTextKO, codError, detailsError);
		return res;
	}

	private RespuestaSAMLResponse respuestaCorrecta(TblUsuariosPush usuario, PropertiesServices ps) {
		RespuestaSAMLResponse res;
		String statusText = ps.getMessage("plataformaErrores.gestionSAMLRequestService.STATUSTEXT_OK", null);
		String cod = ps.getMessage("plataformaErrores.gestionSAMLRequestService.STATUS_OK", null);
		String details = ps.getMessage("plataformaErrores.gestionSAMLRequestService.DETAILS_OK", null);
		
		//si son diferentes lo doy por valido. Se supone que el nombre de usuario ser�a el DNI
		res = generarRespuesta(usuario.getNombreusuario(), usuario.getNombre(), 
				usuario.getApellido1(), usuario.getApellido2(),statusText, cod, details);
		return res;
	}

	private boolean datosNoValidos(String servicio, String plataforma, String dispositivoId) {
		boolean res = false;

		if (null == servicio || servicio.length() <= 0 || checkDispositivoPlataforma(dispositivoId, plataforma))
			return true;

		return res;
	}
	
	private boolean checkDispositivoPlataforma(String dispositivo, String palataforma) {
		return (checkDispositivo(dispositivo) || null == palataforma || palataforma.length() <= 0) ? true : false;
	}

	private boolean checkDispositivo(String dispositivo) {
		return (null == dispositivo || dispositivo.length() <= 0) ? true : false;
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
