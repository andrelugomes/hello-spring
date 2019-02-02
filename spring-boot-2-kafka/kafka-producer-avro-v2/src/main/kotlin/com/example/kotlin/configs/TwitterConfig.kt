package com.example.kotlin.configs

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.social.twitter.api.Twitter
import org.springframework.social.twitter.api.impl.TwitterTemplate


@Configuration
class TwitterConfig {

    @Bean
    @ConditionalOnMissingBean
    fun twitter(): Twitter {
        val consumerKey = "W55uJ19XR9Qfiep3h760vQ84H"
        val consumerSecret = "NxiSaNQ28MJ8kJaigrd0iRwHEc0deyXwityBDHM9yMHMRnlO5F"
        val accessToken = "184978491-vWLxGbL84XBgocNqGeTdHjdrCZ3TFSEudvFJesMr"
        val accessTokenSecret = "6i0QTjOj4SswJEiFw2J1gT03UerAJ6BKikLJUfuTqmN5v"

        return TwitterTemplate(consumerKey, consumerSecret, accessToken, accessTokenSecret)
    }
}