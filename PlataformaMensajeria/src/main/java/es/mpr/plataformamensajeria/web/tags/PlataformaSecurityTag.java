package es.mpr.plataformamensajeria.web.tags;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import es.mpr.plataformamensajeria.servicios.ifaces.ServicioUsuario;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

public class PlataformaSecurityTag extends BodyTagSupport {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ServicioUsuario servicioUsuario;
	
	
	private String usuarioLogueado = "";
	private String usuarioNombre = "";
	private String showIfGranted="";
	private String showIfNotGranted="";

       public int doStartTag() throws JspException {
    	 boolean skipBody=false;

    	 

    	 //Verificar tipo de comprobación
    	 // 	* Usuario Logueado
    	 //		* Usuario pasado por parámetro
    	   Integer rolUsuarioId = new Integer(-1);
    	 if(usuarioLogueado!=null&&usuarioLogueado.equals("true")){
    		 String  rolUsuario = (String)pageContext.getSession().getAttribute(PlataformaMensajeriaUtil.ROL_USUARIO_PLATAFORMA);
    		 if(rolUsuario==null){
    			 return Tag.SKIP_BODY;
    		 }
    		 rolUsuarioId = PlataformaMensajeriaUtil.ROLES.get(rolUsuario);

    	 }else if(usuarioNombre!=null&&usuarioNombre.length()>0){
    		 ServletContext servletContext = pageContext.getServletContext();
        	 WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        	 servicioUsuario = (ServicioUsuario)context.getBean("servicioUsuarioImpl");
    		 rolUsuarioId = PlataformaMensajeriaUtil.getRolUsuarioByUsername(usuarioNombre, servicioUsuario);
    		 
    	 }
         if(rolUsuarioId!=null&&rolUsuarioId==1){
        	 //Tiene permisos de administrador, por lo que hay que comprobar si en el tag se indica que se muestre o no
        	 if(showIfGranted!=null&&"false".equals(showIfGranted)){
        		 skipBody=true;
        	 }else{
        		 skipBody=false; //por de fecto se muestra siempre a no ser que se indique lo contrario
        	 }
         }else{
        	//No tiene permisos de administrador, por lo que hay que comprobar si en el tag se indica que se muestre o no
        	 if(showIfNotGranted!=null&&"true".equals(showIfNotGranted)){
        		 skipBody=false;
        	 }else{
        		 skipBody=true; // Si no tiene permisos siempre se oculta el contenido a no ser que se indique lo contrario
        	 }
         }
         if(skipBody){
        	 return Tag.SKIP_BODY;
         }
    	 return Tag.EVAL_BODY_INCLUDE;

     }

	public String getUsuarioLogueado() {
		return usuarioLogueado;
	}

	public void setUsuarioLogueado(String usuarioLogueado) {
		this.usuarioLogueado = usuarioLogueado;
	}

	public String getUsuarioNombre() {
		return usuarioNombre;
	}

	public void setUsuarioNombre(String usuarioNombre) {
		this.usuarioNombre = usuarioNombre;
	}

	public String getShowIfGranted() {
		return showIfGranted;
	}

	public void setShowIfGranted(String showIfGranted) {
		this.showIfGranted = showIfGranted;
	}

	public String getShowIfNotGranted() {
		return showIfNotGranted;
	}

	public void setShowIfNotGranted(String showIfNotGranted) {
		this.showIfNotGranted = showIfNotGranted;
	}

	/**
	 * @return the servicioUsuario
	 */
	public ServicioUsuario getServicioUsuario() {
		return servicioUsuario;
	}

	/**
	 * @param servicioUsuario the servicioUsuario to set
	 */
	public void setServicioUsuario(ServicioUsuario servicioUsuario) {
		this.servicioUsuario = servicioUsuario;
	}

 }
