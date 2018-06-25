package es.minhap.misim.components;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.annotation.Resource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import misim.bus.common.bean.SoapPayload;
import misim.bus.common.util.XMLUtils;

import org.mule.api.MuleContext;
import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.lifecycle.Callable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import es.minhap.common.properties.PropertiesServices;
import es.minhap.misim.bus.core.pojo.PeticionPayload;
import es.minhap.misim.bus.model.exception.ModelException;
import es.minhap.plataformamensajeria.iop.beans.PeticionConfirmarAltaUsuario;
import es.minhap.plataformamensajeria.iop.beans.PeticionSolicitudRegistroMovil;
import es.minhap.plataformamensajeria.iop.beans.ResponseStatusTypeRegistroMovil;
import es.minhap.plataformamensajeria.iop.beans.RespuestaRegistroMovil;
import es.minhap.plataformamensajeria.iop.beans.lotes.DestinatarioPeticionLotesSMSXMLBean;
import es.minhap.plataformamensajeria.iop.beans.lotes.DestinatariosPeticionLotesSMSXMLBean;
import es.minhap.plataformamensajeria.iop.beans.lotes.MensajePeticionLotesSMSXMLBean;
import es.minhap.plataformamensajeria.iop.beans.lotes.MensajesPeticionLotesXMLBean;
import es.minhap.plataformamensajeria.iop.beans.lotes.PeticionXMLBean;
import es.minhap.plataformamensajeria.iop.services.exceptions.PlataformaBusinessException;
import es.minhap.plataformamensajeria.iop.services.gestionServiciosPush.IRegistroMovilService;
import es.redsara.intermediacion.scsp.esquemas.v3.respuesta.Respuesta;
import es.redsara.misim.misim_bus_webapp.respuesta.ResponseStatusType;

/**
 * Cliente genérico para JAX-WS
 * 
 * @author ludarcos
 * 
 */
public class InvocarRegistroMovil implements Callable {

	private static final Logger LOG = LoggerFactory.getLogger(InvocarRegistroMovil.class);

	@Resource(name = "registroMovilServiceImpl")
	private IRegistroMovilService registroMovilService;

	@Resource(name = "reloadableResourceBundleMessageSource")
	ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;
	PropertiesServices ps = null;

	public static final String RECEPT_QUEUE = "vm://recepcion-sim";

