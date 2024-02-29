package com.fabiocarlesso.forum.service

import com.fabiocarlesso.forum.mapper.TopicoFormMapper
import com.fabiocarlesso.forum.mapper.TopicoViewMapper
import com.fabiocarlesso.forum.model.TopicoTest
import com.fabiocarlesso.forum.repository.TopicoRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

import org.springframework.data.domain.PageImpl

class TopicoServiceTest {
    val topicos = PageImpl(listOf(TopicoTest.build()))
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
    fun listar() {
    }
}