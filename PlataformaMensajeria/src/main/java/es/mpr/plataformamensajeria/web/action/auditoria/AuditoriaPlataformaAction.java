package es.mpr.plataformamensajeria.web.action.auditoria;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.map.j2ee.exceptions.BaseException;
import com.map.j2ee.pagination.PaginatedList;
import com.map.j2ee.util.KeyValueObject;
import com.opensymphony.xwork2.Preparable;

import es.minhap.common.util.DateUtil;
import es.mpr.plataformamensajeria.beans.AuditoriaPlataformaBean;
import es.mpr.plataformamensajeria.impl.PlataformaPaginationAction;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioAuditoriaPlataforma;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaProperties;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;
 
/**
 * <p>Clase Action de Struts2 para la gesti&oacute;n de los Auditorias.
 * 
 * <p>
 * Proporciona m&eacute;todos para la creaci&oacute;n, modificaci&oacute;n, borrado y listado de los Usuarios
 * 
 * @author i-nercya
 *
 */
@Controller("auditoriaPlataforma")
@Scope("prototype")
public class AuditoriaPlataformaAction extends PlataformaPaginationAction implements ServletRequestAware, Preparable{
	
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(AuditoriaPlataformaAction.class);
	
	@Resource(name="servicioAuditoriaPlataformaImpl")
	private ServicioAuditoriaPlataforma servicioAuditoriaPlataforma;
	
	@Resource(name = "plataformaMensajeriaProperties")
	private PlataformaMensajeriaProperties properties;
	
	private AuditoriaPlataformaBean auditoriaPlataforma;
	
	public List<AuditoriaPlataformaBean> listaAuditorias= null;
	
	//Combos
	List<KeyValueObject> comboEntidad = new ArrayList<KeyValueObject>();
	List<KeyValueObject> comboOperacion = new ArrayList<KeyValueObject>();

	
    private String resultCount;
	
    ////MIGRADO
    public String newSearch() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
	   	int page = getPage("tableId"); //Pagina a mostrar
    	String order = getOrder("tableId"); //Ordenar de modo ascendente o descendente
    	String columnSort = getColumnSort("tableId"); //Columna usada para ordenar
    	boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
    	int inicio = (page-1)* Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20"));
    	if(auditoriaPlataforma==null){
    		auditoriaPlataforma = new AuditoriaPlataformaBean();
    		auditoriaPlataforma.setFechaDesde(DateUtil.toDayBegin(new Date()));
    		auditoriaPlataforma.setFechaHasta(DateUtil.toDayEnd(new Date()));
    	}
    	PaginatedList<AuditoriaPlataformaBean> result = servicioAuditoriaPlataforma.getAuditoriasPlataforma(inicio,(export)?-1:Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20"))
    			,order,columnSort,auditoriaPlataforma,export); 
    	Integer totalSize = result.getTotalList();
    	
    	listaAuditorias =  result.getPageList();
    	resultCount = (totalSize!=null)?totalSize.toString():"0";
    	//Atributos de request
    	getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_TOTALSIZE", null), totalSize);
    	if(!export){
    		getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_PAGESIZE", null),
    				Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20")));
    	}else{
    		getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_PAGESIZE", null), totalSize);
    	}
    	    	    	
    	if (listaAuditorias!=null && !listaAuditorias.isEmpty())
    	{    		
    		for (int indice=0;indice<listaAuditorias.size();indice++) {
    			
    			AuditoriaPlataformaBean auditoriaPlataforma = listaAuditorias.get(indice);
    			auditoriaPlataforma.setAdtUsuario(StringEscapeUtils.escapeHtml(auditoriaPlataforma.getAdtUsuario()));
    			auditoriaPlataforma.setLogDescripcion(StringEscapeUtils.escapeHtml(auditoriaPlataforma.getLogDescripcion()));
    			auditoriaPlataforma.setSourceDescription(StringEscapeUtils.escapeHtml(auditoriaPlataforma.getSourceDescription()));
    			auditoriaPlataforma.setSourceName(StringEscapeUtils.escapeHtml(auditoriaPlataforma.getSourceName()));
    		}
    	}
    	    	 	
