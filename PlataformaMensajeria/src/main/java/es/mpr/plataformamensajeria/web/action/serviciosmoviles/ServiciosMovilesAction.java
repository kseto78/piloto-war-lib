package es.mpr.plataformamensajeria.web.action.serviciosmoviles;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.map.j2ee.exceptions.BaseException;
import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;
import com.map.j2ee.util.KeyValueObject;
import com.opensymphony.xwork2.Preparable;

import es.mpr.plataformamensajeria.beans.AplicacionBean;
import es.mpr.plataformamensajeria.beans.DetalleAplicacionBean;
import es.mpr.plataformamensajeria.beans.DetalleServicioMovilBean;
import es.mpr.plataformamensajeria.beans.OrganismoBean;
import es.mpr.plataformamensajeria.beans.ServicioMovilBean;
import es.mpr.plataformamensajeria.beans.ServidoresOrganismosBean;
import es.mpr.plataformamensajeria.beans.UsuarioBean;
import es.mpr.plataformamensajeria.beans.UsuariosPushBean;
import es.mpr.plataformamensajeria.beans.UsuariosServiciosMovilesBean;
import es.mpr.plataformamensajeria.impl.PlataformaPaginationAction;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServicioMovil;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioUsuario;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaProperties;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;
import es.mpr.plataformamensajeria.util.Utiles;

/**
 * <p>
 * Clase Action de Struts2 para la gesti&oacute;n de los servicios moviles.
 * 
 * <p>
 * Proporciona m&eacute;todos para la creaci&oacute;n, modificaci&oacute;n, borrado y listado de los Servicios Moviles
 * 
 * @author Everis
 * 
 */

@Controller("serviciosMovilesAction")
@Scope("prototype")
public class ServiciosMovilesAction extends PlataformaPaginationAction implements ServletRequestAware, Preparable {

	protected static final String ERRORSDOTACTION = "errors.action.servicioMovil.loadServicioMovil";

	protected static final String INFOUSER = "infoUser";

	protected static final String LOGDOTACCIONID_REF = "log.ACCIONID_ELIMINAR";

	protected static final String NOUSER = "noUser";

	protected static final String PLATAFORMADOTSE = "plataforma.servicioMovil.delete.error";

	protected static final String ACTIVO = "'activo'";

	protected static final String GENERALESDOTREQ = "generales.REQUEST_ATTRIBUTE_TOTALSIZE";

	protected static final String PLATAFORMAMENSA = "plataformaMensajeriaProperties";

	protected static final String GENERALESDOTPAG = "generales.PAGESIZE";

	protected static final String LOGDOTSOURCE_SE = "log.SOURCE_SERVICIOSMOVILES";

	protected static final String SERVICIOSMOVILE = "[ServiciosMovilesAction - loadUsuariosServiciosMoviles] ";

	protected static final String LOGDOTACCION_EL = "log.ACCION_ELIMINAR";

	protected static final String TABLEID = "tableId";

	protected static final String GENERALESDOTREQ0 = "generales.REQUEST_ATTRIBUTE_PAGESIZE";

	protected static final String R_CONST_REF = "20";

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**  logger. */
	private static Logger logger = Logger.getLogger(ServiciosMovilesAction.class);
	
	/**  properties. */
	@Resource(name = PLATAFORMAMENSA)
	private PlataformaMensajeriaProperties properties;
	
	/**  servicio servicio movil. */
	@Resource(name = "servicioServicioMovilImpl")
	private ServicioServicioMovil servicioServicioMovil;
	
	/**  servicio usuario. */
	@Resource(name = "servicioUsuarioImpl")
	private ServicioUsuario servicioUsuario;
	
	/**  props. */
	@Resource(name = PLATAFORMAMENSA)
	private PlataformaMensajeriaProperties props;

	/** Constante Usuario. */
	private static final String TXTUSUARIO = "1";

	/**  lista servicios moviles. */
	public List<ServicioMovilBean> listaServiciosMoviles = null;
	
	/**  lista usuarios servicios moviles. */
	private List<UsuariosServiciosMovilesBean> listaUsuariosServiciosMoviles = null;
	
	/**  lista usuarios servicio movil. */
	private List<UsuariosPushBean> listaUsuariosServicioMovil;
	
	/**  combo tipos servicios. */
	List<KeyValueObject> comboTiposServicios = new ArrayList<>();
	
	/**  check del list. */
	private String[] checkDelList;
	
	/**  check del list servicios moviles usuarios. */
	private String[] checkDelListServiciosMovilesUsuarios;
	
