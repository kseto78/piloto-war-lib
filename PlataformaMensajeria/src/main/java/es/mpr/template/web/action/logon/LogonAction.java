package es.mpr.template.web.action.logon;

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
//import com.map.j2ee.security.perm.model.User060VO;
import com.map.j2ee.util.Constants;
import com.opensymphony.xwork2.ActionSupport;

import es.minhap.plataformamensajeria.iop.manager.TblUsuariosManager;
import es.mpr.plataformamensajeria.util.MapUser;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaProperties;

@Controller("logonAction")
@Scope("prototype")
public class LogonAction extends ActionSupport implements ServletRequestAware{
		
	private static final long serialVersionUID = -2273409755502204354L;
	
	private static Logger logger = Logger.getLogger(LogonAction.class);
	
		
	@Resource(name="tblUsuariosManagerImpl")
	private TblUsuariosManager tblUsuariosManager;
	
	@Resource(name = "plataformaMensajeriaProperties")
	private PlataformaMensajeriaProperties properties;
	
	protected HttpServletRequest request;
	
	
    public String permisosMenu() throws Exception {
    	String autenticaUserXML = properties.getProperty("logonAction.AUTENTICA_USER_XML",null);
    	String etiquetaResultado = properties.getProperty("logonAction.ETIQUETA_RESULTADO",null);
    	String etiquetaUsername = properties.getProperty("logonAction.ETIQUETA_USERNAME",null);
    	String expresionOK = properties.getProperty("logonAction.OK",null);

    	//Si el usuario no esta en sesion, se intenta localizar el fichero XML de regreso de AutenticA
    	if(request.getSession().getAttribute("infoUser")==null){
    		
    		if(request.getParameter(autenticaUserXML)==null || ("").equals(request.getParameter(autenticaUserXML))){
    			logger.error("AutenticA - Error: No existe el fichero AUTENTICA_USER_XML como parametro.");
    			return ERROR;
    		}
        		
    		String xml_user_autentica = request.getParameter(autenticaUserXML);
    		if (logger.isDebugEnabled()) {
    			logger.debug("[LogonAction] - xmlAutentica: " + xml_user_autentica);
    		}
    		
    		try{
    			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    			InputSource src = new InputSource();
    			src.setCharacterStream(new StringReader(xml_user_autentica));

    			Document doc = builder.parse(src);
    			
    			String resultado = doc.getElementsByTagName(etiquetaResultado).item(0).getTextContent();
    			
    			if(resultado!=null&&resultado.equals(expresionOK)){
    				
    				String userName = doc.getElementsByTagName(etiquetaUsername).item(0).getTextContent();
    				
    				if(userName!=null&&userName!=null){
    					Integer rolUsuarioId = tblUsuariosManager.getRolByUsername(userName);
    			    	Integer idUsuario = tblUsuariosManager.getUsuarioByUsername(userName).intValue();
    			    	
    			    	if(idUsuario==null||(idUsuario!=null&&idUsuario==-1)){
    			    		return ERROR;
    			    	}
    			    	if(rolUsuarioId==null||(idUsuario!=null&&rolUsuarioId==-1)){
    			    		return ERROR;
    			    	}	    	
    			    	if(rolUsuarioId!=null&&rolUsuarioId == 1){
    			    		request.getSession().setAttribute(properties.getProperty("PlataformaMensajeriaUtil.ROL_USUARIO_PLATAFORMA",null), 
    			    				properties.getProperty("PlataformaMensajeriaUtil.ROL_ADMINISTRADOR",null));

    			    	}else if(rolUsuarioId!=null&&rolUsuarioId == 2){
    			    		request.getSession().setAttribute(properties.getProperty("PlataformaMensajeriaUtil.ROL_USUARIO_PLATAFORMA",null),
    			    				properties.getProperty("PlataformaMensajeriaUtil.ROL_PROPIETARIO",null));

    			    	}
    			    	request.getSession().setAttribute(properties.getProperty("PlataformaMensajeriaUtil.ID_ROL_USUARIO_PLATAFORMA",null), rolUsuarioId);
    			    	request.getSession().setAttribute(properties.getProperty("PlataformaMensajeriaUtil.ID_USUARIO_LOGUEADO",null), idUsuario);
    	
    			    	request.getSession().setAttribute(properties.getProperty("PlataformaMensajeriaUtil.USERNAME",null), userName);
    			    	
    			    	User060VO usuario = new User060VO();
    					usuario.setUsername(userName);
    					usuario.setNombre(doc.getElementsByTagName("givenName").item(0).getTextContent());
    					usuario.setApellidos(doc.getElementsByTagName("sn").item(0).getTextContent());
    					
    					MapUser springUser = new MapUser(userName, "password", true, getAuthorities2(usuario, rolUsuarioId), usuario);
//    			        
    			        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(springUser, null, getAuthorities2(usuario, rolUsuarioId), null, null, null, null, null));
//    			        
    			        request.getSession().setAttribute("infoUser", springUser);
    			        

    			    	
    				}
    				if( request.getSession().getAttribute(Constants.STRUTS2SESSIONLOCALEKEY) == null){
    		    		//Si no recibimos locale seteamos el locale por defecto.
    		    		Locale locale= new Locale(Constants.FWKDEFAULTLOCALELANG,Constants.FWKDEFAULTLOCALECOUNT);
    		    	    request.getSession().setAttribute(Constants.STRUTS2SESSIONLOCALEKEY, locale);
    		    	}
    		    	    	       
    		       	if(logger.isDebugEnabled())
    		       		logger.debug("en sesion el locale vale----" + request.getSession().getAttribute(Constants.STRUTS2SESSIONLOCALEKEY).toString());		
    				
    				
    			} else {
    				return ERROR;
    			}
    	       	
    			return SUCCESS;
    			
    		} catch (Exception e) {
    			
    			logger.error("AutenticA - Excepcion - " + e.getMessage());
    			e.printStackTrace();
    			
    			return ERROR;
    		}

    	}
    	
    	return SUCCESS;
    	
    }
 
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public HttpServletRequest getServletRequest() {
		return request;
	}
	
	protected List<GrantedAuthority> getAuthorities2(User060VO usuario, Integer rolUsuarioId)
	  {
	    List<GrantedAuthority> authList = new ArrayList<>(2);
	    
	    authList.add(new GrantedAuthorityImpl("ROLE_"));
	    String role = new String();
	    if(rolUsuarioId==1){
	    	role = "ROLE_ADMINISTRADOR";
	    }
	    if(rolUsuarioId==2){
	    	role = "ROLE_PROPIETARIO";
	    }
	    authList.add(new GrantedAuthorityImpl(role));
	    
	    return authList;
	  }

	/**
	 * @return the tblUsuariosManager
	 */
	public TblUsuariosManager getTblUsuariosManager() {
		return tblUsuariosManager;
	}

	/**
	 * @param tblUsuariosManager the tblUsuariosManager to set
	 */
	public void setTblUsuariosManager(TblUsuariosManager tblUsuariosManager) {
		this.tblUsuariosManager = tblUsuariosManager;
	}

	
    
}
