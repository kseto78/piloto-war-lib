package es.minhap.misim.components;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;

import misim.bus.common.bean.SoapPayload;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import es.minhap.common.entity.TextComparator;
import es.minhap.common.properties.PropertiesServices;
import es.minhap.misim.bus.model.ParametrosProveedor;
import es.minhap.misim.bus.model.Proveedor;
import es.minhap.misim.bus.model.exception.ModelException;
import es.minhap.misim.bus.model.servicios.ParametrosProveedorManager;
import es.minhap.misim.bus.model.servicios.ProveedorManager;
import es.minhap.misim.bus.query.ParametrosProveedorQuery;
import es.minhap.misim.bus.query.ProductoQuery;
import es.minhap.misim.bus.query.ProveedorQuery;
import es.minhap.misim.transformers.peticionesrest.Parameter;
import es.minhap.misim.transformers.peticionesrest.Parameters;

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
	
	@Resource(name="parametrosProveedorManager")
	private ParametrosProveedorManager parametrosProveedorManager; 
	
	@Resource(name = "reloadableResourceBundleMessageSource")
	ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;

	private String destinatario;
	private String header;
	private String cuerpo;
	private String uim;
	private String encoding;
	private String mensajeId;
	private String usuario;
	private String password;
	private static final String ENCODING = "ISO-8859-1";
	
	@Override
	public Object onCall(final MuleEventContext eventContext) throws Exception {

		LOG.debug("Empezando la identificación del proveedor.");
		
		// Se recupera el documento de la petición
		Document docOriginal = SoapPayload.class.cast(eventContext.getMessage().getPayload()).getSoapMessage();
			
		try{
			
			Long idProducto = Long.class.cast(eventContext.getMessage().getOutboundProperty("idProducto"));
			
			String proveedor = null;
			
			NodeList proveedor1 = docOriginal.getElementsByTagNameNS(
					"http://misim.redsara.es/misim-bus-webapp/peticion",
					"Proveedor");
			
			PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
			
			Long comunicacionHTTP =Long.parseLong(ps.getMessage("comunicacion.http", null, "2", null)); 
			Long comunicacionRCP = Long.parseLong(ps.getMessage("comunicacion.rcp", null, "5", null));
			
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
				if(listaProveedores.get(0).getEncoding()!=null){
					eventContext.getMessage().setOutboundProperty("encoding", listaProveedores.get(0).getEncoding());
				}
				if(listaProveedores.get(0).getCompany()!=null){
					eventContext.getMessage().setOutboundProperty("company", listaProveedores.get(0).getCompany());
				}
				if(listaProveedores.get(0).getBasicAutentication()!=null){
					eventContext.getMessage().setOutboundProperty("basicAutentication", listaProveedores.get(0).getBasicAutentication());
					eventContext.getMessage().setOutboundProperty("usuarioAutentication", listaProveedores.get(0).getUserAutentication());
					eventContext.getMessage().setOutboundProperty("passwordAutentication", listaProveedores.get(0).getPassAutentication());
					
				}else{
					eventContext.getMessage().setOutboundProperty("basicAutentication", "N");
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
					
					if(listaProveedores.get(0).getEndpoint().getComunicacion()!=null 
							&& listaProveedores.get(0).getEndpoint().getComunicacion().getIdComunicacion()!=null 
							&& (listaProveedores.get(0).getEndpoint().getComunicacion().getIdComunicacion().equals(comunicacionHTTP) 
									|| listaProveedores.get(0).getEndpoint().getComunicacion().getIdComunicacion().equals(comunicacionRCP))){
						cargarParametros(eventContext, docOriginal);
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
			LOG.error("Identificación: Error de sistema al identificar el proveedor", e);
			throw new ModelException(e.getMensaje(), e.getCodigo());
			
		}catch (Exception e){
			
			LOG.error("Identificación: Error de sistema al identificar el proveedor", e);
			throw new ModelException("Error de sistema al identificar el proveedor", 502);
		}
		
		LOG.debug("Fin de la identificación del proveedor.");

		return eventContext.getMessage();
	}

	private void cargarParametros(MuleEventContext eventContext, Document docOriginal) throws Exception {
		ParametrosProveedorQuery query = new ParametrosProveedorQuery();
		query.setIdProveedor((Long)eventContext.getMessage().getOutboundProperty("idProveedor"));
		List<ParametrosProveedor> pp = parametrosProveedorManager.getParametrosProveedor(query);
		encoding = eventContext.getMessage().getOutboundProperty("encoding");
				
		NodeList smsDestinatario = docOriginal.getElementsByTagName("SMS_DESTINATARIO");
		NodeList smsTexto = docOriginal.getElementsByTagName("SMS_TEXTO");
		NodeList smsHeader = docOriginal.getElementsByTagName("SMS_HEADER");
		NodeList smsUim = docOriginal.getElementsByTagName("SMS_UIM");
		NodeList smsId = docOriginal.getElementsByTagName("SMS_ID");
		NodeList smsUser = docOriginal.getElementsByTagName("SMS_USUARIO");
		NodeList smsPass = docOriginal.getElementsByTagName("SMS_PASSWORD");
		
		
		destinatario = (null != smsDestinatario  && null != smsDestinatario.item(0)) ? smsDestinatario.item(0).getTextContent() : null;
		
		getCuerpo(smsTexto);
		
		header = (null != smsHeader  && null != smsHeader.item(0))? smsHeader.item(0).getTextContent() : null;
		uim = (null != smsUim && null != smsUim.item(0))? smsUim.item(0).getTextContent() : null;
		mensajeId = (null != smsId && null != smsId.item(0))? smsId.item(0).getTextContent() : null;
		usuario = (null != smsUim && null != smsUser.item(0))? smsUser.item(0).getTextContent() : null;
		password = (null != smsUim && null != smsPass.item(0))? smsPass.item(0).getTextContent() : null;
		
		Parameters parametros = getParametros(pp);
		
		String mensaje = parametros.toXMLSMS(parametros);
				
		eventContext.getMessage().setOutboundProperty("xmlPeticion", mensaje);
		
	}

	private void getCuerpo(NodeList smsTexto) throws UnsupportedEncodingException {
		cuerpo =  (null != smsTexto  && null != smsTexto.item(0))? smsTexto.item(0).getTextContent() : null;
		encodeCuerpo();
	}

	private void encodeCuerpo() throws UnsupportedEncodingException {
		if (null != cuerpo){
			if (null == encoding){
				cuerpo = URLEncoder.encode(cuerpo,ENCODING);
				encoding = ENCODING;
			}else{
				cuerpo = URLEncoder.encode(cuerpo,encoding);
			}
		}
	}	
	
	private Parameters getParametros(List<ParametrosProveedor> parametrosProveedor) throws IllegalAccessException,  NoSuchFieldException {
		Parameters parametros = new Parameters();
		for (ParametrosProveedor pp : parametrosProveedor) {

			if (null != pp.getValor()){
				Parameter param = new Parameter();
				param.setIdentificadorSolicitante(pp.getParametro());
				param.setValue(pp.getValor());
				parametros.getParameters().add(param);
			}else{
				Parameter param = new Parameter();
				param.setIdentificadorSolicitante(pp.getParametro());
				Field field = this.getClass().getDeclaredField(pp.getVariable());
				field.setAccessible(true);
				String fieldValue = (String) field.get(this);
				param.setValue(fieldValue);
				parametros.getParameters().add(param);
			}
		}
		return parametros;
	}
	
}
