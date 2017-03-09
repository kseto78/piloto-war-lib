package es.minhap.misim.components;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import misim.bus.common.bean.SoapPayload;
import misim.bus.common.util.KeyStoreUtils;
import misim.bus.common.util.XMLUtils;

import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.lifecycle.Callable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import es.minhap.misim.bus.model.exception.ModelException;
import es.minhap.misim.bus.model.seguridad.CifradoService;

/**
 * Component to call the Encryption Service SRV-CFR-01
 * 
 * @author ludarcos
 * 
 */

public class Encriptar implements Callable {

	private static final Logger LOG = LoggerFactory.getLogger(Encriptar.class);
	
	@Autowired
	CifradoService cifradoService;

	@Resource(name = "cifradoPrivadoProperties")
	Properties props;

	@Override
	public Object onCall(final MuleEventContext eventContext) throws Exception {

		LOG.debug("Empezando el proceso de encriptación...");
		// TODO : Implement the call to the SRV-CFR-01 service
		
		MuleMessage muleMessage = eventContext.getMessage();

		// Se recupera el documento de la petición
		final Document docOriginal = SoapPayload.class.cast(muleMessage.getPayload()).getSoapMessage();
		
		try{
			//encriptamos la respuesta real con transformacion
			String xml = XMLUtils.dom2xml(docOriginal);
			// Se recuperan todos los nodos Solicitudes del XML
			encriptarMensaje(muleMessage, xml);
			
			//encriptamos la respuesta directa del operador si existe.
			xml = eventContext.getMessage().getOutboundProperty("xmlRespuestaDirectaOperador");
			
			if (null != xml && xml.length() >= 0){
				encriptarMensajeOriginal(eventContext, xml);
			}
		
		}catch(ModelException e){
			//Lanzar error
			throw new ModelException(e.getMensaje(), e.getCodigo());
		}catch(Exception e){
			//Lanzar error
			LOG.error("Cifrado: Error de sistema Cifrado", e);
			throw new ModelException("Error de sistema Cifrado", 502);
		}

		LOG.debug("Proceso de encriptación terminado.");
		return muleMessage;
	}

	private void encriptarMensaje(MuleMessage muleMessage, String xml) throws Exception, ModelException {
		Document documento = XMLUtils.xml2doc(xml, Charset.forName("UTF-8"));
		
		NodeList nodeList = documento.getElementsByTagNameNS(
				"http://schemas.xmlsoap.org/soap/envelope/",
				"Body");

		// Si no existen no se realiza el cifrado
		if(nodeList!=null && nodeList.getLength()>0){
			
			// Se prepara la lista de nodos a cifrar
			List<Node> nodosACifrar = new ArrayList<Node>();
			nodosACifrar.add(nodeList.item(0));
			
			Document docCifrado = cifradoService.cifrar(
					documento, 
				props.getProperty(KeyStoreUtils.KEY_STORE_TYPE),
				props.getProperty(KeyStoreUtils.KEY_STORE_PASSWORD),
				props.getProperty(KeyStoreUtils.KEY_STORE_ALIAS),
				props.getProperty(KeyStoreUtils.ALIAS_PASSWORD),
				props.getProperty(KeyStoreUtils.KEY_STORE_FILE),
				nodosACifrar);
			
			// Verificamos que el documento ha sido cifrado
			
			//Comprobamos el nodo de cifrado
			NodeList cipherValue = docCifrado.getElementsByTagNameNS(
				"http://www.w3.org/2001/04/xmlenc#", 
				"CipherValue");

			if(cipherValue == null || !(cipherValue.getLength() > 0)){
				LOG.error("Cifrado: No se ha cifrado correctamente");
				throw new ModelException("No se ha cifrado correctamente", 320);
			}
			
			SoapPayload.class.cast(muleMessage.getPayload()).setSoapMessage(docCifrado);

		}
	}
	
	private void encriptarMensajeOriginal(MuleEventContext eventContext, String xml) throws Exception, ModelException {
		Document documento = XMLUtils.xml2doc(xml, Charset.forName("UTF-8"));
		
		NodeList nodeList = documento.getElementsByTagNameNS(
				"http://schemas.xmlsoap.org/soap/envelope/",
				"Body");

		// Si no existen no se realiza el cifrado
		if(nodeList!=null && nodeList.getLength()>0){
			
			// Se prepara la lista de nodos a cifrar
			List<Node> nodosACifrar = new ArrayList<Node>();
			nodosACifrar.add(nodeList.item(0));
			
			Document docCifrado = cifradoService.cifrar(
					documento, 
				props.getProperty(KeyStoreUtils.KEY_STORE_TYPE),
				props.getProperty(KeyStoreUtils.KEY_STORE_PASSWORD),
				props.getProperty(KeyStoreUtils.KEY_STORE_ALIAS),
				props.getProperty(KeyStoreUtils.ALIAS_PASSWORD),
				props.getProperty(KeyStoreUtils.KEY_STORE_FILE),
				nodosACifrar);
			
			// Verificamos que el documento ha sido cifrado
			
			//Comprobamos el nodo de cifrado
			NodeList cipherValue = docCifrado.getElementsByTagNameNS(
				"http://www.w3.org/2001/04/xmlenc#", 
				"CipherValue");

			if(cipherValue == null || !(cipherValue.getLength() > 0)){
				LOG.error("Cifrado: No se ha cifrado correctamente");
				throw new ModelException("No se ha cifrado correctamente", 320);
			}
			
			eventContext.getMessage().setOutboundProperty("xmlRespuestaDirectaOperador", XMLUtils.dom2xml(docCifrado));
			
		}
	}

}
