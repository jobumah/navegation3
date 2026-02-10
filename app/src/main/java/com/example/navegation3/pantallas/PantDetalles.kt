package com.example.navegation3.pantallas

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.navegation3.viewmodel.ProductosViewModel

@Composable
fun PantDetalle(
    id: String,
    navegaAtras: () -> Unit,
    viewModel: ProductosViewModel = viewModel()
) {
    val productos by viewModel.productos.collectAsState()
    val producto = productos.find { it.id == id }

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (producto != null) {
                // Imagen grande del producto
                if (producto.urlImagen.isNotEmpty()) {
                    AsyncImage(
                        model = producto.urlImagen,
                        contentDescription = producto.nombre,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Nombre y Precio
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = producto.nombre,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "${producto.precio} €",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF3F51B5)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Descripción
                Text(
                    text = producto.descripcion,
                    fontSize = 16.sp,
                    color = Color.Gray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                )

                Spacer(modifier = Modifier.weight(1f))

                // Botón Atrás
                Button(
                    onClick = navegaAtras,
                    modifier = Modifier
                        .padding(bottom = 32.dp)
                        .wrapContentWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF536694))
                ) {
                    Text("Atrás", modifier = Modifier.padding(horizontal = 16.dp))
                }
            } else {
                // Caso en que no se encuentre el producto (cargando o error)
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}
