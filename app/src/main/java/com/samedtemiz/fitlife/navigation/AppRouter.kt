package com.samedtemiz.fitlife.navigation

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