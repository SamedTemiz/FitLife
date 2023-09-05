package com.samedtemiz.fitlife.ui.screens.auth

import androidx.compose.foundation.Image
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.compose.BurntSienna_300
import com.example.compose.BurntSienna_600
import com.example.compose.BurntSienna_900
import com.example.compose.Comet_300
import com.samedtemiz.fitlife.R
import com.samedtemiz.fitlife.navigation.Screen

//@Preview(showSystemUi = true)
//@Preview(showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
//@Composable
//fun WelcomeScreenPreview() {
//    AppTheme {
//        WelcomeScreen()
//    }
//}

@Composable
fun WelcomeScreen(
    navController: NavController,
) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(
                id = R.drawable.food_bg_dark
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
                            color = Color.White
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
                            color = Comet_300
                        )
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Column {
                        Button(
                            onClick = {
                                navController.navigate(Screen.Auth.Register.route)
                            },
                            modifier = Modifier
                                .fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = BurntSienna_600,
                                contentColor = Color.White
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
                                navController.navigate(Screen.Auth.Login.route)
                            },
                            modifier = Modifier
                                .fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = BurntSienna_300,
                                contentColor = BurntSienna_900
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

