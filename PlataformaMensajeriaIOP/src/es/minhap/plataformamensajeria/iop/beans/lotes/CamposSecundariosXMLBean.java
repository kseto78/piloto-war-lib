
package es.minhap.plataformamensajeria.iop.beans.lotes;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para camposSecundarios complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="camposSecundarios">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PkFields" type="{http://misim.redsara.es/misim-bus-webapp/peticion}pkFields" maxOccurs="3" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "camposSecundarios", propOrder = {
    "pkFields"
})

@XmlRootElement(name = "CamposSecundarios", namespace=CamposSecundariosXMLBean.R_CONST_1)
public class CamposSecundariosXMLBean {

    protected static final String R_CONST_1 = "http://misim.redsara.es/misim-bus-webapp/peticion";
	@XmlElement(name = "PkFields", namespace=R_CONST_1)
    protected List<PkFieldsXMLBean> pkFields;

    /**
     * Gets the value of the pkFields property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pkFields property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPkFields().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PkFieldsXMLBean }
     * 
     * 
     */
    public List<PkFieldsXMLBean> getPkFields() {
        if (pkFields == null) {
            pkFields = new ArrayList<>();
        }
        return this.pkFields;
    }

}
