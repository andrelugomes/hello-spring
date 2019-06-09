package com.github.andrelugomes.springbootwebfluxkotlin.subscriber

import com.github.andrelugomes.springbootwebfluxkotlin.model.kotlin.Tweet
import org.springframework.util.Assert
import java.util.function.Consumer


class MyConsumer: Consumer<Tweet> {

    override fun accept(t: Tweet) {
        Assert.isInstanceOf(Tweet::class.java, t)
    }
}