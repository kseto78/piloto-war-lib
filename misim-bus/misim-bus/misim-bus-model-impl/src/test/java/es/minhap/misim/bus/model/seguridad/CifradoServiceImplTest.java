package es.minhap.misim.bus.model.seguridad;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { ModelTestUtil.SPRING_CONTEXT_LOCATION })
public class CifradoServiceImplTest {

	@Autowired
	CifradoService cifradoService;

	@Resource(name = "cifradoProperties")
	Properties props;

	@Test
	public final void testCifrar() {
		// Cifra un documento
		try {

			// Recuperamos la petición SOAP que cifrar
			final DocumentBuilderFactory dbf = DocumentBuilderFactory
					.newInstance();
			dbf.setNamespaceAware(true);
			final DocumentBuilder db = dbf.newDocumentBuilder();
			final Document docOriginal = db.parse(new ClassPathResource(props
					.getProperty(ModelTestUtil.PETICION_CLASS_PATH)).getFile());

			// Se recuperan todos los nodos Solicitudes del XML
			final NodeList solicitudes = docOriginal
					.getElementsByTagNameNS(
							"http://intermediacion.redsara.es/scsp/esquemas/V3/peticion",
							"Solicitudes");
			assertTrue(
					"El fichero de petición especificado no contiene ningún nodo 'Solicitudes'",
					solicitudes != null);

			// Se prepara la lista de nodos a cifrar
			final List<Node> nodosACifrar = new ArrayList<Node>();
			nodosACifrar.add(solicitudes.item(0));

			// Invocamos al servicio de cifrado
			final Document docCifrado = cifradoService.cifrar(
					docOriginal,
					props.getProperty(ModelTestUtil.KEY_STORE_TYPE),
					props.getProperty(ModelTestUtil.KEY_STORE_PASSWORD),
					props.getProperty(ModelTestUtil.KEY_STORE_ALIAS),
					props.getProperty(ModelTestUtil.ALIAS_PASSWORD),
					new ClassPathResource(props
							.getProperty(ModelTestUtil.KEY_STORE_FILE))
							.getFile().getAbsolutePath(), nodosACifrar);

			assertTrue("El documento no ha sido cifrado", docCifrado != null);

			// Verificamos que el documento ha sido cifrado

			// Etapa 1 : Los datos del nodo Solicitudes ya están cifrados
			final NodeList cipherValue = docCifrado.getElementsByTagNameNS(
					"http://www.w3.org/2001/04/xmlenc#", "CipherValue");
			assertTrue(
					"No existe ningún nodo CipherValue trás el cifrado del documento",
					(cipherValue != null) && (cipherValue.getLength() > 0));

			// Etapa 2 : Los datos del nodo Atributos tienen que corresponder
			// entre el documento original y el documento cifrado
			final Node codigoCertificadoOriginal = docOriginal
					.getElementsByTagNameNS(
							"http://intermediacion.redsara.es/scsp/esquemas/V3/peticion",
							"CodigoCertificado").item(0);
			final Node codigoCertificadoCifrado = docCifrado
					.getElementsByTagNameNS(
							"http://intermediacion.redsara.es/scsp/esquemas/V3/peticion",
							"CodigoCertificado").item(0);

			// Los valores están comparados sin tomar en cuento el tipo de
			// letra, y después de haber
			// sido convertido a mayúsculas y quitado los espacios antes y
			// después de la cadena.
			assertTrue("El mensaje XML no ha sido cifrado correctamente",
					StringUtils.equalsIgnoreCase(StringUtils
							.upperCase(StringUtils
									.trim(codigoCertificadoOriginal
											.getTextContent())), StringUtils
							.upperCase(StringUtils
									.trim(codigoCertificadoCifrado
											.getTextContent()))));

		} catch (final Throwable t) {
			fail("Error inesperado : " + t.getMessage());
		}
	}

