package com.example.gestorinventarioinformaticali.models

import androidx.room.ColumnInfo
import  androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
data class Usuarios(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo("Usuario")
    val nomApels: String,
    @ColumnInfo("Email")
    val email: String,
    @ColumnInfo("Telefono")
    val telefono: String
)

