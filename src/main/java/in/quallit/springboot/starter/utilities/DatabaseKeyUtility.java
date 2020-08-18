package in.quallit.springboot.starter.utilities;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import in.quallit.springboot.starter.utilities.constants.AppEntityCodes;

/**
 * The Class DatabaseKeyEncryptionUtility.
 *
 * @author JIGS
 */
public class DatabaseKeyUtility {

	/**
	 * Instantiates a new database key encryption utility.
	 */
	private DatabaseKeyUtility() {
	}

	/** The Constant ALGORITHM. */
	private static final String ALGORITHM = "AES/ECB/PKCS5Padding";

	/** The Constant KEY. */
	private static final byte[] KEY = "jibpEeplqWeLrlVe".getBytes();

	/** The Constant PREFIX_SUFFIX_LENGTH. */
	private static final int PREFIX_SUFFIX_LENGTH = 4;

	/**
	 * Encrypt.
	 *
	 * @param id the id
	 * @return the string
	 */
	public static String encrypt(Long id, String entityName) {
		if (ObjectUtil.isEmpty(id) || ObjectUtil.isEmpty(entityName)) {
			return null;
		}
		String dbDataStr = entityName + "__" + id;
		Key key = new SecretKeySpec(KEY, "AES");
		try {
			Cipher c = Cipher.getInstance(ALGORITHM);
			c.init(Cipher.ENCRYPT_MODE, key);
			// return
			// Base64.getUrlEncoder().encodeToString(c.doFinal(dbDataStr.getBytes()));
			return bytesToHex(c.doFinal(dbDataStr.getBytes()));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Decrypt.
	 *
	 * @param encryptedId the encrypted id
	 * @return the long
	 */
	public static Long decrypt(String encryptedId, String entityName) {
		if (ObjectUtil.isEmpty(encryptedId) || ObjectUtil.isEmpty(entityName)) {
			return null;
		}
		if (encryptedId.equals("-1")) {
			return Long.valueOf(encryptedId);
		}
		Key key = new SecretKeySpec(KEY, "AES");
		try {
			Cipher c = Cipher.getInstance(ALGORITHM);
			c.init(Cipher.DECRYPT_MODE, key);
			// String valueWithEntityName = new
			// String(c.doFinal(Base64.getUrlDecoder().decode(encryptedId)));
			String valueWithEntityName = new String(c.doFinal(hexToBytes(encryptedId)));
			String[] valArr = valueWithEntityName.split("__");
			if (valArr.length < 2) {
				throw new RuntimeException("Invalid Id");
			}
			if (!entityName.equalsIgnoreCase(valArr[0])) {
				throw new RuntimeException("Invalid entity name found in ID for entity - " + entityName);
			}
			return Long.valueOf(valArr[1]);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static String bytesToHex(byte[] hash) {
		return DatatypeConverter.printHexBinary(hash);
	}

	private static byte[] hexToBytes(String hex) {
		return DatatypeConverter.parseHexBinary(hex);
	}

	// for debugging purpose
	public static void main(String[] args) {
		System.out.println("Encrypted:: " + encrypt(1L, AppEntityCodes.ORGANIZATION));

		System.out.println("Encrypted:: " + encrypt(3L, AppEntityCodes.USER));
	}

}
