package com.example.navegation3.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.navegation3.model.Producto
import com.google.firebase.firestore.firestore
import com.google.firebase.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProductosViewModel : ViewModel() {
    private val db = Firebase.firestore
    private val productosCollection = db.collection("productos")

    private val _productos = MutableStateFlow<List<Producto>>(emptyList())
    val productos: StateFlow<List<Producto>> = _productos

    init {
        getProductos()
    }

    private fun getProductos() {
        productosCollection.addSnapshotListener { snapshot, error ->
            if (error != null) {
                Log.e("Firebase", "Error al obtener productos: ${error.message}", error)
                return@addSnapshotListener
            }

            if (snapshot != null) {
                val productosList = snapshot.documents.mapNotNull { doc ->
                    val producto = doc.toObject(Producto::class.java)
                    producto?.id = doc.id
                    producto
                }
                _productos.value = productosList
            }
        }
    }

    fun addProducto(nombre: String, precio: Double, descripcion: String, urlImagen: String) {
        val producto = Producto(nombre = nombre, precio = precio, descripcion = descripcion, urlImagen = urlImagen)
        productosCollection.add(producto)
            .addOnFailureListener { e ->
                Log.e("Error Firebase", "Error al guardar: ${e.message}", e)
            }
    }

    fun eliminarProducto(id: String) {
        productosCollection.document(id).delete()
            .addOnFailureListener { e ->
                Log.e("Error Firebase", "Error al eliminar: ${e.message}", e)
            }
    }

    fun updateProducto(idProducto: String, nuevoNombre: String, nuevoPrecio: Double?, nuevaDescripcion: String, nuevaUrlImagen: String) {
        val datosActualizados = mutableMapOf<String, Any>()

        if (nuevoNombre.isNotBlank()) {
            datosActualizados["nombre"] = nuevoNombre
        }
        if (nuevoPrecio != null) {
            datosActualizados["precio"] = nuevoPrecio
        }
        if (nuevaDescripcion.isNotBlank()) {
            datosActualizados["descripcion"] = nuevaDescripcion
        }
        if (nuevaUrlImagen.isNotBlank()) {
            datosActualizados["urlImagen"] = nuevaUrlImagen
        }

        if (datosActualizados.isNotEmpty()) {
            productosCollection.document(idProducto)
                .update(datosActualizados)
                .addOnFailureListener { e ->
                    Log.e("Error Firebase", "Error al actualizar: ${e.message}", e)
                }
        }
    }
}
