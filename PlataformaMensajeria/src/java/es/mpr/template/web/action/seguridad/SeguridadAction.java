/**
 * 
 */
package es.mpr.template.web.action.seguridad;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.security.context.SecurityContextHolder;

import com.map.j2ee.exceptions.BaseException;
import com.map.j2ee.security.auth.MapUser;
import com.map.j2ee.security.perm.model.UserVO;
import org.apache.commons.beanutils.locale.LocaleBeanUtils;
import com.opensymphony.xwork2.ActionSupport;

import es.mpr.template.servicios.ifaces.ServicioSecurizado;
import es.mpr.template.web.action.admin.*;

import org.springframework.security.GrantedAuthority;

/**
 * 
 * <p>Clase que contiene los actions asociados a los ejemplos de seguridad de la aplicaci&oacute:n template</p>
 *
 * @author Altran
 * 
 */
public class SeguridadAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected HttpServletRequest request;
    protected HttpServletResponse response;
    private UsuariosForm form=new UsuariosForm();
    private ServicioSecurizado servicio;

    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }


    // La anotación que proteje este método está incluida en la clase interface.
    
     public String ejecutarMetodoProtegido() throws BaseException {
    	 if(request.getSession().getAttribute("infoUser")==null) return "noUser"; 

         this.getServicio().ejecutar();
         return SUCCESS;

     }

    public String detallesUsuarioLogado() throws BaseException {

        /*
         * Recuperamos los datos del usuario Logeado en la aplicaci�n.
         */
    	if(request.getSession().getAttribute("infoUser")==null) return "noUser"; 

        MapUser detallesUsuario = (MapUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

          GrantedAuthority[] roles=detallesUsuario.getAuthorities();

          for (int i = 0; i < roles.length; i++) {
            GrantedAuthority grantedAuthority = roles[i];
            System.out.print(grantedAuthority.getAuthority());
        }


        /*
         * Obtenemos todos los datos almacenados de este usuario en el LDAP.
         */

        UserVO tmpUserVO = (UserVO) detallesUsuario.getUserInfo();

        /*
         * Copiamos los datos al bean del formulario para que se muestren en la jsp.
         */

        //LocaleBeanUtils beanUtils = new LocaleBeanUtils();
        try {
        	LocaleBeanUtils.copyProperties(getForm(),tmpUserVO);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


        return SUCCESS;
    }

    /**
     * @return the servicio
     */
    public ServicioSecurizado getServicio() {
        return servicio;
    }

    /**
     * @param servicio the servicio to set
     */
    public void setServicio(ServicioSecurizado servicio) {
        this.servicio = servicio;
    }

    /**
     * @return the form
     */
    public UsuariosForm getForm() {
        return form;
    }

    /**
     * @param form the form to set
     */
    public void setForm(UsuariosForm form) {
        this.form = form;
    }
}