# Spring Boot Cache Redis

+ https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-caching
+ https://docs.spring.io/spring-framework/docs/5.3.5/reference/html/integration.html#cache

+ https://docs.spring.io/spring-data/data-redis/docs/current/reference/html/#redis:serializer

+ https://www.baeldung.com/spring-multiple-cache-managers

```shell
docker run --name spring-cache-redis -p 6379:6379 -d redis
```

```shell
docker run -it --net=host --rm redis redis-cli -h localhost

localhost:6379> KEYS strings*
localhost:6379> GET numbers::1
localhost:6379> MGET strings::1

localhost:6379> KEYS clients*
```