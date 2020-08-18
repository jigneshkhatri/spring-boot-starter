package in.quallit.springboot.starter.dtos.common;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import in.quallit.springboot.starter.utilities.DatabaseKeyUtility;
import in.quallit.springboot.starter.utilities.ObjectUtil;

/**
 * The Class IdDecrypter.
 *
 * @author JIGS
 */
public class IdDecrypter extends StdDeserializer<Long> implements ContextualDeserializer {

	private String entityName;

	public IdDecrypter() {
		super(Long.class);
	}

	public IdDecrypter(String en) {
		super(Long.class);

		this.entityName = en;
	}

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
		if (ObjectUtil.isNotEmpty(p) && ObjectUtil.isNotEmpty(this.entityName)) {
			return DatabaseKeyUtility.decrypt(val, this.entityName);
		}
		return null;
	}

	@Override
	public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property)
			throws JsonMappingException {
		String en = null;
		CustomJSONSerializer ann = null;

		if (property != null) {
			ann = property.getAnnotation(CustomJSONSerializer.class);
		}

		if (ann != null) {
			en = ann.entityName();
		}

		// if key== null??

		return new IdDecrypter(en);
	}

}
