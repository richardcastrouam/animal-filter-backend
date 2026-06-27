package com.animalfilter.backend.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() } // Desactivar CSRF para poder usar Postman/Android fácilmente
            .authorizeHttpRequests { auth ->
                auth.requestMatchers("/api/media/**").permitAll()
                auth.requestMatchers("/api/users/**").permitAll()
                auth.anyRequest().authenticated()
            }
        return http.build()
    }
}
