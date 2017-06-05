package es.minhap.misim.bus.webapp.restserviceprovider;

import java.io.StringReader;
import java.nio.charset.Charset;
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

import misim.bus.common.bean.SoapPayload;
import misim.bus.common.util.XMLUtils;

import org.mule.api.MuleContext;
import org.mule.api.MuleException;
import org.mule.api.MuleMessage;
import org.mule.api.client.MuleClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import sun.misc.BASE64Decoder;

import com.google.gson.Gson;

import es.minhap.misim.bus.core.pojo.PeticionPayload;
import es.minhap.plataformamensajeria.iop.beans.ResponseStatusTypeConfirmarAltaUsuario;
import es.minhap.plataformamensajeria.iop.beans.RespuestaConfirmarAltaUsuario;


public class ConfirmarAltaUsuarioImpl implements ConfirmarAltaUsuario {
	
	private static final Logger LOG = LoggerFactory.getLogger(ConfirmarAltaUsuarioImpl.class);

	public static final String RECEPT_QUEUE = "vm://registro-movil";
	public static final String SOAP_ACTION = "confirmar-alta-usuario";

	private StringTokenizer tokenizer = null;
	private String username = null;
	private String password = null;

	@Context
	private HttpServletRequest request;

	@Context
	private ServletContext servletContext;

	@Override
	public String confirmarAltaUsuario(MultivaluedMap<String,String>map) {

		RespuestaConfirmarAltaUsuario respuesta = new RespuestaConfirmarAltaUsuario();
		String decoded;
		try {
			// Get the Authorisation Header from Request
			String header = request.getHeader("authorization");

			// Header is in the format "Basic 3nc0dedDat4"
			// We need to extract data before decoding it back to original
			// string
			String data = header.substring(header.indexOf(" ") + 1);
			
			String idDispositivo = (null != map.getFirst("IdDispositivo")) ? map.getFirst("IdDispositivo") : null;
			String idServicioMovil = (null != map.getFirst("ServicioMovil")) ? map.getFirst("ServicioMovil") : null;
			String codConfirmacion = (null != map.getFirst("CodConfirmacion")) ? map.getFirst("CodConfirmacion") : null;
			String uidDispositivo = (null != map.getFirst("UidDispositivo")) ? map.getFirst("UidDispositivo") : null;
			String tokenSession = (null != map.getFirst("TokenSession")) ? map.getFirst("TokenSession") : null;



			// Decode the data back to original string
			byte[] bytes = new BASE64Decoder().decodeBuffer(data);
			decoded = new String(bytes);

			LOG.info(decoded);
			if (null != decoded) {
				tokenizer = new StringTokenizer(decoded, ":");
				username = tokenizer.nextToken();
				password = tokenizer.nextToken();
			}

			if ((null != username && !("").equals(username)) && (null != password && !("").equals(password))
					&& (null != idDispositivo && !("").equals(idDispositivo))
					&& (null != idServicioMovil && !("").equals(idServicioMovil))
					&& (null != codConfirmacion && !("").equals(codConfirmacion)) 
					&& (null != uidDispositivo && !("").equals(uidDispositivo))
					&& (null != tokenSession && !("").equals(tokenSession))) {

				try {

					PeticionConfirmarAltaUsuario peticion = new PeticionConfirmarAltaUsuario();
					peticion.setUsuario(username);
					peticion.setPassword(password);
					peticion.setIdDispositivo(idDispositivo);
					peticion.setIdServicioMovil(idServicioMovil);
					peticion.setCodConfirmacion(codConfirmacion);
					peticion.setUidDispositivo(uidDispositivo);
					peticion.setTokenSession(tokenSession);

					DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
					Document document = docBuilder.newDocument();

					JAXBContext context = JAXBContext.newInstance(peticion.getClass());
					Marshaller marshaller = context.createMarshaller();
					marshaller.marshal(peticion, document);

					String respuestaCompleta = XMLUtils.createSOAPFaultString((Node) document.getDocumentElement());

					try {

						MuleContext muleContext = (MuleContext) servletContext.getAttribute("mule.context");
						MuleClient muleClient = muleContext.getClient();
						SoapPayload<?> payload = new PeticionPayload();
						payload.setSoapAction(SOAP_ACTION);
						payload.setSoapMessage(XMLUtils.xml2doc(respuestaCompleta, Charset.forName("UTF-8")));
						
						final MuleMessage muleResponse = muleClient.send(RECEPT_QUEUE, payload, null, 10000);

						Document respuestaSOAP = muleResponse.getPayload(SoapPayload.class).getSoapMessage();

						NodeList nodoRespuesta = respuestaSOAP.getElementsByTagName("RespuestaConfirmarAltaUsuario");
						String xmlRespuesta = XMLUtils.nodeToString(nodoRespuesta.item(0));

						JAXBContext jaxbContext = JAXBContext.newInstance(RespuestaConfirmarAltaUsuario.class);
						Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

						StringReader reader = new StringReader(xmlRespuesta);
						respuesta = (RespuestaConfirmarAltaUsuario) unmarshaller.unmarshal(reader);

					} catch (final MuleException e) {
						throw new RuntimeException("Error in mule client", e);
					} catch (final Exception e) {
						throw new RuntimeException("Error in provider endpoint", e);
					}

				} catch (Exception e) {
					LOG.error("[ConfirmarAltaUsuarioImpl]Error indeterminado", e);
					ResponseStatusType response = new ResponseStatusType();
					response.setStatusCode("999");
					response.setStatusText("Error indeterminado");
					response.setDetails("Error indeterminado");
				}

			} else {

				ResponseStatusTypeConfirmarAltaUsuario status = new ResponseStatusTypeConfirmarAltaUsuario();
				status.setStatusCode("2000");
				status.setStatusText("La petici&oacute;n no incluye todos los parametros obligatorios");
				status.setDetails("No se ha detectado alguno de los siguientes parametros obligatorios: Usuario, Password, IdDispositivo, ServicioMovil, CodConfirmacion, UidDispositivo o TokenSession");
				respuesta.setStatus(status);
			}
		} catch (Exception e) {
			LOG.error("[ConfirmarAltaUsuarioImpl] No se ha detectado alguno de los siguientes parametros obligatorios", e);
			ResponseStatusTypeConfirmarAltaUsuario status = new ResponseStatusTypeConfirmarAltaUsuario();
			status.setStatusCode("3000");
			status.setStatusText("Autentificiaci&oacute;n no v&aacute;lida o enviada.");
			status.setDetails("No se ha detectado alguno de los siguientes parametros obligatorios: Usuario, Password");
			respuesta.setStatus(status);
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