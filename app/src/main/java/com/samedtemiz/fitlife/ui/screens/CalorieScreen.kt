package com.samedtemiz.fitlife.ui.screens

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.compose.BurntSienna_400
import com.example.compose.BurntSienna_500
import com.example.compose.Licorice_500
import com.example.compose.Licorice_800
import com.example.compose.Licorice_900
import com.samedtemiz.fitlife.R
import com.samedtemiz.fitlife.components.NormalTextBoxComponent
import com.samedtemiz.fitlife.components.SearchBarTextComponent
import com.samedtemiz.fitlife.data.model.ingredient.Ingredient
import com.samedtemiz.fitlife.viewmodel.CalorieViewModel

@Preview(showSystemUi = true, heightDp = 700)
@Composable
fun CalorieScreenPreview() {
    CalorieScreen()
}

@Composable
fun CalorieScreen(calorieViewModel: CalorieViewModel = viewModel()) {
    val ingredientList = calorieViewModel.ingredientList.observeAsState(initial = emptyList()).value

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(
                id = R.drawable.dark_bg
            ),
            contentDescription = "Calorie Screen",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.15f)
            ) {

                Box(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxSize()
                ) {
                    //Search box
                    SearchBarTextComponent(
                        label = "Search",
                        placeholder = "Please enter a ingredient name",
                        trailingIcon = {
                            Icons.Default.Search
                        }
                    )
                }

            }
            Divider(color = Color.White, thickness = 1.dp)

            IngredientList(ingredientList)
        }
    }
}

@Composable
fun IngredientList(ingredients: List<Ingredient>) {
    LazyColumn {
        items(items = ingredients) { ingredient ->
            IngredientCard(ingredient = ingredient)
        }
    }
}


@Composable
fun IngredientCard(ingredient: Ingredient) {
    val headerFont = R.font.posterama_light
    val font = R.font.avenir_next
    val iconColor = BurntSienna_500

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .padding(0.dp, 10.dp, 0.dp, 0.dp)
    ) {
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            modifier = Modifier
                .padding(20.dp, 0.dp)
                .fillMaxSize(),
        ) {

            Column(
                Modifier
                    .fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.25f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = ingredient.name,
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = FontFamily(
                                Font(headerFont)
                            ),
                            color = Licorice_900
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }


                Row(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.35f)
                            .fillMaxHeight(),
                        contentAlignment = Alignment.Center
                    ) {
                        AsyncImage(
                            model = ingredient.image,
                            contentDescription = "recipe.title",
                            modifier = Modifier
                                .size(80.dp)
                                .clip(shape = RoundedCornerShape(20.dp)),
                            alignment = Alignment.Center,
                            contentScale = ContentScale.Fit
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
                                    "Calories: ${ingredient.calories}",
                                    fontSize = 11.sp,
                                    color = Licorice_900,
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
                                    "Protein: ${ingredient.protein}",
                                    fontSize = 11.sp,
                                    color = Licorice_900,
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
                                    "Fat: ${ingredient.fat}",
                                    fontSize = 11.sp,
                                    color = Licorice_900,
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
                                    "Sugar: ${ingredient.sugar}",
                                    fontSize = 11.sp,
                                    color = Licorice_900,
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