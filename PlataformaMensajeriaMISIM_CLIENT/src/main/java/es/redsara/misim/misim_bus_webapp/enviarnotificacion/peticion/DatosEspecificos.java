package es.redsara.misim.misim_bus_webapp.enviarnotificacion.peticion;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.w3c.dom.Element;

import es.minhap.plataformamensajeria.sm.modelo.NotificacionPushData;

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
	    "notificacionDataRequest"})

public class DatosEspecificos  {

	@XmlElement(name = "NotificacionDataRequest", required = true)
	protected NotificacionDataRequest notificacionDataRequest;

	public NotificacionDataRequest getNotificacionDataRequest() {
		return notificacionDataRequest;
	}

	public void setNotificacionDataRequest(NotificacionDataRequest notificacionDataRequest) {
		this.notificacionDataRequest = notificacionDataRequest;
	}

	@Override
	public String toString() {
		return "DatosEspecificos [notificacionDataRequest=" + notificacionDataRequest + "]";
	}

	
	
	
}
