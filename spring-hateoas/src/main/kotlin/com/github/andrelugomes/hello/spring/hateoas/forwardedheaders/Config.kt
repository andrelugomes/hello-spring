package com.github.andrelugomes.hello.spring.hateoas.forwardedheaders

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.filter.ForwardedHeaderFilter

@Configuration
class Config {

    @Bean
    fun forwardedHeaderFilter(): ForwardedHeaderFilter {
        return ForwardedHeaderFilter()
    }

}