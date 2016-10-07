package es.mpr.plataformamensajeria.web.tags;

import java.util.HashMap;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;

import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;
import es.mpr.template.web.action.logon.LogonAction;

public class PlataformaAppEditableTag extends BodyTagSupport{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idAplicacion="";
       public int doStartTag() throws JspException {
    	 boolean skipBody=true;
    	 
    	 HashMap<Integer,Integer> mapPermisosAplicaciones =(HashMap<Integer, Integer>)pageContext.getSession().getAttribute(PlataformaMensajeriaUtil.MAP_PERMISOS_APLICACIONES); 
    	 String rolUsuario = (String)pageContext.getSession().getAttribute(PlataformaMensajeriaUtil.ROL_USUARIO_PLATAFORMA);
    	 if(rolUsuario!=null&&rolUsuario.equals(PlataformaMensajeriaUtil.ROL_PROPIETARIO)){
    	 	if(idAplicacion!=null&&!idAplicacion.equals("")&&mapPermisosAplicaciones!=null){
	    		 Integer modoMap = mapPermisosAplicaciones.get(new Integer(idAplicacion));
	    		 if(modoMap!=null&&modoMap.intValue()==2){
	    			 skipBody=false;
	    		 }
	    	 }
    	 }else  if(rolUsuario!=null&&rolUsuario.equals(PlataformaMensajeriaUtil.ROL_ADMINISTRADOR)){
    		 skipBody=false;
    	 }
         if(skipBody){
        	 return Tag.SKIP_BODY;
         }
    	 return Tag.EVAL_BODY_INCLUDE;

     }
	public String getIdAplicacion() {
		return idAplicacion;
	}
	public void setIdAplicacion(String idAplicacion) {
		this.idAplicacion = idAplicacion;
	}

	

 }
