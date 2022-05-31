# Keycloak Spring Boot Starter

+ https://medium.com/devops-dudes/securing-spring-boot-rest-apis-with-keycloak-1d760b2004e

## Configuration

Annotaion based

```kotlin
@RolesAllowed("admin")
@GetMapping("/admin")
fun admin(): ResponseEntity<String> {
    return ResponseEntity.ok("Admin Access Allowed")
}
```


Configuration based

```kotlin
override fun configure(http: HttpSecurity) {
    super.configure(http)
    http.authorizeRequests()
        .antMatchers("/api/all-users").hasAnyRole("user","admin")
        .anyRequest().permitAll()
    http.csrf().disable()
}
```

## User role based access

Public
```shell
curl localhost:8082/api/public    
```

Role Based 
```shell
/keycloak-spring-boot-starter/employee.sh employee1 employee1
```
```shell
/keycloak-spring-boot-starter/employee.sh employee2 employee2
```
```shell
/keycloak-spring-boot-starter/employee.sh employee3 employee3
```

## Needs authentication

Get a 302 to authenticate user
```shell
curl -v localhost:8082/api/all-users
```



