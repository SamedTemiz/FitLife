package com.samedtemiz.fitlife.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.auth.FirebaseAuth
import com.samedtemiz.fitlife.components.ButtonComponent
import com.samedtemiz.fitlife.data.auth.home.HomeViewModel
import com.samedtemiz.fitlife.navigation.Screen
import com.samedtemiz.fitlife.ui.screens.auth.WelcomeScreen


@Preview(showSystemUi = true, heightDp = 700)
@Composable
fun ProfileScreenPreview() {
//    ProfileScreen()
}

@Composable
fun ProfileScreen(
    homeViewModel: HomeViewModel = viewModel(),
    onNavigateLogin: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            Column() {
                Text(
                    text = "PROFILE SCREEN",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(20.dp))

                ButtonComponent(
                    value = "LOGOUT",
                    onButtonClicked = {
                        homeViewModel.logout()
                        if(!homeViewModel.isUserLoggedIn) onNavigateLogin.invoke()
                    },
                    isEnabled = true
                )
            }

            if (homeViewModel.isLoading) {
                CircularProgressIndicator()
            }
        }

    }
}