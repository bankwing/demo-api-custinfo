package th.co.poc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2

public class swagger {
	@Bean
	public Docket productApi(){
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(regex("/customers.*"))
				.build()
				.apiInfo(metaData());
	}

	

	private ApiInfo metaData() {
		ApiInfo apiInfo = new ApiInfo(
				"Customer API",
				"Customer API for Demo",
				"1.0",
				"Terms of service",
				new Contact("IBM Solutions Delivery","http://www.ibm.com","cisdawen@th.ibm.com"),
				"Demo version 1.0", null);
		return apiInfo;
	}

}
