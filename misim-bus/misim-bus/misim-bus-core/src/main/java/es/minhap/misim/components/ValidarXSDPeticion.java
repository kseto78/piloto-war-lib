package es.minhap.misim.components;

import javax.annotation.Resource;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * Component to call the Auditor�a Service SRV-ADT-02
 * 
 * @author ludarcos
 * 
 */
public class ValidarXSDPeticion implements Callable {

	private static final Logger LOG = LoggerFactory.getLogger(ValidarXSDPeticion.class);
	
	@Resource(name = "reloadableResourceBundleMessageSource")
	ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;

	@Override
	public Object onCall(final MuleEventContext eventContext) throws Exception {
		
		LOG.debug("Empezando el proceso de validacion XSD...");

//		String xsdEspecificos = eventContext.getMessage().getOutboundProperty("xsdEspecificosConsumidor");
//		final Document docOriginal = SoapPayload.class.cast(eventContext.getMessage().getPayload()).getSoapMessage();
//		String xmlOriginal = XMLUtils.dom2xml(docOriginal);
//		
//		try{
//			
//			PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
//			
//			URL url = new URL(String.valueOf(ps.getMessage("ruta.xsd.peticion", null)));
//			File xsdFile = new File(url.toURI());
//			List<String> lines = Files.readAllLines(xsdFile.toPath(), Charset.forName("UTF-8"));
//			StringBuilder sb = new StringBuilder();
//			
//			for (String line : lines) {
//				sb.append(line);
//			}
//			
//			Document xsdDOM = XMLUtils.xml2doc(sb.toString(), Charset.forName("UTF-8"));
//			String systemID = xsdFile.getAbsolutePath();
//			
//			if(!XMLUtils.isValid(xmlOriginal, "UTF-8", XMLUtils.dom2xml(xsdDOM), "UTF-8", systemID)){
//				//Lanzar error
//				LOG.error("Validar XSD: La estructura del fichero recibido no corresponde con el esquema");
//				throw new ModelException("La estructura del fichero recibido no corresponde con el esquema", 401);
//			}
//			
//			if(xsdEspecificos!=null && !("").equals(xsdEspecificos)){
//				
//				int inicio = xmlOriginal.indexOf("<ns2:DatosEspecificos");
//				int fin = xmlOriginal.indexOf("</ns2:DatosEspecificos>");
//				
//				Document docEspecificos = XMLUtils.xml2doc(xmlOriginal.substring(inicio, fin)+"</ns2:DatosEspecificos>", Charset.forName("UTF-8"));
//				
//				StringWriter writer = new StringWriter();
//				Transformer transformer = TransformerFactory.newInstance().newTransformer();
//				transformer.transform(new DOMSource(docEspecificos.getFirstChild().getFirstChild()), new StreamResult(writer));
//				String xmlEspecificos = writer.toString();
//				
//				if(!XSDValidator.process(xmlEspecificos, xsdEspecificos)){
//					//Lanzar error
//					LOG.error("Validar XSD: La estructura del fichero recibido no corresponde con el esquema");
//					throw new ModelException("La estructura del fichero recibido no corresponde con el esquema", 401);
//				}
//			}
//			
//		}catch(ModelException e){
//			//Lanzar error
//			throw new ModelException(e.getMensaje(), e.getCodigo());
//		}catch(Exception e){
//			//Lanzar error
//			LOG.error("Validar XSD: Error de sistema Validar XSD", e);
//			throw new ModelException("Error de sistema Validar XSD", 502);
//		}

		LOG.debug("Proceso de validación de XSD terminado.");
		return eventContext.getMessage();
	}
	
}
