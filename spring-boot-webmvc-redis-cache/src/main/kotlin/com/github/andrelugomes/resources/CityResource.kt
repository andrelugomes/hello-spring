package com.github.andrelugomes.resources

import com.github.andrelugomes.model.City
import com.github.andrelugomes.model.CitySerializable
import com.github.andrelugomes.repository.CityRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cities")
class CityResource(var redisTemplate: StringRedisTemplate, var citiesRepository: CityRepository) {

    var logger: Logger = LoggerFactory.getLogger(CityResource::class.java)

    /**
     * @Cacheable("cities", keyGenerator = "citiesPageKeyGenerator")
     *
     * com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Cannot construct instance of `org.springframework.data.domain.PageImpl` (no Creators, like default constructor, exist): cannot deserialize from Object value (no delegate- or property-based Creator)
     * at [Source: (byte[])"{"@class":"org.springframework.data.domain.PageImpl","content":["java.util.Collections$UnmodifiableRandomAccessList",[]],"pageable":{"@class":"org.springframework.data.domain.PageRequest","sort":{"@class":"org.springframework.data.domain.Sort","sorted":false,"unsorted":true,"empty":true},"pageNumber":0,"pageSize":10,"offset":0,"unpaged":false,"paged":true},"totalElements":0,"totalPages":0,"last":true,"first":true,"number":0,"sort":{"@class":"org.springframework.data.domain.Sort","sorted":false,""[truncated 73 bytes]; line: 1, column: 54]
     *
     *
     * ERROR 27626 --- [nio-8080-exec-2] o.a.c.c.C.[.[.[/].[dispatcherServlet]    : Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed; nested exception is org.springframework.data.redis.serializer.SerializationException: Could not deserialize: create instance error, null, public org.springframework.data.domain.PageImpl(java.util.List<T>,org.springframework.data.domain.Pageable,long); nested exception is com.alibaba.fastjson.JSONException: create instance error, null, public org.springframework.data.domain.PageImpl(java.util.List<T>,org.springframework.data.domain.Pageable,long)] with root cause
     *
     * com.alibaba.fastjson.JSONException: illegal getter
     */
    @GetMapping
    @Cacheable("cities", keyGenerator = "citiesPageKeyGenerator", cacheManager = "pageCacheManager")
    //@Cacheable("cities", keyGenerator = "citiesPageKeyGenerator")
    fun getCities(@RequestParam("state_id") id: Int, pageable: Pageable): Page<City> {
        logger.info("$id-${pageable.pageNumber}-${pageable.pageSize}")

        return citiesRepository.findAllByUf(id, pageable)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Int): ResponseEntity<Unit> {
        logger.info("delete all keys for id=$id")

        redisTemplate.keys("cities::$id*").onEach {
            redisTemplate.delete(it)
        }

        return ResponseEntity.noContent().build()
    }

}
