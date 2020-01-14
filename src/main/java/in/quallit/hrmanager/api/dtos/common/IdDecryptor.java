/**
 * 
 */
package in.quallit.hrmanager.api.dtos.common;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import in.quallit.hrmanager.api.utilities.DatabaseKeyUtility;
import in.quallit.hrmanager.api.utilities.ObjectUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class IdDecryptor.
 *
 * @author JIGS
 */
public class IdDecryptor extends JsonDeserializer<Long> {

	/**
	 * Deserialize.
	 *
	 * @param p    the p
	 * @param ctxt the ctxt
	 * @return the long
	 * @throws IOException             Signals that an I/O exception has occurred.
	 * @throws JsonProcessingException the json processing exception
	 */
	@Override
	public Long deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
		String val = p.getValueAsString();
		if (ObjectUtil.isNotEmpty(p)) {
			return DatabaseKeyUtility.decrypt(val);
		}
		return null;
	}

}
