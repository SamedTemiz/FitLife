package com.samedtemiz.fitlife.ui.screens

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.BurntSienna_600
import com.example.compose.Licorice_400
import com.example.compose.Licorice_600
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.samedtemiz.fitlife.R
import com.samedtemiz.fitlife.ui.app.checkPermissionFor
import com.samedtemiz.fitlife.ui.app.toastMessage
import com.samedtemiz.fitlife.ui.viewmodel.HomeViewModel

//@Preview(showSystemUi = true, heightDp = 700)
//@Composable
//fun HomeScreenPreview() {
//    HomeScreen()
//}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {
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

        // Content
        val locationPermission = android.Manifest.permission.ACCESS_FINE_LOCATION
        var isLocationPermissionGranted by remember {
            mutableStateOf(
                checkPermissionFor(locationPermission)
            )
        }

        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission(),
            onResult = { isGranted ->
                if (isGranted) {
                    Log.d("TAG", "Location $isGranted")
                    isLocationPermissionGranted = true
                } else {
                    toastMessage("Location permission needed")
                }
            })

        if (isLocationPermissionGranted) {
            // İzin verildi
            HomeScreenContent(homeViewModel)
        } else {
            // İzin verilmedi
            var clickCount by remember { mutableIntStateOf(0) }
            Box(
                contentAlignment = Alignment.Center
            ) {
                val gradientBgColor = listOf(Licorice_600, Licorice_400)
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .padding(horizontal = 30.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(brush = Brush.horizontalGradient(colors = gradientBgColor))
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {

                        Button(
                            onClick = {
                                launcher.launch(locationPermission)
                                clickCount++
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = BurntSienna_600
                            ),
                            modifier = Modifier.height(50.dp)
                        ) {
                            Text(text = "Give Permission", fontSize = 16.sp)
                        }
                        if (clickCount > 1) {
                            Text(
                                text = "*If the permission process does not work, you need to give permission from the settings.*",
                                textAlign = TextAlign.Center,
                                color = Color.White,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 20.dp, vertical = 5.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun HomeScreenContent(homeViewModel: HomeViewModel) {
    Column {

    }
}





