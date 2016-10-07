
package es.minhap.plataformamensajeria.iop.beans;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;



public class MensajesNotificacionBean {

   
    protected List<MensajeNotificacionBean> mensajeNotificacion;

       public List<MensajeNotificacionBean> getMensajeNotificacion() {
        if (mensajeNotificacion == null) {
            mensajeNotificacion = new ArrayList<MensajeNotificacionBean>();
        }
        return this.mensajeNotificacion;
    }

}
