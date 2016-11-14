package es.minhap.sim.model;

// Generated 18-jul-2016 10:57:14 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ViewMensajesAdjuntosId generated by hbm2java
 */
@Embeddable
public class ViewMensajesAdjuntosId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3168646765888671532L;

	@Column(name = "MENSAJEADJUNTOID", nullable = false, precision = 22, scale = 0)
	private Long mensajeadjuntoid;

	@Column(name = "MENSAJEID", precision = 22, scale = 0)
	private Long mensajeid;

	@Column(name = "ADJUNTOID", nullable = false, precision = 22, scale = 0)
	private Long adjuntoid;

	public ViewMensajesAdjuntosId() {
	}

	@Override
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ViewMensajesAdjuntosId))
			return false;
		ViewMensajesAdjuntosId castOther = (ViewMensajesAdjuntosId) other;

		return ((this.getMensajeadjuntoid() == castOther.getMensajeadjuntoid()) || (this
				.getMensajeadjuntoid() != null
				&& castOther.getMensajeadjuntoid() != null && this
				.getMensajeadjuntoid().equals(castOther.getMensajeadjuntoid())))
				&& ((this.getMensajeid() == castOther.getMensajeid()) || (this
						.getMensajeid() != null
						&& castOther.getMensajeid() != null && this
						.getMensajeid().equals(castOther.getMensajeid())))
				&& ((this.getAdjuntoid() == castOther.getAdjuntoid()) || (this
						.getAdjuntoid() != null
						&& castOther.getAdjuntoid() != null && this
						.getAdjuntoid().equals(castOther.getAdjuntoid())));
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getMensajeadjuntoid() == null ? 0 : this
						.getMensajeadjuntoid().hashCode());
		result = 37 * result
				+ (getMensajeid() == null ? 0 : this.getMensajeid().hashCode());
		result = 37 * result
				+ (getAdjuntoid() == null ? 0 : this.getAdjuntoid().hashCode());
		return result;
	}

	/**
	 * @return the mensajeadjuntoid
	 */
	public Long getMensajeadjuntoid() {
		return mensajeadjuntoid;
	}

	/**
	 * @param mensajeadjuntoid
	 *            the mensajeadjuntoid to set
	 */
	public void setMensajeadjuntoid(Long mensajeadjuntoid) {
		this.mensajeadjuntoid = mensajeadjuntoid;
	}

	/**
	 * @return the mensajeid
	 */
	public Long getMensajeid() {
		return mensajeid;
	}

	/**
	 * @param mensajeid
	 *            the mensajeid to set
	 */
	public void setMensajeid(Long mensajeid) {
		this.mensajeid = mensajeid;
	}

	/**
	 * @return the adjuntoid
	 */
	public Long getAdjuntoid() {
		return adjuntoid;
	}

	/**
	 * @param adjuntoid
	 *            the adjuntoid to set
	 */
	public void setAdjuntoid(Long adjuntoid) {
		this.adjuntoid = adjuntoid;
	}

}
