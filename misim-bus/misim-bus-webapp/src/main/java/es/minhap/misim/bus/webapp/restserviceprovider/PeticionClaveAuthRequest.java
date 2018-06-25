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
 *          &lt;element name="IdDispositivo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IdServicio" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *          &lt;element name="IdPlataforma" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "idDispositivo",
    "idServicio",
    "idPlataforma",
    "APILevel",
    "uidDispositivo",
    "tokenSession"
})
@XmlRootElement(name = "PeticionClaveAuthRequest", namespace="http://misim.redsara.es/misim-bus-webapp/PeticionClaveAuthRequest")
public class PeticionClaveAuthRequest {

    @XmlElement(name = "Usuario", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/PeticionClaveAuthRequest")
    protected String usuario;
    @XmlElement(name = "Password", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/PeticionClaveAuthRequest")
    protected String password;
    @XmlElement(name = "IdDispositivo", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/PeticionClaveAuthRequest")
    protected String idDispositivo;
    @XmlElement(name = "IdServicio", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/PeticionClaveAuthRequest")
    protected String idServicio;
    @XmlElement(name = "IdPlataforma", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/PeticionClaveAuthRequest")
    protected String idPlataforma;
    @XmlElement(name = "APILevel", required = false, namespace="http://misim.redsara.es/misim-bus-webapp/PeticionClaveAuthRequest")
    protected String APILevel;
    @XmlElement(name = "UidDispositivo", namespace = "http://misim.redsara.es/misim-bus-webapp/PeticionClaveAuthRequest")
   	protected String uidDispositivo;
   	@XmlElement(name = "TokenSession", namespace = "http://misim.redsara.es/misim-bus-webapp/PeticionClaveAuthRequest")
   	protected String tokenSession;
    
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getIdDispositivo() {
		return idDispositivo;
	}
	
	public void setIdDispositivo(String idDispositivo) {
		this.idDispositivo = idDispositivo;
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
	/**
	 * @return the aPILevel
	 */
	public String getAPILevel() {
		return APILevel;
	}
	/**
	 * @param aPILevel the aPILevel to set
	 */
	public void setAPILevel(String aPILevel) {
		APILevel = aPILevel;
	}
	/**
	 * @return the uidDispositivo
	 */
	public String getUidDispositivo() {
		return uidDispositivo;
	}
	/**
	 * @param uidDispositivo the uidDispositivo to set
	 */
	public void setUidDispositivo(String uidDispositivo) {
		this.uidDispositivo = uidDispositivo;
	}
	/**
	 * @return the tokenSession
	 */
	public String getTokenSession() {
		return tokenSession;
	}
	/**
	 * @param tokenSession the tokenSession to set
	 */
	public void setTokenSession(String tokenSession) {
		this.tokenSession = tokenSession;
	}
	
}