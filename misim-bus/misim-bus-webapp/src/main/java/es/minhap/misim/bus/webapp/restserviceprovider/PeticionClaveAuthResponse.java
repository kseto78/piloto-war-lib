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
 *         &lt;element name="DispositivoId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SAMLResponse" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "idServicio",
    "idPlataforma",
    "dispositivoId",
    "samlResponse",
    "remoteHost"
})
@XmlRootElement(name = "PeticionClaveAuthResponse", namespace="http://misim.redsara.es/misim-bus-webapp/PeticionClaveAuthResponse")
public class PeticionClaveAuthResponse {

    @XmlElement(name = "Usuario", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/PeticionClaveAuthResponse")
    protected String usuario;
    @XmlElement(name = "Password", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/PeticionClaveAuthResponse")
    protected String password;
    @XmlElement(name = "IdServicio", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/PeticionClaveAuthRequest")
    protected String idServicio;
    @XmlElement(name = "IdPlataforma", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/PeticionClaveAuthRequest")
    protected String idPlataforma;
    @XmlElement(name = "DispositivoId", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/PeticionClaveAuthResponse")
    protected String dispositivoId;
    @XmlElement(name = "SAMLResponse", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/PeticionClaveAuthResponse")
    protected String samlResponse;
    @XmlElement(name = "RemoteHost", required = false, namespace="http://misim.redsara.es/misim-bus-webapp/PeticionClaveAuthResponse")
    protected String remoteHost;
	
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
	 * @return the dispositivoId
	 */
	public String getDispositivoId() {
		return dispositivoId;
	}
	/**
	 * @param dispositivoId the dispositivoId to set
	 */
	public void setDispositivoId(String dispositivoId) {
		this.dispositivoId = dispositivoId;
	}
	/**
	 * @return the samlResponse
	 */
	public String getSamlResponse() {
		return samlResponse;
	}
	/**
	 * @param samlResponse the samlResponse to set
	 */
	public void setSamlResponse(String samlResponse) {
		this.samlResponse = samlResponse;
	}
	/**
	 * @return the remoteHost
	 */
	public String getRemoteHost() {
		return remoteHost;
	}
	/**
	 * @param remoteHost the remoteHost to set
	 */
	public void setRemoteHost(String remoteHost) {
		this.remoteHost = remoteHost;
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
	 * @return the idPlataforma
	 */
	public String getIdPlataforma() {
		return idPlataforma;
	}
	/**
	 * @param idPlataforma the idPlataforma to set
	 */
	public void setIdPlataforma(String idPlataforma) {
		this.idPlataforma = idPlataforma;
	}
	
}
