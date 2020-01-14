/**
 * 
 */
package in.quallit.hrmanager.api.dtos.common;

import java.io.Serializable;
import java.util.Date;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

// TODO: Auto-generated Javadoc
/**
 * The Class APIError.
 *
 * @author JIGS
 */
public class APIError implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6364436606285438796L;

	/** The message. */
	private String message;

	/** The timestamp. */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private Date timestamp;

	/** The status. */
	private HttpStatus status;

	/** The debug message. */
	private String debugMessage;

	/** The error code. */
	private String errorCode;

	/**
	 * Instantiates a new API error.
	 *
	 * @param apiErrorBuilder the api error builder
	 */
	private APIError(APIErrorBuilder apiErrorBuilder) {
		this.debugMessage = apiErrorBuilder.debugMessage;
		this.errorCode = apiErrorBuilder.errorCode;
		this.message = apiErrorBuilder.message;
		this.status = apiErrorBuilder.status;
		this.timestamp = apiErrorBuilder.timestamp;
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
	 * Gets the timestamp.
	 *
	 * @return the timestamp
	 */
	public Date getTimestamp() {
		return timestamp;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public HttpStatus getStatus() {
		return status;
	}

	/**
	 * Gets the debug message.
	 *
	 * @return the debug message
	 */
	public String getDebugMessage() {
		return debugMessage;
	}

	/**
	 * Gets the error code.
	 *
	 * @return the error code
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * The Class APIErrorBuilder.
	 */
	public static class APIErrorBuilder {

		/** The message. */
		private String message;

		/** The status. */
		private HttpStatus status;

		/** The debug message. */
		private String debugMessage;

		/** The error code. */
		private String errorCode;

		/** The timestamp. */
		private Date timestamp;

		/**
		 * Instantiates a new API error.
		 */
		public APIErrorBuilder() {
			this.timestamp = new Date();
		}

		/**
		 * Instantiates a new API error.
		 *
		 * @param status  the status
		 * @param message the message
		 */
		public APIErrorBuilder(HttpStatus status, String message) {
			this();
			this.status = status;
			this.message = message;
		}

		/**
		 * Message.
		 *
		 * @param message the message
		 * @return the API error builder
		 */
		public APIErrorBuilder message(String message) {
			this.message = message;
			return this;
		}

		/**
		 * Status.
		 *
		 * @param status the status
		 * @return the API error builder
		 */
		public APIErrorBuilder status(HttpStatus status) {
			this.status = status;
			return this;
		}

		/**
		 * Debug message.
		 *
		 * @param debugMessage the debug message
		 * @return the API error builder
		 */
		public APIErrorBuilder debugMessage(String debugMessage) {
			this.debugMessage = debugMessage;
			return this;
		}

		/**
		 * Error code.
		 *
		 * @param errorCode the error code
		 * @return the API error builder
		 */
		public APIErrorBuilder errorCode(String errorCode) {
			this.errorCode = errorCode;
			return this;
		}

		/**
		 * Builds the.
		 *
		 * @return the API error
		 */
		public APIError build() {
			return new APIError(this);
		}

	}

}
