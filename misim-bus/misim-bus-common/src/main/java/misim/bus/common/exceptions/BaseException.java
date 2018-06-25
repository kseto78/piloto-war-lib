package misim.bus.common.exceptions;

public class BaseException extends Exception {

	/**
	 * Serialization
	 */
	private static final long serialVersionUID = 6347080968704213027L;

	private String mensaje;
	private int codigo;
	private String details;

	/**
	 * Constructor por defecto
	 */
	public BaseException() {
		this(null, null);
	}

	/**
	 * Constructor
	 * 
	 * @param mensaje
	 *            Descripción de la excepción
	 */
	public BaseException(final String mensaje) {
		this(mensaje, null);
	}

	/**
	 * Constructor
	 * 
	 * @param codigo
	 *            Código del error
	 */
	public BaseException(final Integer codigo) {
		this(null, codigo);
	}

	/**
	 * Constructor
	 * 
	 * @param mensaje
	 *            Descripción del error
	 * @param codigo
	 *            Código del error
	 */
	public BaseException(final String mensaje, final Integer codigo) {
		this.mensaje = mensaje;
		this.codigo = codigo;
	}
	
	/**
	 * Constructor
	 * 
	 * @param mensaje
	 *            Descripción del error
	 * @param codigo
	 *            Código del error
	 * @param codigo
	 *            Detail del error
	 */
	public BaseException(final String mensaje, final Integer codigo, String details) {
		this.mensaje = mensaje;
		this.codigo = codigo;
		this.details = details;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
}
