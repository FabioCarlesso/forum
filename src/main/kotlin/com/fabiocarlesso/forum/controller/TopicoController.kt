package com.fabiocarlesso.forum.controller

import com.fabiocarlesso.forum.dto.AtualizacaoTopicoForm
import com.fabiocarlesso.forum.dto.NovoTopicoForm
import com.fabiocarlesso.forum.dto.TopicoView
import com.fabiocarlesso.forum.service.TopicoService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

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
    fun cadastrar(
        @RequestBody @Valid topico: NovoTopicoForm,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<TopicoView> {
        val topicoView = service.cadastrar(topico)
        val uri = uriBuilder.path("/topicos/${topicoView.id}").build().toUri()
        return ResponseEntity.created(uri).body(topicoView)
    }
    @PutMapping
    fun atualizar(@RequestBody @Valid topico: AtualizacaoTopicoForm): ResponseEntity<TopicoView>{
        val topicoView = service.atualizar(topico)
        return ResponseEntity.ok(topicoView)
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletar(@PathVariable id: Long){
        return service.deletar(id)
    }
}