package es.mpr.plataformamensajeria.ws.usuariosplataformas;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.6.3
 * Generated source version: 2.6.3
 * 
 */
@WebServiceClient(name = "RegistroUsuarioImplService", 
                  wsdlLocation = "./wsdl/registroUsuario.wsdl",
                  targetNamespace = "http://registrousuario.ws.plataformamensajeria.minhap.es/") 
public class RegistroUsuarioImplService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://registrousuario.ws.plataformamensajeria.minhap.es/", "RegistroUsuarioImplService");
    public final static QName RegistroUsuarioImplPort = new QName("http://registrousuario.ws.plataformamensajeria.minhap.es/", "RegistroUsuarioImplPort");
    static {
        URL url = RegistroUsuarioImplService.class.getResource("./wsdl/registroUsuario.wsdl");
        if (url == null) {
            java.util.logging.Logger.getLogger(RegistroUsuarioImplService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "./wsdl/registroUsuario.wsdl");
        }       
        WSDL_LOCATION = url;
    }

    public RegistroUsuarioImplService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public RegistroUsuarioImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public RegistroUsuarioImplService() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     *
     * @return
     *     returns IRegistroUsuarioService
     */
    @WebEndpoint(name = "RegistroUsuarioImplPort")
    public IRegistroUsuarioService getRegistroUsuarioImplPort() {
        return super.getPort(RegistroUsuarioImplPort, IRegistroUsuarioService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IRegistroUsuarioService
     */
    @WebEndpoint(name = "RegistroUsuarioImplPort")
    public IRegistroUsuarioService getRegistroUsuarioImplPort(WebServiceFeature... features) {
        return super.getPort(RegistroUsuarioImplPort, IRegistroUsuarioService.class, features);
    }

}
