package com.fabiocarlesso.forum.controller

import com.fabiocarlesso.forum.dto.AtualizacaoTopicoForm
import com.fabiocarlesso.forum.dto.NovoTopicoForm
import com.fabiocarlesso.forum.dto.TopicoView
import com.fabiocarlesso.forum.service.TopicoService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/topicos")
class TopicoController(private val service: TopicoService) {
    @GetMapping
    fun listar(): List<TopicoView>{
        return service.listar()
    }
    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): TopicoView {
        return service.buscarPorId(id)
    }
    @PostMapping
    fun cadastrar(@RequestBody @Valid topico: NovoTopicoForm){
        service.cadastrar(topico)
    }
    @PutMapping
    fun atualizar(@RequestBody @Valid topico: AtualizacaoTopicoForm){
        service.atualizar(topico)
    }
}