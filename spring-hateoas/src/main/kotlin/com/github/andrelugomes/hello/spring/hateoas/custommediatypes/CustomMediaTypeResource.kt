package com.github.andrelugomes.hello.spring.hateoas.custommediatypes

import org.springframework.hateoas.Link
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/custom-media-type")
class CustomMediaTypeResource {

    @GetMapping("/{id}", produces = ["application/vnd.brazilian-open-banking.v1+json"])
    fun getSample(
        @PathVariable id: Long
    ): DataRepresentationModel<Sample> {
        val sample = Sample(id, "Sample name : $id")
        val link: Link = linkTo(CustomMediaTypeResource::class.java).slash(sample.id).withSelfRel()

        return DataRepresentationModel.by(sample).add(link)
    }
}