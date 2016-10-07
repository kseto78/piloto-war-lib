//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantaci�n de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perder�n si se vuelve a compilar el esquema de origen. 
// Generado el: 2016.05.30 a las 04:43:26 PM CEST 
//


package es.minhap.plataformamensaferia.iop.beans.envioPremium;

import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import es.minhap.plataformamensajeria.iop.beans.respuestaSeguimiento.SeguimientoMensaje;
import es.minhap.plataformamensajeria.iop.services.exceptions.PlataformaBusinessException;
import es.minhap.plataformamensajeria.iop.util.Utils;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="idExterno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="messageId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="messageStatus" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="statusText" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "idExterno",
    "messageId",
    "messageStatus",
    "statusText"
})
@XmlRootElement(name = "PeticionNotificacionEstadoSMS", namespace="https://www2.agenciatributaria.gob.es/static_files/common/internet/dep/aduanas/es/aeat/dit/adu/itea/server/AcCLEV1Ent.xsd" )
public class PeticionNotificacionEstadoSMS {

	private static final String TAG_ERROR_GENERANDO_RESPUESTA_XML = "Error generando la respuesta";
	private static String PENDIENTE_ENVIO= "PENDIENTE DE ENVIO";
	private static String ENVIANDO = "ENVIANDO";
	private static String INCIDENCIA="INCIDENCIA";
	private static String PENDIENTE_OPERADORA ="PENDIENTE DE OPERADORA";
	private static String ENVIADO= "ENVIADO";
	private static String ANULADO= "ANULADO";
	
    @XmlElement(name="IdExterno", required = true, namespace="https://www2.agenciatributaria.gob.es/static_files/common/internet/dep/aduanas/es/aeat/dit/adu/itea/server/AcCLEV1Ent.xsd" )
    protected String idExterno;
    @XmlElement(required = true, namespace="https://www2.agenciatributaria.gob.es/static_files/common/internet/dep/aduanas/es/aeat/dit/adu/itea/server/AcCLEV1Ent.xsd" )
    protected String messageId;
    @XmlElement(required = true, namespace="https://www2.agenciatributaria.gob.es/static_files/common/internet/dep/aduanas/es/aeat/dit/adu/itea/server/AcCLEV1Ent.xsd" )
    protected String messageStatus;
    @XmlElement(required = true, namespace="https://www2.agenciatributaria.gob.es/static_files/common/internet/dep/aduanas/es/aeat/dit/adu/itea/server/AcCLEV1Ent.xsd" )
    protected String statusText;


    /**
     * Obtiene el valor de la propiedad idExterno.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdExterno() {
        return idExterno;
    }

    /**
     * Define el valor de la propiedad idExterno.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdExterno(String value) {
        this.idExterno = value;
    }

    /**
     * Obtiene el valor de la propiedad messageId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageId() {
        return messageId;
    }

    /**
     * Define el valor de la propiedad messageId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageId(String value) {
        this.messageId = value;
    }

    /**
     * Obtiene el valor de la propiedad messageStatus.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageStatus() {
        return messageStatus;
    }

    /**
     * Define el valor de la propiedad messageStatus.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageStatus(String value) {
        this.messageStatus = value;
    }

    /**
     * Obtiene el valor de la propiedad statusText.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusText() {
        return statusText;
    }

    /**
     * Define el valor de la propiedad statusText.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusText(String value) {
        this.statusText = value;
    }

public String toXMLEstado(ArrayList<SeguimientoMensaje> listaResultados, String sender, String recipient, String statusText)  throws PlataformaBusinessException{
		
		for (SeguimientoMensaje mensaje : listaResultados) {
			this.setIdExterno(mensaje.getIdExterno());
			this.setMessageId(String.valueOf(mensaje.getIdMensaje()));
			this.setStatusText(statusText);
			
			if (PENDIENTE_ENVIO.equals( mensaje.getEstado()) || ENVIANDO.equals(mensaje.getEstado()) || PENDIENTE_OPERADORA.equals(mensaje.getEstado())) {
				this.setMessageStatus("Pendiente");
			} else if (INCIDENCIA.equals(mensaje.getEstado()) || ANULADO.equals(mensaje.getEstado())){
				this.setMessageStatus("Rechazado");
			} else if (ENVIADO.equals(mensaje.getEstado())) {
				this.setMessageStatus("Entregado");
			}
		}
		
		PeticionNotificacionEstadoSMS operacionesMensajes = this;
		
		try {
	        JAXBContext jaxbContext = JAXBContext.newInstance(PeticionNotificacionEstadoSMS.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	
			
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			StringWriter writer = new StringWriter();
			jaxbMarshaller.marshal(operacionesMensajes, writer);
			jaxbMarshaller.marshal(operacionesMensajes, System.out);
			
			return Utils.convertToUTF8(writer.toString());
			
		} catch (Exception e) {
			return Utils.convertToUTF8(TAG_ERROR_GENERANDO_RESPUESTA_XML);
		}
	}
public void loadObjectFromXML(String xml)
		throws PlataformaBusinessException {

	JAXBContext jaxbContext;
	try {
		jaxbContext = JAXBContext.newInstance(PeticionNotificacionEstadoSMS.class);

		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		StringReader reader = new StringReader(xml);
		PeticionNotificacionEstadoSMS respuesta = (PeticionNotificacionEstadoSMS) unmarshaller
				.unmarshal(reader);

		org.apache.commons.beanutils.BeanUtils.copyProperties(this,
				respuesta);

		
	} catch (JAXBException e) {
		throw new PlataformaBusinessException(
				"Error procesando el XML.\nCausa: " + e.getCause()
						+ "\nMensaje: " + e.getMessage() + "\nXML:\n"
						+ xml);
	} catch (IllegalAccessException e) {
		throw new PlataformaBusinessException(
				"Error procesando el XML.\nCausa: " + e.getCause()
						+ "\nMensaje: " + e.getMessage() + "\nXML:\n"
						+ xml);
	} catch (InvocationTargetException e) {
		throw new PlataformaBusinessException(
				"Error procesando el XML.\nCausa: " + e.getCause()
						+ "\nMensaje: " + e.getMessage() + "\nXML:\n"
						+ xml);
	}
}

public String toXML() throws PlataformaBusinessException{
	
	PeticionNotificacionEstadoSMS envioXML = this;
	
	try {
    JAXBContext jaxbContext = JAXBContext.newInstance(PeticionNotificacionEstadoSMS.class);
	Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

	
	jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	
	StringWriter writer = new StringWriter();
	jaxbMarshaller.marshal(envioXML, writer);
	jaxbMarshaller.marshal(envioXML, System.out);
	
	return writer.toString();
	} catch (PropertyException e) {
		throw new PlataformaBusinessException("Error generando el XML.\nCausa: " + e.getCause()+"\nMensaje: " + e.getMessage());
	} catch (JAXBException e) {
		throw new PlataformaBusinessException("Error generando el XML.\nCausa: " + e.getCause()+"\nMensaje: " + e.getMessage());
	}
	
}

}
