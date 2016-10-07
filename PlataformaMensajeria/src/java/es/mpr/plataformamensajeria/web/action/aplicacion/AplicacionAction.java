package es.mpr.plataformamensajeria.web.action.aplicacion;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.xml.security.exceptions.Base64DecodingException;
import org.apache.xml.security.utils.Base64;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.security.context.SecurityContextHolder;

import com.map.j2ee.exceptions.BaseException;
import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;
import com.map.j2ee.util.KeyValueObject;
import com.map.j2ee.util.beanutils.converters.DateConverter;
import com.opensymphony.xwork2.Preparable;

import es.mpr.plataformamensajeria.beans.AplicacionBean;
import es.mpr.plataformamensajeria.beans.DetalleAplicacionBean;
import es.mpr.plataformamensajeria.beans.DetalleServicioBean;
import es.mpr.plataformamensajeria.beans.PlanificacionBean;
import es.mpr.plataformamensajeria.beans.ServicioBean;
import es.mpr.plataformamensajeria.beans.ServidorBean;
import es.mpr.plataformamensajeria.beans.ServidoresServiciosBean;
import es.mpr.plataformamensajeria.beans.UsuarioAplicacionBean;
import es.mpr.plataformamensajeria.impl.PlataformaPaginationAction;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioAplicacion;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioPlanificacion;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServicio;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServidor;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioUsuarioAplicacion;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

/**
 * <p>Clase Action de Struts2 para la gesti&oacute;n de los organismos.
 * 
 * <p>
 * Proporciona m&eacute;todos para la creaci&oacute;n, modificaci&oacute;n, borrado y listado de los Organismos
 * 
 * @author Altran
 *
 */
public class AplicacionAction extends PlataformaPaginationAction implements ServletRequestAware, Preparable{
	
	private static final long serialVersionUID = 1L;
	
	private static final String REQUEST_ATTRIBUTE_TOTALSIZE = "totalSize";
	private static final String REQUEST_ATTRIBUTE_PAGESIZE = "pageSize";
	

	private static final Integer PAGESIZE = new Integer(20); //Elementos por pagina
	
	public List<AplicacionBean> listaAplicaciones= null;
    private ServicioAplicacion servicioAplicacion;
    private ServicioServicio servicioServicio;
 	private ServicioPlanificacion servicioPlanificacion;
 	private ServicioUsuarioAplicacion servicioUsuarioAplicacion;
 	private ServicioServidor servicioServidor;
	private String idAplicacion;
    private String resultCount;
    private String[] checkDelList;
    private String checkPassword;
    public String getCheckPassword() {
		return checkPassword;
	}
	public void setCheckPassword(String checkPassword) {
		this.checkPassword = checkPassword;
	}
	private AplicacionBean aplicacion;
    private PlanificacionBean planificacion;
    private String idPlanificacion;
    private String idServicio;
    private String idServidor;
    private String idProveedorSMS;
    private String idReceptorSMS;
    private String idServidorPush;
    
	public String getIdProveedorSMS() {
		return idProveedorSMS;
	}
	public void setIdProveedorSMS(String idProveedorSMS) {
		this.idProveedorSMS = idProveedorSMS;
	}
	public String getIdReceptorSMS() {
		return idReceptorSMS;
	}
	public void setIdReceptorSMS(String idReceptorSMS) {
		this.idReceptorSMS = idReceptorSMS;
	}
	public void setIdServidor(String idServidor) {
		this.idServidor = idServidor;
	}
	public String getIdServicio() {
		return idServicio;
	}
	public void setIdServicio(String idServicio) {
		this.idServicio = idServicio;
	}
	private List<ServicioBean> listaServiciosAplicacion;

