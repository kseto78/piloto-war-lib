package es.mpr.plataformamensajeria.web.action.receptores;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.xml.security.exceptions.Base64DecodingException;
import org.apache.xml.security.utils.Base64;
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
import es.mpr.plataformamensajeria.beans.ReceptorSMSBean;
import es.mpr.plataformamensajeria.beans.TipoParametroBean;
import es.mpr.plataformamensajeria.impl.PlataformaPaginationAction;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioParametroServidor;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioPlanificacion;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioReceptorSMS;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioTipoParametro;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

/**
 * <p>Clase Action de Struts2 para la gestion de los receptores sms.
 * 
 * <p>
 * Proporciona metodos para la creacion, modificacion, borrado y listado de los receptores sms.
 * 
 * @author jgonzvil
 *
 */
public class ReceptoresSMSAction extends PlataformaPaginationAction implements ServletRequestAware, Preparable{
	
	private static final long serialVersionUID = 1L;
	
	private static final String REQUEST_ATTRIBUTE_TOTALSIZE = "totalSize";
	private static final String REQUEST_ATTRIBUTE_PAGESIZE = "pageSize";
	
	private static final int PARAMETRO_SERVIDOR_TIPO_TELEFONO=7;

	private static final Integer PAGESIZE = Integer.valueOf(20); //Elementos por pagina
	List<KeyValueObject> comboTipoParametros = new ArrayList<KeyValueObject>();
	public List<ReceptorSMSBean> listaReceptoresSMS= null;
    private ServicioReceptorSMS servicioReceptorSMS;
    private ServicioTipoParametro servicioTipoParametro;
    private ServicioParametroServidor servicioParametroServidor;
    private ServicioPlanificacion servicioPlanificacion;
    

	private String tipoParametroId;

	
    private String idReceptorSMS;
    private String resultCount;
    private String[] checkDelList;
    private ParametroServidorBean parametroServidor;
    private ReceptorSMSBean receptorSMS;
    private PlanificacionBean planificacionServidor;
    private String checkPassword;
 
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
    	
    	if(receptorSMS != null)
    		if(receptorSMS.getNombre() != null && receptorSMS.getNombre().length()<=0)
    			receptorSMS.setNombre(null);
    	    	
    	int inicio = (page-1)*PAGESIZE;
    	boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
    	PaginatedList<ReceptorSMSBean> result = servicioReceptorSMS.getReceptoresSMS(inicio,(export)?-1:PAGESIZE,order,columnSort,receptorSMS); 
    	Integer totalSize = result.getTotalList();
    	
    	listaReceptoresSMS =  result.getPageList();
    	
    	//Atributos de request
    	getRequest().setAttribute(REQUEST_ATTRIBUTE_TOTALSIZE, totalSize);
    	
    	if(!export){
    		getRequest().setAttribute(REQUEST_ATTRIBUTE_PAGESIZE, PAGESIZE);
    	}else{
    		getRequest().setAttribute(REQUEST_ATTRIBUTE_PAGESIZE, totalSize);
    	}
    	    	    	
    	if (listaReceptoresSMS!=null && !listaReceptoresSMS.isEmpty())
    	{    		
    		for (int indice=0;indice<listaReceptoresSMS.size();indice++) {
    			
    			ReceptorSMSBean receptorSMS = listaReceptoresSMS.get(indice);
    			receptorSMS.setNombre(StringEscapeUtils.escapeHtml(receptorSMS.getNombre()));
    			receptorSMS.setDescripcion(StringEscapeUtils.escapeHtml(receptorSMS.getDescripcion()));
    		}
    	}
    	    	 	
