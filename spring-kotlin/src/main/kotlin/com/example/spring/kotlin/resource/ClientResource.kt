package com.example.spring.kotlin.resource

import com.example.spring.kotlin.model.Client
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/clients")
class ClientResource {

    @GetMapping("/{id}")
    fun client(@PathVariable("id") id: Int): Client {
       return Client("andre", "andrelugomes@gmail.com")
    }

}
