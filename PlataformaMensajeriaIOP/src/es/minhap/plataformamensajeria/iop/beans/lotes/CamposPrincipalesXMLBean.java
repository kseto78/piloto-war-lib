
package es.minhap.plataformamensajeria.iop.beans.lotes;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para camposPrincipales complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="camposPrincipales">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PkFields" type="{http://misim.redsara.es/misim-bus-webapp/peticion}pkFields"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "camposPrincipales", propOrder = {
    "pkFields"
})
@XmlRootElement(name = "CamposPrincipales", namespace=CamposPrincipalesXMLBean.R_CONST_1)
public class CamposPrincipalesXMLBean {

    protected static final String R_CONST_1 = "http://misim.redsara.es/misim-bus-webapp/peticion";
	@XmlElement(name = "PkFields", required = true, namespace=R_CONST_1)
    protected PkFieldsXMLBean pkFields;

    /**
     * Obtiene el valor de la propiedad pkFields.
     * 
     * @return
     *     possible object is
     *     {@link PkFieldsXMLBean }
     *     
     */
    public PkFieldsXMLBean getPkFields() {
        return pkFields;
    }

    /**
     * Define el valor de la propiedad pkFields.
     * 
     * @param value
     *     allowed object is
     *     {@link PkFieldsXMLBean }
     *     
     */
    public void setPkFields(PkFieldsXMLBean value) {
        this.pkFields = value;
    }
    
    public List<PkFieldsXMLBean> getPkFieldsList() {
    	List<PkFieldsXMLBean> pkFields = new ArrayList<>();
    	if (null != getPkFields()) {
    		pkFields.add(getPkFields());
    	}
    	return pkFields;
    }

}
