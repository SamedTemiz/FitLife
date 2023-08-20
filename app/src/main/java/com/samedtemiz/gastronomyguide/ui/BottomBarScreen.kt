package com.samedtemiz.gastronomyguide.ui

import com.samedtemiz.gastronomyguide.R

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int
){

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
