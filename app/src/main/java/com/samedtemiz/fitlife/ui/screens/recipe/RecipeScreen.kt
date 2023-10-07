package com.samedtemiz.fitlife.ui.screens.recipe

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
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
import androidx.compose.ui.draw.alpha
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.compose.BurntSienna_500
import com.example.compose.BurntSienna_800
import com.example.compose.BurntSienna_900
import com.example.compose.Comet_300
import com.example.compose.Licorice_500
import com.example.compose.Licorice_600
import com.example.compose.Licorice_700
import com.example.compose.Licorice_800
import com.example.compose.RegentBlue_500
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import com.samedtemiz.fitlife.R
import com.samedtemiz.fitlife.data.model.recipe.Recipe
import com.samedtemiz.fitlife.viewmodel.RecipeViewModel
import kotlin.math.absoluteValue


@OptIn(ExperimentalPagerApi::class)
@Composable
fun RecipeScreen(
    recipeViewModel: RecipeViewModel,
    navController: NavController
) {

    val recipes = recipeViewModel.recipes.value

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(
                id = R.drawable.pear_bg
            ),
            contentDescription = "Recipe Screen",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        if (recipes.isNotEmpty()) {
            RecipeCardSlider(recipes = recipes, navController)
        }
    }
}

@ExperimentalPagerApi
@Composable
fun RecipeCardSlider(recipes: List<Recipe>, navController: NavController) {

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
            .fillMaxSize(),
    ) {

        //Horizontal dot indicator
        HorizontalPagerIndicator(
            pagerState = pagerState, modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(0.dp, 20.dp, 0.dp, 0.dp),
            activeColor = BurntSienna_500,
            inactiveColor = BurntSienna_800,
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
                .padding(20.dp, 20.dp, 20.dp, 20.dp),
//                border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground),
                shape = RoundedCornerShape(20.dp),
                backgroundColor = Licorice_600
            ) {
                // Card content
                RecipeCard(recipes[page], navController)
            }

        }
    }
}


@Composable
fun RecipeCard(recipe: Recipe, navController: NavController) {

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
            if (recipe.image != null) {
                AsyncImage(
//                painter = painterResource(id = R.drawable.deneme_food),
                    model = recipe.image,
                    contentDescription = "Recipe",
                    modifier = Modifier
                        .fillMaxSize(),
                    alignment = Alignment.Center,
                    contentScale = ContentScale.FillHeight
                )
            } else {
                ImageNotFound()
            }
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

                    val contentColor = Color.White
                    Column(modifier = Modifier.padding(10.dp)) {
                        recipe.title?.let { title ->
                            Text(
                                text = title,
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
                                            contentColor
                                        )
                                    )
                                    .padding(5.dp)
                                    .fillMaxWidth()
                            )
                        }


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
                            recipe.readyInMinutes?.let { minutes ->
                                Text(
                                    "$minutes minutes",
                                    fontSize = 12.sp,
                                    color = contentColor
                                )
                            }


                            Spacer(Modifier.width(10.dp))

                            Icon(
                                painter = painterResource(R.drawable.group_people),
                                contentDescription = "ReadyMinutes",
                                modifier = Modifier.size(22.dp),
                                tint = contentColor
                            )
                            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                            recipe.servings?.let { servings ->
                                Text(
                                    "Serves $servings",
                                    fontSize = 12.sp,
                                    color = contentColor
                                )
                            }


                            Spacer(Modifier.width(10.dp))

                            Icon(
                                painter = painterResource(R.drawable.healthy_food),
                                contentDescription = "ReadyMinutes",
                                modifier = Modifier.size(22.dp),
                                tint = contentColor
                            )
                            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                            recipe.healthScore?.let { health ->
                                Text(
                                    "Healthy %$health", fontSize = 12.sp,
                                    textAlign = TextAlign.Center,
                                    color = contentColor,
                                )
                            }
                        }

                        Spacer(Modifier.height(10.dp))
                        Divider(color = contentColor, thickness = 1.dp)
                    }

                }

                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {

                    Button(
                        onClick = {
                            navController.navigate("detail/" + recipe.id)
                        },
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
                                    color = BurntSienna_500,
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
                                color = Color.White
                            )

                        }

                    }
                }

            }

        }

    }
}

@Composable
fun ImageNotFound() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                painter = painterResource(id = R.drawable.dish),
                contentDescription = "Image not found",
                tint = Color.White,
                modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "Image not found",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(
                    Font(R.font.avenir_next)
                ),
                color = Color.White
            )
        }
    }
}

@Preview(showSystemUi = true, heightDp = 700)
@Composable
fun RecipeScreenPreview() {
//    RecipeScreen(
//        recipeViewModel = viewModel(),
//        navController = rememberNavController()
//    )
    ImageNotFound()
}