
package es.redsara.misim.misim_bus_webapp.passbook.peticion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para mensajeSMS complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="mensajeSMS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DocUsuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodSia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodOrganismo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodOrganismoPagador" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdExterno" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Destinatario" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Cuerpo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mensajeSMS", propOrder = {
    "docUsuario",
    "codSia",
    "codOrganismo",
    "codOrganismoPagador",
    "idExterno",
    "destinatario",
    "cuerpo"
})
public class MensajeSMS {

    @XmlElement(name = "DocUsuario")
    protected String docUsuario;
    @XmlElement(name = "CodSia")
    protected String codSia;
    @XmlElement(name = "CodOrganismo")
    protected String codOrganismo;
    @XmlElement(name = "CodOrganismoPagador")
    protected String codOrganismoPagador;
    @XmlElement(name = "IdExterno", required = true)
    protected String idExterno;
    @XmlElement(name = "Destinatario", required = true)
    protected String destinatario;
    @XmlElement(name = "Cuerpo", required = true)
    protected String cuerpo;

    /**
     * Obtiene el valor de la propiedad docUsuario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocUsuario() {
        return docUsuario;
    }

    /**
     * Define el valor de la propiedad docUsuario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocUsuario(String value) {
        this.docUsuario = value;
    }

    /**
     * Obtiene el valor de la propiedad codSia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodSia() {
        return codSia;
    }

    /**
     * Define el valor de la propiedad codSia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodSia(String value) {
        this.codSia = value;
    }

    /**
     * Obtiene el valor de la propiedad codOrganismo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodOrganismo() {
        return codOrganismo;
    }

    /**
     * Define el valor de la propiedad codOrganismo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodOrganismo(String value) {
        this.codOrganismo = value;
    }

    /**
     * Obtiene el valor de la propiedad codOrganismoPagador.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodOrganismoPagador() {
        return codOrganismoPagador;
    }

    /**
     * Define el valor de la propiedad codOrganismoPagador.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodOrganismoPagador(String value) {
        this.codOrganismoPagador = value;
    }

    /**
     * Obtiene el valor de la propiedad idExterno.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdExterno() {
        return idExterno;
    }

    /**
     * Define el valor de la propiedad idExterno.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdExterno(String value) {
        this.idExterno = value;
    }

    /**
     * Obtiene el valor de la propiedad destinatario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestinatario() {
        return destinatario;
    }

    /**
     * Define el valor de la propiedad destinatario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestinatario(String value) {
        this.destinatario = value;
    }

    /**
     * Obtiene el valor de la propiedad cuerpo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCuerpo() {
        return cuerpo;
    }

    /**
     * Define el valor de la propiedad cuerpo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCuerpo(String value) {
        this.cuerpo = value;
    }

}
