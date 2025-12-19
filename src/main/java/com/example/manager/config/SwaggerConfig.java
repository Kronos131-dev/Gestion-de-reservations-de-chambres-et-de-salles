package com.example.manager.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Configuration Swagger / OpenAPI
@Configuration
public class SwaggerConfig {

    // Configuration personnalisée de la documentation Swagger
    @Bean
    public OpenAPI customOpenAPI() {

        // Nom du schéma de sécurité JWT
        final String securitySchemeName = "bearerAuth";

        return new OpenAPI()
                // Informations générales de l'API
                .info(new Info()
                        .title("Rooms API")
                        .version("1.0")
                        .description("Documentation")
                )

                // Ajoute la sécurité JWT à toute l'API
                .addSecurityItem(
                        new SecurityRequirement().addList(securitySchemeName)
                )

                // Déclare le schéma de sécurité JWT
                .components(
                        new io.swagger.v3.oas.models.Components()
                                .addSecuritySchemes(
                                        securitySchemeName,
                                        new SecurityScheme()
                                                .name(securitySchemeName)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                                .description("Insère ton JWT ici : Bearer {token}")
                                )
                );
    }
}
