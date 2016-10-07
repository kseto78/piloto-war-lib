package es.mpr.plataformamensajeria.beans;

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

import es.mpr.plataformamensajeria.ws.exceptions.IncompleteMessageException;
import es.mpr.plataformamensajeria.ws.exceptions.PlataformaBusinessException;



/**
 * Formato del XML de env�o de SMS
 <pre>
 [ENVIO]
	[NOMBRELOTE]Nombre del lote[/NOMBRELOTE]    
	[SERVICIO]Identificador del servicio[/SERVICIO]
	[USUARIO]Nombre de usuario aplicacion[/USUARIO]
	[PASSWORD]Password usuario aplicacion[/PASSWORD]
	[MENSAJES]
		[MENSAJE]
			[DOCUSUARIO]11111111A[/DOCUSUARIO]
			[CODSIA][/CODSIA]
			[CODORGANISMO][/CODORGANISMO]
			[CODORGANISMOPAGADOR][/CODORGANISMOPAGADOR]
   			[IDEXTERNO]Id externo del mensaje[/IDEXTERNO]
			[DESTINATARIO]600990099[/DESTINATARIO]
			[CUERPO]Cuerpo del mensaje[/CUERPO]
    	[/MENSAJE]
	[/MENSAJES]
[/ENVIO]
	</pre>
 * @author i-nercya
 *
 */
public class EnvioSMSXMLBean {
		static final String TAG_ENVIO = "ENVIO";
		static final String TAG_NOMBRELOTE="NOMBRELOTE";
		static final String TAG_SERVICIO="SERVICIO";
		static final String TAG_USUARIO="USUARIO";
		static final String TAG_PASSWORD="PASSWORD";
		static final String TAG_MENSAJES="MENSAJES";
		static final String TAG_MENSAJE="MENSAJE";
		static final String TAG_IDEXTERNO="IDEXTERNO";
		static final String TAG_DESTINATARIO="DESTINATARIO";
		static final String TAG_CUERPO="CUERPO";
		
		static final String TAG_DOC_USUARIO = "DOCUSUARIO";
		static final String TAG_CODIGO_SIA = "CODSIA";
		static final String TAG_CODIGO_ORGANISMO = "CODORGANISMO";
		static final String TAG_CODIGO_ORGANISMO_PAGADOR = "CODORGANISMOPAGADOR";
		
