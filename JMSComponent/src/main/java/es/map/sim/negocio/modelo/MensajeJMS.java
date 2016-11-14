package es.map.sim.negocio.modelo;

import java.io.Serializable;

public class MensajeJMS implements Serializable {
	
	
	private static final long serialVersionUID = 3474316847351887658L;

	private String idMensaje;
	
	private String idCanal;
	
	private String nombreLote;
	
	private String servicio;
	
	private String codSia;
	
	private String codOrganismo;
	
	private String codOrganismoPagadorSMS;
	
	private String docUsuario;
	
	private String idExterno;
	
	private String destinatario;
	
	private String origen;
	
	private String cuerpo;
	
	private String rutaCuerpo;
	
	private String usuarioAplicacion;
	
	private String passwordAplicacion;
	
	private String usernameMISIM;
	
	private String passwordMISIM;
	
	private String destinatarioMensajeId;

	
	
	/**
	 * @return the idMensaje
	 */
	public String getIdMensaje() {
		return idMensaje;
	}
	/**
	 * @param idMensaje the idMensaje to set
	 */
	public void setIdMensaje(String idMensaje) {
		this.idMensaje = idMensaje;
	}
	/**
	 * @return the idCanal
	 */
	public String getIdCanal() {
		return idCanal;
	}
	/**
	 * @param idCanal the idCanal to set
	 */
	public void setIdCanal(String idCanal) {
		this.idCanal = idCanal;
	}
	/**
	 * @return the idExterno
	 */
	public String getIdExterno() {
		return idExterno;
	}
	/**
	 * @param idExterno the idExterno to set
	 */
	public void setIdExterno(String idExterno) {
		this.idExterno = idExterno;
	}
	/**
	 * @return the nombreLote
	 */
	public String getNombreLote() {
		return nombreLote;
	}
	/**
	 * @param nombreLote the nombreLote to set
	 */
	public void setNombreLote(String nombreLote) {
		this.nombreLote = nombreLote;
	}
	/**
	 * @return the servicio
	 */
	public String getServicio() {
		return servicio;
	}
	/**
	 * @param servicio the servicio to set
	 */
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
	/**
	 * @return the codSia
	 */
	public String getCodSia() {
		return codSia;
	}
	/**
	 * @param codSia the codSia to set
	 */
	public void setCodSia(String codSia) {
		this.codSia = codSia;
	}
	/**
	 * @return the codOrganismo
	 */
	public String getCodOrganismo() {
		return codOrganismo;
	}
	/**
	 * @param codOrganismo the codOrganismo to set
	 */
	public void setCodOrganismo(String codOrganismo) {
		this.codOrganismo = codOrganismo;
	}
	/**
	 * @return the codOrganismoPagadorSMS
	 */
	public String getCodOrganismoPagadorSMS() {
		return codOrganismoPagadorSMS;
	}
	/**
	 * @param codOrganismoPagadorSMS the codOrganismoPagadorSMS to set
	 */
	public void setCodOrganismoPagadorSMS(String codOrganismoPagadorSMS) {
		this.codOrganismoPagadorSMS = codOrganismoPagadorSMS;
	}
	/**
	 * @return the docUsuario
	 */
	public String getDocUsuario() {
		return docUsuario;
	}
	/**
	 * @param docUsuario the docUsuario to set
	 */
	public void setDocUsuario(String docUsuario) {
		this.docUsuario = docUsuario;
	}
	/**
	 * @return the destinatario
	 */
	public String getDestinatario() {
		return destinatario;
	}
	/**
	 * @param destinatario the destinatario to set
	 */
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	/**
	 * @return the origen
	 */
	public String getOrigen() {
		return origen;
	}
	/**
	 * @param origen the origen to set
	 */
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	/**
	 * @return the cuerpo
	 */
	public String getCuerpo() {
		return cuerpo;
	}
	/**
	 * @param cuerpo the cuerpo to set
	 */
	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}
	/**
	 * @return the rutaCuerpo
	 */
	public String getRutaCuerpo() {
		return rutaCuerpo;
	}
	/**
	 * @param rutaCuerpo the rutaCuerpo to set
	 */
	public void setRutaCuerpo(String rutaCuerpo) {
		this.rutaCuerpo = rutaCuerpo;
	}
	/**
	 * @return the usuarioAplicacion
	 */
	public String getUsuarioAplicacion() {
		return usuarioAplicacion;
	}
	/**
	 * @param usuarioAplicacion the usuarioAplicacion to set
	 */
	public void setUsuarioAplicacion(String usuarioAplicacion) {
		this.usuarioAplicacion = usuarioAplicacion;
	}
	/**
	 * @return the passwordAplicacion
	 */
	public String getPasswordAplicacion() {
		return passwordAplicacion;
	}
	/**
	 * @param passwordAplicacion the passwordAplicacion to set
	 */
	public void setPasswordAplicacion(String passwordAplicacion) {
		this.passwordAplicacion = passwordAplicacion;
	}
	/**
	 * @return the usernameMISIM
	 */
	public String getUsernameMISIM() {
		return usernameMISIM;
	}
	/**
	 * @param usernameMISIM the usernameMISIM to set
	 */
	public void setUsernameMISIM(String usernameMISIM) {
		this.usernameMISIM = usernameMISIM;
	}
	/**
	 * @return the passwordMISIM
	 */
	public String getPasswordMISIM() {
		return passwordMISIM;
	}
	/**
	 * @param passwordMISIM the passwordMISIM to set
	 */
	public void setPasswordMISIM(String passwordMISIM) {
		this.passwordMISIM = passwordMISIM;
	}
	
	/**
	 * @return the destinatarioMensajeId
	 */
	public String getDestinatarioMensajeId() {
		return destinatarioMensajeId;
	}
	
	/**
	 * @param destinatarioMensajeId the destinatarioMensajeId to set
	 */
	public void setDestinatarioMensajeId(String destinatarioMensajeId) {
		this.destinatarioMensajeId = destinatarioMensajeId;
	}
	
	
}
