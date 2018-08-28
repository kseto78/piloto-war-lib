package es.mpr.plataformamensajeria.beans;

import java.io.Serializable;

import com.map.j2ee.auditoria.ifaces.Audit;

/**
 * <p>
 * Clase que representa un misim para la capa de presentaci&oacute;n.
 *
 * @author everis
 */
public class ParametrosProveedorBean implements Audit,Serializable {

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;


	/**
	 * Constructor de parametros proveedor bean.
	 */
	public ParametrosProveedorBean() {
		super();
		this.idParametrosProveedor = null;
		this.idProveedor = null;
		this.parametro = null;
		this.valor = null;
		this.variable = null;
		this.tipoValor = null;
		this.resultadoValor = null;
	}


	/**  id parametros proveedor. */
	protected Long idParametrosProveedor = null;
	
	/**  id proveedor. */
	protected Long idProveedor = null;
	
	/**  parametro. */
	protected String parametro = null;
	
	/**  valor. */
	protected String valor = null;
	
	/**  variable. */
	protected String variable = null;
	
	/**  tipo valor. */
	protected String tipoValor = null;
	
	/**  resultado valor. */
	protected String resultadoValor = null;
	

	/**
	 * Obtener id parametros proveedor.
	 *
	 * @return id parametros proveedor
	 */
	public Long getIdParametrosProveedor() {
		return idParametrosProveedor;
	}


	/**
	 * Modificar id parametros proveedor.
	 *
	 * @param idParametrosProveedor new id parametros proveedor
	 */
	public void setIdParametrosProveedor(Long idParametrosProveedor) {
		this.idParametrosProveedor = idParametrosProveedor;
	}


	/**
	 * Obtener id proveedor.
	 *
	 * @return id proveedor
	 */
	public Long getIdProveedor() {
		return idProveedor;
	}


	/**
	 * Modificar id proveedor.
	 *
	 * @param idProveedor new id proveedor
	 */
	public void setIdProveedor(Long idProveedor) {
		this.idProveedor = idProveedor;
	}


	/**
	 * Obtener parametro.
	 *
	 * @return parametro
	 */
	public String getParametro() {
		return parametro;
	}


	/**
	 * Modificar parametro.
	 *
	 * @param parametro new parametro
	 */
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}


	/**
	 * Obtener valor.
	 *
	 * @return valor
	 */
	public String getValor() {
		return valor;
	}


	/**
	 * Modificar valor.
	 *
	 * @param valor new valor
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}


	/**
	 * Obtener variable.
	 *
	 * @return variable
	 */
	public String getVariable() {
		return variable;
	}


	/**
	 * Modificar variable.
	 *
	 * @param variable new variable
	 */
	public void setVariable(String variable) {
		this.variable = variable;
	}


	/**
	 * Obtener tipo valor.
	 *
	 * @return tipo valor
	 */
	public String getTipoValor() {
		return tipoValor;
	}


	/**
	 * Modificar tipo valor.
	 *
	 * @param tipoValor new tipo valor
	 */
	public void setTipoValor(String tipoValor) {
		this.tipoValor = tipoValor;
	}

	
	/**
	 * Obtener resultado valor.
	 *
	 * @return resultado valor
	 */
	public String getResultadoValor() {
		return resultadoValor;
	}


	/**
	 * Modificar resultado valor.
	 *
	 * @param resultadoValor new resultado valor
	 */
	public void setResultadoValor(String resultadoValor) {
		this.resultadoValor = resultadoValor;
	}


	/* (non-Javadoc)
	 * @see com.map.j2ee.auditoria.ifaces.Audit#obtenerXML()
	 */
	@Override
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
