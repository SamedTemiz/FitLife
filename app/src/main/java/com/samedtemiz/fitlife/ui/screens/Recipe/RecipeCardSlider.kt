package com.samedtemiz.fitlife.ui.screens.Recipe

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
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
import androidx.compose.ui.util.lerp
import coil.compose.AsyncImage
import com.example.compose.AppTheme
import com.google.accompanist.pager.*
import com.samedtemiz.fitlife.R
import com.samedtemiz.fitlife.data.api.model.Recipe
import kotlin.math.absoluteValue


@ExperimentalPagerApi
@Composable
fun RecipeCardSlider(recipes: List<Recipe>) {

    val pagerState = rememberPagerState(
        pageCount = recipes.size,
        initialPage = 0
    )

    // Auto scroll
    /*
    LaunchedEffect(Unit) {
        while (true) {
            yield()
            delay(3000)
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % (pagerState.pageCount),
                animationSpec = tween(600)
            )
        }
    }*/

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
    ) {

        //Horizontal dot indicator
        HorizontalPagerIndicator(
            pagerState = pagerState, modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(0.dp, 20.dp, 0.dp, 0.dp),
            activeColor = MaterialTheme.colorScheme.onBackground,
            inactiveColor = MaterialTheme.colorScheme.secondaryContainer,
            indicatorWidth = 10.dp
        )

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .weight(1f)
        ) { page ->
            Card(modifier = Modifier
                .graphicsLayer {
                    val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                    lerp(
                        start = 0.85f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale

                    }
                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )

                }
                .fillMaxWidth()
                .padding(25.dp, 20.dp, 25.dp, 40.dp),
//                border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground),
                shape = RoundedCornerShape(20.dp),
                backgroundColor = MaterialTheme.colorScheme.secondaryContainer
            ) {
                // Card content
                RecipeCard(recipes[page])
            }

        }
    }
}


@Composable
fun RecipeCard(recipe: Recipe) {
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
            AsyncImage(
//                painter = painterResource(id = R.drawable.deneme_food),
                model = recipe.image,
                contentDescription = recipe.title,
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
                            text = recipe.title,
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
                            Text(
                                "${recipe.readyInMinutes} minutes",
                                fontSize = 11.sp,
                                color = contentColor
                            )

                            Spacer(Modifier.width(10.dp))

                            Icon(
                                painter = painterResource(R.drawable.group_people),
                                contentDescription = "ReadyMinutes",
                                modifier = Modifier.size(22.dp),
                                tint = contentColor
                            )
                            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                            Text(
                                "Serves ${recipe.servings}",
                                fontSize = 11.sp,
                                color = contentColor
                            )

                            Spacer(Modifier.width(10.dp))

                            Icon(
                                painter = painterResource(R.drawable.healthy_food),
                                contentDescription = "ReadyMinutes",
                                modifier = Modifier.size(22.dp),
                                tint = contentColor
                            )
                            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                            Text(
                                "Healthy %${recipe.healthScore}", fontSize = 11.sp,
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