package com.example.gestorinventarioinformaticali.pantallas.product

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.gestorinventarioinformaticali.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActDesc(
    // Lambdas para manejar eventos de botones y control de navegación
    onButtonClickedFuncApp: () -> Unit,
    onButtonClickedStock: () -> Unit,
    onButtonClickedHome: () -> Unit,
    onButtonClickedUser: () -> Unit,
    // Controlador de navegación para la aplicación
    navController: NavHostController,
    // Función para manejar cambios en la descripción del producto
    onDescriptionChanged: (String) -> Unit
){
    // Configuración del comportamiento de desplazamiento del AppBar
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    // Composición de la pantalla utilizando Scaffold
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Actualizar Producto",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                scrollBehavior = scrollBehavior,
            )
        },
        bottomBar = {
            // BottomAppBar que contiene botones de acción
            BottomAppBar10(
                onButtonClickedFuncApp = onButtonClickedFuncApp,
                onButtonClickedStock = onButtonClickedStock,
                onButtonClickedHome = onButtonClickedHome,
                onButtonClickedUser = onButtonClickedUser
            )
        },
    ) { innerPadding ->
        // Columna de desplazamiento perezoso que contiene la descripción
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            item {
                // Componente para editar la descripción, pasando la función para manejar cambios
                Descripcion(onDescriptionChanged)
            }
        }
    }
}

@Composable
fun Descripcion(
    // Función para manejar cambios en la descripción del producto
    onDescriptionChanged: (String) -> Unit
) {
    // Estado mutable para el texto de la descripción y para controlar si se está editando
    var savedText by rememberSaveable { mutableStateOf("") }
    var isEditing by remember { mutableStateOf(false) }

    // Renderizado condicional basado en si se está editando o no
    if (isEditing) {
        // Componente para editar el texto
        EditarTexto(
            initialText = savedText,
            onTextChange = { enteredText ->
                savedText = enteredText
            },
            onEditingFinished = {
                isEditing = false
                // Llama a la función para manejar cambios en la descripción
                onDescriptionChanged(savedText)
            }
        )
    } else {
        // Componente para mostrar el texto de la descripción y un botón para editar
        MostrarTexto(savedText)
        Button(onClick = { isEditing = true }) {
            Text("Edit")
        }
    }
}

@Composable
fun EditarTexto(
    // Texto inicial para la edición
    initialText: String,
    // Función para manejar cambios en el texto
    onTextChange: (String) -> Unit,
    // Función para manejar el final de la edición
    onEditingFinished: () -> Unit
) {
    // Estado mutable para el texto en edición
    var description by remember { mutableStateOf(initialText) }

    // Efecto de lanzamiento para reflejar cambios en el texto
    LaunchedEffect(description) {
        onTextChange(description)
    }

    // Columna que contiene un TextField para editar el texto
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp)) {
        TextField(
            value = description,
            onValueChange = {
                description = it
            },
            label = { Text("Escribe aqui") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                onEditingFinished()
            })
        )
    }
}

@Composable
fun MostrarTexto(text: String) {
    // Columna que muestra el texto de la descripción
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Text(text = "Descripcion: ")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = text)
    }
}

@Composable
fun BottomAppBar10(
    // Lambdas para manejar eventos de botones de acción
    onButtonClickedFuncApp: () -> Unit,
    onButtonClickedStock: () -> Unit,
    onButtonClickedHome: () -> Unit,
    onButtonClickedUser: () -> Unit,
) {
    // Fila que contiene los botones de acción en el BottomAppBar
    Row {
        BottomAppBar(
            actions = {
                // Icono y acción para FuncApp
                IconButton(
                    modifier = Modifier.weight(2f),
                    onClick = onButtonClickedFuncApp
                ) {
                    Icon(Icons.Default.AttachFile, contentDescription = "FuncApp")
                }
                // Icono y acción para Stock
                IconButton(
                    modifier = Modifier.weight(2f),
                    onClick = onButtonClickedStock
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_bar_chart_24),
                        contentDescription = "Stock",
                    )
                }
                // Icono y acción para Home
                IconButton(
                    modifier = Modifier.weight(2f),
                    onClick = onButtonClickedHome
                ) {
                    Icon(
                        Icons.Filled.Home,
                        contentDescription = "Home",
                    )
                }
                // Icono y acción para User
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