package com.samedtemiz.gastronomyguide.app

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.samedtemiz.gastronomyguide.data.auth.home.HomeViewModel
import com.samedtemiz.gastronomyguide.navigation.AppRouter
import com.samedtemiz.gastronomyguide.navigation.Screen
import com.samedtemiz.gastronomyguide.ui.screens.DetailScreen
import com.samedtemiz.gastronomyguide.ui.screens.HomeScreen
import com.samedtemiz.gastronomyguide.ui.screens.LoginScreen
import com.samedtemiz.gastronomyguide.ui.screens.MainScreen
import com.samedtemiz.gastronomyguide.ui.screens.RegisterScreen
import com.samedtemiz.gastronomyguide.ui.screens.WelcomeScreen


@Composable
fun AppLayout(homeViewModel: HomeViewModel = viewModel()) {

    //Oturum açık mı diye kontrol ediyoruz
    homeViewModel.checkForActiveSession()

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
