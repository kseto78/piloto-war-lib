
package es.redsara.misim.misim_bus_webapp.passbook;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.minhap.misim.components.InicializarAEAT;

/**
 * This class was generated by Apache CXF 2.6.3
 * 2016-06-15T17:32:31.531+02:00
 * Generated source version: 2.6.3
 * 
 */
public final class EnvioMensajesPassbookServiceWSBindingPortType_EnvioMensajesPassbookServicePort_Client {

    private static final QName SERVICE_NAME = new QName("http://misim.redsara.es/misim-bus-webapp/", "EnvioMensajesPassbookService");

    private static final Logger LOG = LoggerFactory.getLogger(EnvioMensajesPassbookServiceWSBindingPortType_EnvioMensajesPassbookServicePort_Client.class);
    
    private EnvioMensajesPassbookServiceWSBindingPortType_EnvioMensajesPassbookServicePort_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = EnvioMensajesPassbookService.WSDL_LOCATION;
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) { 
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
      
        EnvioMensajesPassbookService ss = new EnvioMensajesPassbookService(wsdlURL, SERVICE_NAME);
        EnvioMensajesPassbookServiceWSBindingPortType port = ss.getEnvioMensajesPassbookServicePort();  
        
        {
        	LOG.debug("Invoking enviarMensaje...");
        es.redsara.misim.misim_bus_webapp.passbook.peticion.Peticion _enviarMensaje_peticion = null;
        es.redsara.misim.misim_bus_webapp.passbook.respuesta.Respuesta _enviarMensaje__return = port.enviarMensaje(_enviarMensaje_peticion);
        	LOG.info("enviarMensaje.result=" + _enviarMensaje__return);


        }

        System.exit(0);
    }

}