	/**  organismo. */
	private OrganismoBean organismo;
	
	/**  servicio movil. */
	private ServicioMovilBean servicioMovil;
	
	/**  servidor organismo. */
	private ServidoresOrganismosBean servidorOrganismo;
	
	/**  usuario servicio movil. */
	private UsuariosServiciosMovilesBean usuarioServicioMovil;
	
	/**  usuario bean. */
	private UsuarioBean usuarioBean;
	
	/**  aplicacion. */
	private AplicacionBean aplicacion;
	
	/**  detalle servicio movil. */
	private DetalleServicioMovilBean detalleServicioMovil;
	
	/**  detalle aplicacion. */
	private DetalleAplicacionBean detalleAplicacion;
	
	/**  id planificacion. */
	private String idPlanificacion;
	
	/**  id servicio. */
	private String idServicio;
	
	/**  id servidor. */
	private String idServidor;
	
	/**  id proveedor SMS. */
	private String idProveedorSMS;
	
	/**  id receptor SMS. */
	private String idReceptorSMS;
	
	/**  id servidor push. */
	private String idServidorPush;
	
	/**  id usuario. */
	private String idUsuario;
	
	/**  id organismo. */
	private String idOrganismo;
	
	/**  id servicio organismo. */
	private String idServicioOrganismo;
	
	/**  id servicio movil. */
	private String idServicioMovil;
	
	/**  servidor organismo id. */
	private String servidorOrganismoId;
	
	/**  usuario servicio movil id. */
	private String usuarioServicioMovilId;
	
	/**  result count. */
	private String resultCount;
	
	/**  check password. */
	private String checkPassword;
	
	/**  usuario. */
	UsuariosPushBean usuario;
	
	/** Constante SEPARADOR_OPCIONES_VALUES. */
	private static final String SEPARADOR_OPCIONES_VALUES = "#";

	/** Constante SEPARADOR_OPCIONES. */
	private static final String SEPARADOR_OPCIONES = "&&";
	
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

		if (getRequest().getSession().getAttribute(INFOUSER) == null) {
			return NOUSER;
		}
		int page = getPage(TABLEID); 
		// Pagina a mostrar
		String order = getOrder(TABLEID); 
		// Ordenar de modo ascendente o descendente
		String columnSort = getColumnSort(TABLEID); 
		// Columna usada para ordenar

		if (servicioMovil != null && servicioMovil.getNombre() != null && servicioMovil.getNombre().isEmpty()) {
			servicioMovil.setNombre(null);
		}

		int inicio = (page - 1) * Integer.parseInt(properties.getProperty(GENERALESDOTPAG, R_CONST_REF));
		boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
		PaginatedList<ServicioMovilBean> result = servicioServicioMovil.getServiciosMoviles(inicio, 
				export ? -1 : Integer.parseInt(properties.getProperty(GENERALESDOTPAG, R_CONST_REF)), order, 
				columnSort, servicioMovil);
		Integer totalSize = result.getTotalList();

		listaServiciosMoviles = result.getPageList();

		// Atributos de request
		getRequest().setAttribute(properties.getProperty(GENERALESDOTREQ, null), totalSize);
		if (!export) {
			getRequest().setAttribute(properties.getProperty(GENERALESDOTREQ0, null), 
					Integer.parseInt(properties.getProperty(GENERALESDOTPAG, R_CONST_REF)));
		} else {
			getRequest().setAttribute(properties.getProperty(GENERALESDOTREQ0, null), totalSize);
		}

		if (listaServiciosMoviles != null && !listaServiciosMoviles.isEmpty()) {
			for (int indice = 0, s = listaServiciosMoviles.size(); indice < s; indice++) {

				ServicioMovilBean servicioMovil = null;
				servicioMovil = listaServiciosMoviles.get(indice);
				servicioMovil.setNombre(StringEscapeUtils.escapeHtml(servicioMovil.getNombre()));
				servicioMovil.setDescripcion(StringEscapeUtils.escapeHtml(servicioMovil.getDescripcion()));
			}
		}

