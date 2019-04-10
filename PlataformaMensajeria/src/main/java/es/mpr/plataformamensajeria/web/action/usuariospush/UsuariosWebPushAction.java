package es.mpr.plataformamensajeria.web.action.usuariospush;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.bind.DatatypeConverter;

import org.apache.axis.utils.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.URLDecoderUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.map.j2ee.exceptions.BaseException;
import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;
import com.map.j2ee.util.KeyValueObject;
import com.opensymphony.xwork2.Preparable;

import es.mpr.plataformamensajeria.beans.AplicacionBean;
import es.mpr.plataformamensajeria.beans.ServicioBean;
import es.mpr.plataformamensajeria.beans.UsuariosWebPushBean;
import es.mpr.plataformamensajeria.impl.PlataformaPaginationAction;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioAplicacion;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServicio;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioUsuariosWebPush;
import es.mpr.plataformamensajeria.util.MapUser;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaProperties;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;
import es.redsara.misim.misim_bus_webapp.RegUsuario;
import es.redsara.misim.misim_bus_webapp.peticion.PeticionRegistroUsuarioWebPush;
import es.redsara.misim.misim_bus_webapp.respuesta.RespuestaRegistroWebPush;

 
/**
 * <p>Clase Action de Struts2 para la gesti&oacute;n de los Usuarios Push.
 * 
 * <p>
 * Proporciona m&eacute;todos para la creaci&oacute;n, modificaci&oacute;n, borrado y listado de los Usuarios Push
 * 
 * @author jgonzvil
 *
 */
@Controller("usuariosWebPushAction")
@Scope("prototype")
public class UsuariosWebPushAction extends PlataformaPaginationAction implements ServletRequestAware, Preparable{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**  logger. */
	private static Logger logger = Logger.getLogger(UsuariosWebPushAction.class);
	
	/**  servicio usuario web push. */
	@Resource(name="servicioUsuariosWebPushImpl")
	private transient ServicioUsuariosWebPush servicioUsuarioWebPush;
	
	/**  servicio servicio. */
	@Resource(name="servicioServicioImpl")
	private transient ServicioServicio servicioServicio;
	
	/**  servicio aplicacion. */
	@Resource(name="servicioAplicacionImpl")
	private transient ServicioAplicacion servicioAplicacion;
	
	/**  properties. */
	@Resource(name = "plataformaMensajeriaProperties")
	private transient PlataformaMensajeriaProperties properties;
	
	/**  usuarios web push. */
	private UsuariosWebPushBean usuariosWebPush;
	
	/**  combo aplicaciones. */
	//Combos
	transient List<KeyValueObject> comboAplicaciones = new ArrayList<>();
	
	/**  combo servicios. */
	transient List<KeyValueObject> comboServicios = new ArrayList<>();
	
	/**  combo plataformas. */
	transient List<KeyValueObject> comboPlataformas = new ArrayList<>();
	
	/**  lista usuarios web push. */
	private transient List<UsuariosWebPushBean> listaUsuariosWebPush= null;
	
    /**  result count. */
    private String resultCount;
    
    /**  endpoint. */
    private String endpoint;
    
    /**  endpoint2. */
    private String endpoint2;
    
    /**  pdh. */
    private String pdh;
    
    /**  auth. */
    private String auth;
    
    /**  accion. */
    private String accion;
    
    /**  subscription. */
    private String subscription;
    
    /**  id usuario. */
    private String idUsuario;
    
    /** public key, */
    private String publicKey;
	
    /**
     * New search.
     *
     * @return the string
     * @throws BaseException the base exception
     */
    ////MIGRADO
    public String newSearch() throws BaseException {
    	
    	if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
    	
    	boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
    	if(usuariosWebPush==null){
    		usuariosWebPush = new UsuariosWebPushBean();
    		setUserSession();
    	}
    	Integer totalSize = 0;
    	resultCount = "0";
    	publicKey = properties.getProperty("peticion.webpush.publicKey", null);
    	//Atributos de request
    	getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_TOTALSIZE", null), totalSize);
    	if(!export){
    		getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_PAGESIZE", null),
					Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20")));
    	}else{
    		getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_PAGESIZE", null), totalSize);
    	}
    	    	 	
        return SUCCESS;
    }
    
