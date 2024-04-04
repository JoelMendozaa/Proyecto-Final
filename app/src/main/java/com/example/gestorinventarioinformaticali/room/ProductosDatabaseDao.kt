package com.example.gestorinventarioinformaticali.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.gestorinventarioinformaticali.models.tablaProductos
import com.example.gestorinventarioinformaticali.models.tablaStock
import kotlinx.coroutines.flow.Flow

// Interfaz DAO (Data Access Object) que define operaciones para acceder y manipular la base de datos de productos
@Dao
interface ProductosDatabaseDao {

    // Consulta para obtener todos los productos y emitirlos como un flujo de datos
    @Query("SELECT * FROM productos")
    fun obtenerProducto(): Flow<List<tablaProductos>>

    // Consulta para obtener un producto por su ID y emitirlo como un flujo de datos
    @Query("SELECT * FROM productos WHERE id = :id")
    fun obtenerProducto(id: Int): Flow<tablaProductos>

    // Método para insertar un nuevo producto en la base de datos
    @Insert
    suspend fun agregarProducto(productos: tablaProductos)

    // Método para actualizar un producto existente en la base de datos
    @Update
    suspend fun actualizarProducto(productos: tablaProductos)

    // Método para eliminar un producto de la base de datos
    @Delete
    suspend fun borrarProducto(productos: tablaProductos)

    // Consulta para buscar productos por nombre o marca y emitir los resultados como un flujo de datos
    @Query("SELECT * FROM productos WHERE nombre LIKE '%' || :query || '%' OR marca LIKE '%' || :query || '%'")
    fun buscarProductos(query: String): Flow<List<tablaProductos>>

}


