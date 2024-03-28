package com.example.gestorinventarioinformaticali.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gestorinventarioinformaticali.models.Productos

@Database(
    entities = [Productos::class],
    version = 1,
    exportSchema = false
)
abstract class ProductosDatabase: RoomDatabase() {
    abstract fun productoDao(): ProductosDatabaseDao
    companion object {
        @Volatile
        private var INSTANCE: ProductosDatabase? = null

        // Método para obtener una instancia de la base de datos
        fun getInstance(context: Context): ProductosDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProductosDatabase::class.java,
                    "productos_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}