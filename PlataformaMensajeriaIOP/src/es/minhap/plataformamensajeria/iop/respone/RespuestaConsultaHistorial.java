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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import es.minhap.plataformamensajeria.iop.beans.ConsultaHistoricoBean;
import es.minhap.plataformamensajeria.iop.beans.MensajesXMLBean;
import es.minhap.plataformamensajeria.iop.services.exceptions.PlataformaBusinessException;
import es.minhap.plataformamensajeria.iop.util.Utils;

/**
 * 

 * @author i-nercya
 *
 */
public class RespuestaConsultaHistorial {
	protected static final String R_CONST_1 = "\\nMensaje: ";
	protected static final String R_CONST_2 = "Se ha producido un error creando el XML de respuesta.\\nCausa: ";
	static final String TAG_FILTRO_RESPUESTA="RESPUESTA";
	static final String TAG_FILTRO_FILTRO="FILTRO";
	static final String TAG_FILTRO_IDMENSAJE="IDMENSAJE";
	static final String TAG_FILTRO_IDEXTERNO="IDEXTERNO";
	static final String TAG_FILTRO_USUARIO="USUARIO";
	static final String TAG_FILTRO_PASS="PASSWORD";
	static final String TAG_RESULTADOS="RESULTADOS";
	static final String TAG_ITEM="ITEM";
	static final String TAG_ITEM_IDSERVIDOR="IDSERVIDOR";
	static final String TAG_ITEM_IDMENSAJE="IDMENSAJE";
	static final String TAG_ITEM_IDEXTERNO="IDEXTERNO";
	static final String TAG_ITEM_SERVIDOR="SERVIDOR";
	static final String TAG_ITEM_FECHA="FECHA";
	static final String TAG_ITEM_ESTADO="ESTADO";
	
	private String filtroIdMensaje;
	private String filtroIdExterno;
	private String filtroUsuario;
	private String filtroPassword;
	private ArrayList<ConsultaHistoricoBean> resultados = new ArrayList<>();
	private String xmlRespuesta;
	
	public void addConsultaHistoricoBean(ConsultaHistoricoBean consultaHistorico){
		if(resultados!=null){
			resultados.add(consultaHistorico);
		}
	}
	
	public String getFiltroIdMensaje() {
		return filtroIdMensaje;
	}


	public void setFiltroIdMensaje(String filtroIdMensaje) {
		this.filtroIdMensaje = filtroIdMensaje;
	}
	public String getFiltroIdExterno() {
		return filtroIdExterno;
	}
	protected void setFiltroIdExterno(String filtroIdExterno) {
		this.filtroIdExterno = filtroIdExterno;
	}

