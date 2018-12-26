
package es.minhap.misim.components;

import java.util.Properties;

import javax.annotation.Resource;

import misim.bus.common.bean.SoapPayload;
import misim.bus.common.util.KeyStoreUtils;
import misim.bus.common.util.XMLUtils;

import org.mule.api.MuleContext;
import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.lifecycle.Callable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import es.minhap.common.properties.PropertiesServices;
import es.minhap.misim.bus.model.exception.ModelException;
import es.minhap.misim.bus.model.seguridad.CifradoService;
import es.minhap.misim.bus.model.seguridad.FirmaService;
import es.minhap.plataformamensajeria.iop.beans.lotes.PeticionXMLBean;

/**
 * Component to call the Firm Validation Service SRV-FRM-02
 * 
 * @author ludarcos
 * 
 */
public class ValidarFirma implements Callable {
	
	@Autowired
	CifradoService cifradoService;

	@Resource(name = "cifradoPrivadoProperties")
	Properties props;

	private static final Logger LOG = LoggerFactory.getLogger(ValidarFirma.class);

	@Autowired
	FirmaService firmaService;
	
	@Override
	public Object onCall(final MuleEventContext eventContext) throws Exception {

		MuleMessage muleMessage = eventContext.getMessage();
		
		MuleContext ctx = eventContext.getMuleContext();
		
		ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource = ctx.getRegistry().lookupObject("reloadableResourceBundleMessageSource");
		
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);

		// Se recupera el documento de la petición
		final Document docFirmado = SoapPayload.class.cast(muleMessage.getPayload()).getSoapMessage();
				
		// Obtenemos el tipo de firma
		String tipoFirma= eventContext.getMessage().getOutboundProperty("tipoFirma");
		
		try{
			if(("WS-Security").equals(tipoFirma) || ("EnvioMensajesSSL").equals(tipoFirma)){
				// Obtenemos el id del servicio de la peticion para poder buscar en la BBDD el
				// certificado correspondiente, si el tipo de la peticion es de de EnvioMensajesSSL
				String idServicio = null;
				if(tipoFirma.equals("EnvioMensajesSSL")){
					idServicio = descifrar(docFirmado);
					if(idServicio == null){
						throw new ModelException("Falta el id de servicio para comprobar la firma", Integer.parseInt(ps.getMessage("plataformaErrores.ValidarFirma.COD_ERROR_GENERAL", null)));
					}
				}
				
				// Lanzamos la validación de firma
				if(!firmaService.validarFirmaWSSecurity(docFirmado, idServicio, ps)){

					//Lanzar error
					LOG.error("Validar Firma: Firma no válida");
					throw new ModelException("Firma no válida",305);
				}
				
			}else if(("XMLdSig").equals(tipoFirma)){
				// Lanzamos la validación de firma
				if(!firmaService.validarFirmaXMLDSign(docFirmado)){
					//Lanzar error
					LOG.error("Validar Firma: Firma no válida");
					throw new ModelException("Firma no válida",305);
				}
			}

		}catch(ModelException e){
			//Lanzar error
			throw new ModelException(e.getMensaje(), e.getCodigo());
		}catch(Exception e){
			//Lanzar error
			LOG.error("Validar Firma: Error de sistema Validar Firma", e);
			throw new ModelException("Error de sistema Validar Firma", Integer.parseInt(ps.getMessage("plataformaErrores.ValidarFirma.COD_ERROR_GENERAL", null)));
		}

		LOG.debug("Validación de firma realizada.");
		return eventContext.getMessage();
	}

	private String descifrar(Document docFirmado) throws Exception {
		// TODO Auto-generated method stub
		// Desciframos el documento cifrado
		Document docDescifrado = cifradoService.descifrarKey(
				docFirmado,
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
		
		NodeList peticion = docDescifrado.getElementsByTagNameNS("http://misim.redsara.es/misim-bus-webapp/peticion", "Peticion");
		String xmlPeticion = "";
		if(peticion.item(0)!=null){
			xmlPeticion = XMLUtils.nodeToString(peticion.item(0));
			PeticionXMLBean peticionXML = new PeticionXMLBean();
			peticionXML.loadObjectFromXML(xmlPeticion);
			String idServicio = peticionXML.getServicio();
			return idServicio;
		}
		else {
			peticion = docDescifrado.getElementsByTagNameNS("http://misim.redsara.es/misim-bus-webapp/peticion", "ActualizarPasswordPeticion");
			xmlPeticion = XMLUtils.nodeToString(peticion.item(0));
			return "ActualizarPassword";
		}
		
	}

}
