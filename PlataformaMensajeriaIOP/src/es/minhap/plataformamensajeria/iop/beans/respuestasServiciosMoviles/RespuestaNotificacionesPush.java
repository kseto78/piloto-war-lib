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

import es.minhap.common.properties.PropertiesServices;
import es.minhap.plataformamensajeria.iop.services.exceptions.PlataformaBusinessException;

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

	public String toXML(int resultado, PropertiesServices ps) throws PlataformaBusinessException {
		RespuestaNotificacionesPush respuesta = this;
		String codeOK = ps.getMessage("plataformaErrores.gestionNotificacionesPush.STATUSCODE_OK", null);
		String statusTextOK = ps.getMessage("plataformaErrores.gestionNotificacionesPush.STATUSTEXT_OK", null);
		String detailsOK = ps.getMessage("plataformaErrores.gestionNotificacionesPush.STATUSDETAILS_CHANGE_MESSAGE_STATUS_OK", null);
		String codeKO = ps.getMessage("plataformaErrores.gestionNotificacionesPush.STATUSCODE_KO", null);
		String statusTextKO = ps.getMessage("plataformaErrores.gestionNotificacionesPush.STATUSTEXT_KO", null);
		String detailsKO = ps.getMessage("plataformaErrores.gestionNotificacionesPush.STATUSDETAILS_KO", null);
		ResponseNotificacionPushStatusType stat = new ResponseNotificacionPushStatusType();
		if (resultado >= 1) {
			stat.setStatusCode(codeOK);
			stat.setStatusText(statusTextOK);
			stat.setDetails(detailsOK);
			respuesta.setStatus(stat);
		} else {
			stat.setStatusCode(codeKO);
			stat.setStatusText(statusTextKO);
			stat.setDetails(detailsKO);
			respuesta.setStatus(stat);
		}
		return writeResponse(respuesta);
	}
	
	public String actualizarTodosMensajes(int resultado, PropertiesServices ps) throws PlataformaBusinessException {
		RespuestaNotificacionesPush respuesta = this;
		String codeOK = ps.getMessage("plataformaErrores.gestionNotificacionesPush.STATUSCODE_OK", null);
		String statusTextOK = ps.getMessage("plataformaErrores.gestionNotificacionesPush.STATUSTEXT_OK", null);
		String detailsOK = ps.getMessage("plataformaErrores.gestionNotificacionesPush.DETAILS_MODIFICACION_TODOS_MENSAJES", null);
		String codeKO = ps.getMessage("plataformaErrores.gestionNotificacionesPush.STATUSCODE_KO", null);
		String statusTextKO = ps.getMessage("plataformaErrores.gestionNotificacionesPush.STATUSTEXT_KO", null);
		String detailsKO = ps.getMessage("plataformaErrores.gestionNotificacionesPush.DETAILS_ERROR_MODIFICACION_TODOS_MENSAJES", null);
		ResponseNotificacionPushStatusType stat = new ResponseNotificacionPushStatusType();
		if (resultado == 1) {
			stat.setStatusCode(codeOK);
			stat.setStatusText(statusTextOK);
			stat.setDetails(detailsOK);
			respuesta.setStatus(stat);
		} else {
			stat.setStatusCode(codeKO);
			stat.setStatusText(statusTextKO);
			stat.setDetails(detailsKO);
			respuesta.setStatus(stat);
		}
		return writeResponse(respuesta);
	}

	public String loginIncorrectotoXML(PropertiesServices ps) throws PlataformaBusinessException {
		String code = ps.getMessage("plataformaErrores.gestionNotificacionesPush.STATUSLOGINCODE_KO", null);
		String statusText = ps.getMessage("plataformaErrores.gestionNotificacionesPush.STATUSTEXT_KO", null);
		String details = ps.getMessage("plataformaErrores.gestionNotificacionesPush.STATUSLOGIN_KO", null);
		RespuestaNotificacionesPush respuesta = this;
		ResponseNotificacionPushStatusType responseNotificacionPushStatusType = new ResponseNotificacionPushStatusType();
		responseNotificacionPushStatusType.setStatusCode(code);
		responseNotificacionPushStatusType.setStatusText(statusText);
		responseNotificacionPushStatusType.setDetails(details);
		respuesta.setStatus(responseNotificacionPushStatusType);
		return writeResponse(respuesta);
	}
	
	public String incorrectStatusXML(PropertiesServices ps) throws PlataformaBusinessException {
		String code = ps.getMessage("plataformaErrores.gestionNotificacionesPush.STATUSLOGINCODE_KO", null);
		String statusText = ps.getMessage("plataformaErrores.gestionNotificacionesPush.STATUSTEXT_KO", null);
		String details = ps.getMessage("plataformaErrores.gestionNotificacionesPush.STATUS_DETAILS_MESSAGE_KO", null);
		RespuestaNotificacionesPush respuesta = this;
		ResponseNotificacionPushStatusType responseNotificacionPushStatusType = new ResponseNotificacionPushStatusType();
		responseNotificacionPushStatusType.setStatusCode(code);
		responseNotificacionPushStatusType.setStatusText(statusText);
		responseNotificacionPushStatusType.setDetails(details);
		respuesta.setStatus(responseNotificacionPushStatusType);
		return writeResponse(respuesta);
	}
	
	
	public String peticionIncorrectaXML(PropertiesServices ps) throws PlataformaBusinessException {
		String code = ps.getMessage("plataformaErrores.gestionNotificacionesPush.COD_PETICION_INCORRECTA", null);
		String statusText = ps.getMessage("plataformaErrores.gestionNotificacionesPush.STATUSTEXT_KO", null);
		String details = ps.getMessage("plataformaErrores.gestionNotificacionesPush.DETAILS_PETICION_INCORRECTA", null);
		RespuestaNotificacionesPush respuesta = this;
		ResponseNotificacionPushStatusType responseNotificacionPushStatusType = new ResponseNotificacionPushStatusType();
		responseNotificacionPushStatusType.setStatusCode(code);
		responseNotificacionPushStatusType.setStatusText(statusText);
		responseNotificacionPushStatusType.setDetails(details);
		respuesta.setStatus(responseNotificacionPushStatusType);
		return writeResponse(respuesta);
	}
	
	public String noExisteUsuarioPush(PropertiesServices ps) throws PlataformaBusinessException {
		String code = ps.getMessage("plataformaErrores.gestionNotificacionesPush.COD_PETICION_INCORRECTA", null);
		String statusText = ps.getMessage("plataformaErrores.gestionNotificacionesPush.STATUSTEXT_KO", null);
		String details = ps.getMessage("plataformaErrores.gestionNotificacionesPush.DETAILS_ERROR_USUARIO_PUSH", null);
		
		RespuestaNotificacionesPush respuesta = this;
		ResponseNotificacionPushStatusType responseNotificacionPushStatusType = new ResponseNotificacionPushStatusType();
		responseNotificacionPushStatusType.setStatusCode(code);
		responseNotificacionPushStatusType.setStatusText(statusText);
		responseNotificacionPushStatusType.setDetails(details);
		respuesta.setStatus(responseNotificacionPushStatusType);
		return writeResponse(respuesta);
	}
	
	public String incorrectUserXML(PropertiesServices ps) throws PlataformaBusinessException {
		String code = ps.getMessage("plataformaErrores.gestionNotificacionesPush.STATUSUSERCODE_KO", null);
		String statusText = ps.getMessage("plataformaErrores.gestionNotificacionesPush.STATUSTEXT_KO", null);
		String details = ps.getMessage("plataformaErrores.gestionNotificacionesPush.STATUSDETAILS_USER_OK", null);
		RespuestaNotificacionesPush respuesta = this;
		ResponseNotificacionPushStatusType responseNotificacionPushStatusType = new ResponseNotificacionPushStatusType();
		responseNotificacionPushStatusType.setStatusCode(code);
		responseNotificacionPushStatusType.setStatusText(statusText);
		responseNotificacionPushStatusType.setDetails(details);
		respuesta.setStatus(responseNotificacionPushStatusType);
		return writeResponse(respuesta);
	}

	
	public String notPushXML(PropertiesServices ps) throws PlataformaBusinessException {
		String code = ps.getMessage("plataformaErrores.gestionNotificacionesPush.STATUSLOGINCODE_KO", null);
		String statusText = ps.getMessage("plataformaErrores.gestionNotificacionesPush.STATUSTEXT_KO", null);
		String details = ps.getMessage("plataformaErrores.gestionNotificacionesPush.STATUS_DETAILS_PUSH_MESSAGE_KO", null);
		RespuestaNotificacionesPush respuesta = this;
		ResponseNotificacionPushStatusType responseNotificacionPushStatusType = new ResponseNotificacionPushStatusType();
		responseNotificacionPushStatusType.setStatusCode(code);
		responseNotificacionPushStatusType.setStatusText(statusText);
		responseNotificacionPushStatusType.setDetails(details);
		respuesta.setStatus(responseNotificacionPushStatusType);
		return writeResponse(respuesta);
	}

	public String tokenIncorrecto(PropertiesServices ps) throws PlataformaBusinessException {
		String statusTextKO = ps.getMessage("plataformaErrores.generales.STATUSTEXT_KO", null);
		String codeKO = ps.getMessage("plataformaErrores.appMovil.COD_ERROR_TOKEN", null);
		String detailsKO = ps.getMessage("plataformaErrores.generales.DETAILS_ERROR_TOKEN", null);
		RespuestaNotificacionesPush respuesta = this;
		ResponseNotificacionPushStatusType responseNotificacionPushStatusType = new ResponseNotificacionPushStatusType();
		responseNotificacionPushStatusType.setStatusCode(codeKO);
		responseNotificacionPushStatusType.setStatusText(statusTextKO);
		responseNotificacionPushStatusType.setDetails(detailsKO);
		respuesta.setStatus(responseNotificacionPushStatusType);
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
