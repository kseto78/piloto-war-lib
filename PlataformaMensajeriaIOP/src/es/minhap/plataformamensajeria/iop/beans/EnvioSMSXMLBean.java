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

import es.minhap.plataformamensajeria.iop.services.exceptions.PlataformaBusinessException;

/**
 * Formato del XML de env�o de SMS
 * 
 * <pre>
 *  [ENVIO]
 * 	[NOMBRELOTE]Nombre del lote[/NOMBRELOTE]    
 * 	[SERVICIO]Identificador del servicio[/SERVICIO]
 * 	[USUARIO]Nombre de usuario aplicacion[/USUARIO]
 * 	[PASSWORD]Password usuario aplicacion[/PASSWORD]
 * 	[MENSAJES]
 * 		[MENSAJE]
 * 			[DOCUSUARIO]11111111A[/DOCUSUARIO]
 * 			[CODSIA][/CODSIA]
 * 			[CODORGANISMO][/CODORGANISMO]
 * 			[CODORGANISMOPAGADOR][/CODORGANISMOPAGADOR]
 *    			[IDEXTERNO]Id externo del mensaje[/IDEXTERNO]
 * 			[DESTINATARIO]600990099[/DESTINATARIO]
 * 			[CUERPO]Cuerpo del mensaje[/CUERPO]
 *     	[/MENSAJE]
 * 	[/MENSAJES]
 * [/ENVIO]
 * </pre>
 * 
 * @author i-nercya
 * 
 */
public class EnvioSMSXMLBean {

	private static final String XML = "\nXML:\n";
	private static final String MENSAJE = "\nMensaje: ";
	private static final String ERROR_PROCESANDO_EL_XML_CAUSA = "Error procesando el XML.\nCausa: ";
	static final String TAG_PETICION = "pet:Peticion";
	static final String TAG_NOMBRE_LOTE = "pet:NombreLote";
	static final String TAG_SERVICIO = "pet:Servicio";
	static final String TAG_USUARIO = "pet:Usuario";
	static final String TAG_PASS = "pet:Password";

	static final String TAG_MENSAJES = "pet:Mensasjes";
	static final String TAG_MENSAJE = "pet:MensajeSMS";
	static final String TAG_DOC_USUARIO = "pet:DocUsuario";
	static final String TAG_CODIGO_SIA = "pet:CodSia";
	static final String TAG_CODIGO_ORGANISMO = "pet:CodOrganismo";
	static final String TAG_CODIGO_ORGANISMO_PAGADOR = "pet:CodOrganismoPagador";
	static final String TAG_IDEXTERNO = "pet:IdExterno";
	static final String TAG_DESTINATARIO = "pet:Destinatario";
	static final String TAG_CUERPO = "pet:Cuerpo";

	private String nombreLote = "";
	private String servicio = "";
	private String usuario = "";
	private String pass = "";
	private String organismoPagador ="";
	private String codOrganismo = "";

	private ArrayList<MensajeSMSXMLBean> listadoMensajes = new ArrayList<>();
	private ArrayList<String> listaDestinatarios = new ArrayList<>();

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

	public String getPass() {
		return pass;
	}

	public void setPassword(String pass) {
		this.pass = pass;
	}
		
	public String getCodOrganismo() {
		return codOrganismo;
	}
	
	public void setCodOrganismo(String codOrganismo) {
		this.codOrganismo = codOrganismo;
	}

	public ArrayList<MensajeSMSXMLBean> getListadoMensajes() {
		return new ArrayList<>(listadoMensajes);
	}

	public void setListadoMensajes(ArrayList<MensajeSMSXMLBean> listadoMensajes) {
		this.listadoMensajes = new ArrayList<>(listadoMensajes);
	}

	public String getOrganismoPagador() {
		return organismoPagador;
	}

	public void setOrganismoPagador(String organismoPagador) {
		this.organismoPagador = organismoPagador;
	}
	public void addMensaje(MensajeSMSXMLBean mensaje) {
		if (listadoMensajes != null) {
			listadoMensajes.add(mensaje);
		}
	}

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

	/**
	 * 
	 * @return
	 * @throws IncompleteMessageException
	 * @throws PlataformaBusinessException
	 */
	
	// ESCA-JAVA0043:
	/**
	 * 
	 * @author i-nercya
	 * 
	 */
	class EnvioXMLReader extends DefaultHandler {

		String contenido = ""; // cadena para almacenar el contenido de un tag
		EnvioSMSXMLBean envio = new EnvioSMSXMLBean();
		MensajeSMSXMLBean mensaje = null;
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
				mensaje = new MensajeSMSXMLBean();
			}
			if (TAG_DESTINATARIO.equals(qName)) {
				destinatario = new DestinatarioXMLBean();
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
		 * Devuelve el objeto con el resultado mapeado
		 * 
		 * @return
		 */
		public EnvioSMSXMLBean getEnvio() {
			return envio;
		}
	}
}
