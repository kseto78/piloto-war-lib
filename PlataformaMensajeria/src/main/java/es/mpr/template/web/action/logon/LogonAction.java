package es.mpr.template.web.action.logon;

import java.io.FileInputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.map.j2ee.security.perm.model.User060VO;
import com.map.j2ee.util.Constants;
import com.opensymphony.xwork2.ActionSupport;

import es.minhap.plataformamensajeria.iop.manager.TblUsuariosAplicacionesManager;
import es.minhap.plataformamensajeria.iop.manager.TblUsuariosManager;
import es.minhap.sim.model.TblUsuarios;
import es.minhap.sim.model.TblUsuariosAplicaciones;
import es.minhap.sim.query.TblUsuariosAplicacionesQuery;
import es.minhap.sim.query.TblUsuariosQuery;
import es.mpr.plataformamensajeria.util.MapUser;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaProperties;
import es.sag.autentica.sign.ValidateSign;

/**
 * The Class LogonAction.
 */
@Controller("logonAction")
@Scope("prototype")
public class LogonAction extends ActionSupport implements ServletRequestAware{
		
	protected static final String INFOUSER = "infoUser";

	protected static final String ORGANISMO_AGREG = "ORGANISMO AGREGADO AL USUARIO: ";

	protected static final String BLANKIGUAL_A_AP = " Igual a Aplicacion ";

	protected static final String OK = "OK";

	protected static final String PLATAFORMAMENSA = "PlataformaMensajeriaUtil.ROL_USUARIO_PLATAFORMA";

	protected static final String ETIQUETAORGANIS = "etiquetaOrganismos ";

	protected static final String PLATAFORMAMENSA0 = "PlataformaMensajeriaUtil.ID_APLICACION_AEAT";

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -2273409755502204354L;
	
	/**  logger. */
	private static Logger logger = Logger.getLogger(LogonAction.class);
	
		
	/**  tbl usuarios manager. */
	@Resource(name="tblUsuariosManagerImpl")
	private TblUsuariosManager tblUsuariosManager;
	
	/**  tbl usuarios aplicaciones manager. */
	@Resource(name="TblUsuariosAplicacionesManagerImpl")
	private TblUsuariosAplicacionesManager tblUsuariosAplicacionesManager;
	
	/**  properties. */
	@Resource(name = "plataformaMensajeriaProperties")
	private PlataformaMensajeriaProperties properties;
	
