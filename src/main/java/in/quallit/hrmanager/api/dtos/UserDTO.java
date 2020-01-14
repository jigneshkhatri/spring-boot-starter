/**
 * 
 */
package in.quallit.hrmanager.api.dtos;

import in.quallit.hrmanager.api.dtos.common.AbstractDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class UserDTO.
 *
 * @author JIGS
 */
public class UserDTO extends AbstractDTO {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7390762214006245699L;

	/** The name. */
	private String name;

	/** The email. */
	private String email;

	/** The contact number. */
	private Long contactNumber;

	/** The password. */
	private String password;

	/** The access token. */
	private String accessToken;

	/** The refresh token. */
	private String refreshToken;

	/** The active branch id. */
	private String activeBranchId;

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
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the access token.
	 *
	 * @return the access token
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * Sets the access token.
	 *
	 * @param accessToken the new access token
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	/**
	 * Gets the refresh token.
	 *
	 * @return the refresh token
	 */
	public String getRefreshToken() {
		return refreshToken;
	}

	/**
	 * Sets the refresh token.
	 *
	 * @param refreshToken the new refresh token
	 */
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	/**
	 * Gets the active branch id.
	 *
	 * @return the active branch id
	 */
	public String getActiveBranchId() {
		return activeBranchId;
	}

	/**
	 * Sets the active branch id.
	 *
	 * @param activeBranchId the new active branch id
	 */
	public void setActiveBranchId(String activeBranchId) {
		this.activeBranchId = activeBranchId;
	}

}