    /**
     * Search.
     *
     * @return the string
     * @throws BaseException the base exception
     */
    ///MIGRADO
	public String search() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
	   	int page = getPage("tableId"); //Pagina a mostrar
    	String order = getOrder("tableId"); //Ordenar de modo ascendente o descendente
    	String columnSort = getColumnSort("tableId"); //Columna usada para ordenar
    	boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
    	int inicio = (page-1) * Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20"));
    	PaginatedList<UsuariosWebPushBean> result = servicioUsuarioWebPush.getUsuariosPush(inicio,(export)?-1:Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20")),
    			order, columnSort,usuariosWebPush,export,request); 
    	Integer totalSize = result.getTotalList();
    	
    	listaUsuariosWebPush =  result.getPageList();
    	resultCount = (totalSize!=null)?totalSize.toString():"0";
    	//Atributos de request
    	getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_TOTALSIZE", null), totalSize);
    	if(!export){
    		getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_PAGESIZE", null),
					Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20")));
    	}else{
    		getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_PAGESIZE", null), totalSize);
    	}
    	
        return SUCCESS;
	}
	
	/**
	 * Insertar usuario web push.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	public String insertarUsuarioWebPush() throws BaseException{
		
//		logger.info("endpoint-->" + endpoint);
//		logger.info("pdh-->" + pdh);
//		logger.info("auth-->" + auth);
//		logger.info("accion-->" + accion);

		if(!StringUtils.isEmpty(endpoint)){
	    	setUserSession();
		}
		
    	boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
    	if(usuariosWebPush==null){
    		usuariosWebPush = new UsuariosWebPushBean();
    	}
    	Integer totalSize = 0;
    	resultCount = "0";
    	
    	//Atributos de request
    	getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_TOTALSIZE", null), totalSize);
    	if(!export){
    		getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_PAGESIZE", null),
					Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20")));
    	}else{
    		getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_PAGESIZE", null), totalSize);
    	}

		if(!StringUtils.isEmpty(endpoint) && !StringUtils.isEmpty(endpoint2)){
			if (StringUtils.isEmpty(idUsuario)) {
				setUserSession();
			}
			logger.info("idUsuario-->" + idUsuario);
			String endpointMisim = properties.getProperty("peticion.webpush.endpoint", null);
			PeticionRegistroUsuarioWebPush peticion = new PeticionRegistroUsuarioWebPush();
			peticion.setAccion(accion);
			peticion.setAuth(auth);
			peticion.setEndpoint(obtenerUrlDecode());
			peticion.setIdServicio(properties.getProperty("peticion.webpush.idServicio", null).trim());
			peticion.setIdUsuario(idUsuario);
			peticion.setKey(pdh);
			peticion.setUsuario(properties.getProperty("peticion.webpush.aplicacion.usuario", null));
			peticion.setPassword(properties.getProperty("peticion.webpush.aplicacion.password", null));
			RespuestaRegistroWebPush respuesta = null;
			try{
				RegUsuario envioMensajes = RegUsuario.getNewInstance(endpointMisim, peticion);
				respuesta = envioMensajes.sendMessage();
				logger.info(respuesta.getStatus().getDetails());
				
//				subscription = "Resultado de la suscripción";
			}catch(Exception e){
				generarRespuesta(e.getMessage());
				logger.info("Respuesta: "+e.getMessage());
			}
		}
		
		return SUCCESS;
	}

	/**
	 * @return
	 */
	private String obtenerUrlDecode() {
		String str = new String(DatatypeConverter.parseBase64Binary(endpoint+endpoint2));
		
		String url = URLDecoderUtil.decode(str, "UTF-8");
		return url;
	}

	/**
	 * 
	 */
	private void setUserSession() {
		MapUser springUser = (MapUser) request.getSession().getAttribute("infoUser");
		this.setIdUsuario(springUser.getUsername());
	}

	/**
	 * Generar respuesta.
	 *
	 * @param message the message
	 */
	private void generarRespuesta(String message) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.impl.PlataformaPaginationAction#prepare()
	 */
	@Override
	public void prepare() throws Exception {
		
	}
   	
	 /**
 	 * Obtener combo aplicaciones.
 	 *
 	 * @return combo aplicaciones
 	 */
    ///MIGRADO
	public List<KeyValueObject> getComboAplicaciones() {
        List<KeyValueObject> result = new ArrayList<>();
        KeyValueObject option;
        ArrayList<AplicacionBean> keys;
		String rolUsuario = PlataformaMensajeriaUtil.getRolFromSession(request);
		Integer idUsuario = PlataformaMensajeriaUtil.getIdUsuarioFromSession(request);
		keys = (ArrayList<AplicacionBean>)servicioAplicacion.getAplicacionesMenu(rolUsuario, idUsuario);
		if(keys!=null&&!keys.isEmpty()){
	        for (AplicacionBean key :keys) {
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
	///MIGRADO
	public List<KeyValueObject> getComboServicios() throws BusinessException {
        List<KeyValueObject> result = new ArrayList<>();
        KeyValueObject option = null;
        ArrayList<ServicioBean> keys = null;
		String rolUsuario = PlataformaMensajeriaUtil.getRolFromSession(request);
		Integer idUsuario = PlataformaMensajeriaUtil.getIdUsuarioFromSession(request);
		if(usuariosWebPush!=null && usuariosWebPush.getAplicacionId()!=null){
			keys = (ArrayList<ServicioBean>)servicioServicio.getServiciosByAplicacionId(usuariosWebPush.getAplicacionId());
		}else if (rolUsuario!=null && idUsuario!=null){
			keys = (ArrayList<ServicioBean>)servicioServicio.getServicios(rolUsuario,idUsuario);
		}
		if(keys!=null&&!keys.isEmpty()){
	        for (ServicioBean key :keys) {
	            if(key.getCanalid() != null && key.getCanalid() == 5){
	            	option = new KeyValueObject();
		            option.setCodigo(key.getServicioId().toString());
		            option.setDescripcion(key.getNombre());
		            result.add(option);
	            }	            	
	        }
		}
        return result;
    }
	
	/**
	 * Método que resuelve el lugar donde tiene que volver.
	 *
	 * @return volver
	 */
	////MIGRADO
	public String getVolver() {
		String volver="buscarUsuariosWebPush.action";
		if(!PlataformaMensajeriaUtil.isEmpty(from)&&!PlataformaMensajeriaUtil.isEmpty(idFrom)){
			volver=from+"?"+var+"="+idFrom;
		}
		return volver;
	}
	

	/**
	 * Obtener result count.
	 *
	 * @return result count
	 */
	public String getResultCount() {
		return resultCount;
	}
	
	/**
	 * Modificar result count.
	 *
	 * @param resultCount new result count
	 */
	public void setResultCount(String resultCount) {
		this.resultCount = resultCount;
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
	 * Modificar combo plataformas.
	 *
	 * @param comboPlataformas new combo plataformas
	 */
	public void setComboPlataformas(List<KeyValueObject> comboPlataformas) {
		this.comboPlataformas = comboPlataformas;
	}
	
	/**
	 * Modificar combo aplicaciones.
	 *
	 * @param comboAplicaciones new combo aplicaciones
	 */
	public void setComboAplicaciones(List<KeyValueObject> comboAplicaciones) {
		this.comboAplicaciones = comboAplicaciones;
	}
	
	/**
	 * Modificar combo servicios.
	 *
	 * @param comboServicios new combo servicios
	 */
	public void setComboServicios(List<KeyValueObject> comboServicios) {
		this.comboServicios = comboServicios;
	}
	
	/**
	 * Obtener servicio usuario web push.
	 *
	 * @return the servicioUsuarioWebPush
	 */
	public ServicioUsuariosWebPush getServicioUsuarioWebPush() {
		return servicioUsuarioWebPush;
	}
	
	/**
	 * Modificar servicio usuario web push.
	 *
	 * @param servicioUsuarioWebPush the servicioUsuarioWebPush to set
	 */
	public void setServicioUsuarioWebPush(ServicioUsuariosWebPush servicioUsuarioWebPush) {
		this.servicioUsuarioWebPush = servicioUsuarioWebPush;
	}
	
	/**
	 * Obtener properties.
	 *
	 * @return the properties
	 */
	public PlataformaMensajeriaProperties getProperties() {
		return properties;
	}
	
	/**
	 * Modificar properties.
	 *
	 * @param properties the properties to set
	 */
	public void setProperties(PlataformaMensajeriaProperties properties) {
		this.properties = properties;
	}
	
	/**
	 * Obtener usuarios web push.
	 *
	 * @return the usuariosWebPush
	 */
	public UsuariosWebPushBean getUsuariosWebPush() {
		return usuariosWebPush;
	}
	
	/**
	 * Modificar usuarios web push.
	 *
	 * @param usuariosWebPush the usuariosWebPush to set
	 */
	public void setUsuariosWebPush(UsuariosWebPushBean usuariosWebPush) {
		this.usuariosWebPush = usuariosWebPush;
	}
	
	/**
	 * Obtener lista usuarios web push.
	 *
	 * @return the listaUsuariosWebPush
	 */
	public List<UsuariosWebPushBean> getListaUsuariosWebPush() {
		return listaUsuariosWebPush;
	}
	
	/**
	 * Modificar lista usuarios web push.
	 *
	 * @param listaUsuariosWebPush the listaUsuariosWebPush to set
	 */
	public void setListaUsuariosWebPush(List<UsuariosWebPushBean> listaUsuariosWebPush) {
		this.listaUsuariosWebPush = listaUsuariosWebPush;
	}
	
	/**
	 * Obtener endpoint.
	 *
	 * @return the endpoint
	 */
	public String getEndpoint() {
		return endpoint;
	}
	
	/**
	 * Modificar endpoint.
	 *
	 * @param endpoint the endpoint to set
	 */
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}
	
	
	/**
	 * Obtener pdh.
	 *
	 * @return the pdh
	 */
	public String getPdh() {
		return pdh;
	}
	
	/**
	 * Modificar pdh.
	 *
	 * @param pdh the pdh to set
	 */
	public void setPdh(String pdh) {
		this.pdh = pdh;
	}
	
	/**
	 * Obtener auth.
	 *
	 * @return the auth
	 */
	public String getAuth() {
		return auth;
	}
	
	/**
	 * Modificar auth.
	 *
	 * @param auth the auth to set
	 */
	public void setAuth(String auth) {
		this.auth = auth;
	}
	
	/**
	 * Obtener accion.
	 *
	 * @return the accion
	 */
	public String getAccion() {
		return accion;
	}
	
	/**
	 * Modificar accion.
	 *
	 * @param accion the accion to set
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}
	
	/**
	 * Obtener subscription.
	 *
	 * @return the subscription
	 */
	public String getSubscription() {
		return subscription;
	}
	
	/**
	 * Modificar subscription.
	 *
	 * @param subscription the subscription to set
	 */
	public void setSubscription(String subscription) {
		this.subscription = subscription;
	}
	
	/**
	 * Obtener id usuario.
	 *
	 * @return the idUsuario
	 */
	public String getIdUsuario() {
		return idUsuario;
	}
	
	/**
	 * Modificar id usuario.
	 *
	 * @param idUsuario the idUsuario to set
	 */
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * @return the endpoint2
	 */
	public String getEndpoint2() {
		return endpoint2;
	}

	/**
	 * @param endpoint2 the endpoint2 to set
	 */
	public void setEndpoint2(String endpoint2) {
		this.endpoint2 = endpoint2;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
	
	
	
	
	
}
