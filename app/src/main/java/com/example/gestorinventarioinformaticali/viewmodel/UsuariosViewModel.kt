package com.example.gestorinventarioinformaticali.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestorinventarioinformaticali.models.Usuarios
import com.example.gestorinventarioinformaticali.room.UsuariosDatabaseDao
import com.example.gestorinventarioinformaticali.states.UsuariosState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class UsuariosViewModel (
    private val dao: UsuariosDatabaseDao
): ViewModel(){
    var state by mutableStateOf(UsuariosState())
        private set

    init {
        viewModelScope.launch {
            dao.obtenerUsuarios().collectLatest {
                state = state.copy(
                    listaUsuarios = it
                )
            }
        }
    }

    fun  agregarUsuario(usuarios: Usuarios) = viewModelScope.launch {
        dao.agregarUsuario(usuario = usuarios)
    }

    fun actualizarUsuario(usuarios: Usuarios) = viewModelScope.launch {
        dao.actualizarUsuario(usuario = usuarios)
    }

    fun borrarUsuario(usuarios: Usuarios) = viewModelScope.launch {
        dao.borrarUsuario(usuario = usuarios)
    }
}