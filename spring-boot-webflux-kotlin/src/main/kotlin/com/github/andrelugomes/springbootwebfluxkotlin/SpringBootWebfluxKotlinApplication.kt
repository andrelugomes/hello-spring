package com.github.andrelugomes.springbootwebfluxkotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@SpringBootApplication
@EnableReactiveMongoRepositories
class SpringBootWebfluxKotlinApplication

fun main(args: Array<String>) {
	runApplication<SpringBootWebfluxKotlinApplication>(*args)
}

