package es.mpr.plataformamensajeria.beans;

import java.util.Date;

import com.map.j2ee.auditoria.ifaces.Audit;
import com.map.j2ee.util.StringUtil;

/**
 * <p>
 * Clase que representa un tipo parametro para la capa de presentaci&oacute;n.
 *
 * @author Altran
 */
public class TipoParametroBean implements Audit {


	/**  tipoparametroid. */
	protected Integer tipoparametroid;
	
	/**  nombre. */
	protected String nombre;
	
	/**  descripcion. */
	protected String descripcion;
	
	/**  tags. */
	protected String tags;
	
	/**  tipo. */
	protected Integer tipo;
	
	/**  tipocampo. */
	protected String tipocampo;
	
	/**  activo. */
	protected Boolean activo;
	
	/**  fechacreacion. */
	protected Date fechacreacion = null;
	
	/**  creadopor. */
	protected String creadopor = null;
	
	/**  fechamodificacion. */
	protected Date fechamodificacion = null;
	
	/**  modificadopor. */
	protected String modificadopor = null;

	/**
	 * Constructor de tipo parametro bean.
	 */
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

	/**
	 * Obtener id.
	 *
	 * @return id
	 */
	public Object getId() {
		return this.tipoparametroid;
	}

	/**
	 * Modificar id.
	 *
	 * @param id new id
	 */
	public void setId(Object id) {
		this.tipoparametroid = (Integer) id;
	}

	/**
	 * Obtener nombre.
	 *
	 * @return nombre
	 */
	public String getNombre() {
		if (nombre != null) {
			return StringUtil.capitalize(nombre);
		} else {
			return "";
		}
	}

	/* (non-Javadoc)
	 * @see com.map.j2ee.auditoria.ifaces.Audit#obtenerXML()
	 */
	@Override
	public String obtenerXML() {
		return null;
	}

	/**
	 * Obtener tipoparametroid.
	 *
	 * @return the tipoparametroid
	 */
	public Integer getTipoparametroid() {
		return tipoparametroid;
	}

	/**
	 * Modificar tipoparametroid.
	 *
	 * @param tipoparametroid            the tipoparametroid to set
	 */
	public void setTipoparametroid(Integer tipoparametroid) {
		this.tipoparametroid = tipoparametroid;
	}

	/**
	 * Obtener descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Modificar descripcion.
	 *
	 * @param descripcion            the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Obtener tags.
	 *
	 * @return the tag
	 */
	public String getTags() {
		return tags;
	}

	/**
	 * Modificar tags.
	 *
	 * @param tags new tags
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}

	/**
	 * Obtener tipo.
	 *
	 * @return the tipo
	 */
	public Integer getTipo() {
		return tipo;
	}

	/**
	 * Modificar tipo.
	 *
	 * @param tipo            the tipo to set
	 */
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	/**
	 * Obtener tipocampo.
	 *
	 * @return the tipocampo
	 */
	public String getTipocampo() {
		return tipocampo;
	}

	/**
	 * Modificar tipocampo.
	 *
	 * @param tipocampo            the tipocampo to set
	 */
	public void setTipocampo(String tipocampo) {
		this.tipocampo = tipocampo;
	}

	/**
	 * Obtener activo.
	 *
	 * @return the activo
	 */
	public Boolean getActivo() {
		return activo;
	}

	/**
	 * Modificar activo.
	 *
	 * @param activo            the activo to set
	 */
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	/**
	 * Obtener fechacreacion.
	 *
	 * @return the fechacreacion
	 */
	public Date getFechacreacion() {
		return fechacreacion;
	}

	/**
	 * Modificar fechacreacion.
	 *
	 * @param fechacreacion            the fechacreacion to set
	 */
	public void setFechacreacion(Date fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	/**
	 * Obtener creadopor.
	 *
	 * @return the creadopor
	 */
	public String getCreadopor() {
		return creadopor;
	}

	/**
	 * Modificar creadopor.
	 *
	 * @param creadopor            the creadopor to set
	 */
	public void setCreadopor(String creadopor) {
		this.creadopor = creadopor;
	}

	/**
	 * Obtener fechamodificacion.
	 *
	 * @return the fechamodificacion
	 */
	public Date getFechamodificacion() {
		return fechamodificacion;
	}

	/**
	 * Modificar fechamodificacion.
	 *
	 * @param fechamodificacion            the fechamodificacion to set
	 */
	public void setFechamodificacion(Date fechamodificacion) {
		this.fechamodificacion = fechamodificacion;
	}

	/**
	 * Obtener modificadopor.
	 *
	 * @return the modificadopor
	 */
	public String getModificadopor() {
		return modificadopor;
	}

	/**
	 * Modificar modificadopor.
	 *
	 * @param modificadopor            the modificadopor to set
	 */
	public void setModificadopor(String modificadopor) {
		this.modificadopor = modificadopor;
	}

	/**
	 * Modificar nombre.
	 *
	 * @param nombre            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
