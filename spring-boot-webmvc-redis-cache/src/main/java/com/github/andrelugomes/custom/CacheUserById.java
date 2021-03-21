package com.github.andrelugomes.custom;

import org.springframework.cache.annotation.Cacheable;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Cacheable(value = "users", cacheManager = "userCacheManager", key = "#id")
public @interface CacheUserById {
}
