package com.felipemotagodoy.Projeto_Web_Services_Com_Spring_Boot_e_JPA_Hibernate.config;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.builders.RequestHandlerSelectors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.felipemotagodoy.Projeto_Web_Services_Com_Spring_Boot_e_JPA_Hibernate.resources"))
            .build();
    }
}

