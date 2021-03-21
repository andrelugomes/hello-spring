package com.github.andrelugomes.resources

import com.github.andrelugomes.model.User
import com.github.andrelugomes.services.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserResource(var service: UserService) {

    @GetMapping("/{id}")
    fun user(@PathVariable("id") id: Int): ResponseEntity<User> {
        val user = service.getById(id)

        return ResponseEntity.ok(user)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Int): ResponseEntity<Unit> {
        service.deleteById(id)

        return ResponseEntity.noContent().build()
    }

    @PutMapping("/{id}")
    fun put(@PathVariable("id") id: Int, @RequestBody user: User): ResponseEntity<User> {
        service.update(user)

        return ResponseEntity.ok(user)
    }

}