	@Test
	public final void testDescifrar() {
		// Descifra un documento
		try {
			// Recuperamos la petición SOAP que cifrar
			final DocumentBuilderFactory dbf = DocumentBuilderFactory
					.newInstance();
			dbf.setNamespaceAware(true);
			final DocumentBuilder db = dbf.newDocumentBuilder();
			final Document docOriginal = db.parse(new ClassPathResource(props
					.getProperty(ModelTestUtil.PETICION_CLASS_PATH)).getFile());

			// Se recuperan todos los nodos Solicitudes del XML
			final NodeList solicitudes = docOriginal
					.getElementsByTagNameNS(
							"http://intermediacion.redsara.es/scsp/esquemas/V3/peticion",
							"Solicitudes");
			assertTrue(
					"El fichero de petición especificado no contiene ningún nodo 'Solicitudes'",
					solicitudes != null);

			// Se prepara la lista de nodos a cifrar
			final List<Node> nodosACifrar = new ArrayList<Node>();
			nodosACifrar.add(solicitudes.item(0));

			// Invocamos al servicio de cifrado
			final Document docCifrado = cifradoService.cifrar(
					docOriginal,
					props.getProperty(ModelTestUtil.KEY_STORE_TYPE),
					props.getProperty(ModelTestUtil.KEY_STORE_PASSWORD),
					props.getProperty(ModelTestUtil.KEY_STORE_ALIAS),
					props.getProperty(ModelTestUtil.ALIAS_PASSWORD),
					new ClassPathResource(props
							.getProperty(ModelTestUtil.KEY_STORE_FILE))
							.getFile().getAbsolutePath(), nodosACifrar);

			assertTrue("El documento no ha sido cifrado", docCifrado != null);

			System.out
					.println("***************************************\nDoc Cifrado : "
							+ ModelTestUtil.getXMLFlow(docCifrado));

			// Desciframos el documento cifrado
			Document docDescifrado = cifradoService.descifrarKey(
					docCifrado,
					props.getProperty(ModelTestUtil.KEY_STORE_TYPE),
					props.getProperty(ModelTestUtil.KEY_STORE_PASSWORD),
					props.getProperty(ModelTestUtil.KEY_STORE_ALIAS),
					props.getProperty(ModelTestUtil.ALIAS_PASSWORD),
					new ClassPathResource(props
							.getProperty(ModelTestUtil.KEY_STORE_FILE))
							.getFile().getAbsolutePath());

			docDescifrado = cifradoService.descifrar(
					docDescifrado,
					props.getProperty(ModelTestUtil.KEY_STORE_TYPE),
					props.getProperty(ModelTestUtil.KEY_STORE_PASSWORD),
					props.getProperty(ModelTestUtil.KEY_STORE_ALIAS),
					props.getProperty(ModelTestUtil.ALIAS_PASSWORD),
					new ClassPathResource(props
							.getProperty(ModelTestUtil.KEY_STORE_FILE))
							.getFile().getAbsolutePath());

			System.out
					.println("***************************************\nDoc Descifrado : "
							+ ModelTestUtil.getXMLFlow(docDescifrado));

			// Comparamos que los campos del nodo solicitudes del documento
			// original y descifrado son iguales
			final Node nombreCompletoOriginal = docOriginal
					.getElementsByTagNameNS(
							"http://intermediacion.redsara.es/scsp/esquemas/V3/peticion",
							"NombreCompleto").item(0);
			final Node nombreCompletoDescifrado = docDescifrado
					.getElementsByTagNameNS(
							"http://intermediacion.redsara.es/scsp/esquemas/V3/peticion",
							"NombreCompleto").item(0);

			// Los valores están comparados sin tomar en cuento el tipo de
			// letra, y después de haber
			// sido convertido a mayúsculas y quitado los espacios antes y
			// después de la cadena.
			assertTrue("El mensaje XML no ha sido cifrado correctamente",
					StringUtils.equalsIgnoreCase(StringUtils
							.upperCase(StringUtils.trim(nombreCompletoOriginal
									.getTextContent())), StringUtils
							.upperCase(StringUtils
									.trim(nombreCompletoDescifrado
											.getTextContent()))));

		} catch (final Throwable t) {
			fail("Error inesperado : " + t.getMessage());
		}
	}
}
