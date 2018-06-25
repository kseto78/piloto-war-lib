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
import es.minhap.plataformamensajeria.iop.beans.PeticionAcCLEV1;
import es.minhap.plataformamensajeria.iop.beans.RespuestaAcCLEV1;
import es.redsara.misim.misim_bus_webapp.respuesta.ResponseStatusType;
import es.redsara.misim.misim_bus_webapp.respuesta.Respuesta;
/**
 * Clase que implementa la logica del servicio AcCLEV1TestPro
 * @author ralberoc
 *
 */
@WebServiceProvider(portName = "AcCLEV1TestPro", serviceName = "AcCLEV1Service", targetNamespace = "http://misim.redsara.es/misim-bus-webapp/", wsdlLocation = "WEB-INF/wsdl/acclev1-sim/AcCLEV1.wsdl")
@ServiceMode(Mode.MESSAGE)
@Scope(value="request")
public class AcCLEV1ServiceWSProvider extends WSProvider {

	private static final Logger LOG = LoggerFactory.getLogger(AcCLEV1ServiceWSProvider.class);

	/**
	 * Metodo que implementa la logica de la peticion AcCLEV1
	 */
	@Override
	public SOAPMessage invoke(final SOAPMessage request) {

		SOAPMessage responseSOAP = null;

		try {
			LOG.info("INI - AcCLEV1ServiceWSProvider");
			SoapPayload<?> payload = new PeticionPayload();
			payload.setSoapAction(String.class.cast(getContext().getMessageContext().get(SOAP_ACTION)));
			payload.setSoapMessage(XMLUtils.soap2dom(request));
			//Carga de la peticion
			Document document = XMLUtils.soap2dom(request);
			NodeList peticion = document.getElementsByTagNameNS("http://misim.redsara.es/misim-bus-webapp/AcCLEV1Ent",
					"PeticionNotificacionEstadoSMS");

			String xmlPeticion = XMLUtils.nodeToString(peticion.item(0));
			
			PeticionAcCLEV1 peticionAcCLEV1 = new PeticionAcCLEV1();
			peticionAcCLEV1.loadObjectFromXML(xmlPeticion);
			//Carga de la respuesta
			RespuestaAcCLEV1 respuestaPeticion = new RespuestaAcCLEV1();
			respuestaPeticion.operacion(peticionAcCLEV1);
			
			responseSOAP = XMLUtils.dom2soap(XMLUtils.setPayloadFromObject(respuestaPeticion, Charset.forName("UTF-8"), RespuestaAcCLEV1.class));			
			LOG.info("FIN - AcCLEV1ServiceWSProvider");
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
