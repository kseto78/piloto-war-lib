/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package es.minhap.plataformamensajeria.iop.services.usuariosplataformas;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import es.minhap.common.properties.PropertiesServices;
import es.minhap.plataformamensajeria.iop.beans.UsuariosXMLBean;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorUsuariosPush;
import es.minhap.plataformamensajeria.iop.manager.TblUsuariosPushManager;

/**
 * 
 * @author everis
 *
 */
@Service("registroUsuarioPushImpl")
public class RegistroUsuarioPushServiceImpl implements IRegistroUsuarioPushService {

	private static final Logger LOG = Logger.getLogger(RegistroUsuarioPushServiceImpl.class);

	@Resource
	private TblUsuariosPushManager usuariosPushManager;

	@Resource
	private QueryExecutorUsuariosPush queryExecutorUsuariosPush;

	@Resource(name = "reloadableResourceBundleMessageSource")
	private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;

	@Override
	public RegistroUsuarioPushResponse registroUsuario(String nombreUsuario, String servicioId, String usuario,
			String password, String plataformaId, String tokenUsuario, String dispositivoId) {

		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
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
		String errorNoExistePlataforma = ps.getMessage(
				"plataformaErrores.registroUsuarioPush.TAG_MENSAJE_KO_NOEXISTE_PLATAFORMA", null);
		String errorPeticion = ps.getMessage("plataformaErrores.registroUsuarioPush.TAG_ERROR_PETICION_ALTA_USUARIO_PUSH", null);
		String detailsErrorPeticion = ps.getMessage(
				"plataformaErrores.registroUsuarioPush.TAG_MENSAJE_KO_ERROR_ALTA_USUARIO_PUSH", null);

		RegistroUsuarioPushResponse retorno = new RegistroUsuarioPushResponse();
		retorno.setStatus(new ResponseStatusType());
		Integer resultadoAltaUsuario = null;

		try {

			if (dispositivoId == null)
				dispositivoId = completarDispositivo(servicioId);

			if (nombreUsuario == null)
				nombreUsuario = dispositivoId;

			boolean peticionCorrecta = evaluarAltaUsuario(nombreUsuario, servicioId, usuario, password, plataformaId,
					tokenUsuario, dispositivoId);

			if (peticionCorrecta) {

				resultadoAltaUsuario = usuariosPushManager.altaUsuario(nombreUsuario, servicioId, usuario, password,
						plataformaId, tokenUsuario, dispositivoId);

				if (null != resultadoAltaUsuario) {
					if (resultadoAltaUsuario > 0) {

						retorno.getStatus().setStatusCode(statusCodeOK);
						retorno.getStatus().setStatusText(tagOK);
						retorno.getStatus().setDetails(tagMensajeOK);
						retorno.setIdDispositivo(dispositivoId);
					} else if (resultadoAltaUsuario.equals(Integer.valueOf(-1))
							|| resultadoAltaUsuario.equals(Integer.valueOf(-10))) {

						retorno.getStatus().setStatusCode(errorAltaUsuarioPush);
						retorno.getStatus().setStatusText(tagKO);
						retorno.getStatus().setDetails(errorGeneral);
					} else if (resultadoAltaUsuario.equals(Integer.valueOf(-2))) {

						retorno.getStatus().setStatusCode(errorUsuarioAplicacion);
						retorno.getStatus().setStatusText(tagKO);
						retorno.getStatus().setDetails(detailsErrorUsuarioAplicacion);
					} else if (resultadoAltaUsuario.equals(Integer.valueOf(-3))) {

						retorno.getStatus().setStatusCode(errorServicio);
						retorno.getStatus().setStatusText(tagKO);
						retorno.getStatus().setDetails(detailsErrorServicio);
					} else if (resultadoAltaUsuario.equals(Integer.valueOf(-4))) {

						retorno.getStatus().setStatusCode(errorNoExisteUsuarioAplicacion);
						retorno.getStatus().setStatusText(tagKO);
						retorno.getStatus().setDetails(detailsErrorNoExisteUsuarioAplicacion);
					} else if (resultadoAltaUsuario.equals(Integer.valueOf(-5))) {

						retorno.getStatus().setStatusCode(errorAltaUsuario);
						retorno.getStatus().setStatusText(tagKO);
						retorno.getStatus().setDetails(errorNoExistePlataforma);
					}
				}

			} else {

				retorno.getStatus().setStatusCode(errorPeticion);
				retorno.getStatus().setStatusText(tagKO);
				retorno.getStatus().setDetails(detailsErrorPeticion);
			}

		} catch (Exception e) {
			LOG.error("[RegistroUsuarioPushServiceImpl.RegistroUsuario] Error Registrando Usuario Push", e);
			retorno.getStatus().setStatusCode(errorAltaUsuarioPush);
			retorno.getStatus().setStatusText(tagKO);
			retorno.getStatus().setDetails(errorGeneral);
		}

		return retorno;

	}

	private String completarDispositivo(String servicioId) {
		Integer secuencia;

		// obtenemos la secuencia
		secuencia = queryExecutorUsuariosPush.getNextDispositivo();
		LOG.debug("[Capturada secuencia dispositivo]");
		return servicioId + "_" + secuencia;

	}

	@Override
	public RegistroUsuarioPushResponse registroUsuario(UsuariosXMLBean usuariosXML) {

		return this.registroUsuario(usuariosXML.getNombreUsuario(), usuariosXML.getServicioId(),
				usuariosXML.getUsuario(), usuariosXML.getPassword(), usuariosXML.getPlataformaId(),
				usuariosXML.getTokenUsuario(), usuariosXML.getDispositivoId());
	}

	@Override
	public boolean eliminarUsuario(String token) {
		LOG.debug("[RegistroUsuarioPushServiceImpl] Eliminando Usuario");
		boolean res = false;
		
		try {
			res = usuariosPushManager.eliminarUsuario(token);

		} catch(Exception e){
			LOG.error("[RegistroUsuarioPushServiceImpl.eliminarUsuario] Error Eliminando Usuario", e);
		}
			
		return res;
	}

	private boolean evaluarAltaUsuario(String nombreUsuario, String servicioId, String usuario, String password,
			String plataformaId, String tokenUsuario, String dispositivoId) {

		if (evaluarParametro(nombreUsuario) && evaluarParametro(servicioId) && evaluarParametro(usuario)
				&& evaluarParametro(password) && evaluarParametro(plataformaId) && evaluarParametro(tokenUsuario)
				&& evaluarParametro(dispositivoId)) {
			return true;
		} else {
			return false;
		}

	}

	private boolean evaluarParametro(String parametro) {
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
	 * @return the queryExecutorUsuariosPush
	 */
	public QueryExecutorUsuariosPush getQueryExecutorUsuariosPush() {
		return queryExecutorUsuariosPush;
	}

	/**
	 * @param queryExecutorUsuariosPush the queryExecutorUsuariosPush to set
	 */
	public void setQueryExecutorUsuariosPush(QueryExecutorUsuariosPush queryExecutorUsuariosPush) {
		this.queryExecutorUsuariosPush = queryExecutorUsuariosPush;
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
