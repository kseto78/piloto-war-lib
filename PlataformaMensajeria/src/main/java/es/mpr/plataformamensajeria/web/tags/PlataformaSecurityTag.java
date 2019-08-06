package es.mpr.plataformamensajeria.web.tags;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import es.mpr.plataformamensajeria.servicios.ifaces.ServicioUsuario;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

/**
 * Clase PlataformaSecurityTag.
 */
public class PlataformaSecurityTag extends BodyTagSupport {
	 
 	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**  servicio usuario. */
	private ServicioUsuario servicioUsuario;
	
	
	/**  usuario logueado. */
	private String usuarioLogueado = "";
	
	/**  usuario nombre. */
	private String usuarioNombre = "";
	
	/**  show if granted. */
	private String showIfGranted="";
	
	/**  show if not granted. */
	private String showIfNotGranted="";

       /* (non-Javadoc)
        * @see javax.servlet.jsp.tagext.BodyTagSupport#doStartTag()
        */
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
         if(rolUsuarioId!=null&&(rolUsuarioId==1||rolUsuarioId==3)){
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

	/**
	 * Obtener usuario logueado.
	 *
	 * @return usuario logueado
	 */
	public String getUsuarioLogueado() {
		return usuarioLogueado;
	}

	/**
	 * Modificar usuario logueado.
	 *
	 * @param usuarioLogueado new usuario logueado
	 */
	public void setUsuarioLogueado(String usuarioLogueado) {
		this.usuarioLogueado = usuarioLogueado;
	}

	/**
	 * Obtener usuario nombre.
	 *
	 * @return usuario nombre
	 */
	public String getUsuarioNombre() {
		return usuarioNombre;
	}

	/**
	 * Modificar usuario nombre.
	 *
	 * @param usuarioNombre new usuario nombre
	 */
	public void setUsuarioNombre(String usuarioNombre) {
		this.usuarioNombre = usuarioNombre;
	}

	/**
	 * Obtener show if granted.
	 *
	 * @return show if granted
	 */
	public String getShowIfGranted() {
		return showIfGranted;
	}

	/**
	 * Modificar show if granted.
	 *
	 * @param showIfGranted new show if granted
	 */
	public void setShowIfGranted(String showIfGranted) {
		this.showIfGranted = showIfGranted;
	}

	/**
	 * Obtener show if not granted.
	 *
	 * @return show if not granted
	 */
	public String getShowIfNotGranted() {
		return showIfNotGranted;
	}

	/**
	 * Modificar show if not granted.
	 *
	 * @param showIfNotGranted new show if not granted
	 */
	public void setShowIfNotGranted(String showIfNotGranted) {
		this.showIfNotGranted = showIfNotGranted;
	}

	/**
	 * Obtener servicio usuario.
	 *
	 * @return the servicioUsuario
	 */
	public ServicioUsuario getServicioUsuario() {
		return servicioUsuario;
	}

	/**
	 * Modificar servicio usuario.
	 *
	 * @param servicioUsuario the servicioUsuario to set
	 */
	public void setServicioUsuario(ServicioUsuario servicioUsuario) {
		this.servicioUsuario = servicioUsuario;
	}

 }
