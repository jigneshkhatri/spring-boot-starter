/**
 * 
 */
package in.quallit.springboot.starter.exceptions;

/**
 * @author JIGS
 *
 */
public abstract class QuallitException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6078484361717946373L;
	private final String message;
	private final String code;

	protected QuallitException(String code, String message) {
		super(message);
		this.message = message;
		this.code = code;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public String getCode() {
		return code;
	}

}
