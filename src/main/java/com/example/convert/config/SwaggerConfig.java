package com.example.convert.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Currency conversion API",
                description = "Convert currencies",
                version = "1.0",
                contact = @Contact(
                        name = "Marta",
                        email = "mq93162@gmail.com"))
)
public class SwaggerConfig {
}
