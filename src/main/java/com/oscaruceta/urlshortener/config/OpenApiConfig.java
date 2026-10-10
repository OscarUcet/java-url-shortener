
package com.oscaruceta.urlshortener.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "URL Shortener API",
                version = "1.0.0",
                description = "REST API for creating, retrieving, and redirecting shortened URLs."
        )
)
public class OpenApiConfig {
}
