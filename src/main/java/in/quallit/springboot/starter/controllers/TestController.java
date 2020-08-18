package in.quallit.springboot.starter.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import in.quallit.springboot.starter.exceptions.BusinessRuleValidationException;

/**
 * @author JIGS
 */
@RestController
public class TestController {

	@GetMapping("/test")
	public ResponseEntity<String> unsecureTest() {
		throw new BusinessRuleValidationException(BusinessRuleValidationException.Codes.QBRV_001);
//		System.out.println("TEST CONTROLLER HIT");
//		return ResponseEntity.ok().cacheControl(CacheControl.maxAge(60, TimeUnit.MINUTES))
//				.body("Hello World From TEST CONTROLLER!");
	}

	@GetMapping("/s/test")
	public String secureTest() {
		return "Tecure Test";
	}
}
