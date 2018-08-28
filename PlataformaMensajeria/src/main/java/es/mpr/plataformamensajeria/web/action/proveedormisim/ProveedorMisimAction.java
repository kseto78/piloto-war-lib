package es.mpr.plataformamensajeria.web.action.proveedormisim;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.map.j2ee.exceptions.BaseException;
import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;
import com.map.j2ee.util.KeyValueObject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

import es.minhap.misim.bus.model.Endpoint;
import es.minhap.misim.bus.model.Producto;
import es.minhap.misim.bus.model.Transformacion;
import es.minhap.plataformamensajeria.iop.misim.manager.ComunicacionesManager;
import es.minhap.plataformamensajeria.iop.misim.manager.EndpointsManager;
import es.minhap.plataformamensajeria.iop.misim.manager.ProductosManager;
import es.minhap.plataformamensajeria.iop.misim.manager.TransformacionesManager;
import es.mpr.plataformamensajeria.beans.ComunicacionBean;
import es.mpr.plataformamensajeria.beans.EndpointBean;
import es.mpr.plataformamensajeria.beans.ParametrosProveedorBean;
import es.mpr.plataformamensajeria.beans.ProductoBean;
import es.mpr.plataformamensajeria.beans.ProveedorMisimBean;
import es.mpr.plataformamensajeria.beans.TransformacionBean;
import es.mpr.plataformamensajeria.impl.PlataformaPaginationAction;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioParametroProveedorMisim;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioProveedorMisim;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaProperties;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

/**
 * <p>
 * Clase Action de Struts2 para la gesti&oacute;n de los proveedores Misim.
 * 
 * <p>
 * Proporciona m&eacute;todos para la creaci&oacute;n, modificaci&oacute;n,
 * borrado y listado de los proveedores Misim
 * 
 * @author everis
 * 
 */
@Controller("proveedorMisimAction")
@Scope("prototype")
public class ProveedorMisimAction extends PlataformaPaginationAction implements ServletRequestAware, Preparable {

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**  logger. */
	private static Logger logger = Logger.getLogger(ProveedorMisimAction.class);

	/**  servicio proveedor misim. */
	@Resource(name = "servicioProveedorMisimImpl")
	private transient ServicioProveedorMisim servicioProveedorMisim;
	
	/**  servicio parametro proveedor misim. */
	@Resource(name = "servicioParametroProveedorMisimImpl")
	private transient ServicioParametroProveedorMisim servicioParametroProveedorMisim;
	
	/**  productos manager. */
	@Resource(name = "ProductosManagerImpl")
	private transient ProductosManager productosManager;
	
	/**  endpoints manager. */
	@Resource(name = "EndpointsManagerImpl")
	private transient EndpointsManager endpointsManager;
	
	/**  transformaciones manager. */
	@Resource(name = "TransformacionesManagerImpl")
	private transient TransformacionesManager transformacionesManager;
	
	/**  comunicaciones manager. */
	@Resource(name = "ComunicacionesManagerImpl")
	private transient ComunicacionesManager comunicacionesManager;

	/**  properties. */
	@Resource(name = "plataformaMensajeriaProperties")
	private transient PlataformaMensajeriaProperties properties;

	/**  proveedor misim. */
	private ProveedorMisimBean proveedorMisim;
	
	/**  parametro proveedor. */
	private ParametrosProveedorBean parametroProveedor; 
	
	/**  combo productos proveedor misim. */
	transient List<KeyValueObject> comboProductosProveedorMisim = new ArrayList<>();
	
	/**  combo endpoints proveedor misim. */
	transient List<KeyValueObject> comboEndpointsProveedorMisim = new ArrayList<>();
	
	/**  combo transformaciones proveedor misim. */
	transient List<KeyValueObject> comboTransformacionesProveedorMisim = new ArrayList<>();
	
	/**  combo comunicaciones endpoints. */
	transient List<KeyValueObject> comboComunicacionesEndpoints = new ArrayList<>();	
	
	/**  lista proveedores misim. */
	public List<ProveedorMisimBean> listaProveedoresMisim = null;
	
	/**  lista parametros proveedor misim. */
	private List<ParametrosProveedorBean> listaParametrosProveedorMisim = null;

	/**  check del list. */
	private String[] checkDelList;
	
	/**  check del list parametros proveedor misim. */
	private String[] checkDelListParametrosProveedorMisim;

	/**  id proveedor. */
	private String idProveedor;
	
	/**  id parametros proveedor. */
	private String idParametrosProveedor;

	/**  readonly. */
	private String readonly = "false";
	
	/**  check password. */
	private String checkPassword;
	
	/**  recovery. */
	private String recovery = "";
	
	/**  session. */
	@SuppressWarnings("rawtypes")
	private  Map session;
	
	/**  json. */
	String json;

	/** Constante RECOVERY. */
	private static final String RECOVERY = "recovery";

