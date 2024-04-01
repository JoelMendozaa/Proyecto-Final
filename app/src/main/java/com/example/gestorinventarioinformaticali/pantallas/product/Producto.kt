package com.example.gestorinventarioinformaticali.pantallas.product


import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Card
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.gestorinventarioinformaticali.R
import com.example.gestorinventarioinformaticali.models.Productos
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
    val productos by viewModel.listaProductos.collectAsState(initial = emptyList())
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Producto") }
            )
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
    producto: Productos,
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

val listaProducto = listOf(
    Product("Prueba", "Prueba")
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraBusqueda2(navController: NavHostController){
    val context = LocalContext.current
    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    val onSearch: (String) -> Unit = {
        Toast.makeText(context, "Search", Toast.LENGTH_SHORT).show()
        active = false
    }
    SearchBar(
        query = query,
        onQueryChange = { query = it},
        onSearch = onSearch,
        active = active,
        onActiveChange = {active = it},
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        placeholder = { Text(text = "Buscar") },
        leadingIcon = { IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.Menu, contentDescription = null)
        }},
        trailingIcon = {
            IconButton(
                onClick = { onSearch(query) },
                enabled = query.isNotEmpty()
            ) {
                Icon(imageVector = Icons.Default.Search, contentDescription = null)
            }
        }
    ) {
        if(query.isNotEmpty()) {
            val filteredProducts = listaProductos.filter { productos ->  productos.nombre.contains(query, true) ||   productos.descripcion.contains(query, true)}
            filteredProducts.forEach { producto ->
                Row (modifier = Modifier.clickable {  }){
                    Text(text = "${producto.nombre} ${producto.descripcion}")
                    Image(painter = painterResource(id = producto.imagenID), contentDescription = "Productos", modifier = Modifier.size(48.dp))
                }
            }
        }
    }
}

