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

class LoginViewModel: ViewModel() {
    val auth: FirebaseAuth = Firebase.auth
    val _loading = MutableLiveData(false)

    fun signInWithEmailAndPassword(email: String, password: String, home: () -> Unit)
        = viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener{task ->
                        if (task.isSuccessful){
                            val displayName =
                                task.result.user?.email?.split("@")?.get(0)
                            createUser(displayName)
                            home()
                        } else {
                            Log.d("Gestor de Inventario", "signInWithEmailAndPassword: ${task.result.toString()}")
                        }
                    }
            }
            catch (ex: Exception){
                Log.d("Gestor de Inventario", "signInWithEmailAndPassword: ${ex.message}")

            }
    }

    private fun createUser(displayName: String?) {
        val userId = auth.currentUser?.uid
        val user = mutableMapOf<String, Any>()

        user["user_id"] = userId.toString()
        user["display_name"] = displayName.toString()
        FirebaseFirestore.getInstance().collection("users")
            .add(user)
            .addOnSuccessListener {
                Log.d("Gestor de inventario", "Crear ${it.id}")
            }.addOnFailureListener{
                Log.d("Gestor de inventario", "Error")
            }
    }

    fun createUserWithEmailAndPassword(
        email: String,
        password: String,
        home: () -> Unit
    ){
        if (_loading.value == false){
            _loading.value = true
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        home()
                    } else {
                        Log.d("Gestor de Inventario", "createInWithEmailAndPassword: ${task.result.toString()}")
                    }
                    _loading.value = false
                }
        }
    }
}