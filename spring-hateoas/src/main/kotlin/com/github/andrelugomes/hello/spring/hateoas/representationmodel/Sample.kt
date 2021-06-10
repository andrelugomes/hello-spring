package com.github.andrelugomes.hello.spring.hateoas.representationmodel

import org.springframework.hateoas.RepresentationModel

data class Sample(
    val id: Long,
    val name: String
): RepresentationModel<Sample>()