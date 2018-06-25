package es.minhap.misim.bus.webapp.restserviceprovider;

import java.io.StringReader;
import java.nio.charset.Charset;

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

import org.json.JSONObject;
import org.mule.api.MuleContext;
import org.mule.api.MuleException;
import org.mule.api.MuleMessage;
import org.mule.api.client.MuleClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.gson.Gson;

import es.minhap.misim.bus.core.pojo.PeticionPayload;
import es.minhap.plataformamensajeria.iop.beans.ResponseStatusTypeRegistroWebPush;
import es.minhap.plataformamensajeria.iop.beans.RespuestaRegistroWebPush;


/**
 * Clase que implementa la logica de la peticion del servicio registroUsuarioWebPush
 * @author ralberoc
 *
 */
public class RegistroUsuarioWebPushImpl implements RegistroUsuarioWebPush {
	
	private static final Logger LOG = LoggerFactory.getLogger(RegistroUsuarioWebPushImpl.class);

	public static final String RECEPT_QUEUE = "vm://registro-usuario-webpush";
	public static final String SOAP_ACTION = "registro-usuario-webpush";

	private String username = null;
	private String password = null;

	@Context
	private HttpServletRequest request;

	@Context
	private ServletContext servletContext;
	
	/**
	 * Metodo que implementa la logica de la peticion de registro de usuarios Web Push
	 * @param json
	 * @return
	 */
	@Override
	public String registrarUsuarioWebPush(String json) {

		RespuestaRegistroWebPush respuesta = new RespuestaRegistroWebPush();
	
		try {
			
			String endpoint = null;
			String key = null;
			String auth = null;
			String idServicio = null;
			String idUsuario = null;
			String accion = null;
						
			if (null != json && json.length()> 0){
				 JSONObject jsonObj = new JSONObject(json);
				 endpoint = (!jsonObj.isNull("Endpoint")) ? jsonObj.getString("Endpoint") : null;
				 key = (!jsonObj.isNull("Key")) ? jsonObj.getString("Key") : null;
				 auth = (!jsonObj.isNull("Auth")) ? jsonObj.getString("Auth") : null;
				 idServicio = (!jsonObj.isNull("IdServicio")) ? jsonObj.getString("IdServicio") : null;
				 idUsuario = (!jsonObj.isNull("IdUsuario")) ? jsonObj.getString("IdUsuario") : null;
				 accion = (!jsonObj.isNull("Accion")) ? jsonObj.getString("Accion") : null;
				 username = (!jsonObj.isNull("Usuario")) ? jsonObj.getString("Usuario") : null;
				 password = (!jsonObj.isNull("Password")) ? jsonObj.getString("Password") : null;
				
			}
			
			if ((null != username && !("").equals(username)) && (null != password && !("").equals(password))
					&& (null != endpoint && !("").equals(endpoint))
					&& (null != key && !("").equals(key))
					&& (null != auth && !("").equals(auth)) 
					&& (null != accion && !("").equals(accion)) 
					&& (null != idServicio && !("").equals(idServicio))) {

				respuesta = procesarPeticion(respuesta, endpoint, key, auth, idServicio, idUsuario, accion);

			} else {

				ResponseStatusTypeRegistroWebPush status = new ResponseStatusTypeRegistroWebPush();
				status.setStatusCode("2000");
				status.setStatusText("La petici&oacute;n no incluye todos los parametros obligatorios");
				status.setDetails("No se ha detectado alguno de los siguientes parametros obligatorios: Usuario, Password, IdServicio, Endpoint, Key, Auth, Accion");
				respuesta.setStatus(status);
			}
		} catch (Exception e) {
			LOG.error("[ConfirmarAltaUsuarioImpl] No se ha detectado alguno de los siguientes parametros obligatorios", e);
			ResponseStatusTypeRegistroWebPush status = new ResponseStatusTypeRegistroWebPush();
			status.setStatusCode("3000");
			status.setStatusText("Autentificiaci&oacute;n no v&aacute;lida o enviada.");
			status.setDetails("No se ha detectado alguno de los siguientes parametros obligatorios: Usuario, Password");
			respuesta.setStatus(status);
		}

		return this.getJsonResponse(respuesta);

	}

	/**
	 * Metodo que devuelve la respuesta de la peticion de registro de usuarios Web Push
	 * 
	 * @param respuesta
	 * @param endpoint
	 * @param key
	 * @param auth
	 * @param idServicio
	 * @param idUsuario
	 * @param accion
	 * @return
	 */
	private RespuestaRegistroWebPush procesarPeticion(RespuestaRegistroWebPush respuesta, String endpoint,
			String key, String auth, String idServicio, String idUsuario, String accion) {
		try {

			PeticionRegistroUsuarioWebPush peticion = new PeticionRegistroUsuarioWebPush();
			peticion.setUsuario(username);
			peticion.setPassword(password);
			peticion.setEndpoint(endpoint);
			peticion.setAuth(auth);
			peticion.setKey(key);
			peticion.setIdServicio(idServicio);
			peticion.setIdUsuario(idUsuario);
			peticion.setAccion(accion);

			DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document document = docBuilder.newDocument();

			JAXBContext context = JAXBContext.newInstance(peticion.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.marshal(peticion, document);

			String respuestaCompleta = XMLUtils.createSOAPFaultString((Node) document.getDocumentElement());

			respuesta = tratarRespuesta(respuesta, respuestaCompleta);

		} catch (Exception e) {
			LOG.error("[ConfirmarAltaUsuarioImpl]Error indeterminado", e);
			ResponseStatusType response = new ResponseStatusType();
			response.setStatusCode("999");
			response.setStatusText("Error indeterminado");
			response.setDetails("Error indeterminado");
		}
		return respuesta;
	}

	/**
	 * Meotod que valida la respuesta de la peticion de registro de web push
	 * @param respuesta
	 * @param respuestaCompleta
	 * @return
	 */
	private RespuestaRegistroWebPush tratarRespuesta(RespuestaRegistroWebPush respuesta,
			String respuestaCompleta) {
		try {

			MuleContext muleContext = (MuleContext) servletContext.getAttribute("mule.context");
			MuleClient muleClient = muleContext.getClient();
			SoapPayload<?> payload = new PeticionPayload();
			payload.setSoapAction(SOAP_ACTION);
			payload.setSoapMessage(XMLUtils.xml2doc(respuestaCompleta, Charset.forName("UTF-8")));
			
			final MuleMessage muleResponse = muleClient.send(RECEPT_QUEUE, payload, null, 10000);

			Document respuestaSOAP = muleResponse.getPayload(SoapPayload.class).getSoapMessage();

			NodeList nodoRespuesta = respuestaSOAP.getElementsByTagName("RespuestaRegistroWebPush");
			String xmlRespuesta = XMLUtils.nodeToString(nodoRespuesta.item(0));

			JAXBContext jaxbContext = JAXBContext.newInstance(RespuestaRegistroWebPush.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			StringReader reader = new StringReader(xmlRespuesta);
			respuesta = (RespuestaRegistroWebPush) unmarshaller.unmarshal(reader);

		} catch (final MuleException e) {
			throw new RuntimeException("Error in mule client", e);
		} catch (final Exception e) {
			throw new RuntimeException("Error in provider endpoint", e);
		}
		return respuesta;
	}

	/**
	 * Metodo que obtiene la cadena de un json almacenado en un objeto
	 * @param conResponse
	 * @return
	 */
	public String getJsonResponse(Object conResponse) {
		Gson gson = new Gson();

		return gson.toJson(conResponse);
		
	}
}