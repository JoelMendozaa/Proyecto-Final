package com.example.gestorinventarioinformaticali.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestorinventarioinformaticali.models.tablaProductos
import com.example.gestorinventarioinformaticali.room.ProductosDatabaseDao
import com.example.gestorinventarioinformaticali.states.ProductosState
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
    fun buscarProductos(query: String): Flow<List<tablaProductos>> {
        return dao.buscarProductos(query)
    }
}