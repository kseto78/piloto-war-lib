package es.minhap.plataformamensaferia.iop.beans.envioPremium;

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
	    DatosEspecificos.R_CONST_1,
	    DatosEspecificos.R_CONST_5,
	    DatosEspecificos.R_CONST_2,
	    DatosEspecificos.R_CONST_6,
	    DatosEspecificos.R_CONST_3,
	    DatosEspecificos.R_CONST_4})

public class DatosEspecificos  {

	protected static final String R_CONST_1 = "SMS_ID";
	protected static final String R_CONST_2 = "SMS_PASSWORD";
	protected static final String R_CONST_3 = "SMS_HEADER";
	protected static final String R_CONST_4 = "SMS_TEXTO";
	protected static final String R_CONST_5 = "SMS_USUARIO";
	protected static final String R_CONST_6 = "SMS_DESTINATARIO";
	@XmlElement(name = R_CONST_1, required = true)
	protected String SMS_ID;
	@XmlElement(name = R_CONST_5, required = true)
	protected String SMS_USUARIO;
	@XmlElement(name = R_CONST_2, required = true)
	protected String SMS_PASSWORD;
	@XmlElement(name = R_CONST_6, required = true)
	protected String SMS_DESTINATARIO;
	@XmlElement(name = R_CONST_3, required = true)
	protected String SMS_HEADER;
	@XmlElement(name = R_CONST_4, required = true)
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

	public void setSMS_ID(String sMsId) {
		SMS_ID = sMsId;
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

	public void setSMS_HEADER(String sMsHeader) {
		SMS_HEADER = sMsHeader;
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

	public void setSMS_DESTINATARIO(String sMsDestinatario) {
		SMS_DESTINATARIO = sMsDestinatario;
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

	public void setSMS_TEXTO(String sMsTexto) {
		SMS_TEXTO = sMsTexto;
	}

	@Override
	public String toString() {
		return "DatosEspecificos [SMS_ID=" + SMS_ID + ", SMS_USUARIO=" + SMS_USUARIO + ", SMS_PASSWORD=" + SMS_PASSWORD + ", SMS_DESTINATARIO=" + SMS_DESTINATARIO + ", SMS_HEADER=" + SMS_HEADER + ", SMS_TEXTO=" + SMS_TEXTO + "]";
	}

	public String getSMS_USUARIO() {
		return SMS_USUARIO;
	}

	public void setSMS_USUARIO(String sMsUsuario) {
		SMS_USUARIO = sMsUsuario;
	}

	public String getSMS_PASSWORD() {
		return SMS_PASSWORD;
	}

	public void setSMS_PASSWORD(String sMsPassword) {
		SMS_PASSWORD = sMsPassword;
	}
}
