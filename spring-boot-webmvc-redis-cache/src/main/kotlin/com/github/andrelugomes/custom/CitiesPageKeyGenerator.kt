package com.github.andrelugomes.custom

import org.springframework.cache.interceptor.KeyGenerator
import org.springframework.data.domain.PageRequest
import java.lang.reflect.Method

class CitiesPageKeyGenerator : KeyGenerator {

    override fun generate(target: Any, method: Method, vararg params: Any?): Any {
        val stateId = params.first()
        val page = params.last() as PageRequest
        return "${stateId}_${page.pageNumber}_${page.pageSize}_${page.sort}"
    }
}