package com.fabiocarlesso.forum.mapper

import com.fabiocarlesso.forum.dto.NovoTopicoForm
import com.fabiocarlesso.forum.model.Topico
import com.fabiocarlesso.forum.service.CursoService
import com.fabiocarlesso.forum.service.UsuarioService
import org.springframework.stereotype.Component

@Component
class TopicoFormMapper(
        private val cursoService: CursoService,
        private val usuarioService: UsuarioService
): Mapper<NovoTopicoForm, Topico> {
    override fun map(t: NovoTopicoForm): Topico {
        return Topico(
            titulo = t.titulo,
            mensagem = t.mensagem,
            curso = cursoService.buscarPorId(t.idCurso),
            autor = usuarioService.buscarPorId(t.idAutor),
        )
    }
}
