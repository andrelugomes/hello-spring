package com.example.kotlin

import com.example.schema.tweet.Tweet
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Service

@Service
class Consumer {

    companion object {
        private val LOG = LoggerFactory.getLogger(Consumer::class.java)
    }

    @KafkaListener(topics = arrayOf("topic.tweets.avro"))
    fun stringMessage(@Payload tweet: Tweet) {

        LOG.info("received message='{}'", tweet)
    }
}
