package es.mpr.plataformamensajeria.beans;

import java.io.Serializable;
/**
 * 
 * @author i-nercya
 *	static final String TAG_ITEM_IDSERVIDOR="IDSERVIDOR";
	static final String TAG_ITEM_IDMENSAJE="IDMENSAJE";
	static final String TAG_ITEM_IDEXTERNO="IDEXTERNO";
	static final String TAG_ITEM_SERVIDOR="SERVIDOR";
	static final String TAG_ITEM_FECHA="FECHA";
	static final String TAG_ITEM_ESTADO="ESTADO";
	
 */
public class ConsultaHistoricoBean implements Serializable {
  
	private static final long serialVersionUID = 1L;
	
	private String idServidor;
	private String idMensaje;
	private String idExterno;
	private String servidor;
	private String fecha;
	private String estado;
	public String getIdServidor() {
		return idServidor;
	}
	public void setIdServidor(String idServidor) {
		this.idServidor = idServidor;
	}
	public String getIdMensaje() {
		return idMensaje;
	}
	public void setIdMensaje(String idMensaje) {
		this.idMensaje = idMensaje;
	}
	public String getIdExterno() {
		return idExterno;
	}
	public void setIdExterno(String idExterno) {
		this.idExterno = idExterno;
	}
	public String getServidor() {
		return servidor;
	}
	public void setServidor(String servidor) {
		this.servidor = servidor;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	

}
