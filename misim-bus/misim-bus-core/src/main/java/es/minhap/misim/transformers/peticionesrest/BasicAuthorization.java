
package es.minhap.misim.transformers.peticionesrest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/peticion}IdPeticion"/>
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/peticion}NumElementos"/>
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/peticion}TimeStamp"/>
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/peticion}Estado" minOccurs="0"/>
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/peticion}CodigoCertificado"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
		"user",
		"password"
})
@XmlRootElement(name = "BasicAuthorization", namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
public class BasicAuthorization {

	@XmlElement(name = "User", required = true, namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
    protected String user;
	
	@XmlElement(name = "Password", namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
    protected String password;

    /**
     * Obtiene el valor de la propiedad user.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUser() {
        return user;
    }

    /**
     * Define el valor de la propiedad user.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUser(String value) {
        this.user = value;
    }

    /**
     * Obtiene el valor de la propiedad password.
     * 
     */
    public String getPassword() {
        return password;
    }

    /**
     * Define el valor de la propiedad password.
     * 
     */
    public void setPassword(String value) {
        this.password = value;
    }

}
