package es.mpr.plataformamensajeria.web.action.proveedores;

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
import es.mpr.plataformamensajeria.beans.ProveedorSMSBean;
import es.mpr.plataformamensajeria.beans.ServidorBean;
import es.mpr.plataformamensajeria.beans.TipoParametroBean;
import es.mpr.plataformamensajeria.impl.PlataformaPaginationAction;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioParametroServidor;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioPlanificacion;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioProveedorSMS;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioTipoParametro;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

/**
 * <p>Clase Action de Struts2 para la gesti&oacute;n de los proveedores sms.
 * 
 * <p>
 * Proporciona m&eacute;todos para la creaci&oacute;n, modificaci&oacute;n, borrado y listado de los proveedores sms.
 * 
 * @author Altran
 *
 */
public class ProveedoresSMSAction extends PlataformaPaginationAction implements ServletRequestAware, Preparable{
	
	private static final long serialVersionUID = 1L;
	
	private static final String REQUEST_ATTRIBUTE_TOTALSIZE = "totalSize";
	private static final String REQUEST_ATTRIBUTE_PAGESIZE = "pageSize";
	
	private static final int PARAMETRO_SERVIDOR_TIPO_TELEFONO=7;

	private static final Integer PAGESIZE = new Integer(20); //Elementos por pagina
	List<KeyValueObject> comboTipoParametros = new ArrayList<KeyValueObject>();
	public List<ProveedorSMSBean> listaProveedoresSMS= null;
    private ServicioProveedorSMS servicioProveedorSMS;
    private ServicioTipoParametro servicioTipoParametro;
    private ServicioParametroServidor servicioParametroServidor;
    private ServicioPlanificacion servicioPlanificacion;
    

	private String tipoParametroId;

	
    private String idProveedorSMS;
    private String resultCount;
    private String[] checkDelList;
    private ParametroServidorBean parametroServidor;
    private ProveedorSMSBean proveedorSMS;
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
    	
    	if(proveedorSMS != null)
    		if(proveedorSMS.getNombre() != null && proveedorSMS.getNombre().length()<=0)
    			proveedorSMS.setNombre(null);
    	    	
    	int inicio = (page-1)*PAGESIZE;
    	boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
    	PaginatedList<ProveedorSMSBean> result = servicioProveedorSMS.getProveedoresSMS(inicio,(export)?-1:PAGESIZE,order,columnSort,proveedorSMS); 
    	Integer totalSize = result.getTotalList();
    	
    	listaProveedoresSMS =  result.getPageList();
    	
    	//Atributos de request
    	getRequest().setAttribute(REQUEST_ATTRIBUTE_TOTALSIZE, totalSize);
    	
    	if(!export){
    		getRequest().setAttribute(REQUEST_ATTRIBUTE_PAGESIZE, PAGESIZE);
    	}else{
    		getRequest().setAttribute(REQUEST_ATTRIBUTE_PAGESIZE, totalSize);
    	}
    	    	    	
    	if (listaProveedoresSMS!=null && !listaProveedoresSMS.isEmpty())
    	{    		
    		for (int indice=0;indice<listaProveedoresSMS.size();indice++) {
    			
    			ProveedorSMSBean proveedorSMS = listaProveedoresSMS.get(indice);
    			proveedorSMS.setNombre(StringEscapeUtils.escapeHtml(proveedorSMS.getNombre()));
    			proveedorSMS.setDescripcion(StringEscapeUtils.escapeHtml(proveedorSMS.getDescripcion()));
    		}
    	}
    	    	 	
