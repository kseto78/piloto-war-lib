//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantaci�n de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perder�n si se vuelve a compilar el esquema de origen. 
// Generado el: 2016.02.24 a las 09:09:37 PM CET 
//

package es.minhap.plataformamensajeria.iop.beans.respuestasServiciosMoviles;

import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;

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

import es.minhap.plataformamensajeria.iop.services.exceptions.PlataformaBusinessException;
import es.minhap.plataformamensajeria.iop.util.PlataformaErrores;

/**
 * <p>
 * Clase Java para anonymous complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que
 * haya en esta clase.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="statusCode" type="{http://misim.redsara.es/misim-bus-webapp/respuesta}statusCode"/&gt;
 *         &lt;element name="statusText" type="{http://misim.redsara.es/misim-bus-webapp/respuesta}statusText"/&gt;
 *         &lt;element name="details" type="{http://misim.redsara.es/misim-bus-webapp/respuesta}details" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "status" })
@XmlRootElement(name = "RespuestaNotificacionesPush", namespace = "http://misim.redsara.es/misim-bus-webapp/respuesta")
public class RespuestaNotificacionesPush {

	static final String TAG_ERROR_GENERANDO_RESPUESTA_XML = "Se ha producido un error generando la cadena de respuesta";

	@XmlElement(name = "Status", required = true, namespace = "http://misim.redsara.es/misim-bus-webapp/respuesta")
	private ResponseNotificacionPushStatusType status;

	public void loadObjectFromXML(String xmlRespuesta) throws PlataformaBusinessException {

		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(RespuestaNotificacionesPush.class);

			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			StringReader reader = new StringReader(xmlRespuesta);
			RespuestaNotificacionesPush operacionesMensajes = (RespuestaNotificacionesPush) unmarshaller.unmarshal(reader);

			org.apache.commons.beanutils.BeanUtils.copyProperties(this, operacionesMensajes);

		} catch (JAXBException e) {
			throw new PlataformaBusinessException("Error procesando el XML.\nCausa: " + e.getCause() + "\nMensaje: " + e.getMessage() + "\nXML:\n" + xmlRespuesta);
		} catch (IllegalAccessException e) {
			throw new PlataformaBusinessException("Error procesando el XML.\nCausa: " + e.getCause() + "\nMensaje: " + e.getMessage() + "\nXML:\n" + xmlRespuesta);
		} catch (InvocationTargetException e) {
			throw new PlataformaBusinessException("Error procesando el XML.\nCausa: " + e.getCause() + "\nMensaje: " + e.getMessage() + "\nXML:\n" + xmlRespuesta);
		}
	}

	public String toXML(int resultado) throws PlataformaBusinessException {
		RespuestaNotificacionesPush respuesta = this;

		if (resultado >= 1) {
			ResponseNotificacionPushStatusType status = new ResponseNotificacionPushStatusType();
			status.setStatusCode(PlataformaErrores.STATUS_OK);
			status.setStatusText(PlataformaErrores.STATUSTEXT_OK);
			status.setDetails(PlataformaErrores.STATUSDETAILS_CHANGE_MESSAGE_STATUS_OK);
			respuesta.setStatus(status);
		} else {
			ResponseNotificacionPushStatusType status = new ResponseNotificacionPushStatusType();
			status.setStatusCode(PlataformaErrores.STATUSCODE_KO);
			status.setStatusText(PlataformaErrores.STATUSTEXT_KO);
			status.setDetails(PlataformaErrores.STATUSDETAILS_KO);
			respuesta.setStatus(status);
		}
		return writeResponse(respuesta);
	}
	
	public String actualizarTodosMensajes(int resultado) throws PlataformaBusinessException {
		RespuestaNotificacionesPush respuesta = this;

		if (resultado == 1) {
			ResponseNotificacionPushStatusType status = new ResponseNotificacionPushStatusType();
			status.setStatusCode(PlataformaErrores.STATUS_OK);
			status.setStatusText(PlataformaErrores.STATUSTEXT_OK);
			status.setDetails(PlataformaErrores.DETAILS_MODIFICACION_TODOS_MENSAJES);
			respuesta.setStatus(status);
		} else {
			ResponseNotificacionPushStatusType status = new ResponseNotificacionPushStatusType();
			status.setStatusCode(PlataformaErrores.STATUSCODE_KO);
			status.setStatusText(PlataformaErrores.STATUSTEXT_KO);
			status.setDetails(PlataformaErrores.DETAILS_ERROR_MODIFICACION_TODOS_MENSAJES);
			respuesta.setStatus(status);
		}
		return writeResponse(respuesta);
	}

	public String loginIncorrectotoXML() throws PlataformaBusinessException {

		RespuestaNotificacionesPush respuesta = this;
		ResponseNotificacionPushStatusType ResponseNotificacionPushStatusType = new ResponseNotificacionPushStatusType();
		ResponseNotificacionPushStatusType.setStatusCode(PlataformaErrores.STATUSLOGINCODE_KO);
		ResponseNotificacionPushStatusType.setStatusText(PlataformaErrores.STATUSTEXT_KO);
		ResponseNotificacionPushStatusType.setDetails(PlataformaErrores.STATUSLOGIN_KO);
		respuesta.setStatus(ResponseNotificacionPushStatusType);
		return writeResponse(respuesta);
	}
	
	public String incorrectStatusXML() throws PlataformaBusinessException {

		RespuestaNotificacionesPush respuesta = this;
		ResponseNotificacionPushStatusType ResponseNotificacionPushStatusType = new ResponseNotificacionPushStatusType();
		ResponseNotificacionPushStatusType.setStatusCode(PlataformaErrores.STATUSLOGINCODE_KO);
		ResponseNotificacionPushStatusType.setStatusText(PlataformaErrores.STATUSTEXT_KO);
		ResponseNotificacionPushStatusType.setDetails(PlataformaErrores.STATUS_DETAILS_MESSAGE_KO);
		respuesta.setStatus(ResponseNotificacionPushStatusType);
		return writeResponse(respuesta);
	}
	
	
	public String peticionIncorrectaXML() throws PlataformaBusinessException {

		RespuestaNotificacionesPush respuesta = this;
		ResponseNotificacionPushStatusType ResponseNotificacionPushStatusType = new ResponseNotificacionPushStatusType();
		ResponseNotificacionPushStatusType.setStatusCode(PlataformaErrores.COD_PETICION_INCORRECTA);
		ResponseNotificacionPushStatusType.setStatusText(PlataformaErrores.STATUSTEXT_KO);
		ResponseNotificacionPushStatusType.setDetails(PlataformaErrores.DETAILS_PETICION_INCORRECTA);
		respuesta.setStatus(ResponseNotificacionPushStatusType);
		return writeResponse(respuesta);
	}
	
	public String noExisteUsuarioPush() throws PlataformaBusinessException {

		RespuestaNotificacionesPush respuesta = this;
		ResponseNotificacionPushStatusType ResponseNotificacionPushStatusType = new ResponseNotificacionPushStatusType();
		ResponseNotificacionPushStatusType.setStatusCode(PlataformaErrores.COD_PETICION_INCORRECTA);
		ResponseNotificacionPushStatusType.setStatusText(PlataformaErrores.STATUSTEXT_KO);
		ResponseNotificacionPushStatusType.setDetails(PlataformaErrores.DETAILS_ERROR_USUARIO_PUSH);
		respuesta.setStatus(ResponseNotificacionPushStatusType);
		return writeResponse(respuesta);
	}
	
	public String incorrectUserXML() throws PlataformaBusinessException {

		RespuestaNotificacionesPush respuesta = this;
		ResponseNotificacionPushStatusType ResponseNotificacionPushStatusType = new ResponseNotificacionPushStatusType();
		ResponseNotificacionPushStatusType.setStatusCode(PlataformaErrores.STATUSUSERCODE_KO);
		ResponseNotificacionPushStatusType.setStatusText(PlataformaErrores.STATUSTEXT_KO);
		ResponseNotificacionPushStatusType.setDetails(PlataformaErrores.STATUSDETAILS_USER_OK);
		respuesta.setStatus(ResponseNotificacionPushStatusType);
		return writeResponse(respuesta);
	}

	
	public String notPushXML() throws PlataformaBusinessException {

		RespuestaNotificacionesPush respuesta = this;
		ResponseNotificacionPushStatusType ResponseNotificacionPushStatusType = new ResponseNotificacionPushStatusType();
		ResponseNotificacionPushStatusType.setStatusCode(PlataformaErrores.STATUSLOGINCODE_KO);
		ResponseNotificacionPushStatusType.setStatusText(PlataformaErrores.STATUSTEXT_KO);
		ResponseNotificacionPushStatusType.setDetails(PlataformaErrores.STATUS_DETAILS_PUSH_MESSAGE_KO);
		respuesta.setStatus(ResponseNotificacionPushStatusType);
		return writeResponse(respuesta);
	}


	private String writeResponse(RespuestaNotificacionesPush respuesta) throws PlataformaBusinessException {
		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(RespuestaNotificacionesPush.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			StringWriter writer = new StringWriter();
			jaxbMarshaller.marshal(respuesta, writer);

			return writer.toString();
		} catch (PropertyException e) {
			throw new PlataformaBusinessException("Error generando el XML.\nCausa: " + e.getCause() + "\nMensaje: " + e.getMessage());
		} catch (JAXBException e) {
			throw new PlataformaBusinessException("Error generando el XML.\nCausa: " + e.getCause() + "\nMensaje: " + e.getMessage());
		}
	}

	/**
	 * @return the status
	 */
	public ResponseNotificacionPushStatusType getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(ResponseNotificacionPushStatusType status) {
		this.status = status;
	}
}
