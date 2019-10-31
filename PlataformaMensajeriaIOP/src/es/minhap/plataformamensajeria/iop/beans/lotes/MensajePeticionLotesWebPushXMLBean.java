//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantaci�n de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perder�n si se vuelve a compilar el esquema de origen. 
// Generado el: 2016.02.18 a las 12:19:53 PM CET 
//


package es.minhap.plataformamensajeria.iop.beans.lotes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para mensajePush complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="mensajePush"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Titulo" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Cuerpo" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *        &lt;element name="DestinatariosWebPush" type="{http://misim.redsara.es/misim-bus-webapp/peticion}DestinatariosWebPush" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mensajeWebPush", propOrder = {
    "titulo",
    "cuerpo",
    "destinatariosWebPush"
})
@XmlRootElement(name = "MensajeWebPush", namespace=MensajePeticionLotesWebPushXMLBean.R_CONST_1)
public class MensajePeticionLotesWebPushXMLBean {

    protected static final String R_CONST_1 = "http://misim.redsara.es/misim-bus-webapp/peticion";
	@XmlElement(name = "Titulo", required = true, namespace=R_CONST_1)
    protected String titulo;
    @XmlElement(name = "Cuerpo", required = true, namespace=R_CONST_1)
    protected String cuerpo;
    @XmlElement(name = "DestinatariosWebPush", namespace=R_CONST_1)
    protected DestinatariosPeticionLotesWebPushXMLBean destinatariosWebPush;

    /**
     * Obtiene el valor de la propiedad titulo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Define el valor de la propiedad titulo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitulo(String value) {
        this.titulo = value;
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

   

    /**
     * Obtiene el valor de la propiedad destinatariosWebPush.
     * 
     * @return
     *     possible object is
     *     {@link DestinatariosPeticionLotesPushXMLBean }
     *     
     */
    public DestinatariosPeticionLotesWebPushXMLBean getDestinatariosWebPush() {
        return destinatariosWebPush;
    }

    /**
     * Define el valor de la propiedad destinatariosWebPush.
     * 
     * @param value
     *     allowed object is
     *     {@link DestinatariosPeticionLotesWebPushXMLBean }
     *     
     */
    public void setDestinatariosWebPush(DestinatariosPeticionLotesWebPushXMLBean value) {
        this.destinatariosWebPush = value;
    }

}
