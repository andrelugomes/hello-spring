package com.example.kotlin.schedule

import com.example.kotlin.services.TwitterResultsProducer
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.social.twitter.api.Twitter
import org.springframework.stereotype.Component


@Component
class TwitterPoll(@Autowired var twitter: Twitter,
                  @Autowired var twitterResultsProducer: TwitterResultsProducer) {

    val log = LoggerFactory.getLogger(TwitterPoll::class.java)

    @Scheduled(fixedRate = 3000)
    fun poll(){
        log.info("Polling")

        val results = twitter.searchOperations().search("spring", 10)

        results.tweets.forEach {
            log.info("Tweet : $it")
            twitterResultsProducer.publish(it)
        }
    }

}
