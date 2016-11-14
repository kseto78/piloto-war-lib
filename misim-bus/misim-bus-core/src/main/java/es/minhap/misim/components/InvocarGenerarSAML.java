package es.minhap.misim.components;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

import javax.annotation.Resource;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import misim.bus.common.bean.SoapPayload;
import misim.bus.common.util.XMLUtils;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import es.minhap.clave.utils.SPUtil;
import es.minhap.common.properties.PropertiesServices;
import es.minhap.misim.bus.model.exception.ModelException;
import es.minhap.plataformamensajeria.iop.beans.PeticionClaveAuthRequest;
import es.minhap.plataformamensajeria.iop.services.procesarSAMLResponse.IGestionSAMLRequestService;
import es.redsara.misim.misim_bus_webapp.respuesta.rest.Parametros;
import es.redsara.misim.misim_bus_webapp.respuesta.rest.ResponseSAMLStatusType;
import es.redsara.misim.misim_bus_webapp.respuesta.rest.RespuestaSAMLRequest;

/**
 * Cliente genérico para JAX-WS
 * 
 * @author ludarcos
 * 
 */
public class InvocarGenerarSAML implements Callable {

	private static final Logger LOG = LoggerFactory.getLogger(InvocarGenerarSAML.class);

	@Resource
	IGestionSAMLRequestService gestionSAMLRequestImpl;

	@Resource(name = "reloadableResourceBundleMessageSource")
	ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;
	PropertiesServices ps = null;

	@Override
	public Object onCall(final MuleEventContext eventContext) throws ModelException {

		LOG.debug("Empezando el proceso de invocación del enviador...");

		try {
			final Document docOriginal = SoapPayload.class.cast(eventContext.getMessage().getPayload())
					.getSoapMessage();
			System.out.println("REQUEST: " + XMLUtils.dom2xml(docOriginal));

			NodeList peticion = docOriginal.getElementsByTagNameNS(
					"http://misim.redsara.es/misim-bus-webapp/PeticionClaveAuthRequest", "PeticionClaveAuthRequest");
			String xmlPeticion = XMLUtils.nodeToString(peticion.item(0));

			PeticionClaveAuthRequest pet = new PeticionClaveAuthRequest();
			pet.loadObjectFromXML(xmlPeticion);

			String respuesta = gestionSAMLRequestImpl.comprobarDatosUsuario(pet);

			RespuestaSAMLRequest request = new RespuestaSAMLRequest();
			ResponseSAMLStatusType status = new ResponseSAMLStatusType();
			Parametros parametros = null;

			if (respuesta.contains("OK")) {

				ps = new PropertiesServices(reloadableResourceBundleMessageSource);

				String plataformaIOS = ps.getMessage("sp.plataforma.ios", null, null, null);
				String plataformaAndroid = ps.getMessage("sp.plataforma.android", null, null, null);

				String allowLegalPerson = ps.getMessage("attribute.allowLegalPerson", null, null, null);
				String urlClave = ps.getMessage("country.url", null, null, null);
				String urlReturn = ps.getMessage("sp.return", null, null, null);

				String allowCertificateMinApiLevel = ps.getMessage("sp.android.allowCertificateMinApiLevel", null,
						null, null);

				String excludedIdPList = "";
				if (null != pet.getAPILevel() && pet.getIdPlataforma().equals(plataformaAndroid)) {
					if (Integer.parseInt(pet.getAPILevel()) < Integer.parseInt(allowCertificateMinApiLevel))
						excludedIdPList = ps.getMessage("sp.excludedIdPList.excludeCertificate", null, null, null);
					else
						excludedIdPList = ps.getMessage("sp.excludedIdPList", null, null, null);
				} else if (pet.getIdPlataforma().equals(plataformaIOS)) {
					excludedIdPList = ps.getMessage("sp.excludedIdPList.excludeCertificate", null, null, null);
				} else {
					excludedIdPList = ps.getMessage("sp.excludedIdPList", null, null, null);
				}

				String idpList = "";

				respuesta = SPUtil.generarXMLPeticionClaveProperties(ps, "", idpList, urlReturn, null, true);

				if (null != respuesta && respuesta.length() > 0) {
					parametros = new Parametros();

					parametros.setParametro1(respuesta);
					parametros.setParametro2(excludedIdPList);
					parametros.setParametro3(allowLegalPerson);

					status.setDetails("Peticion Procesada Correctamente");
					status.setStatusCode("0000");
					status.setStatusText("OK");
					request.setUrlClave(urlClave);
					request.setUrlReturn(urlReturn);
				} else {
					// parametros.setSAMLRequest("");
					// parametros.setExcludedIdPList(excludedIdPList);
					// parametros.setAllowLegalPerson(allowLegalPerson);

					status.setDetails("Error al generar la peticion de Cl@ve");
					status.setStatusCode("4000");
					status.setStatusText("KO");
				}

				request.setStatus(status);
				request.setParameters(parametros);
				respuesta = request.toXMLSMS(request);
			}

			Document doc = XMLUtils.xml2doc(respuesta, Charset.forName("UTF-8"));
			String respuestaCompleta = XMLUtils.createSOAPFaultString((Node) doc.getDocumentElement());

			SOAPMessage responseMessage = getSoapMessageFromString(respuestaCompleta);
			try {

				SoapPayload<?> initPayload = eventContext.getMessage().getPayload(SoapPayload.class);
				SoapPayload<?> soapPayload = null;
				if (responseMessage.getSOAPBody().hasFault()) {
					// La respuesta es un SOAP Fault
					soapPayload = new SoapPayload<Object>();
					eventContext.getMessage().setOutboundProperty("SOAPFault", true);
				} else {
					// La respuesta no es un SOAP Fault
					// soapPayload = new
					// SoapPayload<RespuestaServiciosDispoibles>();
					soapPayload = new SoapPayload<ResponseSAMLStatusType>();
					eventContext.getMessage().setOutboundProperty("SOAPFault", false);
				}

				System.out.println("RESPONSE: " + XMLUtils.dom2xml(XMLUtils.soap2dom(responseMessage)));
				soapPayload.setSoapAction(initPayload.getSoapAction());
				soapPayload.setSoapMessage(XMLUtils.soap2dom(responseMessage));

				eventContext.getMessage().setPayload(soapPayload);

			} catch (Exception e) {
				// Lanzar error
				LOG.error("Error en la transmisión: Error al obtener la respuesta del servicio Web especificado", e);
				throw new ModelException("Error al obtener la respuesta del servicio Web especificado", 104);
			}

		} catch (ModelException e) {

			throw new ModelException(e.getMensaje(), e.getCodigo());

		} catch (Exception e) {
			// Lanzar error
			LOG.error("Error en la transmisión: Error de sistema Invocar Emisor", e);
			throw new ModelException("Error de sistema Invocar Emisor", 502);
		}
		LOG.debug("Proceso de creación de invocación al emisor terminado.");
		return eventContext.getMessage();
	}

	private SOAPMessage getSoapMessageFromString(String xml) throws SOAPException, IOException {
		MessageFactory factory = MessageFactory.newInstance();
		SOAPMessage message = factory.createMessage(new MimeHeaders(),
				new ByteArrayInputStream(xml.getBytes(Charset.forName("UTF-8"))));
		return message;
	}

}