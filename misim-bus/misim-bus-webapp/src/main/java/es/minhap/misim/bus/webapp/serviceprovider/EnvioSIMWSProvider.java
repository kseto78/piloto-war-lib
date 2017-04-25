package es.minhap.misim.bus.webapp.serviceprovider;

import java.nio.charset.Charset;

import javax.servlet.ServletContext;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.Service.Mode;
import javax.xml.ws.ServiceMode;
import javax.xml.ws.WebServiceProvider;
import javax.xml.ws.handler.MessageContext;

import misim.bus.common.bean.SoapPayload;
import misim.bus.common.exceptions.ApplicationException;
import misim.bus.common.util.XMLUtils;

import org.apache.log4j.Logger;
import org.mule.api.MuleContext;
import org.mule.api.MuleException;
import org.mule.api.MuleMessage;
import org.mule.module.client.MuleClient;
import org.springframework.context.annotation.Scope;

import es.minhap.misim.bus.core.pojo.PeticionPayload;
import es.redsara.misim.misim_bus_webapp.respuesta.ResponseStatusType;
import es.redsara.misim.misim_bus_webapp.respuesta.Respuesta;

@WebServiceProvider(portName = "EnviarMensajesWSPort", serviceName = "EnviarMensajesWS", targetNamespace = "http://misim.redsara.es/misim-bus-webapp/", wsdlLocation = "WEB-INF/wsdl/envio-sim/enviarMensajes.wsdl")
@ServiceMode(Mode.MESSAGE)
@Scope(value="request")
public class EnvioSIMWSProvider extends WSProvider {

	static Logger logger = Logger.getLogger(EnvioSIMWSProvider.class);
	/**
	 * Cola de recepción VM de peticiones sincronas
	 */
	public static final String RECEPT_QUEUE = "vm://envio-sim";
	

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
			
//				LOG.info("Recepción de la petición: "+XMLUtils.dom2xml(XMLUtils.soap2dom(request)));

				final MuleMessage muleResponse = getMuleClient().send(RECEPT_QUEUE,payload, null, 10000);
				logger.info("payload - "+ muleResponse.getPayload().getClass());
				logger.info("payload - "+ muleResponse.getPayload(java.lang.String.class));
				responseSOAP = XMLUtils.dom2soap(muleResponse.getPayload(SoapPayload.class).getSoapMessage());

			} catch (final Exception e) {
				logger.error("[EnvioSIMWSProvider.invoke] - ",e);
				throw new RuntimeException("Error in provider endpoint", e);
			}
			
		} catch (Exception e) {
			try {
				responseSOAP = generateSOAPFaultEnvio(request);
			} catch (Exception e1) {
				logger.error("[EnvioSIMWSProvider.invoke] - ",e);
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
	protected static final SOAPMessage generateSOAPFaultEnvio(SOAPMessage request)throws Exception {
		
		ResponseStatusType response = new ResponseStatusType();
		
		response.setStatusCode("0403");
		response.setStatusText("Error al obtener el contenido XML del mensaje SOAP");
		response.setDetails("Error al obtener el contenido XML del mensaje SOAP");

		Respuesta respuesta = new Respuesta();
		respuesta.setStatus(response);
		
		return XMLUtils.dom2soap(XMLUtils.setPayloadFromObject(respuesta, Charset.forName("UTF-8"), Respuesta.class));
	}

}
