package com.example.spring.kotlin.resource

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/find")
class FindResource {


    @GetMapping
    fun find() = "found!"

}
