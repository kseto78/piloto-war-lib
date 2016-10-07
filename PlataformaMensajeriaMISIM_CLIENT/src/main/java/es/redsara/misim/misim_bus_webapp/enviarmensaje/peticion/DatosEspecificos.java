package es.redsara.misim.misim_bus_webapp.enviarmensaje.peticion;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.w3c.dom.Element;

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
 *         &lt;element name="SMS_DESTINATARIO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SMS_HEADER" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SMS_TEXTO" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
	    "SMS_ID",
	    "SMS_USUARIO",
	    "SMS_PASSWORD",
	    "SMS_DESTINATARIO",
	    "SMS_HEADER",
	    "SMS_TEXTO"})

public class DatosEspecificos  {

	@XmlElement(name = "SMS_ID", required = true)
	protected String SMS_ID;
	@XmlElement(name = "SMS_USUARIO", required = true)
	protected String SMS_USUARIO;
	@XmlElement(name = "SMS_PASSWORD", required = true)
	protected String SMS_PASSWORD;
	@XmlElement(name = "SMS_DESTINATARIO", required = true)
	protected String SMS_DESTINATARIO;
	@XmlElement(name = "SMS_HEADER", required = true)
	protected String SMS_HEADER;
	@XmlElement(name = "SMS_TEXTO", required = true)
	protected String SMS_TEXTO;

	/**
	 * Obtiene el valor de la propiedad SMS_ID.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSMS_ID() {
		return SMS_ID;
	}

	public void setSMS_ID(String sMS_ID) {
		SMS_ID = sMS_ID;
	}

	/**
	 * Obtiene el valor de la propiedad SMS_HEADER.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSMS_HEADER() {
		return SMS_HEADER;
	}

	public void setSMS_HEADER(String sMS_HEADER) {
		SMS_HEADER = sMS_HEADER;
	}

	/**
	 * Obtiene el valor de la propiedad SMS_DESTINATARIO.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSMS_DESTINATARIO() {
		return SMS_DESTINATARIO;
	}

	public void setSMS_DESTINATARIO(String sMS_DESTINATARIO) {
		SMS_DESTINATARIO = sMS_DESTINATARIO;
	}
	
	
	/**
	 * Obtiene el valor de la propiedad SMS_TEXTO.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSMS_TEXTO() {
		return SMS_TEXTO;
	}

	public void setSMS_TEXTO(String sMS_TEXTO) {
		SMS_TEXTO = sMS_TEXTO;
	}

	@Override
	public String toString() {
		return "DatosEspecificos [SMS_ID=" + SMS_ID + ", SMS_USUARIO=" + SMS_USUARIO + ", SMS_PASSWORD=" + SMS_PASSWORD + ", SMS_DESTINATARIO=" + SMS_DESTINATARIO + ", SMS_HEADER=" + SMS_HEADER + ", SMS_TEXTO=" + SMS_TEXTO + "]";
	}

	public String getSMS_USUARIO() {
		return SMS_USUARIO;
	}

	public void setSMS_USUARIO(String sMS_USUARIO) {
		SMS_USUARIO = sMS_USUARIO;
	}

	public String getSMS_PASSWORD() {
		return SMS_PASSWORD;
	}

	public void setSMS_PASSWORD(String sMS_PASSWORD) {
		SMS_PASSWORD = sMS_PASSWORD;
	}
}
