package com.github.andrelugomes.springbootwebfluxkotlin.subscriber

import com.github.andrelugomes.springbootwebfluxkotlin.model.kotlin.Tweet
import org.springframework.util.Assert


class MySubscriber {

    fun subscribe(): (Tweet) -> Unit {
        return {
            Assert.isInstanceOf(Tweet::class.java, it)
        }
    }
}