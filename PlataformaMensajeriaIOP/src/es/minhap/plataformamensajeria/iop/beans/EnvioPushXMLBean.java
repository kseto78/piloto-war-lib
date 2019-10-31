package es.minhap.plataformamensajeria.iop.beans;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import es.minhap.plataformamensajeria.iop.beans.lotes.MensajePeticionLotesPushXMLBean;
import es.minhap.plataformamensajeria.iop.services.exceptions.IncompleteMessageException;
import es.minhap.plataformamensajeria.iop.services.exceptions.PlataformaBusinessException;



public class EnvioPushXMLBean {
	
		private static final String XML = "\nXML:\n";
		private static final String MENSAJE = "\nMensaje: ";
		private static final String ERROR_PROCESANDO_EL_XML_CAUSA = "Error procesando el XML.\nCausa: ";
		static final String TAG_PETICION="pet:Peticion";		
		static final String TAG_NOMBRELOTE="pet:NombreLote";
		static final String TAG_SERVICIO="pet:Servicio";
		static final String TAG_USUARIO="pet:Usuario";
		static final String TAG_IDENTIFICADOR_USUARIO="pet:IdentificadorUsuario";
		static final String TAG_PASS="pet:Password";
		static final String TAG_MENSAJES="pet:Mensajes";
		static final String TAG_MENSAJE="pet:MensajePush";
		static final String TAG_TITULO="pet:Titulo";
		static final String TAG_CUERPO="pet:Cuerpo";
		static final String TAG_ICONO="pet:Icono";	
		static final String TAG_IDEXTERNO="pet:IdExterno";	
		static final String TAG_SONIDO="pet:Sonido";
		static final String TAG_CODIGO_SIA = "pet:CodSia";
		static final String TAG_CODIGO_ORGANISMO = "pet:CodOrganismo";
		static final String TAG_DOC_USUARIO = "pet:DocUsuario";
		
		
		
		private String nombreLote="";
		private String servicio="";
		private String usuario="";
		private String pass="";
		private ArrayList<MensajePeticionLotesPushXMLBean> listadoMensajes = new ArrayList<>();
		
		private String docUsuario="";
		private String codSIA="";
		private String codOrganismo="";
		private String identificadorUsuario="";
		private String icono="";
		private String sonido="";
		private String titulo="";
		
				
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
		public String getPass() {
			return pass;
		}
		public void setPass(String pass) {
			this.pass = pass;
		}
		public ArrayList<MensajePeticionLotesPushXMLBean> getListadoMensajes() {
			return new ArrayList<>(listadoMensajes);
		}
		public void setListadoMensajes(ArrayList<MensajePeticionLotesPushXMLBean> listadoMensajes) {
			this.listadoMensajes = new ArrayList<>(listadoMensajes);
		}
		
		public void addMensaje(MensajePeticionLotesPushXMLBean mensaje){
			if(listadoMensajes!=null){
				listadoMensajes.add(mensaje);
			}
		}
		
		
		/**
		 * 
		 * @return
		 * @throws IncompleteMessageException
		 * @throws PlataformaBusinessException
		 */
		
		
		public void loadObjectFromXML(String xmlEnvio) throws PlataformaBusinessException {
			try {
				SAXParserFactory spf = SAXParserFactory.newInstance();
				SAXParser sp = spf.newSAXParser();
				EnvioXMLReader responseXMLObject = new EnvioXMLReader();
				InputSource is = new InputSource(new StringReader(xmlEnvio));

				sp.parse(is, responseXMLObject);
				this.nombreLote = responseXMLObject.getEnvio().getNombreLote();
				this.servicio = responseXMLObject.getEnvio().getServicio();
				this.usuario = responseXMLObject.getEnvio().getUsuario();
				this.pass = responseXMLObject.getEnvio().getPass();
				this.listadoMensajes = responseXMLObject.getEnvio().getListadoMensajes();
			} catch (ParserConfigurationException | SAXException | IOException e3) {
				throw new PlataformaBusinessException(ERROR_PROCESANDO_EL_XML_CAUSA + e3.getCause() + MENSAJE + e3.getMessage() + XML + xmlEnvio);
			}
		}
		
		
		


class EnvioXMLReader extends DefaultHandler {

