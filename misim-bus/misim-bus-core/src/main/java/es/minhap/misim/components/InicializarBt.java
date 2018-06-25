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
import es.minhap.misim.bus.model.Aplicacion;
import es.minhap.misim.bus.model.Producto;
import es.minhap.misim.bus.model.Proveedor;
import es.minhap.misim.bus.model.exception.ModelException;
import es.minhap.misim.bus.model.servicios.AplicacionManager;
import es.minhap.misim.bus.model.servicios.ProductoManager;
import es.minhap.misim.bus.model.servicios.ProveedorManager;
import es.minhap.misim.bus.query.AplicacionQuery;
import es.minhap.misim.bus.query.ProductoQuery;
import es.minhap.misim.bus.query.ProveedorQuery;

public class InicializarBt implements Callable {
	
	private static final Logger LOG = LoggerFactory.getLogger(InicializarBt.class);
	
	private static final String PRODUCTO_RECIBIR = "RECIBIR_SMS";
	private static final String PRODUCTO_NOTIFICAR = "NOTIFICAR_SMS";
	private static final String PROVEEDOR = "BT";
	private static final String APLICACION = "BT";
	
	@Resource
	private ProductoManager productoManager;
	
	@Resource
	private ProveedorManager proveedorManager;
	
	@Resource
	private AplicacionManager aplicacionManager;

	@Override
	public Object onCall(final MuleEventContext eventContext) throws ModelException {
		
		LOG.debug("Inicio de la inicialización de variables");
		
		eventContext.getMessage().setOutboundProperty("xmlPeticion", "");
		eventContext.getMessage().setOutboundProperty("xmlRespuesta", "");
		
		try {
		
			// Se recupera el documento de la petición
			final Document docOriginal = SoapPayload.class.cast(eventContext.getMessage().getPayload()).getSoapMessage();
			
			AplicacionQuery arg0 = new AplicacionQuery();
			arg0.setNombre(APLICACION);
			arg0.setNombreComparator(TextComparator.EQUALS);
			
			List<Aplicacion> listaAplicaciones = aplicacionManager.getAplicacion(arg0);
			
			if(listaAplicaciones==null || listaAplicaciones.isEmpty()){
				
				throw new ModelException("No existe ninguna aplicación para el usuario de la petición", 315);
				
			}else if (listaAplicaciones.size()>1){
				
				throw new ModelException("Existe más de una aplicación para el usuario de la petición", 312);
			}else{
				eventContext.getMessage().setOutboundProperty("idAplicacion", listaAplicaciones.get(0).getIdAplicacion());
			}
			
			String soapAction=SoapPayload.class.cast(eventContext.getMessage().getPayload(SoapPayload.class)).getSoapAction();
			
			ProductoQuery arg1 = new ProductoQuery();
			
			if(("recepcionSMS").equals(soapAction)){
				arg1.setCodigo(PRODUCTO_RECIBIR);
			}else if (("notificacionSMS").equals(soapAction)){
				arg1.setCodigo(PRODUCTO_NOTIFICAR);
			}else{
				arg1.setCodigo("PRODUCTO_ERRONEO");
			}
			
			arg1.setCodigoComparator(TextComparator.EQUALS);
			
			List<Producto> listaProductos = productoManager.getProducto(arg1);
			
			if(listaProductos==null || listaProductos.isEmpty()){
				
				throw new ModelException("No existe ningún producto para el indicado en la petición", 315);
				
			}else if (listaProductos.size()>1){
				
				throw new ModelException("Existe más de un producto para el indicado en la petición", 312);
				
			}else{
				
				eventContext.getMessage().setOutboundProperty("idProducto", listaProductos.get(0).getIdProducto());
				eventContext.getMessage().setOutboundProperty("codigo", listaProductos.get(0).getCodigo());
			}
			
			ProveedorQuery arg2 = new ProveedorQuery();
			arg2.setNombre(PROVEEDOR);
			arg2.setNombreComparator(TextComparator.EQUALS);
			
			ProductoQuery producto = new ProductoQuery();
			producto.setIdProducto(listaProductos.get(0).getIdProducto());
			arg2.setProducto(producto);
			
			List<Proveedor> listaProveedores = proveedorManager.getProveedor(arg2);
			
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
				
				String idMensaje = null;
				
				NodeList nodeMensaje = docOriginal.getElementsByTagNameNS(
						"http://i3g.com/schema/SiroccoPushStatusService",
						"guid");
				
				
				if (nodeMensaje.item(0)!=null){
					
					idMensaje = nodeMensaje.item(0).getTextContent();
					
				}else{
					
					
					NodeList nodeMensaje2 = docOriginal.getElementsByTagNameNS(
							"http://i3g.com/schema/SiroccoPushService",
							"guid");
					
					if (nodeMensaje2.item(0)!=null){
						
						idMensaje = nodeMensaje2.item(0).getTextContent();
					
					}else{
						
						NodeList nodeMensaje3 = docOriginal.getElementsByTagName("guid");
						idMensaje = nodeMensaje3.item(0).getTextContent();
					}
				}
				
				eventContext.getMessage().setOutboundProperty("idMensajeOperador", idMensaje);
			}
			
		}catch (ModelException e){
		
			throw new ModelException(e.getMensaje(), e.getCodigo());
			
		}catch (Exception e){
			
			LOG.error("Identificación: Error de sistema al inicializar Bt", e);
			throw new ModelException("Error de sistema al inicializar Bt", 502);
		}

		LOG.debug("Fin de la inicialización de variables");
		
		return eventContext.getMessage();
	}

}
