package es.mpr.plataformamensajeria.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import es.minhap.misim.bus.model.Aplicacion;
import es.minhap.misim.bus.model.Comunicacion;
import es.minhap.misim.bus.model.Endpoint;
import es.minhap.misim.bus.model.Producto;
import es.minhap.misim.bus.model.Proveedor;
import es.minhap.misim.bus.model.Transformacion;
import es.mpr.plataformamensajeria.beans.AplicacionMisimBean;
import es.mpr.plataformamensajeria.beans.ComunicacionBean;
import es.mpr.plataformamensajeria.beans.EndpointBean;
import es.mpr.plataformamensajeria.beans.ProductoBean;
import es.mpr.plataformamensajeria.beans.ProveedorMisimBean;
import es.mpr.plataformamensajeria.beans.TransformacionBean;

public class Utiles{
	
	private static final Logger LOGGER = Logger.getLogger(Utiles.class);
	
	private static final String ORDEN_LETRAS = "TRWAGMYFPDXBNJZSQVHLCKE";
	
	private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	/**
	 * Codificacion Base64
	 * @param param
	 * @return
	 */
	public static String encode(byte[] param){
		byte[] encoded = Base64.encodeBase64(param);
		return new String(encoded);
	}
	
	/**
	 * Decodificacion Base64
	 * @param param
	 * @return
	 */
	public static byte[] decode(String param){
		byte[] decoded = Base64.decodeBase64(param.getBytes());
		return decoded;
	}
	
	public static String FiletoBase64String (File originalFile){
        String iconoBase64 = null;
        try {
            FileInputStream fileInputStreamReader = new FileInputStream(originalFile);
            byte[] bytes = new byte[(int)originalFile.length()];
            fileInputStreamReader.read(bytes);
            iconoBase64 = new String(Base64.encodeBase64(bytes));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return iconoBase64;
	}
	
	public static ProductoBean transformacionProducto (Producto producto){
		
		ProductoBean productoBean = new ProductoBean();
		productoBean.setIdProducto(producto.getIdProducto());
		productoBean.setNombre(producto.getNombre());
		productoBean.setCodigo(producto.getCodigo());
		
		return productoBean;
	}
	
	public static EndpointBean transformacionEndpoint (Endpoint endpoint){
		
		EndpointBean endpointBean = new EndpointBean();
		endpointBean.setIdEndpoint(endpoint.getIdEndpoint());
		endpointBean.setNombre(endpoint.getNombre());
		endpointBean.setUrlOperation(endpoint.getOperation());
		endpointBean.setPortName(endpoint.getPortName());
		endpointBean.setServiceName(endpoint.getServiceName());
		endpointBean.setUrlTargetName(endpoint.getTargetName());
		endpointBean.setTimeout(endpoint.getTimeout());
		endpointBean.setUrlEndpoint(endpoint.getUrlEndpoint());
		endpointBean.setFechaCreacion(endpoint.getFechaCreacion());
		endpointBean.setFechaActualizacion(endpoint.getFechaActualizacion());
		
		ComunicacionBean comunicacionBean = transformacionComunicacion(endpoint.getComunicacion());
		endpointBean.setComunicacion(comunicacionBean);
		
		return endpointBean;
	}
	
	public static AplicacionMisimBean transformacionAplicacionMisim (Aplicacion aplicacion){
		
		AplicacionMisimBean aplicacionMisimBean = new AplicacionMisimBean();
		aplicacionMisimBean.setIdAplicacion(aplicacion.getIdAplicacion());
		aplicacionMisimBean.setNombre(aplicacion.getNombre());
		aplicacionMisimBean.setPassword(aplicacion.getPassword());
		aplicacionMisimBean.setUsuario(aplicacion.getUsuario());
		
		return aplicacionMisimBean;
	}
	
	public static ProveedorMisimBean transformacionProveedorMisim (Proveedor proveedor){
		
		ProveedorMisimBean proveedorMisimBean = new ProveedorMisimBean();
		proveedorMisimBean.setIdProveedor(proveedor.getIdProveedor());
		proveedorMisimBean.setCertificado(proveedor.getCertificado());
		proveedorMisimBean.setCertificadoPass(proveedor.getCertificadoPass());
		proveedorMisimBean.setCifrado(proveedor.getCifrado());
		proveedorMisimBean.setCompany(proveedor.getCompany());
		
		/*DUDA proveedores*/
		proveedorMisimBean.setEncoding(proveedor.getEncoding());
		proveedorMisimBean.setBasicAutentication(proveedor.getBasicAutentication());
		proveedorMisimBean.setMethod(proveedor.getMethod());
		proveedorMisimBean.setMediaType(proveedor.getMediaType());
		proveedorMisimBean.setAnadirUim(proveedor.getAnadirUim());
		proveedorMisimBean.setUserAutentication(proveedor.getUserAutentication());
		proveedorMisimBean.setPassAutentication(proveedor.getPassAutentication());	
		
		proveedorMisimBean.setEsquemaCifrado(proveedor.getEsquemaCifrado());
		proveedorMisimBean.setFirma(proveedor.getFirma());
		proveedorMisimBean.setNodoCifrado(proveedor.getNodoCifrado());
		proveedorMisimBean.setNombre(proveedor.getNombre());
		proveedorMisimBean.setPassword(proveedor.getPassword());
		proveedorMisimBean.setTipoFirma(proveedor.getTipoFirma());
		proveedorMisimBean.setType(proveedor.getType());
		proveedorMisimBean.setUsuario(proveedor.getUsuario());

		ProductoBean productoBean = transformacionProducto(proveedor.getProducto());
		proveedorMisimBean.setProducto(productoBean);
		
		EndpointBean endpointBean = transformacionEndpoint(proveedor.getEndpoint());
		proveedorMisimBean.setEndpoint(endpointBean);
		
		TransformacionBean transformacionBean = transformacionTransformacion(proveedor.getTransformacion());
		proveedorMisimBean.setTransformacion(transformacionBean);
		
		return proveedorMisimBean;
	}
	
	public static TransformacionBean transformacionTransformacion (Transformacion transformacion){
		
		TransformacionBean transformacionBean = new TransformacionBean();
		transformacionBean.setIdTransformacion(transformacion.getIdTransformacion());
		transformacionBean.setNombre(transformacion.getNombre());
		transformacionBean.setXslPeticion(transformacion.getXslPeticion());
		transformacionBean.setXslRespuesta(transformacion.getXslRespuesta());
		transformacionBean.setXslFault(transformacion.getXslFault());
		transformacionBean.setFechaCreacion(transformacion.getFechaCreacion());
		transformacionBean.setFechaActualizacion(transformacion.getFechaActualizacion());
		
		return transformacionBean;
	}
	
	public static ComunicacionBean transformacionComunicacion (Comunicacion comunicacion){
		
		ComunicacionBean comunicacionBean = new ComunicacionBean();
		comunicacionBean.setIdComunicacion(comunicacion.getIdComunicacion());
		comunicacionBean.setNombre(comunicacion.getNombre());
		
		return comunicacionBean;
	}
	
	

}