		private String nombreLote;
		private String servicio;
		private String usuario;
		private String password;
		private ArrayList<MensajeSMSXMLBean> listadoMensajes = new ArrayList<MensajeSMSXMLBean>();
		private ArrayList<String> listaDestinatarios = new ArrayList<String>();
		public ArrayList<String> getListaDestinatarios() {
			return listaDestinatarios;
		}
		public void setListaDestinatarios(ArrayList<String> listaDestinatarios) {
			this.listaDestinatarios = listaDestinatarios;
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
		public ArrayList<MensajeSMSXMLBean> getListadoMensajes() {
			return new ArrayList<MensajeSMSXMLBean>(listadoMensajes);
		}
		public void setListadoMensajes(ArrayList<MensajeSMSXMLBean> listadoMensajes) {
			this.listadoMensajes = new ArrayList<MensajeSMSXMLBean>(listadoMensajes);
		}
		
		public void addMensaje(MensajeSMSXMLBean mensaje){
			if(listadoMensajes!=null){
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
		        }catch(ParserConfigurationException e){
		        	throw new PlataformaBusinessException("Error procesando el XML.\nCausa: " + e.getCause()+"\nMensaje: " + e.getMessage()+ "\nXML:\n"+xmlEnvio);
		        }catch(SAXException e2){
		        	throw new PlataformaBusinessException("Error procesando el XML.\nCausa: " + e2.getCause()+"\nMensaje: " + e2.getMessage()+ "\nXML:\n"+xmlEnvio);
		        } catch (IOException e3) {
		        	throw new PlataformaBusinessException("Error procesando el XML.\nCausa: " + e3.getCause()+"\nMensaje: " + e3.getMessage()+ "\nXML:\n"+xmlEnvio);
		        }
		}
		/**
		 * 
		 * @return
		 * @throws IncompleteMessageException
		 * @throws PlataformaBusinessException
		 */
		public String toXML() throws IncompleteMessageException, PlataformaBusinessException{
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = null;
			try {
				docBuilder = docFactory.newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				throw new PlataformaBusinessException("Se ha producido un error creando el XML.\nCausa: " + e.getCause()+"\nMensaje: " + e.getMessage());
			}
	 
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement(TAG_ENVIO);
			doc.appendChild(rootElement);
	 
			Element nombreLoteEl = doc.createElement(TAG_NOMBRELOTE);
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
			
			if(listadoMensajes!=null&&listadoMensajes.size()>0){
				Element mensajes = doc.createElement(TAG_MENSAJES);
				for (MensajeSMSXMLBean mensajeBean : this.listadoMensajes){
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
					
					Element codOrganismoPagador = doc.createElement(TAG_CODIGO_ORGANISMO_PAGADOR);
					codOrganismoPagador.appendChild(doc.createCDATASection(mensajeBean.getCodOrganismoPagador()));
					mensaje.appendChild(codOrganismoPagador);
					
					Element idExterno = doc.createElement(TAG_IDEXTERNO);
					idExterno.appendChild(doc.createCDATASection(mensajeBean.getIdExterno()));
					mensaje.appendChild(idExterno);
					
					if(mensajeBean.getListaDestinatarios()!=null&&mensajeBean.getListaDestinatarios().size()>0){
						StringBuffer sbfDestinatario = new StringBuffer();
						for(String destinatarioStr : mensajeBean.getListaDestinatarios()){
							if(sbfDestinatario.length()>0){
								sbfDestinatario.append(";");
							}
							sbfDestinatario.append(destinatarioStr);
						}
						Element destinatarios = doc.createElement(TAG_DESTINATARIO);
						destinatarios.appendChild(doc.createCDATASection(sbfDestinatario.toString()));
						mensaje.appendChild(destinatarios);
					}else{
						throw new IncompleteMessageException("El mensaje est� incompleto. No tiene destinatarios asociados.");
					} 
					Element cuerpo = doc.createElement(TAG_CUERPO);
					if(mensajeBean.getCuerpo()==null||mensajeBean.getCuerpo().isEmpty()){
						throw new IncompleteMessageException("El mensaje est� incompleto. No cuerpo");
					}
					cuerpo.appendChild(doc.createCDATASection(mensajeBean.getCuerpo()));
					mensaje.appendChild(cuerpo);
					mensajes.appendChild(mensaje);
				}
				rootElement.appendChild(mensajes);
			}
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = null;;
			try {
				transformer = transformerFactory.newTransformer();
			} catch (TransformerConfigurationException e) {
				throw new PlataformaBusinessException("Se ha producido un error creando el XML.\nCausa: " + e.getCause()+"\nMensaje: " + e.getMessage());
			}
			DOMSource source = new DOMSource(doc);
			StringWriter writer = new StringWriter();
			StreamResult result = new StreamResult(writer);
	 		try {
				transformer.transform(source, result);
			} catch (TransformerException e) {
				throw new PlataformaBusinessException("Se ha producido un error creando el XML.\nCausa: " + e.getCause()+"\nMensaje: " + e.getMessage());
			}
			return writer.toString();
		}
		//ESCA-JAVA0043:
		/**
		* 	
		* @author i-nercya
		*
		*/
		class EnvioXMLReader extends DefaultHandler {
		   	
			 String contenido="";  // cadena para almacenar el contenido de un tag
			 EnvioSMSXMLBean envio = new EnvioSMSXMLBean();
			 MensajeSMSXMLBean mensaje = null;
			 DestinatarioXMLBean destinatario = null;
			 AdjuntosXMLBean adjunto = null;
			 boolean errorMessage=false;
			 boolean readingContent=false;
			 StringBuilder builder = new StringBuilder();
			/**
			 * 
			 */
			public void startElement(String uri, String localName, String qName, Attributes attributes) {
				builder.setLength(0);
				if(qName.equals(TAG_MENSAJE)){
					mensaje = new MensajeSMSXMLBean();
				}
				if(qName.equals(TAG_DESTINATARIO)){
					destinatario = new DestinatarioXMLBean();
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
//			  if(readingContent){
//				  contenido += new String(buf, offset, len).replaceAll("\\n", "").replaceAll("\\r", "");
//			  }else{
//				  contenido = new String(buf, offset, len);
//			  }
			 builder.append(buf,offset,len);
		    }

		 /**
		  * Finaliza la lectura del tag qName
		  * @param uri
		  * @param localName
		  * @param qName
		  */
		 public void endElement(String uri, String localName, String qName) {
			 if(qName.equals(TAG_NOMBRELOTE)){
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
			 if(qName.equals(TAG_CODIGO_ORGANISMO_PAGADOR)){
				 mensaje.setCodOrganismoPagador(builder.toString());
			 }
			 if(qName.equals(TAG_IDEXTERNO)){
				 mensaje.setIdExterno(builder.toString());
			 }
			 if(qName.equals(TAG_CUERPO)){
				 mensaje.setCuerpo(builder.toString());
			 }
			 if(qName.equals(TAG_DESTINATARIO)){
				 mensaje.addDestinatario(builder.toString());
				 destinatario=null;
			 }
			 if(qName.equals(TAG_MENSAJE)){
				 envio.addMensaje(mensaje);
				 mensaje=null;
			 }
			 contenido = "";
		 }
		 /**
		  * Devuelve el objeto con el resultado mapeado
		  * @return
		  */
			public EnvioSMSXMLBean getEnvio() {
				return envio;
			}
		}
}
