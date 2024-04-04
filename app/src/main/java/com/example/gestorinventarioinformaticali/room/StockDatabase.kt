package com.example.gestorinventarioinformaticali.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gestorinventarioinformaticali.models.tablaStock

// Anotación que marca la clase como una base de datos Room, especificando las entidades que contiene, la versión y que no exporta el esquema
@Database(
    entities = [tablaStock::class], // Lista de entidades asociadas con esta base de datos
    version = 1, // Versión de la base de datos
    exportSchema = false // Indicación de que no se exportará el esquema de la base de datos
)
abstract class StockDatabase : RoomDatabase() {

    // Método abstracto que proporciona acceso al DAO (Data Access Object) para interactuar con la base de datos
    abstract fun stockDao(): StockDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: StockDatabase? = null

        // Método estático que devuelve una instancia única de la base de datos utilizando el patrón Singleton
        fun getInstance(context: Context): StockDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StockDatabase::class.java,
                    "stock_database" // Nombre de la base de datos
                )
                    .fallbackToDestructiveMigration() // Indica que se realizará una migración destructiva en caso de cambios en la versión de la base de datos
                    .build()
                INSTANCE = instance // Asigna la instancia creada a la variable INSTANCE
                instance // Devuelve la instancia de la base de datos
            }
        }
    }
}
