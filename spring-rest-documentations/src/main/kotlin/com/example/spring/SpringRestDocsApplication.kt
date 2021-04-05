package com.example.spring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ResponseStatus

@SpringBootApplication
class SpringRestDocsApplication

fun main(args: Array<String>) {
	runApplication<SpringRestDocsApplication>(*args)
}