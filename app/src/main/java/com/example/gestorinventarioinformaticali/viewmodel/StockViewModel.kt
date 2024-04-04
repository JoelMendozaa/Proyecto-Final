package com.example.gestorinventarioinformaticali.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestorinventarioinformaticali.models.tablaStock
import com.example.gestorinventarioinformaticali.room.StockDatabaseDao
import com.example.gestorinventarioinformaticali.states.StockState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class StockViewModel(private val dao: StockDatabaseDao): ViewModel() {
    // Flujo de lista de stock obtenido de la base de datos
    val listaStock: Flow<List<tablaStock>> = dao.obtenerStock()

    // Estado mutable para mantener el estado de la lista de stock
    var state by mutableStateOf(StockState())
        private set

    // Inicialización del ViewModel
    init {
        viewModelScope.launch {
            // Observar el flujo de lista de stock y actualizar el estado cuando cambie
            dao.obtenerStock().collectLatest { stock ->
                state = state.copy(
                    listaStock = stock // Actualizar la lista de stock en el estado
                )
            }
        }
    }

    // Función para agregar un nuevo elemento de stock a la base de datos
    fun agregarStock(stock: tablaStock) = viewModelScope.launch {
        dao.agregarStock(stocks = stock)
    }

    // Función para actualizar un elemento de stock existente en la base de datos
    fun actualizarStock(stock: tablaStock) = viewModelScope.launch {
        dao.actualizarStock(stocks = stock)
    }

    // Función para eliminar un elemento de stock de la base de datos
    fun borrarStock(stock: tablaStock) = viewModelScope.launch {
        dao.borrarStock(stocks = stock)
    }
}