	/**
	 * New search.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	public String newSearch() throws BaseException {
		return SUCCESS;
	}
	
	/**
	 * Search.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	public String search() throws BaseException {

		if (getRequest().getSession().getAttribute("infoUser") == null) return "noUser";
		int page = getPage("tableId"); // Pagina a mostrar
		String order = getOrder("tableId"); // Ordenar de modo ascendente o descendente
		String columnSort = getColumnSort("tableId"); // Columna usada para ordenar

		if (proveedorMisim != null)
			if (proveedorMisim.getNombre() != null && proveedorMisim.getNombre().length() <= 0)
				proveedorMisim.setNombre(null);

		int inicio = (page - 1) * Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20"));
		boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
		PaginatedList<ProveedorMisimBean> result = servicioProveedorMisim.getProveedoresMisim(inicio, 
				(export) ? -1 : Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20")), order, 
				columnSort, proveedorMisim);
		Integer totalSize = result.getTotalList();

		listaProveedoresMisim = result.getPageList();

		// Atributos de request
		getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_TOTALSIZE", null), totalSize);
		if (!export) {
			getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_PAGESIZE", null), 
					Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20")));
		} else {
			getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_PAGESIZE", null), totalSize);
		}

		if (listaProveedoresMisim != null && !listaProveedoresMisim.isEmpty()) {
			for (int indice = 0; indice < listaProveedoresMisim.size(); indice++) {

				ProveedorMisimBean proveedorMisim = listaProveedoresMisim.get(indice);
				proveedorMisim.setNombre(StringEscapeUtils.escapeHtml(proveedorMisim.getNombre()));
				proveedorMisim.setCertificado(StringEscapeUtils.escapeHtml(proveedorMisim.getCertificado()));
				proveedorMisim.setCertificadoPass(StringEscapeUtils.escapeHtml(proveedorMisim.getCertificadoPass()));
				proveedorMisim.setCifrado(StringEscapeUtils.escapeHtml(proveedorMisim.getCifrado()));
				proveedorMisim.setEsquemaCifrado(StringEscapeUtils.escapeHtml(proveedorMisim.getEsquemaCifrado()));
				proveedorMisim.setFirma(StringEscapeUtils.escapeHtml(proveedorMisim.getFirma()));
				proveedorMisim.setNodoCifrado(StringEscapeUtils.escapeHtml(proveedorMisim.getNodoCifrado()));
				proveedorMisim.setPassword(StringEscapeUtils.escapeHtml(proveedorMisim.getPassword()));
				proveedorMisim.setTipoFirma(StringEscapeUtils.escapeHtml(proveedorMisim.getTipoFirma()));
				proveedorMisim.setType(StringEscapeUtils.escapeHtml(proveedorMisim.getType()));
				proveedorMisim.setUsuario(StringEscapeUtils.escapeHtml(proveedorMisim.getUsuario()));
			}
		}

		return SUCCESS;
	}
	
	/**
	 * Creates the.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	public String create() throws BaseException {
		
		String accion = properties.getProperty("log.ACCION_INSERTAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_INSERTAR", null));
		String source = properties.getProperty("log.SOURCE_PROVEEDORES_MISIM", null);
		
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";

		if (proveedorMisim != null) {
			
			if (!validaProveedorMisim(proveedorMisim, true)) {
				addActionErrorSession(this.getText("plataforma.proveedorMisim.create.error"));
				return ERROR;
			}
			
			Long idProveedor = servicioProveedorMisim.newProveedorMisim(proveedorMisim, source, accion, accionId);
			this.idProveedor = idProveedor.toString();

			addActionMessageSession(this.getText("plataforma.proveedorMisim.create.ok"));
		}

		return SUCCESS;

	}

	/**
	 * Valida proveedor misim.
	 *
	 * @param proveedorMisim the proveedor misim
	 * @param isUpdate the is update
	 * @return true, if successful
	 */
	private boolean validaProveedorMisim(ProveedorMisimBean proveedorMisim, boolean isUpdate) {
		boolean sw = true;
		
		if (PlataformaMensajeriaUtil.isEmpty(proveedorMisim.getNombre())) {
			addActionErrorSession(this.getText("plataforma.proveedorMisim.field.nombreProveedor.error"));
			sw = false;
		}
		if(!PlataformaMensajeriaUtil.isEmpty(proveedorMisim.getFirma()) && proveedorMisim.getFirma().length() > 1){
			addActionErrorSession(this.getText("plataforma.proveedorMisim.field.firma.error"));
			sw = false;
		}
		if(!PlataformaMensajeriaUtil.isEmpty(proveedorMisim.getCifrado()) && proveedorMisim.getCifrado().length() > 1){
			addActionErrorSession(this.getText("plataforma.proveedorMisim.field.cifrado.error"));
			sw = false;
		}
		if(!PlataformaMensajeriaUtil.isEmpty(proveedorMisim.getBasicAutentication()) && proveedorMisim.getBasicAutentication().length() > 2){
			addActionErrorSession(this.getText("plataforma.proveedorMisim.field.basicAutentication.error"));
			sw = false;
		}
		if(!PlataformaMensajeriaUtil.isEmpty(proveedorMisim.getAnadirUim()) && proveedorMisim.getAnadirUim().length() > 1){
			addActionErrorSession(this.getText("plataforma.proveedorMisim.field.anadirUIM.error"));
			sw = false;
		}
		
	
	if (proveedorMisim.getProducto().getIdProducto()==null){
		addActionErrorSession(this.getText("plataforma.proveedorMisim.field.Producto.error"));
		sw = false;	
	}
	
	else if (proveedorMisim.getProducto().getIdProducto()== -2) {	
		if (PlataformaMensajeriaUtil.isEmpty(proveedorMisim.getProducto().getNombre())) {
			addActionErrorSession(this.getText("plataforma.proveedorMisim.field.nombreProducto.error"));
			sw = false;
		}
		if (PlataformaMensajeriaUtil.isEmpty(proveedorMisim.getProducto().getCodigo())) {
			addActionErrorSession(this.getText("plataforma.proveedorMisim.field.codigoProducto.error"));
			sw = false;
		}
	}
	
	
	if (proveedorMisim.getEndpoint().getIdEndpoint()==null){
		addActionErrorSession(this.getText("plataforma.proveedorMisim.field.Endpoint.error"));
		sw = false;	
	}	
	
	else if (proveedorMisim.getEndpoint().getIdEndpoint()== -2) {	
		if (PlataformaMensajeriaUtil.isEmpty(proveedorMisim.getEndpoint().getNombre())) {
			addActionErrorSession(this.getText("plataforma.proveedorMisim.field.nombreEndpoint.error"));
			sw = false;
		}
		if (PlataformaMensajeriaUtil.isEmpty(proveedorMisim.getEndpoint().getUrlEndpoint())) {
			addActionErrorSession(this.getText("plataforma.proveedorMisim.field.urlEndpoint.error"));
			sw = false;
		}
		if (proveedorMisim.getEndpoint().getComunicacion().getIdComunicacion() == null) {
			addActionErrorSession(this.getText("plataforma.proveedorMisim.field.Comunicacion.error"));
			sw = false;
		}
			}
	
	
	if (proveedorMisim.getTransformacion().getIdTransformacion()!=null && proveedorMisim.getTransformacion().getIdTransformacion() == -2) {
		if (PlataformaMensajeriaUtil.isEmpty(proveedorMisim.getTransformacion().getNombre())) {
			addActionErrorSession(this.getText("plataforma.proveedorMisim.field.nombreTransformacion.error"));
			sw = false;
		}
		if (PlataformaMensajeriaUtil.isEmpty(proveedorMisim.getTransformacion().getXslPeticion())) {
			addActionErrorSession(this.getText("plataforma.proveedorMisim.field.xslPeticion.error"));
			sw = false;
		}
		if (PlataformaMensajeriaUtil.isEmpty(proveedorMisim.getTransformacion().getXslRespuesta())) {
			addActionErrorSession(this.getText("plataforma.proveedorMisim.field.xslRespuesta.error"));
			sw = false;
		}	
	}
		return sw;
	}
	
