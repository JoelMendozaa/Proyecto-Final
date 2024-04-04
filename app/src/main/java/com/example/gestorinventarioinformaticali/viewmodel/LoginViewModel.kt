package com.example.gestorinventarioinformaticali.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    // Instancia de FirebaseAuth para autenticación de usuarios
    private val auth: FirebaseAuth = Firebase.auth

    // LiveData para indicar si la operación de autenticación está en curso
    val _loading = MutableLiveData(false)

    // Función para iniciar sesión con correo electrónico y contraseña
    fun signInWithEmailAndPassword(email: String, password: String, home: () -> Unit) = viewModelScope.launch {
        try {
            // Intento de inicio de sesión
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Si la autenticación es exitosa, se llama a la función para crear un nuevo usuario
                        val displayName = task.result.user?.email?.split("@")?.get(0)
                        createUser(displayName)
                        home() // Navegar a la pantalla principal
                    } else {
                        // Si hay un error, se registra en el log
                        Log.d("Gestor de Inventario", "signInWithEmailAndPassword: ${task.result.toString()}")
                    }
                }
        } catch (ex: Exception) {
            // Si hay una excepción, se registra en el log
            Log.d("Gestor de Inventario", "signInWithEmailAndPassword: ${ex.message}")
        }
    }

    // Función para crear un nuevo usuario en Firestore
    private fun createUser(displayName: String?) {
        val userId = auth.currentUser?.uid
        val user = mutableMapOf<String, Any>()

        userId?.let { user["user_id"] = it }
        displayName?.let { user["display_name"] = it }

        // Agregar el usuario a la colección "users" en Firestore
        FirebaseFirestore.getInstance().collection("users")
            .add(user)
            .addOnSuccessListener {
                Log.d("Gestor de inventario", "Crear ${it.id}")
            }.addOnFailureListener{
                Log.d("Gestor de inventario", "Error")
            }
    }

    // Función para crear un nuevo usuario con correo electrónico y contraseña
    fun createUserWithEmailAndPassword(email: String, password: String, home: () -> Unit) {
        // Verificar que la carga no esté en curso
        if (_loading.value == false) {
            _loading.value = true // Establecer la carga en curso
            // Crear un nuevo usuario con correo electrónico y contraseña
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    // Si la creación de usuario es exitosa, se ejecuta la función home
                    if (task.isSuccessful) {
                        home()
                    } else {
                        // Si hay un error, se registra en el log
                        Log.d("Gestor de Inventario", "createInWithEmailAndPassword: ${task.result.toString()}")
                    }
                    _loading.value = false // Establecer la carga en falso una vez finalizada la operación
                }
        }
    }
}