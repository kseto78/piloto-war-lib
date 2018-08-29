package es.mpr.plataformamensajeria.web.action.auditoria;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringEscapeUtils;
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
 
// TODO: Auto-generated Javadoc
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
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
//	private static Logger logger = Logger.getLogger(AuditoriaPlataformaAction.class);
	
	/**  servicio auditoria plataforma. */
@Resource(name="servicioAuditoriaPlataformaImpl")
	private ServicioAuditoriaPlataforma servicioAuditoriaPlataforma;
	
	/**  properties. */
	@Resource(name = "plataformaMensajeriaProperties")
	private PlataformaMensajeriaProperties properties;
	
	/**  auditoria plataforma. */
	private AuditoriaPlataformaBean auditoriaPlataforma;
	
	/**  lista auditorias. */
	public List<AuditoriaPlataformaBean> listaAuditorias= null;
	
	/**  combo entidad. */
	//Combos
	List<KeyValueObject> comboEntidad = new ArrayList<KeyValueObject>();
	
	/**  combo operacion. */
	List<KeyValueObject> comboOperacion = new ArrayList<KeyValueObject>();

	
    /**  result count. */
    private String resultCount;
	
    /**
     * New search.
     *
     * @return the string
     * @throws BaseException the base exception
     */
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
    
    
    /**
     * Search.
     *
     * @return the string
     * @throws BaseException the base exception
     */
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


/**
 * Obtener combo entidad.
 *
 * @return combo entidad
 */
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

	/**
	 * Obtener combo operacion.
	 *
	 * @return combo operacion
	 */
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
	

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.impl.PlataformaPaginationAction#prepare()
	 */
	@Override
	public void prepare() throws Exception {
	}

	/**
	 * Obtener lista auditorias.
	 *
	 * @return lista auditorias
	 */
	public List<AuditoriaPlataformaBean> getListaAuditorias() {
		return listaAuditorias;
	}
	
	/**
	 * Modificar lista auditorias.
	 *
	 * @param listaAuditorias new lista auditorias
	 */
	public void setListaAuditorias(List<AuditoriaPlataformaBean> listaAuditorias) {
		this.listaAuditorias = listaAuditorias;
	}
	
	/**
	 * Obtener servicio auditoria plataforma.
	 *
	 * @return servicio auditoria plataforma
	 */
	public ServicioAuditoriaPlataforma getServicioAuditoriaPlataforma() {
		return servicioAuditoriaPlataforma;
	}
	
	/**
	 * Modificar servicio auditoria plataforma.
	 *
	 * @param servicioAuditoriaPlataforma new servicio auditoria plataforma
	 */
	public void setServicioAuditoriaPlataforma(
			ServicioAuditoriaPlataforma servicioAuditoriaPlataforma) {
		this.servicioAuditoriaPlataforma = servicioAuditoriaPlataforma;
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
	 * Obtener auditoria plataforma.
	 *
	 * @return auditoria plataforma
	 */
	public AuditoriaPlataformaBean getAuditoriaPlataforma() {
		return auditoriaPlataforma;
	}
	
	/**
	 * Modificar auditoria plataforma.
	 *
	 * @param auditoriaPlataforma new auditoria plataforma
	 */
	public void setAuditoriaPlataforma(AuditoriaPlataformaBean auditoriaPlataforma) {
		this.auditoriaPlataforma = auditoriaPlataforma;
	}
	
	/**
	 * Método que resuelve el lugar donde tiene que volver.
	 *
	 * @return volver
	 */
	public String getVolver() {
		String volver="buscarAuditoria.action";
		if(!PlataformaMensajeriaUtil.isEmpty(from)&&!PlataformaMensajeriaUtil.isEmpty(idFrom)){
			volver=from+"?"+var+"="+idFrom;
		}
		return volver;
	}
}
