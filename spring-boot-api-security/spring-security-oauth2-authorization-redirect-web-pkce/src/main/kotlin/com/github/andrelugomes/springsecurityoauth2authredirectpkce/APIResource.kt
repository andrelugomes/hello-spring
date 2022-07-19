package com.github.andrelugomes.springsecurityoauth2authredirectpkce

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class APIResource {

    @GetMapping("/api")
    fun api(): ResponseEntity<String> {
        return ResponseEntity.ok("Access Allowed")
    }
}