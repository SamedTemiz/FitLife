package com.samedtemiz.fitlife.ui.screens.main

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.samedtemiz.fitlife.ui.screens.CalorieScreen
import com.samedtemiz.fitlife.ui.screens.HealthScreen
import com.samedtemiz.fitlife.ui.screens.HomeScreen
import com.samedtemiz.fitlife.ui.screens.ProfileScreen
import com.samedtemiz.fitlife.ui.screens.Recipe.RecipeScreen
import com.samedtemiz.fitlife.ui.viewmodel.RecipeViewModel


@Composable
fun BottomNavGraph(navController: NavHostController) {
    val recipeViewModel: RecipeViewModel = viewModel()


    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(
            route = BottomBarScreen.Recipe.route,
            exitTransition = exit_RightAnimation(),
            popEnterTransition = enter_RightAnimation()
        ) {
            RecipeScreen(recipeViewModel)
        }

        composable(
            route = BottomBarScreen.Calorie.route,
            exitTransition = exit_RightAnimation(),
            popEnterTransition = enter_RightAnimation()
        ) {
            CalorieScreen()
        }

        composable(
            route = BottomBarScreen.Home.route,
            exitTransition = exit_RightAnimation(),
            popEnterTransition = enter_RightAnimation()
        ) {
            HomeScreen()
        }

        composable(
            route = BottomBarScreen.Health.route,
            exitTransition = exit_RightAnimation(),
            popEnterTransition = enter_RightAnimation()
        ) {
            HealthScreen()
        }

        composable(
            route = BottomBarScreen.Profile.route,
            exitTransition = exit_RightAnimation(),
            popEnterTransition = enter_RightAnimation()
        ) {
            ProfileScreen()
        }
    }
}




fun exit_RightAnimation(): @JvmSuppressWildcards() (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? {
    return {
        slideOutHorizontally(
            targetOffsetX = { -300 },
            animationSpec = tween(
                durationMillis = 300,
                easing = FastOutSlowInEasing
            )
        ) + fadeOut(animationSpec = tween(300))
    }
}


fun enter_RightAnimation(): @JvmSuppressWildcards() (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? {
    return {
        slideInHorizontally(
            initialOffsetX = { 300 },
            animationSpec = tween(
                durationMillis = 300,
                easing = FastOutSlowInEasing
            )
        ) + fadeIn(animationSpec = tween(300))
    }
}