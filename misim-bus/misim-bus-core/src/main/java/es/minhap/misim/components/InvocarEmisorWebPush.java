package es.minhap.misim.components;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import javapns.communication.ProxyManager;
import javapns.notification.PushedNotification;
import javapns.notification.PushedNotifications;
import javapns.test.NotificationTest;

import javax.annotation.Resource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import misim.bus.common.bean.SoapPayload;
import misim.bus.common.util.XMLUtils;

import org.json.JSONObject;
import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import es.minhap.common.properties.PropertiesServices;
import es.minhap.misim.bus.model.Proveedor;
import es.minhap.misim.bus.model.exception.ModelException;
import es.minhap.misim.bus.model.servicios.ProveedorManager;
import es.minhap.misim.tranformers.GMCSendMessage;
import es.minhap.misim.tranformers.NotificacionDataRequest;
import es.minhap.misim.tranformers.PushNotificationSender;
import es.minhap.plataformamensajeria.iop.beans.ConsultaEstadoXMLBean;
import es.minhap.plataformamensajeria.iop.business.beans.push.DatosEspecificosWebPush;
import es.minhap.plataformamensajeria.iop.business.beans.push.PeticionWebPush;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorServicios;
import es.minhap.plataformamensajeria.iop.services.usuariosplataformas.IRegistroUsuarioPushService;
import es.minhap.plataformamensajeria.iop.services.usuariosplataformas.webpush.IPushService;
import es.redsara.misim.misim_bus_webapp.respuesta.ResponseStatusType;
import es.redsara.misim.misim_bus_webapp.respuesta.Respuesta;

/**
 * Cliente genérico para JAX-WS
 * 
 * @author ludarcos
 * 
 */
public class InvocarEmisorWebPush implements Callable {

	private static final Logger LOG = LoggerFactory.getLogger(InvocarEmisorWebPush.class);

	@Resource(name = "pushServiceImpl")
	private IPushService pushService;
	
	@Resource(name = "reloadableResourceBundleMessageSource")
	ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;
	

	@Override
	public Object onCall(final MuleEventContext eventContext) throws ModelException {
		LOG.debug("Empezando el proceso de invocación del enviador...");
		PropertiesServices ps = null;
		try{

			final Document docOriginal = SoapPayload.class.cast(eventContext.getMessage().getPayload()).getSoapMessage();
			
			if(LOG.isInfoEnabled()){
	        	LOG.info("REQUEST: "+ XMLUtils.dom2xml(docOriginal));
	        }
			NodeList peticion = docOriginal.getElementsByTagNameNS("http://misim.redsara.es/misim-bus-webapp/peticion", "Peticion");
			String xmlPeticion = XMLUtils.nodeToString(peticion.item(0));
			
			PeticionWebPush peticionWp = new PeticionWebPush();
			peticionWp.loadObjectFromXML(xmlPeticion);
			
			es.minhap.plataformamensajeria.iop.beans.DatosEspecificosWebPush datos = geDatos(peticionWp.getDatosEspecificos());
			String respuesta= pushService.sendPush(datos, peticionWp.getMensajeId());
			
			Document doc = XMLUtils.xml2doc(respuesta, Charset.forName("UTF-8"));
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
				    
				    if(LOG.isInfoEnabled()){
			        	LOG.info("RESPONSE: " + XMLUtils.dom2xml(XMLUtils.soap2dom(responseMessage)));
			        }
				    
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
	
	private es.minhap.plataformamensajeria.iop.beans.DatosEspecificosWebPush geDatos(
			DatosEspecificosWebPush datosEspecificos) {
		es.minhap.plataformamensajeria.iop.beans.DatosEspecificosWebPush res = new es.minhap.plataformamensajeria.iop.beans.DatosEspecificosWebPush();
		
		res.setAuth(datosEspecificos.getAuth());
		res.setCabecera(datosEspecificos.getCabecera());
		res.setCaducidad(datosEspecificos.getCaducidad());
		res.setCuerpo(datosEspecificos.getCuerpo());
		res.setEndpoint(datosEspecificos.getEndpoint());
		res.setPdh(datosEspecificos.getPdh());
		res.setVapidPrivateKey(datosEspecificos.getVapidPrivateKey());
		res.setVapidPublicKey(datosEspecificos.getVapidPublicKey());
		
		return res;
	}

	private SOAPMessage getSoapMessageFromString(String xml) throws SOAPException, IOException {
		MessageFactory factory = MessageFactory.newInstance();
		SOAPMessage message = factory.createMessage(new MimeHeaders(), new ByteArrayInputStream(xml.getBytes(Charset.forName("UTF-8"))));
		return message;
	}
	
	private DatosEspecificosWebPush leerParametros(Document docOriginal) throws Exception {

		NodeList nodeList = docOriginal.getElementsByTagName("DatosEspecificosWebPush");

		JAXBContext jc = JAXBContext.newInstance(DatosEspecificosWebPush.class);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		return (DatosEspecificosWebPush) unmarshaller.unmarshal((Node) nodeList.item(0));

	}

}