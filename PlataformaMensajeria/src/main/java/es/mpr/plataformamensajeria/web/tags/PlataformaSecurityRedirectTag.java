package es.mpr.plataformamensajeria.web.tags;

import java.util.HashMap;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

public class PlataformaSecurityRedirectTag extends TagSupport
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String redirectTo="";
	private String allowedTo="";
	private String isAction="";
	private String permisoIdAplicacion="";

	public int doStartTag() throws JspException {
		String rolUsuario = (String) pageContext.getSession().getAttribute(PlataformaMensajeriaUtil.ROL_USUARIO_PLATAFORMA);
		if(PlataformaMensajeriaUtil.isEmpty(rolUsuario)){
			return Tag.EVAL_BODY_INCLUDE;
		}
		if(!PlataformaMensajeriaUtil.isEmpty(permisoIdAplicacion)){
			HashMap<Integer,Integer> mapPermisosAplicaciones =(HashMap<Integer, Integer>)pageContext.getSession().getAttribute(PlataformaMensajeriaUtil.MAP_PERMISOS_APLICACIONES); 
			Integer idAplicacion = Integer.parseInt(permisoIdAplicacion);
			
			if(!mapPermisosAplicaciones.containsKey(idAplicacion)&&!rolUsuario.equals(PlataformaMensajeriaUtil.ROL_ADMINISTRADOR)){
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
	public String getAllowedTo() {
		return allowedTo;
	}

	public void setAllowedTo(String allowedTo) {
		this.allowedTo = allowedTo;
	}

	public String getRedirectTo() {
		return redirectTo;
	}

	public void setRedirectTo(String redirectTo) {
		this.redirectTo = redirectTo;
	}
	 public String getIsAction() {
		return isAction;
	}
	public void setIsAction(String isAction) {
		this.isAction = isAction;
	}	
	public String getPermisoIdAplicacion() {
		return permisoIdAplicacion;
	}
	public void setPermisoIdAplicacion(String permisoIdAplicacion) {
		this.permisoIdAplicacion = permisoIdAplicacion;
	}	
}
