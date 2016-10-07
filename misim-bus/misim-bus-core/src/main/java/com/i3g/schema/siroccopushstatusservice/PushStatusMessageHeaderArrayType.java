
package com.i3g.schema.siroccopushstatusservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para PushStatusMessageHeaderArrayType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="PushStatusMessageHeaderArrayType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PushStatusMessageHeader" type="{http://i3g.com/schema/SiroccoPushStatusService}PushStatusMessageHeaderType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PushStatusMessageHeaderArrayType", propOrder = {
    "pushStatusMessageHeader"
})
public class PushStatusMessageHeaderArrayType {

    @XmlElement(name = "PushStatusMessageHeader")
    protected List<PushStatusMessageHeaderType> pushStatusMessageHeader;

    /**
     * Gets the value of the pushStatusMessageHeader property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pushStatusMessageHeader property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPushStatusMessageHeader().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PushStatusMessageHeaderType }
     * 
     * 
     */
    public List<PushStatusMessageHeaderType> getPushStatusMessageHeader() {
        if (pushStatusMessageHeader == null) {
            pushStatusMessageHeader = new ArrayList<PushStatusMessageHeaderType>();
        }
        return this.pushStatusMessageHeader;
    }

}
