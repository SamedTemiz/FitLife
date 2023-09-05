package com.samedtemiz.fitlife.ui.screens.main

import com.samedtemiz.fitlife.R

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int
) {

    sealed class Base {
        object Recipe : BottomBarScreen(
            route = "recipe",
            title = "Recipe",
            icon = R.drawable.noodles
        )

        object Calorie : BottomBarScreen(
            route = "calories",
            title = "Calories",
            icon = R.drawable.calorie
        )

        object Home : BottomBarScreen(
            route = "home",
            title = "Home",
            icon = R.drawable.home
        )

        object Health : BottomBarScreen(
            route = "health",
            title = "Health",
            icon = R.drawable.health
        )

        object Profile : BottomBarScreen(
            route = "profile",
            title = "Profile",
            icon = R.drawable.user
        )
    }

    sealed class Detail{
        object RecipeDetail : BottomBarScreen(
            route = DETAIL_ROUTE,
            title = "Recipe Detail",
            icon = -1
        )
        object HealthResult : BottomBarScreen(
            route = HEALTH_RESULT_ROUTE,
            title = "Health Result",
            icon = -1
        )
    }
}
