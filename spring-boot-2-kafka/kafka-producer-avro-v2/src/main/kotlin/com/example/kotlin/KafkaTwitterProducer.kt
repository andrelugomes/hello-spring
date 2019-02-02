package com.example.kotlin

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling


@SpringBootApplication
@EnableScheduling
class KafkaTwitterProducer

fun main(args: Array<String>) {
    runApplication<KafkaTwitterProducer>(*args) {
        setBannerMode(Banner.Mode.OFF)
    }
}
