package com.github.andrelugomes.hello.spring.hateoas.custommediatypes

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import org.springframework.hateoas.EntityModel

class MyEntityModelSerializer : JsonSerializer<EntityModel<*>>() {

    override fun serialize(value: EntityModel<*>?, gen: JsonGenerator?, serializers: SerializerProvider?) {
        TODO("Not yet implemented")
    }

}
