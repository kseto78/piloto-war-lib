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
 * <p>Clase Java para Mensajes complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Mensajes"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="MensajeSMS" type="{http://misim.redsara.es/misim-bus-webapp/peticion}mensajeSMS" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="MensajeEmail" type="{http://misim.redsara.es/misim-bus-webapp/peticion}mensajeEmail" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="MensajePush" type="{http://misim.redsara.es/misim-bus-webapp/peticion}mensajePush" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="MensajeWebPush" type="{http://misim.redsara.es/misim-bus-webapp/peticion}mensajeWebPush" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = MensajesPeticionLotesXMLBean.R_CONST_1, propOrder = {
    "mensajeSMS",
    "mensajeEmail",
    "mensajePush",
    "mensajeWebPush"
})
@XmlRootElement(name = MensajesPeticionLotesXMLBean.R_CONST_1, namespace=MensajesPeticionLotesXMLBean.R_CONST_2)
public class MensajesPeticionLotesXMLBean {

    protected static final String R_CONST_1 = "Mensajes";
	protected static final String R_CONST_2 = "http://misim.redsara.es/misim-bus-webapp/peticion";
	@XmlElement(name = "MensajeSMS", namespace=R_CONST_2)
    protected List<MensajePeticionLotesSMSXMLBean> mensajeSMS;
    @XmlElement(name = "MensajeEmail", namespace=R_CONST_2)
    protected List<MensajePeticionLotesEmailXMLBean> mensajeEmail;
    @XmlElement(name = "MensajePush", namespace=R_CONST_2)
    protected List<MensajePeticionLotesPushXMLBean> mensajePush;
    @XmlElement(name = "MensajeWebPush", namespace=R_CONST_2)
    protected List<MensajePeticionLotesWebPushXMLBean> mensajeWebPush;

    /**
     * Gets the value of the mensajeSMS property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mensajeSMS property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMensajeSMS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MensajePeticionLotesSMSXMLBean }
     * 
     * 
     */
    public List<MensajePeticionLotesSMSXMLBean> getMensajeSMS() {
        if (mensajeSMS == null) {
            mensajeSMS = new ArrayList<>();
        }
        return this.mensajeSMS;
    }

    /**
     * Gets the value of the mensajeEmail property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mensajeEmail property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMensajeEmail().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MensajePeticionLotesEmailXMLBean }
     * 
     * 
     */
    public List<MensajePeticionLotesEmailXMLBean> getMensajeEmail() {
        if (mensajeEmail == null) {
            mensajeEmail = new ArrayList<>();
        }
        return this.mensajeEmail;
    }

    /**
     * Gets the value of the mensajePush property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mensajePush property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMensajePush().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MensajePeticionLotesPushXMLBean }
     * 
     * 
     */
    public List<MensajePeticionLotesPushXMLBean> getMensajePush() {
        if (mensajePush == null) {
            mensajePush = new ArrayList<>();
        }
        return this.mensajePush;
    }
    
    
    /**
     * Gets the value of the mensajeWebPush property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mensajePush property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMensajePush().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MensajePeticionLotesWebPushXMLBean }
     * 
     * 
     */
    public List<MensajePeticionLotesWebPushXMLBean> getMensajeWebPush() {
        if (mensajeWebPush == null) {
        	mensajeWebPush = new ArrayList<>();
        }
        return this.mensajeWebPush;
    }

}
