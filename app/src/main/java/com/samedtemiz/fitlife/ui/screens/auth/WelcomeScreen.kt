package com.samedtemiz.fitlife.ui.screens.auth

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.AppTheme
import com.samedtemiz.fitlife.R
import com.samedtemiz.fitlife.ui.app.AppSettings
import com.samedtemiz.fitlife.navigation.AppRouter
import com.samedtemiz.fitlife.navigation.Screen

@Preview(showSystemUi = true)
@Preview(showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun WelcomeScreenPreview() {
    AppTheme {
        WelcomeScreen()
    }
}

@Composable
fun WelcomeScreen() {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Image(
            painter = painterResource(
                id = if(AppSettings.isDarkMode(LocalContext.current)) R.drawable.food_bg_dark else R.drawable.food_bg_light
            ),
            contentDescription = "Welcome",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(22.dp),
            contentAlignment = Alignment.CenterEnd
        ) {

            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.5f)
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
                            text = "FitLife",
                            style = TextStyle(
                                fontSize = 42.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily(
                                    Font(R.font.esprit_bold)
                                )
                            ),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "Take a step towards a healthy life",
                            style = TextStyle(
                                fontSize = 14.sp,
                                textAlign = TextAlign.Center,
                                fontFamily = FontFamily(
                                    Font(R.font.avenir_next)
                                )
                            ),
                            color = Color(0xFF8d9395)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Column{
                        Button(
                            onClick = {
                                AppRouter.navigateTo(Screen.RegisterScreen)
                            },
                            modifier = Modifier
                                .fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.secondary,
                                contentColor = MaterialTheme.colorScheme.onSecondary
                            )
                        ) {
                            Text(
                                modifier = Modifier.padding(10.dp),
                                text = "REGISTER",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Light,
                                    fontFamily = FontFamily(
                                        Font(R.font.avenir_next)
                                    )
                                ),
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
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = MaterialTheme.colorScheme.onPrimary
                            ),
                        ) {
                            Text(
                                modifier = Modifier.padding(10.dp),
                                text = "LOGIN",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Light,
                                    fontFamily = FontFamily(
                                        Font(R.font.avenir_next)
                                    )
                                ),
                                letterSpacing = 1.sp,
                            )
                        }
                    }
                }
            }

        }
    }
}

