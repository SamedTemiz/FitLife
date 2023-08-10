package com.samedtemiz.gastronomyguide.data.use_case

import android.util.Patterns

class ValidatePassword {

    fun execute(password: String): ValidationResult{
        if(password.isBlank()){
            return ValidationResult(
                successful = false,
                errorMessage = "The password can't be blank"
            )
        }

        if(password.length < 6){
            return ValidationResult(
                successful = false,
                errorMessage = "Password must be longer than 6 characters"
            )
        }

        val containsLetterAndDigits = password.any{it.isDigit()} && password.any {it.isLetter()}

        if(!containsLetterAndDigits){
            return ValidationResult(
                successful = false,
                errorMessage = "The password needs to contain at least one letter and digit"
            )
        }

        return ValidationResult(
            successful = true
        )
    }


}