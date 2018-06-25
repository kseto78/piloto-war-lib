package es.minhap.plataformamensajeria.iop.beans;

public class DatosAplicacionBean {
	private String usuario;
	private String password;

	public DatosAplicacionBean() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param fecha
	 * @param mensajeid
	 * @param subestadoid
	 */
	public DatosAplicacionBean(String usuario, String password) {
		this.usuario = usuario;
		this.password = password;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
