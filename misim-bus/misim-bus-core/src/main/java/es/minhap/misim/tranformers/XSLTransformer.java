package es.minhap.misim.tranformers;

import java.nio.charset.Charset;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;

import misim.bus.common.util.XMLUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

import es.minhap.misim.bus.model.exception.ModelException;

public class XSLTransformer {
	
	private static final Logger LOG = LoggerFactory.getLogger(XSLTransformer.class);

	public static Document process(final Document dom, final String xslSheet) throws ModelException {
		
		Document document = null;
		try{
			
			String xml=XMLUtils.dom2xml(dom);
			Document domTrans=XMLUtils.xml2doc(xml.replace("&lt;", "<").replace("&gt;", ">"), Charset.forName("UTF-8"));
			System.out.println("ANTES: "+XMLUtils.dom2xml(domTrans));
			final TransformerFactory factory = TransformerFactory.newInstance();
			final Source xslt = new DOMSource(XMLUtils.xml2doc(xslSheet,Charset.forName("UTF-8")));
			final Transformer transformer = factory.newTransformer(xslt);
			final Source text = new DOMSource(domTrans);
			
			final DOMResult transformedXML = new DOMResult();
			transformer.transform(text, transformedXML);
			
 			document = (Document)transformedXML.getNode();
 			System.out.println("DESPUES: "+XMLUtils.dom2xml(document));
			
		}catch(Exception e){
			//Lanzar error
			LOG.error("Error al adaptar el XML de mensaje SOAP en Transformar XSLT", e);
			throw new ModelException("Error al adaptar el XML de mensaje SOAP en Transformar XSLT", 406);
		}
			
		return document;
	}
}
