package com.samedtemiz.fitlife.ui.screens.health

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.compose.BurntSienna_500
import com.example.compose.Licorice_500
import com.example.compose.Licorice_600
import com.example.compose.scaleColorList
import com.samedtemiz.fitlife.R
import com.samedtemiz.fitlife.ui.screens.main.BottomBarScreen
import com.samedtemiz.fitlife.viewmodel.HealthViewModel
import java.math.RoundingMode
import java.text.DecimalFormat

@Composable
fun HealthResultScreen(
    healthViewModel: HealthViewModel,
    navController: NavController
) {
    val df = DecimalFormat("#.#")
    df.roundingMode = RoundingMode.UP

    val bmiResult = healthViewModel.calculateBMI()
    val bmi = df.format(bmiResult)

    val calorieResult = healthViewModel.calculateDailyCalorieNeed()
    val calorie = df.format(calorieResult)

    var scaleColor by remember { mutableStateOf(scaleColorList[0]) }
    var scaleCategory by remember { mutableStateOf("Underweight") }

    if (bmiResult >= 18.5 && bmiResult < 21.75) {
        scaleColor = scaleColorList[0]
        scaleCategory = "Normal"
    } else if (bmiResult >= 21.75 && bmiResult < 25) {
        scaleColor = scaleColorList[1]
        scaleCategory = "Normal"
    } else if (bmiResult >= 25 && bmiResult < 27.5) {
        scaleColor = scaleColorList[2]
        scaleCategory = "Overweight"
    } else if (bmiResult >= 27.5 && bmiResult < 30) {
        scaleColor = scaleColorList[3]
        scaleCategory = "Overweight"
    } else if (bmiResult >= 30 && bmiResult < 35) {
        scaleColor = scaleColorList[4]
        scaleCategory = "Obesity"
    } else if (bmiResult >= 35) {
        scaleColor = scaleColorList[5]
        scaleCategory = "Obesity"
    }

    Surface(color = Licorice_600) {
        Image(
            painter = painterResource(
                id = R.drawable.dark_bg
            ),
            contentDescription = "Calorie Screen",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            // Header
            Box(
                Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Your Result",
                    style = TextStyle(
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // BMI
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(Licorice_500)
            ) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(230.dp)
                ) {
                    Image(
                        painter = painterResource(
                            id = R.drawable.bmi_scale
                        ),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp),
                        contentDescription = "BMI Scale",
                        contentScale = ContentScale.Fit
                    )

                    Text(
                        text = bmi,
                        style = TextStyle(
                            fontSize = 44.sp,
                            fontWeight = FontWeight.Bold,
                            color = scaleColor
                        ),
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 30.dp)
                    )
                }

                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        buildAnnotatedString {
                            append("You are in the ")
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    color = scaleColor
                                )
                            ) {
                                append(scaleCategory)
                            }
                            append(" range.")
                        },
                        color = Color.LightGray,
                        fontSize = 22.sp
                    )
                }
            }

            // Calorie
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Licorice_500)
                    .padding(10.dp)
            ) {
                Text(
                    buildAnnotatedString {
                        append("You burn ")
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        ) {
                            append(calorie)
                        }
                        append(" calories during a typical day.")
                    },
                    color = Color.LightGray,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp)
                ) {
                    Image(
                        painter = painterResource(
                            id = R.drawable.calorie_result_vector
                        ),
                        modifier = Modifier
                            .size(125.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        contentDescription = "Calorie",
                        contentScale = ContentScale.Fit
                    )

                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Remember that this estimate is based on your body weight, height, age, gender, and your average level of activity.",
                        color = Color.LightGray,
                        fontSize = 18.sp
                    )
                }

            }

            Box(
                Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Button(
                    onClick = {
                        navController.navigate(BottomBarScreen.Base.Health.route)
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = BurntSienna_500
                    ),
                    modifier = Modifier.fillMaxSize()
                ) {
                    androidx.compose.material3.Text(
                        text = "RE-CALCULATE",
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