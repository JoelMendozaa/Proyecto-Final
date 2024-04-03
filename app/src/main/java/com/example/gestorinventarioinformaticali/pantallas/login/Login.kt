package com.example.gestorinventarioinformaticali.pantallas.login

import android.icu.text.AlphabeticIndex.Bucket.LabelType
import android.inputmethodservice.Keyboard
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gestorinventarioinformaticali.R

@Composable
fun Login(navController: NavController){
    // True = Login; False = Create
    val showLoginForm = rememberSaveable {
        mutableStateOf(true)
    }
    Surface (
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (showLoginForm.value){
                Text(text = "Iniciar sesión")
                UserForm(
                    isCreateAccount = false
                ){
                    email, password ->
                    Log.d("Gestori de inventario", "Logueando con $email y $password")

                }
            }
            else {
                Text(text = "Crear cuenta")
                UserForm(
                    isCreateAccount = true
                ) {
                    email, password ->
                    Log.d("Gestori de inventario", "Creando cuenta con $email y $password")
                }
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
        Row (
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            val text1 =
                if (showLoginForm.value) "¿No tienes cuenta?"
                else "¿Ya tienes cuenta?"
            val text2 =
                if (showLoginForm.value) "Registrate"
                else "Inicia sesión"
            Text(text = text1)
            Text(text = text2,
                modifier = Modifier
                    .clickable { showLoginForm.value = !showLoginForm.value }
                    .padding(start = 5.dp),
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UserForm(
    isCreateAccount: Boolean = false,
    onDone: (String, String) -> Unit = {email, password -> }
) {
    val email = rememberSaveable {
        mutableStateOf("")
    }
    val password = rememberSaveable {
        mutableStateOf("")
    }
    val passwordVisible = rememberSaveable {
        mutableStateOf(false)
    }
    val valido = remember (email.value, password.value) {
        email.value.trim().isNotEmpty() &&
                password.value.trim().isNotEmpty()
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EmailText(
            emailState = email
        )
        PasswordText(
            passwordState = password,
            labelId = "Password",
            passwordVisible= passwordVisible
        )
        SubmitButton(
            textId = if (isCreateAccount) "Crear cuenta" else "Login",
            inputValido = valido
        ){
            onDone(email.value.trim(), password.value.trim())
            keyboardController?.hide()
        }
    }
}

@Composable
fun SubmitButton(
    textId: String,
    inputValido: Boolean,
    onClic: () -> Unit
) {
    Button(
        onClick = onClic,
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth(),
        shape = CircleShape,
        enabled = inputValido
        ) {
        Text(
            text = textId,
            modifier = Modifier
                .padding(5.dp)
        )
    }
}

@Composable
fun PasswordText(
    passwordState: MutableState<String>,
    labelId: String,
    passwordVisible: MutableState<Boolean>
) {
    val visualTransformation = if (passwordVisible.value)
        VisualTransformation.None
    else PasswordVisualTransformation()
   OutlinedTextField(
       value = passwordState.value,
       onValueChange = { passwordState.value = it },
       label = { Text(text = labelId) },
       singleLine = true,
       keyboardOptions = KeyboardOptions(
           keyboardType = KeyboardType.Password
       ),
       modifier = Modifier
           .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
           .fillMaxWidth(),
       visualTransformation = visualTransformation,
       trailingIcon = {
           if (passwordState.value.isNotBlank()){
               PasswordVisibleIcon(passwordVisible)
           }
           else null
       }
   )
}

@Composable
fun PasswordVisibleIcon(
    passwordVisible: MutableState<Boolean>
) {
    val image =
        if (passwordVisible.value)
            Icons.Default.VisibilityOff
        else
            Icons.Default.Visibility
    IconButton(onClick = {
        passwordVisible.value = !passwordVisible.value
    }) {
        Icon(
            imageVector = image,
            contentDescription = null)
    }
}

@Composable
fun EmailText(emailState: MutableState<String>, labelId: String = "Email") {
    InputField(
        valueState = emailState,
        labelId = labelId,
        keyboardType = KeyboardType.Email
    )
}

@Composable
fun InputField(
    valueState: MutableState<String>,
    labelId: String,
    isSingleLine: Boolean = true,
    keyboardType: KeyboardType
) {
    OutlinedTextField(
        value = valueState.value,
        onValueChange = { valueState.value = it},
        label = {
            Text(text = labelId)
        },
        singleLine = isSingleLine,
        modifier = Modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        )
    )
}



//@Composable
//fun Login(
//    buttonClickedRegister: () ->  Unit,
//    buttonClickedLogin: () -> Unit,
//){
//    var user by rememberSaveable { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//    val valido = remember (user,password){ user.trim().isNotEmpty() && password.trim().isNotEmpty() }
//
//
//    Column (modifier = Modifier
//        .fillMaxSize()
//        .padding(vertical = 25.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ){
//        Image(painter = painterResource(id = R.drawable.logo), contentDescription = "Logo")
//        OutLineTextFieldUser(user = user){ newUser ->
//            user = newUser
//        }
//
//        OutLineTextFieldPassword(password = password) { newPassword ->
//            password = newPassword
//        }
//        Row (modifier = Modifier
//            .padding(15.dp),
//            horizontalArrangement = Arrangement.Center
//        ){
//            Button(onClick = {
//                buttonClickedRegister()
//            }) {
//                Text(text = "Register")
//            }
//
//            Spacer(modifier = Modifier.padding(5.dp))
//
//            Button(onClick = {
//                buttonClickedLogin()
//            },
//                enabled = valido
//            ) {
//                Text (text = "Login")
//            }
//        }
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun OutLineTextFieldUser(user: String, onUserChange: (String) -> Unit) {
//    OutlinedTextField(
//        modifier = Modifier.padding(vertical = 10.dp),
//        value = user,
//        label = { Text(text = "Introduce tu usuario o correo") },
//        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
//        onValueChange = {
//                newValue ->
//            onUserChange(newValue)
//        }
//    )
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun OutLineTextFieldPassword(password: String, onPasswordChange: (String) -> Unit) {
//    OutlinedTextField(
//        value = password,
//        onValueChange = { newValue -> onPasswordChange(newValue) },
//        label = { Text(text = "Introduce tu contraseña") },
//        visualTransformation = PasswordVisualTransformation(),
//        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
//    )
//}
