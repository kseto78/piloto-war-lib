package es.minhap.plataformamensajeria.sm.modelo;

public class ParametrosServidorWebPush {

	int servidorWebPushId;
	int servicioId;

	public ParametrosServidorWebPush() {
	}

	/**
	 * @return the servidorPushId
	 */
	public int getServidorWebPushId() {
		return servidorWebPushId;
	}

	/**
	 * @param servidorPushId
	 *            the servidorPushId to set
	 */
	public void setServidorWebPushId(int servidorWebPushId) {
		this.servidorWebPushId = servidorWebPushId;
	}

	/**
	 * @return the servicioId
	 */
	public int getServicioId() {
		return servicioId;
	}

	/**
	 * @param servicioId
	 *            the servicioId to set
	 */
	public void setServicioId(int servicioId) {
		this.servicioId = servicioId;
	}

	@Override
	public String toString() {
		return "ParametrosServidorPush [ServidorPushId=" + servidorWebPushId + ", servicioId=" + servicioId + "]";
	}

}
