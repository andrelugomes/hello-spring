package com.github.andrelugomes.springbootwebfluxkotlin

import com.github.andrelugomes.springbootwebfluxkotlin.model.kotlin.SortedTweet
import org.assertj.core.api.Assertions
import org.junit.Test


class SortedTweetTest {

    @Test
    fun `Compare two sorted tweets`() {

        val sortedTweet1 = SortedTweet(1, "1", "text", "date")
        val sortedTweet2 = SortedTweet(2, "2", "text", "date")

        val result = sortedTweet1.compareTo(sortedTweet2)
        val result2 = sortedTweet2.compareTo(sortedTweet2)
        val result3 = sortedTweet2.compareTo(sortedTweet1)

        Assertions.assertThat(result).isEqualTo(-1)
        Assertions.assertThat(result2).isEqualTo(0)
        Assertions.assertThat(result3).isEqualTo(1)
    }

}