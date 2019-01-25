package com.github.andrelugomes.springbootwebfluxkotlin.model.kotlin

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "tweets")
data class Tweet (
    @Id val id : Long?,
    @Indexed(unique = true) @JsonProperty("id_str") @Field("id_str") val index : String?,
    @NotBlank @Size(max = 140) val text : String?,
    @JsonProperty("created_at") @Field("created_at") val createdAt : String?
)
