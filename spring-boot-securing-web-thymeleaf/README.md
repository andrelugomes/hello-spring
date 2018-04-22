# Spring Security Web Login with Thymeleaf

## H2 console

localhost:8080/h2

## JDBC Authentication

```java
auth.jdbcAuthentication()
    .usersByUsernameQuery(usersQuery)
    .authoritiesByUsernameQuery(rolesQuery)
```

## Initial Data

```text
resources/data.sql
```
```java
@PostConstruct
public void init(){
    warmupDataConfig.createData();
}
```

## CSRF

+ https://www.owasp.org/index.php/Testing_for_CSRF_(OTG-SESS-005)

+ https://docs.spring.io/spring-security/site/docs/current/reference/html/csrf.html

```java
@EnableWebSecurity
```
```html
<input type="hidden" name="_csrf" value="953da922-2fd5-4d96-a0fa-51b61d8e732a">
```