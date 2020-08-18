package in.quallit.springboot.starter.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import in.quallit.springboot.starter.entities.common.AbstractEntity;
import in.quallit.springboot.starter.utilities.constants.AppEntityCodes;

// TODO: Auto-generated Javadoc
/**
 * The Class UserRole.
 *
 * @author JIGS
 */
@Entity
@Table(name = "user_role")
public class UserRole extends AbstractEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 536063994854915264L;

	/** The user. */
	private User user;

	/** The role. */
	private Role role;

	/** The branch id. */
	private Long branchId;

	/**
	 * Instantiates a new user role.
	 */
	public UserRole() {
		super(AppEntityCodes.USER_ROLE);
	}

	/**
	 * Instantiates a new user role.
	 *
	 * @param id the id
	 */
	public UserRole(Long id) {
		super(id, AppEntityCodes.USER_ROLE);
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@Fetch(FetchMode.SELECT)
	public User getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Gets the role.
	 *
	 * @return the role
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id")
	@Fetch(FetchMode.SELECT)
	public Role getRole() {
		return role;
	}

	/**
	 * Sets the role.
	 *
	 * @param role the new role
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * Gets the branch id.
	 *
	 * @return the branch id
	 */
	@Column(name = "branch_id")
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
