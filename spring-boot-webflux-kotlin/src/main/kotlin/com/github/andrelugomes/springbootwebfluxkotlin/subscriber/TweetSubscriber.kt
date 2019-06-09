package com.github.andrelugomes.springbootwebfluxkotlin.subscriber

import com.github.andrelugomes.springbootwebfluxkotlin.model.kotlin.Tweet
import org.reactivestreams.Subscription
import reactor.core.publisher.BaseSubscriber


class TweetSubscriber : BaseSubscriber<Tweet>() {

    override fun hookOnNext(value: Tweet) {
        println("hookOnNext : $value")
    }

    override fun hookOnComplete() {
        println("Call hookOnComplete...")
    }

    override fun hookOnSubscribe(subscription: Subscription) {
        println("My Subscription : $subscription")
        super.hookOnSubscribe(subscription)
    }


}