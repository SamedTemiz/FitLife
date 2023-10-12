package com.samedtemiz.fitlife.ui.screens.health

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.compose.Comet_300
import com.example.compose.Comet_400
import com.example.compose.Licorice_400
import com.example.compose.Licorice_500
import com.example.compose.Licorice_800
import com.example.compose.RegentBlue_700
import com.samedtemiz.fitlife.R
import com.samedtemiz.fitlife.ui.screens.main.BottomBarScreen
import com.samedtemiz.fitlife.viewmodel.Gender
import com.samedtemiz.fitlife.viewmodel.HealthViewModel


@Composable
fun HealthScreen(
    healthViewModel: HealthViewModel,
    navController: NavController
) {
    Surface(
        Modifier
            .fillMaxSize(),
        color = Licorice_800
    ) {
        val scrollState = rememberScrollState()

        var selectedGender by remember { mutableStateOf(Gender.MALE) }
        var heightSlider by remember { mutableFloatStateOf(150f) }
        var weight by remember { mutableIntStateOf(75) }
        var age by remember { mutableIntStateOf(24) }
        var activitySlider by remember { mutableFloatStateOf(2f) }
        val activityList = listOf(
            "Sedentary",
            "Light",
            "Moderate",
            "Active",
            "Very Active",
        )

        Image(
            painter = painterResource(
                id = R.drawable.pear_bg
            ),
            contentDescription = "Health Screen",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )


        Column(
            Modifier
                .fillMaxSize()
                .padding(20.dp, 20.dp, 20.dp, 10.dp)
                .verticalScroll(state = scrollState)
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(125.dp)
            ) {


                Box(
                    Modifier
                        .fillMaxSize()
                        .weight(1f)
                        .clip(shape = RoundedCornerShape(5.dp))
                        .background(
                            color = if (selectedGender == Gender.MALE) Licorice_400 else Licorice_500
                        )
                ) {
                    IconButton(onClick = {
                        selectedGender = Gender.MALE
                    }, modifier = Modifier.fillMaxSize()) {
                        Column(
                            verticalArrangement = Arrangement.SpaceEvenly,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.male),
                                contentDescription = "Visibility",
                                tint = if (selectedGender == Gender.MALE) Color.White else Comet_300,
                                modifier = Modifier.size(65.dp)
                            )
                            Text(
                                text = "MALE",
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    fontFamily = FontFamily(
                                        Font(R.font.avenir_next)
                                    ),
                                    color = if (selectedGender == Gender.MALE) Color.White else Comet_300,
                                )
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.width(5.dp))

                Box(
                    Modifier
                        .fillMaxSize()
                        .weight(1f)
                        .clip(shape = RoundedCornerShape(5.dp))
                        .background(
                            color = if (selectedGender == Gender.FEMALE) Licorice_400 else Licorice_500
                        )
                ) {
                    IconButton(onClick = {
                        selectedGender = Gender.FEMALE
                    }, modifier = Modifier.fillMaxSize()) {
                        Column(
                            verticalArrangement = Arrangement.SpaceEvenly,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.female),
                                contentDescription = "Visibility",
                                tint = if (selectedGender == Gender.FEMALE) Color.White else Comet_300,
                                modifier = Modifier.size(65.dp)
                            )
                            Text(
                                text = "FEMALE",
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    fontFamily = FontFamily(
                                        Font(R.font.avenir_next)
                                    ),
                                    color = if (selectedGender == Gender.FEMALE) Color.White else Comet_300,
                                )
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
            Column(
                Modifier
                    .fillMaxWidth()
                    .height(125.dp)
                    .clip(shape = RoundedCornerShape(5.dp))
                    .background(Licorice_500),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {


                Text(
                    text = "HEIGHT",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = FontFamily(
                            Font(R.font.avenir_next)
                        ),
                        color = Comet_300
                    ),
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "${heightSlider.toInt()}",
                        style = TextStyle(
                            fontSize = 40.sp,
                            fontFamily = FontFamily(
                                Font(R.font.avenir_next)
                            ),
                            color = Color.White
                        )
                    )
                    Text(
                        text = "cm",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = FontFamily(
                                Font(R.font.avenir_next)
                            ),
                            color = Comet_300
                        ),
                    )
                }
                Slider(
                    value = heightSlider,
                    onValueChange = {
                        heightSlider = it
                    },
                    valueRange = 50f..250f,
                    colors = SliderDefaults.colors(
                        thumbColor = Color.White,
                        activeTrackColor = Color.White,
                        inactiveTrackColor = Comet_400,
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(30.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            ) {
                // WEIGHT
                Column(
                    Modifier
                        .fillMaxSize()
                        .weight(1f)
                        .clip(shape = RoundedCornerShape(5.dp))
                        .background(Licorice_500),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {


                    Text(
                        text = "WEIGHT",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = FontFamily(
                                Font(R.font.avenir_next)
                            ),
                            color = Comet_300
                        ),
                    )
                    Text(
                        text = "$weight",
                        style = TextStyle(
                            fontSize = 40.sp,
                            fontFamily = FontFamily(
                                Font(R.font.avenir_next)
                            ),
                            color = Color.White
                        )
                    )

                    Row {
                        OutlinedButton(
                            onClick = {
                                if (weight > 1) weight -= 1
                            },
                            modifier = Modifier.size(55.dp),  //avoid the oval shape
                            shape = CircleShape,
                            contentPadding = PaddingValues(0.dp),  //avoid the little icon
                            colors = ButtonDefaults.outlinedButtonColors(
                                backgroundColor = Licorice_400,
                            )
                        ) {
                            Icon(
                                Icons.Default.Remove,
                                contentDescription = "Remove",
                                tint = Color.White,
                                modifier = Modifier.size(40.dp)
                            )
                        }

                        Spacer(Modifier.width(10.dp))

                        OutlinedButton(
                            onClick = {
                                if (weight < 700) weight += 1
                            },
                            modifier = Modifier.size(55.dp),  //avoid the oval shape
                            shape = CircleShape,
                            contentPadding = PaddingValues(0.dp),  //avoid the little icon
                            colors = ButtonDefaults.outlinedButtonColors(
                                backgroundColor = Licorice_400,
                            )
                        ) {
                            Icon(
                                Icons.Default.Add,
                                contentDescription = "Add",
                                tint = Color.White,
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    }
                }

                Spacer(Modifier.width(5.dp))

                // AGE
                Column(
                    Modifier
                        .fillMaxSize()
                        .weight(1f)
                        .clip(shape = RoundedCornerShape(5.dp))
                        .background(Licorice_500),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {


                    Text(
                        text = "AGE",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = FontFamily(
                                Font(R.font.avenir_next)
                            ),
                            color = Comet_300
                        ),
                    )
                    Text(
                        text = "$age",
                        style = TextStyle(
                            fontSize = 40.sp,
                            fontFamily = FontFamily(
                                Font(R.font.avenir_next)
                            ),
                            color = Color.White
                        )
                    )

                    Row {
                        OutlinedButton(
                            onClick = {
                                if (age > 1) age -= 1
                            },
                            modifier = Modifier.size(55.dp),  //avoid the oval shape
                            shape = CircleShape,
                            contentPadding = PaddingValues(0.dp),  //avoid the little icon
                            colors = ButtonDefaults.outlinedButtonColors(
                                backgroundColor = Licorice_400,
                            )
                        ) {
                            Icon(
                                Icons.Default.Remove,
                                contentDescription = "Remove",
                                tint = Color.White,
                                modifier = Modifier.size(40.dp)
                            )
                        }

                        Spacer(Modifier.width(10.dp))

                        OutlinedButton(
                            onClick = {
                                if (age < 120) age += 1
                            },
                            modifier = Modifier.size(55.dp),  //avoid the oval shape
                            shape = CircleShape,
                            contentPadding = PaddingValues(0.dp),  //avoid the little icon
                            colors = ButtonDefaults.outlinedButtonColors(
                                backgroundColor = Licorice_400,
                            )
                        ) {
                            Icon(
                                Icons.Default.Add,
                                contentDescription = "Add",
                                tint = Color.White,
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
            Column(
                Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .clip(shape = RoundedCornerShape(5.dp))
                    .background(Licorice_500),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {


                Text(
                    text = "ACTIVITY",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = FontFamily(
                            Font(R.font.avenir_next)
                        ),
                        color = Comet_300
                    ),
                )
                Text(
                    text = activityList[activitySlider.toInt()],
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontFamily = FontFamily(
                            Font(R.font.avenir_next)
                        ),
                        color = Color.White
                    )
                )
                Slider(
                    value = activitySlider,
                    onValueChange = {
                        activitySlider = it
                    },
                    steps = 3,
                    valueRange = 0f..4f,
                    colors = SliderDefaults.colors(
                        thumbColor = Color.White,
                        activeTrackColor = Color.White,
                        inactiveTrackColor = Comet_400,
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(30.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Button(
                    onClick = {
                        // ViewModel'e kullanıcının girdilerini güncellemek için
                        healthViewModel.gender = selectedGender
                        healthViewModel.height = heightSlider.toDouble()
                        healthViewModel.weight = weight.toDouble()
                        healthViewModel.age = age
                        healthViewModel.activityLevel = activitySlider.toInt()

                        navController.navigate(BottomBarScreen.Detail.HealthResult.route)
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = RegentBlue_700
                    ),
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = "CALCULATE BMI & CALORIE",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = FontFamily(
                                Font(R.font.avenir_next)
                            ),
                            color = Color.White
                        ),
                    )
                }
            }
        }
    }
}

//@Preview(showSystemUi = true, heightDp = 700)
//@Composable
//fun DefaultPreview() {
//    HealthScreen()
//}