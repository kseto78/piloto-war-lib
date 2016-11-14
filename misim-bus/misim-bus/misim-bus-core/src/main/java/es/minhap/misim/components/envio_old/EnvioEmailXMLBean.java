package es.minhap.misim.components.envio_old;



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





import org.apache.axis.encoding.Base64;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import es.minhap.misim.components.envio.exceptions.IncompleteMessageException;
import es.minhap.misim.components.envio.exceptions.PlataformaBusinessException;



public class EnvioEmailXMLBean {
	static final String TAG_PETICION="pet:Peticion"; 
	static final String TAG_NOMBRE_LOTE="pet:NombreLote";
	static final String TAG_SERVICIO="pet:Servicio";
	static final String TAG_USUARIO="pet:Usuario";
	static final String TAG_PASSWORD="pet:Password";
	
	
	static final String TAG_MENSAJES="pet:Mensasjes";
	static final String TAG_MENSAJE="pet:MensajeEmail";
	static final String TAG_DOC_USUARIO = "pet:DocUsuario";
	static final String TAG_CODIGO_SIA = "pet:CodSia";
	static final String TAG_CODIGO_ORGANISMO = "pet:CodOrganismo";
	static final String TAG_IDEXTERNO="pet:IdExterno";
	static final String TAG_ASUNTO="pet:Asunto";
	static final String TAG_CUERPO="pet:Cuerpo";
	static final String TAG_ORIGEN="pet:Origen";
	static final String TAG_MODO="pet:Modo";
	static final String TAG_DESTINATARIOS="pet:Destinatarios";
	static final String TAG_TO="pet:To";
	static final String TAG_CC="pet:CC";
	static final String TAG_BCC="pet:Bcc";
	static final String TAG_ADJUNTOS="pet:Adjuntos";
	static final String TAG_ADJUNTO="pet:Adjunto";
	static final String TAG_ADJUNTO_NOMBRE="pet:Nombre";
	static final String TAG_ADJUNTO_CONTENIDO="pet:Contenido"; 
	//static final String TAG_ADJUNTOS_GENERALES="ADJUNTOSGENERALES";
	
	//Nuevos valores 24/10/2013 - SIM V2
//	static final String TAG_FORMATO_CUERPO="FORMATOCUERPO";
//	static final String TAG_CODIFICACION="CODIFICACION";
//	static final String TAG_PRIORIDAD="PRIORIDAD";
	
//	static final String TAG_IMAGENES="IMAGENES";
//	static final String TAG_IMAGEN="IMAGEN";
//	static final String TAG_IMAGEN_CID="CID";
//	static final String TAG_IMAGEN_NOMBRE = "NOMBRE";
//	static final String TAG_IMAGEN_CONTENIDO = "CONTENIDO";
	

	
	private String nombreLote;
	private String servicio;
	private String usuario;
	private String password;
	private ArrayList<MensajesXMLBean> listadoMensajes;
	private ArrayList<AdjuntosXMLBean> listadoAdjuntosGenerales;
	
	public void addAdjuntoGeneral(AdjuntosXMLBean adjunto){
		listadoAdjuntosGenerales.add(adjunto);
	}
	public ArrayList<AdjuntosXMLBean> getListadoAdjuntosGenerales() {
		return listadoAdjuntosGenerales;
	}
	
	public void setListadoAdjuntosGenerales(
			ArrayList<AdjuntosXMLBean> listadoAdjuntosGenerales) {
		this.listadoAdjuntosGenerales = listadoAdjuntosGenerales;
	}
	public EnvioEmailXMLBean(){
		listadoMensajes = new ArrayList<MensajesXMLBean>();
		listadoAdjuntosGenerales = new ArrayList<AdjuntosXMLBean>();
	}
	public String getNombreLote() {
		return nombreLote;
	}

	public void setNombreLote(String nombreLote) {
		this.nombreLote = nombreLote;
	}

