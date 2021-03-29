package com.github.andrelugomes.resources

import com.github.andrelugomes.model.Items
import com.github.andrelugomes.model.PageAdapter
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/items")
class ItemsResource(var redisTemplate: StringRedisTemplate) {

    var logger: Logger = LoggerFactory.getLogger(ItemsResource::class.java)

    /**
     *Using SpEL to create a Custom Key
     */
    @GetMapping("/{client_id}")
    @Cacheable(
        "items",
        key = "'ID='+#id+'::'+'PG_NUM='+#pageable.pageNumber+'::'+'PG_SIZE='+#pageable.pageSize+'::'+'PG_SORT='+#pageable.sort.toString()"
    )
    fun items(@PathVariable("client_id") id: String, pageable: Pageable): Page<Items> {
        logger.info("$id-${pageable.pageNumber}-${pageable.pageSize}")

        val total = 15
        val items = mutableListOf<Items>()

        repeat(total) {
            items.add(Items("Item $it", it.toDouble()))
        }

        return PageAdapter(items.subList(0, 10), pageable, total.toLong())
    }


    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Int): ResponseEntity<Unit> {
        logger.info("delete all keys for id=$id")

        redisTemplate.keys("items::$id*").onEach {
            redisTemplate.delete(it)
        }

        return ResponseEntity.noContent().build()
    }

}
