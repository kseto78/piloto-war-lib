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

import com.i3g.schema.siroccopushstatusservice.PingResponse;
import com.i3g.schema.siroccopushstatusservice.PushStatusResponse;

import es.minhap.misim.bus.core.pojo.PeticionPayload;

@WebServiceProvider(portName = "RecepcionEstadoBtPort", serviceName = "Recepcion004Service", targetNamespace = "http://i3g.com/sirocco/SiroccoPushStatusService", wsdlLocation = "WEB-INF/wsdl/recepcion-bt/wsdl_recepcion_estado.wsdl")
@ServiceMode(Mode.MESSAGE)
@Scope(value="request")
public class RecepcionEstadoBtWSProvider extends WSProvider {

	/**
	 * Cola de recepción VM de peticiones sincronas
	 */
	public static final String RECEPT_QUEUE = "vm://recepcion-bt";
	
	public static final String NOTIFICACION_SMS_BT = "onIncomingStatus";
	public static final String NOTIFICACION_SMS_GENERICO = "notificacionSMS";
	
	public static final String PING_SMS_BT = "ping";
	

	@Override
	public SOAPMessage invoke(final SOAPMessage request) {

		SOAPMessage responseSOAP = null;
		
		String soapAction = String.class.cast(getContext().getMessageContext().get(SOAP_ACTION));
		
		if (soapAction.equals(NOTIFICACION_SMS_BT)){
			
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
	
					soapAction=NOTIFICACION_SMS_GENERICO;
					SoapPayload<?> payload = new PeticionPayload();	
					payload.setSoapAction(soapAction);
					payload.setSoapMessage(XMLUtils.soap2dom(request));

					final MuleMessage muleResponse = getMuleClient().send(RECEPT_QUEUE,payload, null, 10000);
					
					responseSOAP = XMLUtils.dom2soap(muleResponse.getPayload(SoapPayload.class).getSoapMessage());
					
	
				} catch (final Exception e) {
					throw new RuntimeException("Error in provider endpoint", e);
				}
				
			} catch (Exception e) {
				try {
					responseSOAP = generateSOAPNotificacion(request);
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
	protected static final SOAPMessage generateSOAPNotificacion(SOAPMessage request) throws Exception {
		
		PushStatusResponse respuesta = new PushStatusResponse();
		respuesta.setResponse(403);
		
		return XMLUtils.dom2soap(XMLUtils.setPayloadFromObject(respuesta, Charset.forName("UTF-8"), PushStatusResponse.class));
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
