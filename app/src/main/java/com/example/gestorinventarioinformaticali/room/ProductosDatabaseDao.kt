package com.example.gestorinventarioinformaticali.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.gestorinventarioinformaticali.models.Productos
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductosDatabaseDao {

    @Query("SELECT * FROM productos")
    fun obtenerProducto(): Flow<List<Productos>>

    @Query("SELECT * FROM productos WHERE id = :id")
    fun obtenerProducto(id: Int): Flow<Productos>

    @Insert
    suspend fun agregarProducto(productos: Productos)

    @Update
    suspend fun actualizarProducto(productos: Productos)

    @Delete
    suspend fun borrarProducto(productos: Productos)
}