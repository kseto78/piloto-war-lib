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

import es.minhap.misim.bus.core.pojo.PeticionPayload;
import es.telefonica.mi.interfazsimplificado.schemas.DeliverRes;
import es.telefonica.mi.interfazsimplificado.schemas.DeliverRes2;
import es.telefonica.mi.interfazsimplificado.schemas.DeliveryReportRes;
import es.telefonica.mi.interfazsimplificado.schemas.ResponseStatusType;

@WebServiceProvider(portName = "RecepcionTelefonicaPort", serviceName = "Recepcion001Service", targetNamespace = "http://www.telefonica.es/MI/InterfazSimplificado/definitions", wsdlLocation = "WEB-INF/wsdl/recepcion-telefonica/wsdl_recepcion.wsdl")
@ServiceMode(Mode.MESSAGE)
@Scope(value="request")
public class RecepcionTelefonicaWSProvider extends WSProvider {

	/**
	 * Cola de recepción VM de peticiones sincronas
	 */
	public static final String RECEPT_QUEUE = "vm://recepcion-telefonica";
	public static final String RECEPCION_SMS_TELEFONICA = "smsDeliver";
	public static final String RECEPCION_SMS_GENERICO = "recepcionSMS";
	
	public static final String NOTIFICACION_SMS_TELEFONICA = "deliveryReport";
	public static final String NOTIFICACION_SMS_GENERICO = "notificacionSMS";
	

	@Override
	public SOAPMessage invoke(final SOAPMessage request) {

		SOAPMessage responseSOAP = null;
		
		String soapAction = String.class.cast(getContext().getMessageContext().get(SOAP_ACTION));
		
		if(soapAction.equals(RECEPCION_SMS_TELEFONICA)){
			
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
		
		}else if (soapAction.equals(NOTIFICACION_SMS_TELEFONICA)){
			
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
		}else{
			
			try {
				responseSOAP = generateSOAPRecepcionMMS(request);
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
		
		DeliverRes respuesta = new DeliverRes();
		
		ResponseStatusType response = new ResponseStatusType();
		response.setStatusCode("0999");
		response.setStatusText("Error al obtener el contenido XML del mensaje SOAP");
		response.setDetails("Error al obtener el contenido XML del mensaje SOAP");

		respuesta.setStatus(response);
		respuesta.setVersion("1.0");
		
		return XMLUtils.dom2soap(XMLUtils.setPayloadFromObject(respuesta, Charset.forName("UTF-8"), DeliverRes.class));
	}
	
	/**
	 * Genera el SOAP Fault Message
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	protected static final SOAPMessage generateSOAPNotificacion(SOAPMessage request) throws Exception {
		
		
		DeliveryReportRes respuesta = new DeliveryReportRes();
		
		ResponseStatusType response = new ResponseStatusType();
		response.setStatusCode("0999");
		response.setStatusText("Error al obtener el contenido XML del mensaje SOAP");
		response.setDetails("Error al obtener el contenido XML del mensaje SOAP");

		respuesta.setStatus(response);
		respuesta.setVersion("1.0");
		
		return XMLUtils.dom2soap(XMLUtils.setPayloadFromObject(respuesta, Charset.forName("UTF-8"), DeliveryReportRes.class));
	}
	
	/**
	 * Genera el SOAP Fault Message
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	protected static final SOAPMessage generateSOAPRecepcionMMS(SOAPMessage request) throws Exception {
		
		
		DeliverRes2 respuesta = new DeliverRes2();
		
		ResponseStatusType response = new ResponseStatusType();
		response.setStatusCode("0404");
		response.setStatusText("Operacion no disponible");
		response.setDetails("Operacion no disponible");

		respuesta.setStatus(response);
		respuesta.setVersion("1.0");
		
		return XMLUtils.dom2soap(XMLUtils.setPayloadFromObject(respuesta, Charset.forName("UTF-8"), DeliverRes2.class));
	}
	

}
