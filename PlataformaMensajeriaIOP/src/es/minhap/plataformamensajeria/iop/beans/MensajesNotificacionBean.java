
package es.minhap.plataformamensajeria.iop.beans;

import java.util.ArrayList;
import java.util.List;



public class MensajesNotificacionBean {

   
    protected List<MensajeNotificacionBean> mensajeNotificacion;

       public List<MensajeNotificacionBean> getMensajeNotificacion() {
        if (mensajeNotificacion == null) {
            mensajeNotificacion = new ArrayList<MensajeNotificacionBean>();
        }
        return this.mensajeNotificacion;
    }

}
