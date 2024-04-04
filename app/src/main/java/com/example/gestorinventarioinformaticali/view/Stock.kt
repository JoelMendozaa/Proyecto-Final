package com.example.gestorinventarioinformaticali.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Card
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gestorinventarioinformaticali.R
import com.example.gestorinventarioinformaticali.models.tablaStock
import com.example.gestorinventarioinformaticali.viewmodel.StockViewModel
import kotlinx.coroutines.flow.Flow


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Stock(
    // Lambdas para manejar eventos de botones
    onButtonClickedFuncApp: () -> Unit,
    onButtonClickedStock: () -> Unit,
    onButtonClickedHome: () -> Unit,
    onButtonClickedUser: () -> Unit,
    // ViewModel para manejar la lógica de la vista de stock
    viewModel: StockViewModel,
    // Controlador de navegación para la aplicación
    navController: NavController
) {
    // Configuración del comportamiento de desplazamiento del AppBar
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    // Composición de la pantalla utilizando Scaffold
    Scaffold(
        topBar = {
            // AppBar centrado con título "Stock"
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Stock",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                scrollBehavior = scrollBehavior,
            )
        },
        bottomBar = {
            // BottomAppBar que contiene botones de acción
            BottomAppBar9(
                onButtonClickedFuncApp = onButtonClickedFuncApp,
                onButtonClickedStock = onButtonClickedStock,
                onButtonClickedHome = onButtonClickedHome,
                onButtonClickedUser = onButtonClickedUser
            )
        },
        floatingActionButton = {
            // Botón flotante para agregar un nuevo elemento de stock
            FloatingActionButton(
                onClick = { navController.navigate("agregar2") }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Agregar")
            }
        }
    ) {
        // Contenido principal de la vista de stock
        ContentInicioStockView(
            it,
            navController,
            viewModel
        )
    }
}

@Composable
fun ContentInicioStockView(it: PaddingValues, navController: NavController, viewModel: StockViewModel){
    // Obtiene el estado del ViewModel de Stock
    val state = viewModel.state
    Column {
        // Columna con un LazyColumn que muestra los elementos de stock
        LazyColumn {
            items (state.listaStock){
                // Tarjeta que muestra los detalles del elemento de stock
                Card(
                    modifier = Modifier
                        .padding(top = 65.dp, start = 8.dp, end = 8.dp)
                        .fillMaxWidth()
                ){
                    Column (
                        modifier = Modifier
                            .padding(12.dp)
                    ) {
                        Text(text = "Nombre: ${it.nombre}")
                        Text(text = "Marca: ${it.marca}")
                        Text(text = "Stock: ${it.stock}")
                        Row {
                            // Botón para editar el elemento de stock
                            Button(
                                onClick = { navController.navigate("editar2/${it.id}/${it.nombre}/${it.marca}/${it.stock}") }
                            ) {
                                Text(text = "Editar")
                            }
                            // Botón para eliminar el elemento de stock
                            Button(onClick = { viewModel.borrarStock(it) }) {
                                Text(text = "Eliminar")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BottomAppBar9(
    // Lambdas para manejar eventos de botones de acción
    onButtonClickedFuncApp: () -> Unit,
    onButtonClickedStock: () -> Unit,
    onButtonClickedHome: () -> Unit,
    onButtonClickedUser: () -> Unit,
) {
    // Fila que contiene los botones de acción en el BottomAppBar
    Row {
        BottomAppBar(
            actions = {
                // Icono y acción para FuncApp
                IconButton(
                    modifier = Modifier.weight(2f),
                    onClick = onButtonClickedFuncApp
                ) {
                    Icon(Icons.Default.AttachFile, contentDescription = "FuncApp")
                }
                // Icono y acción para Stock
                IconButton(
                    modifier = Modifier.weight(2f),
                    onClick = onButtonClickedStock
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_bar_chart_24),
                        contentDescription = "Stock",
                    )
                }
                // Icono y acción para Home
                IconButton(
                    modifier = Modifier.weight(2f),
                    onClick = onButtonClickedHome
                ) {
                    Icon(
                        Icons.Filled.Home,
                        contentDescription = "Home",
                    )
                }
                // Icono y acción para User
                IconButton(
                    modifier = Modifier.weight(2f),
                    onClick = onButtonClickedUser
                ) {
                    Icon(
                        Icons.Filled.AccountCircle,
                        contentDescription = "User",
                    )
                }
            }
        )
    }
}

