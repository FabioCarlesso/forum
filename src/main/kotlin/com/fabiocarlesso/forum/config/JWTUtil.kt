package com.fabiocarlesso.forum.config

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.util.*
import javax.crypto.SecretKey

@Component
class JWTUtil {
    private val expiration : Long = 60000
    @Value("\${jwt.secret}")
    private lateinit var secret : String
    fun generateToken(username: String) : String {
        return Jwts.builder()
            .subject(username)
            .expiration(Date(System.currentTimeMillis() + expiration))
            .signWith(signingKey())
            .compact()
    }

    private fun signingKey(): SecretKey? {
        return Keys.hmacShaKeyFor(secret.toByteArray(StandardCharsets.UTF_8))
    }
}