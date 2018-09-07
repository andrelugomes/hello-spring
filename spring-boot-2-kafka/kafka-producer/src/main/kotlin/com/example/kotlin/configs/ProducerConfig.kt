package com.example.kotlin.configs

import org.apache.kafka.clients.producer.ProducerConfig.*
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

@Configuration
class ProducerConfig

/**
 * acks=0 -> Fire and Forgot, without confirmation
 * acks=1 -> Leader needs to confirm
 * acks=all -> Leader and all replicas needs to confirm
 */

@Bean
fun producerFactory(): ProducerFactory<String, Any> {
    return DefaultKafkaProducerFactory(mapOf(
        BOOTSTRAP_SERVERS_CONFIG to "localhost:9092,localhost:9093,localhost:9094",
        KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
        VALUE_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
        ACKS_CONFIG to "all"))
}

@Bean
fun kafkaTemplate(): KafkaTemplate<String, Any> {
    return KafkaTemplate(producerFactory())
}


