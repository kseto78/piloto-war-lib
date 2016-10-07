package es.minhap.plataformamensajeria.iop.services.procesarSAMLResponse;

import org.apache.log4j.Logger;

import es.minhap.plataformamensajeria.iop.beans.PeticionClaveAuthResponse;
import es.minhap.plataformamensajeria.iop.beans.ResponseSAMLStatusType;
import es.minhap.plataformamensajeria.iop.beans.RespuestaSAMLResponse;
import es.minhap.plataformamensajeria.iop.beans.UsuariosPushBean;
import es.minhap.plataformamensajeria.iop.jdbc.AplicacionDAO;
import es.minhap.plataformamensajeria.iop.jdbc.UsuariosPushDAO;
import es.minhap.plataformamensajeria.iop.util.PlataformaErrores;

public class GestionSAMLResponseServiceImpl implements IGestionSAMLResponseService {
	static Logger logger = Logger.getLogger(GestionSAMLResponseServiceImpl.class);

	private static final Integer USUARIO_CORRECTO = 1;
	
	private static final String PLATAFORMA_IOS = "2";
	
	@Override
	public String insertarDatosUsuario(PeticionClaveAuthResponse peticion, String nombre, String NIF, String apellido1, String apellido2) {
		UsuariosPushDAO dao = new UsuariosPushDAO();
		AplicacionDAO aplicacionDao = new AplicacionDAO();
		RespuestaSAMLResponse respuesta = new RespuestaSAMLResponse();
		UsuariosPushBean usuario = null;
		String res = "";	
		try {
			if (datosNoValidos(peticion.getIdServicio(), peticion.getIdPlataforma(), peticion.getDispositivoId(), nombre, NIF)) {
				respuesta = generarRespuesta(null, null, null, null, PlataformaErrores.STATUSTEXT_KO, PlataformaErrores.COD_SAML_NO_VALIDADO,
						PlataformaErrores.DETAILS_SAML_NO_VALIDADO);
			} else {
				// comprobamos aplicacion
				aplicacionDao.beginTransaction();
				Integer existeUsuario = aplicacionDao.loginUsuario(peticion.getUsuario(), peticion.getPassword());
				aplicacionDao.endTransaction(true);
				if ((USUARIO_CORRECTO == existeUsuario) || (peticion.getIdPlataforma().equals(PLATAFORMA_IOS))) {
					dao.beginTransaction();
					usuario = dao.getDatosUsuario(peticion.getIdServicio(), peticion.getIdPlataforma(),
							peticion.getDispositivoId(), NIF);
					if (null != usuario) {
						// si nombre, apellido1 y apellido2 no están se
						// rellenan
						if (null == usuario.getNombre()) {
							usuario.setNombre(nombre);
							usuario.setApellido1(apellido1);
							usuario.setApellido2(apellido2);

							if (dao.actualizarUsuario(usuario))
								respuesta = generarRespuesta(NIF, nombre, apellido1, apellido2, PlataformaErrores.STATUSTEXT_OK, PlataformaErrores.STATUS_OK,
										PlataformaErrores.DETAILS_OK);
							else
								// error no se pudo actualizar el usuario
								respuesta = generarRespuesta(null, null, null, null, PlataformaErrores.STATUSTEXT_KO, PlataformaErrores.COD_ERROR_DATOS_NO_ACTUALIZADOS,
										PlataformaErrores.DETAILS_DISPOSITIVO_NO_ACTUALIZADO);
						} else {
							respuesta = generarRespuesta(NIF, nombre, apellido1, apellido2, PlataformaErrores.STATUSTEXT_OK, PlataformaErrores.STATUS_OK,
									PlataformaErrores.DETAILS_OK);
						}
					} else {
						// si no existe busco el iddispositivo e inserto una linea con los datos
						usuario = dao.comprobarDispositivoAplicacion(peticion.getIdServicio(), peticion.getDispositivoId(), peticion.getIdPlataforma());
						if (null != usuario) {
							usuario = completarUsuario(usuario, nombre, NIF, apellido1, apellido2);

							if (dao.insertarUsuarioPush(usuario))
								respuesta = generarRespuesta(NIF, nombre, apellido1, apellido2, PlataformaErrores.STATUSTEXT_OK, PlataformaErrores.STATUS_OK,
										PlataformaErrores.DETAILS_OK);
							else
								respuesta = generarRespuesta(null, null, null, null, PlataformaErrores.STATUSTEXT_KO, PlataformaErrores.COD_ERROR_DATOS_NO_ACTUALIZADOS,
										PlataformaErrores.DETAILS_DISPOSITIVO_NO_ACTUALIZADO);
						} else {
							// error de dispositivo no encontrado
							respuesta = generarRespuesta(null, null, null, null, PlataformaErrores.STATUSTEXT_KO, PlataformaErrores.COD_ERROR_DISPOSITIVO_NO_ENCONTRADO,
									PlataformaErrores.DETAILS_DISPOSITIVO_NO_ENCONTRADO);
						}
					}
					dao.endTransaction(true);
				}else {// no es un usuario correcto
					respuesta = generarRespuesta(null, null, null,null,PlataformaErrores.STATUSTEXT_KO, PlataformaErrores.COD_ERROR_SERVICIO, PlataformaErrores.DETAILS_ERROR_SERVICIO);
				}
			}
			res = respuesta.toXMLSMS(respuesta);
		} catch (Exception e) {
			dao.endTransaction(false);
			// e.printStackTrace();
			respuesta = generarRespuesta(null, null, null, null, PlataformaErrores.STATUSTEXT_KO, PlataformaErrores.COD_ERROR_GENERAL, PlataformaErrores.DETAILS_ERROR_GENERAL);
		} finally {
			dao.closeAll();
		}

		return res;
	}

	private boolean datosNoValidos(String servicio, String plataforma, String dispositivoId, String nombre, String NIF) {
		boolean res = false;

		if (null == servicio || servicio.length() <= 0 || null == plataforma || plataforma.length() <= 0 || null == dispositivoId || dispositivoId.length() <= 0 || null == nombre
				|| nombre.length() <= 0 || null == NIF || NIF.length() <= 0)
			return true;

		return res;
	}

	private RespuestaSAMLResponse generarRespuesta(String NIF, String nombre, String apellido1, String apellido2, String statustext, String codigo, String details) {
		RespuestaSAMLResponse res = new RespuestaSAMLResponse();
		ResponseSAMLStatusType status = new ResponseSAMLStatusType();

		if (null != apellido1 && apellido1.length() > 0)
			res.setApellido1(apellido1);
		if (null != apellido2 && apellido2.length() > 0)
			res.setApellido2(apellido2);
		if (null != NIF && NIF.length() > 0)
			res.setNif(NIF);
		if (null != nombre && nombre.length() > 0)
			res.setNombre(nombre);

		status.setDetails(details);
		status.setStatusCode(codigo);
		status.setStatusText(statustext);
		res.setStatus(status);

		return res;
	}

	private UsuariosPushBean completarUsuario(UsuariosPushBean usuario, String nombre, String NIF, String apellido1, String apellido2) {
		UsuariosPushBean u = new UsuariosPushBean();
		u.setApellido1(apellido1);
		u.setApellido2(apellido2);
		u.setDispositivoId(usuario.getDispositivoId());
		u.setNombreUsuario(NIF);
		u.setNombre(nombre);
		u.setPlataformaId(usuario.getPlataformaId());
		u.setServicioId(usuario.getServicioId());
		u.setTokenUsuario(usuario.getTokenUsuario());
		return u;
	}

}
