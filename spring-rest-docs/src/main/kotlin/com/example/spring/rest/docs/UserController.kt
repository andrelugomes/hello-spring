package com.example.spring.rest.docs

import com.example.spring.rest.docs.models.User
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/users")
class UserController {

    @GetMapping("/{id}")
    fun get(@PathVariable id: Int): ResponseEntity<User> {
        return ResponseEntity.ok(User(id, "Full Name"))
    }
}