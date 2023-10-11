package com.samedtemiz.fitlife.ui.app

import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.samedtemiz.fitlife.navigation.Screen
import com.samedtemiz.fitlife.ui.screens.SplashScreen
import com.samedtemiz.fitlife.ui.screens.main.MainScreen
import com.samedtemiz.fitlife.ui.screens.main.enter_RightAnimation
import com.samedtemiz.fitlife.ui.screens.main.exit_RightAnimation


private lateinit var appContext : Context
@Composable
fun AppLayout(navController: NavHostController) {
    appContext = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route // Main or Welcome screen
    ) {
        composable(route = Screen.Splash.route){
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

fun toastMessage(msg: String){
    Toast.makeText(appContext, msg, Toast.LENGTH_SHORT).show()
}

fun checkPermissionFor(permission: String) : Boolean{
    return ContextCompat.checkSelfPermission(appContext, permission) == PackageManager.PERMISSION_GRANTED
}


