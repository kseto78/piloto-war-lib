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
 *          &lt;element name="DocUsuario" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="NumPagina" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *            &lt;element name="TamPagina" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "idUsuario",
    "numPagina",
    "tamPagina"
})
@XmlRootElement(name = "PeticionGetAvisosUsuario", namespace="http://misim.redsara.es/misim-bus-webapp/PeticionGetAvisosUsuario")
public class PeticionGetAvisosUsuario {

    @XmlElement(name = "Usuario", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/PeticionGetAvisosUsuario")
    protected String usuario;
    @XmlElement(name = "Password", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/PeticionGetAvisosUsuario")
    protected String password;
    @XmlElement(name = "IdDispositivo", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/PeticionGetAvisosUsuario")
    protected String idDispositivo;
    @XmlElement(name = "IdServicio", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/PeticionGetAvisosUsuario")
    protected String idServicio;
    @XmlElement(name = "IdPlataforma", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/PeticionGetAvisosUsuario")
    protected String idPlataforma;
    @XmlElement(name = "IdUsuario", required = false, namespace="http://misim.redsara.es/misim-bus-webapp/PeticionGetAvisosUsuario")
    protected String idUsuario;
    @XmlElement(name = "NumPagina", required = false, namespace="http://misim.redsara.es/misim-bus-webapp/PeticionGetAvisosUsuario")
    protected String numPagina;
    @XmlElement(name = "TamPagina", required = false, namespace="http://misim.redsara.es/misim-bus-webapp/PeticionGetAvisosUsuario")
    protected String tamPagina;
  
    
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
	 * @return the numPagina
	 */
	public String getNumPagina() {
		return numPagina;
	}
	/**
	 * @param numPagina the numPagina to set
	 */
	public void setNumPagina(String numPagina) {
		this.numPagina = numPagina;
	}
	
	/**
	 * @return the tamPagina
	 */
	public String getTamPagina() {
		return tamPagina;
	}
	/**
	 * @param tamPagina the tamPagina to set
	 */
	public void setTamPagina(String tamPagina) {
		this.tamPagina = tamPagina;
	}
	
}