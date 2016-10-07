package es.mpr.plataformamensajeria.web.action.planificaciones;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.security.context.SecurityContextHolder;

import com.map.j2ee.exceptions.BaseException;
import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;
import com.map.j2ee.util.KeyValueObject;
import com.opensymphony.xwork2.Preparable;

import es.mpr.plataformamensajeria.beans.AplicacionBean;
import es.mpr.plataformamensajeria.beans.CanalBean;
import es.mpr.plataformamensajeria.beans.PlanificacionBean;
import es.mpr.plataformamensajeria.beans.ProveedorSMSBean;
import es.mpr.plataformamensajeria.beans.ReceptorSMSBean;
import es.mpr.plataformamensajeria.beans.ServicioBean;
import es.mpr.plataformamensajeria.beans.ServidorBean;
import es.mpr.plataformamensajeria.beans.ServidorPushBean;
import es.mpr.plataformamensajeria.beans.ServidoresServiciosBean;
import es.mpr.plataformamensajeria.beans.TipoPlanificacionBean;
import es.mpr.plataformamensajeria.impl.PlataformaPaginationAction;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioAplicacion;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioCanal;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioPlanificacion;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioProveedorSMS;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioReceptorSMS;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServicio;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServidor;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServidorPush;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioTipoPlanificacion;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

/**
 * <p>Clase Action de Struts2 para la gesti&oacute;n de los planificaciones.
 * 
 * <p>
 * Proporciona m&eacute;todos para la creaci&oacute;n, modificaci&oacute;n, borrado y listado de los planificaciones
 * 
 * @author i-nercya
 *
 */
public class PlanificacionAction extends PlataformaPaginationAction implements ServletRequestAware, Preparable{
	
	private static final long serialVersionUID = 1L;
	
	private static final String REQUEST_ATTRIBUTE_TOTALSIZE = "totalSize";
	private static final String REQUEST_ATTRIBUTE_PAGESIZE = "pageSize";
	private static final Integer CANAL_SMS_ID = 2;
	private static final Integer CANAL_SMTP_ID = 1;
	private static final Integer CANAL_RECEPCION_SMS_ID = 3;
	private static final Integer CANAL_SERVIDOR_PUSH_ID = 4;


	private static final Integer PAGESIZE = Integer.valueOf(20); //Elementos por pagina
	/**
	 * Combos Formulario de Búsqueda.
	 */
	List<KeyValueObject> comboAplicaciones = new ArrayList<KeyValueObject>();
	List<KeyValueObject> comboCanales = new ArrayList<KeyValueObject>();
	List<KeyValueObject> comboServidores = new ArrayList<KeyValueObject>();
	List<KeyValueObject> comboBusquedaServidores = new ArrayList<KeyValueObject>();
	public List<KeyValueObject> getComboBusquedaServidores() {
		return comboBusquedaServidores;
	}
	public void setComboBusquedaServidores(
			List<KeyValueObject> comboBusquedaServidores) {
		this.comboBusquedaServidores = comboBusquedaServidores;
	}
	List<KeyValueObject> comboTipoPlanificaciones = new ArrayList<KeyValueObject>();
	List<KeyValueObject> comboConfiguraciones = new ArrayList<KeyValueObject>();
	private String servicioId;

	
	public String getServicioId() {
		return servicioId;
	}
	public void setServicioId(String servicioId) {
		this.servicioId = servicioId;
	}
	private String tipoPlanificacionId;
	public String getTipoPlanificacionId() {
		return tipoPlanificacionId;
	}
	public void setTipoPlanificacionId(String tipoPlanificacionId) {
		this.tipoPlanificacionId = tipoPlanificacionId;
	}
	public List<KeyValueObject> getComboTipoPlanificaciones() {
		return comboTipoPlanificaciones;
	}
	public void setComboTipoPlanificaciones(
			List<KeyValueObject> comboTipoPlanificaciones) {
		this.comboTipoPlanificaciones = comboTipoPlanificaciones;
	}
	public List<KeyValueObject> getComboServidores() {
		if(tipoPlanificacionId!=null){
			return loadComboServidores(tipoPlanificacionId);
		}else if(planificacion!=null&&planificacion.getTipoPlanificacionId()!=null){
			String tipoPlanificacionId=planificacion.getTipoPlanificacionId().toString();
			if(tipoPlanificacionId.equals("2")){ tipoPlanificacionId = "1"; }
			if(tipoPlanificacionId.equals("1")){ tipoPlanificacionId = "2"; }
			if(tipoPlanificacionId.equals("3")){ tipoPlanificacionId = "3"; }
			if(tipoPlanificacionId.equals("4")){ tipoPlanificacionId = "4"; }
			
			return loadComboServidores(tipoPlanificacionId);
		}
		return loadComboServidores();
	}
	public void setComboServidores(List<KeyValueObject> comboServidores) {
		this.comboServidores = comboServidores;
	}
	public String getIdPlanificacion() {
		return idPlanificacion;
	}
	public void setIdPlanificacion(String idPlanificacion) {
		this.idPlanificacion = idPlanificacion;
	}
	List<KeyValueObject> comboServicios = new ArrayList<KeyValueObject>();
	/**
	 * Criteria PlanificacionBean
	 */
	private PlanificacionBean planificacion;
	
	/**
	 * Lista Planificaciones formulario de Búsqueda
	 */
	public List<PlanificacionBean> listaPlanificaciones= null;

	/**
	 * Servicios
	 */
	private ServicioPlanificacion servicioPlanificacion;
    private ServicioServicio servicioServicio;
    private ServicioCanal servicioCanal;
	private ServicioServidor servicioServidor;
	private ServicioAplicacion servicioAplicacion;
    private ServicioTipoPlanificacion servicioTipoPlanificacion;
    private ServicioProveedorSMS servicioProveedorSMS;
    private ServicioReceptorSMS servicioReceptorSMS;
    private ServicioServidorPush servicioServidorPush;

