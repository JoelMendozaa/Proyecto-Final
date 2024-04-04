package com.example.gestorinventarioinformaticali.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gestorinventarioinformaticali.models.tablaProductos

// Anotación que marca la clase como una base de datos de Room
@Database(
    entities = [tablaProductos::class], // Define las entidades de la base de datos
    version = 1, // Versión de la base de datos
    exportSchema = false // Indica si exportar el esquema de la base de datos
)
// Clase abstracta que representa la base de datos de productos
abstract class ProductosDatabase: RoomDatabase() {

    // Método abstracto que proporciona acceso al DAO de productos
    abstract fun productoDao(): ProductosDatabaseDao

    companion object {
        // Variable estática que contiene una referencia a la instancia única de la base de datos
        @Volatile
        private var INSTANCE: ProductosDatabase? = null

        // Método estático para obtener una instancia única de la base de datos
        fun getInstance(context: Context): ProductosDatabase {
            // Verifica si la instancia ya está creada
            return INSTANCE ?: synchronized(this) {
                // Crea una nueva instancia de la base de datos si aún no existe
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProductosDatabase::class.java,
                    "productos_database"
                )
                    .fallbackToDestructiveMigration() // Permite la migración destructiva en caso de cambios de versión
                    .build()
                INSTANCE = instance // Asigna la instancia a la variable estática
                instance // Devuelve la instancia
            }
        }
    }
}
