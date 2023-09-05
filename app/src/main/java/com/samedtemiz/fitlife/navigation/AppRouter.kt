package com.samedtemiz.fitlife.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.samedtemiz.fitlife.R

sealed class Screen(
    val route: String,
    val title: String
){
    sealed class Auth{
        object Welcome : Screen(
            route = "welcome",
            title = "Welcome"
        )

        object Login : Screen(
            route = "login",
            title = "Login"
        )

        object Register : Screen(
            route = "register",
            title = "Register"
        )
    }

    object Main : Screen(
        route = "main",
        title = "Main"
    )
}