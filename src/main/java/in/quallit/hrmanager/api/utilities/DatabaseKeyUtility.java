/**
 * 
 */
package in.quallit.hrmanager.api.utilities;

import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.RandomStringUtils;

import in.quallit.hrmanager.api.entities.common.PrimaryKey;

// TODO: Auto-generated Javadoc
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
	private static final byte[] KEY = "AitpeBslqWeprlCe".getBytes();

	/** The Constant PREFIX_SUFFIX_LENGTH. */
	private static final int PREFIX_SUFFIX_LENGTH = 4;

	/**
	 * Encrypt.
	 *
	 * @param id the id
	 * @return the string
	 */
	public static String encrypt(Long id) {
		if (ObjectUtil.isEmpty(id)) {
			return null;
		}
		String prefix = RandomStringUtils.random(PREFIX_SUFFIX_LENGTH, true, true);
		String suffix = RandomStringUtils.random(PREFIX_SUFFIX_LENGTH, true, true);
		String dbDataStr = prefix + id + suffix;
		Key key = new SecretKeySpec(KEY, "AES");
		try {
			Cipher c = Cipher.getInstance(ALGORITHM);
			c.init(Cipher.ENCRYPT_MODE, key);
			return Base64.getUrlEncoder().encodeToString(c.doFinal(dbDataStr.getBytes()));
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
	public static Long decrypt(String encryptedId) {
		if (ObjectUtil.isEmpty(encryptedId)) {
			return null;
		}
		if (encryptedId.equals("-1")) {
			return Long.valueOf(encryptedId);
		}
		Key key = new SecretKeySpec(KEY, "AES");
		try {
			Cipher c = Cipher.getInstance(ALGORITHM);
			c.init(Cipher.DECRYPT_MODE, key);
			String valueWithJunk = new String(c.doFinal(Base64.getUrlDecoder().decode(encryptedId)));
			String intermediateValueWithJunk = valueWithJunk.substring(PREFIX_SUFFIX_LENGTH);
			String value = intermediateValueWithJunk.substring(0,
					intermediateValueWithJunk.length() - PREFIX_SUFFIX_LENGTH);
			return Long.valueOf(value);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Gets the primary key.
	 *
	 * @param id the id
	 * @return the primary key
	 */
	public static PrimaryKey getPrimaryKey(String id) {
		PrimaryKey pk = new PrimaryKey();
		pk.setId(id);
		return pk;
	}
}
