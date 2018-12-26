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

	static final String TAG_PETICION = "pet:Peticion";
	static final String TAG_NOMBRE_LOTE = "pet:NombreLote";
	static final String TAG_SERVICIO = "pet:Servicio";
	static final String TAG_USUARIO = "pet:Usuario";
	static final String TAG_PASSWORD = "pet:Password";

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
	private String password = "";
	private String organismoPagador ="";
	private String codOrganismo = "";

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
		
	public String getCodOrganismo() {
		return codOrganismo;
	}
	
	public void setCodOrganismo(String codOrganismo) {
		this.codOrganismo = codOrganismo;
	}

	public ArrayList<MensajeSMSXMLBean> getListadoMensajes() {
		return new ArrayList<MensajeSMSXMLBean>(listadoMensajes);
	}

	public void setListadoMensajes(ArrayList<MensajeSMSXMLBean> listadoMensajes) {
		this.listadoMensajes = new ArrayList<MensajeSMSXMLBean>(listadoMensajes);
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
			this.password = responseXMLObject.getEnvio().getPassword();
			this.listadoMensajes = responseXMLObject.getEnvio().getListadoMensajes();
		} catch (ParserConfigurationException e) {
			throw new PlataformaBusinessException("Error procesando el XML.\nCausa: " + e.getCause() + "\nMensaje: " + e.getMessage() + "\nXML:\n" + xmlEnvio);
		} catch (SAXException e2) {
			throw new PlataformaBusinessException("Error procesando el XML.\nCausa: " + e2.getCause() + "\nMensaje: " + e2.getMessage() + "\nXML:\n" + xmlEnvio);
		} catch (IOException e3) {
			throw new PlataformaBusinessException("Error procesando el XML.\nCausa: " + e3.getCause() + "\nMensaje: " + e3.getMessage() + "\nXML:\n" + xmlEnvio);
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
			if (qName.equals(TAG_MENSAJE)) {
				mensaje = new MensajeSMSXMLBean();
			}
			if (qName.equals(TAG_DESTINATARIO)) {
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
