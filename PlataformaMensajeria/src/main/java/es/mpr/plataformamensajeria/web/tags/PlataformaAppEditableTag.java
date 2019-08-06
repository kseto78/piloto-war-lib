package es.mpr.plataformamensajeria.web.tags;

import java.util.HashMap;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;

import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

/**
 * Clase PlataformaAppEditableTag.
 */
public class PlataformaAppEditableTag extends BodyTagSupport{
	 
 	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**  id aplicacion. */
	private String idAplicacion="";
       
       /* (non-Javadoc)
        * @see javax.servlet.jsp.tagext.BodyTagSupport#doStartTag()
        */
       public int doStartTag() throws JspException {
    	 boolean skipBody=true;
    	 
    	 @SuppressWarnings("unchecked")
		HashMap<Integer,Integer> mapPermisosAplicaciones =(HashMap<Integer, Integer>)pageContext.getSession().getAttribute(PlataformaMensajeriaUtil.MAP_PERMISOS_APLICACIONES); 
    	 String rolUsuario = (String)pageContext.getSession().getAttribute(PlataformaMensajeriaUtil.ROL_USUARIO_PLATAFORMA);
    	 if(rolUsuario!=null&&rolUsuario.equals(PlataformaMensajeriaUtil.ROL_PROPIETARIO)){
    	 	if(idAplicacion!=null&&!idAplicacion.equals("")&&mapPermisosAplicaciones!=null){
	    		 Integer modoMap = mapPermisosAplicaciones.get(new Integer(idAplicacion));
	    		 if(modoMap!=null&&modoMap.intValue()==2){
	    			 skipBody=false;
	    		 }
	    	 }
    	 }else  if(rolUsuario!=null&&(rolUsuario.equals(PlataformaMensajeriaUtil.ROL_ADMINISTRADOR)||rolUsuario.equals(PlataformaMensajeriaUtil.ROL_CAID))){
    		 skipBody=false;
    	 }
         if(skipBody){
        	 return Tag.SKIP_BODY;
         }
    	 return Tag.EVAL_BODY_INCLUDE;

     }
	
	/**
	 * Obtener id aplicacion.
	 *
	 * @return id aplicacion
	 */
	public String getIdAplicacion() {
		return idAplicacion;
	}
	
	/**
	 * Modificar id aplicacion.
	 *
	 * @param idAplicacion new id aplicacion
	 */
	public void setIdAplicacion(String idAplicacion) {
		this.idAplicacion = idAplicacion;
	}

	

 }
