package com.example.gestorinventarioinformaticali.pantallas.stock


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.navigation.NavHostController
import com.example.gestorinventarioinformaticali.R
import com.example.gestorinventarioinformaticali.navigation.ScreenList
import com.example.gestorinventarioinformaticali.view.Stock
import com.example.gestorinventarioinformaticali.viewmodel.StockViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar5(navController: NavHostController) {
    // Configuración del comportamiento de desplazamiento del AppBar
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    // Composición de la pantalla utilizando Scaffold
    Scaffold(
        // Aplicación del comportamiento de desplazamiento al modifier del Scaffold
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            // AppBar centrado con título "Sección Stock"
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Sección Stock",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                scrollBehavior = scrollBehavior,
            )
        },
    ) { innerPadding ->
        // Contenido de la pantalla dentro de un Column
        Column(modifier = Modifier.padding(innerPadding)) {
            Column {
                // Filas de botones con diferentes categorías de stock
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(onClick = { navController.navigate(ScreenList.Stock.name) }) { Text(text = "PC") }
                    Spacer(modifier = Modifier.padding(2.dp))
                    Button(onClick = { navController.navigate(ScreenList.Stock.name) }) { Text(text = "GPU") }
                    Spacer(modifier = Modifier.padding(2.dp))
                    Button(onClick = { navController.navigate(ScreenList.Stock.name) }) { Text(text = "CPU") }
                }
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    Button(onClick = { navController.navigate(ScreenList.Stock.name) }) { Text(text = "PSU") }
                    Spacer(modifier = Modifier.padding(2.dp))
                    Button(onClick = { navController.navigate(ScreenList.Stock.name) }) { Text(text = "RAM") }
                    Spacer(modifier = Modifier.padding(2.dp))
                    Button(onClick = { navController.navigate(ScreenList.Stock.name) }) { Text(text = "SSD") }
                }
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    Button(onClick = { navController.navigate(ScreenList.Stock.name) }) { Text(text = "HDD") }
                    Spacer(modifier = Modifier.padding(2.dp))
                    Button(onClick = { navController.navigate(ScreenList.Stock.name) }) { Text(text = "Teclados") }
                    Spacer(modifier = Modifier.padding(2.dp))
                    Button(onClick = { navController.navigate(ScreenList.Stock.name) }) { Text(text = "Ratones") }
                }
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    Button(onClick = { navController.navigate(ScreenList.Stock.name) }) { Text(text = "Monitores") }
                    Spacer(modifier = Modifier.padding(2.dp))
                    Button(onClick = { navController.navigate(ScreenList.Stock.name) }) { Text(text = "Accesorios") }
                    Spacer(modifier = Modifier.padding(2.dp))
                    Button(onClick = { navController.navigate(ScreenList.Stock.name) }) { Text(text = "Torres") }
                }
            }
        }
    }
}

@Composable
fun SectionStock(
    // Lambdas para manejar eventos de botones de acción
    onButtonClickedFuncApp: () -> Unit,
    onButtonClickedStock: () -> Unit,
    onButtonClickedHome: () -> Unit,
    onButtonClickedUser: () -> Unit,
    // Controlador de navegación para la aplicación
    navController: NavHostController,
    // ViewModel para manejar datos relacionados con el stock
    viewModel: StockViewModel
){
    Column() {
        // Scaffold que contiene el TopAppBar y el BottomAppBar
        Scaffold(
            topBar = {
                // Muestra el TopAppBar5 que contiene los botones de categorías de stock
                TopAppBar5(navController)
            },
            bottomBar = {
                // Muestra el BottomAppBar5 con botones de acción
                BottomAppBar5(
                    onButtonClickedFuncApp = onButtonClickedFuncApp,
                    onButtonClickedStock = onButtonClickedStock,
                    onButtonClickedHome = onButtonClickedHome,
                    onButtonClickedUser = onButtonClickedUser
                )
            },
        ) { innerPadding ->
            // Contenido de la pantalla dentro de un Column
            Column(modifier = Modifier.padding(innerPadding)) {
                // Muestra el contenido relacionado con el stock
                Stock(
                    onButtonClickedFuncApp = onButtonClickedFuncApp,
                    onButtonClickedStock = onButtonClickedStock,
                    onButtonClickedHome = onButtonClickedHome,
                    onButtonClickedUser = onButtonClickedUser,
                    viewModel = viewModel,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun BottomAppBar5(
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
