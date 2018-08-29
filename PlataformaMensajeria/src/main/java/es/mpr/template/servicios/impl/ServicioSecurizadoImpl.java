package es.mpr.template.servicios.impl;

import es.mpr.template.servicios.ifaces.ServicioSecurizado;

/**
 * <p>Clase de implementaci&oacute;n de un servicio con seguridad en el m&eacute;todo que define.</p>
 * 
 * @author Altran
 *
 */
public class ServicioSecurizadoImpl implements ServicioSecurizado {

    /* (non-Javadoc)
     * @see es.mpr.template.servicios.ifaces.ServicioSecurizado#ejecutar()
     */
    public String ejecutar(){

        return "OK";

    }
}