	public String getFiltroUsuario() {
		return filtroUsuario;
	}
	protected void setFiltroUsuario(String filtroUsuario) {
		this.filtroUsuario = filtroUsuario;
	}
	public String getFiltroPassword() {
		return filtroPassword;
	}
	protected void setFiltroPassword(String filtroPassword) {
		this.filtroPassword = filtroPassword;
	}
	public ArrayList<ConsultaHistoricoBean> getResultados() {
		return new ArrayList<>(resultados);
	}
	protected void setResultados(ArrayList<ConsultaHistoricoBean> resultados) {
		this.resultados = new ArrayList<>(resultados);
	}
	private void setXmlRespuesta(String xmlRespuesta) {
		this.xmlRespuesta = xmlRespuesta;
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
	            ConsultarHistoricoReader responseXMLObject=  new ConsultarHistoricoReader();
	            InputSource is = new InputSource(new StringReader(xmlRespuesta));
	            sp.parse(is, responseXMLObject);
	             filtroIdMensaje = responseXMLObject.getResp().getFiltroIdMensaje(); 
	        	 filtroIdExterno = responseXMLObject.getResp().getFiltroIdExterno();
	        	 filtroUsuario = responseXMLObject.getResp().getFiltroUsuario();
	        	 filtroPassword = responseXMLObject.getResp().getFiltroPassword();
	        	 resultados = responseXMLObject.getResp().getResultados(); 	            
	        }catch (ParserConfigurationException | SAXException | IOException e3) {
	        	// TODO logger.warn(e3.getMessage(), e3);
				throw new PlataformaBusinessException("Se ha producido un error procesando el XML: \n " + xmlRespuesta);
	        }
	       
	}
	/**
	 * XML que devuelve la llamada a consultarEstado del servicio web
	 * @return
	 */
	public String getXmlRespuesta() {
		return xmlRespuesta;
	}
	
	/**
	 * 
	 * @param filtroServicio
	 * @param filtroCanal
	 * @param filtroAplicacion
	 * @param filtroLote
	 * @param filtroMensaje
	 * @param filtroIdExterno
	 * @param filtroEstado
	 * @param filtroFechaDesde
	 * @param filtroFechaHasta
	 * @param filtroUsuario
	 * @param filtroPassword
	 * @param listaResultados
	 * @return
	 * @throws PlataformaBusinessException 
	 */
	public String generarXMLRespuesta(String filtroIdMensaje,String filtroIdExterno,String filtroUsuario, String filtroPassword,
			ArrayList<ConsultaHistoricoBean> listaResultados) throws PlataformaBusinessException{
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = null;
		try {
			docBuilder = docFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO logger.warn(e.getMessage(), e);
			try {
				throw new ParserConfigurationException("Error creando documento DOM XML");
			} catch (ParserConfigurationException e1) {
				// TODO logger.warn(e1.getMessage(), e1);
				return "<ERROR></ERROR>";
			}
		}
 
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement(TAG_FILTRO_RESPUESTA);
		doc.appendChild(rootElement);
		Element filtro = doc.createElement(TAG_FILTRO_FILTRO);
		//IDMENSAJE
		Element idMensaje = doc.createElement(TAG_FILTRO_IDMENSAJE);
		idMensaje.appendChild(doc.createTextNode(getValidValue(filtroIdMensaje)));
		filtro.appendChild(idMensaje);
		//IDEXTERNO
		Element idExterno = doc.createElement(TAG_FILTRO_IDEXTERNO);
		idExterno.appendChild(doc.createTextNode(getValidValue(filtroIdExterno)));
		filtro.appendChild(idExterno);
		//USUARIO
		Element usuario = doc.createElement(TAG_FILTRO_USUARIO);
		usuario.appendChild(doc.createTextNode(getValidValue(filtroUsuario)));
		filtro.appendChild(usuario);
		//PASSWORD
		Element password = doc.createElement(TAG_FILTRO_PASS);
		password.appendChild(doc.createTextNode(getValidValue(filtroPassword)));
		filtro.appendChild(password);
		rootElement.appendChild(filtro);
		Element resultados = doc.createElement(TAG_RESULTADOS);
		for(ConsultaHistoricoBean historicoBean : listaResultados){
			Element item = doc.createElement(TAG_ITEM);
			//IDSERVIDOR
			Element idServidorItem = doc.createElement(TAG_ITEM_IDSERVIDOR); 
			idServidorItem.appendChild(doc.createTextNode(getValidValue(historicoBean.getIdServidor())));
			item.appendChild(idServidorItem);
			//IDMENSAJE
			Element idMensajeItem = doc.createElement(TAG_ITEM_IDMENSAJE);
			idMensajeItem.appendChild(doc.createTextNode(getValidValue(historicoBean.getIdMensaje())));
			item.appendChild(idMensajeItem);
			//IDEXTERNO
			Element idExternoItem = doc.createElement(TAG_ITEM_IDEXTERNO);
			idExternoItem.appendChild(doc.createTextNode(getValidValue(historicoBean.getIdExterno())));
			item.appendChild(idExternoItem);
			//SERVIDOR
			Element servidorItem = doc.createElement(TAG_ITEM_SERVIDOR);
			servidorItem.appendChild(doc.createTextNode(getValidValue(historicoBean.getServidor())));
			item.appendChild(servidorItem);
			//FECHA
			Element fechaItem = doc.createElement(TAG_ITEM_FECHA);
			fechaItem.appendChild(doc.createTextNode(getValidValue(historicoBean.getFecha())));
			item.appendChild(fechaItem);
			//ESTADO
			Element estadoItem = doc.createElement(TAG_ITEM_ESTADO);
			estadoItem.appendChild(doc.createTextNode(getValidValue(historicoBean.getEstado())));
			item.appendChild(estadoItem);
			resultados.appendChild(item);
		}
		rootElement.appendChild(resultados);
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = null;
		try {
			transformer = transformerFactory.newTransformer();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			throw new PlataformaBusinessException(R_CONST_2 + e.getCause()+R_CONST_1+ e.getMessage());
		}
		DOMSource source = new DOMSource(doc);
		StringWriter writer = new StringWriter();
		StreamResult result = new StreamResult(writer);
 		try {
			transformer.transform(source, result);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			throw new PlataformaBusinessException(R_CONST_2 + e.getCause()+R_CONST_1+ e.getMessage());
		}
		return Utils.convertToUTF8(writer.toString());
		
	}
	
	public String getValidValue(String str){
		if(str==null){
			return "";
		}
		return str;
	}
