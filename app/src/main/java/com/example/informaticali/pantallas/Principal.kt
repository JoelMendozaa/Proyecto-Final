package com.example.informaticali.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.informaticali.R

@Composable
fun Principal() {
    Column(
        modifier = Modifier
            .padding(vertical = 70.dp)
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

    BottomAppBar()
}

@Composable
fun DividerExample() {
    Column(modifier = Modifier.padding(20.dp)) {
        Divider(thickness = 1.dp, color = Color.Black)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomAppBar() {
    Scaffold(
        bottomBar = {
            BottomAppBar(
                actions = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(Icons.Filled.Check, contentDescription = "InfoApp")
                    }
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_bar_chart_24),
                            contentDescription = "Stock",
                        )
                    }
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            Icons.Filled.Home,
                            contentDescription = "Home",
                        )
                    }
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            Icons.Filled.AccountCircle,
                            contentDescription = "User",
                        )
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = { /* do something */ },
                        containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                    ) {
                        Icon(Icons.Filled.Add, "Localized description")
                    }
                }
            )
        },
    ) { innerPadding ->
        Text(
            modifier = Modifier.padding(innerPadding),
            text = "Example of a scaffold with a bottom app bar."
        )
    }
}
