
package es.redsara.misim.misim_bus_webapp.passbook.peticion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para passbook complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="passbook">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="URL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LogoText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CamposPrincipales" type="{http://misim.redsara.es/misim-bus-webapp/peticion}camposPrincipales"/>
 *         &lt;element name="CamposCabecera" type="{http://misim.redsara.es/misim-bus-webapp/peticion}camposCabecera" minOccurs="0"/>
 *         &lt;element name="CamposSecundarios" type="{http://misim.redsara.es/misim-bus-webapp/peticion}camposSecundarios" minOccurs="0"/>
 *         &lt;element name="CamposDetalleTrasero" type="{http://misim.redsara.es/misim-bus-webapp/peticion}camposDetalleTrasero" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "passbook", propOrder = {
    "url",
    "logoText",
    "description",
    "camposPrincipales",
    "camposCabecera",
    "camposSecundarios",
    "camposDetalleTrasero"
})
public class Passbook {

    @XmlElement(name = "URL", required = true)
    protected String url;
    @XmlElement(name = "LogoText", required = true)
    protected String logoText;
    @XmlElement(name = "Description", required = true)
    protected String description;
    @XmlElement(name = "CamposPrincipales", required = true)
    protected CamposPrincipales camposPrincipales;
    @XmlElement(name = "CamposCabecera")
    protected CamposCabecera camposCabecera;
    @XmlElement(name = "CamposSecundarios")
    protected CamposSecundarios camposSecundarios;
    @XmlElement(name = "CamposAuxiliares")
    protected CamposAuxiliares camposAuxiliares;
    @XmlElement(name = "CamposDetalleTrasero")
    protected CamposDetalleTrasero camposDetalleTrasero;

    /**
     * Obtiene el valor de la propiedad url.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getURL() {
        return url;
    }

    /**
     * Define el valor de la propiedad url.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setURL(String value) {
        this.url = value;
    }

    /**
     * Obtiene el valor de la propiedad logoText.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogoText() {
        return logoText;
    }

    /**
     * Define el valor de la propiedad logoText.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogoText(String value) {
        this.logoText = value;
    }

    /**
     * Obtiene el valor de la propiedad description.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Define el valor de la propiedad description.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Obtiene el valor de la propiedad camposPrincipales.
     * 
     * @return
     *     possible object is
     *     {@link CamposPrincipales }
     *     
     */
    public CamposPrincipales getCamposPrincipales() {
        return camposPrincipales;
    }

    /**
     * Define el valor de la propiedad camposPrincipales.
     * 
     * @param value
     *     allowed object is
     *     {@link CamposPrincipales }
     *     
     */
    public void setCamposPrincipales(CamposPrincipales value) {
        this.camposPrincipales = value;
    }

    /**
     * Obtiene el valor de la propiedad camposCabecera.
     * 
     * @return
     *     possible object is
     *     {@link CamposCabecera }
     *     
     */
    public CamposCabecera getCamposCabecera() {
        return camposCabecera;
    }

    /**
     * Define el valor de la propiedad camposCabecera.
     * 
     * @param value
     *     allowed object is
     *     {@link CamposCabecera }
     *     
     */
    public void setCamposCabecera(CamposCabecera value) {
        this.camposCabecera = value;
    }

    /**
     * Obtiene el valor de la propiedad camposSecundarios.
     * 
     * @return
     *     possible object is
     *     {@link CamposSecundarios }
     *     
     */
    public CamposSecundarios getCamposSecundarios() {
        return camposSecundarios;
    }

    /**
     * Define el valor de la propiedad camposSecundarios.
     * 
     * @param value
     *     allowed object is
     *     {@link CamposSecundarios }
     *     
     */
    public void setCamposSecundarios(CamposSecundarios value) {
        this.camposSecundarios = value;
    }

    /**
     * Obtiene el valor de la propiedad camposAuxiliares.
     * 
     * @return
     *     possible object is
     *     {@link CamposAuxiliaress }
     *     
     */
    public CamposAuxiliares getCamposAuxiliares() {
        return camposAuxiliares;
    }

    /**
     * Define el valor de la propiedad camposAuxiliares.
     * 
     * @param value
     *     allowed object is
     *     {@link CamposAuxiliares }
     *     
     */
    public void setCamposAuxiliares(CamposAuxiliares value) {
        this.camposAuxiliares = value;
    }
    
    /**
     * Obtiene el valor de la propiedad camposDetalleTrasero.
     * 
     * @return
     *     possible object is
     *     {@link CamposDetalleTrasero }
     *     
     */
    public CamposDetalleTrasero getCamposDetalleTrasero() {
        return camposDetalleTrasero;
    }

    /**
     * Define el valor de la propiedad camposDetalleTrasero.
     * 
     * @param value
     *     allowed object is
     *     {@link CamposDetalleTrasero }
     *     
     */
    public void setCamposDetalleTrasero(CamposDetalleTrasero value) {
        this.camposDetalleTrasero = value;
    }

}
