package com.example.gestorinventarioinformaticali.states

import com.example.gestorinventarioinformaticali.models.tablaProductos

// Clase de estado que encapsula la lista de productos
data class ProductosState(
    val listaProductos: List<tablaProductos> = emptyList()
)
