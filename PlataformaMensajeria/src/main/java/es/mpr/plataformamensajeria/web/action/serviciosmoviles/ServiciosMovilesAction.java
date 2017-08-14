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
//import es.mpr.plataformamensajeria.servicios.ifaces.ServicioUsuarioServicioMovil;
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

	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(ServiciosMovilesAction.class);
	
	@Resource(name = "plataformaMensajeriaProperties")
	private PlataformaMensajeriaProperties properties;
	
	@Resource(name = "servicioServicioMovilImpl")
	private ServicioServicioMovil servicioServicioMovil;
	
	@Resource(name = "servicioUsuarioImpl")
	private ServicioUsuario servicioUsuario;
	
	@Resource(name = "plataformaMensajeriaProperties")
	private PlataformaMensajeriaProperties props;

	private static final String Usuario = "1";

	public List<ServicioMovilBean> listaServiciosMoviles = null;
	private List<UsuariosServiciosMovilesBean> listaUsuariosServiciosMoviles = null;
	private List<UsuariosPushBean> listaUsuariosServicioMovil;
	
	List<KeyValueObject> comboTiposServicios = new ArrayList<KeyValueObject>();
	
	private String[] checkDelList;
	private String[] checkDelListServiciosMovilesUsuarios;
	
	private OrganismoBean organismo;
	private ServicioMovilBean servicioMovil;
	private ServidoresOrganismosBean servidorOrganismo;
	private UsuariosServiciosMovilesBean usuarioServicioMovil;
	private UsuarioBean usuarioBean;
	private AplicacionBean aplicacion;
	private DetalleServicioMovilBean detalleServicioMovil;
	private DetalleAplicacionBean detalleAplicacion;
	
	private String idPlanificacion;
	private String idServicio;
	private String idServidor;
	private String idProveedorSMS;
	private String idReceptorSMS;
	private String idServidorPush;
	private String idUsuario;
	private String idOrganismo;
	private String idServicioOrganismo;
	private String idServicioMovil;
	private String servidorOrganismoId;
	private String usuarioServicioMovilId;
	
	private String resultCount;
	private String checkPassword;
	
	UsuariosPushBean usuario;
	
	private static final String SEPARADOR_OPCIONES_VALUES = "#";

	private static final String SEPARADOR_OPCIONES = "&&";
	
	public String newSearch() throws BaseException {
		return SUCCESS;
	}
	

	public String search() throws BaseException {

		if (getRequest().getSession().getAttribute("infoUser") == null) return "noUser";
		int page = getPage("tableId"); // Pagina a mostrar
		String order = getOrder("tableId"); // Ordenar de modo ascendente o descendente
		String columnSort = getColumnSort("tableId"); // Columna usada para ordenar

		if (servicioMovil != null)
			if (servicioMovil.getNombre() != null && servicioMovil.getNombre().length() <= 0)
				servicioMovil.setNombre(null);

		int inicio = (page - 1) * Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20"));
		boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
		PaginatedList<ServicioMovilBean> result = servicioServicioMovil.getServiciosMoviles(inicio, 
				(export) ? -1 : Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20")), order, 
				columnSort, servicioMovil);
		Integer totalSize = result.getTotalList();

		listaServiciosMoviles = result.getPageList();

		// Atributos de request
		getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_TOTALSIZE", null), totalSize);
		if (!export) {
			getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_PAGESIZE", null), 
					Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20")));
		} else {
			getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_PAGESIZE", null), totalSize);
		}

		if (listaServiciosMoviles != null && !listaServiciosMoviles.isEmpty()) {
			for (int indice = 0; indice < listaServiciosMoviles.size(); indice++) {

				ServicioMovilBean servicioMovil = new ServicioMovilBean();
				servicioMovil = listaServiciosMoviles.get(indice);
				servicioMovil.setNombre(StringEscapeUtils.escapeHtml(servicioMovil.getNombre()));
				servicioMovil.setDescripcion(StringEscapeUtils.escapeHtml(servicioMovil.getDescripcion()));
			}
		}

		return SUCCESS;
	}

	public String execute() throws BaseException {

		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";

		int page = getPage("tableId"); // Pagina a mostrar
		String order = getOrder("tableId"); // Ordenar de modo ascendente o descendente
		String columnSort = getColumnSort("tableId"); // Columna usada para ordenar

		if (servicioMovil != null)
			if (servicioMovil.getNombre() != null && servicioMovil.getNombre().length() <= 0)
				servicioMovil.setNombre(null);

		int inicio = (page - 1) * Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20"));
		boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
		PaginatedList<ServicioMovilBean> result = servicioServicioMovil.getServiciosMoviles(inicio, 
				(export) ? -1 : Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20")), order, columnSort, servicioMovil);
		Integer totalSize = result.getTotalList();

		listaServiciosMoviles = result.getPageList();

		getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_TOTALSIZE", null), totalSize);

		if (!export) {
			getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_PAGESIZE", null), 
					Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20")));
		} else {
			getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_PAGESIZE", null), totalSize);
		}
		if (listaServiciosMoviles != null && !listaServiciosMoviles.isEmpty()) {
			for (int indice = 0; indice < listaServiciosMoviles.size(); indice++) {

				ServicioMovilBean servicioMovil = listaServiciosMoviles.get(indice);
				servicioMovil.setNombre(StringEscapeUtils.escapeHtml(servicioMovil.getNombre()));
				servicioMovil.setDescripcion(StringEscapeUtils.escapeHtml(servicioMovil.getDescripcion()));
			}
		}

		return SUCCESS;
	}
	
	public String create() throws BaseException {
		
		String accion = properties.getProperty("log.ACCION_INSERTAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_INSERTAR", null));
		String source = properties.getProperty("log.SOURCE_SERVICIOSMOVILES", null);
		
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";

		if (servicioMovil != null) {
	    				
			if (servicioMovil.getIsEstado() != null && servicioMovil.getIsEstado().indexOf("'activo'")!=-1) {
				servicioMovil.setEstado(new Long(1));
			} else {
				servicioMovil.setEstado(new Long(0));
			}

			if (servicioMovil.getIsIndSuscripcion() != null && servicioMovil.getIsIndSuscripcion().indexOf("'activo'")!=-1) {
				servicioMovil.setIndSuscripcion(new Long(1));
			} else {
				servicioMovil.setIndSuscripcion(new Long(0));
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

	public String update() throws BaseException {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		String accion = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
		String source = properties.getProperty("log.SOURCE_SERVICIOSMOVILES", null);

		ServicioMovilBean servicioMovilBBDD = null;
		if (servicioMovil == null) {

			addActionErrorSession(this.getText("plataforma.servicioMovil.update.error"));

		} else {
			logger.info("[ServicioMovilAction - IdservicioMovil] valor == " + servicioMovil.getServicioMovilId());
			if (servicioMovil.getServicioMovilId() == null) {
				if (idServicioMovil != null) {
					servicioMovil.setServicioMovilId(new Long(idServicioMovil));
					servicioMovilBBDD = servicioServicioMovil.loadServicioMovil(servicioMovil);
				} else {
					String idServicioMovil = (String) request.getAttribute("idServicioMovil");
					logger.info("[ServicioMovilAction - request.getAttribute('idServicioMovil)' == " + idServicioMovil);
					if (idServicioMovil != null) {
						aplicacion.setId(new Long(idServicioMovil));
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

	public String load() throws BaseException {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		int page = getPage("tableId"); // Pagina a mostrar
		String order = getOrder("tableId"); // Ordenar de modo ascendente o descendente
		String columnSort = getColumnSort("tableId"); // Columna usada para ordenar

		if (servicioMovil != null)
			if (servicioMovil.getNombre() != null && servicioMovil.getNombre().length() <= 0)
				servicioMovil.setNombre(null);

		int inicio = (page - 1) * Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20"));
		PaginatedList<ServicioMovilBean> result = servicioServicioMovil.getServiciosMoviles(inicio, 
				Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20")), order, columnSort, servicioMovil);

		listaServiciosMoviles = result.getPageList();
		
		if (idServicioMovil == null)
			throw new BusinessException("EL idServicioMovil recibido es nulo");
		try {
			servicioMovil = new ServicioMovilBean();
			servicioMovil.setServicioMovilId(new Long(idServicioMovil));
			servicioMovil = servicioServicioMovil.loadServicioMovil(servicioMovil);
			listaUsuariosServicioMovil = loadUsuariosServiciosMoviles();
			
			Integer totalSize = 0;
			if (listaUsuariosServicioMovil != null){
				totalSize = listaUsuariosServicioMovil.size();
			}
			
			getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_PAGESIZE", null), 
					Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20")));
			getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_TOTALSIZE", null), totalSize);
			
			return SUCCESS;
		} catch (NumberFormatException e) {
			String mensg = this.getText("errors.action.servicioMovil.loadServicioMovil", new String[] { servicioMovil.getServicioMovilId().toString() });
			throw new BusinessException(mensg);
		} catch (BusinessException e) {
			String mensg = this.getText("errors.action.servicioMovil.loadServicioMovil", new String[] { aplicacion.getAplicacionId().toString() });
			throw new BusinessException(mensg);
		}

	}

	public String delete() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ELIMINAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ELIMINAR", null));
		String source = properties.getProperty("log.SOURCE_SERVICIOSMOVILES", null);
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (idServicioMovil == null) {
			addActionErrorSession(this.getText("plataforma.servicioMovil.delete.error"));

		} else {
			servicioMovil = new ServicioMovilBean();
			servicioMovil.setServicioMovilId(new Long(idServicioMovil));
			servicioServicioMovil.deleteServicioMovil(servicioMovil, accion, accionId, source);
			addActionMessageSession(this.getText("plataforma.servicioMovil.delete.ok"));
		}
		return SUCCESS;

	}

	public String deleteSelected() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ELIMINAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ELIMINAR", null));
		String source = properties.getProperty("log.SOURCE_SERVICIOSMOVILES", null);
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (checkDelList == null) {
			addActionErrorSession(this.getText("plataforma.servicioMovil.deleteSelected.error"));

		} else {
			for (String idServicioMovil : checkDelList) {
				servicioMovil = new ServicioMovilBean();
				servicioMovil.setServicioMovilId(new Long(idServicioMovil));
				servicioServicioMovil.deleteServicioMovil(servicioMovil, accion, accionId, source);
			}
			addActionMessageSession(this.getText("plataforma.servicioMovil.deleteSelected.ok"));

		}
		return SUCCESS;
	}
	
	public String deleteImagen() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ELIMINARIMAGEN", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ELIMINAR", null));
		String source = properties.getProperty("log.SOURCE_SERVICIOSMOVILES", null);
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (idServicioMovil == null) {
			addActionErrorSession(this.getText("plataforma.servicioMovil.delete.error"));

		} else {
			servicioMovil = new ServicioMovilBean();
			servicioMovil.setServicioMovilId(new Long(idServicioMovil));
			servicioServicioMovil.deleteImagenServicioMovil(servicioMovil, accion, accionId, source);
			addActionMessageSession(this.getText("plataforma.servicioMovil.imagen.delete.ok"));
		}
		return SUCCESS;

	}
	
	private List<UsuariosPushBean> loadUsuariosServiciosMoviles() {
   		List<UsuariosPushBean> lista = null;
   		if(idServicioMovil!=null&&idServicioMovil.length()>0){
   			try {
   				lista = servicioUsuario.getUsuariosByServicioMovilId(new Long(idServicioMovil));
				} catch (NumberFormatException | BusinessException e) {
					logger.error("[ServiciosMovilesAction - loadUsuariosServiciosMoviles] " ,e);
				}
   		}else if(servicioMovil!=null&&servicioMovil.getServicioMovilId()!=null){
   			try {
   				lista = servicioUsuario.getUsuariosByServicioMovilId(servicioMovil.getServicioMovilId());
				}  catch (NumberFormatException | BusinessException e) {
					logger.error("[ServiciosMovilesAction - loadUsuariosServiciosMoviles] " ,e);
				}
   		}
			return lista;
	}	

	@Override
	public void prepare() throws Exception {
		comboTiposServicios = getTipoServicio();
		
	}
	
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
		} 
		catch (Exception e){
			LOG.error("[CifradoServiceImpl] - getCertificados:" + e);
			throw new BusinessException(e,"errors.decode.getCertificados");	
		}
	}
	
	/**
	 * Método que resuelve el lugar donde tiene que volver
	 */
	public String getVolver() {
		String volver="buscarServiciosMoviles.action";
		if(!PlataformaMensajeriaUtil.isEmpty(from)&&!PlataformaMensajeriaUtil.isEmpty(idFrom)){
			volver=from+"?"+var+"="+idFrom;
		}
		return volver;
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

	public String getIdServicioOrganismo() {
		return idServicioOrganismo;
	}

	public void setIdServicioOrganismo(String idServicioOrganismo) {
		this.idServicioOrganismo = idServicioOrganismo;
	}

	public String getServidorOrganismoId() {
		return servidorOrganismoId;
	}

	public void setServidorOrganismoId(String servidorOrganismoId) {
		this.servidorOrganismoId = servidorOrganismoId;
	}

	public ServidoresOrganismosBean getServidorOrganismo() {
		return servidorOrganismo;
	}

	public void setServidorOrganismo(ServidoresOrganismosBean servidorOrganismo) {
		this.servidorOrganismo = servidorOrganismo;
	}
	
	public String[] getCheckDelListServiciosMovilesUsuarios() {
		return checkDelListServiciosMovilesUsuarios;
	}

	public void setCheckDelListServiciosMovilesUsuarios(String[] checkDelListServiciosMovilesUsuarios) {
		this.checkDelListServiciosMovilesUsuarios = checkDelListServiciosMovilesUsuarios;
	}
	

	public ServicioServicioMovil getServicioServicioMovil() {
		return servicioServicioMovil;
	}

	public void setServicioServicioMovil(ServicioServicioMovil servicioServicioMovil) {
		this.servicioServicioMovil = servicioServicioMovil;
	}

	public String getIdServicioMovil() {
		return idServicioMovil;
	}

	public void setIdServicioMovil(String idServicioMovil) {
		this.idServicioMovil = idServicioMovil;
	}

	public ServicioMovilBean getServicioMovil() {
		return servicioMovil;
	}

	public void setServicioMovil(ServicioMovilBean servicioMovil) {
		this.servicioMovil = servicioMovil;
	}
	
	public String[] getCheckDelList() {
		return checkDelList;
	}

	public void setCheckDelList(String[] checkDelList) {
		this.checkDelList = checkDelList;
	}

	public ServicioUsuario getServicioUsuario() {
		return servicioUsuario;
	}

	public void setServicioUsuario(ServicioUsuario servicioUsuario) {
		this.servicioUsuario = servicioUsuario;
	}

	public String getUsuarioServicioMovilId() {
		return usuarioServicioMovilId;
	}

	public void setUsuarioServicioMovilId(String usuarioServicioMovilId) {
		this.usuarioServicioMovilId = usuarioServicioMovilId;
	}

	public UsuariosServiciosMovilesBean getUsuarioServicioMovil() {
		return usuarioServicioMovil;
	}

	public void setUsuarioServicioMovil(
			UsuariosServiciosMovilesBean usuarioServicioMovil) {
		this.usuarioServicioMovil = usuarioServicioMovil;
	}

	public void setUsuario(UsuariosPushBean usuario) {
		this.usuario = usuario;
	}

	public void setListaUsuariosServicioMovil(
			List<UsuariosPushBean> listaUsuariosServicioMovil) {
		this.listaUsuariosServicioMovil = listaUsuariosServicioMovil;
	}
	
	public String getCheckPassword() {
		return checkPassword;
	}

	public void setCheckPassword(String checkPassword) {
		this.checkPassword = checkPassword;
	}
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

	public void setIdServidor(String idServidor) {
		this.idServidor = idServidor;
	}

	public String getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(String idServicio) {
		this.idServicio = idServicio;
	}

	public List<ServicioMovilBean> getListaServiciosMoviles() {
		return listaServiciosMoviles;
	}

	public void setListaServiciosMoviles(List<ServicioMovilBean> listaServiciosMoviles) {
		this.listaServiciosMoviles = listaServiciosMoviles;
	}

	public OrganismoBean getOrganismo() {
		return organismo;
	}

	public void setOrganismo(OrganismoBean organismo) {
		this.organismo = organismo;
	}

	public AplicacionBean getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(AplicacionBean aplicacion) {
		this.aplicacion = aplicacion;
	}
	public UsuarioBean getUsuarioBean() {
		return usuarioBean;
	}

	public void setUsuarioBean(UsuarioBean usuarioBean) {
		this.usuarioBean = usuarioBean;
	}

	public String getIdPlanificacion() {
		return idPlanificacion;
	}

	public void setIdPlanificacion(String idPlanificacion) {
		this.idPlanificacion = idPlanificacion;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public List<UsuariosServiciosMovilesBean> getListaUsuariosServiciosMoviles() {
		return listaUsuariosServiciosMoviles;
	}

	public void setListaUsuariosServiciosMoviles(
			List<UsuariosServiciosMovilesBean> listaUsuariosServiciosMoviles) {
		this.listaUsuariosServiciosMoviles = listaUsuariosServiciosMoviles;
	}

	public static String getUsuario() {
		return Usuario;
	}
	
	public DetalleServicioMovilBean getDetalleServicioMovil() {
		return detalleServicioMovil;
	}
	public void setDetalleServicioMovil(DetalleServicioMovilBean detalleServicioMovil) {
		this.detalleServicioMovil = detalleServicioMovil;
	}
	public List<UsuariosPushBean> getListaUsuariosServicioMovil() {
		return listaUsuariosServicioMovil;
	}
	public void setListaServiciosAplicacion(
			List<UsuariosPushBean> listaUsuariosServicioMovil) {
		this.listaUsuariosServicioMovil = listaUsuariosServicioMovil;
	}
	
	public void setServidor(AplicacionBean aplicacion) {
		this.aplicacion = aplicacion;
	}

	public String getIdServidor() {
		return idServidor;
	}

	public String getResultCount() {
		return resultCount;
	}

	public void setResultCount(String resultCount) {
		this.resultCount = resultCount;
	}
	
	public DetalleAplicacionBean getDetalleAplicacion() {
		return detalleAplicacion;
	}

	public void setDetalleAplicacion(DetalleAplicacionBean detalleAplicacion) {
		this.detalleAplicacion = detalleAplicacion;
	}


	/**
	 * @return the comboTiposServicios
	 */
	public List<KeyValueObject> getComboTiposServicios() {
		return comboTiposServicios;
	}


	/**
	 * @param comboTiposServicios the comboTiposServicios to set
	 */
	public void setComboTiposServicios(List<KeyValueObject> comboTiposServicios) {
		this.comboTiposServicios = comboTiposServicios;
	}
	
}
