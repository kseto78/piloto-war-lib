package es.minhap.plataformamensajeria.sm.modelo;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "notificacionPushData", propOrder = {
	    "servers",
	    "cabecera",
	    "cuerpo",
	    "icono",
	    "sonido",
	    "nombreUsuario",
	    "tokensUsuario",
	    "gCMApiKey",
	    "rutaCertificadoAPNS",
	    "passwordCertificadoAPNS",
	    "badge",	 
	    "serviceData",
	    "messageID",
	    "notificacionSilenciosa",
	    "indice"})

public class NotificacionPushData {
	
	
	@XmlElement(name = "Servers", required = true)
	public ArrayList<ParametrosServidorPush> servers;
	
	@XmlElement(name = "Cabecera", required = true)
	public String cabecera = "";
	
	@XmlElement(name = "Cuerpo", required = true)
	public String cuerpo = "";
	
	@XmlElement(name = "Icono", required = true)
	public String icono="";
	
	@XmlElement(name = "Sonido", required = true)
	public String sonido = "";
	
	@XmlElement(name = "NombreUsuario", required = true)
	public String nombreUsuario = "";
	
	@XmlElement(name = "TokensUsuario", required = true)
	public ArrayList<String> tokensUsuario;
	
	@XmlElement(name = "GCMApiKey", required = true)
	public String gCMApiKey = "";
	
	@XmlElement(name = "RutaCertificadoAPNS", required = true)
	public String rutaCertificadoAPNS = "";
	
	@XmlElement(name = "PasswordCertificadoAPNS", required = true)
	public String passwordCertificadoAPNS = "";
	
	@XmlElement(name = "Badge", required = true)
	public String badge = "";
	
	@XmlElement(name = "ServiceData", required = true)
	public DatosServicio serviceData;
	
	@XmlElement(name = "MessageID", required = true)
	public String messageID;
	
	@XmlElement(name = "Indice", required = true)
	public String indice;
	
	@XmlElement(name = "NotificacionSilenciosa", required = false)
	public Boolean notificacionSilenciosa;
	
	public boolean esMultidestinatario = false;
	public Long destinatarioMensajeId;
	public String estado = "";
	public Long UsuarioId = null;
	public Integer plataformaId = null;

	public ArrayList<ParametrosServidorPush> getServers() {
		return servers;
	}

	public void setServers(ArrayList<ParametrosServidorPush> servers) {
		this.servers = servers;
	}

	public String getCabecera() {
		return cabecera;
	}

	public void setCabecera(String cabecera) {
		this.cabecera = cabecera;
	}

	public String getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

	public String getIcono() {
		return icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}

	public String getSonido() {
		return sonido;
	}

	public void setSonido(String sonido) {
		this.sonido = sonido;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public ArrayList<String> getTokensUsuario() {
		return tokensUsuario;
	}

	public void setTokensUsuario(ArrayList<String> tokensUsuario) {
		this.tokensUsuario = tokensUsuario;
	}

	public String getgCMApiKey() {
		return gCMApiKey;
	}

	public void setgCMApiKey(String gCMApiKey) {
		this.gCMApiKey = gCMApiKey;
	}

	public String getRutaCertificadoAPNS() {
		return rutaCertificadoAPNS;
	}

	public void setRutaCertificadoAPNS(String rutaCertificadoAPNS) {
		this.rutaCertificadoAPNS = rutaCertificadoAPNS;
	}

	public String getPasswordCertificadoAPNS() {
		return passwordCertificadoAPNS;
	}

	public void setPasswordCertificadoAPNS(String passwordCertificadoAPNS) {
		this.passwordCertificadoAPNS = passwordCertificadoAPNS;
	}

	public String getBadge() {
		return badge;
	}

	public void setBadge(String badge) {
		this.badge = badge;
	}

	public DatosServicio getServiceData() {
		return serviceData;
	}

	public void setServiceData(DatosServicio serviceData) {
		this.serviceData = serviceData;
	}

	public String getMessageID() {
		return messageID;
	}

	public void setMessageID(String messageID) {
		this.messageID = messageID;
	}

	public String getIndice() {
		return indice;
	}

	public void setIndice(String indice) {
		this.indice = indice;
	}

	public boolean isEsMultidestinatario() {
		return esMultidestinatario;
	}

	public void setEsMultidestinatario(boolean esMultidestinatario) {
		this.esMultidestinatario = esMultidestinatario;
	}

	public Long getDestinatarioMensajeId() {
		return destinatarioMensajeId;
	}

	public void setDestinatarioMensajeId(Long destinatarioMensajeId) {
		this.destinatarioMensajeId = destinatarioMensajeId;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the UsuarioId
	 */
	public Long getUsuarioId() {
		return UsuarioId;
	}

	/**
	 * @param UsuarioId the UsuarioId to set
	 */
	public void setUsuarioId(Long UsuarioId) {
		this.UsuarioId = UsuarioId;
	}

	/**
	 * @return the notificacionSilenciosa
	 */
	public Boolean getNotificacionSilenciosa() {
		return notificacionSilenciosa;
	}

	/**
	 * @param notificacionSilenciosa the notificacionSilenciosa to set
	 */
	public void setNotificacionSilenciosa(Boolean notificacionSilenciosa) {
		this.notificacionSilenciosa = notificacionSilenciosa;
	}
		
	
}
