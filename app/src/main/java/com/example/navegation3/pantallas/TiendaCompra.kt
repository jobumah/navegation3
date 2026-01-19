package com.example.navegation3.pantallas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TiendaCompra(navegaAHome: () -> Unit) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Éxito",
                modifier = Modifier.size(100.dp),
                tint = Color.Blue
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "¡Compra realizada!",
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = navegaAHome,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5C6BC0)),
                shape = RoundedCornerShape(25.dp)
            ) {
                Text(text = "Volver a la Tienda", color = Color.White)
            }
        }
    }
}
