/**
 * 
 */
package in.quallit.springboot.starter.dtos;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import in.quallit.springboot.starter.dtos.common.AbstractDTO;
import in.quallit.springboot.starter.dtos.common.CustomJSONSerializer;
import in.quallit.springboot.starter.dtos.common.IdDecrypter;
import in.quallit.springboot.starter.dtos.common.IdEncrypter;
import in.quallit.springboot.starter.utilities.constants.AppEntityCodes;

// TODO: Auto-generated Javadoc
/**
 * The Class BranchDTO.
 *
 * @author JIGS
 */
public class BranchDTO extends AbstractDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -112944254734149901L;

	/** The id. */
	@JsonSerialize(using = IdEncrypter.class)
	@JsonDeserialize(using = IdDecrypter.class)
	@CustomJSONSerializer(entityName = AppEntityCodes.BRANCH)
	protected Long id;

	/** The name. */
	private String name;
	/** The organization id. */

	@JsonSerialize(using = IdEncrypter.class)
	@JsonDeserialize(using = IdDecrypter.class)
	@CustomJSONSerializer(entityName = AppEntityCodes.ORGANIZATION)
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
