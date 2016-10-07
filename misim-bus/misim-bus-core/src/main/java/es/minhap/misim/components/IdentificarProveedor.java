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

import es.minhap.common.entity.TextComparator;
import es.minhap.misim.bus.model.Proveedor;
import es.minhap.misim.bus.model.exception.ModelException;
import es.minhap.misim.bus.model.servicios.ProveedorManager;
import es.minhap.misim.bus.query.ProductoQuery;
import es.minhap.misim.bus.query.ProveedorQuery;

/**
 * Component to call the Identification Service SRV-ID- 01
 * 
 * @author ludarcos
 * 
 */
public class IdentificarProveedor implements Callable {

	private static final Logger LOG = LoggerFactory.getLogger(IdentificarProveedor.class);
	
	@Resource
	private ProveedorManager proveedorManager;
	
	@Override
	public Object onCall(final MuleEventContext eventContext) throws Exception {

		LOG.debug("Empezando la identificación del proveedor.");
		
		// Se recupera el documento de la petición
		final Document docOriginal = SoapPayload.class.cast(eventContext.getMessage().getPayload()).getSoapMessage();
			
		try{
			
			Long idProducto = Long.class.cast(eventContext.getMessage().getOutboundProperty("idProducto"));
			
			String proveedor = null;
			
			NodeList proveedor1 = docOriginal.getElementsByTagNameNS(
					"http://misim.redsara.es/misim-bus-webapp/peticion",
					"Proveedor");
			
			if (proveedor1.item(0)!=null){
				
				proveedor = proveedor1.item(0).getTextContent();
			}else{
				
				NodeList proveedor2 = docOriginal.getElementsByTagName("Proveedor");
				proveedor = proveedor2.item(0).getTextContent();
			}
			
			ProveedorQuery arg0 = new ProveedorQuery();
			arg0.setNombre(proveedor);
			arg0.setNombreComparator(TextComparator.EQUALS);
			
			ProductoQuery producto = new ProductoQuery();
			producto.setIdProducto(idProducto);
			arg0.setProducto(producto);
			
			List<Proveedor> listaProveedores = proveedorManager.getProveedor(arg0);
			
			if(listaProveedores==null || listaProveedores.isEmpty()){
				
				throw new ModelException("No existe ningún proveedor para el indicado en la petición", 315);
				
			}else if (listaProveedores.size()>1){
				
				throw new ModelException("Existe más de un proveedor para el indicado en la petición", 312);
			}else{
				
				
				//INFORMACION PROVEEDOR
				eventContext.getMessage().setOutboundProperty("idProveedor", listaProveedores.get(0).getIdProveedor());
				
				if(listaProveedores.get(0).getUsuario()!=null){
					eventContext.getMessage().setOutboundProperty("usuario", listaProveedores.get(0).getUsuario());
				}
				if(listaProveedores.get(0).getPassword()!=null){
					eventContext.getMessage().setOutboundProperty("password", listaProveedores.get(0).getPassword());
				}
				if(listaProveedores.get(0).getCompany()!=null){
					eventContext.getMessage().setOutboundProperty("company", listaProveedores.get(0).getCompany());
				}
				if(listaProveedores.get(0).getType()!=null){
					eventContext.getMessage().setOutboundProperty("type", listaProveedores.get(0).getType());
				}
				if(listaProveedores.get(0).getCertificado()!=null){
					eventContext.getMessage().setOutboundProperty("certificado", listaProveedores.get(0).getCertificado());
				}
				if(listaProveedores.get(0).getCertificadoPass()!=null){
					eventContext.getMessage().setOutboundProperty("certificadoPass", listaProveedores.get(0).getCertificadoPass());
				}
				if(listaProveedores.get(0).getFirma()!=null){
					eventContext.getMessage().setOutboundProperty("firma", listaProveedores.get(0).getFirma());
				}else{
					eventContext.getMessage().setOutboundProperty("firma", "N");
				}
				if(listaProveedores.get(0).getTipoFirma()!=null){
					eventContext.getMessage().setOutboundProperty("tipoFirma", listaProveedores.get(0).getTipoFirma());
				}
				if(listaProveedores.get(0).getCifrado()!=null){
					eventContext.getMessage().setOutboundProperty("cifrado", listaProveedores.get(0).getCifrado());
				}else{
					eventContext.getMessage().setOutboundProperty("cifrado", "N");
				}
				if(listaProveedores.get(0).getNodoCifrado()!=null){
					eventContext.getMessage().setOutboundProperty("nodoCifrado", listaProveedores.get(0).getNodoCifrado());
				}
				if(listaProveedores.get(0).getEsquemaCifrado()!=null){
					eventContext.getMessage().setOutboundProperty("esquemaCifrado", listaProveedores.get(0).getEsquemaCifrado());
				}
			
				//ENDPOINT
				if(listaProveedores.get(0).getEndpoint()!=null){
					
					if(listaProveedores.get(0).getEndpoint().getUrlEndpoint()!=null){
						eventContext.getMessage().setOutboundProperty("endpointUrl", listaProveedores.get(0).getEndpoint().getUrlEndpoint());
					}
					if(listaProveedores.get(0).getEndpoint().getServiceName()!=null){
						eventContext.getMessage().setOutboundProperty("serviceName", listaProveedores.get(0).getEndpoint().getServiceName());
					}
					if(listaProveedores.get(0).getEndpoint().getPortName()!=null){
						eventContext.getMessage().setOutboundProperty("portName", listaProveedores.get(0).getEndpoint().getPortName());
					}
					if(listaProveedores.get(0).getEndpoint().getTargetName()!=null){
						eventContext.getMessage().setOutboundProperty("targetName", listaProveedores.get(0).getEndpoint().getTargetName());
					}
					if(listaProveedores.get(0).getEndpoint().getOperation()!=null){
						eventContext.getMessage().setOutboundProperty("operation", listaProveedores.get(0).getEndpoint().getOperation());
					}
					if(listaProveedores.get(0).getEndpoint().getTimeout()!=null){
						eventContext.getMessage().setOutboundProperty("timeout", listaProveedores.get(0).getEndpoint().getTimeout());
					}
					if(listaProveedores.get(0).getEndpoint().getComunicacion()!=null && listaProveedores.get(0).getEndpoint().getComunicacion().getIdComunicacion()!=null){
						eventContext.getMessage().setOutboundProperty("idComunicacion", listaProveedores.get(0).getEndpoint().getComunicacion().getIdComunicacion());
					}
				}	
						
				//TRANSFORMACION
				if(listaProveedores.get(0).getTransformacion()!=null){
					
					if(listaProveedores.get(0).getTransformacion().getXslPeticion()!=null){
						eventContext.getMessage().setOutboundProperty("transformacionPeticion", "S");
						eventContext.getMessage().setOutboundProperty("xslPeticion", listaProveedores.get(0).getTransformacion().getXslPeticion());
					}else{
						eventContext.getMessage().setOutboundProperty("transformacionPeticion", "N");
					}
					if(listaProveedores.get(0).getTransformacion().getXslRespuesta()!=null){
						eventContext.getMessage().setOutboundProperty("transformacionRespuesta", "S");
						eventContext.getMessage().setOutboundProperty("xslRespuesta", listaProveedores.get(0).getTransformacion().getXslRespuesta());
					}else{
						eventContext.getMessage().setOutboundProperty("transformacionRespuesta", "N");
					}
					if(listaProveedores.get(0).getTransformacion().getXslFault()!=null){
						eventContext.getMessage().setOutboundProperty("xslSoapFault", listaProveedores.get(0).getTransformacion().getXslFault());
					}
				}else{
					eventContext.getMessage().setOutboundProperty("transformacionPeticion", "N");
					eventContext.getMessage().setOutboundProperty("transformacionRespuesta", "N");
				}
				
			}	
				
		}catch (ModelException e){
			
			throw new ModelException(e.getMensaje(), e.getCodigo());
			
		}catch (Exception e){
			
			LOG.error("Identificación: Error de sistema al identificar el proveedor", e);
			throw new ModelException("Error de sistema al identificar el proveedor", 502);
		}
		
		LOG.debug("Fin de la identificación del proveedor.");

		return eventContext.getMessage();
	}	

}
