/**
 * 
 */
package in.quallit.springboot.starter.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// TODO: Auto-generated Javadoc
/**
 * The Class SwaggerConfig.
 *
 * @author JIGS
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/**
	 * Api.
	 *
	 * @return the docket
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("in.quallit.springboot.starter.controllers"))
				.paths(PathSelectors.any()).build().apiInfo(apiInfo())
				.globalOperationParameters(Arrays.asList(
						new ParameterBuilder().name("BranchId").description("Id of Branch")
								.modelRef(new ModelRef("string")).parameterType("header").required(false).build(),
						new ParameterBuilder().name("OrganizationId").description("Id of Organization")
								.modelRef(new ModelRef("string")).parameterType("header").required(false).build(),
						new ParameterBuilder().name("Authorization").description("Access token")
								.modelRef(new ModelRef("string")).parameterType("header").required(false).build()));
	}

	private ApiInfo apiInfo() {
		return new ApiInfo("HRManager REST API", "REST API for HRManager clients", "1.0", "Terms of service",
				new Contact("Jignesh M. Khatri", "https://quallit.in", "jignesh@quallit.in"), "License of API",
				"API license URL", Collections.emptyList());
	}
}
