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
import es.minhap.misim.bus.model.Producto;
import es.minhap.misim.bus.model.exception.ModelException;
import es.minhap.misim.bus.model.servicios.ProductoManager;
import es.minhap.misim.bus.query.ProductoQuery;

/**
 * Component to call the Identification Service SRV-ID- 01
 * 
 * @author ludarcos
 * 
 */
public class IdentificarProducto implements Callable {

	private static final Logger LOG = LoggerFactory.getLogger(IdentificarProducto.class);
	
	@Resource
	private ProductoManager productoManager;
	
	@Override
	public Object onCall(final MuleEventContext eventContext) throws Exception {

		LOG.debug("Inicio de la identificación del producto");
		
		// Se recupera el documento de la petición
		final Document docOriginal = SoapPayload.class.cast(eventContext.getMessage().getPayload()).getSoapMessage();
		
		try{
			
			String producto=null;
			
			NodeList producto1 = docOriginal.getElementsByTagNameNS(
					"http://misim.redsara.es/misim-bus-webapp/peticion",
					"Producto");
			
			if (producto1.item(0)!=null){
				
				producto = producto1.item(0).getTextContent();
			}else{
				
				NodeList producto2 = docOriginal.getElementsByTagName("Producto");
				producto = producto2.item(0).getTextContent();
			}
			
			ProductoQuery arg0 = new ProductoQuery();
			arg0.setCodigo(producto);
			arg0.setCodigoComparator(TextComparator.EQUALS);
			
			List<Producto> listaProductos = productoManager.getProducto(arg0);
			
			if(listaProductos==null || listaProductos.isEmpty()){
				
				throw new ModelException("No existe ningún producto para el indicado en la petición", 315);
				
			}else if (listaProductos.size()>1){
				
				throw new ModelException("Existe más de un producto para el indicado en la petición", 312);
			}else{
				
				eventContext.getMessage().setOutboundProperty("idProducto", listaProductos.get(0).getIdProducto());
				eventContext.getMessage().setOutboundProperty("codigo", listaProductos.get(0).getCodigo());
			}
					
		}catch (ModelException e){
			
			throw new ModelException(e.getMensaje(), e.getCodigo());
			
		}catch (Exception e){
			
			LOG.error("Identificación: Error de sistema al identificar el producto", e);
			throw new ModelException("Error de sistema al identificar el producto", 502);
		}
		
		LOG.debug("Fin de la identificación del producto");

		return eventContext.getMessage();
	}	

}
