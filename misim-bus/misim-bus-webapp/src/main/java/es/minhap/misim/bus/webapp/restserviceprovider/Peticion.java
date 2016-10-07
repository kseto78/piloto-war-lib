
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
 *         &lt;element name="Producto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Proveedor" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DatosEspecificos" type="{http://misim.redsara.es/misim-bus-webapp/peticion}DatosEspecificos" minOccurs="0"/>
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
    "servicio",
    "idUsuario",
    "plataforma",
    "idRegistro",
    "idDispositivo"
})
@XmlRootElement(name = "Peticion", namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
public class Peticion {

    @XmlElement(name = "Usuario", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
    protected String usuario;
    @XmlElement(name = "Password", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
    protected String password;
    @XmlElement(name = "Servicio", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
    protected String servicio;
    @XmlElement(name = "IdUsuario", namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
    protected String idUsuario;
    @XmlElement(name = "Plataforma", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
    protected String plataforma;
    @XmlElement(name = "IdRegistro", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
    protected String idRegistro;
    @XmlElement(name = "IdDispositivo", namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
    
    protected String idDispositivo;
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
	public String getServicio() {
		return servicio;
	}
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getPlataforma() {
		return plataforma;
	}
	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}
	public String getIdRegistro() {
		return idRegistro;
	}
	public void setIdRegistro(String idRegistro) {
		this.idRegistro = idRegistro;
	}
	public String getIdDispositivo() {
		return idDispositivo;
	}
	public void setIdDispositivo(String idDispositivo) {
		this.idDispositivo = idDispositivo;
	}
    
}
