package es.mpr.template.web.action.admin;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.security.context.SecurityContextHolder;

import com.map.j2ee.exceptions.BaseException;
import com.map.j2ee.security.perm.manager.GroupManager;
import com.map.j2ee.security.perm.model.GroupVO;
import com.map.j2ee.util.beanutils.LocaleBeanUtils;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * Clase que contiene los actions asociados a las grupos
 * 
 * <p>
 * <a href="GrupoAction.java.html"> <i>Ver codigo fuente</i> </a>
 * </p>
 *  @
 * @author tnarrosf
 */
public class GrupoAction extends ActionSupport implements ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Definicion del logger
	 */
	private static Logger logger = Logger.getLogger(GrupoAction.class);

	protected GrupoForm form = new GrupoForm();
	
	protected String searchResultCollectionId = "";
	
	protected HttpServletRequest request;
	
	protected GroupManager groupManager;	
	
	/**
	 * Accion encargada de entrar en la busqueda
	 */
	public String newSearch() throws Exception {
		try{
			SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}catch(Exception e){
			return "noUser";
		}
		(form).setName("");
		(form).setDescription("");
		(form).setId("");

		return SUCCESS;
	}

	/**
	 * Accion encargada de realizar la consulta lista de grupos
	 */
	@SuppressWarnings("unchecked")
	public String search() throws BaseException {
		if(request.getSession().getAttribute("infoUser")==null) return "noUser"; 
		GroupVO criteria = new GroupVO();
		// Obtener los criterios de busqueda y crear el objeto de criterios
		try {
			LocaleBeanUtils beanUtils = new LocaleBeanUtils(request);
			beanUtils.copyProperties(criteria, form);
		} catch (Exception e) {
			logger.error("Exc copyProperties:", e);
		}
		Collection<GroupVO> listaGrupos = getGroupManager().getObjects(criteria);

		Iterator<GroupVO> iteratorGrupos = listaGrupos.iterator();
		while (iteratorGrupos.hasNext()) {
			GroupVO unGrupo = iteratorGrupos.next();
			unGrupo.setId(StringEscapeUtils.escapeHtml(unGrupo.getId()));
			unGrupo.setName(StringEscapeUtils.escapeHtml(unGrupo.getName()));
			unGrupo.setDescription(StringEscapeUtils.escapeHtml(unGrupo.getDescription()));
    	}
		
		// Se alamcena el resultado en request
		request.setAttribute("listaGrupos", listaGrupos);

		return SUCCESS;
	}

	/**
	 * Accion encargada de iniciar la creacion de un grupo
	 */
	public String create() throws BaseException {
		if(request.getSession().getAttribute("infoUser")==null) return "noUser"; 
		((GrupoForm) form).setName("");
		((GrupoForm) form).setDescription("");
		((GrupoForm) form).setId("");

		// En creacion no se cargan las urls
		return SUCCESS;
	}

	/**
	 * Accion encargada de crear un grupo
	 */
	public String saveNew() throws BaseException {
		if(request.getSession().getAttribute("infoUser")==null) return "noUser"; 
		// Creamos un objeto VO con los campos que llegan del formulario
		GroupVO tmpGrupo = new GroupVO();
		// Copiar datos del formulario al vo: Informamos id, nombre,
		// descripcion
		LocaleBeanUtils beanUtils = new LocaleBeanUtils(request);
		try{
			beanUtils.copyProperties(tmpGrupo, form);
		}catch (Exception e) {
			throw new BaseException(e.getMessage());
		}

		
		// Realizamos el create del grupo
		tmpGrupo = (GroupVO) getGroupManager().create(tmpGrupo);

        try{
			beanUtils.copyProperties(form,tmpGrupo);
		}catch (Exception e) {
			throw new BaseException(e.getMessage());
		}

		return SUCCESS;
	}

	/**
	 * Accion encargada de actualizar un grupo
	 */
	public String save() throws BaseException {
		if(request.getSession().getAttribute("infoUser")==null) return "noUser"; 
		// Creamos un objeto VO con los campos que llegan del formulario
		GroupVO tmpGrupo = new GroupVO();
		// Copiar datos del formulario al vo: Informamos id, nombre,
		// descripcion
		LocaleBeanUtils beanUtils = new LocaleBeanUtils(request);
		try {
			beanUtils.copyProperties(tmpGrupo, form);
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		
		

		// Realizamos la actualizaci�n (update)
		tmpGrupo = (GroupVO) getGroupManager().update(tmpGrupo);

		return SUCCESS;
	}

	/**
	 * Accion encargada de borrar un grupo
	 */
	public String delete() throws BaseException {
		if(request.getSession().getAttribute("infoUser")==null) return "noUser"; 
		// Paso 1: Creamos un objeto VO con los campos que llegan del formulario
		GroupVO tmpGrupo = new GroupVO();

		// Copiar datos del formulario al vo
		LocaleBeanUtils beanUtils = new LocaleBeanUtils(request);

		GroupVO voEdit = new GroupVO();
		String id = request.getParameter("id");

		voEdit.setId(id);
		
		try {
			beanUtils.copyProperties(tmpGrupo, getGroupManager().getObject(voEdit));
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}

		// Paso 2: Realizamos el borrado (delete)
		getGroupManager().delete(tmpGrupo);

		return SUCCESS;
	}

	/**
	 * Accion encargada de editar un grupo
	 */
	public String edit() throws BaseException {
		if(request.getSession().getAttribute("infoUser")==null) return "noUser"; 
		// Paso 1: Recuperar de la request los parametros clave que determinan
		// el objeto a editar
		String id = request.getParameter("id");
		
		if (id == null || id.equals("")) {
			id = form.getId();
		}

		// Paso 2: Recuperar el Grupo VO a editar a partir de los campos clave
		GroupVO vo = new GroupVO();
		vo.setId(id);
		vo = (GroupVO) getGroupManager().getObject(vo);
		if (logger.isDebugEnabled())
			logger.debug("Grupo VO recuperado");

		// Hay que volver a poner en el formulario todos los dominios y grupos
		LocaleBeanUtils beanUtils = new LocaleBeanUtils(request);

		try {
			// Se ponen los datos que ha indicado el usuario en la pantalla
			beanUtils.copyProperties(form, vo);
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}

		return SUCCESS;
	}


	public GrupoForm getForm() {
		return this.form;
	}

	public void setForm(GrupoForm form) {
		this.form = form;
	}
	
	public GroupManager getGroupManager(){
		return groupManager;
	}
	
	public void setGroupManager(GroupManager groupManager){
		this.groupManager = groupManager;
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



	
}