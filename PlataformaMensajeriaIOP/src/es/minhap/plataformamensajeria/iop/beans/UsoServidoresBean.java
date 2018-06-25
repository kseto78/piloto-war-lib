package es.minhap.plataformamensajeria.iop.beans;


public class UsoServidoresBean {
	
	private Long servidorId;
	
	private String nombre;
	
	private Integer nEnvios;
	
	private Long tipoServidor;

	/**
	 * @return the servidorId
	 */
	public Long getServidorId() {
		return servidorId;
	}

	/**
	 * @param servidorId the servidorId to set
	 */
	public void setServidorId(Long servidorId) {
		this.servidorId = servidorId;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the nEnvios
	 */
	public Integer getnEnvios() {
		return nEnvios;
	}

	/**
	 * @param nEnvios the nEnvios to set
	 */
	public void setnEnvios(Integer nEnvios) {
		this.nEnvios = nEnvios;
	}

	/**
	 * @return the tipoServidor
	 */
	public Long getTipoServidor() {
		return tipoServidor;
	}

	/**
	 * @param tipoServidor the tipoServidor to set
	 */
	public void setTipoServidor(Long tipoServidor) {
		this.tipoServidor = tipoServidor;
	}
	
	
}
