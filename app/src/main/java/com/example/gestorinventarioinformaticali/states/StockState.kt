package com.example.gestorinventarioinformaticali.states

import com.example.gestorinventarioinformaticali.models.tablaStock

class StockState (
    val listaStock: List<tablaStock> = emptyList()
){
    fun copy(listaStock: List<tablaStock>): StockState {
        return StockState(listaStock)
    }
}
