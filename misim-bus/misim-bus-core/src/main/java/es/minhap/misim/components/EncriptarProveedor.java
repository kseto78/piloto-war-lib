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

public class EncriptarProveedor implements Callable {

	private static final Logger LOG = LoggerFactory.getLogger(EncriptarProveedor.class);
	
	@Autowired
	CifradoService cifradoService;

	@Resource(name = "cifradoPublicoProperties")
	Properties props;

	@Override
	public Object onCall(final MuleEventContext eventContext) throws Exception {

		LOG.debug("Empezando el proceso de encriptación...");
		// TODO : Implement the call to the SRV-CFR-01 service
		
		MuleMessage muleMessage = eventContext.getMessage();

		// Se recupera el documento de la petición
		final Document docOriginal = SoapPayload.class.cast(muleMessage.getPayload()).getSoapMessage();
		final Document documento = XMLUtils.xml2doc(XMLUtils.dom2xml(docOriginal), Charset.forName("UTF-8"));
		
		try{
			
			// Se recuperan todos los nodos Solicitudes del XML
			NodeList nodeList = documento.getElementsByTagNameNS(
					String.valueOf(eventContext.getMessage().getOutboundProperty("esquemaCifrado")),
					String.valueOf(eventContext.getMessage().getOutboundProperty("nodoCifrado")));
			
			// Si no existen no se realiza el cifrado
			if(nodeList!=null && nodeList.getLength()>0){
				
				// Se prepara la lista de nodos a cifrar
				List<Node> nodosACifrar = new ArrayList<Node>();
				nodosACifrar.add(nodeList.item(0));
				
				Document docCifrado = cifradoService.cifrar(
					documento, 
					props.getProperty(KeyStoreUtils.KEY_STORE_TYPE),
					props.getProperty(KeyStoreUtils.KEY_STORE_PASSWORD),
					String.valueOf(eventContext.getMessage().getOutboundProperty("certificado")),
					"",
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
			
		}catch(ModelException e){
			//Lanzar error
			throw new ModelException(e.getMensaje(), e.getCodigo());
		}catch(Exception e){
			//Lanzar error
			LOG.error("Cifrado: Error de sistema Cifrado", e);
			throw new ModelException("Error de sistema Cifrado", 502);
		}
		
		LOG.debug("Proceso de encriptación terminado.");
		return eventContext.getMessage();
	}

}
