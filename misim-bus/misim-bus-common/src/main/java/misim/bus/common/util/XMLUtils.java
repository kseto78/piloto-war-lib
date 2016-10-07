package misim.bus.common.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.Charset;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.xml.security.c14n.Canonicalizer;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XMLUtils {

	/**
	 * Convert a DOM Document into a soap message.
	 * <p/>
	 * 
	 * @param doc
	 *            El documento que contiene el mensaje SOAP
	 * @return El mensaje SOAP correspondiente
	 * @throws Exception
	 */
	public static SOAPMessage dom2soap(final Document doc) throws Exception {
		org.apache.xml.security.Init.init();
		final Canonicalizer c14n = Canonicalizer
				.getInstance(Canonicalizer.ALGO_ID_C14N_EXCL_OMIT_COMMENTS);
		final byte[] canonicalMessage = c14n.canonicalizeSubtree(doc);
		final ByteArrayInputStream in = new ByteArrayInputStream(
				canonicalMessage);
		final MessageFactory factory = MessageFactory.newInstance();
		return factory.createMessage(null, in);
	}

	/**
	 * Convert a {@link Document} into XML {@link String}
	 * 
	 * @param document
	 * @return
	 */
	public static String dom2xml(final Document document) throws Exception {

		final Transformer transformer = TransformerFactory.newInstance()
				.newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		final StringWriter writer = new StringWriter();
		transformer
				.transform(new DOMSource(document), new StreamResult(writer));
		return writer.getBuffer().toString().replaceAll("\n|\r", "");
	}

	/**
	 * Convert a SOAP Body as this associated Type
	 * 
	 * @param soapDOM
	 *            The SOAP {@link Document}
	 * @param encoding
	 *            The SOAP flow {@link Charset}
	 * @param mappedClass
	 *            the binded {@link Class}
	 * @return an instance of the binded class if success, null otherwise
	 */
	public static <T> T getPayloadFromSOAPBody(final Document soapDOM, final Charset encoding, final Class<T> mappedClass) throws Exception {

		final MessageFactory messageFactory = MessageFactory.newInstance();
		final ByteArrayInputStream soapStringStream = new ByteArrayInputStream(
				dom2xml(soapDOM).getBytes(encoding));
		final SOAPMessage soapMessage = messageFactory.createMessage(
				new MimeHeaders(), soapStringStream);
		final Unmarshaller unmarshaller = JAXBContext.newInstance(mappedClass)
				.createUnmarshaller();
		final Document bodySOAP = soapMessage.getSOAPBody()
				.extractContentAsDocument();
		final T payload = mappedClass.cast(unmarshaller.unmarshal(bodySOAP));

		return payload;

	}

	/**
	 * Build a SOAP Message {@link Document} and set the given object as SOAP
	 * Body
	 * 
	 * @param obj
	 *            Object to marshall as SOAP Body
	 * @param encoding
	 *            Encoding to use to marshall the object as XML
	 * @param mappedClass
	 *            Specific class of the object to marshall
	 * @return a {@link Document} representation of the SOAP Message
	 * @throws Exception
	 */
	public static <T> Document setPayloadFromObject(final T obj,final Charset encoding, final Class<T> mappedClass) throws Exception {

		final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		final Document body = dbf.newDocumentBuilder().newDocument();

		final Marshaller marshaller = JAXBContext.newInstance(mappedClass)
				.createMarshaller();
		marshaller
				.setProperty(Marshaller.JAXB_ENCODING, encoding.displayName());
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		final SOAPMessage soap = MessageFactory.newInstance().createMessage();
		soap.getSOAPBody().addDocument(body);
		marshaller.marshal(obj, soap.getSOAPBody());
		soap.saveChanges();
		return XMLUtils.soap2dom(soap);
	}

	/**
	 * Convert a {@link SOAPMessage} to a {@link Document}
	 * 
	 * @param soap
	 *            The complete {@link SOAPMessage}
	 * @return a {@link Document} if success, null otherwise
	 */
	public static Document soap2dom(final SOAPMessage soap) throws Exception {
		Document soapDOM = null;

		final Source soapSource = soap.getSOAPPart().getContent();
		if (soapSource instanceof DOMSource) {
			final DOMResult r = new DOMResult();
			TransformerFactory.newInstance().newTransformer()
					.transform(soapSource, r);
			soapDOM = (Document) r.getNode();
		} else if (soapSource instanceof SAXSource) {
			final InputSource inSource = ((SAXSource) soapSource)
					.getInputSource();
			final DocumentBuilderFactory dbf = DocumentBuilderFactory
					.newInstance();
			dbf.setNamespaceAware(true);
			DocumentBuilder db = null;

			db = dbf.newDocumentBuilder();

			soapDOM = db.parse(inSource);
		}
		
		return soapDOM;
	}

	/**
	 * Convert a XML {@link String} representation into a {@link Document}
	 * 
	 * @param xml
	 * @param encoding
	 * @return
	 */
	public static Document xml2doc(final String xml, final Charset encoding) throws Exception {

		final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		final DocumentBuilder db = dbf.newDocumentBuilder();
		final Document document = db.parse(new ByteArrayInputStream(xml
				.getBytes(encoding)));

		return document;
	}

	/**
	 * Valida un flujo XML contra un esquema XSD
	 * 
	 * @param xmlDOM
	 *            XML {@link Document}
	 * @param xsdDOM
	 *            XSD {@link Document}
	 * @return {@link Boolean#TRUE} si el flujo XML es válido,
	 *         {@link Boolean#FALSE} si no
	 */
	public static Boolean isValid(final Document xmlDOM, final Document xsdDOM,	final String systemID) {

		Boolean isValid = Boolean.TRUE;
		// create a SchemaFactory capable of understanding WXS schemas
		SchemaFactory factory = SchemaFactory
				.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

		// load a WXS schema, represented by a Schema instance
		Source schemaFile = new DOMSource(xsdDOM, systemID);
		// validate the DOM tree
		try {
			Schema schema = factory.newSchema(schemaFile);

			// create a Validator instance, which can be used to validate an
			// instance document
			Validator validator = schema.newValidator();

			validator.validate(new DOMSource(xmlDOM));
		} catch (SAXException | IOException e) {
			isValid = Boolean.FALSE;
		}

 		return isValid;
	}

	/**
	 * Valida un flujo XML contra un esquema XSD
	 * 
	 * @param xml
	 *            {@link String} representación del flujo XML
	 * @param xmlEncoding
	 *            Codificación del flujo XML
	 * @param xsd
	 *            {@link String} representación del esquema XSD
	 * @param xsdEncoding
	 *            Codificación del esquema XSD
	 * @return {@link Boolean#TRUE} si el flujo XML es válido,
	 *         {@link Boolean#FALSE} si no
	 */
	public static Boolean isValid(final String xml, final String xmlEncoding,
			final String xsd, final String xsdEncoding, final String xsdSystemID) {

		Document xmlDOM, xsdDOM;
		Boolean isValid = Boolean.FALSE;
		try {
			xmlDOM = XMLUtils.xml2doc(xml, Charset.forName(xmlEncoding));

			xsdDOM = XMLUtils.xml2doc(xsd, Charset.forName(xsdEncoding));
			isValid = isValid(xmlDOM, xsdDOM, xsdSystemID);
		} catch (Exception e) {
			isValid=false;
		}
		return isValid;
	}

	/**
	 * Recupera el valor del elemento localName en el XML representado como DOM
	 * @param dom Representación {@link Document} de un XML
	 * @param namespace Namespace
	 * @param localName nombre local del nodo que analizar
	 * @return el texto del nodo
	 */
	public static String getStringValue(final Document dom, final String namespace, final String localName) {

		NodeList nodeList = dom.getElementsByTagNameNS(namespace, localName);
		if (nodeList != null && nodeList.item(0) != null) {
			return nodeList.item(0).getTextContent();
		}else{
			return null;
		}
	}
	
	/**
	 * Create SOAP document.
	 * <p/>
	 * 
	 * @param peticion
	 *            El nodo que contiene la peticion
	 * @return El documento SOAP correspondiente
	 * @throws ParserConfigurationException
	 */
	public static Document createSOAPFaultDocument(Node node) throws Exception{
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation impl = builder.getDOMImplementation();
		
		Document doc = impl.createDocument("http://schemas.xmlsoap.org/soap/envelope/", "soapenv:Envelope", null);
		Element root = doc.getDocumentElement();
		
		Element header = doc.createElement("soapenv:Header");
		root.appendChild(header);
		     
		Element body = doc.createElement("soapenv:Body");
		root.appendChild(body);
		body.appendChild(doc.importNode(node, true));
		
		String xml = dom2xml(doc);
		return xml2doc(xml, Charset.forName("UTF-8"));
	}
	
	/**
	 * Create SOAP document.
	 * <p/>
	 * 
	 * @param peticion
	 *            El nodo que contiene la peticion
	 * @return El documento SOAP correspondiente
	 * @throws ParserConfigurationException
	 */
	public static String createSOAPFaultString(Node node) throws Exception{
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation impl = builder.getDOMImplementation();
		
		Document doc = impl.createDocument("http://schemas.xmlsoap.org/soap/envelope/", "soapenv:Envelope", null);
		Element root = doc.getDocumentElement();
		
		Element header = doc.createElement("soapenv:Header");
		root.appendChild(header);
		     
		Element body = doc.createElement("soapenv:Body");
		root.appendChild(body);
		body.appendChild(doc.importNode(node, true));
		
		return dom2xml(doc);
	}
	
	/**
	 * Create SOAP document.
	 * <p/>
	 * 
	 * @param peticion
	 *            El nodo que contiene la peticion
	 * @return El documento SOAP correspondiente
	 * @throws TransformerException
	 */
	public static String nodeToString(Node node) throws Exception {
		StringWriter sw = new StringWriter();
		
		Transformer t = TransformerFactory.newInstance().newTransformer();
		t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		t.setOutputProperty(OutputKeys.INDENT, "yes");
		t.transform(new DOMSource(node), new StreamResult(sw));
		
		return sw.toString();
	}
}
