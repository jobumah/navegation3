package com.example.navegation3.pantallas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.navegation3.viewmodel.ProductosViewModel

@Composable
fun PantHome(
    email: String,
    onLogout: () -> Unit,
    onNavigateToDetail: (String) -> Unit,
    viewModel: ProductosViewModel = viewModel()
) {
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .statusBarsPadding(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Bienvenido $email", fontSize = 16.sp)
                IconButton(onClick = onLogout) {
                    Icon(Icons.AutoMirrored.Filled.ExitToApp, contentDescription = "Cerrar sesión")
                }
            }
        }
    ) { paddingValues ->
        val productos by viewModel.productos.collectAsState()

        var nombre by remember { mutableStateOf("") }
        var precio by remember { mutableStateOf("") }
        var descripcion by remember { mutableStateOf("") }
        var urlImagen by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre del producto") },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFFE5E7EB),
                    focusedContainerColor = Color(0xFFE5E7EB)
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = precio,
                onValueChange = { precio = it },
                label = { Text("Precio") },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFFE5E7EB),
                    focusedContainerColor = Color(0xFFE5E7EB)
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = descripcion,
                onValueChange = { descripcion = it },
                label = { Text("Descripción") },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFFE5E7EB),
                    focusedContainerColor = Color(0xFFE5E7EB)
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = urlImagen,
                onValueChange = { urlImagen = it },
                label = { Text("URL imagen") },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFFE5E7EB),
                    focusedContainerColor = Color(0xFFE5E7EB)
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    if (nombre.isNotBlank() && precio.isNotBlank()) {
                        viewModel.addProducto(
                            nombre,
                            precio.toDoubleOrNull() ?: 0.0,
                            descripcion,
                            urlImagen
                        )
                        nombre = ""
                        precio = ""
                        descripcion = ""
                        urlImagen = ""
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF536694))
            ) {
                Text("Agregar Producto")
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(items = productos, key = { it.id }) { prod ->
                    ProdItemCard(
                        producto = prod,
                        onView = { onNavigateToDetail(prod.id) },
                        onUpdate = {
                            viewModel.updateProducto(prod.id, nombre, precio.toDoubleOrNull(), descripcion, urlImagen)
                            nombre = ""
                            precio = ""
                            descripcion = ""
                            urlImagen = ""
                        },
                        onDelete = { viewModel.eliminarProducto(prod.id) }
                    )
                }
            }
        }
    }
}