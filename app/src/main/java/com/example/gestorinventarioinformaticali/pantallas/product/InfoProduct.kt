package com.example.gestorinventarioinformaticali.pantallas.product

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.gestorinventarioinformaticali.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar3(
    navController: NavController,
    onButtonClickedActDesc: () -> Unit,
) {
    // Configuración del comportamiento de desplazamiento para la barra superior
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    // Scaffold que contiene la barra superior
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            // Barra superior centrada con título "Producto"
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Producto",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                scrollBehavior = scrollBehavior,
            )
        }
    ) { innerPadding ->
        // Columna para contener el contenido debajo de la barra superior
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            // Elemento para mostrar la imagen del producto y el botón de edición
            item {
                Box(
                    modifier = Modifier
                        .size(height = 200.dp, width = 450.dp)
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .background(color = MaterialTheme.colorScheme.secondary)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        // Imagen del producto
                        Image(
                            painter = painterResource(id = R.drawable.procesador2),
                            contentDescription = "Ejemplo imagen",
                        )
                    }
                    // Botón de edición en la esquina superior derecha
                    Box(
                        modifier = Modifier.align(Alignment.TopEnd)
                    ){
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ){
                            IconButton(onClick = onButtonClickedActDesc) {
                                Icon(imageVector = Icons.Filled.Edit, contentDescription = "Editar")
                            }
                        }
                    }
                }
            }
            // Elemento para mostrar el nombre del producto
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "Nombre Producto")
                }
            }
        }
    }
}

@Composable
fun InfoProduct(
    onButtonClickedFuncApp: () -> Unit,
    onButtonClickedStock: () -> Unit,
    onButtonClickedHome: () -> Unit,
    onButtonClickedUser: () -> Unit,
    onButtonClickedActDesc: () -> Unit,
    navController: NavHostController,
    description: String
) {
    // Scaffold que contiene la barra superior y el contenido
    Scaffold(
        topBar = {
            // Barra superior personalizada
            TopAppBar3(
                navController = navController,
                onButtonClickedActDesc = onButtonClickedActDesc
            )
        },
        bottomBar = {
            // Barra inferior personalizada
            BottomAppBar3(
                onButtonClickedFuncApp = onButtonClickedFuncApp,
                onButtonClickedStock = onButtonClickedStock,
                onButtonClickedHome = onButtonClickedHome,
                onButtonClickedUser = onButtonClickedUser
            )
        },
    ){ innerPadding ->
        // Columna para el contenido
        LazyColumn (modifier = Modifier.padding(innerPadding)) {
            // Elemento para mostrar la descripción del producto
            item {
                MostrarTexto(text = description)
            }
        }
    }
}

@Composable
fun BottomAppBar3(
    onButtonClickedFuncApp: () -> Unit,
    onButtonClickedStock: () -> Unit,
    onButtonClickedHome: () -> Unit,
    onButtonClickedUser: () -> Unit,
) {
    // Barra inferior que contiene los botones de navegación
    Row {
        BottomAppBar(
            actions = {
                // Botón de FuncApp
                IconButton(
                    modifier = Modifier.weight(2f),
                    onClick = onButtonClickedFuncApp
                ) {
                    Icon(Icons.Default.AttachFile, contentDescription = "FuncApp")
                }
                // Botón de Stock
                IconButton(
                    modifier = Modifier.weight(2f),
                    onClick = onButtonClickedStock
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_bar_chart_24),
                        contentDescription = "Stock",
                    )
                }
                // Botón de Home
                IconButton(
                    modifier = Modifier.weight(2f),
                    onClick = onButtonClickedHome
                ) {
                    Icon(
                        Icons.Filled.Home,
                        contentDescription = "Home",
                    )
                }
                // Botón de Usuario
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