package com.samedtemiz.gastronomyguide.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.samedtemiz.gastronomyguide.data.LoginViewModel
import com.samedtemiz.gastronomyguide.data.register.RegisterViewModel
import com.samedtemiz.gastronomyguide.navigation.Navigation


@Composable
fun GastronomyGuideApp() {
//    val loginViewModel = viewModel(modelClass = LoginViewModel::class.java)
//    val registerViewModel = viewModel(modelClass = RegisterViewModel::class.java)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Navigation()
    }

}