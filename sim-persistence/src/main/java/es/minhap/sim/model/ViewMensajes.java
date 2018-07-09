package es.minhap.sim.model;

// Generated 18-jul-2016 10:57:14 by Hibernate Tools 3.4.0.CR1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * ViewMensajes generated by hbm2java
 */
@Entity
@Table(name = "VIEW_MENSAJES")
public class ViewMensajes implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1729221939709318664L;
	private ViewMensajesId id;

	public ViewMensajes() {
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "mensajeid", column = @Column(name = "MENSAJEID", nullable = false, precision = 22, scale = 0)),
			@AttributeOverride(name = "loteenvioid", column = @Column(name = "LOTEENVIOID", nullable = false, precision = 22, scale = 0)),
			@AttributeOverride(name = "codigoexterno", column = @Column(name = "CODIGOEXTERNO", length = 100)),
			@AttributeOverride(name = "cuerpo", column = @Column(name = "CUERPO")),
			@AttributeOverride(name = "cabecera", column = @Column(name = "CABECERA", length = 2000)),
			@AttributeOverride(name = "estadoactual", column = @Column(name = "ESTADOACTUAL", nullable = false, length = 50)),
			@AttributeOverride(name = "numeroenvios", column = @Column(name = "NUMEROENVIOS", precision = 10, scale = 0)),
			@AttributeOverride(name = "ultimoenvio", column = @Column(name = "ULTIMOENVIO", length = 7)),
			@AttributeOverride(name = "fechacreacion", column = @Column(name = "FECHACREACION", nullable = false, length = 7)),
			@AttributeOverride(name = "creadopor", column = @Column(name = "CREADOPOR", nullable = false, length = 100)),
			@AttributeOverride(name = "fechamodificacion", column = @Column(name = "FECHAMODIFICACION", length = 7)),
			@AttributeOverride(name = "modificadopor", column = @Column(name = "MODIFICADOPOR", length = 100)),
			@AttributeOverride(name = "ultimoidhistorico", column = @Column(name = "ULTIMOIDHISTORICO", precision = 22, scale = 0)),
			@AttributeOverride(name = "tipocuerpo", column = @Column(name = "TIPOCUERPO", length = 10)),
			@AttributeOverride(name = "tipocodificacion", column = @Column(name = "TIPOCODIFICACION", length = 50)),
			@AttributeOverride(name = "prioridad", column = @Column(name = "PRIORIDAD", precision = 22, scale = 0)),
			@AttributeOverride(name = "tipomensaje", column = @Column(name = "TIPOMENSAJE", nullable = false, length = 20)),
			@AttributeOverride(name = "telefono", column = @Column(name = "TELEFONO", length = 15)),
			@AttributeOverride(name = "canalid", column = @Column(name = "CANALID", precision = 22, scale = 0)) })
	public ViewMensajesId getId() {
		return this.id;
	}

	public void setId(ViewMensajesId id) {
		this.id = id;
	}

}
