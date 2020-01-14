/**
 * 
 */
package in.quallit.hrmanager.api.entities.common;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;

// TODO: Auto-generated Javadoc
/**
 * The Class PrimaryKey.
 *
 * @author JIGS
 */
public class PrimaryKey implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1588996494102014393L;
	/** The id. */
	private String id;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	@Column(name = "id")
	@Convert(converter = CryptoConverter.class)
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof PrimaryKey) {
			PrimaryKey pk = (PrimaryKey) obj;
			if (this.id == pk.getId()) {
				return true;
			}
		} else {
			return false;
		}
		return false;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return super.hashCode();
	}

}
