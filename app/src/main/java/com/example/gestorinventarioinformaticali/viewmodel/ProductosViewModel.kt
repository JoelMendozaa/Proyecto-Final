package com.example.gestorinventarioinformaticali.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestorinventarioinformaticali.models.tablaCategoria
import com.example.gestorinventarioinformaticali.models.tablaProductos
import com.example.gestorinventarioinformaticali.models.tablaStock
import com.example.gestorinventarioinformaticali.room.ProductosDatabaseDao
import com.example.gestorinventarioinformaticali.states.CategoriaState
import com.example.gestorinventarioinformaticali.states.ProductosState
import com.example.gestorinventarioinformaticali.states.StockState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch



class ProductosViewModel (private val dao: ProductosDatabaseDao): ViewModel(){
    val listaProductos: Flow<List<tablaProductos>> = dao.obtenerProducto()
    var state by mutableStateOf(ProductosState())
        private set

    init {
        viewModelScope.launch {
            dao.obtenerProducto().collectLatest {
                state = state.copy(
                    listaProductos = it
                )
            }
        }
    }
    fun  agregarProducto(productos: tablaProductos) = viewModelScope.launch {
        dao.agregarProducto(productos = productos)
    }
    fun actualizarProducto(productos: tablaProductos) = viewModelScope.launch {
        dao.actualizarProducto(productos = productos)
    }
    fun borrarProducto(productos: tablaProductos) = viewModelScope.launch {
        dao.borrarProducto(productos = productos)
    }

    val listaCategoria: Flow<List<tablaCategoria>> = dao.obtenerCategoria()
    var state2 by mutableStateOf(CategoriaState())
        private set

    init {
        viewModelScope.launch {
            dao.obtenerCategoria().collectLatest {
                state2 = state2.copy(
                    listaCategoria = it
                )
            }
        }
    }



    val listaStock: Flow<List<tablaStock>> = dao.obtenerStock()
    var state3 by mutableStateOf(StockState())
        private set
    init {
        viewModelScope.launch {
            dao.obtenerStock().collectLatest {
                state3 = state3.copy(
                    listaStock = it
                )
            }
        }
    }


}