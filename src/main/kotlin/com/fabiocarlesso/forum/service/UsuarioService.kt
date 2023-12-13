package com.fabiocarlesso.forum.service

import com.fabiocarlesso.forum.model.Usuario
import com.fabiocarlesso.forum.repository.UsuarioRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UsuarioService (private val repository: UsuarioRepository): UserDetailsService {
    fun buscarPorId(id: Long): Usuario {
        return repository.findById(id).get()
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        return UserDetail(repository.findByEmail(username) ?: throw RuntimeException())
    }

}
