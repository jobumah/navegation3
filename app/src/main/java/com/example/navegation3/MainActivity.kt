package com.example.navegation3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.navegation3.navegation.GestionNavegacion
import com.example.navegation3.ui.theme.Navegation3Theme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.Firebase

class MainActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth
        
        enableEdgeToEdge()
        setContent {
            Navegation3Theme {
                GestionNavegacion(auth)
            }
        }
    }
}
