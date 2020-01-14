/**
 * 
 */
package in.quallit.hrmanager.api.entities.common;

import javax.persistence.AttributeConverter;

import org.springframework.stereotype.Component;

import in.quallit.hrmanager.api.utilities.DatabaseKeyUtility;

// TODO: Auto-generated Javadoc
/**
 * The Class CryptoConverter.
 *
 * @author JIGS
 */
@Component
public class CryptoConverter implements AttributeConverter<String, Long> {

	/**
	 * Convert to entity attribute.
	 *
	 * @param dbData the db data
	 * @return the string
	 */
	@Override
	public String convertToEntityAttribute(Long dbData) {
		return DatabaseKeyUtility.encrypt(dbData);
	}

	/**
	 * Convert to database column.
	 *
	 * @param externalData the external data
	 * @return the long
	 */
	@Override
	public Long convertToDatabaseColumn(String externalData) {
		return DatabaseKeyUtility.decrypt(externalData);
	}

}
