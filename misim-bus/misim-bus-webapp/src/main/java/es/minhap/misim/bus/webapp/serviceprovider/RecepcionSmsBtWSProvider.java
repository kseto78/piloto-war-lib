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

import org.mule.api.MuleContext;
import org.mule.api.MuleException;
import org.mule.api.MuleMessage;
import org.mule.module.client.MuleClient;
import org.springframework.context.annotation.Scope;

import com.i3g.schema.siroccopushservice.PingResponse;
import com.i3g.schema.siroccopushservice.PushMOResponseType;

import es.minhap.misim.bus.core.pojo.PeticionPayload;
/**
 * Clase que implementa la logica del servicio de Recepcion003Service
 * @author ralberoc
 *
 */
@WebServiceProvider(portName = "RecepcionSmsBtPort", serviceName = "Recepcion003Service", targetNamespace = "http://i3g.com/sirocco/SiroccoPushService", wsdlLocation = "WEB-INF/wsdl/recepcion-bt/wsdl_recepcion_sms.wsdl")
@ServiceMode(Mode.MESSAGE)
@Scope(value="request")
public class RecepcionSmsBtWSProvider extends WSProvider {

	/**
	 * Cola de recepción VM de peticiones sincronas
	 */
	public static final String RECEPT_QUEUE = "vm://recepcion-bt";
	public static final String RECEPCION_SMS_BT = "http://i3g.com/sirocco/SiroccoPushService/onIncomingMessage";
	public static final String RECEPCION_SMS_GENERICO = "recepcionSMS";
	
	public static final String PING_SMS_BT = "http://i3g.com/sirocco/SiroccoPushService/ping";
	
	/**
	 * Metodo que implementa la logica de la peticion de wsdl_recepcion_sms
	 */
	@Override
	public SOAPMessage invoke(final SOAPMessage request) {

		SOAPMessage responseSOAP = null;
		
		String soapAction = String.class.cast(getContext().getMessageContext().get(SOAP_ACTION));
		
		if(soapAction.equals(RECEPCION_SMS_BT)){
			
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
					
					soapAction=RECEPCION_SMS_GENERICO;
					payload.setSoapAction(soapAction);
					payload.setSoapMessage(XMLUtils.soap2dom(request));

					final MuleMessage muleResponse = getMuleClient().send(RECEPT_QUEUE,payload, null, 10000);
					
					responseSOAP = XMLUtils.dom2soap(muleResponse.getPayload(SoapPayload.class).getSoapMessage());
						
				} catch (final Exception e) {
					throw new RuntimeException("Error in provider endpoint", e);
				}
				
			} catch (Exception e) {
				try {
					responseSOAP = generateSOAPRecepcion(request);
				} catch (Exception e1) {
					throw new ApplicationException(e1.getMessage());
				}
			}
		
		}else if (soapAction.equals(PING_SMS_BT)){
			
			try {
				responseSOAP = generateSOAPRecepcionPing(request);
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
	protected static final SOAPMessage generateSOAPRecepcion(SOAPMessage request) throws Exception {
		
//		DeliveryReportRes respuesta = new DeliveryReportRes();
//		
//		ResponseStatusType response = new ResponseStatusType();
//		response.setStatusCode("0403");
//		response.setStatusText("Error al obtener el contenido XML del mensaje SOAP");
//		response.setDetails("Error al obtener el contenido XML del mensaje SOAP");
//
//		respuesta.setStatus(response);
//		respuesta.setVersion("1.0");
//		
		PushMOResponseType respuesta = new PushMOResponseType();
		respuesta.setGuid("");
//		respuesta.setId("");
		respuesta.setReplyTo(1);
		respuesta.setReplyMessage("Error al obtener el contenido XML del mensaje SOAP");
		
		return XMLUtils.dom2soap(XMLUtils.setPayloadFromObject(respuesta, Charset.forName("UTF-8"), PushMOResponseType.class));
	}
	
	/**
	 * Genera el SOAP para Ping
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	protected static final SOAPMessage generateSOAPRecepcionPing(SOAPMessage request) throws Exception {
		
		PingResponse respuesta = new PingResponse();
		
//		long currentMilliseconds = new Date().getTime();
		long currentMilliseconds = System.currentTimeMillis();
		respuesta.setResponse(currentMilliseconds);
		
		return XMLUtils.dom2soap(XMLUtils.setPayloadFromObject(respuesta, Charset.forName("UTF-8"), PingResponse.class));
	}
	

}
