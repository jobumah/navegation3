package com.example.navegation3.navegation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.navegation3.pantallas.LoginScreen
import com.example.navegation3.pantallas.PantHome
import com.example.navegation3.pantallas.RegisterScreen
import com.example.navegation3.pantallas.TiendaDetalle
import com.google.firebase.auth.FirebaseAuth

@Composable
fun GestionNavegacion(auth: FirebaseAuth) {

    val pilaNavegacion = rememberNavBackStack(Routes.Login)
    NavDisplay(
        backStack = pilaNavegacion,
        onBack = { pilaNavegacion.removeLastOrNull() },
        entryProvider = { key ->
            when (key) {
                is Routes.Login -> NavEntry(key) {
                    LoginScreen(
                        auth = auth,
                        onNavigateToRegister = { pilaNavegacion.add(Routes.Register) },
                        onLoginSuccess = { email -> pilaNavegacion.add(Routes.Home(email)) }
                    )
                }
                is Routes.Register -> NavEntry(key) {
                    RegisterScreen(
                        auth = auth,
                        onNavigateBack = { pilaNavegacion.removeLastOrNull() },
                        onRegisterSuccess = { email -> pilaNavegacion.add(Routes.Home(email)) }
                    )
                }
                is Routes.Home -> NavEntry(key) {
                    PantHome(
                        email = key.email,
                        onLogout = {
                            auth.signOut()
                            while (pilaNavegacion.size > 1) {
                                pilaNavegacion.removeLastOrNull()
                            }
                            pilaNavegacion.add(Routes.Login)
                            pilaNavegacion.removeAt(0)
                        },
                        onNavigateToDetail = { id -> pilaNavegacion.add(Routes.Detalle(id)) }
                    )
                }
                is Routes.Detalle -> NavEntry(key) {
                    // Adaptamos TiendaDetalle para que reciba el id como String si es necesario
                    TiendaDetalle(
                        id = key.id.toIntOrNull() ?: 0,
                        navegaACompra = { }, 
                        navegaAtras = { pilaNavegacion.removeLastOrNull() }
                    )
                }
                else -> NavEntry(Routes.Error) {
                    Text("Error")
                }
            }
        }
    )
}
