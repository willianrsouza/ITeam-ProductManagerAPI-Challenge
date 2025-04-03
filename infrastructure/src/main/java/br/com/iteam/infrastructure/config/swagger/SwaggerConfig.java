package br.com.iteam.infrastructure.config.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "ITeam::Product Management API",
                version = "1.0",
                description = "This project was developed by Willian Souza to meet the requirements of the IT-EAM test (www.it-eam.com). The goal is to create a RESTful API for product management, allowing authenticated users to perform CRUD operations (Create, Read, Update, and Delete). The API includes robust validations and role-based access control.\n" +
                        "\n" +
                        "\uD83D\uDCE7 Email: willianrsouzawork@gmail.com\n" +
                        "\uD83D\uDC19 GitHub: github.com/willianrsouza\n" +
                        "\uD83D\uDD17 LinkedIn: www.linkedin.com/in/willianrsouza/"
        )
)
@SecurityScheme(
        name = "basicAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "basic"
)
public class SwaggerConfig {
        @Bean
        public OpenAPI customOpenAPI() {
                return new OpenAPI()
                        .addSecurityItem(new SecurityRequirement().addList("basicAuth"))
                        .components(new Components()
                                .addSecuritySchemes("basicAuth",
                                        new io.swagger.v3.oas.models.security.SecurityScheme()
                                                .type(io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP)
                                                .scheme("basic")));
        }
}
