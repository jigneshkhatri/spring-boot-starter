/**
 * 
 */
package in.quallit.hrmanager.api.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import in.quallit.hrmanager.api.entities.common.AbstractEntity;
import in.quallit.hrmanager.api.utilities.constants.AppEntityCodes;

// TODO: Auto-generated Javadoc
/**
 * The Class AppEntity.
 *
 * @author JIGS
 */
@Entity
@Table(name = "`app_entity`")
public class AppEntity extends AbstractEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6560085768690882391L;

	/** The name. */
	private String name;

	/** The code. */
	private String code;

	/**
	 * Instantiates a new app entity.
	 */
	public AppEntity() {
		super(AppEntityCodes.APP_ENTITY);
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	@Column(name = "name")
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	@Column(name = "code")
	public String getCode() {
		return code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code the new code
	 */
	public void setCode(String code) {
		this.code = code;
	}

}
