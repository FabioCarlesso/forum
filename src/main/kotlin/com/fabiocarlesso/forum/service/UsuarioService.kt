package com.fabiocarlesso.forum.service

import com.fabiocarlesso.forum.model.Usuario
import com.fabiocarlesso.forum.repository.UsuarioRepository
import org.springframework.stereotype.Service

@Service
class UsuarioService (private val repository: UsuarioRepository) {
    fun buscarPorId(id: Long): Usuario {
        return repository.findById(id).get()
    }

}
