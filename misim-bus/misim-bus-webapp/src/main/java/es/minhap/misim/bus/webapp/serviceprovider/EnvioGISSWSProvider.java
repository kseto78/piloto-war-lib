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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import es.minhap.common.properties.PropertiesServices;
import es.minhap.misim.bus.core.pojo.PeticionPayload;
import es.redsara.misim.misim_bus_webapp.respuesta.ResponseStatusType;
import es.redsara.misim.misim_bus_webapp.respuesta.Respuesta;

@WebServiceProvider(portName = "ws-envio-gissPort", serviceName = "Envio002Service", targetNamespace = "http://misim.redsara.es/misim-bus-webapp/", wsdlLocation = "WEB-INF/wsdl/envio-giss/enviar002.wsdl")
@ServiceMode(Mode.MESSAGE)
@Scope(value="request")
public class EnvioGISSWSProvider extends WSProvider {

	private static final Logger LOG = LoggerFactory.getLogger(EnvioGISSWSProvider.class);
	/**
	 * Cola de recepción VM de peticiones sincronas
	 */
	public static final String RECEPT_QUEUE = "vm://envio-sim-GISS";


	/**
	 * Metodo que implementa la logica de la peticion de envios GISS
	 */
	@Override
	public SOAPMessage invoke(final SOAPMessage request) {

		SOAPMessage responseSOAP = null;
		String errorClave = "";
		try {
			
			final ServletContext servletContext = (ServletContext) getContext().getMessageContext().get(MessageContext.SERVLET_CONTEXT);

			setMuleContext(MuleContext.class.cast(servletContext.getAttribute("mule.context")));

			MuleContext ctx = getMuleContext();
			ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource = ctx.getRegistry().lookupObject("reloadableResourceBundleMessageSource");
			
			PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
			errorClave = ps.getMessage("clave.ERRORCLAVE.GISS", null, "[ERROR-CL@VE]:");
			String ok = ps.getMessage("clave.respuestaOK.GISS", null, "0000");
			
			if (getMuleClient() == null) {
				try {
					setMuleClient(new MuleClient(getMuleContext()));

				} catch (final MuleException e) {
					LOG.error(errorClave + "Error in mule client");
					throw new RuntimeException("Error in mule client", e);
				}
			}
			try {

				SoapPayload<?> payload = new PeticionPayload();
				payload.setSoapAction(String.class.cast(getContext().getMessageContext().get(SOAP_ACTION)));
				payload.setSoapMessage(XMLUtils.soap2dom(request));
//				LOG.info("Recepción de la petición: "+XMLUtils.dom2xml(XMLUtils.soap2dom(request)));

				final MuleMessage muleResponse = getMuleClient().send(RECEPT_QUEUE,payload, null, 10000);
				
				responseSOAP = XMLUtils.dom2soap(muleResponse.getPayload(SoapPayload.class).getSoapMessage());

				String a = XMLUtils.dom2xml(muleResponse.getPayload(SoapPayload.class).getSoapMessage());
				if (!a.contains(ok)){
					Document doc = muleResponse.getPayload(SoapPayload.class).getSoapMessage();
					NodeList nodoRespuesta = doc.getElementsByTagNameNS("http://misim.redsara.es/misim-bus-webapp/respuesta", "CodigoEstado");//("ns2:Respuesta");
					NodeList nodoRespuesta2 = doc.getElementsByTagNameNS("http://misim.redsara.es/misim-bus-webapp/respuesta", "DescripcionError");
					String xmlRespuesta = nodoRespuesta.item(0).getFirstChild().getNodeValue();
					String xmlRespuesta2 = (null != nodoRespuesta2.item(0) )? nodoRespuesta2.item(0).getFirstChild().getNodeValue() : "";
					
					LOG.error(errorClave + xmlRespuesta + " " +xmlRespuesta2);
				}
				
			} catch (final Exception e) {
				throw new RuntimeException("Error in provider endpoint", e);
			}
			
		} catch (Exception e) {
			try {
				responseSOAP = generateSOAPFaultEnvio(request);
				LOG.error(errorClave + "Error al obtener el contenido XML del mensaje SOAP");
			} catch (Exception e1) {
				LOG.error(errorClave + e1.getMessage());
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
		
		response.setStatusCode("0999");
		response.setStatusText("Error al obtener el contenido XML del mensaje SOAP");
		response.setDetails("Error al obtener el contenido XML del mensaje SOAP");

		Respuesta respuesta = new Respuesta();
		respuesta.setStatus(response);
		
		return XMLUtils.dom2soap(XMLUtils.setPayloadFromObject(respuesta, Charset.forName("UTF-8"), Respuesta.class));
	}

}
