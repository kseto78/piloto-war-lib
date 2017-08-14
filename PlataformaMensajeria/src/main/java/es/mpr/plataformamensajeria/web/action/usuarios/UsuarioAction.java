package es.mpr.plataformamensajeria.web.action.usuarios;

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
import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;
import com.map.j2ee.security.perm.model.User060VO;
import com.map.j2ee.util.KeyValueObject;
import com.opensymphony.xwork2.Preparable;

import es.mpr.plataformamensajeria.beans.AplicacionBean;
import es.mpr.plataformamensajeria.beans.UsuarioAplicacionBean;
import es.mpr.plataformamensajeria.beans.UsuarioBean;
import es.mpr.plataformamensajeria.impl.PlataformaPaginationAction;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioAplicacion;
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

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(UsuarioAction.class);

	@Resource(name = "servicioAplicacionImpl")
	private ServicioAplicacion servicioAplicacion;

	@Resource(name = "servicioUsuarioImpl")
	private ServicioUsuario servicioUsuario;

	@Resource(name = "servicioUsuarioAplicacionImpl")
	private ServicioUsuarioAplicacion servicioUsuarioAplicacion;

	@Resource(name = "plataformaMensajeriaProperties")
	private PlataformaMensajeriaProperties properties;

	private UsuarioBean usuario;
	private UsuarioAplicacionBean usuarioAplicacion;

	public List<UsuarioBean> listaUsuarios = null;
	List<UsuarioAplicacionBean> listaUsuarioAplicaciones = new ArrayList<UsuarioAplicacionBean>();

	List<KeyValueObject> comboRoles = new ArrayList<>();
	List<KeyValueObject> comboAplicaciones = new ArrayList<>();
	List<KeyValueObject> comboModos = new ArrayList<>();
	List<KeyValueObject> comboAplicacionesNoAsignadas = new ArrayList<>();

	private String[] checkDelList;

	private String idUsuario;
	private String resultCount;

	private String idAplicacionUsuario;
	private String usuarioAplicacionId;

	private UsuariosForm form;// Para realizar el populate
	private User060VO user060VO;

	public String newSearch() throws BaseException {
		return SUCCESS;
	}

	
	////MIGRADO
	public String search() throws BaseException {

		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		int page = getPage("tableId"); // Pagina a mostrar
		String order = getOrder("tableId"); // Ordenar de modo ascendente o
											// descendente
		String columnSort = getColumnSort("tableId"); // Columna usada para
														// ordenar

		if (usuario != null)
			if (usuario.getNombre() != null && usuario.getNombre().length() <= 0)
				usuario.setNombre(null);

		int inicio = (page - 1) * Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20"));
		boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
		PaginatedList<UsuarioBean> result = servicioUsuario.getUsuarios(inicio,
				(export) ? -1 : Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20")), order,
				columnSort, usuario);
		Integer totalSize = result.getTotalList();

		listaUsuarios = result.getPageList();

		// Atributos de request
		getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_TOTALSIZE", null), totalSize);

		if (!export) {
			getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_PAGESIZE", null),
					Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20")));
		} else {
			getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_PAGESIZE", null), totalSize);
		}

		if (listaUsuarios != null && !listaUsuarios.isEmpty()) {
			for (int indice = 0; indice < listaUsuarios.size(); indice++) {

				UsuarioBean usuario = listaUsuarios.get(indice);
				usuario.setNombre(StringEscapeUtils.escapeHtml(usuario.getNombre()));
				usuario.setLogin(StringEscapeUtils.escapeHtml(usuario.getLogin()));
				usuario.setEmail(StringEscapeUtils.escapeHtml(usuario.getEmail()));
			}
		}

		return SUCCESS;
	}

	private String userNameToLoad;

	public String getUserNameToLoad() {
		return userNameToLoad;
	}

	public void setUserNameToLoad(String userNameToLoad) {
		this.userNameToLoad = userNameToLoad;
	}

	
	///MIGRADO