	String contenido = ""; // cadena para almacenar el contenido de un tag
	EnvioPushXMLBean envio = new EnvioPushXMLBean();
	MensajePushXMLBean mensaje = null;
	DestinatarioXMLBean destinatario = null;
	AdjuntosXMLBean adjunto = null;
	boolean errorMessage = false;
	boolean readingContent = false;
	StringBuilder builder = new StringBuilder();

	/**
		 * 
		 */
	public void startElement(String uri, String localName, String qName, Attributes attributes) {
		builder.setLength(0);
		if (TAG_MENSAJE.equals(qName)) {
			mensaje = new MensajePushXMLBean();
		}
		
	}

	/**
	 * Extrae el contenido que hay entre los tags que se est�n procesando
	 * 
	 * @param buf
	 *            []
	 * @param offset
	 * @param len
	 */
	public void characters(char buf[], int offset, int len) throws SAXException {
		// if(readingContent){
		// contenido += new String(buf, offset, len).replaceAll("\\n",
		// "").replaceAll("\\r", "");
		// }else{
		// contenido = new String(buf, offset, len);
		// }
		builder.append(buf, offset, len);
	}

	/**
	 * Finaliza la lectura del tag qName
	 * 
	 * @param uri
	 * @param localName
	 * @param qName
	 */
	public void endElement(String uri, String localName, String qName) {

		if (TAG_NOMBRELOTE.equals(qName)) {
			envio.setNombreLote(builder.toString());
		}
		if (TAG_SERVICIO.equals(qName)) {
			envio.setServicio(builder.toString());
		}
		if (TAG_USUARIO.equals(qName)) {
			envio.setUsuario(builder.toString());
		}
		if (TAG_PASS.equals(qName)) {
			envio.setPass(builder.toString());
		}
		if (TAG_DOC_USUARIO.equals(qName)) {
			mensaje.setDocUsuario(builder.toString());
		}
		if (TAG_CODIGO_SIA.equals(qName)) {
			mensaje.setCodSIA(builder.toString());
		}
		if (TAG_CODIGO_ORGANISMO.equals(qName)) {
			mensaje.setCodOrganismo(builder.toString());
		}
		
		if (TAG_IDENTIFICADOR_USUARIO.equals(qName)) {
			mensaje.setIdentificadorUsuario(builder.toString());
		}
		
		if (TAG_TITULO.equals(qName)) {
			mensaje.setTitulo(builder.toString());
		}
		
		if (TAG_ICONO.equals(qName)) {
			mensaje.setIcono(builder.toString());
		}
		
		if (TAG_SONIDO.equals(qName)) {
			mensaje.setSonido(builder.toString());
		}							
		if (TAG_IDEXTERNO.equals(qName)) {
			mensaje.setIdExterno(builder.toString());
		}
		if (TAG_CUERPO.equals(qName)) {
			mensaje.setCuerpo(builder.toString());
		}
		
		
		contenido = "";
	}

	/**
	 * Devuelve el objeto con el resultado mapeado
	 * 
	 * @return
	 */
	public EnvioPushXMLBean getEnvio() {
		return envio;
	}
}





public String getDocUsuario() {
	return docUsuario;
}
public void setDocUsuario(String docUsuario) {
	this.docUsuario = docUsuario;
}
public String getCodSIA() {
	return codSIA;
}
public void setCodSIA(String codSIA) {
	this.codSIA = codSIA;
}
public String getCodOrganismo() {
	return codOrganismo;
}
public void setCodOrganismo(String codOrganismo) {
	this.codOrganismo = codOrganismo;
}
public String getIdentificadorUsuario() {
	return identificadorUsuario;
}
public void setIdentificadorUsuario(String identificadorUsuario) {
	this.identificadorUsuario = identificadorUsuario;
}
public String getIcono() {
	return icono;
}
public void setIcono(String icono) {
	this.icono = icono;
}
public String getSonido() {
	return sonido;
}
public void setSonido(String sonido) {
	this.sonido = sonido;
}
public String getTitulo() {
	return titulo;
}
public void setTitulo(String titulo) {
	this.titulo = titulo;
}
	
}

	
