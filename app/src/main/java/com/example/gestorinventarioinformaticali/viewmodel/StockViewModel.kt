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
    val listaStock: Flow<List<tablaStock>> = dao.obtenerStock()
    var state by mutableStateOf(StockState())
        private set
    init {
        viewModelScope.launch {
            dao.obtenerStock().collectLatest {
                state = state.copy(
                    listaStock = it
                )
            }
        }
    }


    fun agregarStock(stock: tablaStock) = viewModelScope.launch {
        dao.agregarStock(stocks = stock)
    }

    fun actualizarStock(stock: tablaStock) = viewModelScope.launch {
        dao.actualizarStock(stocks = stock)
    }

    fun borrarStock(stock: tablaStock) = viewModelScope.launch {
        dao.borrarStock(stocks = stock)
    }
}