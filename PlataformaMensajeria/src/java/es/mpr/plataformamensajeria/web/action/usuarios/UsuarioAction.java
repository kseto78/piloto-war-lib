package es.mpr.plataformamensajeria.web.action.usuarios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.security.context.SecurityContextHolder;

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
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;
import es.mpr.template.web.action.admin.UsuariosForm;
 
/**
 * <p>Clase Action de Struts2 para la gesti&oacute;n de los Usuarios.
 * 
 * <p>
 * Proporciona m&eacute;todos para la creaci&oacute;n, modificaci&oacute;n, borrado y listado de los Usuarios
 * 
 * @author i-nercya
 *
 */
public class UsuarioAction extends PlataformaPaginationAction implements ServletRequestAware, Preparable{
	
	private static final long serialVersionUID = 1L;
	
	private static final String REQUEST_ATTRIBUTE_TOTALSIZE = "totalSize";
	private static final String REQUEST_ATTRIBUTE_PAGESIZE = "pageSize";
	  

	private static final Integer PAGESIZE = new Integer(10000000); //Elementos por pagina
	List<KeyValueObject> comboRoles = new ArrayList<KeyValueObject>();
	List<KeyValueObject> comboAplicaciones = new ArrayList<KeyValueObject>();
	List<KeyValueObject> comboModos = new ArrayList<KeyValueObject>();
	List<KeyValueObject> comboAplicacionesNoAsignadas = new ArrayList<KeyValueObject>();
	

	public List<UsuarioBean> listaUsuarios= null;
	
    private ServicioAplicacion servicioAplicacion;
    private ServicioUsuario servicioUsuario;
    private ServicioUsuarioAplicacion servicioUsuarioAplicacion;
    
    public ServicioUsuarioAplicacion getServicioUsuarioAplicacion() {
		return servicioUsuarioAplicacion;
	}
	public void setServicioUsuarioAplicacion(
			ServicioUsuarioAplicacion servicioUsuarioAplicacion) {
		this.servicioUsuarioAplicacion = servicioUsuarioAplicacion;
	}
	
	private String idUsuario;
    private String resultCount;
    private String[] checkDelList;
    private String idAplicacionUsuario;
    private String usuarioAplicacionId;
	private UsuarioBean usuario;
    private UsuarioAplicacionBean usuarioAplicacion;
    private UsuariosForm form;//Para realizar el populate
    private User060VO user060VO;

