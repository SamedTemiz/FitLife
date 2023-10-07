package com.samedtemiz.fitlife.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.compose.Comet_300
import com.example.compose.Comet_400
import com.example.compose.Comet_500
import com.samedtemiz.fitlife.R
import com.samedtemiz.fitlife.components.HyperlinkText
import com.samedtemiz.fitlife.navigation.Screen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 3000
        ), label = ""
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(4000)
        navController.popBackStack()
        navController.navigate(Screen.Main.route)
    }

    Splash(alphaAnim.value)
}

@Composable
fun Splash(alpha: Float) {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box(modifier = Modifier.align(Alignment.Center)) {
            Image(
                painter = painterResource(
                    id = R.drawable.logo_with_fitlife
                ),
                contentDescription = "Splash Logo",
                modifier = Modifier
                    .size(250.dp)
                    .alpha(alpha = alpha)
            )
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 50.dp)
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 40.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.info),
                    contentDescription = "Info for license",
                    tint = Color.Black,
                    modifier = Modifier.size(25.dp)
                )

                Spacer(Modifier.width(10.dp))
                HyperlinkText(
                    fullText = "The icon objects in this app were created by Pause08, Freepik, DinosoftLabs, Stockio and Phoenix Group from Flaticon.",
                    hyperLinks = mutableMapOf(
                        "Pause08" to "https://www.flaticon.com/authors/pause08",
                        "Freepik" to "https://www.flaticon.com/authors/freepik",
                        "DinosoftLabs" to "https://www.flaticon.com/authors/dinosoftlabs",
                        "Stockio" to "https://www.flaticon.com/authors/stockio",
                        "Phoenix Group" to "https://www.flaticon.com/authors/phoenix-group",
                        "Flaticon" to "https://www.flaticon.com"
                    ),
                    textStyle = TextStyle(
                        color = Comet_500,
                        textAlign = TextAlign.Left
                    ),
                    linkTextColor = Color.Black,
                    linkTextFontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }
        }

    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    Splash(1f)
}