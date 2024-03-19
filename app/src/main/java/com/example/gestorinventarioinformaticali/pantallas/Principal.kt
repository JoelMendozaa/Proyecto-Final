package com.example.gestorinventarioinformaticali.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gestorinventarioinformaticali.R

@Composable
fun Principal(
    onButtonClickedInfoApp: () -> Unit,
    onButtonClickedStock: () -> Unit,
    onButtonClickedPrincipal: () -> Unit,
    onButtonClickedUser: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar()
        },
        bottomBar = {
            Row () {
                BottomAppBar(
                    actions = {
                        IconButton(modifier = Modifier.weight(2f),
                            onClick = { onButtonClickedInfoApp() }) {
                            Icon(Icons.Filled.Check, contentDescription = "InfoApp")
                        }
                        IconButton(modifier = Modifier.weight(2f),
                            onClick = { onButtonClickedStock() }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_bar_chart_24),
                                contentDescription = "Stock",
                            )
                        }
                        IconButton(modifier = Modifier.weight(2f),
                            onClick = { onButtonClickedPrincipal() }) {
                            Icon(
                                Icons.Filled.Home,
                                contentDescription = "Home",
                            )
                        }
                        IconButton(modifier = Modifier.weight(2f),
                            onClick = { onButtonClickedUser() }) {
                            Icon(
                                Icons.Filled.AccountCircle,
                                contentDescription = "User",
                            )
                        }
                    }
                )
            }
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

            Row(modifier = Modifier
                .horizontalScroll(ScrollState(0))
                .padding(3.dp)) {
                Image(painter = painterResource(id = R.drawable.tarjeta), contentDescription = "Tarjeta")
                Image(painter = painterResource(id = R.drawable.procesador), contentDescription = "Procesador")

            }
            DividerExample()
            Column (
                modifier = Modifier
                    .padding(vertical = 70.dp),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "PRODUCTOS", fontSize = 28.sp)

                Row(modifier = Modifier
                    .horizontalScroll(ScrollState(0))
                    .padding(3.dp)) {
                    Image(painter = painterResource(id = R.drawable.tarjeta2), contentDescription = "Tarjeta")
                    Image(painter = painterResource(id = R.drawable.procesador2), contentDescription = "Procesador")
                    Image(painter = painterResource(id = R.drawable.ram), contentDescription = "RAM")
                }
            }
        }
    }
}

@Composable
fun DividerExample() {
    Column(modifier = Modifier.padding(20.dp)) {
        Divider(thickness = 1.dp, color = Color.Black)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar() {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Principal",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                actions = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description"
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
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

            Row(modifier = Modifier
                .horizontalScroll(ScrollState(0))
                .padding(3.dp)) {
                Image(painter = painterResource(id = R.drawable.tarjeta), contentDescription = "Tarjeta")
                Image(painter = painterResource(id = R.drawable.procesador), contentDescription = "Procesador")

            }
            DividerExample()
            Column (
                modifier = Modifier
                    .padding(vertical = 70.dp),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "PRODUCTOS", fontSize = 28.sp)

                Row(modifier = Modifier
                    .horizontalScroll(ScrollState(0))
                    .padding(3.dp)) {
                    Image(painter = painterResource(id = R.drawable.tarjeta2), contentDescription = "Tarjeta")
                    Image(painter = painterResource(id = R.drawable.procesador2), contentDescription = "Procesador")
                    Image(painter = painterResource(id = R.drawable.ram), contentDescription = "RAM")
                }
            }
        }
    }
}