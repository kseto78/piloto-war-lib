package es.minhap.plataformamensajeria.iop.services.actualizarPasswordCorreo;

import javax.annotation.Resource;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import es.minhap.common.properties.PropertiesServices;
import es.minhap.plataformamensajeria.iop.beans.PeticionActualizarPasswordCorreo;
import es.minhap.plataformamensajeria.iop.beans.ResponseStatusTypeActualizarPasswordCorreo;
import es.minhap.plataformamensajeria.iop.beans.RespuestaActualizarPasswordCorreo;
import es.minhap.plataformamensajeria.iop.manager.TblParametrosServidorManager;
import es.minhap.plataformamensajeria.iop.services.exceptions.PlataformaBusinessException;

/**
 * 
 * @author everis
 * 
 */
@Service("actualizarPasswordCorreoImpl")
public class ActualizarPasswordCorreoImpl implements IActualizarPasswordCorreoService {

	private static final Logger LOG = LoggerFactory.getLogger(ActualizarPasswordCorreoImpl.class);
	
	@Resource(name="tblParametrosServidorManagerImpl")
	private TblParametrosServidorManager tblParametrosServidorManager;
	
	@Resource(name = "reloadableResourceBundleMessageSource")
	private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;

	@Override
	public String consultarEstado(PeticionActualizarPasswordCorreo peticion) {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
				
		RespuestaActualizarPasswordCorreo respuesta = checkCampos(peticion, ps);
		
		try{
			//comprobamos todos vienen todos los campos
			if (null != respuesta){
				//faltan campos obligatorios
				return respuesta.toXML(respuesta);
			}else{
				//actualizamos la contrasena y comprobamos conexion
				respuesta= tblParametrosServidorManager.checkActualizarPassword(peticion,ps);
				if (null != respuesta){
					return respuesta.toXML(respuesta);
				}else{
					String statusTextKO = ps.getMessage("plataformaErrores.ActualizarPasswordCorreo.OK", null);
					String statusCodeKO = ps.getMessage("plataformaErrores.ActualizarPasswordCorreo.COD_ERROR_GENERAL", null);
					String detailsKO = ps.getMessage("plataformaErrores.ActualizarPasswordCorreo.DES_ERROR_GENERAL", null);
					respuesta = generarSalida(statusTextKO, statusCodeKO, detailsKO );
				}
			}
		}catch(Exception e){
			LOG.error("[ActualizarPasswordCorreoImpl] Generando XML de respuesta", e);
			String statusTextKO = ps.getMessage("plataformaErrores.ActualizarPasswordCorreo.KO", null);
			String statusCodeKO = ps.getMessage("plataformaErrores.ActualizarPasswordCorreo.COD_ERROR_GENERAL", null);
			String detailsKO = ps.getMessage("plataformaErrores.ActualizarPasswordCorreo.DES_ERROR_GENERAL", null);
			respuesta = generarSalida(statusTextKO, statusCodeKO, detailsKO );
			try {
				return respuesta.toXML(respuesta);
			} catch (PlataformaBusinessException e1) {
				LOG.error("[ActualizarPasswordCorreoImpl] Generando XML de respuesta", e);
			}
		}
		return null;
	}

	private RespuestaActualizarPasswordCorreo checkCampos(PeticionActualizarPasswordCorreo peticion, PropertiesServices ps) {
		String statusTextKO = ps.getMessage("plataformaErrores.ActualizarPasswordCorreo.KO", null);
		
		String statusCodeCamposNulos = ps.getMessage("plataformaErrores.ActualizarPasswordCorreo.COD_ERROR_CAMPOS_OBLIGATORIOS", null);
		String detailsCamposNulos = ps.getMessage("plataformaErrores.ActualizarPasswordCorreo.DES_ERROR_CAMPOS_OBLIGATORIOS", null);
		String statusCodeCamposCompletos = ps.getMessage("plataformaErrores.ActualizarPasswordCorreo.COD_ERROR_CAMPOS_COMPLETOS", null);
		String detailsCamposCompletos = ps.getMessage("plataformaErrores.ActualizarPasswordCorreo.DES_ERROR_CAMPOS_COMPLETOS", null);
		String statusCodeNoEmail = ps.getMessage("plataformaErrores.ActualizarPasswordCorreo.COD_ERROR_NO_EMAIL", null);
		String detailsNoEmail = ps.getMessage("plataformaErrores.ActualizarPasswordCorreo.DES_ERROR_NO_EMAIL", null);
		String statusCodeNoNoreply = ps.getMessage("plataformaErrores.ActualizarPasswordCorreo.COD_ERROR_NO_CUENTA_NOREPLY", null);
		String detailsNoNoreply = ps.getMessage("plataformaErrores.ActualizarPasswordCorreo.DES_ERROR_NO_CUENTA_NOREPLY", null);
		
		RespuestaActualizarPasswordCorreo res = null;
		
		//Comprobar campos null
		if(peticion.getUsuario() == null || peticion.getPassword_new() == null){
			return generarSalida(statusTextKO, statusCodeCamposNulos, detailsCamposNulos );
		}
		
		//campos vacios
		if(peticion.getUsuario().isEmpty() || peticion.getPassword_new().isEmpty()){
			return generarSalida(statusTextKO, statusCodeCamposCompletos, detailsCamposCompletos );
		}
		
		//es email
		if (!isValidEmailAddress(peticion.getUsuario())){
			return generarSalida(statusTextKO, statusCodeNoEmail, detailsNoEmail );
		}
		
		//recojemos por property el nombre de la cuenta noreply
		String nombreCuentaNoreply = ps.getMessage("plataformaErrores.ActualizarPasswordCorreo.NombreCuenta", null);
		LOG.info("nombreCuentaNoreply: "+nombreCuentaNoreply);
		String[] cuentasCorreo = nombreCuentaNoreply.split(";");
		LOG.info("cuentasCorreo: "+cuentasCorreo[0] +" - " + cuentasCorreo[1]);
		int contador = 0;	
		for (String cuenta : cuentasCorreo) {
			if (peticion.getUsuario().toLowerCase().contains(cuenta)){
				contador++;
			}		
		}
		LOG.info("Contador: "+contador);
		if (contador != 1){
			return generarSalida(statusTextKO, statusCodeNoNoreply, detailsNoNoreply );	
		}else{
			return res;
		}
	}

	public static boolean isValidEmailAddress(String email) {
		   boolean result = true;
		   try {
		      InternetAddress emailAddr = new InternetAddress(email);
		      emailAddr.validate();
		   } catch (AddressException ex) {
		      result = false;
		   }
		   return result;
		}
	
	private RespuestaActualizarPasswordCorreo generarSalida(String statusTextKO,
			String statusCodeCamposNulos, String detailsCamposNulos) {
		RespuestaActualizarPasswordCorreo res = new RespuestaActualizarPasswordCorreo();
		ResponseStatusTypeActualizarPasswordCorreo status = new ResponseStatusTypeActualizarPasswordCorreo();
		
		status.setDetails(detailsCamposNulos);
		status.setStatusCode(statusCodeCamposNulos);
		status.setStatusText(statusTextKO);
		res.setStatus(status);
		return res;
	}

	
}
