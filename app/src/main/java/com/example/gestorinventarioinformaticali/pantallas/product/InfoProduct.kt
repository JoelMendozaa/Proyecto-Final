package com.example.gestorinventarioinformaticali.pantallas.product

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
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
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import com.example.gestorinventarioinformaticali.R
import com.example.gestorinventarioinformaticali.pantallas.TopAppBar


@Composable
fun ProductoInfo(
    onButtonClickedInfoApp: () -> Unit,
    onButtonClickedStock: () -> Unit,
    onButtonClickedHome: () -> Unit,
    onButtonClickedUser: () -> Unit
){
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
                            onClick = { onButtonClickedHome() }) {
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
    ){ innerPadding ->
        LazyColumn (modifier = Modifier.padding(innerPadding)) {
            item {
                Image(painter = painterResource(id = R.drawable.procesador), contentDescription = "Procesador")
                Text(text = "i5-9600K \n Intel \n Poderoso procesador de la novena generación de intel, con 6 núcleos y 6 hilos")
            }
        }
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
                        "Productos",
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
        LazyColumn (modifier = Modifier.padding(innerPadding)) {
            item {
                Image(painter = painterResource(id = R.drawable.procesador), contentDescription = "Procesador")
                Text(text = "i5-9600K \n Intel \n Poderoso procesador de la novena generación de intel, con 6 núcleos y 6 hilos")
            }
        }
    }
}
