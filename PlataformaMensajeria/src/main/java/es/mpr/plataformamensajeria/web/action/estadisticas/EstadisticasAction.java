package es.mpr.plataformamensajeria.web.action.estadisticas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

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
@Controller("estadisticaAction")
@Scope("prototype")
public class EstadisticasAction extends PlataformaPaginationAction implements ServletRequestAware, Preparable{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**  logger. */
	private static Logger logger = Logger.getLogger(EstadisticasAction.class);
	
	/**  combo aplicaciones. */
	List<KeyValueObject> comboAplicaciones = new ArrayList<KeyValueObject>();
	
	/**  combo servidores. */
	List<KeyValueObject> comboServidores = new ArrayList<KeyValueObject>();
	
	/**  combo servicios. */
	List<KeyValueObject> comboServicios = new ArrayList<KeyValueObject>();
	
	/**  combo estados. */
	List<KeyValueObject> comboEstados = new ArrayList<KeyValueObject>();
	
	/**  combo canales. */
	List<KeyValueObject> comboCanales = new ArrayList<KeyValueObject>();
	
	/**  combo agrupar. */
	List<KeyValueObject> comboAgrupar = new ArrayList<KeyValueObject>();
	
	/**  lista fila estadistica bean. */
	List<FilaEstadisticaBean> listaFilaEstadisticaBean = null;
	
	/**  servicio aplicacion. */
	@Resource(name="servicioAplicacionImpl")
    private ServicioAplicacion servicioAplicacion;
    
	/**  servicio servidor. */
	@Resource(name="servicioServidorImpl")
	private ServicioServidor servicioServidor;
    
	/**  servicio servicio. */
	@Resource(name="servicioServicioImpl")
	private ServicioServicio servicioServicio;
    
    /**  servicio estado. */
    @Resource(name="servicioEstadoImpl")
    private ServicioEstado servicioEstado;
    
    /**  servicio canal. */
    @Resource(name="servicioCanalImpl")
    private ServicioCanal servicioCanal;
    
    /**  estadisticas plataforma. */
    @Resource(name="estadisticasPlataforma")
    private EstadisticasPlataforma estadisticasPlataforma;
    
    /**  vista id selected. */
    private String vistaIdSelected;
	
	/**  result count. */
	private String resultCount;
	
	/**  reverse. */
	private String reverse="false";
	
	/**  estadistica bean. */
	private EstadisticasBean estadisticaBean;
	
	/**
	 * New search.
	 *
	 * @return the string
	 */
	public String newSearch() {
    	return SUCCESS;
    }
	
