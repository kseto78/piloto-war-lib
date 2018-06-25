package es.minhap.misim.components;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.Properties;

import javax.annotation.Resource;

import misim.bus.common.bean.SoapPayload;
import misim.bus.common.util.XMLUtils;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.w3c.dom.Document;

import es.minhap.common.properties.PropertiesServices;
import es.minhap.misim.bus.model.Aplicacion;
import es.minhap.misim.bus.model.Auditoria;
import es.minhap.misim.bus.model.Estado;
import es.minhap.misim.bus.model.Peticion;
import es.minhap.misim.bus.model.Producto;
import es.minhap.misim.bus.model.Proveedor;
import es.minhap.misim.bus.model.exception.ModelException;
import es.minhap.misim.bus.model.seguridad.CifradoService;
import es.minhap.misim.bus.model.servicios.AplicacionManager;
import es.minhap.misim.bus.model.servicios.AuditoriaManager;
import es.minhap.misim.bus.model.servicios.EstadoManager;
import es.minhap.misim.bus.model.servicios.PeticionManager;
import es.minhap.misim.bus.model.servicios.ProductoManager;
import es.minhap.misim.bus.model.servicios.ProveedorManager;


/**
 * Component to call the Audit Service SRV-ADT-01
 * 
 * @author ludarcos
 * 
 */
public class Auditar implements Callable {

	private static final Logger LOG = LoggerFactory.getLogger(Auditar.class);
	
	@Autowired
	CifradoService cifradoService;
	
	@Resource(name = "cifradoPrivadoProperties")
	Properties props;
	
	@Resource
	private AuditoriaManager auditoriaManager;
	
	@Resource
	private PeticionManager peticionManager;
	
	@Resource
	private AplicacionManager aplicacionManager;
	
	@Resource
	private ProductoManager productoManager;
	
	@Resource
	private ProveedorManager proveedorManager;
	
	@Resource
	private EstadoManager estadoManager;
	
	@Resource(name = "reloadableResourceBundleMessageSource")
	ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;
	

	@Override
	public Object onCall(final MuleEventContext eventContext) throws Exception {

		LOG.debug("Inicio auditoría");
		
		this.inicializarParametros(eventContext);
		final Document docOriginal = SoapPayload.class.cast(eventContext.getMessage().getPayload()).getSoapMessage();
		
		try{
			
			PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
			
			Long idAplicacion = Long.class.cast(eventContext.getMessage().getOutboundProperty("idAplicacion"));
			Long idProducto = Long.class.cast(eventContext.getMessage().getOutboundProperty("idProducto"));
			Long idProovedor = Long.class.cast(eventContext.getMessage().getOutboundProperty("idProveedor"));	
			Long idComunicacion = Long.class.cast(eventContext.getMessage().getOutboundProperty("idComunicacion"));
			
			Long idMensaje=null;
			Long idLote = null;
			
			if (eventContext.getMessage().getOutboundProperty("idMensaje")!=null && !("").equals(eventContext.getMessage().getOutboundProperty("idMensaje"))){
				idMensaje= Long.valueOf(String.valueOf(eventContext.getMessage().getOutboundProperty("idMensaje")));
			}
			
			if (eventContext.getMessage().getOutboundProperty("idLote")!=null && !("").equals(eventContext.getMessage().getOutboundProperty("idLote"))){
				idLote= Long.valueOf(String.valueOf(eventContext.getMessage().getOutboundProperty("idLote")));
			}
			
			Estado estado = new Estado();
			estado.setIdEstado(Long.parseLong(ps.getMessage("estado.enCurso", null, null, null)));
			
			Aplicacion aplicacion = new Aplicacion();
			aplicacion.setIdAplicacion(idAplicacion);
			
			Producto producto = new Producto();
			producto.setIdProducto(idProducto);
			
			Proveedor proveedor = new Proveedor();
			proveedor.setIdProveedor(idProovedor);
			
			String xmlPeticion;
			
			if (idComunicacion == 2){
				Encriptar en = new Encriptar();
				xmlPeticion = en.encriptarParametersHTTP(cifradoService, (String)eventContext.getMessage().getOutboundProperty("xmlPeticion"), props);
				if (null == xmlPeticion){
					xmlPeticion = XMLUtils.dom2xml(docOriginal);
				}
			}else{
				xmlPeticion = XMLUtils.dom2xml(docOriginal);
			}
			
			Peticion peticion = new Peticion();
			peticion.setEstado(estado);
			peticion.setAplicacion(aplicacion);
			peticion.setProducto(producto);
			peticion.setProveedor(proveedor);
			peticion.setMensajePeticion(xmlPeticion);
			
			if (idMensaje!=null){
				peticion.setIdMensaje(idMensaje);
			}
			
			
			
			Long idPeticion = peticionManager.insertPeticion(peticion);
			
			if(idPeticion==null || idPeticion<=0){
				
				LOG.error("Auditar: Error al insertar la petición");
				throw new ModelException("Error al insertar la petición", 202);
			}
				
			peticion.setIdPeticion(idPeticion);
			
			Auditoria auditoria = new Auditoria();
			auditoria.setAplicacion(aplicacion);
			auditoria.setProducto(producto);
			auditoria.setProveedor(proveedor);
			auditoria.setPeticion(peticion);
			auditoria.setFechaCreacion(new Date());
			
			if (idMensaje!=null){
				auditoria.setIdMensaje(idMensaje);
			}
			
			if (idLote!=null){
				auditoria.setIdLote(idLote);
			}
			
			Long idAuditoria = auditoriaManager.insertAuditoria(auditoria);
			
			if(idAuditoria==null || idAuditoria<=0){
				LOG.error("Auditar: Error de Base de Datos");
				throw new ModelException("Error de Base de Datos", 501);
			}
		
			eventContext.getMessage().setOutboundProperty("idAuditoria", String.valueOf(idAuditoria));
			eventContext.getMessage().setOutboundProperty("idPeticion", String.valueOf(idPeticion));
			
			String xmlPeticionOriginal =eventContext.getMessage().getOutboundProperty("xmlPeticion"); 
			Document documentOriginal = XMLUtils.xml2doc(xmlPeticionOriginal, Charset.forName("UTF-8"));
			
			SoapPayload.class.cast(eventContext.getMessage().getPayload()).setSoapMessage(documentOriginal);
		
		}catch(ModelException e){
			//Lanzar error
			throw new ModelException(e.getMensaje(), e.getCodigo());
		}catch(Exception e){
			//Lanzar error
			LOG.error("Auditar: Error de sistema Identificar Auditar", e);
			throw new ModelException("Error de sistema Identificar Auditar", 502);
		}

		LOG.debug("Fin aditoria.");
		return eventContext.getMessage();
	}

	private void inicializarParametros(MuleEventContext eventContext){
			
		eventContext.getMessage().setOutboundProperty("idAuditoria", "");
		eventContext.getMessage().setOutboundProperty("idPeticion", "");
	}

}
