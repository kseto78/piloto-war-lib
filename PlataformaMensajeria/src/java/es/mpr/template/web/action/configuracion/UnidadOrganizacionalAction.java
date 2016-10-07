package es.mpr.template.web.action.configuracion;

import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.security.context.SecurityContextHolder;

import com.map.j2ee.exceptions.BaseException;
import com.map.j2ee.exceptions.BusinessException;
import com.opensymphony.xwork2.ActionSupport;

import es.mpr.template.beans.UnidadOrganizacionalBean;
import es.mpr.template.servicios.ifaces.ServicioUnidadOrganizacional;

/**
 * <p>Clase Action de Struts2 para la gesti&oacute;n de las unidades organizacionales.
 * 
 * <p>
 * Proporciona m&eacute;todos para la creaci&oacute;n, modificaci&oacute;n, borrado y listado de 
 * unidades organizacionales.
 * 
 * @author Altran
 *
 */
public class UnidadOrganizacionalAction extends ActionSupport {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public  List<UnidadOrganizacionalBean>  listaUnidadesOrganizacionales= null;
    private ServicioUnidadOrganizacional servicioUnidadOrganizacional;
    private UnidadOrganizacionalBean unidadOrganizacional;
    private String idUnidadOrg;
    

    public String execute() throws BaseException {
    	//if(request.getSession().getAttribute("infoUser")==null) return "noUser"; 
    	
    	if(unidadOrganizacional != null)
    		if(unidadOrganizacional.getNombre() != null && unidadOrganizacional.getNombre().length()<=0)
    			unidadOrganizacional.setNombre(null);
    	
    	listaUnidadesOrganizacionales = servicioUnidadOrganizacional.getUnidadesOrganizacionales(unidadOrganizacional);
    	
    	if (listaUnidadesOrganizacionales!=null && !listaUnidadesOrganizacionales.isEmpty())
    	{
    		for (int indice=0;indice<listaUnidadesOrganizacionales.size();indice++) {
    			UnidadOrganizacionalBean unaUnidadOrganizacional = listaUnidadesOrganizacionales.get(indice);
    			unaUnidadOrganizacional.setNombre(StringEscapeUtils.escapeHtml(unaUnidadOrganizacional.getNombre()));
    			unaUnidadOrganizacional.setComunidad(StringEscapeUtils.escapeHtml(unaUnidadOrganizacional.getComunidad()));
    			unaUnidadOrganizacional.setProvincia(StringEscapeUtils.escapeHtml(unaUnidadOrganizacional.getProvincia()));
    			unaUnidadOrganizacional.setLocalidad(StringEscapeUtils.escapeHtml(unaUnidadOrganizacional.getLocalidad()));
    		}
    	}
    	 	
        return SUCCESS;
    }
    
    public String create() throws BaseException {
    	//if(request.getSession().getAttribute("infoUser")==null) return "noUser"; 
    	if(unidadOrganizacional == null)
    		throw new BusinessException("La unidadOrganizacional recibida es nula");
    	
    	servicioUnidadOrganizacional.newUnidadOrganizacional(unidadOrganizacional);
    	return SUCCESS;
    	
    }
    
    public String update() throws BaseException {
    	//if(request.getSession().getAttribute("infoUser")==null) return "noUser"; 
    	if(unidadOrganizacional == null)
    		throw new BusinessException("La unidadOrganizacional recibida es nula");
    	
    	servicioUnidadOrganizacional.updateUnidadOrganizacional(unidadOrganizacional);
    	return SUCCESS;
    	
    }
    
    public String load() throws BaseException {
    	//if(request.getSession().getAttribute("infoUser")==null) return "noUser"; 
    	if(idUnidadOrg == null)
    		throw new BusinessException("EL identificador  recibido es nulo");
    	try {
			unidadOrganizacional = new UnidadOrganizacionalBean();
			unidadOrganizacional.setId(new Long(idUnidadOrg));
			unidadOrganizacional = servicioUnidadOrganizacional
					.loadUnidadOrganizacional(unidadOrganizacional);
			return SUCCESS;
		} catch (BusinessException e) {
			String mensg = this.getText("errors.action.organismo.loadUnidadOrganizacional",new String[]{idUnidadOrg});
			throw new BusinessException(mensg);
		}
    	
    }
    
    public String delete() throws BaseException {
    	//if(request.getSession().getAttribute("infoUser")==null) return "noUser"; 
    	if(idUnidadOrg == null)
    		throw new BusinessException("El identificador  recibido es nulo");
    	
    	unidadOrganizacional = new UnidadOrganizacionalBean();
    	unidadOrganizacional.setId(new Long(idUnidadOrg));
    	servicioUnidadOrganizacional.deleteUnidadOrganizacional(unidadOrganizacional);
    	return SUCCESS;
    	
    }
    
	public List<UnidadOrganizacionalBean> getListaUnidadesOrganizacionales() {
		return listaUnidadesOrganizacionales;
	}
	public void setListaUnidadesOrganizacionales(
			List<UnidadOrganizacionalBean> listaUnidadesOrganizacionales) {
		this.listaUnidadesOrganizacionales = listaUnidadesOrganizacionales;
	}
	
	public ServicioUnidadOrganizacional getServicioUnidadOrganizacional() {
		return servicioUnidadOrganizacional;
	}
	
	public void setServicioUnidadOrganizacional(
			ServicioUnidadOrganizacional servicioUnidadOrganizacional) {
		this.servicioUnidadOrganizacional = servicioUnidadOrganizacional;
	}
	
	public UnidadOrganizacionalBean getUnidadOrganizacional() {
		return unidadOrganizacional;
	}
	
	public void setUnidadOrganizacional(UnidadOrganizacionalBean unidadOrganizacional) {
		this.unidadOrganizacional = unidadOrganizacional;
	}
	
	public String getIdUnidadOrg() {
		return idUnidadOrg;
	}
	
	public void setIdUnidadOrg(String idUnidadOrg) {
		this.idUnidadOrg = idUnidadOrg;
	}
	
}
