package es.mpr.plataformamensajeria.web.action.estadisticas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.security.context.SecurityContextHolder;

import com.map.j2ee.exceptions.BaseException;
import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.util.KeyValueObject;
import com.opensymphony.xwork2.Preparable;

import es.mpr.plataformamensajeria.beans.AplicacionBean;
import es.mpr.plataformamensajeria.beans.CanalBean;
import es.mpr.plataformamensajeria.beans.EstadoBean;
import es.mpr.plataformamensajeria.beans.ServicioBean;
import es.mpr.plataformamensajeria.beans.ServidorBean;
import es.mpr.plataformamensajeria.estadisticas.EstadisticasPlataforma;
import es.mpr.plataformamensajeria.estadisticas.bean.EstadisticasBean;
import es.mpr.plataformamensajeria.estadisticas.bean.FilaEstadisticaBean;
import es.mpr.plataformamensajeria.impl.PlataformaPaginationAction;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioAplicacion;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioCanal;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioEstado;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServicio;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServidor;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;
 
/**
 * <p>Clase Action de Struts2 para la gesti&oacute;n de los Usuarios.
 * 
 * <p>
 * Proporciona m&eacute;todos para la creaci&oacute;n, modificaci&oacute;n, borrado y listado de los Usuarios
 * 
 * @author i-nercya
 *
 */
public class EstadisticasAction extends PlataformaPaginationAction implements ServletRequestAware, Preparable{
	
	private static final long serialVersionUID = 1L;
	
	//private static final String REQUEST_ATTRIBUTE_TOTALSIZE = "totalSize";
	//private static final String REQUEST_ATTRIBUTE_PAGESIZE = "pageSize";
	

	//private static final Integer PAGESIZE = new Integer(10); //Elementos por pagina
	List<KeyValueObject> comboAplicaciones = new ArrayList<KeyValueObject>();
	List<KeyValueObject> comboServidores = new ArrayList<KeyValueObject>();
	List<KeyValueObject> comboServicios = new ArrayList<KeyValueObject>();
	List<KeyValueObject> comboEstados = new ArrayList<KeyValueObject>();
	List<KeyValueObject> comboCanales = new ArrayList<KeyValueObject>();
	List<KeyValueObject> comboAgrupar = new ArrayList<KeyValueObject>();
	
	
	
	List<FilaEstadisticaBean> listaFilaEstadisticaBean = null;
	
    private ServicioAplicacion servicioAplicacion;
    private ServicioServidor servicioServidor;
    private ServicioServicio servicioServicio;
    private ServicioEstado servicioEstado;
    private ServicioCanal servicioCanal;
    
    private String vistaIdSelected;
	private String resultCount;
	private String reverse="false";
	public String getReverse() {
		return reverse;
	}
	public void setReverse(String reverse) {
		this.reverse = reverse;
	}
	private EstadisticasBean estadisticaBean;
    
	public EstadisticasBean getEstadisticaBean() {
		return estadisticaBean;
	}
	public void setEstadisticaBean(EstadisticasBean estadisticaBean) {
		this.estadisticaBean = estadisticaBean;
	}
	public String newSearch() {
    	return SUCCESS;
    }
	@SuppressWarnings("unchecked")
	public String search() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
		if(estadisticaBean!=null&&estadisticaBean.getVistaId()!=null){
	    	vistaIdSelected=estadisticaBean.getVistaId().toString();
	    }else{
	    	vistaIdSelected = "3";
	    }
		if(validUsuario()){
			if(validaBusqueda(estadisticaBean)){
			    EstadisticasPlataforma estadisticasPlataforma = new EstadisticasPlataforma(estadisticaBean);
			    HashMap<Integer,Integer> mapPermisosUsuario = (HashMap<Integer, Integer>)request.getSession().
						getAttribute(PlataformaMensajeriaUtil.MAP_PERMISOS_APLICACIONES);
			    String rolUsuario = (String)request.getSession().getAttribute(PlataformaMensajeriaUtil.ROL_USUARIO_PLATAFORMA);
			    
			    estadisticasPlataforma.setRolUsuario(rolUsuario);
			    estadisticasPlataforma.setMapPermisosUsuario(mapPermisosUsuario);
			    if(estadisticaBean!=null&&estadisticaBean.getVistaId()!=null){
			    	vistaIdSelected=estadisticaBean.getVistaId().toString();
			    }else{
			    	vistaIdSelected = "3";
			    }
			    if(reverse!=null&&reverse.equals("true")){
			    	listaFilaEstadisticaBean = estadisticasPlataforma.getEstadisticas(true);
			    }else{
			    	listaFilaEstadisticaBean = estadisticasPlataforma.getEstadisticas(false);
			    }
			    
			    if(listaFilaEstadisticaBean!=null&&listaFilaEstadisticaBean.size()>0){
			    	resultCount = String.valueOf(listaFilaEstadisticaBean.size());
			    }else{
			    	resultCount = "0";
			    }
			}
		}else{
			return ERROR;
		}
		
