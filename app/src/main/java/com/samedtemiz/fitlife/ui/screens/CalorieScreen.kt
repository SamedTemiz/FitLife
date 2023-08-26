package com.samedtemiz.fitlife.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.AppTheme
import com.samedtemiz.fitlife.R

@Preview(showSystemUi = true, heightDp = 700)
@Composable
fun CalorieScreenPreview() {
    AppTheme {
        CalorieScreen()
    }
}

@Composable
fun CalorieScreen() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.2f)
                    .background(Color.Blue)
            ) {

                Box(
                    modifier = Modifier
                        .padding(30.dp)
                        .fillMaxSize()
                        .background(Color.Magenta)
                )

            }

            val headerFont = R.font.posterama_light
            val font = R.font.avenir_next
            val iconColor = MaterialTheme.colorScheme.primary
            val scrollState = rememberScrollState()


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(state = scrollState)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(130.dp)
                        .padding(0.dp, 10.dp, 0.dp, 0.dp)
                ) {
                    Card(
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier
                            .padding(20.dp, 0.dp)
                            .fillMaxSize(),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground),
                    ) {

                        Row(Modifier.fillMaxSize()) {

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(0.35f)
                                    .fillMaxHeight()
                                    .background(Color(0xFFffede9)),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.apple),
                                    contentDescription = "recipe.title",
                                    modifier = Modifier
                                        .size(100.dp)
                                        .clip(shape = RoundedCornerShape(20.dp)),
                                    alignment = Alignment.Center,
                                    contentScale = ContentScale.Fit
                                )
                            }

                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color(0xFFfbf0f2))
                            ) {

                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight(0.3f),
                                    contentAlignment = Alignment.BottomStart
                                ) {
                                    Text(
                                        text = "Pineapple",
                                        style = TextStyle(
                                            fontSize = 20.sp,
                                            fontFamily = FontFamily(
                                                Font(headerFont)
                                            )
                                        ),
                                        modifier = Modifier.padding(13.dp, 0.dp)
                                    )
                                }

                                Row(
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    horizontalArrangement = Arrangement.SpaceEvenly,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(
                                        Modifier.fillMaxHeight(),
                                        verticalArrangement = Arrangement.SpaceEvenly
                                    ) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Icon(
                                                painter = painterResource(R.drawable.calories),
                                                contentDescription = "ReadyMinutes",
                                                modifier = Modifier.size(18.dp),
                                                tint = iconColor
                                            )
                                            Spacer(Modifier.width(3.dp))
                                            Text(
                                                "Calories: 452.5",
                                                fontSize = 11.sp,
                                                color = Color(0xFF231b00),
                                                fontFamily = FontFamily(
                                                    Font(font)
                                                )
                                            )
                                        }

                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Icon(
                                                painter = painterResource(R.drawable.protein),
                                                contentDescription = "ReadyMinutes",
                                                modifier = Modifier.size(18.dp),
                                                tint = iconColor
                                            )
                                            Spacer(Modifier.width(3.dp))
                                            Text(
                                                "Protein: 4.89",
                                                fontSize = 11.sp,
                                                color = Color.Black,
                                                fontFamily = FontFamily(
                                                    Font(font)
                                                )
                                            )
                                        }

                                    }

                                    Column(
                                        Modifier.fillMaxHeight(),
                                        verticalArrangement = Arrangement.SpaceEvenly
                                    ) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Icon(
                                                painter = painterResource(R.drawable.fat),
                                                contentDescription = "ReadyMinutes",
                                                modifier = Modifier.size(18.dp),
                                                tint = iconColor
                                            )
                                            Spacer(Modifier.width(3.dp))
                                            Text(
                                                "Fat: 1.09",
                                                fontSize = 11.sp,
                                                color = Color.Black,
                                                fontFamily = FontFamily(
                                                    Font(font)
                                                )
                                            )
                                        }

                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Icon(
                                                painter = painterResource(R.drawable.sugar),
                                                contentDescription = "ReadyMinutes",
                                                modifier = Modifier.size(18.dp),
                                                tint = iconColor
                                            )
                                            Spacer(Modifier.width(3.dp))
                                            Text(
                                                "Sugar: 89.14",
                                                fontSize = 11.sp,
                                                color = Color.Black,
                                                fontFamily = FontFamily(
                                                    Font(font)
                                                )
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
