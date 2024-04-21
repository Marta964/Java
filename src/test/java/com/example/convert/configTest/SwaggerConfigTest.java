package com.example.convert.configTest;

import com.example.convert.config.SwaggerConfig;
import org.junit.jupiter.api.Test;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SwaggerConfigTest {
    @Test
    void testSwaggerConfig() {
        // Arrange
        SwaggerConfig swaggerConfig = new SwaggerConfig();

        // Act
        OpenAPIDefinition openAPIDefinition = swaggerConfig.getClass().getAnnotation(OpenAPIDefinition.class);
        Info info = openAPIDefinition.info();

        // Assert
        assertNotNull(openAPIDefinition);
        assertNotNull(info);
        assertEquals("Currency conversion API", info.title());
        assertEquals("Convert currencies", info.description());
        assertEquals("1.0", info.version());
        Contact contact = info.contact();
        assertNotNull(contact);
        assertEquals("Marta", contact.name());
        assertEquals("mq93162@gmail.com", contact.email());
    }
}
