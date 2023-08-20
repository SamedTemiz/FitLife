package com.samedtemiz.gastronomyguide.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.samedtemiz.gastronomyguide.components.ButtonComponent
import com.samedtemiz.gastronomyguide.data.remote.model.Recipe
import com.samedtemiz.gastronomyguide.ui.DetailViewModel


@Preview(showSystemUi = true)
@Composable
fun DetailScreenPreview() {
    DetailScreen()
}

@Composable
fun DetailScreen(viewModel: DetailViewModel? = viewModel()) {
    viewModel?.fetchRandomRecipes()
    val recipe = viewModel?.recipeLiveData?.value

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
            ) {

                if (recipe != null) {
                    RecipeDetailScreen(recipe = recipe)
                }


            }

            //GERİ BUTONU
            ButtonComponent(
                value = "GERİ",
                onButtonClicked = {
                },
                isEnabled = true
            )
        }

    }
}

@Composable
fun RecipeDetailScreen(recipe: Recipe) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = recipe.image,
            contentDescription = recipe.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = recipe.title,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Servings: ${recipe.servings} | Ready in: ${recipe.readyInMinutes} minutes",
            style = MaterialTheme.typography.titleSmall,
        )
    }
}