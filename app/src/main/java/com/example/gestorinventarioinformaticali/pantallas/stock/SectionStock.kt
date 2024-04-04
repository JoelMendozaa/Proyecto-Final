package com.example.gestorinventarioinformaticali.pantallas.stock


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.gestorinventarioinformaticali.R
import com.example.gestorinventarioinformaticali.navigation.ScreenList
import com.example.gestorinventarioinformaticali.viewmodel.StockViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar5(navController: NavHostController) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Seccion Stock",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                scrollBehavior = scrollBehavior,
            )
        },
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Column {
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(onClick = { navController.navigate(ScreenList.Stock.name) }) { Text(text = "PC") }
                    Spacer(modifier = Modifier.padding(2.dp))
                    Button(onClick = { navController.navigate(ScreenList.Stock.name) }) { Text(text = "GPU") }
                    Spacer(modifier = Modifier.padding(2.dp))
                    Button(onClick = { navController.navigate(ScreenList.Stock.name) }) { Text(text = "CPU") }
                }
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    Button(onClick = { navController.navigate(ScreenList.Stock.name) }) { Text(text = "PSU") }
                    Spacer(modifier = Modifier.padding(2.dp))
                    Button(onClick = { navController.navigate(ScreenList.Stock.name) }) { Text(text = "RAM") }
                    Spacer(modifier = Modifier.padding(2.dp))
                    Button(onClick = { navController.navigate(ScreenList.Stock.name) }) { Text(text = "SSD") }
                }
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    Button(onClick = { navController.navigate(ScreenList.Stock.name) }) { Text(text = "HDD") }
                    Spacer(modifier = Modifier.padding(2.dp))
                    Button(onClick = { navController.navigate(ScreenList.Stock.name) }) { Text(text = "Teclados") }
                    Spacer(modifier = Modifier.padding(2.dp))
                    Button(onClick = { navController.navigate(ScreenList.Stock.name) }) { Text(text = "Ratones") }
                }
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    Button(onClick = { navController.navigate(ScreenList.Stock.name) }) { Text(text = "Monitores") }
                    Spacer(modifier = Modifier.padding(2.dp))
                    Button(onClick = { navController.navigate(ScreenList.Stock.name) }) { Text(text = "Accesorios") }
                    Spacer(modifier = Modifier.padding(2.dp))
                    Button(onClick = { navController.navigate(ScreenList.Stock.name) }) { Text(text = "Torres") }
                }

            }
        }
    }
}

@Composable
fun SectionStock(
    onButtonClickedFuncApp: () -> Unit,
    onButtonClickedStock: () -> Unit,
    onButtonClickedHome: () -> Unit,
    onButtonClickedUser: () -> Unit,
    navController: NavHostController,
    viewModel: StockViewModel
){
    Column() {
        Scaffold(
            topBar = {
                TopAppBar5(navController)
            },
            bottomBar = {
                BottomAppBar5(
                    onButtonClickedFuncApp = onButtonClickedFuncApp,
                    onButtonClickedStock = onButtonClickedStock,
                    onButtonClickedHome = onButtonClickedHome,
                    onButtonClickedUser = onButtonClickedUser
                )
            },
        ) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                Stock(
                    onButtonClickedFuncApp = onButtonClickedFuncApp,
                    onButtonClickedStock = onButtonClickedStock,
                    onButtonClickedHome = onButtonClickedHome,
                    onButtonClickedUser = onButtonClickedUser,
                    viewModel = viewModel,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun BottomAppBar5(
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