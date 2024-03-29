# Spring Boot OAuth2 Password

This project use:
+ keycloak-spring-boot-starter
+ passwork grant_type
+ Realm Roles
+ Client Roles
+ User Role meppings

References:
+ https://www.keycloak.org/docs/latest/securing_apps/#_spring_boot_adapter
+ https://medium.com/devops-dudes/securing-spring-boot-rest-apis-with-keycloak-1d760b2004e

## Configuration

Associate Realm Roles to Client Roles
![img.png](img.png)

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
./employee-app-client.sh employee1 employee1
```
```shell
./employee-app-client.sh employee2 employee2
```
```shell
./employee-app-client.sh employee3 employee3
```

## Needs authentication

Get a 302 to authenticate user
```shell
curl -v localhost:8082/api/all-users
```



