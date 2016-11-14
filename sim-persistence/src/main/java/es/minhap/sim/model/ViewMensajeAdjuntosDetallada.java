package es.minhap.sim.model;

// Generated 18-jul-2016 10:57:14 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ViewMensajeAdjuntosDetallada generated by hbm2java
 */
@Entity
@Table(name = "VIEW_MENSAJEADJUNTOS_DETALLADA")
public class ViewMensajeAdjuntosDetallada implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6879218840357448308L;

	@Id
	@Column(name = "MENSAJEADJUNTOID", nullable = false, precision = 22, scale = 0)
	private Long mensajeadjuntoid;

	@Column(name = "MENSAJEID", precision = 22, scale = 0)
	private Long mensajeid;

	@Column(name = "ADJUNTOID", nullable = false, precision = 22, scale = 0)
	private Long adjuntoid;

	@Column(name = "USUARIO", length = 50)
	private String usuario;

	@Column(name = "PASSWORD", length = 50)
	private String password;

	public ViewMensajeAdjuntosDetallada() {
	}

	public Long getMensajeadjuntoid() {
		return mensajeadjuntoid;
	}

	public void setMensajeadjuntoid(Long mensajeadjuntoid) {
		this.mensajeadjuntoid = mensajeadjuntoid;
	}

	public Long getMensajeid() {
		return mensajeid;
	}

	public void setMensajeid(Long mensajeid) {
		this.mensajeid = mensajeid;
	}

	public Long getAdjuntoid() {
		return adjuntoid;
	}

	public void setAdjuntoid(Long adjuntoid) {
		this.adjuntoid = adjuntoid;
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
