package com.samedtemiz.fitlife.ui.screens.Recipe

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.example.compose.AppTheme
import com.google.accompanist.pager.*
import com.samedtemiz.fitlife.data.model.recipesList
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Preview(heightDp = 700)
@Composable
fun Preview() {
    AppTheme {
        ViewPagerSlider()
    }
}


@ExperimentalPagerApi
@Composable
fun ViewPagerSlider() {

    val pagerState = rememberPagerState(
        pageCount = recipesList.size,
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
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground),
                shape = RoundedCornerShape(20.dp),
                backgroundColor = MaterialTheme.colorScheme.surfaceVariant
            ) {
                // Card content
                RecipeCard()
            }

        }
    }
}