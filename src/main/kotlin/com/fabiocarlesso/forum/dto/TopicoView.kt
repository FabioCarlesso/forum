package com.fabiocarlesso.forum.dto

import com.fabiocarlesso.forum.model.StatusTopico
import java.time.LocalDateTime

data class TopicoView (
    val id: Long?,
    val titulo: String,
    val mensagem: String,
    val status: StatusTopico,
    val dataCriacao: LocalDateTime,
    val dataAlteracao: LocalDateTime?
)
