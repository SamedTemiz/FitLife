package com.samedtemiz.fitlife.ui.screens.Recipe

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import com.google.accompanist.pager.ExperimentalPagerApi
import com.samedtemiz.fitlife.R

@Preview(showSystemUi = true, heightDp = 700)
@Preview(showSystemUi = true, heightDp = 700, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun RecipeScreenPreview() {
    AppTheme {
        RecipeScreen()
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun RecipeScreen() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.outline)
    ) {

        // Recipe cards showing in slider
        ViewPagerSlider()

    }
}

@Composable
fun RecipeCard() {
    val scrollState = rememberScrollState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.deneme_food),
//                    model = "https://spoonacular.com/recipeImages/633668-556x370.jpg",
                contentDescription = "Recipe",
                modifier = Modifier
                    .fillMaxSize(),
                alignment = Alignment.CenterStart,
                contentScale = ContentScale.FillHeight
            )
        }


        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Column(
                modifier = Modifier.padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                    val contentColor = MaterialTheme.colorScheme.onBackground
                    Column(modifier = Modifier.padding(10.dp)) {
                        Text(
                            text = "Baked Lemon~Lime Chicken Wings",
                            style = TextStyle(
                                fontSize = 24.sp,
                                textAlign = TextAlign.Center,
                                fontFamily = FontFamily(
                                    Font(R.font.posterama_light)
                                )
                            ),
                            color = contentColor,
                            letterSpacing = 2.sp,
                            modifier = Modifier
                                .border(
                                    BorderStroke(
                                        1.dp,
                                        MaterialTheme.colorScheme.onBackground
                                    )
                                )
                                .padding(5.dp)
                                .fillMaxWidth()
                        )

                        Spacer(Modifier.height(10.dp))

                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.timer),
                                contentDescription = "ReadyMinutes",
                                modifier = Modifier.size(22.dp),
                                tint = contentColor
                            )
                            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                            Text("45 minutes", fontSize = 11.sp, color = contentColor)

                            Spacer(Modifier.width(10.dp))

                            Icon(
                                painter = painterResource(R.drawable.group_people),
                                contentDescription = "ReadyMinutes",
                                modifier = Modifier.size(22.dp),
                                tint = contentColor
                            )
                            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                            Text("Serves 4", fontSize = 11.sp, color = contentColor)

                            Spacer(Modifier.width(10.dp))

                            Icon(
                                painter = painterResource(R.drawable.healthy_food),
                                contentDescription = "ReadyMinutes",
                                modifier = Modifier.size(22.dp),
                                tint = contentColor
                            )
                            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                            Text(
                                "Healthy %100", fontSize = 11.sp,
                                textAlign = TextAlign.Center,
                                color = contentColor,
                            )
                        }

                        Spacer(Modifier.height(10.dp))
                        Divider(color = Color.Black, thickness = 1.dp)
                    }

                }

                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {

                    Button(
                        onClick = {},
                        modifier = Modifier
                            .fillMaxWidth(0.5f),
                        contentPadding = PaddingValues(),
                        colors = ButtonDefaults.buttonColors(Color.Transparent),
                    ) {

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(48.dp)
                                .background(
                                    color = MaterialTheme.colorScheme.primary,
                                    shape = RoundedCornerShape(20.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {

                            Text(
                                text = "Detail",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily(
                                    Font(R.font.avenir_next)
                                ),
                                color = MaterialTheme.colorScheme.surfaceVariant
                            )

                        }

                    }
                }

            }

        }

    }
}