	public List<ServicioBean> getListaServiciosAplicacion() {
		return listaServiciosAplicacion;
	}
	public void setListaServiciosAplicacion(
			List<ServicioBean> listaServiciosAplicacion) {
		this.listaServiciosAplicacion = listaServiciosAplicacion;
	}
	public List<AplicacionBean> getListaAplicaciones() {
		return listaAplicaciones;
	}
	public void setListaAplicaciones(List<AplicacionBean> listaAplicaciones) {
		this.listaAplicaciones = listaAplicaciones;
	}
	public AplicacionBean getAplicacion() {
		return aplicacion;
	}
	public void setAplicacion(AplicacionBean aplicacion) {
		this.aplicacion = aplicacion;
	}
	public String getIdAplicacion() {
		return idAplicacion;
	}
	public String newSearch() throws BaseException {
    	return SUCCESS;
    }
	public String search() throws BaseException {
		
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
		
	   	int page = getPage("tableId"); //Pagina a mostrar
    	String order = getOrder("tableId"); //Ordenar de modo ascendente o descendente
    	String columnSort = getColumnSort("tableId"); //Columna usada para ordenar
    	
    	if(aplicacion != null)
    		if(aplicacion.getNombre() != null && aplicacion.getNombre().length()<=0)
    			aplicacion.setNombre(null);
    	    	
    	int inicio = (page-1)*PAGESIZE;
    	boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
    	PaginatedList<AplicacionBean> result = servicioAplicacion.getAplicaciones(inicio,(export)?-1:PAGESIZE,order,columnSort,aplicacion); 
    	Integer totalSize = result.getTotalList();
    	
    	listaAplicaciones =  result.getPageList();
    	
    	//Atributos de request
    	getRequest().setAttribute(REQUEST_ATTRIBUTE_TOTALSIZE, totalSize);
    	if(!export){
    		getRequest().setAttribute(REQUEST_ATTRIBUTE_PAGESIZE, PAGESIZE);
    	}else{
    		getRequest().setAttribute(REQUEST_ATTRIBUTE_PAGESIZE, totalSize);
    	}
    	    	    	
    	if (listaAplicaciones!=null && !listaAplicaciones.isEmpty())
    	{    		
    		for (int indice=0;indice<listaAplicaciones.size();indice++) {
    			
    			AplicacionBean aplicacion = listaAplicaciones.get(indice);
    			aplicacion.setNombre(StringEscapeUtils.escapeHtml(aplicacion.getNombre()));
    			aplicacion.setDescripcion(StringEscapeUtils.escapeHtml(aplicacion.getDescripcion()));
    		}
    	}
    	    	 	
        return SUCCESS;
	}
    public String execute() throws BaseException {
    	
		try{
			SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}catch(Exception e){
			return "noUser";
		}
    	int page = getPage("tableId"); //Pagina a mostrar
    	String order = getOrder("tableId"); //Ordenar de modo ascendente o descendente
    	String columnSort = getColumnSort("tableId"); //Columna usada para ordenar
    	
    	if(aplicacion != null)
    		if(aplicacion.getNombre() != null && aplicacion.getNombre().length()<=0)
    			aplicacion.setNombre(null);
    	    	
    	int inicio = (page-1)*PAGESIZE;
    	boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
    	PaginatedList<AplicacionBean> result = servicioAplicacion.getAplicaciones(inicio,(export)?-1:PAGESIZE,order,columnSort,aplicacion); 
    	Integer totalSize = result.getTotalList();
    	
    	listaAplicaciones =  result.getPageList();
    	
    	getRequest().setAttribute(REQUEST_ATTRIBUTE_TOTALSIZE, totalSize);
    	
    	if(!export){
    		getRequest().setAttribute(REQUEST_ATTRIBUTE_PAGESIZE, PAGESIZE);
    	}else{
    		getRequest().setAttribute(REQUEST_ATTRIBUTE_PAGESIZE, totalSize);
    	}
    	if (listaAplicaciones!=null && !listaAplicaciones.isEmpty())
    	{    		
    		for (int indice=0;indice<listaAplicaciones.size();indice++) {
    			
    			AplicacionBean aplicacion = listaAplicaciones.get(indice);
    			aplicacion.setNombre(StringEscapeUtils.escapeHtml(aplicacion.getNombre()));
    			aplicacion.setDescripcion(StringEscapeUtils.escapeHtml(aplicacion.getDescripcion()));
    		}
    	}
    	    	 	
        return SUCCESS;
    }
    
