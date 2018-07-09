package es.minhap.plataformamensajeria.sm.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Proveedor implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private BigDecimal servidorid;
	private String nombre;
	private String descripcion;
	private BigDecimal pordefecto;
	private BigDecimal activo;
	private Date fechacreacion;
	private String creadopor;
	private Date fechamodificacion;
	private String modificadopor;
	private String urlDestino;
	private BigDecimal tipo;
	private String externalid;
	private String eliminado;
	private Integer metodoConsulta;
	private String usuario;
	private String password;

	/**
	 * @param servidorid
	 * @param nombre
	 * @param descripcion
	 * @param pordefecto
	 * @param activo
	 * @param fechacreacion
	 * @param creadopor
	 * @param fechamodificacion
	 * @param modificadopor
	 * @param urlDestino
	 * @param tipo
	 * @param externalid
	 * @param eliminado
	 */
	
	public Proveedor(BigDecimal servidorid, String nombre, String descripcion,
			BigDecimal pordefecto, BigDecimal activo, Date fechacreacion,
			String creadopor, Date fechamodificacion, String modificadopor,
			String urlDestino, BigDecimal tipo, String externalid,
			String eliminado, Integer metodoConsulta) {
		this.servidorid = servidorid;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.pordefecto = pordefecto;
		this.activo = activo;
		this.fechacreacion = fechacreacion;
		this.creadopor = creadopor;
		this.fechamodificacion = fechamodificacion;
		this.modificadopor = modificadopor;
		this.urlDestino = urlDestino;
		this.tipo = tipo;
		this.externalid = externalid;
		this.eliminado = eliminado;
		this.metodoConsulta = metodoConsulta;
	}

	public Proveedor() {
		
	}

	public BigDecimal getServidorid() {
		return servidorid;
	}

	public void setServidorid(BigDecimal servidorid) {
		this.servidorid = servidorid;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getPordefecto() {
		return pordefecto;
	}

	public void setPordefecto(BigDecimal pordefecto) {
		this.pordefecto = pordefecto;
	}

	public BigDecimal getActivo() {
		return activo;
	}

	public void setActivo(BigDecimal activo) {
		this.activo = activo;
	}

	public Date getFechacreacion() {
		return fechacreacion;
	}

	public void setFechacreacion(Date fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public String getCreadopor() {
		return creadopor;
	}

	public void setCreadopor(String creadopor) {
		this.creadopor = creadopor;
	}

	public Date getFechamodificacion() {
		return fechamodificacion;
	}

	public void setFechamodificacion(Date fechamodificacion) {
		this.fechamodificacion = fechamodificacion;
	}

	public String getModificadopor() {
		return modificadopor;
	}

	public void setModificadopor(String modificadopor) {
		this.modificadopor = modificadopor;
	}

	public String getUrlDestino() {
		return urlDestino;
	}

	public void setUrlDestino(String urlDestino) {
		this.urlDestino = urlDestino;
	}

	public BigDecimal getTipo() {
		return tipo;
	}

	public void setTipo(BigDecimal tipo) {
		this.tipo = tipo;
	}

	public String getExternalid() {
		return externalid;
	}

	public void setExternalid(String externalid) {
		this.externalid = externalid;
	}

	public String getEliminado() {
		return eliminado;
	}

	public void setEliminado(String eliminado) {
		this.eliminado = eliminado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getMetodoConsulta() {
		return metodoConsulta;
	}

	public void setMetodoConsulta(Integer metodoConsulta) {
		this.metodoConsulta = metodoConsulta;
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
