# Spring Boot Cache Redis

+ https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-caching
+ https://docs.spring.io/spring-framework/docs/5.3.5/reference/html/integration.html#cache

+ https://docs.spring.io/spring-data/data-redis/docs/current/reference/html/#redis:serializer

+ https://www.baeldung.com/spring-multiple-cache-managers

+ https://stackoverflow.com/questions/55965523/error-during-deserialization-of-pageimpl-cannot-construct-instance-of-org-spr
+ https://docs.spring.io/spring-framework/docs/5.3.5/reference/html/core.html#expressions

+ [Spring Page Deserialization](https://stackoverflow.com/questions/52490399/spring-boot-page-deserialization-pageimpl-no-constructor)

```shell
docker run --name spring-cache-redis -p 6379:6379 -d redis
```

```shell
docker run -it --net=host --rm redis redis-cli -h localhost

localhost:6379> KEYS strings*
localhost:6379> GET numbers::1
localhost:6379> MGET strings::1

localhost:6379> KEYS clients*

localhost:6379> KEYS items*
```

### Using Java Serializer : JdkSerializationRedisSerializer() : RedisSerializer.java()

using for PagImpl Spring and any `Serializable` class

```shell
localhost:6379> get "cities::9_2_10_UNSORTED"
"\xac\xed\x00\x05sr\x00(org.springframework.data.domain.PageImpl\x0c\n\xe3f\xe5<H\xd6\x02\x00\x01J\x00\x05totalxr\x00%org.springframework.data.domain.Chunk\x0c\n\xe3f\xe5<H\xd6\x02\x00\x02L\x00\acontentt\x00\x10Ljava/util/List;L\x00\bpageablet\x00*Lorg/springframework/data/domain/Pageable;xpsr\x00\x13java.util.ArrayListx\x81\xd2\x1d\x99\xc7a\x9d\x03\x00\x01I\x00\x04sizexp\x00\x00\x00\nw\x04\x00\x00\x00\nsr\x00\"com.github.andrelugomes.model.City/\xa5n\xb1\xd4I\x10\x94\x02\x00\x03I\x00\x04codeI\x00\x02ufL\x00\x04namet\x00\x12Ljava/lang/String;xp\x00O^`\x00\x00\x00\tt\x00\x06Apor\xc3\xa9sq\x00~\x00\a\x00O^\xc3\x00\x00\x00\tt\x00\x06Ara\xc3\xa7usq\x00~\x00\a\x00O_&\x00\x00\x00\tt\x00\nAragar\xc3\xa7assq\x00~\x00\a\x00O_\x89\x00\x00\x00\tt\x00\x0bAragoi\xc3\xa2niasq\x00~\x00\a\x00O`\xeb\x00\x00\x00\tt\x00\tAraguapazsq\x00~\x00\a\x00Oa\xb1\x00\x00\x00\tt\x00\x0bAren\xc3\xb3polissq\x00~\x00\a\x00ObF\x00\x00\x00\tt\x00\aAruan\xc3\xa3sq\x00~\x00\a\x00Ob\xa9\x00\x00\x00\tt\x00\x0bAuril\xc3\xa2ndiasq\x00~\x00\a\x00Ocy\x00\x00\x00\tt\x00\rAvelin\xc3\xb3polissq\x00~\x00\a\x00Od\xa0\x00\x00\x00\tt\x00\x06Balizaxsr\x00+org.springframework.data.domain.PageRequest\xc0\xf9P\xc5\xc0\x9d\xc7&\x02\x00\x01L\x00\x04sortt\x00&Lorg/springframework/data/domain/Sort;xr\x003org.springframework.data.domain.AbstractPageRequest\x11\x1b\xe0U\x00\x1d-\xc7\x02\x00\x02I\x00\x04pageI\x00\x04sizexp\x00\x00\x00\x02\x00\x00\x00\nsr\x00$org.springframework.data.domain.SortO\x9e\x94\xbcF\xc7\xfa!\x02\x00\x01L\x00\x06ordersq\x00~\x00\x02xpsr\x00\x1ajava.util.Arrays$ArrayList\xd9\xa4<\xbe\xcd\x88\x06\xd2\x02\x00\x01[\x00\x01at\x00\x13[Ljava/lang/Object;xpur\x00-[Lorg.springframework.data.domain.Sort$Order;\xd3\xd6\xb4\xe6\xe4\xee\xb4\xf0\x02\x00\x00xp\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\xf6"
```

### Using Jackson Serializer : GenericJackson2JsonRedisSerializer() : RedisSerializer.json()

```shell
localhost:6379> get "clients::1"
"{\"@class\":\"com.github.andrelugomes.model.Client\",\"id\":1,\"token\":\"d1c85499-e698-4076-bb9a-6c64ce3445fb\",\"name\":\"Andr\xc3\xa9\"}
```

### FastJson - FastJsonRedisSerializer(User::class.java)

```shell
localhost:6379> get "users::1"
"{\"id\":1,\"token\":\"fc9101e1-e110-4068-bd55-2f3edf7e904b\"}"
```

## Pageable Curl

```shell
curl -X GET "localhost:8080/cities?state_id=22&page=0&size=10" | jq .
```