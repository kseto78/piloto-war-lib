package es.mpr.plataformamensajeria.beans;

import java.io.Serializable;
import java.util.Date;

import com.map.j2ee.auditoria.ifaces.Audit;
import com.map.j2ee.util.StringUtil;

/**
 * <p>
 * Clase que representa un servidor para la capa de presentaci&oacute;n.
 *
 * @author Altran
 */
public class ParametroServidorBean implements Audit, Serializable {
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** Constante IP. */
	public static final int IP = 1;
	
	/** Constante USUARIO. */
	public static final int USUARIO = 2;
	
	/** Constante PASSWORD. */
	public static final int PASS = 3;
	
	/** Constante CONEXION_SEGURA. */
	public static final int CONEXION_SEGURA = 4;
	
	/** Constante PUERTO. */
	public static final int PUERTO = 10;
	
	/** Constante REQ_AUTH. */
	public static final int REQ_AUTH = 11;

	/**  parametroservidorid. */
	protected Long parametroservidorid;

	/**  valor. */
	protected String valor = null;

	/**  tipoparametroid. */
	protected Long tipoparametroid = null;

	/**  servidorid. */
	protected Long servidorid = null;

	/**  activo. */
	protected Boolean activo = null;

	/**  fechacreacion. */
	protected Date fechacreacion = null;

	/**  creadopor. */
	protected String creadopor = null;

	/**  fechamodificacion. */
	protected Date fechamodificacion = null;

	/**  modificadopor. */
	protected String modificadopor = null;

	/**  tiponombre. */
	protected String tiponombre = null;

	/**  tipodescripcion. */
	protected String tipodescripcion = null;

	/**  tipotag. */
	protected String tipotag = null;

	/**
	 * Constructor de parametro servidor bean.
	 */
	public ParametroServidorBean() {

		this.parametroservidorid = null;
		this.tipoparametroid = null;
		this.tiponombre = null;
		this.valor = null;
		this.tipodescripcion = null;
		this.servidorid = null;
		this.tipotag = null;
		this.activo = null;
		this.fechacreacion = null;
		this.creadopor = null;
		this.fechamodificacion = null;
		this.modificadopor = null;

	}

	/**
	 * Obtener tipo nombre.
	 *
	 * @return tipo nombre
	 */
	public String getTipoNombre() {
		if (tiponombre != null) {
			return StringUtil.capitalize(tiponombre);
		} else {
			return "";
		}
	}

	/**
	 * Modificar tiponombre.
	 *
	 * @param tipoNombre new tiponombre
	 */
	public void setTiponombre(String tipoNombre) {
		this.tiponombre = tipoNombre;
	}

	/**
	 * Obtener id.
	 *
	 * @return id
	 */
	public Object getId() {
		return this.parametroservidorid;
	}

	/**
	 * Modificar id.
	 *
	 * @param id new id
	 */
	public void setId(Object id) {
		this.parametroservidorid = (Long) id;
	}

	/* (non-Javadoc)
	 * @see com.map.j2ee.auditoria.ifaces.Audit#obtenerXML()
	 */
	@Override
	public String obtenerXML() {
		return null;
	}

	/**
	 * Obtener parametroservidorid.
	 *
	 * @return the parametroservidorid
	 */
	public Long getParametroservidorid() {
		return parametroservidorid;
	}

	/**
	 * Modificar parametroservidorid.
	 *
	 * @param parametroservidorid            the parametroservidorid to set
	 */
	public void setParametroservidorid(Long parametroservidorid) {
		this.parametroservidorid = parametroservidorid;
	}

	/**
	 * Obtener valor.
	 *
	 * @return the valor
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * Modificar valor.
	 *
	 * @param valor            the valor to set
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}

	/**
	 * Obtener tipoparametroid.
	 *
	 * @return the tipoparametroid
	 */
	public Long getTipoparametroid() {
		return tipoparametroid;
	}

	/**
	 * Modificar tipoparametroid.
	 *
	 * @param tipoparametroid            the tipoparametroid to set
	 */
	public void setTipoparametroid(Long tipoparametroid) {
		this.tipoparametroid = tipoparametroid;
	}

	/**
	 * Obtener servidorid.
	 *
	 * @return the servidorid
	 */
	public Long getServidorid() {
		return servidorid;
	}

	/**
	 * Modificar servidorid.
	 *
	 * @param servidorid            the servidorid to set
	 */
	public void setServidorid(Long servidorid) {
		this.servidorid = servidorid;
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
	 * Obtener tipodescripcion.
	 *
	 * @return the tipodescripcion
	 */
	public String getTipodescripcion() {
		return tipodescripcion;
	}

	/**
	 * Modificar tipodescripcion.
	 *
	 * @param tipodescripcion            the tipodescripcion to set
	 */
	public void setTipodescripcion(String tipodescripcion) {
		this.tipodescripcion = tipodescripcion;
	}

	/**
	 * Obtener tipotag.
	 *
	 * @return the tipotag
	 */
	public String getTipotag() {
		return tipotag;
	}

	/**
	 * Modificar tipotag.
	 *
	 * @param tipotag            the tipotag to set
	 */
	public void setTipotag(String tipotag) {
		this.tipotag = tipotag;
	}
	

}
