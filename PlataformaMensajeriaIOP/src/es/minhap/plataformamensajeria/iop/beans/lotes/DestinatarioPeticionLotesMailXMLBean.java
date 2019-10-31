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
 * <p>Clase Java para DestinatariosMail complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DestinatariosMail"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="DocUsuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IdExterno" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Destinatarios" type="{http://misim.redsara.es/misim-bus-webapp/peticion}destinatarios"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = DestinatarioPeticionLotesMailXMLBean.R_CONST_2, propOrder = {
    "docUsuario",
    "idExterno",
    "destinatarios"
})
@XmlRootElement(name = DestinatarioPeticionLotesMailXMLBean.R_CONST_2, namespace=DestinatarioPeticionLotesMailXMLBean.R_CONST_1)
public class DestinatarioPeticionLotesMailXMLBean {

    protected static final String R_CONST_1 = "http://misim.redsara.es/misim-bus-webapp/peticion";
	protected static final String R_CONST_2 = "DestinatarioMail";
	@XmlElement(name = "DocUsuario", namespace=R_CONST_1)
    protected String docUsuario;
    @XmlElement(name = "IdExterno", required = true, namespace=R_CONST_1)
    protected String idExterno;
    @XmlElement(name = "Destinatarios", required = true, namespace=R_CONST_1)
    protected DestinatariosPeticionLotesXMLBean destinatarios;

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
     * Obtiene el valor de la propiedad destinatarios.
     * 
     * @return
     *     possible object is
     *     {@link DestinatariosPeticionLotesXMLBean }
     *     
     */
    public DestinatariosPeticionLotesXMLBean getDestinatarios() {
        return destinatarios;
    }

    /**
     * Define el valor de la propiedad destinatarios.
     * 
     * @param value
     *     allowed object is
     *     {@link DestinatariosPeticionLotesXMLBean }
     *     
     */
    public void setDestinatarios(DestinatariosPeticionLotesXMLBean value) {
        this.destinatarios = value;
    }

}
