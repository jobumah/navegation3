package com.example.navegation3.navegation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.navegation3.pantallas.TiendaCompra
import com.example.navegation3.pantallas.TiendaDetalle
import com.example.navegation3.pantallas.TiendaHome

@Composable
fun GestionNavegacion(){

    val pilaNavegacion = rememberNavBackStack(Routes.Home)
    NavDisplay(
        backStack = pilaNavegacion,
        onBack = { pilaNavegacion.removeLastOrNull() },
        entryProvider = { key ->
            when(key){
                is Routes.Home -> NavEntry(key){
                    TiendaHome(navegaADetalle = { id -> pilaNavegacion.add(Routes.Detalle(id)) })
                }
                is Routes.Detalle -> NavEntry(key){
                    TiendaDetalle(
                        id = key.id,
                        navegaACompra = { pilaNavegacion.add(Routes.Compra) },
                        navegaAtras = { pilaNavegacion.removeLastOrNull() }
                    )
                }
                is Routes.Compra -> NavEntry(key){
                    TiendaCompra(
                        navegaAHome = {
                            while (pilaNavegacion.size > 1) {
                                pilaNavegacion.removeLastOrNull()
                            }
                        }
                    )
                }
                else -> NavEntry(Routes.Error){
                    Text("Error")
                }
            }
        }
    )
}
