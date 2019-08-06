package es.mpr.plataformamensajeria.web.tags;

import java.util.HashMap;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

/**
 * The Class PlataformaSecurityRedirectTag.
 */
public class PlataformaSecurityRedirectTag extends TagSupport
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**  redirect to. */
	private String redirectTo="";
	
	/**  allowed to. */
	private String allowedTo="";
	
	/**  is action. */
	private String isAction="";
	
	/**  permiso id aplicacion. */
	private String permisoIdAplicacion="";

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	public int doStartTag() throws JspException {
		String rolUsuario = (String) pageContext.getSession().getAttribute(PlataformaMensajeriaUtil.ROL_USUARIO_PLATAFORMA);
		if(PlataformaMensajeriaUtil.isEmpty(rolUsuario)){
			return Tag.EVAL_BODY_INCLUDE;
		}
		if(!PlataformaMensajeriaUtil.isEmpty(permisoIdAplicacion)){
			@SuppressWarnings("unchecked")
			HashMap<Integer,Integer> mapPermisosAplicaciones =(HashMap<Integer, Integer>)pageContext.getSession().getAttribute(PlataformaMensajeriaUtil.MAP_PERMISOS_APLICACIONES); 
			Integer idAplicacion = Integer.parseInt(permisoIdAplicacion);
			
			if(!mapPermisosAplicaciones.containsKey(idAplicacion)&&(!(rolUsuario.equals(PlataformaMensajeriaUtil.ROL_CAID))&&!rolUsuario.equals(PlataformaMensajeriaUtil.ROL_ADMINISTRADOR))){
				return Tag.EVAL_BODY_INCLUDE;
			}
		}else{
			if(redirectTo!=null&&!"".equals(redirectTo)){
				 
				 if(rolUsuario!=null&&(!rolUsuario.equals(allowedTo)&& !allowedTo.contains(rolUsuario))){
					 return Tag.EVAL_BODY_INCLUDE;
				 }else if(rolUsuario==null||(rolUsuario!=null&&rolUsuario.equals(""))){
					 return Tag.EVAL_BODY_INCLUDE;
				 }
			 } 
		}
			 return Tag.SKIP_BODY;
	}
	
	/**
	 * Obtener allowed to.
	 *
	 * @return allowed to
	 */
	public String getAllowedTo() {
		return allowedTo;
	}

	/**
	 * Modificar allowed to.
	 *
	 * @param allowedTo new allowed to
	 */
	public void setAllowedTo(String allowedTo) {
		this.allowedTo = allowedTo;
	}

	/**
	 * Obtener redirect to.
	 *
	 * @return redirect to
	 */
	public String getRedirectTo() {
		return redirectTo;
	}

	/**
	 * Modificar redirect to.
	 *
	 * @param redirectTo new redirect to
	 */
	public void setRedirectTo(String redirectTo) {
		this.redirectTo = redirectTo;
	}
	 
 	/**
 	 * Obtener checks if is action.
 	 *
 	 * @return checks if is action
 	 */
 	public String getIsAction() {
		return isAction;
	}
	
	/**
	 * Modificar checks if is action.
	 *
	 * @param isAction new checks if is action
	 */
	public void setIsAction(String isAction) {
		this.isAction = isAction;
	}	
	
	/**
	 * Obtener permiso id aplicacion.
	 *
	 * @return permiso id aplicacion
	 */
	public String getPermisoIdAplicacion() {
		return permisoIdAplicacion;
	}
	
	/**
	 * Modificar permiso id aplicacion.
	 *
	 * @param permisoIdAplicacion new permiso id aplicacion
	 */
	public void setPermisoIdAplicacion(String permisoIdAplicacion) {
		this.permisoIdAplicacion = permisoIdAplicacion;
	}	
}
