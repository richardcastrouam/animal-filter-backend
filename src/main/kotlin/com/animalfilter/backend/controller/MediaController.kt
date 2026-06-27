package com.animalfilter.backend.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/media")
class MediaController {

    @PostMapping("/upload")
    fun uploadMedia(
        @RequestParam("file") file: MultipartFile,
        @RequestParam("userId") userId: String
    ): ResponseEntity<Map<String, String>> {
        
        if (file.isEmpty) {
            return ResponseEntity.badRequest().body(mapOf("error" to "Archivo vacío"))
        }

        // Aquí es donde llamaremos a JavaCV para aplicar los filtros
        println("Recibido archivo: ${file.originalFilename} de tipo: ${file.contentType}")
        
        // TODO: Lógica de procesamiento de filtro y subida a Firebase
        
        return ResponseEntity.ok(mapOf(
            "message" to "Archivo recibido correctamente",
            "fileName" to (file.originalFilename ?: "unknown")
        ))
    }
}
