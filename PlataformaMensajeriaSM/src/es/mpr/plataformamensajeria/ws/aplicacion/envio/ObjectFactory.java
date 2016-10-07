
package es.mpr.plataformamensajeria.ws.aplicacion.envio;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the es.minhap.plataformamensajeria.ws.aplicacion.envio package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: es.minhap.plataformamensajeria.ws.aplicacion.envio
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EnvioAplicacioResponse }
     * 
     */
    public EnvioAplicacioResponse createEnvioAplicacioResponse() {
        return new EnvioAplicacioResponse();
    }

    /**
     * Create an instance of {@link ResponseStatusType }
     * 
     */
    public ResponseStatusType createResponseStatusType() {
        return new ResponseStatusType();
    }

    /**
     * Create an instance of {@link EnvioAplicacionRequest }
     * 
     */
    public EnvioAplicacionRequest createEnvioAplicacionRequest() {
        return new EnvioAplicacionRequest();
    }

}
