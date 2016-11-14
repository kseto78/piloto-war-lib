package es.minhap.sim.model;

// Generated 18-jul-2016 10:57:14 by Hibernate Tools 3.4.0.CR1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * ViewMensajesAdjuntosHist generated by hbm2java
 */
@Entity
@Table(name = "VIEW_MENSAJESADJUNTOS_HIST")
public class ViewMensajesAdjuntosHist implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8992394274314045217L;
	private ViewMensajesAdjuntosHistId id;

	public ViewMensajesAdjuntosHist() {
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "mensajeadjuntoid", column = @Column(name = "MENSAJEADJUNTOID", nullable = false, precision = 22, scale = 0)),
			@AttributeOverride(name = "mensajeid", column = @Column(name = "MENSAJEID", precision = 22, scale = 0)),
			@AttributeOverride(name = "adjuntoid", column = @Column(name = "ADJUNTOID", nullable = false, precision = 22, scale = 0)),
			@AttributeOverride(name = "fechahistorificacion", column = @Column(name = "FECHAHISTORIFICACION", length = 7)) })
	public ViewMensajesAdjuntosHistId getId() {
		return this.id;
	}

	public void setId(ViewMensajesAdjuntosHistId id) {
		this.id = id;
	}

}
