package es.minhap.misim.components;

import java.io.StringReader;
import java.nio.charset.Charset;

import javax.annotation.Resource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import misim.bus.common.bean.SoapPayload;
import misim.bus.common.util.XMLUtils;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import es.minhap.common.properties.PropertiesServices;
import es.minhap.misim.bus.model.Proveedor;
import es.minhap.misim.bus.model.exception.ModelException;
import es.minhap.misim.bus.model.servicios.ParametrosProveedorManager;
import es.minhap.misim.bus.model.servicios.ProveedorManager;
import es.minhap.misim.tranformers.RestSender;
import es.minhap.misim.transformers.peticionesrest.BasicAuthorization;
import es.minhap.misim.transformers.peticionesrest.Parameters;
import es.minhap.misim.transformers.peticionesrest.Peticion;
import es.redsara.misim.misim_bus_webapp.respuesta.ResponseStatusType;

/**
 * Cliente genérico para JAX-WS
 * 
 * @author ludarcos
 * 
 */
public class InvocarEmisorHTTP2 implements Callable {
	
	private static final Logger LOG = LoggerFactory.getLogger(InvocarEmisorHTTP2.class);
	
	public static final String ACCION_SMS_ENVIO = "envio";
	public static final String ACCION_SMS_CONSULTA = "consulta";
	
	@Resource(name="parametrosProveedorManager")
	private ParametrosProveedorManager parametrosProveedorManager; 
	
	@Resource(name="proveedorManager")
	private ProveedorManager proveedorManager; 
	
	@Resource(name = "reloadableResourceBundleMessageSource")
	ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;
	
	private String basicAutentication;

	private static final String ENCODING = "ISO-8859-1";
	private static final String BASICAUTENTICATION = "S";
	private static final String METHODPOST ="POST";
	private static final String MEDIATYPEJSON = "json";
	private static final String MEDIATYPEXML = "xml";

	@Override
	public Object onCall(final MuleEventContext eventContext) throws ModelException {

		LOG.debug("Inicio invocación emisor REST");
		
		final Document docOriginal = SoapPayload.class.cast(eventContext.getMessage().getPayload()).getSoapMessage();
		String respuesta ="";
		ResponseStatusType status = null;
		Proveedor p = null;
		try{
						
			Long proveedorId = eventContext.getMessage().getOutboundProperty("idProveedor");
			String encoding = (null != eventContext.getMessage().getOutboundProperty("encoding"))? (String)eventContext.getMessage().getOutboundProperty("encoding") : ENCODING;
			String certificado = eventContext.getMessage().getOutboundProperty("certificado");
			String certificadoPass = eventContext.getMessage().getOutboundProperty("certificadoPass");
			
			p = proveedorManager.getProveedorById(proveedorId);

			Long timeout = (null != p.getEndpoint().getTimeout())? p.getEndpoint().getTimeout() : 10000;

			try{
				String endpointUrl = String.class.cast(eventContext.getMessage().getOutboundProperty("endpointUrl"));
				
				if (eventContext.getMessage().getOutboundProperty("urlEndpointSIM")!=null && !("").equals(eventContext.getMessage().getOutboundProperty("urlEndpointSIM"))){
					endpointUrl = String.class.cast(eventContext.getMessage().getOutboundProperty("urlEndpointSIM"));
				}
				
				PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		
				Peticion peticion = completarPeticion(docOriginal, p, eventContext, ps);
												
				String keystore = null;
				
				if (null != certificado){
					keystore = ps.getMessage("ruta.certificados", null, null, null) + 
						((certificado.contains("sms") == true) ? ps.getMessage("ruta.tempos", null, null, null) + certificado :  certificado);
				}
				
				RestSender sender = new RestSender(keystore, certificadoPass);
				respuesta = sender.sendPeticion(endpointUrl, peticion,p.getMethod(), p.getMediaType(), encoding, timeout.intValue());
				if (LOG.isInfoEnabled()){
					LOG.info("RESPUESTA---> " + respuesta.replace("&lt;", "<").replace("&gt;", ">"));
				}
				
			}catch (Exception e) {
				LOG.error("Error leyendo los parámetros de la llamada: " + e.toString());
				status = new ResponseStatusType();
				status.setDetails("ERROR " + e.toString());
				status.setStatusCode("KO");
			}
		
		}catch (Exception e) {
			LOG.error("Error leyendo los parámetros de la llamada: " + e.toString());
			status = new ResponseStatusType();
			status.setDetails("ERROR " + e.toString());
			status.setStatusCode("KO");
		}
		
		try{
			
			es.minhap.misim.transformers.respuestasrest.Respuesta res = new es.minhap.misim.transformers.respuestasrest.Respuesta();
			res.loadObjectFromXML(respuesta);

			// comprobamos si tiene dtd para eliminarlo
			if (null != res.getStatus() && null != res.getStatus().getDetails() && res.getStatus().getDetails().contains(".dtd")) {
				res.getStatus().setDetails(eliminarDTD(res.getStatus().getDetails()));
			}
			if (null != res.getStatus() && null != res.getStatus().getDetails()) {
				res.getStatus().setDetails(res.getStatus().getDetails().replaceAll("\\<\\?xml(.+?)\\?\\>", "").trim());
			}

			SoapPayload.class.cast(eventContext.getMessage().getPayload()).setSoapMessage(
					XMLUtils.setPayloadFromObject(res, Charset.forName("UTF-8"), es.minhap.misim.transformers.respuestasrest.Respuesta.class));

			eventContext.getMessage().setOutboundProperty("xmlRespuestaDirectaOperador",
					XMLUtils.dom2xml(SoapPayload.class.cast(eventContext.getMessage().getPayload()).getSoapMessage()));
			

		}catch (Exception e) {
			LOG.error("Error al generar la respuesta: " + e.getMessage());
			throw new ModelException(e.getMessage()); 
		}
		
		LOG.debug("Fin invocación emisor HTTP.");

		return eventContext.getMessage();
	}
	
