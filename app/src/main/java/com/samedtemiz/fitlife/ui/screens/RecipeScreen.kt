package com.samedtemiz.fitlife.ui.screens

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.compose.AppTheme
import com.samedtemiz.fitlife.R
import com.samedtemiz.fitlife.data.remote.model.Recipe
import com.samedtemiz.fitlife.ui.viewmodel.RecipeViewModel

@Preview(showSystemUi = true, heightDp = 700)
@Preview(showSystemUi = true, heightDp = 700, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun RecipeScreenPreview() {
    AppTheme {
        RecipeScreen()
    }
}

@Composable
fun RecipeScreen(viewModel: RecipeViewModel? = viewModel()) {

//    viewModel?.fetchRandomRecipes()
//    val recipe = viewModel?.recipeLiveData?.value

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.outline)
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.4f),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
//                    painter = painterResource(id = R.drawable.deneme_food),
                    model = "https://spoonacular.com/recipeImages/633668-556x370.jpg",
                    contentDescription = "Recipe",
                    modifier = Modifier
                        .fillMaxSize(),
                    alignment = Alignment.CenterStart,
                    contentScale = ContentScale.Fit
                )
            }


            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {

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
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.timer),
                                    contentDescription = "ReadyMinutes",
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                                Text("45 minutes", fontSize = 12.sp)

                                Spacer(Modifier.width(16.dp))

                                Icon(
                                    painter = painterResource(R.drawable.group_people),
                                    contentDescription = "ReadyMinutes",
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                                Text("Serves 4", fontSize = 12.sp)

                                Spacer(Modifier.width(16.dp))

                                Icon(
                                    painter = painterResource(R.drawable.healthy_food),
                                    contentDescription = "ReadyMinutes",
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                                Text(
                                    "Healthy %3", fontSize = 12.sp,
                                    textAlign = TextAlign.Center,
                                )
                            }
                        }

                    }

                    Spacer(Modifier.height(20.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Blue)
                    ) {

                        Column {
                            Text(text = "This chicken salad is a lunchtime delight! Packed with creamy avocado, tender chicken, and crunchy veggies, it's a healthy and satisfying meal that won't weigh you down. Tossed in a tangy yogurt dressing with a hint of spice, it's a flavor explosion that's perfect for a light meal.")
                          }

                    }

                }

            }

        }

    }
}

@Composable
fun RecipeCard(recipe: Recipe? = null) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .clip(
                shape = RoundedCornerShape(5)
            )
    ) {
    }
}