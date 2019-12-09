package es.mpr.plataformamensajeria.beans;

import java.io.Serializable;
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
public class DestinatariosMensajesBean implements Audit, Serializable {

	private static final long serialVersionUID = -4567510927310978557L;

	private Long destinatariosMensajes;

	private Long mensajeId;

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
	/**
	 * Constructor por defecto
	 */
	public DestinatariosMensajesBean() {
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

	/**
	 * Obtener XML
	 */
	@Override
	public String obtenerXML() {
		return null;
	}

	/**
	 * Obtener destinatariosMensajes
	 * @return
	 */
	public Long getDestinatariosMensajes() {
		return destinatariosMensajes;
	}

	/**
	 * Modificar destinatariosMensajes
	 * @param destinatariosMensajes
	 */
	public void setDestinatariosMensajes(Long destinatariosMensajes) {
		this.destinatariosMensajes = destinatariosMensajes;
	}

	/**
	 * Obtener mensajeId
	 * @return
	 */
	public Long getMensajeId() {
		return mensajeId;
	}

	/**
	 * Modificar mensajeId
	 * @param mensajeId
	 */
	public void setMensajeId(Long mensajeId) {
		this.mensajeId = mensajeId;
	}

	/**
	 * Obtener destinatario
	 * @return
	 */
	public String getDestinatario() {
		return destinatario;
	}

	/**
	 * Modificar destinatario
	 * @param destinatario
	 */
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	/**
	 * Obtener Estado
	 * @return
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Modificar Estado
	 * @param estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
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
	 * Obtener codigoExterno
	 * @return
	 */
	public String getCodigoExterno() {
		return codigoExterno;
	}

	/**
	 * Modificar codigoExterno
	 * @param codigoExterno
	 */
	public void setCodigoExterno(String codigoExterno) {
		this.codigoExterno = codigoExterno;
	}

	/**
	 * Obtener uim
	 * @return
	 */
	public String getUim() {
		return uim;
	}

	/**
	 * Modificar uim
	 * @param uim
	 */
	public void setUim(String uim) {
		this.uim = uim;
	}

	/**
	 * Obtener nodo
	 * @return
	 */
	public Integer getNodo() {
		return nodo;
	}

	/**
	 * Modificar Nodo
	 * @param nodo
	 */
	public void setNodo(Integer nodo) {
		this.nodo = nodo;
	}

	/**
	 * Obtener DestinatarioBean
	 * @return
	 */
	public ArrayList<DestinatarioBean> getListaDestinatarios() {
		return listaDestinatarios;
	}

	/**
	 * Modificar listaDestinatarios
	 * @param listaDestinatarios
	 */
	public void setListaDestinatarios(ArrayList<DestinatarioBean> listaDestinatarios) {
		this.listaDestinatarios = listaDestinatarios;
	}

	/**
	 * Obtener ultimoEnvio
	 * @return
	 */
	public Date getUltimoEnvio() {
		return ultimoEnvio;
	}

	/**
	 * Modificar ultimoEnvio
	 * @param ultimoEnvio
	 */
	public void setUltimoEnvio(Date ultimoEnvio) {
		this.ultimoEnvio = ultimoEnvio;
	}

	/**
	 * Obtener tipoMensaje
	 * @return
	 */
	public String getTipoMensaje() {
		return tipoMensaje;
	}

	/**
	 * Modificar tipoMensaje
	 * @param tipoMensaje
	 */
	public void setTipoMensaje(String tipoMensaje) {
		this.tipoMensaje = tipoMensaje;
	}
	
	/**
	 * Obtener fechaFormateada
	 * @return
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
