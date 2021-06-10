package com.github.andrelugomes.hello.spring.hateoas

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.filter.ForwardedHeaderFilter

@SpringBootApplication
class HelloSpringHateoasApplication

fun main(args: Array<String>) {
	runApplication<HelloSpringHateoasApplication>(*args)
}
