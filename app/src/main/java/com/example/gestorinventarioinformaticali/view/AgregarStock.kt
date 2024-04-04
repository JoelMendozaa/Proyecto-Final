package com.example.gestorinventarioinformaticali.view

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gestorinventarioinformaticali.models.tablaProductos
import com.example.gestorinventarioinformaticali.models.tablaStock
import com.example.gestorinventarioinformaticali.viewmodel.StockViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgregarStock(navController: NavController, viewModel: StockViewModel) {
    // Scaffold para la estructura básica de la pantalla
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Agregar Stock",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                // Icono de navegación de retroceso
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription = "Back")
                    }
                }
            )
        }
    ) {
        // Contenido de la pantalla para agregar stock
        ContentAgregarStock(it, navController, viewModel)
    }
}

@Composable
fun ContentAgregarStock(it: PaddingValues, navController: NavController, viewModel: StockViewModel) {
    // Estado local para el nombre, marca y stock del nuevo elemento de stock
    var nombre by remember { mutableStateOf("") }
    var marca by remember { mutableStateOf("") }
    var stock by remember { mutableStateOf("") }

    // Columna desplazable para el formulario de agregar stock
    LazyColumn(
        modifier = Modifier
            .padding(it)
            .padding(top = 30.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            // Campos de entrada para el nombre, marca y stock
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text(text = "Nombre") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .padding(bottom = 15.dp)
            )
            OutlinedTextField(
                value = marca,
                onValueChange = { marca = it },
                label = { Text(text = "Marca") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .padding(bottom = 15.dp)
            )
            OutlinedTextField(
                value = stock,
                onValueChange = { stock = it },
                label = { Text(text = "Stock") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .padding(bottom = 15.dp),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )
            // Botón para agregar el nuevo elemento de stock
            Button(onClick = {
                // Crear un nuevo objeto tablaStock con los datos ingresados
                val nuevoStock = tablaStock(nombre = nombre, marca = marca, stock = stock)
                // Llamar al método en el viewModel para agregar el stock
                viewModel.agregarStock(nuevoStock)
                // Navegar de regreso a la pantalla anterior
                navController.popBackStack()
            }) {
                Text(text = "Agregar")
            }
        }
    }
}
