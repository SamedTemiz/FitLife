package com.samedtemiz.fitlife.ui.screens.recipe

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.compose.BurntSienna_500
import com.example.compose.Comet_300
import com.example.compose.Licorice_700
import com.example.compose.Licorice_800
import com.google.android.material.textview.MaterialTextView
import com.samedtemiz.fitlife.R
import com.samedtemiz.fitlife.data.model.recipe.Recipe
import com.samedtemiz.fitlife.viewmodel.RecipeViewModel
import java.util.Locale

@Composable
fun DetailScreen(
    recipeId: Int,
    recipeViewModel: RecipeViewModel,
) {

    val selectedRecipe = recipeViewModel.selectedRecipe.collectAsState()

    LaunchedEffect(recipeId) {
        recipeViewModel.fetchRecipeDetail(recipeId)
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Licorice_800)
        ) {
            selectedRecipe.value?.let { recipe ->
                Header(recipe)
                Content(recipe)
            }
        }
    }
}

@Composable
fun Header(recipe: Recipe) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
    ) {

        // Üst taraf
        Box(modifier = Modifier.fillMaxHeight(0.75f))
        {
            AsyncImage(
//                painter = painterResource(id = R.drawable.deneme_food),
                model = recipe.image,
                contentDescription = "Recipe",
                modifier = Modifier
                    .fillMaxSize(),
                alignment = Alignment.CenterStart,
                contentScale = ContentScale.FillBounds
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(0.dp, 20.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .alpha(0.7f)
                        .background(Licorice_700)
                        .padding(10.dp)
                ) {

                    val cuisine =
                        if (recipe.cuisines.isEmpty()) "CUISINE: UNKNOWN" else recipe.cuisines[0].toString()
                            .uppercase(Locale.getDefault())
                    val dishType =
                        if (recipe.dishTypes.isEmpty()) "DISH TYPE: UNKNOWN" else recipe.dishTypes[0].uppercase(
                            Locale.getDefault()
                        )
                    Row {

                        Text(
                            text = cuisine,
                            style = TextStyle(
                                fontSize = 16.sp,
                                textAlign = TextAlign.Left,
                            ),
                            color = Color.White,
                            letterSpacing = 2.sp,
                            modifier = Modifier.fillMaxWidth(0.6f)
                        )

                        Text(
                            text = dishType,
                            style = TextStyle(
                                fontSize = 16.sp,
                                textAlign = TextAlign.Left,
                            ),
                            color = Color.White,
                            letterSpacing = 2.sp,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    Text(
                        text = recipe.title,
                        style = TextStyle(
                            fontSize = 22.sp,
                            textAlign = TextAlign.Left,
                            fontWeight = FontWeight.ExtraBold,
                            fontFamily = FontFamily(
                                Font(R.font.posterama_light)
                            )
                        ),
                        color = Color.White,
                        letterSpacing = 2.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
        }

        // Alt taraf
        val contentColor = Color.White
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BurntSienna_500)
        ) {
            Row(modifier = Modifier.fillMaxSize())
            {
                Box(
                    Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    Column {
                        Text(
                            text = "Ready Time",
                            style = TextStyle(
                                fontSize = 16.sp,
                                textAlign = TextAlign.Left,
                            ),
                            color = contentColor,
                            modifier = Modifier.alpha(0.7f)
                        )

                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "${recipe.readyInMinutes}mins",
                            style = TextStyle(
                                fontSize = 18.sp,
                                textAlign = TextAlign.Left,
                                fontWeight = FontWeight.Light,
                                fontFamily = FontFamily(
                                    Font(R.font.avenir_next)
                                )
                            ),
                            color = contentColor,
                        )
                    }
                }

                Box(
                    Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    Column {
                        Text(
                            text = "Serves",
                            style = TextStyle(
                                fontSize = 16.sp,
                                textAlign = TextAlign.Left,
                            ),
                            color = contentColor,
                            modifier = Modifier.alpha(0.7f)
                        )

                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "${recipe.servings} Person",
                            style = TextStyle(
                                fontSize = 18.sp,
                                textAlign = TextAlign.Left,
                                fontFamily = FontFamily(
                                    Font(R.font.avenir_next)
                                ),
                                color = contentColor,
                            )
                        )
                    }
                }

                Box(
                    Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    Column {
                        Text(
                            text = "Health Score",
                            style = TextStyle(
                                fontSize = 16.sp,
                                textAlign = TextAlign.Left,
                            ),
                            color = contentColor,
                            modifier = Modifier.alpha(0.7f)
                        )

                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "%${recipe.healthScore}",
                            style = TextStyle(
                                fontSize = 18.sp,
                                textAlign = TextAlign.Left,
                                fontWeight = FontWeight.Light,
                                fontFamily = FontFamily(
                                    Font(R.font.avenir_next)
                                ),
                                color = contentColor,
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Content(recipe: Recipe) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .verticalScroll(state = scrollState)
            .background(Licorice_700)
    ) {
        Column(
            Modifier
                .padding(20.dp)

        ) {

            val circleColor = Color.White
            Text(
                text = "Ingredients",
                style = TextStyle(
                    fontSize = 24.sp,
                    textAlign = TextAlign.Left,
                    fontFamily = FontFamily(
                        Font(R.font.avenir_next)
                    )
                ),
                color = Comet_300,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)
            )

            Spacer(Modifier.height(20.dp))
            Column {
                for (item in recipe.extendedIngredients) {
                    Row(Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
                        Canvas(
                            modifier = Modifier
                                .padding(end = 15.dp)
                                .size(10.dp)
                        ) {
                            drawCircle(color = circleColor)
                        }
                        Text(
                            text = item.original,
                            fontSize = 18.sp,
                            color = Comet_300,
                        )
                    }
                }

            }
        }

        Column(
            Modifier
                .padding(20.dp)
        ) {
            val instructionsState = remember { mutableStateOf(recipe.instructions.isEmpty()) }

            if (instructionsState.value) {
                Text(
                    text = "Instructions",
                    style = TextStyle(
                        fontSize = 24.sp,
                        textAlign = TextAlign.Left,
                        fontFamily = FontFamily(
                            Font(R.font.avenir_next)
                        )
                    ),
                    color = Comet_300,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp)
                )

                Spacer(Modifier.height(20.dp))

                HtmlText(
                    html = recipe.instructions,
                    modifier = Modifier.padding(8.dp)
                )
            } else {
                Text(
                    text = "Source: ${recipe.spoonacularSourceUrl}",
                    style = TextStyle(
                        fontSize = 24.sp,
                        textAlign = TextAlign.Left,
                        fontFamily = FontFamily(
                            Font(R.font.avenir_next)
                        )
                    ),
                    color = Comet_300,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp)
                )
            }
        }
    }
}

@Composable
fun HtmlText(html: String, modifier: Modifier = Modifier) {
    val textColor = MaterialTheme.colorScheme.onBackground
    AndroidView(
        modifier = modifier,
        factory = { context ->
            MaterialTextView(context).apply {
                textSize = 18f
                textAlignment = left
                setTextColor(textColor.toArgb())
            }
        },
        update = { it.text = HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_COMPACT) }
    )
}

@Preview(showSystemUi = true)
@Composable
fun DetailScreenPreview() {
    DetailScreen(0, viewModel())
}
