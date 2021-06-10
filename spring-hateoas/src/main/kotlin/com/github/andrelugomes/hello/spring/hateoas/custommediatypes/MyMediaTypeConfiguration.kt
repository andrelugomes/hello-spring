package com.github.andrelugomes.hello.spring.hateoas.custommediatypes


import com.fasterxml.jackson.databind.Module
import org.springframework.context.annotation.Configuration
import org.springframework.hateoas.config.HypermediaMappingInformation
import org.springframework.http.MediaType

@Configuration
class MyMediaTypeConfiguration : HypermediaMappingInformation {

    override fun getMediaTypes(): MutableList<MediaType> {
        return MediaType.parseMediaTypes("application/vnd.brazilian-open-banking.v1+json")
    }

    override fun getJacksonModule(): Module? {
        return Jackson2BrazilianOpenBankingModule("brasilian-open-banking")
    }

    /*@Bean
    fun myLinkDiscoverer(): MyLinkDiscoverer? {
        return MyLinkDiscoverer()
    }*/
}