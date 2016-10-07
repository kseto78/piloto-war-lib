package es.minhap.plataformamensajeria.iop.services.procesarSAMLResponse;

import org.apache.log4j.Logger;

import es.minhap.plataformamensajeria.iop.beans.PeticionClaveAuthRequest;
import es.minhap.plataformamensajeria.iop.beans.ResponseSAMLStatusType;
import es.minhap.plataformamensajeria.iop.beans.RespuestaSAMLResponse;
import es.minhap.plataformamensajeria.iop.beans.UsuariosPushBean;
import es.minhap.plataformamensajeria.iop.jdbc.AplicacionDAO;
import es.minhap.plataformamensajeria.iop.jdbc.UsuariosPushDAO;
import es.minhap.plataformamensajeria.iop.util.PlataformaErrores;

public class GestionSAMLRequestServiceImpl implements IGestionSAMLRequestService {
	static Logger logger = Logger.getLogger(GestionSAMLRequestServiceImpl.class);

	private static final Integer USUARIO_CORRECTO = 1;

	@Override
	public String comprobarDatosUsuario(PeticionClaveAuthRequest peticion) {
		UsuariosPushDAO dao = new UsuariosPushDAO();
		AplicacionDAO aplicacionDao = new AplicacionDAO();
		RespuestaSAMLResponse respuesta = new RespuestaSAMLResponse();
		UsuariosPushBean usuario = null;
		String res = "";
		try {
			if (datosNoValidos(peticion.getUsuario(), peticion.getPassword(), peticion.getIdServicio(), peticion.getIdDispositivo(), peticion.getIdPlataforma())) {
				respuesta = generarRespuesta(PlataformaErrores.STATUSTEXT_KO, PlataformaErrores.COD_ERROR_PETICION, PlataformaErrores.DETAILS_ERROR_PETICION);
			} else {
				// comprobamos aplicacion
				aplicacionDao.beginTransaction();
				Integer existeUsuario = aplicacionDao.loginUsuario(peticion.getUsuario(), peticion.getPassword());
				aplicacionDao.endTransaction(true);
				if (USUARIO_CORRECTO == existeUsuario) {
					// comprobamos si existe el usuario con sus datos
					usuario = dao.comprobarDispositivoAplicacion(peticion.getIdServicio(), peticion.getIdDispositivo(), peticion.getIdPlataforma());
					if (null != usuario)
						respuesta = generarRespuesta(PlataformaErrores.STATUSTEXT_OK, PlataformaErrores.STATUS_OK, PlataformaErrores.DETAILS_OK);
					else
						respuesta = generarRespuesta(PlataformaErrores.STATUSTEXT_KO, PlataformaErrores.COD_ERROR_DISPOSITIVO_NO_ENCONTRADO,
								PlataformaErrores.DETAILS_DISPOSITIVO_NO_ENCONTRADO);

					dao.endTransaction(true);
				} else {// no es un usuario correcto
					respuesta = generarRespuesta(PlataformaErrores.STATUSTEXT_KO, PlataformaErrores.COD_ERROR_SERVICIO, PlataformaErrores.DETAILS_ERROR_SERVICIO);
				}
			}
			res = respuesta.toXMLSMS(respuesta);
		} catch (Exception e) {
			dao.endTransaction(false);
			aplicacionDao.endTransaction(false);
			// e.printStackTrace();
			respuesta = generarRespuesta(PlataformaErrores.STATUSTEXT_KO, PlataformaErrores.COD_ERROR_GENERAL, PlataformaErrores.DETAILS_OK);
		} finally {
			dao.closeAll();
			aplicacionDao.closeAll();
		}

		return res;
	}

	private boolean datosNoValidos(String usuario, String password, String servicio, String dispositivo, String plataforma) {
		boolean res = false;

		if (null == usuario || usuario.length() <= 0 || null == password || password.length() <= 0 || null == servicio || servicio.length() <= 0 || null == dispositivo
				|| dispositivo.length() <= 0 || null == plataforma || plataforma.length() <= 0)
			return true;

		return res;
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
