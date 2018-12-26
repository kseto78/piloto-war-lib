package es.minhap.misim.bus.model.seguridad;

import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class ModelTestUtilTest {

	/**
	 * Spring context
	 */
	protected static final String SPRING_CONTEXT_LOCATION = "/spring/spring-context.xml";

	/**
	 * Keystore properties IDs
	 */
	static final String KEY_STORE_TYPE = "keyStoreType";
	static final String KEY_STORE_PASSWORD = "keyStorePassword";
	static final String KEY_STORE_ALIAS = "keyStoreAlias";
	static final String ALIAS_PASSWORD = "aliasPassword";
	static final String KEY_STORE_FILE = "keyStoreFile";
	static final String MODEL_PETICION = "modelPeticion";

	/**
	 * XML test files IDs
	 */
	static final String PETICION_CLASS_PATH = "peticion.xml";
	static final String PETICION_INCORRECTA_CLASS_PATH = "peticionIncorrecta";

	public static String getXMLFlow(final Document document)
			throws TransformerException {
		final TransformerFactory tf = TransformerFactory.newInstance();
		final Transformer transformer = tf.newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		final StringWriter writer = new StringWriter();
		transformer
				.transform(new DOMSource(document), new StreamResult(writer));
		final String output = writer.getBuffer().toString()
				.replaceAll("\n|\r", "");

		return output;

	}

}
