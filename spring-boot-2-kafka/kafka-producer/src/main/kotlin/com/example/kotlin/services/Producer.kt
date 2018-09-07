package com.example.kotlin.services
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class Producer(var kafkaTemplate: KafkaTemplate<String, String>) {
    
    val log = LoggerFactory.getLogger(Producer::class.java)

    fun publish(topic: String, message: String) {
        log.info("publishing message=$message on topic=$topic")

        kafkaTemplate.send(topic, message)
    }

    fun publish(topic: String, message: String, partition:Int, key: String) {
        log.info("publishing message=$message on topic=$topic")

        kafkaTemplate.send(topic, message)
    }

}


