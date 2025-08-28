package com.app.prices.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("E-commerce Pricing API")
                        .version("1.0")
                        .description("Service for managing product prices on an e-commerce platform.")
                        .contact(new io.swagger.v3.oas.models.info.Contact()
                                .name("Sergio Liébanas")
                                .email("sergiolsz82@gmail.com")
                                .url("https://github.com/Sergiolsz"))
                        .license(new io.swagger.v3.oas.models.info.License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")));
    }
}