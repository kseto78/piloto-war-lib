package misim.bus.common.exceptions;

/**
 * An Application Exception is thrown in case of technical issue
 * 
 * @author ludarcos
 * 
 */
public class ApplicationException extends RuntimeException {

	/**
	 * Serializable
	 */
	private static final long serialVersionUID = 5413272705728875861L;

	/**
	 * Optional error code of the exception
	 */
	private Integer errorCode;

	public ApplicationException() {
		super();
	}

	public ApplicationException(String message) {
		super(message);
	}

	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ApplicationException(Throwable cause) {
		super(cause);
	}

	public ApplicationException(Integer errorCode) {
		super();
		this.errorCode = errorCode;
	}

	public ApplicationException(Integer errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public ApplicationException(Integer errorCode, String message,
			Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public ApplicationException(Integer errorCode, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
	}

	/**
	 * Get the error code of the exception
	 * 
	 * @return the error code if any, null otherwise
	 */
	public Integer getErrorCode() {
		return errorCode;
	}

	/**
	 * Assert the condition is true. If not an exception is thrown with the
	 * given message
	 */
	public static void assertTrue(boolean condition, String message) {
		if (!condition) {
			throw new ApplicationException(message);
		}
	}

	/**
	 * Assert the condition is false. If not an exception is thrown with the
	 * given message
	 */
	public static void assertFalse(boolean bool, String message) {
		if (bool) {
			throw new ApplicationException(message);
		}
	}

	/**
	 * Assert the given object is null. If not an exception is thrown with the
	 * given message
	 */
	public static void assertNull(Object obj, String message) {
		if (obj != null) {
			throw new ApplicationException(message);
		}
	}

	/**
	 * Assert the given object is not null. If not an exception is thrown with
	 * the given message
	 */
	public static void assertNotNull(Object obj, String message) {
		if (obj == null) {
			throw new ApplicationException(message);
		}
	}

}