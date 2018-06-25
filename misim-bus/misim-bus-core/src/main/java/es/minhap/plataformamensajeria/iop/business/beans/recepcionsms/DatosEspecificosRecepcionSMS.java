package es.minhap.plataformamensajeria.iop.business.beans.recepcionsms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Clase Java para DatosEspecificos complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DatosEspecificos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SMS_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SMS_USUARIO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SMS_PASSWORD" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SMS_UIM" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SMS_HEADER" type="{http://www.w3.org/2001/XMLSchema}string"/> *         
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatosEspecificos", propOrder = {
	    "envioAplicacionRequest"
	    })

public class DatosEspecificosRecepcionSMS  {

	@XmlElement(name = "envioAplicacionRequest", required = true)
	protected EnvioAplicacionRequest envioAplicacionRequest;

	public EnvioAplicacionRequest getEnvioAplicacionRequest() {
		return envioAplicacionRequest;
	}

	public void setEnvioAplicacionRequest(EnvioAplicacionRequest envioAplicacionRequest) {
		this.envioAplicacionRequest = envioAplicacionRequest;
	}

	@Override
	public String toString() {
		return "DatosEspecificos [envioAplicacionRequest=" + envioAplicacionRequest + "]";
	}

	
	

}
