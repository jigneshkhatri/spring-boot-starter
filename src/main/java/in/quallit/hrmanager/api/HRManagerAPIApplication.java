package in.quallit.hrmanager.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class HRManagerAPIApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(HRManagerAPIApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(HRManagerAPIApplication.class);
	}
}
