package com.example.gestorinventarioinformaticali.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.gestorinventarioinformaticali.models.tablaCategoria
import com.example.gestorinventarioinformaticali.models.tablaProductos
import com.example.gestorinventarioinformaticali.models.tablaStock
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductosDatabaseDao {
    @Query("SELECT * FROM productos")
    fun obtenerProducto(): Flow<List<tablaProductos>>

    @Query("SELECT * FROM productos WHERE id = :id")
    fun obtenerProducto(id: Int): Flow<tablaProductos>

    @Insert
    suspend fun agregarProducto(productos: tablaProductos)

    @Update
    suspend fun actualizarProducto(productos: tablaProductos)

    @Delete
    suspend fun borrarProducto(productos: tablaProductos)

    // ---- Categoria ----

    @Query("SELECT * FROM categoria")
    fun obtenerCategoria(): Flow<List<tablaCategoria>>

    @Query("SELECT * FROM categoria WHERE id = :id")
    fun obtenerCategoria(id: Int): Flow<tablaCategoria>


    // ---- Stock ----

    @Query("SELECT * FROM stock")
    fun obtenerStock(): Flow<List<tablaStock>>

    @Query("SELECT * FROM stock WHERE id = :id")
    fun obtenerStock(id: Int): Flow<tablaStock>

    @Insert
    suspend fun agregarStock(stock: tablaStock)

    @Update
    suspend fun actualizarStock(stock: tablaStock)

    @Delete
    suspend fun borrarStock(stock: tablaStock)
}