package es.minhap.plataformamensajeria.iop.business.beans.push;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "notificacionDataRequest", propOrder = {
	    "gCMApiKey",
	    "rutaCertificadoAPNS",
	    "passwordCertificadoAPNS",
	    "token",
	    "url",
	    "puertoUrl",	    
	    "urlFeedback",
	    "puertoUrlFeedback",
	    "badge",
	    "cabecera",
	    "cuerpo",
	    "icon",
	    "sound"
		})	


public class NotificacionDataRequest {
	
	
	@XmlElement(name = "GCMApiKey", required = true)
	public String gCMApiKey;
	
	@XmlElement(name = "RutaCertificadoAPNS", required = true)
	public String rutaCertificadoAPNS;	
	
	@XmlElement(name = "PasswordCertificadoAPNS", required = true)
	public String passwordCertificadoAPNS;	
	
	@XmlElement(name = "Token", required = true)
	public List token;
	
	@XmlElement(name = "Url", required = true)
	public String url;
	
	@XmlElement(name = "PuertoUrl", required = true)
	public String puertoUrl;
	
	@XmlElement(name = "UrlFeedback", required = true)
	public String urlFeedback;
	
	@XmlElement(name = "PuertoUrlFeedback", required = true)
	public String puertoUrlFeedback;	
	
	@XmlElement(name = "Badge", required = true)
	public String badge;
	
	@XmlElement(name = "Cabecera", required = true)
	public String cabecera;
	
	@XmlElement(name = "Cuerpo", required = true)
	public String cuerpo;
	
	@XmlElement(name = "Icon", required = true)
	public String icon;
	
	@XmlElement(name = "Sound", required = true)
	public String sound;
	

	public String getgCMApiKey() {
		return gCMApiKey;
	}

	public void setgCMApiKey(String gCMApiKey) {
		this.gCMApiKey = gCMApiKey;
	}

	public List getToken() {
		return token;
	}

	public void setToken(List token) {
		this.token = token;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getBadge() {
		return badge;
	}

	public void setBadge(String badge) {
		this.badge = badge;
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

	public String getPuertoUrl() {
		return puertoUrl;
	}

	public void setPuertoUrl(String puertoUrl) {
		this.puertoUrl = puertoUrl;
	}

	public String getUrlFeedback() {
		return urlFeedback;
	}

	public void setUrlFeedback(String urlFeedback) {
		this.urlFeedback = urlFeedback;
	}

	@Override
	public String toString() {
		return "NotificacionDataRequest [gCMApiKey=" + gCMApiKey + ", rutaCertificadoAPNS=" + rutaCertificadoAPNS + ", passwordCertificadoAPNS=" + passwordCertificadoAPNS + ", token=" + token + ", url=" + url + ", puertoUrl=" + puertoUrl + ", urlFeedback=" + urlFeedback + ", badge=" + badge + ", cabecera=" + cabecera + ", cuerpo=" + cuerpo + "]";
	}

	public String getPuertoUrlFeedback() {
		return puertoUrlFeedback;
	}

	public void setPuertoUrlFeedback(String puertoUrlFeedback) {
		this.puertoUrlFeedback = puertoUrlFeedback;
	}

	public String getSound() {
		return sound;
	}

	public void setSound(String sound) {
		this.sound = sound;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	
	
	

}
