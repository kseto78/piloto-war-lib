package es.minhap.plataformamensajeria.sm.modelo;


public class ParametrosServidorPush {
	
	int ServidorPushId;
	int PlataformaId;
	String Url;
	String UrlFeedback;
	int PuertoUrl;
	int PuertoUrlFeedback;
	int servicioId;
	int prioridad;

	
	public ParametrosServidorPush(){}


	public int getServidorPushId() {
		return ServidorPushId;
	}


	public void setServidorPushId(int servidorPushId) {
		ServidorPushId = servidorPushId;
	}


	public String getUrl() {
		return Url;
	}


	public void setUrl(String url) {
		Url = url;
	}


	public String getUrlFeedback() {
		return UrlFeedback;
	}


	public void setUrlFeedback(String urlFeedback) {
		UrlFeedback = urlFeedback;
	}


	public int getPuertoUrl() {
		return PuertoUrl;
	}


	public void setPuertoUrl(int puertoUrl) {
		PuertoUrl = puertoUrl;
	}


	public int getPuertoUrlFeedback() {
		return PuertoUrlFeedback;
	}


	public void setPuertoUrlFeedback(int puertoUrlFeedback) {
		PuertoUrlFeedback = puertoUrlFeedback;
	}


	public int getServicioId() {
		return servicioId;
	}


	public void setServicioId(int servicioId) {
		this.servicioId = servicioId;
	}


	public int getPrioridad() {
		return prioridad;
	}


	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}


	public int getPlataformaId() {
		return PlataformaId;
	}


	public void setPlataformaId(int plataformaId) {
		PlataformaId = plataformaId;
	}


	@Override
	public String toString() {
		return "ParametrosServidorPush [ServidorPushId=" + ServidorPushId + ", PlataformaId=" + PlataformaId + ", Url=" + Url + ", UrlFeedback=" + UrlFeedback + ", PuertoUrl=" + PuertoUrl + ", PuertoUrlFeedback=" + PuertoUrlFeedback + ", servicioId=" + servicioId
				+ ", prioridad=" + prioridad + "]";
	}
	
	
}

