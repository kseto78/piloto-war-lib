
package es.mpr.plataformamensajeria.ws.usuariosplataformas;

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
 *         &lt;element name="Servicio" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Usuario" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Password" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IdentificadorUsuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Plataforma" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RegistroId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DispositivoId" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "servicio",
    "usuario",
    "password",
    "identificadorUsuario",
    "plataforma",
    "registroId",
    "dispositivoId"
})
@XmlRootElement(name = "registroUsuarioRequest")
public class RegistroUsuarioRequest {
	
    @XmlElement(name = "Servicio", required = true)
    protected String servicio;
    @XmlElement(name = "Usuario", required = true)
    protected String usuario;
    @XmlElement(name = "Password", required = true)
    protected String password;
    @XmlElement(name = "IdentificadorUsuario")
    protected String identificadorUsuario;
    @XmlElement(name = "Plataforma", required = true)
    protected String plataforma;
    @XmlElement(name = "RegistroId", required = true)
    protected String registroId;
    @XmlElement(name = "DispositivoId", required = true)
    protected String dispositivoId;

    /**
     * Obtiene el valor de la propiedad servicio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServicio() {
        return servicio;
    }

    /**
     * Define el valor de la propiedad servicio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServicio(String value) {
        this.servicio = value;
    }

    /**
     * Obtiene el valor de la propiedad usuario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Define el valor de la propiedad usuario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsuario(String value) {
        this.usuario = value;
    }

    /**
     * Obtiene el valor de la propiedad password.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Define el valor de la propiedad password.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Obtiene el valor de la propiedad identificadorUsuario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificadorUsuario() {
        return identificadorUsuario;
    }

    /**
     * Define el valor de la propiedad identificadorUsuario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificadorUsuario(String value) {
        this.identificadorUsuario = value;
    }

    /**
     * Obtiene el valor de la propiedad plataforma.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlataforma() {
        return plataforma;
    }

    /**
     * Define el valor de la propiedad plataforma.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlataforma(String value) {
        this.plataforma = value;
    }

    /**
     * Obtiene el valor de la propiedad registroId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegistroId() {
        return registroId;
    }

    /**
     * Define el valor de la propiedad registroId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegistroId(String value) {
        this.registroId = value;
    }

    /**
     * Obtiene el valor de la propiedad dispositivoId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
	public String getDispositivoId() {
		return dispositivoId;
	}

	/**
     * Define el valor de la propiedad dispositivoId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
	public void setDispositivoId(String dispositivoId) {
		this.dispositivoId = dispositivoId;
	}

}