        return SUCCESS;
	}

    

    
    /**
     * Comprueba si hay un usuario logueado y con un rol definido
     * @return True si es valido
     */
 private boolean validUsuario() {
	 	boolean sw = true;
		String rolUsuario = PlataformaMensajeriaUtil.getRolFromSession(request);
		Integer idUsuario = PlataformaMensajeriaUtil.getIdUsuarioFromSession(request);
		if(PlataformaMensajeriaUtil.isEmpty(rolUsuario)||PlataformaMensajeriaUtil.isEmptyNumber(idUsuario)){
			sw=false;
		}
		return sw;
	}
 /**
  * 
  * @param estadisticaBean
  * @return
  */
private boolean validaBusqueda(EstadisticasBean estadisticaBean2) {
	 	boolean sw=true;
//		if(estadisticaBean2!=null&&PlataformaMensajeriaUtil.isEmptyNumber(estadisticaBean2.getEstadoId())){
//			addActionErrorSession(this.getText("plataforma.estadistica.busqueda.estado.error"));
//			sw=false;
//		}
		if(estadisticaBean2!=null&&estadisticaBean2.getFechaDesde()==null){
			addActionErrorSession(this.getText("plataforma.estadistica.busqueda.fechadesde.error"));
			sw=false;
		}
		if(estadisticaBean2!=null&&estadisticaBean2.getFechaHasta()==null){
			addActionErrorSession(this.getText("plataforma.estadistica.busqueda.fechahasta.error"));
			sw=false;
		}
		if(estadisticaBean2!=null&&PlataformaMensajeriaUtil.isEmptyNumber(estadisticaBean2.getAgruparId())){
			addActionErrorSession(this.getText("plataforma.estadistica.agrupar.error"));
			sw=false;
		}
		if(estadisticaBean2!=null&&PlataformaMensajeriaUtil.isEmptyNumber(estadisticaBean2.getVistaId())){
			addActionErrorSession(this.getText("plataforma.estadistica.vista.error"));
			sw=false;
		}
		if(estadisticaBean2!=null && estadisticaBean2.getFechaDesde() != null && estadisticaBean2.getFechaHasta() != null && estadisticaBean2.getVistaId() == 3){
			if((estadisticaBean2.getFechaHasta().getTime() - estadisticaBean2.getFechaDesde().getTime())/86400000L > 100){
				addActionErrorSession(this.getText("plataforma.estadistica.vista.maxfechas.error"));
				sw=false;
			}
		}
		return sw;
	}
