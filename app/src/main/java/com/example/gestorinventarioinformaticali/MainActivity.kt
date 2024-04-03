package com.example.gestorinventarioinformaticali

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.room.Room
import com.example.gestorinventarioinformaticali.navigation.MyApp
import com.example.gestorinventarioinformaticali.room.ProductosDatabase
import com.example.gestorinventarioinformaticali.room.StockDatabase
import com.example.gestorinventarioinformaticali.ui.theme.GestorInventarioInformaticaLITheme
import com.example.gestorinventarioinformaticali.viewmodel.ProductosViewModel
import com.example.gestorinventarioinformaticali.viewmodel.StockViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GestorInventarioInformaticaLITheme {
                val database = Room.databaseBuilder(this, ProductosDatabase::class.java, "db_productos").build()
                val database2 = Room.databaseBuilder( this, StockDatabase::class.java, "db_stock").build()
                val dao = database.productoDao()
                val dao2 = database2.stockDao()
                val viewModel = ProductosViewModel(dao)
                val viewModel2 = StockViewModel(dao2)
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp(viewModel = viewModel, viewModel2 = viewModel2)
                }
            }
        }
    }
}
