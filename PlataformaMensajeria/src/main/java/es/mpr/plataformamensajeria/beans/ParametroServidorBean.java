package es.mpr.plataformamensajeria.beans;

import java.util.Date;

import com.map.j2ee.auditoria.ifaces.Audit;
import com.map.j2ee.util.StringUtil;

/**
 * <p>
 * Clase que representa un servidor para la capa de presentaci&oacute;n
 * 
 * @author Altran
 * 
 */
public class ParametroServidorBean implements Audit {
	public static final int IP = 1;
	public static final int USUARIO = 2;
	public static final int PASSWORD = 3;
	public static final int CONEXION_SEGURA = 4;
	public static final int PUERTO = 10;
	public static final int REQ_AUTH = 11;

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

	protected Long parametroservidorid;

	protected String valor = null;

	protected Long tipoparametroid = null;

	protected Long servidorid = null;

	protected Boolean activo = null;

	protected Date fechacreacion = null;

	protected String creadopor = null;

	protected Date fechamodificacion = null;

	protected String modificadopor = null;

	protected String tiponombre = null;

	protected String tipodescripcion = null;

	protected String tipotag = null;

	public String getTipoNombre() {
		if (tiponombre != null) {
			return StringUtil.capitalize(tiponombre);
		} else {
			return "";
		}
	}

	public void setTiponombre(String tipoNombre) {
		this.tiponombre = tipoNombre;
	}

	public Object getId() {
		return this.parametroservidorid;
	}

	public void setId(Object id) {
		this.parametroservidorid = (Long) id;
	}

	@Override
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return the parametroservidorid
	 */
	public Long getParametroservidorid() {
		return parametroservidorid;
	}

	/**
	 * @param parametroservidorid
	 *            the parametroservidorid to set
	 */
	public void setParametroservidorid(Long parametroservidorid) {
		this.parametroservidorid = parametroservidorid;
	}

	/**
	 * @return the valor
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * @param valor
	 *            the valor to set
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}

	/**
	 * @return the tipoparametroid
	 */
	public Long getTipoparametroid() {
		return tipoparametroid;
	}

	/**
	 * @param tipoparametroid
	 *            the tipoparametroid to set
	 */
	public void setTipoparametroid(Long tipoparametroid) {
		this.tipoparametroid = tipoparametroid;
	}

	/**
	 * @return the servidorid
	 */
	public Long getServidorid() {
		return servidorid;
	}

	/**
	 * @param servidorid
	 *            the servidorid to set
	 */
	public void setServidorid(Long servidorid) {
		this.servidorid = servidorid;
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
	 * @return the tipodescripcion
	 */
	public String getTipodescripcion() {
		return tipodescripcion;
	}

	/**
	 * @param tipodescripcion
	 *            the tipodescripcion to set
	 */
	public void setTipodescripcion(String tipodescripcion) {
		this.tipodescripcion = tipodescripcion;
	}

	/**
	 * @return the tipotag
	 */
	public String getTipotag() {
		return tipotag;
	}

	/**
	 * @param tipotag
	 *            the tipotag to set
	 */
	public void setTipotag(String tipotag) {
		this.tipotag = tipotag;
	}

	/**
	 * @return the tiponombre
	 */
	public String getTiponombre() {
		return tiponombre;
	}

}
