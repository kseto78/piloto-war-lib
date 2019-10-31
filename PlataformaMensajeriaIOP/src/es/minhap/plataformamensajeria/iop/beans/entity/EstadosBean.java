package es.minhap.plataformamensajeria.iop.beans.entity;

/**
 * Bean que contiene la información necesaria de un estado
 * 
 * @author everis
 *
 */
public class EstadosBean {

	private Long estadoId;

	private String descripcion;

	public EstadosBean(Long estadoId, String descripcion){
		this.estadoId = estadoId;
		this.descripcion = descripcion;
	}
	
	public EstadosBean() {
		// This method has to be empty.
		
	}

	/**
	 * @return the estadoId
	 */
	public Long getEstadoId() {
		return estadoId;
	}

	/**
	 * @param estadoId
	 *            the estadoId to set
	 */
	public void setEstadoId(Long estadoId) {
		this.estadoId = estadoId;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
