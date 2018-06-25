package es.minhap.misim.bus.webapp.serviceprovider;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

import javax.servlet.ServletContext;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.Service.Mode;
import javax.xml.ws.ServiceMode;
import javax.xml.ws.WebServiceProvider;
import javax.xml.ws.handler.MessageContext;

import misim.bus.common.bean.SoapPayload;
import misim.bus.common.exceptions.ApplicationException;
import misim.bus.common.util.XMLUtils;

import org.mule.api.MuleContext;
import org.mule.api.MuleException;
import org.mule.api.MuleMessage;
import org.mule.module.client.MuleClient;
import org.springframework.context.annotation.Scope;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import es.minhap.misim.bus.core.pojo.PeticionPayload;
import es.minhap.plataformamensajeria.iop.beans.ConsultaEstadoXMLBean;
import es.minhap.plataformamensajeria.iop.services.seguimiento.SeguimientoMensajesImpl;
import es.redsara.misim.misim_bus_webapp.respuesta.envio.aplicaciones.ResponseStatusType;
import es.redsara.misim.misim_bus_webapp.respuesta.envio.aplicaciones.Respuesta;

/**
 * Clase que implementa la logica del servicio SeguimientoMensajesService
 * @author ralberoc
 *
 */
@WebServiceProvider(portName = "SeguimientoMensajesServicePort", serviceName = "SeguimientoMensajesService", 
targetNamespace = "http://misim.redsara.es/misim-bus-webapp/", wsdlLocation = "WEB-INF/wsdl/recepcion-sim/seguimientoSIM.wsdl")
@ServiceMode(Mode.MESSAGE)
@Scope(value="request")
public class RecepcionSeguimientoSIMWSProvider extends WSProvider {

	/**
	 * Cola de recepción VM de peticiones sincronas
	 */
	public static final String RECEPT_QUEUE = "vm://recepcion-sim";
	public static final String ACTION_HISTORIAL = "consultarHistorial";
	
	/**
	 * Metodo que implementa la logica de la peticion de seguimientoSIM
	 */
	@Override
	public SOAPMessage invoke(final SOAPMessage request) {

		SOAPMessage responseSOAP = null;
		try {
			
			final ServletContext servletContext = (ServletContext) getContext().getMessageContext().get(MessageContext.SERVLET_CONTEXT);

			setMuleContext(MuleContext.class.cast(servletContext.getAttribute("mule.context")));

			if (getMuleClient() == null) {
				try {
					setMuleClient(new MuleClient(getMuleContext()));

				} catch (final MuleException e) {
					throw new RuntimeException("Error in mule client", e);
				}
			}
			try {
				
				SoapPayload<?> payload = new PeticionPayload();
				payload.setSoapAction(String.class.cast(getContext().getMessageContext().get(SOAP_ACTION)));
				payload.setSoapMessage(XMLUtils.soap2dom(request));
				
				if(payload.getSoapAction().equals(ACTION_HISTORIAL)){
					final MuleMessage muleResponse = getMuleClient().send(RECEPT_QUEUE,payload, null, 10000);
					
					responseSOAP = XMLUtils.dom2soap(muleResponse.getPayload(SoapPayload.class).getSoapMessage());

				}else{
					SeguimientoMensajesImpl seguimientoMensajesImpl = (SeguimientoMensajesImpl) getMuleContext().getRegistry().lookupObject("seguimientoMensajesImpl");
					
					final Document docOriginal = XMLUtils.soap2dom(request);
					
					NodeList peticion = docOriginal.getElementsByTagNameNS("http://misim.redsara.es/misim-bus-webapp/peticion", "PeticionEstado");
					String xmlPeticion = XMLUtils.nodeToString(peticion.item(0));
					
					ConsultaEstadoXMLBean consultaEstado = new ConsultaEstadoXMLBean();
					consultaEstado.loadObjectFromXML(xmlPeticion);
					
					String respuesta=seguimientoMensajesImpl.consultarEstado(consultaEstado);
					
					Document doc = XMLUtils.xml2doc(respuesta, Charset.forName("UTF-8"));
					String respuestaCompleta = XMLUtils.createSOAPFaultString((Node)doc.getDocumentElement());
					
					responseSOAP = getSoapMessageFromString(respuestaCompleta);
				}
				

			} catch (final Exception e) {
				throw new RuntimeException("Error in provider endpoint", e);
			}
			
		} catch (Exception e) {
			try {
				responseSOAP = generateSOAPFault(request);
			} catch (Exception e1) {
				throw new ApplicationException(e1.getMessage());
			}
		}
		
		return responseSOAP;
	}
	

	/**
	 * Genera el SOAP Fault Message
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	protected static final SOAPMessage generateSOAPFault(SOAPMessage request)throws Exception {
		
		ResponseStatusType response = new ResponseStatusType();
		
		response.setStatusCode("0999");
		response.setStatusText("Error al obtener el contenido XML del mensaje SOAP");
		response.setDetails("Error al obtener el contenido XML del mensaje SOAP");

		Respuesta respuesta = new Respuesta();
		respuesta.setStatus(response);
		
		return XMLUtils.dom2soap(XMLUtils.setPayloadFromObject(respuesta, Charset.forName("UTF-8"), Respuesta.class));
	}

	private SOAPMessage getSoapMessageFromString(String xml) throws SOAPException, IOException {
		MessageFactory factory = MessageFactory.newInstance();
		SOAPMessage message = factory.createMessage(new MimeHeaders(), new ByteArrayInputStream(xml.getBytes(Charset.forName("UTF-8"))));
		return message;
	}
	
}
