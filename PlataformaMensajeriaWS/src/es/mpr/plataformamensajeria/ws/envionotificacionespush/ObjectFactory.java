
package es.mpr.plataformamensajeria.ws.envionotificacionespush;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the es.minhap.plataformamensajeria.ws.envionotificacionpush package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: es.minhap.plataformamensajeria.ws.envionotificacionpush
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EnvioNotificacionPushRequest }
     * 
     */
    public EnvioNotificacionPushRequest createEnvioNotificacionPushRequest() {
        return new EnvioNotificacionPushRequest();
    }

    /**
     * Create an instance of {@link MensajesNotificacionType }
     * 
     */
    public MensajesNotificacionType createMensajesNotificacionType() {
        return new MensajesNotificacionType();
    }

    /**
     * Create an instance of {@link EnvioNotificacionPushResponse }
     * 
     */
    public EnvioNotificacionPushResponse createEnvioNotificacionPushResponse() {
        return new EnvioNotificacionPushResponse();
    }

    /**
     * Create an instance of {@link MensajesType }
     * 
     */
    public MensajesType createMensajesType() {
        return new MensajesType();
    }

    /**
     * Create an instance of {@link MensajeNotificacionType }
     * 
     */
    public MensajeNotificacionType createMensajeNotificacionType() {
        return new MensajeNotificacionType();
    }

    /**
     * Create an instance of {@link ResponseStatusType }
     * 
     */
    public ResponseStatusType createResponseStatusType() {
        return new ResponseStatusType();
    }

}
