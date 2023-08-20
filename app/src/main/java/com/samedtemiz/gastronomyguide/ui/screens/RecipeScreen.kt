package com.samedtemiz.gastronomyguide.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.samedtemiz.gastronomyguide.R

@Preview(showSystemUi = true, heightDp = 700)
@Composable
fun RecipeScreenPreview() {
    RecipeScreen()
}

@Composable
fun RecipeScreen() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.19f),
                    contentAlignment = Alignment.Center
                ) {

                    Column(
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        Text(
                            text = "Discover",
                            fontSize = 48.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF222222),
                            fontFamily = FontFamily(
                                Font(R.font.avenir_next, weight = FontWeight.Normal)
                            )
                        )
                        Text(
                            text = "Your daily inspirations of recipe",
                            fontSize = 18.sp,
                            color = Color(0xFF8d9395),
                            fontFamily = FontFamily(
                                Font(R.font.avenir_next, weight = FontWeight.Normal)
                            )
                        )
                    }

                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .align(androidx.compose.ui.Alignment.Center)
                            .padding(20.dp)
                            .clip(
                                shape = RoundedCornerShape(5)
                            )
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.bg),
                            contentDescription = "Login",
                            modifier = Modifier
                                .fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )

                    }

                }

            }
        }
    }
}