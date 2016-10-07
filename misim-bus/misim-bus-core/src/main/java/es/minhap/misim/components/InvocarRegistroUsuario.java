package es.minhap.misim.components;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import misim.bus.common.bean.SoapPayload;
import misim.bus.common.util.XMLUtils;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import es.minhap.misim.bus.model.exception.ModelException;
import es.minhap.plataformamensajeria.iop.beans.UsuariosXMLBean;
import es.minhap.plataformamensajeria.iop.services.usuariosplataformas.IRegistroUsuarioPushService;
import es.minhap.plataformamensajeria.iop.services.usuariosplataformas.RegistroUsuarioPushResponse;
import es.minhap.plataformamensajeria.iop.util.FactoryServiceSim;
import es.redsara.misim.misim_bus_webapp.respuesta.rest.Respuesta;
import es.redsara.misim.misim_bus_webapp.respuesta.rest.ResponseStatusType;
/**
 * Cliente genérico para JAX-WS
 * 
 * @author ludarcos
 * 
 */
public class InvocarRegistroUsuario implements Callable {
	
	private static final Logger LOG = LoggerFactory.getLogger(InvocarRegistroUsuario.class);

	@Override
	public Object onCall(final MuleEventContext eventContext) throws ModelException {

		LOG.debug("Empezando el proceso de invocación del enviador...");

		try{

			final Document docOriginal = SoapPayload.class.cast(eventContext.getMessage().getPayload()).getSoapMessage();
			System.out.println("REQUEST: " + XMLUtils.dom2xml(docOriginal));

			NodeList peticion = docOriginal.getElementsByTagNameNS("http://misim.redsara.es/misim-bus-webapp/peticion", "Peticion");
			String xmlPeticion = XMLUtils.nodeToString(peticion.item(0));
			
			UsuariosXMLBean usuariosXML = new UsuariosXMLBean();
			usuariosXML.loadObjectFromXML(xmlPeticion);
			
			IRegistroUsuarioPushService usuarioService = FactoryServiceSim.getInstance().getInstanceUsuario();
			RegistroUsuarioPushResponse respuestaUsuario = usuarioService.registroUsuario(usuariosXML);
			
			Respuesta respuesta = new Respuesta();

			if (respuestaUsuario!=null && respuestaUsuario.getStatus()!=null){
				
				ResponseStatusType response = new ResponseStatusType();
	
				response.setStatusCode(respuestaUsuario.getStatus().getStatusCode());
				response.setStatusText(respuestaUsuario.getStatus().getStatusText());
				response.setDetails(respuestaUsuario.getStatus().getDetails());
				
				respuesta.setStatus(response);
				respuesta.setIdDispositivo(respuestaUsuario.getIdDispositivo());
			
			}else{
				
				ResponseStatusType response = new ResponseStatusType();
				
				response.setStatusCode("999");
				response.setStatusText("Error indeterminado");
				response.setDetails("Error indeterminado");
				
				respuesta.setStatus(response);
			}
			
			DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	        Document document = docBuilder.newDocument();
	        
	        JAXBContext context = JAXBContext.newInstance(respuesta.getClass());
	        
	        Marshaller marshaller = context.createMarshaller();
	        marshaller.marshal(respuesta, document);
	        
	        String respuestaIncompleta = XMLUtils.dom2xml(document);
			
			Document doc = XMLUtils.xml2doc(respuestaIncompleta, Charset.forName("UTF-8"));
			String respuestaCompleta = XMLUtils.createSOAPFaultString((Node)doc.getDocumentElement());
			
			SOAPMessage responseMessage=getSoapMessageFromString(respuestaCompleta);
					
	        try{
				
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
				    
				    System.out.println("RESPONSE: " + XMLUtils.dom2xml(XMLUtils.soap2dom(responseMessage)));
					soapPayload.setSoapAction(initPayload.getSoapAction());
					soapPayload.setSoapMessage(XMLUtils.soap2dom(responseMessage));
			
					eventContext.getMessage().setPayload(soapPayload);
					
				}catch(Exception e){
					//Lanzar error
					LOG.error("Error en la transmisión: Error al obtener la respuesta del servicio Web especificado", e);
					throw new ModelException("Error al obtener la respuesta del servicio Web especificado", 104);
				}

		}catch (ModelException e){
			
			throw new ModelException(e.getMensaje(), e.getCodigo());
		}catch(Exception e){
			//Lanzar error
			LOG.error("Error en la transmisión: Error de sistema Invocar Emisor", e);
			throw new ModelException("Error de sistema Invocar Emisor", 502);
		}

		LOG.debug("Proceso de creación de invocación al emisor terminado.");

		return eventContext.getMessage();
	}
	
	private SOAPMessage getSoapMessageFromString(String xml) throws SOAPException, IOException {
		MessageFactory factory = MessageFactory.newInstance();
		SOAPMessage message = factory.createMessage(new MimeHeaders(), new ByteArrayInputStream(xml.getBytes(Charset.forName("UTF-8"))));
		return message;
	}
	
}