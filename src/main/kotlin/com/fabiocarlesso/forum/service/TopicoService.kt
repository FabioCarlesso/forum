package com.fabiocarlesso.forum.service

import com.fabiocarlesso.forum.dto.NovoTopicoDTO
import com.fabiocarlesso.forum.model.Curso
import com.fabiocarlesso.forum.model.Topico
import com.fabiocarlesso.forum.model.Usuario
import org.springframework.stereotype.Service

@Service
class TopicoService(
    private var topicos: List<Topico> = ArrayList(),
    private var cursoService: CursoService,
    private var usuarioService: UsuarioService
    ) {
    init{
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
        topicos = listOf(topico)
    }
    fun listar(): List<Topico> {
        return topicos
    }

    fun buscarPorId(id: Long): Topico {
        return topicos.stream()
            .filter { t ->
                t.id == id
            }
            .findFirst()
            .get()
    }

    fun cadastrar(topico: NovoTopicoDTO) {
        topicos = topicos.plus(Topico(
                        id = topicos.size.toLong() + 1,
                        titulo = topico.titulo,
                        mensagem = topico.mensagem,
                        curso = cursoService.buscarPorId(topico.idCurso),
                        autor = usuarioService.buscarPorId(topico.idAutor),
                    )
        )
    }
}