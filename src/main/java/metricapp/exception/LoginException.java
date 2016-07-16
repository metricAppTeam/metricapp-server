package metricapp.exception;

public class LoginException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8382006402148339211L;

	public LoginException() {
	}

	public LoginException(String message) {
		super(message);
	}

	public LoginException(Throwable cause) {
		super(cause);
	}

	public LoginException(String message, Throwable cause) {
		super(message, cause);
	}

	public LoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
