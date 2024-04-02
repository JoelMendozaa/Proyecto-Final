package com.example.gestorinventarioinformaticali.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gestorinventarioinformaticali.models.tablaCategoria
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

@Database(
    entities = [tablaCategoria::class],
    version = 1,
    exportSchema = false
)
abstract class CategoriaDatabase: RoomDatabase(){
    abstract fun categoriaDao(): ProductosDatabaseDao
    companion object
    @Volatile
    private var INSTANCE2: CategoriaDatabase? = null

    fun getInstance2(context: Context): CategoriaDatabase {
        return INSTANCE2 ?: synchronized(this) {
            val instance2 = Room.databaseBuilder(
                context.applicationContext,
                CategoriaDatabase::class.java,
                "categoria_database"
            )
                .fallbackToDestructiveMigration()
                .build()
            INSTANCE2 = instance2
            instance2
        }
    }


@Database(
    entities = [tablaCategoria::class],
    version = 1,
    exportSchema = false
)
abstract class StockDatabase: RoomDatabase(){
    abstract fun stockDao(): ProductosDatabaseDao
    companion object
    @Volatile
    private var INSTANCE3: StockDatabase? = null

    fun getInstance3(context: Context): StockDatabase {
        return INSTANCE3 ?: synchronized(this) {
            val instance3 = Room.databaseBuilder(
                context.applicationContext,
                StockDatabase::class.java,
                "stock_database"
            )
                .fallbackToDestructiveMigration()
                .build()
            INSTANCE3 = instance3
            instance3
        }
    }

}