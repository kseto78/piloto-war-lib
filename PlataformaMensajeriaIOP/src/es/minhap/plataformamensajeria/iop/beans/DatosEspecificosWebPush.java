package es.minhap.plataformamensajeria.iop.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
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
 *         &lt;element name="Endpoint" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Auth" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Pdh" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="VapidPrivateKey" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="VapidPublicKey" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Caducidad" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
		 "endpoint",
		    "auth",
		    "pdh",
		    "vapidPrivateKey",
		    "vapidPublicKey",
		    "caducidad",
		    "cabecera",
		    "cuerpo"})

@XmlRootElement(name="DatosEspecificosWebPush", namespace=DatosEspecificosWebPush.R_CONST_1)
public class DatosEspecificosWebPush  {
	protected static final String R_CONST_1 = "http://misim.redsara.es/misim-bus-webapp/peticion";
	@XmlElement(name = "Endpoint", required = true, namespace=R_CONST_1)
	protected String endpoint;
	@XmlElement(name = "Auth", required = true, namespace=R_CONST_1)
	protected String auth;
	@XmlElement(name = "Pdh", required = true, namespace=R_CONST_1)
	protected String pdh;
	@XmlElement(name = "VapidPrivateKey", required = true, namespace=R_CONST_1)
	protected String vapidPrivateKey;
	@XmlElement(name = "VapidPublicKey", required = true, namespace=R_CONST_1)
	protected String vapidPublicKey;
	@XmlElement(name = "Caducidad", required = true, namespace=R_CONST_1)
	protected String caducidad;
	@XmlElement(name = "Cabecera", required = true, namespace=R_CONST_1)
	public String cabecera;
	@XmlElement(name = "Cuerpo", required = true, namespace=R_CONST_1)
	public String cuerpo;


	/**
	 * @return the endpoint
	 */
	public String getEndpoint() {
		return endpoint;
	}


	/**
	 * @param endpoint the endpoint to set
	 */
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}


	/**
	 * @return the auth
	 */
	public String getAuth() {
		return auth;
	}


	/**
	 * @param auth the auth to set
	 */
	public void setAuth(String auth) {
		this.auth = auth;
	}


	/**
	 * @return the pdh
	 */
	public String getPdh() {
		return pdh;
	}


	/**
	 * @param pdh the pdh to set
	 */
	public void setPdh(String pdh) {
		this.pdh = pdh;
	}


	/**
	 * @return the vapidPrivateKey
	 */
	public String getVapidPrivateKey() {
		return vapidPrivateKey;
	}


	/**
	 * @param vapidPrivateKey the vapidPrivateKey to set
	 */
	public void setVapidPrivateKey(String vapidPrivateKey) {
		this.vapidPrivateKey = vapidPrivateKey;
	}


	/**
	 * @return the vapidPublicKey
	 */
	public String getVapidPublicKey() {
		return vapidPublicKey;
	}


	/**
	 * @param vapidPublicKey the vapidPublicKey to set
	 */
	public void setVapidPublicKey(String vapidPublicKey) {
		this.vapidPublicKey = vapidPublicKey;
	}


	/**
	 * @return the caducidad
	 */
	public String getCaducidad() {
		return caducidad;
	}


	/**
	 * @param caducidad the caducidad to set
	 */
	public void setCaducidad(String caducidad) {
		this.caducidad = caducidad;
	}

	
	/**
	 * @return the cabecera
	 */
	public String getCabecera() {
		return cabecera;
	}


	/**
	 * @param cabecera the cabecera to set
	 */
	public void setCabecera(String cabecera) {
		this.cabecera = cabecera;
	}


	/**
	 * @return the cuerpo
	 */
	public String getCuerpo() {
		return cuerpo;
	}


	/**
	 * @param cuerpo the cuerpo to set
	 */
	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}


	@Override
	public String toString() {
		return "DatosEspecificos [Endpoint=" + endpoint + ", Auth=" + auth + ", pdh=" + pdh + ", vapidPrivateKey=" + vapidPrivateKey + 
				", vapidPublicKey=" + vapidPublicKey+ ", Caducidad=" + caducidad+ "]";
	}
	
	
}
