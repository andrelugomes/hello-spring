package com.github.andrelugomes.hello.spring.hateoas.custommediatypes

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializerProvider
import org.springframework.hateoas.Link
import org.springframework.hateoas.LinkRelation
import org.springframework.hateoas.Links
import java.io.IOException
import java.util.function.Function


class MyLinksSerializer : JsonSerializer<Links>() {

    private val objectMapper = ObjectMapper()

    @Throws(IOException::class)
    override fun serialize(value: Links, gen: JsonGenerator, provider: SerializerProvider?) {
        val linksMap: MutableMap<LinkRelation, MutableList<Link>> = LinkedHashMap<LinkRelation, MutableList<Link>>()
        for (link in value) {
            linksMap.computeIfAbsent(
                link.rel,
                Function<LinkRelation, MutableList<Link>> { key: LinkRelation? -> ArrayList<Link>() })
                .add(link)
        }
        gen.writeStartObject()
        for ((rel, list) in linksMap) {
            if (list.size == 1) {
                val link: Link = list[0]
                serializeLinkWithRelation(gen, link)
            } else {
                gen.writeArrayFieldStart(rel.value())
                for (link in list) {
                    serializeLinkWithoutRelation(gen, link)
                }
                gen.writeEndArray()
            }
        }
        gen.writeEndObject()
    }

    @Throws(IOException::class)
    private fun serializeLinkWithRelation(gen: JsonGenerator, link: Link) {
        if (isSimpleLink(link)) {
            gen.writeStringField(link.getRel().value(), link.getHref())
        } else {
            gen.writeObjectFieldStart(link.getRel().value())
            gen.writeStringField("href", link.getHref())
            gen.writeObjectField("meta", getAttributes(link))
            gen.writeEndObject()
        }
    }

    @Throws(IOException::class)
    private fun serializeLinkWithoutRelation(gen: JsonGenerator, link: Link) {
        if (isSimpleLink(link)) {
            gen.writeString(link.getHref())
        } else {
            gen.writeStartObject()
            gen.writeStringField("href", link.getHref())
            gen.writeObjectField("meta", getAttributes(link))
            gen.writeEndObject()
        }
    }

    private fun isSimpleLink(link: Link): Boolean {
        return "self" == link.getRel().value() || getAttributes(link)!!.isEmpty()
    }


    private fun getAttributes(link: Link): MutableMap<*, *>? {
        val attributeMap: MutableMap<String, Any>? = objectMapper.convertValue(link,MutableMap::class.java) as MutableMap<String, Any>?
        attributeMap!!.remove("rel")
        attributeMap.remove("href")
        attributeMap.remove("template")
        attributeMap.remove("affordances")
        if (link.isTemplated) {
            attributeMap["isTemplated"] = true
        }
        return attributeMap
    }

}
