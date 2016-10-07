package es.mpr.template.web.action.configuracion;

import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.security.context.SecurityContextHolder;

import com.map.j2ee.exceptions.BaseException;
import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;
import com.map.j2ee.pagination.PaginationAction;

import es.mpr.template.beans.OrganismoBeanOLD;
import es.mpr.template.servicios.ifaces.ServicioOrganismoOLD;

/**
 * <p>Clase Action de Struts2 para la gesti&oacute;n de los organismos.
 * 
 * <p>
 * Proporciona m&eacute;todos para la creaci&oacute;n, modificaci&oacute;n, borrado y listado de los Organismos
 * 
 * @author Altran
 *
 */
public class OrganismoActionOLD extends PaginationAction{
	
	private static final long serialVersionUID = 1L;
	
	private static final String REQUEST_ATTRIBUTE_TOTALSIZE = "totalSize";
	private static final String REQUEST_ATTRIBUTE_PAGESIZE = "pageSize";
	
	private static final Integer PAGESIZE = new Integer(5); //Elementos por pagina
	
	public List<OrganismoBeanOLD> listaOrganismos= null;
    private ServicioOrganismoOLD servicioOrganismo;
    private OrganismoBeanOLD organismo;
    private String idOrganismo;
    
    public String execute() throws BaseException {
    	if(request.getSession().getAttribute("infoUser")==null) return "noUser"; 
    	
    	int page = getPage("tableId"); //Pagina a mostrar
    	String order = getOrder("tableId"); //Ordenar de modo ascendente o descendente
    	String columnSort = getColumnSort("tableId"); //Columna usada para ordenar
    	
    	if(organismo != null)
    		if(organismo.getNombre() != null && organismo.getNombre().length()<=0)
    			organismo.setNombre(null);
    	    	
    	int inicio = (page-1)*PAGESIZE;
    	PaginatedList<OrganismoBeanOLD> result = servicioOrganismo.getOrganismos(inicio,PAGESIZE,order,columnSort,organismo); 
    	Integer totalSize = result.getTotalList();
    	
    	listaOrganismos =  result.getPageList();
    	
    	//Atributos de request
    	getRequest().setAttribute(REQUEST_ATTRIBUTE_TOTALSIZE, totalSize);
    	getRequest().setAttribute(REQUEST_ATTRIBUTE_PAGESIZE, PAGESIZE);
    	    	    	
    	if (listaOrganismos!=null && !listaOrganismos.isEmpty())
    	{    		
    		for (int indice=0;indice<listaOrganismos.size();indice++) {
    			
    			OrganismoBeanOLD organismo = listaOrganismos.get(indice);
    			organismo.setNombre(StringEscapeUtils.escapeHtml(organismo.getNombre()));
    			organismo.setRol(StringEscapeUtils.escapeHtml(organismo.getRol()));
    		}
    	}
    	    	 	
        return SUCCESS;
    }
    
    public String create() throws BaseException {
    	if(request.getSession().getAttribute("infoUser")==null) return "noUser"; 
    	if(organismo == null)
    		throw new BusinessException("EL organismo recibido es nulo");
    	
    	servicioOrganismo.newOrganismo(organismo);
    	
    	return SUCCESS;
    	
    }
    
    public String update() throws BaseException {
    	if(request.getSession().getAttribute("infoUser")==null) return "noUser"; 
    	if(organismo == null)
    		throw new BusinessException("EL organismo recibido es nulo");
    	
    	servicioOrganismo.updateOrganismo(organismo);
    	return SUCCESS;
    	
    }
    
    public String load() throws BaseException {
    	if(request.getSession().getAttribute("infoUser")==null) return "noUser"; 
    	if(idOrganismo == null)
    		throw new BusinessException("EL idOrganismo recibido es nulo");
    	try {
			organismo = new OrganismoBeanOLD();
			organismo.setId(new Long(idOrganismo));
			organismo = servicioOrganismo.loadOrganismo(organismo);
			return SUCCESS;
    	} catch (NumberFormatException e) {
			String mensg = this.getText("errors.action.organismo.loadOrganismo",new String[]{organismo.getId().toString()});
			throw new BusinessException(mensg);
		} catch (BusinessException e) {
			String mensg = this.getText("errors.action.organismo.loadOrganismo",new String[]{organismo.getId().toString()});
			throw new BusinessException(mensg);
		}
    	
    }
    
    public String delete() throws BaseException {
    	if(request.getSession().getAttribute("infoUser")==null) return "noUser"; 
    	if(idOrganismo == null)
    		throw new BusinessException("EL idOrganismo recibido es nulo");
    	
    	organismo = new OrganismoBeanOLD();
    	organismo.setId(new Long(idOrganismo));
    	servicioOrganismo.deleteOrganismo(organismo);
    	return SUCCESS;
    	
    }
    
	public List<OrganismoBeanOLD> getListaOrganismos() {
		return listaOrganismos;
	}


	public void setListaOrganismos(List<OrganismoBeanOLD> listaOrganismos) {
		this.listaOrganismos = listaOrganismos;
	}


	public OrganismoBeanOLD getOrganismo() {
		return organismo;
	}
	
	public void setOrganismo(OrganismoBeanOLD organismo) {
		this.organismo = organismo;
	}
	
	public String getIdOrganismo() {
		return idOrganismo;
	}
	
	public void setIdOrganismo(String idOrganismo) {
		this.idOrganismo = idOrganismo;
	}
	
	public ServicioOrganismoOLD getServicioOrganismo() {
		return servicioOrganismo;
	}
	
	public void setServicioOrganismo(ServicioOrganismoOLD servicioOrganismo) {
		this.servicioOrganismo = servicioOrganismo;
	}
		
}
