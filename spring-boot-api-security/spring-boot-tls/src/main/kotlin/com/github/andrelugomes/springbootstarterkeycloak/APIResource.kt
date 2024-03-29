package com.github.andrelugomes.springbootstarterkeycloak

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class APIResource {

    val logger: Logger = LoggerFactory.getLogger(APIResource::class.java)

    @GetMapping("/secure")
    fun public(): ResponseEntity<String> {
        return ResponseEntity.ok("Access Allowed")
    }

}