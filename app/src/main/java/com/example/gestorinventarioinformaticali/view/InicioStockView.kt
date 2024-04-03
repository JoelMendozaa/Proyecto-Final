package com.example.gestorinventarioinformaticali.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gestorinventarioinformaticali.viewmodel.StockViewModel

@Composable
fun ContentInicioStockView(it: PaddingValues, navController: NavController, viewModel: StockViewModel){
    val state = viewModel.state2

    Column (
        modifier = Modifier.padding(it)
    ) {
        LazyColumn(){
            items (state.listaStock){
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ){
                    Column (
                        modifier = Modifier
                            .padding(12.dp)
                    ) {
                        Text(text = it.nombre)
                        Text(text = it.marca)
                        Text(text = it.stock)
                        IconButton(
                            onClick = { navController.navigate("editar/${it.id}/${it.nombre}/${it.marca}/${it.stock}") }
                        ) {
                            Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
                        }
                        IconButton(onClick = { viewModel.borrarStock(it) }) {
                            Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
                        }
                    }
                }
            }
        }
    }
}
