package es.mpr.template.web.action.admin;

import com.map.j2ee.dao.ldap.LdapUserType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.security.context.SecurityContextHolder;

import com.map.j2ee.exceptions.BaseException;
import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.security.perm.UserException;
import com.map.j2ee.security.perm.manager.GroupManager;
import com.map.j2ee.security.perm.manager.UserManager;
import com.map.j2ee.security.perm.model.GroupVO;
//import com.map.j2ee.security.perm.model.UserVO;
import com.map.j2ee.util.KeyValueObject;
import com.map.j2ee.security.perm.model.User060VO;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

import java.util.Map;

/**
 * 
 * Clase Action para la Gesti&oacute;n de usuarios
 * 
 * @author Altran
 * 
 */
public class UsuariosAction extends ActionSupport implements ServletRequestAware, Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Definicion del logger
	 */
	private static Log logger = LogFactory.getLog(UsuariosAction.class);

	protected UsuariosForm form = new UsuariosForm();
	
	protected String searchResultCollectionId = "";
	
	protected HttpServletRequest request;
	
	protected UserManager userManager;
	
	protected GroupManager groupManager;
	
	/**
	 * Listado de tipos de usuario
	 */
	@SuppressWarnings("rawtypes")
	private Map listUserTypes = null;	
	
	protected List<KeyValueObject> contextUsuarios;
	
	protected Collection<User060VO> listaUsuarios;
	
	/**
	 * @return the searchResultCollectionId
	 */
	public String getSearchResultCollectionId() {
		return searchResultCollectionId;
	}

	/**
	 * @param searchResultCollectionId the searchResultCollectionId to set
	 */
	public void setSearchResultCollectionId(String searchResultCollectionId) {
		this.searchResultCollectionId = searchResultCollectionId;
	}
	
	/**
	 * Realiza la preparaci&oacute;n del contexto para una nueva b&uacute;squeda de usuarios
	 */
	public String newSearch() throws BaseException {
		if(request.getSession().getAttribute("infoUser")==null) return "noUser"; 

		logger.debug("newSearch(): Estableciendo contenido del combo de tipo de usuario");
		
		// Se obtienen la lista de contextos de usuarios en el formulario
		contextUsuarios = getComboValues();

		logger.debug("newSearch(): Establecidos valores iniciales redirigimos a pantalla");
		
		return SUCCESS;
	}

	/**
	 * Realiza la consulta de la lista de usuarios
	 *  
	 * @throws BaseException
	 */
	@SuppressWarnings("unchecked")
	public String search() throws BaseException {
		if(request.getSession().getAttribute("infoUser")==null) return "noUser"; 

		try {
			logger.debug("search(): Recuperando valores del formulario");

			User060VO criteria = new User060VO();

			// Obtener los criterios de busqueda y crear el objeto de criterios
			try {
				BeanUtils.copyProperties(criteria, form);
			} catch (Exception e) {
				throw new BaseException(e.getMessage());
			}

			// Reseteamos el valor de organismo y U.O. para que no filtre la
			// consulta
			criteria.setOrganismo(null);
			criteria.setUnidadOrganizacional(null);
			criteria.setEliminado(null);

			logger.debug("search(): Haciendo consulta al LDAP");

			listaUsuarios = getUserManager().getObjects(criteria);

			Iterator<User060VO> iteratorUsuarios = listaUsuarios.iterator();
			while (iteratorUsuarios.hasNext()) {
	    		User060VO unUsuario = iteratorUsuarios.next();
	    		unUsuario.setId(StringEscapeUtils.escapeHtml(unUsuario.getId()));
	    		unUsuario.setNombre(StringEscapeUtils.escapeHtml(unUsuario.getNombre()));
	    		unUsuario.setApellidos(StringEscapeUtils.escapeHtml(unUsuario.getApellidos()));
	    		unUsuario.setDescription(StringEscapeUtils.escapeHtml(unUsuario.getDescription()));
	    	}

			// Se obtinen la lista de contextos de usuarios en el formulario
			contextUsuarios = getComboValues();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
		
	}
	
	/**
	 * Inicializa el formulario para la creaci&oacute;n de un nuevo usuario
	 * 
	 * @throws BaseException
	 */
	public String create() throws BaseException {
		if(request.getSession().getAttribute("infoUser")==null) return "noUser"; 
		
		initForm();
		
		contextUsuarios = getComboValues();

		return SUCCESS;
	}

	/**
	 * Creamos un nuevo usuario
	 * 
	 * @throws BaseException
	 */
	public String saveNew() throws BaseException {
		if(request.getSession().getAttribute("infoUser")==null) return "noUser"; 
		
			// Creamos un objeto VO con los campos que llegan del formulario
			User060VO tmpUsuario = new User060VO();
			// Copiar datos del formulario al vo: Informamos id, nombre, apellido,
			// nis...

                        form.setPassword(getUserManager().cryptPassword(form.getPassword()));

			try {
				BeanUtils.copyProperties(tmpUsuario, form);
			} catch (Exception e) {
				throw new BaseException(e, e.getMessage());
			}
			
			tmpUsuario.setId(null);
			// Se indica el valor correcto del campo Eliminado
			
			//tmpUsuario.setEliminado(form.getEliminado());
				
			// Realizamos el create del usuario
			tmpUsuario = (User060VO) getUserManager().create(tmpUsuario);

                        try {
				BeanUtils.copyProperties(form,tmpUsuario);
			} catch (Exception e) {
				throw new BaseException(e, e.getMessage());
			}

			return SUCCESS;
	}


	/**
	 * Obtiene la lista de grupos disponibles para el usuario
	 * @param grupos
	 * @return Colecci&oacute;n con los grupos a mostrar en pantalla
	 * @throws BaseException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Collection getGruposCheckBoxes(ArrayList grupos) throws BaseException {
		// Montamos una Collection de GrupoVO's con todos
		// los grupos marcados
		ArrayList gruposInsertar = new ArrayList();

		// Si no han marcado ningún atributo
		if (form.getCheckBoxGruposSeleccionados() == null || 
				form.getCheckBoxGruposSeleccionados().size() == 0)
			return gruposInsertar;
		
		Iterator itGrupos = grupos.iterator();
		while (itGrupos.hasNext()) {
			GroupVO grupoActual = ((GroupVO) itGrupos.next());
			if (form.getCheckBoxGruposSeleccionados().contains(grupoActual.getName())) {
				gruposInsertar.add(grupoActual);
			}
		}

		return gruposInsertar;
	}

	/**
	 * Actualizamos los datos del usuario
	 * 
	 * @throws BaseException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String save() throws BaseException {
		if(request.getSession().getAttribute("infoUser")==null) return "noUser"; 
		
		// Creamos un objeto VO con los campos que llegan del formulario
		User060VO tmpUsuario = new User060VO();
		// Se indica el valor correcto del campo Eliminado
	
		//tmpUsuario.setEliminado(form.getEliminado());
	

		// Copiar datos del formulario al vo: Informamos id, nombre,
		// apellido...

		try {
			BeanUtils.copyProperties(tmpUsuario, form);
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		

		ArrayList grupos = new ArrayList(getGroupManager().getObjects(new GroupVO()));
		// Montamos una Collection de GroupVO's del usuario
		Collection gruposInsertar = getGruposCheckBoxes(grupos);
		// Informamos los grupos del usuario
		tmpUsuario.setGroups(gruposInsertar);

		tmpUsuario = (User060VO) getUserManager().update(tmpUsuario);
		
		return SUCCESS;
	}

	/**
	 * Accion encargada de borrar un usuario
	 */
	public String delete() throws BaseException {
		if(request.getSession().getAttribute("infoUser")==null) return "noUser"; 
		
		// Paso 1: Creamos un objeto VO con los campos que llegan del formulario
		User060VO tmpUsuario = new User060VO();
		String userId = request.getParameter("id");
		String userType = request.getParameter("usertype");
		tmpUsuario.setId(userId);
		tmpUsuario.setTipoUsuario(userType);

		// Paso 2: Realizamos el borrado (delete)
		getUserManager().delete(tmpUsuario);

		// Se ponen los datos que ha indicado el usuario en la pantalla
		contextUsuarios = getComboValues();

		return SUCCESS;
	}

	/**
	 * Accion encargada de editar un usuario
	 */
	public String edit() throws BaseException {
		if(request.getSession().getAttribute("infoUser")==null) return "noUser"; 
		
		// Paso 1: Recuperar de la request los parametros clave que determinan
		// el objeto a editar
		String userId = request.getParameter("id");
		String userType = request.getParameter("usertype");
		
		// Paso 1.1: Comprobamos si el origen es el formulario de consulta
		if (userId == null || userId.equals("")) {
			userId = form.getId();
		}
		if (userType == null || userType.equals("")) {
			userType = form.getTipoUsuario();
		}

		User060VO voEdit = new User060VO();
		voEdit.setId(userId);
		voEdit.setUid(userId);
		// Se indica la rama donde buscar
		voEdit.setTipoUsuario(userType);

		// Paso 2: Recuperar el Usuario VO a editar a partir de los campos clave
		User060VO vo = (User060VO) getUserManager().getObject(voEdit);
                
		if (vo == null)
			throw new UserException("usuario.errors.loadUsuario");

		// Se ponen los datos que ha indicado el usuario en la pantalla
		try {
			BeanUtils.copyProperties(form, vo);
		} catch (Exception e) {
                    e.printStackTrace();
			throw new BaseException(e.getMessage());
		}

                //form.setEliminado(vo.isEliminado());

		setUserGroups(vo);

		return SUCCESS;
	}

	/**
	 * Realiza la consulta de un usuario en el LDAP
	 * @return
	 * @throws BaseException
	 */
	public String editSelf() throws BaseException {
		if(request.getSession().getAttribute("infoUser")==null) return "noUser"; 
		
		logger.debug("editSelf(): Recuperando par�metros del request");
		// Paso 1: Recuperar de la request los parametros clave que determinan
		// el objeto a editar
		String userId = null;
		
		if (request.getParameter("id")!=null) {
			userId = request.getParameter("id");
		} else {
			userId = form.getId();
		}
		//String userType = request.getParameter("usertype");
		logger.debug("editSelf(): Id del usuario a consultar: " + userId);
		
		
		User060VO filtros = new User060VO();
		filtros.setId(userId);
		
		
		
		// Paso 2: Recuperar el Usuario VO a editar a partir de los campos clave
		User060VO vo =(User060VO)getUserManager().getObject(filtros);
                
		if (vo == null)
			throw new UserException("usuario.errors.loadUsuario");
		if (logger.isDebugEnabled())
			logger.debug("Usuario VO recuperado");
		
		// Paso 3: Copiar datos de retorno al formulario, del VO al formulario
		// Paso 3.1: Informar campos básicos (y fáciles) mediante
		// copyProperties: 'id', 'nombre' y 'descripcion'
		try {
			BeanUtils.copyProperties(form, vo);
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}

                //form.setEliminado(vo.isEliminado());

		if (logger.isDebugEnabled())
			logger.debug("Informados campos en el formulario");

		setUserGroups(vo);

		return SUCCESS;
	}

	/**
	 * Realiza la consulta de un usuario en el LDAP
	 * @return
	 * @throws BaseException
	 */
	public String editPassword() throws BaseException {
		if(request.getSession().getAttribute("infoUser")==null) return "noUser"; 
		
		logger.debug("editPassword(): Recuperando parámetros del usuario");
		// Paso 1: Recuperar del formulario el usuario y la password actual
		String userId = form.getUid();
		String userName = form.getUsername();
		logger.debug("editPassword(): Id del usuario a modificar su clave: " + userId);
		logger.debug("editPassword(): Username del usuario a modificar su clave: " + userName);
		
		// Paso 2: Reseteamos la nueva password por si acaso
		if (form == null)
			throw new UserException("usuario.errors.loadUsuario");
		if (logger.isDebugEnabled())
			logger.debug("Usuario VO recuperado");
		form.setPassword("");
		form.setCheckPassword("");
                
		return SUCCESS;
	}
	
	public String savePassword() throws BaseException {
		if(request.getSession().getAttribute("infoUser")==null) return "noUser"; 
		
		form.setPassword(getUserManager().cryptPassword(form.getPassword()));

		// Creamos un objeto VO con los campos que llegan del formulario
		User060VO tmpUsuario = new User060VO();
		tmpUsuario.setId(form.getId());

		tmpUsuario = (User060VO)getUserManager().getObject(tmpUsuario);
		
		tmpUsuario.setPassword(form.getPassword());
		
		// Se indica el valor correcto del campo Eliminado
		// tmpUsuario.setEliminado(form.getEliminado());

		tmpUsuario = (User060VO) getUserManager().update(tmpUsuario);
		
		return SUCCESS;
	}

	/**
	 * Recupera todos los grupos que el usuario puede seleccionar.
	 * 
	 */
	@SuppressWarnings("rawtypes")
	private void setGroupsInForm() throws BusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("setGroupsInForm(): Obtenemos posibles grupos a seleccionar para el usuario");
		}
		form.setCheckBoxGrupos(new ArrayList<String>());
		ArrayList gruposDisponibles = (ArrayList) groupManager.getObjects(new GroupVO());
		if (gruposDisponibles != null) {
			Iterator itGrupos = gruposDisponibles.iterator();
			while (itGrupos.hasNext()) {
				GroupVO unGrupo = (GroupVO) itGrupos.next();
				form.getCheckBoxGrupos().add(unGrupo.getName());
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("setGroupsInForm(): Obtenidos posibles grupos a seleccionar para el usuario");
		}

	}
        
  


	/**
	 * Recupera todos los grupos del usuario.
	 * 
	 * @param user
	 */
	@SuppressWarnings("rawtypes")
	private void setUserGroups(User060VO user) throws BusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("setGroupsInForm(): Obtenemos los grupos asociados al usuario");
		}
		ArrayList gruposUsuario = (ArrayList) user.getGroups();
		if (gruposUsuario != null) {
			Iterator itGruposUsuario = gruposUsuario.iterator();
			while (itGruposUsuario.hasNext()) {
				GroupVO unGrupo = (GroupVO) itGruposUsuario.next();
				form.getCheckBoxGruposSeleccionados().add(unGrupo.getName());
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("setGroupsInForm(): Obtenidos los grupos asociados al usuario");
		}
	}


	/**
	 * Inicializa el form de usuario para empezar de nuevo una nueva busqueda
	 * 
	 * @param request
	 * @param mapping
	 * @param form
	 * @return UsuarioForm
	 */
	private void initForm() {
		if (form != null) {
			form.setApellidos("");
			form.setNombre("");
			form.setUid("");
			form.setUsername("");
			form.setDescription("");
                        form.setCheckBoxGrupos(new ArrayList<String>());
                        form.setCheckBoxGruposSeleccionados(new ArrayList<String>());
		} else {
			form = new UsuariosForm();
		}
	}

	public UsuariosForm getForm() {
            
		return this.form;
	}

	public void setForm(UsuariosForm form) {
           
		this.form = form;
	}
	
	public GroupManager getGroupManager(){
		return groupManager;
	}
	
	public void setGroupManager(GroupManager groupManager){
		this.groupManager = groupManager;
	}
	
	public UserManager getUserManager() {
		return userManager;
	}
	
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	/**
	 * @return the request
	 */
	public HttpServletRequest getServletRequest() {
		return request;
	}



	/**
	 * @return the contextUsuarios
	 */
	public List<KeyValueObject> getContextUsuarios() {
		return contextUsuarios;
	}

	/**
	 * @param contextUsuarios the contextUsuarios to set
	 */
	public void setContextUsuarios(List<KeyValueObject> contextUsuarios) {
		this.contextUsuarios = contextUsuarios;
	}

	/**
	 * @return the listaUsuarios
	 */
	@SuppressWarnings("rawtypes")
	public Collection getListaUsuarios() {
		return listaUsuarios;
	}

	/**
	 * @param listaUsuarios the listaUsuarios to set
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setListaUsuarios(Collection listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	/**
	 * Método que realiza la preparación de datos previos a la ejecución de cualquier acción
	 */
	@Override
	public void prepare() throws Exception {
		contextUsuarios = getComboValues();
		setGroupsInForm();
	}

    @SuppressWarnings({ "unchecked", "rawtypes" })
	private List<KeyValueObject> getComboValues() {
        List<KeyValueObject> result = new ArrayList<KeyValueObject>();

        

        KeyValueObject option = null;
        ArrayList keys = new ArrayList(this.getListUserTypes().keySet());

        java.util.Collections.sort(keys);

        for (Object key :keys) {
            LdapUserType userType = (LdapUserType) this.getListUserTypes().get(key);
            option = new KeyValueObject();
            option.setCodigo((String) key);
            option.setDescripcion(userType.getName());
            result.add(option);
        }
        return result;
    }

    /**
     * @return the listUserTypes
     */
    @SuppressWarnings("rawtypes")
	public Map getListUserTypes() {
        return listUserTypes;
    }

    /**
     * @param listUserTypes the listUserTypes to set
     */
    @SuppressWarnings("rawtypes")
	public void setListUserTypes(Map listUserTypes) {
        this.listUserTypes = listUserTypes;
    }
    

   	

}