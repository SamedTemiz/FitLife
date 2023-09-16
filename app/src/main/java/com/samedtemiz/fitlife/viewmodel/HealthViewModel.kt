package com.samedtemiz.fitlife.viewmodel

import androidx.lifecycle.ViewModel

class HealthViewModel : ViewModel() {
    var gender: Gender = Gender.MALE
    var height: Double = 0.0
    var weight: Double = 0.0
    var age: Int = 0
    var activityLevel: Int = 0

    // Hesaplamalar için gerekli metotları burada tanımlayabilirsiniz.
    // Örneğin, BMI hesaplama metodu:
    fun calculateBMI(): Double {
        // BMI hesaplama mantığını buraya ekleyin.
        val heightInMeters = height / 100.0
        return weight/(heightInMeters*heightInMeters)
    }

    // Günlük kalori ihtiyacı hesaplama metodu:
    fun calculateDailyCalorieNeed(): Double {
        var result = 0.0
        var activity = 0.0

        when (activityLevel) {
            0 -> activity = 1.2
            1 -> activity = 1.375
            2 -> activity = 1.55
            3 -> activity = 1.725
            4 -> activity = 1.9
        }

        if (gender == Gender.MALE) {
            result = ((10 * weight) + (6.25 * height) - (5 * age) + 5) * activity
        } else if (gender == Gender.FEMALE) {
            result = ((10 * weight) + (6.25 * height) - (5 * age) - 161) * activity
        }

        return result
    }
}

enum class Gender {
    MALE,
    FEMALE
}