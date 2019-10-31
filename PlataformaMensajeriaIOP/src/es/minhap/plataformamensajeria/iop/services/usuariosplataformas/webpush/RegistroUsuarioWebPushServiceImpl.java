/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package es.minhap.plataformamensajeria.iop.services.usuariosplataformas.webpush;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import es.minhap.common.entity.TextComparator;
import es.minhap.common.properties.PropertiesServices;
import es.minhap.plataformamensajeria.iop.beans.PeticionRegistroUsuarioWebPush;
import es.minhap.plataformamensajeria.iop.beans.ResponseStatusTypeRegistroWebPush;
import es.minhap.plataformamensajeria.iop.beans.RespuestaRegistroWebPush;
import es.minhap.plataformamensajeria.iop.manager.TblAplicacionesManager;
import es.minhap.plataformamensajeria.iop.manager.TblServiciosManager;
import es.minhap.plataformamensajeria.iop.manager.TblUsuariosWebPushManager;
import es.minhap.plataformamensajeria.iop.util.Utils;
import es.minhap.sim.model.TblAplicaciones;
import es.minhap.sim.model.TblServicios;
import es.minhap.sim.model.TblUsuariosWebPush;
import es.minhap.sim.query.TblAplicacionesQuery;
import es.minhap.sim.query.TblServiciosQuery;
import es.minhap.sim.query.TblUsuariosWebPushQuery;

/**
 * 
 * @author everis
 * 
 */
@Service("registroUsuarioWebPushImpl")
public class RegistroUsuarioWebPushServiceImpl implements IRegistroUsuarioWebPushService {

	protected static final String R_CONST_1 = "plataformaErrores.registroWebPush.STATUSTEXT_OK";

	protected static final String R_CONST_2 = "plataformaErrores.registroWebPush.CODOK";

	protected static final String R_CONST_3 = "plataformaErrores.registroWebPush.DESCOK";

	protected static final String R_CONST_4 = "RegistroUsuarioWebPush.eliminarUsuario";

	private static final String PLATAFORMA_ERRORES_REGISTRO_WEB_PUSH_STATUSTEXT_KO = "plataformaErrores.registroWebPush.STATUSTEXT_KO";

	private static final Logger LOG = LoggerFactory.getLogger(RegistroUsuarioWebPushServiceImpl.class);

	private static final Object ACCIONALTA = "A";

	@Resource(name = "tblUsuariosWebPushManagerImpl")
	private TblUsuariosWebPushManager usuariosWebPushManager;
	
	@Autowired
	TblAplicacionesManager aplicacionesManager;
	
	@Autowired
	TblServiciosManager serviciosManager;

	@Resource(name = "reloadableResourceBundleMessageSource")
	private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;

	@Override
	public RespuestaRegistroWebPush registroUsuario(PeticionRegistroUsuarioWebPush peticion) {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		String statusKO = ps.getMessage(PLATAFORMA_ERRORES_REGISTRO_WEB_PUSH_STATUSTEXT_KO, null);
		String detailsKO = ps.getMessage("plataformaErrores.registroWebPush.DETAILS_ERROR_GENERAL", null);
		String codKO = ps.getMessage("plataformaErrores.registroWebPush.COD_ERROR_GENERAL", null);
		String detailsKOPeticion = ps.getMessage("plataformaErrores.registroWebPush.DESC_0020_GENRAL", null);
		String codKOPeticion = ps.getMessage("plataformaErrores.registroWebPush.COD_0020_GENERAL", null);
		String detailsOK = ps.getMessage(R_CONST_3, null);
		String codOK = ps.getMessage(R_CONST_2, null);
		String statusOK = ps.getMessage(R_CONST_1, null);
		
		RespuestaRegistroWebPush retorno = new RespuestaRegistroWebPush();
		
		try {

			//asignamos un usuario
			if (peticion.getIdUsuario() == null){
				peticion.setIdUsuario(completarDispositivo(true));
			}

			boolean peticionCorrecta = evaluarAltaUsuario(peticion.getAuth(),peticion.getEndpoint(),peticion.getIdServicio(), peticion.getKey());			
			
			if (peticionCorrecta) {
				//Comprobamos servicio(existe y activo)  y aplicacion
				retorno = comprobarServicioAplicacion(peticion.getIdServicio(), peticion.getUsuario(), peticion.getPassword(), ps);
				
				if (null == retorno && peticion.getAccion().equals(ACCIONALTA)){
					retorno = insertarUsuario(peticion, ps);
				}else{
					//Boolean eliminado = eliminarUsuario(peticion.getEndpoint(), peticion.getAuth(), peticion.getKey()) ;
					Boolean eliminado = eliminarUsuarioServicio(peticion);
					if (null != eliminado && eliminado){
						retorno = generarRespuesta(statusOK, codOK, detailsOK);
					}else{
						 generarRespuesta(statusKO, codKO, detailsKO);
					}
				}
				
			}else{
				retorno = generarRespuesta(statusKO, codKOPeticion, detailsKOPeticion);
			}

		} catch (Exception e) {
			LOG.error("[RegistroUsuarioPushServiceImpl.RegistroUsuario] Error Registrando Usuario Push", e);
			retorno = generarRespuesta(statusKO, codKO, detailsKO);
		}

		return retorno;

	}

