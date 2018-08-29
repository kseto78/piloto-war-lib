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

	/**
	 * Constructor de email bean.
	 */
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
	
	
	/**  email id. */
	private Integer emailId;
	
	/**  lote envio id. */
	private Integer loteEnvioId;
	
	/**  codigo externo. */
	private String codigoExterno;
	
	/**  cuerpo. */
	private String cuerpo;
	
	/**  asunto. */
	private String asunto;
	
	/**  estado actual. */
	private String estadoActual;
	
	/**  numero envios. */
	private String numeroEnvios;
	
	/**  fecha creacion. */
	private Date fechaCreacion;
	
	/**  creado por. */
	private String creadoPor;
	
	/**  fecha modificacion. */
	private Date fechaModificacion;
	
	/**  modificado por. */
	private String modificadoPor;
	
	/**  ultimo id historico. */
	private Integer ultimoIdHistorico;
	
	
	/**
	 * Obtener email id.
	 *
	 * @return email id
	 */
	public Integer getEmailId() {
		return emailId;
	}


	/**
	 * Modificar email id.
	 *
	 * @param emailId new email id
	 */
	public void setEmailId(Integer emailId) {
		this.emailId = emailId;
	}


	/**
	 * Obtener lote envio id.
	 *
	 * @return lote envio id
	 */
	public Integer getLoteEnvioId() {
		return loteEnvioId;
	}


	/**
	 * Modificar lote envio id.
	 *
	 * @param loteEnvioId new lote envio id
	 */
	public void setLoteEnvioId(Integer loteEnvioId) {
		this.loteEnvioId = loteEnvioId;
	}


	/**
	 * Obtener codigo externo.
	 *
	 * @return codigo externo
	 */
	public String getCodigoExterno() {
		return codigoExterno;
	}


	/**
	 * Modificar codigo externo.
	 *
	 * @param codigoExterno new codigo externo
	 */
	public void setCodigoExterno(String codigoExterno) {
		this.codigoExterno = codigoExterno;
	}


	/**
	 * Obtener cuerpo.
	 *
	 * @return cuerpo
	 */
	public String getCuerpo() {
		return cuerpo;
	}


	/**
	 * Modificar cuerpo.
	 *
	 * @param cuerpo new cuerpo
	 */
	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}


	/**
	 * Obtener asunto.
	 *
	 * @return asunto
	 */
	public String getAsunto() {
		return asunto;
	}


	/**
	 * Modificar asunto.
	 *
	 * @param cabecera new asunto
	 */
	public void setAsunto(String cabecera) {
		this.asunto = cabecera;
	}


	/**
	 * Obtener estado actual.
	 *
	 * @return estado actual
	 */
	public String getEstadoActual() {
		return estadoActual;
	}


	/**
	 * Modificar estado actual.
	 *
	 * @param estadoActual new estado actual
	 */
	public void setEstadoActual(String estadoActual) {
		this.estadoActual = estadoActual;
	}


	/**
	 * Obtener numero envios.
	 *
	 * @return numero envios
	 */
	public String getNumeroEnvios() {
		return numeroEnvios;
	}


	/**
	 * Modificar numero envios.
	 *
	 * @param numeroEnvios new numero envios
	 */
	public void setNumeroEnvios(String numeroEnvios) {
		this.numeroEnvios = numeroEnvios;
	}


	/**
	 * Obtener fecha creacion.
	 *
	 * @return fecha creacion
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}


	/**
	 * Modificar fecha creacion.
	 *
	 * @param fechaCreacion new fecha creacion
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}


	/**
	 * Obtener creado por.
	 *
	 * @return creado por
	 */
	public String getCreadoPor() {
		return creadoPor;
	}


	/**
	 * Modificar creado por.
	 *
	 * @param creadoPor new creado por
	 */
	public void setCreadoPor(String creadoPor) {
		this.creadoPor = creadoPor;
	}


	/**
	 * Obtener fecha modificacion.
	 *
	 * @return fecha modificacion
	 */
	public Date getFechaModificacion() {
		return fechaModificacion;
	}


	/**
	 * Modificar fecha modificacion.
	 *
	 * @param fechaModificacion new fecha modificacion
	 */
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}


	/**
	 * Obtener modificado por.
	 *
	 * @return modificado por
	 */
	public String getModificadoPor() {
		return modificadoPor;
	}


	/**
	 * Modificar modificado por.
	 *
	 * @param modificadoPor new modificado por
	 */
	public void setModificadoPor(String modificadoPor) {
		this.modificadoPor = modificadoPor;
	}


	/**
	 * Obtener ultimo id historico.
	 *
	 * @return ultimo id historico
	 */
	public Integer getUltimoIdHistorico() {
		return ultimoIdHistorico;
	}


	/**
	 * Modificar ultimo id historico.
	 *
	 * @param ultimoIdHistorico new ultimo id historico
	 */
	public void setUltimoIdHistorico(Integer ultimoIdHistorico) {
		this.ultimoIdHistorico = ultimoIdHistorico;
	}


	/* (non-Javadoc)
	 * @see com.map.j2ee.auditoria.ifaces.Audit#obtenerXML()
	 */
	@Override
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}
}
