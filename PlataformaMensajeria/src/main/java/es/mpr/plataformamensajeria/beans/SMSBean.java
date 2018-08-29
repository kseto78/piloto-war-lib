package es.mpr.plataformamensajeria.beans;

import java.util.Date;

import com.map.j2ee.auditoria.ifaces.Audit;

/**
 *  <p>Bean para mapear la información de los envíos
 *  
 *  <p>
 *  
 *  
 *  @author i-nercya
 */
public class SMSBean implements Audit{

	/**
	 * Constructor de SMS bean.
	 */
	public SMSBean() {
		this.nombreAplicacion=null;
		this.nombreServicio=null;
		this.destinatario=null;
		this.textoSMS=null;
		this.aplicacionId=null;
		this.servicioId=null;
		this.idEnviosSMS=null;
		this.loteEnvio=null;
		this.loteEnvioId=null;
		this.servidorId=null;
		this.nombreServidor=null;
		this.ultimoEnvio=null;
		this.estado=null;
		this.estadoId=null;
		this.idSMS=null;
	}
	
	/**  nombre aplicacion. */
	private String nombreAplicacion;
	
	/**  nombre servicio. */
	private String nombreServicio;
	
	/**  destinatario. */
	private String destinatario;
	
	/**  texto SMS. */
	private String textoSMS;
	
	/**  aplicacion id. */
	private Integer aplicacionId;
	
	/**  servicio id. */
	private Integer servicioId;
	
	/**  id envios SMS. */
	private Integer idEnviosSMS;
	
	/**  lote envio. */
	private String loteEnvio;
	
	/**  lote envio id. */
	private Integer loteEnvioId;
	
	/**  servidor id. */
	private Integer servidorId;
	
	/**  nombre servidor. */
	private String nombreServidor;
	
	/**  ultimo envio. */
	private Date ultimoEnvio;
	
	/**  estado. */
	private String estado;
	
	/**  canal id. */
	private Integer canalId;
	
	/**  estado id. */
	private Integer estadoId;
	
	/**  id SMS. */
	private String idSMS;

	/**
	 * Obtener nombre aplicacion.
	 *
	 * @return nombre aplicacion
	 */
	public String getNombreAplicacion() {
		return nombreAplicacion;
	}

	/**
	 * Modificar nombre aplicacion.
	 *
	 * @param nombreAplicacion new nombre aplicacion
	 */
	public void setNombreAplicacion(String nombreAplicacion) {
		this.nombreAplicacion = nombreAplicacion;
	}

	/**
	 * Obtener nombre servicio.
	 *
	 * @return nombre servicio
	 */
	public String getNombreServicio() {
		return nombreServicio;
	}

	/**
	 * Modificar nombre servicio.
	 *
	 * @param nombreServicio new nombre servicio
	 */
	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}

	/**
	 * Obtener destinatario.
	 *
	 * @return destinatario
	 */
	public String getDestinatario() {
		return destinatario;
	}

	/**
	 * Modificar destinatario.
	 *
	 * @param destinatario new destinatario
	 */
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	/**
	 * Obtener texto SMS.
	 *
	 * @return texto SMS
	 */
	public String getTextoSMS() {
		return textoSMS;
	}

	/**
	 * Modificar texto SMS.
	 *
	 * @param textoSMS new texto SMS
	 */
	public void setTextoSMS(String textoSMS) {
		this.textoSMS = textoSMS;
	}

	/**
	 * Obtener aplicacion id.
	 *
	 * @return aplicacion id
	 */
	public Integer getAplicacionId() {
		return aplicacionId;
	}

	/**
	 * Modificar aplicacion id.
	 *
	 * @param aplicacionId new aplicacion id
	 */
	public void setAplicacionId(Integer aplicacionId) {
		this.aplicacionId = aplicacionId;
	}

	/**
	 * Obtener servicio id.
	 *
	 * @return servicio id
	 */
	public Integer getServicioId() {
		return servicioId;
	}

	/**
	 * Modificar servicio id.
	 *
	 * @param servicioId new servicio id
	 */
	public void setServicioId(Integer servicioId) {
		this.servicioId = servicioId;
	}

	/**
	 * Obtener id envios SMS.
	 *
	 * @return id envios SMS
	 */
	public Integer getIdEnviosSMS() {
		return idEnviosSMS;
	}

	/**
	 * Modificar id envios SMS.
	 *
	 * @param idEnviosSMS new id envios SMS
	 */
	public void setIdEnviosSMS(Integer idEnviosSMS) {
		this.idEnviosSMS = idEnviosSMS;
	}

	/**
	 * Obtener lote envio.
	 *
	 * @return lote envio
	 */
	public String getLoteEnvio() {
		return loteEnvio;
	}

	/**
	 * Modificar lote envio.
	 *
	 * @param loteEnvio new lote envio
	 */
	public void setLoteEnvio(String loteEnvio) {
		this.loteEnvio = loteEnvio;
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
	 * Obtener servidor id.
	 *
	 * @return servidor id
	 */
	public Integer getServidorId() {
		return servidorId;
	}

	/**
	 * Modificar servidor id.
	 *
	 * @param servidorId new servidor id
	 */
	public void setServidorId(Integer servidorId) {
		this.servidorId = servidorId;
	}

	/**
	 * Obtener nombre servidor.
	 *
	 * @return nombre servidor
	 */
	public String getNombreServidor() {
		return nombreServidor;
	}

	/**
	 * Modificar nombre servidor.
	 *
	 * @param nombreServidor new nombre servidor
	 */
	public void setNombreServidor(String nombreServidor) {
		this.nombreServidor = nombreServidor;
	}

	/**
	 * Obtener ultimo envio.
	 *
	 * @return ultimo envio
	 */
	public Date getUltimoEnvio() {
		return ultimoEnvio;
	}

	/**
	 * Modificar ultimo envio.
	 *
	 * @param ultimoEnvio new ultimo envio
	 */
	public void setUltimoEnvio(Date ultimoEnvio) {
		this.ultimoEnvio = ultimoEnvio;
	}

	/**
	 * Obtener estado.
	 *
	 * @return estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Modificar estado.
	 *
	 * @param estado new estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Obtener canal id.
	 *
	 * @return canal id
	 */
	public Integer getCanalId() {
		return canalId;
	}

	/**
	 * Modificar canal id.
	 *
	 * @param canalId new canal id
	 */
	public void setCanalId(Integer canalId) {
		this.canalId = canalId;
	}

	/**
	 * Obtener estado id.
	 *
	 * @return estado id
	 */
	public Integer getEstadoId() {
		return estadoId;
	}

	/**
	 * Modificar estado id.
	 *
	 * @param estadoId new estado id
	 */
	public void setEstadoId(Integer estadoId) {
		this.estadoId = estadoId;
	}

	/**
	 * Obtener id SMS.
	 *
	 * @return id SMS
	 */
	public String getIdSMS() {
		return idSMS;
	}

	/**
	 * Modificar id SMS.
	 *
	 * @param idSMS new id SMS
	 */
	public void setIdSMS(String idSMS) {
		this.idSMS = idSMS;
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
