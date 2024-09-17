package com.riwi.artemisa.infrastructure.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Artemisa E-commerce API",
                version = "1.0.0",
                description = "Application for the management of veterinary, pharmacy and store to provide a complete service to all people who want to take special care of their pets.",
                contact = @Contact(
                        name = "Artemisa E-commerce"
                )
        ),
        servers = {
            @Server(
                    description = "DEV SERVER",
                    url = "http://localhost:8080"
            )
        }
)
public class SwaggerConfig {
}