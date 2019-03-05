package es.mpr.plataformamensajeria.web.action.enviomensajes;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.util.KeyValueObject;
import com.opensymphony.xwork2.Preparable;

import es.minhap.common.spring.ApplicationContextProvider;
import es.mpr.plataformamensajeria.beans.AplicacionBean;
import es.mpr.plataformamensajeria.beans.CanalBean;
import es.mpr.plataformamensajeria.beans.EnvioMensajesAplicacionBean;
import es.mpr.plataformamensajeria.beans.ServicioBean;
import es.mpr.plataformamensajeria.beans.ServicioOrganismosBean;
import es.mpr.plataformamensajeria.impl.PlataformaPaginationAction;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioAplicacion;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioCanal;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioGestionEnvios;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServicio;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioUsuarioAplicacion;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaProperties;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;
import es.redsara.misim.misim_bus_webapp.respuesta.Respuesta;

/**
 * Clase EnviosMensajesAplicacionAction.
 */
@Controller("envioMensajesAction")
@Scope("prototype")
public class EnviosMensajesAplicacionAction extends PlataformaPaginationAction implements ServletRequestAware, Preparable {

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**  logger. */
	private static Logger logger = Logger.getLogger(EnviosMensajesAplicacionAction.class);

	/**  servicio aplicacion. */
	@Resource(name = "servicioAplicacionImpl")
	private ServicioAplicacion servicioAplicacion;

	/**  servicio servicio. */
	@Resource(name = "servicioServicioImpl")
	private ServicioServicio servicioServicio;

	/**  servicio canal. */
	@Resource(name = "servicioCanalImpl")
	private ServicioCanal servicioCanal;

	/**  servicio gestion envios. */
	@Resource(name = "servicioGestionEnviosImpl")
	private ServicioGestionEnvios servicioGestionEnvios;
	
	/**  servicio usuario aplicacion. */
	@Resource(name = "servicioUsuarioAplicacionImpl")
	private ServicioUsuarioAplicacion servicioUsuarioAplicacion;
	
	/**  properties. */
	@Resource(name = "plataformaMensajeriaProperties")
	private PlataformaMensajeriaProperties properties;
	
	/**  envio mensajes aplicacion bean. */
	private EnvioMensajesAplicacionBean envioMensajesAplicacionBean;

	/**  combo aplicaciones. */
	List<KeyValueObject> comboAplicaciones = new ArrayList<KeyValueObject>();
	
	/**  combo servicios. */
	List<KeyValueObject> comboServicios = new ArrayList<KeyValueObject>();
	
	/**  combo canales. */
	List<KeyValueObject> comboCanales = new ArrayList<KeyValueObject>();
	
	/**  combo servicio organismos. */
	List<KeyValueObject> comboServicioOrganismos = new ArrayList<KeyValueObject>();

	/**  id lote. */
	private String idLote;
	
	/**  id peticion. */
	private String idPeticion;
	
	/**  search. */
	private String search;
	
	/**  file input stream. */
	private InputStream fileInputStream;

	
	/** Constante FORMATO_FECHA_TITULO_AUDITORIA. */
	public static final String FORMATO_FECHA_TITULO_AUDITORIA = "yyyyMMdd HHmmss";
	
	/** Constante TIPO_FICHERO. */
	public static final String TIPO_FICHERO = "xml";

