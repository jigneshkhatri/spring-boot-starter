/**
 * 
 */
package in.quallit.springboot.starter.exceptions;

import java.util.HashMap;
import java.util.Map;

/**
 * @author JIGS
 *
 */
public class BusinessRuleValidationException extends QuallitException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7159728091351631414L;

	public BusinessRuleValidationException(String code) {
		super(code, MESSAGES.get(code));
	}

	public static class Codes {
		private Codes() {
		}

		// QBRV - Quallit Business Rule Validation

		public static final String QBRV_001 = "QBRV_001";
		public static final String QBRV_002 = "QBRV_002";
		public static final String QBRV_003 = "QBRV_003";
		public static final String QBRV_004 = "QBRV_004";
		public static final String QBRV_005 = "QBRV_005";
		public static final String QBRV_006 = "QBRV_006";
		public static final String QBRV_007 = "QBRV_007";
		public static final String QBRV_008 = "QBRV_008";
	}

	private static final Map<String, String> MESSAGES = new HashMap<>();

	static {
		MESSAGES.put(Codes.QBRV_001, "Account with entered Email already exists");
		MESSAGES.put(Codes.QBRV_002, "Account with entered Contact Number already exists");
		MESSAGES.put(Codes.QBRV_003, "Missing branch information in headers");
		MESSAGES.put(Codes.QBRV_004, "Not authenticated");
		MESSAGES.put(Codes.QBRV_005, "Not authorized");
		MESSAGES.put(Codes.QBRV_006, "Account is not active");
		MESSAGES.put(Codes.QBRV_007, "Invalid Username/Password");
		MESSAGES.put(Codes.QBRV_008, "No account exists for entered Username");
	}
}
