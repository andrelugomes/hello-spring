package com.github.andrelugomes

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer
import com.github.andrelugomes.model.User
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext

@EnableCaching
@Configuration
class CacheConfiguration {

    /**
     * localhost:6379> get clients::1
     *"{\"@class\":\"com.github.andrelugomes.model.Client\",\"id\":1,\"token\":\"6450cfbf-0e28-491c-918a-008a8f6d97c1\",\"name\":\"Andr\xc3\xa9\"}"
     *
     * localhost:6379> get client-serial::1
     *"{\"@class\":\"com.github.andrelugomes.model.ClientSerializable\",\"id\":1,\"token\":\"56faed10-8bea-4b10-a7e5-b9ced0bc3141\",\"name\":\"Andr\xc3\xa9 Luis\"}"
     */
    @Bean
    @Primary
    fun cacheManager(lettuceConnectionFactory: LettuceConnectionFactory): CacheManager {

        val genericJackson2JsonRedisSerializer = GenericJackson2JsonRedisSerializer()

        val configuration = RedisCacheConfiguration
            .defaultCacheConfig()
            .serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(genericJackson2JsonRedisSerializer)
            )

        return RedisCacheManager.builder(lettuceConnectionFactory).cacheDefaults(configuration).build()
    }

    /*@Bean
    fun redisCacheConfiguration(): RedisCacheConfiguration {

        val stringRedisSerializer = StringRedisSerializer()
        val fastJsonRedisSerializer = FastJsonRedisSerializer(Client::class.java)
        //val fastJsonRedisSerializer = FastJsonRedisSerializer(JSONObject::class.java)
        val jackson2JsonRedisSerializer = Jackson2JsonRedisSerializer(Client::class.java)
        val jdkSerializationRedisSerializer = JdkSerializationRedisSerializer()
        val genericJackson2JsonRedisSerializer = GenericJackson2JsonRedisSerializer()

        *//*val objectMapper = ObjectMapper()
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        val genericJackson2JsonRedisSerializer = GenericJackson2JsonRedisSerializer(objectMapper)*//*

        *//*val objectMapper: ObjectMapper = Jackson2ObjectMapperBuilder().failOnEmptyBeans(false)
            .failOnUnknownProperties(false)
            .indentOutput(false)
            .serializationInclusion(JsonInclude.Include.NON_NULL)
            .modules( // Optional
                Jdk8Module(),  // Dates/Times
                JavaTimeModule()
            )
            .featuresToDisable(
                SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,
                DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS,
                SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS
            ).build()
        val genericJackson2JsonRedisSerializer = GenericJackson2JsonRedisSerializer(objectMapper)*//*

        return RedisCacheConfiguration
            .defaultCacheConfig()
            .serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(genericJackson2JsonRedisSerializer)
            )
    }*/

    /**
     * localhost:6379> get users::1
     *"{\"id\":1,\"token\":\"fb04466f-5b62-49df-abef-df7e13351495\"}"
     */
    @Bean
    fun userCacheManager(lettuceConnectionFactory: LettuceConnectionFactory): CacheManager {
        val configuration = RedisCacheConfiguration
            .defaultCacheConfig()
            .serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(
                    FastJsonRedisSerializer(User::class.java)
                )
            )

        return RedisCacheManager.builder(lettuceConnectionFactory).withCacheConfiguration("users", configuration)
            .build()
    }
}
