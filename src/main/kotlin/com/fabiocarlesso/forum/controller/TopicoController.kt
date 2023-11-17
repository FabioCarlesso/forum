package com.fabiocarlesso.forum.controller

import com.fabiocarlesso.forum.dto.NovoTopicoDTO
import com.fabiocarlesso.forum.model.Topico
import com.fabiocarlesso.forum.service.TopicoService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/topicos")
class TopicoController(private val service: TopicoService) {
    @GetMapping
    fun listar(): List<Topico>{
        return service.listar()
    }
    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): Topico {
        return service.buscarPorId(id)
    }
    @PostMapping
    fun cadastrar(@RequestBody topico: NovoTopicoDTO){
        service.cadastrar(topico)
    }
}