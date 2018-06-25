
package es.minhap.misim.bus.webapp.restserviceprovider;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Usuario" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Password" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Endpoint" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Key" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Auth" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IdServicio" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *          &lt;element name="IdUsuario" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "usuario",
    "password",
    "endpoint",
    "key",
    "auth",
    "idServicio",
	"idUsuario",
	"accion"
})
@XmlRootElement(name = "PeticionRegistroUsuarioWebPush", namespace="http://misim.redsara.es/misim-bus-webapp/peticionRegistroUsuarioWebPush")
public class PeticionRegistroUsuarioWebPush {

    @XmlElement(name = "Usuario", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/peticionRegistroUsuarioWebPush")
    protected String usuario;
    @XmlElement(name = "Password", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/peticionRegistroUsuarioWebPush")
    protected String password;
    @XmlElement(name = "Endpoint", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/peticionRegistroUsuarioWebPush")
    protected String endpoint;
    @XmlElement(name = "Key", namespace="http://misim.redsara.es/misim-bus-webapp/peticionRegistroUsuarioWebPush")
    protected String key;
    @XmlElement(name = "Auth", namespace="http://misim.redsara.es/misim-bus-webapp/peticionRegistroUsuarioWebPush")
    protected String auth;
    @XmlElement(name = "IdServicio", namespace = "http://misim.redsara.es/misim-bus-webapp/peticionRegistroUsuarioWebPush")
	protected String idServicio;
	@XmlElement(name = "IdUsuario", namespace = "http://misim.redsara.es/misim-bus-webapp/peticionRegistroUsuarioWebPush")
	protected String idUsuario;
	@XmlElement(name = "Accion", namespace = "http://misim.redsara.es/misim-bus-webapp/peticionRegistroUsuarioWebPush")
	protected String accion;
	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the endpoint
	 */
	public String getEndpoint() {
		return endpoint;
	}
	/**
	 * @param endpoint the endpoint to set
	 */
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}
	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * @return the auth
	 */
	public String getAuth() {
		return auth;
	}
	/**
	 * @param auth the auth to set
	 */
	public void setAuth(String auth) {
		this.auth = auth;
	}
	/**
	 * @return the idServicio
	 */
	public String getIdServicio() {
		return idServicio;
	}
	/**
	 * @param idServicio the idServicio to set
	 */
	public void setIdServicio(String idServicio) {
		this.idServicio = idServicio;
	}
	/**
	 * @return the idUsuario
	 */
	public String getIdUsuario() {
		return idUsuario;
	}
	/**
	 * @param idUsuario the idUsuario to set
	 */
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	/**
	 * @return the accion
	 */
	public String getAccion() {
		return accion;
	}
	/**
	 * @param accion the accion to set
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}
	
	
	
}
