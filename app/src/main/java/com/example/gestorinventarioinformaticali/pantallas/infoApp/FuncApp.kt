package com.example.gestorinventarioinformaticali.pantallas.infoApp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
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


@Composable
fun FuncApp(
    onButtonClickedFuncApp: () -> Unit,
    onButtonClickedStock: () -> Unit,
    onButtonClickedHome: () -> Unit,
    onButtonClickedUser: () -> Unit,
    navController: NavHostController
) {
    Column() {
        Scaffold(
            topBar = {
                TopAppBar4()
            },
            bottomBar = {
                BottomAppBar4(
                    onButtonClickedFuncApp = onButtonClickedFuncApp,
                    onButtonClickedStock = onButtonClickedStock,
                    onButtonClickedHome = onButtonClickedHome,
                    onButtonClickedUser = onButtonClickedUser
                )
            },
        ) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {

            }
        }
    }
}
@Composable
fun BottomAppBar4(
    onButtonClickedFuncApp: () -> Unit,
    onButtonClickedStock: () -> Unit,
    onButtonClickedHome: () -> Unit,
    onButtonClickedUser: () -> Unit,
) {
    Row {
        BottomAppBar(
            actions = {
                IconButton(
                    modifier = Modifier.weight(2f),
                    onClick = onButtonClickedFuncApp
                ) {
                    Icon(Icons.Filled.Star, contentDescription = "FuncApp")
                }
                IconButton(
                    modifier = Modifier.weight(2f),
                    onClick = onButtonClickedStock
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_bar_chart_24),
                        contentDescription = "Stock",
                    )
                }
                IconButton(
                    modifier = Modifier.weight(2f),
                    onClick = onButtonClickedHome
                ) {
                    Icon(
                        Icons.Filled.Home,
                        contentDescription = "Home",
                    )
                }
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar4() {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Funcionamiento App",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                scrollBehavior = scrollBehavior,
            )
        },
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Text(
                modifier = Modifier.padding(20.dp),
                text = "La aplicación es muy sencilla"
            )
            Text(
                modifier = Modifier.padding(20.dp),
                text = "Para poder añadir stock debemos dirigirnos al " +
                    "bottombar y presionar el icono de barras, dentro seleccionamos que sección vamos " +
                    "editar, añadir o eliminar y dentro nos aparece las opciones"
            )
            Text(
                modifier = Modifier.padding(20.dp),
                text = "Para actualizar los productos, debemos ir al productos y presionar el icono " +
                        "de editar donde nos permite luego editar la descripcion"
            )
            Text(
                modifier = Modifier.padding(20.dp),
                text = "Para añadir o editar los productos de una categoria debemos ingresar dentro " +
                        "de las categorias y ahí nos aparece la opción para añadir y editarla"
            )
        }
    }
}