package in.quallit.springboot.starter.entities.common;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import in.quallit.springboot.starter.enums.StatusEnum;

// TODO: Auto-generated Javadoc
/**
 * The Class AbstractEntity.
 */
@MappedSuperclass
//@IdClass(PrimaryKey.class)
public abstract class AbstractEntity implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1191422925622832672L;

	/** The id. */
	private Long id;

	/** The created on. */
	private Date createdOn;

	/** The created by. */
	private Long createdBy;

	/** The updated on. */
	private Date updatedOn;

	/** The updated by. */
	private Long updatedBy;

	/** The status. */
	private StatusEnum status;

	/** The entity code. */
	private String entityCode;

	/**
	 * Instantiates a new abstract entity.
	 *
	 * @param entityCode the entity code
	 */
	protected AbstractEntity(String entityCode) {
		this.entityCode = entityCode;
	}

	/**
	 * Instantiates a new abstract entity.
	 *
	 * @param id the id
	 */
	protected AbstractEntity(Long id, String entityCode) {
		this.id = id;
		this.entityCode = entityCode;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the created on.
	 *
	 * @return the created on
	 */
	@Column(name = "created_on", insertable = false)
	public Date getCreatedOn() {
		return createdOn;
	}

	/**
	 * Sets the created on.
	 *
	 * @param createdOn the new created on
	 */
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * Gets the created by.
	 *
	 * @return the created by
	 */
	@Column(name = "created_by")
	public Long getCreatedBy() {
		return createdBy;
	}

	/**
	 * Sets the created by.
	 *
	 * @param createdBy the new created by
	 */
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * Gets the updated on.
	 *
	 * @return the updated on
	 */
	@Column(name = "updated_on", insertable = false)
	public Date getUpdatedOn() {
		return updatedOn;
	}

	/**
	 * Sets the updated on.
	 *
	 * @param updatedOn the new updated on
	 */
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	/**
	 * Gets the updated by.
	 *
	 * @return the updated by
	 */
	@Column(name = "updated_by")
	public Long getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * Sets the updated by.
	 *
	 * @param updatedBy the new updated by
	 */
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	@Column(name = "status")
	public StatusEnum getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	/**
	 * Gets the entity code.
	 *
	 * @return the entity code
	 */
	@Transient
	public String getEntityCode() {
		return entityCode;
	}

}
