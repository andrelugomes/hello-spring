package com.example.kotlin

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.kafka.annotation.EnableKafka


@EnableKafka
@SpringBootApplication
class KafkaAvroConsumer: CommandLineRunner {
    override fun run(vararg args: String?) {
        println("running...")
        while (true){
            //
        }
    }

}

fun main(args: Array<String>) {
    SpringApplication(KafkaAvroConsumer::class.java).run(*args)
}


