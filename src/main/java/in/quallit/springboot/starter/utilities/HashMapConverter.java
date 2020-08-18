/**
 * 
 */
package in.quallit.springboot.starter.utilities;

import java.io.IOException;
import java.util.Map;

import javax.persistence.AttributeConverter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author JIGS
 *
 */
public class HashMapConverter implements AttributeConverter<Map<String, Object>, String> {

	private static final Logger LOGGER = LogManager.getLogger(HashMapConverter.class);

	@Override
	public String convertToDatabaseColumn(Map<String, Object> map) {

		String json = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			json = objectMapper.writeValueAsString(map);
		} catch (final JsonProcessingException e) {
			LOGGER.error("JSON writing error", e);
		}

		return json;
	}

	@Override
	public Map<String, Object> convertToEntityAttribute(String json) {

		Map<String, Object> map = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			map = objectMapper.readValue(json, Map.class);
		} catch (final IOException e) {
			LOGGER.error("JSON reading error", e);
		}

		return map;
	}

}