    public String create() throws BaseException {
    	
		try{
			SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}catch(Exception e){
			return "noUser";
		}
    	if(aplicacion != null){
    		//throw new BusinessException("EL aplicacion recibido es nulo");
    	
	    	if(aplicacion.getIsActivo()!=null&&aplicacion.getIsActivo().indexOf("activo")!=-1){
	    		aplicacion.setActivo(new Integer(1));
	    	}else{
	    		aplicacion.setActivo(new Integer(0));
	    	}
	    	if(!validPasswords(aplicacion)||!validaObligatorios(aplicacion,false))
	    		return ERROR;
	    	aplicacion.setPassword(Base64.encode(aplicacion.getPassword().trim().getBytes())); //Eliminamos los espacios
	    	Integer idAplicacion = servicioAplicacion.newAplicacion(aplicacion);
	    	this.idAplicacion = idAplicacion.toString();

		    addActionMessageSession(this.getText("plataforma.aplicacion.create.ok"));
    	}else{
    		addActionErrorSession(this.getText("plataforma.aplicacion.create.error"));
    		//return ERROR;
    	}
    	return SUCCESS;
    	
    }
    
    private boolean validaObligatorios(AplicacionBean aplicacion2,boolean isUpdate) {
    	boolean sw=true;
    	if(PlataformaMensajeriaUtil.isEmpty(aplicacion2.getNombre())){
    		addActionErrorSession(this.getText("plataforma.aplicacion.field.nombre.error"));
    		sw=false;
    	}
    	if(PlataformaMensajeriaUtil.isEmpty(aplicacion2.getDescripcion())){
    		addActionErrorSession(this.getText("plataforma.aplicacion.field.descripcion.error"));
    		sw=false;
    	}
    	if(!isUpdate&&servicioAplicacion.existeUsuario(aplicacion2.getUsuario())){
    		addActionErrorSession("El nombre de usuario '"+aplicacion2.getUsuario()+"' ya existe. Por favor seleccione otro.");
    		sw=false;
    	}
    	
    	if(PlataformaMensajeriaUtil.isEmpty(aplicacion2.getResponsableFuncionalNombre())){
    		addActionErrorSession(this.getText("plataforma.aplicacion.field.responsable.funcional.nombre"));
    		sw=false;
    	}   	
    	if(PlataformaMensajeriaUtil.isEmpty(aplicacion2.getResponsableFuncionalEmail())){
    		addActionErrorSession(this.getText("plataforma.aplicacion.field.responsable.funcional.email"));
    		sw=false;
    	}   
    	
    	if(PlataformaMensajeriaUtil.isEmpty(aplicacion2.getResponsableTecnicoNombre())){
    		addActionErrorSession(this.getText("plataforma.aplicacion.field.responsable.tecnico.nombre"));
    		sw=false;
    	}  
    	
    	if(PlataformaMensajeriaUtil.isEmpty(aplicacion2.getResponsableTecnicoEmail())){
    		addActionErrorSession(this.getText("plataforma.aplicacion.field.responsable.tecnico.email"));
    		sw=false;
    	}  
    	
    	
    	
		return sw;
	}
	public String update() throws BaseException {
		
		try{
			SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}catch(Exception e){
			return "noUser";
		}
    	AplicacionBean aplicacionBBDD = null;
    	if(aplicacion == null){
    		//throw new BusinessException("EL aplicacion recibido es nulo");
    		addActionErrorSession(this.getText("plataforma.aplicacion.update.error"));

    	}else{
	    	System.out.println("[AplicacionAction - Idaplicacion] valor == " + aplicacion.getAplicacionId() );
	    	if(aplicacion.getAplicacionId()==null){
	    		if(idAplicacion!=null){
	    			aplicacion.setAplicacionId(new Integer(idAplicacion));
	    			aplicacionBBDD = servicioAplicacion.loadAplicacion(aplicacion);
	    		}else{
	    			String idAplicacion = (String)request.getAttribute("idAplicacion");
	    			System.out.println("[ServidoresAction - request.getAttribute('idAplicacion)' == " + idAplicacion);
	    			if(idAplicacion!=null){
	    				aplicacion.setId(new Long(idAplicacion));
	    				aplicacionBBDD = servicioAplicacion.loadAplicacion(aplicacion);
	    			}
	    		}
	    			
	    		System.out.println("[AplicacionAction - Idaplicacion despues de setear idAplicacion] valor == " + aplicacion.getAplicacionId() );
	    	}else{
	    		aplicacionBBDD = servicioAplicacion.loadAplicacion(aplicacion);
	    		
	    	}
	    	if(aplicacionBBDD!=null){
	    		aplicacionBBDD.setNombre(aplicacion.getNombre());
	    		aplicacionBBDD.setDescripcion(aplicacion.getDescripcion());
	    		aplicacionBBDD.setActivo(aplicacion.getActivo());
	    		aplicacionBBDD.setUsuario(aplicacion.getUsuario());
	    		aplicacionBBDD.setResponsableFuncionalEmail(aplicacion.getResponsableFuncionalEmail());
	    		aplicacionBBDD.setResponsableFuncionalNombre(aplicacion.getResponsableFuncionalNombre());
	    		aplicacionBBDD.setResponsableTecnicoEmail(aplicacion.getResponsableFuncionalEmail());
	    		aplicacionBBDD.setResponsableTecnicoNombre(aplicacion.getResponsableFuncionalNombre());
	    		
	    		String oldPass="";
	    		try {
					byte[] oldPassByte = Base64.decode(aplicacionBBDD.getPassword());
					oldPass = new String(oldPassByte);
				} catch (Base64DecodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		
	    		if(null!=aplicacion.getPassword()){
	    			aplicacionBBDD.setPassword(Base64.encode(aplicacion.getPassword().trim().getBytes())); //Eliminamos los espacios
	    		}
	    		
    			if(!validPasswords(aplicacion)){
    				return SUCCESS;
    			}
    			
	    		if(!validaObligatorios(aplicacionBBDD,true)){
	    			return SUCCESS;
	    		}
	    	}
	    	servicioAplicacion.updateAplicacion(aplicacionBBDD);
	    	addActionMessageSession(this.getText("plataforma.aplicacion.update.ok"));

    	}
    	return SUCCESS;
    	
    }
    
    private boolean validPasswords(AplicacionBean aplicacion2) {
		boolean sw=true;
		if(PlataformaMensajeriaUtil.isEmpty(aplicacion2.getPassword())){
			addFieldErrorSession(this.getText("plataforma.aplicacion.field.password"));
			sw=false;
		}
		if(PlataformaMensajeriaUtil.isEmpty(aplicacion2.getRePassword())&&!PlataformaMensajeriaUtil.isEmpty(checkPassword)&&checkPassword.equals("true")){
			addFieldErrorSession(this.getText("plataforma.aplicacion.field.rePassword"));
			sw=false;
		}
		 //Eliminamos los espacios
		if(!PlataformaMensajeriaUtil.isEmpty(aplicacion2.getPassword())&&!PlataformaMensajeriaUtil.isEmpty(aplicacion2.getRePassword())&&
				!(aplicacion2.getPassword().trim().equals(aplicacion2.getRePassword().trim()))&&!PlataformaMensajeriaUtil.isEmpty(checkPassword)&&checkPassword.equals("true")){
			addFieldErrorSession(this.getText("plataforma.aplicacion.passwords.error"));
			sw=false;
		}
		if(PlataformaMensajeriaUtil.isEmpty(aplicacion2.getUsuario())){
			addFieldErrorSession(this.getText("plataforma.aplicacion.field.usuario"));
			sw=false;
		}
		return sw;
		
	}
	public String load() throws BaseException {
		try{
			SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}catch(Exception e){
			return "noUser";
		}
    	if(idAplicacion == null)
    		throw new BusinessException("EL idAplicacion recibido es nulo");
    	try {
			aplicacion = new AplicacionBean();
			aplicacion.setAplicacionId(new Integer(idAplicacion));
			aplicacion= servicioAplicacion.loadAplicacion(aplicacion);
			try {
				aplicacion.setPassword(new String(Base64.decode(aplicacion.getPassword())));
			} catch (Base64DecodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return SUCCESS;
    	} catch (NumberFormatException e) {
			String mensg = this.getText("errors.action.organismo.loadOrganismo",new String[]{aplicacion.getAplicacionId().toString()});
			throw new BusinessException(mensg);
		} catch (BusinessException e) {
			String mensg = this.getText("errors.action.organismo.loadOrganismo",new String[]{aplicacion.getAplicacionId().toString()});
			throw new BusinessException(mensg);
		}
    	
    }
  
    public String delete() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
    	if(idAplicacion == null){
    		//throw new BusinessException("EL idAplicacion recibido es nulo");
    		addActionErrorSession(this.getText("plataforma.aplicacion.delete.error"));

    	}else{
	    	aplicacion = new AplicacionBean();
	    	aplicacion.setAplicacionId(new Integer(idAplicacion));
	    	List<ServicioBean> listaServiciosBean = servicioServicio.getServiciosByAplicacionId(aplicacion.getAplicacionId());
	    	if(listaServiciosBean!=null){
		    	Iterator<ServicioBean> iterator = listaServiciosBean.iterator();
		    	//Borrado en cascada de planificaciones y servicios
		    	while(iterator.hasNext()){
		    		ServicioBean servicioBean = iterator.next();
		    		List<PlanificacionBean> listaPlanificacionServicioBean = servicioPlanificacion.getPlanificacionesByServicioID(servicioBean.getServicioId());
		    		if (listaPlanificacionServicioBean != null){
			    		Iterator<PlanificacionBean> iteratorPlanificacionesServicios = listaPlanificacionServicioBean.iterator();
			    		while(iteratorPlanificacionesServicios.hasNext()){
			    			PlanificacionBean planificacionBean = iteratorPlanificacionesServicios.next();
			    			servicioPlanificacion.deletePlanificacion(planificacionBean);
			    		}
		    		}
		    		
		    		servicioServicio.deleteServicio(servicioBean);
		    	}
	    	}
	    	servicioAplicacion.deleteAplicacion(aplicacion);
	    	addActionMessageSession(this.getText("plataforma.aplicacion.delete.ok"));
    	}
    	return SUCCESS;
    	
    }
    
    public String deleteSelected() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
    	if(checkDelList == null){
    		//throw new BusinessException("No se ha seleccionado ningúna aplicacion para eliminar");
    		addActionErrorSession(this.getText("plataforma.aplicacion.deleteSelected.error"));

    	}else{
	    	for(String idAplicacion : checkDelList){
		    	aplicacion = new AplicacionBean();
		    	aplicacion.setAplicacionId(new Integer(idAplicacion));
		    	servicioAplicacion.deleteAplicacion(aplicacion);
	    	}
	    	addActionMessageSession(this.getText("plataforma.aplicacion.deleteSelected.ok"));

    	}
    	return SUCCESS;
    	
    }    
    public String loadDetalleAplicacion() throws BusinessException, IllegalAccessException, InvocationTargetException{
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
    	AplicacionBean detalleApp = new AplicacionBean();
    	DetalleAplicacionBean detalle = new DetalleAplicacionBean();
    	if(idAplicacion!=null){
    		detalleApp.setAplicacionId(new Integer(idAplicacion));
    		detalleApp = servicioAplicacion.loadAplicacion(detalleApp);
 			Date defaultValue = null;         
 			List<UsuarioAplicacionBean> listaUsuariosAplicacion = servicioUsuarioAplicacion.getUsuarioAplicacionesByAplicacionId(new Integer(idAplicacion));
 			detalle.setListaUsuariosAplicacion(listaUsuariosAplicacion);
			DateConverter converter = new DateConverter (defaultValue);         
			ConvertUtils.register (converter, java.util.Date.class);
			BeanUtils.copyProperties(detalle, detalleApp);
			detalle.setActivo(detalleApp.getActivo());
			detalle.setFechaCreacion(detalleApp.getFechaCreacion());
			detalle.setFechaModificacion(detalleApp.getFechaModificacion());
			List<ServicioBean> listServicioBean = servicioServicio.getServiciosByAplicacionId(detalle.getAplicacionId());
			if(listServicioBean!=null)
			for(ServicioBean ser : listServicioBean){
				DetalleServicioBean serBean = new DetalleServicioBean();
				BeanUtils.copyProperties(serBean, ser);
				serBean.setActivo(ser.getActivo());
				serBean.setFechaCreacion(ser.getFechaCreacion());
				serBean.setFechaModificacion(ser.getFechaModificacion());
				serBean.setFromMail(ser.getFromMail());
				serBean.setFromMailName(ser.getFromMailName());
				List<ServidoresServiciosBean> servidoresServiciosBeanList = servicioServicio.getServidoresServicios(ser.getServicioId().toString());
				serBean.setListaServidoresServicios(servidoresServiciosBeanList);
				List<PlanificacionBean> planificacionesList = servicioPlanificacion.getPlanificacionesByServicioID(ser.getServicioId());
				serBean.setListaPlanificaciones(planificacionesList);
				detalle.addDetalleServicio(serBean);
			}
			
			detalleAplicacion = detalle;	
			
    	}
    	return SUCCESS;
    }
   
