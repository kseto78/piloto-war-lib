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

public class EnvioEmailXMLBean {
	private static final String MENSAJE = "\nMensaje: ";
	private static final String XML = "\nXML:\n";
	private static final String ERROR_PROCESANDO_EL_XML_CAUSA = "Error procesando el XML.\nCausa: ";
	static final String TAG_PETICION = "pet:Peticion";
	static final String TAG_NOMBRE_LOTE = "pet:NombreLote";
	static final String TAG_SERVICIO = "pet:Servicio";
	static final String TAG_USUARIO = "pet:Usuario";
	static final String TAG_PASSWORD = "pet:Password";

	static final String TAG_MENSAJES = "pet:Mensasjes";
	static final String TAG_MENSAJE = "pet:MensajeEmail";
	static final String TAG_DOC_USUARIO = "pet:DocUsuario";
	static final String TAG_CODIGO_SIA = "pet:CodSia";
	static final String TAG_CODIGO_ORGANISMO = "pet:CodOrganismo";
	static final String TAG_IDEXTERNO = "pet:IdExterno";
	static final String TAG_ASUNTO = "pet:Asunto";
	static final String TAG_CUERPO = "pet:Cuerpo";
	static final String TAG_ORIGEN = "pet:Origen";
	static final String TAG_MODO = "pet:Modo";
	static final String TAG_DESTINATARIOS = "pet:Destinatarios";
	static final String TAG_TO = "pet:To";
	static final String TAG_CC = "pet:CC";
	static final String TAG_BCC = "pet:Bcc";
	static final String TAG_ADJUNTOS = "pet:Adjuntos";
	static final String TAG_ADJUNTO = "pet:Adjunto";
	static final String TAG_ADJUNTO_NOMBRE = "pet:Nombre";
	static final String TAG_ADJUNTO_CONTENIDO = "pet:Contenido";
	// static final String TAG_ADJUNTOS_GENERALES="ADJUNTOSGENERALES";

	// Nuevos valores 24/10/2013 - SIM V2
	// static final String TAG_FORMATO_CUERPO="FORMATOCUERPO";
	// static final String TAG_CODIFICACION="CODIFICACION";
	// static final String TAG_PRIORIDAD="PRIORIDAD";

	// static final String TAG_IMAGENES="IMAGENES";
	// static final String TAG_IMAGEN="IMAGEN";
	// static final String TAG_IMAGEN_CID="CID";
	// static final String TAG_IMAGEN_NOMBRE = "NOMBRE";
	// static final String TAG_IMAGEN_CONTENIDO = "CONTENIDO";

	private String nombreLote;
	private String servicio;
	private String usuario;
	private String password;
	private String codOrganismo;
	private ArrayList<MensajesXMLBean> listadoMensajes;
	private ArrayList<AdjuntosXMLBean> listadoAdjuntosGenerales;

	public void addAdjuntoGeneral(AdjuntosXMLBean adjunto) {
		listadoAdjuntosGenerales.add(adjunto);
	}

	public ArrayList<AdjuntosXMLBean> getListadoAdjuntosGenerales() {
		return listadoAdjuntosGenerales;
	}

	public void setListadoAdjuntosGenerales(
			ArrayList<AdjuntosXMLBean> listadoAdjuntosGenerales) {
		this.listadoAdjuntosGenerales = listadoAdjuntosGenerales;
	}

	public EnvioEmailXMLBean() {
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
	
	public String getCodOrganismo() {
		return codOrganismo;
	}

	public void setCodOrganismo(String codOrganismo) {
		this.codOrganismo = codOrganismo;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<MensajesXMLBean> getListadoMensajes() {
		return (ArrayList<MensajesXMLBean>) listadoMensajes.clone();
	}

	public void setListadoMensajes(ArrayList<MensajesXMLBean> listadoMensajes) {
		this.listadoMensajes = listadoMensajes;
	}

	public void addMensaje(MensajesXMLBean mensaje) {
		if (this.listadoMensajes != null) {
			listadoMensajes.add(mensaje);
		}
	}

	public void loadObjectFromXML(String xmlEnvio)
			throws PlataformaBusinessException {
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
			this.listadoMensajes = responseXMLObject.getEnvio()
					.getListadoMensajes();
			// Cambio 2012-03-23
			this.listadoAdjuntosGenerales = responseXMLObject.getEnvio()
					.getListadoAdjuntosGenerales();
		} catch (ParserConfigurationException e) {
			throw new PlataformaBusinessException(
					ERROR_PROCESANDO_EL_XML_CAUSA + e.getCause()
							+ MENSAJE + e.getMessage() + XML
							+ xmlEnvio);
		} catch (SAXException e2) {
			throw new PlataformaBusinessException(
					ERROR_PROCESANDO_EL_XML_CAUSA + e2.getCause()
							+ MENSAJE + e2.getMessage() + XML
							+ xmlEnvio);
		} catch (IOException e3) {
			throw new PlataformaBusinessException(
					ERROR_PROCESANDO_EL_XML_CAUSA + e3.getCause()
							+ MENSAJE + e3.getMessage() + XML
							+ xmlEnvio);
		}
	}

	// ESCA-JAVA0043:
	/**
	 * 
	 * @author i-nercya
	 * 
	 */
	class EnvioXMLReader extends DefaultHandler {

		String contenido = ""; // cadena para almacenar el contenido de un tag
		EnvioEmailXMLBean envio = new EnvioEmailXMLBean();
		MensajesXMLBean mensaje = null;
		DestinatarioXMLBean destinatario = null;
		AdjuntosXMLBean adjunto = null;
		ImagenXMLBean imagen = null;
		boolean errorMessage = false;
		boolean readingContent = false;
		boolean processingAdjuntosGenerales = false;
		boolean inCharacters = false;
		boolean readingAdjunto = false;
		boolean readingImagen = false;
		StringBuilder builder = new StringBuilder();

		/**
	 * 
	 */
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) {
			builder.setLength(0);

			contenido = "";
			if (qName.equals(TAG_MENSAJE)) {
				mensaje = new MensajesXMLBean();
			}
			if (qName.equals(TAG_ADJUNTO)) {
				adjunto = new AdjuntosXMLBean();
				readingAdjunto = true;
			}

			if (qName.equals(TAG_DESTINATARIOS) || qName.equals(TAG_TO)
					|| qName.equals(TAG_CC) || qName.equals(TAG_BCC)) {
				destinatario = new DestinatarioXMLBean();
			}
			if (qName.equals(TAG_TO)) {
				destinatario.setTipoDestinatario(DestinatarioXMLBean.TIPO_TO);
				;
			}
			if (qName.equals(TAG_ADJUNTO_CONTENIDO)) {
				readingContent = true;
			}
			// if(qName.equals(TAG_ADJUNTOS_GENERALES)){
			// processingAdjuntosGenerales=true;
			// }
		}

		/**
		 * Extrae el contenido que hay entre los tags que se est�n procesando
		 * 
		 * @param buf
		 *            []
		 * @param offset
		 * @param len
		 */
		public void characters(char buf[], int offset, int len)
				throws SAXException {
			if (readingContent) {
				String cont = new String(buf, offset, len)
						.replaceAll("\\n", "").replaceAll("\\r", "");
				builder.append(cont);
			} else {
				builder.append(buf, offset, len);
			}
		}

		/**
		 * Devuelve el objeto con el resultado mapeado
		 * 
		 * @return
		 */
		public EnvioEmailXMLBean getEnvio() {
			return envio;
		}
	}
}