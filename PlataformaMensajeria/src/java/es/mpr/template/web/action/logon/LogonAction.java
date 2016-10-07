package es.mpr.template.web.action.logon;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.providers.UsernamePasswordAuthenticationToken;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.map.j2ee.security.auth.MapUser;
import com.map.j2ee.security.perm.model.User060VO;
import com.map.j2ee.util.Constants;
import com.opensymphony.xwork2.ActionSupport;

import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

public class LogonAction extends ActionSupport implements ServletRequestAware{
		
	private static final long serialVersionUID = -2273409755502204354L;
	
	private static final String AUTENTICA_USER_XML = "AUTENTICA_USER_XML";
	private static final String OK = "OK";
	private static final String ETIQUETA_RESULTADO = "resultado";
	private static final String ETIQUETA_USERNAME = "userName";
	
	protected HttpServletRequest request;
	private static Logger logger = Logger.getLogger(LogonAction.class);
	
    public String permisosMenu() throws Exception {
    	
    	//Si el usuario no esta en sesion, se intenta localizar el fichero XML de regreso de AutenticA
    	if(request.getSession().getAttribute("infoUser")==null){
    		
    		if(request.getParameter(AUTENTICA_USER_XML)==null || ("").equals(request.getParameter(AUTENTICA_USER_XML))){
    			logger.error("AutenticA - Error: No existe el fichero AUTENTICA_USER_XML como parametro.");
    			return ERROR;
    		}
        		
    		String xml_user_autentica = request.getParameter(AUTENTICA_USER_XML);
    		if (logger.isDebugEnabled()) {
    			logger.debug("[LogonAction] - xmlAutentica: " + xml_user_autentica);
    		}
    		
    		try{
    			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    			InputSource src = new InputSource();
    			src.setCharacterStream(new StringReader(xml_user_autentica));

    			Document doc = builder.parse(src);
    			
    			String resultado = doc.getElementsByTagName(ETIQUETA_RESULTADO).item(0).getTextContent();
    			
    			if(resultado!=null&&resultado.equals(OK)){
    				
    				String userName = doc.getElementsByTagName(ETIQUETA_USERNAME).item(0).getTextContent();
    				
    				if(userName!=null&&userName!=null){
    					Integer rolUsuarioId = PlataformaMensajeriaUtil.getRolUsuarioByUsername(userName);
    			    	Integer idUsuario = PlataformaMensajeriaUtil.getIdByUsername(userName);
    			    	if(idUsuario==null||(idUsuario!=null&&idUsuario==-1)){
    			    		return ERROR;
    			    	}
    			    	if(rolUsuarioId==null||(idUsuario!=null&&rolUsuarioId==-1)){
    			    		return ERROR;
    			    	}	    	
    			    	if(rolUsuarioId!=null&&rolUsuarioId == 1){
    			    		request.getSession().setAttribute(PlataformaMensajeriaUtil.ROL_USUARIO_PLATAFORMA, PlataformaMensajeriaUtil.ROL_ADMINISTRADOR);
    			    	}else if(rolUsuarioId!=null&&rolUsuarioId == 2){
    			    		request.getSession().setAttribute(PlataformaMensajeriaUtil.ROL_USUARIO_PLATAFORMA, PlataformaMensajeriaUtil.ROL_PROPIETARIO);
    			    	}
    			    	request.getSession().setAttribute(PlataformaMensajeriaUtil.ID_ROL_USUARIO_PLATAFORMA, rolUsuarioId);
    			    	request.getSession().setAttribute(PlataformaMensajeriaUtil.ID_USUARIO_LOGUEADO, idUsuario);
    	
    			    	request.getSession().setAttribute(PlataformaMensajeriaUtil.USERNAME, userName);
    			    	
    			    	User060VO usuario = new User060VO();
    					usuario.setUsername(userName);
    					usuario.setNombre(doc.getElementsByTagName("givenName").item(0).getTextContent());
    					usuario.setApellidos(doc.getElementsByTagName("sn").item(0).getTextContent());
    					
    					MapUser springUser = new MapUser(userName, "password", true, getAuthorities(usuario, rolUsuarioId), usuario);
    			        
    			        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(springUser, null));
    			        
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
	
	protected GrantedAuthority[] getAuthorities(User060VO usuario, Integer rolUsuarioId)
	  {
	    List<GrantedAuthority> authList = new ArrayList(2);
	    
	    authList.add(new GrantedAuthorityImpl("ROLE_"));
	    String role = new String();
	    if(rolUsuarioId==1){
	    	role = "ROLE_ADMINISTRADOR";
	    }
	    if(rolUsuarioId==2){
	    	role = "ROLE_PROPIETARIO";
	    }
	    authList.add(new GrantedAuthorityImpl(role));
	    
	    return (GrantedAuthority[])authList.toArray(new GrantedAuthority[0]);
	  }
    
}
