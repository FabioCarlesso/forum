package com.fabiocarlesso.forum.service

import com.fabiocarlesso.forum.model.Curso
import com.fabiocarlesso.forum.model.Topico
import com.fabiocarlesso.forum.model.Usuario
import org.springframework.stereotype.Service

@Service
class TopicoService {
    fun listar(): List<Topico> {
        val topico = Topico(
            id = 1,
            titulo = "Duvida Kotlin",
            mensagem = "Topico em kotlin",
            curso = Curso (
                id = 1,
                nome = "Kotlin",
                categoria = "Programacao"
            ),
            autor = Usuario(
                id = 1,
                nome = "Joao da Silva",
                email = "joaosilva@email.com"
            )
        )
        return listOf(topico, topico, topico)
    }
}