package com.samedtemiz.gastronomyguide.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.samedtemiz.gastronomyguide.screens.HomeScreen
import com.samedtemiz.gastronomyguide.screens.LoginScreen
import com.samedtemiz.gastronomyguide.screens.RegisterScreen

enum class LoginRoutes {
    Login,
    Register
}

enum class HomeRoutes {
    Home,
    Detail
}

@Composable
fun Navigation(
    navController: NavHostController = rememberNavController()
) {

    NavHost(navController = navController, startDestination = LoginRoutes.Login.name) {

        composable(route = LoginRoutes.Login.name) {
            LoginScreen(
                onNavToHomePage = {
                    navController.navigate(HomeRoutes.Home.name) {
                        launchSingleTop = true
                        popUpTo(route = LoginRoutes.Login.name) {
                            inclusive = true
                        }
                    }
                },
                onNavToRegisterPage = {
                    navController.navigate(LoginRoutes.Register.name) {
                        launchSingleTop = true
                        popUpTo(LoginRoutes.Login.name) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(route = LoginRoutes.Register.name) {
            RegisterScreen(
                onNavToHomePage = {
                    navController.navigate(HomeRoutes.Home.name) {
                        popUpTo(LoginRoutes.Register.name) {
                            inclusive = true
                        }
                    }
                },
                onNavToLoginPage = {
                    navController.navigate(LoginRoutes.Login.name)
                }
            )
        }

        composable(route = HomeRoutes.Home.name) {
            HomeScreen(
                onNavToLoginPage = {
                    navController.navigate(LoginRoutes.Login.name)
                }
            )
        }

    }


}