package es.mpr.plataformamensajeria.web.action.contactos;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import com.map.j2ee.exceptions.BaseException;
import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;
import com.map.j2ee.util.KeyValueObject;
import com.opensymphony.xwork2.Preparable;

import es.mpr.plataformamensajeria.beans.AplicacionBean;
import es.mpr.plataformamensajeria.beans.ContactoBean;
import es.mpr.plataformamensajeria.beans.ServicioBean;
import es.mpr.plataformamensajeria.impl.PlataformaPaginationAction;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioAplicacion;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioContacto;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServicio;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaProperties;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

/**
 * <p>
 * Clase Action de Struts2 para la gesti&oacute;n de los Contactos.
 * 
 * <p>
 * Proporciona m&eacute;todos para la creaci&oacute;n, modificaci&oacute;n,
 * borrado y listado de los contactos
 * 
 * 
 * 
 */
@Controller("contactosAction")
@Scope("prototype")
public class ContactosAction extends PlataformaPaginationAction implements ServletRequestAware, Preparable {

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** logger. */
	private static Logger logger = Logger.getLogger(ContactosAction.class);

	/** servicio aplicacion. */
	@Resource(name = "servicioAplicacionImpl")
	private ServicioAplicacion servicioAplicacion;

	/** servicio contacto. */
	@Resource(name = "servicioContactosImpl")
	private ServicioContacto servicioContacto;

	/** properties. */
	@Resource(name = "plataformaMensajeriaProperties")
	private PlataformaMensajeriaProperties properties;

	/**  combo servicios. */
	List<KeyValueObject> comboServicios = new ArrayList<>();
	
	/** combo aplicaciones. */
	List<KeyValueObject> comboAplicaciones = new ArrayList<>();

	/** check del list. */
	private String[] checkDelList;

	/** lista Contactos. */
	public List<ContactoBean> listaContactos = null;

	/** Contacto que se se usará para el new y para el edit. */
	private ContactoBean contacto = new ContactoBean();
	
	/** id del contacto para el new y el edit */
	private String idContacto;
	
	private String servicioId;

	private String aplicacionId;
	
	/** Constante LOG_ACCION_ACTUALIZAR. */
	private static final String LOG_ACCION_ACTUALIZAR = "log.ACCION_ACTUALIZAR";

	/** Constante NO_USER. */
	private static final String NO_USER = "noUser";
	
	/**  servicio servicio. */
	@Resource(name = "servicioServicioImpl")
	private ServicioServicio servicioServicio;
	
	/** Constante INFO_USER. */
	private static final String INFO_USER = "infoUser";
	
	/** Constante TABLE_ID. */
	private static final String TABLE_ID = "tableId";


	/**
	 * New search.
	 *
	 * @return the string
	 * @throws BaseException
	 *             the base exception
	 */
	public String newSearch() throws BaseException {
		return SUCCESS;
	}

	public String search() throws BaseException {

		if (getRequest().getSession().getAttribute(INFO_USER) == null)
			return NO_USER;
		int page = getPage(TABLE_ID); // Pagina a mostrar
		String order = getOrder(TABLE_ID); // Ordenar de modo ascendente o
											// descendente
		String columnSort = getColumnSort(TABLE_ID); // Columna usada para
														// ordenar
		int inicio = (page - 1) * Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20"));
		boolean export = PlataformaMensajeriaUtil.isExport(getRequest());

		PaginatedList<ContactoBean> result;
		
		result = servicioContacto.getContactos(inicio,(export) ? -1 : Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20")), order,
				columnSort, contacto);

		listaContactos = result.getPageList();
		Integer totalSize = listaContactos.size();

		// Atributos de request
		getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_TOTALSIZE", null), totalSize);

		if (!export) {
			getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_PAGESIZE", null),
					Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20")));
		} else {
			getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_PAGESIZE", null), totalSize);
		}

		getRequest().getSession().setAttribute("contacto", contacto);
		
		return SUCCESS;
	}