public String getVistaIdSelected() {
		return vistaIdSelected;
	}
	public void setVistaIdSelected(String vistaIdSelected) {
		this.vistaIdSelected = vistaIdSelected;
	}
	/*   private boolean validaCampos(UsuarioAplicacionBean usuarioAplicacion2) {
    	boolean sw = true;
		if(PlataformaMensajeriaUtil.isEmptyNumber(usuarioAplicacion.getAplicacionId())){
			addActionErrorSession(this.getText("plataforma.usuario.add.usuarioaplicacion.field.aplicacionid.error"));
			sw=false;
		}
		if(PlataformaMensajeriaUtil.isEmptyNumber(usuarioAplicacion.getModo())){
			addActionErrorSession(this.getText("plataforma.usuario.add.usuarioaplicacion.field.modo.error"));
			sw=false;
		}
		
		return sw;
	}*/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<KeyValueObject> getComboAplicaciones() {
        List<KeyValueObject> result = new ArrayList<KeyValueObject>();
        KeyValueObject option = null;
        ArrayList<AplicacionBean> keys = null;
		try {
			String rolUsuario = PlataformaMensajeriaUtil.getRolFromSession(request);
			Integer idUsuario = PlataformaMensajeriaUtil.getIdUsuarioFromSession(request);
			//Se utiliza el mismo método que para extraer las aplicaciones que aparecerán en el menu dependiendo del rol del usuario.
			keys = (ArrayList<AplicacionBean>)servicioAplicacion.getAplicacionesMenu(rolUsuario, idUsuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(keys!=null&&keys.size()>0){
	        for (AplicacionBean key :keys) {
	            
	            option = new KeyValueObject();
	            option.setCodigo(key.getAplicacionId().toString());
	            option.setDescripcion(key.getNombre());
	            result.add(option);
	        }
		}
        return result;
    }
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<KeyValueObject> getComboServidores() {
        List<KeyValueObject> result = new ArrayList<KeyValueObject>();
        KeyValueObject option = null;
        //TODO: DEVOLVER SOLO LOS PARAMETROS ACTIVOS!!!!
        ArrayList<ServidorBean> keys = null;
		try {
			String rolUsuario = PlataformaMensajeriaUtil.getRolFromSession(request);
			Integer idUsuario = PlataformaMensajeriaUtil.getIdUsuarioFromSession(request);
			keys = (ArrayList<ServidorBean>)servicioServidor.getServidoresYProveedores(rolUsuario,idUsuario);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
		if(keys!=null&&keys.size()>0){
	        for (ServidorBean key :keys) {
	            
	            option = new KeyValueObject();
	            option.setCodigo(key.getServidorId().toString());
	            option.setDescripcion(key.getNombre());
	            result.add(option);
	        }
		}
        return result;
    }
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<KeyValueObject> getComboServicios() {
        List<KeyValueObject> result = new ArrayList<KeyValueObject>();
        KeyValueObject option = null;
        //TODO: DEVOLVER SOLO LOS PARAMETROS ACTIVOS!!!!
        ArrayList<ServicioBean> keys = null;
		try {
			String rolUsuario = PlataformaMensajeriaUtil.getRolFromSession(request);
			Integer idUsuario = PlataformaMensajeriaUtil.getIdUsuarioFromSession(request);
			if(estadisticaBean!=null && estadisticaBean.getAplicacionId()!=null && 
					!(estadisticaBean.getAplicacionId().equals(Integer.valueOf(0)))){
				keys = (ArrayList<ServicioBean>)servicioServicio.getServiciosByAplicacionId(estadisticaBean.getAplicacionId());
			}else{
				keys = (ArrayList<ServicioBean>)servicioServicio.getServicios(rolUsuario,idUsuario);
			}
			//keys = (ArrayList<ServicioBean>)servicioServicio.getServicios(rolUsuario,idUsuario);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<KeyValueObject> getComboEstados() {
        List<KeyValueObject> result = new ArrayList<KeyValueObject>();
        KeyValueObject option = null;
        //TODO: DEVOLVER SOLO LOS PARAMETROS ACTIVOS!!!!
        ArrayList<EstadoBean> keys = null;
		try {
			keys = (ArrayList<EstadoBean>)servicioEstado.getEstados();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
		if(keys!=null&&keys.size()>0){
	        for (EstadoBean key :keys) {
	            
	            option = new KeyValueObject();
	            option.setCodigo(key.getEstadoId().toString());
	            option.setDescripcion(key.getNombre());
	            result.add(option);
	        }
		}
        return result;
    }		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<KeyValueObject> getComboCanales() {
        List<KeyValueObject> result = new ArrayList<KeyValueObject>();
        KeyValueObject option = null;
        //TODO: DEVOLVER SOLO LOS PARAMETROS ACTIVOS!!!!
        ArrayList<CanalBean> keys = null;
		try {
			keys = (ArrayList<CanalBean>)servicioCanal.getCanales();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
		if(keys!=null&&keys.size()>0){
	        for (CanalBean key :keys) {
	            
	            option = new KeyValueObject();
	            option.setCodigo(key.getCanalId().toString());
	            option.setDescripcion(key.getNombre());
	            result.add(option);
	        }
		}
        return result;
    }	
	
	public List<KeyValueObject> getComboAgrupar(){
		this.comboAgrupar = EstadisticasPlataforma.getComboAgrupaciones();
		return comboAgrupar;
	}
	public ServicioAplicacion getServicioAplicacion() {
		return servicioAplicacion;
	}
	public void setServicioAplicacion(ServicioAplicacion servicioAplicacion) {
		this.servicioAplicacion = servicioAplicacion;
	}

	public void setComboAplicaciones(List<KeyValueObject> comboAplicaciones) {
		this.comboAplicaciones = comboAplicaciones;
	}
	@Override
	public void prepare() throws Exception {
			
	}

	public String getResultCount() {
		return resultCount;
	}
	public void setResultCount(String resultCount) {
		this.resultCount = resultCount;
	}
    public ServicioCanal getServicioCanal() {
		return servicioCanal;
	}
	public void setServicioCanal(ServicioCanal servicioCanal) {
		this.servicioCanal = servicioCanal;
	}
	public ServicioEstado getServicioEstado() {
		return servicioEstado;
	}
	public void setServicioEstado(ServicioEstado servicioEstado) {
		this.servicioEstado = servicioEstado;
	}


	public List<FilaEstadisticaBean> getListaFilaEstadisticaBean() {
		return listaFilaEstadisticaBean;
	}
	public void setListaFilaEstadisticaBean(
			List<FilaEstadisticaBean> listaFilaEstadisticaBean) {
		this.listaFilaEstadisticaBean = listaFilaEstadisticaBean;
	}
	public ServicioServidor getServicioServidor() {
		return servicioServidor;
	}
	public void setServicioServidor(ServicioServidor servicioServidor) {
		this.servicioServidor = servicioServidor;
	}
	public ServicioServicio getServicioServicio() {
		return servicioServicio;
	}
	public void setServicioServicio(ServicioServicio servicioServicio) {
		this.servicioServicio = servicioServicio;
	}
	public void setComboServidores(List<KeyValueObject> comboServidores) {
		this.comboServidores = comboServidores;
	}
	public void setComboServicios(List<KeyValueObject> comboServicios) {
		this.comboServicios = comboServicios;
	}
	public void setComboEstados(List<KeyValueObject> comboEstados) {
		this.comboEstados = comboEstados;
	}
	public void setComboCanales(List<KeyValueObject> comboCanales) {
		this.comboCanales = comboCanales;
	}
	public void setComboAgrupar(List<KeyValueObject> comboAgrupar) {
		this.comboAgrupar = comboAgrupar;
	}	
}
