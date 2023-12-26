package com.fabiocarlesso.forum.config

import com.fabiocarlesso.forum.service.UsuarioService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.util.*
import javax.crypto.SecretKey

@Component
class JWTUtil (
    private val usuarioService: UsuarioService
){
    private val expiration : Long = 60000
    @Value("\${jwt.secret}")
    private lateinit var secret : String
    fun generateToken(username: String, authorities: Collection<GrantedAuthority>) : String {
        return Jwts.builder()
            .subject(username)
            .claim("role", authorities)
            .expiration(Date(System.currentTimeMillis() + expiration))
            .signWith(signingKey())
            .compact()
    }

    private fun signingKey(): SecretKey? {
        return Keys.hmacShaKeyFor(secret.toByteArray(StandardCharsets.UTF_8))
    }

    fun isValid(jwt: String?): Boolean {
        return try {
            Jwts.parser().verifyWith(signingKey()).build().parseSignedClaims(jwt).payload
            true
        } catch (e: IllegalArgumentException){
            false
        }
    }

    fun getAuthentication(jwt: String?) : Authentication {
        val username = Jwts.parser().verifyWith(signingKey()).build().parseSignedClaims(jwt).payload.subject
        val user = usuarioService.loadUserByUsername(username)
        return UsernamePasswordAuthenticationToken(username, null, user.authorities)
    }
}