package com.github.andrelugomes.springsecurityoauth2

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.AuthenticationManagerResolver
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider
import org.springframework.security.oauth2.server.resource.authentication.OpaqueTokenAuthenticationProvider
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector
import javax.servlet.http.HttpServletRequest


@EnableWebSecurity
class SpringSecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    lateinit var tokenAuthenticationManagerResolver: AuthenticationManagerResolver<HttpServletRequest>

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {

        /*http.csrf().disable()
            .cors().disable()
            .authorizeRequests().anyRequest().authenticated()
            .and()
            //.oauth2ResourceServer().opaqueToken() //OpaqueTokenAuthenticationProvider.class token instrospection
            //.oauth2ResourceServer().jwt() //JwtAuthenticationProvider.class :  openid-connect/certs endpoint to validate X509Certificate
            .oauth2ResourceServer().authenticationManagerResolver(tokenAuthenticationManagerResolver)*/

        http
            .cors {
                it.disable()
            }
            .csrf {
                it.disable()
            }
            .authorizeRequests {
                it.anyRequest().authenticated()
            }
            .oauth2ResourceServer {
                it.authenticationManagerResolver(tokenAuthenticationManagerResolver)
            }
    }
}