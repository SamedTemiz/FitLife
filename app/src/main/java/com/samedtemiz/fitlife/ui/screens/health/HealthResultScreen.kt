package com.samedtemiz.fitlife.ui.screens.health

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.samedtemiz.fitlife.ui.viewmodel.HealthViewModel

@Composable
fun HealthResultScreen(healthViewModel: HealthViewModel) {
        Surface {
            Column {
                Text(text = "BMI: ${healthViewModel.calculateBMI()}")
                Text(text = "CALORIE: ${healthViewModel.calculateDailyCalorieNeed()}")
            }
        }
}