package es.minhap.misim.components;

import java.util.List;

import javax.annotation.Resource;

import misim.bus.common.bean.SoapPayload;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import es.minhap.misim.bus.model.Aplicacion;
import es.minhap.misim.bus.model.exception.ModelException;
import es.minhap.misim.bus.model.servicios.AplicacionManager;
import es.minhap.misim.bus.query.AplicacionQuery;

/**
 * Component to call the Identification Service SRV-ID- 01
 * 
 * @author ludarcos
 * 
 */
public class IdentificarAplicacion implements Callable {

	private static final Logger LOG = LoggerFactory.getLogger(IdentificarAplicacion.class);
	
	@Resource
	private AplicacionManager aplicacionManager;
	
	@Override
	public Object onCall(final MuleEventContext eventContext) throws Exception {

		LOG.debug("Inicio de la identificación de la aplicación");
		
		// Se recupera el documento de la petición
		final Document docOriginal = SoapPayload.class.cast(eventContext.getMessage().getPayload()).getSoapMessage();
		try{
			
			// Se recupera el identificador del mensaje
			String idMensaje = null;
			
			NodeList nodeMensaje = docOriginal.getElementsByTagNameNS(
					"http://misim.redsara.es/misim-bus-webapp/peticion",
					"MensajeId");
			
			
			if (nodeMensaje.item(0)!=null){
				
				idMensaje = nodeMensaje.item(0).getTextContent();
				
			}else{
				
				NodeList nodeMensaje2 = docOriginal.getElementsByTagName("MensajeId");
				idMensaje = nodeMensaje2.item(0).getTextContent();
			}
			
			eventContext.getMessage().setOutboundProperty("idMensaje", idMensaje);
			
			NodeList nodoLoteId = docOriginal.getElementsByTagName("idLote");
			
			if(nodoLoteId!=null && nodoLoteId.item(0)!=null) {
				String idLote=nodoLoteId.item(0).getTextContent();
				eventContext.getMessage().setOutboundProperty("idLote", idLote);
			}

			// Se recupera el url endpoint (no es obligatorio)
			String urlEndpoint = "";
			
			NodeList nodeEndpoint = docOriginal.getElementsByTagNameNS(
					"http://misim.redsara.es/misim-bus-webapp/peticion",
					"UrlEndpoint");
			
			
			if (nodeEndpoint.item(0)!=null){
				
				urlEndpoint = nodeEndpoint.item(0).getTextContent();
				eventContext.getMessage().setOutboundProperty("urlEndpointSIM", urlEndpoint);
				
			}else{
				
				NodeList nodeEndpoint2 = docOriginal.getElementsByTagName("UrlEndpoint");
				if (nodeEndpoint2!=null && nodeEndpoint2.item(0)!=null){
					urlEndpoint = nodeEndpoint2.item(0).getTextContent();
					eventContext.getMessage().setOutboundProperty("urlEndpointSIM", urlEndpoint);
				}
			}
			
			eventContext.getMessage().setOutboundProperty("urlEndpointSIM", urlEndpoint);
			
			String usuario = null;
			String password = null;
			
			NodeList usuario1 = docOriginal.getElementsByTagNameNS(
					"http://misim.redsara.es/misim-bus-webapp/peticion",
					"Usuario");
			
			NodeList password1 = docOriginal.getElementsByTagNameNS(
					"http://misim.redsara.es/misim-bus-webapp/peticion",
					"Password");
			
			if (usuario1.item(0)!=null){
				
				usuario = usuario1.item(0).getTextContent();
			}else{
				
				NodeList usuario2 = docOriginal.getElementsByTagName("Usuario");
				usuario = usuario2.item(0).getTextContent();
			}
			

			if (password1.item(0)!=null){
				
				password=password1.item(0).getTextContent();
			}else{
				
				NodeList password2 = docOriginal.getElementsByTagName("Password");
				password = password2.item(0).getTextContent();
			}
					
			
			
			AplicacionQuery arg0 = new AplicacionQuery();
			arg0.setUsuario(usuario);
			arg0.setPassword(password);
			
			List<Aplicacion> listaAplicaciones = aplicacionManager.getAplicacion(arg0);
			
			if(listaAplicaciones==null || listaAplicaciones.isEmpty()){
				
				throw new ModelException("No existe ninguna aplicación para el usuario de la petición", 315);
				
			}else if (listaAplicaciones.size()>1){
				
				throw new ModelException("Existe más de una aplicación para el usuario de la petición", 312);
			}else{
				
				eventContext.getMessage().setOutboundProperty("idAplicacion", listaAplicaciones.get(0).getIdAplicacion());
			}
			
		}catch (ModelException e){
			
			throw new ModelException(e.getMensaje(), e.getCodigo());
			
		}catch (Exception e){
			
			LOG.error("Identificación: Error de sistema al identificar la aplicación", e);
			throw new ModelException("Error de sistema al identificar la aplicación", 502);
		}
		
		LOG.debug("Empezando la identificación de la aplicación");

		return eventContext.getMessage();
	}	

}
