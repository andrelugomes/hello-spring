package com.github.andrelugomes.springsecurityoauth2.resouces

import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/security")
class Resource {

    @GetMapping("/opaque-token")
    fun opaque(): ResponseEntity<String> {

        //return ResponseEntity.ok("${authentication.getTokenAttributes().get("sub")} is the subject")
        return ResponseEntity.ok("Token OK")
    }

    @GetMapping("/jwt")
    fun jwt(): ResponseEntity<String> {

        return ResponseEntity.ok("JWT Ok")
    }

    @GetMapping("/jwt-principal")
    fun jwt(@AuthenticationPrincipal principal: Jwt): ResponseEntity<String> {

        return ResponseEntity.ok("${principal.getClaimAsString("preferred_username")} is the subject")
    }
}