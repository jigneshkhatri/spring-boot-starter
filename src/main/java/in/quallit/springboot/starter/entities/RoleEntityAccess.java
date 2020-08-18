/**
 * 
 */
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
 * The Class RoleEntityAccess.
 *
 * @author JIGS
 */
@Entity
@Table(name = "`role_entity_access`")
public class RoleEntityAccess extends AbstractEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 9203875296056747664L;

	/** The role. */
	private Role role;

	/** The app entity. */
	private AppEntity appEntity;

	/** The permitted operations. */
	private String permittedOperations;

	/** The has all data access. */
	private Boolean hasAllDataAccess;

	/**
	 * Instantiates a new role entity access.
	 */
	public RoleEntityAccess() {
		super(AppEntityCodes.ROLE_ENTITY_ACCESS);
	}

	/**
	 * Instantiates a new role entity access.
	 *
	 * @param id the id
	 */
	public RoleEntityAccess(Long id) {
		super(id, AppEntityCodes.ROLE_ENTITY_ACCESS);
	}

	/**
	 * Gets the role.
	 *
	 * @return the role
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.SELECT)
	@JoinColumn(name = "role_id")
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
	 * Gets the app entity.
	 *
	 * @return the app entity
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.SELECT)
	@JoinColumn(name = "entity_id")
	public AppEntity getAppEntity() {
		return appEntity;
	}

	/**
	 * Sets the app entity.
	 *
	 * @param appEntity the new app entity
	 */
	public void setAppEntity(AppEntity appEntity) {
		this.appEntity = appEntity;
	}

	/**
	 * Gets the permitted operations.
	 *
	 * @return the permitted operations
	 */
	@Column(name = "permitted_operations")
	public String getPermittedOperations() {
		return permittedOperations;
	}

	/**
	 * Sets the permitted operations.
	 *
	 * @param permittedOperations the new permitted operations
	 */
	public void setPermittedOperations(String permittedOperations) {
		this.permittedOperations = permittedOperations;
	}

	/**
	 * Gets the checks for all data access.
	 *
	 * @return the checks for all data access
	 */
	@Column(name = "has_all_data_access")
	public Boolean getHasAllDataAccess() {
		return hasAllDataAccess;
	}

	/**
	 * Sets the checks for all data access.
	 *
	 * @param hasAllDataAccess the new checks for all data access
	 */
	public void setHasAllDataAccess(Boolean hasAllDataAccess) {
		this.hasAllDataAccess = hasAllDataAccess;
	}

}
