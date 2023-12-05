package com.fabiocarlesso.forum.repository

import com.fabiocarlesso.forum.model.Topico
import org.springframework.data.jpa.repository.JpaRepository

interface TopicoRepository: JpaRepository<Topico, Long> {
    fun findByCursoNome(nomeCurso: String): List<Topico>
}