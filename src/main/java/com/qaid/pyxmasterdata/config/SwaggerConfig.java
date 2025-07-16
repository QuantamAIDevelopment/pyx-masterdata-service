package com.qaid.pyxmasterdata.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "PYX Marketplace Masterdata API",
                description = "API documentation for the PYX Marketplace Masterdata service.",
                version = "1.0.0",
                contact = @Contact(
                        name = "QAID Support",
                        email = "support@qaid.com",
                        url = "https://qaid.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0"
                )
        ),
        servers = {
                @Server(url = "http://localhost:8080", description = "Local Development Server"),
                @Server(url = "https://pxynetwork.com", description = "Production Server"),
                @Server(url = "pyx-masterdata-serivce-gedha7bja3awbbey.canadacentral-01.azurewebsites.net", description = "Azure Dev Web App")
        }
)
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public-api")
                .pathsToMatch("/**")
                .packagesToScan("com.qaid.pyxmasterdata.controller")
                .build();
    }
}

