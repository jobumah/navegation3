package com.example.navegation3.navegation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.navegation3.pantallas.PantDetalle
import com.example.navegation3.pantallas.PantHistorico
import com.example.navegation3.pantallas.PantHome

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
                    PantDetalle(
                        id = key.id,
                        navegaAHistorico = { pilaNavegacion.add(Routes.Historico) },
                        navegaAHome = { 
                            while (pilaNavegacion.size > 1) {
                                pilaNavegacion.removeLastOrNull()
                            }
                        }
                    )
                }
                is Routes.Historico -> NavEntry(key){
                    PantHistorico(
                        navegaAtras = { pilaNavegacion.removeLastOrNull() },
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
