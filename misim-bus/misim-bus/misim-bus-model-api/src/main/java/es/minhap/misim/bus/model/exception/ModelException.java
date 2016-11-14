package es.minhap.misim.bus.model.exception;

import misim.bus.common.exceptions.BaseException;

public class ModelException extends BaseException {

	/**
	 * Serializable
	 */
	private static final long serialVersionUID = 6347080968704213027L;
	
	/**
	 * Constructor por defecto
	 */
	public ModelException() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param mensaje
	 *            Mensaje de descripción del error
	 */
	public ModelException(final String mensaje) {
		super(mensaje);
	}

	/**
	 * Constructor
	 * 
	 * @param codigo
	 *            Código de error
	 */
	public ModelException(final Integer codigo) {
		super(codigo);
	}

	/**
	 * Constructor
	 * 
	 * @param mensaje
	 *            Mensaje de descripción del error
	 * @param codigo
	 *            Código de error
	 */
	public ModelException(final String mensaje, final Integer codigo) {
		super(mensaje, codigo);
	}
	
	/**
	 * Constructor
	 * 
	 * @param mensaje
	 *            Mensaje de descripción del error
	 * @param codigo
	 *            Código de error
	 * @param codigo
	 *            Detalle del error
	 */
	public ModelException(final String mensaje, final Integer codigo, final String details) {
		super(mensaje, codigo, details);
	}
}