	public ServicioProveedorSMS getServicioProveedorSMS() {
		return servicioProveedorSMS;
	}
	public void setServicioProveedorSMS(ServicioProveedorSMS servicioProveedorSMS) {
		this.servicioProveedorSMS = servicioProveedorSMS;
	}
	public ServicioReceptorSMS getServicioReceptorSMS() {
		return servicioReceptorSMS;
	}
	public void setServicioReceptorSMS(ServicioReceptorSMS servicioReceptorSMS) {
		this.servicioReceptorSMS = servicioReceptorSMS;
	}	
	public ServicioServidorPush getServicioServidorPush() {
		return servicioServidorPush;
	}
	public void setServicioServidorPush(ServicioServidorPush servicioServidorPush) {
		this.servicioServidorPush = servicioServidorPush;
	}
	public ServicioTipoPlanificacion getServicioTipoPlanificacion() {
		return servicioTipoPlanificacion;
	}
	public void setServicioTipoPlanificacion(
			ServicioTipoPlanificacion servicioTipoPlanificacion) {
		this.servicioTipoPlanificacion = servicioTipoPlanificacion;
	}
	public ServicioAplicacion getServicioAplicacion() {
		return servicioAplicacion;
	}
	public void setServicioAplicacion(ServicioAplicacion servicioAplicacion) {
		this.servicioAplicacion = servicioAplicacion;
	}
	public ServicioServidor getServicioServidor() {
		return servicioServidor;
	}
	public void setServicioServidor(ServicioServidor servicioServidor) {
		this.servicioServidor = servicioServidor;
	}
	private String tipoParametroId;

	private String idAplicacion;
	private String nAction;
    private String idPlanificacion;
    private String idServicio;
    private String idServidor;
    private String idProveedorSMS;
    private String idReceptorSMS;
    private String idServidorPush;
    private String idOrganismo;
    
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
	public String getIdServidor() {
		return idServidor;
	}
	public void setIdServidor(String idServidor) {
		this.idServidor = idServidor;
	}
	public String getnAction() {
		return nAction;
	}
	public void setnAction(String nAction) {
		this.nAction = nAction;
	}
	public String getIdServicio() {
		return idServicio;
	}
	public void setIdServicio(String idServicio) {
		this.idServicio = idServicio;
	}
	private String resultCount;
    private String[] checkDelList;
    private boolean modificaActivo;

	public boolean isModificaActivo() {
		return modificaActivo;
	}
	public void setModificaActivo(boolean modificaActivo) {
		this.modificaActivo = modificaActivo;
	}
	public String newSearch() throws BaseException {
    	return SUCCESS;
    }
	public String search() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
		
	   	int page = getPage("tableId"); //Pagina a mostrar
    	String order = getOrder("tableId"); //Ordenar de modo ascendente o descendente
    	String columnSort = getColumnSort("tableId"); //Columna usada para ordenar
    	boolean doSearch=true;

