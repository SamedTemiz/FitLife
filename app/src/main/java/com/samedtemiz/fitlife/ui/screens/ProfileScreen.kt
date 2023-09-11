package com.samedtemiz.fitlife.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.samedtemiz.fitlife.R
import com.samedtemiz.fitlife.components.ButtonComponent
import com.samedtemiz.fitlife.ui.viewmodel.HomeViewModel


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
        Image(
            painter = painterResource(
                id = R.drawable.pear_bg
            ),
            contentDescription = "Calorie Screen",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    text = "PROFILE SCREEN",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(Modifier.height(20.dp))

                ButtonComponent(
                    value = "LOGOUT",
                    onButtonClicked = {
                        homeViewModel.logout()
                        if (!homeViewModel.isUserLoggedIn) onNavigateLogin.invoke()
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

@Preview(showSystemUi = true, heightDp = 700)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(viewModel(), {})
}