package com.github.andrelugomes.custom

import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@CacheEvict("users", cacheManager = "userCacheManager", key = "#id")
annotation class RemoveUserFromCache {
}