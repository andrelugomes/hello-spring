# SpringBoot 2 Rest

## HTTP Protocol

## Rest Guideline

### Resources

### JSON Request and Response

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

## Swagger

https://springfox.github.io/springfox/docs/current/

```text
http://localhost:8080/swagger-ui.html
```