package es.minhap.misim.bus.webapp.serviceprovider;

import java.nio.charset.Charset;

import javax.xml.soap.SOAPMessage;
import javax.xml.ws.Service.Mode;
import javax.xml.ws.ServiceMode;
import javax.xml.ws.WebServiceProvider;

import misim.bus.common.bean.SoapPayload;
import misim.bus.common.util.XMLUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import es.minhap.misim.bus.core.pojo.PeticionPayload;
import es.minhap.plataformamensajeria.iop.beans.PeticionEnvioAplicacion;
import es.minhap.plataformamensajeria.iop.beans.RespuestaEnvioAplicacion;
import es.redsara.misim.misim_bus_webapp.respuesta.ResponseStatusType;
import es.redsara.misim.misim_bus_webapp.respuesta.Respuesta;
/**
 * Clase que implementa la logica del servicio de EnvioAplicacionService
 * @author ralberoc
 *
 */
@WebServiceProvider(portName = "EnvioAplicacionImplPort", serviceName = "EnvioAplicacionService", targetNamespace = "http://misim.redsara.es/misim-bus-webapp/", wsdlLocation = "WEB-INF/wsdl/envio-aplicaciones/enviarAplicacion.wsdl")
@ServiceMode(Mode.MESSAGE)
@Scope(value="request")
public class EnvioAplicacionesServerWSProvider extends WSProvider {

	private static final Logger LOG = LoggerFactory.getLogger(EnvioAplicacionesServerWSProvider.class);

	/**
	 * Metodo que implementa la logica de la peticion de enviarAplicacion
	 */
	@Override
	public SOAPMessage invoke(final SOAPMessage request) {

		SOAPMessage responseSOAP = null;

		try {
			LOG.info("INI - EnvioAplicacionesServerWSProvider");
			SoapPayload<?> payload = new PeticionPayload();
			payload.setSoapAction(String.class.cast(getContext().getMessageContext().get(SOAP_ACTION)));
			payload.setSoapMessage(XMLUtils.soap2dom(request));
			//Carga de la peticion
			Document document = XMLUtils.soap2dom(request);
			NodeList peticion = document.getElementsByTagNameNS("http://misim.redsara.es/misim-bus-webapp/peticion",
					"envioAplicacionRequest");

			String xmlPeticion = XMLUtils.nodeToString(peticion.item(0));
			
			PeticionEnvioAplicacion peticionEnvioAplicacion = new PeticionEnvioAplicacion();
			peticionEnvioAplicacion.loadObjectFromXML(xmlPeticion);
			//Carga de la respuesta
			RespuestaEnvioAplicacion respuestaPeticion = new RespuestaEnvioAplicacion();
			respuestaPeticion.recibirSmsAplicacion(peticionEnvioAplicacion);
			
			responseSOAP = XMLUtils.dom2soap(XMLUtils.setPayloadFromObject(respuestaPeticion, Charset.forName("UTF-8"), RespuestaEnvioAplicacion.class));			
			LOG.info("FIN - EnvioAplicacionesServerWSProvider");
		} catch (final Exception e) {
			throw new RuntimeException("Error in provider endpoint", e);
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
