package es.mpr.plataformamensajeria.web.action.servidores;

import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.security.context.SecurityContextHolder;

import com.map.j2ee.exceptions.BaseException;
import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;
import com.map.j2ee.pagination.PaginationAction;

import es.mpr.plataformamensajeria.beans.ProveedorSMSBean;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioProveedorSMS;
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
public class ProveedoresSMSAction extends PaginationAction{
	
	private static final long serialVersionUID = 1L;
	
	private static final String REQUEST_ATTRIBUTE_TOTALSIZE = "totalSize";
	private static final String REQUEST_ATTRIBUTE_PAGESIZE = "pageSize";
	
	private static final Integer PAGESIZE = new Integer(20); //Elementos por pagina
	
	public List<ProveedorSMSBean> listaProveedoresSMS= null;
    private ServicioProveedorSMS servicioProveedorSMS;
    private ProveedorSMSBean proveedorSMS;
    private String idProveedorSMS;
    private String resultCount;
    private String[] checkDelList;
   
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
    
    public String create() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
    	if(proveedorSMS == null)
    		throw new BusinessException("EL proveedorSMS recibido es nulo");
    	String activo = request.getParameter("proveedorSMS.activo");
    	if(activo!=null && activo.equals("true")){
    		proveedorSMS.setActivo(new Integer(1));
    	}else{
    		proveedorSMS.setActivo(new Integer(0));
    	}
    	servicioProveedorSMS.newProveedorSMS(proveedorSMS);
    	
    	return SUCCESS;
    	
    }
    
    public String update() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
    	ProveedorSMSBean proveedorSMSBBDD = null;
    	if(proveedorSMS == null)
    		throw new BusinessException("EL proveedorSMS recibido es nulo");
    	System.out.println("[ProveedoresSMSAction - IdproveedorSMS] valor == " + proveedorSMS.getProveedorSMSId() );
    	if(proveedorSMS.getProveedorSMSId()==null){
    		if(idProveedorSMS!=null){
    			proveedorSMS.setProveedorSMSId(new Long(idProveedorSMS));
    			proveedorSMSBBDD = servicioProveedorSMS.loadProveedorSMS(proveedorSMS);
    		}else{
    			String idProveedorSMS = (String)request.getAttribute("idProveedorSMS");
    			System.out.println("[ProveedoresSMSAction - request.getAttribute('idProveedorSMS)' == " + idProveedorSMS);
    			if(idProveedorSMS!=null){
    				proveedorSMS.setId(new Long(idProveedorSMS));
    				proveedorSMSBBDD = servicioProveedorSMS.loadProveedorSMS(proveedorSMS);
    			}
    		}
    			
    		System.out.println("[ProveedoresSMSAction - IdproveedorSMS despues de setear idProveedorSMS] valor == " + proveedorSMS.getProveedorSMSId() );
    	}else{
    		proveedorSMSBBDD = servicioProveedorSMS.loadProveedorSMS(proveedorSMS);
    		
    	}
    	if(proveedorSMSBBDD!=null){
    		proveedorSMSBBDD.setNombre(proveedorSMS.getNombre());
    		proveedorSMSBBDD.setDescripcion(proveedorSMS.getDescripcion());
    		proveedorSMSBBDD.setActivo(proveedorSMS.getActivo());
    	}
    	servicioProveedorSMS.updateProveedorSMS(proveedorSMSBBDD);
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
    
    public String delete() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
    	if(idProveedorSMS == null)
    		throw new BusinessException("EL idProveedorSMS recibido es nulo");
    	
    	proveedorSMS = new ProveedorSMSBean();
    	proveedorSMS.setProveedorSMSId(new Long(idProveedorSMS));
    	servicioProveedorSMS.deleteProveedorSMS(proveedorSMS);
    	return SUCCESS;
    	
    }
    
    public String deleteSelected() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
    	if(checkDelList == null)
    		throw new BusinessException("No se ha seleccionado ningún proveedorSMS para eliminar");
    	
    	for(String idProveedorSMS : checkDelList){
	    	proveedorSMS = new ProveedorSMSBean();
	    	proveedorSMS.setProveedorSMSId(new Long(idProveedorSMS));
	    	servicioProveedorSMS.deleteProveedorSMS(proveedorSMS);
    	}
    	return SUCCESS;
    	
    }    
	public List<ProveedorSMSBean> getListaProveedoresSMS() {
		return listaProveedoresSMS;
	}


	public void setListaProveedoresSMS(List<ProveedorSMSBean> listaProveedoresSMS) {
		this.listaProveedoresSMS = listaProveedoresSMS;
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
	
	public ServicioProveedorSMS getServicioProveedorSMS() {
		return servicioProveedorSMS;
	}
	
	public void setServicioProveedorSMS(ServicioProveedorSMS servicioProveedorSMS) {
		this.servicioProveedorSMS = servicioProveedorSMS;
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
}
