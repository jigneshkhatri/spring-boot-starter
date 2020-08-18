package in.quallit.springboot.starter.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import in.quallit.springboot.starter.entities.common.AbstractEntity;
import in.quallit.springboot.starter.utilities.constants.AppEntityCodes;

// TODO: Auto-generated Javadoc
/**
 * The Class Role.
 *
 * @author JIGS
 */
@Entity
@Table(name = "role")
public class Role extends AbstractEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8592127159239409029L;

	/** The name. */
	private String name;

	/** The code. */
	private String code;

	/**
	 * Instantiates a new role.
	 */
	public Role() {
		super(AppEntityCodes.ROLE);
	}

	/**
	 * Instantiates a new role.
	 *
	 * @param id the id
	 */
	public Role(Long id) {
		super(id, AppEntityCodes.ROLE);
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
	 * Gets the code.
	 *
	 * @return the code
	 */
	@Column(name = "code")
	public String getCode() {
		return code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code the new code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * The Class Codes.
	 */
	public static class Codes {

		/**
		 * Instantiates a new codes.
		 */
		private Codes() {
		}

		/** The Constant ADMIN. */
		public static final String ADMIN = "ADMIN";
	}

}
