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
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.samedtemiz.fitlife.ui.screens.CalorieScreen
import com.samedtemiz.fitlife.ui.screens.health.HealthScreen
import com.samedtemiz.fitlife.ui.screens.HomeScreen
import com.samedtemiz.fitlife.ui.screens.ProfileScreen
import com.samedtemiz.fitlife.ui.screens.health.HealthResultScreen
import com.samedtemiz.fitlife.ui.screens.recipe.DetailScreen
import com.samedtemiz.fitlife.ui.screens.recipe.RecipeScreen
import com.samedtemiz.fitlife.viewmodel.CalorieViewModel
import com.samedtemiz.fitlife.viewmodel.HealthViewModel
import com.samedtemiz.fitlife.viewmodel.ProfileViewModel
import com.samedtemiz.fitlife.viewmodel.RecipeViewModel


const val DETAIL_ROUTE = "detail/{id}"
const val HEALTH_RESULT_ROUTE = "healthResult"

@Composable
fun BottomNavGraph(navController: NavHostController, mainController: NavController, profileViewModel: ProfileViewModel) {
    val recipeViewModel: RecipeViewModel = viewModel()
    val calorieViewModel: CalorieViewModel = viewModel()
    val healthViewModel: HealthViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "base"
    ) {
        navigation(
            startDestination = BottomBarScreen.Base.Home.route,
            route = "base"
        ){
            composable(
                route = BottomBarScreen.Base.Recipe.route,
                exitTransition = exit_RightAnimation(),
                popEnterTransition = enter_RightAnimation()
            ) {
                RecipeScreen(recipeViewModel, navController)
            }

            composable(
                route = BottomBarScreen.Base.Calorie.route,
                exitTransition = exit_RightAnimation(),
                popEnterTransition = enter_RightAnimation()
            ) {
                CalorieScreen(calorieViewModel)
            }

            composable(
                route = BottomBarScreen.Base.Home.route,
                exitTransition = exit_RightAnimation(),
                popEnterTransition = enter_RightAnimation()
            ) {
                HomeScreen()
            }

            composable(
                route = BottomBarScreen.Base.Health.route,
                exitTransition = exit_RightAnimation(),
                popEnterTransition = enter_RightAnimation()
            ) {
                HealthScreen(healthViewModel, navController)
            }

            composable(
                route = BottomBarScreen.Base.Profile.route,
                exitTransition = exit_RightAnimation(),
                popEnterTransition = enter_RightAnimation()
            ) {
                ProfileScreen(profileViewModel){
                    mainController.navigate("auth")
                }
            }
        }

        //Recipe Detail Screen
        composable(
            route = BottomBarScreen.Detail.RecipeDetail.route,
            arguments = listOf(navArgument("id"){
                type = NavType.IntType
            }),
            exitTransition = exit_RightAnimation(),
            popEnterTransition = enter_RightAnimation()
        ) {backStackEntry ->
            backStackEntry.arguments?.getInt("id")?.let { id -> DetailScreen(recipeId = id, recipeViewModel) }
        }

        // Health Result Screen
        composable(
            route = BottomBarScreen.Detail.HealthResult.route,
            exitTransition = exit_RightAnimation(),
            popEnterTransition = enter_RightAnimation()
        ) {
            HealthResultScreen(healthViewModel, navController)
        }
    }
}




fun exit_RightAnimation(): @JvmSuppressWildcards() AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition? {
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
fun enter_RightAnimation(): @JvmSuppressWildcards() AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition? {
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