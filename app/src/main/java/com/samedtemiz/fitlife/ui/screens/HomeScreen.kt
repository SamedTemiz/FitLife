package com.samedtemiz.fitlife.ui.screens

import android.Manifest
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.samedtemiz.fitlife.R
import com.samedtemiz.fitlife.components.RequestMultiplePermissions
import com.samedtemiz.fitlife.viewmodel.HomeViewModel

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Background
        Image(
            painter = painterResource(
                id = R.drawable.dark_bg
            ),
            contentDescription = "Home Screen",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Permission request
        RequestMultiplePermissions(
            permissions = listOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            content = { HomeScreenContent() }
        )
    }
}


@Composable
fun HomeScreenContent(homeViewModel: HomeViewModel = viewModel()) {
    val location by homeViewModel.location.observeAsState()

    Box(contentAlignment = Alignment.Center) {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.LightGray),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            location?.let { loc ->
                Text(text = "LATITUDE: ${loc.latlng.latitude}")
                Text(text = "LONGITUDE: ${loc.latlng.longitude}")
                Text(text = "COUNTRY: ${loc.country}")
                Text(text = "CITY: ${loc.city}")
                Text(text = "DISTRICT: ${loc.district}")

                homeViewModel.isProcess = false
            }
            Text(text = "içerik sxvxcv")
        }

        if(homeViewModel.isProcess){
            CircularProgressIndicator()
        }
    }

}

@Preview(showSystemUi = true, heightDp = 700)
@Composable
fun HomeScreenPreview() {
    HomeScreenContent(homeViewModel = viewModel())
}





