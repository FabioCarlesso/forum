package com.fabiocarlesso.forum.service

import com.fabiocarlesso.forum.dto.AtualizacaoTopicoForm
import com.fabiocarlesso.forum.dto.NovoTopicoForm
import com.fabiocarlesso.forum.dto.TopicoView
import com.fabiocarlesso.forum.exception.NotFoundException
import com.fabiocarlesso.forum.mapper.TopicoFormMapper
import com.fabiocarlesso.forum.mapper.TopicoViewMapper
import com.fabiocarlesso.forum.repository.TopicoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class TopicoService(
    private val repository: TopicoRepository,
    private val topicoViewMapper: TopicoViewMapper,
    private val topicoFormMapper: TopicoFormMapper,
    private val notFoundMessage: String = "Topico nao encontrado!"
    ) {
    fun listar(
        nomeCurso: String?,
        paginacao: Pageable
    ): Page<TopicoView> {
        val topicos = if (nomeCurso == null){
            repository.findAll(paginacao)
        } else {
            repository.findByCursoNome(nomeCurso, paginacao)
        }
        return topicos.map{ t -> topicoViewMapper.map(t) }
    }
    fun buscarPorId(id: Long): TopicoView {
        val topico = repository.findById(id).orElseThrow{NotFoundException(notFoundMessage)}
        return topicoViewMapper.map(topico)
    }
    fun cadastrar(topico: NovoTopicoForm): TopicoView {
        val novoTopicoNaLista = topicoFormMapper.map(topico)
        repository.save(novoTopicoNaLista)
        return topicoViewMapper.map(novoTopicoNaLista)
    }

    fun atualizar(topico: AtualizacaoTopicoForm): TopicoView {
        val topicoEncontrado = repository.findById(topico.id).orElseThrow{NotFoundException(notFoundMessage)}
        topicoEncontrado.titulo = topico.titulo
        topicoEncontrado.mensagem = topico.mensagem
        repository.save(topicoEncontrado)
        return topicoViewMapper.map(topicoEncontrado)
    }

    fun deletar(id: Long) {
        repository.deleteById(id)
    }
}