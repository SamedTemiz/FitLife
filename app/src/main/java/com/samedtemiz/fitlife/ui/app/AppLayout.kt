package com.samedtemiz.fitlife.ui.app

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.samedtemiz.fitlife.viewmodel.HomeViewModel
import com.samedtemiz.fitlife.navigation.Screen
import com.samedtemiz.fitlife.ui.screens.SplashScreen
import com.samedtemiz.fitlife.ui.screens.auth.LoginScreen
import com.samedtemiz.fitlife.ui.screens.auth.RegisterScreen
import com.samedtemiz.fitlife.ui.screens.auth.WelcomeScreen
import com.samedtemiz.fitlife.ui.screens.main.MainScreen
import com.samedtemiz.fitlife.ui.screens.main.enter_RightAnimation
import com.samedtemiz.fitlife.ui.screens.main.exit_RightAnimation
import com.samedtemiz.fitlife.viewmodel.ProfileViewModel


private lateinit var appContext : Context
@Composable
fun AppLayout(
    navController: NavHostController,
    profileViewModel: ProfileViewModel = viewModel()
) {
    appContext = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route // Main or Welcome screen
    ) {
        /*
        navigation(
            startDestination = Screen.Auth.Welcome.route,
            route = "auth"
        ) {
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
        }*/

        composable(route = Screen.Splash.route){
            SplashScreen(navController = navController)
        }

        composable(
            route = Screen.Main.route,
            exitTransition = exit_RightAnimation(),
            popEnterTransition = enter_RightAnimation()
        ) {
            MainScreen(mainController = navController, profileViewModel)
        }
    }

}

//fun userStatus(profileViewModel: ProfileViewModel): String {
//    //Oturum açık mı diye kontrol ediyoruz
//    profileViewModel.checkForActiveSession()
//
//    return if (profileViewModel.isUserLoggedIn) {
//        Screen.Main.route
//    } else {
//        "auth"
//    }
//}

fun toastMessage(msg: String){
    Toast.makeText(appContext, msg, Toast.LENGTH_SHORT).show()
}

fun checkPermissionFor(permission: String) : Boolean{
    return ContextCompat.checkSelfPermission(appContext, permission) == PackageManager.PERMISSION_GRANTED
}


