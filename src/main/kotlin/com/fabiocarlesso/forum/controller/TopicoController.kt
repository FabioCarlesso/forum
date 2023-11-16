package com.fabiocarlesso.forum.controller

import com.fabiocarlesso.forum.model.Topico
import com.fabiocarlesso.forum.service.TopicoService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/topicos")
class TopicoController(private val service: TopicoService) {
    @GetMapping
    fun listar(): List<Topico>{
        return service.listar()
    }
}