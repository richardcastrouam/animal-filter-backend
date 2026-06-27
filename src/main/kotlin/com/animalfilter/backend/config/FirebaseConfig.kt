package com.animalfilter.backend.config

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import java.io.ByteArrayInputStream
import java.io.FileInputStream
import java.util.Base64

@Configuration
class FirebaseConfig {

    @Value("\${firebase.storage.bucket}")
    lateinit var bucketName: String

    @Value("\${firebase.credentials.path:src/main/resources/serviceAccountKey.json}")
    lateinit var credentialsPath: String

    @Value("\${firebase.credentials.json-base64:}")
    lateinit var credentialsJsonBase64: String

    @PostConstruct
    fun initialize() {
        if (FirebaseApp.getApps().isNotEmpty()) return

        val credentialsStream =
            if (credentialsJsonBase64.isNotBlank()) {
                ByteArrayInputStream(Base64.getDecoder().decode(credentialsJsonBase64))
            } else {
                FileInputStream(credentialsPath)
            }

        credentialsStream.use { serviceAccount ->
            val options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setStorageBucket(bucketName)
                .build()

            FirebaseApp.initializeApp(options)
            println("Firebase Admin SDK inicializado correctamente")
        }
    }
}