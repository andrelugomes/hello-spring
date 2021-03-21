package com.github.andrelugomes.resources

import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import kotlin.random.Random

@RestController
@RequestMapping("/numbers")
class NumberResource {

    @Cacheable("numbers", key = "#id")
    @GetMapping("/{id}")
    fun cache(@PathVariable("id") id: Int) = Random.nextInt(0, 100)


    @CacheEvict("numbers", key = "#id")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Int): ResponseEntity<Unit> {


        return ResponseEntity.noContent().build()
    }

}
