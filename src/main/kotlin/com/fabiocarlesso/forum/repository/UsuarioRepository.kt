package com.fabiocarlesso.forum.repository

import com.fabiocarlesso.forum.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository

interface UsuarioRepository: JpaRepository<Usuario, Long> {
}