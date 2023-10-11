package com.samedtemiz.fitlife.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.samedtemiz.fitlife.R

sealed class Screen(
    val route: String,
    val title: String
){
    object Splash: Screen(
        route = "splash_screen",
        title = "Splash"
    )
    object Main : Screen(
        route = "main",
        title = "Main"
    )
}