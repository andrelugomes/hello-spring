package com.github.andrelugomes.springsecurityoauth2authredirect

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken
import org.springframework.security.oauth2.core.oidc.user.OidcUser
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class IndexController {
    @Autowired
    private val authorizedClientService: OAuth2AuthorizedClientService? = null

    @GetMapping("/index")
    fun getIndex(
        model: Model,
        @AuthenticationPrincipal principal: OidcUser,
        authentication: OAuth2AuthenticationToken
    ): String {
        val authorizedClient = getAuthorizedClient(authentication)

        model.addAttribute("principal_name", principal.name)
        model.addAttribute("principal_preferred_username", principal.attributes["preferred_username"])
        model.addAttribute("id_token", principal.idToken)

        model.addAttribute("user_name", authentication.principal.getAttribute("preferred_username"))

        model.addAttribute("client_name", authorizedClient.clientRegistration.clientId)
        model.addAttribute("scopes", authorizedClient.accessToken.scopes)
        model.addAttribute("access_token", authorizedClient.accessToken.tokenValue)

        return "index"
    }

    private fun getAuthorizedClient(authentication: OAuth2AuthenticationToken): OAuth2AuthorizedClient {
        return authorizedClientService!!.loadAuthorizedClient(
            authentication.authorizedClientRegistrationId, authentication.name
        )
    }
}