package com.github.andrelugomes.resources

import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import kotlin.random.Random

@RestController
@RequestMapping("/strings")
class StringResource {

    @Cacheable("strings", key = "#id")
    @GetMapping("/{id}")
    fun cache(@PathVariable("id") id: Int) = UUID.randomUUID().toString()

    @CacheEvict("strings", key = "#id")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Int): ResponseEntity<Unit> {
        return ResponseEntity.noContent().build()
    }

    @CachePut("strings", key = "#id")
    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: Int) = UUID.randomUUID().toString()

}
