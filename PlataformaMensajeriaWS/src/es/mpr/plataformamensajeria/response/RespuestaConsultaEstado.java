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

import es.mpr.plataformamensajeria.beans.ConsultaEstadoBean;
import es.mpr.plataformamensajeria.beans.MensajesXMLBean;
import es.mpr.plataformamensajeria.ws.exceptions.PlataformaBusinessException;

/**
 * 

 * @author i-nercya
 *
 */
public class RespuestaConsultaEstado {
	static final String TAG_FILTRO_RESPUESTA="RESPUESTA";
	static final String TAG_FILTRO_FILTRO="FILTRO";
	static final String TAG_FILTRO_SERVICIO ="SERVICIO";
	static final String TAG_FILTRO_CANAL = "CANAL";
	static final String TAG_FILTRO_APLICACION = "APLICACION";
	static final String TAG_FILTRO_LOTE = "LOTE";
	static final String TAG_FILTRO_MENSAJE="MENSAJE";
	static final String TAG_FILTRO_IDEXTERNO = "IDEXTERNO";
	static final String TAG_FILTRO_ESTADO = "ESTADO";
	static final String TAG_FILTRO_FECHADESDE="FECHADESDE";
	static final String TAG_FILTRO_FECHAHASTA="FECHAHASTA";
	static final String TAG_FILTRO_USUARIO="USUARIO";
	static final String TAG_FILTRO_PASSWORD="PASSWORD";
	static final String TAG_RESULTADOS="RESULTADOS";
	static final String TAG_ITEM="ITEM";
	static final String TAG_ITEM_IDSERVICIO="IDSERVICIO";
	static final String TAG_ITEM_SERVICIO="SERVICIO";
	static final String TAG_ITEM_IDCANAL="IDCANAL";
	static final String TAG_ITEM_CANAL="CANAL";
	static final String TAG_ITEM_IDAPLICACION="IDAPLICACION";
	static final String TAG_ITEM_APLICACION="APLICACION";
	static final String TAG_ITEM_IDLOTE="IDLOTE";
	static final String TAG_ITEM_IDMENSAJE="IDMENSAJE";
	static final String TAG_ITEM_IDEXTERNO="IDEXTERNO";
	static final String TAG_ITEM_ESTADO="ESTADO";
	static final String TAG_ITEM_NUMEROREINTENTOS="NUMEROREINTENTOS";
	static final String TAG_ITEM_FECHA="FECHA";
	
	private String filtroServicio;
	private String filtroCanal;
	private String filtroAplicacion;
	private String filtroLote;
	private String filtroMensaje;
	private String filtroIdExterno;
	private String filtroEstado;
	private String filtroFechaDesde;
	private String filtroFechaHasta;
	private String filtroUsuario;
	private String filtroPassword;
	private ArrayList<ConsultaEstadoBean> resultados = new ArrayList<ConsultaEstadoBean>();
	
