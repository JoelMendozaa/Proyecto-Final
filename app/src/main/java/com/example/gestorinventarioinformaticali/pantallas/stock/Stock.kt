package com.example.gestorinventarioinformaticali.pantallas.stock

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.gestorinventarioinformaticali.R

@Composable
fun Stock(
    onButtonClickedFuncApp: () -> Unit,
    onButtonClickedStock: () -> Unit,
    onButtonClickedHome: () -> Unit,
    onButtonClickedUser: () -> Unit,
    navController: NavHostController
){
    Column() {
        Scaffold(
            topBar = {
                TopAppBar6()
            },
            bottomBar = {
                BottomAppBar6(
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
fun BottomAppBar6(
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
fun TopAppBar6() {
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
    },
    ) { innerPadding ->
    Column(modifier = Modifier.padding(innerPadding)) {
        ListaProductos(productos = listaProductos)
        }
    }
}

data class Productos(
    val nombre: String,
    val descripcion: String,
    val imagenID: Int,
    val stock: Int
)

val listaProductos = listOf(
    Productos("Producto1", "Descipcion1", R.drawable.procesador2, 50),
    Productos("Producto2", "Descipcion2", R.drawable.procesador2, 50),
    Productos("Producto3", "Descipcion3", R.drawable.procesador2, 50),
    Productos("Producto4", "Descipcion4", R.drawable.procesador2, 50),
    Productos("Producto5", "Descipcion5", R.drawable.procesador2, 50),
    Productos("Producto6", "Descipcion6", R.drawable.procesador2, 50),
    Productos("Producto7", "Descipcion7", R.drawable.procesador2, 50),
    Productos("Producto8", "Descipcion8", R.drawable.procesador2, 50),
    Productos("Producto9", "Descipcion9", R.drawable.procesador2, 50),
    Productos("Producto10", "Descipcion10", R.drawable.procesador2, 50),
    Productos("Producto11", "Descipcion11", R.drawable.procesador2, 50)
)


@Composable
fun ListaItemsProductos(productos: Productos){
    var expanded by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ){
        Image(  // Imagen
            painter = painterResource(id = R.drawable.procesador2),
            contentDescription = "Imagen",
            modifier = Modifier.size(80.dp),
            contentScale = ContentScale.FillBounds
        )
        Column (modifier = Modifier.weight(1f)) {// Nombre y descripcion
            Text(text = productos.nombre, maxLines = 1)
            Text(text = productos.descripcion, maxLines = 1)
        }
        Text(
            text = "Stock: ${productos.stock}",
            modifier = Modifier.padding(6.dp)
        ) // Stock

        IconButton(onClick = { expanded = !expanded }) {
            Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "Opciones")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            offset = DpOffset(x = (-60).dp, y = (-10).dp)
        ) {
            DropdownMenuItem(
                text = { Text("Editar") },
                onClick = { expanded = false }
            )
            DropdownMenuItem(
                text = { Text("Añadir") },
                onClick = { expanded = false }
            )
            DropdownMenuItem(
                text = { Text("Eliminar") },
                onClick = { expanded = false })
        }
    }
}

@Composable
fun ListaProductos(productos: List<Productos>){
    LazyColumn{
        items(productos){productos ->
            ListaItemsProductos(productos = productos)
        }
    }
}