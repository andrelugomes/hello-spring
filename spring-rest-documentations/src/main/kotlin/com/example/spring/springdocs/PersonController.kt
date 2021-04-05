package com.example.spring.springdocs

import com.example.spring.springdocs.data.Person
import com.example.spring.swagger.data.Client
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/person")
class PersonController {

    @Operation(summary = "Get a person by ID")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Found a Person", content = [
            (Content(mediaType = "application/json", array = (
                    ArraySchema(schema = Schema(implementation = Person::class)))))]),
        ApiResponse(responseCode = "400", description = "Bad request", content = [Content()]),
        ApiResponse(responseCode = "404", description = "Not found", content = [Content()])]
    )
    @GetMapping("/{id}")
    fun get(@PathVariable id: Int): ResponseEntity<Person> {
        return ResponseEntity.ok(Person("Person Name"))
    }
}