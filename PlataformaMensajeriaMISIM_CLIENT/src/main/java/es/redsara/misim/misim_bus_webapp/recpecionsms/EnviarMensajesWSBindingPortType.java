package es.redsara.misim.misim_bus_webapp.recpecionsms;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.6.3
 * 2016-02-10T19:48:15.966+01:00
 * Generated source version: 2.6.3
 * 
 */
@WebService(targetNamespace = "http://misim.redsara.es/misim-bus-webapp/", name = "EnviarMensajesWSBindingPortType")
@XmlSeeAlso({es.redsara.misim.misim_bus_webapp.recpecionsms.peticion.ObjectFactory.class, es.redsara.misim.misim_bus_webapp.recpecionsms.respuesta.ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface EnviarMensajesWSBindingPortType {

    @WebResult(name = "Respuesta", targetNamespace = "http://misim.redsara.es/misim-bus-webapp/respuesta", partName = "respuesta")
    @WebMethod(action = "enviarMensaje")
    public es.redsara.misim.misim_bus_webapp.recpecionsms.respuesta.Respuesta enviarMensaje(
        @WebParam(partName = "peticion", name = "Peticion", targetNamespace = "http://misim.redsara.es/misim-bus-webapp/peticion")
        es.redsara.misim.misim_bus_webapp.recpecionsms.peticion.PeticionRecibirSms peticion
    );
}
