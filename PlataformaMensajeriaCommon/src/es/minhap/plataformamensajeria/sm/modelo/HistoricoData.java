package es.minhap.plataformamensajeria.sm.modelo;

public class HistoricoData {

	private Integer mensajeId;
	private String estado_id;
	private Integer servidorId;
	private String destinatariosMensajes;
	private String descripcion;

	public String getEstado_id() {
		return estado_id;
	}

	public void setEstado_id(String estado_id) {
		this.estado_id = estado_id;
	}

	public Integer getServidorId() {
		return servidorId;
	}

	public void setServidorId(Integer servidorId) {
		this.servidorId = servidorId;
	}

	public String getDestinatariosMensajes() {
		return destinatariosMensajes;
	}

	public void setDestinatariosMensajes(String destinatariosMensajes) {
		this.destinatariosMensajes = destinatariosMensajes;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getMensajeId() {
		return mensajeId;
	}

	public void setMensajeId(Integer mensajeId) {
		this.mensajeId = mensajeId;
	}

}
