package es.mpr.plataformamensajeria.web.action.usuariospush;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.map.j2ee.exceptions.BaseException;
import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;
import com.map.j2ee.util.KeyValueObject;
import com.opensymphony.xwork2.Preparable;

import es.mpr.plataformamensajeria.beans.AplicacionBean;
import es.mpr.plataformamensajeria.beans.ServicioBean;
import es.mpr.plataformamensajeria.beans.UsuariosPushBean;
import es.mpr.plataformamensajeria.impl.PlataformaPaginationAction;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioAplicacion;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServicio;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioUsuariosPush;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaProperties;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;
 
/**
 * <p>Clase Action de Struts2 para la gesti&oacute;n de los Usuarios Push.
 * 
 * <p>
 * Proporciona m&eacute;todos para la creaci&oacute;n, modificaci&oacute;n, borrado y listado de los Usuarios Push
 * 
 * @author jgonzvil
 *
 */
@Controller("usuariosPushAction")
@Scope("prototype")
public class UsuariosPushAction extends PlataformaPaginationAction implements ServletRequestAware, Preparable{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

//	private static Logger logger = Logger.getLogger(ServidoresAction.class);
	
	/**  servicio usuario push. */
@Resource(name="servicioUsuariosPushImpl")
	private ServicioUsuariosPush servicioUsuarioPush;
	
	/**  servicio servicio. */
	@Resource(name="servicioServicioImpl")
	private ServicioServicio servicioServicio;
	
	/**  servicio aplicacion. */
	@Resource(name="servicioAplicacionImpl")
	private ServicioAplicacion servicioAplicacion;
	
	/**  properties. */
	@Resource(name = "plataformaMensajeriaProperties")
	private PlataformaMensajeriaProperties properties;
	
	/**  usuarios push. */
	private UsuariosPushBean usuariosPush;
	
	/**  combo aplicaciones. */
	//Combos
	List<KeyValueObject> comboAplicaciones = new ArrayList<KeyValueObject>();
	
	/**  combo servicios. */
	List<KeyValueObject> comboServicios = new ArrayList<KeyValueObject>();
	
	/**  combo plataformas. */
	List<KeyValueObject> comboPlataformas = new ArrayList<KeyValueObject>();
	
	/**  lista usuarios push. */
	public List<UsuariosPushBean> listaUsuariosPush= null;
	
    /**  result count. */
    private String resultCount;
	
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
    	if(usuariosPush==null){
    		usuariosPush = new UsuariosPushBean();
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
    	PaginatedList<UsuariosPushBean> result = servicioUsuarioPush.getUsuariosPush(inicio,(export)?-1:Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20")),
    			order, columnSort,usuariosPush,export,request); 
    	Integer totalSize = result.getTotalList();
    	
    	listaUsuariosPush =  result.getPageList();
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
	
	

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.impl.PlataformaPaginationAction#prepare()
	 */
	@Override
	public void prepare() throws Exception {
		
	}
    
    /**
     * Obtener combo plataformas.
     *
     * @return combo plataformas
     */
    ///MIGRADO
	public List<KeyValueObject> getComboPlataformas() {
        List<KeyValueObject> result = new ArrayList<KeyValueObject>();
        KeyValueObject option = null;
        option = new KeyValueObject();
		option.setCodigo("Android");
		option.setDescripcion("Android");
		result.add(option);		
		
		option = new KeyValueObject();
		option.setCodigo("iOS");
		option.setDescripcion("iOS");
		result.add(option);
		
		return result;
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
		long canalId = 4; //Canal push
		keys = (ArrayList<AplicacionBean>)servicioAplicacion.getAplicacionesByCanal(rolUsuario, idUsuario, canalId);
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
        List<KeyValueObject> result = new ArrayList<KeyValueObject>();
        KeyValueObject option = null;
        ArrayList<ServicioBean> keys = null;
		String rolUsuario = PlataformaMensajeriaUtil.getRolFromSession(request);
		Integer idUsuario = PlataformaMensajeriaUtil.getIdUsuarioFromSession(request);
		if(usuariosPush!=null && usuariosPush.getAplicacionId()!=null){
			keys = (ArrayList<ServicioBean>)servicioServicio.getServiciosByAplicacionId(usuariosPush.getAplicacionId());
		}else{
			keys = (ArrayList<ServicioBean>)servicioServicio.getServicios(rolUsuario,idUsuario);
		}
		if(keys!=null&&keys.size()>0){
	        for (ServicioBean key :keys) {
	        	if(key.getCanalid() != null && key.getCanalid() == 4){
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
		String volver="buscarUsuariosPush.action";
		if(!PlataformaMensajeriaUtil.isEmpty(from)&&!PlataformaMensajeriaUtil.isEmpty(idFrom)){
			volver=from+"?"+var+"="+idFrom;
		}
		return volver;
	}
	
	/**
	 * Obtener lista usuarios push.
	 *
	 * @return lista usuarios push
	 */
	public List<UsuariosPushBean> getListaUsuariosPush() {
		return listaUsuariosPush;
	}
	
	/**
	 * Modificar lista usuarios push.
	 *
	 * @param listaUsuariosPush new lista usuarios push
	 */
	public void setListaUsuariosPush(List<UsuariosPushBean> listaUsuariosPush) {
		this.listaUsuariosPush = listaUsuariosPush;
	}
	
	/**
	 * Obtener servicio usuario push.
	 *
	 * @return servicio usuario push
	 */
	public ServicioUsuariosPush getServicioUsuarioPush() {
		return servicioUsuarioPush;
	}
	
	/**
	 * Modificar servicio usuario push.
	 *
	 * @param servicioUsuarioPush new servicio usuario push
	 */
	public void setServicioUsuarioPush(
			ServicioUsuariosPush servicioUsuarioPush) {
		this.servicioUsuarioPush = servicioUsuarioPush;
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
	 * Obtener usuarios push.
	 *
	 * @return usuarios push
	 */
	public UsuariosPushBean getUsuariosPush() {
		return usuariosPush;
	}
	
	/**
	 * Modificar usuarios push.
	 *
	 * @param usuariosPush new usuarios push
	 */
	public void setUsuariosPush(UsuariosPushBean usuariosPush) {
		this.usuariosPush = usuariosPush;
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
}
