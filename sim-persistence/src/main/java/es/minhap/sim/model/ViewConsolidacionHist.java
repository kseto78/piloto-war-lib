package es.minhap.sim.model;

// Generated 18-jul-2016 10:57:14 by Hibernate Tools 3.4.0.CR1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * ViewConsolidacionHist generated by hbm2java
 */
@Entity
@Table(name = "VIEW_CONSOLIDACION_HIST")
public class ViewConsolidacionHist implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5712479383668021151L;
	private ViewConsolidacionHistId id;

	public ViewConsolidacionHist() {
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "destinatarioiddh", column = @Column(name = "DESTINATARIOIDDH", nullable = false, precision = 22, scale = 0)),
			@AttributeOverride(name = "historicoidhh", column = @Column(name = "HISTORICOIDHH", nullable = false, precision = 22, scale = 0)),
			@AttributeOverride(name = "adjuntoidah", column = @Column(name = "ADJUNTOIDAH", precision = 22, scale = 0)),
			@AttributeOverride(name = "mensajeadjuntoidmah", column = @Column(name = "MENSAJEADJUNTOIDMAH", precision = 22, scale = 0)) })
	public ViewConsolidacionHistId getId() {
		return this.id;
	}

	public void setId(ViewConsolidacionHistId id) {
		this.id = id;
	}

}
