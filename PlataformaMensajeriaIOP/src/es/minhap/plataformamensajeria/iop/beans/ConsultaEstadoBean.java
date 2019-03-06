package es.minhap.plataformamensajeria.iop.beans;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import es.minhap.plataformamensajeria.iop.services.exceptions.PlataformaBusinessException;
/**
 * 
 * @author i-nercya
 *
 */
public class ConsultaEstadoBean implements Serializable {


	private static final String XML = "\nXML:\n";
	private static final String MENSAJE = "\nMensaje: ";
	private static final String ERROR_PROCESANDO_EL_XML_CAUSA = "Error procesando el XML.\nCausa: ";
	static final String TAG_USUARIO="pet:Usuario";
	static final String TAG_PASSWORD="pet:Password";

	static final String TAG_FILTRO="pet:Filtro";
	static final String TAG_APLICACION="pet:Aplicacion";

	static final String TAG_SERVICIO="pet:Servicio";
	static final String TAG_LOTE="pet:Lote";
	static final String TAG_MENSAJE="pet:Mensaje";
	static final String TAG_IDMENSAJE="pet:IdMensaje";
	static final String TAG_IDEXTERNO="pet:IdExterno";
	static final String TAG_ESTADO="pet:Estado";
	static final String TAG_FECHADESDE="pet:FechaDesde";
	static final String TAG_FECHAHASTA="pet:FechaHasta";
	static final String TAG_DOC_USUARIO = "pet:DocUsuario";
	static final String TAG_CODIGO_SIA = "pet:CodSia";
	static final String TAG_CODIGO_ORGANISMO = "pet:CodOrganismo";
	static final String TAG_CODIGO_ORGANISMOPAGADOR = "pet:CodOrganismoPagador";




	private static final long serialVersionUID = 1L;

	private String idServicio;
	private String usuario;
	private String password;
	private String servicio;
	private String idCanal;
	private String canal;
	private String idAplicacion;
	private String aplicacion;
	private String lote;
	private String idMensaje;
	private String idExterno;
	private String estado;
	private String fechaDesde;
	private String fechaHasta;
	private String docUsuario;
	private String codSia;
	private String codOrganismo;
	private String codOrganismoPagador;
	@Deprecated
	private String numeroReintentos;
	private String fecha;

	private transient FiltroXMLBean filtro;



	public FiltroXMLBean getFiltro() {
		return filtro;
	}

	public void setFiltro(FiltroXMLBean filtro) {
		this.filtro = filtro;
	}

	public String getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(String idServicio) {
		this.idServicio = idServicio;
	}

