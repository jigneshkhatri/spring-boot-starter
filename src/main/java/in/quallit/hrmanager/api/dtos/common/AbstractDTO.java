/**
 * 
 */
package in.quallit.hrmanager.api.dtos.common;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import in.quallit.hrmanager.api.enums.StatusEnum;

// TODO: Auto-generated Javadoc
/**
 * The Class AbstractDTO.
 *
 * @author JIGS
 */
public abstract class AbstractDTO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2856049089851268954L;

	/** The id. */
	@JsonSerialize(using = IdEncryptor.class)
	@JsonDeserialize(using = IdDecryptor.class)
	private Long id;

	/** The created on. */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private Date createdOn;

	/** The created by. */
	@JsonSerialize(using = IdEncryptor.class)
	@JsonDeserialize(using = IdDecryptor.class)
	private Long createdBy;

	/** The updated on. */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private Date updatedOn;

	/** The updated by. */
	@JsonSerialize(using = IdEncryptor.class)
	@JsonDeserialize(using = IdDecryptor.class)
	private Long updatedBy;

	/** The status. */
	private StatusEnum status;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
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

}
