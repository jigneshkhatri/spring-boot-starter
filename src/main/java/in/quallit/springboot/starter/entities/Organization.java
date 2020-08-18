/**
 * 
 */
package in.quallit.springboot.starter.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import in.quallit.springboot.starter.entities.common.AbstractEntity;
import in.quallit.springboot.starter.utilities.constants.AppEntityCodes;

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

	/** The email. */
	private String email;

	/** The contact number. */
	private Long contactNumber;

	/** The address. */
	private String address;

	/** The branch id. */
	private Long branchId;

	/**
	 * Instantiates a new organization.
	 */
	public Organization() {
		super(AppEntityCodes.ORGANIZATION);
	}

	/**
	 * Instantiates a new organization.
	 *
	 * @param id the id
	 */
	public Organization(Long id) {
		super(id, AppEntityCodes.ORGANIZATION);
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
	 * Gets the email.
	 *
	 * @return the email
	 */
	@Transient
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
	 * Gets the contact number.
	 *
	 * @return the contact number
	 */
	@Transient
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
	 * Gets the address.
	 *
	 * @return the address
	 */
	@Transient
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
	 * Gets the branch id.
	 *
	 * @return the branch id
	 */
	@Transient
	public Long getBranchId() {
		return branchId;
	}

	/**
	 * Sets the branch id.
	 *
	 * @param branchId the new branch id
	 */
	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

}
