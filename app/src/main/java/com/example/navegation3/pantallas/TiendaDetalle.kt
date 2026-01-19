package com.example.navegation3.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.navegation3.R
import com.example.navegation3.model.Producto

@Composable
fun TiendaDetalle(id: Int, navegaACompra: () -> Unit, navegaAtras: () -> Unit) {
    val producto = when (id) {
        1 -> Producto(1, "Vodka Absolut", 18.50, R.drawable.vodka)
        2 -> Producto(2, "Whisky Jameson", 22.90, R.drawable.jameson)
        3 -> Producto(3, "Ron Añejo", 15.75, R.drawable.ron)
        else -> Producto(0, "Producto no encontrado", 0.0, R.drawable.ic_launcher_foreground)
    }

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = producto.imagen),
                contentDescription = producto.nombre,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = producto.nombre,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "${producto.precio} €",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF5C6BC0)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Descripción: Disfruta de la mejor calidad con ${producto.nombre}. Ideal para tus celebraciones y momentos especiales.",
                fontSize = 15.sp,
                color = Color.DarkGray,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = navegaACompra,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5C6BC0)),
                shape = RoundedCornerShape(25.dp)
            ) {
                Text(text = "Comprar ahora", color = Color.White, fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = navegaAtras,
                modifier = Modifier
                    .width(120.dp)
                    .height(45.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5C6BC0)),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(text = "Atrás", color = Color.White)
            }
        }
    }
}
