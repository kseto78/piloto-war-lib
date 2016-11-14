package es.minhap.misim.tranformers;

import java.io.StringWriter;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import es.minhap.plataformamensaferia.iop.beans.envioPremium.PeticionEnvioXML;
import es.minhap.plataformamensajeria.iop.services.exceptions.PlataformaBusinessException;

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

@XmlRootElement(name="NotificacionDataRequest", namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
public class NotificacionDataRequest {
	
	
	@XmlElement(name = "GCMApiKey", namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
	public String gCMApiKey;
	
	@XmlElement(name = "RutaCertificadoAPNS", namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
	public String rutaCertificadoAPNS;	
	
	@XmlElement(name = "PasswordCertificadoAPNS", namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
	public String passwordCertificadoAPNS;	
	
	@XmlElement(name = "Token", namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
	public List<String> token;
	
	@XmlElement(name = "Url", namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
	public String url;
	
	@XmlElement(name = "PuertoUrl", namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
	public String puertoUrl;
	
	@XmlElement(name = "UrlFeedback", namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
	public String urlFeedback;
	
	@XmlElement(name = "PuertoUrlFeedback", namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
	public String puertoUrlFeedback;	
	
	@XmlElement(name = "Badge", namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
	public String badge;
	
	@XmlElement(name = "Cabecera", namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
	public String cabecera;
	
	@XmlElement(name = "Cuerpo", namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
	public String cuerpo;
	
	@XmlElement(name = "Icon", namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
	public String icon;
	
	@XmlElement(name = "Sound", namespace="http://misim.redsara.es/misim-bus-webapp/peticion")
	public String sound;

	public String getgCMApiKey() {
		return gCMApiKey;
	}

	public void setgCMApiKey(String gCMApiKey) {
		this.gCMApiKey = gCMApiKey;
	}

	public List<String> getToken() {
		return token;
	}

	public void setToken(List<String> token) {
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
	
	
	public String toXML() throws PlataformaBusinessException {
		
		NotificacionDataRequest peticion = this;

		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(NotificacionDataRequest.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			StringWriter writer = new StringWriter();
			jaxbMarshaller.marshal(peticion, writer);

			return writer.toString();

		} catch (PropertyException e) {
			throw new PlataformaBusinessException(
					"Error generando el XML.\nCausa: " + e.getCause()
							+ "\nMensaje: " + e.getMessage());
		} catch (JAXBException e) {
			throw new PlataformaBusinessException(
					"Error generando el XML.\nCausa: " + e.getCause()
							+ "\nMensaje: " + e.getMessage());
		}

	}

}