	public void addConsultaEstadoBean(ConsultaEstadoBean consultaEstado){
		if(resultados!=null){
			resultados.add(consultaEstado);
		}
	}
	/**
	 * Devuelve el Servicio que se ha enviado en la consulta
	 * @return
	 */
	public String getFiltroServicio() {
		return filtroServicio;
	}
	protected void setFiltroServicio(String filtroServicio) {
		this.filtroServicio = filtroServicio;
	}
	/**
	 * Devuelve el Canal que se ha enviado en la consulta
	 * @return
	 */
	public String getFiltroCanal() {
		return filtroCanal;
	}
	protected void setFiltroCanal(String filtroCanal) {
		this.filtroCanal = filtroCanal;
	}
	/**
	 * Devuelve la Aplicación que se ha enviado en la consulta
	 * @return
	 */
	public String getFiltroAplicacion() {
		return filtroAplicacion;
	}
	protected void setFiltroAplicacion(String filtroAplicacion) {
		this.filtroAplicacion = filtroAplicacion;
	}
	/**
	 * Devuelve el Lote que se ha enviado en la consulta
	 * @return
	 */
	public String getFiltroLote() {
		return filtroLote;
	}
	protected void setFiltroLote(String filtroLote) {
		this.filtroLote = filtroLote;
	}
	/**
	 * Devuelve el Mensaje que se ha enviado en la consulta
	 * @return
	 */
	public String getFiltroMensaje() {
		return filtroMensaje;
	}
	protected void setFiltroMensaje(String filtroMensaje) {
		this.filtroMensaje = filtroMensaje;
	}
	/**
	 * Devuelve el IdExterno que se ha enviado en la consulta
	 * @return
	 */
	public String getFiltroIdExterno() {
		return filtroIdExterno;
	}
	protected void setFiltroIdExterno(String filtroIdExterno) {
		this.filtroIdExterno = filtroIdExterno;
	}
	/**
	 * Devuelve el Estado que se ha enviado en la consulta
	 * @return
	 */
	public String getFiltroEstado() {
		return filtroEstado;
	}
	protected void setFiltroEstado(String filtroEstado) {
		this.filtroEstado = filtroEstado;
	}
	/**
	 * Devuelve la Fecha desde que se ha enviado en la consulta
	 * @return
	 */
	public String getFiltroFechaDesde() {
		return filtroFechaDesde;
	}
	protected void setFiltroFechaDesde(String filtroFechaDesde) {
		this.filtroFechaDesde = filtroFechaDesde;
	}
	/**
	 * Devuelve la Fecha hasta que se ha enviado en la consulta
	 * @return
	 */
	public String getFiltroFechaHasta() {
		return filtroFechaHasta;
	}
	protected void setFiltroFechaHasta(String filtroFechaHasta) {
		this.filtroFechaHasta = filtroFechaHasta;
	}
	/**
	 * Devuelve el Usuario que se ha enviado en la consulta
	 * @return
	 */
	public String getFiltroUsuario() {
		return filtroUsuario;
	}
	protected void setFiltroUsuario(String filtroUsuario) {
		this.filtroUsuario = filtroUsuario;
	}
	/**
	 * Devuelve el Password que se ha enviado en la consulta
	 * @return
	 */
	public String getFiltroPassword() {
		return filtroPassword;
	}
	protected void setFiltroPassword(String filtroPassword) {
		this.filtroPassword = filtroPassword;
	}
	/**
	 * Devuelve los resultados obtenidos en la consulta. 
	 * @return ArrayList de tipos <b>ConsultaEstadoBean</b>Devuelve el Servicio que se ha enviado en la consulta
	 */
	public ArrayList<ConsultaEstadoBean> getResultados() {
		return new ArrayList<ConsultaEstadoBean>(resultados);
	}
	protected void setResultados(ArrayList<ConsultaEstadoBean> resultados) {
		this.resultados = new ArrayList<ConsultaEstadoBean>(resultados);
	}
	private String xmlRespuesta;
	
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
	        	 filtroServicio = responseXMLObject.getResp().getFiltroServicio(); 
	        	 filtroCanal = responseXMLObject.getResp().getFiltroCanal();
	        	 filtroAplicacion = responseXMLObject.getResp().getFiltroAplicacion();
	        	 filtroLote = responseXMLObject.getResp().getFiltroLote();
	        	 filtroMensaje = responseXMLObject.getResp().getFiltroMensaje(); 
	        	 filtroIdExterno = responseXMLObject.getResp().getFiltroIdExterno();
	        	 filtroEstado = responseXMLObject.getResp().getFiltroEstado();
	        	 filtroFechaDesde = responseXMLObject.getResp().getFiltroFechaDesde();
	        	 filtroFechaHasta = responseXMLObject.getResp().getFiltroFechaHasta();
	        	 filtroUsuario = responseXMLObject.getResp().getFiltroUsuario();
	        	 filtroPassword = responseXMLObject.getResp().getFiltroPassword();
	        	 resultados = responseXMLObject.getResp().getResultados(); 	            
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
	public String generarXMLRespuesta(String filtroServicio,String filtroCanal, String filtroAplicacion,
			String filtroLote, String filtroMensaje, String filtroIdExterno, String filtroEstado,
			String filtroFechaDesde, String filtroFechaHasta, String filtroUsuario, String filtroPassword,
			ArrayList<ConsultaEstadoBean> listaResultados) throws PlataformaBusinessException{
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
		Element rootElement = doc.createElement(TAG_FILTRO_RESPUESTA);
		doc.appendChild(rootElement);
		Element filtro = doc.createElement(TAG_FILTRO_FILTRO);
		//Servicio
		Element servicio = doc.createElement(TAG_FILTRO_SERVICIO);
		servicio.appendChild(doc.createTextNode(getValidValue(filtroServicio)));
		filtro.appendChild(servicio);
		//Canal
		Element canal = doc.createElement(TAG_FILTRO_CANAL);
		canal.appendChild(doc.createTextNode(getValidValue(filtroCanal)));
		filtro.appendChild(canal);
		//Aplicacion
		Element aplicacion = doc.createElement(TAG_FILTRO_APLICACION);
		aplicacion.appendChild(doc.createTextNode(getValidValue(filtroAplicacion)));
		filtro.appendChild(aplicacion);
		//Lote
		Element lote = doc.createElement(TAG_FILTRO_LOTE);
		lote.appendChild(doc.createTextNode(getValidValue(filtroLote)));
		filtro.appendChild(lote);
		//Mensaje 
		Element mensaje = doc.createElement(TAG_FILTRO_MENSAJE);
		mensaje.appendChild(doc.createTextNode(getValidValue(filtroMensaje)));
		filtro.appendChild(mensaje);
		//IdExterno 
		Element idExterno = doc.createElement(TAG_FILTRO_IDEXTERNO);
		idExterno.appendChild(doc.createTextNode(getValidValue(filtroIdExterno)));
		filtro.appendChild(idExterno);
		//Estado
		Element estado = doc.createElement(TAG_FILTRO_ESTADO);
		estado.appendChild(doc.createTextNode(getValidValue(filtroEstado)));
		filtro.appendChild(estado);
		//Fecha Desde
		Element fechaDesde = doc.createElement(TAG_FILTRO_FECHADESDE);
		fechaDesde.appendChild(doc.createTextNode(getValidValue(filtroFechaDesde)));
		filtro.appendChild(fechaDesde);
		//Fecha Hasta
		Element fechaHasta = doc.createElement(TAG_FILTRO_FECHAHASTA);
		fechaHasta.appendChild(doc.createTextNode(getValidValue(filtroFechaHasta)));
		filtro.appendChild(fechaHasta);
		//Usuario
		Element usuario = doc.createElement(TAG_FILTRO_USUARIO);
		usuario.appendChild(doc.createTextNode(getValidValue(filtroUsuario)));
		filtro.appendChild(usuario);
		//Password
		Element password = doc.createElement(TAG_FILTRO_PASSWORD);
		password.appendChild(doc.createTextNode(getValidValue(filtroPassword)));
		filtro.appendChild(password);
		rootElement.appendChild(filtro);
		Element resultados = doc.createElement(TAG_RESULTADOS);
		for(ConsultaEstadoBean estadoBean : listaResultados){
			Element item = doc.createElement(TAG_ITEM);
			//IDSERVICIO
			Element idServicio = doc.createElement(TAG_ITEM_IDSERVICIO); 
			idServicio.appendChild(doc.createTextNode(getValidValue(estadoBean.getIdServicio())));
			item.appendChild(idServicio);
			//SERVICIO
			Element servicioItem = doc.createElement(TAG_ITEM_SERVICIO);
			servicioItem.appendChild(doc.createTextNode(getValidValue(estadoBean.getServicio())));
			item.appendChild(servicioItem);
			//IDCANAL
			Element idCanal = doc.createElement(TAG_ITEM_IDCANAL);
			idCanal.appendChild(doc.createTextNode(getValidValue(estadoBean.getIdCanal())));
			item.appendChild(idCanal);
			//CANAL
			Element canalItem = doc.createElement(TAG_ITEM_CANAL);
			canalItem.appendChild(doc.createTextNode(getValidValue(estadoBean.getCanal())));
			item.appendChild(canalItem);
			//IDAPLICACION
			Element idAplicacion = doc.createElement(TAG_ITEM_IDAPLICACION);
			idAplicacion.appendChild(doc.createTextNode(getValidValue(estadoBean.getIdAplicacion())));
			item.appendChild(idAplicacion);
			//APLICACION
			Element aplicacionItem = doc.createElement(TAG_ITEM_APLICACION);
			aplicacionItem.appendChild(doc.createTextNode(getValidValue(estadoBean.getAplicacion())));
			item.appendChild(aplicacionItem);
			//IDLOTE 
			Element idLote = doc.createElement(TAG_ITEM_IDLOTE);
			idLote.appendChild(doc.createTextNode(getValidValue(estadoBean.getIdLote())));
			item.appendChild(idLote);
			//IDMENSAJE
			Element idMensaje = doc.createElement(TAG_ITEM_IDMENSAJE);
			idMensaje.appendChild(doc.createTextNode(getValidValue(estadoBean.getIdMensaje())));
			item.appendChild(idMensaje);
			//IDEXTERNO
			Element idExternoItem = doc.createElement(TAG_ITEM_IDEXTERNO);
			idExternoItem.appendChild(doc.createTextNode(getValidValue(estadoBean.getIdExterno())));
			item.appendChild(idExternoItem);
			//ESTADO
			Element estadoItem = doc.createElement(TAG_ITEM_ESTADO);
			estadoItem.appendChild(doc.createTextNode(getValidValue(estadoBean.getEstado())));
			item.appendChild(estadoItem);
			//NUMREINTENTOS
			//Element numReintentos = doc.createElement(TAG_ITEM_NUMEROREINTENTOS);
			//numReintentos.appendChild(doc.createTextNode(getValidValue(estadoBean.getNumeroReintentos())));
			//item.appendChild(numReintentos);
			//FECHA
			Element fecha = doc.createElement(TAG_ITEM_FECHA);
			fecha.appendChild(doc.createTextNode(getValidValue(estadoBean.getFecha())));
			item.appendChild(fecha);
			resultados.appendChild(item);
		}
		rootElement.appendChild(resultados);
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = null;
		try {
			transformer = transformerFactory.newTransformer();
		} catch (TransformerConfigurationException e) {
			throw new PlataformaBusinessException("Se ha producido un error creando el XML de respuesta.\nCausa: " + e.getCause()+"\nMensaje: "+ e.getMessage());
		}
		DOMSource source = new DOMSource(doc);
		StringWriter writer = new StringWriter();
		StreamResult result = new StreamResult(writer);
 		try {
			transformer.transform(source, result);
		} catch (TransformerException e) {
			throw new PlataformaBusinessException("Se ha producido un error creando el XML de respuesta.\nCausa: " + e.getCause()+"\nMensaje: "+ e.getMessage());
		}
		return writer.toString();
		
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
public class ConsultarHistoricoReader extends DefaultHandler {
      	
   	 String contenido="";
   	 String errMsgCodigo="";
   	 String errMsgDescripcion="";
   	 RespuestaConsultaEstado resp = new RespuestaConsultaEstado();
   	 MensajesXMLBean mensaje = null;
   	 boolean swHistoricoBean = false;
   	ConsultaEstadoBean consultaEstadoBean = null;
	/**
	 * 
	 */
	public void startElement(String uri, String localName, String qName, Attributes attributes) {
			if(qName.equals(TAG_ITEM)){
				consultaEstadoBean = new ConsultaEstadoBean();
				swHistoricoBean=true;
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
   	   if(qName.equals(TAG_FILTRO_SERVICIO)&&!swHistoricoBean){
   		   resp.setFiltroServicio(contenido);
   	   }
   	   if(qName.equals(TAG_FILTRO_CANAL)&&!swHistoricoBean){
   		   resp.setFiltroCanal(contenido);
   	   }
   	   if(qName.equals(TAG_FILTRO_APLICACION)&&!swHistoricoBean){
   		   resp.setFiltroAplicacion(contenido);
   	   }   	   
   	   if(qName.equals(TAG_FILTRO_LOTE)&&!swHistoricoBean){
   		   resp.setFiltroLote(contenido);
   	   }   	   
   	   if(qName.equals(TAG_FILTRO_MENSAJE)&&!swHistoricoBean){
   		   resp.setFiltroMensaje(contenido);
   	   }
   	   if(qName.equals(TAG_FILTRO_IDEXTERNO)&&!swHistoricoBean){
   		   resp.setFiltroIdExterno(contenido);
   	   }
   	   if(qName.equals(TAG_FILTRO_ESTADO)&&!swHistoricoBean){
   		   resp.setFiltroEstado(contenido);
   	   }   	   
   	   if(qName.equals(TAG_FILTRO_FECHADESDE)&&!swHistoricoBean){
   		   resp.setFiltroFechaDesde(contenido);
   	   }
   	   if(qName.equals(TAG_FILTRO_FECHAHASTA)&&!swHistoricoBean){
   		   resp.setFiltroFechaHasta(contenido);
   	   }
   	   if(qName.equals(TAG_FILTRO_USUARIO)&&!swHistoricoBean){
   		   resp.setFiltroUsuario(contenido);
   	   }
   	   if(qName.equals(TAG_FILTRO_PASSWORD)&&!swHistoricoBean){
   		   resp.setFiltroPassword(contenido);
   	   }
   	   if(qName.equals(TAG_ITEM_IDSERVICIO)&&swHistoricoBean){
   		   consultaEstadoBean.setIdServicio(contenido);
   	   }
   	   if(qName.equals(TAG_ITEM_SERVICIO)&&swHistoricoBean){
   		   consultaEstadoBean.setServicio(contenido);
   	   }   	   
   	   if(qName.equals(TAG_ITEM_IDCANAL)&&swHistoricoBean){
   		   consultaEstadoBean.setIdCanal(contenido);
   	   }
   	   if(qName.equals(TAG_ITEM_CANAL)&&swHistoricoBean){
   		   consultaEstadoBean.setCanal(contenido);
   	   }   	      	   
   	   if(qName.equals(TAG_ITEM_IDAPLICACION)&&swHistoricoBean){
   		   consultaEstadoBean.setIdAplicacion(contenido);
   	   }
   	   if(qName.equals(TAG_ITEM_APLICACION)&&swHistoricoBean){
   		   consultaEstadoBean.setAplicacion(contenido);
   	   }   	      	      	   
   	   if(qName.equals(TAG_ITEM_IDLOTE)&&swHistoricoBean){
   		   consultaEstadoBean.setIdLote(contenido);
   	   }   	 
   	   if(qName.equals(TAG_ITEM_IDMENSAJE)&&swHistoricoBean){
   		   consultaEstadoBean.setIdMensaje(contenido);
   	   }   	      	      	   
   	   if(qName.equals(TAG_ITEM_IDEXTERNO)&&swHistoricoBean){
   		   consultaEstadoBean.setIdExterno(contenido);
   	   }   	      	      	   
   	   if(qName.equals(TAG_ITEM_ESTADO)&&swHistoricoBean){
   		   consultaEstadoBean.setEstado(contenido);
   	   }   	      	      	   
   	   /*if(qName.equals(TAG_ITEM_NUMEROREINTENTOS)&&swHistoricoBean){
   		   consultaEstadoBean.setNumeroReintentos(contenido);
   	   } */  	      	      	   
   	   if(qName.equals(TAG_ITEM_FECHA)&&swHistoricoBean){
   		   consultaEstadoBean.setFecha(contenido);
   	   }   	      
   	   if(qName.equals(TAG_ITEM)&&swHistoricoBean){
   		   resp.addConsultaEstadoBean(consultaEstadoBean);
   		   consultaEstadoBean=null;
   		   swHistoricoBean=false;
   	   }   	      	   
   	   contenido = "";
    }
    /**
     * Devuelve el objeto RespuestaEnvioXMLBean con el resultado mapeado
     * @return
     */
	public RespuestaConsultaEstado getResp() {
		return resp;
	}
	public void setResp(RespuestaConsultaEstado resp) {
		this.resp = resp;
	}
   }
}