package com.example.gestorinventarioinformaticali.pantallas.login


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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.example.gestorinventarioinformaticali.navigation.ScreenList
import com.example.gestorinventarioinformaticali.viewmodel.LoginViewModel

@Composable
fun Login(
    navController: NavController,
    viewModel: LoginViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
){
    // Estado para alternar entre el formulario de inicio de sesión y el de creación de cuenta
    val showLoginForm = rememberSaveable {
        mutableStateOf(true)
    }

    Surface (
        modifier = Modifier.fillMaxSize()
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Imagen del logo
            Image(painter = painterResource(id = R.drawable.logo), contentDescription = "Logo", modifier = Modifier.size(200.dp))

            // Comprobación para mostrar el formulario de inicio de sesión o de creación de cuenta
            if (showLoginForm.value){
                Text(text = "Iniciar sesión")
                // Formulario de inicio de sesión
                UserForm(
                    isCreateAccount = false
                ){ email, password ->
                    // Lógica para iniciar sesión
                    Log.d("Gestori de inventario", "Logueando con $email y $password")
                    viewModel.signInWithEmailAndPassword(email, password){
                        navController.navigate(ScreenList.Principal.name)
                    }
                }
            }
            else {
                Text(text = "Crear cuenta")
                // Formulario de creación de cuenta
                UserForm(
                    isCreateAccount = true
                ) { email, password ->
                    // Lógica para crear una cuenta
                    Log.d("Gestor de inventario", "Creando cuenta con $email y $password")
                    viewModel.createUserWithEmailAndPassword(email, password){
                        navController.navigate(ScreenList.Principal.name)
                    }
                }
            }
        }

        // Botón para alternar entre el formulario de inicio de sesión y el de creación de cuenta
        Spacer(modifier = Modifier.height(20.dp))
        Row (
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ){
            val text1 =
                if (showLoginForm.value) "¿No tienes cuenta?"
                else "¿Ya tienes cuenta?"
            val text2 =
                if (showLoginForm.value) "Registrate"
                else "Inicia sesión"
            // Textos con enlaces para cambiar entre formularios
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

@Composable
fun UserForm(
    isCreateAccount: Boolean = false,
    onDone: (String, String) -> Unit = { email, password -> }
) {
    // Estados para el email, la contraseña y la visibilidad de la contraseña
    val email = rememberSaveable {
        mutableStateOf("")
    }
    val password = rememberSaveable {
        mutableStateOf("")
    }
    val passwordVisible = rememberSaveable {
        mutableStateOf(false)
    }
    // Estado que determina si el formulario es válido
    val valido = remember (email.value, password.value) {
        email.value.trim().isNotEmpty() &&
                password.value.trim().isNotEmpty()
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Campo de entrada para el email
        EmailText(
            emailState = email
        )
        // Campo de entrada para la contraseña
        PasswordText(
            passwordState = password,
            labelId = "Password",
            passwordVisible= passwordVisible
        )
        // Botón para enviar el formulario
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
    // Botón para enviar el formulario
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
    // Campo de entrada para la contraseña
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
                // Icono para alternar la visibilidad de la contraseña
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
    // Icono para alternar la visibilidad de la contraseña
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
    // Campo de entrada para el email
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
    // Campo de entrada genérico
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