	/**
	 * Update.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	public String update() throws BaseException {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		String accion = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
		String source = properties.getProperty("log.SOURCE_PROVEEDORES_MISIM", null);

		ProveedorMisimBean proveedorMisimBBDD = null;
		if (proveedorMisim == null) {
			addActionErrorSession(this.getText("plataforma.proveedorMisim.update.error"));
		} else {
			
			if (!validaProveedorMisim(proveedorMisim, true)) {
				addActionErrorSession(this.getText("plataforma.proveedorMisim.update.error"));
				return ERROR;
			}
			
			ProveedorMisimBean proveedorMisimNuevo = proveedorMisim;
			
			logger.info("[ProveedorMisimAction - Update] IdProveedor = " + idProveedor);
			
			if (proveedorMisim.getIdProveedor() == null) {
				if (idProveedor != null) {
					proveedorMisim.setIdProveedor(new Long(idProveedor));
					proveedorMisimBBDD = servicioProveedorMisim.loadProveedorMisim(proveedorMisim);
				} else {
					String idProveedor = (String) request.getAttribute("idProveedor");
					logger.info("[ProveedorMisimAction - request.getAttribute('idProveedor)' == " + idProveedor);
					if (idProveedor != null) {
						proveedorMisimBBDD = servicioProveedorMisim.loadProveedorMisim(proveedorMisim);
					}
				}
			} else {
				proveedorMisimBBDD = servicioProveedorMisim.loadProveedorMisim(proveedorMisim);
			}
			if (proveedorMisimBBDD != null) {
				
				proveedorMisimBBDD.setIdProveedor(proveedorMisim.getIdProveedor());
				proveedorMisimBBDD.setNombre(proveedorMisim.getNombre());
				proveedorMisimBBDD.setCertificado(proveedorMisim.getCertificado());
				proveedorMisimBBDD.setCertificadoPass(proveedorMisim.getCertificadoPass());
				proveedorMisimBBDD.setCifrado(proveedorMisim.getCifrado());
				proveedorMisimBBDD.setCompany(proveedorMisim.getCompany());
				/*DUDA proveedores*/
				proveedorMisimBBDD.setEncoding(proveedorMisim.getEncoding());
				proveedorMisimBBDD.setBasicAutentication(proveedorMisim.getBasicAutentication());
				proveedorMisimBBDD.setMethod(proveedorMisim.getMethod());
				proveedorMisimBBDD.setMediaType(proveedorMisim.getMediaType());
				proveedorMisimBBDD.setAnadirUim(proveedorMisim.getAnadirUim());
				proveedorMisimBBDD.setUserAutentication(proveedorMisim.getUserAutentication());
				proveedorMisimBBDD.setPassAutentication(proveedorMisim.getPassAutentication());	
				
