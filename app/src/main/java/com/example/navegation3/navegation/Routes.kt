package com.example.navegation3.navegation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Routes: NavKey {
    @Serializable
    data object Home: Routes()

    @Serializable
    data class Detalle(val id: Int): Routes()

    @Serializable
    data object Compra: Routes()

    @Serializable
    data object Error: Routes()
}
