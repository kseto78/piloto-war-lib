package es.mpr.plataformamensajeria.web.action.servidorespush;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.security.context.SecurityContextHolder;

import com.map.j2ee.exceptions.BaseException;
import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;
import com.map.j2ee.util.KeyValueObject;
import com.map.j2ee.util.StringUtil;
import com.opensymphony.xwork2.Preparable;

import es.mpr.plataformamensajeria.beans.ParametroServidorBean;
import es.mpr.plataformamensajeria.beans.PlanificacionBean;
import es.mpr.plataformamensajeria.beans.PlataformaBean;
import es.mpr.plataformamensajeria.beans.ServidorPushBean;
import es.mpr.plataformamensajeria.beans.TipoParametroBean;
import es.mpr.plataformamensajeria.impl.PlataformaPaginationAction;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioParametroServidor;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioPlanificacion;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioPlataforma;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServidorPush;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioTipoParametro;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

/**
 * <p>Clase Action de Struts2 para la gestion de los servidores Push.
 * 
 * <p>
 * Proporciona metodos para la creacion, modificacion, borrado y listado de los servidores Push.
 * 
 * @author jgonzvil
 *
 */
public class ServidoresPushAction extends PlataformaPaginationAction implements ServletRequestAware, Preparable{
	
	private static final long serialVersionUID = 1L;
	
	private static final String REQUEST_ATTRIBUTE_TOTALSIZE = "totalSize";
	private static final String REQUEST_ATTRIBUTE_PAGESIZE = "pageSize";
	
	private static final int PARAMETRO_ID_PLATAFORMA_IOS=2;
	private static final int PARAMETRO_ID_TIPO_PLANIFICACION=4;
	private static final int PARAMETRO_SERVIDOR_TIPO_TELEFONO=7;

	private static final Integer PAGESIZE = Integer.valueOf(20); //Elementos por pagina
	List<KeyValueObject> comboTipoParametros = new ArrayList<KeyValueObject>();
	List<KeyValueObject> comboPlataformas = new ArrayList<KeyValueObject>();
	public List<ServidorPushBean> listaServidoresPush= null;
    private ServicioServidorPush servicioServidorPush;
    private ServicioTipoParametro servicioTipoParametro;
    private ServicioParametroServidor servicioParametroServidor;
    private ServicioPlanificacion servicioPlanificacion;
    private ServicioPlataforma servicioPlataforma;

	private String tipoParametroId;
	
    private String idServidorPush;
    private String resultCount;
    private String[] checkDelList;
    private ParametroServidorBean parametroServidor;
    private ServidorPushBean servidorPush;
    private PlanificacionBean planificacionServidor;
 
	private String planificacionId;
    public String getPlanificacionId() {
		return planificacionId;
	} 
	public void setPlanificacionId(String planificacionId) {
		this.planificacionId = planificacionId;
	}
	private String parametroServidorId;
    private List<ParametroServidorBean> listaParametrosServidor = null;
    private List<PlanificacionBean> listaPlanificacionesServidor = null;

	public List<PlanificacionBean> getListaPlanificacionesServidor() {
		return listaPlanificacionesServidor;
	}
	public void setListaPlanificacionesServidor(
			List<PlanificacionBean> listaPlanificacionesServidor) {
		this.listaPlanificacionesServidor = listaPlanificacionesServidor;
	}
	public String newSearch() throws BaseException {
    	return SUCCESS;
    }
	public String search() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
	   	int page = getPage("tableId"); //Pagina a mostrar
    	String order = getOrder("tableId"); //Ordenar de modo ascendente o descendente
    	String columnSort = getColumnSort("tableId"); //Columna usada para ordenar
    	
    	if(servidorPush != null)
    		if(servidorPush.getNombre() != null && servidorPush.getNombre().length()<=0)
    			servidorPush.setNombre(null);
    	    	
    	int inicio = (page-1)*PAGESIZE;
    	boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
    	PaginatedList<ServidorPushBean> result = servicioServidorPush.getServidoresPush(inicio,(export)?-1:PAGESIZE,order,columnSort,servidorPush); 
    	Integer totalSize = result.getTotalList();
    	
