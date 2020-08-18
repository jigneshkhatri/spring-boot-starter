package in.quallit.springboot.starter.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import in.quallit.springboot.starter.entities.common.AbstractEntity;
import in.quallit.springboot.starter.utilities.ObjectUtil;
import in.quallit.springboot.starter.utilities.constants.AppEntityCodes;

// TODO: Auto-generated Javadoc
/**
 * The Class User.
 *
 * @author JIGS
 */

@Entity
@Table(name = "user")
public class User extends AbstractEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2528846278829531960L;

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

	/** The user roles. */
	private List<UserRole> userRoles;

	/**
	 * Instantiates a new user.
	 */
	public User() {
		super(AppEntityCodes.USER);
	}

	/**
	 * Instantiates a new user.
	 *
	 * @param id the id
	 */
	public User(Long id) {
		super(id, AppEntityCodes.USER);
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
	 * Gets the password.
	 *
	 * @return the password
	 */
	@Column(name = "password")
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
	@Transient
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
	@Transient
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
	@Column(name = "active_branch_id")
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

	/**
	 * Gets the user roles.
	 *
	 * @return the user roles
	 */
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	@Fetch(FetchMode.SELECT)
	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	/**
	 * Sets the user roles.
	 *
	 * @param userRoles the new user roles
	 */
	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	/**
	 * Gets the branch wise role.
	 *
	 * @param branchId the branch id
	 * @return the branch wise role
	 */
	@Transient
	public Role getBranchWiseRole(Long branchId) {
		if (ObjectUtil.isEmpty(this) || ObjectUtil.isEmpty(this.getUserRoles()) || ObjectUtil.isEmpty(branchId)) {
			return null;
		}
		return this.getUserRoles().stream().filter(single -> single.getBranchId().equals(branchId)).findAny()
				.map(singleUserRole -> singleUserRole.getRole()).orElse(null);
	}

}
