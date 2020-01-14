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
 * The Class Organization.
 *
 * @author JIGS
 */
@Entity
@Table(name = "`organization`")
public class Organization extends AbstractEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6237998135609323190L;
	/** The name. */
	private String name;

	/**
	 * Instantiates a new organization.
	 */
	public Organization() {
		super(AppEntityCodes.ORGANIZATION);
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

}