	/**
	 * Search.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	///MIGRADO
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
			    	listaFilaEstadisticaBean = estadisticasPlataforma.getEstadisticas(estadisticaBean, true);
			    }else{
			    	listaFilaEstadisticaBean = estadisticasPlataforma.getEstadisticas(estadisticaBean, false);
			    }
			    
			    if(listaFilaEstadisticaBean!=null&&listaFilaEstadisticaBean.isEmpty()){
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
     * Comprueba si hay un usuario logueado y con un rol definido.
     *
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
  * Valida busqueda.
  *
  * @param estadisticaBean2 the estadistica bean 2
  * @return true, if successful
  */
private boolean validaBusqueda(EstadisticasBean estadisticaBean2) {
	 	boolean sw=true;
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
	
	/**
	 * Obtener combo aplicaciones.
	 *
	 * @return combo aplicaciones
	 */
	///MIGRADO
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
			logger.error("EstadisticasAction - getComboAplicaciones:", e);
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
	
	/**
	 * Obtener combo servidores.
	 *
	 * @return combo servidores
	 */
	///MIGRADO
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
			logger.error("EstadisticasAction - getComboServidores:", e);
		}
        
        
		if(keys!=null&&keys.size()>0){
	        for (ServidorBean key :keys) {
	            
	            option = new KeyValueObject();
	            option.setCodigo(key.getServidorid().toString());
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
	 */
	///MIGRADO
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
			logger.error("EstadisticasAction - getComboServicios:", e);
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
	 * Obtener combo estados.
	 *
	 * @return combo estados
	 */
	///MIGRADO
	public List<KeyValueObject> getComboEstados() {
        List<KeyValueObject> result = new ArrayList<KeyValueObject>();
        KeyValueObject option = null;
        //TODO: DEVOLVER SOLO LOS PARAMETROS ACTIVOS!!!!
        ArrayList<EstadoBean> keys = null;
		try {
			keys = (ArrayList<EstadoBean>)servicioEstado.getEstados();
		} catch (BusinessException e) {
			logger.error("EstadisticasAction - getComboEstados:", e);
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
	
	/**
	 * Obtener combo canales.
	 *
	 * @return combo canales
	 */
	///MIGRADO
	public List<KeyValueObject> getComboCanales() {
        List<KeyValueObject> result = new ArrayList<KeyValueObject>();
        KeyValueObject option = null;
        //TODO: DEVOLVER SOLO LOS PARAMETROS ACTIVOS!!!!
        ArrayList<CanalBean> keys = null;
		try {
			keys = (ArrayList<CanalBean>)servicioCanal.getCanales();
		} catch (BusinessException e) {
			logger.error("EstadisticasAction - getComboCanales:", e);
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
	
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.impl.PlataformaPaginationAction#prepare()
	 */
	@Override
	public void prepare() throws Exception {
			
	}
	
	/**
	 * Obtener combo agrupar.
	 *
	 * @return combo agrupar
	 */
	public List<KeyValueObject> getComboAgrupar(){
		this.comboAgrupar = EstadisticasPlataforma.getComboAgrupaciones();
		return comboAgrupar;
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
		this.comboAplicaciones = comboAplicaciones;
	}

	/**
	 * Obtener vista id selected.
	 *
	 * @return vista id selected
	 */
	public String getVistaIdSelected() {
		return vistaIdSelected;
	}
	
	/**
	 * Modificar vista id selected.
	 *
	 * @param vistaIdSelected new vista id selected
	 */
	public void setVistaIdSelected(String vistaIdSelected) {
		this.vistaIdSelected = vistaIdSelected;
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
	 * Obtener servicio estado.
	 *
	 * @return servicio estado
	 */
	public ServicioEstado getServicioEstado() {
		return servicioEstado;
	}
	
	/**
	 * Modificar servicio estado.
	 *
	 * @param servicioEstado new servicio estado
	 */
	public void setServicioEstado(ServicioEstado servicioEstado) {
		this.servicioEstado = servicioEstado;
	}


	/**
	 * Obtener lista fila estadistica bean.
	 *
	 * @return lista fila estadistica bean
	 */
	public List<FilaEstadisticaBean> getListaFilaEstadisticaBean() {
		return listaFilaEstadisticaBean;
	}
	
	/**
	 * Modificar lista fila estadistica bean.
	 *
	 * @param listaFilaEstadisticaBean new lista fila estadistica bean
	 */
	public void setListaFilaEstadisticaBean(
			List<FilaEstadisticaBean> listaFilaEstadisticaBean) {
		this.listaFilaEstadisticaBean = listaFilaEstadisticaBean;
	}
	
	/**
	 * Obtener servicio servidor.
	 *
	 * @return servicio servidor
	 */
	public ServicioServidor getServicioServidor() {
		return servicioServidor;
	}
	
	/**
	 * Modificar servicio servidor.
	 *
	 * @param servicioServidor new servicio servidor
	 */
	public void setServicioServidor(ServicioServidor servicioServidor) {
		this.servicioServidor = servicioServidor;
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
	 * Modificar combo servidores.
	 *
	 * @param comboServidores new combo servidores
	 */
	public void setComboServidores(List<KeyValueObject> comboServidores) {
		this.comboServidores = comboServidores;
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
	 * Modificar combo estados.
	 *
	 * @param comboEstados new combo estados
	 */
	public void setComboEstados(List<KeyValueObject> comboEstados) {
		this.comboEstados = comboEstados;
	}
	
	/**
	 * Modificar combo canales.
	 *
	 * @param comboCanales new combo canales
	 */
	public void setComboCanales(List<KeyValueObject> comboCanales) {
		this.comboCanales = comboCanales;
	}
	
	/**
	 * Modificar combo agrupar.
	 *
	 * @param comboAgrupar new combo agrupar
	 */
	public void setComboAgrupar(List<KeyValueObject> comboAgrupar) {
		this.comboAgrupar = comboAgrupar;
	}	
	
	/**
	 * Obtener reverse.
	 *
	 * @return reverse
	 */
	public String getReverse() {
		return reverse;
	}
	
	/**
	 * Modificar reverse.
	 *
	 * @param reverse new reverse
	 */
	public void setReverse(String reverse) {
		this.reverse = reverse;
	}
	
	/**
	 * Obtener estadistica bean.
	 *
	 * @return estadistica bean
	 */
	public EstadisticasBean getEstadisticaBean() {
		return estadisticaBean;
	}
	
	/**
	 * Modificar estadistica bean.
	 *
	 * @param estadisticaBean new estadistica bean
	 */
	public void setEstadisticaBean(EstadisticasBean estadisticaBean) {
		this.estadisticaBean = estadisticaBean;
	}
}
