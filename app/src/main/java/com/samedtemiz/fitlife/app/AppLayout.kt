package com.samedtemiz.fitlife.app

import android.content.Context
import android.content.res.Configuration
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose.AppTheme
import com.samedtemiz.fitlife.data.auth.home.HomeViewModel
import com.samedtemiz.fitlife.navigation.AppRouter
import com.samedtemiz.fitlife.navigation.Screen
import com.samedtemiz.fitlife.ui.screens.auth.LoginScreen
import com.samedtemiz.fitlife.ui.screens.main.MainScreen
import com.samedtemiz.fitlife.ui.screens.auth.RegisterScreen
import com.samedtemiz.fitlife.ui.screens.auth.WelcomeScreen


@Composable
fun AppLayout(homeViewModel: HomeViewModel = viewModel()) {

    //Oturum açık mı diye kontrol ediyoruz
    homeViewModel.checkForActiveSession()

    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.White
        ) {

            if (homeViewModel.isUserLoggedIn.value == true) {
                AppRouter.navigateTo(Screen.MainScreen)
            }

            Crossfade(targetState = AppRouter.currentScreen, label = "Navigation") { currentState ->

                when (currentState.value) {
                    is Screen.WelcomeScreen -> {
                        WelcomeScreen()
                    }

                    is Screen.LoginScreen -> {
                        LoginScreen()
                    }

                    is Screen.RegisterScreen -> {
                        RegisterScreen()
                    }

                    is Screen.MainScreen -> {
                        MainScreen()
                    }

                    else -> {}
                }
            }
        }
    }

}

object AppSettings{
    fun isDarkMode(context: Context): Boolean {
        val darkModeFlag = context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return darkModeFlag == Configuration.UI_MODE_NIGHT_YES
    }
}

