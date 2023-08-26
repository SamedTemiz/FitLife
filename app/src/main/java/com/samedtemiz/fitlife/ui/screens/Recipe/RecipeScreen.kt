package com.samedtemiz.fitlife.ui.screens.Recipe

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.ExperimentalPagerApi
import com.samedtemiz.fitlife.ui.viewmodel.RecipeViewModel

//@Preview(showSystemUi = true, heightDp = 700)
//@Preview(showSystemUi = true, heightDp = 700, uiMode = UI_MODE_NIGHT_YES)
//@Composable
//fun RecipeScreenPreview() {
//    AppTheme {
//        RecipeScreen()
//    }
//}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun RecipeScreen(recipeViewModel: RecipeViewModel) {

    val recipes = recipeViewModel.recipes.value

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        RecipeCardSlider(recipes = recipes)
    }
}