				proveedorMisimBBDD.setEsquemaCifrado(proveedorMisim.getEsquemaCifrado());
				proveedorMisimBBDD.setFirma(proveedorMisim.getFirma());
				proveedorMisimBBDD.setNodoCifrado(proveedorMisim.getNodoCifrado());
				proveedorMisimBBDD.setPassword(proveedorMisim.getPassword());
				proveedorMisimBBDD.setTipoFirma(proveedorMisim.getTipoFirma());
				proveedorMisimBBDD.setType(proveedorMisim.getType());
				proveedorMisimBBDD.setUsuario(proveedorMisim.getUsuario());
				
				proveedorMisimBBDD.setEndpoint(proveedorMisim.getEndpoint());
				proveedorMisimBBDD.setProducto(proveedorMisim.getProducto());
				proveedorMisimBBDD.setTransformacion(proveedorMisim.getTransformacion());
				
				proveedorMisim = proveedorMisimBBDD;
				if (validaProveedorMisim(proveedorMisimBBDD, true)) {
					servicioProveedorMisim.updateProveedorMisim(proveedorMisimBBDD, proveedorMisimNuevo, source, accion, accionId);
					addActionMessageSession(this.getText("plataforma.proveedorMisim.update.ok"));
				}
			}

		}
		return SUCCESS;

	}
	
	/**
	 * Load.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	public String load() throws BaseException {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		int page = getPage("tableId"); // Pagina a mostrar
		String order = getOrder("tableId"); // Ordenar de modo ascendente o descendente
		String columnSort = getColumnSort("tableId"); // Columna usada para ordenar

		if (proveedorMisim != null)
			if (proveedorMisim.getNombre() != null && proveedorMisim.getNombre().length() <= 0)
				proveedorMisim.setNombre(null);

		int inicio = (page - 1) * Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20"));
		PaginatedList<ProveedorMisimBean> result = servicioProveedorMisim.getProveedoresMisim(inicio, 
				Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20")), order, columnSort, proveedorMisim);

		listaProveedoresMisim = result.getPageList();
		
		if (idProveedor == null)
			throw new BusinessException("EL idProveedor recibido es nulo");
		try {
			proveedorMisim = new ProveedorMisimBean();
			proveedorMisim.setIdProveedor(new Long(idProveedor));
			proveedorMisim = servicioProveedorMisim.loadProveedorMisim(proveedorMisim);
			
			return SUCCESS;
		} catch (NumberFormatException e) {
			String mensg = this.getText("errors.action.proveedorMisim.loadProveedorMisim", new String[] { (null != proveedorMisim && null != proveedorMisim.getIdProveedor())? proveedorMisim.getIdProveedor().toString() : "No existe IdProveedor" });
			throw new BusinessException(mensg);
		}
	}	
	
	/**
	 * Delete.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	public String delete() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ELIMINAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ELIMINAR", null));
		String source = properties.getProperty("log.SOURCE_PROVEEDORES_MISIM", null);
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (idProveedor == null) {
			addActionErrorSession(this.getText("plataforma.proveedorMisim.delete.error"));

		} else {
			proveedorMisim = new ProveedorMisimBean();
			proveedorMisim.setIdProveedor(new Long(idProveedor));
			servicioProveedorMisim.deleteProveedorMisim(proveedorMisim, accion, accionId, source);
			addActionMessageSession(this.getText("plataforma.proveedorMisim.delete.ok"));
		}
		return SUCCESS;

	}
	
	/**
	 * Delete selected.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	public String deleteSelected() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ELIMINAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ELIMINAR", null));
		String source = properties.getProperty("log.SOURCE_PROVEEDORES_MISIM", null);
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (checkDelList == null) {
			addActionErrorSession(this.getText("plataforma.proveedorMisim.deleteSelected.error"));

		} else {
			for (String idProveedor : checkDelList) {
				proveedorMisim = new ProveedorMisimBean();
				proveedorMisim.setIdProveedor(new Long(idProveedor));
				servicioProveedorMisim.deleteProveedorMisim(proveedorMisim, accion, accionId, source);
			}
			addActionMessageSession(this.getText("plataforma.proveedorMisim.deleteSelected.ok"));

		}
		return SUCCESS;
	}
	
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.impl.PlataformaPaginationAction#prepare()
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void prepare() throws Exception {
		this.validate();
		session = (Map) ActionContext.getContext().get("session");
		recovery = (String) session.get(RECOVERY);
		
		comboProductosProveedorMisim = getComboValuesProductos();
		comboEndpointsProveedorMisim = getComboValuesEndpoints();
		comboTransformacionesProveedorMisim = getComboValuesTransformaciones();
		comboComunicacionesEndpoints = getComboValuesComunicaciones();
		listaParametrosProveedorMisim = getParametrosProveedorMisim();
		
	}
	
	/**
	 * Obtener combo values productos.
	 *
	 * @return combo values productos
	 */
	private List<KeyValueObject> getComboValuesProductos() {
		List<KeyValueObject> result = new ArrayList<>();

		KeyValueObject option = null;
		ArrayList<ProductoBean> keys = null;
		try {
			keys = (ArrayList<ProductoBean>) servicioProveedorMisim.getProductos();
		} catch (BusinessException e) {
			logger.error("ProveedorMisimAction - getComboValuesProductos:" + e);
		}

		option = new KeyValueObject();
		option.setCodigo("-2");
		option.setDescripcion("NUEVO PRODUCTO");
		result.add(option);
		
		if (keys != null && !keys.isEmpty()){
			for (ProductoBean key : keys) {

				option = new KeyValueObject();
				option.setCodigo(key.getIdProducto().toString());
				option.setDescripcion(key.getNombre());
				result.add(option);
			}
		}
		return result;
	}
	
	/**
	 * Obtener combo values endpoints.
	 *
	 * @return combo values endpoints
	 */
	private List<KeyValueObject> getComboValuesEndpoints() {
		List<KeyValueObject> result = new ArrayList<>();

		KeyValueObject option = null;
		ArrayList<EndpointBean> keys = null;
		try {
			keys = (ArrayList<EndpointBean>) servicioProveedorMisim.getEndpoints();
		} catch (BusinessException e) {
			logger.error("ProveedorMisimAction - getComboValuesEndpoints:" + e);
		}
		option = new KeyValueObject();
		option.setCodigo("-2");
		option.setDescripcion("NUEVO ENDPOINT");
		result.add(option);
		
		if (keys != null && !keys.isEmpty()){
			for (EndpointBean key : keys) {

				option = new KeyValueObject();
				option.setCodigo(key.getIdEndpoint().toString());
				option.setDescripcion(key.getNombre());
				result.add(option);
			}
		}
		return result;
	}
	
	/**
	 * Obtener combo values transformaciones.
	 *
	 * @return combo values transformaciones
	 */
	private List<KeyValueObject> getComboValuesTransformaciones() {
		List<KeyValueObject> result = new ArrayList<>();

		KeyValueObject option = null;
		ArrayList<TransformacionBean> keys = null;
		try {
			keys = (ArrayList<TransformacionBean>) servicioProveedorMisim.getTransformaciones();
		} catch (BusinessException e) {
			logger.error("ProveedorMisimAction - getComboValuesTransformaciones:" + e);
		}

		option = new KeyValueObject();
		option.setCodigo("-2");
		option.setDescripcion("NUEVA TRANSFORMACIÓN");
		result.add(option);
		
		if (keys != null && !keys.isEmpty()){
			for (TransformacionBean key : keys) {

				option = new KeyValueObject();
				option.setCodigo(key.getIdTransformacion().toString());
				option.setDescripcion(key.getNombre());
				result.add(option);
			}
		}		
		return result;
	}
	
	/**
	 * Obtener combo values comunicaciones.
	 *
	 * @return combo values comunicaciones
	 */
	private List<KeyValueObject> getComboValuesComunicaciones() {
		List<KeyValueObject> result = new ArrayList<KeyValueObject>();

		KeyValueObject option = null;
		ArrayList<ComunicacionBean> keys = null;
		try {
			keys = (ArrayList<ComunicacionBean>) servicioProveedorMisim.getComunicaciones();
		} catch (BusinessException e) {
			logger.error("ProveedorMisimAction - getComboValuesComunicaciones:" + e);
		}

		if (keys != null && keys.size() > 0)
			for (ComunicacionBean key : keys) {

				option = new KeyValueObject();
				option.setCodigo(key.getIdComunicacion().toString());
				option.setDescripcion(key.getNombre());
				result.add(option);
			}
		return result;
	}
	

	/**
	 * Método que resuelve el lugar donde tiene que volver.
	 *
	 * @return volver
	 */
	public String getVolver() {
		String volver="buscarProveedoresMisim.action";
		if(!PlataformaMensajeriaUtil.isEmpty(from)&&!PlataformaMensajeriaUtil.isEmpty(idFrom)){
			volver=from+"?"+var+"="+idFrom;
		}
		return volver;
	}
	
	/**
	 * Ajax load producto.
	 *
	 * @return the string
	 */
	public String ajaxLoadProducto() {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		String idProducto = getRequest().getParameter("idProducto");
		if (idProducto == null) {
			addFieldErrorSession("Datos incorrectos");
		} else {
			try {
				Producto producto = null;
				if (Long.valueOf(idProducto) > 0) {
					producto = productosManager.getProducto(Long.valueOf(idProducto));
					if(producto!=null) {
						json = new Gson().toJson(producto);
					}
				}
			}
			catch (Exception e) {
				logger.error("ProveedorMisimAction - ajaxLoadProducto:" + e);
			}

		}
		return SUCCESS;
	}
	
	/**
	 * Ajax load endpoint.
	 *
	 * @return the string
	 */
	public String ajaxLoadEndpoint() {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		String idEndpoint = getRequest().getParameter("idEndpoint");
		if (idEndpoint == null) {
			addFieldErrorSession("Datos incorrectos");
		} else {
			try {
				Endpoint endpoint = null;
				if (Long.valueOf(idEndpoint) > 0) {
					endpoint = endpointsManager.getEndpoint(Long.valueOf(idEndpoint));
					if(endpoint!=null) {
						json = new Gson().toJson(endpoint);
					}
				}
			}
			catch (Exception e) {
				logger.error("ProveedorMisimAction - ajaxLoadEndpoint:" + e);
			}

		}
		return SUCCESS;
	}
	
	/**
	 * Ajax load transformacion.
	 *
	 * @return the string
	 */
	public String ajaxLoadTransformacion() {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		String idTransformacion = getRequest().getParameter("idTransformacion");
		if (idTransformacion == null) {
			addFieldErrorSession("Datos incorrectos");
		} else {
			try {
				Transformacion transformacion = null;
				if (Long.valueOf(idTransformacion) > 0) {
					transformacion = transformacionesManager.getTransformacion(Long.valueOf(idTransformacion));
					if(transformacion!=null) {
						json = new Gson().toJson(transformacion);
					}
				}
			}
			catch (Exception e) {
				logger.error("ProveedorMisimAction - ajaxLoadTransformacion:" + e);
			}

		}
		return SUCCESS;
	}
	
	/**
	 * Obtener parametros proveedor misim.
	 *
	 * @return parametros proveedor misim
	 */
	private List<ParametrosProveedorBean> getParametrosProveedorMisim() {
		List<ParametrosProveedorBean> lista = null;
		if (idProveedor != null && idProveedor.length() > 0) {
			try {
				lista = servicioParametroProveedorMisim.getParametrosProveedorMisimByProveedorId(Long.valueOf(idProveedor));
			} catch (NumberFormatException e) {
				logger.error("ProveedorMisimAction - getParametrosProveedorMisim: idProveedor no es numerico" + e);
			} catch (BusinessException e) {
				logger.error("ProveedorMisimAction - getParametrosProveedorMisim:" + e);
			}
		} else if (proveedorMisim != null && proveedorMisim.getIdProveedor() != null) {
			try {
				lista = servicioParametroProveedorMisim.getParametrosProveedorMisimByProveedorId(proveedorMisim.getIdProveedor());
			} catch (NumberFormatException e) {
				logger.error("ProveedorMisimAction - getParametrosProveedorMisim: idProveedor no es numerico" + e);
			} catch (BusinessException e) {
				logger.error("ProveedorMisimAction - getParametrosProveedorMisim:" + e);
			}
		}
		return lista;
	}
	
	/**
	 * Agrega parametro proveedor misim.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	public String addParametroProveedorMisim() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
		String source = properties.getProperty("log.SOURCE_PROVEEDORES_MISIM", null);
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ANADIR_PARAMETRO", null);
		
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
		if (parametroProveedor != null) {
			if (!validaParametro(parametroProveedor)) {
				return ERROR;
			} else {
				servicioParametroProveedorMisim.newParametroProveedorMisim(parametroProveedor, source, accion, accionId, descripcion);
				addActionMessageSession(this.getText("plataforma.proveedorMisim.parametro.add.ok"));
			}
		} else {
			addActionErrorSession(this.getText("plataforma.proveedorMisim.parametro.add.error"));
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	/**
	 * Valida parametro.
	 *
	 * @param parametroProveedor the parametro proveedor
	 * @return true, if successful
	 * @throws BusinessException the business exception
	 */
	private boolean validaParametro(ParametrosProveedorBean parametroProveedor) throws BusinessException {
		boolean sw = true;
		if (PlataformaMensajeriaUtil.isEmpty(parametroProveedor.getResultadoValor())) {
			addActionErrorSession(this.getText("plataforma.proveedorMisim.parametro.add.resultadoValor.error"));
			sw = false;
		}
		if (PlataformaMensajeriaUtil.isEmptyNumber(parametroProveedor.getIdProveedor().intValue())) {
			addActionErrorSession(this.getText("plataforma.proveedorMisim.parametro.add.idProveedor.error"));
			sw = false;
		}
		if (servicioParametroProveedorMisim.existeParametroProveedorMisim(parametroProveedor)) {
			addActionErrorSession(this.getText("plataforma.proveedorMisim.parametro.add.existe"));
			sw = false;
		}
		return sw;
	}
	
	/**
	 * Delete parametro proveedor misim.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	public String deleteParametroProveedorMisim() throws BaseException {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (idParametrosProveedor == null) {
			addActionErrorSession(this.getText("plataforma.proveedorMisim.parametroProveedor.delete.error"));
		} else {
				deleteParametroProveedorMisim(Long.valueOf(idParametrosProveedor));
			addActionMessageSession(this.getText("plataforma.proveedorMisim.parametroProveedor.delete.ok"));
		}
		return SUCCESS;
	}
	
	/**
	 * Delete parametros proveedor misim selected.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	public String deleteParametrosProveedorMisimSelected() throws BaseException {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (checkDelListParametrosProveedorMisim == null) {
			addActionErrorSession(this.getText("plataforma.proveedorMisim.parametroProveedor.deleteSelected.error"));

		} else {
			
			for (String parProv : checkDelListParametrosProveedorMisim) {
				
				deleteParametroProveedorMisim(Long.valueOf(parProv));

			}
			addActionMessageSession(this.getText("plataforma.proveedorMisim.parametroProveedor.deleteSelected.ok"));

		}
		return SUCCESS;
	}
	
	/**
	 * Delete parametro proveedor misim.
	 *
	 * @param idParametrosProveedor the id parametros proveedor
	 * @throws BusinessException the business exception
	 */
	private void deleteParametroProveedorMisim(Long idParametrosProveedor) throws BusinessException {
		String accion = properties.getProperty("log.ACCION_ELIMINAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ELIMINAR", null));
		String source = properties.getProperty("log.SOURCE_PROVEEDORES_MISIM", null);
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ELIMINAR_PARAMETRO", null);
		
		ParametrosProveedorBean parametrosProveedor = new ParametrosProveedorBean();
		parametrosProveedor.setIdParametrosProveedor(idParametrosProveedor);
		servicioParametroProveedorMisim.deleteParametroProveedorMisim(parametrosProveedor, source, accion, accionId, descripcion);
	}
	

	/**
	 * Obtener check del list.
	 *
	 * @return check del list
	 */
	public String[] getCheckDelList() {
		return checkDelList;
	}

	/**
	 * Modificar check del list.
	 *
	 * @param checkDelList new check del list
	 */
	public void setCheckDelList(String[] checkDelList) {
		this.checkDelList = checkDelList;
	}

	/**
	 * Obtener check password.
	 *
	 * @return check password
	 */
	public String getCheckPassword() {
		return checkPassword;
	}

	/**
	 * Modificar check password.
	 *
	 * @param checkPassword new check password
	 */
	public void setCheckPassword(String checkPassword) {
		this.checkPassword = checkPassword;
	}

	/**
	 * Obtener readonly.
	 *
	 * @return readonly
	 */
	public String getReadonly() {
		return readonly;
	}

	/**
	 * Modificar readonly.
	 *
	 * @param readonly new readonly
	 */
	public void setReadonly(String readonly) {
		this.readonly = readonly;
	}

	/**
	 * Obtener combo productos proveedor misim.
	 *
	 * @return combo productos proveedor misim
	 */
	public List<KeyValueObject> getComboProductosProveedorMisim() {
		return comboProductosProveedorMisim;
	}

	/**
	 * Modificar combo productos proveedor misim.
	 *
	 * @param comboProductosProveedorMisim new combo productos proveedor misim
	 */
	public void setComboProductosProveedorMisim(
			List<KeyValueObject> comboProductosProveedorMisim) {
		this.comboProductosProveedorMisim = comboProductosProveedorMisim;
	}

	/**
	 * Obtener lista proveedores misim.
	 *
	 * @return lista proveedores misim
	 */
	public List<ProveedorMisimBean> getListaProveedoresMisim() {
		return listaProveedoresMisim;
	}

	/**
	 * Modificar lista proveedores misim.
	 *
	 * @param listaProveedoresMisim new lista proveedores misim
	 */
	public void setListaProveedoresMisim(
			List<ProveedorMisimBean> listaProveedoresMisim) {
		this.listaProveedoresMisim = listaProveedoresMisim;
	}

	/**
	 * Obtener servicio proveedor misim.
	 *
	 * @return servicio proveedor misim
	 */
	public ServicioProveedorMisim getServicioProveedorMisim() {
		return servicioProveedorMisim;
	}

	/**
	 * Modificar servicio proveedor misim.
	 *
	 * @param servicioProveedorMisim new servicio proveedor misim
	 */
	public void setServicioProveedorMisim(
			ServicioProveedorMisim servicioProveedorMisim) {
		this.servicioProveedorMisim = servicioProveedorMisim;
	}

	/**
	 * Obtener proveedor misim.
	 *
	 * @return proveedor misim
	 */
	public ProveedorMisimBean getProveedorMisim() {
		return proveedorMisim;
	}

	/**
	 * Modificar proveedor misim.
	 *
	 * @param proveedorMisim new proveedor misim
	 */
	public void setProveedorMisim(ProveedorMisimBean proveedorMisim) {
		this.proveedorMisim = proveedorMisim;
	}

	/**
	 * Obtener id proveedor.
	 *
	 * @return id proveedor
	 */
	public String getIdProveedor() {
		return idProveedor;
	}

	/**
	 * Modificar id proveedor.
	 *
	 * @param idProveedor new id proveedor
	 */
	public void setIdProveedor(String idProveedor) {
		this.idProveedor = idProveedor;
	}

	/**
	 * Obtener combo endpoints proveedor misim.
	 *
	 * @return combo endpoints proveedor misim
	 */
	public List<KeyValueObject> getComboEndpointsProveedorMisim() {
		return comboEndpointsProveedorMisim;
	}

	/**
	 * Modificar combo endpoints proveedor misim.
	 *
	 * @param comboEndpointsProveedorMisim new combo endpoints proveedor misim
	 */
	public void setComboEndpointsProveedorMisim(
			List<KeyValueObject> comboEndpointsProveedorMisim) {
		this.comboEndpointsProveedorMisim = comboEndpointsProveedorMisim;
	}

	/**
	 * Obtener combo transformaciones proveedor misim.
	 *
	 * @return combo transformaciones proveedor misim
	 */
	public List<KeyValueObject> getComboTransformacionesProveedorMisim() {
		return comboTransformacionesProveedorMisim;
	}

	/**
	 * Modificar combo transformaciones proveedor misim.
	 *
	 * @param comboTransformacionesProveedorMisim new combo transformaciones proveedor misim
	 */
	public void setComboTransformacionesProveedorMisim(
			List<KeyValueObject> comboTransformacionesProveedorMisim) {
		this.comboTransformacionesProveedorMisim = comboTransformacionesProveedorMisim;
	}

	/**
	 * Obtener combo comunicaciones endpoints.
	 *
	 * @return combo comunicaciones endpoints
	 */
	public List<KeyValueObject> getComboComunicacionesEndpoints() {
		return comboComunicacionesEndpoints;
	}

	/**
	 * Modificar combo comunicaciones endpoints.
	 *
	 * @param comboComunicacionesEndpoints new combo comunicaciones endpoints
	 */
	public void setComboComunicacionesEndpoints(
			List<KeyValueObject> comboComunicacionesEndpoints) {
		this.comboComunicacionesEndpoints = comboComunicacionesEndpoints;
	}

	/**
	 * Obtener json.
	 *
	 * @return json
	 */
	public String getJson() {
		return json;
	}

	/**
	 * Modificar json.
	 *
	 * @param json new json
	 */
	public void setJson(String json) {
		this.json = json;
	}

	/**
	 * Obtener lista parametros proveedor misim.
	 *
	 * @return lista parametros proveedor misim
	 */
	public List<ParametrosProveedorBean> getListaParametrosProveedorMisim() {
		return listaParametrosProveedorMisim;
	}

	/**
	 * Modificar lista parametros proveedor misim.
	 *
	 * @param listaParametrosProveedorMisim new lista parametros proveedor misim
	 */
	public void setListaParametrosProveedorMisim(
			List<ParametrosProveedorBean> listaParametrosProveedorMisim) {
		this.listaParametrosProveedorMisim = listaParametrosProveedorMisim;
	}

	/**
	 * Obtener servicio parametro proveedor misim.
	 *
	 * @return servicio parametro proveedor misim
	 */
	public ServicioParametroProveedorMisim getServicioParametroProveedorMisim() {
		return servicioParametroProveedorMisim;
	}

	/**
	 * Modificar servicio parametro proveedor misim.
	 *
	 * @param servicioParametroProveedorMisim new servicio parametro proveedor misim
	 */
	public void setServicioParametroProveedorMisim(
			ServicioParametroProveedorMisim servicioParametroProveedorMisim) {
		this.servicioParametroProveedorMisim = servicioParametroProveedorMisim;
	}

	/**
	 * Obtener parametro proveedor.
	 *
	 * @return parametro proveedor
	 */
	public ParametrosProveedorBean getParametroProveedor() {
		return parametroProveedor;
	}

	/**
	 * Modificar parametro proveedor.
	 *
	 * @param parametroProveedor new parametro proveedor
	 */
	public void setParametroProveedor(ParametrosProveedorBean parametroProveedor) {
		this.parametroProveedor = parametroProveedor;
	}

	/**
	 * Obtener check del list parametros proveedor misim.
	 *
	 * @return check del list parametros proveedor misim
	 */
	public String[] getCheckDelListParametrosProveedorMisim() {
		return checkDelListParametrosProveedorMisim;
	}

	/**
	 * Modificar check del list parametros proveedor misim.
	 *
	 * @param checkDelListParametrosProveedorMisim new check del list parametros proveedor misim
	 */
	public void setCheckDelListParametrosProveedorMisim(
			String[] checkDelListParametrosProveedorMisim) {
		this.checkDelListParametrosProveedorMisim = checkDelListParametrosProveedorMisim;
	}

	/**
	 * Obtener id parametros proveedor.
	 *
	 * @return id parametros proveedor
	 */
	public String getIdParametrosProveedor() {
		return idParametrosProveedor;
	}

	/**
	 * Modificar id parametros proveedor.
	 *
	 * @param idParametrosProveedor new id parametros proveedor
	 */
	public void setIdParametrosProveedor(String idParametrosProveedor) {
		this.idParametrosProveedor = idParametrosProveedor;
	}


	/**
	 * Obtener recovery.
	 *
	 * @return recovery
	 */
	public String getRecovery() {
		return recovery;
	}


	/**
	 * Modificar recovery.
	 *
	 * @param recovery new recovery
	 */
	public void setRecovery(String recovery) {
		this.recovery = recovery;
	}
	
	
}