	public String getServicio() {
		return servicio;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	@SuppressWarnings("unchecked")
	public ArrayList<MensajesXMLBean> getListadoMensajes() {
		return (ArrayList<MensajesXMLBean>) listadoMensajes.clone();
	}


	public void setListadoMensajes(ArrayList<MensajesXMLBean> listadoMensajes) {
		this.listadoMensajes = listadoMensajes;
	}
	public void addMensaje(MensajesXMLBean mensaje){
		if(this.listadoMensajes!=null){
			listadoMensajes.add(mensaje);
		}
	}
	public void loadObjectFromXML(String xmlEnvio) throws PlataformaBusinessException{
	       try{
	            SAXParserFactory spf=SAXParserFactory.newInstance();
	            SAXParser sp = spf.newSAXParser();
	            
	            EnvioXMLReader responseXMLObject=  new EnvioXMLReader();
	            
	            InputSource is = new InputSource(new StringReader(xmlEnvio));
	            sp.parse(is, responseXMLObject);
	            this.nombreLote = responseXMLObject.getEnvio().getNombreLote();
	            this.servicio = responseXMLObject.getEnvio().getServicio();
	            this.usuario = responseXMLObject.getEnvio().getUsuario();
	            this.password = responseXMLObject.getEnvio().getPassword();
	            this.listadoMensajes = responseXMLObject.getEnvio().getListadoMensajes();
	            //Cambio 2012-03-23
	            this.listadoAdjuntosGenerales = responseXMLObject.getEnvio().getListadoAdjuntosGenerales();
	        }catch(ParserConfigurationException e){
	        	throw new PlataformaBusinessException("Error procesando el XML.\nCausa: " + e.getCause()+"\nMensaje: " + e.getMessage()+ "\nXML:\n"+xmlEnvio);
	        }catch(SAXException e2){
	        	throw new PlataformaBusinessException("Error procesando el XML.\nCausa: " + e2.getCause()+"\nMensaje: " + e2.getMessage()+ "\nXML:\n"+xmlEnvio);
	        } catch (IOException e3) {
	        	throw new PlataformaBusinessException("Error procesando el XML.\nCausa: " + e3.getCause()+"\nMensaje: " + e3.getMessage()+ "\nXML:\n"+xmlEnvio);
	        }
	}
	/**/public String toXML() throws IncompleteMessageException, PlataformaBusinessException{
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = null;
		try {
			docBuilder = docFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new PlataformaBusinessException("Se ha producido un error creando el XML.\nCausa: " + e.getCause()+"\nMensaje: " + e.getMessage());
		}
 
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement(TAG_PETICION);
		doc.appendChild(rootElement);
 
		Element nombreLoteEl = doc.createElement(TAG_NOMBRE_LOTE);
		nombreLoteEl.appendChild(doc.createTextNode(this.nombreLote));
		rootElement.appendChild(nombreLoteEl);
		
		Element servicioEl = doc.createElement(TAG_SERVICIO);
		servicioEl.appendChild(doc.createTextNode(this.servicio));
		rootElement.appendChild(servicioEl);
		
		Element usuarioEl = doc.createElement(TAG_USUARIO);
		usuarioEl.appendChild(doc.createTextNode(this.usuario));
		rootElement.appendChild(usuarioEl);
		
		Element passwordEl = doc.createElement(TAG_PASSWORD);
		passwordEl.appendChild(doc.createTextNode(this.password));
		rootElement.appendChild(passwordEl);
		
		Element mensajes = doc.createElement(TAG_MENSAJES);
		rootElement.appendChild(passwordEl);
		
		if(listadoMensajes!=null&&listadoMensajes.size()>0){
//			Element mensajeEmail = doc.createElement(TAG_MENSAJE);
			for (MensajesXMLBean mensajeBean : this.listadoMensajes){
				Element mensaje = doc.createElement(TAG_MENSAJE);
				
				Element docUsuario = doc.createElement(TAG_DOC_USUARIO);
				docUsuario.appendChild(doc.createCDATASection(mensajeBean.getDocUsuario()));
				mensaje.appendChild(docUsuario);
				
				Element codSia = doc.createElement(TAG_CODIGO_SIA);
				codSia.appendChild(doc.createCDATASection(mensajeBean.getCodSIA()));
				mensaje.appendChild(codSia);
				
				Element codOrganismo = doc.createElement(TAG_CODIGO_ORGANISMO);
				codOrganismo.appendChild(doc.createCDATASection(mensajeBean.getCodOrganismo()));
				mensaje.appendChild(codOrganismo);
				
				Element idExterno = doc.createElement(TAG_IDEXTERNO);
				idExterno.appendChild(doc.createCDATASection(mensajeBean.getIdExterno()));
				mensaje.appendChild(idExterno);
				
				Element formato = doc.createElement(TAG_ASUNTO);
				formato.appendChild(doc.createCDATASection(mensajeBean.getAsunto()));
				mensaje.appendChild(formato);
				
				Element codificacion = doc.createElement(TAG_CUERPO);
				codificacion.appendChild(doc.createTextNode(mensajeBean.getCuerpo()));
				mensaje.appendChild(codificacion);
				
				Element prioridad = doc.createElement(TAG_ORIGEN);
				prioridad.appendChild(doc.createTextNode(mensajeBean.getOrigen()));
				mensaje.appendChild(prioridad);
				
				Element cabecera = doc.createElement(TAG_MODO);
				cabecera.appendChild(doc.createCDATASection(mensajeBean.getModo()));
				mensaje.appendChild(cabecera);

				if(mensajeBean.getListaDestinatarios()!=null&&mensajeBean.getListaDestinatarios().size()>0){
					Element destinatarios = doc.createElement(TAG_DESTINATARIOS);
					for(DestinatarioXMLBean destinatarioBean : mensajeBean.getListaDestinatarios()){
						if(destinatarioBean.getTipoDestinatario()==DestinatarioXMLBean.TIPO_TO){
							Element to = doc.createElement(TAG_TO);
							to.appendChild(doc.createCDATASection(destinatarioBean.getEmailDestinatario()));
							destinatarios.appendChild(to);
						}
						if(destinatarioBean.getTipoDestinatario()==DestinatarioXMLBean.TIPO_CC){
							Element cc = doc.createElement(TAG_CC);
							cc.appendChild(doc.createCDATASection(destinatarioBean.getEmailDestinatario()));
							destinatarios.appendChild(cc);
						}
						if(destinatarioBean.getTipoDestinatario()==DestinatarioXMLBean.TIPO_BCC){
							Element bcc = doc.createElement(TAG_BCC);
							bcc.appendChild(doc.createCDATASection(destinatarioBean.getEmailDestinatario()));
							destinatarios.appendChild(bcc);
						}						
					}
					mensaje.appendChild(destinatarios);
					
				}else{
					throw new IncompleteMessageException("El mensaje est� incompleto. No tiene destinatarios asociados.");
				}
				
				if(mensajeBean.getListaAdjuntos()!=null&&mensajeBean.getListaAdjuntos().size()>0){
					Element adjuntos = doc.createElement(TAG_ADJUNTOS);
					for(AdjuntosXMLBean adjuntoBean : mensajeBean.getListaAdjuntos()){
						Element adjunto = doc.createElement(TAG_ADJUNTO);
						Element nombreAdjunto = doc.createElement(TAG_ADJUNTO_NOMBRE);
						nombreAdjunto.appendChild(doc.createCDATASection(adjuntoBean.getNombre()));
						adjunto.appendChild(nombreAdjunto);
						Element contenidoBase64Adjunto = doc.createElement(TAG_ADJUNTO_CONTENIDO);
						contenidoBase64Adjunto.appendChild(doc.createCDATASection(adjuntoBean.getContenidoBase64()));
						adjunto.appendChild(contenidoBase64Adjunto);
						adjuntos.appendChild(adjunto);				
					}
					mensaje.appendChild(adjuntos);
				}
				/*if(mensajeBean.getListaImagenes()!=null&&mensajeBean.getListaImagenes().size()>0){
					Element imagenes =  doc.createElement(TAG_IMAGENES);
					for(ImagenXMLBean imagenBean : mensajeBean.getListaImagenes()){
						Element imagen = doc.createElement(TAG_IMAGEN);
						Element cid = doc.createElement(TAG_IMAGEN_CID);
						cid.appendChild(doc.createCDATASection(imagenBean.getCid()));
						imagen.appendChild(cid);
						Element contenidoBase64Imagen = doc.createElement(TAG_IMAGEN_CONTENIDO);
						contenidoBase64Imagen.appendChild(doc.createCDATASection(imagenBean.getContenidoBase64()));
						imagen.appendChild(contenidoBase64Imagen);
						imagenes.appendChild(imagen);
					}
				}*/
				mensajes.appendChild(mensaje);
			}
			rootElement.appendChild(mensajes);
		}


		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = null;
		try {
			transformer = transformerFactory.newTransformer();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			throw new PlataformaBusinessException("Se ha producido un error creando el XML.\nCausa: " + e.getCause()+"\nMensaje: " + e.getMessage());
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
	}/**/
	

//ESCA-JAVA0043:
/**
* 	
* @author i-nercya
*
*/
class EnvioXMLReader extends DefaultHandler {
   	
	 String contenido="";  // cadena para almacenar el contenido de un tag
	 EnvioEmailXMLBean envio = new EnvioEmailXMLBean();
	 MensajesXMLBean mensaje = null;
	 DestinatarioXMLBean destinatario = null;
	 AdjuntosXMLBean adjunto = null;
	 ImagenXMLBean imagen = null;
	 boolean errorMessage=false;
	 boolean readingContent=false;
	 boolean processingAdjuntosGenerales=false;
	 boolean inCharacters = false;
	 boolean readingAdjunto = false;
	 boolean readingImagen = false;
	 StringBuilder builder = new StringBuilder();
	/**
	 * 
	 */
	public void startElement(String uri, String localName, String qName, Attributes attributes) {
		builder.setLength(0);

		contenido="";
		if(qName.equals(TAG_MENSAJE)){
			mensaje = new MensajesXMLBean();
		}
		if(qName.equals(TAG_ADJUNTO)){
			adjunto = new AdjuntosXMLBean();
			readingAdjunto=true;
		}
		
		if(qName.equals(TAG_DESTINATARIOS)||qName.equals(TAG_TO)||qName.equals(TAG_CC)||qName.equals(TAG_BCC)){
			destinatario = new DestinatarioXMLBean();
		}
		if(qName.equals(TAG_TO)){
			destinatario.setTipoDestinatario(DestinatarioXMLBean.TIPO_TO);;
		}
		if(qName.equals(TAG_ADJUNTO_CONTENIDO)){
			readingContent=true;
		}
//		if(qName.equals(TAG_ADJUNTOS_GENERALES)){
//			processingAdjuntosGenerales=true;
//		}
 }

	
 /**
  * Extrae el contenido que hay entre los tags que se est�n procesando
  * @param buf[]
  * @param offset
  * @param len
  */
 public void characters(char buf[], int offset, int len) throws SAXException
    {
	  if(readingContent){
		  String cont = new String(buf, offset, len).replaceAll("\\n", "").replaceAll("\\r", "");
		  builder.append(cont);
	  }else{
		  builder.append(buf,offset,len);
	  }
    }

 /**
  * Finaliza la lectura del tag qName
  * @param uri
  * @param localName
  * @param qName
  */
 public void endElement(String uri, String localName, String qName) {
	 if(qName.equals(TAG_NOMBRE_LOTE)){
		envio.setNombreLote(builder.toString());
	 }
	 if(qName.equals(TAG_SERVICIO)){
		envio.setServicio(builder.toString());
	 }
	 if(qName.equals(TAG_USUARIO)){
		envio.setUsuario(builder.toString());
	 }
	 if(qName.equals(TAG_PASSWORD)){
		envio.setPassword(builder.toString());
	 }
	 if(qName.equals(TAG_DOC_USUARIO)){
		 mensaje.setDocUsuario(builder.toString());
	 }
	 if(qName.equals(TAG_CODIGO_SIA)){
		 mensaje.setCodSIA(builder.toString());
	 }
	 if(qName.equals(TAG_CODIGO_ORGANISMO)){
		 mensaje.setCodOrganismo(builder.toString());
	 }
	 if(qName.equals(TAG_IDEXTERNO)){
		 mensaje.setIdExterno(builder.toString());
	 }
	 ///////////////////////
	 //	NUEVOS VALORES
//		 if(qName.equals(TAG_FORMATO_CUERPO)){
//			 mensaje.setFormatoCuerpo(builder.toString());
//		 }
//		 if(qName.equals(TAG_CODIFICACION)){
//			 mensaje.setCodificacion(builder.toString());
//		 }
//		 if(qName.equals(TAG_PRIORIDAD)){
//			 try{
//				 mensaje.setPrioridad(Integer.valueOf(builder.toString()));
//			 }catch(NumberFormatException exc){
//				 mensaje.setPrioridad(0);
//			 }
//		 }
     // FIN NUEVOS VALORES
	 ///////////////////////
	 if(qName.equals(TAG_ASUNTO)){
		 mensaje.setAsunto(builder.toString());
	 }
	 if(qName.equals(TAG_CUERPO)){
		 mensaje.setCuerpo(builder.toString());
	 }
	 if(qName.equals(TAG_ORIGEN)){
		 mensaje.setOrigen(builder.toString());
	 }
	 if(qName.equals(TAG_MODO)){
		 mensaje.setModo(builder.toString());
	 }
	 if(qName.equals(TAG_TO)){
		 destinatario.setEmailDestinatario(builder.toString());
		 destinatario.setTipoDestinatario(DestinatarioXMLBean.TIPO_TO);
		 mensaje.addDestinatario(destinatario);
		 destinatario=null;
	 }
	 if(qName.equals(TAG_CC)){
		 destinatario.setEmailDestinatario(builder.toString());
		 destinatario.setTipoDestinatario(DestinatarioXMLBean.TIPO_CC);
		 mensaje.addDestinatario(destinatario);
		 destinatario=null;
	 }
	 if(qName.equals(TAG_BCC)){
		 destinatario.setEmailDestinatario(builder.toString());
		 destinatario.setTipoDestinatario(DestinatarioXMLBean.TIPO_BCC);
		 mensaje.addDestinatario(destinatario); 
		 destinatario=null; 
	 }
//	 if(qName.equals(TAG_IMAGEN_CID)){
//		 imagen.setCid(builder.toString());
//	 }
	 if(qName.equals(TAG_ADJUNTO_NOMBRE)){
		 adjunto.setNombre(builder.toString());
	 }
//	 if(qName.equals(TAG_IMAGEN_CONTENIDO)&&readingImagen){
//		 byte[] contenidoByteArray = null;
//		 readingContent=false;
//		 contenidoByteArray = Base64.decode(builder.toString());
//		 imagen.setContenido(contenidoByteArray);
//		
//	 }
	 if(qName.equals(TAG_ADJUNTO_CONTENIDO)&&readingAdjunto){
		 byte[] contenidoByteArray = null;
		 readingContent=false;
		 contenidoByteArray = Base64.decode(builder.toString());
		 adjunto.setContenido(contenidoByteArray);
	 }
	 if(qName.equals(TAG_ADJUNTO)){
		 if(!processingAdjuntosGenerales){
			 mensaje.addAdjunto(adjunto);
		 }else{
			 envio.addAdjuntoGeneral(adjunto);
		 }
		 readingAdjunto=false;
	 }
//	 if(qName.equals(TAG_IMAGEN)){
//		 mensaje.addImagen(imagen);
//		 readingImagen=false;
//	 }
	 if(qName.equals(TAG_MENSAJE)){
		 envio.addMensaje(mensaje);
		 mensaje=null;
	 }
//	if(qName.equals(TAG_ADJUNTOS_GENERALES)){
//		processingAdjuntosGenerales=false;
//	}
	 contenido= "";
 }
 /**
  * Devuelve el objeto con el resultado mapeado
  * @return
  */
	public EnvioEmailXMLBean getEnvio() {
		return envio;
	}
}
}