	@Override
	public boolean eliminarUsuario(String endpoint, String auth, String pdh) {
		try {
			TblUsuariosWebPushQuery query = new TblUsuariosWebPushQuery();
			query.setAuth(auth);
			query.setAuthComparator(TextComparator.EQUALS);
			query.setEndpoint(endpoint);
			query.setEndpointComparator(TextComparator.EQUALS);
			query.setPdh(pdh);
			query.setPdhComparator(TextComparator.EQUALS);
			usuariosWebPushManager.establecerUsuarioEliminado(query);
			return true;
		} catch (Exception e) {
			LOG.error(R_CONST_4, e);
			return false;
		}

	}
	
	@Override
	public boolean eliminarUsuarioServicio(PeticionRegistroUsuarioWebPush peticion) {
		try {
			TblUsuariosWebPushQuery query = new TblUsuariosWebPushQuery();
			query.setEndpoint(peticion.getEndpoint().substring(0,30));
			query.setEndpointComparator(TextComparator.CONTAINS);
			query.setUsuarioid(peticion.getIdUsuario());
			query.setUsuarioidComparator(TextComparator.EQUALS);
			TblServiciosQuery tblServiciosQuery = new TblServiciosQuery();
			tblServiciosQuery.setServicioid(Long.parseLong(peticion.getIdServicio()));
			query.setTblServicios(tblServiciosQuery);
			
			usuariosWebPushManager.establecerUsuarioEliminado(query);
			return true;
		} catch (Exception e) {
			LOG.error(R_CONST_4, e);
			return false;
		}

	}
	
	private RespuestaRegistroWebPush insertarUsuario(PeticionRegistroUsuarioWebPush peticion, PropertiesServices ps) {
		String detailsOK = ps.getMessage(R_CONST_3, null);
		String codOK = ps.getMessage(R_CONST_2, null);
		String statusOK = ps.getMessage(R_CONST_1, null);
		
		//Comprobamos si el usuario ya existe en BBDD con todos los datos iguales
		TblUsuariosWebPush uwp = comprobarExisteUsuario(peticion);
		if (null == uwp){
			TblUsuariosWebPush usuario = new TblUsuariosWebPush();
			usuario.setAuth(peticion.getAuth());
			usuario.setEndpoint(peticion.getEndpoint());
			usuario.setFechacreacion(new Date());
			usuario.setPdh(peticion.getKey());
			usuario.setTblServicios(serviciosManager.getServicio(Long.parseLong(peticion.getIdServicio())));
			usuario.setUsuarioid(peticion.getIdUsuario());
			
			usuariosWebPushManager.insertUsuario(usuario);
			return generarRespuesta(statusOK, codOK, detailsOK);
			
		}else{
			uwp.setEliminado(null);
			uwp.setAuth(peticion.getAuth());
			uwp.setEndpoint(peticion.getEndpoint());
			uwp.setPdh(peticion.getKey());
			uwp.setFechamedificacion(new Date());
			usuariosWebPushManager.updateUsuario(uwp);
			return generarRespuesta(statusOK, codOK, detailsOK);
		}
		
	}

