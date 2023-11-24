package com.fabiocarlesso.forum.service

import com.fabiocarlesso.forum.dto.AtualizacaoTopicoForm
import com.fabiocarlesso.forum.dto.NovoTopicoForm
import com.fabiocarlesso.forum.dto.TopicoView
import com.fabiocarlesso.forum.mapper.TopicoFormMapper
import com.fabiocarlesso.forum.mapper.TopicoViewMapper
import com.fabiocarlesso.forum.model.Curso
import com.fabiocarlesso.forum.model.Topico
import com.fabiocarlesso.forum.model.Usuario
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class TopicoService(
    private var topicos: List<Topico> = ArrayList(),
    private val topicoViewMapper: TopicoViewMapper,
    private val topicoFormMapper: TopicoFormMapper
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
            t -> topicoViewMapper.map(t)
        }.collect(Collectors.toList())
    }

    fun buscarPorId(id: Long): TopicoView {
        val topico = findTopicoById(id)
        return topicoViewMapper.map(topico)
    }

    private fun findTopicoById(id: Long) = topicos.stream()
        .filter { t ->
            t.id == id
        }
        .findFirst()
        .get()

    fun cadastrar(topico: NovoTopicoForm) {
        val novoTopicoNaLista = topicoFormMapper.map(topico)
        novoTopicoNaLista.id = topicos.size.toLong() + 1
        topicos = topicos.plus(novoTopicoNaLista)
    }

    fun atualizar(topico: AtualizacaoTopicoForm) {
        val topicoEncontrado = findTopicoById(topico.id)
        topicos = topicos.minus(topicoEncontrado).plus(Topico(
            id = topico.id,
            titulo = topico.titulo,
            mensagem = topico.mensagem,
            autor = topicoEncontrado.autor,
            curso = topicoEncontrado.curso,
            respostas = topicoEncontrado.respostas,
            status = topicoEncontrado.status,
            dataCriacao = topicoEncontrado.dataCriacao
        ))
    }

    fun deletar(id: Long) {
        val topicoEncontrado = findTopicoById(id)
        topicos = topicos.minus(topicoEncontrado)
    }
}