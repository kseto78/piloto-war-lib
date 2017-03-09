package es.minhap.misim.bus.webapp.restserviceprovider;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Clase Java para anonymous complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que
 * haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Usuario" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Password" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NotificacionId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IdUsuario" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "usuario", "password", "notificacionId", "status", "idUsuario", "uidDispositivo",
		"tokenSession"

})
@XmlRootElement(name = "PeticionNotificacionPush", namespace = "http://misim.redsara.es/misim-bus-webapp/peticionNotificacionPush")
public class PeticionNotificacionEstado {

	@XmlElement(name = "Usuario", required = true, namespace = "http://misim.redsara.es/misim-bus-webapp/peticionNotificacionPush")
	protected String usuario;
	@XmlElement(name = "Password", required = true, namespace = "http://misim.redsara.es/misim-bus-webapp/peticionNotificacionPush")
	protected String password;
	@XmlElement(name = "NotificacionId", required = false, namespace = "http://misim.redsara.es/misim-bus-webapp/peticionNotificacionPush")
	protected String notificacionId;
	@XmlElement(name = "Status", required = false, namespace = "http://misim.redsara.es/misim-bus-webapp/peticionNotificacionPush")
	protected String status;
	@XmlElement(name = "IdUsuario", required = true, namespace = "http://misim.redsara.es/misim-bus-webapp/peticionNotificacionPush")
	protected String idUsuario;
	@XmlElement(name = "UidDispositivo", namespace = "http://misim.redsara.es/misim-bus-webapp/peticionNotificacionPush")
	protected String uidDispositivo;
	@XmlElement(name = "TokenSession", namespace = "http://misim.redsara.es/misim-bus-webapp/peticionNotificacionPush")
	protected String tokenSession;

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
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
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the notificacionId
	 */
	public String getNotificacionId() {
		return notificacionId;
	}

	/**
	 * @param notificacionId
	 *            the notificacionId to set
	 */
	public void setNotificacionId(String notificacionId) {
		this.notificacionId = notificacionId;
	}

	/**
	 * @return the idUsuario
	 */
	public String getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @param idUsuario
	 *            the idUsuario to set
	 */
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * @return the uidDispositivo
	 */
	public String getUidDispositivo() {
		return uidDispositivo;
	}

	/**
	 * @param uidDispositivo
	 *            the uidDispositivo to set
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
	 * @param tokenSession
	 *            the tokenSession to set
	 */
	public void setTokenSession(String tokenSession) {
		this.tokenSession = tokenSession;
	}

}
