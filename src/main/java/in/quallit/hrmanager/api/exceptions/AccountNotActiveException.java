/**
 * 
 */
package in.quallit.hrmanager.api.exceptions;

// TODO: Auto-generated Javadoc
/**
 * The Class AccountNotActiveException.
 *
 * @author JIGS
 */
public class AccountNotActiveException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5267793475902321092L;

	/**
	 * Instantiates a new account not active exception.
	 */
	public AccountNotActiveException() {
		super("Account is not active");
	}

	/**
	 * Instantiates a new account not active exception.
	 *
	 * @param message the message
	 */
	public AccountNotActiveException(String message) {
		super(message);
	}
}