	@Override
	public Object onCall(final MuleEventContext eventContext) throws ModelException {

		LOG.debug("Empezando el proceso de invocación del Registro Movil...");

		SOAPMessage responseMessage = null;
		try {
			ps = new PropertiesServices(reloadableResourceBundleMessageSource);
			String soapAction = SoapPayload.class.cast(eventContext.getMessage().getPayload()).getSoapAction();

			final Document docOriginal = SoapPayload.class.cast(eventContext.getMessage().getPayload())
					.getSoapMessage();

			if (("solicitud-registro-movil").equals(soapAction)) {

				LOG.info("REQUEST REGISTROMOVIL: " + XMLUtils.dom2xml(docOriginal));

				NodeList peticion = docOriginal.getElementsByTagNameNS(
						"http://misim.redsara.es/misim-bus-webapp/rest/peticionSolicitudRegistroMovil",
						"PeticionSolicitudRegistroMovil");
				String xmlPeticion = XMLUtils.nodeToString(peticion.item(0));

				PeticionSolicitudRegistroMovil pet = new PeticionSolicitudRegistroMovil();
				pet.loadObjectFromXML(xmlPeticion);

				String respuesta = registroMovilService.solicitudRegistroMovil(pet);

				// comprobamos si hay que enviar sms
				RespuestaRegistroMovil resp = new RespuestaRegistroMovil();
				resp.loadObjectFromXML(respuesta);
				if (null != resp.getCodConfirmacion()) {
					// ps.getMessage("constantes.SOAP_ACTION", null)
					respuesta = enviarSMS(eventContext.getMuleContext(), pet, resp);
				}
				Document doc = XMLUtils.xml2doc(respuesta, Charset.forName("UTF-8"));
				String respuestaCompleta = XMLUtils.createSOAPFaultString((Node) doc.getDocumentElement());

				responseMessage = getSoapMessageFromString(respuestaCompleta);
			} else  {

				LOG.info("REQUEST CONFIRMARALTAUSUARIO: " + XMLUtils.dom2xml(docOriginal));

				NodeList peticion = docOriginal.getElementsByTagNameNS(
						"http://misim.redsara.es/misim-bus-webapp/rest/peticionConfirmarAltaUsuario",
						"PeticionConfirmarAltaUsuario");
				String xmlPeticion = XMLUtils.nodeToString(peticion.item(0));

				PeticionConfirmarAltaUsuario pet = new PeticionConfirmarAltaUsuario();
				pet.loadObjectFromXML(xmlPeticion);

				String respuesta = registroMovilService.confirmarAltaUsuario(pet);

				Document doc = XMLUtils.xml2doc(respuesta, Charset.forName("UTF-8"));
				String respuestaCompleta = XMLUtils.createSOAPFaultString((Node) doc.getDocumentElement());

				responseMessage = getSoapMessageFromString(respuestaCompleta);
			}
			try {

				SoapPayload<?> initPayload = eventContext.getMessage().getPayload(SoapPayload.class);
				SoapPayload<?> soapPayload = null;
				if (responseMessage.getSOAPBody().hasFault()) {
					// La respuesta es un SOAP Fault
					soapPayload = new SoapPayload<Object>();
					eventContext.getMessage().setOutboundProperty("SOAPFault", true);
				} else {
					// La respuesta no es un SOAP Fault
					soapPayload = new SoapPayload<Respuesta>();
					eventContext.getMessage().setOutboundProperty("SOAPFault", false);
				}

				LOG.info("RESPONSE: " + XMLUtils.dom2xml(XMLUtils.soap2dom(responseMessage)));
				soapPayload.setSoapAction(initPayload.getSoapAction());
				soapPayload.setSoapMessage(XMLUtils.soap2dom(responseMessage));

				eventContext.getMessage().setPayload(soapPayload);

			} catch (Exception e) {
				LOG.error("Error en la transmision: Error al obtener la respuesta del servicio Web especificado", e);
				throw new ModelException("Error al obtener la respuesta del servicio Web especificado", 104);
			}

		} catch (ModelException e) {

			throw new ModelException(e.getMensaje(), e.getCodigo());

		} catch (Exception e) {
			// Lanzar error
			LOG.error("Error en la transmisión: Error de sistema Invocar Emisor", e);
			throw new ModelException("Error de sistema Invocar Emisor", 502);
		}
		LOG.debug("Proceso de creación de invocación al emisor terminado.");
		return eventContext.getMessage();
	}

