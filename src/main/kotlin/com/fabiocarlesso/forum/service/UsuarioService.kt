package com.fabiocarlesso.forum.service

import com.fabiocarlesso.forum.model.Usuario
import org.springframework.stereotype.Service
import java.util.*

@Service
class UsuarioService (private var autores: List<Usuario>) {
    init {
        val autor = Usuario (
            id = 1,
            nome = "Joao da Silva",
            email = "joaosilva@email.com"
        )
        autores = Arrays.asList(autor)
    }
    fun buscarPorId(id: Long): Usuario {
        return autores.stream()
            .filter { a ->
                a.id == id
            }
            .findFirst()
            .get()
    }

}
