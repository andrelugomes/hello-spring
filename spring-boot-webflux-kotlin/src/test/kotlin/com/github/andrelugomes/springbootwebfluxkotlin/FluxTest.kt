package com.github.andrelugomes.springbootwebfluxkotlin

import com.github.andrelugomes.springbootwebfluxkotlin.model.kotlin.SortedTweet
import com.github.andrelugomes.springbootwebfluxkotlin.model.kotlin.Tweet
import com.github.andrelugomes.springbootwebfluxkotlin.model.kotlin.User
import com.github.andrelugomes.springbootwebfluxkotlin.subscriber.MyConsumer
import com.github.andrelugomes.springbootwebfluxkotlin.subscriber.MySubscriber
import com.github.andrelugomes.springbootwebfluxkotlin.subscriber.TweetSubscriber
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Test
import reactor.core.publisher.Flux


class FluxTest {

    lateinit var tweet1: Tweet
    lateinit var tweet2: Tweet

    @Before
    fun setUp() {
        val user = User("user")
        tweet1 = Tweet(123, "123", "text", "date", user)
        tweet2 = Tweet(124, "124", "text", "date", user)
    }

    @Test
    fun `Flux block`() {

        val flux = Flux.just(tweet1,tweet2)

        val first = flux.log().blockFirst()!! //maybe NULL

        Assertions.assertThat(first.id).isEqualTo(123)

        val last = flux.log().blockLast()!!

        Assertions.assertThat(last.id).isEqualTo(124)
    }

    @Test
    fun `Flux subscribe`() {
        var elements : MutableSet<Int> = mutableSetOf()

        Flux.just(1, 2, 3, 4)
            .log()
            .subscribe { elements.add(it) }

        Assertions.assertThat(elements).containsExactly(1, 2, 3, 4)
    }

    @Test
    fun `Flux subscribe disposable`() {

        val flux = Flux.just(tweet1,tweet2)

        var disposable = flux.log().subscribe {
            Assertions.assertThat(it).isExactlyInstanceOf(Tweet::class.java)
        }

        Assertions.assertThat(disposable.isDisposed).isTrue()
    }

    @Test
    fun `Flux multiples subscribe with Consumers`() {

        val flux = Flux.just(tweet1,tweet2)

        var consumer: (Tweet) -> Unit = {
            Assertions.assertThat(it.id).isGreaterThan(0)
        }

        flux.log().subscribe(consumer) //interface Consumer

        flux.log().subscribe(MySubscriber().subscribe()) //interface Consumer

        flux.log().subscribe(MyConsumer()) //interface Consumer

        flux.log().subscribe {
            Assertions.assertThat(it).isExactlyInstanceOf(Tweet::class.java)
        } //interface Consumer
    }


    @Test
    fun `Flux base subscriber`() {

       Flux.just(tweet1,tweet2).subscribeWith(TweetSubscriber())
        
    }

    @Test
    fun `Flux filter, map and collect`() {

        val tweet3 = Tweet(125, "125", "text", "date", null)

        val flux = Flux.just(tweet1,tweet2,tweet3)

        val list = flux
            .log()
            .filter { it.user != null }
            .map { SortedTweet(it.id!!,"NEW:${it.index}", it.text, it.createdAt) }
            .collectSortedList()
            .block()

       Assertions.assertThat(list).hasSize(2)
       Assertions.assertThat(list!![0].index).isEqualTo("NEW:123")
       Assertions.assertThat(list!![1].index).isEqualTo("NEW:124")
    }


    @Test
    fun `Flux map texts`() {

        val flux = Flux.just(tweet1,tweet2)

        val texts = flux.map {it.text!!.toUpperCase()}

        texts.toStream().forEach { println(it) }
    }

    @Test
    fun `Flux simple reduce`() {

        val ids = Flux.just(1L,2L)

        val result = ids.reduce { t, u -> t + u }

        result.subscribe {
            Assertions.assertThat(it).isEqualTo(3L)
        }
    }

    @Test
    fun `Flux simple reduce with explicit BiFunction`() {

        val ids = Flux.just(1L,2L)

        val biFunction: (Long, Long) -> Long = { t, u -> t + u } //Java 1.8 interface BiFunction<T, U, R>

        val result = ids.reduce(biFunction)

        result.subscribe {
            Assertions.assertThat(it).isEqualTo(3L)
        }
    }

    @Test
    fun `Flux reduce nullable ids from tweets`() {

        val flux = Flux.just(tweet1,tweet2)

        val ids = flux.map {it.id}

        //val result = ids.reduce { t, u -> t + u}

        //val result = ids.reduce { t, u -> t!! + u!! }

        //val result = ids.reduce { t, u -> t!!.plus(u!!) }

        //val result = ids.reduce { t: Long?, u: Long? -> t!!.plus(u!!) }

        //val result = ids.reduce { t, u -> u?.let { t?.plus(it) } }

        //val result = ids.reduce { t, u -> u.let { t!!.plus(it!!) } }

        val result = ids.reduce { t, u -> u!!.let { t!!.plus(it) } }

        result.subscribe {
            Assertions.assertThat(it).isEqualTo(tweet1.id?.plus(tweet2.id!!))
        }
    }
}