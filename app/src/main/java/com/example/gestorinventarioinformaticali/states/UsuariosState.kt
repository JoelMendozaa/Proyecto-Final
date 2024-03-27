package com.example.gestorinventarioinformaticali.states

import com.example.gestorinventarioinformaticali.models.Usuarios

data class UsuariosState(
    val listaUsuarios: List<Usuarios> = emptyList()
)
