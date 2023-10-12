package com.samedtemiz.fitlife.ui.screens

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.provider.Settings
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose.BurntSienna_500
import com.example.compose.BurntSienna_900
import com.example.compose.Comet_100
import com.example.compose.Normal_500
import com.example.compose.Normal_600
import com.example.compose.Obesity_500
import com.example.compose.Obesity_600
import com.example.compose.Overweight_500
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Circle
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.samedtemiz.fitlife.R
import com.samedtemiz.fitlife.components.CircularProgressBar
import com.samedtemiz.fitlife.components.RequestMultiplePermissions
import com.samedtemiz.fitlife.data.model.air.AirQualityResponse
import com.samedtemiz.fitlife.viewmodel.HomeViewModel
import com.samedtemiz.fitlife.viewmodel.LocationData
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

@SuppressLint("StaticFieldLeak")
private lateinit var context: Context

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

        context = LocalContext.current
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isLocationEnabled by remember {
            mutableStateOf(
                locationManager.isProviderEnabled(
                    LocationManager.GPS_PROVIDER
                )
            )
        }

        // Permission request
        RequestMultiplePermissions(
            permissions = listOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            content = {
                LocationSettingsAlertDialog(
                    onEnableLocationServices = {
                        openLocationSettings(context)
                    },
                    isLocationServicesEnabled = isLocationEnabled
                )
            }
        )
    }
}


fun openLocationSettings(context: Context) {
    val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
    context.startActivity(intent)
}

@Composable
fun LocationSettingsAlertDialog(
    onEnableLocationServices: () -> Unit,
    isLocationServicesEnabled: Boolean
) {
    val dialogShown = remember { mutableStateOf(false) }

    if (!isLocationServicesEnabled && !dialogShown.value) {
        AlertDialog(
            onDismissRequest = {
                dialogShown.value = false
            },
            title = {
                Text("Enable Location Services")
            },
            text = {
                Text("Location services are needed for this feature. Would you like to enable them?")
            },
            confirmButton = {
                Button(
                    onClick = {
                        dialogShown.value = false
                        onEnableLocationServices()

                    }, colors = ButtonDefaults.buttonColors(
                        backgroundColor = BurntSienna_500,
                        contentColor = BurntSienna_900
                    )
                ) {
                    Text("Enable")
                }
            }
        )

        LaunchedEffect(dialogShown.value) {
            delay(2000) // 2000 milisaniyelik (2 saniye) gecikme
            dialogShown.value = true
        }
    } else {
        dialogShown.value = true
        HomeScreenContent()
    }
}


@Composable
fun HomeScreenContent(homeViewModel: HomeViewModel = viewModel()) {
    val location by homeViewModel.location.observeAsState()
    val airData by homeViewModel.airQualityData.observeAsState()

    var showButton by remember { mutableStateOf(false) }
    var airQualityShow by remember { mutableStateOf(true) }
    var airScaleColor by remember { mutableStateOf(BurntSienna_500) }

    var categoryBackgroundColor by remember { mutableStateOf(BurntSienna_500) }
    var categoryContext by remember { mutableStateOf("Air quality is considered satisfactory, and air pollution poses little or no risk") }
    Box(Modifier.background(Color.White)) {
        if (location != null && airData != null) {
            airScaleColor = colorToHex(
                airData!!.indexes[0].color.red,
                airData!!.indexes[0].color.green,
                airData!!.indexes[0].color.blue,
                airData!!.indexes[0].color.alpha
            )
            val aqi = airData!!.indexes[0].aqi
            categoryBackgroundColor = resultTheme(aqi).first
            categoryContext = resultTheme(aqi).second

            // Map
            LocationOnGoogleMap(location!!, airScaleColor)

            // Info Card for Air Quality Details
            if (airQualityShow) {
                Box(modifier = Modifier.align(Alignment.Center)) {
                    ElevatedCardComposable(airData!!, categoryBackgroundColor, categoryContext)
                }
            }

            homeViewModel.isProcess = false
            showButton = true
        } else {
            Box(
                Modifier
                    .align(Alignment.Center)
            ) {
                if (homeViewModel.isProcess) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        var ticks by remember { mutableIntStateOf(0) }
                        val delayTime = 10
                        LaunchedEffect(Unit) {
                            while (ticks < delayTime) {
                                delay(1.seconds)
                                ticks++
                            }
                        }
                        if (ticks < delayTime) {
                            CircularProgressIndicator(color = BurntSienna_500)
                            Spacer(modifier = Modifier.height(15.dp))

                            Text(
                                "Waiting for location information...",
                                Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                        } else {
                            LocationSettingsAlertDialog(
                                onEnableLocationServices = { openLocationSettings(context) },
                                isLocationServicesEnabled = false
                            )
                        }
                    }
                }
            }
        }

        if (showButton) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 18.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                ElevatedCard(
                    Modifier
                        .height(50.dp)
                        .width(250.dp),
                    shape = RoundedCornerShape(1.dp)
                ) {
                    Button(
                        onClick = { airQualityShow = !airQualityShow },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Comet_100
                        ),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = "AIR QUALITY DETAILS",
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontFamily = FontFamily(
                                    Font(R.font.avenir_next)
                                ),
                                color = Color(0xFF666666)
                            ),
                        )
                    }
                }
            }
        }
    }
}


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun LocationOnGoogleMap(location: LocationData, airScaleColor: Color) {
    val latlng = LatLng(location.latlng.latitude, location.latlng.longitude)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(latlng, 14f)
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
                        latlng, 14f
                    )
                ), 500
            )
        }

        Marker(
            state = MarkerState(position = latlng),
            title = location.district,
            snippet = "Marker in ${location.country}"
        )

        Circle(
            center = latlng,
            clickable = true,
            fillColor = airScaleColor.copy(alpha = 0.3f),
            radius = 2000.0, // Specify the radius in meters
            strokeColor = Color.Black,
            strokeWidth = 2f,
            tag = "DENEME",
            onClick = {
                // Handle circle click event
            }
        )

    }
}


