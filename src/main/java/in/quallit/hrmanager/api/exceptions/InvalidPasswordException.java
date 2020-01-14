/**
 * 
 */
package in.quallit.hrmanager.api.exceptions;

/**
 * @author JIGS
 *
 */
public class InvalidPasswordException extends RuntimeException {
	public InvalidPasswordException(String message) {
		super(message);
	}
}
