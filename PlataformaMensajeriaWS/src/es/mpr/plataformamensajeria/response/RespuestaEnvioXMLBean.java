package es.mpr.plataformamensajeria.response;

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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import es.mpr.plataformamensajeria.beans.MensajeSMSXMLBean;
import es.mpr.plataformamensajeria.beans.MensajesXMLBean;
import es.mpr.plataformamensajeria.ws.exceptions.PlataformaBusinessException;
// ESCA-JAVA0137:
/**
 * 
 * @author i-nercya
 *
 */
public class RespuestaEnvioXMLBean {
	static final String TAG_RESPUESTA = "RESPUESTA";
	static final String TAG_LOTE = "LOTE";
	static final String TAG_ESTADO = "ESTADO";
	static final String TAG_ERRORES_GENERALES = "ERRORES";
	static final String TAG_ID = "ID";
	static final String TAG_ERROR = "ERROR";
	static final String TAG_MENSAJES = "MENSAJES";
	static final String TAG_MENSAJE = "MENSAJE";
	static final String TAG_IDEXTERNO = "IDEXTERNO";
	static final String TAG_IDMENSAJE = "IDMENSAJE";
	static final String TAG_ERRORES_MENSAJE = "ERRORES";
	static final String TAG_RESPUESTA_ESTADO = "ESTADO";
	static final String TAG_RESPUESTA_ERRORES = "ERRORES";
	/**
 * 
		 <?xml version="1.0"?>
		 <RESPUESTA>
		 		<ESTADO>0, 1, 2</ESTADO>
				<ERRORES></ERRORES>
			<LOTE>
		<ID></ID>			
				<ERROR CODIGO="01"  DESCRIPCION="Descripción del error" />		
		 	</LOTE>
		<MENSAJES>
		<MENSAJE>
		  	<IDEXTERNO></IDEXTERNO>
		   <IDMENSAJE></IDMENSAJE>
								<ERRORES>
									<ERROR CODIGO="01"  DESCRIPCION="Descripción del error" />
									<ERROR CODIGO="02"  DESCRIPCION="Descripción del error" />
		    					</ERRORES>
		</MENSAJE>
		<MENSAJE>
		  	<IDEXTERNO></IDEXTERNO>
		   <IDMENSAJE></IDMENSAJE>
						<ERRORES>
							<ERROR CODIGO="01"  DESCRIPCION="Descripción del error" />
							<ERROR CODIGO="02"  DESCRIPCION="Descripción del error" />
		    			</ERRORES>
		</MENSAJE>
		</MENSAJES>
		</RESPUESTA>

 */
	private String xmlRespuesta;
	/**
	 * Crear un XML de respuesta según los parámetros que se pasan al método
	 * @param idLote
	 * @param listaMensajesProcesados
	 * @param listaErroresGenerales
	 * @return
	 * @throws PlataformaBusinessException 
	 */
	public static String  generarXMLRespuestaEmail(Integer idLote, ArrayList<MensajesXMLBean> listaMensajesProcesados, ArrayList<String> listaErroresGenerales, ArrayList<String> listaErroresLote) throws PlataformaBusinessException{
		String retorno = "";
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
		//0 = TODO OK 
		//1 = ERRORES GENERALES
		//2 = ERRORES MENSAJES
		int iEstado=0;
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement(TAG_RESPUESTA);
		doc.appendChild(rootElement);
		//Compobar estado
		Element estado = doc.createElement(TAG_RESPUESTA_ESTADO);
		
		Element erroresGenerales = null;
		//Comprobar errores generales
		if(listaErroresGenerales!=null&&listaErroresGenerales.size()>0){
			iEstado=1;
			erroresGenerales = doc.createElement(TAG_RESPUESTA_ERRORES);
			StringBuffer sbf = new StringBuffer();
			for(String errGen : listaErroresGenerales){
				sbf.append(errGen).append(". ");
			}
			erroresGenerales.appendChild(doc.createTextNode(sbf.toString()));
		}
		
		
		Element lote = doc.createElement(TAG_LOTE);
		Element id = doc.createElement(TAG_ID);
		id.appendChild(doc.createTextNode(idLote.toString()));
		lote.appendChild(id);
		for(String errorLote : listaErroresLote){
			iEstado=1;
			Element error = doc.createElement(TAG_ERROR);
			error.setAttribute("CODIGO", idLote.toString());
			error.setAttribute("DESCRIPCION", errorLote);
			lote.appendChild(error);
		}
		
		Element mensajes = doc.createElement(TAG_MENSAJES);
		for(MensajesXMLBean mensajeBean : listaMensajesProcesados){
			Element mensaje = doc.createElement(TAG_MENSAJE);
			Element idExterno = doc.createElement(TAG_IDEXTERNO);
			idExterno.appendChild(doc.createTextNode(mensajeBean.getIdExterno()));
			mensaje.appendChild(idExterno);
			Element idMensaje = doc.createElement(TAG_IDMENSAJE);
			idMensaje.appendChild(doc.createTextNode(mensajeBean.getIdMensaje()));
			mensaje.appendChild(idMensaje);
			Element erroresMensaje = null;
			if(mensajeBean.getListadoErroresMensajes()!=null&&mensajeBean.getListadoErroresMensajes().size()>0){
				iEstado=2;
				erroresMensaje = doc.createElement(TAG_ERRORES_MENSAJE);
				for(String errorStr : mensajeBean.getListadoErroresMensajes()){
					String[] errorSplited = errorStr.split("#");
					Element error = doc.createElement(TAG_ERROR);
					error.setAttribute("CODIGO", errorSplited[0]);
					error.setAttribute("DESCRIPCION", errorSplited[1]);
					erroresMensaje.appendChild(error);
				}
				mensaje.appendChild(erroresMensaje);
			}
			mensajes.appendChild(mensaje);
		}
		estado.appendChild(doc.createTextNode(String.valueOf(iEstado)));
		rootElement.appendChild(estado);
		if(erroresGenerales!=null){
			rootElement.appendChild(erroresGenerales);
		}
		rootElement.appendChild(lote);
		rootElement.appendChild(mensajes);
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = null;
		try {
			transformer = transformerFactory.newTransformer();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			throw new PlataformaBusinessException("Se ha producido un error creando el XML de respuesta.\nCausa: " + e.getCause()+"\nMensaje: "+ e.getMessage());
		}
		DOMSource source = new DOMSource(doc);
		StringWriter writer = new StringWriter();
		StreamResult result = new StreamResult(writer);
 		try {
			transformer.transform(source, result);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			throw new PlataformaBusinessException("Se ha producido un error creando el XML de respuesta.\nCausa: " + e.getCause()+"\nMensaje: "+ e.getMessage());
		}
		return writer.toString();
	}
	
