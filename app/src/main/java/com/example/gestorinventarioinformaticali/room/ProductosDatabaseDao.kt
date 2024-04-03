package com.example.gestorinventarioinformaticali.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
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

    @Query("SELECT * FROM productos WHERE nombre LIKE '%' || :query || '%' OR marca LIKE '%' || :query || '%'")
    fun buscarProductos(query: String): Flow<List<tablaProductos>>

}

