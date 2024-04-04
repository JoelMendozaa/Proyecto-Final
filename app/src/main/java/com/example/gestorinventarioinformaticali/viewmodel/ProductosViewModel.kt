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

class ProductosViewModel(private val dao: ProductosDatabaseDao) : ViewModel() {
    // Flujo de lista de productos obtenidos de la base de datos
    val listaProductos: Flow<List<tablaProductos>> = dao.obtenerProducto()

    // Estado mutable para mantener el estado de la lista de productos
    var state by mutableStateOf(ProductosState())
        private set

    // Inicialización del ViewModel
    init {
        viewModelScope.launch {
            // Observar el flujo de lista de productos y actualizar el estado cuando cambie
            dao.obtenerProducto().collectLatest { productos ->
                state = state.copy(
                    listaProductos = productos // Actualizar la lista de productos en el estado
                )
            }
        }
    }

    // Función para agregar un nuevo producto a la base de datos
    fun agregarProducto(producto: tablaProductos) = viewModelScope.launch {
        dao.agregarProducto(productos = producto)
    }

    // Función para actualizar un producto existente en la base de datos
    fun actualizarProducto(producto: tablaProductos) = viewModelScope.launch {
        dao.actualizarProducto(productos = producto)
    }

    // Función para eliminar un producto de la base de datos
    fun borrarProducto(producto: tablaProductos) = viewModelScope.launch {
        dao.borrarProducto(productos = producto)
    }

    // Función para buscar productos en la base de datos según una consulta dada
    fun buscarProductos(query: String): Flow<List<tablaProductos>> {
        return dao.buscarProductos(query)
    }
}