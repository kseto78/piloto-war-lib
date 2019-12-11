package es.mpr.plataformamensajeria.web.action.usuarios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
import com.map.j2ee.security.perm.model.User060VO;
import com.map.j2ee.util.KeyValueObject;
import com.opensymphony.xwork2.Preparable;

import es.mpr.plataformamensajeria.beans.AplicacionBean;
import es.mpr.plataformamensajeria.beans.ContactoBean;
import es.mpr.plataformamensajeria.beans.UsuarioAplicacionBean;
import es.mpr.plataformamensajeria.beans.UsuarioBean;
import es.mpr.plataformamensajeria.impl.PlataformaPaginationAction;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioAplicacion;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioContacto;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioUsuario;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioUsuarioAplicacion;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaProperties;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;
import es.mpr.template.web.action.admin.UsuariosForm;

/**
 * <p>
 * Clase Action de Struts2 para la gesti&oacute;n de los Usuarios.
 * 
 * <p>
 * Proporciona m&eacute;todos para la creaci&oacute;n, modificaci&oacute;n,
 * borrado y listado de los Usuarios
 * 
 * @author i-nercya
 * 
 */
@Controller("usuarioAction")
@Scope("prototype")
public class UsuarioAction extends PlataformaPaginationAction implements ServletRequestAware, Preparable {

	protected static final String INFOUSER = "infoUser";

	protected static final String LOGDOTACCION_DE = "log.ACCION_DESCRIPCION_DESASIGNAR_APLICACION";

	protected static final String LOGDOTSOURCE_US = "log.SOURCE_USUARIOS";

	protected static final String PLATAFORMADOTUS = "plataforma.usuario.add.contacto";

	protected static final String LOGDOTACCION_AC = "log.ACCION_ACTUALIZAR";

	protected static final String GENERALESDOTPAG = "generales.PAGESIZE";

	protected static final String R_CONST_REF = ",";

	protected static final String R_CONST_0 = "1";

	protected static final String R_CONST_1 = "2";

	protected static final String R_CONST_2 = "20";

	protected static final String LOGDOTACCIONID_REF = "log.ACCIONID_ELIMINAR";

	protected static final String NOUSER = "noUser";

	protected static final String PLATAFORMADOTUS0 = "plataforma.usuario.add.usuarioaplicacion.AEATGISS".trim();

	protected static final String PLATAFORMADOTUS1 = "plataforma.usuario.create.usuarioexistente";

	protected static final String LOGDOTACCION_EL = "log.ACCION_ELIMINAR";

	protected static final String TABLEID = "tableId";

	protected static final String GENERALESDOTREQ = "generales.REQUEST_ATTRIBUTE_PAGESIZE";

	protected static final String LOGDOTACCIONID_0 = "log.ACCIONID_ACTUALIZAR";

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**  logger. */
	private static Logger logger = Logger.getLogger(UsuarioAction.class);

	/**  servicio aplicacion. */
	@Resource(name = "servicioAplicacionImpl")
	private ServicioAplicacion servicioAplicacion;

	/**  servicio usuario. */
	@Resource(name = "servicioUsuarioImpl")
	private ServicioUsuario servicioUsuario;

	/**  servicio usuario aplicacion. */
	@Resource(name = "servicioUsuarioAplicacionImpl")
	private ServicioUsuarioAplicacion servicioUsuarioAplicacion;
	
	@Resource(name = "servicioContactosImpl")
	private ServicioContacto servicioContacto;

	/**  properties. */
	@Resource(name = "plataformaMensajeriaProperties")
	private PlataformaMensajeriaProperties properties;
	
	/**  new activo. */
	private String newActivo;

	/**  usuario. */
	private UsuarioBean usuario;
	
	/**  contacto. */
	private ContactoBean contacto;
	
	/**  usuario aplicacion. */
	private UsuarioAplicacionBean usuarioAplicacion;

	/**  lista usuarios. */
	public List<UsuarioBean> listaUsuarios = null;
	
	/**  lista usuario aplicaciones. */
	List<UsuarioAplicacionBean> listaUsuarioAplicaciones = new ArrayList<>();

	/**  combo roles. */
	List<KeyValueObject> comboRoles = new ArrayList<>();
	
	/**  combo aplicaciones. */
	List<KeyValueObject> comboAplicaciones = new ArrayList<>();
	
