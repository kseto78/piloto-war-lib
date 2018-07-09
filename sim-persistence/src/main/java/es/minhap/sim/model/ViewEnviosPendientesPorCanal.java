package es.minhap.sim.model;

// Generated 18-jul-2016 10:57:14 by Hibernate Tools 3.4.0.CR1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * ViewEnviosPendientesPorCanal generated by hbm2java
 */
@Entity
@Table(name = "VIEW_ENVIOSPENDIENTESPORCANAL")
public class ViewEnviosPendientesPorCanal implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2208889704157478707L;
	private ViewEnviosPendientesPorCanalId id;

	public ViewEnviosPendientesPorCanal() {
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "aplicacionid", column = @Column(name = "APLICACIONID", nullable = false, precision = 22, scale = 0)),
			@AttributeOverride(name = "nombre", column = @Column(name = "NOMBRE", length = 100)),
			@AttributeOverride(name = "emails", column = @Column(name = "EMAILS", precision = 22, scale = 0)),
			@AttributeOverride(name = "sms", column = @Column(name = "SMS", precision = 22, scale = 0)),
			@AttributeOverride(name = "recepcionsms", column = @Column(name = "RECEPCIONSMS", precision = 22, scale = 0)),
			@AttributeOverride(name = "push", column = @Column(name = "PUSH", precision = 22, scale = 0)) })
	public ViewEnviosPendientesPorCanalId getId() {
		return this.id;
	}

	public void setId(ViewEnviosPendientesPorCanalId id) {
		this.id = id;
	}

}
