package com.github.andrelugomes.springbootwebfluxkotlin.repository

import com.github.andrelugomes.springbootwebfluxkotlin.model.kotlin.Tweet
import org.springframework.data.mongodb.repository.ReactiveMongoRepository


interface TweetsRepository : ReactiveMongoRepository<Tweet, Long>, CustomMongoRepository