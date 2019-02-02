package com.example.kotlin.configs



import com.example.schema.tweet.Tweet
import io.confluent.kafka.serializers.KafkaAvroDeserializer
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.config.KafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer

@Configuration
class AvroConsumerConfig {

    @Bean
    fun consumerFactory(): ConsumerFactory<String, Tweet> {
        return DefaultKafkaConsumerFactory(
            mapOf(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to "localhost:9092,localhost:9093,localhost:9094",
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to KafkaAvroDeserializer::class.java,
                ConsumerConfig.AUTO_OFFSET_RESET_CONFIG to "earliest",
                ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG to "true",
                ConsumerConfig.GROUP_ID_CONFIG to "simple_avro_group_v2",
                KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG to "http://127.0.0.1:8081",
                KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG to "true"
            )
        )
    }

    @Bean
    fun kafkaListenerContainerFactory(): KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Tweet>> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, Tweet>()
        factory.consumerFactory = consumerFactory()
        return factory
    }
}