package com.example.gestorinventarioinformaticali.pantallas.product

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gestorinventarioinformaticali.R
import com.example.gestorinventarioinformaticali.models.tablaProductos
import com.example.gestorinventarioinformaticali.navigation.ScreenList
import com.example.gestorinventarioinformaticali.view.ContentInicioView
import com.example.gestorinventarioinformaticali.viewmodel.ProductosViewModel
import kotlinx.coroutines.flow.Flow
//
//@Composable
//fun Productos(
//    onButtonClickedFuncApp: () -> Unit,
//    onButtonClickedStock: () -> Unit,
//    onButtonClickedHome: () -> Unit,
//    onButtonClickedUser: () -> Unit,
//    onButtonClickedInfoProduct: () -> Unit,
//    navController: NavHostController,
//    viewModel: ProductosViewModel
//){
//    Column() {
//        Scaffold(
//            topBar = {
//                TopAppBar7()
//            },
//            floatingActionButton = {
//                FloatingActionButton(
//                    onClick = { navController.navigate("agregar") }
//                ) {
//                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
//                }
//            },
//            bottomBar = {
//                BottomAppBar7(
//                    onButtonClickedFuncApp = onButtonClickedFuncApp,
//                    onButtonClickedStock = onButtonClickedStock,
//                    onButtonClickedHome = onButtonClickedHome,
//                    onButtonClickedUser = onButtonClickedUser
//                )
//            },
//        ) { innerPadding ->
//            ContentInicioView(it = PaddingValues(), navController, viewModel)
//            Column(modifier = Modifier.padding(innerPadding)) {
//            }
//        }
//    }
//}
//
//@Composable
//fun BottomAppBar7(
//    onButtonClickedFuncApp: () -> Unit,
//    onButtonClickedStock: () -> Unit,
//    onButtonClickedHome: () -> Unit,
//    onButtonClickedUser: () -> Unit,
//) {
//    Row {
//        BottomAppBar(
//            actions = {
//                IconButton(
//                    modifier = Modifier.weight(2f),
//                    onClick = onButtonClickedFuncApp
//                ) {
//                    Icon(Icons.Filled.Star, contentDescription = "FuncApp")
//                }
//                IconButton(
//                    modifier = Modifier.weight(2f),
//                    onClick = onButtonClickedStock
//                ) {
//                    Icon(
//                        painter = painterResource(id = R.drawable.baseline_bar_chart_24),
//                        contentDescription = "Stock",
//                    )
//                }
//                IconButton(
//                    modifier = Modifier.weight(2f),
//                    onClick = onButtonClickedHome
//                ) {
//                    Icon(
//                        Icons.Filled.Home,
//                        contentDescription = "Home",
//                    )
//                }
//                IconButton(
//                    modifier = Modifier.weight(2f),
//                    onClick = onButtonClickedUser
//                ) {
//                    Icon(
//                        Icons.Filled.AccountCircle,
//                        contentDescription = "User",
//                    )
//                }
//            }
//        )
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun TopAppBar7() {
//    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
//    BarraBusqueda(navController = rememberNavController())
//    Scaffold(
//        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
//        topBar = {
//            CenterAlignedTopAppBar(
//                title = {
//                    Text(
//                        "Productos",
//                        maxLines = 1,
//                        overflow = TextOverflow.Ellipsis
//                    )
//                },
//                scrollBehavior = scrollBehavior,
//            )
//        },
//    ) { innerPadding ->
//        Column(modifier = Modifier.padding(innerPadding)) {
//            ListaProductos(productos = listaProductos)
//        }
//    }
//}
//
//data class Productos(
//    val nombre: String,
//    val descripcion: String,
//    val marca: String,
//    val imagenID: Int
//)
//
//val listaProductos = listOf(
//    Productos("Producto1", "Descipcion1", "Marca", R.drawable.procesador2),
//    Productos("Producto2", "Descipcion2", "Marca", R.drawable.procesador2),
//    Productos("Producto3", "Descipcion3", "Marca", R.drawable.procesador2),
//    Productos("Producto4", "Descipcion4", "Marca", R.drawable.procesador2),
//    Productos("Producto5", "Descipcion5", "Marca", R.drawable.procesador2),
//    Productos("Producto6", "Descipcion6", "Marca", R.drawable.procesador2),
//    Productos("Producto7", "Descipcion7", "Marca", R.drawable.procesador2),
//    Productos("Producto8", "Descipcion8", "Marca", R.drawable.procesador2),
//    Productos("Producto9", "Descipcion9","Marca", R.drawable.procesador2),
//    Productos("Producto10", "Descipcion10", "Marca", R.drawable.procesador2),
//    Productos("Producto11", "Descipcion11", "Marca", R.drawable.procesador2)
//)
//
//
//@Composable
//fun ListaItemsProductos(productos: Productos, navController: NavHostController){
//    var expanded by remember { mutableStateOf(false) }
//    Row(
//        modifier = Modifier.fillMaxWidth(),
//        horizontalArrangement = Arrangement.Center
//    ){
//        Image(  // Imagen
//            painter = painterResource(id = R.drawable.procesador2),
//            contentDescription = "Imagen",
//            modifier = Modifier.size(80.dp),
//            contentScale = ContentScale.FillBounds
//        )
//        Column (modifier = Modifier
//            .weight(1f)
//            .clickable { navController.navigate(ScreenList.InfoProduct.name) }) {// Nombre y descripcion
//            Text(text = productos.nombre, maxLines = 1)
//            Text(text = productos.descripcion, maxLines = 1)
//        }
//        IconButton(onClick = { expanded = !expanded }) {
//            Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "Opciones")
//        }
//        DropdownMenu(
//            expanded = expanded,
//            onDismissRequest = { expanded = false },
//            offset = DpOffset(x = (-60).dp, y = (-10).dp)
//        ) {
//            DropdownMenuItem(
//                text = { Text("Editar") },
//                onClick = { expanded = false }
//            )
//            DropdownMenuItem(
//                text = { Text("Añadir") },
//                onClick = { expanded = false }
//            )
//            DropdownMenuItem(
//                text = { Text("Eliminar") },
//                onClick = { expanded = false })
//        }
//    }
//}
//
//@Composable
//fun ListaProductos(productos: Flow<List<tablaProductos>>){
//    LazyColumn{
//        items(productos){producto ->
//            ListaItemsProductos(productos = producto, navController = rememberNavController())
//        }
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun BarraBusqueda(navController: NavHostController){
//    val context = LocalContext.current
//    var query by remember { mutableStateOf("") }
//    var active by remember { mutableStateOf(false) }
//    val onSearch: (String) -> Unit = {
//        Toast.makeText(context, "Search", Toast.LENGTH_SHORT).show()
//        active = false
//    }
//    SearchBar(
//        query = query,
//        onQueryChange = { query = it},
//        onSearch = onSearch,
//        active = active,
//        onActiveChange = {active = it},
//        modifier = Modifier
//            .fillMaxWidth()
//            .wrapContentHeight(),
//        placeholder = { Text(text = "Buscar") },
//        leadingIcon = { IconButton(onClick = { /*TODO*/ }) {
//            Icon(imageVector = Icons.Default.Menu, contentDescription = null)
//        }},
//        trailingIcon = {
//            IconButton(
//                onClick = { onSearch(query) },
//                enabled = query.isNotEmpty()
//            ) {
//                Icon(imageVector = Icons.Default.Search, contentDescription = null)
//            }
//        }
//    ) {
//        if(query.isNotEmpty()) {
//            val filteredProducts = listaProductos.filter { productos ->  productos.nombre.contains(query, true) ||   productos.descripcion.contains(query, true)}
//            filteredProducts.forEach { producto ->
//                Row (modifier = Modifier.clickable {  }){
//                    Text(text = "${producto.nombre} ${producto.descripcion}")
//                    Image(painter = painterResource(id = producto.imagenID), contentDescription = "Productos", modifier = Modifier.size(48.dp))
//                }
//            }
//        }
//    }
//}