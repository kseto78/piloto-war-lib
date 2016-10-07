package es.redsara.misim.misim_bus_webapp.recpecionsms;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.6.3
 * 2016-02-10T19:48:15.977+01:00
 * Generated source version: 2.6.3
 * 
 */
@WebServiceClient(name = "EnviarMensajesWS", 
                  wsdlLocation = "./wsdl-misim/enviarMensajes.wsdl",
                  targetNamespace = "http://misim.redsara.es/misim-bus-webapp/") 
public class EnviarMensajesWS extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://misim.redsara.es/misim-bus-webapp/", "EnviarMensajesWS");
    public final static QName EnviarMensajesWSPort = new QName("http://misim.redsara.es/misim-bus-webapp/", "EnviarMensajesWSPort");
    static {
        URL url = null;
        try {
			url = new URL("file:///c:/EnviarMensajesWS.wsdl");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if (url == null) {
            java.util.logging.Logger.getLogger(EnviarMensajesWS.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "c:/wsdl-misim/enviarMensajes.wsdl");
        }       
        WSDL_LOCATION = url;
    }

    public EnviarMensajesWS(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public EnviarMensajesWS(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public EnviarMensajesWS() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public EnviarMensajesWS(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public EnviarMensajesWS(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public EnviarMensajesWS(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName);
    }

    /**
     *
     * @return
     *     returns EnviarMensajesWSBindingPortType
     */
    @WebEndpoint(name = "EnviarMensajesWSPort")
    public EnviarMensajesWSBindingPortType getEnviarMensajesWSPort() {
        return super.getPort(EnviarMensajesWSPort, EnviarMensajesWSBindingPortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns EnviarMensajesWSBindingPortType
     */
    @WebEndpoint(name = "EnviarMensajesWSPort")
    public EnviarMensajesWSBindingPortType getEnviarMensajesWSPort(WebServiceFeature... features) {
        return super.getPort(EnviarMensajesWSPort, EnviarMensajesWSBindingPortType.class, features);
    }

}
