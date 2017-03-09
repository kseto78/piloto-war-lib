
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package es.minhap.plataformamensajeria.iop.services.usuariosplataformas;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import es.minhap.common.properties.PropertiesServices;
import es.minhap.plataformamensajeria.iop.manager.TblUsuariosPushManager;

/**
 * 
 * @author everis
 *
 */
@Service("registroUsuarioServiceImpl")
public class RegistroUsuarioServiceImpl implements IRegistroUsuarioService {
    
	private static final Logger LOG = LoggerFactory.getLogger(RegistroUsuarioServiceImpl.class);
	
	@Resource
	private TblUsuariosPushManager usuariosPushManager;

	@Resource(name = "reloadableResourceBundleMessageSource")
	private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;


    /* (non-Javadoc)
     * @see es.minhap.plataformamensajeria.ws.registrousuario.IRegistroUsuarioService#registroUsuario(es.minhap.plataformamensajeria.ws.registrousuario.RegistroUsuarioRequest  parameters )*
     */
	@Override
    public RegistroUsuarioResponse registroUsuario(String nombreUsuario, String servicioId, String usuario, String password,
			String plataformaId, String tokenUsuario, String dispositivoId) { 
    	
    	PropertiesServices ps = new PropertiesServices(getReloadableResourceBundleMessageSource());
		String statusCodeOK = ps.getMessage("plataformaErrores.registroUsuarioPush.TAG_STATUSCODE_OK", null);
		String tagOK = ps.getMessage("plataformaErrores.registroUsuarioPush.TAG_OK", null);
		String tagMensajeOK = ps.getMessage("plataformaErrores.registroUsuarioPush.TAG_MENSAJE_OK", null);
		String errorAltaUsuarioPush = ps.getMessage("plataformaErrores.registroUsuarioPush.TAG_ERROR_ALTA_USUARIO_PUSH", null);
		String tagKO = ps.getMessage("plataformaErrores.registroUsuarioPush.TAG_KO", null);
		String errorGeneral = ps.getMessage("plataformaErrores.registroUsuarioPush.TAG_MENSAJE_KO_GENERAL", null);
		String errorUsuarioAplicacion = ps.getMessage(
				"plataformaErrores.registroUsuarioPush.TAG_ERROR_USUARIO_PASSWORD_APLICACION", null);
		String detailsErrorUsuarioAplicacion = ps.getMessage(
				"plataformaErrores.registroUsuarioPush.TAG_MENSAJE_KO_USUARIO_PASSWORD_APLICACION", null);
		String errorServicio = ps.getMessage("plataformaErrores.registroUsuarioPush.TAG_ERROR_SERVICIO", null);
		String detailsErrorServicio = ps.getMessage("plataformaErrores.registroUsuarioPush.TAG_MENSAJE_KO_SERVICIO", null);
		String errorNoExisteUsuarioAplicacion = ps.getMessage(
				"plataformaErrores.registroUsuarioPush.TAG_ERROR_NOEXTSITE_USUARIO_PASSWORD_APLICACION", null);
		String detailsErrorNoExisteUsuarioAplicacion = ps.getMessage(
				"plataformaErrores.registroUsuarioPush.TAG_MENSAJE_KO_NOEXISTE_USUARIO_PASSWORD_APLICACION", null);
		String errorAltaUsuario = ps.getMessage("plataformaErrores.registroUsuarioPush.TAG_ERROR_ALTA_USUARIO_PUSH", null);
		String detailsErrorPeticion = ps.getMessage(
				"plataformaErrores.registroUsuarioPush.TAG_MENSAJE_KO_ERROR_ALTA_USUARIO_PUSH", null);
    	
    	RegistroUsuarioResponse retorno = new RegistroUsuarioResponse();
    	retorno.setStatus(new ResponseStatusType());
    	
    	Integer resultadoAltaUsuario = null;
    		
    	try{
    		
    		boolean peticionCorrecta = evaluarAltaUsuario(nombreUsuario, servicioId, usuario, password, plataformaId, tokenUsuario, dispositivoId);
        	
        	if(peticionCorrecta){
        		
        		resultadoAltaUsuario = getUsuariosPushManager().altaUsuario(nombreUsuario, servicioId, usuario, password, plataformaId, tokenUsuario, dispositivoId, null, null);
        		
        		if(null!=resultadoAltaUsuario){
        			if(resultadoAltaUsuario>0){
        				retorno.getStatus().setStatusCode(statusCodeOK);
        				retorno.getStatus().setStatusText(tagOK);
        				retorno.getStatus().setDetails(tagMensajeOK);
        			} else if(resultadoAltaUsuario.equals(Integer.valueOf(-1)) || resultadoAltaUsuario.equals(Integer.valueOf(-10))){
        				retorno.getStatus().setStatusCode(errorAltaUsuarioPush);
        				retorno.getStatus().setStatusText(tagKO);
        				retorno.getStatus().setDetails(errorGeneral);
        			} else if(resultadoAltaUsuario.equals(Integer.valueOf(-2))){
        				retorno.getStatus().setStatusCode(errorUsuarioAplicacion);
        				retorno.getStatus().setStatusText(tagKO);
        				retorno.getStatus().setDetails(detailsErrorUsuarioAplicacion);
        			} else if(resultadoAltaUsuario.equals(Integer.valueOf(-3))){
        				retorno.getStatus().setStatusCode(errorServicio);
        				retorno.getStatus().setStatusText(tagKO);
        				retorno.getStatus().setDetails(detailsErrorServicio);
        			} else if(resultadoAltaUsuario.equals(Integer.valueOf(-4))){
        				retorno.getStatus().setStatusCode(errorNoExisteUsuarioAplicacion);
        				retorno.getStatus().setStatusText(tagKO);
        				retorno.getStatus().setDetails(detailsErrorNoExisteUsuarioAplicacion);
        			}
        		}
        		
        	} else {
        		retorno.getStatus().setStatusCode(errorAltaUsuario);
				retorno.getStatus().setStatusText(tagKO);
				retorno.getStatus().setDetails(detailsErrorPeticion);
        	}
    		
    		
    	} catch (Exception e) {
    		LOG.error("[RegistroUsuarioResponse.RegistroUsuario] Error Registrando Usuario Push", e);
			retorno.getStatus().setStatusCode(errorAltaUsuarioPush);
			retorno.getStatus().setStatusText(tagKO);
			retorno.getStatus().setDetails(errorGeneral);
		}
    	
    	return retorno;
    	
    }
    
    private boolean evaluarAltaUsuario(String nombreUsuario, String servicioId, String usuario, String password,
			String plataformaId, String tokenUsuario, String dispositivoId){
    	
    	if(evaluarParametro(nombreUsuario) && evaluarParametro(servicioId)
    			&& evaluarParametro(usuario) && evaluarParametro(password)
    			&& evaluarParametro(plataformaId) && evaluarParametro(tokenUsuario)){
    		return true;
    	} else {
    		return false;
    	}
    	
    }
    
    private boolean evaluarParametro(String parametro){
    	return (null != parametro && !parametro.isEmpty()) ? true : false;
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
	public void setReloadableResourceBundleMessageSource(ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource) {
		this.reloadableResourceBundleMessageSource = reloadableResourceBundleMessageSource;
	}

}
