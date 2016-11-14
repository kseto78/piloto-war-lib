package es.minhap.misim.bus.webapp.restserviceprovider;

import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import misim.bus.common.bean.SoapPayload;
import misim.bus.common.util.XMLUtils;

import org.mule.api.MuleContext;
import org.mule.api.MuleException;
import org.mule.api.MuleMessage;
import org.mule.api.client.MuleClient;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import sun.misc.BASE64Decoder;

import com.google.gson.Gson;

import es.minhap.misim.bus.core.pojo.PeticionPayload;
import es.minhap.plataformamensajeria.iop.beans.respuestasServiciosMoviles.ResponseNotificacionPushStatusType;

@Service("gestionNotificacionesPush")
public class GestionNotificacionesPushImpl implements GestionNotificacionesPush  {
	
	public static String ERROR_AUTENTIFICACION = "Error en Autentificacion - La clave no se corresponde con ninguna aplicacion";
	public static String ERROR_REQUESTTIMEOUT = "Error en Peticion - La peticion se ha caducado";
	public static String ERROR_PARAMETROS = "Error en Parametros de entrada";
	
	public static final String RECEPT_QUEUE = "vm://gestion-notificaciones-push";
	public static final String SOAP_ACTION = "notificacionEstado";

	private StringTokenizer tokenizer = null;
	private String username = null;
	private String password = null; 

	
	@Context
	private HttpServletRequest request;

	@Context
	private ServletContext servletContext;
	
	public String notificacionEstado(String notificacionId, String status, String idUsuario) {

		RespuestaNotificacionesPush respuesta = new RespuestaNotificacionesPush();
		  String decoded;
		  try{
		   // Get the Authorisation Header from Request
		   String header = request.getHeader("authorization");
		    
		   // Header is in the format "Basic 3nc0dedDat4"
		   // We need to extract data before decoding it back to original string
		   String data = header.substring(header.indexOf(" ") +1 );
		    
		   // Decode the data back to original string
		   byte[] bytes = new BASE64Decoder().decodeBuffer(data);
		   decoded = new String(bytes);
		    
		   System.out.println(decoded);
			if (null!=decoded){
				  tokenizer = new StringTokenizer(decoded, ":");
				  username = tokenizer.nextToken();
				  password = tokenizer.nextToken(); 
			}
		
		if((null!=username && !("").equals(username))
				 && (null!=password && !("").equals(password))
				 && (null!=idUsuario && !("").equals(idUsuario))){
		
			try {
			
				PeticionNotificacionEstado peticion = new PeticionNotificacionEstado();
				peticion.setUsuario(username);
				peticion.setPassword(password);
				peticion.setNotificacionId(notificacionId);
				peticion.setStatus(status);
				peticion.setIdUsuario(idUsuario);
				
				DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		        Document document = docBuilder.newDocument();
		        
		        JAXBContext context = JAXBContext.newInstance(peticion.getClass());
		        Marshaller marshaller = context.createMarshaller();
		        marshaller.marshal(peticion, document);
		        
				String respuestaCompleta = XMLUtils.createSOAPFaultString((Node)document.getDocumentElement());

				try {
					
					MuleContext muleContext = (MuleContext) servletContext.getAttribute("mule.context");
					MuleClient muleClient = muleContext.getClient();
					SoapPayload<?> payload = new PeticionPayload();
					payload.setSoapAction(SOAP_ACTION);
					payload.setSoapMessage(XMLUtils.xml2doc(respuestaCompleta, Charset.forName("UTF-8")));
				
	
					final MuleMessage muleResponse = muleClient.send(RECEPT_QUEUE,payload, null, 10000);
					
					Document respuestaSOAP = muleResponse.getPayload(SoapPayload.class).getSoapMessage();
					
					NodeList nodoRespuesta = respuestaSOAP.getElementsByTagName("RespuestaNotificacionesPush");
					String xmlRespuesta = XMLUtils.nodeToString(nodoRespuesta.item(0));
					
					JAXBContext jaxbContext = JAXBContext.newInstance(RespuestaNotificacionesPush.class);
					Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
	
					StringReader reader = new StringReader(xmlRespuesta);
					respuesta = (RespuestaNotificacionesPush) unmarshaller.unmarshal(reader);
	
				} catch (final MuleException e) {
					throw new RuntimeException("Error in mule client", e);
				} catch (final Exception e) {
					throw new RuntimeException("Error in provider endpoint", e);
				}
		
			} catch (Exception e) {
			
				ResponseNotificacionPushStatusType response = new ResponseNotificacionPushStatusType();
				
				response.setStatusCode("0999");
				response.setStatusText("Error indeterminado");
				response.setDetails("Error indeterminado");
				
				respuesta.setStatus(response);
			}
			
		}else{
		
			ResponseNotificacionPushStatusType response = new ResponseNotificacionPushStatusType();
			
			response.setStatusCode("3001");
			response.setStatusText("La peticion no incluye todos los parametros obligatorios");
			response.setDetails("No se ha detectado alguno de los siguientes parametros obligatorios: Usuario, Password o idUsuario");
			
			respuesta.setStatus(response);
		}
	  }catch(Exception e){
		   e.printStackTrace();
		   ResponseNotificacionPushStatusType response = new ResponseNotificacionPushStatusType();
			response.setStatusCode("3002");
			response.setStatusText("Autentificiacion no valida o enviada.");
			response.setDetails("No se ha detectado alguno de los siguientes parametros obligatorios: Usuario, Password");
			respuesta.setStatus(response);
	  }

		
		String respuestaJson = this.getJsonResponse(respuesta);
		
		return respuestaJson;
	}
	
	
	public String getJsonResponse(Object conResponse) {
		Gson gson = new Gson();
		
		String result = gson.toJson(conResponse);
		return result;
	}
}