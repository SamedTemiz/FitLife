package com.samedtemiz.gastronomyguide.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.samedtemiz.gastronomyguide.R
import com.samedtemiz.gastronomyguide.navigation.AppRouter
import com.samedtemiz.gastronomyguide.navigation.Screen


@Preview(showSystemUi = true)
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen()
}

@Composable
fun WelcomeScreen() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg),
            contentDescription = "Login",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(22.dp)
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Recipe",
                            fontSize = 60.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF222222)
                        )
                        Text(
                            text = "Help you to cook healthy food",
                            fontSize = 20.sp,
                            color = Color(0xFF8d9395)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(50.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            onClick = {
                                AppRouter.navigateTo(Screen.RegisterScreen)
                            },
                            modifier = Modifier
                                .fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFfc7600),
                                contentColor = Color.White
                            )
                        ) {
                            Text(
                                modifier = Modifier.padding(10.dp),
                                text = "REGISTER",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Normal,
                                letterSpacing = 1.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(15.dp))

                        Button(
                            onClick = {
                                AppRouter.navigateTo(Screen.LoginScreen)
                            },
                            modifier = Modifier
                                .fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White,
                                contentColor = Color(0xFF222222)
                            ),
                        ) {
                            Text(
                                modifier = Modifier.padding(10.dp),
                                text = "LOGIN",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Normal,
                                letterSpacing = 1.sp
                            )
                        }
                    }
                }
            }

        }
    }
}