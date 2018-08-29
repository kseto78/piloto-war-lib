package es.mpr.plataformamensajeria.beans;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.map.j2ee.auditoria.ifaces.Audit;

/**
 *  <p>Bean para mapear la información de los envios historico
 *  
 *  <p>
 *  
 *  
 *  @author jgonzvil
 */
public class MensajeHistoricosBean implements Audit{

	/**
	 * Constructor de mensaje historicos bean.
	 */
	public MensajeHistoricosBean() {
		this.mensajeId=null;
		this.loteEnvioId=null;
		this.codigoExterno=null;
		this.cabecera=null;
		this.estadoActual=null;
		this.numeroEnvios=null;
		this.fechaCreacion=null;
		this.creadoPor=null;
		this.fechaModificacion=null;
		this.modificadoPor=null;
		this.ultimoEnvio=null;
		this.ultimoIdHistorico=null;
		this.cuerpo=null;
		this.tipoCuerpo=null;
		this.tipoCodificacion=null;
		this.prioridad=null;
		this.tipoMensaje=null;
		this.telefono=null;
		this.uim=null;
		this.idEnviosSms=null;
		this.fechaHistorificacion=null;
	}
	
	
	/**  mensaje id. */
	private Long mensajeId;
	
	/**  lote envio id. */
	private Long loteEnvioId;
	
	/**  codigo externo. */
	private String codigoExterno;
	
	/**  cabecera. */
	private String cabecera;
	
	/**  estado actual. */
	private String estadoActual;
	
	/**  numero envios. */
	private Integer numeroEnvios;
	
	/**  fecha creacion. */
	private Date fechaCreacion;
	
	/**  creado por. */
	private String creadoPor;
	
	/**  fecha modificacion. */
	private Date fechaModificacion;
	
	/**  modificado por. */
	private String modificadoPor;
	
	/**  ultimo envio. */
	private Date ultimoEnvio;
	
	/**  ultimo id historico. */
	private Long ultimoIdHistorico;
	
	/**  cuerpo. */
	private String cuerpo;
	
	/**  tipo cuerpo. */
	private String tipoCuerpo;
	
	/**  tipo codificacion. */
	private String tipoCodificacion;
	
	/**  prioridad. */
	private Integer prioridad;
	
	/**  tipo mensaje. */
	private String tipoMensaje;
	
	/**  telefono. */
	private String telefono;
	
	/**  uim. */
	private String uim;
	
	/**  id envios sms. */
	private Long idEnviosSms;
	
	/**  fecha historificacion. */
	private Date fechaHistorificacion;


	/* (non-Javadoc)
	 * @see com.map.j2ee.auditoria.ifaces.Audit#obtenerXML()
	 */
	@Override
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * Obtener mensaje id.
	 *
	 * @return mensaje id
	 */
	public Long getMensajeId() {
		return mensajeId;
	}


	/**
	 * Modificar mensaje id.
	 *
	 * @param mensajeId new mensaje id
	 */
	public void setMensajeId(Long mensajeId) {
		this.mensajeId = mensajeId;
	}


	/**
	 * Obtener lote envio id.
	 *
	 * @return lote envio id
	 */
	public Long getLoteEnvioId() {
		return loteEnvioId;
	}


	/**
	 * Modificar lote envio id.
	 *
	 * @param loteEnvioId new lote envio id
	 */
	public void setLoteEnvioId(Long loteEnvioId) {
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
	 * Obtener cabecera.
	 *
	 * @return cabecera
	 */
	public String getCabecera() {
		return cabecera;
	}


	/**
	 * Modificar cabecera.
	 *
	 * @param cabecera new cabecera
	 */
	public void setCabecera(String cabecera) {
		this.cabecera = cabecera;
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
	public Integer getNumeroEnvios() {
		return numeroEnvios;
	}


	/**
	 * Modificar numero envios.
	 *
	 * @param numeroEnvios new numero envios
	 */
	public void setNumeroEnvios(Integer numeroEnvios) {
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
	 * Obtener ultimo id historico.
	 *
	 * @return ultimo id historico
	 */
	public Long getUltimoIdHistorico() {
		return ultimoIdHistorico;
	}


	/**
	 * Modificar ultimo id historico.
	 *
	 * @param ultimoIdHistorico new ultimo id historico
	 */
	public void setUltimoIdHistorico(Long ultimoIdHistorico) {
		this.ultimoIdHistorico = ultimoIdHistorico;
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
	 * Obtener tipo cuerpo.
	 *
	 * @return tipo cuerpo
	 */
	public String getTipoCuerpo() {
		return tipoCuerpo;
	}


	/**
	 * Modificar tipo cuerpo.
	 *
	 * @param tipoCuerpo new tipo cuerpo
	 */
	public void setTipoCuerpo(String tipoCuerpo) {
		this.tipoCuerpo = tipoCuerpo;
	}


	/**
	 * Obtener tipo codificacion.
	 *
	 * @return tipo codificacion
	 */
	public String getTipoCodificacion() {
		return tipoCodificacion;
	}


	/**
	 * Modificar tipo codificacion.
	 *
	 * @param tipoCodificacion new tipo codificacion
	 */
	public void setTipoCodificacion(String tipoCodificacion) {
		this.tipoCodificacion = tipoCodificacion;
	}


	/**
	 * Obtener prioridad.
	 *
	 * @return prioridad
	 */
	public Integer getPrioridad() {
		return prioridad;
	}


	/**
	 * Modificar prioridad.
	 *
	 * @param prioridad new prioridad
	 */
	public void setPrioridad(Integer prioridad) {
		this.prioridad = prioridad;
	}


	/**
	 * Obtener tipo mensaje.
	 *
	 * @return tipo mensaje
	 */
	public String getTipoMensaje() {
		return tipoMensaje;
	}


	/**
	 * Modificar tipo mensaje.
	 *
	 * @param tipoMensaje new tipo mensaje
	 */
	public void setTipoMensaje(String tipoMensaje) {
		this.tipoMensaje = tipoMensaje;
	}


	/**
	 * Obtener telefono.
	 *
	 * @return telefono
	 */
	public String getTelefono() {
		return telefono;
	}


	/**
	 * Modificar telefono.
	 *
	 * @param telefono new telefono
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	/**
	 * Obtener uim.
	 *
	 * @return uim
	 */
	public String getUim() {
		return uim;
	}


	/**
	 * Modificar uim.
	 *
	 * @param uim new uim
	 */
	public void setUim(String uim) {
		this.uim = uim;
	}


	/**
	 * Obtener id envios sms.
	 *
	 * @return id envios sms
	 */
	public Long getIdEnviosSms() {
		return idEnviosSms;
	}


	/**
	 * Modificar id envios sms.
	 *
	 * @param idEnviosSms new id envios sms
	 */
	public void setIdEnviosSms(Long idEnviosSms) {
		this.idEnviosSms = idEnviosSms;
	}


	/**
	 * Obtener fecha historificacion.
	 *
	 * @return fecha historificacion
	 */
	public Date getFechaHistorificacion() {
		return fechaHistorificacion;
	}


	/**
	 * Modificar fecha historificacion.
	 *
	 * @param fechaHistorificacion new fecha historificacion
	 */
	public void setFechaHistorificacion(Date fechaHistorificacion) {
		this.fechaHistorificacion = fechaHistorificacion;
	}

	/**
	 * Obtener fecha formateada.
	 *
	 * @return fecha formateada
	 */
	public String getFechaFormateada() {
		String fechaFormateada = "";
		if (ultimoEnvio != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			fechaFormateada = sdf.format(ultimoEnvio);
		}
		return fechaFormateada;
	}
}