	private TblUsuariosWebPush comprobarExisteUsuario(PeticionRegistroUsuarioWebPush peticion){
		TblUsuariosWebPushQuery query = new TblUsuariosWebPushQuery();
//		query.setAuth(peticion.getAuth());
//		query.setAuthComparator(TextComparator.EQUALS);
		query.setEndpoint(peticion.getEndpoint().substring(0,30));
		query.setEndpointComparator(TextComparator.CONTAINS);
		query.setUsuarioid(peticion.getIdUsuario());
		query.setUsuarioidComparator(TextComparator.EQUALS);
		TblServiciosQuery tblServiciosQuery = new TblServiciosQuery();
		tblServiciosQuery.setServicioid(Long.parseLong(peticion.getIdServicio()));
		query.setTblServicios(tblServiciosQuery);
//		query.setPdh(peticion.getKey());
//		query.setPdhComparator(TextComparator.EQUALS);
		
		TblServiciosQuery queryServicios = new TblServiciosQuery();
		queryServicios.setServicioid(Long.parseLong(peticion.getIdServicio()));
		
		query.setTblServicios(queryServicios);
		
		return usuariosWebPushManager.getUsuarioPushByQuery(query);
	}
	
	private RespuestaRegistroWebPush comprobarServicioAplicacion(String idServicio, String usuario, String password, PropertiesServices ps) {
		String errorAplicacion = ps.getMessage("plataformaErrores.registroWebPush.DESC_0008_GENERAL", null);
		String codErrorAplicacion = ps.getMessage("plataformaErrores.registroWebPush.COD_0008_GENERAL", null);
		String errorServicio = ps.getMessage("plataformaErrores.registroWebPush.COD_0016_GENERAL", null);
		String codErrorServicio = ps.getMessage("plataformaErrores.registroWebPush.DESC_0016_GENERAL", null);
		String statusKO = ps.getMessage(PLATAFORMA_ERRORES_REGISTRO_WEB_PUSH_STATUSTEXT_KO, null);
		
		RespuestaRegistroWebPush res = null;
		Long servicioId = null;
		
		
		//comprobamos aplicacion
		TblAplicacionesQuery q = new TblAplicacionesQuery();
		q.setUsuario(usuario);
		q.setPassword(Utils.encode64(password));
		q.setActivo(true);

		List<TblAplicaciones> listaAplicaciones = aplicacionesManager.getAplicaciones(q);
		
		if (null != listaAplicaciones && listaAplicaciones.size() == 1){
			try{
				servicioId = Long.parseLong(idServicio);
			}catch(NumberFormatException e){
				// TODO logger.warn(e.getMessage(), e);
				return generarRespuesta(statusKO, errorServicio, codErrorServicio);
			}
			TblServiciosQuery servicio = new TblServiciosQuery();
			servicio.setServicioid(servicioId);
			servicio.addTblAplicacionesIdIn(listaAplicaciones.get(0));
			List<TblServicios> listaServicios = serviciosManager.getServicios(servicio);
			if (null == listaServicios || listaServicios.isEmpty()){
				return generarRespuesta(statusKO, errorServicio, codErrorServicio);
			}
			
		}else{
			return generarRespuesta(statusKO, errorAplicacion, codErrorAplicacion);
		}
				
		return res;
	}


	private String completarDispositivo(boolean comprobarUnico) {
		SecureRandom sr = new SecureRandom();
		String codigo = new BigInteger(160, sr).toString(32);
		while (comprobarUnico && !usuariosWebPushManager.comprobarDispositivoRepetido(codigo)) {
			codigo = new BigInteger(160, sr).toString(32);
		}
		return new BigInteger(160, sr).toString(32);

	}

	private RespuestaRegistroWebPush generarRespuesta(String statusText, String statusCode, String details){
		RespuestaRegistroWebPush res = new RespuestaRegistroWebPush();
		ResponseStatusTypeRegistroWebPush status = new ResponseStatusTypeRegistroWebPush();
		
		status.setDetails(details);
		status.setStatusCode(statusCode);
		status.setStatusText(statusText);
		res.setStatus(status);
		
		return res;
	}



	private boolean evaluarAltaUsuario(String auth, String endpoint, String idServicio, String key) {

		return evaluarParametro(auth) && evaluarParametro(endpoint) && evaluarParametro(idServicio)
				&& evaluarParametro(key);
	}

	private boolean evaluarParametro(String parametro) {
		return null != parametro && !parametro.isEmpty();

	}



}