        return SUCCESS;
    }
    
    
    ////MIGRADO
	public String search() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
	   	int page = getPage("tableId"); //Pagina a mostrar
    	String order = getOrder("tableId"); //Ordenar de modo ascendente o descendente
    	String columnSort = getColumnSort("tableId"); //Columna usada para ordenar
    	boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
    	int inicio = (page-1)* Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20"));
    	PaginatedList<AuditoriaPlataformaBean> result = servicioAuditoriaPlataforma.getAuditoriasPlataforma(inicio,(export)?-1:Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20"))
    			,order,columnSort,auditoriaPlataforma,export); 
    	Integer totalSize = result.getTotalList();
    	
    	listaAuditorias =  result.getPageList();
    	resultCount = (totalSize!=null)?totalSize.toString():"0";
    	//Atributos de request
    	getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_TOTALSIZE", null), totalSize);
    	if(!export){
    		getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_PAGESIZE", null), 
    				Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20")));
    	}else{
    		getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_PAGESIZE", null), totalSize);
    	}
    	    	    	
    	if (listaAuditorias!=null && !listaAuditorias.isEmpty())
    	{    		
    		for (int indice=0;indice<listaAuditorias.size();indice++) {
    			
    			AuditoriaPlataformaBean auditoriaPlataforma = listaAuditorias.get(indice);
    			auditoriaPlataforma.setAdtUsuario(StringEscapeUtils.escapeHtml(auditoriaPlataforma.getAdtUsuario()));
    			auditoriaPlataforma.setLogDescripcion(StringEscapeUtils.escapeHtml(auditoriaPlataforma.getLogDescripcion()));
    			auditoriaPlataforma.setSourceDescription(StringEscapeUtils.escapeHtml(auditoriaPlataforma.getSourceDescription()));
    			auditoriaPlataforma.setSourceName(StringEscapeUtils.escapeHtml(auditoriaPlataforma.getSourceName()));
    		}
    	}
    	    	 	
        return SUCCESS;
	}


////MIGRADO
	public List<KeyValueObject> getComboEntidad() {
        List<KeyValueObject> result = new ArrayList<>();
        KeyValueObject option;
        option = new KeyValueObject();
		option.setCodigo("APLICACIONES");
		option.setDescripcion("APLICACIONES");
		result.add(option);		
		
		option = new KeyValueObject();
		option.setCodigo("PROVEEDORES");
		option.setDescripcion("PROVEEDORES SMS");
		result.add(option);
		
		option = new KeyValueObject();
		option.setCodigo("SERVICIOS");
		option.setDescripcion("SERVICIOS");
		result.add(option);		
		
		option = new KeyValueObject();
		option.setCodigo("ORGANISMOS");
		option.setDescripcion("ORGANISMOS");
		result.add(option);
		
		option = new KeyValueObject();
		option.setCodigo("SERVIDORES");
		option.setDescripcion("SERVIDORES SMTP");
		result.add(option);
		
		option = new KeyValueObject();
		option.setCodigo("USUARIOS");
		option.setDescripcion("USUARIOS");
		result.add(option);	
		
		option = new KeyValueObject();
		option.setCodigo("RECEPTORES");
		option.setDescripcion("RECEPTORES SMS");
		result.add(option);
		
		option = new KeyValueObject();
		option.setCodigo("SERVIDORES PUSH");
		option.setDescripcion("SERVIDORES PUSH");
		result.add(option);
		
		option = new KeyValueObject();
		option.setCodigo("SERVIDORES WEB PUSH");
		option.setDescripcion("SERVIDORES WEB PUSH");
		result.add(option);
		
		
		
		return result;
    }

	////MIGRADO
	public List<KeyValueObject> getComboOperacion() {
        List<KeyValueObject> result = new ArrayList<>();
        KeyValueObject option;
		
        option = new KeyValueObject();
		option.setCodigo("3");
		option.setDescripcion("Actualización");
		result.add(option);

        option = new KeyValueObject();
		option.setCodigo("1");
		option.setDescripcion("Creación");
		result.add(option);

		option = new KeyValueObject();
		option.setCodigo("2");
		option.setDescripcion("Eliminación");
		result.add(option);
		
		return result;
    }
	

	@Override
	public void prepare() throws Exception {
	}

	public List<AuditoriaPlataformaBean> getListaAuditorias() {
		return listaAuditorias;
	}
	public void setListaAuditorias(List<AuditoriaPlataformaBean> listaAuditorias) {
		this.listaAuditorias = listaAuditorias;
	}
	public ServicioAuditoriaPlataforma getServicioAuditoriaPlataforma() {
		return servicioAuditoriaPlataforma;
	}
	public void setServicioAuditoriaPlataforma(
			ServicioAuditoriaPlataforma servicioAuditoriaPlataforma) {
		this.servicioAuditoriaPlataforma = servicioAuditoriaPlataforma;
	}
	public String getResultCount() {
		return resultCount;
	}
	public void setResultCount(String resultCount) {
		this.resultCount = resultCount;
	}
	public AuditoriaPlataformaBean getAuditoriaPlataforma() {
		return auditoriaPlataforma;
	}
	public void setAuditoriaPlataforma(AuditoriaPlataformaBean auditoriaPlataforma) {
		this.auditoriaPlataforma = auditoriaPlataforma;
	}
	/**
	 * Método que resuelve el lugar donde tiene que volver
	 */
	public String getVolver() {
		String volver="buscarAuditoria.action";
		if(!PlataformaMensajeriaUtil.isEmpty(from)&&!PlataformaMensajeriaUtil.isEmpty(idFrom)){
			volver=from+"?"+var+"="+idFrom;
		}
		return volver;
	}
}
