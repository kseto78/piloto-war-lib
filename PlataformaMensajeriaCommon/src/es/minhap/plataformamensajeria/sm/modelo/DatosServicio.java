package es.minhap.plataformamensajeria.sm.modelo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlType(name = "datosServicio", propOrder = {
	    "nMaxEnvios",
	    "fromMail",
	    "fromMailNombre",
	    "headerSMS"})
public class DatosServicio {

	@XmlElement(name = "NMaxEnvios", required = true)
	int nMaxEnvios;
	
	@XmlElement(name = "FromMail", required = true)
	String fromMail;
	
	@XmlElement(name = "FromMailNombre", required = true)
	String fromMailNombre;
	
	@XmlElement(name = "HeaderSMS", required = true)
	String headerSMS;
	
	@XmlElement(name = "MultiOrganismo", required = true)
	int multiOrganismo;
	
	

	public DatosServicio(){}
	
	
	public int getMultiOrganismo() {
		return multiOrganismo;
	}


	public void setMultiOrganismo(int multiOrganismo) {
		this.multiOrganismo = multiOrganismo;
	}

	public void setNMaxEnvios(int nMaxEnvios){
		this.nMaxEnvios = nMaxEnvios;
	}
	
	public int getNMaxEnvios(){
		return this.nMaxEnvios;
	}
	
	public void setFromMail(String fromMail){
		this.fromMail = fromMail;
	}
	
	public String getFromMail(){
		return this.fromMail;
	}
	
	public void setFromMailNombre(String fromMailNombre){
		this.fromMailNombre = fromMailNombre;
	}
	
	public String getFromMailNombre(){
		return this.fromMailNombre;
	}
	
	public void setHeaderSMS(String HeaderSMS){
		this.headerSMS = HeaderSMS;
	}
	
	public String getHeaderSMS(){
		return this.headerSMS;
	}
	
	
}
