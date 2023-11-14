package com.fabiocarlesso.forum.controller

import com.fabiocarlesso.forum.model.Curso
import com.fabiocarlesso.forum.model.Topico
import com.fabiocarlesso.forum.model.Usuario
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/topicos")
class TopicoController {
    @GetMapping
    fun listar(): List<Topico>{
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