	private String enviarSMS(MuleContext muleContext, PeticionSolicitudRegistroMovil pet, RespuestaRegistroMovil resp) {
		String statusTextKO = ps.getMessage("plataformaErrores.generales.STATUSTEXT_KO", null);
		String codeKO = ps.getMessage("plataformaErrores.registroMovil.COD_ERROR_ENVIO_SMS", null);
		String detailsKO = ps.getMessage("plataformaErrores.registroMovil.DETAILS_ERROR_ENVIO_SMS", null);
		
		String servicio = ps.getMessage("constantes.registroMovil.servicio", null);
		String usuarioAplicacion = ps.getMessage("constantes.registroMovil.usuarioAplicacion", null);
		String passwordAplicacion = ps.getMessage("constantes.registroMovil.passwordAplicacion", null);
		String cuerpoMensaje = ps.getMessage("constantes.registroMovil.cuerpoMensaje", null);
		String prefijoIdExterno = ps.getMessage("constantes.registroMovil.prefijoIdExterno", null);
		String nombreLote = ps.getMessage("constantes.registroMovil.nombreLote", null);
		String soapAction = ps.getMessage("constantes.SOAP_ACTION", null);
		
		String pattern = "MM/dd/yyyy HH:mm:ss";
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Calendar cal = Calendar.getInstance();
		String timeStamp = format.format(cal.getTime());
		
		PeticionXMLBean peticionSMS = new PeticionXMLBean();
		MensajesPeticionLotesXMLBean mensajes = new MensajesPeticionLotesXMLBean();
		MensajePeticionLotesSMSXMLBean mensaje = new MensajePeticionLotesSMSXMLBean();
		
		peticionSMS.setUsuario(usuarioAplicacion);
		peticionSMS.setPassword(passwordAplicacion);
		peticionSMS.setNombreLote(nombreLote);
		peticionSMS.setServicio(servicio);
		
		DestinatariosPeticionLotesSMSXMLBean destinatarios = new DestinatariosPeticionLotesSMSXMLBean();
		DestinatarioPeticionLotesSMSXMLBean destinatario = new DestinatarioPeticionLotesSMSXMLBean();
		destinatario.setDestinatario(pet.getNumMovil());
		destinatario.setIdExterno(prefijoIdExterno + timeStamp);
		
		destinatarios.getDestinatarioSMS().add(destinatario);
		mensaje.setDestinatariosSMS(destinatarios);
		mensaje.setCuerpo(cuerpoMensaje + resp.getCodConfirmacion());
		
		peticionSMS.setMensajes(mensajes);
		peticionSMS.getMensajes().getMensajeSMS().add(mensaje);
		
		try{
			String xml = peticionSMS.toXML(peticionSMS);
			
			SoapPayload<?> payload = new PeticionPayload();
			payload.setSoapAction(soapAction);
			
			final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document soapDOM = db.parse(new InputSource(new ByteArrayInputStream(xml.getBytes("UTF-8"))));
			payload.setSoapMessage(soapDOM);
				
			final MuleMessage muleResponse = muleContext.getClient().send(RECEPT_QUEUE,payload, null, 10000);
			
			Document respuestaSOAP = muleResponse.getPayload(SoapPayload.class).getSoapMessage();
			
			String res = XMLUtils.dom2xml(respuestaSOAP);
			if (null != res && res.contains("OK")){
				resp.setCodConfirmacion(null);
				return resp.toXML(resp);
			}else{
				throw new Exception();
			}
		}catch(Exception e){
			try {
				LOG.error("Error Enviando SMS", e);
				RespuestaRegistroMovil response = generarRespuesta(statusTextKO, codeKO, detailsKO);
				return response.toXML(response);
			} catch (PlataformaBusinessException e1) {
				SOAPMessage soapMessage = generateSOAPFaultEnvio(codeKO, detailsKO);
				LOG.error("Error en la transmisión: Error de sistema Invocar Emisor", e1);
				return pharseMessageToString(soapMessage);
			}
		}
	}

	private SOAPMessage getSoapMessageFromString(String xml) throws SOAPException, IOException {
		MessageFactory factory = MessageFactory.newInstance();
		return factory.createMessage(new MimeHeaders(),
				new ByteArrayInputStream(xml.getBytes(Charset.forName("UTF-8"))));

	}

	
	protected static final SOAPMessage generateSOAPFaultEnvio(String code, String descripcion) {
		try {
			ResponseStatusType response = new ResponseStatusType();

			response.setStatusCode(code);
			response.setStatusText("KO");
			response.setDetails(descripcion);

			return XMLUtils.dom2soap(XMLUtils.setPayloadFromObject(response, Charset.forName("UTF-8"),
					ResponseStatusType.class));
		} catch (Exception e) {
			LOG.error("Error generando Respuesta", e);
			return null;
		}
	}

	private RespuestaRegistroMovil generarRespuesta(String statustext, String codigo, String details) {
		RespuestaRegistroMovil res = new RespuestaRegistroMovil();
		ResponseStatusTypeRegistroMovil status = new ResponseStatusTypeRegistroMovil();

		res.setStatus(status);

		status.setDetails(details);
		status.setStatusCode(codigo);
		status.setStatusText(statustext);
		res.setStatus(status);

		return res;
		
	}
	
	private String pharseMessageToString(SOAPMessage soapMessage) {
		String xml="";
		try{
		
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			soapMessage.writeTo(out);
			xml = new String(out.toByteArray());
		}catch(Exception e){
			LOG.error("[InvocarRegistroMovil]pharseMessageToString", e);
		}
		return xml;
	}
}