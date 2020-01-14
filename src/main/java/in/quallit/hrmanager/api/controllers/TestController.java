package in.quallit.hrmanager.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JIGS
 */
@RestController
public class TestController {

	@GetMapping("/test")
	public String unsecureTest() {
		return "UnSecure Test";
	}

	@GetMapping("/s/test")
	public String secureTest() {
		return "Tecure Test";
	}
}
