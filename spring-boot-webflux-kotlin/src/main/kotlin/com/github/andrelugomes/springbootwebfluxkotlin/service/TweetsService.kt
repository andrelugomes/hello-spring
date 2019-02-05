package com.github.andrelugomes.springbootwebfluxkotlin.service

import com.github.andrelugomes.springbootwebfluxkotlin.extentions.selection
import com.github.andrelugomes.springbootwebfluxkotlin.model.kotlin.Tweet
import com.github.andrelugomes.springbootwebfluxkotlin.repository.TweetsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


interface TweetsService {
    fun save(tweet: Tweet): Mono<Tweet>
    fun findAll(): Flux<Tweet>
    fun findById(id: Long): Mono<Tweet>
    fun findById(id: Long, fields: Array<String>): Mono<Tweet>
    fun findFieldsById(id: Long, fields: Array<String>): Mono<Tweet>
    fun findAllSelection(user: String, fields: Array<String>): Flux<Tweet>
}

@Service
class TweetsServiceImpl(@Autowired var repository: TweetsRepository) : TweetsService {
    override fun save(tweet: Tweet) = repository.save(tweet)

    override fun findAll() = repository.findAll()

    override fun findById(id: Long) = repository.findById(id)

    override fun findById(id: Long, fields: Array<String>) = repository.findById(id).map {
            tweet -> tweet.selection(fields)
    }

    override fun findFieldsById(id: Long, fields: Array<String>) = repository.findFieldsById(id, fields)

    override fun findAllSelection(user: String, fields: Array<String>): Flux<Tweet> = repository.findFieldsByUser(user, fields)
}