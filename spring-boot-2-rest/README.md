# SpringBoot 2 Rest

## HTTP Protocol

## Rest Guideline

### Resources

### JSON / XML  Request and Response

```bash
curl -X GET "http://localhost:8080/v1/cities/1" -H "accept: application/xml" //Content Negotiation
<City><id>1</id><cod>1100015</cod><name>Alta Floresta D'Oeste</name><state><id>22</id><cod>11</cod><name>Rondônia</name><uf>RO</uf><region><id>1</id><name>Norte</name><active>true</active></region></state></City>                                                                                                                                        ?  ~ curl -X GET localhost:8080/regions/1/representation -H  "accept: application/json"

curl -X GET "http://localhost:8080/v1/cities/1" -H "accept: application/json"
{"id":1,"cod":1100015,"name":"Alta Floresta D'Oeste","state":{"id":22,"cod":11,"name":"Rondônia","uf":"RO","region":{"id":1,"name":"Norte","active":true}}}

```

### HTTP Verbs

### HTTP Status code
    
### Sub-resources for relations 

### Fitering, Paging and Sorting

### Versioning

#### URI Version
```bash
curl -X GET "http://localhost:8080/cities/v1/1" -H  "accept: */*"
```

http://localhost:8080/v1/cities

#### Accept header Versioning
headers[Accept=application/vnd.brazil.app-v1+json]
http://localhost:8080/cities

```bash
curl -X GET "http://localhost:8080/cities/1" -H  "accept: application/vnd.brazil.api-v1.0+json"
```

#### Param Version
http://localhost:8080/cities?version=1

#### Custom Headers versioning
headers[X-API-VERSION=1]
http://localhost:8080/cities

+ https://www.vinaysahni.com/best-practices-for-a-pragmatic-restful-api
+ https://blog.mwaysolutions.com/2014/06/05/10-best-practices-for-better-restful-api/
+ http://www.baeldung.com/rest-versioning
+ http://www.springboottutorial.com/spring-boot-versioning-for-rest-services
+ https://github.com/Haufe-Lexware/api-style-guide
+ http://www.kennethlange.com/posts/7_tips_for_designing_a_better_rest_api.html
+ https://stackoverflow.com/questions/13488697/restful-design-when-to-use-sub-resources

+ https://ruslanspivak.com/lsbaws-part1/
+ https://devblast.com/b/calling-your-web-api-restful-youre-doing-it-wrong
+ http://restcookbook.com/HTTP%20Methods/idempotency/
+ https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers

## Swagger

https://springfox.github.io/springfox/docs/current/

```text
http://localhost:8080/swagger-ui.html
```