package com.samedtemiz.fitlife.ui.app

import android.content.Context
import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.samedtemiz.fitlife.data.auth.home.HomeViewModel
import com.samedtemiz.fitlife.navigation.Screen
import com.samedtemiz.fitlife.ui.screens.auth.LoginScreen
import com.samedtemiz.fitlife.ui.screens.auth.RegisterScreen
import com.samedtemiz.fitlife.ui.screens.auth.WelcomeScreen
import com.samedtemiz.fitlife.ui.screens.main.MainScreen
import com.samedtemiz.fitlife.ui.screens.main.enter_RightAnimation
import com.samedtemiz.fitlife.ui.screens.main.exit_RightAnimation


@Composable
fun AppLayout(
    navController: NavHostController,
    homeViewModel: HomeViewModel = viewModel()
) {

    NavHost(
        navController = navController,
        startDestination = userStatus(homeViewModel) // Main or Welcome screen
    ) {
        navigation(
            startDestination = Screen.Auth.Welcome.route,
            route = "auth"
        ){
            composable(
                route = Screen.Auth.Welcome.route,
                exitTransition = exit_RightAnimation(),
                popEnterTransition = enter_RightAnimation()
            ) {
                WelcomeScreen(navController = navController)
            }

            composable(
                route = Screen.Auth.Login.route,
                exitTransition = exit_RightAnimation(),
                popEnterTransition = enter_RightAnimation()
            ) {
                LoginScreen(navController = navController)
            }

            composable(
                route = Screen.Auth.Register.route,
                exitTransition = exit_RightAnimation(),
                popEnterTransition = enter_RightAnimation()
            ) {
                RegisterScreen(navController = navController)
            }
        }

        composable(
            route = Screen.Main.route,
            exitTransition = exit_RightAnimation(),
            popEnterTransition = enter_RightAnimation()
        ) {
            MainScreen(mainController = navController)
        }
    }

}

fun userStatus(homeViewModel: HomeViewModel): String{

    //Oturum açık mı diye kontrol ediyoruz
    homeViewModel.checkForActiveSession()

    if (homeViewModel.isUserLoggedIn == true) {
        return Screen.Main.route
    }else{
        return "auth"
    }
}

object AppSettings {
    fun isDarkMode(context: Context): Boolean {
        val darkModeFlag =
            context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return darkModeFlag == Configuration.UI_MODE_NIGHT_YES
    }
}