	public String newSearch() throws BaseException {
    	return SUCCESS;
    }
	public String search() throws BaseException {
		/**
		 * Consulta para obtener los usuarios 
		 * select usr.* 
			from tbl_usuarios usr, view_usuarios_aplicaciones vua
			where usr.usuarioid = vua.usuarioid and vua.aplicacionid = 23
		 */
		
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
	   	int page = getPage("tableId"); //Pagina a mostrar
    	String order = getOrder("tableId"); //Ordenar de modo ascendente o descendente
    	String columnSort = getColumnSort("tableId"); //Columna usada para ordenar
    	
    	if(usuario != null)
    		if(usuario.getNombre() != null && usuario.getNombre().length()<=0)
    			usuario.setNombre(null);
    	    	
    	int inicio = (page-1)*PAGESIZE;
    	boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
    	PaginatedList<UsuarioBean> result = servicioUsuario.getUsuarios(inicio,(export)?-1:PAGESIZE,order,columnSort,usuario); 
    	Integer totalSize = result.getTotalList();
    	
    	listaUsuarios =  result.getPageList();
    	
    	//Atributos de request
    	getRequest().setAttribute(REQUEST_ATTRIBUTE_TOTALSIZE, totalSize);
    	
    	if(!export){
    		getRequest().setAttribute(REQUEST_ATTRIBUTE_PAGESIZE, PAGESIZE);
    	}else{
    		getRequest().setAttribute(REQUEST_ATTRIBUTE_PAGESIZE, totalSize);
    	}
    	    	    	
    	if (listaUsuarios!=null && !listaUsuarios.isEmpty())
    	{    		
    		for (int indice=0;indice<listaUsuarios.size();indice++) {
    			
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
	
    public String execute() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
    	
    	int page = getPage("tableId"); //Pagina a mostrar
    	String order = getOrder("tableId"); //Ordenar de modo ascendente o descendente
    	String columnSort = getColumnSort("tableId"); //Columna usada para ordenar
    	
    	if(usuario != null)
    		if(usuario.getNombre() != null && usuario.getNombre().length()<=0)
    			usuario.setNombre(null);
    	    	
    	int inicio = (page-1)*PAGESIZE;
    	boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
    	PaginatedList<UsuarioBean> result = servicioUsuario.getUsuarios(inicio,(export)?-1:PAGESIZE,order,columnSort,usuario); 
    	Integer totalSize = result.getTotalList();
    	
    	listaUsuarios =  result.getPageList();
    	
    	//Atributos de request
    	getRequest().setAttribute(REQUEST_ATTRIBUTE_TOTALSIZE, totalSize);
    	
    	if(!export){
    		getRequest().setAttribute(REQUEST_ATTRIBUTE_PAGESIZE, PAGESIZE);
    	}else{
    		getRequest().setAttribute(REQUEST_ATTRIBUTE_PAGESIZE, totalSize);
    	}
    	    	    	
    	if (listaUsuarios!=null && !listaUsuarios.isEmpty())
    	{    		
    		for (int indice=0;indice<listaUsuarios.size();indice++) {
    			
    			UsuarioBean usuario = listaUsuarios.get(indice);
    			usuario.setNombre(StringEscapeUtils.escapeHtml(usuario.getNombre()));
    			usuario.setLogin(StringEscapeUtils.escapeHtml(usuario.getLogin()));
    		}
    	}
    	    	 	
        return SUCCESS;
    }
    
    public String create() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
    	if(usuario != null){
    		//throw new BusinessException("EL servidor recibido es nulo");
 		    	if(usuario.getIsActivo()!=null&&usuario.getIsActivo().indexOf("activo")!=-1){
 		    		usuario.setActivo(new Integer(1));
		    	}else{
		    		usuario.setActivo(new Integer(0));
		    	}
 		    	if(validUsuario(usuario)&&!existeUsuario(usuario.getLogin())){
 		    		Integer idUsuario = servicioUsuario.newUsuario(usuario);
 		    		this.idUsuario = idUsuario.toString();
 		    		addActionMessageSession(this.getText("plataforma.usuario.create.ok"));
 		    	}else{
 		    		return ERROR;
 		    	}
    	}else{
    		addActionErrorSession(this.getText("plataforma.usuario.create.error"));
    		return ERROR;
    	}
    	return SUCCESS;
    }
    /**
     * Comprueba si existe el usuario. Si existe, muestra un mensaje de error
     * @param loginUsuario
     * @return
     * @throws BusinessException
     */
    private boolean existeUsuario(String loginUsuario) throws BusinessException{
    	boolean sw = servicioUsuario.existeUsuario(loginUsuario);
    	if(sw){
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
     * @param loginUsuario
     * @return
     * @throws BusinessException
     */
    private boolean existeUsuarioEdicion(Integer idUsuario, String loginUsuario) throws BusinessException{
    	boolean sw = servicioUsuario.existeUsuarioEdicion(idUsuario, loginUsuario);
    	if(sw){
    		addFieldErrorSession(this.getText("plataforma.usuario.create.usuarioexistente"));
    	}
    	return sw;
    }

	private boolean validUsuario(UsuarioBean usuario) {
    	boolean sw=true;
		if(usuario!=null&&PlataformaMensajeriaUtil.isEmpty(usuario.getLogin())){
			addFieldErrorSession(this.getText("plataforma.usuario.field.login"));
			sw=false;
		}
		if(usuario!=null&&PlataformaMensajeriaUtil.isEmpty(usuario.getNombre())){
			addFieldErrorSession(this.getText("plataforma.usuario.field.nombre"));
			sw=false;
		}
		if(usuario!=null&&!PlataformaMensajeriaUtil.isEmpty(usuario.getEmail())
				&&!PlataformaMensajeriaUtil.validateEmail(usuario.getEmail())){
			addFieldErrorSession(this.getText("plataforma.usuario.field.email.format"));
			sw=false;
		}
		if(usuario!=null&&PlataformaMensajeriaUtil.isEmptyNumber(usuario.getRolId())){
			addFieldErrorSession(this.getText("plataforma.usuario.field.rolId"));
			sw=false;
		}
		return sw;
	}
	public String update() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
    	UsuarioBean usuarioBBDD = null;
    	if(usuario == null){
    		//throw new BusinessException("EL servidor recibido es nulo");
    		addActionErrorSession(this.getText("plataforma.usuario.update.error"));
    	}else{
		    	System.out.println("[UsuarioAction - IdUsuario] valor == " + usuario.getUsuarioId() );
		    	if(usuario.getUsuarioId()==null){
		    		if(idUsuario!=null){
		    			usuario.setUsuarioId(new Integer(idUsuario));
		    			usuarioBBDD = servicioUsuario.loadUsuario(usuario);
		    		}else{
		    			String idServidor = (String)request.getAttribute("idUsuario");
		    			System.out.println("[UsuarioAction - request.getAttribute('idUsuario)' == " + idServidor);
		    			if(idServidor!=null){
		    				usuario.setId(new Integer(idUsuario));
		    				usuarioBBDD = servicioUsuario.loadUsuario(usuario);
		    			}
		    		}
		    	}else{
		    		usuarioBBDD = servicioUsuario.loadUsuario(usuario);
		    		
		    	}
		    	if(usuarioBBDD!=null){
		    		usuarioBBDD.setUsuarioId(usuario.getUsuarioId());
		    		usuarioBBDD.setNombre(usuario.getNombre());
		    		usuarioBBDD.setLogin(usuario.getLogin());
		    		usuarioBBDD.setActivo(usuario.getActivo());
		    		usuarioBBDD.setEmail(usuario.getEmail());
		    		usuarioBBDD.setRolId(usuario.getRolId());
		    		Integer rolSession =  PlataformaMensajeriaUtil.getRolUsuarioByUsername(usuario.getLogin());
		    		
		    		if(validUsuario(usuarioBBDD)&&!existeUsuarioEdicion(usuarioBBDD.getUsuarioId(), usuarioBBDD.getLogin())){
		    			servicioUsuario.updateUsuario(usuarioBBDD);
		    			addActionMessageSession(this.getText("plataforma.usuario.update.ok"));
		    			
		    			//Si el usuario modificado ha cambiado su rol y no corresponde con el usuario logueado, se cambia el rol en sesion
		    			if(rolSession!=null&&rolSession!=usuario.getRolId()&&usuario.getLogin().equals(PlataformaMensajeriaUtil.getUsuarioLogueado().getUsername())){
			    			PlataformaMensajeriaUtil.changeSessionRol(usuario.getRolId(),request.getSession());
			    		}
		    		}
		    	}	
    	}
    	return SUCCESS;
    	
    }
    
    public String load() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
    	if(idUsuario == null)
    		throw new BusinessException("EL idUsuario recibido es nulo");
    	try {
    		
			usuario = new UsuarioBean();
			usuario.setUsuarioId(new Integer(idUsuario));
			usuario = servicioUsuario.loadUsuario(usuario);
			
			return SUCCESS;
    	} catch (NumberFormatException e) {
			String mensg = this.getText("errors.action.organismo.loadOrganismo",new String[]{usuario.getUsuarioId().toString()});
			throw new BusinessException(mensg);
		} catch (BusinessException e) {
			String mensg = this.getText("errors.action.organismo.loadOrganismo",new String[]{usuario.getUsuarioId().toString()});
			throw new BusinessException(mensg);
		}
    	
    }
    public String delete() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
    	if(idUsuario == null){
    		addActionErrorSession(this.getText("plataforma.usuario.delete.error"));
    	}else{
	    	usuario = new UsuarioBean();
	    	usuario.setUsuarioId(new Integer(idUsuario));
	    	servicioUsuario.deleteUsuario(usuario);
	    	addActionMessageSession(this.getText("plataforma.usuario.delete.ok"));
	  	}
    	return SUCCESS;
    	
    }
    
