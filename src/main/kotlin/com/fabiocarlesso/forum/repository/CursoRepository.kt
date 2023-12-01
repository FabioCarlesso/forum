package com.fabiocarlesso.forum.repository

import com.fabiocarlesso.forum.model.Curso
import org.springframework.data.jpa.repository.JpaRepository

interface CursoRepository: JpaRepository<Curso, Long> {
}