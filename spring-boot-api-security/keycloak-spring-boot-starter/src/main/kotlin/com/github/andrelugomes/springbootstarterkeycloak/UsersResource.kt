package com.github.andrelugomes.springbootstarterkeycloak

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.annotation.security.RolesAllowed

@RestController
@RequestMapping("/api")
class UsersResource {

    val logger: Logger = LoggerFactory.getLogger(UsersResource::class.java)

    @GetMapping("/public")
    fun public(): ResponseEntity<String> {
        return ResponseEntity.ok("Public Access Allowed")
    }

    @RolesAllowed("user")
    @GetMapping("/user")
    fun user(): ResponseEntity<String> {
       return ResponseEntity.ok("User Access Allowed")
    }

    @RolesAllowed("admin")
    @GetMapping("/admin")
    fun admin(): ResponseEntity<String> {
        return ResponseEntity.ok("Admin Access Allowed")
    }

    @GetMapping("/all-users")
    fun all(): ResponseEntity<String> {
        return ResponseEntity.ok("All Users Access Allowed")
    }
}