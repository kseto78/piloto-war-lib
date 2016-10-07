
package es.minhap.plataformamensajeria.iop.beans;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


public class MensajesType {

   
    protected List<ResponseStatusType> mensaje;

 
    public List<ResponseStatusType> getMensaje() {
        if (mensaje == null) {
            mensaje = new ArrayList<ResponseStatusType>();
        }
        return this.mensaje;
    }
    
    public void setMensaje(List<ResponseStatusType> mensaje) {
        this.mensaje = mensaje;
    }

}