        return SUCCESS;
	}
    public String execute() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
    	
    	int page = getPage("tableId"); //Pagina a mostrar
    	String order = getOrder("tableId"); //Ordenar de modo ascendente o descendente
    	String columnSort = getColumnSort("tableId"); //Columna usada para ordenar
    	
    	if(receptorSMS != null)
    		if(receptorSMS.getNombre() != null && receptorSMS.getNombre().length()<=0)
    			receptorSMS.setNombre(null);
    	    	
    	int inicio = (page-1)*PAGESIZE;
    	boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
    	PaginatedList<ReceptorSMSBean> result = servicioReceptorSMS.getReceptoresSMS(inicio,(export)?-1:PAGESIZE,order,columnSort,receptorSMS); 
    	Integer totalSize = result.getTotalList();
    	
    	listaReceptoresSMS =  result.getPageList();
    	
    	getRequest().setAttribute(REQUEST_ATTRIBUTE_TOTALSIZE, totalSize);
    	
    	if(!export){
    		getRequest().setAttribute(REQUEST_ATTRIBUTE_PAGESIZE, PAGESIZE);
    	}else{
    		getRequest().setAttribute(REQUEST_ATTRIBUTE_PAGESIZE, totalSize);
    	}
    	    	    	
    	if (listaReceptoresSMS!=null && !listaReceptoresSMS.isEmpty())
    	{    		
    		for (int indice=0;indice<listaReceptoresSMS.size();indice++) {
    			
    			ReceptorSMSBean receptor = listaReceptoresSMS.get(indice);
    			receptor.setNombre(StringEscapeUtils.escapeHtml(receptor.getNombre()));
    			receptor.setDescripcion(StringEscapeUtils.escapeHtml(receptor.getDescripcion()));
    		}
    	}
    	    	 	
        return SUCCESS;
    }
    
    public String create() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
    	if(receptorSMS != null){
	    	if(receptorSMS.getIsActivo()!=null&&receptorSMS.getIsActivo().indexOf("'activo'")!=-1){
	    		receptorSMS.setActivo(Integer.valueOf(1));
	    	}else{
	    		receptorSMS.setActivo(Integer.valueOf(0));
	    	}
	    	if(validaServidor(receptorSMS) && validPassword(receptorSMS)){
	    		if(null != receptorSMS.getPassword()){
	    			receptorSMS.setPassword(Base64.encode(receptorSMS.getPassword().trim().getBytes())); //Eliminamos los espacios
	    		}
		    	Long idReceptorSMS = servicioReceptorSMS.newReceptorSMS(receptorSMS);
		    	this.idReceptorSMS = idReceptorSMS.toString();
			    addActionMessageSession(this.getText("plataforma.receptorsms.create.ok"));
	    	}else{
	    		return ERROR;
	    	}
    	}else{
    		addActionErrorSession(this.getText("plataforma.receptorsms.create.error"));
    	}
    	return SUCCESS;
    	
    }
    private boolean validaServidor(ReceptorSMSBean servidor) {
    	boolean sw=true;
		if(PlataformaMensajeriaUtil.isEmpty(servidor.getNombre())){
			addActionErrorSession(this.getText("plataforma.receptorsms.field.nombre.error"));
			sw=false;
		}
		if(PlataformaMensajeriaUtil.isEmpty(servidor.getDescripcion())){
			addActionErrorSession(this.getText("plataforma.receptorsms.field.descripcion.error"));
			sw=false;
		}
		return sw;
	} 
    
    private boolean validPassword(ReceptorSMSBean servidor) {
    	boolean sw=true;
    	
		if(PlataformaMensajeriaUtil.isEmpty(servidor.getUsuario())){
			addFieldErrorSession(this.getText("plataforma.receptorsms.field.usuario.error"));
			sw=false;
		}
		if(PlataformaMensajeriaUtil.isEmpty(servidor.getPassword())){
			addFieldErrorSession(this.getText("plataforma.receptorsms.field.password.error"));
			sw=false;
		}
		if(PlataformaMensajeriaUtil.isEmpty(servidor.getRePassword()) && !PlataformaMensajeriaUtil.isEmpty(checkPassword) && checkPassword.equals("true")){
			addFieldErrorSession(this.getText("plataforma.receptorsms.field.rePassword.error"));
			sw=false;
		}
		//Eliminamos los espacios
		if(!PlataformaMensajeriaUtil.isEmpty(servidor.getPassword())&&!PlataformaMensajeriaUtil.isEmpty(servidor.getRePassword())
				&&!(servidor.getPassword().trim().equals(servidor.getRePassword().trim()))
				&& !PlataformaMensajeriaUtil.isEmpty(checkPassword) && checkPassword.equals("true")){
			addFieldErrorSession(this.getText("plataforma.receptorsms.field.passwords.error"));
			sw=false;
		}
    	
    	return sw;
    }
    
    public String update() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
    	ReceptorSMSBean receptorSMSBBDD = null;
    	if(receptorSMS == null){
    		//throw new BusinessException("EL receptorSMS recibido es nulo");
    		addActionErrorSession(this.getText("plataforma.receptorsms.update.error"));

    	}else{
	    	System.out.println("[ReceptoresSMSAction - Idreceptor] valor == " + receptorSMS.getReceptorSMSId() );
	    	if(receptorSMS.getReceptorSMSId()==null){
	    		if(idReceptorSMS!=null){
	    			receptorSMS.setReceptorSMSId(new Long(idReceptorSMS));
	    			receptorSMSBBDD = servicioReceptorSMS.loadReceptorSMS(receptorSMS);
	    		}else{
	    			String idProvedorSMS = (String)request.getAttribute("idReceptorSMS");
	    			System.out.println("[ServidoresAction - request.getAttribute('idServidor)' == " + idProvedorSMS);
	    			if(idProvedorSMS!=null){
	    				receptorSMS.setId(new Long(idProvedorSMS));
	    				receptorSMSBBDD = servicioReceptorSMS.loadReceptorSMS(receptorSMS);
	    			}
	    		}
	    			
	    		System.out.println("[ReceptoresSMSAction - Idreceptor despues de setear idReceptorSMS] valor == " + receptorSMS.getReceptorSMSId() );
	    	}else{
	    		receptorSMSBBDD = servicioReceptorSMS.loadReceptorSMS(receptorSMS);
	    		
	    	}
	    	if(receptorSMSBBDD!=null){
	    		receptorSMSBBDD.setNombre(receptorSMS.getNombre());
	    		receptorSMSBBDD.setDescripcion(receptorSMS.getDescripcion());
	    		receptorSMSBBDD.setActivo(receptorSMS.getActivo());
	    		receptorSMSBBDD.setUsuario(receptorSMS.getUsuario());
	    		receptorSMSBBDD.setPassword(receptorSMS.getPassword());
	    		receptorSMSBBDD.setRePassword(receptorSMS.getRePassword());
	    		receptorSMSBBDD.setPorDefecto(receptorSMS.getPorDefecto());
	    	}
	    	if(receptorSMSBBDD!=null && validaServidor(receptorSMSBBDD) && validPassword(receptorSMSBBDD)){
	    		if(null != receptorSMS.getPassword() && !receptorSMS.getPassword().isEmpty()){
	    			receptorSMSBBDD.setPassword(Base64.encode(receptorSMS.getPassword().trim().getBytes())); //Eliminamos los espacios
		    	}
		    	servicioReceptorSMS.updateReceptorSMS(receptorSMSBBDD);
		    	addActionMessageSession(this.getText("plataforma.receptorsms.update.ok"));
	    	}
    	}
	    	
    	return SUCCESS;
    	
    }
    
    public String load() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
    	if(idReceptorSMS == null)
    		throw new BusinessException("EL idReceptorSMS recibido es nulo");
    	try {
			receptorSMS = new ReceptorSMSBean();
			receptorSMS.setReceptorSMSId(new Long(idReceptorSMS));
			receptorSMS= servicioReceptorSMS.loadReceptorSMS(receptorSMS);
			if(null != receptorSMS.getPassword()){
				try {
					receptorSMS.setPassword(new String(Base64.decode(receptorSMS.getPassword())));
				} catch (Base64DecodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return SUCCESS;
    	} catch (NumberFormatException e) {
			String mensg = this.getText("errors.action.organismo.loadOrganismo",new String[]{receptorSMS.getReceptorSMSId().toString()});
			throw new BusinessException(mensg);
		} catch (BusinessException e) {
			String mensg = this.getText("errors.action.organismo.loadOrganismo",new String[]{receptorSMS.getReceptorSMSId().toString()});
			throw new BusinessException(mensg);
		}
    	
    }
    public String deleteParametroReceptorSMS() throws BaseException{
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
    		if(parametroServidorId == null){
    			addActionErrorSession(this.getText("plataforma.receptorsms.parametro.delete.error"));
    		}else{
	        	parametroServidor = new ParametroServidorBean();
	        	parametroServidor.setParametroServidorId(Integer.valueOf(parametroServidorId));
	        	servicioParametroServidor.deleteParametroServidor(parametroServidor);
	        	addActionMessageSession(this.getText("plataforma.receptorsms.parametro.delete.ok"));

    		}
    	return SUCCESS;
    }
    public String deletePlanificacionReceptorSMS() throws BaseException{
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
		if(planificacionId == null){
			addActionErrorSession(this.getText("plataforma.receptorsms.planificacion.delete.error"));

		}else{
	    	planificacionServidor = new PlanificacionBean();
	    	planificacionServidor.setPlanificacionId(Integer.valueOf(planificacionId));
	    	servicioPlanificacion.deletePlanificacion(planificacionServidor);
	    	addActionMessageSession(this.getText("plataforma.receptorsms.planificacion.delete.ok"));
		}
	return SUCCESS;
}    
    public String delete() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
    	if(idReceptorSMS == null){
    		addActionErrorSession(this.getText("plataforma.receptorsms.delete.error"));
    	}else{
	    	receptorSMS = new ReceptorSMSBean();
	    	receptorSMS.setReceptorSMSId(new Long(idReceptorSMS));
	    	servicioReceptorSMS.deleteReceptorSMS(receptorSMS);
	    	addActionMessageSession(this.getText("plataforma.receptorsms.delete.ok"));

    	}
    	return SUCCESS;
    	
    }
    
    public String deleteSelected() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
    	if(checkDelList == null){
    		addActionErrorSession(this.getText("plataforma.receptorsms.deleteSelected.error"));
    	}else{
	    	for(String idReceptorSMS : checkDelList){
		    	receptorSMS = new ReceptorSMSBean();
		    	receptorSMS.setReceptorSMSId(new Long(idReceptorSMS));
		    	servicioReceptorSMS.deleteReceptorSMS(receptorSMS);
	    	}
	    	addActionMessageSession(this.getText("plataforma.receptorsms.deleteSelected.ok"));

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
    		addActionErrorSession(this.getText("plataforma.receptorsms.parametro.telefonomovil.error"));
    		sw=false;
    	}
    	try {
			if(servicioParametroServidor.existeParametroServidor(parametroServidor)){
				addActionErrorSession(this.getText("plataforma.receptorsms.parametro.add.existe"));
				sw=false;
			}
			
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return sw;
    }    
    public String addParametroReceptorSMS() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
    	if(parametroServidor!=null){
    		if(!validaParametro(parametroServidor)){
    			return ERROR;
    		}else{
    			try{
					servicioParametroServidor.newParametroServidor(parametroServidor);
					//addActionMessage(this.getText("plataforma.mensaje.paramtetroservidorok"));
					addActionMessageSession(this.getText("plataforma.receptorsms.parametro.add.ok"));
    			}catch(ConstraintViolationException e){
    				addActionErrorSession(this.getText("plataforma.receptorsms.parametro.add.constraint.error"));
    			}
     		}
    	}else{
    		addActionErrorSession(this.getText("plataforma.receptorsms.parametro.add.error"));
    		return ERROR;
    	}
    	return SUCCESS;
    }
    public String addPlanificacionReceptorSMS() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
    	if(planificacionServidor!=null&&PlataformaMensajeriaUtil.isEmpty(idReceptorSMS)){
    		if(planificacionValida(planificacionServidor)){
	    		planificacionServidor.setActivo(Integer.valueOf(1));
	    		planificacionServidor.setTipoPlanificacionId(Integer.valueOf(3));

				int valido = servicioPlanificacion.validaPlanificacionServidor(planificacionId, planificacionServidor.getServidorId(),
						planificacionServidor.getLunes(),planificacionServidor.getMartes(),planificacionServidor.getMiercoles(),
						planificacionServidor.getJueves(),planificacionServidor.getViernes(),planificacionServidor.getSabado(),
						planificacionServidor.getDomingo(),planificacionServidor.getHoraHasta(),planificacionServidor.getHoraDesde());
				
				if(valido == 1){
					servicioPlanificacion.newPlanificacion(planificacionServidor);
		    		addActionMessageSession(this.getText("plataforma.receptorsms.planificacion.add.ok"));
				}else if (valido == 0)
				{
			    	   addActionErrorSession("No se ha a&ntilde;adido la planificaci&oacute;n. La planificaci&oacute;n introducida se solapa con otras planificaciones");
			    	   return ERROR;
				}	    		
    		}else{
    			return ERROR;
    		}
    	}else{
    		addActionErrorSession(this.getText("plataforma.receptorsms.planificacion.add.error"));
    	}
    	return SUCCESS;
    }
	ArrayList<TipoParametroBean> tiposParametros = new ArrayList<TipoParametroBean>();

	@Override
	public void prepare() throws Exception {
		//contextUsuarios = getComboValues();
		if(idReceptorSMS!=null){
			tiposParametros = (ArrayList<TipoParametroBean>) servicioTipoParametro.getTipoParametrosReceptorSMS();
					
			comboTipoParametros = getComboValues();
			listaParametrosServidor = getParametrosReceptorSMS();
			listaPlanificacionesServidor = getLoadPlanificacionesReceptorSMS();
		}
	}
	public ArrayList<TipoParametroBean> getTiposParametros() {
		return tiposParametros;
	}

	public void setTiposParametros(ArrayList<TipoParametroBean> tiposParametros) {
		this.tiposParametros = tiposParametros;
	}
	   private List<PlanificacionBean> getLoadPlanificacionesReceptorSMS() {
   		List<PlanificacionBean> lista = null;
   		if(idReceptorSMS!=null&&idReceptorSMS.length()>0){
   			try {
   				lista = servicioPlanificacion.getPlanificacionesByReceptorSMSId(Integer.valueOf(idReceptorSMS));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (BusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
   		}else if(receptorSMS!=null&&receptorSMS.getReceptorSMSId()!=null){
   			try {
   				lista = servicioPlanificacion.getPlanificacionesByReceptorSMSId(Integer.valueOf(idReceptorSMS));
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
    private List<ParametroServidorBean> getParametrosReceptorSMS() {
    		List<ParametroServidorBean> lista = null;
    		if(idReceptorSMS!=null&&idReceptorSMS.length()>0){
    			try {
    				lista = servicioParametroServidor.getParametroServidorByReceptorSMSId(Integer.valueOf(idReceptorSMS));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (BusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}else if(receptorSMS!=null&&receptorSMS.getReceptorSMSId()!=null){
    			try {
    				lista = servicioParametroServidor.getParametroServidorByReceptorSMSId(receptorSMS.getReceptorSMSId().intValue());
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
			keys =(ArrayList<TipoParametroBean>) servicioTipoParametro.getTipoParametrosReceptorSMS();
			
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
	public List<ReceptorSMSBean> getlistaReceptoresSMS() {
		return listaReceptoresSMS;
	}
	public void setlistaReceptoresSMS(List<ReceptorSMSBean> listaReceptoresSMS) {
		this.listaReceptoresSMS = listaReceptoresSMS;
	}
	public ServicioReceptorSMS getServicioReceptorSMS() {
		return servicioReceptorSMS;
	}
	public void setServicioReceptorSMS(ServicioReceptorSMS servicioReceptorSMS) {
		this.servicioReceptorSMS = servicioReceptorSMS;
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
	public ReceptorSMSBean getReceptorSMS() {
		return receptorSMS;
	}
	public void setReceptorSMS(ReceptorSMSBean receptorSMS) {
		this.receptorSMS = receptorSMS;
	}
	public String getIdReceptorSMS() {
		return idReceptorSMS;
	}
	public void setIdReceptorSMS(String idReceptorSMS) {
		this.idReceptorSMS = idReceptorSMS;
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
    			addFieldErrorSession(this.getText("plataforma.receptorsms.planificacion.horas.error"));
    			sw=false;
    		
    		}
    		if(!PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getHoraDesde())&&PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getHoraHasta())){
    			addFieldErrorSession(this.getText("plataforma.receptorsms.planificacion.horaHasta.error"));
    			sw=false;
    		}
    		if(PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getHoraDesde())&&!PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getHoraHasta())){
    			addFieldErrorSession(this.getText("plataforma.receptorsms.planificacion.horaDesde.error"));
    			sw=false;
    		}
    		if(sw){
    			if(!validoFormatoHora(planificacionServidor.getHoraDesde())){
    				addFieldErrorSession(this.getText("plataforma.receptorsms.planificacion.horaDesde.formato.error"));
    				sw=false;
    			}    			
    			if(!validoFormatoHora(planificacionServidor.getHoraHasta())){
    				addFieldErrorSession(this.getText("plataforma.receptorsms.planificacion.horaHasta.formato.error"));
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
    			addFieldErrorSession(this.getText("plataforma.receptorsms.planificacion.dias.error"));
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
		String volver="buscarReceptorSMS.action";
		if(!PlataformaMensajeriaUtil.isEmpty(from)&&!PlataformaMensajeriaUtil.isEmpty(idFrom)){
			volver=from+"?"+var+"="+idFrom;
		}
		return volver;
	}
	public String getCheckPassword() {
		return checkPassword;
	}
	public void setCheckPassword(String checkPassword) {
		this.checkPassword = checkPassword;
	}
}
