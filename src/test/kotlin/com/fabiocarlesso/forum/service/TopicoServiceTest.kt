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
    val topicos = PageImpl(listOf(TopicoTest.build()))
    val paginacao: Pageable = mockk()
    val topicoRepository: TopicoRepository = mockk{
        every {
            findByCursoNome(any(), any())
        } returns topicos
    }
    val topicoViewMapper: TopicoViewMapper = mockk()
    val topicoFormMapper: TopicoFormMapper = mockk()
    val topicoService = TopicoService(
        topicoRepository, topicoViewMapper, topicoFormMapper
    )
    @Test
    fun `deve listar a partir do nome do curso`() {
        every { topicoViewMapper.map(any()) } returns TopicoViewTest.build()
        topicoService.listar("Kotlin avançado", paginacao)
        verify (exactly = 1) { topicoRepository.findByCursoNome(any(), any()) }
        verify (exactly = 1) { topicoViewMapper.map(any()) }
        verify (exactly = 0) { topicoRepository.findAll(paginacao) }
    }
}