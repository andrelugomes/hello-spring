# Spring Hateoas

## Custom Media Types

+ JSON:API | https://jsonapi.org
+ MyCustom | Open Banking | https://github.com/toedter/spring-hateoas-jsonapi/blob/8ebc0c3510/lib/src/main/java/com/toedter/spring/hateoas/jsonapi/JsonApiLinksSerializer.java


## Forwarded Headers

+ https://docs.spring.io/spring-hateoas/docs/current/reference/html/#server.link-builder.forwarded-headers

+ `ForwardedHeaderFilter()`

```shell
curl  localhost:8080/custom-media-type/12345 \
    -H 'X-Forwarded-Proto: https' \
    -H 'X-Forwarded-Host: example.com' \
    -H 'X-Forwarded-Port: 9001' | jq .
```