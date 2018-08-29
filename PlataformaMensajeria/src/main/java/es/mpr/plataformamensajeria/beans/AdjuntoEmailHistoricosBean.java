package es.mpr.plataformamensajeria.beans;

import java.util.Date;

import com.map.j2ee.auditoria.ifaces.Audit;
/**
 *  <p>Bean para mapear la información de los adjuntos historicos
 *  
 *  <p>
 *  
 *  
 *  @author jgonzvil
 */
public class AdjuntoEmailHistoricosBean implements Audit{

	/**
	 * Constructor por defecto
	 */
	public AdjuntoEmailHistoricosBean() {
		this.adjuntoId = null;
		this.nombre = null;
		this.fechaCreacion=null;
		this.creadoPor = null;
		this.fechaModificacion = null;
		this.modificadoPor = null;
		this.contenido = null;
		this.imagen = null;
		this.fechaHistorificacion = null;
		this.contenidoFile = null;
	}
	private Long adjuntoId;
	private String nombre;
	private Long emailId;
	private Date fechaCreacion;
	private String creadoPor;
	private Date fechaModificacion;
	private String modificadoPor;
	private byte[] contenido;
	private Integer imagen;
	private Date fechaHistorificacion;
	private String contenidoFile;
	
	/**
	 * Modifica el numero de imagen
	 * @param imagen
	 */
	public void setImagen(Integer imagen) {
		this.imagen = imagen;
	}
	/**
	 * Obtiene el numero de imagen
	 * @return
	 */
	public Integer getImagen() {
		return imagen;
	}

	/**
	 * Obtiene el id adjunto
	 * @return
	 */
	public Long getAdjuntoId() {
		return adjuntoId;
	}
	/**
	 * Modifica el id adjunto
	 * @param adjuntoId
	 */
	public void setAdjuntoId(Long adjuntoId) {
		this.adjuntoId = adjuntoId;
	}

	/**
	 * Obtiene el nombre
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Modifica el nombre
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Obtiene el contenido del documento
	 * @return
	 */
	public byte[] getContenido() {
		return contenido;
	}

	/**
	 * Modifica el contenido del documento
	 * @param contenido
	 */
	public void setContenido(byte[] contenido) {
		this.contenido = contenido;
	}
	
	/**
	 * Obtiene el id del email
	 * @return
	 */
	public Long getEmailId() {
		return emailId;
	}

	/**
	 * Modifica el id de email
	 * @param emailId
	 */
	public void setEmailId(Long emailId) {
		this.emailId = emailId;
	}

	/**
	 * Obtiene la fecha de creacion
	 * @return
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * Modifica la fecha de creacion
	 * @param fechaCreacio
	 */
	public void setFechaCreacion(Date fechaCreacio) {
		this.fechaCreacion = fechaCreacio;
	}

	/**
	 * Obtiene la cadena creadoPor
	 * @return
	 */
	public String getCreadoPor() {
		return creadoPor;
	}

	/**
	 * Modifica la cadena creadoPor
	 * @param creadoPor
	 */
	public void setCreadoPor(String creadoPor) {
		this.creadoPor = creadoPor;
	}

	/**
	 * Obtiene la fecha de modificacion
	 * @return
	 */
	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	/**
	 * Modifica la fecha de creacion
	 * @param fechaModificacion
	 */
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	/**
	 * Obtener cadena modificadoPor
	 * @return
	 */
	public String getModificadoPor() {
		return modificadoPor;
	}

	/**
	 * Modificar cadena modificadoPor
	 * @param modificadoPor
	 */
	public void setModificadoPor(String modificadoPor) {
		this.modificadoPor = modificadoPor;
	}

	/**
	 * Obtener cadena del XML
	 */
	@Override
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Obtiene la fecha de historificacion
	 * @return
	 */
	public Date getFechaHistorificacion() {
		return fechaHistorificacion;
	}
	
	/**
	 * Modifica la fecha de historificacion
	 * @param fechaHistorificacion
	 */
	public void setFechaHistorificacion(Date fechaHistorificacion) {
		this.fechaHistorificacion = fechaHistorificacion;
	}
	/**
	 * @return the contenidoFile
	 */
	public String getContenidoFile() {
		return contenidoFile;
	}
	/**
	 * @param contenidoFile the contenidoFile to set
	 */
	public void setContenidoFile(String contenidoFile) {
		this.contenidoFile = contenidoFile;
	}
	
}
