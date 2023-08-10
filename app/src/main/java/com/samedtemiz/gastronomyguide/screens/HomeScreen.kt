package com.samedtemiz.gastronomyguide.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.samedtemiz.gastronomyguide.components.ButtonComponent
import com.samedtemiz.gastronomyguide.data.LoginViewModel


@Composable
fun HomeScreen(
    onNavToLoginPage: () -> Unit
) {
    val loginViewModel = viewModel<LoginViewModel>()
    val state = loginViewModel.state
    val context = LocalContext.current

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(modifier = Modifier.fillMaxSize()) {

                Text(text = "Ana sayfa", fontSize = 45.sp)
                ButtonComponent(
                    value = "Logout",
                    onButtonClicked = {
                        loginViewModel.logoutUser()
                        onNavToLoginPage.invoke()
                    },
                    true
                )
            }

            if (state.isLoading) {
                CircularProgressIndicator()
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen {

    }
}