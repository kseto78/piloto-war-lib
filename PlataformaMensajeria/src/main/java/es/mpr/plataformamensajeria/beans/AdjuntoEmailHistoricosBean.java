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
	
	public void setImagen(Integer imagen) {
		this.imagen = imagen;
	}
	public Integer getImagen() {
		return imagen;
	}

	public Long getAdjuntoId() {
		return adjuntoId;
	}

	public void setAdjuntoId(Long adjuntoId) {
		this.adjuntoId = adjuntoId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public byte[] getContenido() {
		return contenido;
	}

	public void setContenido(byte[] contenido) {
		this.contenido = contenido;
	}

	public Long getEmailId() {
		return emailId;
	}

	public void setEmailId(Long emailId) {
		this.emailId = emailId;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacio) {
		this.fechaCreacion = fechaCreacio;
	}

	public String getCreadoPor() {
		return creadoPor;
	}

	public void setCreadoPor(String creadoPor) {
		this.creadoPor = creadoPor;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getModificadoPor() {
		return modificadoPor;
	}

	public void setModificadoPor(String modificadoPor) {
		this.modificadoPor = modificadoPor;
	}

	@Override
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}
	public Date getFechaHistorificacion() {
		return fechaHistorificacion;
	}
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
