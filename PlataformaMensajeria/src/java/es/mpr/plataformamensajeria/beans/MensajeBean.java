package es.mpr.plataformamensajeria.beans;

import java.text.SimpleDateFormat;
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
public class MensajeBean implements Audit{

	public MensajeBean() {
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
		this.nodo=null;
	}
	
	
	private Integer mensajeId;
	private Integer loteEnvioId;
	private String codigoExterno;
	private String cabecera;
	private String estadoActual;
	private String numeroEnvios;
	private Date fechaCreacion;
	private String creadoPor;
	private Date fechaModificacion;
	private String modificadoPor;
	private Date ultimoEnvio;
	private Integer ultimoIdHistorico;
	private String cuerpo;
	private String tipoCuerpo;
	private String tipoCodificacion;
	private String prioridad;
	private String tipoMensaje;
	private String telefono;
	private String uim;
	private Integer idEnviosSms;
	private Integer nodo;


	@Override
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}


	public Integer getMensajeId() {
		return mensajeId;
	}


	public void setMensajeId(Integer mensajeId) {
		this.mensajeId = mensajeId;
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


	public String getCabecera() {
		return cabecera;
	}


	public void setCabecera(String cabecera) {
		this.cabecera = cabecera;
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


	public Date getUltimoEnvio() {
		return ultimoEnvio;
	}


	public void setUltimoEnvio(Date ultimoEnvio) {
		this.ultimoEnvio = ultimoEnvio;
	}


	public Integer getUltimoIdHistorico() {
		return ultimoIdHistorico;
	}


	public void setUltimoIdHistorico(Integer ultimoIdHistorico) {
		this.ultimoIdHistorico = ultimoIdHistorico;
	}


	public String getCuerpo() {
		return cuerpo;
	}


	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}


	public String getTipoCuerpo() {
		return tipoCuerpo;
	}


	public void setTipoCuerpo(String tipoCuerpo) {
		this.tipoCuerpo = tipoCuerpo;
	}


	public String getTipoCodificacion() {
		return tipoCodificacion;
	}


	public void setTipoCodificacion(String tipoCodificacion) {
		this.tipoCodificacion = tipoCodificacion;
	}


	public String getPrioridad() {
		return prioridad;
	}


	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}


	public String getTipoMensaje() {
		return tipoMensaje;
	}


	public void setTipoMensaje(String tipoMensaje) {
		this.tipoMensaje = tipoMensaje;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public String getUim() {
		return uim;
	}


	public void setUim(String uim) {
		this.uim = uim;
	}


	public Integer getIdEnviosSms() {
		return idEnviosSms;
	}


	public void setIdEnviosSms(Integer idEnviosSms) {
		this.idEnviosSms = idEnviosSms;
	}


	public Integer getNodo() {
		return nodo;
	}


	public void setNodo(Integer nodo) {
		this.nodo = nodo;
	}
	
	public String getFechaFormateada() {
		String fechaFormateada = "";
		if (ultimoEnvio != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			fechaFormateada = sdf.format(ultimoEnvio);
		}
		return fechaFormateada;
	}
}
