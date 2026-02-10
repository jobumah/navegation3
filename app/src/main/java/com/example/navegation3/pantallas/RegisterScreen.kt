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
fun RegisterScreen(auth: FirebaseAuth, onNavigateBack: () -> Unit, onRegisterSuccess: (String) -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    
    var muestraDialogRegistro by remember { mutableStateOf(false) }
    var passNotEquals by remember { mutableStateOf(false) }
    var muestraErrorFirebase by remember { mutableStateOf(false) }

    if (muestraDialogRegistro) {
        AlertDialog(
            onDismissRequest = { 
                muestraDialogRegistro = false
                onRegisterSuccess(email) 
            },
            confirmButton = {
                TextButton(onClick = { 
                    muestraDialogRegistro = false
                    onRegisterSuccess(email)
                }) {
                    Text("Aceptar")
                }
            },
            title = { Text("Registro con éxito") },
            text = { Text("Usuario creado correctamente.") }
        )
    }

    if (passNotEquals) {
        AlertDialog(
            onDismissRequest = { passNotEquals = false },
            confirmButton = {
                TextButton(onClick = { passNotEquals = false }) {
                    Text("Reintentar")
                }
            },
            title = { Text("Error") },
            text = { Text("Las contraseñas no coinciden.") }
        )
    }

    if (muestraErrorFirebase) {
        AlertDialog(
            onDismissRequest = { muestraErrorFirebase = false },
            confirmButton = {
                TextButton(onClick = { muestraErrorFirebase = false }) {
                    Text("Cerrar")
                }
            },
            title = { Text("Error") },
            text = { Text("Hubo un fallo en la creación del usuario.") }
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
            text = "Registro de usuario/a",
            fontSize = 24.sp,
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

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Repite contraseña") },
            visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                Text(
                    text = if (confirmPasswordVisible) "Ocultar" else "Mostrar",
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .clickable { confirmPasswordVisible = !confirmPasswordVisible },
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
                if (password == confirmPassword) {
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnSuccessListener {
                            muestraDialogRegistro = true
                        }
                        .addOnFailureListener { e ->
                            Log.e("Firebase", "Error en creación de usuario ${e.message}", e)
                            muestraErrorFirebase = true
                        }
                } else {
                    passNotEquals = true
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4A5D8F))
        ) {
            Text(text = "Regístrate", color = Color.White, fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onNavigateBack,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4A5D8F))
        ) {
            Text(text = "Cancelar", color = Color.White)
        }
    }
}
