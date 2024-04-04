package com.example.gestorinventarioinformaticali.states

import com.example.gestorinventarioinformaticali.models.tablaStock

// Clase de estado que encapsula la lista de stocks
class StockState(
    val listaStock: List<tablaStock> = emptyList()
) {
    // Método para copiar el estado con una nueva lista de stocks
    fun copy(listaStock: List<tablaStock>): StockState {
        return StockState(listaStock)
    }
}