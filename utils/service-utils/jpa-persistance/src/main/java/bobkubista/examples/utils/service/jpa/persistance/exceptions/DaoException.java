/**
 *
 */
package bobkubista.examples.utils.service.jpa.persistance.exceptions;

/**
 * @author bkubista
 *
 */
public class DaoException extends BobException {

	private static final long serialVersionUID = -1915096235318812613L;

	/**
	 * Constructor
	 * 
	 * @param message
	 *            the error message
	 */
	public DaoException(final String message) {
		super(message);
	}

	/**
	 * Consructor
	 * 
	 * @param message
	 *            the error message
	 * @param cause
	 *            the cause
	 */
	public DaoException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor
	 * 
	 * @param cause
	 *            the cause
	 */
	public DaoException(final Throwable cause) {
		super(cause);
	}

}