//	public String execute() throws BaseException {
//		if (getRequest().getSession().getAttribute("infoUser") == null)
//			return "noUser";
//
//		int page = getPage("tableId"); // Pagina a mostrar
//		String order = getOrder("tableId"); // Ordenar de modo ascendente o
//											// descendente
//		String columnSort = getColumnSort("tableId"); // Columna usada para
//														// ordenar
//
//		if (usuario != null)
//			if (usuario.getNombre() != null && usuario.getNombre().length() <= 0)
//				usuario.setNombre(null);
//
//		int inicio = (page - 1) * Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20"));
//		boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
//		PaginatedList<UsuarioBean> result = servicioUsuario.getUsuarios(inicio,
//				(export) ? -1 : Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20")), order,
//				columnSort, usuario);
//		Integer totalSize = result.getTotalList();
//
//		listaUsuarios = result.getPageList();
//
//		// Atributos de request
//		getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_TOTALSIZE", null), totalSize);
//
//		if (!export) {
//			getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_PAGESIZE", null),
//					Integer.parseInt(properties.getProperty("generales.PAGESIZE", "20")));
//		} else {
//			getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_PAGESIZE", null), totalSize);
//		}
//
//		if (listaUsuarios != null && !listaUsuarios.isEmpty()) {
//			for (int indice = 0; indice < listaUsuarios.size(); indice++) {
//
//				UsuarioBean usuario = listaUsuarios.get(indice);
//				usuario.setNombre(StringEscapeUtils.escapeHtml(usuario.getNombre()));
//				usuario.setLogin(StringEscapeUtils.escapeHtml(usuario.getLogin()));
//			}
//		}
//
//		return SUCCESS;
//	}

	////MIGRADO
	public String create() throws BaseException {
		String accion = properties.getProperty("log.ACCION_INSERTAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_INSERTAR", null));
		String source = properties.getProperty("log.SOURCE_USUARIOS", null);
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (usuario != null) {
			if (usuario.getIsActivo() != null && usuario.getIsActivo().indexOf("activo") != -1) {
				usuario.setActivo(true);
			} else {
				usuario.setActivo(false);
			}
			if (validUsuario(usuario) && !existeUsuario(usuario.getLogin())) {
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
	 * @param loginUsuario
	 * @return
	 * @throws BusinessException
	 */
	////MIGRADO
	private boolean existeUsuario(String loginUsuario) throws BusinessException {
		boolean sw = servicioUsuario.existeUsuario(loginUsuario);
		if (sw) {
			addActionErrorSession(this.getText("plataforma.usuario.create.usuarioexistente"));
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
	 * @param loginUsuario
	 * @return
	 * @throws BusinessException
	 */
	
	////MIGRADO
	private boolean existeUsuarioEdicion(Integer idUsuario, String loginUsuario) throws BusinessException {
		boolean sw = servicioUsuario.existeUsuarioEdicion(idUsuario, loginUsuario);
		if (sw) {
			addFieldErrorSession(this.getText("plataforma.usuario.create.usuarioexistente"));
		}
		return sw;
	}

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

	
	
	///MIGRADO
	public String update() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
		String source = properties.getProperty("log.SOURCE_USUARIOS", null);
		
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		UsuarioBean usuarioBBDD = null;
		if (usuario == null) {
			addActionErrorSession(this.getText("plataforma.usuario.update.error"));
		} else {
			if (usuario.getUsuarioId() == null) {
				if (idUsuario != null) {
					usuario.setUsuarioId(new Long(idUsuario));
					usuarioBBDD = servicioUsuario.loadUsuario(usuario);
				} else {
					String idServidor = (String) request.getAttribute("idUsuario");
					if (idServidor != null) {
						usuario.setId(new Integer(idUsuario));
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
				Integer rolSession = PlataformaMensajeriaUtil.getRolUsuarioByUsername(usuario.getLogin(), servicioUsuario);

				if (validUsuario(usuarioBBDD)
						&& !existeUsuarioEdicion(usuarioBBDD.getUsuarioId().intValue(), usuarioBBDD.getLogin())) {
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

	////MIGRADO
	public String load() throws BaseException {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (idUsuario == null)
			throw new BusinessException("EL idUsuario recibido es nulo");
		try {

			usuario = new UsuarioBean();
			usuario.setUsuarioId(new Long(idUsuario));
			usuario = servicioUsuario.loadUsuario(usuario);

			return SUCCESS;
		} catch (NumberFormatException | BusinessException e) {
			logger.error("UsuarioAction - load:" + e);
			String mensg = this.getText("errors.action.organismo.loadOrganismo", new String[] { usuario.getUsuarioId()
					.toString() });
			throw new BusinessException(mensg);
		} 

	}

	
	///MIGRADO
	public String delete() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ELIMINAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ELIMINAR", null));
		String source = properties.getProperty("log.SOURCE_USUARIOS", null);
		String accionActualizar = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		Long accionIdActualizar = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_DESASIGNAR_APLICACION", null);
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (idUsuario == null) {
			addActionErrorSession(this.getText("plataforma.usuario.delete.error"));
		} else {
			usuario = new UsuarioBean();
			usuario.setUsuarioId(new Long(idUsuario));
			
			///traer todas las aplicaciones por el usuario y se eliminan las relaciones en tabla usuario-aplicacion
			List<UsuarioAplicacionBean> listaUsuariosAplicaciones = servicioUsuarioAplicacion.getUsuarioAplicacionesByUsuarioId(new Integer(idUsuario));
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

	///MIGRADO
	public String deleteSelected() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ELIMINAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ELIMINAR", null));
		String source = properties.getProperty("log.SOURCE_USUARIOS", null);
		String accionActualizar = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		Long accionIdActualizar = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_DESASIGNAR_APLICACION", null);
		
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (checkDelList == null) {
			addActionErrorSession(this.getText("plataforma.usuario.deleteselected.error"));
		} else {
			for (String idUsuario : checkDelList) {
				usuario = new UsuarioBean();
				usuario.setUsuarioId(new Long(idUsuario));
				
				///traer todas las aplicaciones por el usuario y se eliminan las relaciones en tabla usuario-aplicacion
				List<UsuarioAplicacionBean> listaUsuariosAplicaciones = servicioUsuarioAplicacion.getUsuarioAplicacionesByUsuarioId(new Integer(idUsuario));
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

	////MIGRADO
	public String addUsuarioAplicacion() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
		String source = properties.getProperty("log.SOURCE_USUARIOS", null);
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ASIGNAR_APLICACION", null);
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		boolean sw = true;
		if (validaCampos(usuarioAplicacion)) {
			try {
				servicioUsuarioAplicacion.newUsuarioAplicacion(usuarioAplicacion, source, accion, accionId, descripcion);
			} catch (Exception e) {
				logger.error("UsuarioAction - addUsuarioAplicacion:" + e);
				addActionErrorSession(this.getText("plataforma.usuario.add.usuarioaplicacion.erro"));
				sw = false;
			}
			if (sw) {
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

	///MIGRADO
	private boolean validaCampos(UsuarioAplicacionBean usuarioAplicacion2) {
		boolean sw = true;
		if (PlataformaMensajeriaUtil.isEmptyNumber(usuarioAplicacion.getAplicacionId().intValue())) {
			addActionErrorSession(this.getText("plataforma.usuario.add.usuarioaplicacion.field.aplicacionid.error"));
			sw = false;
		}
		if (PlataformaMensajeriaUtil.isEmptyNumber(usuarioAplicacion.getModo())) {
			addActionErrorSession(this.getText("plataforma.usuario.add.usuarioaplicacion.field.modo.error"));
			sw = false;
		}

		return sw;
	}

	///MIGRADO
	public String deleteUsuarioAplicacion() throws BaseException {
		String accion = properties.getProperty("log.ACCION_ACTUALIZAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_ACTUALIZAR", null));
		String source = properties.getProperty("log.SOURCE_USUARIOS", null);
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_DESASIGNAR_APLICACION", null);
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (!PlataformaMensajeriaUtil.isEmpty(usuarioAplicacionId) && !PlataformaMensajeriaUtil.isEmpty(idUsuario)) {
			UsuarioAplicacionBean usuarioAplicacionBean = new UsuarioAplicacionBean();
			usuarioAplicacionBean.setUsuarioAplicacionId(new Long(usuarioAplicacionId));
			UsuarioBean usuario = new UsuarioBean();
			usuario.setUsuarioId(new Long(idUsuario));
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

	
	////MIGRADO
	@Override
	public void prepare() throws Exception {
		if (!PlataformaMensajeriaUtil.isEmpty(idUsuario)) {
			comboAplicacionesNoAsignadas = getComboAplicacionesNoAsignadas(idUsuario);
			List<UsuarioAplicacionBean> lista = servicioUsuarioAplicacion.getUsuarioAplicacionesByUsuarioId(new Integer(
					idUsuario));
			for (UsuarioAplicacionBean ua : lista) {
				if (null != ua.getUsuarioAplicacionId()){
					listaUsuarioAplicaciones.add(ua);
				}
			}
			
		}
	}

////MIGRADO
	public List<KeyValueObject> getComboAplicacionesNoAsignadas(String idUsuario) {
		List<KeyValueObject> result = new ArrayList<>();
		KeyValueObject option;
		ArrayList<AplicacionBean> keys = null;
		try {
			keys = (ArrayList<AplicacionBean>) servicioAplicacion.getAplicacionesNoAsignadas(idUsuario);
		} catch (BusinessException e) {
			logger.error("UsuarioAction - getComboAplicacionesNoAsignadas:" + e);
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

////MIGRADO
	public List<KeyValueObject> getComboAplicaciones() {
		List<KeyValueObject> result = new ArrayList<>();
		KeyValueObject option;
		ArrayList<AplicacionBean> keys = null;
		try {
			keys = (ArrayList<AplicacionBean>) servicioAplicacion.getAplicaciones();
		} catch (BusinessException e) {
			logger.error("UsuarioAction - getComboAplicaciones:" + e);
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

///MIGRADO
	public List<KeyValueObject> getComboRoles() {
		List<KeyValueObject> result = new ArrayList<>();
		KeyValueObject option;
		option = new KeyValueObject();
		option.setCodigo("1");
		option.setDescripcion("Administrador");
		result.add(option);
		option = new KeyValueObject();
		option.setCodigo("2");
		option.setDescripcion("Propietario Aplicacion");
		result.add(option);
		return result;
	}

////MIGRADO
	public List<KeyValueObject> getComboModos() {
		List<KeyValueObject> result = new ArrayList<>();
		KeyValueObject option;
		option = new KeyValueObject();
		option.setCodigo("1");
		option.setDescripcion("Lectura");
		result.add(option);
		option = new KeyValueObject();
		option.setCodigo("2");
		option.setDescripcion("Lectura/Escritura");
		result.add(option);
		return result;
	}

	public boolean isEmpty(String value) {
		if (value == null || (value != null && value.equals(""))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Método que resuelve el lugar donde tiene que volver
	 */
	public String getVolver() {
		String volver = "buscarUsuarios.action";
		if (!PlataformaMensajeriaUtil.isEmpty(from) && !PlataformaMensajeriaUtil.isEmpty(idFrom)) {
			volver = from + "?" + var + "=" + idFrom;
		}
		return volver;
	}

	public List<UsuarioBean> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<UsuarioBean> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public ServicioAplicacion getServicioAplicacion() {
		return servicioAplicacion;
	}

	public void setServicioAplicacion(ServicioAplicacion servicioAplicacion) {
		this.servicioAplicacion = servicioAplicacion;
	}

	public ServicioUsuario getServicioUsuario() {
		return servicioUsuario;
	}

	public void setServicioUsuario(ServicioUsuario servicioUsuario) {
		this.servicioUsuario = servicioUsuario;
	}

	public UsuarioBean getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioBean usuario) {
		this.usuario = usuario;
	}

	public void setComboRoles(List<KeyValueObject> comboRoles) {
		this.comboRoles = comboRoles;
	}

	public void setComboAplicaciones(List<KeyValueObject> comboAplicaciones) {
		this.comboAplicaciones = comboAplicaciones;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idServidor) {
		this.idUsuario = idServidor;
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

	public UsuarioAplicacionBean getUsuarioAplicacion() {
		return usuarioAplicacion;
	}

	public void setUsuarioAplicacion(UsuarioAplicacionBean usuarioAplicacion) {
		this.usuarioAplicacion = usuarioAplicacion;
	}

	public String getIdAplicacionUsuario() {
		return idAplicacionUsuario;
	}

	public void setIdAplicacionUsuario(String idAplicacionUsuario) {
		this.idAplicacionUsuario = idAplicacionUsuario;
	}

	public List<KeyValueObject> getComboAplicacionesNoAsignadas() {
		return comboAplicacionesNoAsignadas;
	}

	public void setComboAplicacionesNoAsignadas(List<KeyValueObject> comboAplicacionesNoAsignadas) {
		this.comboAplicacionesNoAsignadas = comboAplicacionesNoAsignadas;
	}

	public void setComboModos(List<KeyValueObject> comboModos) {
		this.comboModos = comboModos;
	}

	public List<UsuarioAplicacionBean> getListaUsuarioAplicaciones() {
		return listaUsuarioAplicaciones;
	}

	public void setListaUsuarioAplicaciones(List<UsuarioAplicacionBean> listaUsuarioAplicaciones) {
		this.listaUsuarioAplicaciones = listaUsuarioAplicaciones;
	}

	public String getUsuarioAplicacionId() {
		return usuarioAplicacionId;
	}

	public void setUsuarioAplicacionId(String usuarioAplicacionId) {
		this.usuarioAplicacionId = usuarioAplicacionId;
	}

	public UsuariosForm getForm() {
		return form;
	}

	public void setForm(UsuariosForm form) {
		this.form = form;
	}

	public User060VO getUser060VO() {
		return user060VO;
	}

	public void setUser060VO(User060VO user060vo) {
		user060VO = user060vo;
	}

	public ServicioUsuarioAplicacion getServicioUsuarioAplicacion() {
		return servicioUsuarioAplicacion;
	}

	public void setServicioUsuarioAplicacion(ServicioUsuarioAplicacion servicioUsuarioAplicacion) {
		this.servicioUsuarioAplicacion = servicioUsuarioAplicacion;
	}
}
