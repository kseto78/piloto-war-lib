package es.minhap.misim.tranformers;

import java.nio.charset.Charset;

import misim.bus.common.util.XMLUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

import es.minhap.misim.bus.model.exception.ModelException;

public class XMLConcat {
	
	private static final Logger LOG = LoggerFactory.getLogger(XMLConcat.class);

	public static Document process(final Document domRespuesta, final Document domPeticion)
			throws ModelException {
		
		Document document = null;
		try{
			
			String xmlPeticion = XMLUtils.dom2xml(domPeticion);
			String xmlRespuesta = XMLUtils.dom2xml(domRespuesta);
			
			String xmlResultado = "<root>";
			xmlResultado = xmlResultado + xmlPeticion;
			xmlResultado = xmlResultado + xmlRespuesta;
			xmlResultado = xmlResultado +"</root>";

			document = XMLUtils.xml2doc(xmlResultado,Charset.forName("UTF-8"));
//			System.out.println(xmlResultado);
			
		}catch(Exception e){
			//Lanzar error
			LOG.error("Concatenación Respuesta XML: Error al adaptar el XML de mensaje SOAP en Concatenar Respuesta XML", e);
			throw new ModelException("Error al adaptar el XML de mensaje SOAP en Concatenar Respuesta XML", 406);
		}
			
		return document;
	}
}
