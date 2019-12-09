package es.mpr.plataformamensajeria.beans;

import java.util.Date;

import com.map.j2ee.auditoria.ifaces.Audit;
/**
 *  <p>Bean para mapear la información de los envíos
 *  
 *  <p>
 *  
 *  
 *  @author Altran
 */
public class DestinatarioBean implements Audit{

	private Integer destinatarioId;
	private String nombre;
	private String email;
	private String mensajeId;
	private Date fechaCreacion;
	private String creadoPor;
	private Date fechaModificacion;
	private String modificadoPor;
	private String tipoDestinatario; 
	//TO, CC, BCC

	/**
	 * COnstructor por defecto
	 */
	public DestinatarioBean() {
		this.destinatarioId=null;
		this.nombre=null;
		this.email=null;
		this.mensajeId=null;
		this.fechaCreacion=null;
		this.creadoPor=null;
		this.fechaModificacion=null;
		this.modificadoPor=null;
		this.tipoDestinatario=null;
	}
	
	/**
	 * Obtener destinatarioId
	 * @return
	 */
	public Integer getDestinatarioId() {
		return destinatarioId;
	}

	/**
	 * Modificar destinatarioId
	 * @param destinatarioId
	 */
	public void setDestinatarioId(Integer destinatarioId) {
		this.destinatarioId = destinatarioId;
	}

	/**
	 * Obtener nombre
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Modificar nombre
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtener email
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Modificar email
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Obtener id
	 * @return
	 */
	public String getMensajeId() {
		return mensajeId;
	}

	/**
	 * Modificar mensajeId
	 * @param mensajeId
	 */
	public void setMensajeId(String mensajeId) {
		this.mensajeId = mensajeId;
	}

	/**
	 * Obtener fechaCreacion
	 * @return
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * Modificar fechaCreacion
	 * @param fechaCreacion
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * Obtener creadoPor
	 * @return
	 */
	public String getCreadoPor() {
		return creadoPor;
	}

	/**
	 * Modificar creadoPor
	 * @param creadoPor
	 */
	public void setCreadoPor(String creadoPor) {
		this.creadoPor = creadoPor;
	}

	/**
	 * Obtener fechaModificacion
	 * @return
	 */
	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	/**
	 * Modificar fechaModificacion
	 * @param fechaModificacion
	 */
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	/**
	 * Obtener modificadoPor
	 * @return
	 */
	public String getModificadoPor() {
		return modificadoPor;
	}

	/**
	 * Modificar modificadoPor
	 * @param modificadoPor
	 */
	public void setModificadoPor(String modificadoPor) {
		this.modificadoPor = modificadoPor;
	}

	/**
	 * Obtener tipoDestinatario
	 * @return
	 */
	public String getTipoDestinatario() {
		return tipoDestinatario;
	}

	/**
	 * Modificar tipoDestinatario
	 * @param tipoDestinatario
	 */
	public void setTipoDestinatario(String tipoDestinatario) {
		this.tipoDestinatario = tipoDestinatario;
	}

	/**
	 * Obtener XML
	 */
	@Override
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}
}
