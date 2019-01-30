package com.example.kotlin.configs


import com.example.schema.tweet.Tweet
import io.confluent.kafka.serializers.KafkaAvroSerializer
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

@Configuration
class AvroProducerConfig {

    @Bean
    fun avroProducerFactory(): ProducerFactory<String, Tweet> {
        return DefaultKafkaProducerFactory(
            mapOf(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to "localhost:9092,localhost:9093,localhost:9094",
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to KafkaAvroSerializer::class.java,
                ProducerConfig.ACKS_CONFIG to "all",
                ProducerConfig.RETRIES_CONFIG to "5",
                KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG to "http://127.0.0.1:8081"
            )
        )
    }

    @Bean
    fun avroKafkaTemplate(): KafkaTemplate<String, Tweet> {
        return KafkaTemplate(avroProducerFactory())
    }
}