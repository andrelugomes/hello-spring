package com.github.andrelugomes.springresttokenvalidation

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/insecure")
class InsecureResource {

    @GetMapping
    fun insecure(): ResponseEntity<String> {

        return ResponseEntity.ok("OK")
    }
}