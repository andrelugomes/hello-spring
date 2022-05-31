package com.github.andrelugomes.springbootstarterkeycloak

import org.keycloak.adapters.springsecurity.KeycloakConfiguration
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper
import org.springframework.security.core.session.SessionRegistryImpl
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy

@KeycloakConfiguration
@EnableGlobalMethodSecurity(jsr250Enabled = true)
class SecurityConfig : KeycloakWebSecurityConfigurerAdapter() {

    /**
     * Registers the KeycloakAuthenticationProvider with the authentication manager.
     */
    @Autowired
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        val keycloakAuthenticationProvider = keycloakAuthenticationProvider()
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(SimpleAuthorityMapper())
        auth.authenticationProvider(keycloakAuthenticationProvider)
    }

    /**
     * Defines the session authentication strategy.
     */
    @Bean
    override fun sessionAuthenticationStrategy(): SessionAuthenticationStrategy? {
        return RegisterSessionAuthenticationStrategy(SessionRegistryImpl())
    }

    override fun configure(http: HttpSecurity) {
        super.configure(http)
        http.authorizeRequests()
            .antMatchers("/api/all-users").hasAnyRole("user","admin")
            .anyRequest().permitAll()
        http.csrf().disable()
    }
}