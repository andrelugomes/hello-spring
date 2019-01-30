package com.github.andrelugomes.springbootwebfluxkotlin.model.kotlin

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.mongodb.core.mapping.Field


data class User(
    @JsonProperty("created_at") @Field("created_at") val screenName: String?
)