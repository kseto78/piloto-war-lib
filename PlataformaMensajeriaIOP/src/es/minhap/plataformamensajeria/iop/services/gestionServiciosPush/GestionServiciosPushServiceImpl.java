package es.minhap.plataformamensajeria.iop.services.gestionServiciosPush;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import es.minhap.common.properties.PropertiesServices;
import es.minhap.plataformamensajeria.iop.beans.RegistroUsuarioXMLBean;
import es.minhap.plataformamensajeria.iop.beans.ServiciosDisponiblesXMLBean;
import es.minhap.plataformamensajeria.iop.beans.respuestasServiciosMoviles.RespuestaServiciosDisponibles;
import es.minhap.plataformamensajeria.iop.beans.respuestasServiciosMoviles.RespuestaServiciosRegistrarUsuario;
import es.minhap.plataformamensajeria.iop.beans.respuestasServiciosMoviles.ServicioMovil;
import es.minhap.plataformamensajeria.iop.manager.TblAplicacionesManager;
import es.minhap.plataformamensajeria.iop.manager.TblServiciosMovilesManager;
import es.minhap.plataformamensajeria.iop.manager.TblUsuariosPushManager;
import es.minhap.plataformamensajeria.iop.manager.TblUsuariosServiciosMovilesManager;
import es.minhap.plataformamensajeria.iop.services.exceptions.PlataformaBusinessException;
import es.minhap.plataformamensajeria.iop.services.seguimiento.ISeguimientoMensajesService;

/**
 * 
 * @author everis
 *
 */
@Service("gestionServiciosPushImpl")
public class GestionServiciosPushServiceImpl implements IGestionServiciosPushService {
	private final static Logger LOG = Logger.getLogger(GestionServiciosPushServiceImpl.class);

	@Resource
	private TblAplicacionesManager aplicacionesManager;
	
	@Resource
	private TblUsuariosPushManager usuariosPushManager;
	
	@Resource
	private TblUsuariosServiciosMovilesManager usuariosServiciosMovilesManager;
	
	@Resource
	private TblServiciosMovilesManager serviciosMovilesManager;
	
	@Resource
	private ISeguimientoMensajesService seguimientoMensajeService;

	@Resource(name = "reloadableResourceBundleMessageSource")
	private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;
	
	
	private static String xmlGenerado = "[GestionServiciosPushServiceImpl] XML de respuesta generado";

	@Override
	public String registroUsuarioEnServicio(RegistroUsuarioXMLBean registroUsuarioXMLBean) {
		LOG.debug("[GestionServiciosPushServiceImpl] Registro usuario en servicio");
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		String accionAltaServicio = ps.getMessage("constantes.accionesServiciosPush.ACCION_ALTA_SERVICIO", null);
		String accionBajaServicio = ps.getMessage("constantes.accionesServiciosPush.ACCION_BAJA_SERVICIO", null);
		String accionConsultaServicio = ps.getMessage("constantes.accionesServiciosPush.ACCION_CONSULTA_SERVICIO", null);
		
		String xmlResultado = "";
		RespuestaServiciosRegistrarUsuario respuesta = new RespuestaServiciosRegistrarUsuario(); 
		try {
			
			Boolean existeUsuario = aplicacionesManager.existeAplicacion(registroUsuarioXMLBean.getUsuario(), registroUsuarioXMLBean.getPassword());
			if(existeUsuario) {
				List<String> users = usuariosPushManager.getIdUsersFromDispositivo(registroUsuarioXMLBean.getIdDispositivo(),registroUsuarioXMLBean.getIdUsuario());
				if (accionAltaServicio.equals(registroUsuarioXMLBean.getAccion())) {
					xmlResultado = altaUsuarioServicio(registroUsuarioXMLBean, respuesta, users, ps);
				} else if (accionBajaServicio.equals(registroUsuarioXMLBean.getAccion())) {
					xmlResultado = bajaUsuarioServicio(registroUsuarioXMLBean, respuesta, users,ps);
				} else if (accionConsultaServicio.equals(registroUsuarioXMLBean.getAccion())) {
					xmlResultado = consultaUsuarioServicio(registroUsuarioXMLBean, respuesta, users,ps);
				} else {
					throw new PlataformaBusinessException("La accion a realizar no es la correcta");
				}
				LOG.debug("[GestionServiciosPushServiceImpl] Generando XML de la respuesta");
				LOG.trace(xmlResultado);
			} else{
				xmlResultado = respuesta.loginIncorrectotoXML(ps);
			}
			LOG.trace(xmlResultado);
			LOG.debug(xmlGenerado);
		} catch (PlataformaBusinessException e) {
			LOG.error("[GestionServiciosPushServiceImpl] RegistrandoUsuario en Servicio Push",e);
		} 
		return xmlResultado;
		
	}
	
