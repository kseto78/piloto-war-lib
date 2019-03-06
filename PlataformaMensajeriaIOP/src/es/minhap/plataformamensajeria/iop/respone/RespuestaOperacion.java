package es.minhap.plataformamensajeria.iop.respone;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import es.minhap.plataformamensajeria.iop.beans.ConsultaEstadoBean;
import es.minhap.plataformamensajeria.iop.beans.MensajesXMLBean;
import es.minhap.plataformamensajeria.iop.services.exceptions.PlataformaBusinessException;
import es.minhap.plataformamensajeria.iop.util.Utils;

/**
 * 

 * @author i-nercya
 *
 */
public class RespuestaOperacion {
	
	private static Logger LOG = LoggerFactory.getLogger(RespuestaOperacion.class);
	
	public static final String TAG_OPERACION="OPERACION";
	public static final String TAG_REENVIARLOTE="REENVIARLOTE";
	public static final String TAG_ANULARLOTE="ANULARLOTE";
	public static final String TAG_REENVIARMENSAJE="REENVIARMENSAJE";
	public static final String TAG_ANULARMENSAJE="ANULARMENSAJE";
	public static final String TAG_OK="OK";
	public static final String TAG_ERROR="ERROR";
	private String xmlRespuesta;
	
	
	private String operacion;
	private ArrayList<String> errores = new ArrayList<String>(); 
	
	
	public String getOperacion() {
		return operacion;
	}
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	public ArrayList<String> getErrores() {
		return errores;
	}
	public void setErrores(ArrayList<String> errores) {
		this.errores = errores;
	}
	/**
	 * Format [numero]#[descripcion]
	 * @param error
	 */
	public void addError(String error){
		this.errores.add(error);
	}
	/**
	 * Parsea la respuesta
	 * @param responseXML
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public void parseResponse(String responseXML) throws PlataformaBusinessException{
		this.xmlRespuesta=responseXML;
		parse();
	}
	/**
	 * Parsea la respuesta
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException+
	 */
	private void parse() throws PlataformaBusinessException{
	       try{
	            SAXParserFactory spf=SAXParserFactory.newInstance();
	            SAXParser sp = spf.newSAXParser();
	            OperacionReader responseXMLObject=  new OperacionReader();
	            InputSource is = new InputSource(new StringReader(xmlRespuesta));
	            sp.parse(is, responseXMLObject);
	            this.operacion = responseXMLObject.getResp().getOperacion();
	            this.errores = responseXMLObject.getResp().getErrores();
	        }catch(ParserConfigurationException e){
	        	throw new PlataformaBusinessException("Se ha producido un error procesando el XML: \n " + xmlRespuesta);
	        }catch(SAXException e2){
	        	throw new PlataformaBusinessException("Se ha producido un error procesando el XML: \n " + xmlRespuesta);
	        } catch (IOException e3) {
	        	throw new PlataformaBusinessException("Se ha producido un error procesando el XML: \n " + xmlRespuesta);
	        }
	       
	}
	/**
	 * 
	 * @param operacionTag
	 * @param listaErrores
	 * @return
	 */
	public String generarXMLRespuesta(String operacionTag, String error){
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = null;
		try {
			docBuilder = docFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			try {
				throw new ParserConfigurationException("Error creando documento DOM XML");
			} catch (ParserConfigurationException e1) {
				return "<ERROR></ERROR>";
			}
		}
 
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement(TAG_OPERACION);
		doc.appendChild(rootElement);
		Element tipoOperacion = doc.createElement(operacionTag);
		if(error==null||(error.isEmpty())){
			Element okTag = doc.createElement(TAG_OK);
			tipoOperacion.appendChild(okTag);
		}else if(!error.isEmpty()){
				Element errorTag = doc.createElement(TAG_ERROR);
				String[] splited=error.split("#");
				errorTag.setAttribute("CODIGO", splited[0]);
				errorTag.setAttribute("DESCRIPCION", splited[1]);
				tipoOperacion.appendChild(errorTag);
		}
		rootElement.appendChild(tipoOperacion);
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = null;
		try {
			transformer = transformerFactory.newTransformer();
		} catch (TransformerConfigurationException e) {
			LOG.error("Error en RespuestaOperacion",e);
		}
		DOMSource source = new DOMSource(doc);
		StringWriter writer = new StringWriter();
		StreamResult result = new StreamResult(writer);
 		try {
 			if(transformer != null){
 				transformer.transform(source, result);
 			}
		} catch (TransformerException e) {
			LOG.error("Error en RespuestaOperacion",e);
		}
		return Utils.convertToUTF8(writer.toString());
		
	}
	/**
	 * 
	 * @param str
	 * @return
	 */
	public String getValidValue(String str){
		if(str==null){
			return "";
		}
		return str;
	}
	/**
	 * XML que devuelve la llamada a consultarEstado del servicio web
	 * @return
	 */
	public String getXmlRespuesta() {
		return xmlRespuesta;
	}
// ESCA-JAVA0043:
/**
 * 	
 * @author i-nercya
 *
 */
public class OperacionReader extends DefaultHandler {
      	
   	 String contenido="";
   	 String errMsgCodigo="";
   	 String errMsgDescripcion="";
   	 RespuestaOperacion resp = new RespuestaOperacion();
   	 MensajesXMLBean mensaje = null;
   	 boolean swHistoricoBean = false;
   	ConsultaEstadoBean consultaEstadoBean = null;

   	/**
   	 * 
   	 */
	public void startElement(String uri, String localName, String qName, Attributes attributes) {
			if(qName.equals(TAG_ANULARLOTE)){
				resp.setOperacion(TAG_ANULARLOTE);
			}
			if(qName.equals(TAG_REENVIARLOTE)){
				resp.setOperacion(TAG_REENVIARLOTE);
			}
			if(qName.equals(TAG_ANULARMENSAJE)){
				resp.setOperacion(TAG_ANULARMENSAJE);
			}
			if(qName.equals(TAG_REENVIARMENSAJE)){
				resp.setOperacion(TAG_REENVIARMENSAJE);
			}
			if(qName.equals(TAG_ERROR)){
				errMsgCodigo = attributes.getValue(uri, "CODIGO");
				errMsgDescripcion = attributes.getValue(uri, "DESCRIPCION");
		}

    }

    /**
     * Extrae el contenido que hay entre los tags que se est�n procesando
     * @param buf[]
     * @param offset
     * @param len
     */
    public void characters(char buf[], int offset, int len) throws SAXException
       {
       	contenido = new String(buf, offset, len); 
       }

    /**
     * Finaliza la lectura del tag qName
     * @param uri
     * @param localName
     * @param qName
     */
    public void endElement(String uri, String localName, String qName) {
   	  	if(qName.equals(TAG_ERROR)){
   	  		resp.addError(errMsgCodigo+"#"+errMsgDescripcion);
   	  	}
    }
    /**
     * Devuelve el objeto RespuestaEnvioXMLBean con el resultado mapeado
     * @return
     */
	public RespuestaOperacion getResp() {
		return resp;
	}
	public void setResp(RespuestaOperacion resp) {
		this.resp = resp;
	}
   }
}