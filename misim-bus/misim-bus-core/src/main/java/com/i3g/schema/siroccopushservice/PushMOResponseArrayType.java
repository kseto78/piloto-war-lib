
package com.i3g.schema.siroccopushservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para PushMOResponseArrayType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="PushMOResponseArrayType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="moresponse" type="{http://i3g.com/schema/SiroccoPushService}PushMOResponseType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PushMOResponseArrayType", propOrder = {
    "moresponse"
})
public class PushMOResponseArrayType {

    protected List<PushMOResponseType> moresponse;

    /**
     * Gets the value of the moresponse property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the moresponse property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMoresponse().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PushMOResponseType }
     * 
     * 
     */
    public List<PushMOResponseType> getMoresponse() {
        if (moresponse == null) {
            moresponse = new ArrayList<PushMOResponseType>();
        }
        return this.moresponse;
    }

}
