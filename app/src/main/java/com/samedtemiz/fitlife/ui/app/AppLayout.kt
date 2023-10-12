package com.samedtemiz.fitlife.ui.app

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.samedtemiz.fitlife.navigation.Screen
import com.samedtemiz.fitlife.ui.screens.SplashScreen
import com.samedtemiz.fitlife.ui.screens.main.MainScreen
import com.samedtemiz.fitlife.ui.screens.main.enter_RightAnimation
import com.samedtemiz.fitlife.ui.screens.main.exit_RightAnimation

@Composable
fun AppLayout(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route // Main or Welcome screen
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)
        }

        composable(
            route = Screen.Main.route,
            exitTransition = exit_RightAnimation(),
            popEnterTransition = enter_RightAnimation()
        ) {
            MainScreen()
        }
    }
}


