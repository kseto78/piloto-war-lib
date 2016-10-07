package es.mpr.plataformamensajeria.beans;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.map.j2ee.auditoria.ifaces.Audit;

/**
 * <p>
 * Bean para mapear la información de los envíos
 * 
 * <p>
 * 
 * 
 * @author Altran
 */
public class DestinatariosMensajesHistoricosBean implements Audit {

	public DestinatariosMensajesHistoricosBean() {
		this.destinatariosMensajes = null;
		this.mensajeId = null;
		this.destinatario = null;
		this.estado = null;
		this.fechaCreacion = null;
		this.creadoPor = null;
		this.fechaModificacion = null;
		this.modificadoPor = null;
		this.codigoExterno = null;
		this.uim = null;
		this.nodo = null;
		this.listaDestinatarios = null;
		this.ultimoEnvio = null;
		this.tipoMensaje = null;
	}

	private Integer destinatariosMensajes;

	private Integer mensajeId;

	private String destinatario;

	private String estado;

	private Date fechaCreacion;

	private String creadoPor;

	private Date fechaModificacion;

	private String modificadoPor;

	private String codigoExterno;

	private String uim;

	private Integer nodo;

	private Date ultimoEnvio;

	private ArrayList<DestinatarioBean> listaDestinatarios;

	private String tipoMensaje;

	@Override
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer getDestinatariosMensajes() {
		return destinatariosMensajes;
	}

	public void setDestinatariosMensajes(Integer destinatariosMensajes) {
		this.destinatariosMensajes = destinatariosMensajes;
	}

	public Integer getMensajeId() {
		return mensajeId;
	}

	public void setMensajeId(Integer mensajeId) {
		this.mensajeId = mensajeId;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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

	public String getCodigoExterno() {
		return codigoExterno;
	}

	public void setCodigoExterno(String codigoExterno) {
		this.codigoExterno = codigoExterno;
	}

	public String getUim() {
		return uim;
	}

	public void setUim(String uim) {
		this.uim = uim;
	}

	public Integer getNodo() {
		return nodo;
	}

	public void setNodo(Integer nodo) {
		this.nodo = nodo;
	}

	public ArrayList<DestinatarioBean> getListaDestinatarios() {
		return listaDestinatarios;
	}

	public void setListaDestinatarios(ArrayList<DestinatarioBean> listaDestinatarios) {
		this.listaDestinatarios = listaDestinatarios;
	}

	public Date getUltimoEnvio() {
		return ultimoEnvio;
	}

	public void setUltimoEnvio(Date ultimoEnvio) {
		this.ultimoEnvio = ultimoEnvio;
	}

	public String getTipoMensaje() {
		return tipoMensaje;
	}

	public void setTipoMensaje(String tipoMensaje) {
		this.tipoMensaje = tipoMensaje;
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
