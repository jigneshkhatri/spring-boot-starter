/**
 * 
 */
package in.quallit.hrmanager.api.dtos.common;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import in.quallit.hrmanager.api.utilities.DatabaseKeyUtility;
import in.quallit.hrmanager.api.utilities.ObjectUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class IdEncryptor.
 *
 * @author JIGS
 */
public class IdEncryptor extends JsonSerializer<Long> {

	/**
	 * Serialize.
	 *
	 * @param value       the value
	 * @param gen         the gen
	 * @param serializers the serializers
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Override
	public void serialize(Long value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		if (ObjectUtil.isNotEmpty(value)) {
			gen.writeObject(DatabaseKeyUtility.encrypt(value));
		} else {
			gen.writeObject(null);
		}
	}

}
