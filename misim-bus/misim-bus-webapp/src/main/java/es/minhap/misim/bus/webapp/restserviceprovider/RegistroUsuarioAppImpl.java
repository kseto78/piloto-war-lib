package es.minhap.misim.bus.webapp.restserviceprovider;

import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.SOAPMessage;

import misim.bus.common.bean.SoapPayload;
import misim.bus.common.util.XMLUtils;

import org.eclipse.jetty.util.log.Log;
import org.json.JSONObject;
import org.mule.api.MuleContext;
import org.mule.api.MuleException;
import org.mule.api.MuleMessage;
import org.mule.api.client.MuleClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import sun.misc.BASE64Decoder;

import com.google.gson.Gson;

import es.minhap.misim.bus.core.pojo.PeticionPayload;
import es.minhap.misim.components.InicializarAEAT;

@Service("registroUsuarioApp")
public class RegistroUsuarioAppImpl implements RegistroUsuarioApp {
	
	private static final Logger LOG = LoggerFactory.getLogger(RegistroUsuarioAppImpl.class);

	public static String ERROR_AUTENTIFICACION = "Error en Autentificacion - La clave no se corresponde con ninguna aplicacion";
	public static String ERROR_REQUESTTIMEOUT = "Error en Peticion - La peticion se ha caducado";
	public static String ERROR_PARAMETROS = "Error en Parametros de entrada";

	public static final String RECEPT_QUEUE = "vm://registro-usuario";
	public static final String SOAP_ACTION = "registrarUsuarioApp";
	public static final String SOAP_APLICATION = "Mi buzón gob.ES";

	private StringTokenizer tokenizer = null;
	private String username = null;
	private String password = null;

	@Context
	private HttpServletRequest request;

	@Context
	private ServletContext servletContext;

	public String registrarUsuario(MultivaluedMap<String,String>map) {

		Respuesta respuesta = new Respuesta();
		
		  String decoded;
		  try{
			 
		   // Get the Authorisation Header from Request
		   String header = request.getHeader("authorization");
		    
		   // Header is in the format "Basic 3nc0dedDat4"
		   // We need to extract data before decoding it back to original string
		   String data = header.substring(header.indexOf(" ") +1 );
		   
		   
		   
		   String servicio = (null != map.getFirst("Servicio")) ? map.getFirst("Servicio") : null;
		   String idUsuario = (null != map.getFirst("IdUsuario")) ? map.getFirst("IdUsuario") : null;
		   String plataforma = (null != map.getFirst("Plataforma")) ? map.getFirst("Plataforma") : null;
		   String idRegistro = (null != map.getFirst("IdRegistro")) ? map.getFirst("IdRegistro") : null;
		   String idDispositivo = (null != map.getFirst("IdDispositivo")) ? map.getFirst("IdDispositivo") : null;
		   String uidDispositivo = (null != map.getFirst("UidDispositivo")) ? map.getFirst("UidDispositivo") : null;
		   String tokenSession = (null != map.getFirst("TokenSession")) ? map.getFirst("TokenSession") : null;
		   
		   // Decode the data back to original string
		   byte[] bytes = new BASE64Decoder().decodeBuffer(data);
		   decoded = new String(bytes);
		    
		   LOG.info(decoded);
			if (null!=decoded){
				  tokenizer = new StringTokenizer(decoded, ":");
				  username = tokenizer.nextToken();
				  password = tokenizer.nextToken(); 
			}
		
		if((null!=username && !("").equals(username))
				 && (null!=password && !("").equals(password))
				 && (null!=servicio && !("").equals(servicio))
				 && (null!=plataforma && !("").equals(plataforma))
				 && (null!=idRegistro && !("").equals(idRegistro))
				 && (null!=uidDispositivo && !("").equals(uidDispositivo))
				 ){
		
			try {
			
				Peticion peticion = new Peticion();
				peticion.setUsuario(username);
				peticion.setPassword(password);
				peticion.setServicio(servicio);
				peticion.setIdUsuario(idUsuario);
				peticion.setPlataforma(plataforma);
				peticion.setIdRegistro(idRegistro);
				peticion.setIdDispositivo(idDispositivo);
				peticion.setTokenSession(tokenSession);
				peticion.setUidDispositivo(uidDispositivo);
				
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
					payload.setSoapAplication(SOAP_APLICATION);
					payload.setSoapMessage(XMLUtils.xml2doc(respuestaCompleta, Charset.forName("UTF-8")));
				
					final MuleMessage muleResponse = muleClient.send(RECEPT_QUEUE,payload, null, 10000);
					
					Document respuestaSOAP = muleResponse.getPayload(SoapPayload.class).getSoapMessage();
					
					NodeList nodoRespuesta = respuestaSOAP.getElementsByTagName("Respuesta");
					String xmlRespuesta = XMLUtils.nodeToString(nodoRespuesta.item(0));
					
					JAXBContext jaxbContext = JAXBContext.newInstance(Respuesta.class);
					Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
	
					StringReader reader = new StringReader(xmlRespuesta);
					respuesta = (Respuesta) unmarshaller.unmarshal(reader);
	
				} catch (final MuleException e) {
					throw new RuntimeException("Error in mule client", e);
				} catch (final Exception e) {
					throw new RuntimeException("Error in provider endpoint", e);
				}
		
			} catch (Exception e) {
			
				ResponseStatusType response = new ResponseStatusType();
				
				response.setStatusCode("999");
				response.setStatusText("Error indeterminado");
				response.setDetails("Error indeterminado");
				
				respuesta.setStatus(response);
			}
			
		}else{
		
			ResponseStatusType response = new ResponseStatusType();
			
			response.setStatusCode("2000");
			response.setStatusText("La petici&oacute;n no incluye todos los parametros obligatorios");
			response.setDetails("No se ha detectado alguno de los siguientes parametros obligatorios: Usuario, Password, Servicio, Plataforma, IdRegistro o UidDispositivo");
			
			respuesta.setStatus(response);
		}
	  }catch(Exception e){
		   e.printStackTrace();
			ResponseStatusType response = new ResponseStatusType();
			response.setStatusCode("3000");
			response.setStatusText("Autentificiaci&oacute;n no v&aacute;lida o enviada.");
			response.setDetails("No se ha detectado alguno de los siguientes parametros obligatorios: Usuario, Password");
			respuesta.setStatus(response);
	  }

		
		String respuestaJson = this.getJsonResponse(respuesta);
		
		return respuestaJson;
	}

	/**
	 * Genera el SOAP Fault Message
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	protected static final SOAPMessage generateSOAPFault() throws Exception {

		ResponseStatusType response = new ResponseStatusType();

		response.setStatusCode("999");
		response.setStatusText("Error indeterminado");
		response.setDetails("Error indeterminado");

		Respuesta respuesta = new Respuesta();
		respuesta.setStatus(response);

		return XMLUtils.dom2soap(XMLUtils.setPayloadFromObject(respuesta, Charset.forName("UTF-8"), Respuesta.class));
	}

	public String getJsonResponse(Object conResponse) {
		Gson gson = new Gson();

		String result = gson.toJson(conResponse);
		return result;
	}

}