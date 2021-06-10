package com.github.andrelugomes.hello.spring.hateoas.custommediatypes

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.module.SimpleModule
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.Links


class Jackson2BrazilianOpenBankingModule(name: String?) : SimpleModule(name) {

    init {
        //setMixInAnnotation(EntityModel::class.java, EntityModelMixin::class.java)

        //addSerializer(EntityModel::class.java, MyEntityModelSerializer())

        addSerializer(Links::class.java, MyLinksSerializer())
        //addDeserializer(Links::class.java, JsonApiLinksDeserializer())
    }

    //@JsonSerialize(using = MyEntityModelSerializer::class)
    //@JsonDeserialize(using = JsonApiEntityModelDeserializer::class)
    internal abstract class EntityModelMixin<T> : EntityModel<T>()
}