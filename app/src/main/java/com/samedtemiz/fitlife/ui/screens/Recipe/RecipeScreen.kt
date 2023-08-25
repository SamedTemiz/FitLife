package com.samedtemiz.fitlife.ui.screens.Recipe

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.AppTheme
import com.samedtemiz.fitlife.ui.viewmodel.RecipeViewModel

@Preview(showSystemUi = true, heightDp = 700)
@Preview(showSystemUi = true, heightDp = 700, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun RecipeScreenPreview() {
    AppTheme {
        RecipeScreen(recipeViewModel = RecipeViewModel())
    }
}


@Composable
fun RecipeScreen(recipeViewModel: RecipeViewModel) {


    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.outline)
    ) {

    }
}