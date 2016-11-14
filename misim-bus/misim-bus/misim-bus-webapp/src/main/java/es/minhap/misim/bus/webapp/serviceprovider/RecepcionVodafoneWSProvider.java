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
import es.redsara.misim.misim_bus_webapp.respuesta.envio.aplicaciones.ResponseStatusType;
import es.redsara.misim.misim_bus_webapp.respuesta.envio.aplicaciones.Respuesta;
import es.redsara.misim.misim_bus_webapp.respuesta.vodafone.ResultadoOp;


@WebServiceProvider(portName = "RecepcionVodafonePort", serviceName = "Recepcion002Service", 
targetNamespace = "http://princast.es/redboxentrada", wsdlLocation = "WEB-INF/wsdl/recepcion-vodafone/wsdl_recepcion.wsdl")
@ServiceMode(Mode.MESSAGE)
@Scope(value="request")
public class RecepcionVodafoneWSProvider extends WSProvider {

	/**
	 * Cola de recepción VM de peticiones sincronas
	 */
	public static final String RECEPT_QUEUE = "vm://recepcion-vodafone";
	
	public static final String RECEPCION_SMS_VODAFONE = "urn:receiveSMSEntrada";
	public static final String RECEPCION_SMS_GENERICO = "recepcionSMS";
	
	public static final String NOTIFICACION_SMS_VODAFONE = "urn:receiveNotificacion";
	public static final String NOTIFICACION_SMS_GENERICO = "notificacionSMS";
	

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
				
				String soapAction = String.class.cast(getContext().getMessageContext().get(SOAP_ACTION));
				if(soapAction.equals(RECEPCION_SMS_VODAFONE)){
					soapAction=RECEPCION_SMS_GENERICO;
					
					SoapPayload<?> payload = new PeticionPayload();
					payload.setSoapAction(soapAction);
					payload.setSoapMessage(XMLUtils.soap2dom(request));
				
//					System.out.println("Recepción de la petición: "+XMLUtils.dom2xml(XMLUtils.soap2dom(request)));

					final MuleMessage muleResponse = getMuleClient().send(RECEPT_QUEUE,payload, null, 10000);
					
					responseSOAP = XMLUtils.dom2soap(muleResponse.getPayload(SoapPayload.class).getSoapMessage());
					
				}else if (soapAction.equals(NOTIFICACION_SMS_VODAFONE)){
					soapAction=NOTIFICACION_SMS_GENERICO;
					
					SoapPayload<?> payload = new PeticionPayload();
					payload.setSoapAction(soapAction);
					payload.setSoapMessage(XMLUtils.soap2dom(request));
				
//					System.out.println("Recepción de la petición: "+XMLUtils.dom2xml(XMLUtils.soap2dom(request)));

					final MuleMessage muleResponse = getMuleClient().send(RECEPT_QUEUE,payload, null, 10000);
					
					responseSOAP = XMLUtils.dom2soap(muleResponse.getPayload(SoapPayload.class).getSoapMessage());
					
				}else{
					
					responseSOAP = generateSOAPFaultNoAction(request);
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
		
		ResultadoOp response = new ResultadoOp();
		
		response.setEstado("NOK");
		response.setCodigo("0403");
		response.setDetalle("Error al obtener el contenido XML del mensaje SOAP");
		
		return XMLUtils.dom2soap(XMLUtils.setPayloadFromObject(response, Charset.forName("UTF-8"), ResultadoOp.class));
	}
	
	/**
	 * Genera el SOAP Fault Message
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	protected static final SOAPMessage generateSOAPFaultNoAction(SOAPMessage request) throws Exception {
		
		ResponseStatusType response = new ResponseStatusType();
		
		response.setStatusCode("0404");
		response.setStatusText("Operacion no disponible");
		response.setDetails("Operacion no disponible");

		Respuesta respuesta = new Respuesta();
		respuesta.setStatus(response);
		
		return XMLUtils.dom2soap(XMLUtils.setPayloadFromObject(respuesta, Charset.forName("UTF-8"), Respuesta.class));
	}

}
