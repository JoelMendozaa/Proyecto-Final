package com.example.gestorinventarioinformaticali.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.gestorinventarioinformaticali.models.tablaStock
import kotlinx.coroutines.flow.Flow

// Interfaz DAO (Data Access Object) que define las operaciones de acceso a la base de datos para la entidad "tablaStock"
@Dao
interface StockDatabaseDao {

    // Consulta para obtener todos los registros de la tabla "stocks" como un flujo de lista de objetos "tablaStock"
    @Query("SELECT * FROM stocks")
    fun obtenerStock(): Flow<List<tablaStock>>

    // Consulta para obtener un registro específico de la tabla "stocks" según su ID como un flujo de objeto "tablaStock"
    @Query("SELECT * FROM stocks WHERE id = :id")
    fun obtenerStock(id: Int): Flow<tablaStock>

    // Método para insertar un nuevo registro en la tabla "stocks"
    @Insert
    suspend fun agregarStock(stocks: tablaStock)

    // Método para actualizar un registro existente en la tabla "stocks"
    @Update
    suspend fun actualizarStock(stocks: tablaStock)

    // Método para eliminar un registro de la tabla "stocks"
    @Delete
    suspend fun borrarStock(stocks: tablaStock)
}
