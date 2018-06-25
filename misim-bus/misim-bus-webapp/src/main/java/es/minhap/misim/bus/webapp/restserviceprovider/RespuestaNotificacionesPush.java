package es.minhap.misim.bus.webapp.restserviceprovider;

import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

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

import es.minhap.plataformamensajeria.iop.beans.respuestasServiciosMoviles.ResponseNotificacionPushStatusType;
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
 *         &lt;element name="status" type="{http://misim.redsara.es/misim-bus-webapp/respuesta}statusCode"/&gt;
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

	private static String STATUSCODE_OK = "0000";
	private static String STATUSLOGINCODE_KO = "3007";
	private static String STATUSTEXT_OK = "OK";
	private static String STATUSTEXT_KO = "KO";
	private static String STATUSDETAILS_OK = "Peticion procesada correctamente";
	private static String STATUSLOGIN_KO = "Error al loguearse en la Plataforma. Usuario y/o password incorrectos";
	private static String STATUSNOSERVICIOSMOVILES_CODE = "3006";
	private static String STATUSNOSERVICIOSMOVILES_TEXT = "No se han encontrado Servicios M&oacute;viles disponibles";

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

	public String toXML(List<ServicioMovil> serviciosMoviles) throws PlataformaBusinessException {
		RespuestaNotificacionesPush respuesta = this;
		if (null != serviciosMoviles && !serviciosMoviles.isEmpty()) {
			ResponseNotificacionPushStatusType status = new ResponseNotificacionPushStatusType();
			status.setStatusCode(STATUSCODE_OK);
			status.setStatusText(STATUSTEXT_OK);
			status.setDetails(STATUSDETAILS_OK);
			respuesta.setStatus(status);
		} else {
			ResponseNotificacionPushStatusType status = new ResponseNotificacionPushStatusType();
			status.setStatusCode(STATUSNOSERVICIOSMOVILES_CODE);
			status.setStatusText(STATUSTEXT_KO);
			status.setDetails(STATUSNOSERVICIOSMOVILES_TEXT);
			respuesta.setStatus(status);
		}
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

	public String loginIncorrectotoXML() throws PlataformaBusinessException {

		RespuestaNotificacionesPush respuesta = this;
		ResponseNotificacionPushStatusType status = new ResponseNotificacionPushStatusType();
		status.setStatusCode(STATUSLOGINCODE_KO);
		status.setStatusText(STATUSLOGIN_KO);
		status.setDetails(STATUSLOGIN_KO);
		respuesta.setStatus(status);
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
