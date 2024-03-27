package com.example.gestorinventarioinformaticali.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.gestorinventarioinformaticali.R
import com.example.gestorinventarioinformaticali.navigation.ScreenList
import com.example.gestorinventarioinformaticali.viewmodel.UsuariosViewModel


@Composable
fun InicioView(
    navController: NavHostController,
    viewModel: UsuariosViewModel
) {
    Scaffold(
        topBar = {
            TopAppBar8(navController)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("agregar") }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) {
        ContentInicioView(it, navController, viewModel)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar8(navController: NavHostController) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Inicio View",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                scrollBehavior = scrollBehavior
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(ScrollState(0)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "CATEGORIA", fontSize = 28.sp)

            Row(
                modifier = Modifier
                    .horizontalScroll(ScrollState(0))
                    .padding(3.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.tarjeta),
                    contentDescription = "Tarjeta",
                    modifier = Modifier
                        .clickable { navController.navigate(ScreenList.Product.name) }
                        .padding(4.dp)

                )
                Image(
                    painter = painterResource(id = R.drawable.procesador),
                    contentDescription = "Procesador",
                    modifier = Modifier
                        .clickable { navController.navigate(ScreenList.Product.name) }
                        .padding(4.dp)
                )
            }
            DividerExample()
            Column(
                modifier = Modifier
                    .padding(vertical = 70.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "PRODUCTOS", fontSize = 28.sp)

                LazyRow(
                    modifier = Modifier
                        .padding(3.dp)
                ) {

                    item {
                        Image(
                            painter = painterResource(id = R.drawable.procesador2),
                            contentDescription = "Procesador",
                            modifier = Modifier
                                .clickable { navController.navigate(ScreenList.InfoProduct.name) }
                                .padding(4.dp)
                        )

                        Image(
                            painter = painterResource(id = R.drawable.tarjeta2),
                            contentDescription = "Tarjeta",
                            modifier = Modifier
                                .clickable { navController.navigate(ScreenList.InfoProduct.name) }
                                .padding(4.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.ram),
                            contentDescription = "RAM",
                            modifier = Modifier
                                .clickable { navController.navigate(ScreenList.InfoProduct.name) }
                                .padding(4.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.ssd),
                            contentDescription = "SSD",
                            modifier = Modifier
                                .clickable { navController.navigate(ScreenList.InfoProduct.name) }
                                .padding(4.dp)
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun ContentInicioView(it: PaddingValues, navController: NavController, viewModel: UsuariosViewModel){
    val state = viewModel.state

    Column (
        modifier = Modifier.padding(it)
    ) {
        LazyColumn(){
            items (state.listaUsuarios){
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ){
                    Column (
                        modifier = Modifier
                            .padding(12.dp)
                    ) {
                        Text(text = it.nomApels)
                        Text(text = it.email)
                        Text(text = "${it.telefono}")
                        IconButton(
                            onClick = { navController.navigate("editar/${it.id}/${it.nomApels}/${it.email}/${it.telefono}") }
                        ) {
                            Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
                        }
                        IconButton(onClick = { viewModel.borrarUsuario(it) }) {
                            Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
                        }
                    }
                }
            }
        }
    }
}
