
package es.minhap.plataformamensajeria.iop.beans.lotes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
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
 *         &lt;element name="BackgroundColor" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ForegroundColor" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PassTypeIdentifier" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "backgroundColor",
    "foregroundColor",
    "passTypeIdentifier",
    "camposPrincipales",
    "camposCabecera",
    "camposSecundarios",
    "camposAuxiliares",
    "camposDetalleTrasero"
})
@XmlRootElement(name = "PassBook", namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
public class PassbookXMLBean {

    @XmlElement(name = "URL", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
    protected String url;
    @XmlElement(name = "LogoText", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
    protected String logoText;
    @XmlElement(name = "Description", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
    protected String description;
    @XmlElement(name = "BackgroundColor", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
    protected String backgroundColor;
    @XmlElement(name = "ForegroundColor", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
    protected String foregroundColor;
    @XmlElement(name = "PassTypeIdentifier", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
    protected String passTypeIdentifier;
    @XmlElement(name = "CamposPrincipales", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
    protected CamposPrincipalesXMLBean camposPrincipales;
    @XmlElement(name = "CamposCabecera", namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
    protected CamposCabeceraXMLBean camposCabecera;
    @XmlElement(name = "CamposSecundarios", namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
    protected CamposSecundariosXMLBean camposSecundarios;
    @XmlElement(name = "CamposAuxiliares", namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
    protected CamposAuxiliaresXMLBean camposAuxiliares;
    @XmlElement(name = "CamposDetalleTrasero", namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
    protected CamposDetalleTraseroXMLBean camposDetalleTrasero;

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
     * Obtiene el valor de la propiedad backgroundColor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * Define el valor de la propiedad backgroundColor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBackgroundColor(String value) {
        this.backgroundColor = value;
    }

    /**
     * Obtiene el valor de la propiedad foregroundColor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getForegroundColor() {
        return foregroundColor;
    }

    /**
     * Define el valor de la propiedad foregroundColor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setForegroundColor(String value) {
        this.foregroundColor = value;
    }

    /**
     * Obtiene el valor de la propiedad passTypeIdentifier.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassTypeIdentifier() {
        return passTypeIdentifier;
    }

    /**
     * Define el valor de la propiedad passTypeIdentifier.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassTypeIdentifier(String value) {
        this.passTypeIdentifier = value;
    }

    /**
     * Obtiene el valor de la propiedad camposPrincipales.
     * 
     * @return
     *     possible object is
     *     {@link CamposPrincipalesXMLBean }
     *     
     */
    public CamposPrincipalesXMLBean getCamposPrincipales() {
        return camposPrincipales;
    }

    /**
     * Define el valor de la propiedad camposPrincipales.
     * 
     * @param value
     *     allowed object is
     *     {@link CamposPrincipalesXMLBean }
     *     
     */
    public void setCamposPrincipales(CamposPrincipalesXMLBean value) {
        this.camposPrincipales = value;
    }

    /**
     * Obtiene el valor de la propiedad camposCabecera.
     * 
     * @return
     *     possible object is
     *     {@link CamposCabeceraXMLBean }
     *     
     */
    public CamposCabeceraXMLBean getCamposCabecera() {
        return camposCabecera;
    }

    /**
     * Define el valor de la propiedad camposCabecera.
     * 
     * @param value
     *     allowed object is
     *     {@link CamposCabeceraXMLBean }
     *     
     */
    public void setCamposCabecera(CamposCabeceraXMLBean value) {
        this.camposCabecera = value;
    }

    /**
     * Obtiene el valor de la propiedad camposSecundarios.
     * 
     * @return
     *     possible object is
     *     {@link CamposSecundariosXMLBean }
     *     
     */
    public CamposSecundariosXMLBean getCamposSecundarios() {
        return camposSecundarios;
    }

    /**
     * Define el valor de la propiedad camposSecundarios.
     * 
     * @param value
     *     allowed object is
     *     {@link CamposSecundariosXMLBean }
     *     
     */
    public void setCamposSecundarios(CamposSecundariosXMLBean value) {
        this.camposSecundarios = value;
    }

    /**
     * Obtiene el valor de la propiedad camposAuxiliares.
     * 
     * @return
     *     possible object is
     *     {@link CamposAuxiliaresXMLBean }
     *     
     */
    public CamposAuxiliaresXMLBean getCamposAuxiliares() {
        return camposAuxiliares;
    }

    /**
     * Define el valor de la propiedad camposAuxiliares.
     * 
     * @param value
     *     allowed object is
     *     {@link CamposAuxiliaresXMLBean }
     *     
     */
    public void setCamposAuxiliares(CamposAuxiliaresXMLBean value) {
        this.camposAuxiliares= value;
    }
    
    /**
     * Obtiene el valor de la propiedad camposDetalleTrasero.
     * 
     * @return
     *     possible object is
     *     {@link CamposDetalleTraseroXMLBean }
     *     
     */
    public CamposDetalleTraseroXMLBean getCamposDetalleTrasero() {
        return camposDetalleTrasero;
    }

    /**
     * Define el valor de la propiedad camposDetalleTrasero.
     * 
     * @param value
     *     allowed object is
     *     {@link CamposDetalleTraseroXMLBean }
     *     
     */
    public void setCamposDetalleTrasero(CamposDetalleTraseroXMLBean value) {
        this.camposDetalleTrasero = value;
    }

}
