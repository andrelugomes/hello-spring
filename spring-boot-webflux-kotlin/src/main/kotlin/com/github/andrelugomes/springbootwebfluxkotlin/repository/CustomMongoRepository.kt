package com.github.andrelugomes.springbootwebfluxkotlin.repository

import com.github.andrelugomes.springbootwebfluxkotlin.model.kotlin.Tweet
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

interface CustomMongoRepository {
    fun findFieldsById(id: Long, fields: Array<String>): Mono<Tweet>
}

@Repository
class CustomMongoRepositoryImpl(@Autowired val mongoTemplate: MongoTemplate) : CustomMongoRepository {

    //@Query(value = "{ 'id' : ?0 }", fields = "{ text : 1, id : 1 }")
    override fun findFieldsById(id: Long, fields: Array<String>): Mono<Tweet> {
        val query = Query(Criteria.where("_id").`is`(id))

        fields.forEach { query.fields().include(it) }

        return Mono.justOrEmpty(mongoTemplate.findOne(query, Tweet::class.java))
    }
}