	/**
	 * Crear un XML de respuesta según los parámetros que se pasan al método
	 * @param idLote
	 * @param listaMensajesProcesados
	 * @param listaErroresGenerales
	 * @return
	 */
	public static String  generarXMLRespuestaSMS(Integer idLote, 
			ArrayList<MensajeSMSXMLBean> listaMensajesProcesados, ArrayList<String> listaErroresGenerales, ArrayList<String> listaErroresLote){
		String retorno = "";
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = null;
		int iEstado=0;
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
		Element rootElement = doc.createElement(TAG_RESPUESTA);
		doc.appendChild(rootElement);
		//Compobar estado
		Element estado = doc.createElement(TAG_RESPUESTA_ESTADO);
		
		Element erroresGenerales = null;
		//Comprobar errores generales
		if(listaErroresGenerales!=null&&listaErroresGenerales.size()>0){
			iEstado=1;
			erroresGenerales = doc.createElement(TAG_RESPUESTA_ERRORES);
			StringBuffer sbf = new StringBuffer();
			for(String errGen : listaErroresGenerales){
				sbf.append(errGen);
			}
			erroresGenerales.appendChild(doc.createTextNode(sbf.toString()));
		}
		
		
		Element lote = doc.createElement(TAG_LOTE);
		Element id = doc.createElement(TAG_ID);
		id.appendChild(doc.createTextNode(idLote.toString()));
		//Crear mensajes errores generales
		lote.appendChild(id);
		for(String errorLote : listaErroresLote){
			iEstado=1;
			Element error = doc.createElement(TAG_ERROR);
			error.setAttribute("CODIGO", idLote.toString());
			error.setAttribute("DESCRIPCION", errorLote);
			lote.appendChild(error);
		}
		
		
		Element mensajes = doc.createElement(TAG_MENSAJES);
		for(MensajeSMSXMLBean mensajeBean : listaMensajesProcesados){
			Element mensaje = doc.createElement(TAG_MENSAJE);
			Element idExterno = doc.createElement(TAG_IDEXTERNO);
			idExterno.appendChild(doc.createTextNode(mensajeBean.getIdExterno()));
			mensaje.appendChild(idExterno);
			Element idMensaje = doc.createElement(TAG_IDMENSAJE);
			idMensaje.appendChild(doc.createTextNode(mensajeBean.getIdMensaje()));
			mensaje.appendChild(idMensaje);
			Element erroresMensaje = null;
			if(mensajeBean.getListadoErroresMensajes()!=null&&mensajeBean.getListadoErroresMensajes().size()>0){
				iEstado=2;
				erroresMensaje = doc.createElement(TAG_ERRORES_MENSAJE);
				for(String errorStr : mensajeBean.getListadoErroresMensajes()){
					String[] errorSplited = errorStr.split("#");
					Element error = doc.createElement(TAG_ERROR);
					error.setAttribute("CODIGO", errorSplited[0]);
					error.setAttribute("DESCRIPCION", errorSplited[1]);
					erroresMensaje.appendChild(error);
				}
				mensaje.appendChild(erroresMensaje);
			}
			mensajes.appendChild(mensaje);
		}
		estado.appendChild(doc.createTextNode(String.valueOf(iEstado)));
		rootElement.appendChild(estado);
		if(erroresGenerales!=null){
			rootElement.appendChild(erroresGenerales);
		}
		rootElement.appendChild(lote);
		rootElement.appendChild(mensajes);
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = null;
		try {
			transformer = transformerFactory.newTransformer();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DOMSource source = new DOMSource(doc);
		StringWriter writer = new StringWriter();
		StreamResult result = new StreamResult(writer);
 		try {
			transformer.transform(source, result);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return writer.toString();
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
	 * @throws IOException
	 */
	private void parse() throws PlataformaBusinessException{
	       try{
	            SAXParserFactory spf=SAXParserFactory.newInstance();
	            SAXParser sp = spf.newSAXParser();
	            LibraryXMLReader responseXMLObject=  new LibraryXMLReader();
	            InputSource is = new InputSource(new StringReader(xmlRespuesta));

	            sp.parse(is, responseXMLObject);
	           	this.idLote = responseXMLObject.getResp().getIdLote();
	           	this.listadoErroresLote = responseXMLObject.getResp().getListadoErroresLote();
	           	this.listadoErroresGeneral = responseXMLObject.getResp().getListadoErroresGeneral();
	           	this.listadoMensajes = responseXMLObject.getResp().getListadoMensajes();
	        }catch(ParserConfigurationException e){
	        	throw new PlataformaBusinessException("Se ha producido un error procesando el XML: \n " + xmlRespuesta);
	        }catch(SAXException e2){
	        	throw new PlataformaBusinessException("Se ha producido un error procesando el XML: \n " + xmlRespuesta);
	        } catch (IOException e3) {
	        	throw new PlataformaBusinessException("Se ha producido un error procesando el XML: \n " + xmlRespuesta);
	        }
	       
	}

	
	
	private String idLote;
	private ArrayList<String> listadoErroresLote = new ArrayList<String>();
	public ArrayList<String> getListadoErroresLote() {
		return listadoErroresLote;
	}



	private ArrayList<String> listadoErroresGeneral = new ArrayList<String>();
	private ArrayList<MensajesXMLBean> listadoMensajes = new ArrayList<MensajesXMLBean>();
	public String getXmlRespuesta() {
		return xmlRespuesta;
	}
	protected void setIdLote(String idLote){
		this.idLote = idLote;
	}
	public String getIdLote() {
		return idLote;
	}
	/**
	 * Devuelve la lista de errores genéricos
	 * @return
	 */
	public ArrayList<String> getListadoErroresGeneral() {
		return new ArrayList<String>(listadoErroresGeneral);
	}
	/**
	 * Devuelve el listado de mensajes
	 * @return
	 */
	public ArrayList<MensajesXMLBean> getListadoMensajes() {
		return new ArrayList<MensajesXMLBean>(listadoMensajes);
	}
	/**
	 * Añadir un error de tipo general (lote) a la respuesta
	 * @param errorGeneral
	 */
	public void addErrorGeneral(String errorGeneral){
		if(listadoErroresGeneral!=null){
			this.listadoErroresGeneral.add(errorGeneral);
		}
	}
	/**
	 * Añadir un error de tipo general (lote) a la respuesta
	 * @param errorLote
	 */
	public void addErrorLote(String errorLote){
		if(listadoErroresLote!=null){
			this.listadoErroresLote.add(errorLote);
		}
	}
	/**
	 * Añade un objeto de tipo MensajeXMLBean a la lista de Mensajes
	 * @param mensaje
	 */
	public void addMensaje(MensajesXMLBean mensaje){
		if(listadoMensajes!=null){
			this.listadoMensajes.add(mensaje);
		}
	}
	
// ESCA-JAVA0043:
/**
 * 	
 * @author i-nercya
 *
 */
class LibraryXMLReader extends DefaultHandler {
      	
   	 String contenido="";  // cadena para almacenar el contenido de un tag
   	 String errMsgCodigo="";
   	 String errMsgDescripcion="";
   	 RespuestaEnvioXMLBean resp = new RespuestaEnvioXMLBean();
   	 MensajesXMLBean mensaje = null;
   	int estado=0;
  	 boolean tieneErrores=false;
   	 boolean errorMessage=false;
   	 boolean erroresGenerales=false;

	/**
	 * 
	 */
	public void startElement(String uri, String localName, String qName, Attributes attributes) {
		if(qName.equals(TAG_ERRORES_GENERALES)&&!errorMessage){
			erroresGenerales=true;
		}
		if(qName.equals(TAG_MENSAJE)){
			mensaje = new MensajesXMLBean();
		}
		if(qName.equals(TAG_ERRORES_MENSAJE)){
			errorMessage=true;
		}
		if(qName.equals(TAG_ERROR)){
				errMsgCodigo = attributes.getValue(uri, "CODIGO");
				errMsgDescripcion = attributes.getValue(uri, "DESCRIPCION");
		}
    }

    /**
     * Extrae el contenido que hay entre los tags que se están procesando
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
   	   if(qName.equals(TAG_ID)){
    		   resp.setIdLote(contenido);
	   }
   	   if(qName.equals(TAG_IDMENSAJE)){
   		   mensaje.setIdMensaje(contenido);
   	   }
   	   if(qName.equals(TAG_IDEXTERNO)){
   		   mensaje.setIdExterno(contenido);
   	   }
   	   if(qName.equals(TAG_ERROR)){
   		   if(errorMessage){
   			   mensaje.addErroresMensaje(errMsgCodigo+"#"+errMsgDescripcion);
   			   errMsgCodigo = null;
   			   errMsgDescripcion = null;
   		   }else{
   			   resp.addErrorLote(errMsgCodigo+"#"+errMsgDescripcion);
   		   }
   	   }
   	   if(qName.equals(TAG_ERRORES_MENSAJE)&&!erroresGenerales){
   		   if(errorMessage){
   			   errorMessage=false;
   		   }
   	   }
   	   if(erroresGenerales){
   		   erroresGenerales=false;
   		   tieneErrores=true;
   		   resp.addErrorGeneral(contenido);
   	   }
   	   if(qName.equals(TAG_ESTADO)){
   		   estado = Integer.valueOf(estado);
   	   }
   	   if(qName.equals(TAG_MENSAJE)){
   		   resp.addMensaje(mensaje);
   		   mensaje=null;
   	   }
   	   contenido = "";
    }
    /**
     * Devuelve el objeto RespuestaEnvioXMLBean con el resultado mapeado
     * @return
     */
	public RespuestaEnvioXMLBean getResp() {
		return resp;
	}
	public void setResp(RespuestaEnvioXMLBean resp) {
		this.resp = resp;
	}
   }
}
