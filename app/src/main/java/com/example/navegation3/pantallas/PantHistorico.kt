package com.example.navegation3.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PantHistorico(
    navegaAtras: () -> Unit,
    navegaAHome: () -> Unit
) {
    Scaffold() { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Pantalla Histórico", fontSize = 30.sp)
            Spacer(Modifier.height(16.dp))
            Button(onClick = { navegaAtras() }) {
                Text("Volver atrás")
            }
            Spacer(Modifier.height(16.dp))
            Button(onClick = { navegaAHome() }) {
                Text("Volver a Home")
            }
        }
    }
}
