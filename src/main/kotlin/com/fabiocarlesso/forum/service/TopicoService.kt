package com.fabiocarlesso.forum.service

import com.fabiocarlesso.forum.dto.NovoTopicoForm
import com.fabiocarlesso.forum.dto.TopicoView
import com.fabiocarlesso.forum.model.Curso
import com.fabiocarlesso.forum.model.Topico
import com.fabiocarlesso.forum.model.Usuario
import org.springframework.stereotype.Service
import java.util.stream.Collectors

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
    fun listar(): List<TopicoView> {
        return topicos.stream().map{
            t -> TopicoView(
                id = t.id,
                titulo = t.titulo,
                mensagem = t.mensagem,
                dataCriacao = t.dataCriacao,
                status = t.status
            )
        }.collect(Collectors.toList())
    }

    fun buscarPorId(id: Long): TopicoView {
        val topico = topicos.stream()
            .filter { t ->
                t.id == id
            }
            .findFirst()
            .get()
        return TopicoView(
            id = topico.id,
            titulo = topico.titulo,
            mensagem = topico.mensagem,
            dataCriacao = topico.dataCriacao,
            status = topico.status
        )
    }

    fun cadastrar(topico: NovoTopicoForm) {
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