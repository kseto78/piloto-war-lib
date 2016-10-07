package es.minhap.plataformamensajeria.iop.services.procesarSAMLResponse;

import java.util.List;

import org.apache.log4j.Logger;

import es.minhap.plataformamensajeria.iop.beans.PeticionClaveAuthRequest;
import es.minhap.plataformamensajeria.iop.beans.ResponseSAMLStatusType;
import es.minhap.plataformamensajeria.iop.beans.RespuestaSAMLResponse;
import es.minhap.plataformamensajeria.iop.beans.UsuariosPushBean;
import es.minhap.plataformamensajeria.iop.jdbc.AplicacionDAO;
import es.minhap.plataformamensajeria.iop.jdbc.UsuariosPushDAO;
import es.minhap.plataformamensajeria.iop.util.PlataformaErrores;

public class GetAuthServiceImpl implements IGetAuthService {
	static Logger logger = Logger.getLogger(GetAuthServiceImpl.class);

	private static final Integer USUARIO_CORRECTO = 1;
		
	@Override
	public String getDatosUsuario(PeticionClaveAuthRequest peticion) {
		UsuariosPushDAO dao = new UsuariosPushDAO();
		AplicacionDAO aplicacionDao = new AplicacionDAO();
		RespuestaSAMLResponse respuesta = new RespuestaSAMLResponse();
		List<UsuariosPushBean> listaUsuarios = null;
		String res = "";	
		try {
			if (datosNoValidos(peticion.getIdServicio(), peticion.getIdPlataforma(), peticion.getIdDispositivo())) {
				respuesta = generarRespuesta(null, null, null, null, PlataformaErrores.STATUSTEXT_KO, PlataformaErrores.COD_ERROR_PETICION,
						PlataformaErrores.DETAILS_ERROR_PETICION);
			} else {
				// comprobamos aplicacion
				aplicacionDao.beginTransaction();
				Integer existeUsuario = aplicacionDao.loginUsuario(peticion.getUsuario(), peticion.getPassword());
				aplicacionDao.endTransaction(true);
				if (USUARIO_CORRECTO == existeUsuario) {
					dao.beginTransaction();
					listaUsuarios = dao.getListaUsuarios(peticion.getIdServicio(), peticion.getIdPlataforma(), peticion.getIdDispositivo());
					dao.endTransaction(true);
					respuesta = analizarListaUsusarios(listaUsuarios, peticion.getIdDispositivo());
					
				}else {// no es un usuario correcto
					respuesta = generarRespuesta(null, null, null,null,PlataformaErrores.STATUSTEXT_KO, PlataformaErrores.COD_ERROR_SERVICIO, PlataformaErrores.DETAILS_ERROR_SERVICIO);
				}
			}
			res = respuesta.toXMLSMS(respuesta);
		} catch (Exception e) {
			logger.error("[GetAuthServiceImpl.getDatosUsuario] Obteniendo datos usuario",e);
			dao.endTransaction(false);
			respuesta = generarRespuesta(null, null, null, null, PlataformaErrores.STATUSTEXT_KO, PlataformaErrores.COD_ERROR_GENERAL, PlataformaErrores.DETAILS_ERROR_GENERAL);
		} finally {
			dao.closeAll();
		}

		return res;
	}
	
	private RespuestaSAMLResponse analizarListaUsusarios(List<UsuariosPushBean> listaUsuarios, String dispositivoId) {
		RespuestaSAMLResponse res;
		UsuariosPushBean usuario;
		if (listaUsuarios.size() == 1){
			usuario = listaUsuarios.get(0);
			res = respuestaCorrecta(usuario);
		}else{
			res = respuestaIncorrecta();
		}
		return res;
	}

	private RespuestaSAMLResponse respuestaIncorrecta() {
		RespuestaSAMLResponse res;
		//error que no se encuentra
		res = generarRespuesta(null, null,null, null,PlataformaErrores.STATUSTEXT_KO, PlataformaErrores.COD_ERROR_USUARIO_DISPOSITIVO,
				PlataformaErrores.DETAILS_ERROR_USUARIO_DISPOSITIVO);
		return res;
	}

	private RespuestaSAMLResponse respuestaCorrecta(UsuariosPushBean usuario) {
		RespuestaSAMLResponse res;
		//si son diferentes lo doy por valido. Se supone que el nombre de usuario sería el DNI
		res = generarRespuesta(usuario.getNombreUsuario(), usuario.getNombre(), 
				usuario.getApellido1(), usuario.getApellido2(),PlataformaErrores.STATUSTEXT_OK, PlataformaErrores.STATUS_OK,
				PlataformaErrores.DETAILS_OK);
		return res;
	}

	private boolean datosNoValidos(String servicio, String plataforma, String dispositivoId) {
		boolean res = false;

		if (null == servicio || servicio.length() <= 0 || null == plataforma || plataforma.length() <= 0 || null == dispositivoId || dispositivoId.length() <= 0)
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

}
