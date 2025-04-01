package br.com.iteam.infrastructure.config.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "ITeam::Product Management",
                version = "1.0",
                description = "This project was developed to meet the requirements of the IT-EAM " +
                              "test (www.it-eam.com). The goal is to create a RESTful API for product management," +
                              "allowing authenticated users to perform CRUD (Create, Read, Update, and Delete) " +
                              "operations. The API features robust validations and role-based access contro"
        )
)
public class SwaggerConfig {
}
