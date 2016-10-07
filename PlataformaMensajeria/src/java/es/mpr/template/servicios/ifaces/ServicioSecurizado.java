package es.mpr.template.servicios.ifaces;

import org.springframework.security.annotation.Secured;

/**
 * <p>Interface que define un servicio de ejemplo con un m&eacute;todo 
 * con anotaci&oacute;n @Secured de Spring Security.
 * 
 * Solamente pueden ejecutar el m&eacute;todo los usuarios que pertenecen al rol <i>ROLE_ADMINFWK</i>
 * </p>
 * 
 * @author Altran
 *
 */
public interface ServicioSecurizado {
	
/**
 * Mediante esta anotaci&oacute;n de Spring subordinamos la ejecuci&oacute;n de este m&eacute;todo a un ROL.
 * 
 * @return Resultado de ejecutar el m&eacute;todo
 * 
 * @throws AccessDeniedException.class
 * 
 */
    @Secured("ROLE_ADMINFWK")
	String ejecutar();
	
}
