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
import javax.xml.bind.annotation.XmlType;

import es.minhap.plataformamensajeria.sm.modelo.Adjunto;


/**
 * <p>Clase Java para adjuntos complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="adjuntos"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Adjunto" type="{http://misim.redsara.es/misim-bus-webapp/peticion}adjunto"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "adjuntos", propOrder = {
    "adjunto"
})
public class AdjuntosPeticionLotesXMLBean {
	 @XmlElement(name = "Adjunto", namespace="http://misim.redsara.es/misim-bus-webapp/peticion", required = true)
	    protected List<AdjuntoPeticionLotesXMLBean> adjunto;

	    /**
	     * Gets the value of the adjunto property.
	     * 
	     * <p>
	     * This accessor method returns a reference to the live list,
	     * not a snapshot. Therefore any modification you make to the
	     * returned list will be present inside the JAXB object.
	     * This is why there is not a <CODE>set</CODE> method for the adjunto property.
	     * 
	     * <p>
	     * For example, to add a new item, do as follows:
	     * <pre>
	     *    getAdjunto().add(newItem);
	     * </pre>
	     * 
	     * 
	     * <p>
	     * Objects of the following type(s) are allowed in the list
	     * {@link Adjunto }
	     * 
	     * 
	     */
	    public List<AdjuntoPeticionLotesXMLBean> getAdjunto() {
	        if (adjunto == null) {
	            adjunto = new ArrayList<AdjuntoPeticionLotesXMLBean>();
	        }
	        return this.adjunto;
	    }

	}
