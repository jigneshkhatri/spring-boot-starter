/**
 * 
 */
package in.quallit.springboot.starter.dtos;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import in.quallit.springboot.starter.dtos.common.CustomJSONSerializer;
import in.quallit.springboot.starter.dtos.common.IdDecrypter;
import in.quallit.springboot.starter.dtos.common.IdEncrypter;
import in.quallit.springboot.starter.utilities.constants.AppEntityCodes;

// TODO: Auto-generated Javadoc
/**
 * The Class OrganizationDTO.
 *
 * @author JIGS
 */
public class OrganizationDTO extends BranchDTO {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6156339337980544911L;

	/** The id. */
	@JsonSerialize(using = IdEncrypter.class)
	@JsonDeserialize(using = IdDecrypter.class)
	@CustomJSONSerializer(entityName = AppEntityCodes.ORGANIZATION)
	protected Long id;

	/** The name. */
	private String name;

	/** The email. */
	private String email;

	/** The contact number. */
	private Long contactNumber;

	/** The address. */
	private String address;

	/** The branch id. */
	@JsonSerialize(using = IdEncrypter.class)
	@JsonDeserialize(using = IdDecrypter.class)
	@CustomJSONSerializer(entityName = AppEntityCodes.BRANCH)
	private Long branchId;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	@Override
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	@Override
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the contact number.
	 *
	 * @return the contact number
	 */
	@Override
	public Long getContactNumber() {
		return contactNumber;
	}

	/**
	 * Sets the contact number.
	 *
	 * @param contactNumber the new contact number
	 */
	@Override
	public void setContactNumber(Long contactNumber) {
		this.contactNumber = contactNumber;
	}

	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	@Override
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the address.
	 *
	 * @param address the new address
	 */
	@Override
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Gets the branch id.
	 *
	 * @return the branch id
	 */
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
