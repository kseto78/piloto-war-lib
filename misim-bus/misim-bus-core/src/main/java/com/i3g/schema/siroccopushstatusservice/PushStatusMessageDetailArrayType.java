
package com.i3g.schema.siroccopushstatusservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para PushStatusMessageDetailArrayType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="PushStatusMessageDetailArrayType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PushStatusMessageDetail" type="{http://i3g.com/schema/SiroccoPushStatusService}PushStatusMessageDetailType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PushStatusMessageDetailArrayType", propOrder = {
    "pushStatusMessageDetail"
})
public class PushStatusMessageDetailArrayType {

    @XmlElement(name = "PushStatusMessageDetail")
    protected List<PushStatusMessageDetailType> pushStatusMessageDetail;

    /**
     * Gets the value of the pushStatusMessageDetail property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pushStatusMessageDetail property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPushStatusMessageDetail().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PushStatusMessageDetailType }
     * 
     * 
     */
    public List<PushStatusMessageDetailType> getPushStatusMessageDetail() {
        if (pushStatusMessageDetail == null) {
            pushStatusMessageDetail = new ArrayList<PushStatusMessageDetailType>();
        }
        return this.pushStatusMessageDetail;
    }

}
