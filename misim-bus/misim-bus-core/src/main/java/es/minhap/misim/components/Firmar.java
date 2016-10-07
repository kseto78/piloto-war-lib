package es.minhap.misim.components;

import java.nio.charset.Charset;
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
import org.w3c.dom.NodeList;

import es.minhap.misim.bus.model.exception.ModelException;
import es.minhap.misim.bus.model.seguridad.FirmaService;

/**
 * Component to call the Firm Service SRV-FRM-01
 * 
 * @author ludarcos
 * 
 */
public class Firmar implements Callable {

	private static final Logger LOG = LoggerFactory.getLogger(Firmar.class);
	
	@Autowired
	FirmaService firmaService;

	@Resource(name = "firmaProperties")
	Properties props;

	@Override
	public Object onCall(final MuleEventContext eventContext) throws Exception {

		LOG.debug("Empenzando el proceso de firma...");
		// TODO : Implementar la llamada al servicio de firma : SRV-FRM-01
		
		MuleMessage muleMessage = eventContext.getMessage();

		// Se recupera el documento de la petición
		final Document docOriginal = SoapPayload.class.cast(muleMessage.getPayload()).getSoapMessage();
		final Document documento = XMLUtils.xml2doc(XMLUtils.dom2xml(docOriginal), Charset.forName("UTF-8"));
		
		try{
			
			String tipoFirma= eventContext.getMessage().getOutboundProperty("tipoFirma");
			
			if(("WS-Security").equals(tipoFirma)){
			
				// Invocamos al servicio de firma
				final Document docFirmado = firmaService.firmarWSSecurity(
						documento,
						props.getProperty(KeyStoreUtils.KEY_STORE_TYPE),
						props.getProperty(KeyStoreUtils.KEY_STORE_PASSWORD),
						props.getProperty(KeyStoreUtils.KEY_STORE_ALIAS),
						props.getProperty(KeyStoreUtils.ALIAS_PASSWORD),
						props.getProperty(KeyStoreUtils.KEY_STORE_FILE));
		
				// Se recuperan todos los nodos Signature del XML
				final NodeList signatureL = docFirmado.getElementsByTagNameNS(
						"http://www.w3.org/2000/09/xmldsig#", 
						"Signature");
		
				// Si no hay o si no hay uno único, la firma no es válida
				if(signatureL == null ||signatureL.getLength() != 1 ){
					LOG.error("Firma: Error al generar la firma del mensaje");
					throw new ModelException("Error al generar la firma del mensaje", 306);
				}
				
				SoapPayload.class.cast(muleMessage.getPayload()).setSoapMessage(docFirmado);
				
			}else if(("XMLdSig").equals(tipoFirma)){
				
				// Invocamos al servicio de firma
				final Document docFirmado = firmaService.firmarXMLDSign(
						documento,
						props.getProperty(KeyStoreUtils.KEY_STORE_TYPE),
						props.getProperty(KeyStoreUtils.KEY_STORE_PASSWORD),
						props.getProperty(KeyStoreUtils.KEY_STORE_ALIAS),
						props.getProperty(KeyStoreUtils.ALIAS_PASSWORD),
						props.getProperty(KeyStoreUtils.KEY_STORE_FILE));
		
				// Se recuperan todos los nodos Signature del XML
				final NodeList signatureL = docFirmado.getElementsByTagNameNS(
						"http://www.w3.org/2000/09/xmldsig#", 
						"Signature");
		
				// Si no hay o si no hay uno único, la firma no es válida
				if(signatureL == null ||signatureL.getLength() != 1 ){
					LOG.error("Firma: Error al generar la firma del mensaje");
					throw new ModelException("Error al generar la firma del mensaje", 306);
				}
				
				SoapPayload.class.cast(muleMessage.getPayload()).setSoapMessage(docFirmado);
			}
		
		}catch(ModelException e){
			//Lanzar error
			throw new ModelException(e.getMensaje(), e.getCodigo());
		}catch(Exception e){
			//Lanzar error
			LOG.error("Firma: Error de sistema Firmar", e);
			throw new ModelException("Error de sistema Firma", 502);
		}
		
		LOG.debug("Proceso de firma terminado.");
		return muleMessage;
	}

}
