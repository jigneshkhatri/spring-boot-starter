package in.quallit.springboot.starter.dtos.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import in.quallit.springboot.starter.utilities.DatabaseKeyUtility;
import in.quallit.springboot.starter.utilities.ObjectUtil;

/**
 * The Class IdEncrypter.
 *
 * @author JIGS
 */
public class IdEncrypter extends StdSerializer<Object> implements ContextualSerializer {

	private String entityName;

	public IdEncrypter() {
		super(Object.class);
	}

	public IdEncrypter(String en) {
		super(Object.class);

		this.entityName = en;
	}

	/**
	 * Serialize.
	 *
	 * @param value       the value
	 * @param gen         the gen
	 * @param serializers the serializers
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Override
	public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		if (value instanceof Long) {
			if (ObjectUtil.isNotEmpty(value) && ObjectUtil.isNotEmpty(this.entityName)) {
				gen.writeObject(DatabaseKeyUtility.encrypt((Long) value, this.entityName));
			} else {
				gen.writeObject(null);
			}
		} else if (value instanceof List) {
			if (ObjectUtil.isNotEmpty(value) && ObjectUtil.isNotEmpty(this.entityName)) {
				List<Long> values = (List<Long>) value;
				List<String> strValues = new ArrayList<>();
				values.forEach(single -> strValues.add(DatabaseKeyUtility.encrypt(single, this.entityName)));
				gen.writeObject(strValues);
			} else {
				gen.writeObject(null);
			}
		}
		// Similarly add more else if block if required
	}

	@Override
	public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property)
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

		return new IdEncrypter(en);
	}

}
