package com.fabiocarlesso.forum.service

import com.fabiocarlesso.forum.mapper.TopicoFormMapper
import com.fabiocarlesso.forum.mapper.TopicoViewMapper
import com.fabiocarlesso.forum.model.TopicoTest
import com.fabiocarlesso.forum.model.TopicoViewTest
import com.fabiocarlesso.forum.repository.TopicoRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable

class TopicoServiceTest {
    private val topicos = PageImpl(listOf(TopicoTest.build()))
    private val paginacao: Pageable = mockk()
    private val topicoRepository: TopicoRepository = mockk{
        every { findByCursoNome(any(), any()) } returns topicos
        every { findAll(paginacao) } returns topicos
    }
    private val topicoViewMapper: TopicoViewMapper = mockk(){
        every { map(any()) } returns TopicoViewTest.build()
    }
    private val topicoFormMapper: TopicoFormMapper = mockk()
    private val topicoService = TopicoService(
        topicoRepository, topicoViewMapper, topicoFormMapper
    )
    @Test
    fun `deve listar a partir do nome do curso`() {
        topicoService.listar("Kotlin avançado", paginacao)
        verify (exactly = 1) { topicoRepository.findByCursoNome(any(), any()) }
        verify (exactly = 1) { topicoViewMapper.map(any()) }
        verify (exactly = 0) { topicoRepository.findAll(paginacao) }
    }
    @Test
    fun `deve listar todos os topicos quando o nome do curso for nulo`() {
        topicoService.listar(null, paginacao)
        verify (exactly = 0) { topicoRepository.findByCursoNome(any(), any()) }
        verify (exactly = 1) { topicoViewMapper.map(any()) }
        verify (exactly = 1) { topicoRepository.findAll(paginacao) }
    }
}