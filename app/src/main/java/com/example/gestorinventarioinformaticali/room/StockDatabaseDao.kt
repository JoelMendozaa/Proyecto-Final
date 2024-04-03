package com.example.gestorinventarioinformaticali.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.gestorinventarioinformaticali.models.tablaStock
import kotlinx.coroutines.flow.Flow

@Dao
interface StockDatabaseDao{
    @Query("SELECT * FROM stocks")
    fun obtenerStock(): Flow<List<tablaStock>>

    @Query("SELECT * FROM stocks WHERE id = :id")
    fun obtenerStock(id: Int): Flow<tablaStock>

    @Insert
    suspend fun agregarStock(stocks: tablaStock)

    @Update
    suspend fun actualizarStock(stocks: tablaStock)

    @Delete
    suspend fun borrarStock(stocks: tablaStock)
}