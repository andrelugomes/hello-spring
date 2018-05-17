package com.example.springboot2rest.config;

import static java.util.Collections.emptyList;

import com.example.springboot2rest.resource.API;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.google.common.base.Predicate;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket apiV1() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("api-1.0")
                .select()
                .apis(getRequestHandlerPredicate(API.V1))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfo(
                        "Brazil REST API",
                        "All cities from Brazil.",
                        "1.0",
                        "Use without moderation",
                        contact(),
                        "License of API", "API license URL", emptyList()));
    }

    @Bean
    public Docket apiV2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("api-2.0")
                .select()
                .apis(getRequestHandlerPredicate(API.V2))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfo(
                        "Brazil REST API",
                        "All cities from Brazil.",
                        "2.0",
                        "Use without moderation",
                        contact(),
                        "License of API", "API license URL", emptyList()));
    }


    @Bean
    public Docket apiV1Url() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("api-1.0-url")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.springboot2rest.resource"))
                .paths(PathSelectors.regex("/cities/v1.*"))
                .build()
                .apiInfo(new ApiInfo(
                        "Brazil REST API",
                        "All cities from Brazil.",
                        "1.0-url",
                        "Use without moderation",
                        contact(),
                        "License of API", "API license URL", emptyList()));
    }

    @Bean
    public Docket apiV2Url() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("api-2.0-url")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.springboot2rest.resource"))
                .paths(PathSelectors.regex("/cities/v2.*"))
                .build()
                .apiInfo(new ApiInfo(
                        "Brazil REST API",
                        "All cities from Brazil.",
                        "2.0-url",
                        "Use without moderation",
                        contact(),
                        "License of API", "API license URL", emptyList()));
    }

    private Contact contact () {
        return new Contact("André Luis Gomes", "https://about.me/andreluisgomes", "andrelugomes@gmail.com");
    }

    private Predicate<RequestHandler> getRequestHandlerPredicate(String version) {
        return p -> {
            if (p.produces() != null) {
                for (MediaType mt : p.produces()) {
                    if (mt.toString().equals(version)) {
                        return true;
                    }
                }
            }
            return false;
        };
    }

}