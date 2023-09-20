package com.samedtemiz.fitlife.ui.screens

import android.Manifest
import android.annotation.SuppressLint
import android.location.Location
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose.BurntSienna_500
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.samedtemiz.fitlife.R
import com.samedtemiz.fitlife.components.RequestMultiplePermissions
import com.samedtemiz.fitlife.viewmodel.HomeViewModel
import com.samedtemiz.fitlife.viewmodel.LocationData
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

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
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (location != null) {
                location?.let { loc ->
                    LocationOnGoogleMap(loc)
                    homeViewModel.isProcess = false
                }
            }
        }

        if (homeViewModel.isProcess) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                CircularProgressIndicator(color = BurntSienna_500)
                Spacer(modifier = Modifier.height(10.dp))

                var ticks by remember { mutableStateOf(0) }
                LaunchedEffect(Unit) {
                    while (ticks < 10) {
                        delay(1.seconds)
                        ticks++
                    }
                }

                if (ticks < 10) {
                    Text("Waiting for location information...", Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
                } else {
                    Text("It looks like location service is not turned on, please turn location on.", Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
                }
            }
        }
    }

}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun LocationOnGoogleMap(location: LocationData) {
    val latlng = LatLng(location.latlng.latitude, location.latlng.longitude)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(latlng, 15f)
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = MapProperties(isMyLocationEnabled = true)
    ) {
        val coroutineScope = rememberCoroutineScope()
        coroutineScope.launch {
            cameraPositionState.animate(
                CameraUpdateFactory.newCameraPosition(
                    CameraPosition.fromLatLngZoom(
                        latlng, 15f
                    )
                ), 500
            )
        }

        MarkerInfoWindow(
            state = MarkerState(position = latlng),
        ) { marker ->
            Box(
                modifier = Modifier
                    .background(
                        color = Color.LightGray,
                        shape = RoundedCornerShape(35.dp, 35.dp, 35.dp, 35.dp)
                    ),
            ) {


                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.map),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .height(80.dp)
                            .fillMaxWidth(),

                        )
                    //.........................Spacer
                    Spacer(modifier = Modifier.height(24.dp))
                    //.........................Text: title
                    Text(
                        text = "Marker Title",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.headlineSmall,
                        color = BurntSienna_500,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    //.........................Text : description
                    Text(
                        text = "Customizing a marker's info window",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 10.dp, start = 25.dp, end = 25.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.bodyLarge,
                        color = BurntSienna_500,
                    )
                    //.........................Spacer
                    Spacer(modifier = Modifier.height(24.dp))

                }

            }
        }
    }
}

@Composable
fun TimerTicks(
    initTick: Long = 1_000L,
    interval: Long = 1_000L,
    content: @Composable (tickTime: Long) -> Unit
) {

    var ticks by remember(initTick) {
        mutableStateOf(initTick)
    }

    content.invoke(ticks)

    LaunchedEffect(ticks) {
        val diff = ticks + interval
        delay(interval)
        ticks = diff
    }
}

@Preview(showSystemUi = true, heightDp = 700)
@Composable
fun HomeScreenPreview() {
    HomeScreenContent(homeViewModel = viewModel())
}





