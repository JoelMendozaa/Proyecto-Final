package com.example.gestorinventarioinformaticali.states

import com.example.gestorinventarioinformaticali.models.tablaCategoria
import com.example.gestorinventarioinformaticali.models.tablaProductos
import com.example.gestorinventarioinformaticali.models.tablaStock

data class ProductosState(
    val listaProductos: List<tablaProductos> = emptyList()
)

data class StockState(
    val listaStock: List<tablaStock> = emptyList()
)

data class CategoriaState(
    val listaCategoria: List<tablaCategoria> = emptyList()
)