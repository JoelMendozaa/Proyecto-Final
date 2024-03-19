package com.example.gestorinventarioinformaticali.pantallas.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.gestorinventarioinformaticali.R

@Composable
fun Register(
    buttonClickedPrincipal: () ->  Unit,
){
    var nombre by remember { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var numero by remember { mutableStateOf("")}

    Column (modifier = Modifier
        .fillMaxSize()
        .padding(vertical = 25.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(painter = painterResource(id = R.drawable.logo), contentDescription = "Logo")
        OutLineTextFieldNombre(user = nombre){ newNombre ->
            nombre = newNombre
        }

        OutLineTextFieldCorreo(correo = correo) { newCorreo ->
            correo = newCorreo
        }

        OutLineTextFieldPassword2(password = password) { newPassword ->
            password = newPassword
        }

        OutLineTextFieldTelefono(numero = numero) { newNumero ->
            numero = newNumero
        }
        Row (modifier = Modifier
            .padding(15.dp),
            horizontalArrangement = Arrangement.Center
        ){
            Button(onClick = {
                buttonClickedPrincipal()
            }) {
                Text(text = "Register")
            }
        }
    }
}



@Composable
fun OutLineTextFieldNombre(user: String, onUserChange: (String) -> Unit) {
    OutlinedTextField(
        modifier = Modifier.padding(vertical = 10.dp),
        value = user,
        label = { Text(text = "Introduce tu usuario o correo") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        onValueChange = {
                newValue -> onUserChange(newValue)
        }
    )
}


@Composable
fun OutLineTextFieldCorreo(correo: String, onUserChange: (String) -> Unit) {
    OutlinedTextField(
        modifier = Modifier.padding(vertical = 10.dp),
        value = correo,
        label = { Text(text = "Introduce tu correo") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        onValueChange = {
                newValue -> onUserChange(newValue)
        }
    )
}

@Composable
fun OutLineTextFieldPassword2(password: String, onPasswordChange: (String) -> Unit) {
    OutlinedTextField(
        value = password,
        onValueChange = { newValue -> onPasswordChange(newValue) },
        label = { Text(text = "Introduce tu contraseña") },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutLineTextFieldTelefono(numero: String, onUserChange: (String) -> Unit) {
    OutlinedTextField(
        modifier = Modifier.padding(vertical = 10.dp),
        value = numero,
        label = { Text(text = "Introduce tu numero") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        onValueChange = {
                newValue -> onUserChange(newValue)
        }
    )
}