	/**  request. */
	protected HttpServletRequest request;
	
	
    /**
     * Permisos menu.
     *
     * @return the string
     * @throws Exception the exception
     */
    public String permisosMenu() throws Exception {
    	String autenticaUserXML = properties.getProperty("logonAction.AUTENTICA_USER_XML",null);
    	String etiquetaResultado = properties.getProperty("logonAction.ETIQUETA_RESULTADO",null);
    	String etiquetaUsername = properties.getProperty("logonAction.ETIQUETA_USERNAME",null);
    	String etiquetaOrganismo = properties.getProperty("logonAction.ETIQUETA_ORGANISMO",null);
    	String rutaCertificadoAutentica = properties.getProperty("logonAction.RUTA_CERTIFICADO_AUTENTICA",null);
    	
    	properties.getProperty("logonAction.ETIQUETA_PUESTOS",null);
    	
    	String expresionOK = properties.getProperty("logonAction.OK",null);        

    	//Si el usuario no esta en sesion, se intenta localizar el fichero XML de regreso de AutenticA
    	if(request.getSession().getAttribute(INFOUSER)==null){
    		
    		if(request.getParameter(autenticaUserXML)==null || "".equals(request.getParameter(autenticaUserXML))){
    			logger.error("AutenticA - Error: No existe el fichero AUTENTICA_USER_XML como parametro.");
    			return ERROR;
    		}
        		
    		FileInputStream cerAutentica  =  new FileInputStream (rutaCertificadoAutentica);
    		
    		String xmlUserAutentica = request.getParameter(autenticaUserXML);
    		if (logger.isDebugEnabled()) {
    			logger.debug("[LogonAction] - xmlAutentica: " + xmlUserAutentica);
    		}

    		ValidateSign val = new ValidateSign();
    		String isValid = val.validateSign(xmlUserAutentica, cerAutentica);
    		
    		cerAutentica.close();
    		
    		if(!OK.equals(isValid)){
    			return ERROR;
    		}
    		try{
    			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    			InputSource src = new InputSource();
    			src.setCharacterStream(new StringReader(xmlUserAutentica));

    			Document doc = builder.parse(src);
    			
    			String resultado = doc.getElementsByTagName(etiquetaResultado).item(0).getTextContent();
    			
    			if(resultado != null && resultado.equals(expresionOK)){
    				
    				String userName = doc.getElementsByTagName(etiquetaUsername).item(0).getTextContent();
    				
    				if(userName != null && userName != null){
    					Integer rolUsuarioId = tblUsuariosManager.getRolByUsername(userName);    			    	
    					Integer idUsuario = tblUsuariosManager.getUsuarioByUsernameActivo(userName).intValue();
    			    	
    			    	TblUsuariosAplicacionesQuery query = new TblUsuariosAplicacionesQuery();
    			    	TblUsuariosQuery usuarioQuery = new TblUsuariosQuery();
    			    	if(idUsuario!=null){
    			    		usuarioQuery.setUsuarioid((long)idUsuario);
        			    	query.setTblUsuarios(usuarioQuery);
        			    	List<TblUsuariosAplicaciones> listUsuarioAplicaciones = tblUsuariosAplicacionesManager.getUsuariosAplicacionesByQuery(query);
        			    	
        			    	logger.debug("listUsuarioAplicaciones "+listUsuarioAplicaciones.size());		    	
        			    	if(listUsuarioAplicaciones!=null && !listUsuarioAplicaciones.isEmpty()){
        			    		List<String> arrayOrganismos = new ArrayList<>();
        			    		for (TblUsuariosAplicaciones usuarioAplicacion : listUsuarioAplicaciones){
        			    			logger.debug("usuarioAplicacion "+usuarioAplicacion.getAplicacionid() +" Aplicacion" + usuarioAplicacion.getAplicacionid());
        			    			if(usuarioAplicacion.getAplicacionid()!=null){
        			    				logger.debug("ID_APLICACION_AEAT "+
        			    					properties.getProperty(PLATAFORMAMENSA0, null +
        			    						BLANKIGUAL_A_AP + usuarioAplicacion.getAplicacionid()));
        		    			    	if(properties.getProperty(PLATAFORMAMENSA0, null).equals(usuarioAplicacion.getAplicacionid().toString())){
        		    			    		logger.debug(ETIQUETAORGANIS+etiquetaOrganismo);
        		    			    		request.getSession().setAttribute(properties.getProperty("PlataformaMensajeriaUtil.ES_AEAT",null), OK);
        		    			    		TblUsuarios usuario = tblUsuariosManager.getById(Long.valueOf(idUsuario));
        		    			    		if(null != usuario.getOrganismo() && !"".equals(usuario.getOrganismo())){
        		    			    			arrayOrganismos.add(usuario.getOrganismo());
        		    			    			logger.info(ORGANISMO_AGREG+usuario.getOrganismo());
        		    			    		}
        		    			    	}
        			    				logger.debug("ID_APLICACION_GISS "+
        			    					properties.getProperty(PLATAFORMAMENSA0, null +
        			    						BLANKIGUAL_A_AP + usuarioAplicacion.getAplicacionid()));
        		    			    	if(properties.getProperty("PlataformaMensajeriaUtil.ID_APLICACION_GISS", null).equals(usuarioAplicacion.getAplicacionid().toString())){
        		    			    		logger.debug(ETIQUETAORGANIS+etiquetaOrganismo);
        		    			    		request.getSession().setAttribute(properties.getProperty("PlataformaMensajeriaUtil.ES_GISS",null), OK);
        		    			    		TblUsuarios usuario = tblUsuariosManager.getById(Long.valueOf(idUsuario));
        		    			    		if(null != usuario.getOrganismo() && !"".equals(usuario.getOrganismo())){
        		    			    			arrayOrganismos.add(usuario.getOrganismo());
        		    			    			logger.info(ORGANISMO_AGREG+usuario.getOrganismo());
        		    			    		}
        		    			    	}
        			    			
        			    			}
        			    		}
        			    		logger.debug("arrayOrganismos "+arrayOrganismos);
        			    		request.getSession().setAttribute(properties.getProperty("PlataformaMensajeriaUtil.ARRAY_ORGANISMOS",null), arrayOrganismos);
        			    	}
    			    	}

    			    	if(idUsuario == null || (idUsuario!=null && idUsuario==-1)){
    			    		return ERROR;
    			    	}
    			    	if(rolUsuarioId == null || (idUsuario!=null && rolUsuarioId == -1)){
    			    		return ERROR;
    			    	}	    	
    			    	if(rolUsuarioId != null && rolUsuarioId == 1){
    			    		request.getSession().setAttribute(properties.getProperty(PLATAFORMAMENSA,null), 
    			    				properties.getProperty("PlataformaMensajeriaUtil.ROL_ADMINISTRADOR",null));

    			    	}else if(rolUsuarioId!=null&&rolUsuarioId == 2){
    			    		request.getSession().setAttribute(properties.getProperty(PLATAFORMAMENSA,null),
    			    				properties.getProperty("PlataformaMensajeriaUtil.ROL_PROPIETARIO",null));

    			    	}else if(rolUsuarioId!=null&&rolUsuarioId == 3){
    			    		request.getSession().setAttribute(properties.getProperty(PLATAFORMAMENSA,null),
    			    				properties.getProperty("PlataformaMensajeriaUtil.ROL_CAID",null));

    			    	}
    			    	request.getSession().setAttribute(properties.getProperty("PlataformaMensajeriaUtil.ID_ROL_USUARIO_PLATAFORMA",null), rolUsuarioId);
    			    	request.getSession().setAttribute(properties.getProperty("PlataformaMensajeriaUtil.ID_USUARIO_LOGUEADO",null), idUsuario);
    	
    			    	request.getSession().setAttribute(properties.getProperty("PlataformaMensajeriaUtil.USERNAME",null), userName);

    			    	User060VO usuario = new User060VO();
    					usuario.setUsername(userName);
    					usuario.setNombre(doc.getElementsByTagName("givenName").item(0).getTextContent());
    					usuario.setApellidos(doc.getElementsByTagName("sn").item(0).getTextContent());
    					
    					MapUser springUser = new MapUser(userName, "password", true, getAuthorities2(usuario, rolUsuarioId), usuario);
    			        
    			        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(springUser, null, getAuthorities2(usuario, rolUsuarioId), null, null, null, null, null));
    			        
    			        request.getSession().setAttribute(INFOUSER, springUser);
    				}
    				
    				if( request.getSession().getAttribute(Constants.STRUTS2SESSIONLOCALEKEY) == null){
    		    		//Si no recibimos locale seteamos el locale por defecto.
    		    		Locale locale= new Locale(Constants.FWKDEFAULTLOCALELANG,Constants.FWKDEFAULTLOCALECOUNT);
    		    	    request.getSession().setAttribute(Constants.STRUTS2SESSIONLOCALEKEY, locale);
    		    	}
    		    	    	       
    		       	if(logger.isDebugEnabled()) {
						logger.debug("en sesion el locale vale----" + request.getSession().getAttribute(Constants.STRUTS2SESSIONLOCALEKEY));
					}		
    				
    				
    			} else {
    				return ERROR;
    			}
    	       	
    			return SUCCESS;
    			
    		} catch (Exception e) {
    			
    			logger.error("AutenticA - Excepcion - " + e.getMessage());
    			return ERROR;
    		}

    	}
    	
