/**
 * 
 */
package es.minhap.misim.bus.model.seguridad;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Properties;

import javax.annotation.Resource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import misim.bus.common.exceptions.ApplicationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * @author ludarcos
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { ModelTestUtil.SPRING_CONTEXT_LOCATION })
public class FirmaServiceTest {

	@Autowired
	FirmaService firmaService;

	@Resource(name = "firmaProperties")
	Properties props;

	/**
	 * Test method for
	 * {@link es.minhap.misim.bus.model.seguridad.FirmaServiceImpl#firmarWSSecurity(org.w3c.dom.Document, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}
	 */
	@Test
	public final void testFirmarWSSecurity() {

		// Firma un documento
		try {

			// Recuperamos el documento SOAP sin firmar
			final DocumentBuilderFactory dbf = DocumentBuilderFactory
					.newInstance();
			dbf.setNamespaceAware(true);
			final DocumentBuilder db = dbf.newDocumentBuilder();
			final Document docOrginal = db.parse(new ClassPathResource(props
					.getProperty(ModelTestUtil.PETICION_CLASS_PATH)).getFile());

			// Invocamos al servicio de firma
			final Document docFirmado = firmaService.firmarWSSecurity(
					docOrginal,
					props.getProperty(ModelTestUtil.KEY_STORE_TYPE),
					props.getProperty(ModelTestUtil.KEY_STORE_PASSWORD),
					props.getProperty(ModelTestUtil.KEY_STORE_ALIAS),
					props.getProperty(ModelTestUtil.ALIAS_PASSWORD),
					new ClassPathResource(props
							.getProperty(ModelTestUtil.KEY_STORE_FILE))
							.getFile().getAbsolutePath());

			// Se recuperan todos los nodos Signature del XML
			final NodeList signatureL = docFirmado.getElementsByTagNameNS(
					"http://www.w3.org/2000/09/xmldsig#", "Signature");

			// Si no hay o si no hay uno único, la firma no es válida
			assertTrue(
					"No existe un nodo Signature único trás la firma del documento",
					(signatureL != null) && (signatureL.getLength() == 1));

		} catch (final Throwable t) {
			fail("Error inesperado : " + t.getMessage());
		}

	}

	/**
	 * Test method for
	 * {@link es.minhap.misim.bus.model.seguridad.FirmaServiceImpl#firmarWSSecurity(org.w3c.dom.Document, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public final void testKOFirmarWSSecurity() {

		try {
			// Recuperamos el documento sin firmar, de un formato SOAP inválido
			final DocumentBuilderFactory dbf = DocumentBuilderFactory
					.newInstance();
			dbf.setNamespaceAware(true);
			final DocumentBuilder db = dbf.newDocumentBuilder();
			final Document docOrginal = db.parse(new ClassPathResource(props
					.getProperty(ModelTestUtil.PETICION_INCORRECTA_CLASS_PATH))
					.getFile());

			// Invocamos al servicio de firma
			firmaService.firmarWSSecurity(docOrginal,
					props.getProperty(ModelTestUtil.KEY_STORE_TYPE),
					props.getProperty(ModelTestUtil.KEY_STORE_PASSWORD),
					props.getProperty(ModelTestUtil.KEY_STORE_ALIAS),
					props.getProperty(ModelTestUtil.ALIAS_PASSWORD),
					props.getProperty(ModelTestUtil.KEY_STORE_FILE));

			fail("Una excepción debería haber sido lanzada");
		} catch (final ApplicationException ae) {
			assertTrue(true);
		} catch (final Throwable t) {
			fail("Error inesperado : " + t.getMessage());
		}

	}

	/**
	 * Test method for
	 * {@link es.minhap.misim.bus.model.seguridad.FirmaServiceImpl#validarFirmaWSSecurity(org.w3c.dom.Document)}
	 * .
	 */
	@Test
	public final void testValidarFirmaWSSecurity() {
		// Firma un documento
		try {

			// Recuperamos el documento SOAP sin firmar
			final DocumentBuilderFactory dbf = DocumentBuilderFactory
					.newInstance();
			dbf.setNamespaceAware(true);
			final DocumentBuilder db = dbf.newDocumentBuilder();
			final Document docOrginal = db.parse(new ClassPathResource(props
					.getProperty(ModelTestUtil.PETICION_CLASS_PATH)).getFile());

			// Invocamos al servicio de firma
			final Document docFirmado = firmaService.firmarWSSecurity(
					docOrginal,
					props.getProperty(ModelTestUtil.KEY_STORE_TYPE),
					props.getProperty(ModelTestUtil.KEY_STORE_PASSWORD),
					props.getProperty(ModelTestUtil.KEY_STORE_ALIAS),
					props.getProperty(ModelTestUtil.ALIAS_PASSWORD),
					new ClassPathResource(props
							.getProperty(ModelTestUtil.KEY_STORE_FILE))
							.getFile().getAbsolutePath());

			// Lanzamos la validación de firma
			assertTrue("La validación ha fracasado",
					firmaService.validarFirmaWSSecurity(docFirmado));
		} catch (final Throwable t) {
			fail("Error inesperado : " + t.getMessage());
		}
	}

}
