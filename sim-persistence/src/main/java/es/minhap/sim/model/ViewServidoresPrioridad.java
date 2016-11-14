package es.minhap.sim.model;

// Generated 18-jul-2016 10:57:14 by Hibernate Tools 3.4.0.CR1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * ViewServidoresPrioridad generated by hbm2java
 */
@Entity
@Table(name = "VIEW_SERVIDORES_PRIORIDAD")
public class ViewServidoresPrioridad implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6844127478254052679L;
	private ViewServidoresPrioridadId id;

	public ViewServidoresPrioridad() {
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "servidorid", column = @Column(name = "SERVIDORID", precision = 22, scale = 0)),
			@AttributeOverride(name = "ip", column = @Column(name = "IP", length = 200)),
			@AttributeOverride(name = "usuario", column = @Column(name = "USUARIO", length = 200)),
			@AttributeOverride(name = "contraseña", column = @Column(name = "CONTRASEÑA", length = 200)),
			@AttributeOverride(name = "conexion", column = @Column(name = "CONEXION", length = 200)),
			@AttributeOverride(name = "puerto", column = @Column(name = "PUERTO", length = 200)),
			@AttributeOverride(name = "autentificacion", column = @Column(name = "AUTENTIFICACION", length = 200)),
			@AttributeOverride(name = "tiempoespera", column = @Column(name = "TIEMPOESPERA", length = 200)),
			@AttributeOverride(name = "servicioid", column = @Column(name = "SERVICIOID", precision = 22, scale = 0)),
			@AttributeOverride(name = "prioridad", column = @Column(name = "PRIORIDAD", precision = 22, scale = 0)) })
	public ViewServidoresPrioridadId getId() {
		return this.id;
	}

	public void setId(ViewServidoresPrioridadId id) {
		this.id = id;
	}

}