        return SUCCESS;
	}
    public String execute() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
    	
    	int page = getPage("tableId"); //Pagina a mostrar
    	String order = getOrder("tableId"); //Ordenar de modo ascendente o descendente
    	String columnSort = getColumnSort("tableId"); //Columna usada para ordenar
    	
    	if(proveedorSMS != null)
    		if(proveedorSMS.getNombre() != null && proveedorSMS.getNombre().length()<=0)
    			proveedorSMS.setNombre(null);
    	    	
    	int inicio = (page-1)*PAGESIZE;
    	boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
    	PaginatedList<ProveedorSMSBean> result = servicioProveedorSMS.getProveedoresSMS(inicio,(export)?-1:PAGESIZE,order,columnSort,proveedorSMS); 
    	Integer totalSize = result.getTotalList();
    	
    	listaProveedoresSMS =  result.getPageList();
    	
    	getRequest().setAttribute(REQUEST_ATTRIBUTE_TOTALSIZE, totalSize);
    	
    	if(!export){
    		getRequest().setAttribute(REQUEST_ATTRIBUTE_PAGESIZE, PAGESIZE);
    	}else{
    		getRequest().setAttribute(REQUEST_ATTRIBUTE_PAGESIZE, totalSize);
    	}
    	    	    	
    	if (listaProveedoresSMS!=null && !listaProveedoresSMS.isEmpty())
    	{    		
    		for (int indice=0;indice<listaProveedoresSMS.size();indice++) {
    			
    			ProveedorSMSBean proveedor = listaProveedoresSMS.get(indice);
    			proveedor.setNombre(StringEscapeUtils.escapeHtml(proveedor.getNombre()));
    			proveedor.setDescripcion(StringEscapeUtils.escapeHtml(proveedor.getDescripcion()));
    		}
    	}
    	    	 	
        return SUCCESS;
    }
    
    public String create() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
    	if(proveedorSMS != null){
	    	if(proveedorSMS.getIsActivo()!=null&&proveedorSMS.getIsActivo().indexOf("'activo'")!=-1){
	    		proveedorSMS.setActivo(new Integer(1));
	    	}else{
	    		proveedorSMS.setActivo(new Integer(0));
	    	}
	    	if(validaServidor(proveedorSMS)){
		    	Long idProveedorSMS = servicioProveedorSMS.newProveedorSMS(proveedorSMS);
		    	this.idProveedorSMS = idProveedorSMS.toString();
			    addActionMessageSession(this.getText("plataforma.proveedorsms.create.ok"));
	    	}else{
	    		return ERROR;
	    	}
    	}else{
    		addActionErrorSession(this.getText("plataforma.proveedorsms.create.error"));
    	}
    	return SUCCESS;
    	
    }
    private boolean validaServidor(ProveedorSMSBean servidor) {
    	boolean sw=true;
		if(PlataformaMensajeriaUtil.isEmpty(servidor.getNombre())){
			addActionErrorSession(this.getText("plataforma.proveedorsms.field.nombre.error"));
			sw=false;
		}
		if(PlataformaMensajeriaUtil.isEmpty(servidor.getDescripcion())){
			addActionErrorSession(this.getText("plataforma.proveedorsms.field.descripcion.error"));
			sw=false;
		}
		if(PlataformaMensajeriaUtil.isEmpty(servidor.getUrlDestino())){
			addActionErrorSession(this.getText("plataforma.proveedorsms.field.urldestino.error"));
			sw=false;
		}
		if(PlataformaMensajeriaUtil.isEmpty(servidor.getMetodoConsulta())){
			addActionErrorSession(this.getText("plataforma.proveedorsms.field.metodoConsulta.error"));
			sw=false;
		}
		return sw;
	}    
    public String update() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
    	ProveedorSMSBean proveedorSMSBBDD = null;
    	if(proveedorSMS == null){
    		//throw new BusinessException("EL proveedorSMS recibido es nulo");
    		addActionErrorSession(this.getText("plataforma.proveedorsms.update.error"));

    	}else{
	    	System.out.println("[ProveedoresSMSAction - Idproveedor] valor == " + proveedorSMS.getProveedorSMSId() );
	    	if(proveedorSMS.getProveedorSMSId()==null){
	    		if(idProveedorSMS!=null){
	    			proveedorSMS.setProveedorSMSId(new Long(idProveedorSMS));
	    			proveedorSMSBBDD = servicioProveedorSMS.loadProveedorSMS(proveedorSMS);
	    		}else{
	    			String idProvedorSMS = (String)request.getAttribute("idProveedorSMS");
	    			System.out.println("[ServidoresAction - request.getAttribute('idServidor)' == " + idProvedorSMS);
	    			if(idProvedorSMS!=null){
	    				proveedorSMS.setId(new Long(idProvedorSMS));
	    				proveedorSMSBBDD = servicioProveedorSMS.loadProveedorSMS(proveedorSMS);
	    			}
	    		}
	    			
	    		System.out.println("[ProveedoresSMSAction - Idproveedor despues de setear idProveedorSMS] valor == " + proveedorSMS.getProveedorSMSId() );
	    	}else{
	    		proveedorSMSBBDD = servicioProveedorSMS.loadProveedorSMS(proveedorSMS);
	    		
	    	}
	    	if(proveedorSMSBBDD!=null){
	    		proveedorSMSBBDD.setNombre(proveedorSMS.getNombre());
	    		proveedorSMSBBDD.setDescripcion(proveedorSMS.getDescripcion());
	    		proveedorSMSBBDD.setActivo(proveedorSMS.getActivo());
	    		proveedorSMSBBDD.setUrlDestino(proveedorSMS.getUrlDestino());
	    		proveedorSMSBBDD.setPorDefecto(proveedorSMS.getPorDefecto());
	    		//0 = Recepción de Estado / 1 = Consulta de Estado
	    		proveedorSMSBBDD.setMetodoConsulta(proveedorSMS.getMetodoConsulta());
	    	}
	    	if(validaServidor(proveedorSMSBBDD)){
		    	servicioProveedorSMS.updateProveedorSMS(proveedorSMSBBDD);
		    	addActionMessageSession(this.getText("plataforma.proveedorsms.update.ok"));
	    	}
    	}
	    	
    	return SUCCESS;
    	
    }
    
    public String load() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
    	if(idProveedorSMS == null)
    		throw new BusinessException("EL idProveedorSMS recibido es nulo");
    	try {
			proveedorSMS = new ProveedorSMSBean();
			proveedorSMS.setProveedorSMSId(new Long(idProveedorSMS));
			proveedorSMS= servicioProveedorSMS.loadProveedorSMS(proveedorSMS);
			return SUCCESS;
    	} catch (NumberFormatException e) {
			String mensg = this.getText("errors.action.organismo.loadOrganismo",new String[]{proveedorSMS.getProveedorSMSId().toString()});
			throw new BusinessException(mensg);
		} catch (BusinessException e) {
			String mensg = this.getText("errors.action.organismo.loadOrganismo",new String[]{proveedorSMS.getProveedorSMSId().toString()});
			throw new BusinessException(mensg);
		}
    	
    }
    public String deleteParametroProveedorSMS() throws BaseException{
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
    		if(parametroServidorId == null){
    			addActionErrorSession(this.getText("plataforma.proveedorsms.parametro.delete.error"));
    		}else{
	        	parametroServidor = new ParametroServidorBean();
	        	parametroServidor.setParametroServidorId(new Integer(parametroServidorId));
	        	servicioParametroServidor.deleteParametroServidor(parametroServidor);
	        	addActionMessageSession(this.getText("plataforma.proveedorsms.parametro.delete.ok"));

    		}
    	return SUCCESS;
    }
    public String deletePlanificacionProveedorSMS() throws BaseException{
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
		if(planificacionId == null){
			addActionErrorSession(this.getText("plataforma.proveedorsms.planificacion.delete.error"));

		}else{
	    	planificacionServidor = new PlanificacionBean();
	    	planificacionServidor.setPlanificacionId(new Integer(planificacionId));
	    	servicioPlanificacion.deletePlanificacion(planificacionServidor);
	    	addActionMessageSession(this.getText("plataforma.proveedorsms.planificacion.delete.ok"));
		}
	return SUCCESS;
}    
    public String delete() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
    	if(idProveedorSMS == null){
    		addActionErrorSession(this.getText("plataforma.proveedorsms.delete.error"));
    	}else{
	    	proveedorSMS = new ProveedorSMSBean();
	    	proveedorSMS.setProveedorSMSId(new Long(idProveedorSMS));
	    	servicioProveedorSMS.deleteProveedorSMS(proveedorSMS);
	    	addActionMessageSession(this.getText("plataforma.proveedorsms.delete.ok"));

    	}
    	return SUCCESS;
    	
    }
    
    public String deleteSelected() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
    	if(checkDelList == null){
    		addActionErrorSession(this.getText("plataforma.proveedorsms.deleteSelected.error"));
    	}else{
	    	for(String idProveedorSMS : checkDelList){
		    	proveedorSMS = new ProveedorSMSBean();
		    	proveedorSMS.setProveedorSMSId(new Long(idProveedorSMS));
		    	servicioProveedorSMS.deleteProveedorSMS(proveedorSMS);
	    	}
	    	addActionMessageSession(this.getText("plataforma.proveedorsms.deleteSelected.ok"));

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
    	if(parametroServidor.getTipoParametroId()==PARAMETRO_SERVIDOR_TIPO_TELEFONO&&!PlataformaMensajeriaUtil.isMobileNumber(parametroServidor.getValor())){
    		addActionErrorSession(this.getText("plataforma.proveedorsms.parametro.telefonomovil.error"));
    		sw=false;
    	}
    	try {
			if(servicioParametroServidor.existeParametroServidor(parametroServidor)){
				addActionErrorSession(this.getText("plataforma.proveedorsms.parametro.add.existe"));
				sw=false;
			}
			
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return sw;
    }    
    public String addParametroProveedorSMS() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
    	if(parametroServidor!=null){
    		if(!validaParametro(parametroServidor)){
    			return ERROR;
    		}else{
    			try{
					servicioParametroServidor.newParametroServidor(parametroServidor);
					//addActionMessage(this.getText("plataforma.mensaje.paramtetroservidorok"));
					addActionMessageSession(this.getText("plataforma.proveedorsms.parametro.add.ok"));
    			}catch(ConstraintViolationException e){
    				addActionErrorSession(this.getText("plataforma.proveedorsms.parametro.add.constraint.error"));
    			}
     		}
    	}else{
    		addActionErrorSession(this.getText("plataforma.proveedorsms.parametro.add.error"));
    		return ERROR;
    	}
    	return SUCCESS;
    }
    public String addPlanificacionProveedorSMS() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
    	if(planificacionServidor!=null&&PlataformaMensajeriaUtil.isEmpty(idProveedorSMS)){
    		if(planificacionValida(planificacionServidor)){
	    		planificacionServidor.setActivo(new Integer(1));
	    		planificacionServidor.setTipoPlanificacionId(new Integer(2));

				int valido = servicioPlanificacion.validaPlanificacionServidor(planificacionId, planificacionServidor.getServidorId(),
						planificacionServidor.getLunes(),planificacionServidor.getMartes(),planificacionServidor.getMiercoles(),
						planificacionServidor.getJueves(),planificacionServidor.getViernes(),planificacionServidor.getSabado(),
						planificacionServidor.getDomingo(),planificacionServidor.getHoraHasta(),planificacionServidor.getHoraDesde());
				
				if(valido == 1){
					servicioPlanificacion.newPlanificacion(planificacionServidor);
		    		addActionMessageSession(this.getText("plataforma.proveedorsms.planificacion.add.ok"));
				}else if (valido == 0)
				{
			    	   addActionErrorSession("No se ha a&ntilde;adido la planificaci&oacute;n. La planificaci&oacute;n introducida se solapa con otras planificaciones");
			    	   return ERROR;
				}	    		
    		}else{
    			return ERROR;
    		}
    	}else{
    		addActionErrorSession(this.getText("plataforma.proveedorsms.planificacion.add.error"));
    	}
    	return SUCCESS;
    }
	ArrayList<TipoParametroBean> tiposParametros = new ArrayList<TipoParametroBean>();

	@Override
	public void prepare() throws Exception {
		//contextUsuarios = getComboValues();
		if(idProveedorSMS!=null){
			tiposParametros = (ArrayList<TipoParametroBean>) servicioTipoParametro.getTipoParametrosProveedorSMS();
					
			comboTipoParametros = getComboValues();
			listaParametrosServidor = getParametrosProveedorSMS();
			listaPlanificacionesServidor = getLoadPlanificacionesProveedorSMS();
		}
	}
	public ArrayList<TipoParametroBean> getTiposParametros() {
		return tiposParametros;
	}

	public void setTiposParametros(ArrayList<TipoParametroBean> tiposParametros) {
		this.tiposParametros = tiposParametros;
	}
	   private List<PlanificacionBean> getLoadPlanificacionesProveedorSMS() {
   		List<PlanificacionBean> lista = null;
   		if(idProveedorSMS!=null&&idProveedorSMS.length()>0){
   			try {
   				lista = servicioPlanificacion.getPlanificacionesByProveedorSMSId(new Integer(idProveedorSMS));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (BusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
   		}else if(proveedorSMS!=null&&proveedorSMS.getProveedorSMSId()!=null){
   			try {
   				lista = servicioPlanificacion.getPlanificacionesByProveedorSMSId(new Integer(idProveedorSMS));
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
    private List<ParametroServidorBean> getParametrosProveedorSMS() {
    		List<ParametroServidorBean> lista = null;
    		if(idProveedorSMS!=null&&idProveedorSMS.length()>0){
    			try {
    				lista = servicioParametroServidor.getParametroServidorByProveedorSMSId(new Integer(idProveedorSMS));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (BusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}else if(proveedorSMS!=null&&proveedorSMS.getProveedorSMSId()!=null){
    			try {
    				lista = servicioParametroServidor.getParametroServidorByProveedorSMSId(proveedorSMS.getProveedorSMSId().intValue());
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
        //TODO: DEVOLVER SOLO LOS PARAMETROS ACTIVOS!!!!
        ArrayList<TipoParametroBean> keys = null;
		try {
			keys =(ArrayList<TipoParametroBean>) servicioTipoParametro.getTipoParametrosProveedorSMS();
			
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
	public List<KeyValueObject> getComboTipoParametros() {
		return comboTipoParametros;
	}
	public void setComboTipoParametros(List<KeyValueObject> comboTipoParametros) {
		this.comboTipoParametros = comboTipoParametros;
	}
	public List<ProveedorSMSBean> getListaProveedoresSMS() {
		return listaProveedoresSMS;
	}
	public void setListaProveedoresSMS(List<ProveedorSMSBean> listaProveedoresSMS) {
		this.listaProveedoresSMS = listaProveedoresSMS;
	}
	public ServicioProveedorSMS getServicioProveedorSMS() {
		return servicioProveedorSMS;
	}
	public void setServicioProveedorSMS(ServicioProveedorSMS servicioProveedorSMS) {
		this.servicioProveedorSMS = servicioProveedorSMS;
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
	public ProveedorSMSBean getProveedorSMS() {
		return proveedorSMS;
	}
	public void setProveedorSMS(ProveedorSMSBean proveedorSMS) {
		this.proveedorSMS = proveedorSMS;
	}
	public String getIdProveedorSMS() {
		return idProveedorSMS;
	}
	public void setIdProveedorSMS(String idProveedorSMS) {
		this.idProveedorSMS = idProveedorSMS;
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
		if(value==null||(value!=null&&value.equals(""))){
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
    			addFieldErrorSession(this.getText("plataforma.proveedorsms.planificacion.horas.error"));
    			sw=false;
    		
    		}
    		if(!PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getHoraDesde())&&PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getHoraHasta())){
    			addFieldErrorSession(this.getText("plataforma.proveedorsms.planificacion.horaHasta.error"));
    			sw=false;
    		}
    		if(PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getHoraDesde())&&!PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getHoraHasta())){
    			addFieldErrorSession(this.getText("plataforma.proveedorsms.planificacion.horaDesde.error"));
    			sw=false;
    		}
    		if(sw){
    			if(!validoFormatoHora(planificacionServidor.getHoraDesde())){
    				addFieldErrorSession(this.getText("plataforma.proveedorsms.planificacion.horaDesde.formato.error"));
    				sw=false;
    			}    			
    			if(!validoFormatoHora(planificacionServidor.getHoraHasta())){
    				addFieldErrorSession(this.getText("plataforma.proveedorsms.planificacion.horaHasta.formato.error"));
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
    			addFieldErrorSession(this.getText("plataforma.proveedorsms.planificacion.dias.error"));
    			sw=false;
    		}
    		
    	}
    	return sw;
    }
	private boolean validoHoras(String horaDesde, String horaHasta) {
		boolean sw=true;
		String[] horaDesdeArray = horaDesde.split(":");
		String[] horaHastaArray = horaHasta.split(":");
		int hDesde = new Integer(horaDesdeArray[0]);
		int mDesde = new Integer(horaDesdeArray[1]);
		int hHasta = new Integer(horaHastaArray[0]);
		int mHasta = new Integer(horaHastaArray[1]);
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
		String volver="buscarProveedorSMS.action";
		if(!PlataformaMensajeriaUtil.isEmpty(from)&&!PlataformaMensajeriaUtil.isEmpty(idFrom)){
			volver=from+"?"+var+"="+idFrom;
		}
		return volver;
	}
}
