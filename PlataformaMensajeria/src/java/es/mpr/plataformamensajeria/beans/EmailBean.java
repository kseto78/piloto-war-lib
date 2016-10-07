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
public class EmailBean implements Audit{

	public EmailBean() {
		this.emailId=null;
		this.loteEnvioId=null;
		this.codigoExterno=null;
		this.cuerpo=null;
		this.asunto=null;
		this.estadoActual=null;
		this.numeroEnvios=null;
		this.fechaCreacion=null;
		this.creadoPor=null;
		this.fechaModificacion=null;
		this.modificadoPor=null;
		this.ultimoIdHistorico=null;	
	}
	
	
	private Integer emailId;
	private Integer loteEnvioId;
	private String codigoExterno;
	private String cuerpo;
	private String asunto;
	private String estadoActual;
	private String numeroEnvios;
	private Date fechaCreacion;
	private String creadoPor;
	private Date fechaModificacion;
	private String modificadoPor;
	private Integer ultimoIdHistorico;
	
	
	public Integer getEmailId() {
		return emailId;
	}


	public void setEmailId(Integer emailId) {
		this.emailId = emailId;
	}


	public Integer getLoteEnvioId() {
		return loteEnvioId;
	}


	public void setLoteEnvioId(Integer loteEnvioId) {
		this.loteEnvioId = loteEnvioId;
	}


	public String getCodigoExterno() {
		return codigoExterno;
	}


	public void setCodigoExterno(String codigoExterno) {
		this.codigoExterno = codigoExterno;
	}


	public String getCuerpo() {
		return cuerpo;
	}


	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}


	public String getAsunto() {
		return asunto;
	}


	public void setAsunto(String cabecera) {
		this.asunto = cabecera;
	}


	public String getEstadoActual() {
		return estadoActual;
	}


	public void setEstadoActual(String estadoActual) {
		this.estadoActual = estadoActual;
	}


	public String getNumeroEnvios() {
		return numeroEnvios;
	}


	public void setNumeroEnvios(String numeroEnvios) {
		this.numeroEnvios = numeroEnvios;
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


	public Integer getUltimoIdHistorico() {
		return ultimoIdHistorico;
	}


	public void setUltimoIdHistorico(Integer ultimoIdHistorico) {
		this.ultimoIdHistorico = ultimoIdHistorico;
	}


	@Override
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}
}
