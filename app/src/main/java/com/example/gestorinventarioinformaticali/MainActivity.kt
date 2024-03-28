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
import com.example.gestorinventarioinformaticali.ui.theme.GestorInventarioInformaticaLITheme
import com.example.gestorinventarioinformaticali.viewmodel.ProductosViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GestorInventarioInformaticaLITheme {
                // A surface container using the 'background' color from the theme
                val database = Room.databaseBuilder(this, ProductosDatabase::class.java, "db_usuarios").build()
                val dao = database.productoDao()
                val viewModel = ProductosViewModel(dao)
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp(viewModel = viewModel)
                }
            }
        }
    }
}
