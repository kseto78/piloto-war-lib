package es.minhap.sim.model;

// Generated 18-jul-2016 10:57:14 by Hibernate Tools 3.4.0.CR1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * ViewCanales generated by hbm2java
 */
@Entity
@Table(name = "VIEW_CANALES")
public class ViewCanales implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4458602844172996935L;
	private ViewCanalesId id;

	public ViewCanales() {
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "canalid", column = @Column(name = "CANALID", nullable = false, precision = 22, scale = 0)),
			@AttributeOverride(name = "nombre", column = @Column(name = "NOMBRE", length = 100)),
			@AttributeOverride(name = "descripcion", column = @Column(name = "DESCRIPCION", length = 500)),
			@AttributeOverride(name = "activo", column = @Column(name = "ACTIVO", precision = 1, scale = 0)),
			@AttributeOverride(name = "fechacreacion", column = @Column(name = "FECHACREACION", length = 7)),
			@AttributeOverride(name = "creadopor", column = @Column(name = "CREADOPOR", length = 100)),
			@AttributeOverride(name = "fechamodificacion", column = @Column(name = "FECHAMODIFICACION", length = 7)),
			@AttributeOverride(name = "modificadopor", column = @Column(name = "MODIFICADOPOR", length = 100)) })
	public ViewCanalesId getId() {
		return this.id;
	}

	public void setId(ViewCanalesId id) {
		this.id = id;
	}

}
