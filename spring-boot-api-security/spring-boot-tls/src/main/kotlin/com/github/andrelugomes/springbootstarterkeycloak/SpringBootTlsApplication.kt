package com.github.andrelugomes.springbootstarterkeycloak

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootTlsApplication

fun main(args: Array<String>) {
	runApplication<SpringBootTlsApplication>(*args)
}