	private String eliminarDTD(String respuesta) throws Exception{
		Document doc;
		  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		  dbf.setValidating(false);
	        dbf.setNamespaceAware(true);
	        dbf.setFeature("http://xml.org/sax/features/namespaces", false);
	        dbf.setFeature("http://xml.org/sax/features/validation", false);
	        dbf.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
	        dbf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

	        DocumentBuilder db = dbf.newDocumentBuilder();

	        InputSource is = new InputSource();
	        is.setCharacterStream(new StringReader(respuesta));
	        doc = db.parse(is); 
	        return XMLUtils.dom2xml(doc);
	}


	private Peticion completarPeticion(Document docOriginal, Proveedor p, MuleEventContext eventContext, PropertiesServices ps) throws Exception {
		Peticion pet = new Peticion();
		Parameters parametros = null;
		String mensaje = null;
		try{
					
			//Si es JSON o xml
			if ((null != p.getMethod() && p.getMethod().equals(METHODPOST)) && null != p.getMediaType()){
				if (p.getMediaType().contains(MEDIATYPEJSON)){
					mensaje = getMensajeJson(docOriginal);					
				}else if(p.getMediaType().contains(MEDIATYPEXML)){
					mensaje = XMLUtils.dom2xml(docOriginal);
				}
			}else {
				parametros = new Parameters();
				parametros = parametros.loadObjectFromXML( XMLUtils.dom2xml(docOriginal));
			}
			
		getBasicAutentication(pet, eventContext);
		
		pet.setParameters(parametros);
		pet.setMessage(mensaje);
		}catch (Exception e){
			LOG.error("Error al generar la peticion: ", e);
		}
		
		return pet;
	}


	private String getMensajeJson(Document docOriginal) {
		String res = null;
		NodeList nodoRespuesta = docOriginal.getElementsByTagNameNS("http://misim.redsara.es/misim-bus-webapp/peticion", "Peticion");
		NodeList subList = nodoRespuesta.item(0).getChildNodes();
		if (subList != null && subList.getLength() > 0) {
			res = subList.item(0).getNodeValue();
		}
		return res;
	}

	private void getBasicAutentication(Peticion pet, MuleEventContext eventContext) {
		if (null != basicAutentication && basicAutentication.equals(BASICAUTENTICATION)){
			BasicAuthorization aut = new BasicAuthorization();
			aut.setUser((String)eventContext.getMessage().getOutboundProperty("usuarioAutentication"));
			aut.setPassword((String)eventContext.getMessage().getOutboundProperty("passwordAutentication"));
			pet.setBasicAuthorization(aut);
		}
	}

	
	
	
}