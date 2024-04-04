package com.example.gestorinventarioinformaticali.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gestorinventarioinformaticali.R
import androidx.navigation.NavHostController
import com.example.gestorinventarioinformaticali.navigation.ScreenList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(navController: NavHostController) {
    // Configuración del comportamiento de desplazamiento para la barra superior
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    // Scaffold que contiene la barra superior
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            // Barra superior centrada con título "InformaticaLI"
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "InformaticaLI",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Blue
                    )
                },
                scrollBehavior = scrollBehavior
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Título "CATEGORÍA"
            Text(
                text = "CATEGORÍA",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Row para mostrar las imágenes de las categorías con desplazamiento horizontal
            Row(
                modifier = Modifier
                    .horizontalScroll(ScrollState(0))
                    .padding(3.dp)
            ) {
                // Imagen de la categoría "Tarjeta"
                Image(
                    painter = painterResource(id = R.drawable.tarjeta),
                    contentDescription = "Tarjeta",
                    modifier = Modifier
                        .clickable { navController.navigate(ScreenList.Product.name) }
                        .padding(4.dp)
                        .size(200.dp)
                )
                // Imagen de la categoría "Procesador"
                Image(
                    painter = painterResource(id = R.drawable.procesador),
                    contentDescription = "Procesador",
                    modifier = Modifier
                        .clickable { navController.navigate(ScreenList.Product.name) }
                        .padding(4.dp)
                        .size(200.dp)
                )
            }

            // Separador entre categorías
            DividerExample()

            // Columna para mostrar el título "PRODUCTOS" y las imágenes de productos con desplazamiento horizontal
            Column(
                modifier = Modifier
                    .padding(vertical = 70.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "PRODUCTOS",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                LazyRow(
                    modifier = Modifier
                        .padding(3.dp)
                ) {
                    item {
                        // Imágenes de productos con enlaces a la pantalla de información del producto
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
fun Principal(
    onButtonClickedFuncApp: () -> Unit,
    onButtonClickedStock: () -> Unit,
    onButtonClickedPrincipal: () -> Unit,
    onButtonClickedUser: () -> Unit,
    onButtonClickedInfoProduct: () -> Unit,
    onButtonClickedProduct: () -> Unit,
    navController: NavHostController,
) {

    // Scaffold que contiene la barra superior y la barra inferior
    Scaffold(
        topBar = {
            // Barra superior personalizada
            TopAppBar(navController)
        },
        bottomBar = {
            // Barra inferior personalizada
            BottomAppBar(
                onButtonClickedFuncApp = onButtonClickedFuncApp,
                onButtonClickedStock = onButtonClickedStock,
                onButtonClickedHome = onButtonClickedPrincipal,
                onButtonClickedUser = onButtonClickedUser
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){

        }
    }
}

@Composable
fun DividerExample() {
    // Ejemplo de un divisor
    Column(modifier = Modifier.padding(20.dp)) {
        Divider(thickness = 1.dp, color = Color.Black)
    }
}

@Composable
fun BottomAppBar(
    onButtonClickedFuncApp: () -> Unit,
    onButtonClickedStock: () -> Unit,
    onButtonClickedHome: () -> Unit,
    onButtonClickedUser: () -> Unit,
) {
    // Barra inferior que contiene iconos de acciones
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