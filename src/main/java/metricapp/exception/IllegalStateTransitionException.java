package metricapp.exception;

public class IllegalStateTransitionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -917704349155420972L;

	public IllegalStateTransitionException() {
	}

	public IllegalStateTransitionException(String message) {
		super(message);
	}

	public IllegalStateTransitionException(Throwable cause) {
		super(cause);
	}

	public IllegalStateTransitionException(String message, Throwable cause) {
		super(message, cause);
	}

	public IllegalStateTransitionException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
