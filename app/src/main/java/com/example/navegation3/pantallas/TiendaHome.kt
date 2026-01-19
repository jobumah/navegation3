package com.example.navegation3.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.navegation3.R

data class Producto(
    val id: Int,
    val nombre: String,
    val precio: Double,
    val imagen: Int
)

@Composable
fun TiendaHome(navegaADetalle: (Int) -> Unit) {
    val listaProductos = listOf(
        Producto(1, "Zapatillas Nike", 99.99, R.drawable.ic_launcher_foreground),
        Producto(2, "Camiseta Malaga CF", 69.9, R.drawable.ic_launcher_foreground),
        Producto(3, "Gorra Malaga CF", 15.0, R.drawable.ic_launcher_foreground)
    )

    Scaffold(
        topBar = {
            Text(
                text = "Catálogo disponible:",
                modifier = Modifier.padding(16.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(listaProductos) { producto ->
                ItemProducto(producto = producto, onVerClick = { navegaADetalle(producto.id) })
            }
        }
    }
}

@Composable
fun ItemProducto(producto: Producto, onVerClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE0E0E0)) // Un gris clarito como la imagen
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Imagen del producto
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = producto.imagen),
                    contentDescription = producto.nombre,
                    modifier = Modifier.size(60.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Textos (Nombre y Precio)
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = producto.nombre,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.Black
                )
                Text(
                    text = "${producto.precio} €",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            }

            // Botón Ver
            Button(
                onClick = onVerClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5C6BC0)), // Azul/violeta de la imagen
                shape = RoundedCornerShape(50)
            ) {
                Text(text = "Ver", color = Color.White)
            }
        }
    }
}
