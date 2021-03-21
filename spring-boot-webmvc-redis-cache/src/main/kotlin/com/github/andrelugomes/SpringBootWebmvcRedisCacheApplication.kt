package com.github.andrelugomes

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
class SpringBootWebmvcRedisCacheApplication

fun main(args: Array<String>) {
	runApplication<SpringBootWebmvcRedisCacheApplication>(*args)
}
