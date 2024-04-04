package com.example.gestorinventarioinformaticali.view

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gestorinventarioinformaticali.models.tablaProductos
import com.example.gestorinventarioinformaticali.viewmodel.ProductosViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarView(
    navController: NavController,
    viewModel: ProductosViewModel,
    id: Int,
    nombre: String?,
    marca: String?,
) {
    // Scaffold para la estructura básica de la pantalla
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Editar Producto",
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
        // Contenido de la pantalla para editar el producto
        ContentEditarView(it, navController, viewModel, id, nombre, marca)
    }
}

@Composable
fun ContentEditarView(
    it: PaddingValues,
    navController: NavController,
    viewModel: ProductosViewModel,
    id: Int,
    nombre: String?,
    marca: String?,
) {
    // Estado local para el nombre y marca del producto a editar
    var nombre by remember { mutableStateOf(nombre ?: "") }
    var marca by remember { mutableStateOf(marca ?: "") }

    // Columna desplazable para el formulario de editar producto
    LazyColumn(
        modifier = Modifier
            .padding(it)
            .padding(top = 30.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            // Campos de entrada para el nombre y marca del producto a editar
            OutlinedTextField(
                value = nombre ?: "",
                onValueChange = { nombre = it },
                label = { Text(text = "Nombre") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .padding(bottom = 15.dp)
            )
            OutlinedTextField(
                value = marca ?: "",
                onValueChange = { marca = it },
                label = { Text(text = "Marca") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .padding(bottom = 15.dp)
            )
            // Botón para editar el producto
            Button(onClick = {
                // Crear un nuevo objeto tablaProductos con los datos editados
                val productoEditado = tablaProductos(id = id, nombre = nombre!!, marca = marca!!)
                // Llamar al método en el viewModel para actualizar el producto
                viewModel.actualizarProducto(productoEditado)
                // Navegar de regreso a la pantalla anterior
                navController.popBackStack()
            }) {
                Text(text = "Editar")
            }
        }
    }
}