    public String deleteSelected() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
    	if(checkDelList == null){
    		//throw new BusinessException("No se ha seleccionado ningún servidor para eliminar");
    		addActionErrorSession(this.getText("plataforma.usuario.deleteselected.error"));
    	}else{
	    	for(String idUsuario : checkDelList){
		    	usuario = new UsuarioBean();
		    	usuario.setUsuarioId(new Integer(idUsuario));
		    	servicioUsuario.deleteUsuario(usuario);
	    	}
	    	addActionMessageSession(this.getText("plataforma.usuario.deleteselected.ok"));
    	}
    	return SUCCESS;
    	
    }    
    
    public String addUsuarioAplicacion() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
    	boolean sw = true;
    	if(validaCampos(usuarioAplicacion)){
    		try{
    		Integer id = servicioUsuarioAplicacion.newUsuarioAplicacion(usuarioAplicacion);
    		
    		
    		}catch (Exception e) {
				// TODO: handle exception
    			addActionErrorSession(this.getText("plataforma.usuario.add.usuarioaplicacion.erro"));
    			sw=false;
			}
    		if(sw){
    			idUsuario = usuarioAplicacion.getUsuarioId().toString();
    			UsuarioBean usuario = new UsuarioBean();
    			usuario.setUsuarioId(usuarioAplicacion.getUsuarioId());
    			UsuarioBean usuBean = servicioUsuario.loadUsuario(usuario);
    			usuBean.setModificadoPor(PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto());
    			usuBean.setFechaModificacion(new Date());
    			servicioUsuario.updateUsuario(usuBean);
    			addActionMessageSession(this.getText("plataforma.usuario.add.usuarioaplicacion.ok"));
    		}
    	}else{
    		
    		//addActionErrorSession(this.getText("plataforma.usuario.add.usuarioaplicacion.error"));
    	}
    	return SUCCESS;
    }
    
    
    private boolean validaCampos(UsuarioAplicacionBean usuarioAplicacion2) {
    	boolean sw = true;
		if(PlataformaMensajeriaUtil.isEmptyNumber(usuarioAplicacion.getAplicacionId())){
			addActionErrorSession(this.getText("plataforma.usuario.add.usuarioaplicacion.field.aplicacionid.error"));
			sw=false;
		}
		if(PlataformaMensajeriaUtil.isEmptyNumber(usuarioAplicacion.getModo())){
			addActionErrorSession(this.getText("plataforma.usuario.add.usuarioaplicacion.field.modo.error"));
			sw=false;
		}
		
		return sw;
	}
	public String deleteUsuarioAplicacion() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
    	if(!PlataformaMensajeriaUtil.isEmpty(usuarioAplicacionId)&&!PlataformaMensajeriaUtil.isEmpty(idUsuario)){
    		UsuarioAplicacionBean usuarioAplicacionBean = new UsuarioAplicacionBean();
    		usuarioAplicacionBean.setUsuarioAplicacionId(new Integer(usuarioAplicacionId));
    		UsuarioBean usuario = new UsuarioBean();
    		usuario.setUsuarioId(new Integer(idUsuario));
    		UsuarioBean usuBean = servicioUsuario.loadUsuario(usuario);
			usuBean.setModificadoPor(PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto());
			usuBean.setFechaModificacion(new Date());
			servicioUsuario.updateUsuario(usuBean);
    		servicioUsuarioAplicacion.deleteUsuarioAplicacion(usuarioAplicacionBean);
    		addActionMessageSession(this.getText("plataforma.usuario.delete.usuarioaplicacion.ok"));
    	}else{
    		addActionErrorSession(this.getText("plataforma.usuario.delete.usuarioaplicacion.error"));
    	}
    	return SUCCESS;
    }
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<KeyValueObject> getComboAplicacionesNoAsignadas(String idUsuario) {
        List<KeyValueObject> result = new ArrayList<KeyValueObject>();
        KeyValueObject option = null;
        //TODO: DEVOLVER SOLO LOS PARAMETROS ACTIVOS!!!!
        ArrayList<AplicacionBean> keys = null;
		try {
			keys = (ArrayList<AplicacionBean>)servicioAplicacion.getAplicacionesNoAsignadas(idUsuario);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
		if(keys!=null&&keys.size()>0)
        for (AplicacionBean key :keys) {
            
            option = new KeyValueObject();
            option.setCodigo(key.getAplicacionId().toString());
            option.setDescripcion(key.getNombre());
            result.add(option);
        }
        return result;
    } 
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<KeyValueObject> getComboAplicaciones() {
        List<KeyValueObject> result = new ArrayList<KeyValueObject>();
        KeyValueObject option = null;
        //TODO: DEVOLVER SOLO LOS PARAMETROS ACTIVOS!!!!
        ArrayList<AplicacionBean> keys = null;
		try {
			keys = (ArrayList<AplicacionBean>)servicioAplicacion.getAplicaciones();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
		if(keys!=null&&keys.size()>0)
        for (AplicacionBean key :keys) {
            
            option = new KeyValueObject();
            option.setCodigo(key.getAplicacionId().toString());
            option.setDescripcion(key.getNombre());
            result.add(option);
        }
        return result;
    }
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<KeyValueObject> getComboRoles() {
        List<KeyValueObject> result = new ArrayList<KeyValueObject>();
        KeyValueObject option = null;
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<KeyValueObject> getComboModos() {
        List<KeyValueObject> result = new ArrayList<KeyValueObject>();
        KeyValueObject option = null;
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
	@Override
	public void prepare() throws Exception {
		//contextUsuarios = getComboValues();
		if(!PlataformaMensajeriaUtil.isEmpty(idUsuario)){
			comboAplicacionesNoAsignadas=getComboAplicacionesNoAsignadas(idUsuario);
			listaUsuarioAplicaciones = servicioUsuarioAplicacion.getUsuarioAplicacionesByUsuarioId(new Integer(idUsuario));
		}
	
			
//		}else{
//			comboAplicaciones = getComboAplicaciones();
//		}
//			comboRoles = getComboRoles();
		
			
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

	public boolean isEmpty(String value){
		if(value==null||(value!=null&&value.equals(""))){
			return true;
		}else{
			return false;
		}
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
	public void setComboAplicacionesNoAsignadas(
			List<KeyValueObject> comboAplicacionesNoAsignadas) {
		this.comboAplicacionesNoAsignadas = comboAplicacionesNoAsignadas;
	}
	public void setComboModos(List<KeyValueObject> comboModos) {
		this.comboModos = comboModos;
	}
	List<UsuarioAplicacionBean> listaUsuarioAplicaciones = new ArrayList<UsuarioAplicacionBean>();
	
	public List<UsuarioAplicacionBean> getListaUsuarioAplicaciones() {
		return listaUsuarioAplicaciones;
	}
	public void setListaUsuarioAplicaciones(
			List<UsuarioAplicacionBean> listaUsuarioAplicaciones) {
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
	/**
	 * Método que resuelve el lugar donde tiene que volver
	 */
	public String getVolver() {
		String volver="buscarUsuarios.action";
		if(!PlataformaMensajeriaUtil.isEmpty(from)&&!PlataformaMensajeriaUtil.isEmpty(idFrom)){
			volver=from+"?"+var+"="+idFrom;
		}
		return volver;
	}
}
