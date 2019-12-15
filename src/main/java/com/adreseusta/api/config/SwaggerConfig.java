package com.adreseusta.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.adreseusta.api"))
                .build()
                .apiInfo(metaData())
                .enableUrlTemplating(true);
    }

    private ApiInfo metaData() {
        return new ApiInfo("AdreseUsta.com REST API", "AdreseUsta.com REST API", "1.0", "terms of service",
                new Contact("Erdinc Ozdemir", "http://erdincozdemir.com", "erdincozdemir@gmail.com"),
                "Apache License Version 2.0", "https://www.apache.org/licences/LICENSE-2.0",
                new ArrayList<>());
    }
}
