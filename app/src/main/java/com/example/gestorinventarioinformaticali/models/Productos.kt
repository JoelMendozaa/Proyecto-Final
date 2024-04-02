package com.example.gestorinventarioinformaticali.models

import androidx.room.ColumnInfo
import  androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "productos")
data class tablaProductos(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo("Nombre")
    val nombre: String,
    @ColumnInfo("Marca")
    val marca: String
)

@Entity(tableName = "categoria")
data class tablaCategoria(
    @PrimaryKey (autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo("Nombre")
    val nombre: String
)

@Entity(tableName = "stock")
data class tablaStock(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo("Nombre")
    val nombre: String,
    @ColumnInfo("Stock")
    val stock: Int
)