    	int inicio = (page-1)*PAGESIZE;
	    if(planificacion!=null&&!PlataformaMensajeriaUtil.isEmpty(planificacion.getHoraDesde())&&!validoFormatoHora(planificacion.getHoraDesde())){
	    	addActionErrorSession(this.getText("plataforma.planificacion.busquedas.formato.horadesde.error"));
	    	doSearch=false;
	    }
	    if(planificacion!=null&&!PlataformaMensajeriaUtil.isEmpty(planificacion.getHoraHasta())&&!validoFormatoHora(planificacion.getHoraHasta())){
	    	addActionErrorSession(this.getText("plataforma.planificacion.busquedas.formato.horahasta.error"));
	    	doSearch=false;
	    }
	    if(planificacion!=null&&!PlataformaMensajeriaUtil.isEmpty(planificacion.getHoraDesdeFin())&&!validoFormatoHora(planificacion.getHoraDesdeFin())){
	    	addActionErrorSession(this.getText("plataforma.planificacion.busquedas.formato.horadesdefin.error"));
	    	doSearch=false;
	    }
	    if(planificacion!=null&&!PlataformaMensajeriaUtil.isEmpty(planificacion.getHoraHastaFin())&&!validoFormatoHora(planificacion.getHoraHastaFin())){
	    	addActionErrorSession(this.getText("plataforma.planificacion.busquedas.formato.horadesdefin.error"));
	    	doSearch=false;
	    }
	    if(doSearch){
	    	boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
	    	PaginatedList<PlanificacionBean> result = servicioPlanificacion.getPlanificaciones(inicio,(export)?-1:PAGESIZE,order,columnSort,planificacion); 
	    	Integer totalSize = result.getTotalList();
	    	
	    	listaPlanificaciones =  result.getPageList();
	    	
	    	//Atributos de request
	    	getRequest().setAttribute(REQUEST_ATTRIBUTE_TOTALSIZE, totalSize);
	    	
	    	if(!export){
	    		getRequest().setAttribute(REQUEST_ATTRIBUTE_PAGESIZE, PAGESIZE);
	    	}else{
	    		getRequest().setAttribute(REQUEST_ATTRIBUTE_PAGESIZE, totalSize);
	    	}
	    	    	    	
	    	if (listaPlanificaciones!=null && !listaPlanificaciones.isEmpty())
	    	{    		
	    		for (int indice=0;indice<listaPlanificaciones.size();indice++) {
	    			
	    			PlanificacionBean planificacion = listaPlanificaciones.get(indice);
	    			planificacion.setNombreServicio(StringEscapeUtils.escapeHtml(planificacion.getNombreServicio()));
	    			planificacion.setNombreServidor(StringEscapeUtils.escapeHtml(planificacion.getNombreServidor()));
	    		}
	    	}
	    }	    	 	
        return SUCCESS;
	}
    public String execute() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
    	
    	int page = getPage("tableId"); //Pagina a mostrar
    	String order = getOrder("tableId"); //Ordenar de modo ascendente o descendente
    	String columnSort = getColumnSort("tableId"); //Columna usada para ordenar
    	    	
    	int inicio = (page-1)*PAGESIZE;
    	boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
    	PaginatedList<PlanificacionBean> result = servicioPlanificacion.getPlanificaciones(inicio,(export)?-1:PAGESIZE,order,columnSort,planificacion); 
    	Integer totalSize = result.getTotalList();
    	
    	listaPlanificaciones =  result.getPageList();
    	
    	//Atributos de request
    	getRequest().setAttribute(REQUEST_ATTRIBUTE_TOTALSIZE, totalSize);
    	
    	if(!export){
    		getRequest().setAttribute(REQUEST_ATTRIBUTE_PAGESIZE, PAGESIZE);
    	}else{
    		getRequest().setAttribute(REQUEST_ATTRIBUTE_PAGESIZE, totalSize);
    	}
    	    	    	
    	if (listaPlanificaciones!=null && !listaPlanificaciones.isEmpty())
    	{    		
    		for (int indice=0;indice<listaPlanificaciones.size();indice++) {
    			
    			PlanificacionBean planificacion = listaPlanificaciones.get(indice);
    			planificacion.setNombreServicio(StringEscapeUtils.escapeHtml(planificacion.getNombreServicio()));
    			planificacion.setNombreServidor(StringEscapeUtils.escapeHtml(planificacion.getNombreServidor()));
    		}
    	}
    	    	 	
        return SUCCESS;
    }
    public String createPlanificacionApp() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
    	if(planificacion != null){
	    		//throw new BusinessException("EL planificacion recibido es nulo");
	    	
	    	if(planificacion.getIsActivo()!=null&&planificacion.getIsActivo().indexOf("activo")!=-1){
	    		planificacion.setActivo(Integer.valueOf(1));
	    	}else{
	    		planificacion.setActivo(Integer.valueOf(0));
	    	}
	    	if(planificacionValida(planificacion)){
	    		Integer servidorId = Integer.valueOf(0);
	    		int valido = servicioPlanificacion.validaPlanificacionOptima(idPlanificacion, planificacion.getTipoPlanificacionId(),0,
	    				planificacion.getServicioId(),planificacion.getLunes(),planificacion.getMartes(),planificacion.getMiercoles(),
	    				planificacion.getJueves(),planificacion.getViernes(),planificacion.getSabado(),planificacion.getDomingo(),
	    				planificacion.getHoraHasta(),planificacion.getHoraDesde());
	    		// Si valido = 1 es correcto
	    		if(valido == 1){
			    	Integer idPlanificacion = servicioPlanificacion.newPlanificacion(planificacion);
			    	if(idPlanificacion>0){
			    		servicioPlanificacion.crearJobPorPlanificacion(idPlanificacion);
			    	}
			    	this.idPlanificacion = idPlanificacion.toString();
				    addActionMessageSession(this.getText("plataforma.planificacion.create.ok"));
	    		}
	    		else if (valido == 2)
	    		{
 			    	addActionErrorSession("No se ha guardado la planificaci&oacute;n. La planificaci&oacute;n introducida se solapa con otras planificaciones");
	    		}
	    		else
	    			addActionErrorSession("No se ha guardado la planificaci&oacute;n. La configuraci&oacute;n seleccionada no garantiza el env&iacute;o de los mensajes");
	    		
	    	}else{
	    		return ERROR;
	    	}

    	}else{
    		addActionErrorSession(this.getText("plataforma.planificacion.create.error"));
    		return ERROR;
    	}
    	return SUCCESS;
    	
    }   
    public String create() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
    	if(planificacion != null){
	    		//throw new BusinessException("EL planificacion recibido es nulo");
	    	
	    	if(planificacion.getIsActivo()!=null&&planificacion.getIsActivo().indexOf("activo")!=-1){
	    		planificacion.setActivo(Integer.valueOf(1));
	    	}else{
	    		planificacion.setActivo(Integer.valueOf(0));
	    	}
	    	if(planificacionValida(planificacion)){
		    	Integer idPlanificacion = servicioPlanificacion.newPlanificacion(planificacion);
		    	this.idPlanificacion = idPlanificacion.toString();
			    addActionMessageSession(this.getText("plataforma.planificacion.create.ok"));
//			    if(!servicioPlanificacion.validaPlanificacionOptima(idPlanificacion)){
//			    	addActionErrorSession("La configuración seleccionada para la planificación no garantiza el envío de los mensajes");
//			    }
	    	}else{
	    		return ERROR;
	    	}

    	}else{
    		addActionErrorSession(this.getText("plataforma.planificacion.create.error"));
    		return ERROR;
    	}
    	return SUCCESS;
    	
    }
    public String updatePlanificacionViewApp() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
       modificaActivo=false;
       int valido = servicioPlanificacion.validaPlanificacionOptima(idPlanificacion, planificacion.getTipoPlanificacionId(),0,
				planificacion.getServicioId(),planificacion.getLunes(),planificacion.getMartes(),planificacion.getMiercoles(),
				planificacion.getJueves(),planificacion.getViernes(),planificacion.getSabado(),planificacion.getDomingo(),
				planificacion.getHoraHasta(),planificacion.getHoraDesde());
		// Si valido = 1 es correcto
		
		if(valido == 1){
	  	   String retorno = update();
	  	   servicioPlanificacion.modificarJobPorPlanificacion(planificacion.getPlanificacionId());
	  	   return SUCCESS;

		}else if (valido == 2)
		{
    	   addActionErrorSession("No se ha actualizado la planificaci&oacute;n. La planificaci&oacute;n introducida se solapa con otras planificaciones");
    	   return ERROR;
		}
		else{
    	   addActionErrorSession("No se ha actualizado la planificaci&oacute;n. La configuraci&oacute;n seleccionada no garantiza el env&iacute;o de los mensajes");
  		   return ERROR;
       }
    }    

   public String updatePlanificacionServicio() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
   	   modificaActivo=false;
   	   int valido = servicioPlanificacion.validaPlanificacionOptima(idPlanificacion, planificacion.getTipoPlanificacionId(),0,
			planificacion.getServicioId(),planificacion.getLunes(),planificacion.getMartes(),planificacion.getMiercoles(),
			planificacion.getJueves(),planificacion.getViernes(),planificacion.getSabado(),planificacion.getDomingo(),
			planificacion.getHoraHasta(),planificacion.getHoraDesde());
   	   
   	   if(valido == 1){
	 	   String retorno = update();
	 	   servicioPlanificacion.modificarJobPorPlanificacion(planificacion.getPlanificacionId());
	 	   return SUCCESS;
   	   }
   	   else if (valido == 2)
   	   {
	 	   addActionErrorSession("No se ha actualizado la planificaci&oacute;n. La planificaci&oacute;n introducida se solapa con otras planificaciones");
	 	   return ERROR;
   	   }else{
   		   addActionErrorSession("No se ha actualizado la planificaci&oacute;n. La configuraci&oacute;n seleccionada no garantiza el env&iacute;o de los mensajes");
   		   return ERROR;
   	   }
   }    
   
   
   public String updatePlanificacionOrganismo() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
  	   modificaActivo=false;
  	   int valido = servicioPlanificacion.validaPlanificacionOptimaOrganismo(idPlanificacion, planificacion.getTipoPlanificacionId(),0,
			planificacion.getServicioId(),planificacion.getLunes(),planificacion.getMartes(),planificacion.getMiercoles(),
			planificacion.getJueves(),planificacion.getViernes(),planificacion.getSabado(),planificacion.getDomingo(),
			planificacion.getHoraHasta(),planificacion.getHoraDesde(),Integer.valueOf(idOrganismo));
  	   
  	   if(valido == 1){
	 	   String retorno = update();
	 	   servicioPlanificacion.modificarJobPorPlanificacion(planificacion.getPlanificacionId());
	 	   return SUCCESS;
  	   }
  	   else if (valido == 2)
  	   {
	 	   addActionErrorSession("No se ha actualizado la planificaci&oacute;n. La planificaci&oacute;n introducida se solapa con otras planificaciones");
	 	   return ERROR;
  	   }else{
  		   addActionErrorSession("No se ha actualizado la planificaci&oacute;n. La configuraci&oacute;n seleccionada no garantiza el env&iacute;o de los mensajes");
  		   return ERROR;
  	   }
  }    
   
    public String updatePlanificacionServer() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
    	modificaActivo=false;
    	int valido = servicioPlanificacion.validaPlanificacionServidor(idPlanificacion, planificacion.getServidorId(),
    			   planificacion.getLunes(),planificacion.getMartes(),planificacion.getMiercoles(),
    			   planificacion.getJueves(),planificacion.getViernes(),planificacion.getSabado(),
    			   planificacion.getDomingo(),planificacion.getHoraHasta(),planificacion.getHoraDesde());
		
    	if(valido == 1){
    		return update();
		}
    	else
		{
	    	   addActionErrorSession("No se ha actualizado la planificaci&oacute;n. La planificaci&oacute;n introducida se solapa con otras planificaciones");
	    	   return ERROR;
		}		
    }
    public String update() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
    	PlanificacionBean planificacionBBDD = null;
    	if(planificacion == null){
    		//throw new BusinessException("EL planificacion recibido es nulo");
    		addActionErrorSession(this.getText("plataforma.planificacion.update.error"));

    	}else{
	    	//System.out.println("[PlanificacionesAction - Idplanificacion] valor == " + planificacion.getPlanificacionId() );
	    	if(planificacion.getPlanificacionId()==null){
	    		if(idPlanificacion!=null){
	    			planificacion.setPlanificacionId(Integer.valueOf(idPlanificacion));
	    			planificacionBBDD = servicioPlanificacion.loadPlanificacion(planificacion);
	    		}else{
	    			String idPlanificacion = (String)request.getAttribute("idPlanificacion");
	    			System.out.println("[PlanificacionesAction - request.getAttribute('idPlanificacion)' == " + idPlanificacion);
	    			if(idPlanificacion!=null){
	    				planificacion.setPlanificacionId(Integer.valueOf(idPlanificacion));
	    				planificacionBBDD = servicioPlanificacion.loadPlanificacion(planificacion);
	    			}
	    		}
	    			
	    		System.out.println("[PlanificacionesAction - Idplanificacion despues de setear idPlanificacion] valor == " + planificacion.getPlanificacionId() );
	    	}else{
	    		planificacionBBDD = servicioPlanificacion.loadPlanificacion(planificacion);
	    		
	    	}
	    	if(planificacionBBDD!=null){
	    		planificacionBBDD.setServicioId(planificacion.getServicioId());
	    		planificacionBBDD.setLunes(planificacion.getLunes());
	    		planificacionBBDD.setMartes(planificacion.getMartes());
	    		planificacionBBDD.setMiercoles(planificacion.getMiercoles());
	    		planificacionBBDD.setJueves(planificacion.getJueves());
	    		planificacionBBDD.setViernes(planificacion.getViernes());
	    		planificacionBBDD.setSabado(planificacion.getSabado());
	    		planificacionBBDD.setDomingo(planificacion.getDomingo());
	    		planificacionBBDD.setHoraDesde(planificacion.getHoraDesde());
	    		planificacionBBDD.setHoraHasta(planificacion.getHoraHasta());
	    		planificacionBBDD.setServidorId(planificacion.getServidorId());
	    		if(modificaActivo){
	    			planificacionBBDD.setActivo(planificacion.getActivo());
	    		}
	    		planificacionBBDD.setTipoPlanificacionId(planificacion.getTipoPlanificacionId());
	    		planificacionBBDD.setServidorId(planificacion.getServidorId());
	    		planificacionBBDD.setAplicacionId(planificacion.getAplicacionId());
	    		if(planificacionBBDD.getServicioId()!=null&&planificacionBBDD.getServicioId()==0){
	    			planificacionBBDD.setServicioId(null);
	    		}
	    		//planificacionBBDD.setNombre(planificacion.getNombre());
	    		//planificacionBBDD.setDescripcion(planificacion.getDescripcion());
	    		//planificacionBBDD.setActivo(planificacion.getActivo());
	    	}
	    	if(planificacionValida(planificacion)){
		    	servicioPlanificacion.updatePlanificacion(planificacionBBDD);
			    servicioPlanificacion.modificarJobPorPlanificacion(planificacion.getPlanificacionId());
			 	addActionMessageSession(this.getText("plataforma.planificacion.update.ok"));
	    	}else{
	    		return ERROR;
	    	}
	    	
    	}
    	return SUCCESS;
    	
    }
    
    public String load() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
    	if(idPlanificacion == null)
    		throw new BusinessException("EL idPlanificacion recibido es nulo");
    	try {
    		modificaActivo=true;
			planificacion = new PlanificacionBean();
			planificacion.setPlanificacionId(Integer.valueOf(idPlanificacion));
			planificacion= servicioPlanificacion.loadPlanificacion(planificacion);
			if(servicioId==null&&planificacion.getServicioId()!=null){
				servicioId = planificacion.getServicioId().toString();
			}
//			comboTipoPlanificaciones = loadTipoPlanificaciones();
			
			if(planificacion.getTipoPlanificacionId()!=null&&planificacion.getTipoPlanificacionId()==1){
				tipoPlanificacionId = "2";
			}else if(planificacion.getTipoPlanificacionId()!=null&&planificacion.getTipoPlanificacionId()==2){
				tipoPlanificacionId = "1";
			}else if(planificacion.getTipoPlanificacionId()!=null&&planificacion.getTipoPlanificacionId()==3){
				tipoPlanificacionId = "3";
			}else if(planificacion.getTipoPlanificacionId()!=null&&planificacion.getTipoPlanificacionId()==4){
				tipoPlanificacionId = "4";
			}
			//comboServidores = loadComboServidores(tipoPlanificacionId);
			return SUCCESS;
    	} catch (NumberFormatException e) {
			String mensg = this.getText("errors.action.organismo.loadOrganismo",new String[]{planificacion.getPlanificacionId().toString()});
			throw new BusinessException(mensg);
		} catch (BusinessException e) {
			String mensg = this.getText("errors.action.organismo.loadOrganismo",new String[]{planificacion.getPlanificacionId().toString()});
			throw new BusinessException(mensg);
		}
    	
    }
  
    public String deletePlanificacionServicioViewApp() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
    	boolean sw=true;
    	if(idPlanificacion == null){
    		//throw new BusinessException("EL idPlanificacion recibido es nulo");
    		addActionErrorSession(this.getText("plataforma.planificacion.delete.error"));
    	}else{
	    	planificacion = new PlanificacionBean();
	    	planificacion.setPlanificacionId(Integer.valueOf(idPlanificacion));
	    	sw=servicioPlanificacion.deletePlanificacion(planificacion);
	   		servicioPlanificacion.eliminarJobPorPlanificacion(Integer.valueOf(idPlanificacion));
	    	if(!sw){
	    		addActionErrorSession(this.getText("plataforma.planificacion.delete.error"));
	    	}else{
	    		
	    		addActionMessageSession(this.getText("plataforma.planificacion.delete.ok"));
	    	}
    	}
    	return SUCCESS;
    	
    }

    public String delete() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
    	boolean sw=true;
    	if(idPlanificacion == null){
    		//throw new BusinessException("EL idPlanificacion recibido es nulo");
    		addActionErrorSession(this.getText("plataforma.planificacion.delete.error"));
    	}else{
	    	planificacion = new PlanificacionBean();
	    	planificacion.setPlanificacionId(Integer.valueOf(idPlanificacion));
	    	sw=servicioPlanificacion.deletePlanificacion(planificacion);
	    	if(!sw){
	    		addActionErrorSession(this.getText("plataforma.planificacion.delete.error"));
	    	}else{
	    		addActionMessageSession(this.getText("plataforma.planificacion.delete.ok"));
	    	}
    	}
    	return SUCCESS;
    	
    }
    
    public String deleteSelected() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
    	boolean sw=true;
    	if(checkDelList == null){
    		addActionErrorSession(this.getText("plataforma.planificacion.deleteSelected.error"));

    	}else{
    		for(String idPlanificacion : checkDelList){
		    	planificacion = new PlanificacionBean();
		    	planificacion.setPlanificacionId(Integer.valueOf(idPlanificacion));
		    	sw=servicioPlanificacion.deletePlanificacion(planificacion);
		    	if(!sw){
		    		addActionErrorSession(this.getText("plataforma.planificacion.delete.error") + " [Identificador: " + planificacion.getPlanificacionId()+"]");
		    	}
	    	}
    		if(sw)
	    	addActionMessageSession(this.getText("plataforma.planificacion.deleteSelected.ok"));

    	}
    	return SUCCESS;
    	
    }    

    public String loadServidoresByTipoPlan(){
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
    	if(tipoPlanificacionId==null||(tipoPlanificacionId!=null&&tipoPlanificacionId.equals(""))){
    	   if(planificacion!=null&&planificacion.getTipoPlanificacionId()!=null){
    		   tipoPlanificacionId=planificacion.getTipoPlanificacionId().toString();
    	   }
    	}
    	if(tipoPlanificacionId!=null&&tipoPlanificacionId.equals("1")){
    		tipoPlanificacionId="2";
    	}else if(tipoPlanificacionId!=null&&tipoPlanificacionId.equals("2")){
    		//comboServidores = loadComboServidores("1");
    		tipoPlanificacionId="1";
    	}
    	return SUCCESS;
    }
    public String loadTipoPlanificacionByServicioCanal(){
    	if(servicioId!=null){
    		try {
				comboTipoPlanificaciones=loadTipoPlanificaciones();
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	return SUCCESS;
    }    
	@Override
	public void prepare() throws Exception {
		//contextUsuarios = getComboValues();
		if(idServicio!=null){
			ServicioBean servicioBean = new ServicioBean();
			servicioBean.setId(Integer.valueOf(idServicio));
			
			ServicioBean servicio = servicioServicio.loadServicio(servicioBean);
			String rolUsuario = PlataformaMensajeriaUtil.getRolFromSession(request);
			if(rolUsuario.equals(PlataformaMensajeriaUtil.ROL_ADMINISTRADOR)){
				comboConfiguraciones = getComboConfiguracion(servicio.getCanalId());
			} else if(rolUsuario.equals(PlataformaMensajeriaUtil.ROL_PROPIETARIO)){
				comboConfiguraciones = getComboServidoresServicio(servicio.getServicioId());
			}
			if(planificacion==null){planificacion = new PlanificacionBean();}
			planificacion.setServicioId(Integer.valueOf(idServicio));
			if(servicio.getCanalId()==1){
				planificacion.setTipoPlanificacionId(Integer.valueOf(1));
			}else if(servicio.getCanalId()==2){
				planificacion.setTipoPlanificacionId(Integer.valueOf(2));
			} else if(servicio.getCanalId()==3){
				planificacion.setTipoPlanificacionId(Integer.valueOf(3));
			} else if(servicio.getCanalId()==4){
				planificacion.setTipoPlanificacionId(Integer.valueOf(4));
			}
		}else{
			if(tipoPlanificacionId==null&&servicioId==null){
				//EntityManager em = servicioPlanificacion.getEntityManager();
				comboAplicaciones = loadComboAplicaciones();
				comboCanales = loadComboCanales();
				//comboServidores = loadComboServidores();
				comboBusquedaServidores = loadComboBusquedaServidores();
				comboServicios = loadComboServicios();
				comboTipoPlanificaciones =loadTipoPlanificaciones();
			}
		}
	}
	public List<KeyValueObject> getComboConfiguraciones() {
		return comboConfiguraciones;
	}
	public void setComboConfiguraciones(List<KeyValueObject> comboConfiguraciones) {
		this.comboConfiguraciones = comboConfiguraciones;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<KeyValueObject> getComboConfiguracion(Integer idCanal) {
        List<KeyValueObject> result = new ArrayList<KeyValueObject>();
        
        

        KeyValueObject option = null;
        //TODO: DEVOLVER SOLO LOS PARAMETROS ACTIVOS!!!!
        ArrayList<ProveedorSMSBean> keysSMS = null;
        ArrayList<ServidorBean> keysSMTP = null;
        ArrayList<ReceptorSMSBean> keysReceptorSMS = null;
        ArrayList<ServidorPushBean> keysServidorPush = null;
        if(idCanal!=null&&idCanal.intValue()==CANAL_SMS_ID){
				try {
					keysSMS = (ArrayList<ProveedorSMSBean>)servicioProveedorSMS.getProveedoresSMS();
				} catch (BusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
		        
				if(keysSMS!=null&&keysSMS.size()>0)
		        for (ProveedorSMSBean key :keysSMS) {
		            
		            option = new KeyValueObject();
		            option.setCodigo(key.getProveedorSMSId().toString());
		            option.setDescripcion(key.getNombre());
		            result.add(option);
		        }
        }else if(idCanal!=null&&idCanal.intValue()==CANAL_SMTP_ID){
			try {
				keysSMTP = (ArrayList<ServidorBean>)servicioServidor.getServidores();
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        
			if(keysSMTP!=null&&keysSMTP.size()>0)
	        for (ServidorBean key :keysSMTP) {
	            
	            option = new KeyValueObject();
	            option.setCodigo(key.getServidorId().toString());
	            option.setDescripcion(key.getNombre());
	            result.add(option);
	        }        	
        } else if(idCanal!=null&&idCanal.intValue()==CANAL_RECEPCION_SMS_ID){
			try {
				keysReceptorSMS = (ArrayList<ReceptorSMSBean>)servicioReceptorSMS.getReceptoresSMS();
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        
			if(keysReceptorSMS!=null&&keysReceptorSMS.size()>0)
	        for (ReceptorSMSBean key :keysReceptorSMS) {
	            
	            option = new KeyValueObject();
	            option.setCodigo(key.getReceptorSMSId().toString());
	            option.setDescripcion(key.getNombre());
	            result.add(option);
	        } 
        } else if(idCanal!=null&&idCanal.intValue()==CANAL_SERVIDOR_PUSH_ID){
			try {
				keysServidorPush = (ArrayList<ServidorPushBean>)servicioServidorPush.getServidoresPush();
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        
			if(keysServidorPush!=null&&keysServidorPush.size()>0)
	        for (ServidorPushBean key :keysServidorPush) {
	            
	            option = new KeyValueObject();
	            option.setCodigo(key.getServidorPushId().toString());
	            option.setDescripcion(key.getNombre());
	            result.add(option);
	        } 
        }
        return result;
    }
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<KeyValueObject> getComboServidoresServicio(Integer servicioId) throws BusinessException {
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<KeyValueObject> loadComboServicios()throws BusinessException  {
        List<KeyValueObject> result = new ArrayList<KeyValueObject>();
        KeyValueObject option = null;
        //TODO: DEVOLVER SOLO LOS PARAMETROS ACTIVOS!!!!
        ArrayList<ServicioBean> keys = null;
        String rolUsuario = PlataformaMensajeriaUtil.getRolFromSession(request);
		Integer idUsuario = PlataformaMensajeriaUtil.getIdUsuarioFromSession(request);
		if(planificacion!=null&&planificacion.getAplicacionId()!=null){
			keys = (ArrayList<ServicioBean>)servicioServicio.getServiciosByAplicacionId(planificacion.getAplicacionId());
		}else{
			keys = (ArrayList<ServicioBean>)servicioServicio.getServicios(rolUsuario,idUsuario);
		}
        
        
		if(keys!=null&&keys.size()>0)
        for (ServicioBean key :keys) {
            
            option = new KeyValueObject();
            option.setCodigo(key.getServicioId().toString());
          option.setDescripcion(key.getNombre());
            result.add(option);
        }
        return result;
    }
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<KeyValueObject> loadComboServidores(String tipoPlanificacionId) {
        List<KeyValueObject> result = new ArrayList<KeyValueObject>();
        KeyValueObject option = null;
        //TODO: DEVOLVER SOLO LOS PARAMETROS ACTIVOS!!!!
        ArrayList<ServidorBean> keys = null;
		try {
			
	    		keys = (ArrayList<ServidorBean>)servicioServidor.getServidoresByTipoPlanificacion(tipoPlanificacionId);
	    	
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
		if(keys!=null&&keys.size()>0)
        for (ServidorBean key :keys) {
            
            option = new KeyValueObject();
            option.setCodigo(key.getServidorId().toString());
          option.setDescripcion(key.getNombre());
            result.add(option);
        }
        return result;
    }
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<KeyValueObject> loadComboServidores() {
        List<KeyValueObject> result = new ArrayList<KeyValueObject>();
        KeyValueObject option = null;
        //TODO: DEVOLVER SOLO LOS PARAMETROS ACTIVOS!!!!
        ArrayList<ServidorBean> keys = null;
		try {
	    	if(planificacion!=null&&planificacion.getTipoPlanificacionId()!=null&&planificacion.getTipoPlanificacionId().equals("1")){
	    		keys = (ArrayList<ServidorBean>)servicioServidor.getServidoresByTipoPlanificacion("2");
	    	}else if(planificacion!=null&&planificacion.getTipoPlanificacionId()!=null&&planificacion.getTipoPlanificacionId().equals("2")){
	    		keys = (ArrayList<ServidorBean>)servicioServidor.getServidoresByTipoPlanificacion("1");
	    	}else if(planificacion!=null&&planificacion.getTipoPlanificacionId()!=null&&planificacion.getTipoPlanificacionId().equals("3")){
	    		keys = (ArrayList<ServidorBean>)servicioServidor.getServidoresByTipoPlanificacion("3");
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
	private List<KeyValueObject> loadComboBusquedaServidores() {
        List<KeyValueObject> result = new ArrayList<KeyValueObject>();
        KeyValueObject option = null;
        //TODO: DEVOLVER SOLO LOS PARAMETROS ACTIVOS!!!!
        ArrayList<ServidorBean> keys = null;
		try {
    		keys = (ArrayList<ServidorBean>)servicioServidor.getAllServidores();
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
	private List<KeyValueObject> loadComboCanales() {
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
        
        
		if(keys!=null&&keys.size()>0)
        for (CanalBean key :keys) {
            
            option = new KeyValueObject();
            option.setCodigo(key.getCanalId().toString());
          option.setDescripcion(key.getNombre());
            result.add(option);
        }
        return result;
    }
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<KeyValueObject> loadTipoPlanificaciones() throws BusinessException {
        List<KeyValueObject> result = new ArrayList<KeyValueObject>();
        KeyValueObject option = null;
        //TODO: DEVOLVER SOLO LOS PARAMETROS ACTIVOS!!!!
        ArrayList<TipoPlanificacionBean> keys = null;
        ServicioBean servicioBean =null;
		if(servicioId!=null){
	        ServicioBean servicio = new ServicioBean();
	        servicio.setServicioId(Integer.valueOf(servicioId));
	        servicioBean = servicioServicio.loadServicio(servicio);
		}
        
        keys = new ArrayList<TipoPlanificacionBean>();
        TipoPlanificacionBean tp = new TipoPlanificacionBean();
        if(servicioBean!=null&&servicioBean.getCanalId()!=null&&servicioBean.getCanalId()==1){
        	tp.setNombre("Email");
        	tp.setTipoPlanificacionId(1);
        	keys.add(tp);
        }else if(servicioBean!=null&&servicioBean.getCanalId()!=null&&servicioBean.getCanalId()==2){
	        tp.setNombre("SMS");
	        tp.setTipoPlanificacionId(2);
	        keys.add(tp);
        }else if(servicioBean!=null&&servicioBean.getCanalId()!=null&&servicioBean.getCanalId()==3){
	        tp.setNombre("Receptor SMS");
	        tp.setTipoPlanificacionId(3);
	        keys.add(tp);
	    }else if(servicioBean!=null&&servicioBean.getCanalId()!=null&&servicioBean.getCanalId()==4){
	        tp.setNombre("Notificaciones Push");
	        tp.setTipoPlanificacionId(4);
	        keys.add(tp);
	    }
		if(keys.size()>0){
	        for (TipoPlanificacionBean key :keys) {
	            
	            option = new KeyValueObject();
	            option.setCodigo(key.getTipoPlanificacionId().toString());
	          option.setDescripcion(key.getNombre());
	            result.add(option);
	        }
		}
        return result;
    }
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<KeyValueObject> loadComboAplicaciones() {
        List<KeyValueObject> result = new ArrayList<KeyValueObject>();
        KeyValueObject option = null;
        //TODO: DEVOLVER SOLO LOS PARAMETROS ACTIVOS!!!!
        ArrayList<AplicacionBean> keys = null;
		try {
			keys = (ArrayList<AplicacionBean>)servicioAplicacion.getAplicaciones();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
		if(keys!=null&&keys.size()>0)
        for (AplicacionBean key :keys) {
            
            option = new KeyValueObject();
            option.setCodigo(key.getAplicacionId().toString());
          option.setDescripcion(key.getNombre());
            result.add(option);
        }
        return result;
    }

//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	private List<KeyValueObject> getComboValues() {
//        List<KeyValueObject> result = new ArrayList<KeyValueObject>();
//        
//        
//
//        KeyValueObject option = null;
//        //TODO: DEVOLVER SOLO LOS PARAMETROS ACTIVOS!!!!
//        ArrayList<TipoParametroBean> keys = null;
//		try {
//			keys = (ArrayList<TipoParametroBean>)servicioTipoParametro.getTipoParametrosPlanificacion();
//		} catch (BusinessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        
//        
//		if(keys!=null&&keys.size()>0)
//        for (TipoParametroBean key :keys) {
//            
//            option = new KeyValueObject();
//            option.setCodigo(key.getTipoParametroId().toString());
//            option.setDescripcion(key.getNombre());
//            result.add(option);
//        }
//        return result;
//    }

	public ServicioPlanificacion getServicioPlanificacion() {
		return servicioPlanificacion;
	}
	public void setServicioPlanificacion(ServicioPlanificacion servicioPlanificacion) {
		this.servicioPlanificacion = servicioPlanificacion;
	}

	public String getTipoParametroId() {
		return tipoParametroId;
	}
	public void setTipoParametroId(String tipoParametroId) {
		this.tipoParametroId = tipoParametroId;
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

    
    public ServicioServicio getServicioServicio() {
		return servicioServicio;
	}
	public void setServicioServicio(ServicioServicio servicioServicio) {
		this.servicioServicio = servicioServicio;
	}
	public List<KeyValueObject> getComboAplicaciones() {
		return comboAplicaciones;
	}
	public void setComboAplicaciones(List<KeyValueObject> comboAplicaciones) {
		this.comboAplicaciones = comboAplicaciones;
	}
	public List<KeyValueObject> getComboCanales() {
		return comboCanales;
	}
	public void setComboCanales(List<KeyValueObject> comboCanales) {
		this.comboCanales = comboCanales;
	}

	public List<KeyValueObject> getComboServicios() {
		return comboServicios;
	}
	public void setComboServicios(List<KeyValueObject> comboServicios) {
		this.comboServicios = comboServicios;
	}
	public PlanificacionBean getPlanificacion() {
		return planificacion;
	}
	public void setPlanificacion(PlanificacionBean planificacion) {
		this.planificacion = planificacion;
	}
	public List<PlanificacionBean> getListaPlanificaciones() {
		return listaPlanificaciones;
	}
	public void setListaPlanificaciones(List<PlanificacionBean> listaPlanificaciones) {
		this.listaPlanificaciones = listaPlanificaciones;
	}
	public ServicioCanal getServicioCanal() {
		return servicioCanal;
	}
	public void setServicioCanal(ServicioCanal servicioCanal) {
		this.servicioCanal = servicioCanal;
	}
    /**
     * Verifica que se ha introducido por lo menos un día de la semana y las horas de inicio y fin
     * @param planificacionServidor
     */
    private boolean planificacionValida(PlanificacionBean planificacionServidor){
    	boolean sw=true;
    	if(planificacionServidor!=null){
    		if(PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getHoraDesde())&&PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getHoraHasta())){
    			addFieldErrorSession(this.getText("plataforma.servidores.planificacion.horas.error"));
    			sw=false;
    		
    		}
    		if(!PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getHoraDesde())&&PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getHoraHasta())){
    			addFieldErrorSession(this.getText("plataforma.servidores.planificacion.horaHasta.error"));
    			sw=false;
    		}
    		if(PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getHoraDesde())&&!PlataformaMensajeriaUtil.isEmpty(planificacionServidor.getHoraHasta())){
    			addFieldErrorSession(this.getText("plataforma.servidores.planificacion.horaDesde.error"));
    			sw=false;
    		}
    		if(sw){
    			if(!validoFormatoHora(planificacionServidor.getHoraDesde())){
    				addFieldErrorSession(this.getText("plataforma.servidores.planificacion.horaDesde.formato.error"));
    				sw=false;
    			}    			
    			if(!validoFormatoHora(planificacionServidor.getHoraHasta())){
    				addFieldErrorSession(this.getText("plataforma.servidores.planificacion.horaHasta.formato.error"));
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
    			addFieldErrorSession(this.getText("plataforma.servidores.planificacion.dias.error"));
    			sw=false;
    		}
    	/*	if(PlataformaMensajeriaUtil.isEmptyNumber(planificacionServidor.getServicioId())){
    			addFieldErrorSession(this.getText("plataforma.servidores.planificacion.field.servicioid.error"));
    			sw=false;
    		}*/
    /*		if(PlataformaMensajeriaUtil.isEmptyNumber(planificacionServidor.getServidorId())){
    			addFieldErrorSession(this.getText("plataforma.servidores.planificacion.field.servidorid.error"));
    		}
    */
    		if(PlataformaMensajeriaUtil.isEmptyNumber(planificacionServidor.getTipoPlanificacionId())){
    			addFieldErrorSession(this.getText("plataforma.servidores.planificacion.field.tipoplanificacion.error"));
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
	
/*	@Override
	/*
	 * Método que resuelve el lugar donde tiene que volver
	*//*
	public String getVar() {
		String volver="buscarPlanificaciones.action";
		if(!PlataformaMensajeriaUtil.isEmpty(from)&&!PlataformaMensajeriaUtil.isEmpty(idFrom)){
			volver=from+"?"+var+"="+idFrom;
		}
		return volver;
	}
*/
	public String getVolver() {
		String volver="buscarPlanificaciones.action";
		if(!PlataformaMensajeriaUtil.isEmpty(from)&&!PlataformaMensajeriaUtil.isEmpty(idFrom)){
			volver=from+"?"+var+"="+idFrom;
		}
		return volver;
	}
	public String getIdAplicacion() {
		return idAplicacion;
	}
	public void setIdAplicacion(String idAplicacion) {
		this.idAplicacion = idAplicacion;
	}
	public String getNAction() {
		return nAction;
	}
	public void setNAction(String action) {
		this.nAction = action;
	}
	public String getIdServidorPush() {
		return idServidorPush;
	}
	public void setIdServidorPush(String idServidorPush) {
		this.idServidorPush = idServidorPush;
	}
	public String getIdOrganismo() {
		return idOrganismo;
	}
	public void setIdOrganismo(String idOrganismo) {
		this.idOrganismo = idOrganismo;
	}
	
}
