package com.samedtemiz.fitlife.data.auth

import android.util.Patterns

object AuthValidator {
    fun validateFirstName(firstName: String): ValidationResult {
        if (firstName.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "The first name can't be blank"
            )
        }

        if(firstName.length < 3){
            return ValidationResult(
                successful = false,
                errorMessage = "First name must be longer than 2 characters"
            )
        }

        return ValidationResult(
            successful = true
        )
    }

    fun validateLastName(lastName: String): ValidationResult {

        if (lastName.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "The last name can't be blank"
            )
        }

        if(lastName.length < 3){
            return ValidationResult(
                successful = false,
                errorMessage = "Last name must be longer than 2 characters"
            )
        }

        return ValidationResult(
            successful = true
        )
    }

    fun validateEmail(email: String): ValidationResult {
        if(email.isBlank()){
            return ValidationResult(
                successful = false,
                errorMessage = "The email can't be blank"
            )
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return ValidationResult(
                successful = false,
                errorMessage = "That's not a valid email"
            )
        }

        return ValidationResult(
            successful = true
        )
    }

    fun validatePassword(password: String): ValidationResult {
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

    fun validateConfirmPassword(password: String, confirmPassword: String): ValidationResult {
        if(confirmPassword.isBlank()){
            return ValidationResult(
                successful = false,
                errorMessage = "The confirm password can't be blank"
            )
        }
        if(password != confirmPassword){
            return ValidationResult(
                successful = false,
                errorMessage = "The passwords don't match"
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}

data class ValidationResult (
    val successful: Boolean,
    val errorMessage: String? = null
)