    	return SUCCESS;
    	
    }
 
	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.ServletRequestAware#setServletRequest(javax.servlet.http.HttpServletRequest)
	 */
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	/**
	 * Obtener servlet request.
	 *
	 * @return servlet request
	 */
	public HttpServletRequest getServletRequest() {
		return request;
	}
	
	/**
	 * Obtener authorities 2.
	 *
	 * @param usuario the usuario
	 * @param rolUsuarioId the rol usuario id
	 * @return authorities 2
	 */
	protected List<GrantedAuthority> getAuthorities2(User060VO usuario, Integer rolUsuarioId) {
	    List<GrantedAuthority> authList = new ArrayList<>(2);
	    
	    
	    authList.add(new GrantedAuthorityImpl("ROLE_"));
	    String role = "";
	    if(rolUsuarioId==1){
	    	role = "ROLE_ADMINISTRADOR";
	    }
	    if(rolUsuarioId==2){
	    	role = "ROLE_PROPIETARIO";
	    }
	    
	    if(rolUsuarioId==3){
	    	role = "ROLE_CAID";
	    }
	    authList.add(new GrantedAuthorityImpl(role));
	    
	    return authList;
	  }

	/**
	 * Obtener tbl usuarios manager.
	 *
	 * @return the tblUsuariosManager
	 */
	public TblUsuariosManager getTblUsuariosManager() {
		return tblUsuariosManager;
	}

	/**
	 * Modificar tbl usuarios manager.
	 *
	 * @param tblUsuariosManager the tblUsuariosManager to set
	 */
	public void setTblUsuariosManager(TblUsuariosManager tblUsuariosManager) {
		this.tblUsuariosManager = tblUsuariosManager;
	}

	
    
}
