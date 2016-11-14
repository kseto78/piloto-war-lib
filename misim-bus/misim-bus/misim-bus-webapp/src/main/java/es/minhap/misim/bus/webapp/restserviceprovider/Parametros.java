
package es.minhap.misim.bus.webapp.restserviceprovider;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para responseStatusType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="responseStatusType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SAMLRequest" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="excludedIdPList" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="allowLegalPerson" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "parameters", propOrder = {
    "parametro1",
    "parametro2",
    "parametro3"
})
public class Parametros {

    @XmlElement(name = "Parametro1", required = true, namespace = "http://misim.redsara.es/misim-bus-webapp/respuestaSAMLResponse")
    protected String parametro1;
    @XmlElement(name = "Parametro2", required = true, namespace = "http://misim.redsara.es/misim-bus-webapp/respuestaSAMLResponse")
    protected String parametro2;
    @XmlElement(name = "Parametro3", namespace = "http://misim.redsara.es/misim-bus-webapp/respuestaSAMLResponse")
    protected String parametro3;
	/**
	 * @return the parametro1
	 */
	public String getParametro1() {
		return parametro1;
	}
	/**
	 * @param parametro1 the parametro1 to set
	 */
	public void setParametro1(String parametro1) {
		this.parametro1 = parametro1;
	}
	/**
	 * @return the parametro2
	 */
	public String getParametro2() {
		return parametro2;
	}
	/**
	 * @param parametro2 the parametro2 to set
	 */
	public void setParametro2(String parametro2) {
		this.parametro2 = parametro2;
	}
	/**
	 * @return the parametro3
	 */
	public String getParametro3() {
		return parametro3;
	}
	/**
	 * @param parametro3 the parametro3 to set
	 */
	public void setParametro3(String parametro3) {
		this.parametro3 = parametro3;
	}
	

}
