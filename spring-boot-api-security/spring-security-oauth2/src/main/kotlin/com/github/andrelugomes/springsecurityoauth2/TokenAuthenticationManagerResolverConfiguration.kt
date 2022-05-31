package com.github.andrelugomes.springsecurityoauth2

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManagerResolver
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider
import org.springframework.security.oauth2.server.resource.authentication.OpaqueTokenAuthenticationProvider
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector
import javax.servlet.http.HttpServletRequest

@Configuration
class TokenAuthenticationManagerResolverConfiguration {

    @Bean
    fun tokenAuthenticationManagerResolver(jwtDecoder: JwtDecoder, opaqueTokenIntrospector: OpaqueTokenIntrospector):
            AuthenticationManagerResolver<HttpServletRequest> {
        val jwt = ProviderManager(JwtAuthenticationProvider(jwtDecoder))
        val opaqueToken = ProviderManager(OpaqueTokenAuthenticationProvider(opaqueTokenIntrospector))

        return AuthenticationManagerResolver { request ->
            if (isJwtPath(request)) {
                jwt
            } else {
                opaqueToken
            }
        }
    }

    private fun isJwtPath(request: HttpServletRequest): Boolean {
        return request.requestURI == "/security/jwt" || request.requestURI == "/security/jwt-principal"
    }
}