	public String newContacto() throws BaseException {

		/**
		 * TOOD METODO DONDE SE VA A EJECUTAR AL REALIZAR LA un nuevo USUARIO. En el
		 * cuadro de busqueda.
		 * 
		 */
		String accion = properties.getProperty("log.ACCION_INSERTAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_INSERTAR", null));
		String source = properties.getProperty("log.SOURCE_CONTACTOS", null);
		try {
			SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return NO_USER;
		}
		if (contacto != null) {
			if(!validaObligatoriosContacto(contacto)){
				return ERROR;
			}			
			Long contactoId = servicioContacto.newContacto(contacto, source, accion, accionId);
			this.idContacto = contactoId.toString();

			addActionMessageSession(this.getText("plataforma.contacto.create.ok"));
		} else {
			addActionErrorSession(this.getText("plataforma.contacto.create.error"));
		}
		return SUCCESS;
	
	}

	/** user name to load. */
	private String userNameToLoad;



	/**
	 * Obtener user name to load.
	 *
	 * @return user name to load
	 */
	public String getUserNameToLoad() {
		return userNameToLoad;
	}

	/**
	 * Modificar user name to load.
	 *
	 * @param userNameToLoad
	 *            new user name to load
	 */
	public void setUserNameToLoad(String userNameToLoad) {
		this.userNameToLoad = userNameToLoad;
	}
	

	/**
	 * Update.
	 *
	 * @return the string
	 * @throws BaseException
	 *             the base exception
	 */

	public String update() throws BaseException {
		String accion = properties.getProperty(ContactosAction.LOG_ACCION_ACTUALIZAR, null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
		String source = properties.getProperty("log.SOURCE_CONTACTOS", null);
		try {
			SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return NO_USER;
		}
		ContactoBean contactoBBDD = null;
		if (contacto == null) {
			addActionErrorSession(this.getText("plataforma.contacto.update.error"));
		} else {
			logger.info("[ContactosAction - IdContacto] valor == " + contacto.getContactoId());			


			contactoBBDD = servicioContacto.loadContacto(contacto);
								
			contactoBBDD.setOrganismo(contacto.getOrganismo());
			contactoBBDD.setAplicacionid(contacto.getAplicacionid());
			contactoBBDD.setServicioid(contacto.getServicioid());					
			contactoBBDD.setNombre(contacto.getNombre());
			contactoBBDD.setApellidos(contacto.getApellidos());
			contactoBBDD.setEmail(contacto.getEmail());
			contactoBBDD.setTelefono(contacto.getTelefono());
			
			if(validaObligatoriosContacto(contactoBBDD)){
				servicioContacto.updateContacto(contactoBBDD, source, accion, accionId);
				addActionMessageSession(this.getText("plataforma.contacto.update.ok"));
			}		
		
			logger.info("[ContactoAction - IdContacto despues de setear idContacto] valor == "
						+ contacto.getContactoId());
		
		}
		return SUCCESS;

	}

	private boolean validaObligatoriosContacto(ContactoBean contactoBBDD) {
		boolean sw = true;		
		if (PlataformaMensajeriaUtil.isEmpty(contactoBBDD.getNombre())) {
			addActionErrorSession(this.getText("plataforma.contactos.field.nombre.error"));
			sw = false;
		}
		
		return sw;
	}

	/**
	 * Load.
	 *
	 * @return the string
	 * @throws BaseException
	 *             the base exception
	 */

	public String load() throws BaseException {

		try {
			SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			logger.error("ContactosAction - load:" + e);
			return NO_USER;
		}
		if (idContacto == null)
			throw new BusinessException("EL idContacto recibido es nulo");
		try {
			contacto = new ContactoBean();
			Long cntcId = Long.valueOf(idContacto);
			contacto.setContactoId(cntcId);
			contacto = servicioContacto.loadContacto(contacto);
			
			return SUCCESS;
		} catch (NumberFormatException | BusinessException e) {
			String mensg = this.getText("plataforma.contacto.update.error", new String[] { contacto
					.getContactoId().toString() });
			logger.error("ContactosAction - load:" + e);
			throw new BusinessException(mensg);
		}

	}


	/**
	 * Delete.
	 *
	 * @return the string
	 * @throws BaseException
	 *             the base exception
	 */

	public String delete() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ELIMINAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ELIMINAR", null));
		String source = properties.getProperty("log.SOURCE_CONTACTOS", null);
		
		if (getRequest().getSession().getAttribute(ContactosAction.INFO_USER) == null)
			return NO_USER;
		if (idContacto == null) {
			addActionErrorSession(this.getText("plataforma.ejecucionjob.delete.error"));			
			
		} else {
			contacto = new ContactoBean();
			contacto.setContactoId(Long.valueOf(idContacto));
			
			servicioContacto.deleteContacto(contacto.getContactoId(), source, accion, accionId);
			addActionMessageSession(this.getText("plataforma.contacto.delete.ok"));
		}
		return SUCCESS;

	}

	/**
	 * Delete selected.
	 *
	 * @return the string
	 * @throws BaseException
	 *             the base exception
	 */

	public String deleteSelected() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ELIMINAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ELIMINAR", null));
		String source = properties.getProperty("log.SOURCE_CONTACTOS", null);
				
		
		if (getRequest().getSession().getAttribute(INFO_USER) == null)
			return "NO_USER";
		if (checkDelList == null) {
			addActionErrorSession(this.getText("plataforma.contactos.deleteselected.error"));
			
		} else {
			for (String contactoId : checkDelList) {								
				servicioContacto.deleteContacto(Long.valueOf(contactoId), source, accion, accionId);
			}
			addActionMessageSession(this.getText("plataforma.contactos.deleteselected.ok"));
			
		}
		return SUCCESS;
	}


	/**
	 * Obtener combo aplicaciones.
	 *
	 * @return combo aplicaciones
	 */

	public List<KeyValueObject> getComboAplicaciones() {
		List<KeyValueObject> result = new ArrayList<>();
		KeyValueObject option;
		ArrayList<AplicacionBean> keys = null;
		try {
			keys = (ArrayList<AplicacionBean>) servicioAplicacion.getAplicaciones();
		} catch (BusinessException e) {
			logger.error("ContactosAction - getComboAplicaciones:" + e);
		}

		if (keys != null && !keys.isEmpty())
			for (AplicacionBean key : keys) {

				option = new KeyValueObject();
				option.setCodigo(key.getAplicacionId().toString());
				option.setDescripcion(key.getNombre());
				result.add(option);
			}
		return result;
	}

	/**
	 * Comprueba empty.
	 *
	 * @param value
	 *            the value
	 * @return true, si es empty
	 */
	public boolean isEmpty(String value) {
		if (value == null || (value != null && value.equals(""))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Método que resuelve el lugar donde tiene que volver.
	 *
	 * @return volver
	 */
	public String getVolver() {
		String volver = "listarContactos.action";
		if (!PlataformaMensajeriaUtil.isEmpty(from) && !PlataformaMensajeriaUtil.isEmpty(idFrom)) {
			volver = from + "?" + var + "=" + idFrom;
		}
		return volver;
	}


	/**
	 * Obtener servicio aplicacion.
	 *
	 * @return servicio aplicacion
	 */
	public ServicioAplicacion getServicioAplicacion() {
		return servicioAplicacion;
	}

	/**
	 * Modificar servicio aplicacion.
	 *
	 * @param servicioAplicacion
	 *            new servicio aplicacion
	 */
	public void setServicioAplicacion(ServicioAplicacion servicioAplicacion) {
		this.servicioAplicacion = servicioAplicacion;
	}


	/**
	 * Obtener Contacto.
	 *
	 * @return contacto
	 */
	public ContactoBean getContacto() {
		return contacto;
	}

	/**
	 * Modificar Contacto.
	 *
	 * @param Contacto
	 *            new Contacto
	 */
	public void setContacto(ContactoBean contacto) {
		this.contacto = contacto;
	}



	/**
	 * Modificar combo aplicaciones.
	 *
	 * @param comboAplicaciones
	 *            new combo aplicaciones
	 */
	public void setComboAplicaciones(List<KeyValueObject> comboAplicaciones) {
		this.comboAplicaciones = comboAplicaciones;
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
	 * @param checkDelList
	 *            new check del list
	 */
	public void setCheckDelList(String[] checkDelList) {
		this.checkDelList = checkDelList;
	}
		
	public String getIdContacto() {
		return idContacto;
	}

	public void setIdContacto(String idContacto) {
		this.idContacto = idContacto;
	}
	/**
	 * Obtener combo servicios.
	 *
	 * @return combo servicios
	 * @throws BusinessException the business exception
	 */
	
	public List<KeyValueObject> getComboServicios() throws BusinessException {
		List<KeyValueObject> result = new ArrayList<>();
		KeyValueObject option;
		ArrayList<ServicioBean> keys;
		String rolUsuario = PlataformaMensajeriaUtil.getRolFromSession(request);
		Integer idUsuario = PlataformaMensajeriaUtil.getIdUsuarioFromSession(request);
		if (contacto != null && contacto.getAplicacionid() != null) {
			keys = (ArrayList<ServicioBean>) servicioServicio.getServiciosByAplicacionId(contacto
					.getAplicacionid().intValue());
		} else {
			keys = (ArrayList<ServicioBean>) servicioServicio.getServicios(rolUsuario, idUsuario);
		}
		if (keys != null && !keys.isEmpty()) {
			for (ServicioBean key : keys) {

				option = new KeyValueObject();
				option.setCodigo(key.getServicioId().toString());
				option.setDescripcion(key.getNombre());
				result.add(option);
			}
		}
		return result;
	}
	/**
	 * Obtener servicio servicio.
	 *
	 * @return servicio servicio
	 */
	public ServicioServicio getServicioServicio() {
		return servicioServicio;
	}

	/**
	 * Modificar servicio servicio.
	 *
	 * @param servicioServicio new servicio servicio
	 */
	public void setServicioServicio(ServicioServicio servicioServicio) {
		this.servicioServicio = servicioServicio;
	}
	
	/**
	 * Modificar combo servicios.
	 *
	 * @param comboServicios new combo servicios
	 */
	public void setComboServicios(List<KeyValueObject> comboServicios) {
		this.comboServicios = new ArrayList<KeyValueObject>(comboServicios);
	}
	
	public String getServicioId() {
		return servicioId;
	}

	public void setServicioId(String servicioId) {
		this.servicioId = servicioId;
	}

	public String getAplicacionId() {
		return aplicacionId;
	}

	public void setAplicacionId(String aplicacinId) {
		this.aplicacionId = aplicacinId;
	}
}
