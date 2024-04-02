package com.example.gestorinventarioinformaticali.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gestorinventarioinformaticali.models.tablaProductos

@Database(
    entities = [tablaProductos::class],
    version = 1,
    exportSchema = false
)
abstract class ProductosDatabase: RoomDatabase() {
    abstract fun productoDao(): ProductosDatabaseDao
    companion object {
        @Volatile
        private var INSTANCE: ProductosDatabase? = null

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
