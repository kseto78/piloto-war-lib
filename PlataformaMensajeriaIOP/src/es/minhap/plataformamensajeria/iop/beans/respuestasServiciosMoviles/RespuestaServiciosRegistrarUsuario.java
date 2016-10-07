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

	private static final String STATUSCODE_OK = "0000";
	private static final String STATUSCODE_KO = "3001";
	private static final String STATUSLOGINCODE_KO = "3002";
	private static final String STATUSUSER_EXISTS_CODE_KO = "3003";
	private static final String STAT_INVALID_SERVICE_CODE_KO = "3004";
	private static final String STATUSCODE_INVALID_ACTION_KO="3013";
	private static final String STATUSTEXT_OK = "OK";
	private static final String STATUSTEXT_KO = "KO";
	private static final String STATUSDETAILS_OK = "Peticion procesada correctamente";
	private static final String STATUSDETAILS_KO = "Error en la peticion";
	private static final String STATUS_USER_EXISTS_DETAILS_KO= "El usuario ya estaba dado de alta en este servicio";
	private static final String STATUS_SUBSCRIPTION_DETAILS_KO= "El estado de su suscripcion es NO ACTIVO";
	private static final String STATUS_SUBSCRIPTION_DETAILS_OK= "El estado de su suscripcion es ACTIVO";
	private static final String STATUSLOGIN_KO = "Error al loguearse en la Plataforma. Usuario y/o password incorrectos";
	private static final String STATUS_SUBSCRIPTION_NO_AVAILAIBLE_KO ="El usuario no tiene dado de alta este servicio, por tanto no se puede obtener el estado de la suscripcion";
	private static final String STATUS_INVALID_SERVICE_KO ="El servicio movil indicado no existe";
	private static final String STATUSDETAILS_INVALID_ACTION_KO="Error, la accion a realizar no se corresponde con A, B,C.";
	

	static final String TAG_ERROR_GENERANDO_RESPUESTA_XML = "Se ha producido un error generando la cadena de respuesta";
	
	private static final String SUSCRIPCION_ACTIVA = "1";
	private static final String SUSCRIPCION_NO_ACTIVA = "0";
	
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
	
	public String loginIncorrectotoXML() throws PlataformaBusinessException {

		RespuestaServiciosRegistrarUsuario respuesta = this;
		ResponseUsuariosServicosStatusType responseStatusType = new ResponseUsuariosServicosStatusType();
		responseStatusType.setStatusCode(STATUSLOGINCODE_KO);
		responseStatusType.setStatusText(STATUSLOGIN_KO);
		responseStatusType.setDetails(STATUSLOGIN_KO);
		respuesta.setStatus(responseStatusType);
		return writeResponse(respuesta);
	}
	
	public String userExistsXML() throws PlataformaBusinessException {
		RespuestaServiciosRegistrarUsuario respuesta = this;
		ResponseUsuariosServicosStatusType responseStatusType = new ResponseUsuariosServicosStatusType();
		responseStatusType.setStatusCode(STATUSUSER_EXISTS_CODE_KO);
		responseStatusType.setStatusText(STATUSDETAILS_KO);
		responseStatusType.setDetails(STATUS_USER_EXISTS_DETAILS_KO);
		respuesta.setStatus(responseStatusType);
		return writeResponse(respuesta);
	}
	
	public String invalidMobielServiceXML() throws PlataformaBusinessException {
		RespuestaServiciosRegistrarUsuario respuesta = this;
		ResponseUsuariosServicosStatusType responseStatusType = new ResponseUsuariosServicosStatusType();
		responseStatusType.setStatusCode(STAT_INVALID_SERVICE_CODE_KO);
		responseStatusType.setStatusText(STATUSDETAILS_KO);
		responseStatusType.setDetails(STATUS_INVALID_SERVICE_KO);
		respuesta.setStatus(responseStatusType);
		return writeResponse(respuesta);
	}
	
	public String invalidActionXML() throws PlataformaBusinessException {
		RespuestaServiciosRegistrarUsuario respuesta = this;
		ResponseUsuariosServicosStatusType responseStatusType = new ResponseUsuariosServicosStatusType();
		responseStatusType.setStatusCode(STATUSCODE_INVALID_ACTION_KO);
		responseStatusType.setStatusText(STATUSDETAILS_KO);
		responseStatusType.setDetails(STATUSDETAILS_INVALID_ACTION_KO);
		respuesta.setStatus(responseStatusType);
		return writeResponse(respuesta);
	}

	
	public String checkUserService(String estadoSuscripcion) throws PlataformaBusinessException {
		RespuestaServiciosRegistrarUsuario respuesta = this;
		ResponseUsuariosServicosStatusType responseStatusType = new ResponseUsuariosServicosStatusType();
		responseStatusType.setStatusCode(STATUSCODE_OK);
		responseStatusType.setStatusText(STATUSDETAILS_OK);
		
		if (SUSCRIPCION_ACTIVA.equals(estadoSuscripcion)) {
			responseStatusType.setDetails(STATUS_SUBSCRIPTION_DETAILS_OK);
		} else if (SUSCRIPCION_NO_ACTIVA.equals(estadoSuscripcion)){
			responseStatusType.setDetails(STATUS_SUBSCRIPTION_DETAILS_KO);
		} else {
			responseStatusType.setStatusCode(STATUSCODE_KO);
			responseStatusType.setDetails(STATUS_SUBSCRIPTION_NO_AVAILAIBLE_KO);
		}
		respuesta.setStatus(responseStatusType);
		return writeResponse(respuesta);
	}
	public String userExistsXML(boolean updateSubscription)  throws PlataformaBusinessException{
		RespuestaServiciosRegistrarUsuario respuesta = this;
		ResponseUsuariosServicosStatusType status = new ResponseUsuariosServicosStatusType();
		if (updateSubscription == true) {
			status.setStatusCode(STATUSCODE_OK);
			status.setStatusText(STATUSTEXT_OK);
			status.setDetails(STATUSDETAILS_OK);
			respuesta.setStatus(status);
			return writeResponse(respuesta);
		} else {
			return userExistsXML();
		}
		
	}
	
	public String toXML(boolean success) throws PlataformaBusinessException {
		RespuestaServiciosRegistrarUsuario respuesta = this;
		ResponseUsuariosServicosStatusType status = new ResponseUsuariosServicosStatusType();
		if (success == true) {
			status.setStatusCode(STATUSCODE_OK);
			status.setStatusText(STATUSTEXT_OK);
			status.setDetails(STATUSDETAILS_OK);
			respuesta.setStatus(status);
		} else {
			status.setStatusCode(STATUSCODE_KO);
			status.setStatusText(STATUSTEXT_KO);
			status.setDetails(STATUSDETAILS_KO);
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
