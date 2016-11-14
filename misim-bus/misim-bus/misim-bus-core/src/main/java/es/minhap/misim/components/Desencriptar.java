package es.minhap.misim.components;

import java.nio.charset.Charset;
import java.util.Properties;

import javax.annotation.Resource;
import javax.xml.soap.SOAPMessage;

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
import org.w3c.dom.NodeList;

import es.minhap.misim.bus.model.exception.ModelException;
import es.minhap.misim.bus.model.seguridad.CifradoService;

public class Desencriptar implements Callable {

	private static final Logger LOG = LoggerFactory.getLogger(Desencriptar.class);

	@Autowired
	CifradoService cifradoService;

	@Resource(name = "cifradoPrivadoProperties")
	Properties props;
	
	@Override
	public Object onCall(final MuleEventContext eventContext) throws Exception {

		LOG.debug("Empezando el proceso de desencriptación...");
		// TODO : Implement the call to the SRV-CFR-02 service

		MuleMessage muleMessage = eventContext.getMessage();

		// Se recupera el documento de la petición
		final Document docOriginal = SoapPayload.class.cast(muleMessage.getPayload()).getSoapMessage();
		final Document documento = XMLUtils.xml2doc(XMLUtils.dom2xml(docOriginal), Charset.forName("UTF-8"));
		
		try{
			
			//Comprobamos si esta cifrado		
			final NodeList encryptedData = docOriginal.getElementsByTagNameNS(
					"http://www.w3.org/2001/04/xmlenc#", 
					"EncryptedData");
			
			if(encryptedData != null && encryptedData.getLength() > 0){
				
				LOG.info("Descifrado: Es necesario descifrar");
				
				// Desciframos el documento cifrado
				Document docDescifrado = cifradoService.descifrarKey(
						documento,
						props.getProperty(KeyStoreUtils.KEY_STORE_TYPE),
						props.getProperty(KeyStoreUtils.KEY_STORE_PASSWORD),
						props.getProperty(KeyStoreUtils.KEY_STORE_ALIAS),
						props.getProperty(KeyStoreUtils.ALIAS_PASSWORD),
						props.getProperty(KeyStoreUtils.KEY_STORE_FILE));
		
				docDescifrado = cifradoService.descifrar(
						docDescifrado,
						props.getProperty(KeyStoreUtils.KEY_STORE_TYPE),
						props.getProperty(KeyStoreUtils.KEY_STORE_PASSWORD),
						props.getProperty(KeyStoreUtils.KEY_STORE_ALIAS),
						props.getProperty(KeyStoreUtils.ALIAS_PASSWORD),
						props.getProperty(KeyStoreUtils.KEY_STORE_FILE));
		
				// Verificamos que el documento ha sido descifrado
				
				//Comprobamos el nodo de cifrado
				final NodeList encryptedDataDescifrado = docDescifrado.getElementsByTagNameNS(
						"http://www.w3.org/2001/04/xmlenc#", 
						"EncryptedData");
				
				if(encryptedDataDescifrado != null && encryptedDataDescifrado.getLength() > 0){
					//Lanzar error
					LOG.error("Descifrado: No se ha descifrado correctamente");
					throw new ModelException("No se ha descifrado correctamente", 320);
				}
				
				SOAPMessage soapMessage = XMLUtils.dom2soap(docDescifrado);
				soapMessage.getSOAPHeader().removeContents();
				SoapPayload.class.cast(muleMessage.getPayload()).setSoapMessage(XMLUtils.soap2dom(soapMessage));
				
			}else{
				LOG.info("Descifrado: No es necesario descifrar");
			}
		
		}catch(ModelException e){
			//Lanzar error
			throw new ModelException(e.getMensaje(), e.getCodigo());
		}catch(Exception e){
			//Lanzar error
			LOG.error("Descifrado: Error de sistema Descifrado", e);
			throw new ModelException("Error de sistema Descifrado", 502);
		}

		LOG.debug("Proceso de desencriptación terminado.");
		return muleMessage;
	}

}
