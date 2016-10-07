
package com.i3g.schema.siroccopushservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para PushMORequestArrayType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="PushMORequestArrayType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mo" type="{http://i3g.com/schema/SiroccoPushService}PushMORequestType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PushMORequestArrayType", propOrder = {
    "mo"
})
public class PushMORequestArrayType {

    protected List<PushMORequestType> mo;

    /**
     * Gets the value of the mo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PushMORequestType }
     * 
     * 
     */
    public List<PushMORequestType> getMo() {
        if (mo == null) {
            mo = new ArrayList<PushMORequestType>();
        }
        return this.mo;
    }

}
