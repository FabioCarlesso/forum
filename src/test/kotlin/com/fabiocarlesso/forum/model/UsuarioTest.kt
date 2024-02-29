package com.fabiocarlesso.forum.model

object UsuarioTest {
    fun build() = Usuario (
        id = 1,
        nome = "Joao",
        email = "email@mail.com",
        password = "123456",
        role = listOf()
    )
}