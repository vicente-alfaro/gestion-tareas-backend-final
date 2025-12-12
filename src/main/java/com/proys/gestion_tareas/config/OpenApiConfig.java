package com.proys.gestion_tareas.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI gestionTareasOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Gestión de Tareas y Proyectos")
                        .description("Documentación de la API para el proyecto final")
                        .version("1.0.0"));
    }
}
