package com.example.gestorinventarioinformaticali.pantallas.stock

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Card
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gestorinventarioinformaticali.R
import com.example.gestorinventarioinformaticali.models.tablaProductos
import com.example.gestorinventarioinformaticali.viewmodel.ProductosViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Stock(
    onButtonClickedFuncApp: () -> Unit,
    onButtonClickedStock: () -> Unit,
    onButtonClickedHome: () -> Unit,
    onButtonClickedUser: () -> Unit,
    viewModel: ProductosViewModel,
    navController: NavController
) {
    val stocks by viewModel.listaProductos.collectAsState(initial = emptyList())
    Scaffold(
        topBar = {
           TopAppBar9()
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("agregar") }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Agregar")
            }
        },
        bottomBar = {
            BottomAppBar9(
                onButtonClickedFuncApp = onButtonClickedFuncApp,
                onButtonClickedStock = onButtonClickedStock,
                onButtonClickedHome = onButtonClickedHome,
                onButtonClickedUser = onButtonClickedUser
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(16.dp)
        ) {
            stocks.forEach { stockItem ->
                item {
                    StockItem(
                        modifier = Modifier.fillMaxSize(),
                        producto = stockItem,
                        onItemClick = {
                            navController.navigate(
                                "editar/${stockItem.id}/${stockItem.nombre}/${stockItem.marca}"
                            )
                        }
                    ) {
                        viewModel.borrarProducto(stockItem)
                    }
                }
            }
        }
    }
}

@Composable
fun StockItem(
    modifier : Modifier,
    producto: tablaProductos,
    onItemClick: () -> Unit,
    onClickDelete: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onItemClick)
            .padding(top = 10.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Nombre: ${producto.nombre}")
            Text(text = "Marca: ${producto.marca}")
            Text(text = "Stock: ${producto.stock}")
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = onItemClick) {
                    Text(text = "Editar")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = onClickDelete) {
                    Text(text = "Eliminar")
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
fun TopAppBar9() {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
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
        }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            item {
            }
        }
    }
}