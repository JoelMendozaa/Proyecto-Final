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
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Stock(
    onButtonClickedFuncApp: () -> Unit,
    onButtonClickedStock: () -> Unit,
    onButtonClickedHome: () -> Unit,
    onButtonClickedUser: () -> Unit,
    viewModel: StockViewModel,
    navController: NavController
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    Scaffold(
        topBar = {
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
            BottomAppBar9(
                onButtonClickedFuncApp = onButtonClickedFuncApp,
                onButtonClickedStock = onButtonClickedStock,
                onButtonClickedHome = onButtonClickedHome,
                onButtonClickedUser = onButtonClickedUser
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("agregar2") }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Agregar")
            }
        }
    ) {
        ContentInicioStockView(
            it,
            navController,
            viewModel
        )
    }
}



@Composable
fun ContentInicioStockView(it: PaddingValues, navController: NavController, viewModel: StockViewModel){
    val state = viewModel.state
    Column {
        LazyColumn {
            items (state.listaStock){
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
                            Button(
                                onClick = { navController.navigate("editar/${it.id}/${it.nombre}/${it.marca}/${it.stock}") }
                            ) {
                                Text(text = "Editar")
                            }
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
                    Icon(Icons.Default.AttachFile, contentDescription = "FuncApp")
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