	@Override
	public String consultaServiciosDisponibles(ServiciosDisponiblesXMLBean servDispoXMLBean) {
		LOG.debug("[GestionServiciosPushServiceImpl] Consultando los servicios disponibles");
		String xmlResultado = "";
		RespuestaServiciosDisponibles respuesta = new RespuestaServiciosDisponibles(); 

		try {
			Boolean existeUsuario = aplicacionesManager.existeAplicacion(servDispoXMLBean.getUsuario(), servDispoXMLBean.getPassword());
			if(existeUsuario) {
				List<ServicioMovil> serviciosMoviles = serviciosMovilesManager.consultarServiciosDisponibles(servDispoXMLBean.getIdUsuario());
				LOG.debug("[GestionServiciosPushServiceImpl] Generando xml de respuesta");
				xmlResultado = respuesta.toXML(serviciosMoviles);
				LOG.trace(xmlResultado);
			} else{
				xmlResultado = respuesta.loginIncorrectotoXML();
			}
			LOG.trace(xmlResultado);
			LOG.debug(xmlGenerado);
		} catch (PlataformaBusinessException e) {
			LOG.error("[GestionServiciosPushServiceImpl] Consultando Servicios Disponibles",e);
		} 
		return xmlResultado;
	}

	
	
	
	private String consultaUsuarioServicio(RegistroUsuarioXMLBean registroUsuarioXMLBean,
			RespuestaServiciosRegistrarUsuario respuesta, List<String> users, PropertiesServices ps)
			throws PlataformaBusinessException {
		String xmlResultado;
		boolean checkMobileServiceId = serviciosMovilesManager.checkMobileServie(registroUsuarioXMLBean.getIdServicioMovil());
		if (checkMobileServiceId) {
			String estadoSuscripcion = usuariosServiciosMovilesManager.checkSuscriptionStatus(users.get(0), registroUsuarioXMLBean.getIdServicioMovil());
			xmlResultado = respuesta.checkUserService(estadoSuscripcion, ps);
		} else {
			xmlResultado = respuesta.invalidMobielServiceXML(ps);
		}
		return xmlResultado;
	}
	private String bajaUsuarioServicio(RegistroUsuarioXMLBean registroUsuarioXMLBean,
			RespuestaServiciosRegistrarUsuario respuesta, List<String> users, PropertiesServices ps) throws PlataformaBusinessException {
		String xmlResultado;
		boolean checkMobileServiceId = serviciosMovilesManager.checkMobileServie(registroUsuarioXMLBean.getIdServicioMovil());
		if (checkMobileServiceId) {
			boolean removeFromService = false;
			for (String user : users) {
				removeFromService = usuariosServiciosMovilesManager.updateUsuarioServicio(user, registroUsuarioXMLBean.getIdServicioMovil(), registroUsuarioXMLBean.getUsuario(), false);
			}
			xmlResultado = respuesta.toXML(removeFromService, ps);
		} else {
			xmlResultado = respuesta.invalidMobielServiceXML(ps);
		}
		return xmlResultado;
	}
	private String altaUsuarioServicio(RegistroUsuarioXMLBean registroUsuarioXMLBean,
			RespuestaServiciosRegistrarUsuario respuesta, List<String> users, PropertiesServices ps) throws PlataformaBusinessException {
		String xmlResultado;
		boolean exists = usuariosServiciosMovilesManager.comprobarUsuarioServicio(users, registroUsuarioXMLBean.getIdServicioMovil());
		if (!exists) {
			boolean checkMobileServiceId = serviciosMovilesManager.checkMobileServie(registroUsuarioXMLBean.getIdServicioMovil());
			if (checkMobileServiceId) {
				boolean insertIntoService = false;
				for (String user : users) {
					insertIntoService = usuariosServiciosMovilesManager.registraUsuarioServicio(user, registroUsuarioXMLBean.getIdServicioMovil(), registroUsuarioXMLBean.getUsuario());
				}
				xmlResultado = respuesta.toXML(insertIntoService, ps);
			} else {
				xmlResultado = respuesta.invalidMobielServiceXML(ps);
			}
		} else {
			boolean updateSubscription = false;
			for (String user : users) {
				updateSubscription = usuariosServiciosMovilesManager.updateUsuarioServicio(user, registroUsuarioXMLBean.getIdServicioMovil(), registroUsuarioXMLBean.getUsuario(), true);
			}
			xmlResultado = respuesta.userExistsXML(updateSubscription, ps);
		}
		return xmlResultado;
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
	 * @return the usuariosServiciosMovilesManager
	 */
	public TblUsuariosServiciosMovilesManager getUsuariosServiciosMovilesManager() {
		return usuariosServiciosMovilesManager;
	}

	/**
	 * @param usuariosServiciosMovilesManager the usuariosServiciosMovilesManager to set
	 */
	public void setUsuariosServiciosMovilesManager(TblUsuariosServiciosMovilesManager usuariosServiciosMovilesManager) {
		this.usuariosServiciosMovilesManager = usuariosServiciosMovilesManager;
	}

	/**
	 * @return the serviciosMovilesManager
	 */
	public TblServiciosMovilesManager getServiciosMovilesManager() {
		return serviciosMovilesManager;
	}

	/**
	 * @param serviciosMovilesManager the serviciosMovilesManager to set
	 */
	public void setServiciosMovilesManager(TblServiciosMovilesManager serviciosMovilesManager) {
		this.serviciosMovilesManager = serviciosMovilesManager;
	}

	/**
	 * @return the seguimientoMensajeService
	 */
	public ISeguimientoMensajesService getSeguimientoMensajeService() {
		return seguimientoMensajeService;
	}

	/**
	 * @param seguimientoMensajeService the seguimientoMensajeService to set
	 */
	public void setSeguimientoMensajeService(ISeguimientoMensajesService seguimientoMensajeService) {
		this.seguimientoMensajeService = seguimientoMensajeService;
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
