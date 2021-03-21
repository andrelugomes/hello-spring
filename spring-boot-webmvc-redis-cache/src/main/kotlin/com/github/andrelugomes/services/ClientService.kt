package com.github.andrelugomes.services


import com.github.andrelugomes.model.Client
import com.github.andrelugomes.model.ClientSerializable
import com.github.andrelugomes.resources.ClientResource
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import java.util.*

@Service
class ClientService {

    var logger: Logger = LoggerFactory.getLogger(ClientResource::class.java)

    @Cacheable("clients", key = "#id")
    fun getClientById(id: Int): Client {
        logger.info("Caching client=$id")
        return Client(id = id, token = UUID.randomUUID().toString(), name = "André")
    }

    @CacheEvict("clients", key = "#id")
    fun deleteById(id: Int) {
        logger.info("Cache Evict for client=$id")
    }

    @Cacheable("client-serial", key = "#id")
    fun getClientSerializableById(id: Int): ClientSerializable {
        logger.info("Caching client=$id")
        return ClientSerializable(id, UUID.randomUUID().toString(), "André Luis")
    }
}