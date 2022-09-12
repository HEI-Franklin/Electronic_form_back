package com.electronicform.hei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "EF API", version = "1.0", description = "Electronic Form API description"))
@SecurityScheme(name = "api", scheme = "bearer", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class electronicformApplication {

	public static void main(String[] args) {
		SpringApplication.run(electronicformApplication.class, args);
	}

}