	/**
	 * Load.
	 *
	 * @return the string
	 * @throws BusinessException the business exception
	 */
	public String load() throws BusinessException {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (validUsuario()) {
			envioMensajesAplicacionBean = new EnvioMensajesAplicacionBean();
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	/**
	 * Enviar peticion.
	 *
	 * @return the string
	 */
	public String enviarPeticion() {

		String resultado = null;
		
		Respuesta respuesta = null;

		boolean validPeticion = false;

		if (envioMensajesAplicacionBean != null) {
			validPeticion = validEnvioPeticion(envioMensajesAplicacionBean);
		}
		
		if (null != envioMensajesAplicacionBean && search != null && search.length() > 0) {
			if (-1 != search.indexOf('|')){
				envioMensajesAplicacionBean.setOrganismo(search.substring(0,search.indexOf('|')).trim());
			}else{
				envioMensajesAplicacionBean.setOrganismo(search);
			}
		}
		
		if(envioMensajesAplicacionBean.getCanalId().equals("2")){ //Enviamos SMS y comprobados que tenemos intentos
			if(!servicioAplicacion.validarSms(envioMensajesAplicacionBean.getAplicacionId().longValue())){
				addActionErrorSession("Limite de envio de SMS diario superado");			
				return SUCCESS;
			}

		}
		
		if (validPeticion) {
			try{
				ApplicationContext  applicationContext = WebApplicationContextUtils.getWebApplicationContext(getRequest().getSession().getServletContext());
				
				if (!ApplicationContextProvider.getInstance().isLoaded()) {
					ApplicationContextProvider.getInstance().loadApplicationContext(applicationContext);
				}
				ApplicationContextProvider context = ApplicationContextProvider.getInstance();
				
				try{
					respuesta = servicioGestionEnvios.enviarPeticion(context, envioMensajesAplicacionBean);
					
					if(!respuesta.getStatus().getStatusCode().equals("1000")){
						envioMensajesAplicacionBean = null;
						resultado =  respuesta.getStatus().getStatusText()+" "+respuesta.getStatus().getDetails();					
						if(respuesta.getMensajes() != null && respuesta.getMensajes().size() > 0){
							resultado += " "+respuesta.getMensajes().get(0).getMensaje().getErrorMensaje().getStatusCode() +" - "+respuesta.getMensajes().get(0).getMensaje().getErrorMensaje().getDetails();
							}
						addActionErrorSession(resultado);						
					}else{
						if(respuesta.getLote().getIdLote() != null){
							resultado = respuesta.getStatus().getDetails()+" El Id Lote es: "+respuesta.getLote().getIdLote();
						}
						if(respuesta.getMensajes() != null){
							resultado +=  " El Id de mensaje es: "+respuesta.getMensajes().get(0).getMensaje().getIdMensaje();
						}
						if(resultado != null){
							addActionMessageSession(resultado);
							servicioAplicacion.smsEnviado(envioMensajesAplicacionBean.getAplicacionId().longValue());
						}
						
						
					}
				} catch (Exception e) {
					logger.error("EnvioMensajesAplicacionAction - enviarPeticion:" + e);
				}
				
			
			}catch(Exception e){
				logger.error("EnvioMensajesAplicacionAction - enviarPeticion:" + e);
			}
		}
		return SUCCESS;
	}

	/**
	 * Comprueba si hay un usuario logueado y con un rol definido.
	 *
	 * @return boolean
	 */
	////MIGRADO
	private boolean validUsuario() {
		boolean sw = true;
		String rolUsuario = PlataformaMensajeriaUtil.getRolFromSession(request);
		Integer idUsuario = PlataformaMensajeriaUtil.getIdUsuarioFromSession(request);
		if (PlataformaMensajeriaUtil.isEmpty(rolUsuario) || PlataformaMensajeriaUtil.isEmptyNumber(idUsuario)) {
			sw = false;
		}
		return sw;
	}

	/**
	 * Obtener combo aplicaciones.
	 *
	 * @return combo aplicaciones
	 */
	////MIGRADO
	public List<KeyValueObject> getComboAplicaciones() {
		List<KeyValueObject> result = new ArrayList<>();
		KeyValueObject option;
		ArrayList<AplicacionBean> keys;
		String rolUsuario = PlataformaMensajeriaUtil.getRolFromSession(request);
		Integer idUsuario = PlataformaMensajeriaUtil.getIdUsuarioFromSession(request);
		keys = (ArrayList<AplicacionBean>) servicioAplicacion.getAplicacionesMenu(rolUsuario, idUsuario);
		if (keys != null && !keys.isEmpty()) {
			for (AplicacionBean key : keys) {
				option = new KeyValueObject();
				option.setCodigo(key.getAplicacionId().toString());
				option.setDescripcion(key.getNombre());
				result.add(option);
			}
		}
		return result;
	}

	/**
	 * Obtener combo servicios.
	 *
	 * @return combo servicios
	 * @throws BusinessException the business exception
	 */
	////MIGRADO
	public List<KeyValueObject> getComboServicios() throws BusinessException {
		List<KeyValueObject> result = new ArrayList<>();
		KeyValueObject option;
		ArrayList<ServicioBean> keys;
		String rolUsuario = PlataformaMensajeriaUtil.getRolFromSession(request);
		Integer idUsuario = PlataformaMensajeriaUtil.getIdUsuarioFromSession(request);
		if (envioMensajesAplicacionBean != null && envioMensajesAplicacionBean.getAplicacionId() != null) {
			keys = (ArrayList<ServicioBean>) servicioServicio.getServiciosByAplicacionId(envioMensajesAplicacionBean
					.getAplicacionId().intValue());
		} else {
			keys = (ArrayList<ServicioBean>) servicioServicio.getServicios(rolUsuario, idUsuario);
		}
		if (keys != null && !keys.isEmpty()) {
			for (ServicioBean key : keys) {

				option = new KeyValueObject();
				option.setCodigo(key.getServicioId().toString());
				option.setDescripcion(key.getNombre());
				result.add(option);
			}
		}
		return result;
	}
	
	/**
	 * Obtener combo organismos servicios.
	 *
	 * @return combo organismos servicios
	 * @throws BusinessException the business exception
	 */
	public List<KeyValueObject> getComboOrganismosServicios() throws BusinessException {
		List<KeyValueObject> result = new ArrayList<>();
		KeyValueObject option;
		ArrayList<ServicioOrganismosBean> keys = null;
//		String rolUsuario = PlataformaMensajeriaUtil.getRolFromSession(request);
//		Integer idUsuario = PlataformaMensajeriaUtil.getIdUsuarioFromSession(request);
		if (envioMensajesAplicacionBean != null && envioMensajesAplicacionBean.getServicioId() != null) {
			keys = (ArrayList<ServicioOrganismosBean>) servicioServicio.getOrganismoServicio(envioMensajesAplicacionBean.getServicioId());
		}
		if (keys != null && !keys.isEmpty()) {
			for (ServicioOrganismosBean key : keys) {

				option = new KeyValueObject();
				option.setCodigo(key.getServicioId().toString());
				option.setDescripcion(key.getNombreOrganismo());
				result.add(option);
			}
		}
		return result;
	}

	/**
	 * Obtener combo canales.
	 *
	 * @return combo canales
	 * @throws BusinessException the business exception
	 */
	////MIGRADO
	public List<KeyValueObject> getComboCanales() throws BusinessException {
		List<KeyValueObject> result = new ArrayList<>();
		KeyValueObject option;
		ArrayList<CanalBean> keys;
		keys = (ArrayList<CanalBean>) servicioCanal.getCanales();
		if (keys != null && !keys.isEmpty()) {
			for (CanalBean key : keys) {
				option = new KeyValueObject();
				option.setCodigo(key.getCanalId().toString());
				option.setDescripcion(key.getNombre());
				result.add(option);
			}
		}
		return result;
	}


	/**
	 * Aplicacion select event.
	 *
	 * @return the string
	 */
	public String aplicacionSelectEvent() {

		if (envioMensajesAplicacionBean != null) {
			AplicacionBean aplicacion = new AplicacionBean();
			aplicacion.setId(envioMensajesAplicacionBean.getAplicacionId());
			try {
				aplicacion = servicioAplicacion.loadAplicacion(aplicacion);

			} catch (BusinessException e) {
				logger.error("EnviosMensajesAcyion - aplicacionSelectEvent:" + e);
			}

		}
		return SUCCESS;
	}
	
	/**
	 * Valid envio peticion.
	 *
	 * @param envioPeticion the envio peticion
	 * @return true, if successful
	 */
	private boolean validEnvioPeticion(EnvioMensajesAplicacionBean envioPeticion) {
		boolean sw = true;
		if (PlataformaMensajeriaUtil.isEmptyNumber(envioPeticion.getAplicacionId())) {
			addActionErrorSession(this.getText("plataforma.enviomensajes.field.aplicacionId.error"));
			sw = false;
		}
		if (PlataformaMensajeriaUtil.isEmpty(envioPeticion.getServicioId())) {
			addActionErrorSession(this.getText("plataforma.enviomensajes.field.servicioId.error"));
			sw = false;
		}		
		if (PlataformaMensajeriaUtil.isEmpty(envioPeticion.getNombreLote())) {
			addActionErrorSession(this.getText("plataforma.enviomensajes.field.nombreLote.error"));
			sw = false;
		}

		//va a depender de la aplicacion seleccionada
		if (PlataformaMensajeriaUtil.isEmpty(envioPeticion.getCanalId())) {
			addActionErrorSession(this.getText("plataforma.enviomensajes.field.canal.error"));
			sw = false;
		}
		
		if (null != envioPeticion.getCanalId()){
			
			if(envioPeticion.getCanalId().equals("1")) {//email
				if (PlataformaMensajeriaUtil.isEmpty(envioPeticion.getAsunto())) {
					addActionErrorSession(this.getText("plataforma.enviomensajes.field.asunto.error"));
					sw = false;
				}
				if (PlataformaMensajeriaUtil.isEmpty(envioPeticion.getMensaje())) {
					addActionErrorSession(this.getText("plataforma.enviomensajes.field.mensaje.error"));
					sw = false;
				}
				if (PlataformaMensajeriaUtil.isEmpty(envioPeticion.getTo())) {
					addActionErrorSession(this.getText("plataforma.enviomensajes.field.emailTo.error"));
					sw = false;
				}else{
					String[] correos = envioPeticion.getTo().split(";");	
					for (String correo : correos) {
						if (!PlataformaMensajeriaUtil.validateEmail(correo)) {	
						addActionErrorSession(this.getText("plataforma.enviomensajes.field.emailTo.email.error"));
						sw = false;
						break;
						}
					}	
				}
				if (envioPeticion.getCc() != null && !envioPeticion.getCc().equals("")) {
					String[] correos = envioPeticion.getCc().split(";");	
					for (String correo : correos) {
						if (!PlataformaMensajeriaUtil.validateEmail(correo)) {	
							addActionErrorSession(this.getText("plataforma.enviomensajes.field.emailCc.email.error"));
							sw = false;
							break;
						}
					}	
				}
				if (envioPeticion.getCco() != null && !envioPeticion.getCco().equals("")) {
					String[] correos = envioPeticion.getCco().split(";");	
					for (String correo : correos) {
						if (!PlataformaMensajeriaUtil.validateEmail(correo)) {	
							addActionErrorSession(this.getText("plataforma.enviomensajes.field.emailCco.email.error"));
							sw = false;
							break;
						}
					}	
				}
			}else if (envioPeticion.getCanalId().equals("2")) {//Sms
				if (PlataformaMensajeriaUtil.isEmpty(envioPeticion.getMensaje())) {
					addActionErrorSession(this.getText("plataforma.enviomensajes.field.mensaje.error"));
					sw = false;
				}			
				if (PlataformaMensajeriaUtil.isEmpty(envioPeticion.getMovil())) {
					addActionErrorSession(this.getText("plataforma.enviomensajes.field.movil.error"));
					sw = false;
				}else if(!PlataformaMensajeriaUtil.isMobileNumber(envioPeticion.getMovil())) {
					addActionErrorSession(this.getText("plataforma.enviomensajes.field.movil.numero.error"));
					sw = false;
				}
			}else if (envioPeticion.getCanalId().equals("4")) {//Push
				if (PlataformaMensajeriaUtil.isEmpty(envioPeticion.getTitulo())) {
					addActionErrorSession(this.getText("plataforma.enviomensajes.field.titulo.error"));
					sw = false;
				}
				if (PlataformaMensajeriaUtil.isEmpty(envioPeticion.getMensaje())) {
					addActionErrorSession(this.getText("plataforma.enviomensajes.field.mensaje.error"));
					sw = false;
				}
//				if (PlataformaMensajeriaUtil.isEmpty(envioPeticion.getIdExterno())) {
//					addActionErrorSession(this.getText("plataforma.enviomensajes.field.idExterno.error"));
//					sw = false;
//				}
				if (PlataformaMensajeriaUtil.isEmpty(envioPeticion.getIdUsuario())) {
					addActionErrorSession(this.getText("plataforma.enviomensajes.field.idUsuario.error"));
					sw = false;
				}
			}else if (envioPeticion.getCanalId().equals("5")) {//WebPush
				if (PlataformaMensajeriaUtil.isEmpty(envioPeticion.getTitulo())) {
					addActionErrorSession(this.getText("plataforma.enviomensajes.field.titulo.error"));
					sw = false;
				}
				if (PlataformaMensajeriaUtil.isEmpty(envioPeticion.getCuerpo())) {
					addActionErrorSession(this.getText("plataforma.enviomensajes.field.cuerpo.error"));
					sw = false;
				}
			}
	
		}

		return sw;
	}

	/**
	 * Obtener servicio aplicacion.
	 *
	 * @return servicio aplicacion
	 */
	public ServicioAplicacion getServicioAplicacion() {
		return servicioAplicacion;
	}

	/**
	 * Modificar servicio aplicacion.
	 *
	 * @param servicioAplicacion new servicio aplicacion
	 */
	public void setServicioAplicacion(ServicioAplicacion servicioAplicacion) {
		this.servicioAplicacion = servicioAplicacion;
	}

	/**
	 * Modificar combo aplicaciones.
	 *
	 * @param comboAplicaciones new combo aplicaciones
	 */
	public void setComboAplicaciones(List<KeyValueObject> comboAplicaciones) {
		this.comboAplicaciones = new ArrayList<KeyValueObject>(comboAplicaciones);
	}


	/**
	 * Obtener servicio canal.
	 *
	 * @return servicio canal
	 */
	public ServicioCanal getServicioCanal() {
		return servicioCanal;
	}

	/**
	 * Modificar servicio canal.
	 *
	 * @param servicioCanal new servicio canal
	 */
	public void setServicioCanal(ServicioCanal servicioCanal) {
		this.servicioCanal = servicioCanal;
	}


	/**
	 * Obtener servicio servicio.
	 *
	 * @return servicio servicio
	 */
	public ServicioServicio getServicioServicio() {
		return servicioServicio;
	}

	/**
	 * Modificar servicio servicio.
	 *
	 * @param servicioServicio new servicio servicio
	 */
	public void setServicioServicio(ServicioServicio servicioServicio) {
		this.servicioServicio = servicioServicio;
	}

	/**
	 * Modificar combo servicios.
	 *
	 * @param comboServicios new combo servicios
	 */
	public void setComboServicios(List<KeyValueObject> comboServicios) {
		this.comboServicios = new ArrayList<KeyValueObject>(comboServicios);
	}

	/**
	 * Modificar combo canales.
	 *
	 * @param comboCanales new combo canales
	 */
	public void setComboCanales(List<KeyValueObject> comboCanales) {
		this.comboCanales = new ArrayList<KeyValueObject>(comboCanales);
	}

	/**
	 * Obtener id lote.
	 *
	 * @return id lote
	 */
	public String getIdLote() {
		return idLote;
	}

	/**
	 * Obtener envio mensajes aplicacion bean.
	 *
	 * @return envio mensajes aplicacion bean
	 */
	public EnvioMensajesAplicacionBean getEnvioMensajesAplicacionBean() {
		return envioMensajesAplicacionBean;
	}

	/**
	 * Modificar envio mensajes aplicacion bean.
	 *
	 * @param envioMensajesAplicacionBean new envio mensajes aplicacion bean
	 */
	public void setEnvioMensajesAplicacionBean(
			EnvioMensajesAplicacionBean envioMensajesAplicacionBean) {
		this.envioMensajesAplicacionBean = envioMensajesAplicacionBean;
	}

	/**
	 * Modificar id lote.
	 *
	 * @param idLote new id lote
	 */
	public void setIdLote(String idLote) {
		this.idLote = idLote;
	}
	
	/**
	 * Obtener id peticion.
	 *
	 * @return id peticion
	 */
	public String getIdPeticion() {
		return idPeticion;
	}

	/**
	 * Modificar id peticion.
	 *
	 * @param idPeticion new id peticion
	 */
	public void setIdPeticion(String idPeticion) {
		this.idPeticion = idPeticion;
	}

	/**
	 * Obtener file input stream.
	 *
	 * @return file input stream
	 */
	public InputStream getFileInputStream() {
		return fileInputStream;
	}

	/**
	 * Devuelve el servicio de gestion de envios.
	 *
	 * @return servicio gestion envios
	 */
	public ServicioGestionEnvios getServicioGestionEnvios() {
		return servicioGestionEnvios;
	}

	/**
	 * Modificar servicio gestion envios.
	 *
	 * @param servicioGestionEnvios new servicio gestion envios
	 */
	public void setServicioGestionEnvios(ServicioGestionEnvios servicioGestionEnvios) {
		this.servicioGestionEnvios = servicioGestionEnvios;
	}

	/**
	 * Obtener combo servicio organismos.
	 *
	 * @return combo servicio organismos
	 */
	public List<KeyValueObject> getComboServicioOrganismos() {
		return comboServicioOrganismos;
	}

	/**
	 * Modificar combo servicio organismos.
	 *
	 * @param comboServicioOrganismos new combo servicio organismos
	 */
	public void setComboServicioOrganismos(
			List<KeyValueObject> comboServicioOrganismos) {
		this.comboServicioOrganismos = comboServicioOrganismos;
	}

	/**
	 * Obtener search.
	 *
	 * @return the search
	 */
	public String getSearch() {
		return search;
	}

	/**
	 * Modificar search.
	 *
	 * @param search the search to set
	 */
	public void setSearch(String search) {
		this.search = search;
	}
	

}
