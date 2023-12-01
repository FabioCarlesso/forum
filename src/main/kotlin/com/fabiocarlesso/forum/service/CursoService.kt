package com.fabiocarlesso.forum.service

import com.fabiocarlesso.forum.model.Curso
import com.fabiocarlesso.forum.repository.CursoRepository
import org.springframework.stereotype.Service

@Service
class CursoService (private val repository: CursoRepository) {
    fun buscarPorId(id: Long): Curso{
        return repository.findById(id).get()
    }
}
