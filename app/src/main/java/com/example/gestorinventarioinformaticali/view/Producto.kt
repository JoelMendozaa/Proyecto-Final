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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gestorinventarioinformaticali.R
import com.example.gestorinventarioinformaticali.models.tablaProductos
import com.example.gestorinventarioinformaticali.viewmodel.ProductosViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Producto(
    onButtonClickedFuncApp: () -> Unit,
    onButtonClickedStock: () -> Unit,
    onButtonClickedHome: () -> Unit,
    onButtonClickedUser: () -> Unit,
    viewModel: ProductosViewModel,
    navController: NavController
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    Scaffold(
        topBar = {
            Column {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            "Productos",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    },
                    scrollBehavior = scrollBehavior,
                )
            }
        },
        content = {
            BarraBusqueda2(navController = navController, viewModel = viewModel)
            ContentInicioView(
                it,
                navController,
                viewModel,
                modifier = Modifier.padding(top = 100.dp)
            )

        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("agregar1") }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Agregar")
            }
        },
        bottomBar = {
            BottomAppBar12(
                onButtonClickedFuncApp = onButtonClickedFuncApp,
                onButtonClickedStock = onButtonClickedStock,
                onButtonClickedHome = onButtonClickedHome,
                onButtonClickedUser = onButtonClickedUser
            )
        }
    )
}

@Composable
fun ContentInicioView(it: PaddingValues, navController: NavController, viewModel: ProductosViewModel, modifier: Modifier){
    val state = viewModel.state
    Column {
        LazyColumn {
            items (state.listaProductos){
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
                        Row{
                            Button(
                                onClick = { navController.navigate("editar/${it.id}/${it.nombre}/${it.marca}") }
                            ) {
                                Text(text = "Editar")
                            }
                            Button(onClick = { viewModel.borrarProducto(it) }) {
                                Text(text = "Elininar")
                            }
                        }
                    }
                }
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraBusqueda2(
    navController: NavController,
    viewModel: ProductosViewModel,
) {
    val context = LocalContext.current
    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    val onSearch: (String) -> Unit = {
        active = false
    }

    SearchBar(
        query = query,
        onQueryChange = { query = it },
        onSearch = onSearch,
        active = active,
        onActiveChange = { active = it },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 4.dp, end = 4.dp),
        placeholder = { Text(text = "Buscar") },
        trailingIcon = {
            if (active) {
                IconButton(
                    onClick = {
                        query = ""
                        active = false
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Clear",
                    )
                }
            } else {
                IconButton(
                    onClick = { active = true },
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                    )
                }
            }
        }
    ) {
        if (query.isNotEmpty()) {
            val productos = viewModel.buscarProductos(query).collectAsState(initial = emptyList())

            LazyColumn {
                items(productos.value) { producto ->
                    Card (
                        modifier = Modifier
                            .padding(top = 65.dp, start = 8.dp, end = 8.dp)
                            .fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(12.dp)
                        ) {
                            Text(text = "Nombre: ${producto.nombre}")
                            Text(text = "Marca: ${producto.marca}")
                            Row {
                                Button(
                                    onClick = { navController.navigate("editar/${producto.id}/${producto.nombre}/${producto.marca}") }
                                ) {
                                    Text(text = "Editar")
                                }
                                Button(onClick = { viewModel.borrarProducto(producto) }) {
                                    Text(text = "Eliminar")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BottomAppBar12(
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
