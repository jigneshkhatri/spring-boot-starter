/**
 * 
 */
package in.quallit.springboot.starter.controllers.common;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import in.quallit.springboot.starter.dtos.common.APIError;
import in.quallit.springboot.starter.entities.common.ResponseBody;
import in.quallit.springboot.starter.exceptions.QuallitException;

/**
 * The Class RestExceptionHandler.
 *
 * @author JIGS
 */
//@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Handle http message not readable.
	 *
	 * @param ex      the ex
	 * @param headers the headers
	 * @param status  the status
	 * @param request the request
	 * @return the response entity
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String errorMessage = "Malformed JSON request";
		ex.printStackTrace();
		ex.getMessage();
		return ResponseEntity.badRequest()
				.body(new ResponseBody<>(new APIError.APIErrorBuilder(HttpStatus.BAD_REQUEST, errorMessage)
						.debugMessage(ex.getMessage()).build()));
	}

	/**
	 * Handle SQL integrity constraint violation exception.
	 *
	 * @param ex the ex
	 * @return the response entity
	 */
	@ExceptionHandler(DataIntegrityViolationException.class)
	protected ResponseEntity<ResponseBody<APIError>> handleSQLIntegrityConstraintViolationException(
			DataIntegrityViolationException ex) {
		ex.printStackTrace();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseBody<>(
				new APIError.APIErrorBuilder(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMostSpecificCause().getMessage())
						.debugMessage(ex.getMessage()).build()));
	}

	@ExceptionHandler(QuallitException.class)
	protected ResponseEntity<ResponseBody<APIError>> handleQuallitExceptions(QuallitException ex) {
		ex.printStackTrace();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ResponseBody<>(new APIError.APIErrorBuilder(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage())
						.errorCode(ex.getCode()).build()));
	}

	/**
	 * Handle runtime exception.
	 *
	 * @param ex the ex
	 * @return the response entity
	 */
	@ExceptionHandler(RuntimeException.class)
	protected ResponseEntity<ResponseBody<APIError>> handleRuntimeException(RuntimeException ex) {
		ex.printStackTrace();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ResponseBody<>(
						new APIError.APIErrorBuilder(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage())
								.debugMessage(ex.getMessage()).build()));
	}

}
