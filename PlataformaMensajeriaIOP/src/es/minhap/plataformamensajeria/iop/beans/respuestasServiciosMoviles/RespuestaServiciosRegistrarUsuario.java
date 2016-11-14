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
/**
 *  @author everis
 */
import es.minhap.plataformamensajeria.iop.services.exceptions.PlataformaBusinessException;


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
@XmlRootElement(name = "Respuesta", namespace = "http://misim.redsara.es/misim-bus-webapp/respuesta")
public class RespuestaServiciosRegistrarUsuario {

	
	
		@XmlElement(name = "Status", required = true, namespace = "http://misim.redsara.es/misim-bus-webapp/respuesta")
	private ResponseUsuariosServicosStatusType status;

	public void loadObjectFromXML(String xmlRespuesta) throws PlataformaBusinessException {

		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(RespuestaServiciosRegistrarUsuario.class);

			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			StringReader reader = new StringReader(xmlRespuesta);
			RespuestaServiciosRegistrarUsuario operacionesMensajes = (RespuestaServiciosRegistrarUsuario) unmarshaller.unmarshal(reader);

			org.apache.commons.beanutils.BeanUtils.copyProperties(this, operacionesMensajes);

		} catch (JAXBException e) {
			throw new PlataformaBusinessException("Error procesando el XML.\nCausa: " + e.getCause() + "\nMensaje: " + e.getMessage() + "\nXML:\n" + xmlRespuesta);
		} catch (IllegalAccessException e) {
			throw new PlataformaBusinessException("Error procesando el XML.\nCausa: " + e.getCause() + "\nMensaje: " + e.getMessage() + "\nXML:\n" + xmlRespuesta);
		} catch (InvocationTargetException e) {
			throw new PlataformaBusinessException("Error procesando el XML.\nCausa: " + e.getCause() + "\nMensaje: " + e.getMessage() + "\nXML:\n" + xmlRespuesta);
		}
	}
	
	public String loginIncorrectotoXML(PropertiesServices ps) throws PlataformaBusinessException {
		String statusKO = ps.getMessage("plataformaErrores.gestionServicioPush.STATUSTEXT_KO", null);
		String codeKO = ps.getMessage("plataformaErrores.gestionServicioPush.STATUSLOGINCODE_KO", null);
		String detailsKO = ps.getMessage("plataformaErrores.gestionServicioPush.STATUSLOGIN_KO", null);
		RespuestaServiciosRegistrarUsuario respuesta = this;
		ResponseUsuariosServicosStatusType responseStatusType = new ResponseUsuariosServicosStatusType();
		responseStatusType.setStatusCode(codeKO);
		responseStatusType.setStatusText(statusKO);
		responseStatusType.setDetails(detailsKO);
		respuesta.setStatus(responseStatusType);
		return writeResponse(respuesta);
	}
	
	public String userExistsXML(PropertiesServices ps) throws PlataformaBusinessException {
		String statusKO = ps.getMessage("plataformaErrores.gestionServicioPush.STATUSTEXT_KO", null);
		String codeKO = ps.getMessage("plataformaErrores.gestionServicioPush.STATUSUSER_EXISTS_CODE_KO", null);
		String detailsKO = ps.getMessage("plataformaErrores.gestionServicioPush.STATUS_USER_EXISTS_DETAILS_KO", null);
		RespuestaServiciosRegistrarUsuario respuesta = this;
		ResponseUsuariosServicosStatusType responseStatusType = new ResponseUsuariosServicosStatusType();
		responseStatusType.setStatusCode(codeKO);
		responseStatusType.setStatusText(statusKO);
		responseStatusType.setDetails(detailsKO);
		respuesta.setStatus(responseStatusType);
		return writeResponse(respuesta);
	}
	
	public String invalidMobielServiceXML(PropertiesServices ps) throws PlataformaBusinessException {
		String statusKO = ps.getMessage("plataformaErrores.gestionServicioPush.STATUSTEXT_KO", null);
		String codeKO = ps.getMessage("plataformaErrores.gestionServicioPush.STAT_INVALID_SERVICE_CODE_KO", null);
		String detailsKO = ps.getMessage("plataformaErrores.gestionServicioPush.STATUS_INVALID_SERVICE_KO", null);
		RespuestaServiciosRegistrarUsuario respuesta = this;
		ResponseUsuariosServicosStatusType responseStatusType = new ResponseUsuariosServicosStatusType();
		responseStatusType.setStatusCode(codeKO);
		responseStatusType.setStatusText(statusKO);
		responseStatusType.setDetails(detailsKO);
		respuesta.setStatus(responseStatusType);
		return writeResponse(respuesta);
	}
	
	public String checkUserService(String estadoSuscripcion, PropertiesServices ps) throws PlataformaBusinessException {
		String statusOK = ps.getMessage("plataformaErrores.gestionServicioPush.STATUSTEXT_OK", null);
		String codeOK = ps.getMessage("plataformaErrores.gestionServicioPush.STATUSCODE_OK", null);
		String detailsOK = ps.getMessage("plataformaErrores.gestionServicioPush.STATUS_SUBSCRIPTION_DETAILS_OK", null);
		String statusKO = ps.getMessage("plataformaErrores.gestionServicioPush.STATUSTEXT_KO", null);
		String codeKO = ps.getMessage("plataformaErrores.gestionServicioPush.STATUSUSER_EXISTS_CODE_KO", null);
		String detailsKO = ps.getMessage("plataformaErrores.gestionServicioPush.STATUS_SUBSCRIPTION_DETAILS_KO", null);
		String detailsKO_2 = ps.getMessage("plataformaErrores.gestionServicioPush.STATUS_SUBSCRIPTION_NO_AVAILAIBLE_KO", null);
		String suscripcionActiva = ps.getMessage("plataformaErrores.gestionServicioPush.SUSCRIPCION_ACTIVA", null);
		String suscripcionNoActiva = ps.getMessage("plataformaErrores.gestionServicioPush.SUSCRIPCION_NO_ACTIVA", null);
		RespuestaServiciosRegistrarUsuario respuesta = this;
		ResponseUsuariosServicosStatusType responseStatusType = new ResponseUsuariosServicosStatusType();
		responseStatusType.setStatusCode(codeOK);
		responseStatusType.setStatusText(statusOK);

		
		if (suscripcionActiva.equals(estadoSuscripcion)) {
			responseStatusType.setDetails(detailsOK);
		} else if (suscripcionNoActiva.equals(estadoSuscripcion)){
			responseStatusType.setDetails(detailsKO);
		} else {
			responseStatusType.setStatusCode(codeKO);
			responseStatusType.setDetails(detailsKO_2);
		}
		respuesta.setStatus(responseStatusType);
		return writeResponse(respuesta);
	}
	public String userExistsXML(boolean updateSubscription, PropertiesServices ps)  throws PlataformaBusinessException{
		String statusOK = ps.getMessage("plataformaErrores.gestionServicioPush.STATUSTEXT_OK", null);
		String codeOK = ps.getMessage("plataformaErrores.gestionServicioPush.STATUSCODE_OK", null);
		String detailsOK = ps.getMessage("plataformaErrores.gestionServicioPush.STATUSDETAILS_OK", null);
		RespuestaServiciosRegistrarUsuario respuesta = this;
		ResponseUsuariosServicosStatusType status = new ResponseUsuariosServicosStatusType();
		if (updateSubscription == true) {
			status.setStatusCode(codeOK);
			status.setStatusText(statusOK);
			status.setDetails(detailsOK);
			respuesta.setStatus(status);
			return writeResponse(respuesta);
		} else {
			return userExistsXML(ps);
		}
		
	}
	
	public String toXML(boolean success, PropertiesServices ps) throws PlataformaBusinessException {
		String statusOK = ps.getMessage("plataformaErrores.gestionServicioPush.STATUSTEXT_OK", null);
		String codeOK = ps.getMessage("plataformaErrores.gestionServicioPush.STATUSCODE_OK", null);
		String detailsOK = ps.getMessage("plataformaErrores.gestionServicioPush.STATUSDETAILS_OK", null);
		String statusKO = ps.getMessage("plataformaErrores.gestionServicioPush.STATUSTEXT_KO", null);
		String codeKO = ps.getMessage("plataformaErrores.gestionServicioPush.STATUSCODE_KO", null);
		String detailsKO = ps.getMessage("plataformaErrores.gestionServicioPush.STATUSDETAILS_KO", null);
		
		RespuestaServiciosRegistrarUsuario respuesta = this;
		ResponseUsuariosServicosStatusType status = new ResponseUsuariosServicosStatusType();
		if (success == true) {
			status.setStatusCode(codeOK);
			status.setStatusText(statusOK);
			status.setDetails(detailsOK);
			respuesta.setStatus(status);
		} else {
			status.setStatusCode(codeKO);
			status.setStatusText(statusKO);
			status.setDetails(detailsKO);
			respuesta.setStatus(status);
		}
		return writeResponse(respuesta);
	}
	
	private String writeResponse(RespuestaServiciosRegistrarUsuario respuesta) throws PlataformaBusinessException {
		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(RespuestaServiciosRegistrarUsuario.class);
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
	public ResponseUsuariosServicosStatusType getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(ResponseUsuariosServicosStatusType status) {
		this.status = status;
	}

	
}
