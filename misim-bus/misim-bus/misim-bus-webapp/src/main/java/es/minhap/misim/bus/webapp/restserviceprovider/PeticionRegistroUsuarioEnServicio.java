
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
 *         &lt;element name="IdUsuario" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IdServicioMovil" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="accion" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "idUsuario",
    "idServicioMovil",
    "idDispositivo",
    "accion",
})
@XmlRootElement(name = "PeticionRegistroUsuario", namespace="http://misim.redsara.es/misim-bus-webapp/rest/peticionRegistroUsuarioEnServicio")
public class PeticionRegistroUsuarioEnServicio {

    @XmlElement(name = "Usuario", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/rest/peticionRegistroUsuarioEnServicio")
    protected String usuario;
    @XmlElement(name = "Password", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/rest/peticionRegistroUsuarioEnServicio")
    protected String password;
    @XmlElement(name = "IdUsuario", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/rest/peticionRegistroUsuarioEnServicio")
    protected String idUsuario;
    @XmlElement(name = "IdServicioMovil", namespace="http://misim.redsara.es/misim-bus-webapp/rest/peticionRegistroUsuarioEnServicio")
    protected String idServicioMovil;
    @XmlElement(name = "IdDispositivo", namespace="http://misim.redsara.es/misim-bus-webapp/rest/peticionRegistroUsuarioEnServicio")
    protected String idDispositivo;
    @XmlElement(name = "Accion", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/rest/peticionRegistroUsuarioEnServicio")
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
    
}
