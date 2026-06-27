package com.animalfilter.backend.controller

import com.animalfilter.backend.model.UserRegistrationRequest
import com.animalfilter.backend.model.UserResponse
import com.animalfilter.backend.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService) {

    @PostMapping("/register")
    fun register(@RequestBody request: UserRegistrationRequest): ResponseEntity<Any> {
        return try {
            val response = userService.registerUser(request)
            ResponseEntity.ok(response)
        } catch (e: Exception) {
            // Esto nos dirá el error real en el log de IntelliJ y lo enviará a la app
            println("Error en registro: ${e.message}")
            ResponseEntity.badRequest().body(mapOf("error" to e.message))
        }
    }
    @GetMapping("/login")
    fun login(@RequestParam email: String): ResponseEntity<Any> {
        return try {
            val user = userService.getUserByEmail(email)
            if (user != null) {
                ResponseEntity.ok(user)
            } else {
                ResponseEntity.notFound().build()
            }
        } catch (e: Exception) {
            ResponseEntity.status(401).build()
        }
    }
}
