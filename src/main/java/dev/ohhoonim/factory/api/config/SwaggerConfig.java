package dev.ohhoonim.factory.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;

@OpenAPIDefinition(
        info = @Info(title = "The Factory API 명세서",
                description = "The Factory API 명세서",
                version = "v1"))
@Configuration
public class SwaggerConfig {
    @Bean
 public OpenAPI customOpenAPI() {
   return new OpenAPI()
          .components(new Components()
          .addSecuritySchemes("bearer-key",
          new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("Bearer").bearerFormat("jwt")));
}
}
