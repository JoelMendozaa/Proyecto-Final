package com.example.gestorinventarioinformaticali.pantallas.user

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.gestorinventarioinformaticali.view.BottomAppBar9


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar2(onButtonClickedLogin: () -> Unit) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Usuario",
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
                OutlinedText()
                Row(
                    modifier = Modifier
                        .padding(innerPadding),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(onClick = {
                        onButtonClickedLogin()
                    }) {
                        Text(text = "Cerrar Sesión")
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun User(
    onButtonClickedFuncApp: () -> Unit,
    onButtonClickedLogin: () -> Unit,
    onButtonClickedStock: () -> Unit,
    onButtonClickedHome: () -> Unit,
    onButtonClickedUser: () -> Unit,
){
    Scaffold(
        topBar = {
            TopAppBar2(onButtonClickedLogin)
        },
        bottomBar = {
            BottomAppBar9(
                onButtonClickedFuncApp = onButtonClickedFuncApp,
                onButtonClickedStock = onButtonClickedStock,
                onButtonClickedHome = onButtonClickedHome,
                onButtonClickedUser = onButtonClickedUser
            )
        },
        modifier = Modifier.padding(15.dp)
    ){ innerPadding ->
        Column (modifier = Modifier.verticalScroll(rememberScrollState())) {
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

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(imageVector = Icons.Filled.AccountCircle, contentDescription = "User", modifier = Modifier.size(200.dp) )
        OutLineTextFieldNombre(user = nombre) { newNombre ->
            nombre = newNombre
        }

        OutLineTextFieldCorreo(correo = correo) { newCorreo ->
            correo = newCorreo
        }

        OutLineTextFieldApellido(apellido = apellido) { newApellido ->
            apellido = newApellido
        }
        Row (
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutLineTextFieldTelefono(numero = numero) { newNumero ->
                numero = newNumero
            }
            Spacer(modifier = Modifier.padding(5.dp))
            OutLineTextFieldDNI(dni = dni) { newDni ->
                dni = newDni
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
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


@OptIn(ExperimentalMaterial3Api::class)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutLineTextFieldApellido(apellido: String, onApellidoChange: (String) -> Unit) {
    OutlinedTextField(
        value = apellido,
        onValueChange = { newValue -> onApellidoChange(newValue) },
        label = { Text(text = "Apellido1 Apellido2") },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutLineTextFieldTelefono(numero: String, onUserChange: (String) -> Unit) {
    OutlinedTextField(
        modifier = Modifier
            .padding(vertical = 10.dp)
            .size(width = 135.dp, height = 60.dp),
        value = numero,
        label = { Text(text = "123456789") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        onValueChange = {
                newValue -> onUserChange(newValue)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutLineTextFieldDNI(dni: String, onDniChange: (String) -> Unit) {
    OutlinedTextField(
        modifier = Modifier
            .padding(vertical = 10.dp)
            .size(width = 135.dp, height = 60.dp),
        value = dni,
        label = { Text(text = "11111111A") },
        onValueChange = {
                newValue -> onDniChange(newValue)
        }
    )
}

