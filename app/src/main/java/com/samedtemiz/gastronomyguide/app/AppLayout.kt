package com.samedtemiz.gastronomyguide.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.samedtemiz.gastronomyguide.navigation.Navigation
import com.samedtemiz.gastronomyguide.ui.theme.GastronomyGuideTheme


@Composable
fun AppLayout() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Navigation()
    }
}