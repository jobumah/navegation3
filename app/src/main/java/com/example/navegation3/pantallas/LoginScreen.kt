package com.example.navegation3.pantallas

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoginScreen(auth: FirebaseAuth, onNavigateToRegister: () -> Unit, onLoginSuccess: (String) -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var muestraDialogError by remember { mutableStateOf(false) }

    if (muestraDialogError) {
        AlertDialog(
            onDismissRequest = { muestraDialogError = false },
            confirmButton = {
                TextButton(onClick = { muestraDialogError = false }) {
                    Text("Aceptar")
                }
            },
            title = { Text("Error") },
            text = { Text("Email o contraseña Incorrecta") }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Login",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                Text(
                    text = if (passwordVisible) "Ocultar" else "Mostrar",
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .clickable { passwordVisible = !passwordVisible },
                    color = Color(0xFF4A5D8F),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            },
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    auth.signInWithEmailAndPassword(email, password)
                        .addOnSuccessListener {
                            onLoginSuccess(email)
                        }
                        .addOnFailureListener { e ->
                            Log.e("Firebase", "Error en login ${e.message}", e)
                            muestraDialogError = true
                        }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4A5D8F))
        ) {
            Text(text = "Entrar", color = Color.White, fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row {
            Text(text = "¿No tienes cuenta? ", color = Color.Gray)
            Text(
                text = "Regístrate",
                color = Color(0xFF4A5D8F),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { onNavigateToRegister() }
            )
        }
    }
}
