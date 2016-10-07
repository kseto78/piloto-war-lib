package es.mpr.plataformamensajeria.beans;

import java.util.Date;

import com.map.j2ee.auditoria.ifaces.Audit;
/**
 *  <p>Bean para mapear la información de los destinatarios historico
 *  
 *  <p>
 *  
 *  
 *  @author Altran
 */
public class DestinatarioHistoricosBean implements Audit{

	public DestinatarioHistoricosBean() {
		this.destinatarioId=null;
		this.nombre=null;
		this.email=null;
		this.mensajeId=null;
		this.fechaCreacion=null;
		this.creadoPor=null;
		this.fechaModificacion=null;
		this.modificadoPor=null;
		this.tipoDestinatario=null;
		this.fechaHistorificacion=null;
	}
	
	private Integer destinatarioId;
	private String nombre;
	private String email;
	private String mensajeId;
	private Date fechaCreacion;
	private String creadoPor;
	private Date fechaModificacion;
	private String modificadoPor;
	private String tipoDestinatario; //TO, CC, BCC
	private Date fechaHistorificacion;

	
	public Integer getDestinatarioId() {
		return destinatarioId;
	}


	public void setDestinatarioId(Integer destinatarioId) {
		this.destinatarioId = destinatarioId;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getMensajeId() {
		return mensajeId;
	}


	public void setMensajeId(String mensajeId) {
		this.mensajeId = mensajeId;
	}


	public Date getFechaCreacion() {
		return fechaCreacion;
	}


	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
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


	public String getTipoDestinatario() {
		return tipoDestinatario;
	}


	public void setTipoDestinatario(String tipoDestinatario) {
		this.tipoDestinatario = tipoDestinatario;
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
}
