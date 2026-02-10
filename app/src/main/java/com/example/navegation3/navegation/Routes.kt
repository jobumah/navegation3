package com.example.navegation3.navegation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Routes: NavKey {
    @Serializable
    data object Login: Routes()

    @Serializable
    data object Register: Routes()

    @Serializable
    data class Home(val email: String): Routes()

    @Serializable
    data class Detalle(val id: String): Routes()

    @Serializable
    data object Error: Routes()
}
