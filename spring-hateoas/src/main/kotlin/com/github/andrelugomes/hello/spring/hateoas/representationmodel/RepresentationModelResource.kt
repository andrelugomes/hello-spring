package com.github.andrelugomes.hello.spring.hateoas.representationmodel

import org.springframework.hateoas.Link
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/representation-model")
class RepresentationModelResource {

    @GetMapping("/{id}")
    fun getSample(
        @PathVariable id: Long
    ): Sample {
        val sample = Sample(id, "Sample name : $id")
        val link = Link.of("/sample/$id")
        sample.add(link)
        return sample
    }
}