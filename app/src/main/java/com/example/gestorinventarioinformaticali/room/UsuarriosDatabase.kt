package com.example.gestorinventarioinformaticali.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gestorinventarioinformaticali.models.Usuarios

@Database(
    entities = [Usuarios::class],
    version = 1,
    exportSchema = false
)
abstract class UsuarriosDatabase: RoomDatabase() {
    abstract fun usuarioDao(): UsuariosDatabaseDao
}