@Composable
fun ElevatedCardComposable(
    airData: AirQualityResponse,
    scaleColor: Color,
    categoryContext: String
) {
    val air = airData.indexes[0]

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.elevatedCardColors(
            containerColor = Comet_100
        ),
        modifier = Modifier.padding(10.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val percentage = air.aqi.toFloat() / 100

            if (percentage == 0f) {
                Image(
                    painter = painterResource(id = R.drawable.map),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .height(80.dp)
                        .fillMaxWidth(),

                    )
            } else {
                CircularProgressBar(percentage = percentage, number = 100, color = scaleColor)
            }

            //.........................Spacer
            Spacer(modifier = Modifier.height(24.dp))
            //.........................Text: title
            Text(
                text = air.category,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth(),
                style = MaterialTheme.typography.headlineSmall,
                color = BurntSienna_500,
            )

            Spacer(modifier = Modifier.height(8.dp))
            //.........................Text : description
            if (categoryContext.isNotEmpty()) {
                Text(
                    text = categoryContext,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 10.dp, start = 25.dp, end = 25.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xFF666666),
                )
            }
            //.........................Spacer
            Spacer(modifier = Modifier.height(24.dp))

        }
    }
}

fun colorToHex(
    red: Double? = null,
    green: Double? = null,
    blue: Double? = null,
    alpha: Double? = null
): Color {
    val redInt = red?.let { (it * 255).toInt() } ?: 0
    val greenInt = green?.let { (it * 255).toInt() } ?: 0
    val blueInt = blue?.let { (it * 255).toInt() } ?: 0
    val alphaInt = alpha?.let { (it * 255).toInt() } ?: 255

    return Color(red = redInt, green = greenInt, blue = blueInt, alpha = alphaInt)
}

fun resultTheme(aqi: Int): Pair<Color, String> {
    val categoryBackgroundColor: Color
    var categoryContext = ""

    when (aqi) {
        in 0..19 -> {
            categoryBackgroundColor = Obesity_600
            categoryContext =
                "Everyone may begin to experience some adverse health effects, and members of the sensitive groups may experience more serious effects."
        }
        in 20..39 -> {
            categoryBackgroundColor = Obesity_500
            categoryContext =
                "Although the general public is not likely to be affected at this AQI range, people with lung disease, older adults, and children are at greater risk from exposure to ozone, whereas persons with heart and lung disease, older adults, and children are at greater risk from the presence of particles in the air."
        }
        in 40..59 -> {
            categoryBackgroundColor = Overweight_500
            categoryContext =
                "Air quality is acceptable; however, for some pollutants, there may be a moderate health concern for a very small number of people. For example, people who are unusually sensitive to ozone may experience respiratory symptoms."
        }
        in 60..79 -> {
            categoryBackgroundColor = Normal_600
        }
        else -> {
            categoryBackgroundColor = Normal_500
        }
    }


    return categoryBackgroundColor to categoryContext
}





