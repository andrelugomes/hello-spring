package com.example.kotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling


@SpringBootApplication
@EnableScheduling
class KafkaProducer

fun main(args: Array<String>) {
    runApplication<KafkaProducer>(*args)
}
