package com.example.gestorinventarioinformaticali.states

import com.example.gestorinventarioinformaticali.models.tablaProductos

data class ProductosState(
    val listaProductos: List<tablaProductos> = emptyList()
)