// ESCA-JAVA0043:
/**
 * 	
 * @author i-nercya
 *
 */
public class ConsultarHistoricoReader extends DefaultHandler {
      	
   	 String contenido="";  // cadena para almacenar el contenido de un tag
   	 String errMsgCodigo="";
   	 String errMsgDescripcion="";
   	 RespuestaConsultaHistorial resp = new RespuestaConsultaHistorial();
   	 MensajesXMLBean mensaje = null;
   	 boolean swHistoricoBean = false;
   	ConsultaHistoricoBean consultaHistoricoBean = null;
	/**
	 * 
	 */
	public void startElement(String uri, String localName, String qName, Attributes attributes) {
			if(TAG_ITEM.equals(qName)){
				consultaHistoricoBean = new ConsultaHistoricoBean();
				swHistoricoBean=true;
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
   	   if(TAG_FILTRO_IDMENSAJE.equals(qName)&&!swHistoricoBean){
   		   resp.setFiltroIdMensaje(contenido);
   	   }
   	   if(TAG_FILTRO_IDEXTERNO.equals(qName)&&!swHistoricoBean){
   		   resp.setFiltroIdExterno(contenido);
   	   }
   	   if(TAG_FILTRO_USUARIO.equals(qName)&&!swHistoricoBean){
   		   resp.setFiltroUsuario(contenido);
   	   }   	   
   	   if(TAG_FILTRO_PASS.equals(qName)&&!swHistoricoBean){
   		   resp.setFiltroPassword(contenido);
   	   }   	   
   	   if(TAG_ITEM_IDMENSAJE.equals(qName)&&swHistoricoBean){
   		   consultaHistoricoBean.setIdMensaje(contenido);
   	   }
   	   if(TAG_ITEM_IDEXTERNO.equals(qName)&&swHistoricoBean){
   		consultaHistoricoBean.setIdExterno(contenido);
   	   }   	   
   	   if(TAG_ITEM_IDSERVIDOR.equals(qName)&&swHistoricoBean){
   		consultaHistoricoBean.setIdServidor(contenido);
   	   }
   	   if(TAG_ITEM_SERVIDOR.equals(qName)&&swHistoricoBean){
   		consultaHistoricoBean.setServidor(contenido);
   	   }   	      	   
   	   if(TAG_ITEM_FECHA.equals(qName)&&swHistoricoBean){
   		consultaHistoricoBean.setFecha(contenido);
   	   }
   	   if(TAG_ITEM_ESTADO.equals(qName)&&swHistoricoBean){
   		consultaHistoricoBean.setEstado(contenido);
   	   }   	      	      	   
   	   if(TAG_ITEM.equals(qName)&&swHistoricoBean){
   		   resp.addConsultaHistoricoBean(consultaHistoricoBean);
   		   consultaHistoricoBean=null;
   		   swHistoricoBean=false;
   	   }   	      	   
   	   contenido = "";
    }
    /**
     * Devuelve el objeto RespuestaEnvioXMLBean con el resultado mapeado
     * @return
     */
	public RespuestaConsultaHistorial getResp() {
		return resp;
	}
	public void setResp(RespuestaConsultaHistorial resp) {
		this.resp = resp;
	}
   }
}