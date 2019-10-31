//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantaci�n de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perder�n si se vuelve a compilar el esquema de origen. 
// Generado el: 2016.02.18 a las 12:19:53 PM CET 
//


package es.minhap.plataformamensajeria.iop.beans.lotes;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para DestinatariosPush complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DestinatariosPush"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="DocUsuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IdExterno" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="IdentificadorUsuario" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = DestinatariosPeticionLotesWebPushXMLBean.R_CONST_2, propOrder = {
    "destinatarioWebPush"
})
@XmlRootElement(name = DestinatariosPeticionLotesWebPushXMLBean.R_CONST_2, namespace=DestinatariosPeticionLotesWebPushXMLBean.R_CONST_1)
public class DestinatariosPeticionLotesWebPushXMLBean {

	protected static final String R_CONST_1 = "http://misim.redsara.es/misim-bus-webapp/peticion";
	protected static final String R_CONST_2 = "DestinatariosWebPush";
	@XmlElement(name = "DestinatarioWebPush", required = true, namespace=R_CONST_1)
	protected List<DestinatarioPeticionLotesWebPushXMLBean> destinatarioWebPush;

	 /**
     * Gets the value of the destinatariosWebPush property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the destinatariosMail property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDestinatariosMail().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DestinatariosPeticionLotesWebPushXMLBean }
     * 
     * 
     */
    public List<DestinatarioPeticionLotesWebPushXMLBean> getDestinatarioWebPush() {
        if (destinatarioWebPush == null) {
        	destinatarioWebPush = new ArrayList<>();
        }
        return this.destinatarioWebPush;
    }
}
