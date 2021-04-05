package com.example.spring.swagger

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.service.ApiInfo
import springfox.documentation.swagger2.annotations.EnableSwagger2


@Configuration
//@EnableSwagger2 //springfox-boot-starter:3.0.0
class SwaggerConfig {

    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.example.spring.swagger"))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(metaData());
    }

    private fun metaData(): ApiInfo {
        return ApiInfoBuilder()
            .title("Clients REST API")
            .description("\"REST API for greeting clients\"")
            .version("1.0.0")
            .license("Apache License Version 2.0")
            .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
            .build()
    }
}