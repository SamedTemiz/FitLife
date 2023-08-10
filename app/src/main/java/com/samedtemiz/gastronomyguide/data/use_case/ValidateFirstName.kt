package com.samedtemiz.gastronomyguide.data.use_case

class ValidateFirstName {

    fun execute(firstName: String): ValidationResult {
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

}