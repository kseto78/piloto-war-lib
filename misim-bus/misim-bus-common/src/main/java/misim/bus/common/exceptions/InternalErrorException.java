package misim.bus.common.exceptions;

public class InternalErrorException extends BaseException {

	/**
	 * Serializable
	 */
	private static final long serialVersionUID = -2589017743253750623L;

	private Throwable causa;

	/**
	 * Constructor por defecto
	 */
	public InternalErrorException() {
		super(null, null);
	}

	/**
	 * Constructor
	 * 
	 * @param mensaje
	 *            Mensaje de descripción del error
	 */
	public InternalErrorException(final String mensaje) {
		super(mensaje);
	}

	/**
	 * Constructor
	 * 
	 * @param codigo
	 *            Código del error
	 */
	public InternalErrorException(final Integer codigo) {
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
	public InternalErrorException(final String mensaje, final Integer codigo) {
		super(mensaje, codigo);
	}

	/**
	 * Constructor
	 * 
	 * @param mensaje
	 *            Mensaje de descripción del error
	 * @param codigo
	 *            Código del error
	 * @param cause
	 *            Causa del error
	 */
	public InternalErrorException(final String mensaje, final Integer codigo,
			Throwable causa) {
		super(mensaje, codigo);
		this.causa = causa;
	}

	public Throwable getCausa() {
		return causa;
	}

}
