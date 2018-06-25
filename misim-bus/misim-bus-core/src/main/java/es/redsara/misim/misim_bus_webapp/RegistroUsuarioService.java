package es.redsara.misim.misim_bus_webapp;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.7.1
 * 2016-01-28T09:56:53.296+01:00
 * Generated source version: 2.7.1
 * 
 */
@WebServiceClient(name = "RegistroUsuarioService", 
                  wsdlLocation = "file:/C:/workspace_MISIM/maven.1453279207193/misim-bus/misim-bus-core/src/main/resources/wsdl/registro-usuario/registroUsuario.wsdl",
                  targetNamespace = "http://misim.redsara.es/misim-bus-webapp/") 
public class RegistroUsuarioService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://misim.redsara.es/misim-bus-webapp/", "RegistroUsuarioService");
    public final static QName RegistroUsuarioImplPort = new QName("http://misim.redsara.es/misim-bus-webapp/", "RegistroUsuarioImplPort");
    static {
        URL url = null;
        try {
            url = new URL("file://workspace_MISIM/maven.1453279207193/misim-bus/misim-bus-core/src/main/resources/wsdl/registro-usuario/registroUsuario.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(RegistroUsuarioService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/C:/workspace_MISIM/maven.1453279207193/misim-bus/misim-bus-core/src/main/resources/wsdl/registro-usuario/registroUsuario.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public RegistroUsuarioService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public RegistroUsuarioService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public RegistroUsuarioService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public RegistroUsuarioService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public RegistroUsuarioService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public RegistroUsuarioService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns EnviarMensajesWSBindingPortType
     */
    @WebEndpoint(name = "EnvioMensajesServicePort")
    public RegistroUsuarioServicePortType getEnviarMensajesWSPort() {
        return super.getPort(RegistroUsuarioImplPort, RegistroUsuarioServicePortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns EnviarMensajesWSBindingPortType
     */
    @WebEndpoint(name = "RegistroUsuarioImplPort")
    public RegistroUsuarioServicePortType getEnviarMensajesWSPort(WebServiceFeature... features) {
        return super.getPort(RegistroUsuarioImplPort, RegistroUsuarioServicePortType.class, features);
    }

}
