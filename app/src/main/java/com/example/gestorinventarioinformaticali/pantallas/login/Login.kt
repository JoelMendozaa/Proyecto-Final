package com.example.gestorinventarioinformaticali.pantallas.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
fun Login(
    buttonClickedRegister: () ->  Unit,
    buttonClickedLogin: () -> Unit,
){
    var user by rememberSaveable { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val valido = remember (user,password){ user.trim().isNotEmpty() && password.trim().isNotEmpty() }


    Column (modifier = Modifier
        .fillMaxSize()
        .padding(vertical = 25.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(painter = painterResource(id = R.drawable.logo), contentDescription = "Logo")
        OutLineTextFieldUser(user = user){ newUser ->
            user = newUser
        }

        OutLineTextFieldPassword(password = password) { newPassword ->
            password = newPassword
        }
        Row (modifier = Modifier
            .padding(15.dp),
            horizontalArrangement = Arrangement.Center
        ){
            Button(onClick = {
                buttonClickedRegister()
            }) {
                Text(text = "Register")
            }

            Spacer(modifier = Modifier.padding(5.dp))

            Button(onClick = {
                buttonClickedLogin()
            },
                enabled = valido
            ) {
                Text (text = "Login")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutLineTextFieldUser(user: String, onUserChange: (String) -> Unit) {
    OutlinedTextField(
        modifier = Modifier.padding(vertical = 10.dp),
        value = user,
        label = { Text(text = "Introduce tu usuario o correo") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        onValueChange = {
                newValue ->
            onUserChange(newValue)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutLineTextFieldPassword(password: String, onPasswordChange: (String) -> Unit) {
    OutlinedTextField(
        value = password,
        onValueChange = { newValue -> onPasswordChange(newValue) },
        label = { Text(text = "Introduce tu contraseña") },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
}
