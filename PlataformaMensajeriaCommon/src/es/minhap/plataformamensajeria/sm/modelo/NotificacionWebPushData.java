package es.minhap.plataformamensajeria.sm.modelo;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "notificacionWebPushData", propOrder = {
	    "servers",
		"cabecera",
	    "cuerpo",
	    "serviceData",
	    "messageID",
	    "indice"
	    })

public class NotificacionWebPushData {
	@XmlElement(name = "Servers", required = true)
	public ArrayList<ParametrosServidorWebPush> servers;
		
	@XmlElement(name = "Cabecera", required = true)
	public String cabecera = "";
	
	@XmlElement(name = "Cuerpo", required = true)
	public String cuerpo = "";
	
	@XmlElement(name = "MessageID", required = true)
	public String messageID;
	
	@XmlElement(name = "indice", required = true)
	public String indice;
	
	private boolean esMultidestinatario = false;
	private Long destinatarioMensajeId;
	private String estado = "";
	private Long usuarioId = null;
	private Integer caducidadWebPush = null;
	private String vapidPublicKey = null;
	private String vapidPrivateKey = null;
	private String endpoint = null;
	private String auth = null;
	private String pdh = null;


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

	
	public String getMessageID() {
		return messageID;
	}

	public void setMessageID(String messageID) {
		this.messageID = messageID;
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
	 * @return the usuarioId
	 */
	public Long getUsuarioId() {
		return usuarioId;
	}

	/**
	 * @param usuarioId the usuarioId to set
	 */
	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	/**
	 * @return the caducidadWebPush
	 */
	public Integer getCaducidadWebPush() {
		return caducidadWebPush;
	}

	/**
	 * @param caducidadWebPush the caducidadWebPush to set
	 */
	public void setCaducidadWebPush(Integer caducidadWebPush) {
		this.caducidadWebPush = caducidadWebPush;
	}

	/**
	 * @return the vapidPublicKey
	 */
	public String getVapidPublicKey() {
		return vapidPublicKey;
	}

	/**
	 * @param vapidPublicKey the vapidPublicKey to set
	 */
	public void setVapidPublicKey(String vapidPublicKey) {
		this.vapidPublicKey = vapidPublicKey;
	}

	/**
	 * @return the vapidPrivateKey
	 */
	public String getVapidPrivateKey() {
		return vapidPrivateKey;
	}

	/**
	 * @param vapidPrivateKey the vapidPrivateKey to set
	 */
	public void setVapidPrivateKey(String vapidPrivateKey) {
		this.vapidPrivateKey = vapidPrivateKey;
	}

	/**
	 * @return the endpoint
	 */
	public String getEndpoint() {
		return endpoint;
	}

	/**
	 * @param endpoint the endpoint to set
	 */
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	/**
	 * @return the auth
	 */
	public String getAuth() {
		return auth;
	}

	/**
	 * @param auth the auth to set
	 */
	public void setAuth(String auth) {
		this.auth = auth;
	}

	/**
	 * @return the pdh
	 */
	public String getPdh() {
		return pdh;
	}

	/**
	 * @param pdh the pdh to set
	 */
	public void setPdh(String pdh) {
		this.pdh = pdh;
	}

	/**
	 * @return the servers
	 */
	public ArrayList<ParametrosServidorWebPush> getServers() {
		return servers;
	}

	/**
	 * @param servers the servers to set
	 */
	public void setServers(ArrayList<ParametrosServidorWebPush> servers) {
		this.servers = servers;
	}

	/**
	 * @return the indice
	 */
	public String getIndice() {
		return indice;
	}

	/**
	 * @param indice the indice to set
	 */
	public void setIndice(String indice) {
		this.indice = indice;
	}
	
	

		
	
}
