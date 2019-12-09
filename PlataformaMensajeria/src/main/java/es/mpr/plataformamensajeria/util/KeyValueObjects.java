package es.mpr.plataformamensajeria.util;

/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/

import java.io.Serializable;

public class KeyValueObjects implements Serializable {
	private static final long serialVersionUID = 1L;
	private String codigo;
	private String descripcion;
	private String nombre;

	public KeyValueObjects() {
		// This method has to be empty.
	}

	public KeyValueObjects(String aCodigo, String aDescripcion) {
		setCodigo(aCodigo);
		setDescripcion(aDescripcion);
	}

	public String getCodigo() {
		return this.codigo;
	}

	public final void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public final void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
