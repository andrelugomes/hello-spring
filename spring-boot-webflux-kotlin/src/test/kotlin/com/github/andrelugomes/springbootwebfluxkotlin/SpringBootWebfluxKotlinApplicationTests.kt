package com.github.andrelugomes.springbootwebfluxkotlin

import com.github.andrelugomes.springbootwebfluxkotlin.model.kotlin.Tweet
import com.github.andrelugomes.springbootwebfluxkotlin.model.kotlin.User
import com.github.andrelugomes.springbootwebfluxkotlin.repository.TweetsRepository
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Mono

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringBootWebfluxKotlinApplicationTests {

	@Autowired
	lateinit var client: WebTestClient

    @Autowired
    lateinit var repository: TweetsRepository

	@Test
	fun `Should crate a new Tweet`() {

        val id: Long = 12131234
        val text = "Awesome Tweet"
        val tweet = Tweet(id, "12131234", text, "Some date", User("user"))

        client.post()
            .uri("/tweets")
			    .contentType(MediaType.APPLICATION_JSON_UTF8)
			    .accept(MediaType.APPLICATION_JSON_UTF8)
			.body(Mono.just(tweet), Tweet::class.java)
                .exchange()
            .expectStatus().isCreated
            .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
            .expectBody()
                .jsonPath("$.id").isEqualTo(id)
                .jsonPath("$.text").isEqualTo(text)
	}

    @Test
    fun `Should show a Tweet`() {

        val id: Long = 123321

        val save = repository.save(Tweet(id, id.toString(), "text", "Some date", User("user")))
        save.block()

        client.get()
            .uri("/tweets/{id}",id)
            .accept(MediaType.APPLICATION_JSON_UTF8)
            .exchange()
            .expectStatus().isOk
            .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
            .expectBody()
            .jsonPath("$.id").isEqualTo(id)
            .jsonPath("$.text").isEqualTo("text")
            .jsonPath("$.user.screen_name").isEqualTo("user")
    }

}

