package es.minhap.sim.model;

// Generated 18-jul-2016 10:57:14 by Hibernate Tools 3.4.0.CR1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * ViewGestionEmailsHist generated by hbm2java
 */
@Entity
@Table(name = "VIEW_GESTIONEMAILS_HIST")
public class ViewGestionEmailsHist implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7855050995574084033L;
	private ViewGestionEmailsHistId id;

	public ViewGestionEmailsHist() {
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "aplicacion", column = @Column(name = "APLICACION", length = 100)),
			@AttributeOverride(name = "canal", column = @Column(name = "CANAL", length = 100)),
			@AttributeOverride(name = "servicio", column = @Column(name = "SERVICIO", length = 100)),
			@AttributeOverride(name = "estado", column = @Column(name = "ESTADO", length = 50)),
			@AttributeOverride(name = "destinatario", column = @Column(name = "DESTINATARIO", length = 4000)),
			@AttributeOverride(name = "aplicacionid", column = @Column(name = "APLICACIONID", precision = 22, scale = 0)),
			@AttributeOverride(name = "servicioid", column = @Column(name = "SERVICIOID", precision = 22, scale = 0)),
			@AttributeOverride(name = "canalid", column = @Column(name = "CANALID", precision = 22, scale = 0)),
			@AttributeOverride(name = "loteenvioid", column = @Column(name = "LOTEENVIOID", precision = 22, scale = 0)),
			@AttributeOverride(name = "loteenvio", column = @Column(name = "LOTEENVIO", length = 100)),
			@AttributeOverride(name = "emailid", column = @Column(name = "EMAILID", nullable = false, precision = 22, scale = 0)),
			@AttributeOverride(name = "ultimoenvio", column = @Column(name = "ULTIMOENVIO", length = 7)),
			@AttributeOverride(name = "estadoid", column = @Column(name = "ESTADOID", precision = 22, scale = 0)),
			@AttributeOverride(name = "servidorid", column = @Column(name = "SERVIDORID", precision = 22, scale = 0)),
			@AttributeOverride(name = "idexterno", column = @Column(name = "IDEXTERNO", length = 100)),
			@AttributeOverride(name = "fechahistorificacion", column = @Column(name = "FECHAHISTORIFICACION", length = 7)) })
	public ViewGestionEmailsHistId getId() {
		return this.id;
	}

	public void setId(ViewGestionEmailsHistId id) {
		this.id = id;
	}

}
