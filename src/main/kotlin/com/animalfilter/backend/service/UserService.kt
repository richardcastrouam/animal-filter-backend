package com.animalfilter.backend.service

import com.animalfilter.backend.model.UserRegistrationRequest
import com.animalfilter.backend.model.UserResponse
import com.google.cloud.firestore.Firestore
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserRecord
import com.google.firebase.cloud.FirestoreClient
import org.springframework.stereotype.Service

@Service
class UserService {

    fun registerUser(request: UserRegistrationRequest): UserResponse {
        // 1. Crear usuario en Firebase Auth
        val createRequest = UserRecord.CreateRequest()
            .setEmail(request.email)
            .setPassword(request.password)
            .setDisplayName(request.username)

        val userRecord: UserRecord = FirebaseAuth.getInstance().createUser(createRequest)

        // 2. Guardar datos adicionales en Firestore
        val db: Firestore = FirestoreClient.getFirestore()
        val userMap = mapOf(
            "uid" to userRecord.uid,
            "email" to request.email,
            "username" to request.username,
            "profileImageUrl" to "",
            "description" to "",
            "friends" to emptyList<String>()
        )

        db.collection("users").document(userRecord.uid).set(userMap).get()

        return UserResponse(
            uid = userRecord.uid,
            email = userRecord.email,
            username = userRecord.displayName
        )
    }

    fun getUserByEmail(email: String): UserResponse? {
        val userRecord = FirebaseAuth.getInstance().getUserByEmail(email)
        return UserResponse(
            uid = userRecord.uid,
            email = userRecord.email,
            username = userRecord.displayName
        )
    }
}
