package com.samedtemiz.fitlife.ui.screens.main

import android.annotation.SuppressLint
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import com.samedtemiz.fitlife.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.compose.BurntSienna_400
import com.example.compose.BurntSienna_500
import com.example.compose.Comet_300
import com.example.compose.Licorice_800
import com.example.compose.Licorice_900
import com.samedtemiz.fitlife.ui.viewmodel.HomeViewModel

@Preview(showSystemUi = true)
@Preview(showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun MainScreenPreview() {
//        MainScreen()
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    mainController: NavController,
    homeViewModel: HomeViewModel
) {

    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        drawerElevation = 0.dp,
        backgroundColor = Licorice_800,
        bottomBar = { BottomBar(navController = navController) },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            BottomNavGraph(navController = navController, mainController, homeViewModel)
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Base.Recipe,
        BottomBarScreen.Base.Calorie,
        BottomBarScreen.Base.Home,
        BottomBarScreen.Base.Health,
        BottomBarScreen.Base.Profile
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
    val contentColor = if (selected) BurntSienna_400 else Comet_300

    Box(
        modifier = Modifier
            .drawBehind {
                val strokeWidth = 4.dp.toPx()
                val borderColor =
                    if (selected)
                        contentColor
                    else {
                        Color.Transparent
                    }
                drawLine(
                    color = borderColor,
                    start = Offset(0f, size.height - strokeWidth / 2),
                    end = Offset(size.width, size.height - strokeWidth / 2),
                    strokeWidth = strokeWidth
                )
            }
            .weight(1f)
            .fillMaxHeight(0.11f)
            .clickable(onClick = {
                if (!selected) {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }
                }
            }), contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Icon(
                painterResource(id = screen.icon),
                contentDescription = "icon",
                tint = contentColor,
                modifier = Modifier.size(28.dp)
            )
            Text(
                modifier = Modifier.padding(0.dp, 15.dp, 0.dp, 0.dp),
                text = screen.title,
                fontWeight = FontWeight.SemiBold,
                color = contentColor,
                fontFamily = FontFamily(
                    Font(R.font.avenir_next, weight = FontWeight.Normal)
                ),
                letterSpacing = 0.05.em
            )
        }
    }
}
