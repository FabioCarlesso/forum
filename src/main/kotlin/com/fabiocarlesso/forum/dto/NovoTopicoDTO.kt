package com.fabiocarlesso.forum.dto

data class NovoTopicoDTO (
        val titulo: String,
        val mensagem: String,
        val idCurso: Long,
        val idAutor: Long
)
