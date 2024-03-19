package com.example.gestorinventarioinformaticali.pantallas.user

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.gestorinventarioinformaticali.R
import com.example.gestorinventarioinformaticali.pantallas.TopAppBar


@Composable
fun User(
    onButtonClickedInfoApp: () -> Unit,
    onButtonClickedLogin: () -> Unit,
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
        Column {
            OutlinedText()
            Row (modifier = Modifier
                .padding(innerPadding),
                horizontalArrangement = Arrangement.Center
            ){
                Button(onClick = {
                    onButtonClickedLogin()
                }) {
                    Text(text = "Cerrar Sesión")
                }
            }

        }

    }
}

@Composable
fun OutlinedText (){
    var nombre by remember { mutableStateOf("") }
    var apellido by rememberSaveable { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var numero by remember { mutableStateOf("") }
    var dni by remember { mutableStateOf("") }

    Column (modifier = Modifier.fillMaxSize()) {
        OutLineTextFieldNombre(user = nombre) { newNombre ->
            nombre = newNombre
        }

        OutLineTextFieldCorreo(correo = correo) { newCorreo ->
            correo = newCorreo
        }

        OutLineTextFieldApellido(apellido = apellido) { newApellido ->
            apellido = newApellido
        }

        OutLineTextFieldTelefono(numero = numero) { newNumero ->
            numero = newNumero
        }

        OutLineTextFieldDNI(dni = dni) { newDni ->
            dni = newDni
        }
    }
}


@Composable
fun OutLineTextFieldNombre(user: String, onNombreChange: (String) -> Unit) {
    OutlinedTextField(
        modifier = Modifier.padding(vertical = 10.dp),
        value = user,
        label = { Text(text = "Nombre") },
        onValueChange = {
                newValue -> onNombreChange(newValue)
        }
    )
}


@Composable
fun OutLineTextFieldCorreo(correo: String, onCorreoChange: (String) -> Unit) {
    OutlinedTextField(
        modifier = Modifier.padding(vertical = 10.dp),
        value = correo,
        label = { Text(text = "correo@gmail.com") },
        onValueChange = {
                newValue -> onCorreoChange(newValue)
        }
    )
}

@Composable
fun OutLineTextFieldApellido(apellido: String, onApellidoChange: (String) -> Unit) {
    OutlinedTextField(
        value = apellido,
        onValueChange = { newValue -> onApellidoChange(newValue) },
        label = { Text(text = "Apellido1 Apellido2") },
    )
}

@Composable
fun OutLineTextFieldTelefono(numero: String, onUserChange: (String) -> Unit) {
    OutlinedTextField(
        modifier = Modifier.padding(vertical = 10.dp),
        value = numero,
        label = { Text(text = "123456789") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        onValueChange = {
                newValue -> onUserChange(newValue)
        }
    )
}

@Composable
fun OutLineTextFieldDNI(dni: String, onDniChange: (String) -> Unit) {
    OutlinedTextField(
        modifier = Modifier.padding(vertical = 10.dp),
        value = dni,
        label = { Text(text = "11111111A") },
        onValueChange = {
                newValue -> onDniChange(newValue)
        }
    )
}