	/**  combo modos. */
	List<KeyValueObject> comboModos = new ArrayList<>();
	
	/**  combo aplicaciones no asignadas. */
	List<KeyValueObject> comboAplicacionesNoAsignadas = new ArrayList<>();

	/**  check del list. */
	private String[] checkDelList;

	/**  id usuario. */
	private String idUsuario;
	
	/**  result count. */
	private String resultCount;

	/**  id aplicacion usuario. */
	private String idAplicacionUsuario;
	
	/**  usuario aplicacion id. */
	private String usuarioAplicacionId;

	/**  form. */
	private UsuariosForm form;
	// Para realizar el populate
	
	/**  user 060 VO. */
	private User060VO user060VO;

	/**  user name to load. */
	private String userNameToLoad;

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
		// Ordenar de modo ascendente o
											// descendente
		String columnSort = getColumnSort(TABLEID); 
		// Columna usada para
														// ordenar

		if (usuario != null && usuario.getNombre() != null && usuario.getNombre().isEmpty()) {
			usuario.setNombre(null);
		}

		int inicio = (page - 1) * Integer.parseInt(properties.getProperty(GENERALESDOTPAG, R_CONST_2));
		boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
		PaginatedList<UsuarioBean> result = servicioUsuario.getUsuarios(inicio,
				export ? -1 : Integer.parseInt(properties.getProperty(GENERALESDOTPAG, R_CONST_2)), order,
				columnSort, usuario);
		Integer totalSize = result.getTotalList();

		listaUsuarios = result.getPageList();

		// Atributos de request
		getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_TOTALSIZE", null), totalSize);

		if (!export) {
			getRequest().setAttribute(properties.getProperty(GENERALESDOTREQ, null),
					Integer.parseInt(properties.getProperty(GENERALESDOTPAG, R_CONST_2)));
		} else {
			getRequest().setAttribute(properties.getProperty(GENERALESDOTREQ, null), totalSize);
		}

		if (listaUsuarios != null && !listaUsuarios.isEmpty()) {
			for (int indice = 0, s = listaUsuarios.size(); indice < s; indice++) {

				UsuarioBean usuario = listaUsuarios.get(indice);
				usuario.setNombre(StringEscapeUtils.escapeHtml(usuario.getNombre()));
				usuario.setLogin(StringEscapeUtils.escapeHtml(usuario.getLogin()));
				usuario.setEmail(StringEscapeUtils.escapeHtml(usuario.getEmail()));
			}
		}

