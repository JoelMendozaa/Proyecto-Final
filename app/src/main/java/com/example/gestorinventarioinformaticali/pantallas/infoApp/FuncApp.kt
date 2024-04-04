package com.example.gestorinventarioinformaticali.pantallas.infoApp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.gestorinventarioinformaticali.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar4() {
    // Configuración del comportamiento de desplazamiento del AppBar
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    // Composición de la pantalla utilizando Scaffold
    Scaffold(
        // Aplicación del comportamiento de desplazamiento al modifier del Scaffold
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            // AppBar centrado con título "Funcionamiento del Gestor de Inventario"
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Funcionamiento del Gestor de Inventario",
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
            // Textos que explican el funcionamiento de la aplicación
            Text(
                modifier = Modifier.padding(20.dp),
                text = "La aplicación es muy sencilla"
            )
            Text(
                modifier = Modifier.padding(20.dp),
                text = "Para poder añadir stock debemos dirigirnos al " +
                        "BottomBar y presionar el icono de barras, dentro seleccionamos que sección vamos " +
                        "editar, añadir o eliminar y dentro nos aparece las opciones"
            )
            Text(
                modifier = Modifier.padding(20.dp),
                text = "Para actualizar los productos, debemos ir al productos y presionar el icono " +
                        "donde nos permite luego cambiar la descripción"
            )
            Text(
                modifier = Modifier.padding(20.dp),
                text = "Para añadir, editar y/o eliminar los productos de una categoria debemos ingresar dentro " +
                        "de las categorías y ahí nos aparece la opción para añadir y editarla"
            )
        }
    }
}

@Composable
fun FuncApp(
    // Lambdas para manejar eventos de botones de acción
    onButtonClickedFuncApp: () -> Unit,
    onButtonClickedStock: () -> Unit,
    onButtonClickedHome: () -> Unit,
    onButtonClickedUser: () -> Unit,
    // Controlador de navegación para la aplicación
    navController: NavHostController
) {
    Column() {
        // Scaffold que contiene el TopAppBar y el BottomAppBar
        Scaffold(
            topBar = {
                // Muestra el TopAppBar4 que explica el funcionamiento de la aplicación
                TopAppBar4()
            },
            bottomBar = {
                // Muestra el BottomAppBar4 con botones de acción
                BottomAppBar4(
                    onButtonClickedFuncApp = onButtonClickedFuncApp,
                    onButtonClickedStock = onButtonClickedStock,
                    onButtonClickedHome = onButtonClickedHome,
                    onButtonClickedUser = onButtonClickedUser
                )
            },
        ) { innerPadding ->
            // Contenido de la pantalla dentro de un Column
            Column(modifier = Modifier.padding(innerPadding)) {

            }
        }
    }
}


@Composable
fun BottomAppBar4(
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
