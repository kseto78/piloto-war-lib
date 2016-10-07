package es.mpr.plataformamensajeria.beans;

import java.io.Serializable;
/**
 * 
 * @author i-nercya
 *
 */
public class ConsultaEstadoBean implements Serializable {

	/**
	 * 	static final String TAG_ITEM="ITEM";
	static final String TAG_ITEM_IDSERVICIO="IDSERVICIO";
	static final String TAG_ITEM_SERVICIO="SERVICIO";
	static final String TAG_ITEM_IDCANAL="IDCANAL";
	static final String TAG_ITEM_CANAL="CANAL";
	static final String TAG_ITEM_IDAPLICACION="IDAPLICACION";
	static final String TAG_ITEM_APLICACION="APLICACION";
	static final String TAG_ITEM_IDLOTE="IDLOTE";
	static final String TAG_ITEM_IDMENSAJE="IDMENSAJE";
	static final String TAG_ITEM_IDEXTERNO="IDEXTERNO";
	static final String TAG_ITEM_ESTADO="ESTADO";
	static final String TAG_ITEM_NUMEROREINTENTOS="NUMEROREINTENTOS";
	static final String TAG_ITEM_FECHA="FECHA";
	 */
	private static final long serialVersionUID = 1L;
	
	private String idServicio;
	private String servicio;
	private String idCanal;
	private String canal;
	private String idAplicacion;
	private String aplicacion;
	private String idLote;
	private String idMensaje;
	private String idExterno;
	private String estado;
	@Deprecated
	private String numeroReintentos;
	private String fecha;
	public String getIdServicio() {
		return idServicio;
	}
	public void setIdServicio(String idServicio) {
		this.idServicio = idServicio;
	}
	public String getServicio() {
		return servicio;
	}
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
	public String getIdCanal() {
		return idCanal;
	}
	public void setIdCanal(String idCanal) {
		this.idCanal = idCanal;
	}
	public String getCanal() {
		return canal;
	}
	public void setCanal(String canal) {
		this.canal = canal;
	}
	public String getIdAplicacion() {
		return idAplicacion;
	}
	public void setIdAplicacion(String idAplicacion) {
		this.idAplicacion = idAplicacion;
	}
	public String getAplicacion() {
		return aplicacion;
	}
	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}
	public String getIdLote() {
		return idLote;
	}
	public void setIdLote(String idLote) {
		this.idLote = idLote;
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
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getNumeroReintentos() {
		return numeroReintentos;
	}
	public void setNumeroReintentos(String numeroReintentos) {
		this.numeroReintentos = numeroReintentos;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	

}