		return SUCCESS;
	}

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
	 * @param userNameToLoad new user name to load
	 */
	public void setUserNameToLoad(String userNameToLoad) {
		this.userNameToLoad = userNameToLoad;
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
		String source = properties.getProperty(LOGDOTSOURCE_US, null);
		if (getRequest().getSession().getAttribute(INFOUSER) == null) {
			return NOUSER;
		}
		if (usuario != null) {
			if (newActivo != null && "true".equals(newActivo)) {
				usuario.setActivo(true);
			} else {
				usuario.setActivo(false);
			}
			if (validUsuario(usuario) && !existeUsuario(usuario.getLogin())) {
				
				if(usuario.getRolId() == 2){
					contacto = new ContactoBean();
					contacto.setNombre(usuario.getNombre());
					contacto.setOrganismo(usuario.getOrganismo());
					contacto.setTelefono(usuario.getTelefono());
					contacto.setEmail(usuario.getEmail());
					
					Long idContacto = servicioContacto.newContacto(contacto, source, accion, accionId);
					usuario.setIdcontacto(idContacto);
					addActionMessageSession(this.getText(PLATAFORMADOTUS));
				}				
				
				Integer id = servicioUsuario.newUsuario(usuario, source, accion, accionId);
				this.idUsuario = id.toString();
				addActionMessageSession(this.getText("plataforma.usuario.create.ok"));
			} else {
				return ERROR;
			}
		} else {
			addActionErrorSession(this.getText("plataforma.usuario.create.error"));
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * Comprueba si existe el usuario. Si existe, muestra un mensaje de error
	 *
	 * @param loginUsuario the login usuario
	 * @return true, if successful
	 * @throws BusinessException the business exception
	 */
	
	private boolean existeUsuario(String loginUsuario) throws BusinessException {
		boolean sw = servicioUsuario.existeUsuario(loginUsuario);
		if (sw) {
			addActionErrorSession(this.getText(PLATAFORMADOTUS1));
			usuario.setNombre(null);
			usuario.setEmail(null);
			usuario.setLogin(null);
			usuario.setActivo(null);
		}
		return sw;
	}

	/**
	 * Comprueba si existe el usuario. Si existe, muestra un mensaje de error
	 *
	 * @param idUsuario the id usuario
	 * @param loginUsuario the login usuario
	 * @return true, if successful
	 * @throws BusinessException the business exception
	 */
	
	
	private boolean existeUsuarioEdicion(Integer idUsuario, String loginUsuario) throws BusinessException {
		boolean sw = servicioUsuario.existeUsuarioEdicion(idUsuario, loginUsuario);
		if (sw) {
			addFieldErrorSession(this.getText(PLATAFORMADOTUS1));
		}
		return sw;
	}

	/**
	 * Valid usuario.
	 *
	 * @param usuario the usuario
	 * @return true, if successful
	 */
	////MIGRADO
	private boolean validUsuario(UsuarioBean usuario) {
		boolean sw = true;
		if (usuario != null && PlataformaMensajeriaUtil.isEmpty(usuario.getLogin())) {
			addFieldErrorSession(this.getText("plataforma.usuario.field.login"));
			sw = false;
		}
		if (usuario != null && PlataformaMensajeriaUtil.isEmpty(usuario.getNombre())) {
			addFieldErrorSession(this.getText("plataforma.usuario.field.nombre"));
			sw = false;
		}
		if (usuario != null && !PlataformaMensajeriaUtil.isEmpty(usuario.getEmail())
				&& !PlataformaMensajeriaUtil.validateEmail(usuario.getEmail())) {
			addFieldErrorSession(this.getText("plataforma.usuario.field.email.format"));
			sw = false;
		}
		if (usuario != null && PlataformaMensajeriaUtil.isEmptyNumber(usuario.getRolId())) {
			addFieldErrorSession(this.getText("plataforma.usuario.field.rolId"));
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
	///MIGRADO
	public String update() throws BaseException {
		String accion = properties.getProperty(LOGDOTACCION_AC, null);
		Long accionId = Long.parseLong(properties.getProperty(LOGDOTACCIONID_0, null));
		String source = properties.getProperty(LOGDOTSOURCE_US, null);
		
		if (getRequest().getSession().getAttribute(INFOUSER) == null) {
			return NOUSER;
		}
		UsuarioBean usuarioBBDD = null;
		if (usuario == null) {
			addActionErrorSession(this.getText("plataforma.usuario.update.error"));
		} else {
			if (usuario.getUsuarioId() == null) {
				if (idUsuario != null) {
					usuario.setUsuarioId(Long.valueOf(idUsuario));
					usuarioBBDD = servicioUsuario.loadUsuario(usuario);
				} else {
					String idServidor = (String) request.getAttribute("idUsuario");
					if (idServidor != null) {
						usuario.setId(Integer.valueOf(idUsuario));
						usuarioBBDD = servicioUsuario.loadUsuario(usuario);
					}
				}
			} else {
				usuarioBBDD = servicioUsuario.loadUsuario(usuario);

			}
			if (usuarioBBDD != null) {
				usuarioBBDD.setUsuarioId(usuario.getUsuarioId());
				usuarioBBDD.setNombre(usuario.getNombre());
				usuarioBBDD.setLogin(usuario.getLogin());
				usuarioBBDD.setActivo(usuario.getActivo());
				usuarioBBDD.setEmail(usuario.getEmail());
				usuarioBBDD.setRolId(usuario.getRolId());
				usuarioBBDD.setTelefono(usuario.getTelefono());
				usuarioBBDD.setOrganismo(usuario.getOrganismo());
				Integer rolSession = PlataformaMensajeriaUtil.getRolUsuarioByUsername(usuario.getLogin(), servicioUsuario);

				if (validUsuario(usuarioBBDD)
						&& !existeUsuarioEdicion(usuarioBBDD.getUsuarioId().intValue(), usuarioBBDD.getLogin()) && validAplicaciones(usuarioBBDD)) {
					if(usuarioBBDD.getRolId() == 2){
						contacto = new ContactoBean();
						if(usuarioBBDD.getIdcontacto() != null){
								
							contacto.setContactoId(usuarioBBDD.getIdcontacto());
							contacto = servicioContacto.loadContacto(contacto);
							contacto.setNombre(usuarioBBDD.getNombre());
							contacto.setEmail(usuarioBBDD.getEmail());
							contacto.setTelefono(usuarioBBDD.getTelefono());
							contacto.setOrganismo(usuarioBBDD.getOrganismo());
							servicioContacto.updateContacto(contacto, source, accion, accionId);
							addActionMessageSession(this.getText("plataforma.usuario.update.contacto"));
						} else{
								
							contacto.setNombre(usuario.getNombre());
							contacto.setOrganismo(usuario.getOrganismo());
							contacto.setTelefono(usuario.getTelefono());
							contacto.setEmail(usuario.getEmail());
							
							Long idContacto = servicioContacto.newContacto(contacto, source, accion, accionId);
							usuarioBBDD.setIdcontacto(idContacto);
							addActionMessageSession(this.getText(PLATAFORMADOTUS));
						}
						
					}
					
					servicioUsuario.updateUsuario(usuarioBBDD, source, accion, accionId);
					addActionMessageSession(this.getText("plataforma.usuario.update.ok"));

					// Si el usuario modificado ha cambiado su rol y no
					// corresponde con el usuario logueado, se cambia el rol en
					// sesion
					if (rolSession != null && rolSession != usuario.getRolId()
							&& usuario.getLogin().equals(PlataformaMensajeriaUtil.getUsuarioLogueado().getUsername())) {
						PlataformaMensajeriaUtil.changeSessionRol(usuario.getRolId(), request.getSession());
					}
				}
			}
		}
		return SUCCESS;

	}

	private boolean validAplicaciones(UsuarioBean usuarioBBDD) throws BusinessException {
		String aplicacionAEATGISS = properties.getProperty(PLATAFORMADOTUS0, null);
		List<String> aplAeatGiss = new ArrayList<>(Arrays.asList(aplicacionAEATGISS.split(R_CONST_REF)));
		
		if("".equals(usuarioBBDD.getOrganismo()) && usuarioBBDD.getRolId() == 2){
			List<UsuarioAplicacionBean> listaUsuariosAplicaciones = servicioUsuarioAplicacion.getUsuarioAplicacionesByUsuarioId(Integer.valueOf(idUsuario));
			for(UsuarioAplicacionBean aplicacion : listaUsuariosAplicaciones){
					
					for(String aplicacionAeatGiss : aplAeatGiss){
						if(aplicacionAeatGiss.equals(String.valueOf(aplicacion.getAplicacionId()))){
							addActionErrorSession(this.getText("plataforma.usuario.add.usuarioaplicacion.validaAplicaciones"));						
									
							return false;
						}
					}					
			}
		}
		
		
		return true;
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
		if (idUsuario == null) {
			throw new BusinessException("EL idUsuario recibido es nulo");
		}
		try {

			usuario = new UsuarioBean();
			usuario.setUsuarioId(Long.valueOf(idUsuario));
			usuario = servicioUsuario.loadUsuario(usuario);

			return SUCCESS;
		} catch (NumberFormatException | BusinessException e) {
			logger.error("UsuarioAction - load:" + e);
			String mensg = this.getText("errors.action.organismo.loadOrganismo", new String[] { usuario.getUsuarioId()
					.toString() });
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
		String source = properties.getProperty(LOGDOTSOURCE_US, null);
		String accionActualizar = properties.getProperty(LOGDOTACCION_AC, null);
		Long accionIdActualizar = Long.parseLong(properties.getProperty(LOGDOTACCIONID_0, null));
		String descripcion = properties.getProperty(LOGDOTACCION_DE, null);
		if (getRequest().getSession().getAttribute(INFOUSER) == null) {
			return NOUSER;
		}
		if (idUsuario == null) {
			addActionErrorSession(this.getText("plataforma.usuario.delete.error"));
		} else {
			usuario = new UsuarioBean();
			usuario.setUsuarioId(Long.valueOf(idUsuario));
			
			///traer todas las aplicaciones por el usuario y se eliminan las relaciones en tabla usuario-aplicacion
			List<UsuarioAplicacionBean> listaUsuariosAplicaciones = servicioUsuarioAplicacion.getUsuarioAplicacionesByUsuarioId(Integer.valueOf(idUsuario));
			for (UsuarioAplicacionBean ua : listaUsuariosAplicaciones) {
				if (null != ua.getUsuarioAplicacionId()){
					UsuarioAplicacionBean uab = new UsuarioAplicacionBean();
					uab.setUsuarioAplicacionId(ua.getUsuarioAplicacionId());
					servicioUsuarioAplicacion.deleteUsuarioAplicacion(uab, source, accionActualizar, accionIdActualizar, descripcion);
				}
			}
			servicioUsuario.deleteUsuario(usuario, source, accion, accionId);	
			addActionMessageSession(this.getText("plataforma.usuario.delete.ok"));
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
		String source = properties.getProperty(LOGDOTSOURCE_US, null);
		String accionActualizar = properties.getProperty(LOGDOTACCION_AC, null);
		Long accionIdActualizar = Long.parseLong(properties.getProperty(LOGDOTACCIONID_0, null));
		String descripcion = properties.getProperty(LOGDOTACCION_DE, null);
		
		if (getRequest().getSession().getAttribute(INFOUSER) == null) {
			return NOUSER;
		}
		if (checkDelList == null) {
			addActionErrorSession(this.getText("plataforma.usuario.deleteselected.error"));
		} else {
			for (String idUsuario : checkDelList) {
				usuario = new UsuarioBean();
				usuario.setUsuarioId(Long.valueOf(idUsuario));
				
				///traer todas las aplicaciones por el usuario y se eliminan las relaciones en tabla usuario-aplicacion
				List<UsuarioAplicacionBean> listaUsuariosAplicaciones = servicioUsuarioAplicacion.getUsuarioAplicacionesByUsuarioId(Integer.valueOf(idUsuario));
				for (UsuarioAplicacionBean ua : listaUsuariosAplicaciones) {
					if (null != ua.getUsuarioAplicacionId()){
						UsuarioAplicacionBean uab = new UsuarioAplicacionBean();
						uab.setUsuarioAplicacionId(ua.getUsuarioAplicacionId());
						servicioUsuarioAplicacion.deleteUsuarioAplicacion(uab, source, accionActualizar, accionIdActualizar, descripcion);
					}
				}
				servicioUsuario.deleteUsuario(usuario, source, accion, accionId);
			}
			addActionMessageSession(this.getText("plataforma.usuario.deleteselected.ok"));
		}
		return SUCCESS;

	}

	/**
	 * Agrega usuario aplicacion.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	
	public String addUsuarioAplicacion() throws BaseException {
		String accion = properties.getProperty(LOGDOTACCION_AC, null);
		Long accionId = Long.parseLong(properties.getProperty(LOGDOTACCIONID_0, null));
		String source = properties.getProperty(LOGDOTSOURCE_US, null);
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ASIGNAR_APLICACION", null);
		if (getRequest().getSession().getAttribute(INFOUSER) == null) {
			return NOUSER;
		}
		boolean sw = true;
		if (validaCampos(usuarioAplicacion,usuario)) {
			try {
				servicioUsuarioAplicacion.newUsuarioAplicacion(usuarioAplicacion, source, accion, accionId, descripcion);
			} catch (Exception e) {
				logger.error("UsuarioAction - addUsuarioAplicacion:" + e);
				addActionErrorSession(this.getText("plataforma.usuario.add.usuarioaplicacion.erro"));
				sw = false;
			}
			if (sw) {
				
				usuario.setUsuarioId(Long.valueOf(idUsuario));
				usuario = servicioUsuario.loadUsuario(usuario);
				if(usuario.getIdcontacto() != null){
					contacto = new ContactoBean();
					contacto.setContactoId(usuario.getIdcontacto());
					contacto = servicioContacto.loadContacto(contacto);
					if(contacto.getAplicacionid() == null){
						contacto.setAplicacionid(usuarioAplicacion.getAplicacionId());
						servicioContacto.updateContacto(contacto, source, accion, accionId);
						addActionMessageSession(this.getText("plataforma.usuario.add.contacto.aplicacion"));
					}
				}
				
				
				idUsuario = usuarioAplicacion.getUsuarioId().toString();
				UsuarioBean u = new UsuarioBean();
				u.setUsuarioId(usuarioAplicacion.getUsuarioId());
				UsuarioBean usuBean = servicioUsuario.loadUsuario(u);
				usuBean.setModificadoPor(PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto());
				usuBean.setFechaModificacion(new Date());
				servicioUsuario.updateUsuario(usuBean, source, accion, accionId);
				addActionMessageSession(this.getText("plataforma.usuario.add.usuarioaplicacion.ok"));
			}
		}
		return SUCCESS;
	}

	/**
	 * Valida campos.
	 *
	 * @param usuarioAplicacion2 the usuario aplicacion 2
	 * @return true, if successful
	 */
	
	private boolean validaCampos(UsuarioAplicacionBean usuarioAplicacion2,UsuarioBean usuario) {
		boolean sw = true;
		String aplicacionAEATGISS = properties.getProperty(PLATAFORMADOTUS0, null);
		List<String> aplAeatGiss = new ArrayList<>(Arrays.asList(aplicacionAEATGISS.split(R_CONST_REF)));
		if (PlataformaMensajeriaUtil.isEmptyNumber(usuarioAplicacion.getAplicacionId().intValue())) {
			addActionErrorSession(this.getText("plataforma.usuario.add.usuarioaplicacion.field.aplicacionid.error"));
			sw = false;
		}
		if (PlataformaMensajeriaUtil.isEmptyNumber(usuarioAplicacion.getModo())) {
			addActionErrorSession(this.getText("plataforma.usuario.add.usuarioaplicacion.field.modo.error"));
			sw = false;
		}
		if ("".equals(usuario.getOrganismo())){
			for(String aplicacion : aplAeatGiss){
				if(aplicacion.equals(String.valueOf(usuarioAplicacion2.getAplicacionId()))){
						
					addActionErrorSession(this.getText("plataforma.usuario.add.usuarioaplicacion.organismo"));
					sw = false;
				}
			}		
		}
		

		return sw;
	}

	/**
	 * Delete usuario aplicacion.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	
	public String deleteUsuarioAplicacion() throws BaseException {
		String accion = properties.getProperty(LOGDOTACCION_AC, null);
		Long accionId = Long.parseLong(properties.getProperty(LOGDOTACCIONID_0, null));
		String source = properties.getProperty(LOGDOTSOURCE_US, null);
		String descripcion = properties.getProperty(LOGDOTACCION_DE, null);
		if (getRequest().getSession().getAttribute(INFOUSER) == null) {
			return NOUSER;
		}
		if (!PlataformaMensajeriaUtil.isEmpty(usuarioAplicacionId) && !PlataformaMensajeriaUtil.isEmpty(idUsuario)) {
			UsuarioAplicacionBean usuarioAplicacionBean = new UsuarioAplicacionBean();
			usuarioAplicacionBean.setUsuarioAplicacionId(Long.valueOf(usuarioAplicacionId));
			UsuarioBean usuario = new UsuarioBean();
			usuario.setUsuarioId(Long.valueOf(idUsuario));
			UsuarioBean usuBean = servicioUsuario.loadUsuario(usuario);
			usuBean.setModificadoPor(PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto());
			usuBean.setFechaModificacion(new Date());
			servicioUsuario.updateUsuario(usuBean, source, accion, accionId);
			servicioUsuarioAplicacion.deleteUsuarioAplicacion(usuarioAplicacionBean, source, accion, accionId, descripcion);
			addActionMessageSession(this.getText("plataforma.usuario.delete.usuarioaplicacion.ok"));
		} else {
			addActionErrorSession(this.getText("plataforma.usuario.delete.usuarioaplicacion.error"));
		}
		return SUCCESS;
	}

	
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.impl.PlataformaPaginationAction#prepare()
	 */
	
	@Override
	public void prepare() throws Exception {
		if (!PlataformaMensajeriaUtil.isEmpty(idUsuario)) {
			comboAplicacionesNoAsignadas = getComboAplicacionesNoAsignadas(idUsuario);
			List<UsuarioAplicacionBean> lista = servicioUsuarioAplicacion.getUsuarioAplicacionesByUsuarioId(Integer.valueOf(idUsuario));
			for (UsuarioAplicacionBean ua : lista) {
				if (null != ua.getUsuarioAplicacionId()){
					listaUsuarioAplicaciones.add(ua);
				}
			}
			
		}
	}

/**
 * Obtener combo aplicaciones no asignadas.
 *
 * @param idUsuario the id usuario
 * @return combo aplicaciones no asignadas
 */

	public List<KeyValueObject> getComboAplicacionesNoAsignadas(String idUsuario) {
		List<KeyValueObject> result = new ArrayList<>();
		KeyValueObject option;
		ArrayList<AplicacionBean> keys = null;
		try {
			keys = (ArrayList<AplicacionBean>) servicioAplicacion.getAplicacionesNoAsignadas(idUsuario);
		} catch (BusinessException e) {
			logger.error("UsuarioAction - getComboAplicacionesNoAsignadas:" + e);
		}

		if (keys != null && !keys.isEmpty()) {
			for (AplicacionBean key : keys) {

				option = new KeyValueObject();
				option.setCodigo(key.getAplicacionId().toString());
				option.setDescripcion(key.getNombre());
				result.add(option);
			}
		}
		return result;
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
			logger.error("UsuarioAction - getComboAplicaciones:" + e);
		}

		if (keys != null && !keys.isEmpty()) {
			for (AplicacionBean key : keys) {

				option = new KeyValueObject();
				option.setCodigo(key.getAplicacionId().toString());
				option.setDescripcion(key.getNombre());
				result.add(option);
			}
		}
		return result;
	}

/**
 * Obtener combo roles.
 *
 * @return combo roles
 */

	public List<KeyValueObject> getComboRoles() {
		List<KeyValueObject> result = new ArrayList<>();
		KeyValueObject option;
		option = new KeyValueObject();
		option.setCodigo(R_CONST_0);
		option.setDescripcion("Administrador");
		result.add(option);
		option = new KeyValueObject();
		option.setCodigo(R_CONST_1);
		option.setDescripcion("Propietario Aplicacion");
		result.add(option);
		option = new KeyValueObject();
		option.setCodigo("3");
		option.setDescripcion("Caid");
		result.add(option);
		return result;
	}

/**
 * Obtener combo modos.
 *
 * @return combo modos
 */

	public List<KeyValueObject> getComboModos() {
		List<KeyValueObject> result = new ArrayList<>();
		KeyValueObject option;
		option = new KeyValueObject();
		option.setCodigo(R_CONST_0);
		option.setDescripcion("Lectura");
		result.add(option);
		option = new KeyValueObject();
		option.setCodigo(R_CONST_1);
		option.setDescripcion("Lectura/Escritura");
		result.add(option);
		return result;
	}

	/**
	 * Comprueba empty.
	 *
	 * @param value the value
	 * @return true, si es empty
	 */
	public boolean isEmpty(String value) {
		return value == null || (value != null && "".equals(value));
	}

	/**
	 * Método que resuelve el lugar donde tiene que volver.
	 *
	 * @return volver
	 */
	public String getVolver() {
		String volver = "buscarUsuarios.action";
		if (!PlataformaMensajeriaUtil.isEmpty(from) && !PlataformaMensajeriaUtil.isEmpty(idFrom)) {
			volver = from + "?" + var + "=" + idFrom;
		}
		return volver;
	}

	/**
	 * Obtener lista usuarios.
	 *
	 * @return lista usuarios
	 */
	public List<UsuarioBean> getListaUsuarios() {
		return listaUsuarios;
	}

	/**
	 * Modificar lista usuarios.
	 *
	 * @param listaUsuarios new lista usuarios
	 */
	public void setListaUsuarios(List<UsuarioBean> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
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
	 * @param servicioAplicacion new servicio aplicacion
	 */
	public void setServicioAplicacion(ServicioAplicacion servicioAplicacion) {
		this.servicioAplicacion = servicioAplicacion;
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
	 * Obtener usuario.
	 *
	 * @return usuario
	 */
	public UsuarioBean getUsuario() {
		return usuario;
	}

	/**
	 * Modificar usuario.
	 *
	 * @param usuario new usuario
	 */
	public void setUsuario(UsuarioBean usuario) {
		this.usuario = usuario;
	}

	/**
	 * Modificar combo roles.
	 *
	 * @param comboRoles new combo roles
	 */
	public void setComboRoles(List<KeyValueObject> comboRoles) {
		this.comboRoles = comboRoles;
	}

	/**
	 * Modificar combo aplicaciones.
	 *
	 * @param comboAplicaciones new combo aplicaciones
	 */
	public void setComboAplicaciones(List<KeyValueObject> comboAplicaciones) {
		this.comboAplicaciones = comboAplicaciones;
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
	 * @param idServidor new id usuario
	 */
	public void setIdUsuario(String idServidor) {
		this.idUsuario = idServidor;
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
	 * Obtener usuario aplicacion.
	 *
	 * @return usuario aplicacion
	 */
	public UsuarioAplicacionBean getUsuarioAplicacion() {
		return usuarioAplicacion;
	}

	/**
	 * Modificar usuario aplicacion.
	 *
	 * @param usuarioAplicacion new usuario aplicacion
	 */
	public void setUsuarioAplicacion(UsuarioAplicacionBean usuarioAplicacion) {
		this.usuarioAplicacion = usuarioAplicacion;
	}

	/**
	 * Obtener id aplicacion usuario.
	 *
	 * @return id aplicacion usuario
	 */
	public String getIdAplicacionUsuario() {
		return idAplicacionUsuario;
	}

	/**
	 * Modificar id aplicacion usuario.
	 *
	 * @param idAplicacionUsuario new id aplicacion usuario
	 */
	public void setIdAplicacionUsuario(String idAplicacionUsuario) {
		this.idAplicacionUsuario = idAplicacionUsuario;
	}

	/**
	 * Obtener combo aplicaciones no asignadas.
	 *
	 * @return combo aplicaciones no asignadas
	 */
	public List<KeyValueObject> getComboAplicacionesNoAsignadas() {
		return comboAplicacionesNoAsignadas;
	}

	/**
	 * Modificar combo aplicaciones no asignadas.
	 *
	 * @param comboAplicacionesNoAsignadas new combo aplicaciones no asignadas
	 */
	public void setComboAplicacionesNoAsignadas(List<KeyValueObject> comboAplicacionesNoAsignadas) {
		this.comboAplicacionesNoAsignadas = comboAplicacionesNoAsignadas;
	}

	/**
	 * Modificar combo modos.
	 *
	 * @param comboModos new combo modos
	 */
	public void setComboModos(List<KeyValueObject> comboModos) {
		this.comboModos = comboModos;
	}

	/**
	 * Obtener lista usuario aplicaciones.
	 *
	 * @return lista usuario aplicaciones
	 */
	public List<UsuarioAplicacionBean> getListaUsuarioAplicaciones() {
		return listaUsuarioAplicaciones;
	}

	/**
	 * Modificar lista usuario aplicaciones.
	 *
	 * @param listaUsuarioAplicaciones new lista usuario aplicaciones
	 */
	public void setListaUsuarioAplicaciones(List<UsuarioAplicacionBean> listaUsuarioAplicaciones) {
		this.listaUsuarioAplicaciones = listaUsuarioAplicaciones;
	}

	/**
	 * Obtener usuario aplicacion id.
	 *
	 * @return usuario aplicacion id
	 */
	public String getUsuarioAplicacionId() {
		return usuarioAplicacionId;
	}

	/**
	 * Modificar usuario aplicacion id.
	 *
	 * @param usuarioAplicacionId new usuario aplicacion id
	 */
	public void setUsuarioAplicacionId(String usuarioAplicacionId) {
		this.usuarioAplicacionId = usuarioAplicacionId;
	}

	/**
	 * Obtener form.
	 *
	 * @return form
	 */
	public UsuariosForm getForm() {
		return form;
	}

	/**
	 * Modificar form.
	 *
	 * @param form new form
	 */
	public void setForm(UsuariosForm form) {
		this.form = form;
	}

	/**
	 * Obtener user 060 VO.
	 *
	 * @return user 060 VO
	 */
	public User060VO getUser060VO() {
		return user060VO;
	}

	/**
	 * Modificar user 060 VO.
	 *
	 * @param user060vo new user 060 VO
	 */
	public void setUser060VO(User060VO user060vo) {
		user060VO = user060vo;
	}

	/**
	 * Obtener servicio usuario aplicacion.
	 *
	 * @return servicio usuario aplicacion
	 */
	public ServicioUsuarioAplicacion getServicioUsuarioAplicacion() {
		return servicioUsuarioAplicacion;
	}

	/**
	 * Modificar servicio usuario aplicacion.
	 *
	 * @param servicioUsuarioAplicacion new servicio usuario aplicacion
	 */
	public void setServicioUsuarioAplicacion(ServicioUsuarioAplicacion servicioUsuarioAplicacion) {
		this.servicioUsuarioAplicacion = servicioUsuarioAplicacion;
	}


	/**
	 * @return the newActivo
	 */
	public String getNewActivo() {
		return newActivo;
	}


	/**
	 * @param newActivo the newActivo to set
	 */
	public void setNewActivo(String newActivo) {
		this.newActivo = newActivo;
	}
}