	public String getServicio() {
		return servicio;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	public String getIdCanal() {
		return idCanal;
	}

	public void setIdCanal(String idCanal) {
		this.idCanal = idCanal;
	}

	public String getCanal() {
		return canal;
	}

	public void setCanal(String canal) {
		this.canal = canal;
	}

	public String getIdAplicacion() {
		return idAplicacion;
	}

	public void setIdAplicacion(String idAplicacion) {
		this.idAplicacion = idAplicacion;
	}

	public String getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public String getIdMensaje() {
		return idMensaje;
	}

	public void setIdMensaje(String idMensaje) {
		this.idMensaje = idMensaje;
	}

	public String getIdExterno() {
		return idExterno;
	}

	public void setIdExterno(String idExterno) {
		this.idExterno = idExterno;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public String getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public String getDocUsuario() {
		return docUsuario;
	}

	public void setDocUsuario(String docUsuario) {
		this.docUsuario = docUsuario;
	}

	public String getCodSia() {
		return codSia;
	}

	public void setCodSia(String codSia) {
		this.codSia = codSia;
	}

	public String getCodOrganismo() {
		return codOrganismo;
	}

	public void setCodOrganismo(String codOrganismo) {
		this.codOrganismo = codOrganismo;
	}

	public String getCodOrganismoPagador() {
		return codOrganismoPagador;
	}

	public void setCodOrganismoPagador(String codOrganismoPagador) {
		this.codOrganismoPagador = codOrganismoPagador;
	}

	public String getNumeroReintentos() {
		return numeroReintentos;
	}

	public void setNumeroReintentos(String numeroReintentos) {
		this.numeroReintentos = numeroReintentos;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public void loadObjectFromXML(String xmlEnvio) throws PlataformaBusinessException{
		try{
			SAXParserFactory spf=SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();

			ConsultaEstadoXMLReader responseXMLObject=  new ConsultaEstadoXMLReader();

			InputSource is = new InputSource(new StringReader(xmlEnvio));
			sp.parse(is, responseXMLObject);

			this.usuario = responseXMLObject.getEnvio().getUsuario();
			this.password = responseXMLObject.getEnvio().getPassword();
			this.filtro=responseXMLObject.getEnvio().getFiltro();
			// this.listadoMensajes = responseXMLObject.getEnvio().getListadoMensajes();
			//Cambio 2012-03-23
			//this.listadoAdjuntosGenerales = responseXMLObject.getEnvio().getListadoAdjuntosGenerales();
		}catch(ParserConfigurationException e){
			throw new PlataformaBusinessException(ERROR_PROCESANDO_EL_XML_CAUSA + e.getCause()+MENSAJE + e.getMessage()+ XML+xmlEnvio);
		}catch(SAXException e2){
			throw new PlataformaBusinessException(ERROR_PROCESANDO_EL_XML_CAUSA + e2.getCause()+MENSAJE + e2.getMessage()+ XML+xmlEnvio);
		} catch (IOException e3) {
			throw new PlataformaBusinessException(ERROR_PROCESANDO_EL_XML_CAUSA + e3.getCause()+MENSAJE + e3.getMessage()+ XML+xmlEnvio);
		}
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

	class ConsultaEstadoXMLReader extends DefaultHandler {

		String contenido="";  // cadena para almacenar el contenido de un tag
		ConsultaEstadoBean consulta = new ConsultaEstadoBean();
		FiltroXMLBean filtro = null;
		MensajesXMLBean mensaje = null;
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
			if(qName.equals(TAG_FILTRO)){
				filtro = new FiltroXMLBean();
			}
			if(qName.equals(TAG_MENSAJE)){
				mensaje = new MensajesXMLBean();
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

			
			if(qName.equals(TAG_USUARIO)){
				consulta.setUsuario(builder.toString());
			}
			if(qName.equals(TAG_PASSWORD)){
				consulta.setPassword(builder.toString());
			}

			if(qName.equals(TAG_APLICACION)){
				filtro.setIdAplicacion(builder.toString());
			}
			if(qName.equals(TAG_SERVICIO)){
				filtro.setIdServicio(builder.toString());
			}
			if(qName.equals(TAG_LOTE)){
				filtro.setLote(builder.toString());
			}

			if(qName.equals(TAG_IDMENSAJE)){
				mensaje.setIdMensaje(builder.toString());
			}
			if(qName.equals(TAG_IDEXTERNO)){
				mensaje.setIdExterno(builder.toString());
			}

			if(qName.equals(TAG_ESTADO)){
				filtro.setEstado(builder.toString());
			}
			if(qName.equals(TAG_FECHADESDE)){
				filtro.setFechaDesde(builder.toString());
			}
			if(qName.equals(TAG_FECHAHASTA)){
				filtro.setFechaHasta(builder.toString());
			}
			if(qName.equals(TAG_DOC_USUARIO)){
				filtro.setDocUsuario(builder.toString());
			}
			if(qName.equals(TAG_CODIGO_SIA)){
				filtro.setCodSia(builder.toString());
			}
			if(qName.equals(TAG_CODIGO_ORGANISMO)){
				filtro.setCodOrganismo(builder.toString());
			}
			if(qName.equals(TAG_CODIGO_ORGANISMOPAGADOR)){
				filtro.setCodOrganismoPagador(builder.toString());
			}

			if(qName.equals(TAG_FILTRO)){
				filtro.addMensaje(mensaje);
				consulta.setFiltro(filtro);
			}


			contenido= "";
		}
		/**
		 * Devuelve el objeto con el resultado mapeado
		 * @return
		 */

		public ConsultaEstadoBean getEnvio() {
			return consulta;
		}


	}

}
