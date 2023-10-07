package com.samedtemiz.fitlife.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import com.samedtemiz.fitlife.viewmodel.HomeViewModel
import com.samedtemiz.fitlife.viewmodel.ProfileViewModel


@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel,
    onNavigateLogin: () -> Unit
) {
    val user by profileViewModel.user.observeAsState()

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
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxSize()
            ) {
                Text(
                    text = "PROFILE",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Column {
                    user.let {
                        if (it != null) {
                            if(it.email != null){
                                Text(
                                    text = "Email: ${it.email}",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }
                        }
                    }

                }

                Spacer(Modifier.height(20.dp))

                ButtonComponent(
                    value = "LOGOUT",
                    onButtonClicked = {
                        profileViewModel.logout()
                        if (!profileViewModel.isUserLoggedIn) onNavigateLogin.invoke()
                    },
                    isEnabled = true
                )
            }

            if (profileViewModel.isLoading) {
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