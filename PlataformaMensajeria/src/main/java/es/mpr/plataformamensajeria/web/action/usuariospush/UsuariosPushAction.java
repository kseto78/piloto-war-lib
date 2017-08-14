package es.mpr.plataformamensajeria.web.action.usuariospush;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
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
import es.mpr.plataformamensajeria.web.action.servidores.ServidoresAction;
 
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
	
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(ServidoresAction.class);
	
	@Resource(name="servicioUsuariosPushImpl")
	private ServicioUsuariosPush servicioUsuarioPush;
	
	@Resource(name="servicioServicioImpl")
	private ServicioServicio servicioServicio;
	
	@Resource(name="servicioAplicacionImpl")
	private ServicioAplicacion servicioAplicacion;
	
	@Resource(name = "plataformaMensajeriaProperties")
	private PlataformaMensajeriaProperties properties;
	
	private UsuariosPushBean usuariosPush;
	
	//Combos
	List<KeyValueObject> comboAplicaciones = new ArrayList<KeyValueObject>();
	List<KeyValueObject> comboServicios = new ArrayList<KeyValueObject>();
	List<KeyValueObject> comboPlataformas = new ArrayList<KeyValueObject>();
	
	public List<UsuariosPushBean> listaUsuariosPush= null;
	
    private String resultCount;
	
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
	
	

	@Override
	public void prepare() throws Exception {
		
	}
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
	  * 
	  * @return
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
	 * 
	 * @return
	 * @throws BusinessException 
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
	            
	            option = new KeyValueObject();
	            option.setCodigo(key.getServicioId().toString());
	            option.setDescripcion(key.getNombre());
	            result.add(option);
	        }
		}
        return result;
    }
	
	/**
	 * Método que resuelve el lugar donde tiene que volver
	 */
	////MIGRADO
	public String getVolver() {
		String volver="buscarUsuariosPush.action";
		if(!PlataformaMensajeriaUtil.isEmpty(from)&&!PlataformaMensajeriaUtil.isEmpty(idFrom)){
			volver=from+"?"+var+"="+idFrom;
		}
		return volver;
	}
	
	public List<UsuariosPushBean> getListaUsuariosPush() {
		return listaUsuariosPush;
	}
	public void setListaUsuariosPush(List<UsuariosPushBean> listaUsuariosPush) {
		this.listaUsuariosPush = listaUsuariosPush;
	}
	public ServicioUsuariosPush getServicioUsuarioPush() {
		return servicioUsuarioPush;
	}
	public void setServicioUsuarioPush(
			ServicioUsuariosPush servicioUsuarioPush) {
		this.servicioUsuarioPush = servicioUsuarioPush;
	}
	public String getResultCount() {
		return resultCount;
	}
	public void setResultCount(String resultCount) {
		this.resultCount = resultCount;
	}
	public UsuariosPushBean getUsuariosPush() {
		return usuariosPush;
	}
	public void setUsuariosPush(UsuariosPushBean usuariosPush) {
		this.usuariosPush = usuariosPush;
	}
	
	public ServicioServicio getServicioServicio() {
		return servicioServicio;
	}
	public void setServicioServicio(ServicioServicio servicioServicio) {
		this.servicioServicio = servicioServicio;
	}
	public ServicioAplicacion getServicioAplicacion() {
		return servicioAplicacion;
	}
	public void setServicioAplicacion(ServicioAplicacion servicioAplicacion) {
		this.servicioAplicacion = servicioAplicacion;
	}
	public void setComboPlataformas(List<KeyValueObject> comboPlataformas) {
		this.comboPlataformas = comboPlataformas;
	}
	public void setComboAplicaciones(List<KeyValueObject> comboAplicaciones) {
		this.comboAplicaciones = comboAplicaciones;
	}
	public void setComboServicios(List<KeyValueObject> comboServicios) {
		this.comboServicios = comboServicios;
	}
}
