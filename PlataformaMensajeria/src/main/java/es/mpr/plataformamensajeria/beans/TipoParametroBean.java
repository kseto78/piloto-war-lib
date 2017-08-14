package es.mpr.plataformamensajeria.beans;

import java.util.Date;

import com.map.j2ee.auditoria.ifaces.Audit;
import com.map.j2ee.util.StringUtil;

/**
 * <p>
 * Clase que representa un tipo parametro para la capa de presentaci&oacute;n
 * 
 * @author Altran
 * 
 */
public class TipoParametroBean implements Audit {


	protected Integer tipoparametroid;
	protected String nombre;
	protected String descripcion;
	protected String tags;
	protected Integer tipo;
	protected String tipocampo;
	protected Boolean activo;
	protected Date fechacreacion = null;
	protected String creadopor = null;
	protected Date fechamodificacion = null;
	protected String modificadopor = null;

	public TipoParametroBean() {
		super();
		this.tipoparametroid = null;
		this.nombre = null;
		this.descripcion = null;
		this.tags = null;
		this.activo = null;
		this.tipo = null;
		this.fechacreacion = null;
		this.creadopor = null;
		this.fechamodificacion = null;
		this.modificadopor = null;
	}

	public Object getId() {
		return this.tipoparametroid;
	}

	public void setId(Object id) {
		this.tipoparametroid = (Integer) id;
	}

	public String getNombre() {
		if (nombre != null) {
			return StringUtil.capitalize(nombre);
		} else {
			return "";
		}
	}

	@Override
	public String obtenerXML() {
		return null;
	}

	/**
	 * @return the tipoparametroid
	 */
	public Integer getTipoparametroid() {
		return tipoparametroid;
	}

	/**
	 * @param tipoparametroid
	 *            the tipoparametroid to set
	 */
	public void setTipoparametroid(Integer tipoparametroid) {
		this.tipoparametroid = tipoparametroid;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion
	 *            the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the tag
	 */
	public String getTags() {
		return tags;
	}

	/**
	 * @param tag
	 *            the tag to set
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}

	/**
	 * @return the tipo
	 */
	public Integer getTipo() {
		return tipo;
	}

	/**
	 * @param tipo
	 *            the tipo to set
	 */
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the tipocampo
	 */
	public String getTipocampo() {
		return tipocampo;
	}

	/**
	 * @param tipocampo
	 *            the tipocampo to set
	 */
	public void setTipocampo(String tipocampo) {
		this.tipocampo = tipocampo;
	}

	/**
	 * @return the activo
	 */
	public Boolean getActivo() {
		return activo;
	}

	/**
	 * @param activo
	 *            the activo to set
	 */
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	/**
	 * @return the fechacreacion
	 */
	public Date getFechacreacion() {
		return fechacreacion;
	}

	/**
	 * @param fechacreacion
	 *            the fechacreacion to set
	 */
	public void setFechacreacion(Date fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	/**
	 * @return the creadopor
	 */
	public String getCreadopor() {
		return creadopor;
	}

	/**
	 * @param creadopor
	 *            the creadopor to set
	 */
	public void setCreadopor(String creadopor) {
		this.creadopor = creadopor;
	}

	/**
	 * @return the fechamodificacion
	 */
	public Date getFechamodificacion() {
		return fechamodificacion;
	}

	/**
	 * @param fechamodificacion
	 *            the fechamodificacion to set
	 */
	public void setFechamodificacion(Date fechamodificacion) {
		this.fechamodificacion = fechamodificacion;
	}

	/**
	 * @return the modificadopor
	 */
	public String getModificadopor() {
		return modificadopor;
	}

	/**
	 * @param modificadopor
	 *            the modificadopor to set
	 */
	public void setModificadopor(String modificadopor) {
		this.modificadopor = modificadopor;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
