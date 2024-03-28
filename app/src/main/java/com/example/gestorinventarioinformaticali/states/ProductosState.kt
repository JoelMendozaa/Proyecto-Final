package com.example.gestorinventarioinformaticali.states

import com.example.gestorinventarioinformaticali.models.Productos

data class ProductosState(
    val listaProductos: List<Productos> = emptyList()
)
