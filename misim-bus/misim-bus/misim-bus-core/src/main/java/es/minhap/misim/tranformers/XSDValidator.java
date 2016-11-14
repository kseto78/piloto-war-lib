package es.minhap.misim.tranformers;

import java.io.IOException;
import java.nio.charset.Charset;

import javax.xml.XMLConstants;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import misim.bus.common.util.XMLUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import es.minhap.misim.bus.model.exception.ModelException;

public class XSDValidator {

	private static final Logger LOG = LoggerFactory.getLogger(XSDValidator.class);
	
	public static boolean process(final String xml, final String xsd)throws ModelException {
		
		try{
          
			SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = factory.newSchema(new DOMSource(XMLUtils.xml2doc(xsd, Charset.forName("UTF-8"))));
            Validator validator = schema.newValidator();
			validator.validate(new DOMSource(XMLUtils.xml2doc(xml, Charset.forName("UTF-8"))));
			
		} catch (IOException | SAXException e) {
			//Lanzar error
			LOG.error("La estructura del fichero recibido no corresponde con el esquema", e);
			throw new ModelException("La estructura del fichero recibido no corresponde con el esquema", 401);
        } catch (Exception e){
        	//Lanzar error
			LOG.error("Validar XSD: Error de sistema Validar XSD", e);
			throw new ModelException("Error de sistema Validar XSD", 502);
        }
			
		return true;
	}
}
