package metricapp.exception;


public class BusException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7469888499359766214L;

	public BusException() {
    }

    public BusException(String message) {
        super(message);
    }

    public BusException(Throwable cause) {
        super(cause);
    }

    public BusException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
