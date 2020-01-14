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
 * The Class Branch.
 *
 * @author JIGS
 */
@Entity
@Table(name = "`branch`")
public class Branch extends AbstractEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8499012634547319421L;

	/** The name. */
	private String name;

	/** The organization id. */
	private Long organizationId;

	/**
	 * Instantiates a new branch.
	 */
	public Branch() {
		super(AppEntityCodes.BRANCH);
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
	 * Gets the organization id.
	 *
	 * @return the organization id
	 */
	@Column(name = "organization_id")
	public Long getOrganizationId() {
		return organizationId;
	}

	/**
	 * Sets the organization id.
	 *
	 * @param organizationId the new organization id
	 */
	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

}