	private List<ServicioBean> loadSeviciosAplicacion() {
   		List<ServicioBean> lista = null;
   		if(idAplicacion!=null&&idAplicacion.length()>0){
   			try {
   				lista = servicioServicio.getServiciosByAplicacionId(new Integer(idAplicacion));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (BusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
   		}else if(aplicacion!=null&&aplicacion.getAplicacionId()!=null){
   			try {
   				lista = servicioServicio.getServiciosByAplicacionId(new Integer(aplicacion.getAplicacionId()));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (BusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
   		}
			return lista;
	}
	ServicioBean servicio;
	List<KeyValueObject> comboServidores = new ArrayList<KeyValueObject>();
    public String loadPlanificaciones() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
    	if(idPlanificacion == null)
    		throw new BusinessException("EL idPlanificacion recibido es nulo");
    	try {
			planificacion = new PlanificacionBean();
			planificacion.setPlanificacionId(new Integer(idPlanificacion));
			planificacion= servicioPlanificacion.loadPlanificacion(planificacion);
			servicio = new ServicioBean();
			if(planificacion.getServicioId()!=null){
				servicio.setId(planificacion.getServicioId());
				servicio = servicioServicio.loadServicio(servicio);
			}
			String rolUsuario = PlataformaMensajeriaUtil.getRolFromSession(request);
			if(rolUsuario.equals(PlataformaMensajeriaUtil.ROL_ADMINISTRADOR)){
				comboServidores = loadComboServidores();
			} else if(rolUsuario.equals(PlataformaMensajeriaUtil.ROL_PROPIETARIO)){
				comboServidores = loadComboServidoresServicio(servicio.getServicioId());
			}
				
			return SUCCESS;
    	} catch (NumberFormatException e) {
			String mensg = this.getText("errors.action.organismo.loadOrganismo",new String[]{planificacion.getPlanificacionId().toString()});
			throw new BusinessException(mensg);
		} catch (BusinessException e) {
			String mensg = this.getText("errors.action.organismo.loadOrganismo",new String[]{planificacion.getPlanificacionId().toString()});
			throw new BusinessException(mensg);
		}
    	
    }     
    public List<KeyValueObject> getComboServidores(){
    	return comboServidores;
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
	private List<KeyValueObject> loadComboServidores() {
        List<KeyValueObject> result = new ArrayList<KeyValueObject>();
        KeyValueObject option = null;
        //TODO: DEVOLVER SOLO LOS PARAMETROS ACTIVOS!!!!
        ArrayList<ServidorBean> keys = null;
		try {
	    	if(planificacion!=null&&planificacion.getTipoPlanificacionId()!=null&&planificacion.getTipoPlanificacionId()==1){
	    		keys = (ArrayList<ServidorBean>)servicioServidor.getServidoresByTipoPlanificacion("2");
	    	}else if(planificacion!=null&&planificacion.getTipoPlanificacionId()!=null&&planificacion.getTipoPlanificacionId()==2){
	    		keys = (ArrayList<ServidorBean>)servicioServidor.getServidoresByTipoPlanificacion("1");
	    	}else if(planificacion!=null&&planificacion.getTipoPlanificacionId()!=null&&planificacion.getTipoPlanificacionId()==3){
	    		keys = (ArrayList<ServidorBean>)servicioServidor.getServidoresByTipoPlanificacion("3");
	    	}else if(planificacion!=null&&planificacion.getTipoPlanificacionId()!=null&&planificacion.getTipoPlanificacionId()==4){
	    		keys = (ArrayList<ServidorBean>)servicioServidor.getServidoresByTipoPlanificacion("4");
	    	}
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
	private List<KeyValueObject> loadComboServidoresServicio(Integer servicioId) throws BusinessException {
        List<KeyValueObject> result = new ArrayList<KeyValueObject>();
        KeyValueObject option = null;
        
        ArrayList<ServidoresServiciosBean> keys = null;
        
        if(null != servicioId){
        	keys = (ArrayList<ServidoresServiciosBean>) servicioServicio.getServidoresServicios(servicioId.toString());

    		if(keys!=null&&keys.size()>0) {
    			for (ServidoresServiciosBean key :keys) {
                
    	            option = new KeyValueObject();
    	            option.setCodigo(key.getServidorId().toString());
    	            option.setDescripcion(key.getNombreServidor());
    	            result.add(option);
    			}
    		}
        }
        
        return result;
    }
	public PlanificacionBean getPlanificacion() {
		return planificacion;
	}
	public void setPlanificacion(PlanificacionBean planificacion) {
		this.planificacion = planificacion;
	}
	public String getIdPlanificacion() {
		return idPlanificacion;
	}
	public void setIdPlanificacion(String idPlanificacion) {
		this.idPlanificacion = idPlanificacion;
	}
	@Override
	public void prepare() throws Exception {
		//contextUsuarios = getComboValues();
		listaServiciosAplicacion = loadSeviciosAplicacion();
	}



	public List<AplicacionBean> getListaServidores() {
		return listaAplicaciones;
	}
	public void setListaServidores(List<AplicacionBean> listaAplicaciones) {
		this.listaAplicaciones = listaAplicaciones;
	}
	public ServicioAplicacion getServicioAplicacion() {
		return servicioAplicacion;
	}
	public void setServicioAplicacion(ServicioAplicacion servicioAplicacion) {
		this.servicioAplicacion = servicioAplicacion;
	}
	public AplicacionBean getServidor() {
		return aplicacion;
	}
	public void setServidor(AplicacionBean aplicacion) {
		this.aplicacion = aplicacion;
	}
	public String getIdServidor() {
		return idServidor;
	}
	public void setIdAplicacion(String idAplicacion) {
		this.idAplicacion = idAplicacion;
	}
	public String getResultCount() {
		return resultCount;
	}
	public void setResultCount(String resultCount) {
		this.resultCount = resultCount;
	}
	public String[] getCheckDelList() {
		return checkDelList;
	}
	public void setCheckDelList(String[] checkDelList) {
		this.checkDelList = checkDelList;
	}
 	public ServicioUsuarioAplicacion getServicioUsuarioAplicacion() {
		return servicioUsuarioAplicacion;
	}
	public void setServicioUsuarioAplicacion(
			ServicioUsuarioAplicacion servicioUsuarioAplicacion) {
		this.servicioUsuarioAplicacion = servicioUsuarioAplicacion;
	}
	private DetalleAplicacionBean detalleAplicacion;
    public DetalleAplicacionBean getDetalleAplicacion() {
		return detalleAplicacion;
	}
	public void setDetalleAplicacion(DetalleAplicacionBean detalleAplicacion) {
		this.detalleAplicacion = detalleAplicacion;
	}
	public ServicioPlanificacion getServicioPlanificacion() {
		return servicioPlanificacion;
	}
	public void setServicioPlanificacion(ServicioPlanificacion servicioPlanificacion) {
		this.servicioPlanificacion = servicioPlanificacion;
	}
	public ServicioServicio getServicioServicio() {
		return servicioServicio;
	}
	public void setServicioServicio(ServicioServicio servicioServicio) {
		this.servicioServicio = servicioServicio;
	}

	public void setServicioServidor(ServicioServidor servicioServidor) {
		this.servicioServidor = servicioServidor;
	}
	public ServicioServidor getServicioServidor() {
		return servicioServidor;
	}
	
	/**
	 * Método que resuelve el lugar donde tiene que volver
	 */
	public String getVolver() {
		String volver="buscarAplicaciones.action";
		if(!PlataformaMensajeriaUtil.isEmpty(from)&&!PlataformaMensajeriaUtil.isEmpty(idFrom)){
			volver=from+"?"+var+"="+idFrom;
		}
		return volver;
	}
	public String getIdServidorPush() {
		return idServidorPush;
	}
	public void setIdServidorPush(String idServidorPush) {
		this.idServidorPush = idServidorPush;
	}
	
}
