package com.github.andrelugomes.springbootwebfluxkotlin

import com.github.andrelugomes.springbootwebfluxkotlin.model.kotlin.Tweet
import com.github.andrelugomes.springbootwebfluxkotlin.model.kotlin.User
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Test
import reactor.core.publisher.Mono

class MonoTest {

    lateinit var tweet: Tweet

    @Before
    fun setUp() {
        val user = User("user")
        tweet = Tweet(123, "123", "text", "date", user)
    }

    @Test
    fun `Mono block`() {

        val mono = Mono.just(tweet)

        val block = mono.block() //maybe NULL

        //val tweet = if (block != null) block else throw NullPointerException("Expression 'mono.block()' must not be null")
        val tweet = mono.block()!! // !! throws NPE

        Assertions.assertThat(tweet.id).isEqualTo(123)
        Assertions.assertThat(tweet.index).isEqualTo("123")
    }

    @Test
    fun `Mono subscribe`() {

        val mono = Mono.just(tweet)

        mono.subscribe {
            Assertions.assertThat(it.id).isEqualTo(123)
            Assertions.assertThat(it.index).isEqualTo("123")
        }
    }

    @Test
    fun `Mono Map the tweet`() {

        Mono.just(tweet)
            .map {
                it.copy(index = it.index!!.plus("4"), user = null)  //new Tweet
            }
            .subscribe {
                Assertions.assertThat(it.user).isNull()
                Assertions.assertThat(it.index).isEqualTo("1234")
            }
    }

    @Test
    fun `Mono flatMap to a new Mono with reversed index`() {

        Mono.just(tweet)
            .flatMap {
                Mono.just(it.copy(index = it.index!!.reversed()))  //new Mono with reversed index
            }
            .subscribe {
                Assertions.assertThat(it.id).isEqualTo(123)
                Assertions.assertThat(it.index).isEqualTo("321")
            }
    }

    @Test
    fun `Mono subscribe logging errors`() {

        Mono.just(tweet)
            .log()
            .subscribe(
                { Assertions.assertThat(it.id).isEqualTo(123) },
                { println("ERROR! Something breaks the stream") },
                { println("TEST COMPLETED") }
            )

    }

    @Test
    fun `Mono subscribe then unsubscribe from a stream using dispose()`() {

        Mono.just(tweet)
            .log()
            .subscribe {
                Assertions.assertThat(it.index).isEqualTo("123")
            }
            .dispose()
    }

    @Test
    fun `Mono subscribe disposable assertion`() {

        val disposable = Mono.just(tweet)
            .log()
            .subscribe {
                Assertions.assertThat(it.index).isEqualTo("123")
            }

        Assertions.assertThat(disposable.isDisposed).isTrue()
    }
}