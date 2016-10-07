/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package es.minhap.plataformamensajeria.iop.services.usuariosplataformas;

import org.apache.log4j.Logger;

import es.minhap.plataformamensajeria.iop.beans.UsuariosXMLBean;
import es.minhap.plataformamensajeria.iop.jdbc.UsuariosPushDAO;

public class RegistroUsuarioPushServiceImpl implements
		IRegistroUsuarioPushService {

	static Logger logger = Logger
			.getLogger(RegistroUsuarioPushServiceImpl.class);

	static final String TAG_OK = "OK";
	static final String TAG_KO = "KO";
	static final String TAG_STATUSCODE_OK = "0000";

	static final String TAG_ERROR_PETICION_ALTA_USUARIO_PUSH = "3006";
	static final String TAG_ERROR_ALTA_USUARIO_PUSH = "3007";
	static final String TAG_ERROR_USUARIO_PASSWORD_APLICACION = "3008";
	static final String TAG_ERROR_SERVICIO = "3009";
	static final String TAG_ERROR_NOEXTSITE_USUARIO_PASSWORD_APLICACION = "3010";

	static final String TAG_MENSAJE_OK = "Peticion procesada correctamente";
	static final String TAG_MENSAJE_KO_ERROR_ALTA_USUARIO_PUSH = "La peticion no esta construida correctamente. Faltan campos obligatorios.";
	static final String TAG_MENSAJE_KO_GENERAL = "Se ha producido un error procesando la peticion";
	static final String TAG_MENSAJE_KO_USUARIO_PASSWORD_APLICACION = "El servicio no esta relacionado con la aplicacion del usuario/password";
	static final String TAG_MENSAJE_KO_SERVICIO = "El servicio no existe o se encuentra inactivo";
	static final String TAG_MENSAJE_KO_NOEXISTE_USUARIO_PASSWORD_APLICACION = "El usuario/password no pertenece a ninguna aplicacion";
	static final String TAG_MENSAJE_KO_NOEXISTE_PLATAFORMA = "La plataforma introducida no es correcta";
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * es.minhap.plataformamensajeria.ws.registrousuario.IRegistroUsuarioService
	 * #registroUsuario(es.minhap.plataformamensajeria.ws.registrousuario.
	 * RegistroUsuarioRequest parameters )*
	 */
	public RegistroUsuarioPushResponse registroUsuario(String nombreUsuario,
			String servicioId, String usuario, String password,
			String plataformaId, String tokenUsuario, String dispositivoId) {

		RegistroUsuarioPushResponse _return = new RegistroUsuarioPushResponse();
		_return.setStatus(new ResponseStatusType());

		UsuariosPushDAO dao = new UsuariosPushDAO();
		Integer resultadoAltaUsuario = null;

		dao.beginTransaction();

		try {

			if (dispositivoId == null)
				dispositivoId = completarDispositivo(servicioId);

			if (nombreUsuario == null)
				nombreUsuario = dispositivoId;

			boolean peticionCorrecta = evaluarAltaUsuario(nombreUsuario,
					servicioId, usuario, password, plataformaId, tokenUsuario,
					dispositivoId);

			if (peticionCorrecta) {

				resultadoAltaUsuario = dao.altaUsuario(nombreUsuario,
						servicioId, usuario, password, plataformaId,
						tokenUsuario, dispositivoId);

				if (null != resultadoAltaUsuario) {
					if (resultadoAltaUsuario > 0) {
						
						_return.getStatus().setStatusCode(TAG_STATUSCODE_OK);
						_return.getStatus().setStatusText(TAG_OK);
						_return.getStatus().setDetails(TAG_MENSAJE_OK);
						_return.setIdDispositivo(dispositivoId);
					} else if (resultadoAltaUsuario.equals(Integer.valueOf(-1))
							|| resultadoAltaUsuario
									.equals(Integer.valueOf(-10))) {
						
						_return.getStatus().setStatusCode(
								TAG_ERROR_ALTA_USUARIO_PUSH);
						_return.getStatus().setStatusText(TAG_KO);
						_return.getStatus().setDetails(TAG_MENSAJE_KO_GENERAL);
					} else if (resultadoAltaUsuario.equals(Integer.valueOf(-2))) {
						
						_return.getStatus().setStatusCode(
								TAG_ERROR_USUARIO_PASSWORD_APLICACION);
						_return.getStatus().setStatusText(TAG_KO);
						_return.getStatus().setDetails(
								TAG_MENSAJE_KO_USUARIO_PASSWORD_APLICACION);
					} else if (resultadoAltaUsuario.equals(Integer.valueOf(-3))) {
						
						_return.getStatus().setStatusCode(TAG_ERROR_SERVICIO);
						_return.getStatus().setStatusText(TAG_KO);
						_return.getStatus().setDetails(TAG_MENSAJE_KO_SERVICIO);
					} else if (resultadoAltaUsuario.equals(Integer.valueOf(-4))) {
						
						_return.getStatus()
								.setStatusCode(
										TAG_ERROR_NOEXTSITE_USUARIO_PASSWORD_APLICACION);
						_return.getStatus().setStatusText(TAG_KO);
						_return.getStatus()
								.setDetails(
										TAG_MENSAJE_KO_NOEXISTE_USUARIO_PASSWORD_APLICACION);
					}else if (resultadoAltaUsuario.equals(Integer.valueOf(-5))) {
					
						_return.getStatus()
								.setStatusCode(TAG_ERROR_ALTA_USUARIO_PUSH);
						_return.getStatus().setStatusText(TAG_KO);
						_return.getStatus()
								.setDetails(TAG_MENSAJE_KO_NOEXISTE_PLATAFORMA);
					}
				}

			} else {
			
				_return.getStatus().setStatusCode(
						TAG_ERROR_PETICION_ALTA_USUARIO_PUSH);
				_return.getStatus().setStatusText(TAG_KO);
				_return.getStatus().setDetails(
						TAG_MENSAJE_KO_ERROR_ALTA_USUARIO_PUSH);
			}

		} catch (Exception e) {
			
			_return.getStatus().setStatusCode(TAG_ERROR_ALTA_USUARIO_PUSH);
			_return.getStatus().setStatusText(TAG_KO);
			_return.getStatus().setDetails(TAG_MENSAJE_KO_GENERAL);
		} finally {
			if (_return != null
					&& _return.getStatus().getStatusCode()
							.equals(TAG_STATUSCODE_OK)) {
				dao.endTransaction(true);
			} else {
				dao.endTransaction(false);
			}
			dao.closeAll();
		}

		return _return;

	}

	private String completarDispositivo(String servicioId) {
		UsuariosPushDAO dao = null;
		Integer secuencia = null;

		// obtenemos la secuencia
		dao = new UsuariosPushDAO();
		dao.beginTransaction();
		try {
			secuencia = dao.getSecuencia();
			logger.debug("[Capturada secuencia dispositivo]");
			return servicioId + "_" + secuencia;
		} catch (Exception e) {
			logger.debug("Capturada secuencia dispositivo]");
		} finally {
			dao.endTransaction(false);
			dao.closeAll();
		}
		return null;
	}

	@Override
	public RegistroUsuarioPushResponse registroUsuario(
			UsuariosXMLBean usuariosXML) {

		return this.registroUsuario(usuariosXML.getNombreUsuario(),
				usuariosXML.getServicioId(), usuariosXML.getUsuario(),
				usuariosXML.getPassword(), usuariosXML.getPlataformaId(),
				usuariosXML.getTokenUsuario(), usuariosXML.getDispositivoId());
	}
	
	@Override
	public boolean eliminarUsuario(String token) {
		logger.debug("[RegistroUsuarioPushServiceImpl] Eliminando Usuario");
		boolean res = false;
		UsuariosPushDAO dao = new UsuariosPushDAO();
		
		try {
			dao.beginTransaction();
			res = dao.eliminarUsuario(token);
	    				
		} finally{
			dao.endTransaction(true);
			dao.closeAll();
		}
		return res;
	}

	private boolean evaluarAltaUsuario(String nombreUsuario, String servicioId,
			String usuario, String password, String plataformaId,
			String tokenUsuario, String dispositivoId) {

		if (evaluarParametro(nombreUsuario) && evaluarParametro(servicioId)
				&& evaluarParametro(usuario) && evaluarParametro(password)
				&& evaluarParametro(plataformaId)
				&& evaluarParametro(tokenUsuario)
				&& evaluarParametro(dispositivoId)) {
			return true;
		} else {
			return false;
		}

	}

	private boolean evaluarParametro(String parametro) {

		if (null != parametro && !parametro.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	

}
