
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
 *         &lt;element name="IdDispositivo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CodConfirmacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UidDispositivo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TokenSession" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "idServicioMovil",
    "codConfirmacion",
    "uidDispositivo",
	"tokenSession"
})
@XmlRootElement(name = "PeticionConfirmarAltaUsuario", namespace="http://misim.redsara.es/misim-bus-webapp/rest/peticionConfirmarAltaUsuario")
public class PeticionConfirmarAltaUsuario {

    @XmlElement(name = "Usuario", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/rest/peticionConfirmarAltaUsuario")
    protected String usuario;
    @XmlElement(name = "Password", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/rest/peticionConfirmarAltaUsuario")
    protected String password;
    @XmlElement(name = "IdDispositivo", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/rest/peticionConfirmarAltaUsuario")
    protected String idDispositivo;
    @XmlElement(name = "IdServicioMovil", namespace="http://misim.redsara.es/misim-bus-webapp/rest/peticionConfirmarAltaUsuario")
    protected String idServicioMovil;
    @XmlElement(name = "CodConfirmacion", namespace="http://misim.redsara.es/misim-bus-webapp/rest/peticionConfirmarAltaUsuario")
    protected String codConfirmacion;
    @XmlElement(name = "UidDispositivo", namespace = "http://misim.redsara.es/misim-bus-webapp/peticionConfirmarAltaUsuario")
	protected String uidDispositivo;
	@XmlElement(name = "TokenSession", namespace = "http://misim.redsara.es/misim-bus-webapp/peticionConfirmarAltaUsuario")
	protected String tokenSession;
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
	 * @return the idDispositivo
	 */
	public String getIdDispositivo() {
		return idDispositivo;
	}
	/**
	 * @param idDispositivo the idDispositivo to set
	 */
	public void setIdDispositivo(String idDispositivo) {
		this.idDispositivo = idDispositivo;
	}
	/**
	 * @return the codConfirmacion
	 */
	public String getCodConfirmacion() {
		return codConfirmacion;
	}
	/**
	 * @param codConfirmacion the codConfirmacion to set
	 */
	public void setCodConfirmacion(String codConfirmacion) {
		this.codConfirmacion = codConfirmacion;
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
	/**
	 * @return the idServicioMovil
	 */
	public String getIdServicioMovil() {
		return idServicioMovil;
	}
	/**
	 * @param idServicioMovil the idServicioMovil to set
	 */
	public void setIdServicioMovil(String idServicioMovil) {
		this.idServicioMovil = idServicioMovil;
	}
	
	
}
