/**
 * 
 */
package in.quallit.springboot.starter.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import in.quallit.springboot.starter.entities.common.AbstractEntity;
import in.quallit.springboot.starter.utilities.constants.AppEntityCodes;

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

	/** The contact number. */
	private Long contactNumber;

	/** The email. */
	private String email;

	/** The address. */
	private String address;

	/** The is default. */
	private Boolean isDefault;

	/**
	 * Instantiates a new branch.
	 */
	public Branch() {
		super(AppEntityCodes.BRANCH);
	}

	/**
	 * Instantiates a new branch.
	 *
	 * @param id the id
	 */
	public Branch(Long id) {
		super(id, AppEntityCodes.BRANCH);
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

	/**
	 * Gets the contact number.
	 *
	 * @return the contact number
	 */
	@Column(name = "contact_number")
	public Long getContactNumber() {
		return contactNumber;
	}

	/**
	 * Sets the contact number.
	 *
	 * @param contactNumber the new contact number
	 */
	public void setContactNumber(Long contactNumber) {
		this.contactNumber = contactNumber;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	@Column(name = "address")
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the address.
	 *
	 * @param address the new address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Gets the checks if is default.
	 *
	 * @return the checks if is default
	 */
	@Column(name = "is_default")
	public Boolean getIsDefault() {
		return isDefault;
	}

	/**
	 * Sets the checks if is default.
	 *
	 * @param isDefault the new checks if is default
	 */
	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}

}
