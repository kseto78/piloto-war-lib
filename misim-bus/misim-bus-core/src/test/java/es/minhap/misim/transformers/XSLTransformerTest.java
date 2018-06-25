package es.minhap.misim.transformers;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

import misim.bus.common.util.XMLUtils;

import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;

import es.minhap.misim.tranformers.XSLTransformer;

public class XSLTransformerTest {

	private String xml = StringUtils.EMPTY;
	private Document xmlDOM = null;
	private Document xsdDOM = null;
	private String xsl = StringUtils.EMPTY;
	private String systemID = StringUtils.EMPTY;

	@Before
	public void setUp() throws Exception {
		
		// Cargar el flujo XML a DOM

		// --> Lectura del XML (petición v3)
		List<String> lines = Files.readAllLines((new File(getClass().getResource("/xml/respuestaCompletaConsultaNotifica.xml").toURI())).toPath(), Charset.forName("UTF-8"));
		// --> Recomposición del flujo XML
		StringBuilder sb = new StringBuilder();
		for (String line : lines) {
			sb.append(line);
		}
		xml = sb.toString();
		// --> Creación del DOM a partir del flujo XML
		xmlDOM = XMLUtils.xml2doc(xml, Charset.forName("UTF-8"));

		// Cargar el flujo XSL

		// --> Lectura del XSL (tranformación SCSP V3 a V2)
		lines = Files.readAllLines((new File(getClass().getResource("/xsl/respuestaCompletaConsultaNotifica_nuevo.xsl").toURI())).toPath(), Charset.forName("UTF-8"));
		// --> Recomposición del flujo XML
		sb = new StringBuilder();
		for (String line : lines) {
			sb.append(line);
		}
		xsl = sb.toString();

		// Cargar el flujo XSD

		// --> Lectura del XSD (validación del formato SCSPv2)
		File xsdFile = new File(getClass().getResource("/xsd/soap-message-respuesta-SCSPv3.xsd").toURI());
		lines = Files.readAllLines(xsdFile.toPath(), Charset.forName("UTF-8"));
		// --> Recomposición del flujo XML
		sb = new StringBuilder();
		for (String line : lines) {
			sb.append(line);
		}
		
		xsdDOM = XMLUtils.xml2doc(sb.toString(), Charset.forName("UTF-8"));
		systemID = xsdFile.getAbsolutePath();
	}

	@Test
	public void testProcess() {

		try {
			
			Document domTransformed = XSLTransformer.process(xmlDOM, xsl);
			
			assertTrue("El flujo XML no es válido", XMLUtils.isValid(
					XMLUtils.dom2xml(domTransformed), "UTF-8",
					XMLUtils.dom2xml(xsdDOM), "UTF-8", systemID));

		} catch (Exception e) {
			fail(e.getLocalizedMessage());
		}
	}
}