		return SUCCESS;
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute() throws BaseException {

		if (getRequest().getSession().getAttribute(INFOUSER) == null) {
			return NOUSER;
		}

		int page = getPage(TABLEID); 
		// Pagina a mostrar
		String order = getOrder(TABLEID); 
		// Ordenar de modo ascendente o descendente
		String columnSort = getColumnSort(TABLEID); 
		// Columna usada para ordenar

		if (servicioMovil != null && servicioMovil.getNombre() != null && servicioMovil.getNombre().isEmpty()) {
			servicioMovil.setNombre(null);
		}

		int inicio = (page - 1) * Integer.parseInt(properties.getProperty(GENERALESDOTPAG, R_CONST_REF));
		boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
		PaginatedList<ServicioMovilBean> result = servicioServicioMovil.getServiciosMoviles(inicio, 
				export ? -1 : Integer.parseInt(properties.getProperty(GENERALESDOTPAG, R_CONST_REF)), order, columnSort, servicioMovil);
		Integer totalSize = result.getTotalList();

		listaServiciosMoviles = result.getPageList();

		getRequest().setAttribute(properties.getProperty(GENERALESDOTREQ, null), totalSize);

		if (!export) {
			getRequest().setAttribute(properties.getProperty(GENERALESDOTREQ0, null), 
					Integer.parseInt(properties.getProperty(GENERALESDOTPAG, R_CONST_REF)));
		} else {
			getRequest().setAttribute(properties.getProperty(GENERALESDOTREQ0, null), totalSize);
		}
		if (listaServiciosMoviles != null && !listaServiciosMoviles.isEmpty()) {
			for (int indice = 0, s = listaServiciosMoviles.size(); indice < s; indice++) {

				ServicioMovilBean servicioMovil = listaServiciosMoviles.get(indice);
				servicioMovil.setNombre(StringEscapeUtils.escapeHtml(servicioMovil.getNombre()));
				servicioMovil.setDescripcion(StringEscapeUtils.escapeHtml(servicioMovil.getDescripcion()));
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
		String source = properties.getProperty(LOGDOTSOURCE_SE, null);
		
		if (getRequest().getSession().getAttribute(INFOUSER) == null) {
			return NOUSER;
		}

		if (servicioMovil != null) {
	    				
			if (servicioMovil.getIsEstado() != null && servicioMovil.getIsEstado().indexOf(ACTIVO)!=-1) {
				servicioMovil.setEstado(Integer.valueOf(1));
			} else {
				servicioMovil.setEstado(Integer.valueOf(0));
			}

			if (servicioMovil.getIsIndSuscripcion() != null && servicioMovil.getIsIndSuscripcion().indexOf(ACTIVO)!=-1) {
				servicioMovil.setIndSuscripcion(Integer.valueOf(1));
			} else {
				servicioMovil.setIndSuscripcion(Integer.valueOf(0));
			}
			
			if (!validaObligatorios(servicioMovil, true)) {
				addActionErrorSession(this.getText("plataforma.servicioMovil.create.error"));
				return ERROR;
			}
			Long idServicioMovil = servicioServicioMovil.newServicioMovil(servicioMovil, source, accion, accionId);
			this.idServicioMovil = idServicioMovil.toString();

			addActionMessageSession(this.getText("plataforma.servicioMovil.create.ok"));
		}

		return SUCCESS;

	}

	/**
	 * Valida obligatorios.
	 *
	 * @param aplicacion2 the aplicacion 2
	 * @param isUpdate the is update
	 * @return true, if successful
	 */
	private boolean validaObligatorios(ServicioMovilBean aplicacion2, boolean isUpdate) {
		boolean sw = true;
		if (PlataformaMensajeriaUtil.isEmpty(aplicacion2.getDescripcion())) {
			addActionErrorSession(this.getText("plataforma.servicioMovil.field.descripcion.error"));
			sw = false;
		}
		if (PlataformaMensajeriaUtil.isEmpty(aplicacion2.getNombre())) {
			addActionErrorSession(this.getText("plataforma.servicioMovil.field.nombre.error"));
			sw = false;
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
		if (getRequest().getSession().getAttribute(INFOUSER) == null) {
			return NOUSER;
		}
		String accion = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
		String source = properties.getProperty(LOGDOTSOURCE_SE, null);

		ServicioMovilBean servicioMovilBBDD = null;
		if (servicioMovil == null) {

			addActionErrorSession(this.getText("plataforma.servicioMovil.update.error"));

		} else {
			logger.info("[ServicioMovilAction - IdservicioMovil] valor == " + servicioMovil.getServicioMovilId());
			if (servicioMovil.getServicioMovilId() == null) {
				if (idServicioMovil != null) {
					servicioMovil.setServicioMovilId(Long.valueOf(idServicioMovil));
					servicioMovilBBDD = servicioServicioMovil.loadServicioMovil(servicioMovil);
				} else {
					String idServicioMovil = (String) request.getAttribute("idServicioMovil");
					logger.info("[ServicioMovilAction - request.getAttribute('idServicioMovil)' == " + idServicioMovil);
					if (idServicioMovil != null) {
						aplicacion.setId(Long.valueOf(idServicioMovil));
						servicioMovilBBDD = servicioServicioMovil.loadServicioMovil(servicioMovil);
					}
				}
				logger.info("[AplicacionAction - Idaplicacion despues de setear idAplicacion] valor == " + aplicacion.getAplicacionId());
			} else {
				servicioMovilBBDD = servicioServicioMovil.loadServicioMovil(servicioMovil);

			}
			if (servicioMovilBBDD != null) {
				servicioMovilBBDD.setUrlServicio(servicioMovil.getUrlServicio());
				servicioMovilBBDD.setNombre(servicioMovil.getNombre());
				servicioMovilBBDD.setDescripcion(servicioMovil.getDescripcion());
				servicioMovilBBDD.setTipo(servicioMovil.getTipo()); 
				servicioMovilBBDD.setEstado(servicioMovil.getEstado());
				servicioMovilBBDD.setNombreContacto(servicioMovil.getNombreContacto());
				servicioMovilBBDD.setTelefonoContacto(servicioMovil.getTelefonoContacto());
				servicioMovilBBDD.setUrlAvisoSuscripcion(servicioMovil.getUrlAvisoSuscripcion());
				servicioMovilBBDD.setIndSuscripcion(servicioMovil.getIndSuscripcion()); 
				servicioMovilBBDD.setEndpointUser(servicioMovil.getEndpointUser());
				servicioMovilBBDD.setEndpointPass(servicioMovil.getEndpointPass());
				
				if(servicioMovil.getIcono()!=null){
				File originalFile = new File(servicioMovil.getIcono());
		        String iconoBase64 = Utiles.FiletoBase64String(originalFile);

		        servicioMovilBBDD.setIcono(iconoBase64);}
		        
				servicioMovil = servicioMovilBBDD;
				if (validaObligatorios(servicioMovilBBDD, true)) {
					servicioServicioMovil.updateServicioMovil(servicioMovilBBDD, source, accion, accionId);
					addActionMessageSession(this.getText("plataforma.servicioMovil.update.ok"));
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
		if (getRequest().getSession().getAttribute(INFOUSER) == null) {
			return NOUSER;
		}
		int page = getPage(TABLEID); 
		// Pagina a mostrar
		String order = getOrder(TABLEID); 
		// Ordenar de modo ascendente o descendente
		String columnSort = getColumnSort(TABLEID); 
		// Columna usada para ordenar

		if (servicioMovil != null && servicioMovil.getNombre() != null && servicioMovil.getNombre().isEmpty()) {
			servicioMovil.setNombre(null);
		}

		int inicio = (page - 1) * Integer.parseInt(properties.getProperty(GENERALESDOTPAG, R_CONST_REF));
		PaginatedList<ServicioMovilBean> result = servicioServicioMovil.getServiciosMoviles(inicio, 
				Integer.parseInt(properties.getProperty(GENERALESDOTPAG, R_CONST_REF)), order, columnSort, servicioMovil);

		listaServiciosMoviles = result.getPageList();
		
		if (idServicioMovil == null) {
			throw new BusinessException("EL idServicioMovil recibido es nulo");
		}
		try {
			servicioMovil = new ServicioMovilBean();
			servicioMovil.setServicioMovilId(Long.valueOf(idServicioMovil));
			servicioMovil = servicioServicioMovil.loadServicioMovil(servicioMovil);
			listaUsuariosServicioMovil = loadUsuariosServiciosMoviles();
			
			Integer totalSize = 0;
			if (listaUsuariosServicioMovil != null){
				totalSize = listaUsuariosServicioMovil.size();
			}
			
			getRequest().setAttribute(properties.getProperty(GENERALESDOTREQ0, null), 
					Integer.parseInt(properties.getProperty(GENERALESDOTPAG, R_CONST_REF)));
			getRequest().setAttribute(properties.getProperty(GENERALESDOTREQ, null), totalSize);
			
			return SUCCESS;
		} catch (NumberFormatException e) {
			logger.error(e.getMessage(), e);
			String mensg = this.getText(ERRORSDOTACTION, new String[] { servicioMovil.getServicioMovilId().toString() });
			throw new BusinessException(mensg);
		} catch (BusinessException e) {
			logger.error(e.getMessage(), e);
			String mensg = this.getText(ERRORSDOTACTION, new String[] { aplicacion.getAplicacionId().toString() });
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
		String accion = properties.getProperty(LOGDOTACCION_EL, null);
		Long accionId = Long.parseLong(properties.getProperty(LOGDOTACCIONID_REF, null));
		String source = properties.getProperty(LOGDOTSOURCE_SE, null);
		if (getRequest().getSession().getAttribute(INFOUSER) == null) {
			return NOUSER;
		}
		if (idServicioMovil == null) {
			addActionErrorSession(this.getText(PLATAFORMADOTSE));

		} else {
			servicioMovil = new ServicioMovilBean();
			servicioMovil.setServicioMovilId(Long.valueOf(idServicioMovil));
			servicioServicioMovil.deleteServicioMovil(servicioMovil, accion, accionId, source);
			addActionMessageSession(this.getText("plataforma.servicioMovil.delete.ok"));
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
		String accion = properties.getProperty(LOGDOTACCION_EL, null);
		Long accionId = Long.parseLong(properties.getProperty(LOGDOTACCIONID_REF, null));
		String source = properties.getProperty(LOGDOTSOURCE_SE, null);
		if (getRequest().getSession().getAttribute(INFOUSER) == null) {
			return NOUSER;
		}
		if (checkDelList == null) {
			addActionErrorSession(this.getText("plataforma.servicioMovil.deleteSelected.error"));

		} else {
			for (String idServicioMovil : checkDelList) {
				servicioMovil = new ServicioMovilBean();
				servicioMovil.setServicioMovilId(Long.valueOf(idServicioMovil));
				servicioServicioMovil.deleteServicioMovil(servicioMovil, accion, accionId, source);
			}
			addActionMessageSession(this.getText("plataforma.servicioMovil.deleteSelected.ok"));

		}
		return SUCCESS;
	}
	
	/**
	 * Delete imagen.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	public String deleteImagen() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ELIMINARIMAGEN", null);
		Long accionId = Long.parseLong(properties.getProperty(LOGDOTACCIONID_REF, null));
		String source = properties.getProperty(LOGDOTSOURCE_SE, null);
		if (getRequest().getSession().getAttribute(INFOUSER) == null) {
			return NOUSER;
		}
		if (idServicioMovil == null) {
			addActionErrorSession(this.getText(PLATAFORMADOTSE));

		} else {
			servicioMovil = new ServicioMovilBean();
			servicioMovil.setServicioMovilId(Long.valueOf(idServicioMovil));
			servicioServicioMovil.deleteImagenServicioMovil(servicioMovil, accion, accionId, source);
			addActionMessageSession(this.getText("plataforma.servicioMovil.imagen.delete.ok"));
		}
		return SUCCESS;

	}
	
	/**
	 * Load usuarios servicios moviles.
	 *
	 * @return the list
	 */
	private List<UsuariosPushBean> loadUsuariosServiciosMoviles() {
   		List<UsuariosPushBean> lista = null;
   		if(idServicioMovil!=null&&!idServicioMovil.isEmpty()){
   			try {
   				lista = servicioUsuario.getUsuariosByServicioMovilId(Long.valueOf(idServicioMovil));
				} catch (NumberFormatException | BusinessException e) {
					logger.error(SERVICIOSMOVILE ,e);
				}
   		}else if(servicioMovil!=null&&servicioMovil.getServicioMovilId()!=null){
   			try {
   				lista = servicioUsuario.getUsuariosByServicioMovilId(servicioMovil.getServicioMovilId());
				}  catch (NumberFormatException | BusinessException e) {
					logger.error(SERVICIOSMOVILE ,e);
				}
   		}
			return lista;
	}	

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.impl.PlataformaPaginationAction#prepare()
	 */
	@Override
	public void prepare() throws Exception {
		comboTiposServicios = getTipoServicio();
		
	}
	
	/**
	 * Obtener tipo servicio.
	 *
	 * @return tipo servicio
	 * @throws BusinessException the business exception
	 */
	public List<KeyValueObject> getTipoServicio() throws BusinessException {
		
		try {
			String options = props.getProperty("generales.serviciosMoviles.opciones", null);
			
			String[] opciones = options.split(SEPARADOR_OPCIONES);
			List<KeyValueObject> result = new ArrayList<>();

			for (String combo : opciones) {
				KeyValueObject option =  new KeyValueObject();
				String [] valores = combo.split(SEPARADOR_OPCIONES_VALUES);
				option.setDescripcion(valores[1]);
				option.setCodigo(valores[0]);
				result.add(option);
			}
			
			return result;
		} catch (Exception e){
			LOG.error("[CifradoServiceImpl] - getCertificados:" + e);
			throw new BusinessException(e,"errors.decode.getCertificados");	
		}
	}
	
	/**
	 * Método que resuelve el lugar donde tiene que volver.
	 *
	 * @return volver
	 */
	public String getVolver() {
		String volver="buscarServiciosMoviles.action";
		if(!PlataformaMensajeriaUtil.isEmpty(from)&&!PlataformaMensajeriaUtil.isEmpty(idFrom)){
			volver=from+"?"+var+"="+idFrom;
		}
		return volver;
	}

	/**
	 * Obtener id servidor push.
	 *
	 * @return id servidor push
	 */
	public String getIdServidorPush() {
		return idServidorPush;
	}

	/**
	 * Modificar id servidor push.
	 *
	 * @param idServidorPush new id servidor push
	 */
	public void setIdServidorPush(String idServidorPush) {
		this.idServidorPush = idServidorPush;
	}

	/**
	 * Obtener id organismo.
	 *
	 * @return id organismo
	 */
	public String getIdOrganismo() {
		return idOrganismo;
	}

	/**
	 * Modificar id organismo.
	 *
	 * @param idOrganismo new id organismo
	 */
	public void setIdOrganismo(String idOrganismo) {
		this.idOrganismo = idOrganismo;
	}

	/**
	 * Obtener id servicio organismo.
	 *
	 * @return id servicio organismo
	 */
	public String getIdServicioOrganismo() {
		return idServicioOrganismo;
	}

	/**
	 * Modificar id servicio organismo.
	 *
	 * @param idServicioOrganismo new id servicio organismo
	 */
	public void setIdServicioOrganismo(String idServicioOrganismo) {
		this.idServicioOrganismo = idServicioOrganismo;
	}

	/**
	 * Obtener servidor organismo id.
	 *
	 * @return servidor organismo id
	 */
	public String getServidorOrganismoId() {
		return servidorOrganismoId;
	}

	/**
	 * Modificar servidor organismo id.
	 *
	 * @param servidorOrganismoId new servidor organismo id
	 */
	public void setServidorOrganismoId(String servidorOrganismoId) {
		this.servidorOrganismoId = servidorOrganismoId;
	}

	/**
	 * Obtener servidor organismo.
	 *
	 * @return servidor organismo
	 */
	public ServidoresOrganismosBean getServidorOrganismo() {
		return servidorOrganismo;
	}

	/**
	 * Modificar servidor organismo.
	 *
	 * @param servidorOrganismo new servidor organismo
	 */
	public void setServidorOrganismo(ServidoresOrganismosBean servidorOrganismo) {
		this.servidorOrganismo = servidorOrganismo;
	}
	
	/**
	 * Obtener check del list servicios moviles usuarios.
	 *
	 * @return check del list servicios moviles usuarios
	 */
	public String[] getCheckDelListServiciosMovilesUsuarios() {
		return checkDelListServiciosMovilesUsuarios;
	}

	/**
	 * Modificar check del list servicios moviles usuarios.
	 *
	 * @param checkDelListServiciosMovilesUsuarios new check del list servicios moviles usuarios
	 */
	public void setCheckDelListServiciosMovilesUsuarios(String[] checkDelListServiciosMovilesUsuarios) {
		this.checkDelListServiciosMovilesUsuarios = checkDelListServiciosMovilesUsuarios;
	}
	

	/**
	 * Obtener servicio servicio movil.
	 *
	 * @return servicio servicio movil
	 */
	public ServicioServicioMovil getServicioServicioMovil() {
		return servicioServicioMovil;
	}

	/**
	 * Modificar servicio servicio movil.
	 *
	 * @param servicioServicioMovil new servicio servicio movil
	 */
	public void setServicioServicioMovil(ServicioServicioMovil servicioServicioMovil) {
		this.servicioServicioMovil = servicioServicioMovil;
	}

	/**
	 * Obtener id servicio movil.
	 *
	 * @return id servicio movil
	 */
	public String getIdServicioMovil() {
		return idServicioMovil;
	}

	/**
	 * Modificar id servicio movil.
	 *
	 * @param idServicioMovil new id servicio movil
	 */
	public void setIdServicioMovil(String idServicioMovil) {
		this.idServicioMovil = idServicioMovil;
	}

	/**
	 * Obtener servicio movil.
	 *
	 * @return servicio movil
	 */
	public ServicioMovilBean getServicioMovil() {
		return servicioMovil;
	}

	/**
	 * Modificar servicio movil.
	 *
	 * @param servicioMovil new servicio movil
	 */
	public void setServicioMovil(ServicioMovilBean servicioMovil) {
		this.servicioMovil = servicioMovil;
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
	 * Obtener servicio usuario.
	 *
	 * @return servicio usuario
	 */
	public ServicioUsuario getServicioUsuario() {
		return servicioUsuario;
	}

	/**
	 * Modificar servicio usuario.
	 *
	 * @param servicioUsuario new servicio usuario
	 */
	public void setServicioUsuario(ServicioUsuario servicioUsuario) {
		this.servicioUsuario = servicioUsuario;
	}

	/**
	 * Obtener usuario servicio movil id.
	 *
	 * @return usuario servicio movil id
	 */
	public String getUsuarioServicioMovilId() {
		return usuarioServicioMovilId;
	}

	/**
	 * Modificar usuario servicio movil id.
	 *
	 * @param usuarioServicioMovilId new usuario servicio movil id
	 */
	public void setUsuarioServicioMovilId(String usuarioServicioMovilId) {
		this.usuarioServicioMovilId = usuarioServicioMovilId;
	}

	/**
	 * Obtener usuario servicio movil.
	 *
	 * @return usuario servicio movil
	 */
	public UsuariosServiciosMovilesBean getUsuarioServicioMovil() {
		return usuarioServicioMovil;
	}

	/**
	 * Modificar usuario servicio movil.
	 *
	 * @param usuarioServicioMovil new usuario servicio movil
	 */
	public void setUsuarioServicioMovil(
			UsuariosServiciosMovilesBean usuarioServicioMovil) {
		this.usuarioServicioMovil = usuarioServicioMovil;
	}

	/**
	 * Modificar usuario.
	 *
	 * @param usuario new usuario
	 */
	public void setUsuario(UsuariosPushBean usuario) {
		this.usuario = usuario;
	}

	/**
	 * Modificar lista usuarios servicio movil.
	 *
	 * @param listaUsuariosServicioMovil new lista usuarios servicio movil
	 */
	public void setListaUsuariosServicioMovil(
			List<UsuariosPushBean> listaUsuariosServicioMovil) {
		this.listaUsuariosServicioMovil = listaUsuariosServicioMovil;
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
	 * Obtener id proveedor SMS.
	 *
	 * @return id proveedor SMS
	 */
	public String getIdProveedorSMS() {
		return idProveedorSMS;
	}

	/**
	 * Modificar id proveedor SMS.
	 *
	 * @param idProveedorSMS new id proveedor SMS
	 */
	public void setIdProveedorSMS(String idProveedorSMS) {
		this.idProveedorSMS = idProveedorSMS;
	}

	/**
	 * Obtener id receptor SMS.
	 *
	 * @return id receptor SMS
	 */
	public String getIdReceptorSMS() {
		return idReceptorSMS;
	}

	/**
	 * Modificar id receptor SMS.
	 *
	 * @param idReceptorSMS new id receptor SMS
	 */
	public void setIdReceptorSMS(String idReceptorSMS) {
		this.idReceptorSMS = idReceptorSMS;
	}

	/**
	 * Modificar id servidor.
	 *
	 * @param idServidor new id servidor
	 */
	public void setIdServidor(String idServidor) {
		this.idServidor = idServidor;
	}

	/**
	 * Obtener id servicio.
	 *
	 * @return id servicio
	 */
	public String getIdServicio() {
		return idServicio;
	}

	/**
	 * Modificar id servicio.
	 *
	 * @param idServicio new id servicio
	 */
	public void setIdServicio(String idServicio) {
		this.idServicio = idServicio;
	}

	/**
	 * Obtener lista servicios moviles.
	 *
	 * @return lista servicios moviles
	 */
	public List<ServicioMovilBean> getListaServiciosMoviles() {
		return listaServiciosMoviles;
	}

	/**
	 * Modificar lista servicios moviles.
	 *
	 * @param listaServiciosMoviles new lista servicios moviles
	 */
	public void setListaServiciosMoviles(List<ServicioMovilBean> listaServiciosMoviles) {
		this.listaServiciosMoviles = listaServiciosMoviles;
	}

	/**
	 * Obtener organismo.
	 *
	 * @return organismo
	 */
	public OrganismoBean getOrganismo() {
		return organismo;
	}

	/**
	 * Modificar organismo.
	 *
	 * @param organismo new organismo
	 */
	public void setOrganismo(OrganismoBean organismo) {
		this.organismo = organismo;
	}

	/**
	 * Obtener aplicacion.
	 *
	 * @return aplicacion
	 */
	public AplicacionBean getAplicacion() {
		return aplicacion;
	}

	/**
	 * Modificar aplicacion.
	 *
	 * @param aplicacion new aplicacion
	 */
	public void setAplicacion(AplicacionBean aplicacion) {
		this.aplicacion = aplicacion;
	}
	
	/**
	 * Obtener usuario bean.
	 *
	 * @return usuario bean
	 */
	public UsuarioBean getUsuarioBean() {
		return usuarioBean;
	}

	/**
	 * Modificar usuario bean.
	 *
	 * @param usuarioBean new usuario bean
	 */
	public void setUsuarioBean(UsuarioBean usuarioBean) {
		this.usuarioBean = usuarioBean;
	}

	/**
	 * Obtener id planificacion.
	 *
	 * @return id planificacion
	 */
	public String getIdPlanificacion() {
		return idPlanificacion;
	}

	/**
	 * Modificar id planificacion.
	 *
	 * @param idPlanificacion new id planificacion
	 */
	public void setIdPlanificacion(String idPlanificacion) {
		this.idPlanificacion = idPlanificacion;
	}

	/**
	 * Obtener id usuario.
	 *
	 * @return id usuario
	 */
	public String getIdUsuario() {
		return idUsuario;
	}

	/**
	 * Modificar id usuario.
	 *
	 * @param idUsuario new id usuario
	 */
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * Obtener lista usuarios servicios moviles.
	 *
	 * @return lista usuarios servicios moviles
	 */
	public List<UsuariosServiciosMovilesBean> getListaUsuariosServiciosMoviles() {
		return listaUsuariosServiciosMoviles;
	}

	/**
	 * Modificar lista usuarios servicios moviles.
	 *
	 * @param listaUsuariosServiciosMoviles new lista usuarios servicios moviles
	 */
	public void setListaUsuariosServiciosMoviles(
			List<UsuariosServiciosMovilesBean> listaUsuariosServiciosMoviles) {
		this.listaUsuariosServiciosMoviles = listaUsuariosServiciosMoviles;
	}

	/**
	 * Obtener usuario.
	 *
	 * @return usuario
	 */
	public static String getUsuario() {
		return TXTUSUARIO;
	}
	
	/**
	 * Obtener detalle servicio movil.
	 *
	 * @return detalle servicio movil
	 */
	public DetalleServicioMovilBean getDetalleServicioMovil() {
		return detalleServicioMovil;
	}
	
	/**
	 * Modificar detalle servicio movil.
	 *
	 * @param detalleServicioMovil new detalle servicio movil
	 */
	public void setDetalleServicioMovil(DetalleServicioMovilBean detalleServicioMovil) {
		this.detalleServicioMovil = detalleServicioMovil;
	}
	
	/**
	 * Obtener lista usuarios servicio movil.
	 *
	 * @return lista usuarios servicio movil
	 */
	public List<UsuariosPushBean> getListaUsuariosServicioMovil() {
		return listaUsuariosServicioMovil;
	}
	
	/**
	 * Modificar lista servicios aplicacion.
	 *
	 * @param listaUsuariosServicioMovil new lista servicios aplicacion
	 */
	public void setListaServiciosAplicacion(
			List<UsuariosPushBean> listaUsuariosServicioMovil) {
		this.listaUsuariosServicioMovil = listaUsuariosServicioMovil;
	}
	
	/**
	 * Modificar servidor.
	 *
	 * @param aplicacion new servidor
	 */
	public void setServidor(AplicacionBean aplicacion) {
		this.aplicacion = aplicacion;
	}

	/**
	 * Obtener id servidor.
	 *
	 * @return id servidor
	 */
	public String getIdServidor() {
		return idServidor;
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
	 * Obtener detalle aplicacion.
	 *
	 * @return detalle aplicacion
	 */
	public DetalleAplicacionBean getDetalleAplicacion() {
		return detalleAplicacion;
	}

	/**
	 * Modificar detalle aplicacion.
	 *
	 * @param detalleAplicacion new detalle aplicacion
	 */
	public void setDetalleAplicacion(DetalleAplicacionBean detalleAplicacion) {
		this.detalleAplicacion = detalleAplicacion;
	}


	/**
	 * Obtener combo tipos servicios.
	 *
	 * @return the comboTiposServicios
	 */
	public List<KeyValueObject> getComboTiposServicios() {
		return comboTiposServicios;
	}


	/**
	 * Modificar combo tipos servicios.
	 *
	 * @param comboTiposServicios the comboTiposServicios to set
	 */
	public void setComboTiposServicios(List<KeyValueObject> comboTiposServicios) {
		this.comboTiposServicios = comboTiposServicios;
	}
	
}
