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
@XmlRootElement(name = "Respuesta", namespace = RespuestaServiciosRegistrarUsuario.R_CONST_2)
public class RespuestaServiciosRegistrarUsuario {

	
	
		protected static final String R_CONST_1 = "plataformaErrores.gestionServicioPush.STATUSTEXT_KO";
	protected static final String R_CONST_2 = "http://misim.redsara.es/misim-bus-webapp/respuesta";
	protected static final String R_CONST_3 = "plataformaErrores.gestionServicioPush.STATUSTEXT_OK";
	protected static final String R_CONST_4 = "\\nMensaje: ";
	protected static final String R_CONST_5 = "plataformaErrores.gestionServicioPush.STATUSDETAILS_OK";
	protected static final String R_CONST_6 = "plataformaErrores.gestionServicioPush.STATUSUSER_EXISTS_CODE_KO";
	protected static final String R_CONST_7 = "plataformaErrores.gestionServicioPush.STATUSCODE_OK";
		@XmlElement(name = "Status", required = true, namespace = R_CONST_2)
	private ResponseUsuariosServicosStatusType status;

	public void loadObjectFromXML(String xmlRespuesta) throws PlataformaBusinessException {

		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(RespuestaServiciosRegistrarUsuario.class);

			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			StringReader reader = new StringReader(xmlRespuesta);
			RespuestaServiciosRegistrarUsuario operacionesMensajes = (RespuestaServiciosRegistrarUsuario) unmarshaller.unmarshal(reader);

			org.apache.commons.beanutils.BeanUtils.copyProperties(this, operacionesMensajes);

		} catch (JAXBException | IllegalAccessException | InvocationTargetException e) {
			throw new PlataformaBusinessException("Error procesando el XML.\nCausa: " + e.getCause() + R_CONST_4 + e.getMessage() + "\nXML:\n" + xmlRespuesta);
		}
	}
	
	public String loginIncorrectotoXML(PropertiesServices ps) throws PlataformaBusinessException {
		String statusKO = ps.getMessage(R_CONST_1, null);
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
		String statusKO = ps.getMessage(R_CONST_1, null);
		String codeKO = ps.getMessage(R_CONST_6, null);
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
		String statusKO = ps.getMessage(R_CONST_1, null);
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
		String statusOK = ps.getMessage(R_CONST_3, null);
		String codeOK = ps.getMessage(R_CONST_7, null);
		String detailsOK = ps.getMessage("plataformaErrores.gestionServicioPush.STATUS_SUBSCRIPTION_DETAILS_OK", null);
		String codeKO = ps.getMessage(R_CONST_6, null);
		String detailsKO = ps.getMessage("plataformaErrores.gestionServicioPush.STATUS_SUBSCRIPTION_DETAILS_KO", null);
		String detailsKo2 = ps.getMessage("plataformaErrores.gestionServicioPush.STATUS_SUBSCRIPTION_NO_AVAILAIBLE_KO", null);
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
			responseStatusType.setDetails(detailsKo2);
		}
		respuesta.setStatus(responseStatusType);
		return writeResponse(respuesta);
	}
	public String userExistsXML(boolean updateSubscription, PropertiesServices ps)  throws PlataformaBusinessException{
		String statusOK = ps.getMessage(R_CONST_3, null);
		String codeOK = ps.getMessage(R_CONST_7, null);
		String detailsOK = ps.getMessage(R_CONST_5, null);
		RespuestaServiciosRegistrarUsuario respuesta = this;
		ResponseUsuariosServicosStatusType status = new ResponseUsuariosServicosStatusType();
		if (updateSubscription) {
			status.setStatusCode(codeOK);
			status.setStatusText(statusOK);
			status.setDetails(detailsOK);
			respuesta.setStatus(status);
			return writeResponse(respuesta);
		} else {
			return userExistsXML(ps);
		}
		
	}

	public String accionIncorrectaXML(PropertiesServices ps) throws PlataformaBusinessException {
		String statusKO = ps.getMessage(R_CONST_1, null);
		String codeKO = ps.getMessage("plataformaErrores.gestionServicioPush.STAT_INVALID_ACCION_CODE", null);
		String detailsKO = ps.getMessage("plataformaErrores.gestionServicioPush.DETAILS_ACCION_INCORRECTA", null);

		RespuestaServiciosRegistrarUsuario respuesta = this;
		ResponseUsuariosServicosStatusType status = new ResponseUsuariosServicosStatusType();

		status.setStatusCode(codeKO);
		status.setStatusText(statusKO);
		status.setDetails(detailsKO);
		respuesta.setStatus(status);

		return writeResponse(respuesta);
	}
	
	public String errorToken(PropertiesServices ps) throws PlataformaBusinessException{
		String statusKO = ps.getMessage(R_CONST_1, null);
		String codeKO = ps.getMessage("plataformaErrores.appMovil.COD_ERROR_TOKEN", null);
		String detailsKO = ps.getMessage("plataformaErrores.generales.DETAILS_ERROR_TOKEN", null);
		RespuestaServiciosRegistrarUsuario respuesta = this;
		ResponseUsuariosServicosStatusType status = new ResponseUsuariosServicosStatusType();

		status.setStatusCode(codeKO);
		status.setStatusText(statusKO);
		status.setDetails(detailsKO);
		respuesta.setStatus(status);

		return writeResponse(respuesta);
	}
	

	public String toXML(boolean success, PropertiesServices ps) throws PlataformaBusinessException {
		String statusOK = ps.getMessage(R_CONST_3, null);
		String codeOK = ps.getMessage(R_CONST_7, null);
		String detailsOK = ps.getMessage(R_CONST_5, null);
		String statusKO = ps.getMessage(R_CONST_1, null);
		String codeKO = ps.getMessage("plataformaErrores.gestionServicioPush.STATUSCODE_KO", null);
		String detailsKO = ps.getMessage("plataformaErrores.gestionServicioPush.STATUSDETAILS_KO", null);
		
		RespuestaServiciosRegistrarUsuario respuesta = this;
		ResponseUsuariosServicosStatusType status = new ResponseUsuariosServicosStatusType();
		if (success) {
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
		} catch (JAXBException e) {
			throw new PlataformaBusinessException("Error generando el XML.\nCausa: " + e.getCause() + R_CONST_4 + e.getMessage());
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
