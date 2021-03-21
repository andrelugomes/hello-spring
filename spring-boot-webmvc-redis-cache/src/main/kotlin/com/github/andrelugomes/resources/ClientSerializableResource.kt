package com.github.andrelugomes.resources

import com.github.andrelugomes.model.Client
import com.github.andrelugomes.model.ClientSerializable
import com.github.andrelugomes.services.ClientService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/clients-serial")
class ClientSerializableResource(var service: ClientService) {

    @GetMapping("/{id}")
    fun client(@PathVariable("id") id: Int): ResponseEntity<ClientSerializable> {
        val client = service.getClientSerializableById(id)

        return ResponseEntity.ok(client)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Int): ResponseEntity<Unit> {
        service.deleteById(id)

        return ResponseEntity.noContent().build()
    }

}
