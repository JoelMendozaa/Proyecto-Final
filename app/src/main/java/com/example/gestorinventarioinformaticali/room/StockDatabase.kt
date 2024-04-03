package com.example.gestorinventarioinformaticali.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gestorinventarioinformaticali.models.tablaStock

@Database(
    entities = [tablaStock::class],
    version = 1,
    exportSchema = false
)


abstract class StockDatabase: RoomDatabase() {
    abstract fun stockDao(): StockDatabaseDao

    companion object{
        @Volatile
        private var INSTANCE: StockDatabase? = null

        fun getInstance(context: Context): StockDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StockDatabase::class.java,
                    "stock_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}