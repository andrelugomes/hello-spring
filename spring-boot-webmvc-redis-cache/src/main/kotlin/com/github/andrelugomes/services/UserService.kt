package com.github.andrelugomes.services

import com.github.andrelugomes.custom.CacheUser
import com.github.andrelugomes.custom.CacheUserById
import com.github.andrelugomes.custom.RemoveUserFromCache
import com.github.andrelugomes.custom.UpdateUserCached
import com.github.andrelugomes.model.User
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService {

    var logger: Logger = LoggerFactory.getLogger(UserService::class.java)

    //@CacheUser
    @CacheUserById
    fun getById(id: Int): User {
        logger.info("Caching user=$id")
        return User(id, UUID.randomUUID().toString())
    }

    @RemoveUserFromCache
    fun deleteById(id: Int) {

    }

    @UpdateUserCached
    fun update(user: User): User {

        return user
    }

}
