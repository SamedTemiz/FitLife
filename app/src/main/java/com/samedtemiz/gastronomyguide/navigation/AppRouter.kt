package com.samedtemiz.gastronomyguide.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screen {
    object WelcomeScreen : Screen()
    object LoginScreen : Screen()
    object RegisterScreen : Screen()
    object MainScreen : Screen()
}

object AppRouter {

    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.WelcomeScreen)

    fun navigateTo(destination: Screen) {
        currentScreen.value = destination
    }


}