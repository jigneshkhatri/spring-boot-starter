/**
 * 
 */
package in.quallit.springboot.starter.entities.common;

import in.quallit.springboot.starter.dtos.common.APIError;

// TODO: Auto-generated Javadoc
/**
 * The Class ResponseBody.
 *
 * @author JIGS
 * @param <E> the element type
 */
public class ResponseBody<E extends Object> {

	/** The data. */
	private E data;

	/** The message. */
	private String message;

	/** The error. */
	private APIError error;

	/**
	 * Instantiates a new response body.
	 */
	public ResponseBody() {
	}

	/**
	 * Instantiates a new response body.
	 *
	 * @param data the data
	 */
	public ResponseBody(E data) {
		this.data = data;
	}

	/**
	 * Instantiates a new response body.
	 *
	 * @param error the error
	 */
	public ResponseBody(APIError error) {
		this.error = error;
	}

	/**
	 * Instantiates a new response body.
	 *
	 * @param data    the data
	 * @param message the message
	 */
	public ResponseBody(E data, String message) {
		this.data = data;
		this.message = message;
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public E getData() {
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(E data) {
		this.data = data;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Gets the error.
	 *
	 * @return the error
	 */
	public APIError getError() {
		return error;
	}

	/**
	 * Sets the error.
	 *
	 * @param error the new error
	 */
	public void setError(APIError error) {
		this.error = error;
	}

}
