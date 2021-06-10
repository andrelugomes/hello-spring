package com.github.andrelugomes.hello.spring.hateoas.entitymodel

import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.Link
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/entity-model")
class EntityModelResource {

    @GetMapping("/{id}")
    fun getSample(
        @PathVariable id: Long
    ): EntityModel<Sample> {
        val sample = Sample(id, "Sample name : $id")
        val link = Link.of("/entity-model/$id")


        return EntityModel.of(sample).add(link)
    }
}