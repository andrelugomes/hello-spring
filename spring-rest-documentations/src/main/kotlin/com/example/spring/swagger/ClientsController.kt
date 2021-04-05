package com.example.spring.swagger

import com.example.spring.swagger.data.Client
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/clients")
@Api(value = "Clients")
class ClientsController {

    @GetMapping("/{id}")
    @ApiOperation("Get Client by ID")
    fun get(@PathVariable id: Int): ResponseEntity<Client> {
        return ResponseEntity.ok(Client("Client Name"))
    }
}