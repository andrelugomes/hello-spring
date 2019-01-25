package com.github.andrelugomes.springbootwebfluxkotlin.resources

import com.github.andrelugomes.springbootwebfluxkotlin.model.kotlin.Tweet
import com.github.andrelugomes.springbootwebfluxkotlin.service.TweetsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import javax.validation.Valid

@RestController
@RequestMapping("/tweets")
class TweetsResource(@Autowired var service: TweetsService) {

    /*Block Body
    @PostMapping
    fun save(@Valid @RequestBody tweet: Tweet): Mono<Tweet> {
        return service.save(tweet)
    }*/

    /*Explicit Return Type
    @PostMapping
    fun save(@Valid @RequestBody tweet: Tweet): Mono<Tweet> = service.save(tweet)*/

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    fun save(@Valid @RequestBody tweet: Tweet) = service.save(tweet)

    @GetMapping
    fun findAll() = service.findAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): Mono<ResponseEntity<Tweet>> {

        return service.findById(id)
            .map {tweet -> ResponseEntity.ok(tweet) }
            .defaultIfEmpty(ResponseEntity.notFound().build())
    }

    @GetMapping("/{id}", params = ["non-mongo"])
    fun findByIdFieldSelection(@PathVariable id: Long,
                 @RequestParam(required = false, defaultValue = "") fields: Array<String>
    ): Mono<ResponseEntity<Tweet>> {

        return service.findById(id, fields)
            .map {tweet -> ResponseEntity.ok(tweet) }
            .defaultIfEmpty(ResponseEntity.notFound().build())
    }

    @GetMapping("/{id}", params = ["mongo"])
    fun findByIdMongoFieldSelection(@PathVariable id: Long,
                               @RequestParam(required = false, defaultValue = "") fields: Array<String>
    ): Mono<ResponseEntity<Tweet>> {

        return service.findFieldsById(id, fields)
            .map {tweet -> ResponseEntity.ok(tweet) }
            .defaultIfEmpty(ResponseEntity.notFound().build())
    }
}

