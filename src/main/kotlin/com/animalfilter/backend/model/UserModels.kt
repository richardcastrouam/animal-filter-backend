package com.animalfilter.backend.model

data class UserRegistrationRequest(
    val email: String,
    val password: String,
    val username: String
)

data class UserResponse(
    val uid: String,
    val email: String,
    val username: String,
    val profileImageUrl: String? = null
)
