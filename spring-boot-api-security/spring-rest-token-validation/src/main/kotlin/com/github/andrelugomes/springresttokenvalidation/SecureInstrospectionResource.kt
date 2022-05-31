package com.github.andrelugomes.springresttokenvalidation

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import java.net.URLEncoder
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.nio.charset.StandardCharsets
import java.util.*

@RestController
@RequestMapping("/secure-introspection")
class SecureInstrospectionResource {

    val logger: Logger = LoggerFactory.getLogger(SecureInstrospectionResource::class.java)
    val client: HttpClient = HttpClient.newHttpClient()

    @GetMapping
    fun secure(@RequestHeader headers: HttpHeaders): ResponseEntity<String> {

        val authServer = URI("http://localhost:8080/realms/api-authorization/protocol/openid-connect/token/introspect")

        val request =
            HttpRequest.newBuilder(authServer)
                .setHeader("Authorization", "Basic ${Base64.getEncoder().encodeToString("spring-rest-token-validation:1clyzVUEAkjwMIxBz6EcseAClRl1pPjd".toByteArray())}")
                .setHeader("Content-Type", "application/x-www-form-urlencoded")
                .POST(
                    ofFormData(
                        mapOf(
                            "token" to URLEncoder.encode(
                                headers["Authorization"]?.first(),
                                StandardCharsets.UTF_8
                            )
                        )
                    )
                )
                .build()

        logger.info(request.toString())

        val response = client.send(request, HttpResponse.BodyHandlers.ofString())


        return if (response.statusCode() == 200) {
            ResponseEntity.ok("Introspection OK")
        } else {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
        }
    }

    fun ofFormData(data: Map<Any, Any>): HttpRequest.BodyPublisher? {
        val result = StringBuilder()
        for ((key, value) in data) {
            if (result.isNotEmpty()) {
                result.append("&")
            }
            val encodedName = URLEncoder.encode(key.toString(), StandardCharsets.UTF_8)
            val encodedValue = URLEncoder.encode(value.toString(), StandardCharsets.UTF_8)
            result.append(encodedName)
            if (encodedValue != null) {
                result.append("=")
                result.append(encodedValue)
            }
        }
        return HttpRequest.BodyPublishers.ofString(result.toString())
    }
}