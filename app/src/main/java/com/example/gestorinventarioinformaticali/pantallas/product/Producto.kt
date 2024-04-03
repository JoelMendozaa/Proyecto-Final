package com.example.gestorinventarioinformaticali.pantallas.product


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Card
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
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
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gestorinventarioinformaticali.R
import com.example.gestorinventarioinformaticali.models.tablaProductos
import com.example.gestorinventarioinformaticali.viewmodel.ProductosViewModel
import kotlinx.coroutines.flow.Flow

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
    val productos by viewModel.listaProductos.collectAsState(initial = emptyList())
    Scaffold(
        topBar = {
            TopAppBar12(productosViewModel = viewModel)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("agregar") }
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
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            productos.forEach { producto ->
                ProductItem(
                    producto = producto,
                    onItemClick = { navController.navigate("editar/${producto.id}/${producto.nombre}/${producto.marca}") }
                ) {
                    viewModel.borrarProducto(producto)
                }
            }
        }
    }
}

@Composable
fun ProductItem(
    producto: tablaProductos,
    onItemClick: () -> Unit,
    onClickDelete: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onItemClick)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Nombre: ${producto.nombre}")
            Text(text = "Marca: ${producto.marca}")
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = onItemClick) {
                Text(text = "Editar")
            }
        }
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Button(onClick = onClickDelete) {
                Text(text = "Eliminar")
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar12(productosViewModel: ProductosViewModel) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    BarraBusqueda2(navController = rememberNavController(), viewModel = productosViewModel)
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Productos",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                scrollBehavior = scrollBehavior,
            )
        },
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            ListaProductos(productos = productosViewModel.listaProductos)
        }
    }
}

@Composable
fun ListaProductos(productos: Flow<List<tablaProductos>>) {
    val listaProductosState by productos.collectAsState(initial = emptyList())

    LazyColumn {
        items(listaProductosState) { producto ->
            Text(text = "Nombre: ${producto.nombre}, Marca: ${producto.marca}")
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

data class Product(
    val nombre: String,
    val marca: String,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraBusqueda2(
    navController: NavHostController,
    viewModel: ProductosViewModel
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
            .wrapContentHeight(),
        placeholder = { Text(text = "Buscar") },
        leadingIcon = {
            IconButton(onClick = { /* TODO*/ }) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = null)
            }
        },
        trailingIcon = {
            IconButton(
                onClick = { onSearch(query) },
                enabled = query.isNotEmpty()
            ) {
                Icon(imageVector = Icons.Default.Search, contentDescription = null)
            }
        }
    ) {
        if (query.isNotEmpty()) {
            val productos = viewModel.buscarProductos(query).collectAsState(initial = emptyList())

            LazyColumn {
                items(productos.value) { producto ->
                    ProductItem(
                        producto = producto,
                        onItemClick = { /*TODO*/ },
                        onClickDelete = { /*TODO*/}
                    )
                }
            }
        }
    }
}
