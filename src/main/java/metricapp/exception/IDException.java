package metricapp.exception;

public class IDException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8382006402148339211L;

	public IDException() {
	}

	public IDException(String message) {
		super(message);
	}

	public IDException(Throwable cause) {
		super(cause);
	}

	public IDException(String message, Throwable cause) {
		super(message, cause);
	}

	public IDException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
