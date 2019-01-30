package com.example.kotlin.services
import com.example.kotlin.configs.Topics
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.social.twitter.api.Tweet
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.util.*

@Service
class TwitterResultsProducer(var avroKafkaTemplate: KafkaTemplate<String, com.example.schema.tweet.Tweet>) {

    val log = LoggerFactory.getLogger(TwitterResultsProducer::class.java)

    fun publish(result: Tweet) {
        log.info("publishing message=$result")
        val tweet = com.example.schema.tweet.Tweet.newBuilder()
        tweet.id = result.id
        tweet.createdAt = result.createdAt.format()
        tweet.idStr = result.idStr
        tweet.text = result.text

        avroKafkaTemplate.send(Topics.TWEETS_AVRO, tweet.build())
    }

}

inline fun Date.format() : String? {
    val format = SimpleDateFormat("dd/MM/yyy")
    return format.format(this)
}