    	listaServidoresPush = result.getPageList();
    	
    	//Atributos de request
    	getRequest().setAttribute(REQUEST_ATTRIBUTE_TOTALSIZE, totalSize);
    	
    	if(!export){
    		getRequest().setAttribute(REQUEST_ATTRIBUTE_PAGESIZE, PAGESIZE);
    	}else{
    		getRequest().setAttribute(REQUEST_ATTRIBUTE_PAGESIZE, totalSize);
    	}
    	    	    	
    	if (listaServidoresPush!=null && !listaServidoresPush.isEmpty())
    	{    		
    		for (int indice=0;indice<listaServidoresPush.size();indice++) {
    			
    			ServidorPushBean servidorPush = listaServidoresPush.get(indice);
    			servidorPush.setNombre(StringEscapeUtils.escapeHtml(servidorPush.getNombre()));
    			servidorPush.setDescripcion(StringEscapeUtils.escapeHtml(servidorPush.getDescripcion()));
    		}
    	}
    	    	 	
        return SUCCESS;
	}
    public String execute() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
    	int page = getPage("tableId"); //Pagina a mostrar
    	String order = getOrder("tableId"); //Ordenar de modo ascendente o descendente
    	String columnSort = getColumnSort("tableId"); //Columna usada para ordenar
    	
    	if(servidorPush != null)
    		if(servidorPush.getNombre() != null && servidorPush.getNombre().length()<=0)
    			servidorPush.setNombre(null);
    	    	
    	int inicio = (page-1)*PAGESIZE;
    	boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
    	PaginatedList<ServidorPushBean> result = servicioServidorPush.getServidoresPush(inicio,(export)?-1:PAGESIZE,order,columnSort,servidorPush); 
    	Integer totalSize = result.getTotalList();
    	
    	listaServidoresPush =  result.getPageList();
    	
    	getRequest().setAttribute(REQUEST_ATTRIBUTE_TOTALSIZE, totalSize);
    	
    	if(!export){
    		getRequest().setAttribute(REQUEST_ATTRIBUTE_PAGESIZE, PAGESIZE);
    	}else{
    		getRequest().setAttribute(REQUEST_ATTRIBUTE_PAGESIZE, totalSize);
    	}
    	    	    	
    	if (listaServidoresPush!=null && !listaServidoresPush.isEmpty())
    	{    		
    		for (int indice=0;indice<listaServidoresPush.size();indice++) {
    			
    			ServidorPushBean receptor = listaServidoresPush.get(indice);
    			receptor.setNombre(StringEscapeUtils.escapeHtml(receptor.getNombre()));
    			receptor.setDescripcion(StringEscapeUtils.escapeHtml(receptor.getDescripcion()));
    		}
    	}
    	    	 	
        return SUCCESS;
    }
    
    public String create() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
    	if(servidorPush != null){
	    	if(servidorPush.getIsActivo()!=null&&servidorPush.getIsActivo().indexOf("'activo'")!=-1){
	    		servidorPush.setActivo(Integer.valueOf(1));
	    	}else{
	    		servidorPush.setActivo(Integer.valueOf(0));
	    	}
	    	if(validaServidor(servidorPush)){
		    	Long idServidorPush = servicioServidorPush.newServidorPush(servidorPush);
		    	this.idServidorPush = idServidorPush.toString();
			    addActionMessageSession(this.getText("plataforma.servidorpush.create.ok"));
	    	}else{
	    		return ERROR;
	    	}
    	}else{
    		addActionErrorSession(this.getText("plataforma.servidorpush.create.error"));
    	}
    	return SUCCESS;
    	
    }
    private boolean validaServidor(ServidorPushBean servidor) {
    	boolean sw=true;
		if(PlataformaMensajeriaUtil.isEmpty(servidor.getNombre())){
			addActionErrorSession(this.getText("plataforma.servidorpush.field.nombre.error"));
			sw=false;
		}
		if(PlataformaMensajeriaUtil.isEmpty(servidor.getDescripcion())){
			addActionErrorSession(this.getText("plataforma.servidorpush.field.descripcion.error"));
			sw=false;
		}
		if(PlataformaMensajeriaUtil.isEmpty(servidor.getUrlDestino())){
			addActionErrorSession(this.getText("plataforma.servidorpush.field.urldestino.error"));
			sw=false;
		}
		if(PlataformaMensajeriaUtil.isEmptyNumber(servidor.getPlataformaId())){
			addActionErrorSession(this.getText("plataforma.servidorpush.field.plataforma.error"));
			sw=false;
		}
		if(!PlataformaMensajeriaUtil.isEmptyNumber(servidor.getPlataformaId()) 
				&& servidor.getPlataformaId().equals(PARAMETRO_ID_PLATAFORMA_IOS)
				&& PlataformaMensajeriaUtil.isEmpty(servidor.getUrlFeedback())){
			addActionErrorSession(this.getText("plataforma.servidorpush.field.urlfeedback.error"));
			sw=false;
		}
		return sw;
	}    
    public String update() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
    	ServidorPushBean servidorPushBBDD = null;
    	if(servidorPush == null){
    		//throw new BusinessException("EL servidorPush recibido es nulo");
    		addActionErrorSession(this.getText("plataforma.servidorpush.update.error"));

    	}else{
	    	System.out.println("[ServidoresPushAction - IdServidorPush] valor == " + servidorPush.getServidorPushId() );
	    	if(servidorPush.getServidorPushId()==null){
	    		if(idServidorPush!=null){
	    			servidorPush.setServidorPushId(new Long(idServidorPush));
	    			servidorPushBBDD = servicioServidorPush.loadServidorPush(servidorPush);
	    		}else{
	    			String idProvedorSMS = (String)request.getAttribute("idServidorPush");
	    			System.out.println("[ServidoresAction - request.getAttribute('idServidor)' == " + idProvedorSMS);
	    			if(idProvedorSMS!=null){
	    				servidorPush.setId(new Long(idProvedorSMS));
	    				servidorPushBBDD = servicioServidorPush.loadServidorPush(servidorPush);
	    			}
	    		}
	    			
	    		System.out.println("[ServidoresPushAction - IdServidorPush despues de setear idServidorPush] valor == " + servidorPush.getServidorPushId() );
	    	}else{
	    		servidorPushBBDD = servicioServidorPush.loadServidorPush(servidorPush);
	    		
	    	}
	    	if(servidorPushBBDD!=null){
	    		servidorPushBBDD.setNombre(servidorPush.getNombre());
	    		servidorPushBBDD.setDescripcion(servidorPush.getDescripcion());
	    		servidorPushBBDD.setActivo(servidorPush.getActivo());
	    		servidorPushBBDD.setUrlDestino(servidorPush.getUrlDestino());
	    		servidorPushBBDD.setUrlFeedback(servidorPush.getUrlFeedback());
	    		servidorPushBBDD.setPlataformaId(servidorPush.getPlataformaId());
	    		servidorPushBBDD.setPorDefecto(servidorPush.getPorDefecto());
	    	}
	    	if(servidorPushBBDD!=null && validaServidor(servidorPushBBDD)){
		    	servicioServidorPush.updateServidorPush(servidorPushBBDD);
		    	addActionMessageSession(this.getText("plataforma.servidorpush.update.ok"));
	    	}
    	}
	    	
    	return SUCCESS;
    	
    }
    
    public String load() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
    	if(idServidorPush == null)
    		throw new BusinessException("EL idServidorPush recibido es nulo");
    	try {
			servidorPush = new ServidorPushBean();
			servidorPush.setServidorPushId(new Long(idServidorPush));
			servidorPush= servicioServidorPush.loadServidorPush(servidorPush);
			return SUCCESS;
    	} catch (NumberFormatException e) {
			String mensg = this.getText("errors.action.organismo.loadOrganismo",new String[]{servidorPush.getServidorPushId().toString()});
			throw new BusinessException(mensg);
		} catch (BusinessException e) {
			String mensg = this.getText("errors.action.organismo.loadOrganismo",new String[]{servidorPush.getServidorPushId().toString()});
			throw new BusinessException(mensg);
		}
    	
    }
    public String deleteParametroServidorPush() throws BaseException{
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
    		if(parametroServidorId == null){
    			addActionErrorSession(this.getText("plataforma.servidorpush.parametro.delete.error"));
    		}else{
	        	parametroServidor = new ParametroServidorBean();
	        	parametroServidor.setParametroServidorId(Integer.valueOf(parametroServidorId));
	        	servicioParametroServidor.deleteParametroServidor(parametroServidor);
	        	addActionMessageSession(this.getText("plataforma.servidorpush.parametro.delete.ok"));

    		}
    	return SUCCESS;
    }
    public String deletePlanificacionServidorPush() throws BaseException{
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
		if(planificacionId == null){
			addActionErrorSession(this.getText("plataforma.servidorpush.planificacion.delete.error"));

		}else{
	    	planificacionServidor = new PlanificacionBean();
	    	planificacionServidor.setPlanificacionId(Integer.valueOf(planificacionId));
	    	servicioPlanificacion.deletePlanificacion(planificacionServidor);
	    	addActionMessageSession(this.getText("plataforma.servidorpush.planificacion.delete.ok"));
		}
	return SUCCESS;
}    
    public String delete() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
    	if(idServidorPush == null){
    		addActionErrorSession(this.getText("plataforma.servidorpush.delete.error"));
    	}else{
	    	servidorPush = new ServidorPushBean();
	    	servidorPush.setServidorPushId(new Long(idServidorPush));
	    	servicioServidorPush.deleteServidorPush(servidorPush);
	    	addActionMessageSession(this.getText("plataforma.servidorpush.delete.ok"));

    	}
    	return SUCCESS;
    	
    }
    
    public String deleteSelected() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
    	if(checkDelList == null){
    		addActionErrorSession(this.getText("plataforma.servidorpush.deleteSelected.error"));
    	}else{
	    	for(String idServidorPush : checkDelList){
		    	servidorPush = new ServidorPushBean();
		    	servidorPush.setServidorPushId(new Long(idServidorPush));
		    	servicioServidorPush.deleteServidorPush(servidorPush);
	    	}
	    	addActionMessageSession(this.getText("plataforma.servidorpush.deleteSelected.ok"));

    	}
    	return SUCCESS;
    	
    }    
    private boolean validaParametro(ParametroServidorBean parametroServidor){
    	boolean sw=true;
    	if(PlataformaMensajeriaUtil.isEmptyNumber(parametroServidor.getTipoParametroId())){
    		addActionErrorSession(this.getText("plataforma.servidores.parametro.add.parametroid.error"));
    		sw=false;
    	}
    	if(PlataformaMensajeriaUtil.isEmpty(parametroServidor.getValor())){
    		addActionErrorSession(this.getText("plataforma.servidores.parametro.add.valor.error"));
    		sw=false;
    	}
    	if(!PlataformaMensajeriaUtil.isEmpty(parametroServidor.getValor())){
	    	try{
	    		Integer.parseInt(parametroServidor.getValor());
	    	} catch(NumberFormatException e){
	    		addActionErrorSession(this.getText("plataforma.servidores.parametro.add.valor.error.integer"));
				sw=false;
	    	}
    	}
    	if(parametroServidor.getTipoParametroId()==PARAMETRO_SERVIDOR_TIPO_TELEFONO&&!PlataformaMensajeriaUtil.isMobileNumber(parametroServidor.getValor())){
    		addActionErrorSession(this.getText("plataforma.servidorpush.parametro.telefonomovil.error"));
    		sw=false;
    	}
    	try {
			if(servicioParametroServidor.existeParametroServidor(parametroServidor)){
				addActionErrorSession(this.getText("plataforma.servidorpush.parametro.add.existe"));
				sw=false;
			}
			
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return sw;
    }    
    public String addParametroServidorPush() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
    	if(parametroServidor!=null){
    		if(!validaParametro(parametroServidor)){
    			return ERROR;
    		}else{
    			try{
					servicioParametroServidor.newParametroServidor(parametroServidor);
					addActionMessageSession(this.getText("plataforma.servidorpush.parametro.add.ok"));
    			}catch(ConstraintViolationException e){
    				addActionErrorSession(this.getText("plataforma.servidorpush.parametro.add.constraint.error"));
    			}
     		}
    	}else{
    		addActionErrorSession(this.getText("plataforma.servidorpush.parametro.add.error"));
    		return ERROR;
    	}
    	return SUCCESS;
    }
    public String addPlanificacionServidorPush() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
    	if(planificacionServidor!=null&&PlataformaMensajeriaUtil.isEmpty(idServidorPush)){
    		if(planificacionValida(planificacionServidor)){
	    		planificacionServidor.setActivo(Integer.valueOf(1));
	    		planificacionServidor.setTipoPlanificacionId(Integer.valueOf(PARAMETRO_ID_TIPO_PLANIFICACION));

				int valido = servicioPlanificacion.validaPlanificacionServidor(planificacionId, planificacionServidor.getServidorId(),
						planificacionServidor.getLunes(),planificacionServidor.getMartes(),planificacionServidor.getMiercoles(),
						planificacionServidor.getJueves(),planificacionServidor.getViernes(),planificacionServidor.getSabado(),
						planificacionServidor.getDomingo(),planificacionServidor.getHoraHasta(),planificacionServidor.getHoraDesde());
				
				if(valido == 1){
					servicioPlanificacion.newPlanificacion(planificacionServidor);
		    		addActionMessageSession(this.getText("plataforma.servidorpush.planificacion.add.ok"));
				}else if (valido == 0)
				{
			    	   addActionErrorSession("No se ha a&ntilde;adido la planificaci&oacute;n. La planificaci&oacute;n introducida se solapa con otras planificaciones");
			    	   return ERROR;
				}	    		
    		}else{
    			return ERROR;
    		}
    	}else{
    		addActionErrorSession(this.getText("plataforma.servidorpush.planificacion.add.error"));
    	}
    	return SUCCESS;
    }
	ArrayList<TipoParametroBean> tiposParametros = new ArrayList<TipoParametroBean>();

	@Override
	public void prepare() throws Exception {
		comboPlataformas = getComboPlataformaValues();
		
		if(idServidorPush!=null){
			tiposParametros = (ArrayList<TipoParametroBean>) servicioTipoParametro.getTipoParametrosServidorPush();
					
			comboTipoParametros = getComboValues();
			listaParametrosServidor = getParametrosServidorPush();
			listaPlanificacionesServidor = getLoadPlanificacionesServidorPush();
		}
	}
	public ArrayList<TipoParametroBean> getTiposParametros() {
		return tiposParametros;
	}

	public void setTiposParametros(ArrayList<TipoParametroBean> tiposParametros) {
		this.tiposParametros = tiposParametros;
	}
	   private List<PlanificacionBean> getLoadPlanificacionesServidorPush() {
   		List<PlanificacionBean> lista = null;
   		if(idServidorPush!=null&&idServidorPush.length()>0){
   			try {
   				lista = servicioPlanificacion.getPlanificacionesByServidorPushId(Integer.valueOf(idServidorPush));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (BusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
   		}else if(servidorPush!=null&&servidorPush.getServidorPushId()!=null){
   			try {
   				lista = servicioPlanificacion.getPlanificacionesByServidorPushId(Integer.valueOf(idServidorPush));
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
    private List<ParametroServidorBean> getParametrosServidorPush() {
    		List<ParametroServidorBean> lista = null;
    		if(idServidorPush!=null&&idServidorPush.length()>0){
    			try {
    				lista = servicioParametroServidor.getParametroServidorByServidorPushId(Integer.valueOf(idServidorPush));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (BusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}else if(servidorPush!=null&&servidorPush.getServidorPushId()!=null){
    			try {
    				lista = servicioParametroServidor.getParametroServidorByServidorPushId(servidorPush.getServidorPushId().intValue());
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<KeyValueObject> getComboValues() {
        List<KeyValueObject> result = new ArrayList<KeyValueObject>();
        
        KeyValueObject option = null;
        
        ArrayList<TipoParametroBean> keys = null;
		try {
			keys =(ArrayList<TipoParametroBean>) servicioTipoParametro.getTipoParametrosServidorPush();
			
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
		if(keys!=null&&keys.size()>0)
        for (TipoParametroBean key :keys) {
            
            option = new KeyValueObject();
            option.setCodigo(key.getTipoParametroId().toString());
            option.setDescripcion(StringUtil.capitalize(key.getNombre()));
            
            result.add(option);
        }
        return result;
    }
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<KeyValueObject> getComboPlataformaValues() {
		
        List<KeyValueObject> result = new ArrayList<KeyValueObject>();
        
        KeyValueObject option = null;
        
        ArrayList<PlataformaBean> keys = null;
		try {
			
			keys =(ArrayList<PlataformaBean>) servicioPlataforma.getPlataformas();
			
		} catch (BusinessException e) {
			e.printStackTrace();
		}
        
		if(keys!=null&&keys.size()>0) {
			for (PlataformaBean key :keys) {
	            
	            option = new KeyValueObject();
	            option.setCodigo(key.getPlataformaId().toString());
	            option.setDescripcion(StringUtil.capitalize(key.getNombre()));
	            
	            result.add(option);
	        }
		}
        
        return result;
    }
	public List<KeyValueObject> getComboTipoParametros() {
		return comboTipoParametros;
	}
	public void setComboTipoParametros(List<KeyValueObject> comboTipoParametros) {
		this.comboTipoParametros = comboTipoParametros;
	}
	public List<ServidorPushBean> getlistaServidoresPush() {
		return listaServidoresPush;
	}
	public void setlistaServidoresPush(List<ServidorPushBean> listaServidoresPush) {
		this.listaServidoresPush = listaServidoresPush;
	}
	public ServicioServidorPush getServicioServidorPush() {
		return servicioServidorPush;
	}
	public void setServicioServidorPush(ServicioServidorPush servicioServidorPush) {
		this.servicioServidorPush = servicioServidorPush;
	}
	public ServicioTipoParametro getServicioTipoParametro() {
		return servicioTipoParametro;
	}
	public void setServicioTipoParametro(ServicioTipoParametro servicioTipoParametro) {
		this.servicioTipoParametro = servicioTipoParametro;
	}
	public ServicioParametroServidor getServicioParametroServidor() {
		return servicioParametroServidor;
	}
	public void setServicioParametroServidor(
			ServicioParametroServidor servicioParametroServidor) {
		this.servicioParametroServidor = servicioParametroServidor;
	}
	public String getTipoParametroId() {
		return tipoParametroId;
	}
	public void setTipoParametroId(String tipoParametroId) {
		this.tipoParametroId = tipoParametroId;
	}
	public ServidorPushBean getServidorPush() {
		return servidorPush;
	}
	public void setServidorPush(ServidorPushBean servidorPush) {
		this.servidorPush = servidorPush;
	}
	public String getIdServidorPush() {
		return idServidorPush;
	}
	public void setIdServidorPush(String idServidorPush) {
		this.idServidorPush = idServidorPush;
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
	public ParametroServidorBean getParametroServidor() {
		return parametroServidor;
	}
	public void setParametroServidor(ParametroServidorBean parametroServidor) {
		this.parametroServidor = parametroServidor;
	}
	public List<ParametroServidorBean> getListaParametrosServidor() {
		return listaParametrosServidor;
	}
	public void setListaParametrosServidor(
			List<ParametroServidorBean> listaParametrosServidor) {
		this.listaParametrosServidor = listaParametrosServidor;
	}    
    
	public void setParametroServidorId(String parametroServidorId){
		this.parametroServidorId = parametroServidorId;
	}
	   public PlanificacionBean getPlanificacionServidor() {
			return planificacionServidor;
		}
		public void setPlanificacionServidor(PlanificacionBean planificacionServidor) {
			this.planificacionServidor = planificacionServidor;
		}	
	public String getParametroServidorId(){
			if(parametroServidor!=null&&parametroServidor.getParametroServidorId()!=null){
				return parametroServidor.getParametroServidorId().toString();
			}else{
				return parametroServidorId;
			}
				
		
	}
    
    public ServicioPlanificacion getServicioPlanificacion() {
		return servicioPlanificacion;
	}
	public void setServicioPlanificacion(ServicioPlanificacion servicioPlanificacion) {
		this.servicioPlanificacion = servicioPlanificacion;
	}
	public boolean isEmpty(String value){
		if(value==null||value.equals("")){
			return true;
		}else{
			return false;
		}
	}
	   /**
     * Verifica que se ha introducido por lo menos un día de la semana y las horas de inicio y fin
     * @param planificacionServidor
     */
    private boolean planificacionValida(PlanificacionBean planificacionServidor){
    	boolean sw=true;
    	if(planificacionServidor!=null){
    		if(PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getHoraDesde())&&PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getHoraHasta())){
    			addFieldErrorSession(this.getText("plataforma.servidorpush.planificacion.horas.error"));
    			sw=false;
    		
    		}
    		if(!PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getHoraDesde())&&PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getHoraHasta())){
    			addFieldErrorSession(this.getText("plataforma.servidorpush.planificacion.horaHasta.error"));
    			sw=false;
    		}
    		if(PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getHoraDesde())&&!PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getHoraHasta())){
    			addFieldErrorSession(this.getText("plataforma.servidorpush.planificacion.horaDesde.error"));
    			sw=false;
    		}
    		if(sw){
    			if(!validoFormatoHora(planificacionServidor.getHoraDesde())){
    				addFieldErrorSession(this.getText("plataforma.servidorpush.planificacion.horaDesde.formato.error"));
    				sw=false;
    			}    			
    			if(!validoFormatoHora(planificacionServidor.getHoraHasta())){
    				addFieldErrorSession(this.getText("plataforma.servidorpush.planificacion.horaHasta.formato.error"));
    				sw=false;
    			}
    			if(sw){
    				if(!validoHoras(planificacionServidor.getHoraDesde(),planificacionServidor.getHoraHasta())){
    					sw=false;
    				}
    			}
    		}
    		if(PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getLunes())&&PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getMartes())&&
    				PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getMiercoles())&&
    				PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getJueves())&&
    				PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getViernes())&&
    				PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getSabado())&&
    				PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getDomingo())){
    			addFieldErrorSession(this.getText("plataforma.servidorpush.planificacion.dias.error"));
    			sw=false;
    		}
    		
    	}
    	return sw;
    }
	private boolean validoHoras(String horaDesde, String horaHasta) {
		boolean sw=true;
		String[] horaDesdeArray = horaDesde.split(":");
		String[] horaHastaArray = horaHasta.split(":");
		int hDesde = Integer.valueOf(horaDesdeArray[0]);
		int mDesde = Integer.valueOf(horaDesdeArray[1]);
		int hHasta = Integer.valueOf(horaHastaArray[0]);
		int mHasta = Integer.valueOf(horaHastaArray[1]);
		if(hDesde>hHasta){
			addFieldErrorSession(this.getText("plataforma.servidores.planificacion.horaDesde.menor.error"));
			sw=false;
		}else if(hDesde==hHasta&&mDesde>mHasta){
			addFieldErrorSession(this.getText("plataforma.servidores.planificacion.horaDesde.menor.error"));
			sw=false;
		}else if(hDesde==hHasta&&mDesde==mHasta){
			addFieldErrorSession(this.getText("plataforma.servidores.planificacion.horas.iguales.error"));
			sw=false;
		}
		return sw;
	}
	private boolean validoFormatoHora(String hora) {
		boolean sw=true;
		if(!PlataformaMensajeriaUtil.isEmpty(hora)){
			if(!PlataformaMensajeriaUtil.validaFormatoHora(hora)){
				sw=false;
			}
		}
		return sw;
	}
	/**
	 * Método que resuelve el lugar donde tiene que volver
	 */
	public String getVolver() {
		String volver="buscarServidorPush.action";
		if(!PlataformaMensajeriaUtil.isEmpty(from)&&!PlataformaMensajeriaUtil.isEmpty(idFrom)){
			volver=from+"?"+var+"="+idFrom;
		}
		return volver;
	}
	public List<KeyValueObject> getComboPlataformas() {
		return comboPlataformas;
	}
	public void setComboPlataformas(List<KeyValueObject> comboPlataformas) {
		this.comboPlataformas = comboPlataformas;
	}
	public ServicioPlataforma getServicioPlataforma() {
		return servicioPlataforma;
	}
	public void setServicioPlataforma(ServicioPlataforma servicioPlataforma) {
		this.servicioPlataforma = servicioPlataforma;
	}
}
